package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.block.KairosekiBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModTags;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.LightningExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class LightningEntity extends Entity {
  protected static final DataParameter<Float> LENGTH = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
  protected static final DataParameter<Float> SIZE = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
  protected static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
  protected static final DataParameter<Integer> BRANCHES = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
  protected static final DataParameter<Integer> SEGMENTS = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
  protected static final DataParameter<Integer> ALIVE_TICKS = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
  protected static final DataParameter<Integer> EXISTING_TICKS = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
  protected static final DataParameter<Integer> COLOR = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
  protected static final DataParameter<Integer> ANGLE = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
  protected static final DataParameter<Boolean> MIMIC_VANILLA = EntityDataManager.createKey(LightningEntity.class, DataSerializers.BOOLEAN);
  protected static final DataParameter<Boolean> LIGHTNING_MOVEMENT = EntityDataManager.createKey(LightningEntity.class, DataSerializers.BOOLEAN);
  protected static final DataParameter<Boolean> ENERGY_EFFECT = EntityDataManager.createKey(LightningEntity.class, DataSerializers.BOOLEAN);
  private final LightningBoltEntity bolt = new LightningBoltEntity(this.world, 0.0D, 0.0D, 0.0D, false);
  
  public long seed;
  private List<Entity> entities = new ArrayList<>();
  private List<BlockPos> contactBlocks = new ArrayList<>();
  public List<BlockPos> firstContactBlocks = new ArrayList<>();
  public List<Integer> targets = new ArrayList<>();
  public ArrayList<Entity> explosionTargets = new ArrayList<>();
  
  private Entity thrower;
  private int explosionSize = 0;
  private float explosionBlockResistance = 0.1F;
  private int heightDifference = 0;
  private boolean explosionDestroysBlocks = true;
  private boolean firstTick = true;
  private boolean hasToCheckForTarget = true;
  private boolean canCauseKnockback = true;
  private double boxSizeDivision = 0.1D;
  
  private int targetTimeToReset = 20;
  private float travelSpeed = 12.0F;
  private float travelLength = 0.0F;
  
  private BlockPos finalPos;
  
  private DamageSource source;
  
  public LightningEntity(EntityType<LightningEntity> entityType, World world) {
    super(entityType, world);
    this.ignoreFrustumCheck = true;
    this.seed = this.rand.nextLong();
    
    this.entities = new ArrayList<>();
    this.firstContactBlocks = new ArrayList<>();
    this.contactBlocks = new ArrayList<>();
    this.targets = new ArrayList<>();
  }
  
  public LightningEntity(Entity thrower, float travelLength) {
    this(thrower, thrower.getPosX(), thrower.getPosYEye(), thrower.getPosZ(), thrower.rotationYaw, thrower.rotationPitch, travelLength, travelLength);
  }
  
  public LightningEntity(Entity thrower, float travelLength, float travelSpeed) {
    this(thrower, thrower.getPosX(), thrower.getPosYEye(), thrower.getPosZ(), thrower.rotationYaw, thrower.rotationPitch, travelLength, travelSpeed);
  }

  
  public LightningEntity(Entity thrower, double posX, double posY, double posZ, float rotationYaw, float rotationPitch, float travelLength, float travelSpeed) {
    this(GoroProjectiles.LIGHTNING, thrower.world);
    
    setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
    setLength(2.0F);
    this.thrower = thrower;
    this.travelLength = travelLength;
    this.travelSpeed = travelSpeed;
    this.source = (new ModIndirectEntityDamageSource(ModDamageSource.LIGHTNING_BOLT.getDamageType(), this, this.thrower)).setDamageBypassesArmor();
  }
  
  public LightningEntity(World worldIn) {
    super(GoroProjectiles.LIGHTNING, worldIn);
  }

  
  public void onEntityImpactEvent(Entity hitEntity) {
    if (this.targets.contains(Integer.valueOf(hitEntity.getEntity().getEntityId())) || hitEntity.getEntityId() == getEntityId()) {
      return;
    }
    if (this.hasToCheckForTarget) {
      this.targets.add(Integer.valueOf(hitEntity.getEntity().getEntityId()));
    }
    if (getDamage() > 0.0F) {
      
      if (hitEntity.isAlive() && hitEntity instanceof LivingEntity) {
        
        if (getMimicVanilla()) {
          
          hitEntity.setFire(2 + (int)((getFireTimer() / 20) + getDamage() / 5.0F));
          hitEntity.onStruckByLightning(this.bolt);
        } 
        ((LivingEntity)hitEntity).hurtTime = hitEntity.hurtResistantTime = 0;
        hitEntity.attackEntityFrom(this.source, getDamage());
        onFirstImpact(hitEntity.getPosition());
      } 
      
      if (hitEntity instanceof net.minecraft.entity.projectile.ThrowableEntity) {
        
        if (hitEntity instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)hitEntity).source.getDamageType().equals("lightningBolt")) {
          return;
        }
        hitEntity.remove();
      } 
    } 
  }


  
  public void onBlockImpact(BlockPos hit) {
    if (this.heightDifference > 0 && this.thrower.getPosY() - this.heightDifference > hit.getY()) {
      return;
    }
    Block block = this.world.getBlockState(hit).getBlock();
    boolean blockIsKairoseki = KairosekiBlockProtectionRule.INSTANCE.isBanned(block);
    boolean blockIsRestricted = RestrictedBlockProtectionRule.INSTANCE.isBanned(block);
    
    if (!blockIsKairoseki && !blockIsRestricted && this.explosionDestroysBlocks) {
      this.world.removeBlock(hit, false);
    }
  }



  
  public void onFirstImpact(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion(this, this.world, hit.getX(), hit.getY(), hit.getZ(), this.explosionSize);
    explosion.immuneEntities = this.explosionTargets;
    explosion.setExplosionSound(false);
    if (!this.canCauseKnockback)
      explosion.disableExplosionKnockback(); 
    explosion.setStaticDamage(getDamage() / 2.0F);
    explosion.setDamageSource(this.source);
    explosion.setDamageOwner(false);
    explosion.setStaticBlockResistance(this.explosionBlockResistance);
    explosion.setDestroyBlocks(this.explosionDestroysBlocks);
    explosion.setHeightDifference(this.heightDifference);
    explosion.setFireAfterExplosion(true);
    explosion.setAlwaysDamage(true);
    explosion.setSmokeParticles((ParticleEffect)new LightningExplosionParticleEffect(this.explosionSize + 1));
    explosion.setExplosionSound(false);
    explosion.setDamageEntities(true);
    explosion.doExplosion();
    this.explosionTargets.addAll(explosion.damagedEntities);
  }

  
  public void tick() {
    super.tick();
    
    if (this.ticksExisted >= getAliveTicks()) {
      remove();
    }
    if (getLightningMovement()) {
      this.seed = this.rand.nextLong();
    }
    if (!this.world.isRemote) {
      
      boolean addedTick = false;
      
      if (getLength() != this.travelLength) {
        
        setAliveTicks(getAliveTicks() + 1);
        setLength(Math.min(getLength() + this.travelSpeed, this.travelLength));
        this.firstTick = true;
        addedTick = true;
      } 
      
      if (getExistingTicks() % this.targetTimeToReset == 0 && this.hasToCheckForTarget && !addedTick) {
        
        this.targets.clear();
        this.explosionTargets.clear();
      } 
      
      this.entities.clear();
      this.contactBlocks.clear();
      
      boolean canExplode = (this.explosionSize > 0 && CommonConfig.INSTANCE.isAbilityGriefingEnabled());
      Vec3d vec3d = getLookVec();
      
      for (int i = 0; i < getLength(); i++) {
        
        double currentX = getPosX() + vec3d.x * i;
        double currentY = getPosYEye() + vec3d.y * i;
        double currentZ = getPosZ() + vec3d.z * i;
        double boxSize = 0.2D + getSize() / this.boxSizeDivision;
        
        AxisAlignedBB alignedBB = new AxisAlignedBB(currentX - boxSize, currentY - boxSize, currentZ - boxSize, currentX + boxSize, currentY + boxSize, currentZ + boxSize);

        
        Objects.requireNonNull(this.entities); this.world.getEntitiesWithinAABBExcludingEntity(this.thrower, alignedBB).stream().filter(e -> !this.entities.contains(e)).forEach(this.entities::add);
        
        if (canExplode || !getMimicVanilla()) {
          
          BlockPos currentBlockPos = new BlockPos(currentX, currentY, currentZ);
          BlockState blockState = this.world.getBlockState(currentBlockPos);
          if (blockState.getBlock() == Blocks.BARRIER || blockState
            .getBlock() == ModBlocks.BARRIER || blockState
            .getBlock().asItem().isIn(ModTags.Items.KAIROSEKI)) {
            
            remove();
            return;
          } 
          double x;
          for (x = alignedBB.minX; x < alignedBB.maxX; x++) {
            double y; for (y = alignedBB.minY; y < alignedBB.maxY; y++) {
              double z; for (z = alignedBB.minZ; z < alignedBB.maxZ; z++) {
                BlockPos blockPos = new BlockPos(x, y, z);
                BlockState state = this.world.getBlockState(blockPos);
                if (!state.isAir((IBlockReader)this.world, blockPos) && !this.contactBlocks.contains(blockPos) && !blockPos.equals(currentBlockPos))
                  this.contactBlocks.add(blockPos); 
              } 
            } 
          }  if (this.firstTick && !this.world.getBlockState(currentBlockPos).isAir((IBlockReader)this.world, currentBlockPos) && !this.firstContactBlocks.contains(currentBlockPos)) {
            this.firstContactBlocks.add(currentBlockPos);
          }
          if (i == getLength()) {
            this.finalPos = currentBlockPos;
          }
        } 
      } 
      
      this.entities.forEach(this::onEntityImpactEvent);
      if ((this.ticksExisted % 5 == 0 || this.firstTick) && canExplode) {
        
        if (this.firstTick) { this.firstContactBlocks.forEach(this::onFirstImpact); }
        else { this.contactBlocks.forEach(this::onBlockImpact); }
        
        if (getDamage() > 12.0F && this.finalPos != null && this.finalPos.getY() > this.world.getWorldInfo().getGenerator().getCloudHeight()) {
          this.world.getWorldInfo().setThundering(true);
        }
      } 
    } 
    if (this.firstTick && getLength() == this.travelLength) {
      this.firstTick = false;
    } else if (getMimicVanilla()) {
      this.world.setTimeLightningFlash(4);
    } 
    increaseExistingTicks();
  }

  
  @OnlyIn(Dist.CLIENT)
  public boolean isInRangeToRenderDist(double distance) {
    double d0 = 64.0D * getRenderDistanceWeight();
    return (distance < d0 * d0);
  }

  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }

  
  protected void registerData() {
    this.dataManager.register(LENGTH, Float.valueOf(0.0F));
    this.dataManager.register(BRANCHES, Integer.valueOf(5));
    this.dataManager.register(ANGLE, Integer.valueOf(50));
    this.dataManager.register(SEGMENTS, Integer.valueOf(7));
    this.dataManager.register(SIZE, Float.valueOf(0.01F));
    
    this.dataManager.register(DAMAGE, Float.valueOf(10.0F));
    this.dataManager.register(ALIVE_TICKS, Integer.valueOf(10));
    this.dataManager.register(EXISTING_TICKS, Integer.valueOf(0));
    this.dataManager.register(COLOR, Integer.valueOf(641023));
    
    this.dataManager.register(MIMIC_VANILLA, Boolean.valueOf(true));
    this.dataManager.register(LIGHTNING_MOVEMENT, Boolean.valueOf(true));
    this.dataManager.register(ENERGY_EFFECT, Boolean.valueOf(true));
  }





  
  public void setExplosion(int range) {
    this.explosionSize = range;
  }
  
  public void setExplosion(int range, boolean destroysBlocks) {
    this.explosionSize = range;
    this.explosionDestroysBlocks = destroysBlocks;
  }
  
  public void setExplosion(int range, boolean destroysBlocks, float explosionBlockResistance) {
    this.explosionSize = range;
    this.explosionDestroysBlocks = destroysBlocks;
    this.explosionBlockResistance = explosionBlockResistance;
  }

  
  public void setExplosion(int range, boolean destroysBlocks, float explosionBlockResistance, int heightLimit) {
    this.explosionSize = range;
    this.explosionDestroysBlocks = destroysBlocks;
    this.explosionBlockResistance = explosionBlockResistance;
    this.heightDifference = heightLimit;
  }
  
  public void setTargetTimeToReset(int value) {
    this.targetTimeToReset = value;
  }
  
  public void disableExplosionKnockback() {
    this.canCauseKnockback = false;
  }
  
  public int getAliveTicks() {
    return ((Integer)this.dataManager.get(ALIVE_TICKS)).intValue();
  }
  
  public int getExistingTicks() {
    return ((Integer)this.dataManager.get(EXISTING_TICKS)).intValue();
  }
  
  public void increaseExistingTicks() {
    this.dataManager.set(EXISTING_TICKS, Integer.valueOf(getExistingTicks() + 1));
  }
  
  public void setAliveTicks(int ticks) {
    this.dataManager.set(ALIVE_TICKS, Integer.valueOf(ticks));
  }
  
  public float getDamage() {
    return ((Float)this.dataManager.get(DAMAGE)).floatValue();
  }
  
  public void setDamage(float damage) {
    this.dataManager.set(DAMAGE, Float.valueOf(damage));
  }
  
  public float getLength() {
    return ((Float)this.dataManager.get(LENGTH)).floatValue();
  }
  
  public void setLength(float length) {
    this.dataManager.set(LENGTH, Float.valueOf(length));
  }
  
  public void disableLightningMimic() {
    this.dataManager.set(MIMIC_VANILLA, Boolean.valueOf(false));
  }
  
  public void disableLightningMovement() {
    this.dataManager.set(LIGHTNING_MOVEMENT, Boolean.valueOf(false));
  }
  
  public void setSize(float size) {
    this.dataManager.set(SIZE, Float.valueOf(size));
  }
  
  public float getSize() {
    return ((Float)this.dataManager.get(SIZE)).floatValue();
  }
  
  public int getColor() {
    return ((Integer)this.dataManager.get(COLOR)).intValue();
  }
  
  public boolean getMimicVanilla() {
    return ((Boolean)this.dataManager.get(MIMIC_VANILLA)).booleanValue();
  }
  
  public boolean getLightningMovement() {
    return ((Boolean)this.dataManager.get(LIGHTNING_MOVEMENT)).booleanValue();
  }
  
  public void setColor(Color color) {
    this.dataManager.set(COLOR, Integer.valueOf(color.getRGB()));
  }
  
  public int getBranches() {
    return ((Integer)this.dataManager.get(BRANCHES)).intValue();
  }
  
  public void setBranches(int branches) {
    this.dataManager.set(BRANCHES, Integer.valueOf(branches));
  }
  
  public int getSegments() {
    return ((Integer)this.dataManager.get(SEGMENTS)).intValue();
  }
  
  public void setSegments(int segments) {
    this.dataManager.set(SEGMENTS, Integer.valueOf(segments));
  }
  
  public int getAngle() {
    return ((Integer)this.dataManager.get(ANGLE)).intValue();
  }
  
  public void setAngle(int angle) {
    this.dataManager.set(BRANCHES, Integer.valueOf(Math.round((float)WyHelper.clamp(angle, 0L, 180L))));
  }
  
  public void setBoxSizeDivision(double value) {
    this.boxSizeDivision = value;
  }

  
  public void disableEnergyEffect() {
    this.dataManager.set(ENERGY_EFFECT, Boolean.valueOf(false));
  }

  
  public boolean getEnergyEffect() {
    return ((Boolean)this.dataManager.get(ENERGY_EFFECT)).booleanValue();
  }

  
  public void readAdditional(CompoundNBT compound) {
    compound.putInt("alive_ticks", getAliveTicks());
    compound.putFloat("damage", getDamage());
  }

  
  public void writeAdditional(CompoundNBT compound) {
    this.dataManager.set(ALIVE_TICKS, Integer.valueOf(compound.getInt("alive_ticks")));
    this.dataManager.set(DAMAGE, Float.valueOf(compound.getFloat("damage")));
  }
}


