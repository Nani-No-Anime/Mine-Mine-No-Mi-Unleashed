package xyz.pixelatedw.mineminenomi.entities.projectiles.beta;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class StickyProjectile extends AbilityProjectileEntity {
  public StickyProjectile(World world) {
    super(BetaProjectiles.STICKY_PROJECTILE, world);
  }

  
  public StickyProjectile(EntityType type, World world) {
    super(type, world);
  }
  private boolean causeExplosion = false;
  
  public StickyProjectile(World world, double x, double y, double z) {
    super(BetaProjectiles.STICKY_PROJECTILE, world, x, y, z);
  }

  
  public StickyProjectile(World world, LivingEntity player) {
    super(BetaProjectiles.STICKY_PROJECTILE, world, player);
    
    setDamage(2.0F);
    setMaxLife(20);
    setGravity(0.025F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote) {
      int i;
      for (i = 0; i < 4; i++) {
        
        double offsetX = WyHelper.randomDouble() / 5.0D;
        double offsetY = WyHelper.randomDouble() / 5.0D;
        double offsetZ = WyHelper.randomDouble() / 5.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.BETA);
        data.setLife(10);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + 0.25D + offsetY, getPosZ() + offsetZ);
      } 
      
      if (this.causeExplosion)
      {
        for (i = 0; i < 2; i++) {
          
          double offsetX = WyHelper.randomDouble() / 2.0D;
          double offsetY = WyHelper.randomDouble() / 2.0D;
          double offsetZ = WyHelper.randomDouble() / 2.0D;
          
          GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
          data.setLife(7);
          data.setSize(1.2F);
          WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
        } 
      }
    } 
  }

  
  private void onEntityImpactEvent(LivingEntity entity) {
    if (this.causeExplosion) {
      
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 2.0F);
      explosion.setStaticDamage(10.0F);
      explosion.setExplosionSound(true);
      explosion.setDamageOwner(false);
      explosion.setDestroyBlocks(true);
      explosion.setFireAfterExplosion(false);
      explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
      explosion.setDamageEntities(true);
      explosion.doExplosion();
    }
    else {
      
      entity.addPotionEffect(new EffectInstance(ModEffects.STICKY, 300, 0, false, false));
      for (int i = 0; i < 20; i++) {
        
        double offsetX = WyHelper.randomWithRange(-2, 2);
        double offsetZ = WyHelper.randomWithRange(-2, 2);
        BlockPos location = new BlockPos(getPosX() + offsetX, getPosY() - 1.0D, getPosZ() + offsetZ);
        if (this.world.getBlockState(location.down()).isSolid()) {
          AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.MUCUS, DefaultProtectionRules.AIR_FOLIAGE);
        }
      } 
    } 
  }
  
  private void onBlockImpactEvent(BlockPos hit) {
    if (this.causeExplosion) {
      
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
      explosion.setStaticDamage(10.0F);
      explosion.setExplosionSound(true);
      explosion.setDamageOwner(false);
      explosion.setDestroyBlocks(true);
      explosion.setFireAfterExplosion(true);
      explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
      explosion.setDamageEntities(true);
      explosion.doExplosion();
    }
    else {
      
      for (int i = 0; i < 20; i++) {
        
        double offsetX = WyHelper.randomWithRange(-2, 2);
        double offsetZ = WyHelper.randomWithRange(-2, 2);
        BlockPos location = new BlockPos(getPosX() + offsetX, getPosY(), getPosZ() + offsetZ);
        if (this.world.getBlockState(location.down()).isSolid()) {
          AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.MUCUS, DefaultProtectionRules.AIR_FOLIAGE);
        }
      } 
    } 
  }
  
  public void setCauseExplosion() {
    this.causeExplosion = true;
  }
}


