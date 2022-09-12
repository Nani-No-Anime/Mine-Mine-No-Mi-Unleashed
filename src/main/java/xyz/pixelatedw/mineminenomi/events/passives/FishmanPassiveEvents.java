package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.UUID;



public class FishmanPassiveEvents
{
  @EventBusSubscriber(modid = "mineminenomi")
  public static class CommonEvents
  {
    private static final AttributeModifier SWIM_SPEED = (new AttributeModifier(UUID.fromString("a11440ee-5d84-4c36-960b-992e13b66aff"), "Fishman Speed Multiplier", 1.8D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);

    
    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
      IEntityStats statsProps = EntityStatsCapability.get(event.getEntityLiving());
      
      if (!statsProps.isFishman() || !event.getEntityLiving().isInWater()) {
        return;
      }
      event.getEntityLiving().setAir(300);
      
      if (!(event.getEntityLiving() instanceof PlayerEntity)) {
        return;
      }
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
      
      if (AbilityHelper.isAffectedByWater((LivingEntity)player) && (!devilFruitProps.hasDevilFruit() || event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL))) {
        
        if (player.getRidingEntity() == null)
          player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 50, 0, false, false)); 
        if (!player.getAttribute(LivingEntity.SWIM_SPEED).hasModifier(SWIM_SPEED)) {
          player.getAttribute(LivingEntity.SWIM_SPEED).applyModifier(SWIM_SPEED);
        }
      }
      else if (player.getAttribute(LivingEntity.SWIM_SPEED).hasModifier(SWIM_SPEED)) {
        player.getAttribute(LivingEntity.SWIM_SPEED).removeModifier(SWIM_SPEED);
      } 
      
      Ability samehadaAbility = abilityProps.getEquippedAbility(SamehadaShoteiAbility.INSTANCE);
      boolean isSamehadaActive = (samehadaAbility != null && samehadaAbility.isContinuous());
      if (isSamehadaActive) {
        player.setMotion(0.0D, -5.0D, 0.0D);
      }
    }
    
    @SubscribeEvent
    public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
      PlayerEntity player = event.getPlayer();
      IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
      IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
      
      if (!statsProps.isFishman()) {
        return;
      }
      float speed = event.getOriginalSpeed();
      if (AbilityHelper.isAffectedByWater((LivingEntity)player) && (!devilFruitProps.hasDevilFruit() || event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL))) {
        if (!player.onGround)
          speed *= 5.0F; 
        speed *= 5.0F;
        event.setNewSpeed(speed);
      } 
    }

    
    @SubscribeEvent
    public static void onPotionEvent(PotionEvent.PotionApplicableEvent event) {
      if (!(event.getEntityLiving() instanceof PlayerEntity))
        return; 
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
      
      if (!statsProps.isFishman()) {
        return;
      }
      if (event.getPotionEffect().getPotion().equals(Effects.DOLPHINS_GRACE)) {
        event.setResult(Event.Result.DENY);
      }
    }
  }
  
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class ClientEvents
  {
    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent event) {
      Minecraft mc = Minecraft.getInstance();
      ClientPlayerEntity clientPlayerEntity = mc.player;
      IEntityStats props = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
      
      if (event.getType() == RenderGameOverlayEvent.ElementType.AIR && props.isFishman() && clientPlayerEntity.isInWater()) {
        event.setCanceled(true);
      }
    }
    
    @SubscribeEvent
    public static void onEntityInWater(EntityViewRenderEvent.FogDensity event) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
      
      if (!statsProps.isFishman()) {
        return;
      }
      if (clientPlayerEntity.areEyesInFluid(FluidTags.WATER)) {
        
        event.setCanceled(true);
        event.setDensity(0.005F);
      } 
    }
  }
}


