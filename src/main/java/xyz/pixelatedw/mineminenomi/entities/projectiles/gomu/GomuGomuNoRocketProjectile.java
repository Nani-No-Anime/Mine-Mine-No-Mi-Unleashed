package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoRocketProjectile extends AbilityProjectileEntity {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GearSecondParticleEffect();
  
  public boolean gear2 = false;
  
  public GomuGomuNoRocketProjectile(World world) {
    super(GomuProjectiles.GOMU_GOMU_NO_ROCKET, world);
  }

  
  public GomuGomuNoRocketProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public GomuGomuNoRocketProjectile(World world, double x, double y, double z) {
    super(GomuProjectiles.GOMU_GOMU_NO_ROCKET, world, x, y, z);
  }

  
  public GomuGomuNoRocketProjectile(World world, LivingEntity player) {
    super(GomuProjectiles.GOMU_GOMU_NO_ROCKET, world, player);
    
    setDamage(4.0F);
    setLife(24);
    setPhysical(true);
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    if (getLife() >= getMaxLife()) {
      return;
    }
    PlayerEntity player = (PlayerEntity)getThrower();
    
    double powerX = Math.abs(hit.getX() - player.getPosX()) / 2.5D;
    double powerY = (hit.getY() - player.getPosY()) / 3.0D;
    double powerZ = Math.abs(hit.getZ() - player.getPosZ()) / 2.5D;
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, powerX, powerZ);
    player.setMotion(speed.x, powerY, speed.z);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    if (this.gear2)
    {
      if (this.ticksExisted % 2 == 0)
        PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
    }
  }
}


