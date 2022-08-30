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
/*    */ public class ChallengeCountdownMessageThread
/*    */   implements Runnable {
/*    */   private Thread worker;
/* 17 */   private final AtomicBoolean running = new AtomicBoolean(false);
/*    */   
/*    */   private ServerPlayerEntity player;
/*    */   
/*    */   private String challengeName;
/*    */   
/*    */   public ChallengeCountdownMessageThread(ServerPlayerEntity player, String challengeName) {
/* 24 */     this.player = player;
/* 25 */     this.challengeName = challengeName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {
/* 30 */     this.worker = new Thread(this, "Countdown Message Thread");
/* 31 */     this.worker.setDaemon(true);
/* 32 */     this.worker.start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stop() {
/* 37 */     this.running.set(false);
/* 38 */     Thread.currentThread().interrupt();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 44 */     this.running.set(true);
/*    */ 
/*    */     
/*    */     try {
/* 48 */       StringTextComponent stringTextComponent = new StringTextComponent(TextFormatting.GOLD + this.challengeName);
/* 49 */       ITextComponent titleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent, (Entity)this.player, 0);
/* 50 */       this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.TITLE, titleComponent));
/*    */       
/* 52 */       int i = 3;
/* 53 */       while (i >= 0)
/*    */       {
/* 55 */         String countdown = (i > 0) ? (i + "") : "FIGHT";
/* 56 */         StringTextComponent stringTextComponent1 = new StringTextComponent(TextFormatting.GOLD + "" + countdown);
/* 57 */         ITextComponent subtitleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent1, (Entity)this.player, 0);
/* 58 */         this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.SUBTITLE, subtitleComponent));
/* 59 */         i--;
/* 60 */         Thread.sleep(1200L);
/*    */       }
/*    */     
/* 63 */     } catch (CommandSyntaxException|InterruptedException e) {
/*    */       
/* 65 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 68 */     stop();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\ChallengeCountdownMessageThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */