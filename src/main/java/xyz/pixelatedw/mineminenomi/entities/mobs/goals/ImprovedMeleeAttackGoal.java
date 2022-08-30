/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.Hand;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class ImprovedMeleeAttackGoal
/*    */   extends MeleeAttackGoal {
/*    */   public ImprovedMeleeAttackGoal(CreatureEntity entity, double speed, boolean useLongMemory) {
/* 15 */     super(entity, speed, useLongMemory);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldContinueExecuting() {
/* 21 */     if (!super.shouldContinueExecuting()) {
/* 22 */       return false;
/*    */     }
/* 24 */     LivingEntity target = this.attacker.getAttackTarget();
/*    */     
/* 26 */     boolean isInvisible = (target != null && target.isPotionActive(Effects.INVISIBILITY));
/* 27 */     return !isInvisible;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 33 */     LivingEntity target = this.attacker.getAttackTarget();
/* 34 */     boolean isInvisible = (target != null && target.isPotionActive(Effects.INVISIBILITY));
/*    */     
/* 36 */     if (isInvisible) {
/*    */       
/* 38 */       this.attacker.setAttackTarget((LivingEntity)null);
/*    */       
/*    */       return;
/*    */     } 
/* 42 */     super.tick();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
/* 48 */     double reach = ((this.attacker.getSize(Pose.STANDING)).width * 2.0F * (this.attacker.getSize(Pose.STANDING)).width * 2.0F + enemy.getWidth());
/* 49 */     double d0 = reach + this.attacker.getAttribute(ModAttributes.ATTACK_RANGE).getValue();
/* 50 */     d0 = Math.max(10.0D, d0);
/* 51 */     if (distToEnemySqr <= d0 && this.attackTick <= 0) {
/*    */       
/* 53 */       this.attackTick = 20;
/* 54 */       this.attacker.swingArm(Hand.MAIN_HAND);
/* 55 */       this.attacker.attackEntityAsMob((Entity)enemy);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\ImprovedMeleeAttackGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */