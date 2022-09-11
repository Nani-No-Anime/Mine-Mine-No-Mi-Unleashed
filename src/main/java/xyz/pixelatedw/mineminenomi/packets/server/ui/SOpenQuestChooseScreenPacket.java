package xyz.pixelatedw.mineminenomi.packets.server.ui;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
import xyz.pixelatedw.mineminenomi.screens.TrainerScreen;



public class SOpenQuestChooseScreenPacket
{
  private int questGiverEntity;
  
  public SOpenQuestChooseScreenPacket() {}
  
  public SOpenQuestChooseScreenPacket(int questGiverEntity) {
    this.questGiverEntity = questGiverEntity;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.questGiverEntity);
  }

  
  public static SOpenQuestChooseScreenPacket decode(PacketBuffer buffer) {
    SOpenQuestChooseScreenPacket msg = new SOpenQuestChooseScreenPacket();
    msg.questGiverEntity = buffer.readInt();
    return msg;
  }

  
  public static void handle(SOpenQuestChooseScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenQuestChooseScreenPacket message) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      Entity questGiver = (Minecraft.getInstance()).world.getEntityByID(message.questGiverEntity);
      
      if (!(questGiver instanceof TrainerEntity)) {
        return;
      }
      Minecraft.getInstance().displayGuiScreen((Screen)new TrainerScreen((PlayerEntity)clientPlayerEntity, (TrainerEntity)questGiver, ((TrainerEntity)questGiver).getAvailableQuests((PlayerEntity)clientPlayerEntity)));
    }
  }
}


