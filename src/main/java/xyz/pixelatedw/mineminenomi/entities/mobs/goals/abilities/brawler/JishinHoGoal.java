/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class JishinHoGoal
/*    */   extends CooldownGoal {
/*    */   private OPEntity entity;
/*    */   
/*    */   public JishinHoGoal(OPEntity entity) {
/* 19 */     super(entity, 120, entity.getRNG().nextInt(30));
/* 20 */     this.entity = entity;
/* 21 */     this.entity.addThreat(5);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 27 */     boolean shouldExecute = super.shouldExecute();
/* 28 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/* 29 */     boolean hasChance = (Math.abs(WyHelper.randomDouble()) < 0.7D);
/* 30 */     boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 6.0F);
/* 31 */     boolean hasEmptyHand = AbilityHelper.canUseBrawlerAbilities((LivingEntity)this.entity);
/*    */     
/* 33 */     if (shouldExecute && hasTarget && hasChance && hasDistance && hasEmptyHand) {
/* 34 */       return true;
/*    */     }
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 42 */     super.endCooldown();
/* 43 */     this.entity.setCurrentGoal(null);
/* 44 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 50 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 6.0D);
/* 51 */     targets.removeIf(entity -> !entity.onGround);
/* 52 */     targets.remove(this.entity);
/*    */     
/* 54 */     for (LivingEntity target : targets) {
/*    */       
/* 56 */       target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), 10.0F);
/* 57 */       target.setMotion(0.0D, 0.75D, 0.0D);
/* 58 */       target.velocityChanged = true;
/*    */     } 
/*    */     
/* 61 */     for (int i = 0; i < 10; i++) {
/* 62 */       JishinHoAbility.PARTICLES.spawn(this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/* 64 */     this.entity.setCurrentGoal((Goal)this);
/* 65 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\JishinHoGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */