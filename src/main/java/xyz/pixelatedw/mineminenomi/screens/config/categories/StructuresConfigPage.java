package xyz.pixelatedw.mineminenomi.screens.config.categories;

import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;
import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;

public class StructuresConfigPage
  extends ConfigPage {
  private ModBooleanOption enableTrainingStructuresSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnTrainingStructures);
  private ModSliderOption trainingStructureSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceTrainingStructure);
  private ModBooleanOption enableSmallShipsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnSmallShips);
  private ModSliderOption smallShipsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceSmallShip);
  private ModBooleanOption enableMediumShipsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnMediumShips);
  private ModSliderOption mediumShipsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceMediumShip);
  private ModBooleanOption enableLargeShipsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnLargeShips);
  private ModSliderOption largeShipsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceLargeShips);
  private ModBooleanOption enableGhostShipsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnGhostShips);
  private ModSliderOption ghostShipsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceGhostShip);
  private ModBooleanOption enableCampsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnCamps);
  private ModSliderOption campsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceCamps);
  private ModBooleanOption enableSmallBasesSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnSmallBases);
  private ModSliderOption smallBasesSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceSmallBase);
  private ModBooleanOption enableLargeBasesSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnLargeBases);
  private ModSliderOption largeBasesSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceLargeBase);
  private ModBooleanOption enableWatchTowersSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnWatchTowers);
  private ModSliderOption watchTowersSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceWatchTower);
  private ModBooleanOption enableSkyIslandsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnSkyIslands);
  private ModSliderOption skyIslandsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceSkyIsland);


  
  public void init(ConfigCategoryList list) {
    list.addCategory("Training Structures");
    list.addOption((AbstractOption)this.enableTrainingStructuresSpawn, (AbstractOption)this.trainingStructureSpawnChance);
    
    list.addCategory("Small Ships");
    list.addOption((AbstractOption)this.enableSmallShipsSpawn, (AbstractOption)this.smallShipsSpawnChance);
    
    list.addCategory("Medium Ships");
    list.addOption((AbstractOption)this.enableMediumShipsSpawn, (AbstractOption)this.mediumShipsSpawnChance);
    
    list.addCategory("Large Ships");
    list.addOption((AbstractOption)this.enableLargeShipsSpawn, (AbstractOption)this.largeShipsSpawnChance);
    
    list.addCategory("Ghost Ships");
    list.addOption((AbstractOption)this.enableGhostShipsSpawn, (AbstractOption)this.ghostShipsSpawnChance);
    
    list.addCategory("Camps");
    list.addOption((AbstractOption)this.enableCampsSpawn, (AbstractOption)this.campsSpawnChance);
    
    list.addCategory("Small Bases");
    list.addOption((AbstractOption)this.enableSmallBasesSpawn, (AbstractOption)this.smallBasesSpawnChance);
    
    list.addCategory("Large Bases");
    list.addOption((AbstractOption)this.enableLargeBasesSpawn, (AbstractOption)this.largeBasesSpawnChance);
    
    list.addCategory("Watch Towers");
    list.addOption((AbstractOption)this.enableWatchTowersSpawn, (AbstractOption)this.watchTowersSpawnChance);
    
    list.addCategory("Sky Islands");
    list.addOption((AbstractOption)this.enableSkyIslandsSpawn, (AbstractOption)this.skyIslandsSpawnChance);
  }


  
  public ITextComponent getTitle() {
    return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.structures", new Object[0]);
  }
}


