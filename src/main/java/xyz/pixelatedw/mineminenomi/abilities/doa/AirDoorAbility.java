package xyz.pixelatedw.mineminenomi.abilities.doa;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModSounds;

public class AirDoorAbility extends DamagedContinuousAbility {
  public static final AirDoorAbility INSTANCE = new AirDoorAbility();

  
  public AirDoorAbility() {
    super("Air Door", AbilityHelper.getDevilFruitCategory());
    setDescription("The user travels into an air dimension and is invincible during that time");
    setThreshold(60.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onDamagedEvent = this::onDamaged;
  }
  
  private boolean onDamaged(LivingEntity entity, DamageSource damageSource, double amount) {
    return damageSource.isDamageAbsolute();
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 1200, 0, false, false));
    player.world.playSound(null, player.getPosition(), ModSounds.DOA_IN_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.removePotionEffect(Effects.INVISIBILITY);
    player.world.playSound(null, player.getPosition(), ModSounds.DOA_OUT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    double cooldown = (10.0F + this.continueTime / 40.0F);
    setMaxCooldown(cooldown);
    
    return true;
  }
}


