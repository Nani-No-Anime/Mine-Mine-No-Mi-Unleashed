package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;


import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;

import java.util.List;

public class UrsusShockProjectile
  extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
  public float multiplier = 0.0F;
  
  public UrsusShockProjectile(World world)
  {
    super(NikyuProjectiles.URSUS_SHOCK, world);






































    
    this.dealtAOE = false; } public UrsusShockProjectile(EntityType type, World world) { super(type, world); this.dealtAOE = false; } public UrsusShockProjectile(World world, double x, double y, double z) { super(NikyuProjectiles.URSUS_SHOCK, world, x, y, z); this.dealtAOE = false; } public UrsusShockProjectile(World world, LivingEntity player) { super(NikyuProjectiles.URSUS_SHOCK, world, player); this.dealtAOE = false; setDamage(100.0F); setMaxLife(400); setArmorPiercing(); setCanGetStuckInGround(); setPhysical(false);
    setAffectedByImbuing();
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent; } private void onBlockImpactEvent(BlockPos hit) { if (this.dealtAOE) {
      return;
    }
    AbilityHelper.createSphere(this.world, getPosition(), 24, 5, false, Blocks.AIR, 2, GRIEF_RULE);
    List<Entity> list = WyHelper.getEntitiesNear(getPosition(), this.world, 40.0D, new Class[] { Entity.class });
    list.remove(getThrower());
    
    for (Entity target : list) {
      
      if (target == this)
        continue; 
      if (target instanceof LivingEntity) {
        
        ((LivingEntity)target).hurtTime = target.hurtResistantTime = 0;
        target.attackEntityFrom(ModDamageSource.causePlayerDamage((PlayerEntity)getThrower()), 100.0F);
        Vec3d speed = target.getLook(1.0F).mul(-1.0D, -1.0D, -1.0D).mul(5.0D, 0.0D, 5.0D);
        target.setMotion(speed.x, 1.0D, speed.z);
        target.velocityChanged = true;
      } 
    } 
    this.dealtAOE = true; }
  private void onTickEvent() { if (this.dealtAOE) {
      setSize(Math.min(getSize() + 0.5F, 20.0F));
      setMotion(0.0D, 0.0D, 0.0D);
      setRotation(90.0F, 0.0F);
    }  } private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE }); private boolean dealtAOE; public void registerData() { super.registerData();
    this.dataManager.register(SIZE, Float.valueOf(0.0F)); }







  
  public void setSize(float size) {
    this.dataManager.set(SIZE, Float.valueOf(size));
  }

  
  public float getSize() {
    return ((Float)this.dataManager.get(SIZE)).floatValue();
  }
}


