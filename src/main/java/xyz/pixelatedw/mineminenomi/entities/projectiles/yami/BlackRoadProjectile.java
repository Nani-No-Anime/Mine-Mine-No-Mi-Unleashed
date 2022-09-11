package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;


import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterChargingParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BlackRoadProjectile extends AbilityProjectileEntity {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new DarkMatterChargingParticleEffect();
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE });

  
  public BlackRoadProjectile(World world) {
    super(YamiProjectiles.BLACK_ROAD, world);
  }

  
  public BlackRoadProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public BlackRoadProjectile(World world, double x, double y, double z) {
    super(YamiProjectiles.BLACK_ROAD, world, x, y, z);
  }

  
  public BlackRoadProjectile(World world, LivingEntity player) {
    super(YamiProjectiles.BLACK_ROAD, world, player);
    
    setMaxLife(1);
    setDamage(10.0F);
    setPassThroughEntities();
    setPassThroughBlocks();
    
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
      if (j > 2) {
        break;
      }
      j++;
    } 
    
    if (pos == null) {
      return;
    }
    int size = 2 + 4 * (getMaxLife() - getLife()) / getMaxLife();
    
    AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), size, ModBlocks.DARKNESS, GRIEF_RULE);
    
    PARTICLES.spawn(this.world, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
  }
}


