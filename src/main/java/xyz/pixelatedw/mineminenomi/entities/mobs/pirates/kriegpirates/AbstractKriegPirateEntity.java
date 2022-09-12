package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;

import java.util.function.Predicate;

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
    
    this.goalSelector.addGoal(1, (Goal)new SwimGoal(this));
    this.goalSelector.addGoal(2, (Goal)new OpenDoorGoal(this, false));
    this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal(this));
    
    this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal(this, LivingEntity.class, 10, true, true, NON_KRIEG_PIRATE));
  }
}


