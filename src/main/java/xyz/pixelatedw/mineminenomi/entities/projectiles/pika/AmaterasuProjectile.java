package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;
import java.lang.invoke.SerializedLambda;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
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

public class AmaterasuProjectile extends AbilityProjectileEntity {
  public AmaterasuProjectile(World world) {
    super(PikaProjectiles.AMATERASU, world);
  }

  
  public AmaterasuProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public AmaterasuProjectile(World world, double x, double y, double z) {
    super(PikaProjectiles.AMATERASU, world, x, y, z);
  }

  
  public AmaterasuProjectile(World world, LivingEntity player) {
    super(PikaProjectiles.AMATERASU, world, player);
    
    setDamage(70.0F);
    setArmorPiercing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), getDamage() / 4.0F);
    explosion.setStaticDamage(getDamage() * 0.75F);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(getDamage() / 6.0F)));
    explosion.doExplosion();
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote) {
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.PIKA);
      data.setLife(40);
      data.setSize(10.0F);
      data.setRotation(Vector3f.YP);
      WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX(), getPosY(), getPosZ());
    } 
  }
}


