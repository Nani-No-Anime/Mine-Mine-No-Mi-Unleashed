package xyz.pixelatedw.mineminenomi.abilities.horu;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class ChiyuHormoneAbility extends PunchAbility {
  public static final ChiyuHormoneAbility INSTANCE = new ChiyuHormoneAbility();

  
  public ChiyuHormoneAbility() {
    super("Chiyu Hormone", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setDescription("The user injects the target with special hormones to stimulate the body's immune system, healing them\n\n" + TextFormatting.DARK_GREEN + "SHIFT-USE" + TextFormatting.RESET + ": The user injects themselves");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isSneaking()) {
      
      player.addPotionEffect(new EffectInstance(ModEffects.CHIYU_HORMONE, 300, 0));
      endContinuity(player);
      return false;
    } 
    
    return true;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    target.addPotionEffect(new EffectInstance(ModEffects.CHIYU_HORMONE, 300, 0));
    
    return 0.0F;
  }
}


