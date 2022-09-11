package xyz.pixelatedw.mineminenomi.abilities.doa;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class KaitenDoorAbility extends PunchAbility {
  public static final KaitenDoorAbility INSTANCE = new KaitenDoorAbility();

  
  public KaitenDoorAbility() {
    super("Kaiten Door", AbilityHelper.getDevilFruitCategory());
    setDescription("Turn the head of your opponent into a rotating door to confuse and disorient them");
    setMaxCooldown(8.0D);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 1, false, false));
    EffectInstance instance = new EffectInstance(ModEffects.DOOR_HEAD, 200, 0, false, false);
    target.addPotionEffect(instance);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 4.0F;
  }
}


