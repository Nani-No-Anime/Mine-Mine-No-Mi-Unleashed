package xyz.pixelatedw.mineminenomi.abilities.pero;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class CandyArmorAbility extends ContinuousAbility implements IBodyOverlayAbility {
  public static final CandyArmorAbility INSTANCE = new CandyArmorAbility();
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.CANDY_ARMOR).setColor(WyHelper.hexToRGB("#FFFFFF99"));

  
  public CandyArmorAbility() {
    super("Candy Armor", AbilityHelper.getDevilFruitCategory());
    setThreshold(15.0D);
    setDescription("Coat yourself with a hard candy armor, which boosts defense, but reduces mobility");
    
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20, 2, false, false));
    player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 2, false, false));
    player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 1, false, false));
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 12.0D) + 2;
    setMaxCooldown(cooldown);
    
    player.removePotionEffect(Effects.RESISTANCE);
    player.removePotionEffect(Effects.SLOWNESS);
    player.removePotionEffect(Effects.MINING_FATIGUE);
    return true;
  }


  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }
}


