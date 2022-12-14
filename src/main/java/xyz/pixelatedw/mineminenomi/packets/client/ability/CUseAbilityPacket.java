package xyz.pixelatedw.mineminenomi.packets.client.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.events.passives.DoaPassiveEvents;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.function.Supplier;






public class CUseAbilityPacket
{
  private int slot;
  
  public CUseAbilityPacket() {}
  
  public CUseAbilityPacket(int slot) {
    this.slot = slot;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.slot);
  }

  
  public static CUseAbilityPacket decode(PacketBuffer buffer) {
    CUseAbilityPacket msg = new CUseAbilityPacket();
    msg.slot = buffer.readInt();
    return msg;
  }

  
  public static void handle(CUseAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();

            
            ((PlayerEntity)serverPlayerEntity).world.getProfiler().startSection("abilityUse");

            
            IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);

            
            Ability abl = abilityDataProps.getEquippedAbility(message.slot);

            
            if (abl == null || serverPlayerEntity.isSpectator() || !abl.canUse((PlayerEntity)serverPlayerEntity)) {
              return;
            }

            
            if (message.slot > 8 * CommonConfig.INSTANCE.getAbilityBars()) {
              return;
            }

            
            try {
              if (!(abl instanceof xyz.pixelatedw.mineminenomi.abilities.doa.AirDoorAbility) && DoaPassiveEvents.isInsideDoor((PlayerEntity)serverPlayerEntity)) {
                return;
              }

              
              if (abl instanceof ChargeableAbility && abl.isCharging() && !((ChargeableAbility)abl).isCancelable()) {
                return;
              }

              
              if (abl instanceof ContinuousAbility && !abl.isContinuous() && !(abl instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility)) {
                for (Ability ability : abilityDataProps.getEquippedAbilities()) {
                  if (ability instanceof ContinuousAbility && ability.isContinuous() && !(ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility)) {
                    if (CommonConfig.INSTANCE.getStopContinuousAbility()) {
                      ((ContinuousAbility)ability).endContinuity((PlayerEntity)serverPlayerEntity);
                    } else {
                      return;
                    } 
                  }
                } 
              }
              
              if (abl instanceof ZoanAbility && !abl.isContinuous()) {
                for (Ability ability : abilityDataProps.getEquippedAbilities()) {
                  if (ability instanceof ZoanAbility && ability.isContinuous()) {
                    if (CommonConfig.INSTANCE.getStopContinuousAbility()) {
                      ((ZoanAbility)ability).endContinuity((PlayerEntity)serverPlayerEntity);
                    } else {
                      return;
                    } 
                  }
                } 
              }
              
              abl.use((PlayerEntity)serverPlayerEntity);
            } catch (Exception e) {
              e.printStackTrace();
              
              abl.startCooldown((PlayerEntity)serverPlayerEntity);
            } 
            
            ((PlayerEntity)serverPlayerEntity).world.getProfiler().endSection();
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


