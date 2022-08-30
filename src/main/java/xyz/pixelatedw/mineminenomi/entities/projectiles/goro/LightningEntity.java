/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.effect.LightningBoltEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.KairosekiBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.LightningExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class LightningEntity extends Entity {
/*  43 */   protected static final DataParameter<Float> LENGTH = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
/*  44 */   protected static final DataParameter<Float> SIZE = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
/*  45 */   protected static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
/*  46 */   protected static final DataParameter<Integer> BRANCHES = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
/*  47 */   protected static final DataParameter<Integer> SEGMENTS = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
/*  48 */   protected static final DataParameter<Integer> ALIVE_TICKS = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
/*  49 */   protected static final DataParameter<Integer> EXISTING_TICKS = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
/*  50 */   protected static final DataParameter<Integer> COLOR = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
/*  51 */   protected static final DataParameter<Integer> ANGLE = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
/*  52 */   protected static final DataParameter<Boolean> MIMIC_VANILLA = EntityDataManager.createKey(LightningEntity.class, DataSerializers.BOOLEAN);
/*  53 */   protected static final DataParameter<Boolean> LIGHTNING_MOVEMENT = EntityDataManager.createKey(LightningEntity.class, DataSerializers.BOOLEAN);
/*  54 */   protected static final DataParameter<Boolean> ENERGY_EFFECT = EntityDataManager.createKey(LightningEntity.class, DataSerializers.BOOLEAN);
/*  55 */   private final LightningBoltEntity bolt = new LightningBoltEntity(this.world, 0.0D, 0.0D, 0.0D, false);
/*     */   
/*     */   public long seed;
/*  58 */   private List<Entity> entities = new ArrayList<>();
/*  59 */   private List<BlockPos> contactBlocks = new ArrayList<>();
/*  60 */   public List<BlockPos> firstContactBlocks = new ArrayList<>();
/*  61 */   public List<Integer> targets = new ArrayList<>();
/*  62 */   public ArrayList<Entity> explosionTargets = new ArrayList<>();
/*     */   
/*     */   private Entity thrower;
/*  65 */   private int explosionSize = 0;
/*  66 */   private float explosionBlockResistance = 0.1F;
/*  67 */   private int heightDifference = 0;
/*     */   private boolean explosionDestroysBlocks = true;
/*     */   private boolean firstTick = true;
/*     */   private boolean hasToCheckForTarget = true;
/*     */   private boolean canCauseKnockback = true;
/*  72 */   private double boxSizeDivision = 0.1D;
/*     */   
/*  74 */   private int targetTimeToReset = 20;
/*  75 */   private float travelSpeed = 12.0F;
/*  76 */   private float travelLength = 0.0F;
/*     */   
/*     */   private BlockPos finalPos;
/*     */   
/*     */   private DamageSource source;
/*     */   
/*     */   public LightningEntity(EntityType<LightningEntity> entityType, World world) {
/*  83 */     super(entityType, world);
/*  84 */     this.ignoreFrustumCheck = true;
/*  85 */     this.seed = this.rand.nextLong();
/*     */     
/*  87 */     this.entities = new ArrayList<>();
/*  88 */     this.firstContactBlocks = new ArrayList<>();
/*  89 */     this.contactBlocks = new ArrayList<>();
/*  90 */     this.targets = new ArrayList<>();
/*     */   }
/*     */   
/*     */   public LightningEntity(Entity thrower, float travelLength) {
/*  94 */     this(thrower, thrower.getPosX(), thrower.getPosYEye(), thrower.getPosZ(), thrower.rotationYaw, thrower.rotationPitch, travelLength, travelLength);
/*     */   }
/*     */   
/*     */   public LightningEntity(Entity thrower, float travelLength, float travelSpeed) {
/*  98 */     this(thrower, thrower.getPosX(), thrower.getPosYEye(), thrower.getPosZ(), thrower.rotationYaw, thrower.rotationPitch, travelLength, travelSpeed);
/*     */   }
/*     */ 
/*     */   
/*     */   public LightningEntity(Entity thrower, double posX, double posY, double posZ, float rotationYaw, float rotationPitch, float travelLength, float travelSpeed) {
/* 103 */     this(GoroProjectiles.LIGHTNING, thrower.world);
/*     */     
/* 105 */     setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
/* 106 */     setLength(2.0F);
/* 107 */     this.thrower = thrower;
/* 108 */     this.travelLength = travelLength;
/* 109 */     this.travelSpeed = travelSpeed;
/* 110 */     this.source = (new ModIndirectEntityDamageSource(ModDamageSource.LIGHTNING_BOLT.getDamageType(), this, this.thrower)).setDamageBypassesArmor();
/*     */   }
/*     */   
/*     */   public LightningEntity(World worldIn) {
/* 114 */     super(GoroProjectiles.LIGHTNING, worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityImpactEvent(Entity hitEntity) {
/* 119 */     if (this.targets.contains(Integer.valueOf(hitEntity.getEntity().getEntityId())) || hitEntity.getEntityId() == getEntityId()) {
/*     */       return;
/*     */     }
/* 122 */     if (this.hasToCheckForTarget) {
/* 123 */       this.targets.add(Integer.valueOf(hitEntity.getEntity().getEntityId()));
/*     */     }
/* 125 */     if (getDamage() > 0.0F) {
/*     */       
/* 127 */       if (hitEntity.isAlive() && hitEntity instanceof LivingEntity) {
/*     */         
/* 129 */         if (getMimicVanilla()) {
/*     */           
/* 131 */           hitEntity.setFire(2 + (int)((getFireTimer() / 20) + getDamage() / 5.0F));
/* 132 */           hitEntity.onStruckByLightning(this.bolt);
/*     */         } 
/* 134 */         ((LivingEntity)hitEntity).hurtTime = hitEntity.hurtResistantTime = 0;
/* 135 */         hitEntity.attackEntityFrom(this.source, getDamage());
/* 136 */         onFirstImpact(hitEntity.getPosition());
/*     */       } 
/*     */       
/* 139 */       if (hitEntity instanceof net.minecraft.entity.projectile.ThrowableEntity) {
/*     */         
/* 141 */         if (hitEntity instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)hitEntity).source.getDamageType().equals("lightningBolt")) {
/*     */           return;
/*     */         }
/* 144 */         hitEntity.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockImpact(BlockPos hit) {
/* 152 */     if (this.heightDifference > 0 && this.thrower.getPosY() - this.heightDifference > hit.getY()) {
/*     */       return;
/*     */     }
/* 155 */     Block block = this.world.getBlockState(hit).getBlock();
/* 156 */     boolean blockIsKairoseki = KairosekiBlockProtectionRule.INSTANCE.isBanned(block);
/* 157 */     boolean blockIsRestricted = RestrictedBlockProtectionRule.INSTANCE.isBanned(block);
/*     */     
/* 159 */     if (!blockIsKairoseki && !blockIsRestricted && this.explosionDestroysBlocks) {
/* 160 */       this.world.removeBlock(hit, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onFirstImpact(BlockPos hit) {
/* 168 */     ExplosionAbility explosion = AbilityHelper.newExplosion(this, this.world, hit.getX(), hit.getY(), hit.getZ(), this.explosionSize);
/* 169 */     explosion.immuneEntities = this.explosionTargets;
/* 170 */     explosion.setExplosionSound(false);
/* 171 */     if (!this.canCauseKnockback)
/* 172 */       explosion.disableExplosionKnockback(); 
/* 173 */     explosion.setStaticDamage(getDamage() / 2.0F);
/* 174 */     explosion.setDamageSource(this.source);
/* 175 */     explosion.setDamageOwner(false);
/* 176 */     explosion.setStaticBlockResistance(this.explosionBlockResistance);
/* 177 */     explosion.setDestroyBlocks(this.explosionDestroysBlocks);
/* 178 */     explosion.setHeightDifference(this.heightDifference);
/* 179 */     explosion.setFireAfterExplosion(true);
/* 180 */     explosion.setAlwaysDamage(true);
/* 181 */     explosion.setSmokeParticles((ParticleEffect)new LightningExplosionParticleEffect(this.explosionSize + 1));
/* 182 */     explosion.setExplosionSound(false);
/* 183 */     explosion.setDamageEntities(true);
/* 184 */     explosion.doExplosion();
/* 185 */     this.explosionTargets.addAll(explosion.damagedEntities);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 190 */     super.tick();
/*     */     
/* 192 */     if (this.ticksExisted >= getAliveTicks()) {
/* 193 */       remove();
/*     */     }
/* 195 */     if (getLightningMovement()) {
/* 196 */       this.seed = this.rand.nextLong();
/*     */     }
/* 198 */     if (!this.world.isRemote) {
/*     */       
/* 200 */       boolean addedTick = false;
/*     */       
/* 202 */       if (getLength() != this.travelLength) {
/*     */         
/* 204 */         setAliveTicks(getAliveTicks() + 1);
/* 205 */         setLength(Math.min(getLength() + this.travelSpeed, this.travelLength));
/* 206 */         this.firstTick = true;
/* 207 */         addedTick = true;
/*     */       } 
/*     */       
/* 210 */       if (getExistingTicks() % this.targetTimeToReset == 0 && this.hasToCheckForTarget && !addedTick) {
/*     */         
/* 212 */         this.targets.clear();
/* 213 */         this.explosionTargets.clear();
/*     */       } 
/*     */       
/* 216 */       this.entities.clear();
/* 217 */       this.contactBlocks.clear();
/*     */       
/* 219 */       boolean canExplode = (this.explosionSize > 0 && CommonConfig.INSTANCE.isAbilityGriefingEnabled());
/* 220 */       Vec3d vec3d = getLookVec();
/*     */       
/* 222 */       for (int i = 0; i < getLength(); i++) {
/*     */         
/* 224 */         double currentX = getPosX() + vec3d.x * i;
/* 225 */         double currentY = getPosYEye() + vec3d.y * i;
/* 226 */         double currentZ = getPosZ() + vec3d.z * i;
/* 227 */         double boxSize = 0.2D + getSize() / this.boxSizeDivision;
/*     */         
/* 229 */         AxisAlignedBB alignedBB = new AxisAlignedBB(currentX - boxSize, currentY - boxSize, currentZ - boxSize, currentX + boxSize, currentY + boxSize, currentZ + boxSize);
/*     */ 
/*     */         
/* 232 */         Objects.requireNonNull(this.entities); this.world.getEntitiesWithinAABBExcludingEntity(this.thrower, alignedBB).stream().filter(e -> !this.entities.contains(e)).forEach(this.entities::add);
/*     */         
/* 234 */         if (canExplode || !getMimicVanilla()) {
/*     */           
/* 236 */           BlockPos currentBlockPos = new BlockPos(currentX, currentY, currentZ);
/* 237 */           BlockState blockState = this.world.getBlockState(currentBlockPos);
/* 238 */           if (blockState.getBlock() == Blocks.BARRIER || blockState
/* 239 */             .getBlock() == ModBlocks.BARRIER || blockState
/* 240 */             .getBlock().asItem().isIn(ModTags.Items.KAIROSEKI)) {
/*     */             
/* 242 */             remove();
/*     */             return;
/*     */           } 
/*     */           double x;
/* 246 */           for (x = alignedBB.minX; x < alignedBB.maxX; x++) {
/* 247 */             double y; for (y = alignedBB.minY; y < alignedBB.maxY; y++) {
/* 248 */               double z; for (z = alignedBB.minZ; z < alignedBB.maxZ; z++) {
/* 249 */                 BlockPos blockPos = new BlockPos(x, y, z);
/* 250 */                 BlockState state = this.world.getBlockState(blockPos);
/* 251 */                 if (!state.isAir((IBlockReader)this.world, blockPos) && !this.contactBlocks.contains(blockPos) && !blockPos.equals(currentBlockPos))
/* 252 */                   this.contactBlocks.add(blockPos); 
/*     */               } 
/*     */             } 
/* 255 */           }  if (this.firstTick && !this.world.getBlockState(currentBlockPos).isAir((IBlockReader)this.world, currentBlockPos) && !this.firstContactBlocks.contains(currentBlockPos)) {
/* 256 */             this.firstContactBlocks.add(currentBlockPos);
/*     */           }
/* 258 */           if (i == getLength()) {
/* 259 */             this.finalPos = currentBlockPos;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 264 */       this.entities.forEach(this::onEntityImpactEvent);
/* 265 */       if ((this.ticksExisted % 5 == 0 || this.firstTick) && canExplode) {
/*     */         
/* 267 */         if (this.firstTick) { this.firstContactBlocks.forEach(this::onFirstImpact); }
/* 268 */         else { this.contactBlocks.forEach(this::onBlockImpact); }
/*     */         
/* 270 */         if (getDamage() > 12.0F && this.finalPos != null && this.finalPos.getY() > this.world.getWorldInfo().getGenerator().getCloudHeight()) {
/* 271 */           this.world.getWorldInfo().setThundering(true);
/*     */         }
/*     */       } 
/*     */     } 
/* 275 */     if (this.firstTick && getLength() == this.travelLength) {
/* 276 */       this.firstTick = false;
/* 277 */     } else if (getMimicVanilla()) {
/* 278 */       this.world.setTimeLightningFlash(4);
/*     */     } 
/* 280 */     increaseExistingTicks();
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean isInRangeToRenderDist(double distance) {
/* 286 */     double d0 = 64.0D * getRenderDistanceWeight();
/* 287 */     return (distance < d0 * d0);
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> createSpawnPacket() {
/* 292 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void registerData() {
/* 297 */     this.dataManager.register(LENGTH, Float.valueOf(0.0F));
/* 298 */     this.dataManager.register(BRANCHES, Integer.valueOf(5));
/* 299 */     this.dataManager.register(ANGLE, Integer.valueOf(50));
/* 300 */     this.dataManager.register(SEGMENTS, Integer.valueOf(7));
/* 301 */     this.dataManager.register(SIZE, Float.valueOf(0.01F));
/*     */     
/* 303 */     this.dataManager.register(DAMAGE, Float.valueOf(10.0F));
/* 304 */     this.dataManager.register(ALIVE_TICKS, Integer.valueOf(10));
/* 305 */     this.dataManager.register(EXISTING_TICKS, Integer.valueOf(0));
/* 306 */     this.dataManager.register(COLOR, Integer.valueOf(641023));
/*     */     
/* 308 */     this.dataManager.register(MIMIC_VANILLA, Boolean.valueOf(true));
/* 309 */     this.dataManager.register(LIGHTNING_MOVEMENT, Boolean.valueOf(true));
/* 310 */     this.dataManager.register(ENERGY_EFFECT, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExplosion(int range) {
/* 319 */     this.explosionSize = range;
/*     */   }
/*     */   
/*     */   public void setExplosion(int range, boolean destroysBlocks) {
/* 323 */     this.explosionSize = range;
/* 324 */     this.explosionDestroysBlocks = destroysBlocks;
/*     */   }
/*     */   
/*     */   public void setExplosion(int range, boolean destroysBlocks, float explosionBlockResistance) {
/* 328 */     this.explosionSize = range;
/* 329 */     this.explosionDestroysBlocks = destroysBlocks;
/* 330 */     this.explosionBlockResistance = explosionBlockResistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExplosion(int range, boolean destroysBlocks, float explosionBlockResistance, int heightLimit) {
/* 335 */     this.explosionSize = range;
/* 336 */     this.explosionDestroysBlocks = destroysBlocks;
/* 337 */     this.explosionBlockResistance = explosionBlockResistance;
/* 338 */     this.heightDifference = heightLimit;
/*     */   }
/*     */   
/*     */   public void setTargetTimeToReset(int value) {
/* 342 */     this.targetTimeToReset = value;
/*     */   }
/*     */   
/*     */   public void disableExplosionKnockback() {
/* 346 */     this.canCauseKnockback = false;
/*     */   }
/*     */   
/*     */   public int getAliveTicks() {
/* 350 */     return ((Integer)this.dataManager.get(ALIVE_TICKS)).intValue();
/*     */   }
/*     */   
/*     */   public int getExistingTicks() {
/* 354 */     return ((Integer)this.dataManager.get(EXISTING_TICKS)).intValue();
/*     */   }
/*     */   
/*     */   public void increaseExistingTicks() {
/* 358 */     this.dataManager.set(EXISTING_TICKS, Integer.valueOf(getExistingTicks() + 1));
/*     */   }
/*     */   
/*     */   public void setAliveTicks(int ticks) {
/* 362 */     this.dataManager.set(ALIVE_TICKS, Integer.valueOf(ticks));
/*     */   }
/*     */   
/*     */   public float getDamage() {
/* 366 */     return ((Float)this.dataManager.get(DAMAGE)).floatValue();
/*     */   }
/*     */   
/*     */   public void setDamage(float damage) {
/* 370 */     this.dataManager.set(DAMAGE, Float.valueOf(damage));
/*     */   }
/*     */   
/*     */   public float getLength() {
/* 374 */     return ((Float)this.dataManager.get(LENGTH)).floatValue();
/*     */   }
/*     */   
/*     */   public void setLength(float length) {
/* 378 */     this.dataManager.set(LENGTH, Float.valueOf(length));
/*     */   }
/*     */   
/*     */   public void disableLightningMimic() {
/* 382 */     this.dataManager.set(MIMIC_VANILLA, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public void disableLightningMovement() {
/* 386 */     this.dataManager.set(LIGHTNING_MOVEMENT, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public void setSize(float size) {
/* 390 */     this.dataManager.set(SIZE, Float.valueOf(size));
/*     */   }
/*     */   
/*     */   public float getSize() {
/* 394 */     return ((Float)this.dataManager.get(SIZE)).floatValue();
/*     */   }
/*     */   
/*     */   public int getColor() {
/* 398 */     return ((Integer)this.dataManager.get(COLOR)).intValue();
/*     */   }
/*     */   
/*     */   public boolean getMimicVanilla() {
/* 402 */     return ((Boolean)this.dataManager.get(MIMIC_VANILLA)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getLightningMovement() {
/* 406 */     return ((Boolean)this.dataManager.get(LIGHTNING_MOVEMENT)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setColor(Color color) {
/* 410 */     this.dataManager.set(COLOR, Integer.valueOf(color.getRGB()));
/*     */   }
/*     */   
/*     */   public int getBranches() {
/* 414 */     return ((Integer)this.dataManager.get(BRANCHES)).intValue();
/*     */   }
/*     */   
/*     */   public void setBranches(int branches) {
/* 418 */     this.dataManager.set(BRANCHES, Integer.valueOf(branches));
/*     */   }
/*     */   
/*     */   public int getSegments() {
/* 422 */     return ((Integer)this.dataManager.get(SEGMENTS)).intValue();
/*     */   }
/*     */   
/*     */   public void setSegments(int segments) {
/* 426 */     this.dataManager.set(SEGMENTS, Integer.valueOf(segments));
/*     */   }
/*     */   
/*     */   public int getAngle() {
/* 430 */     return ((Integer)this.dataManager.get(ANGLE)).intValue();
/*     */   }
/*     */   
/*     */   public void setAngle(int angle) {
/* 434 */     this.dataManager.set(BRANCHES, Integer.valueOf(Math.round((float)WyHelper.clamp(angle, 0L, 180L))));
/*     */   }
/*     */   
/*     */   public void setBoxSizeDivision(double value) {
/* 438 */     this.boxSizeDivision = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableEnergyEffect() {
/* 443 */     this.dataManager.set(ENERGY_EFFECT, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getEnergyEffect() {
/* 448 */     return ((Boolean)this.dataManager.get(ENERGY_EFFECT)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 453 */     compound.putInt("alive_ticks", getAliveTicks());
/* 454 */     compound.putFloat("damage", getDamage());
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 459 */     this.dataManager.set(ALIVE_TICKS, Integer.valueOf(compound.getInt("alive_ticks")));
/* 460 */     this.dataManager.set(DAMAGE, Float.valueOf(compound.getFloat("damage")));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\LightningEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */