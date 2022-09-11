package xyz.pixelatedw.mineminenomi.packets.client.ability;

import java.util.function.Supplier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SChangeCombatBarPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class CChangeCombatBarPacket
{
  private int dir = 0;

  
  public CChangeCombatBarPacket() {}
  
  public CChangeCombatBarPacket(int dir) {
    this.dir = dir;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.dir);
  }

  
  public static CChangeCombatBarPacket decode(PacketBuffer buffer) {
    CChangeCombatBarPacket msg = new CChangeCombatBarPacket();
    msg.dir = buffer.readInt();
    return msg;
  }

  
  public static void handle(CChangeCombatBarPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();

            
            IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);

            
            if (message.dir == 0) {
              if ((abilityProps.getCombatBarSet() + 1) * 8 < 8 * CommonConfig.INSTANCE.getAbilityBars()) {
                abilityProps.nextCombatBarSet();
              } else {
                abilityProps.setCombatBarSet(0);
              } 
            } else if (abilityProps.getCombatBarSet() > 0) {
              abilityProps.prevCombatBarSet();
            } else {
              abilityProps.setCombatBarSet(CommonConfig.INSTANCE.getAbilityBars() - 1);
            } 

            
            WyNetwork.sendTo(new SChangeCombatBarPacket(abilityProps.getCombatBarSet()), (PlayerEntity)serverPlayerEntity);
          });
    }
    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


