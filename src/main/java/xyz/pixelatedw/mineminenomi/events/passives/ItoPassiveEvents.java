package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.ito.BlackKnightAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


@EventBusSubscriber(modid = "mineminenomi")
public class ItoPassiveEvents
{
  @SubscribeEvent
  public static void onEntityAttack(LivingHurtEvent event) {
    if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity attacker = (PlayerEntity)event.getSource().getTrueSource();
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)attacker);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)attacker);
    LivingEntity attacked = event.getEntityLiving();
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.ITO_ITO_NO_MI)) {
      return;
    }
    BlackKnightAbility ability = (BlackKnightAbility)abilityProps.getEquippedAbility((Ability)BlackKnightAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (!isActive) {
      return;
    }
    BlackKnightEntity knight = WyHelper.getEntitiesNear(attacker.getPosition(), attacker.world, 20.0D,  BlackKnightEntity.class ).stream().findFirst().orElse(null);
    
    if (knight != null && knight.getOwner() == attacker) {
      knight.forcedTargets.add(attacked);
    }
  }
  
  @SubscribeEvent
  public static void onEntityDamaged(LivingDamageEvent event) {
    if (!(event.getEntityLiving() instanceof BlackKnightEntity)) {
      return;
    }
    BlackKnightEntity entity = (BlackKnightEntity)event.getEntityLiving();
    PlayerEntity owner = entity.getOwner();
    
    if (owner == null) {
      return;
    }
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)owner);
    
    if (!props.hasDevilFruit(ModAbilities.ITO_ITO_NO_MI) || entity.getHealth() - event.getAmount() >= 0.0F) {
      return;
    }
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)owner);
    
    BlackKnightAbility ability = (BlackKnightAbility)abilityProps.getEquippedAbility((Ability)BlackKnightAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (!isActive) {
      return;
    }
    ability.setMaxCooldown(60.0D);
    ability.endContinuity(owner);
  }
}


