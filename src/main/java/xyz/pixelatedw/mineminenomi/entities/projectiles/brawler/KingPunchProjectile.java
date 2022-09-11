package xyz.pixelatedw.mineminenomi.entities.projectiles.brawler;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class KingPunchProjectile extends AbilityProjectileEntity {
  public KingPunchProjectile(World world) {
    super(BrawlerProjectiles.KING_PUNCH, world);
  }

  
  public KingPunchProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public KingPunchProjectile(World world, double x, double y, double z) {
    super(BrawlerProjectiles.KING_PUNCH, world, x, y, z);
  }

  
  public KingPunchProjectile(World world, LivingEntity player) {
    super(BrawlerProjectiles.KING_PUNCH, world, player);
    
    setPhysical(true);
    setCollisionSize(4.0D);
    setMaxLife(30);
    setPassThroughEntities();
    setCanGetStuckInGround();
    setDamageSource(this.bypassingSource);
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 20; i++) {
        
        double offsetX = WyHelper.randomDouble() / 1.25D;
        double offsetY = WyHelper.randomDouble() / 1.25D;
        double offsetZ = WyHelper.randomDouble() / 1.25D;
        
        ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.POOF, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
        ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.EXPLOSION_EMITTER, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
      } 
    }
  }
}


