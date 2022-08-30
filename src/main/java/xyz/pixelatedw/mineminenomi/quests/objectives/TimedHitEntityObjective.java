/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ 
/*    */ public class TimedHitEntityObjective
/*    */   extends HitEntityObjective {
/* 11 */   private long lastHit = 0L;
/* 12 */   private int seconds = 0;
/*    */ 
/*    */   
/*    */   public TimedHitEntityObjective(String title, int count, int seconds) {
/* 16 */     super(title, count, (HitEntityObjective.ICheckHit)null);
/* 17 */     this.seconds = seconds * 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public TimedHitEntityObjective(String title, int count, int seconds, HitEntityObjective.ICheckHit check) {
/* 22 */     super(title, count, check);
/* 23 */     this.seconds = seconds * 20;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source, float amount) {
/* 29 */     long hitTime = player.world.getGameTime();
/*    */     
/* 31 */     if (this.lastHit == 0L) {
/* 32 */       this.lastHit = player.world.getGameTime();
/*    */     }
/* 34 */     if (hitTime - this.seconds <= this.lastHit) {
/*    */       
/* 36 */       this.lastHit = hitTime;
/* 37 */       return super.checkHit(player, target, source, amount);
/*    */     } 
/*    */ 
/*    */     
/* 41 */     setProgress(0.0D);
/* 42 */     this.lastHit = 0L;
/* 43 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedTitle() {
/* 50 */     String objectiveKey = (new TranslationTextComponent(String.format("quest.objective." + APIConfig.projectId + ".%s", new Object[] { getId() }), new Object[0])).getKey();
/* 51 */     return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf((int)getMaxProgress()), Integer.valueOf(this.seconds / 20) })).getFormattedText();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\TimedHitEntityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */