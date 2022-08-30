/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HakaiHoGoal extends CooldownGoal {
/*    */   private OPEntity entity;
/*    */   private double damage;
/*    */   
/*    */   public HakaiHoGoal(OPEntity entity) {
/* 22 */     super(entity, 90, entity.getRNG().nextInt(20));
/* 23 */     this.entity = entity;
/* 24 */     this.damage = 2.0D + this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
/* 25 */     this.entity.addThreat(4);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 31 */     boolean shouldExecute = super.shouldExecute();
/* 32 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/* 33 */     boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 3.0F);
/* 34 */     boolean hasEmptyHand = AbilityHelper.canUseBrawlerAbilities((LivingEntity)this.entity);
/*    */     
/* 36 */     if (shouldExecute && hasTarget && hasDistance && hasEmptyHand) {
/* 37 */       return true;
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 45 */     super.endCooldown();
/* 46 */     this.entity.setCurrentGoal(null);
/* 47 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 53 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 3.0D);
/* 54 */     targets.remove(this.entity);
/*    */     
/* 56 */     for (LivingEntity target : targets)
/*    */     {
/* 58 */       target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), (float)this.damage);
/*    */     }
/*    */     
/* 61 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)this.entity, this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 2.0F);
/* 62 */     explosion.setExplosionSound(true);
/* 63 */     explosion.setDamageOwner(false);
/* 64 */     explosion.setDestroyBlocks(false);
/* 65 */     explosion.setFireAfterExplosion(false);
/* 66 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 67 */     explosion.setDamageEntities(false);
/* 68 */     explosion.doExplosion();
/*    */     
/* 70 */     this.entity.setCurrentGoal((Goal)this);
/* 71 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\HakaiHoGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */