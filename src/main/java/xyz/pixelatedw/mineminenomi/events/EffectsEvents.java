/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*     */ import net.minecraftforge.client.event.InputEvent;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.living.PotionEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IIgnoreMilkEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.effects.GuardingEffect;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSetEffectDetailsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class EffectsEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class Common {
/*     */     @SubscribeEvent
/*     */     public static void onDrinkMilk(PotionEvent.PotionRemoveEvent event) {
/*  37 */       if (event.getEntityLiving().getActiveHand() == null) {
/*     */         return;
/*     */       }
/*  40 */       boolean isMilkBucket = (event.getEntityLiving().getHeldItem(event.getEntityLiving().getActiveHand()).getItem() == Items.MILK_BUCKET);
/*     */       
/*  42 */       if (isMilkBucket)
/*     */       {
/*  44 */         if (event.getPotion() instanceof IIgnoreMilkEffect && !((IIgnoreMilkEffect)event.getPotion()).isRemoveable()) {
/*  45 */           event.setCanceled(true);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityHurt(LivingHurtEvent event) {
/*  52 */       LivingEntity entity = event.getEntityLiving();
/*  53 */       Entity source = event.getSource().getTrueSource();
/*  54 */       for (EffectInstance effectInstance : entity.getActivePotionEffects()) {
/*     */         
/*  56 */         if (effectInstance.getPotion() instanceof GuardingEffect) {
/*     */           
/*  58 */           GuardingEffect instance = (GuardingEffect)effectInstance.getPotion();
/*  59 */           if (instance.useOnlySources && !instance.acceptableSources.contains(event.getSource().getDamageType())) {
/*     */             return;
/*     */           }
/*  62 */           if (event.getSource().isDamageAbsolute()) {
/*     */             return;
/*     */           }
/*  65 */           if (instance.reduceSpeedAfterHit) {
/*  66 */             Objects.requireNonNull(entity); entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 1, false, false));
/*     */           } 
/*  68 */           float power = (effectInstance.getAmplifier() + 1) * 2.5F;
/*  69 */           power *= event.getSource().isUnblockable() ? 0.8F : 1.0F;
/*     */           
/*  71 */           if (event.getAmount() < power) {
/*     */             
/*  73 */             entity.setMotion(0.0D, 0.0D, 0.0D);
/*  74 */             event.setAmount(0.0F);
/*  75 */             event.setCanceled(true);
/*     */           } else {
/*  77 */             event.setAmount(event.getAmount() - power);
/*     */           } 
/*     */           return;
/*     */         } 
/*  81 */         if (effectInstance.getPotion() instanceof xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect) {
/*     */           
/*  83 */           int dur = effectInstance.getDuration();
/*  84 */           double damage = event.getAmount();
/*     */           
/*  86 */           int newDur = (int)(dur - damage * 100.0D);
/*  87 */           ((EffectInstanceMixin)effectInstance).setDuration(newDur);
/*  88 */           WyNetwork.sendToAllTrackingAndSelf(new SSetEffectDetailsPacket(entity.getEntityId(), effectInstance), entity);
/*     */           
/*  90 */           if (effectInstance.getDuration() <= 1)
/*     */           {
/*  92 */             if (entity instanceof PlayerEntity) {
/*     */               
/*  94 */               PlayerEntity player = (PlayerEntity)entity;
/*  95 */               AbilityHelper.enableAbilities(player, ability -> (ability.getCategory() == AbilityHelper.getDevilFruitCategory()));
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onMouseClicked(InputEvent.ClickInputEvent event) {
/* 110 */       if (event.getHand() == Hand.MAIN_HAND) {
/*     */         
/* 112 */         ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*     */ 
/*     */ 
/*     */         
/* 116 */         Objects.requireNonNull(IBindHandsEffect.class); clientPlayerEntity.getActivePotionEffects().stream().map(EffectInstance::getPotion).filter(IBindHandsEffect.class::isInstance)
/* 117 */           .filter(eff -> ((IBindHandsEffect)eff).isBlockingSwings())
/* 118 */           .forEach(eff -> {
/*     */               event.setCanceled(true);
/*     */               event.setSwingHand(false);
/*     */             });
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onHandRendering(RenderHandEvent event) {
/* 129 */       if (event.getHand() != Hand.MAIN_HAND) {
/*     */         return;
/*     */       }
/* 132 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*     */       
/* 134 */       if (clientPlayerEntity.isPotionActive(ModEffects.NO_HANDS)) {
/* 135 */         event.setCanceled(true);
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onShocked(EntityViewRenderEvent.CameraSetup event) {
/* 141 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*     */       
/* 143 */       if (!clientPlayerEntity.isPotionActive(ModEffects.DIZZY)) {
/*     */         return;
/*     */       }
/* 146 */       if (clientPlayerEntity.getActivePotionEffect(ModEffects.DIZZY).getDuration() <= 0) {
/*     */         
/* 148 */         clientPlayerEntity.removePotionEffect(ModEffects.DIZZY);
/*     */         
/*     */         return;
/*     */       } 
/* 152 */       if ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0)
/*     */       {
/* 154 */         event.setRoll((float)Math.sin((((PlayerEntity)clientPlayerEntity).ticksExisted % 100)));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\EffectsEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */