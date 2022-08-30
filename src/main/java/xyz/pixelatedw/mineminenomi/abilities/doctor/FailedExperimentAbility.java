/*    */ package xyz.pixelatedw.mineminenomi.abilities.doctor;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.projectile.PotionEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.potion.PotionUtils;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class FailedExperimentAbility extends ChargeableAbility {
/* 23 */   public static final Ability INSTANCE = (Ability)new FailedExperimentAbility();
/*    */ 
/*    */   
/*    */   public FailedExperimentAbility() {
/* 27 */     super("Failed Experiment", AbilityHelper.getStyleCategory());
/* 28 */     setMaxCooldown(7.0D);
/* 29 */     setMaxChargeTime(2.0D);
/* 30 */     setDescription("Throws a random splash potion with a debuff effect at the enemy");
/*    */     
/* 32 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 33 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 38 */     ItemStack medicBag = (ItemStack)player.inventory.armorInventory.get(2);
/* 39 */     boolean hasMedicBag = (medicBag.getItem() == ModArmors.MEDIC_BAG);
/*    */     
/* 41 */     if (!hasMedicBag) {
/*    */       
/* 43 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
/* 44 */       return false;
/*    */     } 
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 52 */     ItemStack medicBag = (ItemStack)player.inventory.armorInventory.get(2);
/* 53 */     boolean hasMedicBag = (medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG);
/*    */     
/* 55 */     if (!hasMedicBag) {
/*    */       
/* 57 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
/* 58 */       return false;
/*    */     } 
/*    */     
/* 61 */     PotionEntity potion = new PotionEntity(player.world, (LivingEntity)player);
/*    */     
/* 63 */     int potionType = (int)WyHelper.randomWithRange(0, 3);
/* 64 */     ItemStack stack = new ItemStack((IItemProvider)Items.SPLASH_POTION);
/*    */     
/* 66 */     switch (potionType) {
/*    */       
/*    */       case 0:
/* 69 */         stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance[] { new EffectInstance(Effects.NAUSEA, 200, 1) }));
/*    */         break;
/*    */       case 1:
/* 72 */         stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance[] { new EffectInstance(Effects.MINING_FATIGUE, 200, 1) }));
/*    */         break;
/*    */       case 2:
/* 75 */         stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance[] { new EffectInstance(Effects.POISON, 200, 1) }));
/*    */         break;
/*    */       case 3:
/* 78 */         stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance[] { new EffectInstance(Effects.HUNGER, 200, 1) }));
/*    */         break;
/*    */     } 
/* 81 */     potion.setItem(stack);
/*    */     
/* 83 */     potion.shoot((Entity)player, player.rotationPitch, player.rotationYaw, -20.0F, 0.8F, 1.0F);
/* 84 */     player.world.addEntity((Entity)potion);
/*    */     
/* 86 */     medicBag.damageItem(10, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */     
/* 88 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\FailedExperimentAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */