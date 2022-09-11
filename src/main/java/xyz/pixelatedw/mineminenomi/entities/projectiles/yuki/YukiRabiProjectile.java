package xyz.pixelatedw.mineminenomi.entities.projectiles.yuki;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class YukiRabiProjectile extends AbilityProjectileEntity {
  public YukiRabiProjectile(World world) {
    super(YukiProjectiles.YUKI_RABI, world);
  }

  
  public YukiRabiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public YukiRabiProjectile(World world, double x, double y, double z) {
    super(YukiProjectiles.YUKI_RABI, world, x, y, z);
  }

  
  public YukiRabiProjectile(World world, LivingEntity player) {
    super(YukiProjectiles.YUKI_RABI, world, player);
    
    setDamage(3.5F);
    setChangeHurtTime(true);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onTickEvent = this::onTickEvent;
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity entity) {
    AbilityHelper.addFrostbite(entity, getThrower(), 15);
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 2; i++) {
        ParticleType<GenericParticleData> particle;
        double offsetX = WyHelper.randomDouble() / 5.0D;
        double offsetY = WyHelper.randomDouble() / 5.0D;
        double offsetZ = WyHelper.randomDouble() / 5.0D;

        
        if (i % 2 == 0) {
          particle = ModParticleTypes.YUKI2;
        } else {
          particle = ModParticleTypes.YUKI;
        } 
        GenericParticleData data = new GenericParticleData(particle);
        data.setLife(20);
        data.setSize(1.3F);
        data.setMotion(0.0D, -0.02D, 0.0D);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + 0.25D + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


