package xyz.pixelatedw.mineminenomi.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

import javax.annotation.Nullable;

public class DummyEntity
  extends OPEntity
{
  private boolean hasBazooka;
  
  public DummyEntity(EntityType<? extends DummyEntity> type, World world) {
    super(type, world);
    EntityStatsCapability.get((LivingEntity)this).setFaction("civilian");
  }

  
  public void setBazooka() {
    this.hasBazooka = true;
  }


  
  public void tick() {
    this.noClip = true;
    super.tick();
    this.noClip = false;
    setNoGravity(true);
    setMotion(0.0D, 0.0D, 0.0D);
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    if (this.hasBazooka) {
      setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.BAZOOKA));
    }
    return spawnData;
  }
}


