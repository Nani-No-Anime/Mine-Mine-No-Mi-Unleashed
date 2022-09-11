package xyz.pixelatedw.mineminenomi.abilities.beta;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.beta.StickyProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class BetaLauncherAbility extends RepeaterAbility {
  public static final Ability INSTANCE = (Ability)new BetaLauncherAbility();

  
  public BetaLauncherAbility() {
    super("Beta Launcher", AbilityHelper.getDevilFruitCategory());
    setDescription("Shoots sticky Mucus which cause explosions on contact when combined with fire (also holding Flint & Steel)");
    setMaxCooldown(9.0D);
    setMaxRepeaterCount(6, 3);
    
    this.onUseEvent = this::onUseEvent;
  }


  
  private boolean onUseEvent(PlayerEntity player) {
    HanamizuShinkenAbility ability = (HanamizuShinkenAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)HanamizuShinkenAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      ability.endContinuity(player);
    }
    StickyProjectile proj = new StickyProjectile(player.world, (LivingEntity)player);
    if (player.getHeldItemMainhand().getItem() == Items.FLINT_AND_STEEL) {
      
      proj.setDamage(8.0F);
      proj.setCauseExplosion();
      proj.setChangeHurtTime(true);
    } 
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
    
    return true;
  }
}


