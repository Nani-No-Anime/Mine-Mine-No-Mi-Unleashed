package xyz.pixelatedw.mineminenomi.entities.mobs.animals;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.BreatheAirGoal;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class SeaKingEntity extends AnimalEntity {
  public SeaKingEntity(World world) {
    super(ModEntities.SEA_KING, world);
    this.goalSelector.addGoal(0, (Goal)new FindWaterGoal(this));
    this.goalSelector.addGoal(1, (Goal)new RandomSwimmingGoal(this, 1.0D, 10));
    this.goalSelector.addGoal(3, (Goal)new MeleeAttackGoal(this, 1.0D, false));
    this.goalSelector.addGoal(4, (Goal)new BreatheAirGoal(this));
    this.goalSelector.addGoal(6, (Goal)new LookRandomlyGoal((MobEntity)this));
    
    this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
    this.targetSelector.addGoal(0, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, false));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(16.0D);
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(80.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
  }


  
  public boolean canBreatheUnderwater() {
    return true;
  }

  
  public boolean canSwim() {
    return true;
  }


  
  public AgeableEntity createChild(AgeableEntity ageable) {
    return null;
  }
}


