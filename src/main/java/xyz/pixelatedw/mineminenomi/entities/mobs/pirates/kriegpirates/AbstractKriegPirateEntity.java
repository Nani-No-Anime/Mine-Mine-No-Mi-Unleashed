package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;

import java.util.function.Predicate;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;

public abstract class AbstractKriegPirateEntity
  extends AbstractPirateEntity {
  static {
    NON_KRIEG_PIRATE = (target -> (target instanceof AbstractPirateEntity) ? (!((AbstractPirateEntity)target).getCrew().equalsIgnoreCase("Krieg Pirates")) : ((target instanceof PlayerEntity) ? true : ((target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity))));
  }




  
  private static final Predicate<LivingEntity> NON_KRIEG_PIRATE;




  
  public AbstractKriegPirateEntity(EntityType<? extends MobEntity> type, World world) {
    super(type, world, null);
    
    setCrew("Krieg Pirates");
  }


  
  protected void registerGoals() {
    ((GroundPathNavigator)getNavigator()).setBreakDoors(true);
    
    this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
    this.goalSelector.addGoal(2, (Goal)new OpenDoorGoal((MobEntity)this, false));
    this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
    
    this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, LivingEntity.class, 10, true, true, NON_KRIEG_PIRATE));
  }
}


