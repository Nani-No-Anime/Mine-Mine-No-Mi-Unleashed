package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class WaxCloneEntity extends OPEntity {
  private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(WaxCloneEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
  private static final DataParameter<Boolean> IS_TEXTURED = EntityDataManager.createKey(WaxCloneEntity.class, DataSerializers.BOOLEAN);
  private int tick = 0;

  
  public WaxCloneEntity(World world) {
    super(ModEntities.WAX_CLONE, world);
    this.experienceValue = 0;
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
  }


  
  protected void registerGoals() {
    this.goalSelector.addGoal(1, (Goal)new PanicGoal(this, 1.25D));
  }


  
  protected void registerData() {
    super.registerData();
    this.dataManager.register(OWNER, Optional.empty());
    this.dataManager.register(IS_TEXTURED, Boolean.valueOf(false));
    EntityStatsCapability.get((LivingEntity)this).setHeart(false);
    EntityStatsCapability.get((LivingEntity)this).setShadow(false);
  }


  
  public void tick() {
    if (!this.world.isRemote && getOwner() == null) {
      
      remove();
      
      return;
    } 
    setRevengeTarget((LivingEntity)getOwner());
    
    if (this.tick > 300) {
      remove();
    }
    this.tick++;
    super.tick();
  }


  
  public void remove() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 10; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        if (i % 2 == 0) {
          ((ServerWorld)this.world).spawnParticle(ParticleTypes.CLOUD, getPosX() + offsetX, getPosY() + 1.5D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
        } else {
          ((ServerWorld)this.world).spawnParticle(ParticleTypes.POOF, getPosX() + offsetX, getPosY() + 1.5D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
        } 
      }  } 
    super.remove();
  }


  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    if (this.dataManager.get(OWNER) != null)
      compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString()); 
    compound.putBoolean("isTextured", ((Boolean)this.dataManager.get(IS_TEXTURED)).booleanValue());
  }


  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
    this.dataManager.set(IS_TEXTURED, Boolean.valueOf(compound.getBoolean("isTextured")));
  }

  
  public void setOwner(UUID uuid) {
    this.dataManager.set(OWNER, Optional.of(uuid));
  }

  
  public UUID getOwnerUUID() {
    return ((Optional<UUID>)getDataManager().get(OWNER)).get();
  }

  
  public PlayerEntity getOwner() {
    return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
  }

  
  public void setTextured() {
    this.dataManager.set(IS_TEXTURED, Boolean.valueOf(true));
  }

  
  public boolean isTextured() {
    return ((Boolean)getDataManager().get(IS_TEXTURED)).booleanValue();
  }
}


