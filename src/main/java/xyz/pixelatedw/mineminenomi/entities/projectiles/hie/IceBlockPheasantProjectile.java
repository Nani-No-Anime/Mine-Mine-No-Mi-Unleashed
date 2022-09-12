package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class IceBlockPheasantProjectile extends AbilityProjectileEntity {
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE });

  
  public IceBlockPheasantProjectile(World world) {
    super(HieProjectiles.ICE_BLOCK_PHEASANT, world);
  }

  
  public IceBlockPheasantProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public IceBlockPheasantProjectile(World world, double x, double y, double z) {
    super(HieProjectiles.ICE_BLOCK_PHEASANT, world, x, y, z);
  }

  
  public IceBlockPheasantProjectile(World world, LivingEntity player) {
    super(HieProjectiles.ICE_BLOCK_PHEASANT, world, player);
    
    setDamage(45.0F);
    setArmorPiercing();
    setPassThroughEntities();
    setPhysical(false);
    setAffectedByImbuing();
    setMaxLife(60);
    this.onTickEvent = this::onTickEvent;
    
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.FROZEN, 300, 0) });
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
    AbilityHelper.createFilledSphere(this.world, pos.getX(), pos.getY(), pos.getZ(), size, Blocks.BLUE_ICE, GRIEF_RULE);
    
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 5; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
        data.setLife(8);
        data.setSize(2.0F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


