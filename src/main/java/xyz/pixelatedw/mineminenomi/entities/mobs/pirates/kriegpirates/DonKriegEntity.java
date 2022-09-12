package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;

import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.CleaveAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg.BakudanGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg.DaisensoGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg.GunArrayGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg.MH5Goal;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;

public class DonKriegEntity extends AbstractKriegPirateEntity {
  private ServerBossInfo bossInfo = null;
  
  private static final DataParameter<Boolean> DAISENSO_PHASE = EntityDataManager.createKey(DonKriegEntity.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> MH5_PHASE = EntityDataManager.createKey(DonKriegEntity.class, DataSerializers.BOOLEAN);
  
  public CleaveAttackGoal fistCleaveAttack;
  
  public BlockPos challengeSpawnPosition;
  
  public static final String ANIM_GUN_ARRAY_NAME = "GUN_ARRAY";
  public static final int ANIM_GUN_ARRAY_ID = 120;
  public static final String ANIM_MH5_NAME = "MH5";
  public static final int ANIM_MH5_ID = 121;
  
  public DonKriegEntity(World world) {
    super(ModEntities.DON_KRIEG, world);
    setCrew("Krieg Pirates");
    
    OPEntity.Animation.create("GUN_ARRAY", 120);
    OPEntity.Animation.create("MH5", 121);
  }


  
  protected void registerData() {
    super.registerData();
    getDataManager().register(DAISENSO_PHASE, Boolean.valueOf(false));
    getDataManager().register(MH5_PHASE, Boolean.valueOf(false));
  }


  
  protected void registerGoals() {
    this.fistCleaveAttack = (new CleaveAttackGoal((OPEntity)this, 80, 4, 6)).setAnimationId(OPEntity.Animation.CLEAVE_ATTACK.ordinal());
    
    super.registerGoals();
    this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(1, (Goal)this.fistCleaveAttack);
    this.goalSelector.addGoal(2, (Goal)new GunArrayGoal(this, (int)WyHelper.randomWithRange(60, 80), 10));
    this.goalSelector.addGoal(2, (Goal)new BakudanGoal(this, (int)WyHelper.randomWithRange(120, 140), 10));
    this.goalSelector.addGoal(3, (Goal)new DaisensoGoal(this));
    this.goalSelector.addGoal(4, (Goal)new MH5Goal(this));
    
    setDoriki(100.0D + WyHelper.randomWithRange(0, 20) + getThreat());
    setBelly(20.0D + WyHelper.randomWithRange(0, 5));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2199999988079071D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
    getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
    getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(2.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(20.0D);
    getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue(1.5D);
  }


  
  public void tick() {
    if (this.bossInfo != null)
      this.bossInfo.setPercent(getHealth() / getMaxHealth()); 
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
    
    setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack((IItemProvider)ModArmors.WOOTZ_STEEL_ARMOR));
    
    if (reason == SpawnReason.EVENT) {
      this.bossInfo = (ServerBossInfo)(new ServerBossInfo(getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    }
    return spawnData;
  }


  
  public boolean canDespawn(double distance) {
    return false;
  }

  
  public boolean isDaisensoActive() {
    return ((Boolean)this.dataManager.get(DAISENSO_PHASE)).booleanValue();
  }

  
  public boolean isMH5Active() {
    return ((Boolean)this.dataManager.get(MH5_PHASE)).booleanValue();
  }

  
  public void triggerDaisensoPhase() {
    this.dataManager.set(DAISENSO_PHASE, Boolean.valueOf(true));
  }

  
  public void triggerMH5Phase() {
    this.dataManager.set(MH5_PHASE, Boolean.valueOf(true));
  }
}


