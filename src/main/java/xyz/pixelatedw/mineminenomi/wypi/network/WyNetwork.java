/*    */ package xyz.pixelatedw.mineminenomi.wypi.network;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import net.minecraftforge.fml.network.NetworkRegistry;
/*    */ import net.minecraftforge.fml.network.PacketDistributor;
/*    */ import net.minecraftforge.fml.network.simple.SimpleChannel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ 
/*    */ 
/*    */ public class WyNetwork
/*    */ {
/* 22 */   private static int packet = 0;
/* 23 */   private static final String PROTOCOL_VERSION = Integer.toString(1);
/* 24 */   public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(APIConfig.projectId, "main_channel"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION
/*    */ 
/*    */       
/* 27 */       ::equals, PROTOCOL_VERSION
/* 28 */       ::equals); static { Objects.requireNonNull(PROTOCOL_VERSION); Objects.requireNonNull(PROTOCOL_VERSION); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static <MSG> void registerPacket(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
/* 34 */     INSTANCE.registerMessage(packet++, messageType, encoder, decoder, messageConsumer);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToServer(MSG msg) {
/* 39 */     INSTANCE.sendToServer(msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendTo(MSG msg, PlayerEntity player) {
/* 44 */     if (!(player instanceof ServerPlayerEntity)) {
/*    */       return;
/*    */     }
/* 47 */     INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)player), msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToAll(MSG msg) {
/* 52 */     INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToAllTrackingAndSelf(MSG msg, LivingEntity tracked) {
/* 57 */     INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> tracked), msg);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToAllAround(MSG msg, Entity sender) {
/*    */     try {
/* 64 */       INSTANCE.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(sender.getPosX(), sender.getPosY(), sender.getPosZ(), 250.0D, sender.dimension)), msg);
/* 65 */       if (sender instanceof ServerPlayerEntity) {
/* 66 */         sendTo(msg, (PlayerEntity)sender);
/*    */       }
/* 68 */     } catch (Exception e) {
/*    */       
/* 70 */       e.printStackTrace();
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\network\WyNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */