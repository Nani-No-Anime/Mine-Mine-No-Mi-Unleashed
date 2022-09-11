package xyz.pixelatedw.mineminenomi.packets.client.ability;

import java.util.function.Supplier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class CRemoveAbilityPacket
{
  private int slot;
  
  public CRemoveAbilityPacket() {}
  
  public CRemoveAbilityPacket(int id) {
    this.slot = id;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.slot);
  }

  
  public static CRemoveAbilityPacket decode(PacketBuffer buffer) {
    CRemoveAbilityPacket msg = new CRemoveAbilityPacket();
    msg.slot = buffer.readInt();
    return msg;
  }

  
  public static void handle(CRemoveAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
            
            Ability ability = abilityDataProps.getEquippedAbility(message.slot);
            if (ability == null) {
              return;
            }
            if (ability instanceof ContinuousAbility && ((ContinuousAbility)ability).isContinuous()) {
              ((ContinuousAbility)ability).endContinuity((PlayerEntity)serverPlayerEntity);
            } else if (ability instanceof ChargeableAbility && ((ChargeableAbility)ability).isCharging()) {
              ((ChargeableAbility)ability).endCharging((PlayerEntity)serverPlayerEntity);
            } else if (ability.isOnCooldown()) {
              ability.stopCooldown((PlayerEntity)serverPlayerEntity);
            } 
            abilityDataProps.setEquippedAbility(message.slot, null);
            if (abilityDataProps.getEquippedAbilitySlot(ability) >= 0) {
              WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)serverPlayerEntity, ability), (LivingEntity)serverPlayerEntity);
            }
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


