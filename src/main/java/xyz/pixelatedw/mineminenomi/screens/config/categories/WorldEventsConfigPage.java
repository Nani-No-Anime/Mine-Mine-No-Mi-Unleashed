package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;
import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;

public class WorldEventsConfigPage
  extends ConfigPage {
  private ModBooleanOption canSpawnWorldNPCs = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.canSpawnWorldNPCs);
  
  private ModBooleanOption canSpawnTraders = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.canSpawnTraders);
  private ModSliderOption chanceForTraderSpawn = new ModSliderOption("spawn_chance", 1.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForTraderSpawn);
  private ModSliderOption timeBetweenTraderSpawns = new ModSliderOption("time_between_spawns", 1.0D, 3600.0D, 60.0F, CommonConfig.INSTANCE.timeBetweenTraderSpawns);
  
  private ModBooleanOption canSpawnTrainers = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.canSpawnTrainers);
  private ModSliderOption chanceForTrainerSpawn = new ModSliderOption("spawn_chance", 1.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForTrainerSpawn);
  private ModSliderOption timeBetweenTrainerSpawns = new ModSliderOption("time_between_spawns", 1.0D, 3600.0D, 60.0F, CommonConfig.INSTANCE.timeBetweenTrainerSpawns);
  
  private ModBooleanOption canSpawnAmbushes = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.canSpawnAmbushes);
  private ModSliderOption chanceForAmbushSpawn = new ModSliderOption("spawn_chance", 1.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForAmbushSpawn);
  private ModSliderOption timeBetweenAmbushSpawns = new ModSliderOption("time_between_spawns", 1.0D, 3600.0D, 60.0F, CommonConfig.INSTANCE.timeBetweenAmbushSpawns);


  
  public void init(ConfigCategoryList list) {
    list.addCategory("Faction NPCs");
    list.addOption((AbstractOption)this.canSpawnWorldNPCs, null);
    
    list.addCategory("Traders");
    list.addOption((AbstractOption)this.canSpawnTraders, (AbstractOption)this.chanceForTraderSpawn);
    list.addOption((AbstractOption)this.timeBetweenTraderSpawns, null);
    
    list.addCategory("Trainers");
    list.addOption((AbstractOption)this.canSpawnTrainers, (AbstractOption)this.chanceForTrainerSpawn);
    list.addOption((AbstractOption)this.timeBetweenTrainerSpawns, null);
    
    list.addCategory("Ambushes");
    list.addOption((AbstractOption)this.canSpawnAmbushes, (AbstractOption)this.chanceForAmbushSpawn);
    list.addOption((AbstractOption)this.timeBetweenAmbushSpawns, null);
  }


  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.world_events", new Object[0]);
  }
}


