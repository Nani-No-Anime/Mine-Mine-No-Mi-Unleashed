package xyz.pixelatedw.mineminenomi.entities.projectiles.mero;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class MeroMeroMellowProjectile
  extends AbilityProjectileEntity {
  public MeroMeroMellowProjectile(World world) {
    super(MeroProjectiles.MERO_MERO_MELLOW, world);
  }

  
  public MeroMeroMellowProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public MeroMeroMellowProjectile(World world, double x, double y, double z) {
    super(MeroProjectiles.MERO_MERO_MELLOW, world, x, y, z);
  }

  
  public MeroMeroMellowProjectile(World world, LivingEntity player) {
    super(MeroProjectiles.MERO_MERO_MELLOW, world, player);
    
    setDamage(10.0F);
    setPassThroughEntities();
    setMaxLife(30);
    
    this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.LOVESTRUCK, 200, 1) });
  }
}


