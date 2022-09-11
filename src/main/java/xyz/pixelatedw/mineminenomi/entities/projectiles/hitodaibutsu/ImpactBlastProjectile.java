package xyz.pixelatedw.mineminenomi.entities.projectiles.hitodaibutsu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.hitodaibutsu.ImpactWaveParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class ImpactBlastProjectile extends AbilityProjectileEntity {
  public static final ParticleEffect PARTICLES = (ParticleEffect)new ImpactWaveParticleEffect();

  
  public ImpactBlastProjectile(World world) {
    super(HitoDaibutsuProjectiles.IMPACT_BLAST, world);
  }

  
  public ImpactBlastProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public ImpactBlastProjectile(World world, double x, double y, double z) {
    super(HitoDaibutsuProjectiles.IMPACT_BLAST, world, x, y, z);
  }

  
  public ImpactBlastProjectile(World world, LivingEntity player) {
    super(HitoDaibutsuProjectiles.IMPACT_BLAST, world, player);
    setDamage(60.0F);
    setPhysical(false);
    setAffectedByImbuing();
    setMaxLife(20);
    setCollisionSize(5.0D);
    setPassThroughBlocks();
    setPassThroughEntities();
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (this.ticksExisted > 0) {
      
      if (this.ticksExisted % 2 == 0) {
        
        ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), 5.0F);
        explosion.setHeightDifference(45);
        explosion.setStaticDamage(40.0F * (getLife() / getMaxLife()));
        explosion.doExplosion();
      } 
      
      if (this.ticksExisted % 5 == 0)
      {
        PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), (getMotion()).x, (getMotion()).y, (getMotion()).z);
      }
    } 
  }
}


