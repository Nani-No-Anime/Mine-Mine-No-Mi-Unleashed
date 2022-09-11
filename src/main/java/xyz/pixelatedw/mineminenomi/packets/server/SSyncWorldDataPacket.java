package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;




public class SSyncWorldDataPacket
{
  private CompoundNBT data;
  
  public SSyncWorldDataPacket() {}
  
  public SSyncWorldDataPacket(ExtendedWorldData worldData) {
    this.data = new CompoundNBT();
    this.data = worldData.write(this.data);
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeCompoundTag(this.data);
  }

  
  public static SSyncWorldDataPacket decode(PacketBuffer buffer) {
    SSyncWorldDataPacket msg = new SSyncWorldDataPacket();
    msg.data = buffer.readCompoundTag();
    return msg;
  }

  
  public static void handle(SSyncWorldDataPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SSyncWorldDataPacket message) {
      ClientWorld clientWorld = (Minecraft.getInstance()).world;
      ExtendedWorldData worldData = ExtendedWorldData.get((World)clientWorld);
      if (worldData != null)
        worldData.read(message.data); 
    }
  }
}


