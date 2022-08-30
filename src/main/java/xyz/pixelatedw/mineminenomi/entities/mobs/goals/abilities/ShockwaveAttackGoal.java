/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.ShockwaveProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ShockwaveAttackGoal extends CooldownGoal {
/*     */   private OPEntity entity;
/*     */   private int hitCount;
/*     */   private int maxCount;
/*     */   private int duration;
/*     */   private final int maxDuration;
/*     */   private float prevHealth;
/*     */   private int animationId;
/*     */   private boolean canBreakBlocks;
/*     */   
/*     */   public ShockwaveAttackGoal(OPEntity entity, int cooldown, int hitCount, boolean canBreakBlocks) {
/*  24 */     super(entity, cooldown, (int)WyHelper.randomWithRange(5, 10));
/*  25 */     this.entity = entity;
/*  26 */     this.maxCount = hitCount;
/*  27 */     this.prevHealth = this.entity.getHealth();
/*  28 */     this.canBreakBlocks = canBreakBlocks;
/*  29 */     this.maxDuration = 5;
/*  30 */     this.entity.addThreat(2 + (this.canBreakBlocks ? 1 : 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public ShockwaveAttackGoal(OPEntity entity, int duration, int hitCount) {
/*  35 */     this(entity, duration, hitCount, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public ShockwaveAttackGoal(OPEntity entity, int duration) {
/*  40 */     this(entity, duration, 3, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public ShockwaveAttackGoal setAnimationId(int id) {
/*  45 */     this.animationId = id;
/*  46 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  52 */     if (this.entity.getHealth() < this.prevHealth) {
/*     */       
/*  54 */       this.hitCount++;
/*  55 */       this.prevHealth = this.entity.getHealth();
/*     */     } 
/*     */     
/*  58 */     boolean shouldExecute = (super.shouldExecute() || this.hitCount >= this.maxCount);
/*  59 */     boolean hasMovement = !this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED);
/*  60 */     boolean hasTarget = (this.entity.getAttackTarget() != null);
/*  61 */     boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) > 10.0F);
/*  62 */     boolean hasEnemyInSight = (hasTarget && this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget()));
/*     */     
/*  64 */     if (shouldExecute && hasMovement && hasTarget && hasEnemyInSight && hasDistance) {
/*  65 */       return true;
/*     */     }
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/*  73 */     this.duration++;
/*  74 */     boolean continueExecution = false;
/*     */     
/*  76 */     if (this.duration < this.maxDuration) {
/*  77 */       continueExecution = true;
/*     */     }
/*  79 */     if (!continueExecution) {
/*     */       
/*  81 */       this.entity.setAnimation(0);
/*  82 */       this.hitCount = 0;
/*  83 */       this.maxCount = (int)Math.abs(WyHelper.randomWithRange(this.maxCount - 2, this.maxCount + 2));
/*  84 */       this.entity.setCurrentGoal((Goal)this);
/*  85 */       setOnCooldown(true);
/*     */     } 
/*     */     
/*  88 */     return continueExecution;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCooldown() {
/*  94 */     super.endCooldown();
/*  95 */     this.entity.setCurrentGoal(null);
/*  96 */     this.entity.setPreviousGoal((Goal)this);
/*  97 */     this.duration = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 103 */     this.entity.setAnimation(this.animationId);
/*     */     
/* 105 */     double x = this.entity.getAttackTarget().getPosX() - this.entity.getPosX() + ((this.entity.world.getDifficulty().getId() >= Difficulty.HARD.getId()) ? this.entity.getRNG().nextGaussian() : 0.0D);
/* 106 */     double y = (this.entity.getAttackTarget().getBoundingBox()).minY + (this.entity.getAttackTarget().getHeight() / 2.0F) - this.entity.getPosY() + (this.entity.getHeight() / 2.0F);
/* 107 */     double z = this.entity.getAttackTarget().getPosZ() - this.entity.getPosZ() + ((this.entity.world.getDifficulty().getId() >= Difficulty.HARD.getId()) ? this.entity.getRNG().nextGaussian() : 0.0D);
/*     */     
/* 109 */     ShockwaveProjectile proj = new ShockwaveProjectile(this.entity.world, (LivingEntity)this.entity, this.canBreakBlocks);
/* 110 */     proj.shoot(x, y, z, 1.5F, 0.0F);
/* 111 */     this.entity.world.addEntity((Entity)proj);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\ShockwaveAttackGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */