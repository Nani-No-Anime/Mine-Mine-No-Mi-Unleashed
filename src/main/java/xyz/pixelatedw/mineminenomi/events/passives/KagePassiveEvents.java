package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.kage.DoppelmanAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


@EventBusSubscriber(modid = "mineminenomi")
public class KagePassiveEvents
{
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.getEntityLiving() instanceof LivingEntity)) {
      return;
    }
    LivingEntity entity = event.getEntityLiving();
    IEntityStats statsProps = EntityStatsCapability.get(entity);
    
    if (!statsProps.hasShadow() && entity.getBrightness() > 0.8F) {
      entity.setFire(2);
    }
  }
  
  @SubscribeEvent
  public static void onEntityHurt(LivingAttackEvent event) {
    if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity attacker = (PlayerEntity)event.getSource().getTrueSource();
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)attacker);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)attacker);
    LivingEntity attacked = event.getEntityLiving();
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.KAGE_KAGE_NO_MI)) {
      return;
    }
    DoppelmanAbility ability = (DoppelmanAbility)abilityProps.getEquippedAbility((Ability)DoppelmanAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (!isActive) {
      return;
    }
    DoppelmanEntity doppelman = WyHelper.getEntitiesNear(attacker.getPosition(), attacker.world, 20.0D, DoppelmanEntity.class).stream().findFirst().orElse(null);
    
    if (doppelman != null && doppelman.getOwner() == attacker) {
      doppelman.forcedTargets.add(attacked);
    }
  }
  
  @SubscribeEvent
  public static void onEntityDamaged(LivingDamageEvent event) {
    if (!(event.getEntityLiving() instanceof DoppelmanEntity)) {
      return;
    }
    DoppelmanEntity entity = (DoppelmanEntity)event.getEntityLiving();
    PlayerEntity owner = entity.getOwner();
    
    if (owner == null) {
      return;
    }
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)owner);
    
    if (!props.hasDevilFruit(ModAbilities.KAGE_KAGE_NO_MI) || entity.getHealth() - event.getAmount() >= 0.0F) {
      return;
    }
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)owner);
    
    DoppelmanAbility ability = (DoppelmanAbility)abilityProps.getEquippedAbility((Ability)DoppelmanAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (!isActive) {
      return;
    }
    ability.setMaxCooldown(60.0D);
    ability.endContinuity(owner);
  }
}


