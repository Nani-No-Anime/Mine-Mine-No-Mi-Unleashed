package xyz.pixelatedw.mineminenomi.abilities.moku;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.moku.WhiteStrikeParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class WhiteStrikeAbility
  extends Ability {
  public static final Ability INSTANCE = new WhiteStrikeAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new WhiteStrikeParticleEffect();

  
  public WhiteStrikeAbility() {
    super("White Strike", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setDescription("Surrounds the nearby area with smoke, slowing down nearby entities");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldownEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { LivingEntity.class });
    targets.remove(player);
    
    targets.forEach(entity -> entity.addPotionEffect(new EffectInstance(ModEffects.SMOKE, 800, 0, false, false)));



    
    return true;
  }

  
  private void duringCooldownEvent(PlayerEntity player, int cooldown) {
    if (cooldown > WyHelper.percentage(80.0D, this.maxCooldown))
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D); 
  }
}


