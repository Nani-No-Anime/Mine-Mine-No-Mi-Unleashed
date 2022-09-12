package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;


import java.util.List;
import java.util.Optional;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class WhiteSnakeProjectile
  extends AbilityProjectileEntity
{
  private Optional<LivingEntity> target;
  
  public WhiteSnakeProjectile(World world) {
    super(MokuProjectiles.WHITE_SNAKE, world);
  }

  
  public WhiteSnakeProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public WhiteSnakeProjectile(World world, double x, double y, double z) {
    super(MokuProjectiles.WHITE_SNAKE, world, x, y, z);
  }

  
  public WhiteSnakeProjectile(World world, LivingEntity player) {
    super(MokuProjectiles.WHITE_SNAKE, world, player);
    
    setDamage(25.0F);
    setMaxLife(40);
    setPhysical(true);
    
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.BLINDNESS, 300, 0), new EffectInstance(Effects.POISON, 300, 0) });



    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (this.target == null || !this.target.isPresent() || !((LivingEntity)this.target.get()).isAlive() || ((LivingEntity)this.target.get()).getPositionVec().squareDistanceTo(getPositionVec()) > 15.0D) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(getPosition(), this.world, 5.0D, (getThrower() instanceof net.minecraft.entity.player.PlayerEntity) ? FactionHelper.getOutsideGroupPredicate(getThrower()) : EntityPredicates.NOT_SPECTATING, new Class[] { LivingEntity.class });
      list.remove(getThrower());
      list.sort(MobsHelper.ENTITY_THREAT);
      if (list.size() > 0) {
        this.target = list.stream().findAny();
      }
    } else {
      
      Vec3d dist = getPositionVec().subtract(((LivingEntity)this.target.get()).getPositionVec()).add(0.0D, -1.0D, 0.0D);
      double speedReduction = 12.0D;
      double speed = 0.6D;
      double xSpeed = Math.min(speed, -dist.x / speedReduction);
      double ySpeed = Math.min(speed, -dist.y / speedReduction);
      double zSpeed = Math.min(speed, -dist.z / speedReduction);
      setMotion(xSpeed, ySpeed, zSpeed);
      this.velocityChanged = true;
    } 
    
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 40; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
        data.setLife(10);
        data.setSize((float)(WyHelper.randomDouble() * 4.0D));
        data.setColor(0.3F, 0.3F, 0.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


