/*    */ package xyz.pixelatedw.mineminenomi.challenges;
/*    */ 
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.STitlePacket;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextComponentUtils;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChallengeWinMessageThread
/*    */   implements Runnable {
/*    */   private Thread worker;
/* 19 */   private final AtomicBoolean running = new AtomicBoolean(false);
/*    */   
/*    */   private ServerPlayerEntity player;
/*    */   
/*    */   private List<String> rewards;
/*    */   
/*    */   public ChallengeWinMessageThread(ServerPlayerEntity player, List<String> rewards) {
/* 26 */     this.player = player;
/* 27 */     this.rewards = rewards;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start() {
/* 32 */     this.worker = new Thread(this, "Win Message Thread");
/* 33 */     this.worker.setDaemon(true);
/* 34 */     this.worker.start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stop() {
/* 39 */     this.running.set(false);
/* 40 */     Thread.currentThread().interrupt();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 46 */     this.running.set(true);
/*    */ 
/*    */     
/*    */     try {
/* 50 */       StringTextComponent stringTextComponent = new StringTextComponent(TextFormatting.GOLD + "Challenge Completed!");
/* 51 */       ITextComponent titleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent, (Entity)this.player, 0);
/* 52 */       this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.TITLE, titleComponent));
/*    */       
/* 54 */       int i = 0;
/* 55 */       while (i < this.rewards.size())
/*    */       {
/* 57 */         if (WyHelper.isNullOrEmpty(this.rewards.get(i))) {
/*    */           
/* 59 */           i--;
/*    */           continue;
/*    */         } 
/* 62 */         StringTextComponent stringTextComponent1 = new StringTextComponent(TextFormatting.GOLD + "" + (String)this.rewards.get(i));
/* 63 */         ITextComponent subtitleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent1, (Entity)this.player, 0);
/* 64 */         this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.SUBTITLE, subtitleComponent));
/* 65 */         Thread.sleep(1500L);
/* 66 */         if (i < this.rewards.size() - 1) {
/*    */           
/* 68 */           stringTextComponent1 = new StringTextComponent("");
/* 69 */           subtitleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent1, (Entity)this.player, 0);
/* 70 */           this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.SUBTITLE, subtitleComponent));
/* 71 */           Thread.sleep(300L);
/*    */         } 
/* 73 */         i++;
/*    */       }
/*    */     
/* 76 */     } catch (CommandSyntaxException|InterruptedException e) {
/*    */       
/* 78 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 81 */     stop();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\ChallengeWinMessageThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */