/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class DamageSourceEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttackEvent(LivingAttackEvent event) {
/*  31 */     if (!CommonConfig.INSTANCE.isSpecialSourceEventsEnabled()) {
/*     */       return;
/*     */     }
/*  34 */     Entity attacker = event.getSource().getImmediateSource();
/*  35 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/*  37 */     if (attacker instanceof OPEntity)
/*     */     {
/*  39 */       if (((OPEntity)attacker).hasBusoHaki()) {
/*     */         
/*  41 */         Item mainShield = entity.getHeldItemMainhand().getItem();
/*  42 */         Item secondaryShield = entity.getHeldItemOffhand().getItem();
/*  43 */         if (entity instanceof PlayerEntity && Math.random() > 0.5D && (mainShield.equals(Items.SHIELD) || secondaryShield.equals(Items.SHIELD))) {
/*     */           
/*  45 */           ((PlayerEntity)entity).getCooldownTracker().setCooldown(Items.SHIELD, 100);
/*  46 */           entity.resetActiveHand();
/*  47 */           entity.world.setEntityState(attacker, (byte)30);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityDamageEvent(LivingAttackEvent event) {
/*  57 */     DamageSource source = event.getSource();
/*  58 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/*  60 */     if (entity.world.isRemote || !(source instanceof xyz.pixelatedw.mineminenomi.init.ModDamageSource) || entity.isPotionActive(ModEffects.GUARDING)) {
/*     */       return;
/*     */     }
/*  63 */     switch (source.getDamageType()) {
/*     */       
/*     */       case "lava":
/*  66 */         if (!CommonConfig.INSTANCE.isSpecialSourceEventsEnabled() || !(source.getTrueSource() instanceof PlayerEntity))
/*     */           return; 
/*  68 */         entity.removePotionEffect(Effects.FIRE_RESISTANCE);
/*     */         break;
/*     */       case "onFire":
/*  71 */         if (!CommonConfig.INSTANCE.isSpecialSourceEventsEnabled() || !(source.getTrueSource() instanceof PlayerEntity)) {
/*     */           return;
/*     */         }
/*  74 */         AbilityHelper.reduceEffect(entity.getActivePotionEffect(ModEffects.FROZEN), 2.0D);
/*  75 */         AbilityHelper.reduceEffect(entity.getActivePotionEffect(Effects.FIRE_RESISTANCE), 2.0D);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityHurtEvent(LivingHurtEvent event) {
/*  83 */     DamageSource source = event.getSource();
/*  84 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/*  86 */     if (entity.world.isRemote) {
/*     */       return;
/*     */     }
/*  89 */     if (source.getDamageType().equals("lightningBolt") || source
/*  90 */       .getDamageType().equals("lava") || source
/*  91 */       .getDamageType().equals("onFire") || source
/*  92 */       .getDamageType().equals("inFire")) {
/*     */ 
/*     */       
/*  95 */       if (entity.isPotionActive(ModEffects.FROZEN)) {
/*     */         
/*  97 */         AbilityHelper.reduceEffect(entity.getActivePotionEffect(ModEffects.FROZEN), 2.0D);
/*  98 */         entity.extinguish();
/*     */         
/*     */         return;
/*     */       } 
/* 102 */       if (entity.isPotionActive(ModEffects.FROSTBITE)) {
/*     */         
/* 104 */         entity.removePotionEffect(ModEffects.FROSTBITE);
/* 105 */         entity.extinguish();
/*     */       } 
/* 107 */       if (entity.isPotionActive(ModEffects.CANDLE_LOCK)) {
/*     */         
/* 109 */         entity.removePotionEffect(ModEffects.CANDLE_LOCK);
/* 110 */         entity.extinguish();
/*     */       } 
/* 112 */       if (entity.isPotionActive(ModEffects.CANDY_STUCK)) {
/*     */         
/* 114 */         entity.removePotionEffect(ModEffects.CANDY_STUCK);
/* 115 */         entity.extinguish();
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     if (event.getSource().getImmediateSource() instanceof PlayerEntity) {
/*     */       
/* 121 */       PlayerEntity attackerPlayer = (PlayerEntity)event.getSource().getImmediateSource();
/* 122 */       IDevilFruit capability = DevilFruitCapability.get((LivingEntity)attackerPlayer);
/* 123 */       if (!capability.hasDevilFruit(ModAbilities.DOKU_DOKU_NO_MI)) {
/*     */         return;
/*     */       }
/* 126 */       if (attackerPlayer.isPotionActive(ModEffects.PHYSICAL_MOVING_GUARD)) {
/*     */         
/* 128 */         EffectInstance effect = attackerPlayer.getActivePotionEffect(ModEffects.PHYSICAL_MOVING_GUARD);
/* 129 */         int amplifier = 1 + effect.getAmplifier();
/* 130 */         entity.addPotionEffect(new EffectInstance(Effects.POISON, amplifier * 40, amplifier - 1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\DamageSourceEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */