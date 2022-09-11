package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class IceTimeCapsuleAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new IceTimeCapsuleAbility();

  
  public IceTimeCapsuleAbility() {
    super("Ice Time Capsule", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(45.0D);
    setDescription("Hit the target to encase them in ice");
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    EffectInstance instance = new EffectInstance(ModEffects.FROZEN, 300, 0);
    target.addPotionEffect(instance);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 1.0F;
  }
}


