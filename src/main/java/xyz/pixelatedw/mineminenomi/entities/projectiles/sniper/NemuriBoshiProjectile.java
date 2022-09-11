package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class NemuriBoshiProjectile extends AbilityProjectileEntity {
  public NemuriBoshiProjectile(World world) {
    super(SniperProjectiles.NEMURI_BOSHI, world);
  }

  
  public NemuriBoshiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public NemuriBoshiProjectile(World world, double x, double y, double z) {
    super(SniperProjectiles.NEMURI_BOSHI, world, x, y, z);
  }

  
  public NemuriBoshiProjectile(World world, LivingEntity player) {
    super(SniperProjectiles.NEMURI_BOSHI, world, player);
    
    setDamage(2.0F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    EffectInstance effect = new EffectInstance(ModEffects.UNCONSCIOUS, 120, 1);
    hitEntity.addPotionEffect(effect);
    if (this.owner instanceof ServerPlayerEntity)
      ((ServerPlayerEntity)this.owner).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(hitEntity.getEntityId(), effect)); 
  }
}


