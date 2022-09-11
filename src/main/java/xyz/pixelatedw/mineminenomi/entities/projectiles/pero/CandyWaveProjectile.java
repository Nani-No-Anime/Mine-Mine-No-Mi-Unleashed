package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;

import java.lang.invoke.SerializedLambda;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.pero.CandyWaveParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class CandyWaveProjectile extends AbilityProjectileEntity {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new CandyWaveParticleEffect();
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE });

  
  public CandyWaveProjectile(World world) {
    super(PeroProjectiles.CANDY_WAVE, world);
  }

  
  public CandyWaveProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public CandyWaveProjectile(World world, double x, double y, double z) {
    super(PeroProjectiles.CANDY_WAVE, world, x, y, z);
  }

  
  public CandyWaveProjectile(World world, LivingEntity player) {
    super(PeroProjectiles.CANDY_WAVE, world, player);
    
    setDamage(24.0F);
    setMaxLife(12);
    setPhysical(false);
    setAffectedByImbuing();
    setPassThroughEntities();
    setPassThroughBlocks();
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.CANDY_STUCK, 100, 0, false, false, false) });


    
    this.onTickEvent = this::onTickEvent;
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
      if (j > 3) {
        break;
      }
      j++;
    } 
    
    if (pos == null) {
      return;
    }
    int size = 2 + 4 * (getMaxLife() - getLife()) / getMaxLife();
    
    AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), size, ModBlocks.CANDY, GRIEF_RULE);
    
    PARTICLES.spawn(this.world, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
  }
}


