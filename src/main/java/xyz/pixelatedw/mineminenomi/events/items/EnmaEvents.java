/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class EnmaEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 26 */     if (!(event.getEntityLiving() instanceof PlayerEntity) || (event.getEntityLiving()).world.isRemote) {
/*    */       return;
/*    */     }
/* 29 */     ItemStack stack = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.MAINHAND);
/*    */     
/* 31 */     if (stack.getItem() != ModWeapons.ENMA || !stack.hasTag()) {
/*    */       return;
/*    */     }
/* 34 */     if (stack.getTag().getBoolean("isClone")) {
/*    */       return;
/*    */     }
/* 37 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 38 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/* 39 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 40 */     float imbuingExp = hakiProps.getBusoshokuImbuingHakiExp();
/*    */     
/* 42 */     if (imbuingExp < 20.0F) {
/* 43 */       player.attackEntityFrom(DamageSource.WITHER, player.getMaxHealth());
/* 44 */     } else if (imbuingExp >= 20.0F && imbuingExp < 30.0F) {
/*    */       
/* 46 */       player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1, false, false));
/* 47 */       player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1, false, false));
/* 48 */       if (!player.isPotionActive(Effects.WITHER)) {
/* 49 */         player.addPotionEffect(new EffectInstance(Effects.WITHER, 100, 1, false, false));
/*    */       }
/* 51 */     } else if (imbuingExp >= 30.0F) {
/*    */       
/* 53 */       if (abilityProps.hasEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE)) {
/*    */         
/* 55 */         BusoshokuHakiImbuingAbility abl = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/*    */         
/* 57 */         if (abl.isDisabled()) {
/*    */           
/* 59 */           player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0, false, false));
/* 60 */           player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 0, false, false));
/*    */         } 
/*    */         
/* 63 */         if (abl.isOnCooldown() || !abl.isContinuous())
/*    */         {
/*    */           
/* 66 */           abl.setMaxCooldown(0.0D);
/* 67 */           abl.use(player);
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 72 */         player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 1, false, false));
/* 73 */         player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 1, false, false));
/* 74 */         if (!player.isPotionActive(Effects.WITHER))
/* 75 */           player.addPotionEffect(new EffectInstance(Effects.WITHER, 100, 2, false, false)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\items\EnmaEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */