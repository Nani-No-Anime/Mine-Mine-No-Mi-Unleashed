/*     */ package xyz.pixelatedw.mineminenomi.events.items;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.FOVUpdateEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.GunArrayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.ColaBackpackBonusAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class CombatItemEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityHurt(LivingHurtEvent event) {
/*  38 */     Entity source = event.getSource().getImmediateSource();
/*  39 */     if (source == null) {
/*     */       return;
/*     */     }
/*  42 */     if (source instanceof LivingEntity) {
/*     */       
/*  44 */       LivingEntity entity = (LivingEntity)event.getSource().getImmediateSource();
/*  45 */       ItemStack stack = entity.getHeldItemMainhand();
/*  46 */       if (stack != null && !stack.isEmpty() && stack.hasTag() && stack.getTag().getBoolean("isClone")) {
/*  47 */         event.setAmount(event.getAmount() / 1.25F);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEquipmentUpdate(LivingEquipmentChangeEvent event) {
/*  54 */     if (!(event.getEntityLiving() instanceof PlayerEntity))
/*     */       return; 
/*  56 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  57 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  58 */     IEntityStats stats = EntityStatsCapability.get((LivingEntity)player);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     boolean flag = false;
/*     */     
/*  65 */     if (event.getSlot() == EquipmentSlotType.CHEST) {
/*     */       
/*  67 */       flag |= checkForCombatItem(player, event.getFrom(), event.getTo(), ModArmors.WOOTZ_STEEL_ARMOR, (Ability)GunArrayAbility.INSTANCE);
/*  68 */       boolean colaBackpackCheck = checkForCombatItem(player, event.getFrom(), event.getTo(), ModArmors.COLA_BACKPACK, (Ability)ColaBackpackBonusAbility.INSTANCE);
/*     */       
/*  70 */       flag |= colaBackpackCheck;
/*  71 */       if (colaBackpackCheck) {
/*     */         
/*  73 */         if (props.hasUnlockedAbility((Ability)ColaBackpackBonusAbility.INSTANCE)) {
/*  74 */           stats.alterMaxCola(400);
/*     */         } else {
/*  76 */           stats.alterMaxCola(-400);
/*  77 */         }  WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), stats), player);
/*     */       } 
/*     */     } 
/*     */     
/*  81 */     if (event.getSlot() == EquipmentSlotType.HEAD)
/*     */     {
/*  83 */       flag |= checkForCombatItem(player, event.getFrom(), event.getTo(), ModArmors.SNIPER_GOGGLES, (Ability)ZoomAbility.INSTANCE);
/*     */     }
/*     */     
/*  86 */     if (flag) {
/*  87 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean checkForCombatItem(PlayerEntity player, ItemStack from, ItemStack to, Item itemCheck, Ability ability) {
/*  92 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  93 */     boolean flag = false;
/*     */     
/*  95 */     if (to.getItem() == itemCheck && !props.hasUnlockedAbility(ability)) {
/*     */       
/*  97 */       props.addUnlockedAbility(ability);
/*  98 */       flag = true;
/*     */     }
/* 100 */     else if (from.getItem() == itemCheck && props.hasUnlockedAbility(ability)) {
/*     */       
/* 102 */       Ability abl = props.getUnlockedAbility(ability);
/* 103 */       if (abl instanceof ContinuousAbility)
/* 104 */         ((ContinuousAbility)abl).endContinuity(player); 
/* 105 */       props.removeUnlockedAbility(ability);
/* 106 */       props.removeEquippedAbility(ability);
/* 107 */       flag = true;
/*     */     } 
/*     */     
/* 110 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void updateZoom(FOVUpdateEvent event) {
/* 117 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 118 */     if (clientPlayerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() != ModArmors.SNIPER_GOGGLES) {
/*     */       return;
/*     */     }
/* 121 */     IAbilityData aprops = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*     */     
/* 123 */     if (aprops.getEquippedAbility((Ability)ZoomAbility.INSTANCE) != null) {
/*     */       
/* 125 */       Ability ability = aprops.getEquippedAbility((Ability)ZoomAbility.INSTANCE);
/*     */       
/* 127 */       if (ability.isContinuous())
/* 128 */         event.setNewfov(0.01F); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\items\CombatItemEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */