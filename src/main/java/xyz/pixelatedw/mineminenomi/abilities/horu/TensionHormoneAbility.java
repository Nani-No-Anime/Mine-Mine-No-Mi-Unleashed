package xyz.pixelatedw.mineminenomi.abilities.horu;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class TensionHormoneAbility extends PunchAbility {
  public static final TensionHormoneAbility INSTANCE = new TensionHormoneAbility();

  
  public TensionHormoneAbility() {
    super("Tension Hormone", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("The user injects a target with special hormones providing a supply of adrenaline that strengthens them\n\n§2SHIFT-USE§r: The user injects themselves");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {

    if (player.isSneaking()) {
      
      player.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 1));
      endContinuity(player);
      return false;
    } 
    
    return true;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    target.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 1));
    
    return 0.0F;
  }
}


