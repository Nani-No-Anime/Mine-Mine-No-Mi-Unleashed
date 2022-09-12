package xyz.pixelatedw.mineminenomi.abilities.sube;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

import java.util.Arrays;
import java.util.List;

public class SubeSubeDeflectAbility
  extends ContinuousAbility implements IOnDamageAbility {
  public static final SubeSubeDeflectAbility INSTANCE = new SubeSubeDeflectAbility();
  private List<String> blockedSources = Arrays.asList(new String[] { "mob", "player", "ability_projectile", "arrow", "sting", "trident", "thrown", "sweetBerryBush", "cactus" });

  
  public SubeSubeDeflectAbility() {
    super("Sube Sube Deflect", AbilityHelper.getDevilFruitCategory());
    setDescription("Temporarily makes the user immune to attacks either by weapon or by hand to hand combat, as those attacks would just slip off their body.");
    setThreshold(12.0D);
    
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
    if (source.getImmediateSource() instanceof AbilityProjectileEntity && !((AbilityProjectileEntity)source.getImmediateSource()).isPhysical()) {
      return false;
    }
    return !this.blockedSources.contains(source.getDamageType());
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 8.0D) + 4;
    setMaxCooldown(cooldown);
    return true;
  }
}


