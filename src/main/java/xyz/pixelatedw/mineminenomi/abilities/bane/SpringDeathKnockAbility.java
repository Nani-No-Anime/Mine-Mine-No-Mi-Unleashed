package xyz.pixelatedw.mineminenomi.abilities.bane;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bane.SpringDeathKnockProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SpringDeathKnockAbility extends Ability {
  public static final Ability INSTANCE = new SpringDeathKnockAbility();

  
  public SpringDeathKnockAbility() {
    super("Spring Death Knock", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(4.0D);
    setDescription("By turning the user's arm into a spring and compressing it, they can launch a powerful punch");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    SpringDeathKnockProjectile proj = new SpringDeathKnockProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    return true;
  }
}


