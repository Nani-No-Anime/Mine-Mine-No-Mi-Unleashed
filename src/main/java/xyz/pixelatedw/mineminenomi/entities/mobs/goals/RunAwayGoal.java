package xyz.pixelatedw.mineminenomi.entities.mobs.goals;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineCaptainEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateCaptainEntity;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class RunAwayGoal extends Goal {
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
    // check if captain is near for +4 threat bonus
    Predicate<Entity> captainFilter = FactionHelper.getSameGroupPredicate(entity).and(e -> {
      boolean result = false;
      result = (e instanceof PirateCaptainEntity) || (e instanceof MarineCaptainEntity);
      return result;
    });
    Optional<LivingEntity> captain = WyHelper.<LivingEntity>getEntitiesNear(this.entity.getPosition(), this.entity.world, 20.0D).stream().filter(captainFilter).findAny();

    Predicate<Entity> targetFilter = EntityPredicates.NOT_SPECTATING.and(EntityPredicates.CAN_AI_TARGET).and(FactionHelper.getOutsideGroupPredicate((LivingEntity) this.entity)).and(e -> {
      boolean result = false;
      int entityThreat = 0;
      int initialThreat = 0;
      int randomValue = 0;
      int captainBonus = captain.isPresent() ? 4 : 0;
      if (e instanceof LivingEntity) {
        entityThreat = MobsHelper.getEntityThreatLevel((LivingEntity) e);
        initialThreat = Math.max(Math.max(6, this.entity.getThreat()), MobsHelper.getEntityThreatLevel(this.entity));
        randomValue = this.entity.getRNG().nextInt(3);
        result = (entityThreat >= initialThreat + captainBonus + randomValue);
      }

      return result;
    });

    Optional<LivingEntity> target = WyHelper.<LivingEntity>getEntitiesNear(this.entity.getPosition(), this.entity.world, 20.0D).stream().filter(targetFilter).findAny();

    if (target.isPresent()) {

      BlockPos targetPos = target.get().getPosition();
      this.targetPosX = (int) (this.entity.getPosX() - targetPos.getX() - this.entity.getPosX());
      this.targetPosY = (int) (this.entity.getPosY() - targetPos.getY() - this.entity.getPosY());
      this.targetPosZ = (int) (this.entity.getPosZ() - targetPos.getZ() - this.entity.getPosZ());
      return true;
    }

    return false;
  }

  public void startExecuting() { this.entity.getNavigator().tryMoveToXYZ(this.targetPosX, this.targetPosY, this.targetPosZ, this.speed); }

  public boolean shouldContinueExecuting() { return !this.entity.getNavigator().noPath(); }
}
