package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;


import java.util.List;
import java.util.Optional;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HidarumaProjectile
  extends AbilityProjectileEntity {
  public HidarumaProjectile(World world) {
    super(MeraProjectiles.HIDARUMA, world);
  }
  private Optional<LivingEntity> target;
  
  public HidarumaProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public HidarumaProjectile(World world, double x, double y, double z) {
    super(MeraProjectiles.HIDARUMA, world, x, y, z);
  }

  
  public HidarumaProjectile(World world, LivingEntity player) {
    super(MeraProjectiles.HIDARUMA, world, player);
    
    setDamage(3.5F);
    setMaxLife(120);
    setGravity(0.0F);
    setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 3);
    if (!MinecraftForge.EVENT_BUS.post(event))
      hitEntity.setFire(3); 
    remove();
  }

  
  public void setTarget(Optional<LivingEntity> target) {
    this.target = target;
  }

  
  private void onTickEvent() {
    if (this.target == null || !this.target.isPresent() || !((LivingEntity)this.target.get()).isAlive()) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(getPosition(), this.world, 16.0D, (getThrower() instanceof net.minecraft.entity.player.PlayerEntity) ? FactionHelper.getOutsideGroupPredicate(getThrower()) : EntityPredicates.NOT_SPECTATING, new Class[] { LivingEntity.class });
      list.remove(getThrower());
      list.sort(MobsHelper.ENTITY_THREAT);
      if (list.size() > 0)
      {
        this.target = list.stream().findAny();
      }
    }
    else {
      
      Vec3d dist = getPositionVec().subtract(((LivingEntity)this.target.get()).getPositionVec()).add(0.0D, -1.0D, 0.0D);
      double speedReduction = 12.0D;
      double speed = 0.5D;
      double xSpeed = Math.min(speed, -dist.x / speedReduction);
      double ySpeed = Math.min(speed, -dist.y / speedReduction);
      double zSpeed = Math.min(speed, -dist.z / speedReduction);
      setMotion(xSpeed, ySpeed, zSpeed);
      this.velocityChanged = true;
    } 
    
    if (areEyesInFluid(FluidTags.WATER)) {
      
      remove();
      getEntityWorld().addParticle(ParticleTypes.SMOKE, getPosX(), getPosY() + 1.1D, getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
    
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 1; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        WyHelper.spawnParticles(ParticleTypes.HAPPY_VILLAGER, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


