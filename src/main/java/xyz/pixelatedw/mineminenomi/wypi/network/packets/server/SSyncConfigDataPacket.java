package xyz.pixelatedw.mineminenomi.wypi.network.packets.server;

import java.util.function.Supplier;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;

public class SSyncConfigDataPacket {
  private int DorikiLimit = 0;

  public SSyncConfigDataPacket() {}

  public SSyncConfigDataPacket(int DorikiLimit) { this.DorikiLimit = DorikiLimit; }

  public void encode(PacketBuffer buffer) { buffer.writeInt(DorikiLimit); }

  public static SSyncConfigDataPacket decode(PacketBuffer buffer) {
    SSyncConfigDataPacket msg = new SSyncConfigDataPacket();
    msg.DorikiLimit = buffer.readInt();
    return msg;
  }

  public static void handle(SSyncConfigDataPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context) ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
      ((NetworkEvent.Context) ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }

    ((NetworkEvent.Context) ctx.get()).setPacketHandled(true);
  }

  public static class ClientHandler {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SSyncConfigDataPacket message) {
      System.out.println("DorikiLimit from Server" + message.DorikiLimit);
      CommonConfig.INSTANCE.setDorikiLimit(message.DorikiLimit);
    }
  }
}
