package xyz.pixelatedw.mineminenomi.abilities.chiyu;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.ChiyupopoParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class ChiyupopoAbility
  extends Ability {
  public static final Ability INSTANCE = new ChiyupopoAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new ChiyupopoParticleEffect();

  
  public ChiyupopoAbility() {
    super("Chiyupopo", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(30.0D);
    setDescription("Releases dandelions made of tears that temporarily increase the healing rate of those around the user");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    player.heal(player.getMaxHealth() / 2.0F);
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 15.0D, FactionHelper.getSameGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    for (LivingEntity entity : targets)
    {
      entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 300, 1));
    }
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return true;
  }
}


