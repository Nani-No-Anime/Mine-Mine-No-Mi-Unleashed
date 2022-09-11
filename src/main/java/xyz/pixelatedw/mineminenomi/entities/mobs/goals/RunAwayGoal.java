package xyz.pixelatedw.mineminenomi.entities.mobs.goals;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class RunAwayGoal
  extends Goal
{
  private OPEntity entity;
  private int targetPosX;
  private int targetPosY;
  private int targetPosZ;
  private double speed;
  
  public RunAwayGoal(OPEntity entity, double speed) {
    this.entity = entity;
    this.speed = speed;
    setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
  }





  
  public boolean shouldExecute() {
    Predicate<Entity> filter = EntityPredicates.NOT_SPECTATING.and(EntityPredicates.CAN_AI_TARGET).and(FactionHelper.getOutsideGroupPredicate((LivingEntity)this.entity)).and(e -> (e instanceof LivingEntity && MobsHelper.getEntityThreatLevel((LivingEntity)e) >= 6 + this.entity.getRNG().nextInt(3)));




    
    Optional<PlayerEntity> target = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 20.0D, new Class[] { PlayerEntity.class }).stream().filter(filter).findAny();
    
    if (target.isPresent()) {
      
      BlockPos targetPos = ((PlayerEntity)target.get()).getPosition();
      this.targetPosX = (int)(this.entity.getPosX() - targetPos.getX() - this.entity.getPosX());
      this.targetPosY = (int)(this.entity.getPosY() - targetPos.getY() - this.entity.getPosY());
      this.targetPosZ = (int)(this.entity.getPosZ() - targetPos.getZ() - this.entity.getPosZ());
      return true;
    } 
    
    return false;
  }


  
  public void startExecuting() {
    this.entity.getNavigator().tryMoveToXYZ(this.targetPosX, this.targetPosY, this.targetPosZ, this.speed);
  }


  
  public boolean shouldContinueExecuting() {
    return !this.entity.getNavigator().noPath();
  }
}


