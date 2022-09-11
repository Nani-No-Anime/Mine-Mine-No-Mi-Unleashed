package xyz.pixelatedw.mineminenomi.entities.mobs.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class ImprovedMeleeAttackGoal
  extends MeleeAttackGoal {
  public ImprovedMeleeAttackGoal(CreatureEntity entity, double speed, boolean useLongMemory) {
    super(entity, speed, useLongMemory);
  }


  
  public boolean shouldContinueExecuting() {
    if (!super.shouldContinueExecuting()) {
      return false;
    }
    LivingEntity target = this.attacker.getAttackTarget();
    
    boolean isInvisible = (target != null && target.isPotionActive(Effects.INVISIBILITY));
    return !isInvisible;
  }


  
  public void tick() {
    LivingEntity target = this.attacker.getAttackTarget();
    boolean isInvisible = (target != null && target.isPotionActive(Effects.INVISIBILITY));
    
    if (isInvisible) {
      
      this.attacker.setAttackTarget((LivingEntity)null);
      
      return;
    } 
    super.tick();
  }


  
  protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
    double reach = ((this.attacker.getSize(Pose.STANDING)).width * 2.0F * (this.attacker.getSize(Pose.STANDING)).width * 2.0F + enemy.getWidth());
    double d0 = reach + this.attacker.getAttribute(ModAttributes.ATTACK_RANGE).getValue();
    d0 = Math.max(10.0D, d0);
    if (distToEnemySqr <= d0 && this.attackTick <= 0) {
      
      this.attackTick = 20;
      this.attacker.swingArm(Hand.MAIN_HAND);
      this.attacker.attackEntityAsMob((Entity)enemy);
    } 
  }
}


