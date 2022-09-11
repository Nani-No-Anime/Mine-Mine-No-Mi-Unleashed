package xyz.pixelatedw.mineminenomi.events.passives;


import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;


@EventBusSubscriber(modid = "mineminenomi")
public class HiePassiveEvents
{
  public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.HIE, "Hie", HiePassiveEvents::hieDamage, new net.minecraft.util.DamageSource[0]);
  
  public static boolean hieDamage(LivingEntity target, LivingEntity attacker) {
    if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled())
      attacker.addPotionEffect(new EffectInstance(ModEffects.FROZEN, 40, 0)); 
    return false;
  }
}


