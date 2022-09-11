package xyz.pixelatedw.mineminenomi.wypi.abilities.events;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

@Cancelable
public class ProjectileShootEvent
  extends Event
{
  private AbilityProjectileEntity projectile;
  private float velocity;
  private float inaccuracy;
  
  public ProjectileShootEvent(AbilityProjectileEntity abilityProjectileEntity, float velocity, float inaccuracy) {
    this.projectile = abilityProjectileEntity;
    this.velocity = velocity;
    this.inaccuracy = inaccuracy;
  }

  
  public AbilityProjectileEntity getProjectile() {
    return this.projectile;
  }

  
  public float getVelocity() {
    return this.velocity;
  }

  
  public float getInaccuracy() {
    return this.inaccuracy;
  }
}


