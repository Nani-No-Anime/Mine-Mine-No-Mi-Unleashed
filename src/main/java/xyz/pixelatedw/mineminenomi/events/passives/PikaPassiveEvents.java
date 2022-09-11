package xyz.pixelatedw.mineminenomi.events.passives;


import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;

@EventBusSubscriber(modid = "mineminenomi")
public class PikaPassiveEvents {
  public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.PIKA, "Pika", PikaPassiveEvents::pikaDamage, new net.minecraft.util.DamageSource[0]);
  
  public static boolean pikaDamage(LivingEntity target, LivingEntity attacker) {
    if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled())
      attacker.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 60, 2)); 
    return false;
  }
}


