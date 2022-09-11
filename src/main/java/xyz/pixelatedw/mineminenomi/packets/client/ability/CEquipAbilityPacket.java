package xyz.pixelatedw.mineminenomi.packets.client.ability;

import java.util.function.Supplier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public class CEquipAbilityPacket
{
  private int slot;
  private String abilityName;
  
  public CEquipAbilityPacket() {}
  
  public CEquipAbilityPacket(int id, Ability ability) {
    this.slot = id;
    this.abilityName = WyHelper.getResourceName(ability.getName());
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.slot);
    buffer.writeInt(this.abilityName.length());
    buffer.writeString(this.abilityName, this.abilityName.length());
  }

  
  public static CEquipAbilityPacket decode(PacketBuffer buffer) {
    CEquipAbilityPacket msg = new CEquipAbilityPacket();
    msg.slot = buffer.readInt();
    int len = buffer.readInt();
    msg.abilityName = buffer.readString(len);
    return msg;
  }

  
  public static void handle(CEquipAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
            
            Ability ability = ((Ability)GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation("mineminenomi", message.abilityName))).create();
            
            abilityDataProps.setEquippedAbility(message.slot, ability);
            WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)serverPlayerEntity, ability), (LivingEntity)serverPlayerEntity);
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


