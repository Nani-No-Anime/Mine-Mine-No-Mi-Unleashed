package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HanaGenericEntity
  extends AbilityProjectileEntity
{
  public HanaGenericEntity(World world) {
    super(HanaProjectiles.GENERIC_HANA, world);
  }

  
  public HanaGenericEntity(World world, LivingEntity player) {
    super(HanaProjectiles.GENERIC_HANA, world, player);
    setCollisionSize(1.25D);
    setDamage(0.0F);
    setFake();
    setMaxLife(10);
    setPhysical(true);
  }
  
  public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {}
}


