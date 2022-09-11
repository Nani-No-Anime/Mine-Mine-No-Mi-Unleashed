package xyz.pixelatedw.mineminenomi.events.passives;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;


@EventBusSubscriber(modid = "mineminenomi")
public class SunaPassiveEvents
{
  public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.SUNA2, "Suna", SunaPassiveEvents::sunaDamage, new net.minecraft.util.DamageSource[0]);
  
  public static boolean sunaDamage(LivingEntity target, LivingEntity attacker) {
    if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled()) {
      attacker.addPotionEffect(new EffectInstance(Effects.WITHER, 40, 1));
    }
    return target.isWet();
  }

  
  @SubscribeEvent
  public static void playerUpdateEvent(TickEvent.PlayerTickEvent event) {
    PlayerEntity player = event.player;
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (!props.hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI)) {
      return;
    }
    if (player.isWet()) {
      player.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, 40, 0, true, true));
    }
  }
  
  @SubscribeEvent
  public static void projectileImpactEvent(ProjectileImpactEvent.Throwable event) {
    if (event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY) {
      
      EntityRayTraceResult entityHit = (EntityRayTraceResult)event.getRayTraceResult();
      if (entityHit.getEntity() instanceof LivingEntity && event.getThrowable() instanceof net.minecraft.entity.projectile.PotionEntity) {
        
        LivingEntity entity = (LivingEntity)entityHit.getEntity();
        IDevilFruit props = DevilFruitCapability.get(entity);
        if (props.hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI))
          entity.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, 80, 0, true, true)); 
      } 
    } 
  }
}


