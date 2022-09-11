package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;
import javax.annotation.Nullable;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.BlockGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.GapCloserGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.RetreatAndFlintlockGoal;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class GinEntity extends AbstractKriegPirateEntity {
  private ServerBossInfo bossInfo = null;

  
  public GinEntity(World world) {
    super(ModEntities.GIN, world);
    setCrew("Krieg Pirates");
  }


  
  protected void registerGoals() {
    super.registerGoals();
    this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(2, (Goal)new GapCloserGoal((OPEntity)this, 2.0D, 3));
    this.goalSelector.addGoal(2, (Goal)new BlockGoal((OPEntity)this, (int)WyHelper.randomWithRange(40, 60), 4, 6));
    this.goalSelector.addGoal(3, (Goal)new RetreatAndFlintlockGoal((OPEntity)this, 100, 50));

    
    setDoriki(40.0D + WyHelper.randomWithRange(0, 20) + getThreat());
    setBelly(5.0D + WyHelper.randomWithRange(0, 5));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23999999463558197D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
    getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
    getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(2.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
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
    
    setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.TONFA));
    setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack((IItemProvider)ModWeapons.TONFA));
    
    if (reason == SpawnReason.EVENT) {
      this.bossInfo = (ServerBossInfo)(new ServerBossInfo(getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    }
    return spawnData;
  }


  
  public boolean canDespawn(double distance) {
    return false;
  }
}


