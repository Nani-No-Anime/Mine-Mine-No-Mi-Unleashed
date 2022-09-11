package xyz.pixelatedw.mineminenomi.abilities.suke;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class SukePunchAbility extends PunchAbility implements IParallelContinuousAbility {
  public static final Ability INSTANCE = (Ability)new SukePunchAbility();

  
  public SukePunchAbility() {
    super("Suke Punch", AbilityHelper.getDevilFruitCategory());
    setDescription("Turns an entity's entire body invisible after hitting them");
    
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    if (target.isPotionActive(Effects.INVISIBILITY)) {
      target.removePotionEffect(Effects.INVISIBILITY);
    } else {
      target.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 2000, 1, false, false));
    } 
    return 0.0F;
  }
}


