package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BlackKnightEntity extends OPEntity {
  private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(BlackKnightEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
  public boolean isAggressive = true;
  public List<LivingEntity> forcedTargets = new ArrayList<>();

  
  public BlackKnightEntity(World world) {
    super(ModEntities.BLACK_KNIGHT, world);
  }


  
  protected void registerGoals() {
    this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
    this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
    
    this.targetSelector.addGoal(2, (Goal)new HurtByTargetGoal(this, new Class[0]));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
    getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
  }


  
  protected void registerData() {
    super.registerData();
    this.dataManager.register(OWNER, Optional.empty());
    EntityStatsCapability.get((LivingEntity)this).setHeart(false);
    EntityStatsCapability.get((LivingEntity)this).setShadow(false);
  }


  
  public boolean attackEntityFrom(DamageSource damageSource, float damageValue) {
    if (damageSource.getTrueSource() != null && damageSource.getTrueSource() instanceof PlayerEntity && damageSource.getTrueSource() == getOwner()) {
      return false;
    }
    return super.attackEntityFrom(damageSource, damageValue);
  }


  
  public boolean attackEntityAsMob(Entity target) {
    float damage = (float)getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
    int knockback = 0;
    
    if (target instanceof LivingEntity) {
      
      damage += EnchantmentHelper.getModifierForCreature(getHeldItemMainhand(), ((LivingEntity)target).getCreatureAttribute());
      knockback = (int)(knockback + EnchantmentHelper.getKnockbackModifier((LivingEntity)this));
    } 
    
    boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this), damage);
    
    if (flag)
    {
      if (knockback > 0) {
        
        target.addVelocity((-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F));
        setMotion(getMotion().mul(0.6D, 1.0D, 0.6D));
      } 
    }
    
    return flag;
  }


  
  public void tick() {
    if (!this.world.isRemote) {
      
      PlayerEntity owner = getOwner();
      
      if (owner == null) {
        
        remove();
        
        return;
      } 
      if (getDistance((Entity)owner) > 10.0F) {
        getNavigator().tryMoveToEntityLiving((Entity)owner, 1.5D);
      }
      if (getDistance((Entity)owner) > 80.0F) {
        setPositionAndUpdate(owner.getPosX(), owner.getPosY(), owner.getPosZ());
      }
      IEntityStats ownerProps = EntityStatsCapability.get((LivingEntity)owner);
      IDevilFruit ownerDFProps = DevilFruitCapability.get((LivingEntity)owner);
      List<LivingEntity> targetsList = this.isAggressive ? WyHelper.getEntitiesNear(getPosition(), this.world, 10.0D, new Class[] { PlayerEntity.class, AbstractMarineEntity.class, AbstractPirateEntity.class, MonsterEntity.class }) : (!this.forcedTargets.isEmpty() ? this.forcedTargets : new ArrayList<>());
      LivingEntity target = null;
      
      if (!ownerDFProps.getDevilFruit().equalsIgnoreCase("ito_ito")) {
        remove();
      }
      if (!targetsList.isEmpty() && (getAttackTarget() == null || !getAttackTarget().isAlive())) {
        
        if (targetsList.contains(owner)) {
          targetsList.remove(owner);
        }
        if (ownerProps.isMarine()) {
          targetsList = (List<LivingEntity>)targetsList.stream().filter(x -> !(x instanceof AbstractMarineEntity)).collect(Collectors.toList());
        }
        target = targetsList.stream().findFirst().orElse(null);
      } 
      
      if (target != null) {
        setAttackTarget(target);
      }
      if (!this.forcedTargets.isEmpty()) {
        
        Iterator<LivingEntity> it = this.forcedTargets.iterator();
        while (it.hasNext()) {
          
          LivingEntity forcedTarget = it.next();
          if (forcedTarget == null || !forcedTarget.isAlive()) {
            it.remove();
          }
        } 
      } 
    } 
    super.tick();
  }


  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    if (this.dataManager.get(OWNER) != null)
    {
      compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString());
    }
  }


  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
  }

  
  public void setOwner(LivingEntity owner) {
    this.dataManager.set(OWNER, Optional.of(owner.getUniqueID()));
    EntityStatsCapability.get((LivingEntity)this).setFaction(EntityStatsCapability.get(owner).getFaction());
  }

  
  public PlayerEntity getOwner() {
    return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
  }

  
  public UUID getOwnerUUID() {
    return ((Optional<UUID>)getDataManager().get(OWNER)).get();
  }
}


