/*     */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.AbilityUseEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class OutOfBodyAbilitiesEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onAbilityUse(AbilityUseEvent event) {
/*  43 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  45 */     Optional<Ability> ability = getOOBAbility((LivingEntity)player);
/*     */ 
/*     */     
/*  48 */     if (ability != null && ability.isPresent()) {
/*     */       
/*  50 */       if (event.getAbility().equals(ability.get())) {
/*     */         return;
/*     */       }
/*     */       
/*  54 */       if (ability.get() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.YutaiRidatsuAbility)
/*     */       {
/*  56 */         if (event.getAbility() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.MiniHollowAbility || event.getAbility() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.NegativeHollowAbility || event.getAbility() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.TokuHollowAbility) {
/*     */           return;
/*     */         }
/*     */       }
/*     */     } 
/*  61 */     if (isSpirit((LivingEntity)player)) {
/*  62 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
/*  70 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  72 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/*  75 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  81 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/*     */       
/*  83 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*     */       
/*  85 */       if (player.isCreative() || player.isSpectator()) {
/*     */         return;
/*     */       }
/*  88 */       Optional<Ability> ability = getOOBAbility((LivingEntity)player);
/*  89 */       boolean isActive = (ability.isPresent() && ((Ability)ability.get()).isContinuous());
/*  90 */       boolean isPhysical = (ability.isPresent() && ((IOutOfBodyAbility)ability.get()).isPhysical());
/*     */       
/*  92 */       if (!isActive) {
/*     */         
/*  94 */         if (!isPhysical) {
/*  95 */           player.noClip = false;
/*     */         }
/*     */         return;
/*     */       } 
/*  99 */       if (!isPhysical)
/*     */       {
/* 101 */         player.onGround = false;
/* 102 */         player.noClip = true;
/*     */       }
/*     */     
/* 105 */     } else if (event.getEntityLiving() instanceof PhysicalBodyEntity) {
/*     */       
/* 107 */       PhysicalBodyEntity body = (PhysicalBodyEntity)event.getEntityLiving();
/*     */       
/* 109 */       if (AbilityHelper.isAffectedByWater((LivingEntity)body) && body.getOwner() != null) {
/*     */         
/* 111 */         Optional<Ability> ability = getOOBAbility((LivingEntity)body.getOwner());
/* 112 */         if (ability.get() instanceof ContinuousAbility) {
/* 113 */           ((ContinuousAbility)ability.get()).endContinuity(body.getOwner());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemPickup(EntityItemPickupEvent event) {
/* 121 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 123 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 126 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemTossed(ItemTossEvent event) {
/* 132 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 134 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 137 */     event.setCanceled(true);
/* 138 */     player.addItemStackToInventory(event.getEntityItem().getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityHits(AttackEntityEvent event) {
/* 144 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 146 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 149 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityLeftClickBlocks(PlayerInteractEvent.LeftClickBlock event) {
/* 155 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 157 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 160 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRightClickBlocks(PlayerInteractEvent.RightClickBlock event) {
/* 166 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 168 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 171 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityBreaksBlocks(BlockEvent.BreakEvent event) {
/* 177 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 179 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 182 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityPlaceBlocks(BlockEvent.EntityPlaceEvent event) {
/* 188 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 191 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */     
/* 193 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 196 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttackEvent(LivingAttackEvent event) {
/* 202 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 205 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */     
/* 207 */     if (isSpirit((LivingEntity)player) && event.getSource() != DamageSource.MAGIC) {
/* 208 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Optional<Ability> getOOBAbility(LivingEntity entity) {
/* 215 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/* 216 */     Optional<Ability> optional = Arrays.<Ability>stream(abilityProps.getEquippedAbilities(IOutOfBodyAbility.IS_ACTIVE)).findFirst();
/* 217 */     return optional;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSpirit(LivingEntity entity) {
/* 222 */     if (entity instanceof PlayerEntity && (((PlayerEntity)entity).isCreative() || entity.isSpectator())) {
/* 223 */       return false;
/*     */     }
/* 225 */     Optional<Ability> ability = getOOBAbility(entity);
/*     */     
/* 227 */     if (ability == null || !ability.isPresent() || ((IOutOfBodyAbility)ability.get()).isPhysical()) {
/* 228 */       return false;
/*     */     }
/* 230 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\abilities\OutOfBodyAbilitiesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */