package xyz.pixelatedw.mineminenomi.abilities.doku;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

import java.awt.*;

public class DokuFuguAbility extends ContinuousAbility implements IParallelContinuousAbility, IBodyOverlayAbility {
  public static final DokuFuguAbility INSTANCE = new DokuFuguAbility();
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(1.0F, 1.0F, 1.0F, 0.6F));

  
  public DokuFuguAbility() {
    super("Doku Fugu", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(30.0D);
    setThreshold(60.0D);
    setDescription("The user covers themselves in poison creating a thin protective layer to damage");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    return !VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player);
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    if (VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      
      endContinuity(player);
      
      return;
    } 
    player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 2, false, false));
  }


  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }
}


