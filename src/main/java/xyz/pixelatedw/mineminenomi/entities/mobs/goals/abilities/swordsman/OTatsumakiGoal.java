package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class OTatsumakiGoal
  extends CooldownGoal {
  private OPEntity entity;
  private double damage;
  
  public OTatsumakiGoal(OPEntity entity) {
    super(entity, 80, entity.getRNG().nextInt(20));
    this.entity = entity;
    this.entity.addThreat(5);
    this.damage = this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
  }


  
  public boolean shouldExecute() {
    if (!super.shouldExecute()) {
      return false;
    }
    ItemStack itemStack = this.entity.getHeldItemMainhand();
    
    if (itemStack == null || itemStack.isEmpty() || this.entity.getAttackTarget() == null) {
      return false;
    }
    if (!this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget())) {
      return false;
    }
    if (this.entity.getDistance((Entity)this.entity.getAttackTarget()) > 3.0F) {
      return false;
    }
    execute();
    return true;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
  }

  
  public void execute() {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 4.0D);
    targets.remove(this.entity);
    for (LivingEntity target : targets) {
      
      target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), (float)this.damage);
      target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0, false, true));
    } 
    
    OTatsumakiAbility.PARTICLES.spawn(this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


