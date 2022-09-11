package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.entities.BombEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BakudanGoal
  extends CooldownGoal {
  public BakudanGoal(DonKriegEntity entity, int timer, int random) {
    super((OPEntity)entity, timer, random);
    this.entity = entity;
  }
  
  private DonKriegEntity entity;
  
  public boolean shouldExecute() {
    boolean shouldExecute = super.shouldExecute();
    boolean hasTarget = (this.entity.getAttackTarget() != null);
    boolean hasLowHP = (this.entity.getHealth() <= this.entity.getMaxHealth() / 1.25D);
    boolean hasDifferentPhase = (this.entity.getPreviousGoal() != this);
    boolean hasChance = (Math.abs(WyHelper.randomDouble()) < 0.3D);
    boolean hasMovementUnblocked = !this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED);
    
    if (shouldExecute && hasTarget && hasLowHP && hasDifferentPhase && hasChance && hasMovementUnblocked) {
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
    if (this.entity.getAttackTarget() == null) {
      
      this.entity.setCurrentGoal((Goal)this);
      setOnCooldown(true);
      this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
      
      return;
    } 
    for (int i = 0; i < 6; i++) {
      
      BombEntity bomb = new BombEntity(this.entity.world);
      BlockPos pos = WyHelper.findOnGroundSpawnLocation(this.entity.world, ModEntities.BOMB, this.entity.getAttackTarget().getPosition(), 15, 10);
      if (pos != null) {
        
        bomb.setPosition(pos.getX(), pos.getY(), pos.getZ());
        this.entity.world.addEntity((Entity)bomb);
        if (i > 4)
          this.entity.setAnimation(OPEntity.Animation.NONE.ordinal()); 
      } 
    } 
    this.entity.setCurrentGoal((Goal)this);
    setOnCooldown(true);
  }
}


