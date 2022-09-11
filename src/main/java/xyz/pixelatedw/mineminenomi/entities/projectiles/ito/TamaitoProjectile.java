package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class TamaitoProjectile extends AbilityProjectileEntity {
  public TamaitoProjectile(World world) {
    super(ItoProjectiles.TAMAITO, world);
  }

  
  public TamaitoProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public TamaitoProjectile(World world, double x, double y, double z) {
    super(ItoProjectiles.TAMAITO, world, x, y, z);
  }

  
  public TamaitoProjectile(World world, LivingEntity player) {
    super(ItoProjectiles.TAMAITO, world, player);
    
    setDamage(10.0F);
    setPhysical(false);
    setAffectedByImbuing();
    setMaxLife(30);
    setArmorPiercing();
    this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
  }
}


