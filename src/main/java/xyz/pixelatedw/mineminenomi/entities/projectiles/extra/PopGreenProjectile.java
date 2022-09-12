package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PopGreenProjectile extends AbilityProjectileEntity {
  private PopGreenType type;
  
  public PopGreenProjectile(World world) { super(ExtraProjectiles.POP_GREEN, world);











    
    this.type = null; } public PopGreenProjectile(EntityType type, World world) { super(type, world); this.type = null; } public PopGreenProjectile(World world, double x, double y, double z) { super(ExtraProjectiles.POP_GREEN, world, x, y, z); this.type = null; }

  
  public PopGreenProjectile(World world, LivingEntity player, PopGreenType type) {
    super(ExtraProjectiles.POP_GREEN, world, player); this.type = null;
    this.type = type;
    setDamage((this.type == PopGreenType.IMPACT_WOLF) ? 25.0F : 4.0F);
    setPhysical(false);
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (this.type == PopGreenType.IMPACT_WOLF && 
      !this.world.isRemote) {
      int i;
      for (i = 0; i < 8; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
        data.setLife(10);
        data.setSize(2.5F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
      
      for (i = 0; i < 4; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA2);
        data.setLife(7);
        data.setSize(1.0F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    } 
  }
  private void onBlockImpactEvent(BlockPos block) {
    ExplosionAbility bakuhatsu_explosion;
    ExplosionAbility wolf_explosion;
    switch (this.type) {





      
      case BAKUHATSU:
        bakuhatsu_explosion = AbilityHelper.newExplosion((Entity)this.owner, this.world, block.getX(), block.getY(), block.getZ(), 1.0F);
        bakuhatsu_explosion.setExplosionSound(true);
        bakuhatsu_explosion.setDestroyBlocks(true);
        bakuhatsu_explosion.setFireAfterExplosion(false);
        bakuhatsu_explosion.setDamageOwner(true);
        bakuhatsu_explosion.setDamageEntities(true);
        bakuhatsu_explosion.doExplosion();
        break;
      case IMPACT_WOLF:
        wolf_explosion = AbilityHelper.newExplosion((Entity)this.owner, this.world, block.getX(), block.getY(), block.getZ(), 3.0F);
        wolf_explosion.setExplosionSound(true);
        wolf_explosion.setDestroyBlocks(true);
        wolf_explosion.setFireAfterExplosion(true);
        wolf_explosion.setDamageOwner(true);
        wolf_explosion.setDamageEntities(true);
        wolf_explosion.doExplosion();
        break;
    } 
  } private void onEntityImpactEvent(LivingEntity entity) {
    int i;
    ExplosionAbility bakuhatsu_explosion;
    Vec3d speed;
    ExplosionAbility wolf_explosion;
    switch (this.type) {



      
      case TAKE_JAVELIN:
        for (i = 0; i < 7; i++) {
          entity.hurtResistantTime = 0;
          BambooPillarEntity pillar = new BambooPillarEntity(entity.world, entity);
          pillar.rotationPitch = 90.0F;
          pillar.setPosition(entity.getPosX() + Math.random(), entity.getPosY(), entity.getPosZ() + Math.random());
          pillar.setMotion(0.0D, 0.4D, 0.0D);
          entity.world.addEntity((Entity)pillar);
          entity.hurtResistantTime = 0;
          entity.attackEntityFrom(pillar.source, 1.0F);
        } 
        break;
      case BAKUHATSU:
        bakuhatsu_explosion = AbilityHelper.newExplosion((Entity)this.owner, this.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 1.0F);
        bakuhatsu_explosion.setExplosionSound(true);
        bakuhatsu_explosion.setDestroyBlocks(true);
        bakuhatsu_explosion.setFireAfterExplosion(false);
        bakuhatsu_explosion.setDamageOwner(true);
        bakuhatsu_explosion.setDamageEntities(true);
        bakuhatsu_explosion.doExplosion();
        break;
      case TRAMPOLIA:
        speed = WyHelper.propulsion(entity, 2.0D, 2.0D);
        entity.setMotion(speed.x, 0.8D, speed.z);
        entity.velocityChanged = true;
        break;
      case IMPACT_WOLF:
        wolf_explosion = AbilityHelper.newExplosion((Entity)this.owner, this.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 3.0F);
        wolf_explosion.setExplosionSound(true);
        wolf_explosion.setDestroyBlocks(true);
        wolf_explosion.setFireAfterExplosion(true);
        wolf_explosion.setDamageOwner(true);
        wolf_explosion.setDamageEntities(true);
        wolf_explosion.doExplosion();
        break;
    } 
  }



  
  public enum PopGreenType
  {
    NONE, DEVIL, RAFFLESIA, TAKE_JAVELIN, BAKUHATSU, HUMANDRAKE, TRAMPOLIA, IMPACT_WOLF;
  }
}


