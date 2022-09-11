package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoKongProjectile extends AbilityProjectileEntity {
  public GomuGomuNoKongProjectile(World world) {
    super(GomuProjectiles.GOMU_NO_KONG_GUN, world);
  }

  
  public GomuGomuNoKongProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public GomuGomuNoKongProjectile(World world, double x, double y, double z) {
    super(GomuProjectiles.GOMU_NO_KONG_GUN, world, x, y, z);
  }

  
  public GomuGomuNoKongProjectile(World world, LivingEntity player) {
    super(GomuProjectiles.GOMU_NO_KONG_GUN, world, player);
    
    setDamage(30.0F);
    setMaxLife(30);
    setPhysical(true);
    setCanGetStuckInGround();
    setPassThroughEntities();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
    explosion.setStaticDamage(12.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles(null);
    explosion.setDamageEntities(false);
    explosion.doExplosion();
  }
}


