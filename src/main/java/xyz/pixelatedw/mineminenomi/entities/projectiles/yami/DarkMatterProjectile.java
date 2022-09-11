package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;

public class DarkMatterProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });
  private static final ParticleEffect PARTICLES = (ParticleEffect)new DarkMatterParticleEffect();

  
  public DarkMatterProjectile(World world) {
    super(YamiProjectiles.DARK_MATTER, world);
  }

  
  public DarkMatterProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public DarkMatterProjectile(World world, double x, double y, double z) {
    super(YamiProjectiles.DARK_MATTER, world, x, y, z);
  }

  
  public DarkMatterProjectile(World world, LivingEntity player) {
    super(YamiProjectiles.DARK_MATTER, world, player);
    
    setDamage(15.0F);
    setMaxLife(20);
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    AbilityHelper.createFilledSphere(this.world, hit.getX(), hit.getY(), hit.getZ(), 3, ModBlocks.DARKNESS, GRIEF_RULE);
    PARTICLES.spawn(this.world, hit.getX(), hit.getY(), hit.getZ(), 0.0D, 0.0D, 0.0D);
  }


  
  public void registerData() {
    super.registerData();
    this.dataManager.register(SIZE, Float.valueOf(0.0F));
  }

  
  public void increaseSize() {
    this.dataManager.set(SIZE, Float.valueOf(Math.min(((Float)this.dataManager.get(SIZE)).floatValue() + 0.1F, 17.5F)));
  }


  
  public void setSize(float size) {
    this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 17.5F)));
  }


  
  public float getSize() {
    return ((Float)this.dataManager.get(SIZE)).floatValue();
  }
}


