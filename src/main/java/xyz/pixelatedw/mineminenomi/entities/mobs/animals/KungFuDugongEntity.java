package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class KungFuDugongEntity extends AnimalEntity {
  private static final AttributeModifier RAGE_MODIFIER = (new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Rage Mode Multiplier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final Item[] FOOD = new Item[] { Items.COOKED_BEEF, Items.COOKED_CHICKEN, Items.COOKED_COD, Items.COOKED_MUTTON, Items.COOKED_PORKCHOP, Items.COOKED_RABBIT, Items.COOKED_SALMON };
  
  private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
  private static final DataParameter<Boolean> IS_ENRAGED = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> IS_SITTING = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> IS_HAPPY = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> IS_TRAINING = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);

  
  public KungFuDugongEntity(World world) {
    super(ModEntities.KUNG_FU_DUGONG, world);
    this.goalSelector.addGoal(0, (Goal)new SwimGoal((MobEntity)this));
    this.goalSelector.addGoal(0, (Goal)new MeleeAttackGoal(this, 1.0D, false));
    this.goalSelector.addGoal(1, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(2, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(2, (Goal)new LookRandomlyGoal((MobEntity)this));
    
    this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
  }


  
  protected void registerData() {
    super.registerData();
    getDataManager().register(OWNER, Optional.empty());
    getDataManager().register(IS_ENRAGED, Boolean.valueOf(false));
    getDataManager().register(IS_SITTING, Boolean.valueOf(false));
    getDataManager().register(IS_HAPPY, Boolean.valueOf(false));
    getDataManager().register(IS_TRAINING, Boolean.valueOf(false));
  }


  
  public void livingTick() {
    super.livingTick();
    
    if (!this.world.isRemote) {
      
      boolean flagOwnerNearby = (getOwner() != null && getDistance((Entity)getOwner()) < 10.0F);
      boolean flagTamed = isTamed();
      boolean flagHasNoTarget = (getAttackTarget() == null);
      boolean flagSitting = (flagHasNoTarget && isSitting());
      boolean flagHealth = (getHealth() > getMaxHealth() / 3.0F);
      
      if (flagOwnerNearby && flagTamed && flagSitting && flagHealth) {
        setHappy(true);
      } else {
        setHappy(false);
      } 
      if (!flagHasNoTarget)
      {
        setHappy(false);
      }




      
      if (flagSitting || isTraining()) {
        getNavigator().clearPath();
      }
      if (getAttackTarget() == getOwner()) {
        
        setAttackTarget(null);
        setRevengeTarget(null);
        getNavigator().clearPath();
      } 
      
      if (flagTamed)
      {
        if (flagSitting) {
          
          List<PlayerEntity> players = WyHelper.getEntitiesNear(getPosition(), this.world, 3.0D, new Class[] { PlayerEntity.class });
          for (PlayerEntity player : players)
          {
            if (player != getOwner()) {
              continue;
            }
            lookAt(EntityAnchorArgument.Type.EYES, player.getEyePosition(0.0F));
            addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 40, 0, false, false));
          }
        
        } else {
          
          if (getDistance((Entity)getOwner()) > 10.0F) {
            getNavigator().tryMoveToEntityLiving((Entity)getOwner(), 1.5D);
          }
          if (getDistance((Entity)getOwner()) > 80.0F) {
            setPositionAndUpdate(getOwner().getPosX(), getOwner().getPosY(), getOwner().getPosZ());
          }
          for (CreatureEntity target : WyHelper.getEntitiesNear(getOwner().getPosition(), this.world, 40.0D, CreatureEntity.class)) {
            
            if (target != null && target.isAlive() && target.getAttackTarget() != null && target.getAttackTarget().equals(getOwner()))
            {
              setAttackTarget((LivingEntity)target);
            }
          } 
        } 
      }
    } 
  }


  
  public boolean attackEntityFrom(DamageSource damageSource, float amount) {
    super.attackEntityFrom(damageSource, amount);
    
    Entity entity = damageSource.getTrueSource();
    
    if (isTamed() && entity instanceof LivingEntity && entity != getOwner()) {
      setAttackTarget((LivingEntity)entity);
    } else if (!isTamed()) {
      
      if (!this.world.isRemote)
      {
        if (entity instanceof PlayerEntity) {
          
          PlayerEntity player = (PlayerEntity)entity;
          
          if (!player.getHeldItem(player.getActiveHand()).isEmpty()) {
            
            setEnraged(true);
            for (int i = 0; i < 5; i++)
            {
              double offsetX = this.rand.nextGaussian() * 0.02D;
              double offsetY = this.rand.nextGaussian() * 0.02D;
              double offsetZ = this.rand.nextGaussian() * 0.02D;
              
              ((ServerWorld)this.world).spawnParticle(ParticleTypes.ANGRY_VILLAGER, getPosX() + offsetX, getPosY() + 1.0D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            
            }
          
          }
          else if (!isEnraged() && getHealth() < getMaxHealth() / 2.0F) {
            
            for (int i = 0; i < 5; i++) {
              
              double offsetX = this.rand.nextGaussian() * 0.02D;
              double offsetY = this.rand.nextGaussian() * 0.02D;
              double offsetZ = this.rand.nextGaussian() * 0.02D;
              
              ((ServerWorld)this.world).spawnParticle(ParticleTypes.HEART, getPosX() + offsetX, getPosY() + 1.0D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            } 
            setOwner(player.getUniqueID());
          } 
        } 
      }
    } 

    
    return true;
  }


  
  public boolean processInteract(PlayerEntity player, Hand hand) {
    if (isTamed() && player == getOwner() && !player.world.isRemote && hand == Hand.MAIN_HAND) {
      
      ItemStack stack = player.getHeldItem((player.getActiveHand() != null) ? player.getActiveHand() : Hand.MAIN_HAND);
      
      if (!stack.isEmpty() && getHealth() < getMaxHealth()) {
        
        Optional<Item> food = Arrays.<Item>stream(FOOD).filter(x -> (stack.getItem() == x)).findFirst();
        
        if (food.isPresent())
        {
          stack.shrink(1);
          heal(4.0F);
          for (int i = 0; i < 5; i++) {
            
            double offsetX = this.rand.nextGaussian() * 0.02D;
            double offsetY = this.rand.nextGaussian() * 0.02D;
            double offsetZ = this.rand.nextGaussian() * 0.02D;
            
            ((ServerWorld)this.world).spawnParticle(ParticleTypes.HEART, getPosX() + offsetX, getPosY() + 1.0D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
          } 
          return true;
        }
      
      } else if (stack.isEmpty()) {
        
        setSiting(!isSitting());
        setAttackTarget((LivingEntity)null);
        return true;
      } 
    } 
    
    return false;
  }


  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    if (((Optional)this.dataManager.get(OWNER)).isPresent())
      compound.putString("owner", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString()); 
    compound.putBoolean("isEnraged", ((Boolean)this.dataManager.get(IS_ENRAGED)).booleanValue());
    compound.putBoolean("isSitting", ((Boolean)this.dataManager.get(IS_SITTING)).booleanValue());
    compound.putBoolean("isHappy", ((Boolean)this.dataManager.get(IS_HAPPY)).booleanValue());
    compound.putBoolean("isTraining", ((Boolean)this.dataManager.get(IS_TRAINING)).booleanValue());
  }


  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    if (!WyHelper.isNullOrEmpty(compound.getString("owner")))
      this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("owner")))); 
    this.dataManager.set(IS_ENRAGED, Boolean.valueOf(compound.getBoolean("isEnraged")));
    this.dataManager.set(IS_SITTING, Boolean.valueOf(compound.getBoolean("isSitting")));
    this.dataManager.set(IS_HAPPY, Boolean.valueOf(compound.getBoolean("isHappy")));
    this.dataManager.set(IS_TRAINING, Boolean.valueOf(compound.getBoolean("isTraining")));
  }


  
  public boolean canDespawn(double distance) {
    return !isTamed();
  }

  
  public boolean isTraining() {
    return ((Boolean)this.dataManager.get(IS_TRAINING)).booleanValue();
  }

  
  public void setTraining(boolean value) {
    this.dataManager.set(IS_TRAINING, Boolean.valueOf(value));
  }

  
  public boolean isHappy() {
    return ((Boolean)this.dataManager.get(IS_HAPPY)).booleanValue();
  }

  
  public void setHappy(boolean value) {
    this.dataManager.set(IS_HAPPY, Boolean.valueOf(value));
  }

  
  public boolean isSitting() {
    return ((Boolean)this.dataManager.get(IS_SITTING)).booleanValue();
  }

  
  public void setSiting(boolean value) {
    this.dataManager.set(IS_SITTING, Boolean.valueOf(value));
  }

  
  public boolean isEnraged() {
    return ((Boolean)this.dataManager.get(IS_ENRAGED)).booleanValue();
  }

  
  public void setEnraged(boolean value) {
    this.dataManager.set(IS_ENRAGED, Boolean.valueOf(value));
    IAttributeInstance attr = getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    attr.removeModifier(RAGE_MODIFIER);
    if (value) {
      attr.applyModifier(RAGE_MODIFIER);
    }
  }
  
  public void setOwner(UUID uuid) {
    this.dataManager.set(OWNER, Optional.of(uuid));
  }

  
  public PlayerEntity getOwner() {
    return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
  }

  
  public boolean isTamed() {
    return (getOwner() != null);
  }


  
  public AgeableEntity createChild(AgeableEntity ageable) {
    return null;
  }
}


