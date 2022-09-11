package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModEffects;




@EventBusSubscriber(modid = "mineminenomi")
public class DamageSourceEvents
{
  @SubscribeEvent
  public static void onEntityAttackEvent(LivingAttackEvent event) {
    if (!CommonConfig.INSTANCE.isSpecialSourceEventsEnabled()) {
      return;
    }
    Entity attacker = event.getSource().getImmediateSource();
    LivingEntity entity = event.getEntityLiving();
    
    if (attacker instanceof OPEntity)
    {
      if (((OPEntity)attacker).hasBusoHaki()) {
        
        Item mainShield = entity.getHeldItemMainhand().getItem();
        Item secondaryShield = entity.getHeldItemOffhand().getItem();
        if (entity instanceof PlayerEntity && Math.random() > 0.5D && (mainShield.equals(Items.SHIELD) || secondaryShield.equals(Items.SHIELD))) {
          
          ((PlayerEntity)entity).getCooldownTracker().setCooldown(Items.SHIELD, 100);
          entity.resetActiveHand();
          entity.world.setEntityState(attacker, (byte)30);
        } 
      } 
    }
  }


  
  @SubscribeEvent
  public static void onEntityDamageEvent(LivingAttackEvent event) {
    DamageSource source = event.getSource();
    LivingEntity entity = event.getEntityLiving();
    
    if (entity.world.isRemote || !(source instanceof xyz.pixelatedw.mineminenomi.init.ModDamageSource) || entity.isPotionActive(ModEffects.GUARDING)) {
      return;
    }
    switch (source.getDamageType()) {
      
      case "lava":
        if (!CommonConfig.INSTANCE.isSpecialSourceEventsEnabled() || !(source.getTrueSource() instanceof PlayerEntity))
          return; 
        entity.removePotionEffect(Effects.FIRE_RESISTANCE);
        break;
      case "onFire":
        if (!CommonConfig.INSTANCE.isSpecialSourceEventsEnabled() || !(source.getTrueSource() instanceof PlayerEntity)) {
          return;
        }
        AbilityHelper.reduceEffect(entity.getActivePotionEffect(ModEffects.FROZEN), 2.0D);
        AbilityHelper.reduceEffect(entity.getActivePotionEffect(Effects.FIRE_RESISTANCE), 2.0D);
        break;
    } 
  }

  
  @SubscribeEvent
  public static void onEntityHurtEvent(LivingHurtEvent event) {
    DamageSource source = event.getSource();
    LivingEntity entity = event.getEntityLiving();
    
    if (entity.world.isRemote) {
      return;
    }
    if (source.getDamageType().equals("lightningBolt") || source
      .getDamageType().equals("lava") || source
      .getDamageType().equals("onFire") || source
      .getDamageType().equals("inFire")) {

      
      if (entity.isPotionActive(ModEffects.FROZEN)) {
        
        AbilityHelper.reduceEffect(entity.getActivePotionEffect(ModEffects.FROZEN), 2.0D);
        entity.extinguish();
        
        return;
      } 
      if (entity.isPotionActive(ModEffects.FROSTBITE)) {
        
        entity.removePotionEffect(ModEffects.FROSTBITE);
        entity.extinguish();
      } 
      if (entity.isPotionActive(ModEffects.CANDLE_LOCK)) {
        
        entity.removePotionEffect(ModEffects.CANDLE_LOCK);
        entity.extinguish();
      } 
      if (entity.isPotionActive(ModEffects.CANDY_STUCK)) {
        
        entity.removePotionEffect(ModEffects.CANDY_STUCK);
        entity.extinguish();
      } 
    } 
    
    if (event.getSource().getImmediateSource() instanceof PlayerEntity) {
      
      PlayerEntity attackerPlayer = (PlayerEntity)event.getSource().getImmediateSource();
      IDevilFruit capability = DevilFruitCapability.get((LivingEntity)attackerPlayer);
      if (!capability.hasDevilFruit(ModAbilities.DOKU_DOKU_NO_MI)) {
        return;
      }
      if (attackerPlayer.isPotionActive(ModEffects.PHYSICAL_MOVING_GUARD)) {
        
        EffectInstance effect = attackerPlayer.getActivePotionEffect(ModEffects.PHYSICAL_MOVING_GUARD);
        int amplifier = 1 + effect.getAmplifier();
        entity.addPotionEffect(new EffectInstance(Effects.POISON, amplifier * 40, amplifier - 1));
      } 
    } 
  }
}


