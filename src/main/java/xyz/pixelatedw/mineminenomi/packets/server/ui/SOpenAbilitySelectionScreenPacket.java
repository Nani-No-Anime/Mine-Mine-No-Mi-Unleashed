/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SOpenAbilitySelectionScreenPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static SOpenAbilitySelectionScreenPacket decode(PacketBuffer buffer) {
/* 26 */     SOpenAbilitySelectionScreenPacket msg = new SOpenAbilitySelectionScreenPacket();
/* 27 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SOpenAbilitySelectionScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 32 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 33 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 34 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenAbilitySelectionScreenPacket message) {
/* 42 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*    */       
/* 44 */       if (clientPlayerEntity == null) {
/*    */         return;
/*    */       }
/* 47 */       Minecraft.getInstance().displayGuiScreen((Screen)new SelectHotbarAbilitiesScreen((PlayerEntity)clientPlayerEntity));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenAbilitySelectionScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */