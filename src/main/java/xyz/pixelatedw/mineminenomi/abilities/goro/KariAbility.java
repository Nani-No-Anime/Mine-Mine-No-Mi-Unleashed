package xyz.pixelatedw.mineminenomi.abilities.goro;


import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.KariParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class KariAbility
  extends ChargeableAbility
{
  public static final Ability INSTANCE = (Ability)new KariAbility();
  
  private static final KariParticleEffect PARTICLES = new KariParticleEffect(1);

  
  public KariAbility() {
    super("Kari", AbilityHelper.getDevilFruitCategory());
    setDescription("The user heats the air around them with lightning until it explodes with a thunder clap. \n\nThis can be used to avoid and neutralize projectiles.");
    setMaxCooldown(12.0D);
    setMaxChargeTime(3.0D);
    setCancelable();
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    
    if (chargeTimer % 2 == 0) {
      
      float percentage = 1.0F - getChargeTime() / getMaxChargeTime();
      PARTICLES.setRange((int)(6.0F + 10.0F * percentage));
      PARTICLES.setSize(2.0F + 3.0F * percentage);
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    float percentage = 1.0F - getChargeTime() / getMaxChargeTime();
    List<Entity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, (6.0F + 10.0F * percentage), FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { Entity.class });
    list.remove(player);
    
    for (Entity target : list) {
      
      if (target instanceof net.minecraft.entity.projectile.ThrowableEntity || target instanceof net.minecraft.entity.projectile.AbstractArrowEntity) {
        target.remove();
      }
      if (target instanceof LivingEntity) {
        target.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player), 20.0F + 20.0F * percentage);
      }
    } 
    player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER, 2.0F, 0.5F + player.getRNG().nextFloat() * 0.2F);
    
    setMaxCooldown((1.0F + percentage * (getMaxChargeTime() / 20.0F - 1.0F)));
    return true;
  }
}


