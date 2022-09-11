package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.SnowLayerBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HikenProjectile extends AbilityProjectileEntity {
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { LiquidBlockProtectionRule.INSTANCE, SnowLayerBlockProtectionRule.INSTANCE });

  
  public HikenProjectile(World world) {
    super(MeraProjectiles.HIKEN, world);
  }

  
  public HikenProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public HikenProjectile(World world, double x, double y, double z) {
    super(MeraProjectiles.HIKEN, world, x, y, z);
  }

  
  public HikenProjectile(World world, LivingEntity player) {
    super(MeraProjectiles.HIKEN, world, player);
    
    setDamage(50.0F);
    setCanGetStuckInGround();
    setPassThroughEntities();
    setMaxLife(32);
    setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
    explosion.setStaticDamage(25.0F);
    explosion.setHeightDifference(30);
    explosion.disableExplosionKnockback();
    explosion.setFireAfterExplosion(true);
    explosion.setExplosionSound(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
    explosion.setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
    explosion.doExplosion();
  }

  
  private void onTickEvent() {
    if (this.ticksExisted > 2) {
      
      BlockPos pos = getPosition();
      this.onBlockImpactEvent.onImpact(pos);
    } 
    
    if (areEyesInFluid(FluidTags.WATER) && CommonConfig.INSTANCE.getDestroyWater()) {
      
      List<BlockPos> coords = AbilityHelper.createFilledSphere(getEntityWorld(), (int)getPosX(), (int)getPosY(), (int)getPosZ(), 2, Blocks.AIR, GRIEF_RULE);
      for (BlockPos blockPos : coords) {
        
        WyHelper.spawnParticles(ParticleTypes.BUBBLE, (ServerWorld)getEntityWorld(), blockPos.getX() + WyHelper.randomDouble() / 2.0D, blockPos.getY() + 0.8D, blockPos.getZ() + WyHelper.randomDouble() / 2.0D);
        getEntityWorld().addParticle(ParticleTypes.SMOKE, blockPos.getX(), blockPos.getY() + 1.1D, blockPos.getZ(), 0.0D, 0.0D, 0.0D);
      } 
    } 
    
    if (!this.world.isRemote) {
      int i;
      for (i = 0; i < 20; i++) {
        
        double offsetX = WyHelper.randomDouble() * 2.0D;
        double offsetY = WyHelper.randomDouble() * 2.0D;
        double offsetZ = WyHelper.randomDouble() * 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
        data.setMotion((getMotion()).x / 10.0D, (getMotion()).y / 10.0D, (getMotion()).z / 10.0D);
        data.setLife(60);
        data.setSize(3.0F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
      
      for (i = 0; i < 10; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
        data.setLife(7);
        data.setSize(1.2F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    } 
  }
}


