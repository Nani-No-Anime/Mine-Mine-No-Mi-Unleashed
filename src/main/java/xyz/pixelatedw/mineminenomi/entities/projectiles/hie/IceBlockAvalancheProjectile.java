package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceAgeAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;

public class IceBlockAvalancheProjectile
  extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
  public boolean finalized = false;
  
  public IceBlockAvalancheProjectile(World world) {
    super(HieProjectiles.ICE_BLOCK_AVALANCHE, world);
  }

  
  public IceBlockAvalancheProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public IceBlockAvalancheProjectile(World world, double x, double y, double z) {
    super(HieProjectiles.ICE_BLOCK_AVALANCHE, world, x, y, z);
  }

  
  public IceBlockAvalancheProjectile(World world, LivingEntity player) {
    super(HieProjectiles.ICE_BLOCK_AVALANCHE, world, player);
    setDamage(50.0F);
    setMaxLife(150);
    setPhysical(false);
    setPassThroughEntities();
    setCanGetStuckInGround();
    
    this.onTickEvent = this::onTickEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onTickEvent() {
    float mult = getSize() / 6.0F;
    setBoundingBox(getBoundingBox().grow(mult));
    setCollisionSize(mult);
    
    if (!this.finalized) {
      setSize(getSize() + 0.4F);
    }
    if (this.world.getBlockState(getPosition().down()).isSolid()) {
      setMotion(0.0D, 0.0D, 0.0D);
    }
  }
  
  public void onBlockImpactEvent(BlockPos pos) {
    if (!this.world.isRemote) {
      IceAgeAbility.PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
    }
  }

  
  public void registerData() {
    super.registerData();
    this.dataManager.register(SIZE, Float.valueOf(1.0F));
  }


  
  public void setSize(float size) {
    this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 50.0F)));
  }


  
  public float getSize() {
    return ((Float)this.dataManager.get(SIZE)).floatValue();
  }
}


