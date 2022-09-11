package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.kage.NightmareSoldiersAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedHurtByTargetGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.KageProjectiles;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class NightmareSoldierEntity extends OPEntity {
  private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(NightmareSoldierEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
  
  private static final String[] TEXTURES = new String[] { "marine1", "marine2", "marine3", "marine4", "marine5", "pirate1", "pirate2", "pirate3", "pirate4", "pirate5", "fishman_pirate1", "fishman_pirate2", "fishman_pirate3", "bandit1", "bandit2", "bandit3" };

  
  public NightmareSoldierEntity(World world) {
    super(KageProjectiles.NIGHTMARE_SOLDIER, world, TEXTURES);
  }


  
  protected void registerGoals() {
    this.goalSelector.addGoal(1, (Goal)new SwimGoal(this));
    this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, AbstractMarineEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, AbstractPirateEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, AbstractBanditEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal(this));
  }





  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22499999403953552D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
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


  
  public void tick() {
    if (!this.world.isRemote) {
      
      if (getOwner() == null || !getOwner().isAlive()) {
        
        remove();
        
        return;
      } 
      if (getDistance((Entity)getOwner()) > 10.0F) {
        getNavigator().tryMoveToEntityLiving((Entity)getOwner(), 1.5D);
      }
      if (getDistance((Entity)getOwner()) > 128.0F) {
        setPositionAndUpdate(getOwner().getPosX(), getOwner().getPosY(), getOwner().getPosZ());
      }
      IAbilityData props = AbilityDataCapability.get((LivingEntity)getOwner());
      NightmareSoldiersAbility ability = (NightmareSoldiersAbility)props.getEquippedAbility((Ability)NightmareSoldiersAbility.INSTANCE);
      List<LivingEntity> attackList = WyHelper.getEntitiesNear(getPosition(), this.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)getOwner()), new Class[0]);
      attackList.remove(getOwner());
      attackList.removeIf(e -> e instanceof NightmareSoldierEntity);
      LivingEntity target = attackList.stream().findFirst().orElse(null);
      
      if (ability == null) {
        remove();
      }
      if (getAttackTarget() == getOwner() || getAttackTarget() instanceof NightmareSoldierEntity) {
        setAttackTarget(null);
      }
      if (target != null) {
        setAttackTarget(target);
      }
    } 
    super.tick();
  }


  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    if (this.dataManager.get(OWNER) != null) {
      compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString());
    }
  }

  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
  }

  
  public void setOwner(LivingEntity owner) {
    this.dataManager.set(OWNER, Optional.of(owner.getUniqueID()));
    IEntityStats stats = EntityStatsCapability.get((LivingEntity)this);
    stats.setFaction(EntityStatsCapability.get(owner).getFaction());
    
    Predicate<Entity> factionScope = FactionHelper.getOutsideGroupPredicate((LivingEntity)this);
    Predicate<Entity> invisibleEntity = entity -> (entity instanceof LivingEntity) ? (!((LivingEntity)entity).isPotionActive(Effects.INVISIBILITY)) : false;




    
    if (factionScope != null) {
      
      this.targetSelector.addGoal(1, (Goal)new ImprovedHurtByTargetGoal(this, factionScope, new Class[0]));
      this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, OPEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
      this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
      this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
    } 
  }

  
  public PlayerEntity getOwner() {
    return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
  }
}


