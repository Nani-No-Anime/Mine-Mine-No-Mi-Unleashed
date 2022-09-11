package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.ushibison.KokuteiCrossParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class KokuteiCrossAbility extends PunchAbility implements IFormRequiredAbility {
  public static final KokuteiCrossAbility INSTANCE = new KokuteiCrossAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new KokuteiCrossParticleEffect();

  
  public KokuteiCrossAbility() {
    super("Kokutei Cross", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(7.0D);
    setDescription("The transformed user crosses their hooves to hit the opponent");
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.5D, 1.5D);
    target.setMotion(speed.x, player.getMotion().getY(), speed.z);
    if (target instanceof PlayerEntity) {
      ((ServerPlayerEntity)target).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)target));
    }
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 20.0F;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)BisonHeavyZoanInfo.INSTANCE };
  }
}


