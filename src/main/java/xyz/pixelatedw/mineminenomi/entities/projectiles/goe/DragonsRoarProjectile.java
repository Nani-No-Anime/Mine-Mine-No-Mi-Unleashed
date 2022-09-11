package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;

public class DragonsRoarProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
  public DragonsRoarProjectile(World world) {
    super(GoeProjectiles.DRAGONS_ROAR, world);
  }

  
  public DragonsRoarProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public DragonsRoarProjectile(World world, double x, double y, double z) {
    super(GoeProjectiles.DRAGONS_ROAR, world, x, y, z);
  }

  
  public DragonsRoarProjectile(World world, LivingEntity player, float size) {
    super(GoeProjectiles.DRAGONS_ROAR, world, player);
    
    setDamage(10.0F);
    setMaxLife(30);
    setPassThroughEntities();
    setDamageSource(this.bypassingSource);
    
    setSize(size);
    setCollisionSize((size / 4.0F));
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos pos) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), getSize() / 2.0F);
    explosion.setHeightDifference(45);
    explosion.setStaticBlockResistance(1.35F);
    explosion.setProtectOwnerFromFalling(true);
    explosion.setExplosionSound(true);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)getSize()));
    explosion.setDamageSource(this.bypassingSource);
    explosion.setStaticDamage(0.0F);
    explosion.doExplosion();
  }


  
  public void registerData() {
    super.registerData();
    this.dataManager.register(SIZE, Float.valueOf(1.0F));
  }


  
  public void setSize(float size) {
    this.dataManager.set(SIZE, Float.valueOf(MathHelper.clamp(size, 1.0F, 50.0F)));
  }


  
  public float getSize() {
    return ((Float)this.dataManager.get(SIZE)).floatValue();
  }
}


