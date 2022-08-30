/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.PotionEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FishmanPassiveEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class CommonEvents
/*     */   {
/*  39 */     private static final AttributeModifier SWIM_SPEED = (new AttributeModifier(UUID.fromString("a11440ee-5d84-4c36-960b-992e13b66aff"), "Fishman Speed Multiplier", 1.8D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  44 */       IEntityStats statsProps = EntityStatsCapability.get(event.getEntityLiving());
/*     */       
/*  46 */       if (!statsProps.isFishman() || !event.getEntityLiving().isInWater()) {
/*     */         return;
/*     */       }
/*  49 */       event.getEntityLiving().setAir(300);
/*     */       
/*  51 */       if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/*  54 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  55 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  56 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/*  58 */       if (AbilityHelper.isAffectedByWater((LivingEntity)player) && (!devilFruitProps.hasDevilFruit() || event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL))) {
/*     */         
/*  60 */         if (player.getRidingEntity() == null)
/*  61 */           player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 50, 0, false, false)); 
/*  62 */         if (!player.getAttribute(LivingEntity.SWIM_SPEED).hasModifier(SWIM_SPEED)) {
/*  63 */           player.getAttribute(LivingEntity.SWIM_SPEED).applyModifier(SWIM_SPEED);
/*     */         }
/*     */       }
/*  66 */       else if (player.getAttribute(LivingEntity.SWIM_SPEED).hasModifier(SWIM_SPEED)) {
/*  67 */         player.getAttribute(LivingEntity.SWIM_SPEED).removeModifier(SWIM_SPEED);
/*     */       } 
/*     */       
/*  70 */       Ability samehadaAbility = abilityProps.getEquippedAbility(SamehadaShoteiAbility.INSTANCE);
/*  71 */       boolean isSamehadaActive = (samehadaAbility != null && samehadaAbility.isContinuous());
/*  72 */       if (isSamehadaActive) {
/*  73 */         player.setMotion(0.0D, -5.0D, 0.0D);
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
/*  79 */       PlayerEntity player = event.getPlayer();
/*  80 */       IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
/*  81 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*     */       
/*  83 */       if (!statsProps.isFishman()) {
/*     */         return;
/*     */       }
/*  86 */       float speed = event.getOriginalSpeed();
/*  87 */       if (AbilityHelper.isAffectedByWater((LivingEntity)player) && (!devilFruitProps.hasDevilFruit() || event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL))) {
/*  88 */         if (!player.onGround)
/*  89 */           speed *= 5.0F; 
/*  90 */         speed *= 5.0F;
/*  91 */         event.setNewSpeed(speed);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPotionEvent(PotionEvent.PotionApplicableEvent event) {
/*  98 */       if (!(event.getEntityLiving() instanceof PlayerEntity))
/*     */         return; 
/* 100 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 101 */       IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
/*     */       
/* 103 */       if (!statsProps.isFishman()) {
/*     */         return;
/*     */       }
/* 106 */       if (event.getPotionEffect().getPotion().equals(Effects.DOLPHINS_GRACE)) {
/* 107 */         event.setResult(Event.Result.DENY);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class ClientEvents
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onRenderOverlay(RenderGameOverlayEvent event) {
/* 117 */       Minecraft mc = Minecraft.getInstance();
/* 118 */       ClientPlayerEntity clientPlayerEntity = mc.player;
/* 119 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/* 121 */       if (event.getType() == RenderGameOverlayEvent.ElementType.AIR && props.isFishman() && clientPlayerEntity.isInWater()) {
/* 122 */         event.setCanceled(true);
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityInWater(EntityViewRenderEvent.FogDensity event) {
/* 128 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 129 */       IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/* 131 */       if (!statsProps.isFishman()) {
/*     */         return;
/*     */       }
/* 134 */       if (clientPlayerEntity.areEyesInFluid(FluidTags.WATER)) {
/*     */         
/* 136 */         event.setCanceled(true);
/* 137 */         event.setDensity(0.005F);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\FishmanPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */