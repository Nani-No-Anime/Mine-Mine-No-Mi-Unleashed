package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;

public class VoltVariProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
  public VoltVariProjectile(World world) {
    super(GoroProjectiles.VOLT_VARI, world);
  }
  private ExplosionAbility explosion;
  
  public VoltVariProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public VoltVariProjectile(World world, double x, double y, double z) {
    super(GoroProjectiles.VOLT_VARI, world, x, y, z);
  }

  
  public VoltVariProjectile(World world, LivingEntity player, float power) {
    super(GoroProjectiles.VOLT_VARI, world, player);
    
    setMaxLife(40);
    setPassThroughEntities();
    
    this.onTickEvent = this::onTickEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    
    this.explosion = AbilityHelper.newExplosion(getThrower(), this.world, 0.0D, 0.0D, 0.0D, 0.0F);
    this.explosion.setStaticDamage(5.0F + power / 10.0F);
    this.explosion.setExplosionSound(true);
    this.explosion.setDamageOwner(false);
    this.explosion.setDestroyBlocks(true);
    this.explosion.setStaticBlockResistance(0.1F);
    this.explosion.setDamageEntities(true);
    this.explosion.setDamageSource((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeIndirectDamageFromSource((ThrowableEntity)this));
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    float voltage = ((Float)this.dataManager.get(SIZE)).floatValue();
    if (voltage > 2.0F) {
      
      this.explosion.setExplosionPos(hit.getX(), hit.getY(), hit.getZ());
      this.explosion.doExplosion();
    } 
  }

  
  private void onTickEvent() {
    float voltage = ((Float)this.dataManager.get(SIZE)).floatValue();
    
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 5; i++) {
        
        ParticleType<GenericParticleData> particleToUse = (this.ticksExisted % 2 == 0) ? ModParticleTypes.GORO2 : ModParticleTypes.GORO;
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(particleToUse);
        data.setLife(8);
        data.setSize(4.0F * voltage / 100.0F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }


  
  public void registerData() {
    super.registerData();
    this.dataManager.register(SIZE, Float.valueOf(0.0F));
  }


  
  public void setSize(float size) {
    setDamage(10.0F + size * 2.0F);
    setBoundingBox(getBoundingBox().grow((size / 100.0F)));
    this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 100.0F)));
    float power = ((Float)this.dataManager.get(SIZE)).floatValue() / 20.0F;
    this.explosion.setExplosionSize(power * 2.0F);
    this.explosion.setExplodedBlocksLimit((int)(power * 150.0F));
    this.explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(power * 0.9F)));
  }


  
  public float getSize() {
    return 0.7F + ((Float)this.dataManager.get(SIZE)).floatValue() / 10.0F;
  }
}


