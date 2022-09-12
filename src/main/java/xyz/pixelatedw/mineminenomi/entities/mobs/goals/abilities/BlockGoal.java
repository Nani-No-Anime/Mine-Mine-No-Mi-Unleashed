package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.UUID;

public class BlockGoal extends CooldownGoal {
  private OPEntity entity;
  private int hitCount;
  private int maxCount;
  private int duration;
  private int maxDuration;
  private int randomizer;
  private float prevHealth;
  private UUID armorUuid = UUID.fromString("89a306f5-3d73-40ba-9332-c30fd88c204a");
  private AttributeModifier armorModifier = new AttributeModifier(this.armorUuid, "Armor Bonus", 2.5D, AttributeModifier.Operation.MULTIPLY_TOTAL);

  
  public BlockGoal(OPEntity entity) {
    this(entity, 2, 3);
    this.entity.addThreat(1);
  }

  
  public BlockGoal(OPEntity entity, int duration, int hitCount) {
    this(entity, duration, hitCount, 3);
  }

  
  public BlockGoal(OPEntity entity, int duration, int hitCount, int random) {
    super(entity, 30, (int)WyHelper.randomWithRange(5, 10));
    this.entity = entity;
    this.maxDuration = duration;
    this.maxCount = hitCount;
    this.prevHealth = this.entity.getHealth();
    this.randomizer = random;
    this.entity.addThreat(1);
  }


  
  public boolean shouldExecute() {
    if (!super.shouldExecute()) {
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
    return true;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
    this.duration = 0;
  }


  
  public void startExecuting() {
    this.entity.setAnimation(OPEntity.Animation.BLOCK.ordinal());
    if (this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED))
      this.entity.removePotionEffect(ModEffects.MOVEMENT_BLOCKED); 
    this.entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, this.maxDuration, 0, false, false));
    IAttributeInstance attr = this.entity.getAttribute(SharedMonsterAttributes.ARMOR);
    if (attr.hasModifier(this.armorModifier))
      attr.removeModifier(this.armorUuid); 
    attr.applyModifier(this.armorModifier);
  }


  
  public boolean shouldContinueExecuting() {
    this.duration++;
    boolean continueExecution = false;
    
    if (this.duration < this.maxDuration) {
      continueExecution = true;
    }
    if (!continueExecution) {
      
      this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
      this.hitCount = 0;
      this.maxCount = (int)WyHelper.randomWithRange(0, this.randomizer);
      this.entity.setCurrentGoal((Goal)this);
      setOnCooldown(true);
    } 
    
    return continueExecution;
  }
}


