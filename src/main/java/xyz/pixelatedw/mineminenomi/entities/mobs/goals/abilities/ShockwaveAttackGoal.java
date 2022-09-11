package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.Difficulty;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.ShockwaveProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ShockwaveAttackGoal extends CooldownGoal {
  private OPEntity entity;
  private int hitCount;
  private int maxCount;
  private int duration;
  private final int maxDuration;
  private float prevHealth;
  private int animationId;
  private boolean canBreakBlocks;
  
  public ShockwaveAttackGoal(OPEntity entity, int cooldown, int hitCount, boolean canBreakBlocks) {
    super(entity, cooldown, (int)WyHelper.randomWithRange(5, 10));
    this.entity = entity;
    this.maxCount = hitCount;
    this.prevHealth = this.entity.getHealth();
    this.canBreakBlocks = canBreakBlocks;
    this.maxDuration = 5;
    this.entity.addThreat(2 + (this.canBreakBlocks ? 1 : 0));
  }

  
  public ShockwaveAttackGoal(OPEntity entity, int duration, int hitCount) {
    this(entity, duration, hitCount, false);
  }

  
  public ShockwaveAttackGoal(OPEntity entity, int duration) {
    this(entity, duration, 3, false);
  }

  
  public ShockwaveAttackGoal setAnimationId(int id) {
    this.animationId = id;
    return this;
  }


  
  public boolean shouldExecute() {
    if (this.entity.getHealth() < this.prevHealth) {
      
      this.hitCount++;
      this.prevHealth = this.entity.getHealth();
    } 
    
    boolean shouldExecute = (super.shouldExecute() || this.hitCount >= this.maxCount);
    boolean hasMovement = !this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED);
    boolean hasTarget = (this.entity.getAttackTarget() != null);
    boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) > 10.0F);
    boolean hasEnemyInSight = (hasTarget && this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget()));
    
    if (shouldExecute && hasMovement && hasTarget && hasEnemyInSight && hasDistance) {
      return true;
    }
    return false;
  }


  
  public boolean shouldContinueExecuting() {
    this.duration++;
    boolean continueExecution = false;
    
    if (this.duration < this.maxDuration) {
      continueExecution = true;
    }
    if (!continueExecution) {
      
      this.entity.setAnimation(0);
      this.hitCount = 0;
      this.maxCount = (int)Math.abs(WyHelper.randomWithRange(this.maxCount - 2, this.maxCount + 2));
      this.entity.setCurrentGoal((Goal)this);
      setOnCooldown(true);
    } 
    
    return continueExecution;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
    this.duration = 0;
  }


  
  public void startExecuting() {
    this.entity.setAnimation(this.animationId);
    
    double x = this.entity.getAttackTarget().getPosX() - this.entity.getPosX() + ((this.entity.world.getDifficulty().getId() >= Difficulty.HARD.getId()) ? this.entity.getRNG().nextGaussian() : 0.0D);
    double y = (this.entity.getAttackTarget().getBoundingBox()).minY + (this.entity.getAttackTarget().getHeight() / 2.0F) - this.entity.getPosY() + (this.entity.getHeight() / 2.0F);
    double z = this.entity.getAttackTarget().getPosZ() - this.entity.getPosZ() + ((this.entity.world.getDifficulty().getId() >= Difficulty.HARD.getId()) ? this.entity.getRNG().nextGaussian() : 0.0D);
    
    ShockwaveProjectile proj = new ShockwaveProjectile(this.entity.world, (LivingEntity)this.entity, this.canBreakBlocks);
    proj.shoot(x, y, z, 1.5F, 0.0F);
    this.entity.world.addEntity((Entity)proj);
  }
}


