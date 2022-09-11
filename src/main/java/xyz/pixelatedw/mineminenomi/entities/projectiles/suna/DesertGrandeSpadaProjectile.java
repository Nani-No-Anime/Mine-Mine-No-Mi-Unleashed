package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertSpadaParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DesertGrandeSpadaProjectile
  extends AbilityProjectileEntity {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new DesertSpadaParticleEffect();
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE });

  
  public DesertGrandeSpadaProjectile(World world) {
    super(SunaProjectiles.DESERT_GRANDE_SPADA, world);
  }

  
  public DesertGrandeSpadaProjectile(World world, double x, double y, double z) {
    super(SunaProjectiles.DESERT_GRANDE_SPADA, world, x, y, z);
  }

  
  public DesertGrandeSpadaProjectile(World world, LivingEntity player) {
    super(SunaProjectiles.DESERT_GRANDE_SPADA, world, player);
    
    setDamage(85.0F);
    setPhysical(false);
    setAffectedByImbuing();
    setMaxLife(5);
    setPassThroughBlocks();
    setPassThroughEntities();
  }
}


