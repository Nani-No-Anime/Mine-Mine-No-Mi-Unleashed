package xyz.pixelatedw.mineminenomi.entities;

import java.util.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class BottomHalfBodyEntity
  extends OPEntity {
  private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(BottomHalfBodyEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
  private Ability parentAbility = null;

  
  public BottomHalfBodyEntity(World world) {
    super(ModEntities.BOTTOM_HALF_BODY, world);
  }

  
  public void setParentAbility(Ability ability) {
    this.parentAbility = ability;
  }

  
  public void setOwner(LivingEntity owner) {
    this.dataManager.set(OWNER, Optional.of(owner.getUniqueID()));
    EntityStatsCapability.get((LivingEntity)this).setFaction(EntityStatsCapability.get(owner).getFaction());
  }

  
  public UUID getOwnerUUID() {
    return (getDataManager().get(OWNER)).get();
  }

  
  public PlayerEntity getOwner() {
    return (getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid((getDataManager().get(OWNER)).get()) : null;
  }


  
  public boolean attackEntityFrom(DamageSource source, float amount) {
    PlayerEntity owner = getOwner();
    if (owner == null) {
      return false;
    }
    owner.attackEntityFrom(DamageSource.MAGIC, amount);
    setHealth(owner.getHealth());
    
    return super.attackEntityFrom(source, amount);
  }


  
  public void tick() {
    if (!this.world.isRemote) {
      
      PlayerEntity owner = getOwner();
      boolean hasParentAbilityActive = false;
      
      if (owner != null) {
        
        IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)owner);
        Ability parentAbilityInstance = abilityProps.getEquippedAbility(this.parentAbility);
        if (parentAbilityInstance != null && parentAbilityInstance.isContinuous())
        {
          hasParentAbilityActive = true;
        }
      } 
      
      if (owner == null || !owner.isAlive() || this.parentAbility == null || !hasParentAbilityActive) {
        
        remove();
        
        return;
      } 
      if (getDistance((Entity)getOwner()) > 5.0F) {
        getNavigator().tryMoveToEntityLiving((Entity)getOwner(), 1.5D);
      }
    } 
    super.tick();
  }


  
  protected void registerData() {
    super.registerData();
    this.dataManager.register(OWNER, null);
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
    getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
  }


  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    if (this.dataManager.get(OWNER) != null)
    {
      compound.putString("OwnerUUID", this.dataManager.get(OWNER).get().toString());
    }
  }


  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
  }


  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket((Entity)this);
  }
}


