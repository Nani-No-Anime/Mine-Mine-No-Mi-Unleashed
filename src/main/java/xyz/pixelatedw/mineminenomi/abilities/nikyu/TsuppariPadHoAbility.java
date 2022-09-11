package xyz.pixelatedw.mineminenomi.abilities.nikyu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PadHoProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class TsuppariPadHoAbility extends RepeaterAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new TsuppariPadHoAbility();

  
  public TsuppariPadHoAbility() {
    super("Tsuppari Pad Ho", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(7.5D);
    setMaxRepeaterCount(5, 4);
    setDescription("Launches a barrage of paw-shaped shockwaves at the opponent");
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onUseEvent = this::onUseEvent;
  }
  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
  }
  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    player.world.playMovingSound(null, (Entity)player, ModSounds.MULTIPLE_PAD_HO_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    for (int i = 0; i < 6; i++) {
      
      PadHoProjectile proj = new PadHoProjectile(player.world, (LivingEntity)player);
      proj.setChangeHurtTime(true);
      proj.setDamage(5.0F);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 15.0F);
    } 
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointBothArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


