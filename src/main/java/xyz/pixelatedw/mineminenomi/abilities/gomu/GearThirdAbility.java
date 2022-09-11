package xyz.pixelatedw.mineminenomi.abilities.gomu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class GearThirdAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final GearThirdAbility INSTANCE = new GearThirdAbility();

  
  public GearThirdAbility() {
    super("Gear Third", AbilityHelper.getDevilFruitCategory());
    setThreshold(10.0D);
    setDescription("By blowing air and inflating their body, the user's attacks get bigger and gain incredible strength");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!GomuHelper.canActivateGear(AbilityDataCapability.get((LivingEntity)player), (Ability)INSTANCE)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_GEAR_ACTIVE, new Object[0]));
      return false;
    } 
    
    return true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 20.0D);
    setMaxCooldown(cooldown);
    if (EntityStatsCapability.get((LivingEntity)player).getDoriki() < 3000) {
      
      player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 300, 1, true, true));
      player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 300, 1, true, true));
      player.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, 300, 1, true, true));
    } 
    
    return true;
  }
}


