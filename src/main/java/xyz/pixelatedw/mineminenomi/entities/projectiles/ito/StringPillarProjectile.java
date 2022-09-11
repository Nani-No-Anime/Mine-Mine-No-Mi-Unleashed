package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class StringPillarProjectile extends AbilityProjectileEntity {
  public StringPillarProjectile(World world) {
    super(ItoProjectiles.STRING_PILLAR, world);
  }

  
  public StringPillarProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public StringPillarProjectile(World world, double x, double y, double z) {
    super(ItoProjectiles.STRING_PILLAR, world, x, y, z);
  }

  
  public StringPillarProjectile(World world, LivingEntity player) {
    super(ItoProjectiles.STRING_PILLAR, world, player);
    
    setDamage(15.0F);
    setPhysical(false);
    setAffectedByImbuing();
    setPassThroughEntities();
    setArmorPiercing();
    this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
  }

  
  public void tick() {
    super.tick();
    this.rotationPitch = 90.0F;
  }
}


