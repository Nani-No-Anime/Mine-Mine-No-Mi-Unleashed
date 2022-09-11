package xyz.pixelatedw.mineminenomi.abilities.ori;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class BindAbility extends PunchAbility {
  public static final BindAbility INSTANCE = new BindAbility();

  
  public BindAbility() {
    super("Bind", AbilityHelper.getDevilFruitCategory());
    setDescription("Punching ability that binds the target.");
    setMaxCooldown(5.0D);
    
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    EffectInstance instance = new EffectInstance(ModEffects.BIND, 200, 0, false, true);
    target.addPotionEffect(instance);
    if (player instanceof ServerPlayerEntity) {
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
    }
  }
  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 4.0F;
  }
}


