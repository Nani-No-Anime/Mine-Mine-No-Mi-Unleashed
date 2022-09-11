package xyz.pixelatedw.mineminenomi.events.passives;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;

@EventBusSubscriber(modid = "mineminenomi")
public class GasuPassiveEvents {
  public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.GASU, "Gasu", GasuPassiveEvents::gasuDamage, new net.minecraft.util.DamageSource[0]);
  
  public static boolean gasuDamage(LivingEntity target, LivingEntity attacker) {
    if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled())
      attacker.addPotionEffect(new EffectInstance(Effects.POISON, 40, 0)); 
    return false;
  }
}


