package xyz.pixelatedw.mineminenomi.abilities.nikyu;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class HanpatsuAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new HanpatsuAbility();

  
  public HanpatsuAbility() {
    super("Hanpatsu", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("Anyone the user punches gets sent flying far into the air");
    
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    double xPower = WyHelper.randomWithRange(-20, 20);
    if (xPower >= 0.0D) { xPower += 30.0D; }
    else { xPower -= 30.0D; }
    
    double zPower = WyHelper.randomWithRange(-20, 20);
    if (zPower >= 0.0D) { zPower += 30.0D; }
    else { zPower -= 30.0D; }
    
    target.setPosition(player.getPosX(), player.getPosY() + 20.0D, player.getPosZ());
    target.setMotion(xPower, 2.5D, zPower);
    target.velocityChanged = true;
    target.fallDistance = 0.0F;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 1.0F;
  }
}


