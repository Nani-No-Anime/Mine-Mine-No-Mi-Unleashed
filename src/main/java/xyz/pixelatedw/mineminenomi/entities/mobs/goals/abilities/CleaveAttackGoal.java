package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.List;

public class CleaveAttackGoal
  extends CooldownGoal
{
  private OPEntity entity;
  private int hitCount;
  private int maxCount;
  private int duration;
  private float knockbackPower = 1.5F; private int maxDuration; private float prevHealth; private int animationId;
  private int distance;
  private boolean canBreakBlocks;
  
  public CleaveAttackGoal(OPEntity entity, int cooldown, int hitCount, int distance, boolean canBreakBlocks) {
    super(entity, cooldown, (int)WyHelper.randomWithRange(5, 10));
    this.entity = entity;
    this.maxCount = hitCount;
    this.prevHealth = this.entity.getHealth();
    this.canBreakBlocks = canBreakBlocks;
    this.distance = distance;
    this.maxDuration = 7;
    this.entity.addThreat(2 + (this.canBreakBlocks ? 1 : 0));
  }

  
  public CleaveAttackGoal(OPEntity entity, int cooldown, int hitCount, int distance) {
    this(entity, cooldown, hitCount, distance, false);
  }

  
  public CleaveAttackGoal setAnimationId(int id) {
    this.animationId = id;
    return this;
  }

  
  public CleaveAttackGoal setKnockbackPower(float power) {
    this.knockbackPower = power;
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
    boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) < this.distance);
    
    if (shouldExecute && hasMovement && hasTarget && hasDistance) {
      return true;
    }
    return false;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
    this.duration = 0;
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


  
  public void startExecuting() {
    this.entity.setAnimation(this.animationId);
    List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, this.distance, new Class[] { LivingEntity.class });
    targets.remove(this.entity);
    float damage = (float)this.entity.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
    for (LivingEntity target : targets) {
      
      target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), damage);
      double x = target.getPosX() - this.entity.getPosX();
      double z;
      for (z = target.getPosZ() - this.entity.getPosZ(); x * x + z * z < 1.0E-4D; z = (Math.random() - Math.random()) * 0.01D)
      {
        x = (Math.random() - Math.random()) * 0.01D;
      }
      target.knockBack((Entity)target, this.knockbackPower, -x, -z);
      if (this.canBreakBlocks) {
        
        ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)this.entity, this.entity.world, target.getPosX(), target.getPosY(), target.getPosZ(), 1.5F);
        explosion.setDamageEntities(false);
        explosion.setExplosionSound(false);
        explosion.doExplosion();
      } 
    } 
  }
}


