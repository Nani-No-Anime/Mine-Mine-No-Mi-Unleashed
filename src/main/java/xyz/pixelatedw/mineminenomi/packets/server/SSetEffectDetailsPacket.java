package xyz.pixelatedw.mineminenomi.packets.server;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;

import java.util.function.Supplier;



public class SSetEffectDetailsPacket
{
  private int entityId;
  private int effectId;
  private int duration;
  
  public SSetEffectDetailsPacket() {}
  
  public SSetEffectDetailsPacket(int entityId, EffectInstance instance) {
    this.entityId = entityId;
    this.effectId = (byte)(Effect.getId(instance.getPotion()) & 0xFF);
    this.duration = instance.getDuration();
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeInt(this.effectId);
    buffer.writeInt(this.duration);
  }

  
  public static SSetEffectDetailsPacket decode(PacketBuffer buffer) {
    SSetEffectDetailsPacket msg = new SSetEffectDetailsPacket();
    msg.entityId = buffer.readInt();
    msg.effectId = buffer.readInt();
    msg.duration = buffer.readInt();
    return msg;
  }

  
  public static void handle(SSetEffectDetailsPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SSetEffectDetailsPacket message) {
      Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
      
      if (target == null || !(target instanceof LivingEntity)) {
        return;
      }
      LivingEntity livingTarget = (LivingEntity)target;
      Effect effect = Effect.get(message.effectId & 0xFF);
      
      ((EffectInstanceMixin)livingTarget.getActivePotionEffect(effect)).setDuration(message.duration);
    }
  }
}


