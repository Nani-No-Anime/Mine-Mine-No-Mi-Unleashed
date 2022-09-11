package xyz.pixelatedw.mineminenomi.abilities.moku;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteOutProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class WhiteOutAbility extends Ability {
  public static final Ability INSTANCE = new WhiteOutAbility();

  
  public WhiteOutAbility() {
    super("White Out", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("Shoots a cloud of smoke to engulf the opponent and trap them");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    WhiteOutProjectile proj = new WhiteOutProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.75F, 1.0F);
    
    return true;
  }
}


