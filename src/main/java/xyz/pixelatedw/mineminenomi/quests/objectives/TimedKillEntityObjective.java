/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ 
/*    */ public class TimedKillEntityObjective
/*    */   extends KillEntityObjective {
/* 11 */   private long firstHit = 0L;
/* 12 */   private int ticks = 0;
/*    */ 
/*    */   
/*    */   public TimedKillEntityObjective(String title, int count, int seconds) {
/* 16 */     super(title, count, (KillEntityObjective.ICheckKill)null);
/* 17 */     this.ticks = seconds * 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public TimedKillEntityObjective(String title, int count, int seconds, KillEntityObjective.ICheckKill check) {
/* 22 */     super(title, count, check);
/* 23 */     this.ticks = seconds * 20;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source) {
/* 29 */     long hitTime = player.world.getGameTime();
/*    */     
/* 31 */     if (hitTime > this.firstHit + this.ticks) {
/*    */       
/* 33 */       setProgress(0.0D);
/* 34 */       this.firstHit = 0L;
/*    */     } 
/*    */     
/* 37 */     if (this.firstHit == 0L) {
/* 38 */       this.firstHit = player.world.getGameTime();
/*    */     }
/* 40 */     if (hitTime - this.ticks <= this.firstHit) {
/* 41 */       return super.checkKill(player, target, source);
/*    */     }
/* 43 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedTitle() {
/* 49 */     String objectiveKey = (new TranslationTextComponent(String.format("quest.objective." + APIConfig.projectId + ".%s", new Object[] { getId() }), new Object[0])).getKey();
/* 50 */     return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf((int)getMaxProgress()), Integer.valueOf(this.ticks / 20) })).getFormattedText();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\TimedKillEntityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */