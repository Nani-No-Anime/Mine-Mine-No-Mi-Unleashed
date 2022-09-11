package xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.Tag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModTags;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileBlockEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileHitEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileShootEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;



public class AbilityProjectileEntity
  extends ThrowableEntity
{
  private int maxLife = 64;
  private int knockbackStrength = 0;
  private double collisionSize = 1.0D;
  private float damage = 0.1F;
  private float gravity = 1.0E-4F;
  
  private boolean canPassThroughBlocks = false;
  private boolean canPassThroughEntities = false;
  private boolean canGetStuckInGround = false;
  protected boolean stuckInGround = false;
  private boolean changeHurtTime = false;
  private boolean armorPiercing = false;
  private boolean canHurtThrower = false;
  private boolean isFake = false;
  private int hurtTime = 10;
  boolean entityDamaged = false;
  boolean applyOnlyOnce = true;
  private List<Integer> targets = new ArrayList<>();
  private int targetResetTime = 20;
  private static final DataParameter<Integer> OWNER = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.VARINT);
  private static final DataParameter<Integer> LIFE = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.VARINT);
  private static final DataParameter<Boolean> IS_PHYSICAL = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> IS_AFFECTED_BY_HARDENING = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> IS_AFFECTED_BY_IMBUING = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.BOOLEAN);

  
  public IOnEntityImpact onEntityImpactEvent;
  
  public IOnBlockImpact onBlockImpactEvent;
  
  public IOnTick onTickEvent;
  
  @Deprecated
  public IWithEffects withEffects;
  
  public DamageSource source;
  
  public DamageSource bypassingSource;
  
  private static final Block[] NON_SOLID_BLOCKS = new Block[] { Blocks.SUNFLOWER, Blocks.GRASS, Blocks.TALL_GRASS, Blocks.SEAGRASS, Blocks.TALL_SEAGRASS, Blocks.VINE, Blocks.REDSTONE_WIRE, Blocks.DEAD_BUSH, Blocks.ROSE_BUSH, ModBlocks.OPE };

  
  public AbilityProjectileEntity(EntityType type, World world) {
    super(type, world); this.onEntityImpactEvent = (hitEntity -> { if (!this.targets.contains(Integer.valueOf(hitEntity.getEntityId())))
          this.onBlockImpactEvent.onImpact(hitEntity.getPosition()); 
      }); this.onBlockImpactEvent = (hit -> { 
      }); this.onTickEvent = (() -> {
      
      }); this.withEffects = (() -> new EffectInstance[0]); } public AbilityProjectileEntity(EntityType type, World world, double x, double y, double z) { super(type, x, y, z, world); this.onEntityImpactEvent = (hitEntity -> { if (!this.targets.contains(Integer.valueOf(hitEntity.getEntityId())))
          this.onBlockImpactEvent.onImpact(hitEntity.getPosition()); 
      }); this.onBlockImpactEvent = (hit -> { 
      }); this.onTickEvent = (() -> {
      
      }); this.withEffects = (() -> new EffectInstance[0]); } public AbilityProjectileEntity(EntityType type, World world, LivingEntity thrower) { super(type, thrower, world); this.onEntityImpactEvent = (hitEntity -> { if (!this.targets.contains(Integer.valueOf(hitEntity.getEntityId())))
          this.onBlockImpactEvent.onImpact(hitEntity.getPosition());  }); this.onBlockImpactEvent = (hit -> {  }); this.onTickEvent = (() -> {  }); this.withEffects = (() -> new EffectInstance[0]); this.maxLife = getLife();
    this.damage = 0.1F;
    setThrower(thrower);
    
    this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)thrower)).setProjectile();
    this.bypassingSource = (new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)thrower)).setProjectile().setDamageBypassesArmor(); }



  
  public void tick() {
    super.tick();
    
    if (!this.world.isRemote) {
      EntityRayTraceResult entityRayTraceResult=null;
      if (getLife() <= 0) {
        
        setLife(this.maxLife);
        
        remove();
        
        return;
      } 
      setLife(getLife() - 1);
      
      if (ExtendedWorldData.get(this.world).isInsideRestrictedArea((int)getPosX(), (int)getPosY(), (int)getPosZ())) {
        
        remove();
        
        return;
      } 
      Vec3d vec31 = new Vec3d(getPosX(), getPosY(), getPosZ());
      Vec3d vec3 = new Vec3d(getPosX() + (getMotion()).x, getPosY() + (getMotion()).y, getPosZ() + (getMotion()).z);
      BlockRayTraceResult blockRayTraceResult = this.world.rayTraceBlocks(new RayTraceContext(vec3, vec31, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, this));
      
      double sizeX = this.collisionSize;
      double sizeY = this.collisionSize;
      double sizeZ = this.collisionSize;
      
      AxisAlignedBB aabb = (new AxisAlignedBB(getPosX(), getPosY(), getPosZ(), getPosX(), getPosY(), getPosZ())).grow(sizeX, sizeY, sizeZ);
      List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, aabb);



      
      list = (List<LivingEntity>)list.stream().filter(target -> (target instanceof LivingEntity && target.canBeCollidedWith() && target != getThrower()) ? target.canEntityBeSeen(this) : true).collect(Collectors.toList());
      
      Entity target = list.stream().findAny().orElse(null);
      
      if (target != null) {
        entityRayTraceResult = new EntityRayTraceResult(target);
      }
      if (entityRayTraceResult!=null && entityRayTraceResult.getType() == RayTraceResult.Type.ENTITY) {
        onImpact((RayTraceResult)entityRayTraceResult);
      }
      if (this.ticksExisted % getTargetResetTime() == 0) {
        clearTargets();
      }
    } 
    this.onTickEvent.onTick();
  }


  
  public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
    ProjectileShootEvent event = new ProjectileShootEvent(this, velocity, inaccuracy);
    if (MinecraftForge.EVENT_BUS.post(event))
      return; 
    clearTargets();
    super.shoot(entityThrower, rotationPitchIn, rotationYawIn, pitchOffset, velocity, inaccuracy);
  }


  
  public boolean handleFluidAcceleration(Tag<Fluid> fluidTag) {
    if (this.inWater)
      doWaterSplashEffect(); 
    return false;
  }


  
  protected void onImpact(RayTraceResult hit) {
    if (!isAlive()) {
      return;
    }
    if (!this.world.isRemote)
    {
      if (hit.getType() == RayTraceResult.Type.ENTITY) {
        
        EntityRayTraceResult entityHit = (EntityRayTraceResult)hit;
        
        if (entityHit.getEntity() instanceof LivingEntity && getThrower() != null) {
          
          LivingEntity hitEntity = (LivingEntity)entityHit.getEntity();
          IEntityStats statProps = EntityStatsCapability.get(getThrower());
          
          if (hitEntity == getThrower() && !this.canHurtThrower) {
            return;
          }
          ProjectileHitEvent event = new ProjectileHitEvent(this, hit);
          if (MinecraftForge.EVENT_BUS.post(event)) {
            return;
          }
          if (!this.entityDamaged && !this.targets.contains(Integer.valueOf(hitEntity.getEntity().getEntityId())) && hitEntity.getEntity().isAlive()) {
            
            if (this.source == null) {
              this.source = (new IndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile();
            }
            if (this.bypassingSource == null) {
              this.bypassingSource = (new IndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().setDamageBypassesArmor();
            }
            if (!canBlockDamageSource(this.source, hitEntity)) {
              
              if (isAffectedByHardening() && getThrower() != null && this.applyOnlyOnce) {
                
                IHakiData hakiProps = HakiDataCapability.get(getThrower());
                IAbilityData attackerAbilityProps = AbilityDataCapability.get(getThrower());
                Ability busoHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
                boolean hasBusoHakiActive = (busoHaki != null && busoHaki.isContinuous());
                
                Ability fullBodyBusoHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
                boolean hasFullBodyBusoHakiActive = (fullBodyBusoHaki != null && fullBodyBusoHaki.isContinuous());
                
                if (hasBusoHakiActive || hasFullBodyBusoHakiActive)
                  this.damage = (float)(this.damage * (1.0D + Math.min((hakiProps.getBusoshokuHardeningHakiExp() / (
                      isPhysical() ? 'E' : 'E')), 0.5D))); 
                WyDebug.debug("Hardening Haki Projectile Damage: " + this.damage);
                this.applyOnlyOnce = false;
              } 
              
              this.damage = (float)(this.damage * statProps.getDamageMultiplier());
              
              if (this.armorPiercing) {
                
                float reduction = getArmorDamage();
                this.entityDamaged = hitEntity.attackEntityFrom(this.source, this.damage - reduction);
                hitEntity.hurtTime = hitEntity.hurtResistantTime = 0;
                hitEntity.attackEntityFrom(this.bypassingSource, reduction);

              
              }
              else if (this.damage == 0.0F && this.isFake) {
                this.entityDamaged = true;
              } else {
                this.entityDamaged = hitEntity.attackEntityFrom(this.source, this.damage);
              
              }

            
            }
            else if (hitEntity.getActiveItemStack().getItem() instanceof net.minecraft.item.ShieldItem) {
              
              hitEntity.getActiveItemStack().damageItem((int)(getDamage() + 17.0F), hitEntity, p_220282_1_ -> p_220282_1_.sendBreakAnimation(hitEntity.getActiveHand()));
            } 
          } 





          
          if (this.entityDamaged) {
            
            triggerEffects(hitEntity);
            this.onEntityImpactEvent.onImpact(hitEntity);
            
            if (this.knockbackStrength > 0) {
              
              Vec3d vec3d = getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale(this.knockbackStrength * 0.6D);
              if (vec3d.lengthSquared() > 0.0D)
              {
                hitEntity.addVelocity(vec3d.x, 0.1D, vec3d.z);
              }
            } 
            
            if (!this.canPassThroughEntities) {
              
              remove();
              
              return;
            } 
            
            this.targets.add(Integer.valueOf(entityHit.getEntity().getEntityId()));
            this.entityDamaged = false;
          } 

          
          if (this.changeHurtTime) {
            hitEntity.hurtResistantTime = this.hurtTime;
          }
        } else if (entityHit.getEntity() instanceof AbilityProjectileEntity) {
          
          AbilityProjectileEntity entity = (AbilityProjectileEntity)entityHit.getEntity();
          onProjectileCollision(this, entity);
        }
      
      } else if (hit.getType() == RayTraceResult.Type.BLOCK) {
        
        BlockRayTraceResult blockHit = (BlockRayTraceResult)hit;
        
        ProjectileHitEvent event = new ProjectileHitEvent(this, hit);
        if (MinecraftForge.EVENT_BUS.post(event)) {
          return;
        }
        BlockState state = this.world.getBlockState(blockHit.getPos());
        
        if (!passedThroughNonSolidBlock(blockHit.getPos())) {
          
          if (state.getBlock() == Blocks.BARRIER || state
            .getBlock() == ModBlocks.BARRIER || state
            .getBlock().asItem().isIn(ModTags.Items.KAIROSEKI)) {
            
            this.onBlockImpactEvent.onImpact(blockHit.getPos());
            remove();
            
            return;
          } 
          if (!this.canPassThroughBlocks) {
            
            this.onBlockImpactEvent.onImpact(blockHit.getPos());
            if (!this.canGetStuckInGround) {
              remove();
            }
          } 
        } 
      } 
    }
  }
  
  public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {
    boolean isPhysical = owner.isPhysical();
    boolean isTargetPhysical = target.isPhysical();
    boolean isDamageLarger = (owner.getDamage() > target.getDamage());
    
    if (isPhysical) {
      
      if (isTargetPhysical) {
        
        if (isDamageLarger) {
          
          target.remove();
        } else {
          
          owner.remove();
        } 
      } else {
        
        owner.remove();
      }
    
    }
    else if (isTargetPhysical) {
      target.remove();
    
    }
    else if (isDamageLarger) {
      target.remove();
    } else {
      owner.remove();
    } 
  }


  
  public boolean canBeCollidedWith() {
    return true;
  }

  
  public float getArmorDamage() {
    float reduction = (float)(this.damage * (0.1D + (this.damage / 150.0F)));
    if (reduction > this.damage)
      reduction = this.damage; 
    return reduction;
  }

  
  public void triggerEffects(LivingEntity hitEntity) {
    if ((this.withEffects.getEffects()).length > 0)
    {
      for (EffectInstance instance : this.withEffects.getEffects()) {
        
        hitEntity.addPotionEffect(instance);
        if (getThrower() instanceof ServerPlayerEntity) {
          ((ServerPlayerEntity)getThrower()).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(hitEntity.getEntityId(), instance));
        }
      } 
    }
  }
  
  public boolean canBlockDamageSource(DamageSource damageSource, LivingEntity target) {
    if (damageSource == null) {
      return false;
    }
    ProjectileBlockEvent event = new ProjectileBlockEvent(damageSource.getImmediateSource());
    boolean flag = event.canBlock();
    
    if (!damageSource.isUnblockable() && target.isActiveItemStackBlocking() && flag) {
      
      Vec3d vec3d2 = damageSource.getDamageLocation();
      if (vec3d2 != null) {
        
        Vec3d vec3d = getLook(1.0F);
        Vec3d vec3d1 = vec3d2.subtractReverse(new Vec3d(target.getPosX(), target.getPosY(), target.getPosZ())).normalize();
        vec3d1 = new Vec3d(vec3d1.x, 0.0D, vec3d1.z);
        return (vec3d1.dotProduct(vec3d) < 0.0D);
      } 
    } 
    
    return false;
  }


  
  public void setDamageSource(DamageSource s) {
    this.source = s;
  }

  
  private boolean passedThroughNonSolidBlock(BlockPos pos) {
    return Arrays.<Block>stream(NON_SOLID_BLOCKS).anyMatch(block -> (this.world.getBlockState(pos).getBlock() == block));
  }


  
  public void remove() {
    super.remove();
  }


  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putInt("life", getLife());
    compound.putInt("maxLife", this.maxLife);
    compound.putInt("hurtTime", this.hurtTime);
    compound.putInt("knockbackStrength", this.knockbackStrength);
    compound.putFloat("damage", this.damage);
    compound.putFloat("gravity", this.gravity);
    compound.putDouble("collisionSize", this.collisionSize);
    compound.putBoolean("isPhysical", ((Boolean)this.dataManager.get(IS_PHYSICAL)).booleanValue());
    compound.putBoolean("usesBusoHaki", ((Boolean)this.dataManager.get(IS_AFFECTED_BY_HARDENING)).booleanValue());
    compound.putBoolean("affectedByImbuing", ((Boolean)this.dataManager.get(IS_AFFECTED_BY_IMBUING)).booleanValue());
    compound.putBoolean("canPassThroughBlocks", this.canPassThroughBlocks);
    compound.putBoolean("canPassThroughEntities", this.canPassThroughEntities);
    compound.putBoolean("canGetStuckInGround", this.canGetStuckInGround);
    compound.putBoolean("changeHurtTime", this.changeHurtTime);
    compound.putBoolean("armorPiercing", this.armorPiercing);
    compound.putBoolean("canHurtThrower", this.canHurtThrower);
    compound.putInt("ownerUUID", ((Integer)this.dataManager.get(OWNER)).intValue());
  }


  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    setLife(compound.getInt("life"));
    this.maxLife = compound.getInt("maxLife");
    this.hurtTime = compound.getInt("hurtTime");
    this.knockbackStrength = compound.getInt("knockbackStrength");
    this.damage = compound.getFloat("damage");
    this.gravity = compound.getFloat("gravity");
    this.collisionSize = compound.getDouble("collisionSize");
    this.dataManager.set(IS_PHYSICAL, Boolean.valueOf(compound.getBoolean("isPhysical")));
    this.dataManager.set(IS_AFFECTED_BY_HARDENING, Boolean.valueOf(compound.getBoolean("usesBusoHaki")));
    this.dataManager.set(IS_AFFECTED_BY_IMBUING, Boolean.valueOf(compound.getBoolean("affectedByImbuing")));
    this.canPassThroughBlocks = compound.getBoolean("canPassThroughBlocks");
    this.canPassThroughEntities = compound.getBoolean("canPassThroughEntities");
    this.canGetStuckInGround = compound.getBoolean("canGetStuckInGround");
    this.changeHurtTime = compound.getBoolean("changeHurtTime");
    this.armorPiercing = compound.getBoolean("armorPiercing");
    this.canHurtThrower = compound.getBoolean("canHurtThrower");
    this.dataManager.set(OWNER, Integer.valueOf(compound.getInt("ownerUUID")));
  }


  
  protected float getGravityVelocity() {
    return this.gravity;
  }


  
  public boolean isImmuneToExplosions() {
    return true;
  }

  
  public void clearTargets() {
    this.targets.clear();
  }


  
  public void registerData() {
    this.dataManager.register(LIFE, Integer.valueOf(64));
    this.dataManager.register(OWNER, Integer.valueOf(-1));
    this.dataManager.register(IS_PHYSICAL, Boolean.valueOf(false));
    this.dataManager.register(IS_AFFECTED_BY_HARDENING, Boolean.valueOf(false));
    this.dataManager.register(IS_AFFECTED_BY_IMBUING, Boolean.valueOf(false));
  }


  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket((Entity)this);
  }





  
  public void setThrower(LivingEntity entity) {
    this.dataManager.set(OWNER, Integer.valueOf(entity.getEntityId()));
    this.owner = entity;
  }


  
  @Nullable
  public LivingEntity getThrower() {
    return (getDataManager().get(OWNER) != null && this.world.getEntityByID(((Integer)getDataManager().get(OWNER)).intValue()) instanceof LivingEntity) ? (LivingEntity)this.world.getEntityByID(((Integer)getDataManager().get(OWNER)).intValue()) : super.getThrower();
  }

  
  public void setKnockbackStrength(int knockbackStrength) {
    this.knockbackStrength = knockbackStrength;
  }

  
  public double getCollisionSize() {
    return this.collisionSize;
  }

  
  public void setCollisionSize(double val) {
    this.collisionSize = val;
  }

  
  public int getLife() {
    return ((Integer)this.dataManager.get(LIFE)).intValue();
  }

  
  public int getMaxLife() {
    return this.maxLife;
  }

  
  public void setMaxLife(int life) {
    this.maxLife = life;
    setLife(this.maxLife);
  }

  
  public void setLife(int life) {
    this.dataManager.set(LIFE, Integer.valueOf(life));
  }

  
  public void setPhysical(boolean affectedByHardening) {
    this.dataManager.set(IS_PHYSICAL, Boolean.valueOf(true));
    this.dataManager.set(IS_AFFECTED_BY_HARDENING, Boolean.valueOf(affectedByHardening));
  }

  
  public void setAffectedByImbuing() {
    this.dataManager.set(IS_AFFECTED_BY_IMBUING, Boolean.valueOf(true));
  }

  
  public void setAffectedByHardening() {
    this.dataManager.set(IS_AFFECTED_BY_HARDENING, Boolean.valueOf(true));
  }

  
  public void setHurtThrower() {
    this.canHurtThrower = true;
  }

  
  public boolean isPhysical() {
    return ((Boolean)this.dataManager.get(IS_PHYSICAL)).booleanValue();
  }

  
  public boolean isAffectedByHaki() {
    return (isAffectedByHardening() || isAffectedByImbuing());
  }

  
  public boolean isAffectedByHardening() {
    return ((Boolean)this.dataManager.get(IS_AFFECTED_BY_HARDENING)).booleanValue();
  }

  
  public boolean isAffectedByImbuing() {
    return ((Boolean)this.dataManager.get(IS_AFFECTED_BY_IMBUING)).booleanValue();
  }

  
  public void setPassThroughBlocks() {
    this.canPassThroughBlocks = true;
  }

  
  public void setFake() {
    this.isFake = true;
  }

  
  public void setArmorPiercing() {
    this.armorPiercing = true;
  }

  
  public void setPassThroughEntities() {
    this.canPassThroughEntities = true;
  }

  
  public void setCanGetStuckInGround() {
    this.canGetStuckInGround = true;
  }

  
  public void setDamage(float damage) {
    this.damage = damage;
  }

  
  public float getDamage() {
    return this.damage;
  }

  
  public void setGravity(float gravity) {
    this.gravity = gravity;
  }

  
  public boolean isStuckInGround() {
    return this.stuckInGround;
  }

  
  public void setChangeHurtTime(boolean flag) {
    this.changeHurtTime = flag;
  }

  
  public void setHurtTime(int time) {
    this.hurtTime = time;
  }

  
  public void setTargetResetTime(int time) {
    this.targetResetTime = time;
  }

  
  public int getTargetResetTime() {
    return this.targetResetTime;
  }
  
  public static interface IWithEffects extends Serializable {
    EffectInstance[] getEffects();
  }
  
  public static interface IOnTick extends Serializable {
    void onTick();
  }
  
  public static interface IOnBlockImpact extends Serializable {
    void onImpact(BlockPos param1BlockPos);
  }
  
  public static interface IOnEntityImpact extends Serializable {
    void onImpact(LivingEntity param1LivingEntity);
  }
}


