package xyz.pixelatedw.mineminenomi.abilities.chiyu;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.HealingTouchParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class HealingTouchAbility extends PunchAbility {
  public static final HealingTouchAbility INSTANCE = new HealingTouchAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new HealingTouchParticleEffect();

  
  public HealingTouchAbility() {
    super("Healing Touch", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("Touch the target to heal them");
    
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    target.heal(20.0F);
    target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return 0.0F;
  }
}


