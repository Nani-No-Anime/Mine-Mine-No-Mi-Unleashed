/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class TatsuMakiGoal
/*    */   extends CooldownGoal {
/*    */   private OPEntity entity;
/*    */   
/*    */   public TatsuMakiGoal(OPEntity entity) {
/* 22 */     super(entity, 90, entity.getRNG().nextInt(10));
/* 23 */     this.entity = entity;
/* 24 */     this.entity.addThreat(3);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 30 */     boolean shouldExecute = super.shouldExecute();
/* 31 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/* 32 */     boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 3.0F);
/* 33 */     boolean hasEmptyHand = AbilityHelper.canUseBrawlerAbilities((LivingEntity)this.entity);
/*    */     
/* 35 */     if (shouldExecute && hasTarget && hasDistance && hasEmptyHand) {
/* 36 */       return true;
/*    */     }
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 44 */     super.endCooldown();
/* 45 */     this.entity.setCurrentGoal(null);
/* 46 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 52 */     double damage = this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
/*    */     
/* 54 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 2.0D);
/* 55 */     targets.remove(this.entity);
/* 56 */     for (LivingEntity target : targets) {
/*    */       
/* 58 */       target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), (float)damage);
/* 59 */       target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0, false, true));
/*    */     } 
/*    */     
/* 62 */     OTatsumakiAbility.PARTICLES.spawn(this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 64 */     this.entity.setCurrentGoal((Goal)this);
/* 65 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\TatsuMakiGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */