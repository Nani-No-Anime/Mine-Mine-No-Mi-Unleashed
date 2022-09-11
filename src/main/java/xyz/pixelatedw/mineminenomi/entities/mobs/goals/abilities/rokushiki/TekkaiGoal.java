package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class TekkaiGoal extends CooldownGoal {
  private OPEntity entity;
  private int hitCount;
  private int maxCount;
  private float prevHealth;
  private UUID knockbackResistanceUUID = UUID.fromString("4929edc3-45e7-4763-aecc-d478f5eadc3b");
  private AttributeModifier knockbackModifier = new AttributeModifier(this.knockbackResistanceUUID, "Knockback Resistance", 999.0D, AttributeModifier.Operation.ADDITION);

  
  public TekkaiGoal(OPEntity entity) {
    super(entity, 50, (int)WyHelper.randomWithRange(5, 10));
    this.entity = entity;
    this.entity.addThreat(3);
    this.maxCount = 2;
    this.prevHealth = this.entity.getHealth();
  }


  
  public boolean shouldExecute() {
    if (!super.shouldExecute()) {
      return false;
    }
    this.entity.fallDistance = 0.0F;
    
    if (isOnCooldown()) {
      
      IAttributeInstance knockbackResistsance = this.entity.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
      if (this.cooldown < this.maxCooldown / 2 && knockbackResistsance.getModifier(this.knockbackResistanceUUID) != null) {
        knockbackResistsance.removeModifier(this.knockbackModifier);
      }
      cooldownTick();
      return false;
    } 
    
    if (this.entity.getAttackTarget() == null) {
      return false;
    }
    if (this.entity.getHealth() < this.prevHealth) {
      
      this.hitCount++;
      this.prevHealth = this.entity.getHealth();
    } 
    
    float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
    if (distance > 3.0F) {
      return false;
    }
    if (this.hitCount < this.maxCount) {
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
    if (this.entity.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getModifier(this.knockbackResistanceUUID) == null) {
      this.entity.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(this.knockbackModifier);
    }
    this.entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 70, 100));
    this.entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 70, 100));
    this.entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 70, 5));
    this.entity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 70, -100));
    
    this.hitCount = 0;
    this.maxCount = (int)(2.0D + WyHelper.randomWithRange(1, 3));
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


