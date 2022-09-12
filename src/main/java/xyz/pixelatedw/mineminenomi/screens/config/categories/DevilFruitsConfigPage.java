package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.*;

public class DevilFruitsConfigPage
  extends ConfigPage {
  private ModBooleanOption abilityGriefing = new ModBooleanOption("ability_griefing", CommonConfig.INSTANCE.abilityGriefing);
  private ModSliderOption devilFruitDropsFromLeavesChance = new ModSliderOption("devil_fruit_drop_from_leaves_chance", 0.0D, 100.0D, 0.1F, CommonConfig.INSTANCE.devilFruitDropsFromLeavesChance);
  private ModSliderOption abilityBars = new ModSliderOption("ability_bars", 1.0D, 10.0D, 1.0F, CommonConfig.INSTANCE.abilityBars);
  private ModBooleanOption waterChecks = new ModBooleanOption("water_checks", CommonConfig.INSTANCE.waterChecks);
  private ModIteratableOption logiaProjectileHitLogic = new ModIteratableOption("logia_projectile_hit_invulnerability", CommonConfig.INSTANCE.logiaProjectileHitLogic);
  private ModBooleanOption logiaInvulnerability = new ModBooleanOption("logia_invulnerability", CommonConfig.INSTANCE.logiaInvulnerability);
  private ModBooleanOption logiaReturnEffect = new ModBooleanOption("logia_return_effect", CommonConfig.INSTANCE.logiaReturnEffect);
  private ModBooleanOption specialFlying = new ModBooleanOption("special_flying", CommonConfig.INSTANCE.specialFlying);
  private ModBooleanOption specialSourceEvents = new ModBooleanOption("special_sources", CommonConfig.INSTANCE.specialSourceEvents);
  private ModBooleanOption yamiPower = new ModBooleanOption("yami_power", CommonConfig.INSTANCE.yamiPower);
  private ModBooleanOption abilityFraudChecks = new ModBooleanOption("ability_fraud_checks", CommonConfig.INSTANCE.abilityFraudChecks);
  private ModBooleanOption stopContinuousAbilities = new ModBooleanOption("stop_continuous_abilities", CommonConfig.INSTANCE.stopContinuousAbilities);
  private ModBooleanOption sharedCooldowns = new ModBooleanOption("shared_cooldowns", CommonConfig.INSTANCE.sharedCooldowns);
  private ModBooleanOption abilityInvulnerability = new ModBooleanOption("ability_invulnerability", CommonConfig.INSTANCE.abilityInvulnerability);
  private ModBooleanOption animeScreaming = new ModBooleanOption("anime_screaming", CommonConfig.INSTANCE.animeScreaming);
  private ModBooleanOption randomizedFruits = new ModBooleanOption("randomized_fruits", CommonConfig.INSTANCE.randomizedFruits);
  
  private ModIteratableOption oneFruitPerWorldLogic = new ModIteratableOption("one_fruit_per_world_logic", CommonConfig.INSTANCE.oneFruitPerWorldLogic);
  private ModBooleanOption unableToPickupDF = new ModBooleanOption("unable_pickup_df", CommonConfig.INSTANCE.unableToPickupDF);
  private ModSliderOption fruitsLimitInInventory = new ModSliderOption("fruits_limit_in_inventory", 1.0D, 10.0D, 1.0F, CommonConfig.INSTANCE.fruitsLimitInInventory);
  private ModSliderOption daysForInactivity = new ModSliderOption("days_for_inactivity", 1.0D, 30.0D, 1.0F, CommonConfig.INSTANCE.daysForInactivity);
  
  private ModSliderOption chanceForDroppedAppleReincarnation = new ModSliderOption("dropped_apple_reincarnation", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForDroppedAppleReincarnation);
  private ModSliderOption chanceForInventoryAppleReincarnation = new ModSliderOption("inventory_apple_reincarnation", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForInventoryAppleReincarnation);
  private ModSliderOption chanceForChestAppleReincarnation = new ModSliderOption("chest_apple_reincarnation", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForChestAppleReincarnation);



  
  public void init(ConfigCategoryList list) {
    list.addOption((AbstractOption)this.abilityGriefing);
    list.addOption((AbstractOption)this.abilityFraudChecks);
    list.addOption((AbstractOption)this.stopContinuousAbilities);
    list.addOption((AbstractOption)this.sharedCooldowns);
    list.addOption((AbstractOption)this.abilityBars);
    list.addOption((AbstractOption)this.animeScreaming);
    list.addOption((AbstractOption)this.randomizedFruits);
    
    list.addOption((AbstractOption)this.devilFruitDropsFromLeavesChance);
    
    list.addOption((AbstractOption)this.yamiPower);
    list.addOption((AbstractOption)this.abilityInvulnerability);
    list.addOption((AbstractOption)this.logiaInvulnerability);
    list.addOption((AbstractOption)this.logiaProjectileHitLogic);
    list.addOption((AbstractOption)this.logiaReturnEffect);
    
    list.addOption((AbstractOption)this.waterChecks);
    list.addOption((AbstractOption)this.specialFlying);
    list.addOption((AbstractOption)this.specialSourceEvents);
    
    list.addCategory("One Fruit per World");
    list.addOption((AbstractOption)this.oneFruitPerWorldLogic);
    list.addOption((AbstractOption)this.unableToPickupDF);
    list.addOption((AbstractOption)this.fruitsLimitInInventory);
    list.addOption((AbstractOption)this.daysForInactivity);
    
    list.addCategory("Devil Fruits Reincarnation");
    list.addOption((AbstractOption)this.chanceForDroppedAppleReincarnation);
    list.addOption((AbstractOption)this.chanceForInventoryAppleReincarnation);
    list.addOption((AbstractOption)this.chanceForChestAppleReincarnation);
  }


  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.devil_fruits", new Object[0]);
  }
}


