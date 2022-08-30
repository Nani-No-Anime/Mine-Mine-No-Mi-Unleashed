/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.enchantment.ProtectionEnchantment;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.fluid.IFluidState;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.storage.loot.LootContext;
/*     */ import net.minecraft.world.storage.loot.LootParameters;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.HardThrowableProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.KairosekiBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class ExplosionAbility
/*     */   extends Explosion
/*     */ {
/*     */   private World world;
/*     */   private Entity exploder;
/*     */   private double explosionX;
/*     */   private double explosionY;
/*     */   private double explosionZ;
/*     */   private float explosionSize;
/*     */   private ParticleEffect particles;
/*  61 */   private final List<BlockPos> affectedBlockPositions = Lists.newArrayList();
/*  62 */   private final Map<PlayerEntity, Vec3d> playerKnockbackMap = Maps.newHashMap();
/*  63 */   public List<FallingBlockEntity> removedBlocks = Lists.newArrayList();
/*  64 */   private final Random random = new Random();
/*     */   
/*     */   private boolean canStartFireAfterExplosion = false;
/*     */   private boolean canDestroyBlocks = true;
/*     */   private boolean canDropBlocksAfterExplosion = false;
/*     */   private boolean canDamageEntities = true;
/*     */   private boolean canDamageOwner = false;
/*     */   private boolean canAlwaysDamage = true;
/*     */   private boolean canProduceExplosionSound = true;
/*     */   private boolean protectOwnerFromFalling = false;
/*     */   private boolean canCauseKnockback = true;
/*     */   private boolean addRemovedBlocksToList = false;
/*  76 */   private float staticDamage = 0.0F;
/*  77 */   private float staticBlockResistance = 0.0F;
/*  78 */   private int heightDifference = 0;
/*     */   private int explodedBlocksLimit;
/*  80 */   private int size = 52;
/*     */   private int explodedBlocks;
/*  82 */   public ArrayList<Entity> immuneEntities = new ArrayList<>();
/*  83 */   public ArrayList<Entity> damagedEntities = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public ExplosionAbility(Entity entity, World world, double posX, double posY, double posZ, float power) {
/*  87 */     super(world, entity, posX, posY, posZ, power, false, Explosion.Mode.DESTROY);
/*  88 */     this.world = world;
/*  89 */     this.exploder = entity;
/*  90 */     this.explosionSize = power;
/*  91 */     this.explosionX = posX;
/*  92 */     this.explosionY = posY;
/*  93 */     this.explosionZ = posZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExplosionPos(double posX, double posY, double posZ) {
/*  98 */     this.explosionX = posX;
/*  99 */     this.explosionY = posY;
/* 100 */     this.explosionZ = posZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExplosionSize(float explosionSize) {
/* 105 */     this.explosionSize = explosionSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExplodedBlocksLimit(int limit) {
/* 110 */     this.explodedBlocksLimit = limit;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getStaticDamage() {
/* 115 */     return this.staticDamage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStaticDamage(float damage) {
/* 120 */     this.staticDamage = damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getStaticBlockResistance() {
/* 125 */     return this.staticBlockResistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStaticBlockResistance(float damage) {
/* 130 */     this.staticBlockResistance = damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeightDifference(int heightDifference) {
/* 135 */     this.heightDifference = heightDifference;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamageOwner(boolean damageOwner) {
/* 140 */     this.canDamageOwner = damageOwner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamageEntities(boolean damageEntities) {
/* 145 */     this.canDamageEntities = damageEntities;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDropBlocksAfterExplosion(boolean canDrop) {
/* 150 */     this.canDropBlocksAfterExplosion = canDrop;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFireAfterExplosion(boolean hasFire) {
/* 155 */     this.canStartFireAfterExplosion = hasFire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDestroyBlocks(boolean canDestroyBlocks) {
/* 160 */     this.canDestroyBlocks = canDestroyBlocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSmokeParticles(ParticleEffect particle) {
/* 165 */     this.particles = particle;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAlwaysDamage() {
/* 170 */     return this.canAlwaysDamage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlwaysDamage(boolean flag) {
/* 175 */     this.canAlwaysDamage = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRemovedBlocksToList() {
/* 180 */     this.addRemovedBlocksToList = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProtectOwnerFromFalling(boolean flag) {
/* 185 */     this.protectOwnerFromFalling = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSmokeParticles() {
/* 190 */     return (this.particles != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExplosionSound(boolean hasSound) {
/* 195 */     this.canProduceExplosionSound = hasSound;
/*     */   }
/*     */   
/*     */   private void resetDamage(LivingEntity entity) {
/* 199 */     entity.hurtTime = entity.hurtResistantTime = 0;
/*     */   }
/*     */   
/*     */   public void disableExplosionKnockback() {
/* 203 */     this.canCauseKnockback = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doExplosion() {
/* 208 */     boolean flag = (this.exploder != null && this.exploder instanceof PlayerEntity && AbilityHelper.checkForRestriction(this.world, (int)this.explosionX, (int)this.explosionY, (int)this.explosionZ));
/* 209 */     if (flag || (this.heightDifference > 0 && this.exploder != null && this.exploder.getPosY() - this.heightDifference > this.explosionY)) {
/*     */       return;
/*     */     }
/* 212 */     Set<BlockPos> set = Sets.newHashSet();
/*     */ 
/*     */     
/* 215 */     if ((this.size + 4) > this.explosionSize) {
/* 216 */       this.size = Math.max((int)(this.explosionSize + 4.0F), 16);
/*     */     }
/* 218 */     for (int j = 0; j < this.size; j++) {
/*     */       
/* 220 */       for (int k = 0; k < this.size; k++) {
/*     */         
/* 222 */         for (int l = 0; l < this.size; l++) {
/*     */           
/* 224 */           if (j == 0 || j == this.size - 1 || k == 0 || k == this.size - 1 || l == 0 || l == this.size - 1) {
/*     */             
/* 226 */             double d0 = (j / (this.size - 1) * 2.0F - 1.0F);
/* 227 */             double d1 = (k / (this.size - 1) * 2.0F - 1.0F);
/* 228 */             double d2 = (l / (this.size - 1) * 2.0F - 1.0F);
/* 229 */             double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/* 230 */             d0 /= d3;
/* 231 */             d1 /= d3;
/* 232 */             d2 /= d3;
/* 233 */             float f = this.explosionSize * (0.7F + this.world.rand.nextFloat() * 0.6F);
/* 234 */             double eX = this.explosionX;
/* 235 */             double eY = this.explosionY;
/* 236 */             double eZ = this.explosionZ;
/*     */             
/* 238 */             for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
/*     */               
/* 240 */               BlockPos blockpos = new BlockPos(eX, eY, eZ);
/* 241 */               BlockState blockState = this.world.getBlockState(blockpos);
/* 242 */               IFluidState ifluidstate = this.world.getFluidState(blockpos);
/* 243 */               if (!blockState.isAir((IBlockReader)this.world, blockpos) || !ifluidstate.isEmpty()) {
/*     */                 
/* 245 */                 float f2 = Math.max(blockState.getExplosionResistance((IWorldReader)this.world, blockpos, this.exploder, this), ifluidstate.getExplosionResistance((IWorldReader)this.world, blockpos, this.exploder, this));
/* 246 */                 if (this.exploder != null) {
/* 247 */                   f2 = this.exploder.getExplosionResistance(this, (IBlockReader)this.world, blockpos, blockState, ifluidstate, f2);
/*     */                 }
/* 249 */                 f = (float)(f - ((getStaticBlockResistance() > 0.0D) ? getStaticBlockResistance() : ((f2 + 0.3F) * 0.3F)));
/*     */               } 
/*     */               
/* 252 */               if (f > 0.0F && (this.exploder == null || this.exploder.canExplosionDestroyBlock(this, (IBlockReader)this.world, blockpos, blockState, f)))
/*     */               {
/* 254 */                 set.add(blockpos);
/*     */               }
/*     */               
/* 257 */               eX += d0 * 0.30000001192092896D;
/* 258 */               eY += d1 * 0.30000001192092896D;
/* 259 */               eZ += d2 * 0.30000001192092896D;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 266 */     this.affectedBlockPositions.addAll(set);
/* 267 */     float f3 = this.explosionSize * 2.0F;
/* 268 */     int k1 = MathHelper.floor(this.explosionX - f3 - 1.0D);
/* 269 */     int l1 = MathHelper.floor(this.explosionX + f3 + 1.0D);
/* 270 */     int i2 = MathHelper.floor(this.explosionY - f3 - 1.0D);
/* 271 */     int i1 = MathHelper.floor(this.explosionY + f3 + 1.0D);
/* 272 */     int j2 = MathHelper.floor(this.explosionZ - f3 - 1.0D);
/* 273 */     int j1 = MathHelper.floor(this.explosionZ + f3 + 1.0D);
/*     */     List<Entity> list = null;
/* 275 */     if (this.canDamageOwner) {
/* 276 */       list = this.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(k1, i2, j2, l1, i1, j1));
/*     */     } else {
/* 278 */       list = this.world.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB(k1, i2, j2, l1, i1, j1));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     list = (List<Entity>)list.stream().filter(e -> { if (e instanceof LivingEntity) { DamageSource source = getDamageSource(); if (source.getImmediateSource() != null) return ((LivingEntity)e).canEntityBeSeen(source.getImmediateSource());  }  return true; }).collect(Collectors.toList());
/*     */     
/* 292 */     if (this.immuneEntities.size() > 0) {
/* 293 */       list.removeAll(this.immuneEntities);
/*     */     }
/* 295 */     ForgeEventFactory.onExplosionDetonate(this.world, this, list, f3);
/* 296 */     Vec3d vec3d = new Vec3d(this.explosionX, this.explosionY, this.explosionZ);
/*     */     
/* 298 */     if (this.canDamageEntities)
/*     */     {
/* 300 */       for (int k2 = 0; k2 < list.size(); k2++) {
/*     */         
/* 302 */         Entity entity = list.get(k2);
/* 303 */         boolean isInProtection = AbilityHelper.checkForRestriction(this.world, (int)entity.getPosX(), (int)entity.getPosY(), (int)entity.getPosZ());
/*     */         
/* 305 */         if (!entity.isImmuneToExplosions() && !isInProtection) {
/*     */           
/* 307 */           double distance = entity.getDistanceSq(this.explosionX, this.explosionY, this.explosionZ) / f3;
/* 308 */           if (distance <= 1.0D) {
/*     */             
/* 310 */             double xDistance = entity.getPosX() - this.explosionX;
/* 311 */             double yDistance = entity.getPosY() + entity.getEyeHeight() - this.explosionY;
/* 312 */             double zDistance = entity.getPosZ() - this.explosionZ;
/* 313 */             double squareDistance = MathHelper.sqrt(xDistance * xDistance + yDistance * yDistance + zDistance * zDistance);
/* 314 */             if (squareDistance != 0.0D) {
/*     */               
/* 316 */               xDistance /= squareDistance;
/* 317 */               yDistance /= squareDistance;
/* 318 */               zDistance /= squareDistance;
/* 319 */               double blockDensity = (getStaticBlockResistance() > 0.0D) ? 0.0D : Explosion.getBlockDensity(vec3d, entity);
/* 320 */               double power = (1.0D - distance) * blockDensity;
/*     */               
/* 322 */               if (entity instanceof LivingEntity && getAlwaysDamage()) {
/* 323 */                 resetDamage((LivingEntity)entity);
/*     */               }
/* 325 */               if (this.staticDamage > 0.0F) {
/*     */                 
/* 327 */                 entity.attackEntityFrom(getDamageSource(), this.staticDamage);
/* 328 */                 this.damagedEntities.add(entity);
/*     */               }
/*     */               else {
/*     */                 
/* 332 */                 float damage = (float)((power * power + power) / 2.0D * 7.0D * f3 + 1.0D);
/* 333 */                 entity.attackEntityFrom(getDamageSource(), damage);
/* 334 */                 this.damagedEntities.add(entity);
/*     */               } 
/*     */               
/* 337 */               double blastDamageReduction = power;
/*     */               
/* 339 */               if (entity instanceof LivingEntity) {
/* 340 */                 blastDamageReduction = ProtectionEnchantment.getBlastDamageReduction((LivingEntity)entity, power);
/*     */               }
/* 342 */               if (this.canCauseKnockback) {
/*     */                 
/* 344 */                 entity.setMotion(entity.getMotion().add(xDistance * blastDamageReduction, yDistance * blastDamageReduction, zDistance * blastDamageReduction));
/* 345 */                 if (entity instanceof PlayerEntity) {
/*     */                   
/* 347 */                   PlayerEntity playerEntity = (PlayerEntity)entity;
/* 348 */                   if (!playerEntity.isSpectator() && (!playerEntity.isCreative() || !playerEntity.abilities.isFlying))
/*     */                   {
/* 350 */                     this.playerKnockbackMap.put(playerEntity, new Vec3d(xDistance * power, yDistance * power, zDistance * power));
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 360 */     if (this.canProduceExplosionSound) {
/* 361 */       this.world.playSound((PlayerEntity)null, this.explosionX, this.explosionY, this.explosionZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
/*     */     }
/* 363 */     if (hasSmokeParticles()) {
/* 364 */       this.particles.spawn(this.world, this.explosionX, this.explosionY, this.explosionZ, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 367 */     if (this.canDestroyBlocks && CommonConfig.INSTANCE.isAbilityGriefingEnabled() && (this.explodedBlocksLimit <= 0 || this.explodedBlocks < this.explodedBlocksLimit)) {
/*     */       
/* 369 */       ObjectArrayList<Pair<ItemStack, BlockPos>> objectarraylist = new ObjectArrayList();
/* 370 */       Collections.shuffle(this.affectedBlockPositions, this.world.rand);
/*     */       
/* 372 */       for (BlockPos blockpos : this.affectedBlockPositions) {
/*     */         
/* 374 */         BlockState blockstate = this.world.getBlockState(blockpos);
/*     */         
/* 376 */         if (blockstate.getMaterial() == Material.WATER && !CommonConfig.INSTANCE.getDestroyWater()) {
/*     */           continue;
/*     */         }
/* 379 */         boolean blockIsKairoseki = KairosekiBlockProtectionRule.INSTANCE.isBanned(blockstate.getBlock());
/* 380 */         boolean blockIsRestricted = RestrictedBlockProtectionRule.INSTANCE.isBanned(blockstate.getBlock());
/* 381 */         boolean hardBlockRestriction = (this.addRemovedBlocksToList && HardThrowableProtectionRule.INSTANCE.isBanned(blockstate.getBlock()));
/* 382 */         boolean inProtectedAreaFlag = ExtendedWorldData.get(this.world).isInsideRestrictedArea(blockpos.getX(), blockpos.getY(), blockpos.getZ());
/*     */         
/* 384 */         boolean fallingProtection = true;
/* 385 */         if (this.protectOwnerFromFalling && this.exploder != null) {
/* 386 */           fallingProtection = (Math.sqrt(this.exploder.getDistanceSq(blockpos.getX(), this.exploder.getPosY(), blockpos.getZ())) > 1.5D);
/*     */         }
/* 388 */         if (!blockstate.isAir((IBlockReader)this.world, blockpos) && !blockIsKairoseki && !blockIsRestricted && !hardBlockRestriction && fallingProtection && !inProtectedAreaFlag) {
/*     */           
/* 390 */           if (this.world instanceof ServerWorld && blockstate.canDropFromExplosion((IBlockReader)this.world, blockpos, this)) {
/*     */             
/* 392 */             TileEntity tileentity = blockstate.hasTileEntity() ? this.world.getTileEntity(blockpos) : null;
/* 393 */             LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)this.world)).withRandom(this.world.rand).withParameter(LootParameters.POSITION, blockpos).withParameter(LootParameters.TOOL, ItemStack.EMPTY).withNullableParameter(LootParameters.BLOCK_ENTITY, tileentity).withNullableParameter(LootParameters.THIS_ENTITY, this.exploder);
/* 394 */             lootcontext$builder.withParameter(LootParameters.EXPLOSION_RADIUS, Float.valueOf(this.explosionSize));
/*     */             
/* 396 */             if (this.canDropBlocksAfterExplosion) {
/* 397 */               blockstate.getDrops(lootcontext$builder).forEach(p_229977_2_ -> func_229976_a_(objectarraylist, p_229977_2_, blockpos));
/*     */             }
/*     */             
/* 400 */             if (this.addRemovedBlocksToList) {
/* 401 */               this.removedBlocks.add(new FallingBlockEntity(this.world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), blockstate));
/*     */             }
/*     */           } 
/* 404 */           blockstate.onBlockExploded(this.world, blockpos, this);
/* 405 */           this.explodedBlocks++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 410 */     if (this.canStartFireAfterExplosion && CommonConfig.INSTANCE.isAbilityGriefingEnabled()) {
/*     */       
/* 412 */       Iterator<BlockPos> positions = this.affectedBlockPositions.iterator();
/* 413 */       while (positions.hasNext()) {
/*     */         
/* 415 */         BlockPos blockpos1 = positions.next();
/* 416 */         boolean inProtectedAreaFlag = ExtendedWorldData.get(this.world).isInsideRestrictedArea(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
/*     */         
/* 418 */         if (this.world.getBlockState(blockpos1).isAir((IBlockReader)this.world, blockpos1) && this.world.getBlockState(blockpos1.down()).isOpaqueCube((IBlockReader)this.world, blockpos1.down()) && this.random.nextInt(5) == 0 && !inProtectedAreaFlag) {
/*     */           
/* 420 */           this.world.setBlockState(blockpos1, Blocks.FIRE.getDefaultState());
/* 421 */           positions.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void func_229976_a_(ObjectArrayList<Pair<ItemStack, BlockPos>> p_229976_0_, ItemStack p_229976_1_, BlockPos p_229976_2_) {
/* 429 */     int i = p_229976_0_.size();
/*     */     
/* 431 */     for (int j = 0; j < i; j++) {
/*     */       
/* 433 */       Pair<ItemStack, BlockPos> pair = (Pair<ItemStack, BlockPos>)p_229976_0_.get(j);
/* 434 */       ItemStack itemstack = (ItemStack)pair.getFirst();
/* 435 */       if (ItemEntity.func_226532_a_(itemstack, p_229976_1_)) {
/*     */         
/* 437 */         ItemStack itemstack1 = ItemEntity.func_226533_a_(itemstack, p_229976_1_, 16);
/* 438 */         p_229976_0_.set(j, Pair.of(itemstack1, pair.getSecond()));
/* 439 */         if (p_229976_1_.isEmpty()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 446 */     p_229976_0_.add(Pair.of(p_229976_1_, p_229976_2_));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<PlayerEntity, Vec3d> getPlayerKnockbackMap() {
/* 452 */     return this.playerKnockbackMap;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\ExplosionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */