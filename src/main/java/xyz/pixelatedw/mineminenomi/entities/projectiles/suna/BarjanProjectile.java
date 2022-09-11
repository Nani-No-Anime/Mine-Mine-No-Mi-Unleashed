package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;

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

public class BarjanProjectile extends AbilityProjectileEntity {
  public BarjanProjectile(World world) {
    super(SunaProjectiles.BARJAN, world);
  }

  
  public BarjanProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public BarjanProjectile(World world, double x, double y, double z) {
    super(SunaProjectiles.BARJAN, world, x, y, z);
  }

  
  public BarjanProjectile(World world, LivingEntity player) {
    super(SunaProjectiles.BARJAN, world, player);
    
    setDamage(30.0F);
    setMaxLife(15);
    setPhysical(false);
    setAffectedByImbuing();
    setCanGetStuckInGround();
    setPassThroughEntities();
    
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.WITHER, 100, 3, false, false), new EffectInstance(Effects.HUNGER, 150, 4, false, false), new EffectInstance(Effects.WEAKNESS, 150, 0, false, false), new EffectInstance(Effects.SLOWNESS, 150, 0, false, false), new EffectInstance(Effects.MINING_FATIGUE, 150, 0, false, false) });





    
    this.onEntityImpactEvent = this::onEntityImpact;
    this.onTickEvent = this::onTickEvent;
  }



  
  private void onEntityImpact(LivingEntity entity) {}



  
  private void onTickEvent() {
    for (int i = 0; i < 5; i++) {
      
      double offsetX = WyHelper.randomDouble();
      double offsetY = WyHelper.randomDouble() / 4.0D;
      double offsetZ = WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
      data.setLife(4);
      data.setSize(1.4F);
      WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
    } 
  }
}


