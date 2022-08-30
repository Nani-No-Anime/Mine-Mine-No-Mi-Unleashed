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
/*    */ public class GeneralConfigPage
/*    */   extends ConfigPage
/*    */ {
/*    */   private static double dorikiRewardMultiplierValue;
/* 17 */   private ModSliderOption dorikiRewardMultiplier = new ModSliderOption("doriki_reward_multiplier", 0.0D, 10.0D, 0.2F, CommonConfig.INSTANCE.dorikiRewardMultiplier);
/* 18 */   private ModSliderOption hakiExpMultiplier = new ModSliderOption("haki_exp_multiplier", 0.0D, 10.0D, 0.2F, CommonConfig.INSTANCE.hakiExpMultiplier);
/* 19 */   private ModSliderOption dorikiLimit = new ModSliderOption("doriki_limit", 0.0D, 99999.0D, 100.0F, CommonConfig.INSTANCE.dorikiLimit);
/* 20 */   private ModSliderOption hakiExpLimit = new ModSliderOption("haki_exp_limit", 0.0D, 999.0D, 10.0F, CommonConfig.INSTANCE.hakiExpLimit);
/* 21 */   private ModBooleanOption minimumDorikiPerKill = new ModBooleanOption("minimum_doriki_per_kill", CommonConfig.INSTANCE.minimumDorikiPerKill);
/* 22 */   private ModBooleanOption mobRewards = new ModBooleanOption("mob_rewards", CommonConfig.INSTANCE.mobRewards);
/* 23 */   private ModBooleanOption extraHearts = new ModBooleanOption("extra_hearts", CommonConfig.INSTANCE.extraHearts);
/* 24 */   private ModBooleanOption destroySpawner = new ModBooleanOption("destroy_spawner", CommonConfig.INSTANCE.destroySpawner);
/* 25 */   private ModBooleanOption randomizeRace = new ModBooleanOption("race_randomizer", CommonConfig.INSTANCE.randomizeRace);
/* 26 */   private ModIteratableOption haoshokuUnlockLogic = new ModIteratableOption("haoshoku_unlock_logic", CommonConfig.INSTANCE.haoshokuUnlockLogic);
/*    */   
/* 28 */   private ModIteratableOption keepStatsAfterDeath = new ModIteratableOption("keep_stats_after_death", CommonConfig.INSTANCE.keepStatsAfterDeath);
/* 29 */   private ModSliderOption dorikiKeepPercentage = new ModSliderOption("doriki_percentage_after_death", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.dorikiKeepPercentage);
/* 30 */   private ModSliderOption bountyKeepPercentage = new ModSliderOption("bounty_percentage_after_death", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.bountyKeepPercentage);
/* 31 */   private ModSliderOption bellyKeepPercentage = new ModSliderOption("belly_percentage_after_death", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.bellyKeepPercentage);
/* 32 */   private ModSliderOption hakiExpKeepPercentage = new ModSliderOption("haki_exp_percentage_after_death", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.hakiExpKeepPercentage);
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(ConfigCategoryList list) {
/* 37 */     list.addOption((AbstractOption)this.dorikiRewardMultiplier);
/* 38 */     list.addOption((AbstractOption)this.hakiExpMultiplier);
/* 39 */     list.addOption((AbstractOption)this.dorikiLimit);
/* 40 */     list.addOption((AbstractOption)this.hakiExpLimit);
/* 41 */     list.addOption((AbstractOption)this.minimumDorikiPerKill);
/* 42 */     list.addOption((AbstractOption)this.mobRewards);
/* 43 */     list.addOption((AbstractOption)this.extraHearts);
/* 44 */     list.addOption((AbstractOption)this.destroySpawner);
/* 45 */     list.addOption((AbstractOption)this.randomizeRace);
/* 46 */     list.addOption((AbstractOption)this.haoshokuUnlockLogic);
/*    */     
/* 48 */     list.addCategory("Keep Stats after Death");
/* 49 */     list.addOption((AbstractOption)this.keepStatsAfterDeath);
/* 50 */     list.addOption((AbstractOption)this.dorikiKeepPercentage);
/* 51 */     list.addOption((AbstractOption)this.bountyKeepPercentage);
/* 52 */     list.addOption((AbstractOption)this.bellyKeepPercentage);
/* 53 */     list.addOption((AbstractOption)this.hakiExpKeepPercentage);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getTitle() {
/* 59 */     return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.general", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\categories\GeneralConfigPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */