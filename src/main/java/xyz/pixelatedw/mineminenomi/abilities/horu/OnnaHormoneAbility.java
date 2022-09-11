package xyz.pixelatedw.mineminenomi.abilities.horu;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class OnnaHormoneAbility extends PunchAbility {
  public static final OnnaHormoneAbility INSTANCE = new OnnaHormoneAbility();

  
  public OnnaHormoneAbility() {
    super("Onna Hormone", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("By injecting an enemy with special female hormones, the user can inflict moderate debuffs on them\n\n" + TextFormatting.DARK_GREEN + "SHIFT-USE" + TextFormatting.RESET + ": The user injects themselves");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isSneaking()) {
      
      player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 600, 2));
      player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 600, 1));
      player.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0));
      endContinuity(player);
      return false;
    } 
    
    return true;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 600, 2));
    target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 600, 1));
    target.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0));
    
    return 0.0F;
  }
}


