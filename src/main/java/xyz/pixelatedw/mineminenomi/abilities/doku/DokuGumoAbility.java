package xyz.pixelatedw.mineminenomi.abilities.doku;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.DokuGumoParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

import java.util.List;

public class DokuGumoAbility
  extends ContinuousAbility {
  public static final DokuGumoAbility INSTANCE = new DokuGumoAbility();
  
  private static final DokuGumoParticleEffect PARTICLES = new DokuGumoParticleEffect();

  
  public DokuGumoAbility() {
    super("Doku Gumo", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(30.0D);
    setThreshold(20.0D);
    setDescription("Creates a dense cloud of poisonous smoke, which moves along with the user and poisons and blinds everyone inside");
    
    this.duringContinuityEvent = this::duringContinuity;
  }

  
  private void duringContinuity(PlayerEntity player, int timer) {
    int power = 0;
    int duration = 100;
    int range = 8;
    boolean color = false;
    
    if (VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      
      color = true;
      power += 2;
      duration *= 2;
      range = (int)(range * 1.5D);
    } 
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    player.addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));
    
    for (LivingEntity enemy : targets) {
      
      if (!enemy.isPotionActive(Effects.BLINDNESS))
        enemy.addPotionEffect(new EffectInstance(Effects.BLINDNESS, duration, power)); 
      if (!enemy.isPotionActive(Effects.POISON))
        enemy.addPotionEffect(new EffectInstance(Effects.POISON, duration, power + 1)); 
      if (!enemy.isPotionActive(Effects.WEAKNESS)) {
        enemy.addPotionEffect(new EffectInstance(Effects.WEAKNESS, duration, power));
      }
    } 
    PARTICLES.venomDemon = color;
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  public void enableVenomDemoMode() {
    setCustomTexture("doku_gumo_venom");
  }

  
  public void disableVenomDemoMode() {
    setCustomTexture("");
  }
}


