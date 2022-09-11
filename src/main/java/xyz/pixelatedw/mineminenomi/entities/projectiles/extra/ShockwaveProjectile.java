package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import java.lang.invoke.SerializedLambda;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.particles.effects.common.ShockwaveParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class ShockwaveProjectile extends AbilityProjectileEntity {
  private static final ShockwaveParticleEffect PARTICLES = new ShockwaveParticleEffect();
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE });
  
  private boolean canBreakBlocks = false;
  public int power;
  
  public ShockwaveProjectile(World world) {
    super(ExtraProjectiles.SHOCKWAVE, world);
















    
    this.power = 8; } public ShockwaveProjectile(EntityType type, World world) { super(type, world); this.power = 8; } public ShockwaveProjectile(World world, double x, double y, double z) { super(ExtraProjectiles.SHOCKWAVE, world, x, y, z); this.power = 8; } public ShockwaveProjectile(World world, LivingEntity entity) {
    this(world, entity, false);
  } public ShockwaveProjectile(World world, LivingEntity entity, boolean canBreakBlocks) {
    super(ExtraProjectiles.SHOCKWAVE, world, entity);
    this.power = 8;
    setDamage(this.power);
    setCollisionSize(2.0D);
    setPhysical(false);
    setMaxLife(10);
    setPassThroughEntities();
    this.onTickEvent = this::onTickEvent;
    this.canBreakBlocks = canBreakBlocks;
  }

  
  private void onTickEvent() {
    BlockPos pos = null;
    int j = 1;
    
    while (pos == null) {
      
      BlockState state = this.world.getBlockState(getPosition().down(j));
      
      if (state.isSolid()) {
        
        pos = getPosition().down(j);
        
        break;
      } 
      if (j > 5) {
        break;
      }
      j++;
    } 
    
    if (pos == null) {
      return;
    }
    if (this.canBreakBlocks) {
      AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), this.power / 8, Blocks.AIR, GRIEF_RULE);
    }
    PARTICLES.spawn(this.world, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
  }
}


