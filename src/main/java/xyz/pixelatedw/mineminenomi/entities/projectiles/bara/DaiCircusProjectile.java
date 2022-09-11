package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.bara.KuchuKirimomiDaiCircusAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class DaiCircusProjectile extends AbilityProjectileEntity {
  public DaiCircusProjectile(World world) {
    super(BaraProjectiles.DAI_CIRCUS, world);
  }

  
  public DaiCircusProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public DaiCircusProjectile(World world, double x, double y, double z) {
    super(BaraProjectiles.DAI_CIRCUS, world, x, y, z);
  }

  
  public DaiCircusProjectile(World world, LivingEntity player) {
    super(BaraProjectiles.DAI_CIRCUS, world, player);
    
    setDamage(4.0F);
    setMaxLife(30);
    setPhysical(true);
    setCollisionSize(2.0D);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    LivingEntity owner = getThrower();
    
    IAbilityData abilityProps = AbilityDataCapability.get(owner);
    KuchuKirimomiDaiCircusAbility ability = (KuchuKirimomiDaiCircusAbility)abilityProps.getEquippedAbility((Ability)KuchuKirimomiDaiCircusAbility.INSTANCE);
    if (ability != null && ability.isContinuous() && FactionHelper.getOutsideGroupPredicate(owner).test(hitEntity))
      ability.grabEntity(hitEntity); 
  }
}


