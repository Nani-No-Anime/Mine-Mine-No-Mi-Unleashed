package xyz.pixelatedw.mineminenomi.wypi.abilities;

import java.io.Serializable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public abstract class PunchAbility
  extends ContinuousAbility
{
  private boolean isStoppingAfterHit = true;
  protected IOnHitEffect onHitEffectEvent = (player, target) -> {
    
    };
  protected IOnHitEntity onHitEntityEvent = (player, target) -> 0.0F;
  
  public PunchAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
  }

  
  public void setStoppingAfterHit(boolean flag) {
    this.isStoppingAfterHit = flag;
  }

  
  public boolean isStoppingAfterHit() {
    return this.isStoppingAfterHit;
  }

  
  public DamageSource getPunchDamageSource(PlayerEntity player) {
    return (DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this);
  }




  
  public void hitEffect(PlayerEntity player, LivingEntity target) {
    this.onHitEffectEvent.hitEffect(player, target);
    
    if (this.isStoppingAfterHit) {
      endContinuity(player);
    }
  }
  
  public float hitEntity(PlayerEntity player, LivingEntity target) {
    float result = this.onHitEntityEvent.onHitEntity(player, target);
    
    if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
      WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.getEntityId(), getDisplayName()), (LivingEntity)player);
    }
    return result;
  }
  
  public static interface IOnHitEntity extends Serializable {
    float onHitEntity(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity);
  }
  
  public static interface IOnHitEffect extends Serializable {
    void hitEffect(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity);
  }
}


