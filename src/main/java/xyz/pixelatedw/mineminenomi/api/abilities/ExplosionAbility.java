package xyz.pixelatedw.mineminenomi.api.abilities;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraftforge.event.ForgeEventFactory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.block.HardThrowableProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.KairosekiBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ExplosionAbility
  extends Explosion
{
  private World world;
  private Entity exploder;
  private double explosionX;
  private double explosionY;
  private double explosionZ;
  private float explosionSize;
  private ParticleEffect particles;
  private final List<BlockPos> affectedBlockPositions = Lists.newArrayList();
  private final Map<PlayerEntity, Vec3d> playerKnockbackMap = Maps.newHashMap();
  public List<FallingBlockEntity> removedBlocks = Lists.newArrayList();
  private final Random random = new Random();
  
  private boolean canStartFireAfterExplosion = false;
  private boolean canDestroyBlocks = true;
  private boolean canDropBlocksAfterExplosion = false;
  private boolean canDamageEntities = true;
  private boolean canDamageOwner = false;
  private boolean canAlwaysDamage = true;
  private boolean canProduceExplosionSound = true;
  private boolean protectOwnerFromFalling = false;
  private boolean canCauseKnockback = true;
  private boolean addRemovedBlocksToList = false;
  private float staticDamage = 0.0F;
  private float staticBlockResistance = 0.0F;
  private int heightDifference = 0;
  private int explodedBlocksLimit;
  private int size = 52;
  private int explodedBlocks;
  public ArrayList<Entity> immuneEntities = new ArrayList<>();
  public ArrayList<Entity> damagedEntities = new ArrayList<>();

  
  public ExplosionAbility(Entity entity, World world, double posX, double posY, double posZ, float power) {
    super(world, entity, posX, posY, posZ, power, false, Explosion.Mode.DESTROY);
    this.world = world;
    this.exploder = entity;
    this.explosionSize = power;
    this.explosionX = posX;
    this.explosionY = posY;
    this.explosionZ = posZ;
  }

  
  public void setExplosionPos(double posX, double posY, double posZ) {
    this.explosionX = posX;
    this.explosionY = posY;
    this.explosionZ = posZ;
  }

  
  public void setExplosionSize(float explosionSize) {
    this.explosionSize = explosionSize;
  }

  
  public void setExplodedBlocksLimit(int limit) {
    this.explodedBlocksLimit = limit;
  }

  
  public double getStaticDamage() {
    return this.staticDamage;
  }

  
  public void setStaticDamage(float damage) {
    this.staticDamage = damage;
  }

  
  public double getStaticBlockResistance() {
    return this.staticBlockResistance;
  }

  
  public void setStaticBlockResistance(float damage) {
    this.staticBlockResistance = damage;
  }

  
  public void setHeightDifference(int heightDifference) {
    this.heightDifference = heightDifference;
  }

  
  public void setDamageOwner(boolean damageOwner) {
    this.canDamageOwner = damageOwner;
  }

  
  public void setDamageEntities(boolean damageEntities) {
    this.canDamageEntities = damageEntities;
  }

  
  public void setDropBlocksAfterExplosion(boolean canDrop) {
    this.canDropBlocksAfterExplosion = canDrop;
  }

  
  public void setFireAfterExplosion(boolean hasFire) {
    this.canStartFireAfterExplosion = hasFire;
  }

  
  public void setDestroyBlocks(boolean canDestroyBlocks) {
    this.canDestroyBlocks = canDestroyBlocks;
  }

  
  public void setSmokeParticles(ParticleEffect particle) {
    this.particles = particle;
  }

  
  public boolean getAlwaysDamage() {
    return this.canAlwaysDamage;
  }

  
  public void setAlwaysDamage(boolean flag) {
    this.canAlwaysDamage = flag;
  }

  
  public void addRemovedBlocksToList() {
    this.addRemovedBlocksToList = true;
  }

  
  public void setProtectOwnerFromFalling(boolean flag) {
    this.protectOwnerFromFalling = flag;
  }

  
  public boolean hasSmokeParticles() {
    return (this.particles != null);
  }

  
  public void setExplosionSound(boolean hasSound) {
    this.canProduceExplosionSound = hasSound;
  }
  
  private void resetDamage(LivingEntity entity) {
    entity.hurtTime = entity.hurtResistantTime = 0;
  }
  
  public void disableExplosionKnockback() {
    this.canCauseKnockback = false;
  }

  
  public void doExplosion() {
    boolean flag = (this.exploder != null && this.exploder instanceof PlayerEntity && AbilityHelper.checkForRestriction(this.world, (int)this.explosionX, (int)this.explosionY, (int)this.explosionZ));
    if (flag || (this.heightDifference > 0 && this.exploder != null && this.exploder.getPosY() - this.heightDifference > this.explosionY)) {
      return;
    }
    Set<BlockPos> set = Sets.newHashSet();

    
    if ((this.size + 4) > this.explosionSize) {
      this.size = Math.max((int)(this.explosionSize + 4.0F), 16);
    }
    for (int j = 0; j < this.size; j++) {
      
      for (int k = 0; k < this.size; k++) {
        
        for (int l = 0; l < this.size; l++) {
          
          if (j == 0 || j == this.size - 1 || k == 0 || k == this.size - 1 || l == 0 || l == this.size - 1) {
            
            double d0 = (j / (this.size - 1) * 2.0F - 1.0F);
            double d1 = (k / (this.size - 1) * 2.0F - 1.0F);
            double d2 = (l / (this.size - 1) * 2.0F - 1.0F);
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            d0 /= d3;
            d1 /= d3;
            d2 /= d3;
            float f = this.explosionSize * (0.7F + this.world.rand.nextFloat() * 0.6F);
            double eX = this.explosionX;
            double eY = this.explosionY;
            double eZ = this.explosionZ;
            
            for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
              
              BlockPos blockpos = new BlockPos(eX, eY, eZ);
              BlockState blockState = this.world.getBlockState(blockpos);
              IFluidState ifluidstate = this.world.getFluidState(blockpos);
              if (!blockState.isAir((IBlockReader)this.world, blockpos) || !ifluidstate.isEmpty()) {
                
                float f2 = Math.max(blockState.getExplosionResistance((IWorldReader)this.world, blockpos, this.exploder, this), ifluidstate.getExplosionResistance((IWorldReader)this.world, blockpos, this.exploder, this));
                if (this.exploder != null) {
                  f2 = this.exploder.getExplosionResistance(this, (IBlockReader)this.world, blockpos, blockState, ifluidstate, f2);
                }
                f = (float)(f - ((getStaticBlockResistance() > 0.0D) ? getStaticBlockResistance() : ((f2 + 0.3F) * 0.3F)));
              } 
              
              if (f > 0.0F && (this.exploder == null || this.exploder.canExplosionDestroyBlock(this, (IBlockReader)this.world, blockpos, blockState, f)))
              {
                set.add(blockpos);
              }
              
              eX += d0 * 0.30000001192092896D;
              eY += d1 * 0.30000001192092896D;
              eZ += d2 * 0.30000001192092896D;
            } 
          } 
        } 
      } 
    } 
    
    this.affectedBlockPositions.addAll(set);
    float f3 = this.explosionSize * 2.0F;
    int k1 = MathHelper.floor(this.explosionX - f3 - 1.0D);
    int l1 = MathHelper.floor(this.explosionX + f3 + 1.0D);
    int i2 = MathHelper.floor(this.explosionY - f3 - 1.0D);
    int i1 = MathHelper.floor(this.explosionY + f3 + 1.0D);
    int j2 = MathHelper.floor(this.explosionZ - f3 - 1.0D);
    int j1 = MathHelper.floor(this.explosionZ + f3 + 1.0D);
    List<Entity> list = null;
    if (this.canDamageOwner) {
      list = this.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(k1, i2, j2, l1, i1, j1));
    } else {
      list = this.world.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB(k1, i2, j2, l1, i1, j1));
    } 









    
    list = (List<Entity>)list.stream().filter(e -> { if (e instanceof LivingEntity) { DamageSource source = getDamageSource(); if (source.getImmediateSource() != null) return ((LivingEntity)e).canEntityBeSeen(source.getImmediateSource());  }  return true; }).collect(Collectors.toList());
    
    if (this.immuneEntities.size() > 0) {
      list.removeAll(this.immuneEntities);
    }
    ForgeEventFactory.onExplosionDetonate(this.world, this, list, f3);
    Vec3d vec3d = new Vec3d(this.explosionX, this.explosionY, this.explosionZ);
    
    if (this.canDamageEntities)
    {
      for (int k2 = 0; k2 < list.size(); k2++) {
        
        Entity entity = list.get(k2);
        boolean isInProtection = AbilityHelper.checkForRestriction(this.world, (int)entity.getPosX(), (int)entity.getPosY(), (int)entity.getPosZ());
        
        if (!entity.isImmuneToExplosions() && !isInProtection) {
          
          double distance = entity.getDistanceSq(this.explosionX, this.explosionY, this.explosionZ) / f3;
          if (distance <= 1.0D) {
            
            double xDistance = entity.getPosX() - this.explosionX;
            double yDistance = entity.getPosY() + entity.getEyeHeight() - this.explosionY;
            double zDistance = entity.getPosZ() - this.explosionZ;
            double squareDistance = MathHelper.sqrt(xDistance * xDistance + yDistance * yDistance + zDistance * zDistance);
            if (squareDistance != 0.0D) {
              
              xDistance /= squareDistance;
              yDistance /= squareDistance;
              zDistance /= squareDistance;
              double blockDensity = (getStaticBlockResistance() > 0.0D) ? 0.0D : Explosion.getBlockDensity(vec3d, entity);
              double power = (1.0D - distance) * blockDensity;
              
              if (entity instanceof LivingEntity && getAlwaysDamage()) {
                resetDamage((LivingEntity)entity);
              }
              if (this.staticDamage > 0.0F) {
                
                entity.attackEntityFrom(getDamageSource(), this.staticDamage);
                this.damagedEntities.add(entity);
              }
              else {
                
                float damage = (float)((power * power + power) / 2.0D * 7.0D * f3 + 1.0D);
                entity.attackEntityFrom(getDamageSource(), damage);
                this.damagedEntities.add(entity);
              } 
              
              double blastDamageReduction = power;
              
              if (entity instanceof LivingEntity) {
                blastDamageReduction = ProtectionEnchantment.getBlastDamageReduction((LivingEntity)entity, power);
              }
              if (this.canCauseKnockback) {
                
                entity.setMotion(entity.getMotion().add(xDistance * blastDamageReduction, yDistance * blastDamageReduction, zDistance * blastDamageReduction));
                if (entity instanceof PlayerEntity) {
                  
                  PlayerEntity playerEntity = (PlayerEntity)entity;
                  if (!playerEntity.isSpectator() && (!playerEntity.isCreative() || !playerEntity.abilities.isFlying))
                  {
                    this.playerKnockbackMap.put(playerEntity, new Vec3d(xDistance * power, yDistance * power, zDistance * power));
                  }
                } 
              } 
            } 
          } 
        } 
      } 
    }
    
    if (this.canProduceExplosionSound) {
      this.world.playSound((PlayerEntity)null, this.explosionX, this.explosionY, this.explosionZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
    }
    if (hasSmokeParticles()) {
      this.particles.spawn(this.world, this.explosionX, this.explosionY, this.explosionZ, 0.0D, 0.0D, 0.0D);
    }
    
    if (this.canDestroyBlocks && CommonConfig.INSTANCE.isAbilityGriefingEnabled() && (this.explodedBlocksLimit <= 0 || this.explodedBlocks < this.explodedBlocksLimit)) {
      
      ObjectArrayList<Pair<ItemStack, BlockPos>> objectarraylist = new ObjectArrayList();
      Collections.shuffle(this.affectedBlockPositions, this.world.rand);
      
      for (BlockPos blockpos : this.affectedBlockPositions) {
        
        BlockState blockstate = this.world.getBlockState(blockpos);
        
        if (blockstate.getMaterial() == Material.WATER && !CommonConfig.INSTANCE.getDestroyWater()) {
          continue;
        }
        boolean blockIsKairoseki = KairosekiBlockProtectionRule.INSTANCE.isBanned(blockstate.getBlock());
        boolean blockIsRestricted = RestrictedBlockProtectionRule.INSTANCE.isBanned(blockstate.getBlock());
        boolean hardBlockRestriction = (this.addRemovedBlocksToList && HardThrowableProtectionRule.INSTANCE.isBanned(blockstate.getBlock()));
        boolean inProtectedAreaFlag = ExtendedWorldData.get(this.world).isInsideRestrictedArea(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        
        boolean fallingProtection = true;
        if (this.protectOwnerFromFalling && this.exploder != null) {
          fallingProtection = (Math.sqrt(this.exploder.getDistanceSq(blockpos.getX(), this.exploder.getPosY(), blockpos.getZ())) > 1.5D);
        }
        if (!blockstate.isAir((IBlockReader)this.world, blockpos) && !blockIsKairoseki && !blockIsRestricted && !hardBlockRestriction && fallingProtection && !inProtectedAreaFlag) {
          
          if (this.world instanceof ServerWorld && blockstate.canDropFromExplosion((IBlockReader)this.world, blockpos, this)) {
            
            TileEntity tileentity = blockstate.hasTileEntity() ? this.world.getTileEntity(blockpos) : null;
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)this.world)).withRandom(this.world.rand).withParameter(LootParameters.POSITION, blockpos).withParameter(LootParameters.TOOL, ItemStack.EMPTY).withNullableParameter(LootParameters.BLOCK_ENTITY, tileentity).withNullableParameter(LootParameters.THIS_ENTITY, this.exploder);
            lootcontext$builder.withParameter(LootParameters.EXPLOSION_RADIUS, Float.valueOf(this.explosionSize));
            
            if (this.canDropBlocksAfterExplosion) {
              blockstate.getDrops(lootcontext$builder).forEach(p_229977_2_ -> func_229976_a_(objectarraylist, p_229977_2_, blockpos));
            }
            
            if (this.addRemovedBlocksToList) {
              this.removedBlocks.add(new FallingBlockEntity(this.world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), blockstate));
            }
          } 
          blockstate.onBlockExploded(this.world, blockpos, this);
          this.explodedBlocks++;
        } 
      } 
    } 
    
    if (this.canStartFireAfterExplosion && CommonConfig.INSTANCE.isAbilityGriefingEnabled()) {
      
      Iterator<BlockPos> positions = this.affectedBlockPositions.iterator();
      while (positions.hasNext()) {
        
        BlockPos blockpos1 = positions.next();
        boolean inProtectedAreaFlag = ExtendedWorldData.get(this.world).isInsideRestrictedArea(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
        
        if (this.world.getBlockState(blockpos1).isAir((IBlockReader)this.world, blockpos1) && this.world.getBlockState(blockpos1.down()).isOpaqueCube((IBlockReader)this.world, blockpos1.down()) && this.random.nextInt(5) == 0 && !inProtectedAreaFlag) {
          
          this.world.setBlockState(blockpos1, Blocks.FIRE.getDefaultState());
          positions.remove();
        } 
      } 
    } 
  }

  
  private static void func_229976_a_(ObjectArrayList<Pair<ItemStack, BlockPos>> p_229976_0_, ItemStack p_229976_1_, BlockPos p_229976_2_) {
    int i = p_229976_0_.size();
    
    for (int j = 0; j < i; j++) {
      
      Pair<ItemStack, BlockPos> pair = (Pair<ItemStack, BlockPos>)p_229976_0_.get(j);
      ItemStack itemstack = (ItemStack)pair.getFirst();
      if (ItemEntity.func_226532_a_(itemstack, p_229976_1_)) {
        
        ItemStack itemstack1 = ItemEntity.func_226533_a_(itemstack, p_229976_1_, 16);
        p_229976_0_.set(j, Pair.of(itemstack1, pair.getSecond()));
        if (p_229976_1_.isEmpty()) {
          return;
        }
      } 
    } 

    
    p_229976_0_.add(Pair.of(p_229976_1_, p_229976_2_));
  }


  
  public Map<PlayerEntity, Vec3d> getPlayerKnockbackMap() {
    return this.playerKnockbackMap;
  }
}


