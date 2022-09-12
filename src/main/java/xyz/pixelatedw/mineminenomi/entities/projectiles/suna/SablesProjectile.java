package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileShootEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class SablesProjectile extends AbilityProjectileEntity {
  public Vec3d firstVector = null;
  public Vec3d vector = null;
  boolean shoot = false;
  public float mult = 1.0F;
  private static final SablesParticleEffect PARTICLES = new SablesParticleEffect();

  
  public SablesProjectile(World world) {
    super(SunaProjectiles.SABLES, world);
  }

  
  public SablesProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public SablesProjectile(World world, double x, double y, double z) {
    super(SunaProjectiles.SABLES, world, x, y, z);
  }

  
  public SablesProjectile(World world, LivingEntity player) {
    super(SunaProjectiles.SABLES, world, player);
    
    setDamage(0.0F);
    setMaxLife(300);
    setPhysical(false);
    setPassThroughEntities();
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote) {
      
      if (this.firstVector == null)
      {
        this.firstVector = getPositionVec();
      }
      
      PARTICLES.mult = this.mult;
      PARTICLES.spawn(this.world, getPosX(), getPosY() - 10.0D, getPosZ(), (getMotion()).x, (getMotion()).y, (getMotion()).z);
      
      float growthXZ = 4.0F + 6.0F * this.mult;
      float growthY = 10.0F + 12.0F * this.mult;
      
      ArrayList<FallingBlockEntity> list = new ArrayList<>();
      AxisAlignedBB box = (new AxisAlignedBB(new BlockPos(getPositionVec()))).grow(growthXZ, growthY, growthXZ);
      for (Entity entity : (getThrower()).world.getEntitiesWithinAABB(Entity.class, box, e -> (e != getThrower()))) {
        
        entity.setMotion((entity.getMotion()).x + (getPosX() - entity.getPosX()) / 25.0D, 
            (entity.getMotion()).y + (getPosY() - entity.getPosY()) / 25.0D, 
            (entity.getMotion()).z + (getPosZ() - entity.getPosZ()) / 25.0D);
        entity.velocityChanged = true;
        
        if (entity instanceof FallingBlockEntity) {
          
          if (!list.contains(entity))
          {
            list.add((FallingBlockEntity)entity);
          }
          if (list.size() > 900)
          {
            IntStream.range(0, 100).forEach(i -> {
                  ((FallingBlockEntity)list.get(i)).setMotion((entity.getMotion()).x + getPosX() - entity.getPosX(), (entity.getMotion()).y + getPosY() - entity.getPosY(), (entity.getMotion()).z + getPosZ() - entity.getPosZ());

                  
                  list.remove(i);
                });
          }
        } 

        
        if (getDistance(entity) < 2.0F) {
          entity.attackEntityFrom(DamageSource.FLY_INTO_WALL, 10.0F * this.mult);
        }
      } 
      box = (new AxisAlignedBB(new BlockPos(getPositionVec()))).grow((growthXZ / 2.0F), 15.0D, (growthXZ / 2.0F));

      
      if (CommonConfig.INSTANCE.isAbilityGriefingEnabled()) {
        double x;
        for (x = box.minX; x < box.maxX; x++) {
          double y; for (y = box.minY; y < box.maxY; y++) {
            double z; for (z = box.minZ; z < box.maxZ; z++) {
              
              BlockPos blockPos = new BlockPos(x, y, z);
              BlockState state = this.world.getBlockState(blockPos);
              if (!state.isAir((IBlockReader)this.world, blockPos) && this.rand.nextFloat() > 0.995D && DefaultProtectionRules.CORE_FOLIAGE_ORE.isPresent(state)) {
                
                FallingBlockEntity fallingBlock = new FallingBlockEntity(this.world, x, y, z, state);
                fallingBlock.setMotion(0.0D, WyHelper.randomDouble() / 3.0D, 0.0D);
                fallingBlock.velocityChanged = true;
                fallingBlock.shouldDropItem = false;
                fallingBlock.fallTime = 1;
                this.world.addEntity((Entity)fallingBlock);
                this.world.removeBlock(blockPos, false);
              } 
            } 
          } 
        } 
      }  if (this.vector != null && !this.shoot) {
        
        Vec3d dist = getPositionVec().subtract(this.vector).add(0.0D, -1.0D, 0.0D);
        double speedReduction = 20.0D;
        double speed = 0.4D;
        double xSpeed = Math.min(speed, -dist.x / speedReduction);
        double ySpeed = Math.min(speed, -dist.y / speedReduction);
        double zSpeed = Math.min(speed, -dist.z / speedReduction);
        setMotion(xSpeed, ySpeed, zSpeed);
      } 

      
      if (isWet() && this.ticksExisted % 20 == 0) {
        
        this.mult = (float)(this.mult - 0.1D);
        if (0.0F > this.mult) {
          remove();
        }
      } 
    } 
  }

  
  public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
    this.shoot = true;
    
    ProjectileShootEvent event = new ProjectileShootEvent(this, velocity, inaccuracy);
    if (MinecraftForge.EVENT_BUS.post(event)) {
      return;
    }
    Vec3d dist = entityThrower.getPositionVec().add(0.0D, entityThrower.getEyeHeight(), 0.0D).subtract(this.vector).add(0.0D, -1.0D, 0.0D);
    double speedReduction = 4.0D;
    double xSpeed = -dist.x / speedReduction;
    double zSpeed = -dist.z / speedReduction;
    setMotion(MathHelper.clamp(xSpeed, -1.0D, 1.0D), 0.0D, MathHelper.clamp(zSpeed, -1.0D, 1.0D));
  }
}


