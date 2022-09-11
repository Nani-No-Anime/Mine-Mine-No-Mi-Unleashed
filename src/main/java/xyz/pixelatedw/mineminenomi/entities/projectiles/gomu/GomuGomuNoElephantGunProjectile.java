package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoElephantGunProjectile extends AbilityProjectileEntity {
  public GomuGomuNoElephantGunProjectile(World world) {
    super(GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN, world);
  }

  
  public GomuGomuNoElephantGunProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public GomuGomuNoElephantGunProjectile(World world, double x, double y, double z) {
    super(GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN, world, x, y, z);
  }

  
  public GomuGomuNoElephantGunProjectile(World world, LivingEntity player) {
    super(GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN, world, player);
    
    setDamage(24.0F);
    setMaxLife(12);
    setPhysical(true);
    setCanGetStuckInGround();
    setPassThroughEntities();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
    explosion.setStaticDamage(8.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles(null);
    explosion.setDamageEntities(false);
    explosion.doExplosion();
  }
}


