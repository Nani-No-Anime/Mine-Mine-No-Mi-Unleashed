package xyz.pixelatedw.mineminenomi.abilities.suke;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class SkattingAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final Ability INSTANCE = (Ability)new SkattingAbility();

  
  public SkattingAbility() {
    super("Skatting", AbilityHelper.getDevilFruitCategory());
    setDescription("Turns the user's entire body invisible");
    
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuity(PlayerEntity player, int tick) {
    player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 60, 1, false, false));
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.removePotionEffect(Effects.INVISIBILITY);
    return true;
  }
}


