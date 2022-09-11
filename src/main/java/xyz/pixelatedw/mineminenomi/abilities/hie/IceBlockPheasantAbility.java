package xyz.pixelatedw.mineminenomi.abilities.hie;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockPheasantProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class IceBlockPheasantAbility extends Ability {
  public static final Ability INSTANCE = new IceBlockPheasantAbility();

  
  public IceBlockPheasantAbility() {
    super("Ice Block: Pheasant", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(25.0D);
    setDescription("Releases a massive wave of ice in the shape of a pheasant");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    IceBlockPheasantProjectile proj = new IceBlockPheasantProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.0F);
    
    return true;
  }
}


