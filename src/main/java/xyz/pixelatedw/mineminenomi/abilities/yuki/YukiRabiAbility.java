package xyz.pixelatedw.mineminenomi.abilities.yuki;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.yuki.YukiRabiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class YukiRabiAbility extends RepeaterAbility {
  public static final YukiRabiAbility INSTANCE = new YukiRabiAbility();

  
  public YukiRabiAbility() {
    super("Yuki Rabi", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches numerous hardened snowballs, shaped like a rabbit's head, that can inflict §2Frostbite§r on their enemies");
    setMaxCooldown(6.0D);
    setMaxRepeaterCount(6, 3);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    YukiRabiProjectile proj = new YukiRabiProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 2.0F);
    
    return true;
  }
}


