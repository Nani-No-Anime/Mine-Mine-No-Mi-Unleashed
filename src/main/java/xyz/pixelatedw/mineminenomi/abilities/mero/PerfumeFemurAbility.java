package xyz.pixelatedw.mineminenomi.abilities.mero;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.mero.PerfumeFemurParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class PerfumeFemurAbility extends PunchAbility {
  public static final PerfumeFemurAbility INSTANCE = new PerfumeFemurAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new PerfumeFemurParticleEffect();

  
  public PerfumeFemurAbility() {
    super("Perfume Femur", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(14.0D);
    setDescription("Turns enemies hit by the user into stone");
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    EffectInstance instance = new EffectInstance(ModEffects.LOVESTRUCK, 200, 1);
    target.addPotionEffect(instance);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
    
    PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 10.0F;
  }
}


