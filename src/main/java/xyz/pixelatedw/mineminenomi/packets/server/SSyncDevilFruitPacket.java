package xyz.pixelatedw.mineminenomi.packets.server;

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
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

import java.util.function.Supplier;



public class SSyncDevilFruitPacket
{
  private int entityId;
  private INBT data;
  
  public SSyncDevilFruitPacket() {}
  
  public SSyncDevilFruitPacket(int entityId, IDevilFruit props) {
    this.data = (INBT)new CompoundNBT();
    this.data = DevilFruitCapability.INSTANCE.getStorage().writeNBT(DevilFruitCapability.INSTANCE, props, null);
    this.entityId = entityId;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeCompoundTag((CompoundNBT)this.data);
  }

  
  public static SSyncDevilFruitPacket decode(PacketBuffer buffer) {
    SSyncDevilFruitPacket msg = new SSyncDevilFruitPacket();
    msg.entityId = buffer.readInt();
    msg.data = (INBT)buffer.readCompoundTag();
    return msg;
  }

  
  public static void handle(SSyncDevilFruitPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SSyncDevilFruitPacket message) {
      Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
      if (target == null || !(target instanceof LivingEntity)) {
        return;
      }
      IDevilFruit props = DevilFruitCapability.get((LivingEntity)target);
      DevilFruitCapability.INSTANCE.getStorage().readNBT(DevilFruitCapability.INSTANCE, props, null, message.data);
    }
  }
}


