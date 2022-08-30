/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Food;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kuku.GourmetamorphosisAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class KukuPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onItemRightClicked(PlayerInteractEvent.RightClickItem event) {
/* 32 */     PlayerEntity player = event.getPlayer();
/* 33 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*    */     
/* 35 */     if (!props.hasDevilFruit(ModAbilities.KUKU_KUKU_NO_MI)) {
/*    */       return;
/*    */     }
/* 38 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 40 */     GourmetamorphosisAbility ability = (GourmetamorphosisAbility)abilityProps.getEquippedAbility((Ability)GourmetamorphosisAbility.INSTANCE);
/* 41 */     boolean hasAbilityActive = (ability != null && ability.isContinuous());
/*    */     
/* 43 */     if (hasAbilityActive) {
/*    */       
/* 45 */       ItemStack stack = event.getItemStack();
/* 46 */       int foodlevel = 2;
/* 47 */       float saturation = 0.25F;
/*    */       
/* 49 */       if (stack.getItem() instanceof xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem) {
/*    */         return;
/*    */       }
/* 52 */       if (stack.isFood()) {
/*    */         
/* 54 */         Food food = stack.getItem().getFood();
/* 55 */         foodlevel += food.getHealing();
/* 56 */         saturation += food.getSaturation();
/*    */       } 
/*    */       
/* 59 */       player.sendBreakAnimation(Hand.MAIN_HAND);
/* 60 */       player.getFoodStats().addStats(foodlevel, saturation);
/* 61 */       stack.shrink(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static void onEdibleCheck(ItemTooltipEvent event) {
/* 69 */     PlayerEntity player = event.getPlayer();
/*    */     
/* 71 */     if (player == null) {
/*    */       return;
/*    */     }
/* 74 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*    */     
/* 76 */     if (player == null || !props.hasDevilFruit(ModAbilities.KUKU_KUKU_NO_MI)) {
/*    */       return;
/*    */     }
/* 79 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 81 */     GourmetamorphosisAbility ability = (GourmetamorphosisAbility)abilityProps.getEquippedAbility((Ability)GourmetamorphosisAbility.INSTANCE);
/* 82 */     boolean hasAbilityActive = (ability != null && ability.isContinuous());
/*    */     
/* 84 */     if (hasAbilityActive) {
/*    */       
/* 86 */       if (event.getItemStack().getItem() instanceof xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem) {
/*    */         return;
/*    */       }
/* 89 */       StringTextComponent foodString = new StringTextComponent(TextFormatting.YELLOW + "" + (new TranslationTextComponent(ModI18n.ITEM_GOURMETAMORPHOSIS_FOOD, new Object[0])).getFormattedText());
/* 90 */       if (!event.getToolTip().contains(foodString)) {
/*    */         
/* 92 */         event.getToolTip().add(new StringTextComponent(""));
/* 93 */         event.getToolTip().add(foodString);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\KukuPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */