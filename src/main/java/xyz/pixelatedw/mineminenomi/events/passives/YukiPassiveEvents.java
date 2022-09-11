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
public class YukiPassiveEvents {
  public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.YUKI, "Yuki", YukiPassiveEvents::yukiDamage, new net.minecraft.util.DamageSource[0]);
  
  public static boolean yukiDamage(LivingEntity target, LivingEntity attacker) {
    if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled())
      attacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 1)); 
    return false;
  }
}


