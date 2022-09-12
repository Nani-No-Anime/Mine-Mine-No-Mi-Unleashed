package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mera.JujikaParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class JujikaProjectile extends AbilityProjectileEntity {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new JujikaParticleEffect();

  
  public JujikaProjectile(World world) {
    super(MeraProjectiles.JUJIKA, world);
  }

  
  public JujikaProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public JujikaProjectile(World world, double x, double y, double z) {
    super(MeraProjectiles.JUJIKA, world, x, y, z);
  }

  
  public JujikaProjectile(World world, LivingEntity player) {
    super(MeraProjectiles.JUJIKA, world, player);
    setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
    setDamage(25.0F);
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (areEyesInFluid(FluidTags.WATER)) {
      
      remove();
      getEntityWorld().addParticle(ParticleTypes.SMOKE, getPosX(), getPosY() + 1.1D, getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
    
    if (!this.world.isRemote && this.ticksExisted > 0) {
      
      Direction dir = Direction.fromAngle((getThrower()).rotationYaw);
      PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, dir.ordinal());
    } 
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    if (CommonConfig.INSTANCE.isAbilityGriefingEnabled())
    {
      for (int j = -2; j <= 2; j++) {
        int i;
        for (i = -5; i <= 5; i++) {
          if (this.world.isAirBlock(new BlockPos(hit.getX() + i, hit.getY() + j, hit.getZ())))
            this.world.setBlockState(new BlockPos(hit.getX() + i, hit.getY() + j, hit.getZ()), Blocks.FIRE.getDefaultState()); 
        } 
        for (i = -5; i <= 5; i++) {
          if (this.world.isAirBlock(new BlockPos(hit.getX(), hit.getY() + j, hit.getZ() + i)))
            this.world.setBlockState(new BlockPos(hit.getX(), hit.getY() + j, hit.getZ() + i), Blocks.FIRE.getDefaultState()); 
        } 
      } 
    }
  }
}


