package xyz.pixelatedw.mineminenomi.wypi.network.packets.server;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;





public class SSyncAbilityDataPacket
{
  private int entityId;
  private INBT data;
  
  public SSyncAbilityDataPacket() {}
  
  public SSyncAbilityDataPacket(int entityId, IAbilityData abiltiyDataProps) {
    this.data = (INBT)new CompoundNBT();
    this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, abiltiyDataProps, null);
    this.entityId = entityId;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeCompoundTag((CompoundNBT)this.data);
  }

  
  public static SSyncAbilityDataPacket decode(PacketBuffer buffer) {
    SSyncAbilityDataPacket msg = new SSyncAbilityDataPacket();
    msg.entityId = buffer.readInt();
    msg.data = (INBT)buffer.readCompoundTag();
    return msg;
  }

  
  public static void handle(SSyncAbilityDataPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SSyncAbilityDataPacket message) {
      Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
      if (target == null || !(target instanceof LivingEntity)) {
        return;
      }
      IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
      AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, props, null, message.data);
    }
  }
}


