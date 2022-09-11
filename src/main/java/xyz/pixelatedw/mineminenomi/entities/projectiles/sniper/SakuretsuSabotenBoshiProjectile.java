package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
import java.lang.invoke.SerializedLambda;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class SakuretsuSabotenBoshiProjectile extends AbilityProjectileEntity {
  public SakuretsuSabotenBoshiProjectile(World world) {
    super(SniperProjectiles.SAKURETSU_SABOTEN_BOSHI, world);
  }

  
  public SakuretsuSabotenBoshiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public SakuretsuSabotenBoshiProjectile(World world, double x, double y, double z) {
    super(SniperProjectiles.SAKURETSU_SABOTEN_BOSHI, world, x, y, z);
  }

  
  public SakuretsuSabotenBoshiProjectile(World world, LivingEntity player) {
    super(SniperProjectiles.SAKURETSU_SABOTEN_BOSHI, world, player);
    
    setDamage(15.0F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 5.0F);
    explosion.setStaticDamage(20.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(false);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
    explosion.setDamageEntities(false);
    explosion.doExplosion();
    
    int flag = 50;
    
    for (int i = 0; i < 8; i++) {
      
      int a1 = (int)WyHelper.randomWithRange(-5, 5);
      int a2 = (int)WyHelper.randomWithRange(-5, 5);
      
      AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() - 3), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
      AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() - 2), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
      AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() - 1), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
      AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), (int)getPosY(), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
      AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() + 1), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
      AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() + 2), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
    } 
  }
}


