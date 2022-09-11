package xyz.pixelatedw.mineminenomi.abilities.magu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.RyuseiKazanProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class RyuseiKazanAbility extends RepeaterAbility {
  public static final Ability INSTANCE = (Ability)new RyuseiKazanAbility();

  
  public RyuseiKazanAbility() {
    super("Ryusei Kazan", AbilityHelper.getDevilFruitCategory());
    setDescription("Functions like 'Dai Funka', but multiple fists are launched at the opponent");
    setMaxCooldown(20.0D);
    setMaxRepeaterCount(10, 5);
    
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onUseEvent = this::onUseEvent;
  }
  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    AbilityHelper.slowEntityFall((LivingEntity)player);
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RyuseiKazanProjectile proj = new RyuseiKazanProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 4.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.MAGU_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }
}


