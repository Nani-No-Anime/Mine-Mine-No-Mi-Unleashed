package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;

public class GapCloserGoal extends Goal {
  private OPEntity entity;
  private int hitCount;
  private int maxCount;
  private float prevHealth;
  private double speed;
  
  public GapCloserGoal(OPEntity entity) {
    this(entity, 1.7D, 3);
  }

  
  public GapCloserGoal(OPEntity entity, double speed, int hitCount) {
    this.entity = entity;
    this.speed = speed;
    this.maxCount = hitCount;
    this.prevHealth = this.entity.getHealth();
    this.entity.addThreat(1);
  }


  
  public boolean shouldExecute() {
    if (this.entity.getAttackTarget() == null) {
      return false;
    }
    if (this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED)) {
      return false;
    }
    if (this.entity.getHealth() < this.prevHealth) {
      
      this.hitCount++;
      this.prevHealth = this.entity.getHealth();
    } 
    
    float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
    if (distance > 14.0F && distance < 2.0F) {
      return false;
    }
    if (this.hitCount < this.maxCount) {
      return false;
    }
    execute();
    return true;
  }

  
  public void execute() {
    double mX = (-MathHelper.sin(this.entity.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.entity.rotationPitch / 180.0F * 3.1415927F)) * 0.4D;
    double mZ = (MathHelper.cos(this.entity.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.entity.rotationPitch / 180.0F * 3.1415927F)) * 0.4D;
    
    double f2 = MathHelper.sqrt(mX * mX + (this.entity.getMotion()).y * (this.entity.getMotion()).y + mZ * mZ);
    mX /= f2;
    mZ /= f2;
    mX += this.entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0D;
    mZ += this.entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0D;
    mX *= this.speed;
    mZ *= this.speed;
    
    this.entity.setMotion(new Vec3d(mX, 0.3D, mZ));
    
    this.hitCount = 0;
  }
}


