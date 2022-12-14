package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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

public class YasakaniNoMagatamaProjectile extends AbilityProjectileEntity {
  public YasakaniNoMagatamaProjectile(World world) {
    super(PikaProjectiles.YASAKANI_NO_MAGATAMA, world);
  }

  
  public YasakaniNoMagatamaProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public YasakaniNoMagatamaProjectile(World world, double x, double y, double z) {
    super(PikaProjectiles.YASAKANI_NO_MAGATAMA, world, x, y, z);
  }

  
  public YasakaniNoMagatamaProjectile(World world, LivingEntity player) {
    super(PikaProjectiles.YASAKANI_NO_MAGATAMA, world, player);
    
    setDamage(4.0F);
    setChangeHurtTime(true);
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
    explosion.setStaticDamage(4.0F);
    explosion.disableExplosionKnockback();
    explosion.setAlwaysDamage(true);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
    explosion.doExplosion();
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote) {
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.PIKA);
      data.setLife(30);
      data.setSize(2.5F);
      data.setRotation(Vector3f.YP);
      WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX(), getPosY(), getPosZ());
    } 
  }
}


