package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class HakaiHoGoal extends CooldownGoal {
  private OPEntity entity;
  private double damage;
  
  public HakaiHoGoal(OPEntity entity) {
    super(entity, 90, entity.getRNG().nextInt(20));
    this.entity = entity;
    this.damage = 2.0D + this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
    this.entity.addThreat(4);
  }


  
  public boolean shouldExecute() {
    boolean shouldExecute = super.shouldExecute();
    boolean hasTarget = (this.entity.getAttackTarget() != null);
    boolean hasDistance = (hasTarget && this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 3.0F);
    boolean hasEmptyHand = AbilityHelper.canUseBrawlerAbilities((LivingEntity)this.entity);
    
    if (shouldExecute && hasTarget && hasDistance && hasEmptyHand) {
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
    List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 3.0D);
    targets.remove(this.entity);
    
    for (LivingEntity target : targets)
    {
      target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), (float)this.damage);
    }
    
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)this.entity, this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 2.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(false);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.setDamageEntities(false);
    explosion.doExplosion();
    
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


