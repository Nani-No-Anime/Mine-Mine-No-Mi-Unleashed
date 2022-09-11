package xyz.pixelatedw.mineminenomi.events.passives;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;

@EventBusSubscriber(modid = "mineminenomi")
public class GoroPassiveEvents {
  public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.GORO, "Goro", GoroPassiveEvents::goroDamage, new DamageSource[] { DamageSource.LIGHTNING_BOLT, DamageSource.IN_FIRE, DamageSource.HOT_FLOOR });

  
  public static boolean goroDamage(LivingEntity target, LivingEntity attacker) {
    IDevilFruit props = DevilFruitCapability.get(attacker);
    boolean attackerHasGomu = (props.hasDevilFruit(ModAbilities.GOMU_GOMU_NO_MI) && attacker.getHeldItemMainhand().isEmpty());
    if (!attackerHasGomu) {
      
      if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled())
        attacker.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)target), 12.0F); 
      return false;
    } 
    return true;
  }
}


