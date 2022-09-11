package xyz.pixelatedw.mineminenomi.abilities.ito;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.TamaitoProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class TamaitoAbility extends Ability {
  public static final TamaitoAbility INSTANCE = new TamaitoAbility();

  
  public TamaitoAbility() {
    super("Tamaito", AbilityHelper.getDevilFruitCategory());
    setDescription("The user shoots a small bundle of strings, acting like a bullet");
    setMaxCooldown(1.5D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    TamaitoProjectile proj = new TamaitoProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.2F);
    
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    return true;
  }
}


