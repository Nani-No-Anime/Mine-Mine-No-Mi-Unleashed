package xyz.pixelatedw.mineminenomi.abilities.moku;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.moku.WhiteLauncherParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class WhiteLauncherAbility extends Ability implements IMultiTargetAbility {
  public static final Ability INSTANCE = new WhiteLauncherAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new WhiteLauncherParticleEffect();

  
  public WhiteLauncherAbility() {
    super("White Launcher", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(7.0D);
    setDescription("Transforms the user into smoke and launches them forward");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
    clearTargets();
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 5.5D, 5.5D);
    player.setMotion(speed.x, 2.0D, speed.z);
    
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if ((cooldownTimer / 20) > this.maxCooldown / 20.0D - 3.0D) {
      
      if (cooldownTimer % 2 == 0) {
        PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
      }
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      
      for (LivingEntity target : list) {
        
        if (isTarget(target) && player.canEntityBeSeen((Entity)target))
          target.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F); 
      } 
    } 
  }
}


