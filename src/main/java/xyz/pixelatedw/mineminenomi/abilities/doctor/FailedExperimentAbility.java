package xyz.pixelatedw.mineminenomi.abilities.doctor;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class FailedExperimentAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new FailedExperimentAbility();

  
  public FailedExperimentAbility() {
    super("Failed Experiment", AbilityHelper.getStyleCategory());
    setMaxCooldown(7.0D);
    setMaxChargeTime(2.0D);
    setDescription("Throws a random splash potion with a debuff effect at the enemy");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    ItemStack medicBag = (ItemStack)player.inventory.armorInventory.get(2);
    boolean hasMedicBag = (medicBag.getItem() == ModArmors.MEDIC_BAG);
    
    if (!hasMedicBag) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
      return false;
    } 
    
    return true;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    ItemStack medicBag = (ItemStack)player.inventory.armorInventory.get(2);
    boolean hasMedicBag = (medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG);
    
    if (!hasMedicBag) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
      return false;
    } 
    
    PotionEntity potion = new PotionEntity(player.world, (LivingEntity)player);
    
    int potionType = (int)WyHelper.randomWithRange(0, 3);
    ItemStack stack = new ItemStack((IItemProvider)Items.SPLASH_POTION);
    
    switch (potionType) {
      
      case 0:
        stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance[] { new EffectInstance(Effects.NAUSEA, 200, 1) }));
        break;
      case 1:
        stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance[] { new EffectInstance(Effects.MINING_FATIGUE, 200, 1) }));
        break;
      case 2:
        stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance[] { new EffectInstance(Effects.POISON, 200, 1) }));
        break;
      case 3:
        stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance[] { new EffectInstance(Effects.HUNGER, 200, 1) }));
        break;
    } 
    potion.setItem(stack);
    
    potion.shoot((Entity)player, player.rotationPitch, player.rotationYaw, -20.0F, 0.8F, 1.0F);
    player.world.addEntity((Entity)potion);
    
    medicBag.damageItem(10, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    
    return true;
  }
}


