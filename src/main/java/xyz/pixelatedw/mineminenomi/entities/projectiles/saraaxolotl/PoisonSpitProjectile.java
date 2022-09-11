package xyz.pixelatedw.mineminenomi.entities.projectiles.saraaxolotl;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class PoisonSpitProjectile extends AbilityProjectileEntity {
  public PoisonSpitProjectile(World world) {
    super(SaraAxolotlProjectiles.POISON_SPIT, world);
  }

  
  public PoisonSpitProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public PoisonSpitProjectile(World world, double x, double y, double z) {
    super(SaraAxolotlProjectiles.POISON_SPIT, world, x, y, z);
  }

  
  public PoisonSpitProjectile(World world, LivingEntity player) {
    super(SaraAxolotlProjectiles.POISON_SPIT, world, player);
    
    setDamage(8.0F);
    setPhysical(false);
    setMaxLife(10);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 200, 0));
    this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 7; i++) {
        
        double offsetX = WyHelper.randomDouble() / 6.0D;
        double offsetY = WyHelper.randomDouble() / 6.0D;
        double offsetZ = WyHelper.randomDouble() / 6.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU_PINK);
        data.setLife(5);
        data.setSize(0.7F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


