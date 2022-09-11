package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RaigoProjectile extends AbilityProjectileEntity {
  private boolean explodedOnBlock = false;
  private boolean dealtAOE = false;
  private boolean closeToFloor = false;
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE, (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });

  
  private static final int EXPLOSION_RADIUS = 20;


  
  public RaigoProjectile(World world) {
    super(GoroProjectiles.RAIGO, world);
  }

  
  public RaigoProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public RaigoProjectile(World world, double x, double y, double z) {
    super(GoroProjectiles.RAIGO, world, x, y, z);
  }

  
  public RaigoProjectile(World world, LivingEntity player) {
    super(GoroProjectiles.RAIGO, world, player);
    
    setDamage(100.0F);
    setMaxLife(256);
    setCollisionSize(8.0D);
    setPassThroughEntities();
    setCanGetStuckInGround();
    setTargetResetTime(120);
    
    setDamageSource((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeIndirectDamageFromSource((ThrowableEntity)this));
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    AbilityHelper.createSphere(this.world, getPosition().down(2), 20, 20, false, Blocks.AIR, 3, GRIEF_RULE);
    
    if (!this.dealtAOE) {
      
      List<Entity> list = WyHelper.getEntitiesNear(getPosition(), this.world, 40.0D, new Class[] { Entity.class });
      list.remove(getThrower());
      
      for (Entity target : list) {
        
        if (target instanceof ThrowableEntity || target instanceof net.minecraft.entity.projectile.AbstractArrowEntity) {
          target.remove();
        }
        if (target instanceof LivingEntity) {
          
          ((LivingEntity)target).hurtTime = target.hurtResistantTime = 0;
          target.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)getThrower()), 100.0F);
          Vec3d speed = target.getLook(1.0F).mul(-1.0D, -1.0D, -1.0D).mul(5.0D, 0.0D, 5.0D);
          target.setMotion(speed.x, 1.0D, speed.z);
          target.velocityChanged = true;
        } 
      } 
      this.dealtAOE = true;
    } 
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote) {
      
      for (int i = 0; i < 25; i++) {
        
        ParticleType<GenericParticleData> particleToUse = (this.ticksExisted % 2 == 0) ? ModParticleTypes.GORO2 : ModParticleTypes.GORO;
        
        double offsetX = WyHelper.randomDouble() * 5.0D;
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble() * 5.0D;
        
        GenericParticleData data = new GenericParticleData(particleToUse);
        data.setLife(20);
        data.setSize(7.0F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
      
      setRotation(0.0F, 90.0F);
      if (!this.closeToFloor) {
        
        BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)this, 30.0D);
        if (blockRayTraceResult.getType().equals(RayTraceResult.Type.BLOCK)) {
          
          setMaxLife(16);
          this.closeToFloor = true;
        } 
      } 
      
      if (this.ticksExisted % 5 == 0)
      {
        for (int j = 0; j < 10; j++) {
          
          float boltLength = (float)WyHelper.randomWithRange(36, 50);

          
          LightningEntity bolt = new LightningEntity((Entity)this, getPosX(), getPosY(), getPosZ(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(-90, 90), boltLength, boltLength);
          
          bolt.setAngle(20);
          bolt.setAliveTicks(20);
          bolt.setDamage(0.0F);
          bolt.setExplosion(0, false);
          bolt.setSize(boltLength / 800.0F);
          bolt.setBranches(3);
          bolt.setSegments(10);
          this.world.addEntity(bolt);
        } 
      }
    } 
  }
}


