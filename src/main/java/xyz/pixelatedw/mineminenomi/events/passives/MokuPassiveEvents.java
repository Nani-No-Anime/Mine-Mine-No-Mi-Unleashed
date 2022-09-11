package xyz.pixelatedw.mineminenomi.events.passives;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;

@EventBusSubscriber(modid = "mineminenomi")
public class MokuPassiveEvents {
  public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.MOKU2, "Moku", MokuPassiveEvents::mokuDamage, new DamageSource[] { DamageSource.IN_FIRE });
  
  public static boolean mokuDamage(LivingEntity target, LivingEntity attacker) {
    if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled())
      attacker.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 1)); 
    return false;
  }
}


