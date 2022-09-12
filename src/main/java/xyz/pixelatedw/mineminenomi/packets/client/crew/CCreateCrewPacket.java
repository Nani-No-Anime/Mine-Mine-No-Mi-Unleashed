package xyz.pixelatedw.mineminenomi.packets.client.crew;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.function.Supplier;


public class CCreateCrewPacket
{
  private String name;
  
  public CCreateCrewPacket() {}
  
  public CCreateCrewPacket(String name) {
    this.name = name;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.name.length());
    buffer.writeString(this.name);
  }

  
  public static CCreateCrewPacket decode(PacketBuffer buffer) {
    CCreateCrewPacket msg = new CCreateCrewPacket();
    int len = buffer.readInt();
    msg.name = buffer.readString(len);
    return msg;
  }

  
  public static void handle(CCreateCrewPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
            
            ExtendedWorldData worldProps = ExtendedWorldData.get(((PlayerEntity)serverPlayerEntity).world);
            boolean hasSakeBottle = (!serverPlayerEntity.getHeldItemMainhand().isEmpty() && serverPlayerEntity.getHeldItemMainhand().getItem().equals(ModItems.SAKE_BOTTLE));
            
            boolean isAlreadyInCrew = (worldProps.getCrewWithMember(serverPlayerEntity.getUniqueID()) != null);
            
            if (!hasSakeBottle || isAlreadyInCrew || !props.isPirate()) {
              return;
            }
            
            Crew crew = new Crew(message.name, (LivingEntity)serverPlayerEntity);
            
            worldProps.addCrew(crew);
            
            crew.create(((PlayerEntity)serverPlayerEntity).world);
            if (CommonConfig.INSTANCE.isCrewWorldMessageEnabled()) {
              TranslationTextComponent newCrewMsg = new TranslationTextComponent(ModI18n.CREW_MESSAGE_NEW_CREW, new Object[] { message.name });
              for (PlayerEntity target : ((PlayerEntity)serverPlayerEntity).world.getPlayers()) {
                target.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.GOLD + newCrewMsg.getFormattedText()));
              }
            } 
            WyNetwork.sendToAll(new SSyncWorldDataPacket(worldProps));
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


