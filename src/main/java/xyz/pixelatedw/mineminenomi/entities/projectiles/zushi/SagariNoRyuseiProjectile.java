package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;

public class SagariNoRyuseiProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
  public SagariNoRyuseiProjectile(World world) {
    super(ZushiProjectiles.SAGARI_NO_RYUSEI, world);
  }

  
  public SagariNoRyuseiProjectile(EntityType type, World world) {
    super(type, world);
  }
  private static final int MAX_DAMAGE = 95;
  
  public SagariNoRyuseiProjectile(World world, double x, double y, double z) {
    super(ZushiProjectiles.SAGARI_NO_RYUSEI, world, x, y, z);
  }

  
  public SagariNoRyuseiProjectile(World world, LivingEntity player) {
    super(ZushiProjectiles.SAGARI_NO_RYUSEI, world, player);
    
    setDamage(95.0F);
    setArmorPiercing();
    setMaxLife(256);
    setPhysical(false);
    setHurtThrower();
    
    setDamageSource((new IndirectEntityDamageSource("ability_projectile", (Entity)this, null)).setProjectile());
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    float mult = getSize() / 30.0F;
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), 20.0F * mult);
    explosion.setStaticDamage(90.0F * mult);
    explosion.addRemovedBlocksToList();
    explosion.setDamageOwner(true);
    explosion.setDamageSource((new IndirectEntityDamageSource("explosion", (Entity)this, null)).setExplosion());
    explosion.setFireAfterExplosion(true);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(20.0F * mult)));
    explosion.doExplosion();
    
    int size = 0;
    for (FallingBlockEntity entity : explosion.removedBlocks) {
      
      entity.setMotion(WyHelper.randomWithRange(-1, 1) / 2.0D * mult, (0.75D + 
          WyHelper.randomDouble()) * mult, 
          WyHelper.randomWithRange(-1, 1) / 2.0D * mult);
      entity.velocityChanged = true;
      entity.shouldDropItem = false;
      entity.fallTime = 1;
      this.world.addEntity((Entity)entity);
      size++;
      
      if (size > 256) {
        break;
      }
    } 
  }
  
  private void onTickEvent() {
    float mult = getSize() / 30.0F;
    
    if (1 > this.ticksExisted) {
      setBoundingBox(getBoundingBox().grow(mult));
    }
    if (!this.world.isRemote) {
      
      setDamage(95.0F * mult);
      
      for (int i = 0; i < 25; i++) {
        
        ParticleType<GenericParticleData> particleToUse = (this.ticksExisted % 2 == 0) ? ModParticleTypes.MOKU : ModParticleTypes.MERA;
        
        double offsetX = WyHelper.randomDouble() * 5.0D * mult;
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble() * 5.0D * mult;
        
        GenericParticleData data = new GenericParticleData(particleToUse);
        data.setLife(20);
        data.setSize(7.0F * mult);
        
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    } 
  }

  
  public void registerData() {
    super.registerData();
    this.dataManager.register(SIZE, Float.valueOf(0.0F));
  }

  
  public void setSize(float size) {
    this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 30.0F)));
  }


  
  public float getSize() {
    return ((Float)this.dataManager.get(SIZE)).floatValue();
  }
}


