package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class RetreatAndFlintlockGoal extends CooldownGoal {
  private float animationTimer = 0.0F;
  
  private OPEntity entity;
  
  public RetreatAndFlintlockGoal(OPEntity entity, int timer, int random) {
    super(entity, timer, random);
    this.entity = entity;
  }


  
  public boolean shouldExecute() {
    if (!super.shouldExecute()) {
      return false;
    }
    if (this.entity.getAttackTarget() == null) {
      return false;
    }
    if (this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED)) {
      return false;
    }
    float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
    if (distance < 3.0F || distance > 35.0F) {
      return false;
    }
    if (!this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget())) {
      return false;
    }
    if (isOnCooldown()) {
      
      cooldownTick();
      return false;
    } 
    
    execute();
    return true;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
    this.animationTimer = 0.0F;
  }

  
  public void execute() {
    this.entity.setAnimation(OPEntity.Animation.FLINTLOCK_POINTING.ordinal());
    this.animationTimer += 0.05F;
    
    if (this.entity.getAttackTarget() == null) {
      
      this.entity.setCurrentGoal((Goal)this);
      setOnCooldown(true);
      this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
      
      return;
    } 
    if (this.animationTimer < 0.2F) {
      
      Vec3d speed = WyHelper.propulsion(this.entity.getAttackTarget(), 1.25D, 1.25D);
      this.entity.setMotion(speed.x, 0.1D, speed.z);
    }
    else if (this.animationTimer >= 1.4F) {
      
      NormalBulletProjectile proj = new NormalBulletProjectile(this.entity.world, (LivingEntity)this.entity);
      proj.setDamage(4.0F);
      
      LivingEntity target = this.entity.getAttackTarget();
      
      double velX = target.getPosX() - this.entity.getPosX();
      double velY = (target.getBoundingBox()).minY + (target.getHeight() / 3.0F) - proj.getPosY();
      double velZ = target.getPosZ() - this.entity.getPosZ();
      double x = MathHelper.sqrt(velX * velX + velZ * velZ);
      
      proj.shoot(velX, velY + x * 0.10000000149011612D, velZ, 1.6F, (15 - this.entity.world.getDifficulty().getId() * 4));
      this.entity.world.addEntity((Entity)proj);
      
      this.entity.setCurrentGoal((Goal)this);
      setOnCooldown(true);
      this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
    } 
  }
}


