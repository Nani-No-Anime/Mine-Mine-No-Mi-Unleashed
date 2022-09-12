package xyz.pixelatedw.mineminenomi.entities.mobs.marines;

import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerBossInfo;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.CleaveAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ShockwaveAttackGoal;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;

public class MorganEntity extends AbstractMarineEntity {
  private ServerBossInfo bossInfo = null;

  
  public MorganEntity(World world) {
    super(ModEntities.MORGAN, world);
  }


  
  protected void registerGoals() {
    boolean isHardDifficulty = (this.world.getDifficulty().getId() >= Difficulty.HARD.getId());
    
    super.registerGoals();
    this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(2, (Goal)(new CleaveAttackGoal(this, 100, 5, 4)).setAnimationId(OPEntity.Animation.CLEAVE_ATTACK.ordinal()).setKnockbackPower(2.0F));
    this.goalSelector.addGoal(2, (Goal)(new ShockwaveAttackGoal(this, 100, 4, isHardDifficulty)).setAnimationId(OPEntity.Animation.SHOCKWAVE.ordinal()));

    
    setDoriki(30.0D + WyHelper.randomWithRange(0, 10) + getThreat());
    setBelly(50.0D + WyHelper.randomWithRange(0, 5));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23999999463558197D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
    getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.7D);
    getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(3.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
    getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue(1.5D);
  }


  
  public void tick() {
    if (this.bossInfo != null) {
      this.bossInfo.setPercent(getHealth() / getMaxHealth());
    }
    super.tick();
  }


  
  public void addTrackingPlayer(ServerPlayerEntity player) {
    super.addTrackingPlayer(player);
    if (this.bossInfo != null) {
      this.bossInfo.addPlayer(player);
    }
  }

  
  public void removeTrackingPlayer(ServerPlayerEntity player) {
    super.removeTrackingPlayer(player);
    if (this.bossInfo != null) {
      this.bossInfo.removePlayer(player);
    }
  }

  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    if (reason == SpawnReason.EVENT) {
      this.bossInfo = (ServerBossInfo)(new ServerBossInfo(getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    }
    return spawnData;
  }


  
  public boolean canDespawn(double distance) {
    return false;
  }
}


