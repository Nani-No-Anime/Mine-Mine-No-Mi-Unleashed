package xyz.pixelatedw.mineminenomi.abilities.pika;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.YasakaniNoMagatamaProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class YasakaniNoMagatamaAbility extends RepeaterAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new YasakaniNoMagatamaAbility();

  
  public YasakaniNoMagatamaAbility() {
    super("Yasakani no Magatama", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(14.0D);
    setMaxRepeaterCount(25, 3);
    setDescription("Fires a torrent of deadly light particles, causing huge destruction");
    
    this.onUseEvent = this::onUseEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onStartContinuityEvent = this::onStartContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity playerEntity) {
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
    AbilityHelper.slowEntityFall((LivingEntity)player);
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    for (int i = 0; i < WyHelper.randomWithRange(3, 6); i++) {
      
      YasakaniNoMagatamaProjectile proj = new YasakaniNoMagatamaProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.5F, 30.0F);
    } 
    
    player.world.playSound(null, player.getPosition(), ModSounds.PIKA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointBothArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


