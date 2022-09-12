package xyz.pixelatedw.mineminenomi.entities.projectiles.gura;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GekishinProjectile extends AbilityProjectileEntity {
  public GekishinProjectile(World world) {
    super(GuraProjectiles.GEKISHIN, world);
  }

  
  public GekishinProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public GekishinProjectile(World world, double x, double y, double z) {
    super(GuraProjectiles.GEKISHIN, world, x, y, z);
  }

  
  public GekishinProjectile(World world, LivingEntity player) {
    super(GuraProjectiles.GEKISHIN, world, player);
    setDamage(70.0F);
    setMaxLife(50);
    setCollisionSize(2.0D);
    setPassThroughEntities();
    setPassThroughBlocks();
    setCollisionSize(3.0D);
    
    setDamageSource(this.bypassingSource);
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    for (int i = 0; i < 3; i++) {
      
      if (i % 2 == 0) {
        
        ((ServerWorld)this.world)
          .spawnParticle(ParticleTypes.EXPLOSION, 
            
            getPosX() + WyHelper.randomDouble() * 1.5D, 
            getPosY() + WyHelper.randomDouble() * 1.5D, 
            getPosZ() + WyHelper.randomDouble() * 1.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D);

      
      }
      else {


        
        double offsetX = WyHelper.randomDouble() * 5.0D;
        double offsetY = WyHelper.randomDouble() * 5.0D;
        double offsetZ = WyHelper.randomDouble() * 5.0D;
        
        GenericParticleData data = new GenericParticleData((this.rand.nextDouble() > 0.5D) ? ModParticleTypes.MOKU : ModParticleTypes.MOKU2);
        data.setLife(3);
        data.setSize(10.0F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    } 
    
    int size = 0;
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), 8.0F);
    explosion.setHeightDifference(45);
    explosion.setStaticBlockResistance(1.35F);
    explosion.setProtectOwnerFromFalling(true);
    explosion.setExplosionSound(false);
    explosion.setSmokeParticles(null);
    explosion.setDamageSource(this.bypassingSource);
    explosion.setStaticDamage(15.0F);
    explosion.addRemovedBlocksToList();
    explosion.doExplosion();
    
    for (FallingBlockEntity entity : explosion.removedBlocks) {
      
      entity.setMotion(WyHelper.randomWithRange(-1, 1) / 2.0D, 0.5D + 
          WyHelper.randomDouble(), 
          WyHelper.randomWithRange(-1, 1) / 2.0D);
      entity.velocityChanged = true;
      entity.shouldDropItem = false;
      entity.fallTime = 1;
      this.world.addEntity((Entity)entity);
      size++;
      
      if (size > 50)
        break; 
    } 
  }
}


