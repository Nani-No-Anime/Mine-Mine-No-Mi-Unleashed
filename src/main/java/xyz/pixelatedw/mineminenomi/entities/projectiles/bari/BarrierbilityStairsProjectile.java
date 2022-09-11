package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierbilityStairsAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class BarrierbilityStairsProjectile extends AbilityProjectileEntity {
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE });

  
  public BarrierbilityStairsProjectile(World world) {
    super(BariProjectiles.BARRIERBILITY_STAIRS, world);
  }

  
  public BarrierbilityStairsProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public BarrierbilityStairsProjectile(World world, double x, double y, double z) {
    super(BariProjectiles.BARRIERBILITY_STAIRS, world, x, y, z);
  }

  
  public BarrierbilityStairsProjectile(World world, LivingEntity player) {
    super(BariProjectiles.BARRIERBILITY_STAIRS, world, player);
    
    setMaxLife(40);
    setPhysical(false);
    setPassThroughEntities();
    setPassThroughBlocks();
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    IAbilityData abilityProps = AbilityDataCapability.get(getThrower());
    BarrierbilityStairsAbility ability = (BarrierbilityStairsAbility)abilityProps.getEquippedAbility((Ability)BarrierbilityStairsAbility.INSTANCE);
    
    if (ability != null && ability.isContinuous())
      ability.fillBlocksList(AbilityHelper.createFilledCube(this.world, getPosX(), getPosY() - 2.0D, getPosZ(), 1, 1, 1, ModBlocks.BARRIER, GRIEF_RULE)); 
  }
}


