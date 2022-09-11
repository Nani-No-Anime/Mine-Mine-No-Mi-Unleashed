package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class KamieAbility
  extends ContinuousAbility implements IOnDamageAbility {
  public static final Ability INSTANCE = (Ability)new KamieAbility();

  
  public KamieAbility() {
    super("Kami-E", AbilityHelper.getRacialCategory());
    setThreshold(6.0D);
    setDescription("Makes the user's body flexible in order to avoid attacks");
    
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 10.0D) + 4;
    setMaxCooldown(cooldown);
    return true;
  }


  
  public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
    ArrayList<String> acceptableInstantSources = new ArrayList<>(Arrays.asList(new String[] { "mob", "player", "ability_projectile" }));
    
    if (source.getImmediateSource() instanceof LivingEntity) {
      return !acceptableInstantSources.contains(source.getDamageType());
    }
    return false;
  }
}


