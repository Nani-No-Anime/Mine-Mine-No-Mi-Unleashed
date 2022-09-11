package xyz.pixelatedw.mineminenomi.entities.mobs.goals;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;


public class ImprovedHurtByTargetGoal
  extends HurtByTargetGoal
{
  @Nullable
  private Predicate<Entity> factionPredicate;
  
  public ImprovedHurtByTargetGoal(OPEntity entity, @Nullable Predicate<Entity> factionPredicate, Class<?>... exclude) {
    super(entity, exclude);
    this.factionPredicate = factionPredicate;
  }


  
  protected boolean isSuitableTarget(@Nullable LivingEntity potentialTarget, EntityPredicate targetPredicate) {
    if (this.factionPredicate == null || this.factionPredicate.test(potentialTarget))
      return false; 
    return super.isSuitableTarget(potentialTarget, targetPredicate);
  }
}


