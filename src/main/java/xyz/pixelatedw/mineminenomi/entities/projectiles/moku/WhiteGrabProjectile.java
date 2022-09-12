package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.moku.WhiteGrabAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class WhiteGrabProjectile extends AbilityProjectileEntity {
  public WhiteGrabProjectile(World world) {
    super(MokuProjectiles.WHITE_GRAB, world);
  }

  
  public WhiteGrabProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public WhiteGrabProjectile(World world, double x, double y, double z) {
    super(MokuProjectiles.WHITE_GRAB, world, x, y, z);
  }

  
  public WhiteGrabProjectile(World world, LivingEntity player) {
    super(MokuProjectiles.WHITE_GRAB, world, player);
    
    setDamage(4.0F);
    setMaxLife(30);
    setPhysical(true);
    setCollisionSize(2.0D);
    
    this.onTickEvent = this::onTickEvent;
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    LivingEntity owner = getThrower();
    
    IAbilityData abilityProps = AbilityDataCapability.get(owner);
    WhiteGrabAbility ability = (WhiteGrabAbility)abilityProps.getEquippedAbility((Ability)WhiteGrabAbility.INSTANCE);
    if (ability != null && ability.isContinuous() && FactionHelper.getOutsideGroupPredicate(owner).test(hitEntity)) {
      ability.grabEntity(hitEntity);
    }
  }
  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 10; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
        data.setLife(10);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


