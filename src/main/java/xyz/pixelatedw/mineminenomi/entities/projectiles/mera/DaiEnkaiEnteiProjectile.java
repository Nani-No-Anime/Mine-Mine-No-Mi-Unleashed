package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.SnowLayerBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;

import java.util.List;

public class DaiEnkaiEnteiProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { LiquidBlockProtectionRule.INSTANCE, SnowLayerBlockProtectionRule.INSTANCE });

  
  public DaiEnkaiEnteiProjectile(World world) {
    super(MeraProjectiles.DAI_ENKAI_ENTEI, world);
  }

  
  public DaiEnkaiEnteiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public DaiEnkaiEnteiProjectile(World world, double x, double y, double z) {
    super(MeraProjectiles.DAI_ENKAI_ENTEI, world, x, y, z);
  }

  
  public DaiEnkaiEnteiProjectile(World world, LivingEntity player) {
    super(MeraProjectiles.DAI_ENKAI_ENTEI, world, player);
    
    setDamage(80.0F);
    setDamageSource(ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 14.0F);
    explosion.setStaticDamage(80.0F);
    explosion.setStaticBlockResistance(0.25F);
    explosion.setFireAfterExplosion(true);
    explosion.setSmokeParticles(new CommonExplosionParticleEffect(15));
    explosion.setDamageSource(ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
    explosion.doExplosion();
  }

  
  private void onTickEvent() {
    if (areEyesInFluid(FluidTags.WATER) && CommonConfig.INSTANCE.getDestroyWater()) {
      
      List<BlockPos> coords = AbilityHelper.createFilledSphere(getEntityWorld(), (int)getPosX(), (int)getPosY(), (int)getPosZ(), 9, Blocks.AIR, GRIEF_RULE);
      for (BlockPos blockPos : coords) {
        
        WyHelper.spawnParticles(ParticleTypes.BUBBLE, (ServerWorld)getEntityWorld(), blockPos.getX() + WyHelper.randomDouble() / 2.0D, blockPos.getY() + 0.8D, blockPos.getZ() + WyHelper.randomDouble() / 2.0D);
        getEntityWorld().addParticle(ParticleTypes.SMOKE, blockPos.getX(), blockPos.getY() + 1.1D, blockPos.getZ(), 0.0D, 0.0D, 0.0D);
      } 
    } 
    
    if (!this.world.isRemote) {
      int i;
      for (i = 0; i < 20; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
        data.setLife(6);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
      
      for (i = 0; i < 2; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
        data.setLife(4);
        data.setSize(1.2F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    } 
  }


  
  public void registerData() {
    super.registerData();
    this.dataManager.register(SIZE, Float.valueOf(0.0F));
  }

  
  public void increaseSize() {
    this.dataManager.set(SIZE, Float.valueOf(Math.min(((Float)this.dataManager.get(SIZE)).floatValue() + 0.1F, 17.5F)));
  }


  
  public void setSize(float size) {
    this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 17.5F)));
  }


  
  public float getSize() {
    return ((Float)this.dataManager.get(SIZE)).floatValue();
  }
}


