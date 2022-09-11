package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class IceBallProjectile extends AbilityProjectileEntity {
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });

  
  public IceBallProjectile(World world) {
    super(HieProjectiles.ICE_BALL, world);
  }

  
  public IceBallProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public IceBallProjectile(World world, double x, double y, double z) {
    super(HieProjectiles.ICE_BALL, world, x, y, z);
  }

  
  public IceBallProjectile(World world, LivingEntity player) {
    super(HieProjectiles.ICE_BALL, world, player);
    
    setDamage(25.0F);
    setMaxLife(32);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
    
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.FROZEN, 200, 0), new EffectInstance(Effects.SLOWNESS, 100, 0), new EffectInstance(Effects.MINING_FATIGUE, 100, 0) });
  }





  
  private void onBlockImpactEvent(BlockPos hit) {
    AbilityHelper.createEmptySphere(this.world, hit.getX(), hit.getY(), hit.getZ(), 6, Blocks.BLUE_ICE, GRIEF_RULE);
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 10; i++) {
        
        double offsetX = WyHelper.randomDouble() / 1.5D;
        double offsetY = WyHelper.randomDouble() / 1.5D;
        double offsetZ = WyHelper.randomDouble() / 1.5D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
        data.setLife(3);
        data.setSize(1.5F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


