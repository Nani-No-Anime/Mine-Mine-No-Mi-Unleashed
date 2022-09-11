package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class ChampFightProjectile
  extends AbilityProjectileEntity
{
  public ChampFightProjectile(World world) {
    super(DoruProjectiles.CHAMP_FIGHT, world);
  }

  
  public ChampFightProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public ChampFightProjectile(World world, double x, double y, double z) {
    super(DoruProjectiles.CHAMP_FIGHT, world, x, y, z);
  }

  
  public ChampFightProjectile(World world, LivingEntity player) {
    super(DoruProjectiles.CHAMP_FIGHT, world, player);
    
    setDamage(10.0F);
    setMaxLife(20);
    setPhysical(true);
  }
}


