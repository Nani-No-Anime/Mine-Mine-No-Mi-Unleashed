package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;


import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.KarakusagawaraSeikenChargeParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class KarakusagawaraSeikenAbility
  extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new KarakusagawaraSeikenAbility();
  
  private static final KarakusagawaraSeikenChargeParticleEffect PARTICLES = new KarakusagawaraSeikenChargeParticleEffect();

  
  public KarakusagawaraSeikenAbility() {
    super("Karakusagawara Seiken", AbilityHelper.getRacialCategory());
    setMaxCooldown(25.0D);
    setMaxChargeTime(2.0D);
    setDescription("The user punches the air, which sends a shockwave through water vapor in the air");
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    targets.forEach(entity -> {
          
          entity.attackEntityFrom(ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this).setDamageBypassesArmor(), (entity.isInWater() || player.isInWater()) ? 50.0F : 20.0F);
          
          entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
        });
    return true;
  }
}


