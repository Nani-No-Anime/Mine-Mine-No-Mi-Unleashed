package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class WhiteBlowProjectile
  extends AbilityProjectileEntity {
  public WhiteBlowProjectile(World world) {
    super(MokuProjectiles.WHITE_BLOW, world);
  }

  
  public WhiteBlowProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public WhiteBlowProjectile(World world, double x, double y, double z) {
    super(MokuProjectiles.WHITE_BLOW, world, x, y, z);
  }

  
  public WhiteBlowProjectile(World world, LivingEntity player) {
    super(MokuProjectiles.WHITE_BLOW, world, player);
    
    setDamage(2.0F);
    setMaxLife(24);
    setChangeHurtTime(true);
    setPhysical(true);
    
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.SMOKE, 200, 0) });
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 10; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
        data.setLife(10);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


