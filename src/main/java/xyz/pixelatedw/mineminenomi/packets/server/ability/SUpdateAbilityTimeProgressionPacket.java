package xyz.pixelatedw.mineminenomi.packets.server.ability;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.function.Supplier;







public class SUpdateAbilityTimeProgressionPacket
{
  private int entityId;
  private int abilityId;
  private double timeProgression;
  
  public SUpdateAbilityTimeProgressionPacket() {}
  
  public SUpdateAbilityTimeProgressionPacket(PlayerEntity player, Ability ability) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    this.entityId = player.getEntityId();
    this.abilityId = props.getEquippedAbilitySlot(ability);
    
    this.timeProgression = player.getAttribute(ModAttributes.TIME_PROGRESSION).getValue();
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeInt(this.abilityId);
    
    buffer.writeDouble(this.timeProgression);
  }

  
  public static SUpdateAbilityTimeProgressionPacket decode(PacketBuffer buffer) {
    SUpdateAbilityTimeProgressionPacket msg = new SUpdateAbilityTimeProgressionPacket();
    msg.entityId = buffer.readInt();
    msg.abilityId = buffer.readInt();
    
    msg.timeProgression = buffer.readDouble();
    
    return msg;
  }

  
  public static void handle(SUpdateAbilityTimeProgressionPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SUpdateAbilityTimeProgressionPacket message) {
      Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
      if (target == null || !(target instanceof PlayerEntity) || message.abilityId < 0) {
        return;
      }
      IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
      Ability ability = props.getEquippedAbility(message.abilityId);
      
      if (ability == null) {
        return;
      }
      ability.setTimeProgression(message.timeProgression);
    }
  }
}


