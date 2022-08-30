/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class CleaveAttackGoal
/*     */   extends CooldownGoal
/*     */ {
/*     */   private OPEntity entity;
/*     */   private int hitCount;
/*     */   private int maxCount;
/*     */   private int duration;
/*  23 */   private float knockbackPower = 1.5F; private int maxDuration; private float prevHealth; private int animationId;
/*     */   private int distance;
/*     */   private boolean canBreakBlocks;
/*     */   
/*     */   public CleaveAttackGoal(OPEntity entity, int cooldown, int hitCount, int distance, boolean canBreakBlocks) {
/*  28 */     super(entity, cooldown, (int)WyHelper.randomWithRange(5, 10));
/*  29 */     this.entity = entity;
/*  30 */     this.maxCount = hitCount;
/*  31 */     this.prevHealth = this.entity.getHealth();
/*  32 */     this.canBreakBlocks = canBreakBlocks;
/*  33 */     this.distance = distance;
/*  34 */     this.maxDuration = 7;
/*  35 */     this.entity.addThreat(2 + (this.canBreakBlocks ? 1 : 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public CleaveAttackGoal(OPEntity entity, int cooldown, int hitCount, int distance) {
/*  40 */     this(entity, cooldown, hitCount, distance, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public CleaveAttackGoal setAnimationId(int id) {
/*  45 */     this.animationId = id;
/*  46 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CleaveAttackGoal setKnockbackPower(float power) {
/*  51 */     this.knockbackPower = power;
/*  52 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  58 */     if (this.entity.getHealth() < this.prevHealth) {
/*     */       
/*  60 */       this.hitCount++;
/*  61 */       this.prevHealth = this.entity.getHealth();
/*     */     } 
/*     */     
/*  64 */     boolean shouldExecute = (super.shouldExecute() || this.hitCount >= this.maxCount);
/*  65 */     boolean hasMovement = !this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED);
/*  66 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/*  67 */     boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) < this.distance);
/*     */     
/*  69 */     if (shouldExecute && hasMovement && hasTarget && hasDistance) {
/*  70 */       return true;
/*     */     }
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCooldown() {
/*  78 */     super.endCooldown();
/*  79 */     this.entity.setCurrentGoal(null);
/*  80 */     this.entity.setPreviousGoal((Goal)this);
/*  81 */     this.duration = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/*  87 */     this.duration++;
/*  88 */     boolean continueExecution = false;
/*     */     
/*  90 */     if (this.duration < this.maxDuration) {
/*  91 */       continueExecution = true;
/*     */     }
/*  93 */     if (!continueExecution) {
/*     */       
/*  95 */       this.entity.setAnimation(0);
/*  96 */       this.hitCount = 0;
/*  97 */       this.maxCount = (int)Math.abs(WyHelper.randomWithRange(this.maxCount - 2, this.maxCount + 2));
/*  98 */       this.entity.setCurrentGoal((Goal)this);
/*  99 */       setOnCooldown(true);
/*     */     } 
/*     */     
/* 102 */     return continueExecution;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 108 */     this.entity.setAnimation(this.animationId);
/* 109 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, this.distance, new Class[] { LivingEntity.class });
/* 110 */     targets.remove(this.entity);
/* 111 */     float damage = (float)this.entity.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
/* 112 */     for (LivingEntity target : targets) {
/*     */       
/* 114 */       target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), damage);
/* 115 */       double x = target.getPosX() - this.entity.getPosX();
/*     */       double z;
/* 117 */       for (z = target.getPosZ() - this.entity.getPosZ(); x * x + z * z < 1.0E-4D; z = (Math.random() - Math.random()) * 0.01D)
/*     */       {
/* 119 */         x = (Math.random() - Math.random()) * 0.01D;
/*     */       }
/* 121 */       target.knockBack((Entity)target, this.knockbackPower, -x, -z);
/* 122 */       if (this.canBreakBlocks) {
/*     */         
/* 124 */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)this.entity, this.entity.world, target.getPosX(), target.getPosY(), target.getPosZ(), 1.5F);
/* 125 */         explosion.setDamageEntities(false);
/* 126 */         explosion.setExplosionSound(false);
/* 127 */         explosion.doExplosion();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\CleaveAttackGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */