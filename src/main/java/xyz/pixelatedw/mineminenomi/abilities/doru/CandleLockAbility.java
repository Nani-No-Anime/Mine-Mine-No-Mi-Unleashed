package xyz.pixelatedw.mineminenomi.abilities.doru;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.CandleLockProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class CandleLockAbility extends Ability {
  public static final Ability INSTANCE = new CandleLockAbility();

  
  public CandleLockAbility() {
    super("Candle Lock", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(12.0D);
    setDescription("Traps the opponent's feet in hardened wax, which makes them unable to move");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    CandleLockProjectile proj = new CandleLockProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    
    return true;
  }
}


