package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;

@EventBusSubscriber(modid = "mineminenomi")
public class MeraPassiveEvents {
  public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.MERA, "Mera", MeraPassiveEvents::meraDamage, new DamageSource[] { DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.HOT_FLOOR });
  
  public static boolean meraDamage(LivingEntity target, LivingEntity attacker) {
    if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled()) {
      SetOnFireEvent event = new SetOnFireEvent(attacker, target, 10);
      if (!MinecraftForge.EVENT_BUS.post(event))
        attacker.setFire(5); 
    } 
    return false;
  }
}


