package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.List;

public class JishinHoGoal
  extends CooldownGoal {
  private OPEntity entity;
  
  public JishinHoGoal(OPEntity entity) {
    super(entity, 120, entity.getRNG().nextInt(30));
    this.entity = entity;
    this.entity.addThreat(5);
  }


  
  public boolean shouldExecute() {
    boolean shouldExecute = super.shouldExecute();
    boolean hasTarget = (this.entity.getAttackTarget() != null);
    boolean hasChance = (Math.abs(WyHelper.randomDouble()) < 0.7D);
    boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 6.0F);
    boolean hasEmptyHand = AbilityHelper.canUseBrawlerAbilities((LivingEntity)this.entity);
    
    if (shouldExecute && hasTarget && hasChance && hasDistance && hasEmptyHand) {
      return true;
    }
    return false;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
  }


  
  public void startExecuting() {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 6.0D);
    targets.removeIf(entity -> !entity.onGround);
    targets.remove(this.entity);
    
    for (LivingEntity target : targets) {
      
      target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), 10.0F);
      target.setMotion(0.0D, 0.75D, 0.0D);
      target.velocityChanged = true;
    } 
    
    for (int i = 0; i < 10; i++) {
      JishinHoAbility.PARTICLES.spawn(this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


