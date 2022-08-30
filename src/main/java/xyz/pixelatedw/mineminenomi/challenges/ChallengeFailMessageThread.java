/*    */ package xyz.pixelatedw.mineminenomi.challenges;
/*    */ 
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.STitlePacket;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextComponentUtils;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ 
/*    */ public class ChallengeFailMessageThread
/*    */   implements Runnable {
/*    */   private Thread worker;
/* 17 */   private final AtomicBoolean running = new AtomicBoolean(false);
/*    */   
/*    */   private ServerPlayerEntity player;
/*    */ 
/*    */   
/*    */   public ChallengeFailMessageThread(ServerPlayerEntity player) {
/* 23 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {
/* 28 */     this.worker = new Thread(this, "Fail Message Thread");
/* 29 */     this.worker.setDaemon(true);
/* 30 */     this.worker.start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stop() {
/* 35 */     this.running.set(false);
/* 36 */     Thread.currentThread().interrupt();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 42 */     this.running.set(true);
/*    */ 
/*    */     
/*    */     try {
/* 46 */       StringTextComponent stringTextComponent = new StringTextComponent(TextFormatting.GOLD + "Challenge Failed!");
/* 47 */       ITextComponent titleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent, (Entity)this.player, 0);
/* 48 */       this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.TITLE, titleComponent));
/*    */     }
/* 50 */     catch (CommandSyntaxException e) {
/*    */       
/* 52 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 55 */     stop();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\ChallengeFailMessageThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */