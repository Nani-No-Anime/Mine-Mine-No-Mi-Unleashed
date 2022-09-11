package xyz.pixelatedw.mineminenomi.abilities.awa;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.awa.GoldenHourParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class GoldenHourAbility
  extends ContinuousAbility {
  public static final GoldenHourAbility INSTANCE = new GoldenHourAbility();
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GoldenHourParticleEffect();

  
  public GoldenHourAbility() {
    super("Golden Hour", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(60.0D);
    setThreshold(10.0D);
    setDescription("Spreads bubbles on enemies around, leaving them weakened and immobile");
    
    this.duringContinuityEvent = this::duringContinuity;
  }

  
  private void duringContinuity(PlayerEntity player, int timer) {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class );
    targets.removeIf(Entity::isInWater);
    targets.remove(player);
    
    for (LivingEntity target : targets) {
      
      if (!target.isPotionActive(ModEffects.WASHED)) {
        target.addPotionEffect(new EffectInstance(ModEffects.WASHED, 600, 1, true, false, true));
      }
      PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
  }
}


