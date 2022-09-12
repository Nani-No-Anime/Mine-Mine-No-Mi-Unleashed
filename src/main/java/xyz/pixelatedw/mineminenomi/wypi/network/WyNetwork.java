package xyz.pixelatedw.mineminenomi.wypi.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class WyNetwork
{
  private static int packet = 0;
  //private static final String PROTOCOL_VERSION = "1U1";
  private static final String PROTOCOL_VERSION = "1";
  public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(APIConfig.projectId, "main_channel"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION

      
      ::equals, PROTOCOL_VERSION
      ::equals); static { Objects.requireNonNull(PROTOCOL_VERSION); Objects.requireNonNull(PROTOCOL_VERSION); }



  
  public static <MSG> void registerPacket(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
    INSTANCE.registerMessage(packet++, messageType, encoder, decoder, messageConsumer);
  }

  
  public static <MSG> void sendToServer(MSG msg) {
    INSTANCE.sendToServer(msg);
  }

  
  public static <MSG> void sendTo(MSG msg, PlayerEntity player) {
    if (!(player instanceof ServerPlayerEntity)) {
      return;
    }
    INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)player), msg);
  }

  
  public static <MSG> void sendToAll(MSG msg) {
    INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
  }

  
  public static <MSG> void sendToAllTrackingAndSelf(MSG msg, LivingEntity tracked) {
    INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> tracked), msg);
  }


  
  public static <MSG> void sendToAllAround(MSG msg, Entity sender) {
    try {
      INSTANCE.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(sender.getPosX(), sender.getPosY(), sender.getPosZ(), 250.0D, sender.dimension)), msg);
      if (sender instanceof ServerPlayerEntity) {
        sendTo(msg, (PlayerEntity)sender);
      }
    } catch (Exception e) {
      
      e.printStackTrace();
      return;
    } 
  }
}


