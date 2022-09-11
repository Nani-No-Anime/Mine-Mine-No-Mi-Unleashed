package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GastilleProjectile extends AbilityProjectileEntity {
  public GastilleProjectile(World world) {
    super(GasuProjectiles.GASTILLE, world);
  }

  
  public GastilleProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public GastilleProjectile(World world, double x, double y, double z) {
    super(GasuProjectiles.GASTILLE, world, x, y, z);
  }

  
  public GastilleProjectile(World world, LivingEntity player) {
    super(GasuProjectiles.GASTILLE, world, player);
    
    setDamage(30.0F);
    setDamageSource(DamageSource.IN_FIRE);
    setPassThroughEntities();
    setCanGetStuckInGround();
    
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.POISON, 200, 3) });


    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 5.0F);
    explosion.setStaticDamage(28.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(true);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
    explosion.setDamageEntities(true);
    explosion.doExplosion();
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 2; i++) {
        
        double offsetX = WyHelper.randomDouble() / 5.0D;
        double offsetY = WyHelper.randomDouble() / 5.0D;
        double offsetZ = WyHelper.randomDouble() / 5.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU2);
        data.setLife(5);
        data.setSize(0.8F);
        data.setColor(0.4F, 0.7F, 1.0F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


