/*     */ package xyz.pixelatedw.mineminenomi.wypi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.multiplayer.ServerData;
/*     */ import net.minecraft.network.play.server.SDisconnectPacket;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WyServerProtection
/*     */ {
/*     */   protected static boolean IS_CHECKED = false;
/*     */   
/*     */   public static BannedServer getBannedServerInfo(String ip) throws IOException {
/*  25 */     String apiURL = "/server-check/" + ip;
/*  26 */     BannedServer result = WyHelper.<BannedServer>sendGET(apiURL, BannedServer.class);
/*     */     
/*  28 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private static class BannedServer
/*     */   {
/*     */     private String reason;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ProtectionEvents
/*     */   {
/*     */     private static final Function<Pair<ClientPlayerEntity, String>, TimerTask> TASK = pair -> new TimerTask()
/*     */       {
/*     */         public void run() {
/*  43 */           StringTextComponent message = new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "ERROR! \n\n " + TextFormatting.RESET + "This server has been closed due to: " + (String)pair.getRight());
/*  44 */           ((ClientPlayerEntity)pair.getLeft()).connection.handleDisconnect(new SDisconnectPacket((ITextComponent)message));
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public void onEntityJoinWorld(TickEvent.ClientTickEvent event) {
/*  52 */       ClientPlayerEntity player = (Minecraft.getInstance()).player;
/*     */       
/*  54 */       if (player == null || (Minecraft.getInstance().getCurrentServerData() == null && !WyPatreon.isPromoBuild()) || player.ticksExisted % 6000 != 0) {
/*     */         return;
/*     */       }
/*  57 */       ServerData server = Minecraft.getInstance().getCurrentServerData();
/*     */       
/*  59 */       String serverIp = "";
/*  60 */       if (server != null)
/*  61 */         serverIp = server.serverIP; 
/*  62 */       serverIp = serverIp.split(":")[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  86 */       WyServerProtection.BannedServer info = null;
/*     */ 
/*     */       
/*     */       try {
/*  90 */         info = WyServerProtection.getBannedServerInfo(serverIp);
/*     */       }
/*  92 */       catch (IOException e) {
/*     */         
/*  94 */         e.printStackTrace();
/*     */       } 
/*     */       
/*  97 */       if (info != null) {
/*     */         
/*  99 */         String reason = info.reason;
/* 100 */         (new Timer()).schedule(TASK.apply(Pair.of(player, reason)), 200L);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\WyServerProtection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */