package xyz.pixelatedw.mineminenomi.entities.projectiles.noro;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class NoroNoroBeamProjectile
  extends AbilityProjectileEntity {
  public NoroNoroBeamProjectile(World world) {
    super(NoroProjectiles.NORO_NORO_BEAM, world);
  }

  
  public NoroNoroBeamProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public NoroNoroBeamProjectile(World world, double x, double y, double z) {
    super(NoroProjectiles.NORO_NORO_BEAM, world, x, y, z);
  }

  
  public NoroNoroBeamProjectile(World world, LivingEntity player) {
    super(NoroProjectiles.NORO_NORO_BEAM, world, player);
    
    setMaxLife(10);
    setCollisionSize(1.25D);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    if (hitEntity.isPotionActive(ModEffects.NORO_SLOWNESS)) {
      
      int newTimer = hitEntity.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getDuration() + 240;
      int noroSlowness = Math.min(9, hitEntity.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getAmplifier() + 1);
      
      hitEntity.removePotionEffect(ModEffects.NORO_SLOWNESS);
      hitEntity.addPotionEffect(new EffectInstance(ModEffects.NORO_SLOWNESS, newTimer, noroSlowness));
    }
    else {
      
      hitEntity.addPotionEffect(new EffectInstance(ModEffects.NORO_SLOWNESS, 240, 0));
    } 
  }
}


