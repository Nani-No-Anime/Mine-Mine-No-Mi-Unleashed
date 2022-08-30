/*     */ package xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.fluid.Fluid;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.tags.Tag;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IndirectEntityDamageSource;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileBlockEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileHitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileShootEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbilityProjectileEntity
/*     */   extends ThrowableEntity
/*     */ {
/*  63 */   private int maxLife = 64;
/*  64 */   private int knockbackStrength = 0;
/*  65 */   private double collisionSize = 1.0D;
/*  66 */   private float damage = 0.1F;
/*  67 */   private float gravity = 1.0E-4F;
/*     */   
/*     */   private boolean canPassThroughBlocks = false;
/*     */   private boolean canPassThroughEntities = false;
/*     */   private boolean canGetStuckInGround = false;
/*     */   protected boolean stuckInGround = false;
/*     */   private boolean changeHurtTime = false;
/*     */   private boolean armorPiercing = false;
/*     */   private boolean canHurtThrower = false;
/*     */   private boolean isFake = false;
/*  77 */   private int hurtTime = 10;
/*     */   boolean entityDamaged = false;
/*     */   boolean applyOnlyOnce = true;
/*  80 */   private List<Integer> targets = new ArrayList<>();
/*  81 */   private int targetResetTime = 20;
/*  82 */   private static final DataParameter<Integer> OWNER = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.VARINT);
/*  83 */   private static final DataParameter<Integer> LIFE = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.VARINT);
/*  84 */   private static final DataParameter<Boolean> IS_PHYSICAL = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.BOOLEAN);
/*  85 */   private static final DataParameter<Boolean> IS_AFFECTED_BY_HARDENING = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.BOOLEAN);
/*  86 */   private static final DataParameter<Boolean> IS_AFFECTED_BY_IMBUING = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.BOOLEAN);
/*     */ 
/*     */   
/*     */   public IOnEntityImpact onEntityImpactEvent;
/*     */   
/*     */   public IOnBlockImpact onBlockImpactEvent;
/*     */   
/*     */   public IOnTick onTickEvent;
/*     */   
/*     */   @Deprecated
/*     */   public IWithEffects withEffects;
/*     */   
/*     */   public DamageSource source;
/*     */   
/*     */   public DamageSource bypassingSource;
/*     */   
/* 102 */   private static final Block[] NON_SOLID_BLOCKS = new Block[] { Blocks.SUNFLOWER, Blocks.GRASS, Blocks.TALL_GRASS, Blocks.SEAGRASS, Blocks.TALL_SEAGRASS, Blocks.VINE, Blocks.REDSTONE_WIRE, Blocks.DEAD_BUSH, Blocks.ROSE_BUSH, ModBlocks.OPE };
/*     */ 
/*     */   
/*     */   public AbilityProjectileEntity(EntityType type, World world) {
/* 106 */     super(type, world); this.onEntityImpactEvent = (hitEntity -> { if (!this.targets.contains(Integer.valueOf(hitEntity.getEntityId())))
/*     */           this.onBlockImpactEvent.onImpact(hitEntity.getPosition()); 
/*     */       }); this.onBlockImpactEvent = (hit -> { 
/*     */       }); this.onTickEvent = (() -> {
/*     */       
/* 111 */       }); this.withEffects = (() -> new EffectInstance[0]); } public AbilityProjectileEntity(EntityType type, World world, double x, double y, double z) { super(type, x, y, z, world); this.onEntityImpactEvent = (hitEntity -> { if (!this.targets.contains(Integer.valueOf(hitEntity.getEntityId())))
/*     */           this.onBlockImpactEvent.onImpact(hitEntity.getPosition()); 
/*     */       }); this.onBlockImpactEvent = (hit -> { 
/*     */       }); this.onTickEvent = (() -> {
/*     */       
/* 116 */       }); this.withEffects = (() -> new EffectInstance[0]); } public AbilityProjectileEntity(EntityType type, World world, LivingEntity thrower) { super(type, thrower, world); this.onEntityImpactEvent = (hitEntity -> { if (!this.targets.contains(Integer.valueOf(hitEntity.getEntityId())))
/* 117 */           this.onBlockImpactEvent.onImpact(hitEntity.getPosition());  }); this.onBlockImpactEvent = (hit -> {  }); this.onTickEvent = (() -> {  }); this.withEffects = (() -> new EffectInstance[0]); this.maxLife = getLife();
/* 118 */     this.damage = 0.1F;
/* 119 */     setThrower(thrower);
/*     */     
/* 121 */     this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)thrower)).setProjectile();
/* 122 */     this.bypassingSource = (new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)thrower)).setProjectile().setDamageBypassesArmor(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 128 */     super.tick();
/*     */     
/* 130 */     if (!this.world.isRemote) {
/*     */       EntityRayTraceResult entityRayTraceResult=null;
/* 132 */       if (getLife() <= 0) {
/*     */         
/* 134 */         setLife(this.maxLife);
/*     */         
/* 136 */         remove();
/*     */         
/*     */         return;
/*     */       } 
/* 140 */       setLife(getLife() - 1);
/*     */       
/* 142 */       if (ExtendedWorldData.get(this.world).isInsideRestrictedArea((int)getPosX(), (int)getPosY(), (int)getPosZ())) {
/*     */         
/* 144 */         remove();
/*     */         
/*     */         return;
/*     */       } 
/* 148 */       Vec3d vec31 = new Vec3d(getPosX(), getPosY(), getPosZ());
/* 149 */       Vec3d vec3 = new Vec3d(getPosX() + (getMotion()).x, getPosY() + (getMotion()).y, getPosZ() + (getMotion()).z);
/* 150 */       BlockRayTraceResult blockRayTraceResult = this.world.rayTraceBlocks(new RayTraceContext(vec3, vec31, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, this));
/*     */       
/* 152 */       double sizeX = this.collisionSize;
/* 153 */       double sizeY = this.collisionSize;
/* 154 */       double sizeZ = this.collisionSize;
/*     */       
/* 156 */       AxisAlignedBB aabb = (new AxisAlignedBB(getPosX(), getPosY(), getPosZ(), getPosX(), getPosY(), getPosZ())).grow(sizeX, sizeY, sizeZ);
/* 157 */       List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, aabb);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 162 */       list = (List<LivingEntity>)list.stream().filter(target -> (target instanceof LivingEntity && target.canBeCollidedWith() && target != getThrower()) ? target.canEntityBeSeen(this) : true).collect(Collectors.toList());
/*     */       
/* 164 */       Entity target = list.stream().findAny().orElse(null);
/*     */       
/* 166 */       if (target != null) {
/* 167 */         entityRayTraceResult = new EntityRayTraceResult(target);
/*     */       }
/* 169 */       if (entityRayTraceResult!=null && entityRayTraceResult.getType() == RayTraceResult.Type.ENTITY) {
/* 170 */         onImpact((RayTraceResult)entityRayTraceResult);
/*     */       }
/* 172 */       if (this.ticksExisted % getTargetResetTime() == 0) {
/* 173 */         clearTargets();
/*     */       }
/*     */     } 
/* 176 */     this.onTickEvent.onTick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
/* 182 */     ProjectileShootEvent event = new ProjectileShootEvent(this, velocity, inaccuracy);
/* 183 */     if (MinecraftForge.EVENT_BUS.post(event))
/*     */       return; 
/* 185 */     clearTargets();
/* 186 */     super.shoot(entityThrower, rotationPitchIn, rotationYawIn, pitchOffset, velocity, inaccuracy);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleFluidAcceleration(Tag<Fluid> fluidTag) {
/* 192 */     if (this.inWater)
/* 193 */       doWaterSplashEffect(); 
/* 194 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(RayTraceResult hit) {
/* 200 */     if (!isAlive()) {
/*     */       return;
/*     */     }
/* 203 */     if (!this.world.isRemote)
/*     */     {
/* 205 */       if (hit.getType() == RayTraceResult.Type.ENTITY) {
/*     */         
/* 207 */         EntityRayTraceResult entityHit = (EntityRayTraceResult)hit;
/*     */         
/* 209 */         if (entityHit.getEntity() instanceof LivingEntity && getThrower() != null) {
/*     */           
/* 211 */           LivingEntity hitEntity = (LivingEntity)entityHit.getEntity();
/* 212 */           IEntityStats statProps = EntityStatsCapability.get(getThrower());
/*     */           
/* 214 */           if (hitEntity == getThrower() && !this.canHurtThrower) {
/*     */             return;
/*     */           }
/* 217 */           ProjectileHitEvent event = new ProjectileHitEvent(this, hit);
/* 218 */           if (MinecraftForge.EVENT_BUS.post(event)) {
/*     */             return;
/*     */           }
/* 221 */           if (!this.entityDamaged && !this.targets.contains(Integer.valueOf(hitEntity.getEntity().getEntityId())) && hitEntity.getEntity().isAlive()) {
/*     */             
/* 223 */             if (this.source == null) {
/* 224 */               this.source = (new IndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile();
/*     */             }
/* 226 */             if (this.bypassingSource == null) {
/* 227 */               this.bypassingSource = (new IndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().setDamageBypassesArmor();
/*     */             }
/* 229 */             if (!canBlockDamageSource(this.source, hitEntity)) {
/*     */               
/* 231 */               if (isAffectedByHardening() && getThrower() != null && this.applyOnlyOnce) {
/*     */                 
/* 233 */                 IHakiData hakiProps = HakiDataCapability.get(getThrower());
/* 234 */                 IAbilityData attackerAbilityProps = AbilityDataCapability.get(getThrower());
/* 235 */                 Ability busoHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiHardeningAbility.INSTANCE);
/* 236 */                 boolean hasBusoHakiActive = (busoHaki != null && busoHaki.isContinuous());
/*     */                 
/* 238 */                 Ability fullBodyBusoHaki = attackerAbilityProps.getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/* 239 */                 boolean hasFullBodyBusoHakiActive = (fullBodyBusoHaki != null && fullBodyBusoHaki.isContinuous());
/*     */                 
/* 241 */                 if (hasBusoHakiActive || hasFullBodyBusoHakiActive)
/* 242 */                   this.damage = (float)(this.damage * (1.0D + Math.min((hakiProps.getBusoshokuHardeningHakiExp() / (
/* 243 */                       isPhysical() ? 'E' : 'E')), 0.5D))); 
/* 244 */                 WyDebug.debug("Hardening Haki Projectile Damage: " + this.damage);
/* 245 */                 this.applyOnlyOnce = false;
/*     */               } 
/*     */               
/* 248 */               this.damage = (float)(this.damage * statProps.getDamageMultiplier());
/*     */               
/* 250 */               if (this.armorPiercing) {
/*     */                 
/* 252 */                 float reduction = getArmorDamage();
/* 253 */                 this.entityDamaged = hitEntity.attackEntityFrom(this.source, this.damage - reduction);
/* 254 */                 hitEntity.hurtTime = hitEntity.hurtResistantTime = 0;
/* 255 */                 hitEntity.attackEntityFrom(this.bypassingSource, reduction);
/*     */ 
/*     */               
/*     */               }
/* 259 */               else if (this.damage == 0.0F && this.isFake) {
/* 260 */                 this.entityDamaged = true;
/*     */               } else {
/* 262 */                 this.entityDamaged = hitEntity.attackEntityFrom(this.source, this.damage);
/*     */               
/*     */               }
/*     */ 
/*     */             
/*     */             }
/* 268 */             else if (hitEntity.getActiveItemStack().getItem() instanceof net.minecraft.item.ShieldItem) {
/*     */               
/* 270 */               hitEntity.getActiveItemStack().damageItem((int)(getDamage() + 17.0F), hitEntity, p_220282_1_ -> p_220282_1_.sendBreakAnimation(hitEntity.getActiveHand()));
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 279 */           if (this.entityDamaged) {
/*     */             
/* 281 */             triggerEffects(hitEntity);
/* 282 */             this.onEntityImpactEvent.onImpact(hitEntity);
/*     */             
/* 284 */             if (this.knockbackStrength > 0) {
/*     */               
/* 286 */               Vec3d vec3d = getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale(this.knockbackStrength * 0.6D);
/* 287 */               if (vec3d.lengthSquared() > 0.0D)
/*     */               {
/* 289 */                 hitEntity.addVelocity(vec3d.x, 0.1D, vec3d.z);
/*     */               }
/*     */             } 
/*     */             
/* 293 */             if (!this.canPassThroughEntities) {
/*     */               
/* 295 */               remove();
/*     */               
/*     */               return;
/*     */             } 
/*     */             
/* 300 */             this.targets.add(Integer.valueOf(entityHit.getEntity().getEntityId()));
/* 301 */             this.entityDamaged = false;
/*     */           } 
/*     */ 
/*     */           
/* 305 */           if (this.changeHurtTime) {
/* 306 */             hitEntity.hurtResistantTime = this.hurtTime;
/*     */           }
/* 308 */         } else if (entityHit.getEntity() instanceof AbilityProjectileEntity) {
/*     */           
/* 310 */           AbilityProjectileEntity entity = (AbilityProjectileEntity)entityHit.getEntity();
/* 311 */           onProjectileCollision(this, entity);
/*     */         }
/*     */       
/* 314 */       } else if (hit.getType() == RayTraceResult.Type.BLOCK) {
/*     */         
/* 316 */         BlockRayTraceResult blockHit = (BlockRayTraceResult)hit;
/*     */         
/* 318 */         ProjectileHitEvent event = new ProjectileHitEvent(this, hit);
/* 319 */         if (MinecraftForge.EVENT_BUS.post(event)) {
/*     */           return;
/*     */         }
/* 322 */         BlockState state = this.world.getBlockState(blockHit.getPos());
/*     */         
/* 324 */         if (!passedThroughNonSolidBlock(blockHit.getPos())) {
/*     */           
/* 326 */           if (state.getBlock() == Blocks.BARRIER || state
/* 327 */             .getBlock() == ModBlocks.BARRIER || state
/* 328 */             .getBlock().asItem().isIn(ModTags.Items.KAIROSEKI)) {
/*     */             
/* 330 */             this.onBlockImpactEvent.onImpact(blockHit.getPos());
/* 331 */             remove();
/*     */             
/*     */             return;
/*     */           } 
/* 335 */           if (!this.canPassThroughBlocks) {
/*     */             
/* 337 */             this.onBlockImpactEvent.onImpact(blockHit.getPos());
/* 338 */             if (!this.canGetStuckInGround) {
/* 339 */               remove();
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {
/* 348 */     boolean isPhysical = owner.isPhysical();
/* 349 */     boolean isTargetPhysical = target.isPhysical();
/* 350 */     boolean isDamageLarger = (owner.getDamage() > target.getDamage());
/*     */     
/* 352 */     if (isPhysical) {
/*     */       
/* 354 */       if (isTargetPhysical) {
/*     */         
/* 356 */         if (isDamageLarger) {
/*     */           
/* 358 */           target.remove();
/*     */         } else {
/*     */           
/* 361 */           owner.remove();
/*     */         } 
/*     */       } else {
/*     */         
/* 365 */         owner.remove();
/*     */       }
/*     */     
/*     */     }
/* 369 */     else if (isTargetPhysical) {
/* 370 */       target.remove();
/*     */     
/*     */     }
/* 373 */     else if (isDamageLarger) {
/* 374 */       target.remove();
/*     */     } else {
/* 376 */       owner.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 383 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getArmorDamage() {
/* 388 */     float reduction = (float)(this.damage * (0.1D + (this.damage / 150.0F)));
/* 389 */     if (reduction > this.damage)
/* 390 */       reduction = this.damage; 
/* 391 */     return reduction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerEffects(LivingEntity hitEntity) {
/* 396 */     if ((this.withEffects.getEffects()).length > 0)
/*     */     {
/* 398 */       for (EffectInstance instance : this.withEffects.getEffects()) {
/*     */         
/* 400 */         hitEntity.addPotionEffect(instance);
/* 401 */         if (getThrower() instanceof ServerPlayerEntity) {
/* 402 */           ((ServerPlayerEntity)getThrower()).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(hitEntity.getEntityId(), instance));
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canBlockDamageSource(DamageSource damageSource, LivingEntity target) {
/* 409 */     if (damageSource == null) {
/* 410 */       return false;
/*     */     }
/* 412 */     ProjectileBlockEvent event = new ProjectileBlockEvent(damageSource.getImmediateSource());
/* 413 */     boolean flag = event.canBlock();
/*     */     
/* 415 */     if (!damageSource.isUnblockable() && target.isActiveItemStackBlocking() && flag) {
/*     */       
/* 417 */       Vec3d vec3d2 = damageSource.getDamageLocation();
/* 418 */       if (vec3d2 != null) {
/*     */         
/* 420 */         Vec3d vec3d = getLook(1.0F);
/* 421 */         Vec3d vec3d1 = vec3d2.subtractReverse(new Vec3d(target.getPosX(), target.getPosY(), target.getPosZ())).normalize();
/* 422 */         vec3d1 = new Vec3d(vec3d1.x, 0.0D, vec3d1.z);
/* 423 */         return (vec3d1.dotProduct(vec3d) < 0.0D);
/*     */       } 
/*     */     } 
/*     */     
/* 427 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamageSource(DamageSource s) {
/* 433 */     this.source = s;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean passedThroughNonSolidBlock(BlockPos pos) {
/* 438 */     return Arrays.<Block>stream(NON_SOLID_BLOCKS).anyMatch(block -> (this.world.getBlockState(pos).getBlock() == block));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/* 444 */     super.remove();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 450 */     super.writeAdditional(compound);
/* 451 */     compound.putInt("life", getLife());
/* 452 */     compound.putInt("maxLife", this.maxLife);
/* 453 */     compound.putInt("hurtTime", this.hurtTime);
/* 454 */     compound.putInt("knockbackStrength", this.knockbackStrength);
/* 455 */     compound.putFloat("damage", this.damage);
/* 456 */     compound.putFloat("gravity", this.gravity);
/* 457 */     compound.putDouble("collisionSize", this.collisionSize);
/* 458 */     compound.putBoolean("isPhysical", ((Boolean)this.dataManager.get(IS_PHYSICAL)).booleanValue());
/* 459 */     compound.putBoolean("usesBusoHaki", ((Boolean)this.dataManager.get(IS_AFFECTED_BY_HARDENING)).booleanValue());
/* 460 */     compound.putBoolean("affectedByImbuing", ((Boolean)this.dataManager.get(IS_AFFECTED_BY_IMBUING)).booleanValue());
/* 461 */     compound.putBoolean("canPassThroughBlocks", this.canPassThroughBlocks);
/* 462 */     compound.putBoolean("canPassThroughEntities", this.canPassThroughEntities);
/* 463 */     compound.putBoolean("canGetStuckInGround", this.canGetStuckInGround);
/* 464 */     compound.putBoolean("changeHurtTime", this.changeHurtTime);
/* 465 */     compound.putBoolean("armorPiercing", this.armorPiercing);
/* 466 */     compound.putBoolean("canHurtThrower", this.canHurtThrower);
/* 467 */     compound.putInt("ownerUUID", ((Integer)this.dataManager.get(OWNER)).intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 473 */     super.readAdditional(compound);
/* 474 */     setLife(compound.getInt("life"));
/* 475 */     this.maxLife = compound.getInt("maxLife");
/* 476 */     this.hurtTime = compound.getInt("hurtTime");
/* 477 */     this.knockbackStrength = compound.getInt("knockbackStrength");
/* 478 */     this.damage = compound.getFloat("damage");
/* 479 */     this.gravity = compound.getFloat("gravity");
/* 480 */     this.collisionSize = compound.getDouble("collisionSize");
/* 481 */     this.dataManager.set(IS_PHYSICAL, Boolean.valueOf(compound.getBoolean("isPhysical")));
/* 482 */     this.dataManager.set(IS_AFFECTED_BY_HARDENING, Boolean.valueOf(compound.getBoolean("usesBusoHaki")));
/* 483 */     this.dataManager.set(IS_AFFECTED_BY_IMBUING, Boolean.valueOf(compound.getBoolean("affectedByImbuing")));
/* 484 */     this.canPassThroughBlocks = compound.getBoolean("canPassThroughBlocks");
/* 485 */     this.canPassThroughEntities = compound.getBoolean("canPassThroughEntities");
/* 486 */     this.canGetStuckInGround = compound.getBoolean("canGetStuckInGround");
/* 487 */     this.changeHurtTime = compound.getBoolean("changeHurtTime");
/* 488 */     this.armorPiercing = compound.getBoolean("armorPiercing");
/* 489 */     this.canHurtThrower = compound.getBoolean("canHurtThrower");
/* 490 */     this.dataManager.set(OWNER, Integer.valueOf(compound.getInt("ownerUUID")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getGravityVelocity() {
/* 496 */     return this.gravity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isImmuneToExplosions() {
/* 502 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearTargets() {
/* 507 */     this.targets.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerData() {
/* 513 */     this.dataManager.register(LIFE, Integer.valueOf(64));
/* 514 */     this.dataManager.register(OWNER, Integer.valueOf(-1));
/* 515 */     this.dataManager.register(IS_PHYSICAL, Boolean.valueOf(false));
/* 516 */     this.dataManager.register(IS_AFFECTED_BY_HARDENING, Boolean.valueOf(false));
/* 517 */     this.dataManager.register(IS_AFFECTED_BY_IMBUING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> createSpawnPacket() {
/* 523 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThrower(LivingEntity entity) {
/* 532 */     this.dataManager.set(OWNER, Integer.valueOf(entity.getEntityId()));
/* 533 */     this.owner = entity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getThrower() {
/* 540 */     return (getDataManager().get(OWNER) != null && this.world.getEntityByID(((Integer)getDataManager().get(OWNER)).intValue()) instanceof LivingEntity) ? (LivingEntity)this.world.getEntityByID(((Integer)getDataManager().get(OWNER)).intValue()) : super.getThrower();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKnockbackStrength(int knockbackStrength) {
/* 545 */     this.knockbackStrength = knockbackStrength;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getCollisionSize() {
/* 550 */     return this.collisionSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCollisionSize(double val) {
/* 555 */     this.collisionSize = val;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLife() {
/* 560 */     return ((Integer)this.dataManager.get(LIFE)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxLife() {
/* 565 */     return this.maxLife;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxLife(int life) {
/* 570 */     this.maxLife = life;
/* 571 */     setLife(this.maxLife);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLife(int life) {
/* 576 */     this.dataManager.set(LIFE, Integer.valueOf(life));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhysical(boolean affectedByHardening) {
/* 581 */     this.dataManager.set(IS_PHYSICAL, Boolean.valueOf(true));
/* 582 */     this.dataManager.set(IS_AFFECTED_BY_HARDENING, Boolean.valueOf(affectedByHardening));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAffectedByImbuing() {
/* 587 */     this.dataManager.set(IS_AFFECTED_BY_IMBUING, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAffectedByHardening() {
/* 592 */     this.dataManager.set(IS_AFFECTED_BY_HARDENING, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHurtThrower() {
/* 597 */     this.canHurtThrower = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPhysical() {
/* 602 */     return ((Boolean)this.dataManager.get(IS_PHYSICAL)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAffectedByHaki() {
/* 607 */     return (isAffectedByHardening() || isAffectedByImbuing());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAffectedByHardening() {
/* 612 */     return ((Boolean)this.dataManager.get(IS_AFFECTED_BY_HARDENING)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAffectedByImbuing() {
/* 617 */     return ((Boolean)this.dataManager.get(IS_AFFECTED_BY_IMBUING)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassThroughBlocks() {
/* 622 */     this.canPassThroughBlocks = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFake() {
/* 627 */     this.isFake = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArmorPiercing() {
/* 632 */     this.armorPiercing = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassThroughEntities() {
/* 637 */     this.canPassThroughEntities = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanGetStuckInGround() {
/* 642 */     this.canGetStuckInGround = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamage(float damage) {
/* 647 */     this.damage = damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDamage() {
/* 652 */     return this.damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGravity(float gravity) {
/* 657 */     this.gravity = gravity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStuckInGround() {
/* 662 */     return this.stuckInGround;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChangeHurtTime(boolean flag) {
/* 667 */     this.changeHurtTime = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHurtTime(int time) {
/* 672 */     this.hurtTime = time;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTargetResetTime(int time) {
/* 677 */     this.targetResetTime = time;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTargetResetTime() {
/* 682 */     return this.targetResetTime;
/*     */   }
/*     */   
/*     */   public static interface IWithEffects extends Serializable {
/*     */     EffectInstance[] getEffects();
/*     */   }
/*     */   
/*     */   public static interface IOnTick extends Serializable {
/*     */     void onTick();
/*     */   }
/*     */   
/*     */   public static interface IOnBlockImpact extends Serializable {
/*     */     void onImpact(BlockPos param1BlockPos);
/*     */   }
/*     */   
/*     */   public static interface IOnEntityImpact extends Serializable {
/*     */     void onImpact(LivingEntity param1LivingEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\projectiles\AbilityProjectileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */