/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class HandcuffsEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityDeath(LivingDeathEvent event) {
/* 28 */     LivingEntity entity = event.getEntityLiving();
/* 29 */     if (entity.isPotionActive(ModEffects.HANDCUFFED)) {
/* 30 */       entity.entityDropItem(new ItemStack((IItemProvider)ModItems.NORMAL_HANDCUFFS));
/* 31 */     } else if (entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
/* 32 */       entity.entityDropItem(new ItemStack((IItemProvider)ModItems.KAIROSEKI_HANDCUFFS));
/*    */     } 
/*    */   }
/*    */   @SubscribeEvent
/*    */   public static void onEntityHurt(LivingHurtEvent event) {
/*    */     EffectInstance instance;
/* 38 */     Entity source = event.getSource().getTrueSource();
/*    */     
/* 40 */     if (!(source instanceof PlayerEntity) || event.getAmount() <= 0.0F || event.getEntityLiving().getHealth() - event.getAmount() > 0.0F) {
/*    */       return;
/*    */     }
/* 43 */     PlayerEntity playerSource = (PlayerEntity)source;
/*    */     
/* 45 */     ItemStack offhand = playerSource.getHeldItemOffhand();
/*    */     
/* 47 */     if (offhand.isEmpty() || !(offhand.getItem() instanceof xyz.pixelatedw.mineminenomi.items.HandcuffsItem)) {
/*    */       return;
/*    */     }
/*    */     
/* 51 */     int diff = 600 + EntityStatsCapability.get((LivingEntity)playerSource).getDoriki() - EntityStatsCapability.get(event.getEntityLiving()).getDoriki();
/*    */     
/* 53 */     event.getEntityLiving().setHealth(2.0F);
/*    */     
/* 55 */     if (offhand.getItem() == ModItems.NORMAL_HANDCUFFS) {
/* 56 */       instance = new EffectInstance(ModEffects.HANDCUFFED, diff * 2, 0, false, true);
/* 57 */     } else if (offhand.getItem() == ModItems.KAIROSEKI_HANDCUFFS) {
/* 58 */       instance = new EffectInstance(ModEffects.HANDCUFFED_KAIROSEKI, diff * 2, 0, false, true);
/*    */     } else {
/* 60 */       instance = new EffectInstance(ModEffects.HANDCUFFED, diff * 2, 0, false, true);
/*    */     } 
/* 62 */     event.getEntityLiving().addPotionEffect(instance);
/* 63 */     event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int)(diff * 1.2D), 1, false, false));
/* 64 */     event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.RESISTANCE, 5, 4, false, false));
/* 65 */     if (playerSource instanceof ServerPlayerEntity)
/* 66 */       ((ServerPlayerEntity)playerSource).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(event.getEntityLiving().getEntityId(), instance)); 
/* 67 */     offhand.shrink(1);
/* 68 */     event.setCanceled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
/* 74 */     if (!(event.getTarget() instanceof LivingEntity)) {
/*    */       return;
/*    */     }
/* 77 */     PlayerEntity player = event.getPlayer();
/*    */     
/* 79 */     if (player.getHeldItemMainhand().getItem() != ModItems.KEY) {
/*    */       return;
/*    */     }
/* 82 */     LivingEntity target = (LivingEntity)event.getTarget();
/*    */     
/* 84 */     if (target.isPotionActive(ModEffects.HANDCUFFED) || target.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
/*    */       
/* 86 */       ItemStack keyStack = player.getHeldItemMainhand();
/* 87 */       keyStack.shrink(1);
/* 88 */       target.removeActivePotionEffect(ModEffects.HANDCUFFED);
/* 89 */       target.removeActivePotionEffect(ModEffects.HANDCUFFED_KAIROSEKI);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\items\HandcuffsEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */