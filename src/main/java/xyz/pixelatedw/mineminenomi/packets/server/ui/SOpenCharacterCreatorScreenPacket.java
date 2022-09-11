package xyz.pixelatedw.mineminenomi.packets.server.ui;

import java.util.function.Supplier;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.CharacterCreatorScreen;



public class SOpenCharacterCreatorScreenPacket
{
  private boolean hasRandomizedRace;
  
  public SOpenCharacterCreatorScreenPacket() {}
  
  public SOpenCharacterCreatorScreenPacket(boolean hasRandomizedRace) {
    this.hasRandomizedRace = hasRandomizedRace;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.hasRandomizedRace);
  }

  
  public static SOpenCharacterCreatorScreenPacket decode(PacketBuffer buffer) {
    SOpenCharacterCreatorScreenPacket msg = new SOpenCharacterCreatorScreenPacket();
    msg.hasRandomizedRace = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(SOpenCharacterCreatorScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenCharacterCreatorScreenPacket message) {
      CharacterCreatorScreen.open(message.hasRandomizedRace);
    }
  }
}


