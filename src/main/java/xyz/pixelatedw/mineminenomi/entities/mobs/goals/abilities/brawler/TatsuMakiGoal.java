package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class TatsuMakiGoal
  extends CooldownGoal {
  private OPEntity entity;
  
  public TatsuMakiGoal(OPEntity entity) {
    super(entity, 90, entity.getRNG().nextInt(10));
    this.entity = entity;
    this.entity.addThreat(3);
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
    double damage = this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 2.0D);
    targets.remove(this.entity);
    for (LivingEntity target : targets) {
      
      target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), (float)damage);
      target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0, false, true));
    } 
    
    OTatsumakiAbility.PARTICLES.spawn(this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


