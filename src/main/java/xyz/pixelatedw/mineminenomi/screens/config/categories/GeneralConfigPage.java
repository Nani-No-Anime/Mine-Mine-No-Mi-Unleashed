package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;
import xyz.pixelatedw.mineminenomi.screens.config.ModIteratableOption;
import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;

public class GeneralConfigPage
  extends ConfigPage
{
  private static double dorikiRewardMultiplierValue;
  private ModSliderOption dorikiRewardMultiplier = new ModSliderOption("doriki_reward_multiplier", 0.0D, 10.0D, 0.2F, CommonConfig.INSTANCE.dorikiRewardMultiplier);
  private ModSliderOption hakiExpMultiplier = new ModSliderOption("haki_exp_multiplier", 0.0D, 10.0D, 0.2F, CommonConfig.INSTANCE.hakiExpMultiplier);
  private ModSliderOption dorikiLimit = new ModSliderOption("doriki_limit", 0.0D, 99999.0D, 100.0F, CommonConfig.INSTANCE.dorikiLimit);
  private ModSliderOption hakiExpLimit = new ModSliderOption("haki_exp_limit", 0.0D, 999.0D, 10.0F, CommonConfig.INSTANCE.hakiExpLimit);
  private ModBooleanOption minimumDorikiPerKill = new ModBooleanOption("minimum_doriki_per_kill", CommonConfig.INSTANCE.minimumDorikiPerKill);
  private ModBooleanOption mobRewards = new ModBooleanOption("mob_rewards", CommonConfig.INSTANCE.mobRewards);
  private ModBooleanOption extraHearts = new ModBooleanOption("extra_hearts", CommonConfig.INSTANCE.extraHearts);
  private ModBooleanOption destroySpawner = new ModBooleanOption("destroy_spawner", CommonConfig.INSTANCE.destroySpawner);
  private ModBooleanOption randomizeRace = new ModBooleanOption("race_randomizer", CommonConfig.INSTANCE.randomizeRace);
  private ModIteratableOption haoshokuUnlockLogic = new ModIteratableOption("haoshoku_unlock_logic", CommonConfig.INSTANCE.haoshokuUnlockLogic);
  
  private ModIteratableOption keepStatsAfterDeath = new ModIteratableOption("keep_stats_after_death", CommonConfig.INSTANCE.keepStatsAfterDeath);
  private ModSliderOption dorikiKeepPercentage = new ModSliderOption("doriki_percentage_after_death", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.dorikiKeepPercentage);
  private ModSliderOption bountyKeepPercentage = new ModSliderOption("bounty_percentage_after_death", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.bountyKeepPercentage);
  private ModSliderOption bellyKeepPercentage = new ModSliderOption("belly_percentage_after_death", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.bellyKeepPercentage);
  private ModSliderOption hakiExpKeepPercentage = new ModSliderOption("haki_exp_percentage_after_death", 0.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.hakiExpKeepPercentage);


  
  public void init(ConfigCategoryList list) {
    list.addOption((AbstractOption)this.dorikiRewardMultiplier);
    list.addOption((AbstractOption)this.hakiExpMultiplier);
    list.addOption((AbstractOption)this.dorikiLimit);
    list.addOption((AbstractOption)this.hakiExpLimit);
    list.addOption((AbstractOption)this.minimumDorikiPerKill);
    list.addOption((AbstractOption)this.mobRewards);
    list.addOption((AbstractOption)this.extraHearts);
    list.addOption((AbstractOption)this.destroySpawner);
    list.addOption((AbstractOption)this.randomizeRace);
    list.addOption((AbstractOption)this.haoshokuUnlockLogic);
    
    list.addCategory("Keep Stats after Death");
    list.addOption((AbstractOption)this.keepStatsAfterDeath);
    list.addOption((AbstractOption)this.dorikiKeepPercentage);
    list.addOption((AbstractOption)this.bountyKeepPercentage);
    list.addOption((AbstractOption)this.bellyKeepPercentage);
    list.addOption((AbstractOption)this.hakiExpKeepPercentage);
  }


  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.general", new Object[0]);
  }
}


