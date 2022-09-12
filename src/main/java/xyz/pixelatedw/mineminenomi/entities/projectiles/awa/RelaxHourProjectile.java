package xyz.pixelatedw.mineminenomi.entities.projectiles.awa;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RelaxHourProjectile extends AbilityProjectileEntity {
  public RelaxHourProjectile(World world) {
    super(AwaProjectiles.RELAX_HOUR, world);
  }

  
  public RelaxHourProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public RelaxHourProjectile(World world, double x, double y, double z) {
    super(AwaProjectiles.RELAX_HOUR, world, x, y, z);
  }

  
  public RelaxHourProjectile(World world, LivingEntity player) {
    super(AwaProjectiles.RELAX_HOUR, world, player);
    setDamage(3.0F);
    setMaxLife(40);
    setPhysical(false);
    setAffectedByImbuing();
    setChangeHurtTime(true);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    hitEntity.addPotionEffect(new EffectInstance(ModEffects.WASHED, 200, 0));
    this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote) {
      int i;
      for (i = 0; i < 15; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        ParticleType<GenericParticleData> particle = ModParticleTypes.AWA;
        if (i % 3 == 0) {
          particle = ModParticleTypes.AWA3;
        }
        GenericParticleData data = new GenericParticleData(particle);
        data.setLife(7);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
      
      for (i = 0; i < 5; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.AWA_FOAM);
        data.setLife(7);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    } 
  }
}


