/*    */ package xyz.pixelatedw.mineminenomi.screens.config.categories;
/*    */ 
/*    */ import net.minecraft.client.settings.AbstractOption;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ModIteratableOption;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;
/*    */ 
/*    */ public class DevilFruitsConfigPage
/*    */   extends ConfigPage {
/* 15 */   private ModBooleanOption abilityGriefing = new ModBooleanOption("ability_griefing", CommonConfig.INSTANCE.abilityGriefing);
/* 16 */   private ModSliderOption devilFruitDropsFromLeavesChance = new ModSliderOption("devil_fruit_drop_from_leaves_chance", 0.0D, 100.0D, 0.1F, CommonConfig.INSTANCE.devilFruitDropsFromLeavesChance);
/* 17 */   private ModSliderOption abilityBars = new ModSliderOption("ability_bars", 1.0D, 10.0D, 1.0F, CommonConfig.INSTANCE.abilityBars);
/* 18 */   private ModBooleanOption waterChecks = new ModBooleanOption("water_checks", CommonConfig.INSTANCE.waterChecks);
/* 19 */   private ModIteratableOption logiaProjectileHitLogic = new ModIteratableOption("logia_projectile_hit_invulnerability", CommonConfig.INSTANCE.logiaProjectileHitLogic);
/* 20 */   private ModBooleanOption logiaInvulnerability = new ModBooleanOption("logia_invulnerability", CommonConfig.INSTANCE.logiaInvulnerability);
/* 21 */   private ModBooleanOption logiaReturnEffect = new ModBooleanOption("logia_return_effect", CommonConfig.INSTANCE.logiaReturnEffect);
/* 22 */   private ModBooleanOption specialFlying = new ModBooleanOption("special_flying", CommonConfig.INSTANCE.specialFlying);
/* 23 */   private ModBooleanOption specialSourceEvents = new ModBooleanOption("special_sources", CommonConfig.INSTANCE.specialSourceEvents);
/* 24 */   private ModBooleanOption yamiPower = new ModBooleanOption("yami_power", CommonConfig.INSTANCE.yamiPower);
/* 25 */   private ModBooleanOption abilityFraudChecks = new ModBooleanOption("ability_fraud_checks", CommonConfig.INSTANCE.abilityFraudChecks);
/* 26 */   private ModBooleanOption stopContinuousAbilities = new ModBooleanOption("stop_continuous_abilities", CommonConfig.INSTANCE.stopContinuousAbilities);
/* 27 */   private ModBooleanOption sharedCooldowns = new ModBooleanOption("shared_cooldowns", CommonConfig.INSTANCE.sharedCooldowns);
/* 28 */   private ModBooleanOption abilityInvulnerability = new ModBooleanOption("ability_invulnerability", CommonConfig.INSTANCE.abilityInvulnerability);
/* 29 */   private ModBooleanOption animeScreaming = new ModBooleanOption("anime_screaming", CommonConfig.INSTANCE.animeScreaming);
/* 30 */   private ModBooleanOption randomizedFruits = new ModBooleanOption("randomized_fruits", CommonConfig.INSTANCE.randomizedFruits);
/*    */   
/* 32 */   private ModIteratableOption oneFruitPerWorldLogic = new ModIteratableOption("one_fruit_per_world_logic", CommonConfig.INSTANCE.oneFruitPerWorldLogic);
/* 33 */   private ModBooleanOption unableToPickupDF = new ModBooleanOption("unable_pickup_df", CommonConfig.INSTANCE.unableToPickupDF);
/* 34 */   private ModSliderOption fruitsLimitInInventory = new ModSliderOption("fruits_limit_in_inventory", 1.0D, 10.0D, 1.0F, CommonConfig.INSTANCE.fruitsLimitInInventory);
/* 35 */   private ModSliderOption daysForInactivity = new ModSliderOption("days_for_inactivity", 1.0D, 30.0D, 1.0F, CommonConfig.INSTANCE.daysForInactivity);
/*    */   
/* 37 */   private ModSliderOption chanceForDroppedAppleReincarnation = new ModSliderOption("dropped_apple_reincarnation", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForDroppedAppleReincarnation);
/* 38 */   private ModSliderOption chanceForInventoryAppleReincarnation = new ModSliderOption("inventory_apple_reincarnation", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForInventoryAppleReincarnation);
/* 39 */   private ModSliderOption chanceForChestAppleReincarnation = new ModSliderOption("chest_apple_reincarnation", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForChestAppleReincarnation);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(ConfigCategoryList list) {
/* 45 */     list.addOption((AbstractOption)this.abilityGriefing);
/* 46 */     list.addOption((AbstractOption)this.abilityFraudChecks);
/* 47 */     list.addOption((AbstractOption)this.stopContinuousAbilities);
/* 48 */     list.addOption((AbstractOption)this.sharedCooldowns);
/* 49 */     list.addOption((AbstractOption)this.abilityBars);
/* 50 */     list.addOption((AbstractOption)this.animeScreaming);
/* 51 */     list.addOption((AbstractOption)this.randomizedFruits);
/*    */     
/* 53 */     list.addOption((AbstractOption)this.devilFruitDropsFromLeavesChance);
/*    */     
/* 55 */     list.addOption((AbstractOption)this.yamiPower);
/* 56 */     list.addOption((AbstractOption)this.abilityInvulnerability);
/* 57 */     list.addOption((AbstractOption)this.logiaInvulnerability);
/* 58 */     list.addOption((AbstractOption)this.logiaProjectileHitLogic);
/* 59 */     list.addOption((AbstractOption)this.logiaReturnEffect);
/*    */     
/* 61 */     list.addOption((AbstractOption)this.waterChecks);
/* 62 */     list.addOption((AbstractOption)this.specialFlying);
/* 63 */     list.addOption((AbstractOption)this.specialSourceEvents);
/*    */     
/* 65 */     list.addCategory("One Fruit per World");
/* 66 */     list.addOption((AbstractOption)this.oneFruitPerWorldLogic);
/* 67 */     list.addOption((AbstractOption)this.unableToPickupDF);
/* 68 */     list.addOption((AbstractOption)this.fruitsLimitInInventory);
/* 69 */     list.addOption((AbstractOption)this.daysForInactivity);
/*    */     
/* 71 */     list.addCategory("Devil Fruits Reincarnation");
/* 72 */     list.addOption((AbstractOption)this.chanceForDroppedAppleReincarnation);
/* 73 */     list.addOption((AbstractOption)this.chanceForInventoryAppleReincarnation);
/* 74 */     list.addOption((AbstractOption)this.chanceForChestAppleReincarnation);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getTitle() {
/* 80 */     return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.devil_fruits", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\categories\DevilFruitsConfigPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */