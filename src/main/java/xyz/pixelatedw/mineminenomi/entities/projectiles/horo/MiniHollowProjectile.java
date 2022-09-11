package xyz.pixelatedw.mineminenomi.entities.projectiles.horo;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class MiniHollowProjectile extends AbilityProjectileEntity {
  public MiniHollowProjectile(World world) {
    super(HoroProjectiles.MINI_HOLLOW, world);
  }

  
  public MiniHollowProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public MiniHollowProjectile(World world, double x, double y, double z) {
    super(HoroProjectiles.MINI_HOLLOW, world, x, y, z);
  }

  
  public MiniHollowProjectile(World world, LivingEntity player) {
    super(HoroProjectiles.MINI_HOLLOW, world, player);
    
    setDamage(2.5F);
    setChangeHurtTime(true);
    
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.NAUSEA, 150, 0), new EffectInstance(Effects.SLOWNESS, 150, 0) });





    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos pos) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, pos.getX(), pos.getY(), pos.getZ(), 1.0F);
    explosion.setStaticDamage(7.5F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.setDamageEntities(false);
    explosion.doExplosion();
  }
}


