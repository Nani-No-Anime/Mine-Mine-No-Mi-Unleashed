package xyz.pixelatedw.mineminenomi.abilities.kobu;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.kobu.ShoureiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.List;

public class ShoureiAbility
  extends Ability {
  public static final ShoureiAbility INSTANCE = new ShoureiAbility();
  private static final ShoureiParticleEffect PARTICLES = new ShoureiParticleEffect();

  
  public ShoureiAbility() {
    super("Shourei", AbilityHelper.getDevilFruitCategory());
    setDescription("Increases other people's fighting spirit and physical strength by simply encouraging them with words.");
    setMaxCooldown(60.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20.0D, FactionHelper.getSameGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    for (LivingEntity target : targets) {
      
      target.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 1, true, false, true));
      target.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 1, true, false, true));
      target.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 400, 1, true, false, true));
      target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 0, true, false, true));
      
      PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
    
    return true;
  }
}


