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

public class TokuHollowProjectile extends AbilityProjectileEntity {
  public TokuHollowProjectile(World world) {
    super(HoroProjectiles.TOKU_HOLLOW, world);
  }

  
  public TokuHollowProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public TokuHollowProjectile(World world, double x, double y, double z) {
    super(HoroProjectiles.TOKU_HOLLOW, world, x, y, z);
  }

  
  public TokuHollowProjectile(World world, LivingEntity player) {
    super(HoroProjectiles.TOKU_HOLLOW, world, player);
    
    setDamage(25.0F);
    
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.NAUSEA, 350, 1), new EffectInstance(Effects.SLOWNESS, 350, 1) });



    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos pos) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, pos.getX(), pos.getY(), pos.getZ(), 7.0F);
    explosion.setStaticDamage(35.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(7));
    explosion.setDamageEntities(true);
    explosion.doExplosion();
  }
}


