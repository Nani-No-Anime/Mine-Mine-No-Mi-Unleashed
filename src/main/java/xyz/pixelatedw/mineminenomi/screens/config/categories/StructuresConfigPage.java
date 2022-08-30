/*    */ package xyz.pixelatedw.mineminenomi.screens.config.categories;
/*    */ 
/*    */ import net.minecraft.client.settings.AbstractOption;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;
/*    */ 
/*    */ public class StructuresConfigPage
/*    */   extends ConfigPage {
/* 14 */   private ModBooleanOption enableTrainingStructuresSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnTrainingStructures);
/* 15 */   private ModSliderOption trainingStructureSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceTrainingStructure);
/* 16 */   private ModBooleanOption enableSmallShipsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnSmallShips);
/* 17 */   private ModSliderOption smallShipsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceSmallShip);
/* 18 */   private ModBooleanOption enableMediumShipsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnMediumShips);
/* 19 */   private ModSliderOption mediumShipsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceMediumShip);
/* 20 */   private ModBooleanOption enableLargeShipsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnLargeShips);
/* 21 */   private ModSliderOption largeShipsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceLargeShips);
/* 22 */   private ModBooleanOption enableGhostShipsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnGhostShips);
/* 23 */   private ModSliderOption ghostShipsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceGhostShip);
/* 24 */   private ModBooleanOption enableCampsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnCamps);
/* 25 */   private ModSliderOption campsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceCamps);
/* 26 */   private ModBooleanOption enableSmallBasesSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnSmallBases);
/* 27 */   private ModSliderOption smallBasesSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceSmallBase);
/* 28 */   private ModBooleanOption enableLargeBasesSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnLargeBases);
/* 29 */   private ModSliderOption largeBasesSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceLargeBase);
/* 30 */   private ModBooleanOption enableWatchTowersSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnWatchTowers);
/* 31 */   private ModSliderOption watchTowersSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceWatchTower);
/* 32 */   private ModBooleanOption enableSkyIslandsSpawn = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.spawnSkyIslands);
/* 33 */   private ModSliderOption skyIslandsSpawnChance = new ModSliderOption("spawn_chance", 0.0D, 100.0D, 0.5F, CommonConfig.INSTANCE.spawnChanceSkyIsland);
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(ConfigCategoryList list) {
/* 38 */     list.addCategory("Training Structures");
/* 39 */     list.addOption((AbstractOption)this.enableTrainingStructuresSpawn, (AbstractOption)this.trainingStructureSpawnChance);
/*    */     
/* 41 */     list.addCategory("Small Ships");
/* 42 */     list.addOption((AbstractOption)this.enableSmallShipsSpawn, (AbstractOption)this.smallShipsSpawnChance);
/*    */     
/* 44 */     list.addCategory("Medium Ships");
/* 45 */     list.addOption((AbstractOption)this.enableMediumShipsSpawn, (AbstractOption)this.mediumShipsSpawnChance);
/*    */     
/* 47 */     list.addCategory("Large Ships");
/* 48 */     list.addOption((AbstractOption)this.enableLargeShipsSpawn, (AbstractOption)this.largeShipsSpawnChance);
/*    */     
/* 50 */     list.addCategory("Ghost Ships");
/* 51 */     list.addOption((AbstractOption)this.enableGhostShipsSpawn, (AbstractOption)this.ghostShipsSpawnChance);
/*    */     
/* 53 */     list.addCategory("Camps");
/* 54 */     list.addOption((AbstractOption)this.enableCampsSpawn, (AbstractOption)this.campsSpawnChance);
/*    */     
/* 56 */     list.addCategory("Small Bases");
/* 57 */     list.addOption((AbstractOption)this.enableSmallBasesSpawn, (AbstractOption)this.smallBasesSpawnChance);
/*    */     
/* 59 */     list.addCategory("Large Bases");
/* 60 */     list.addOption((AbstractOption)this.enableLargeBasesSpawn, (AbstractOption)this.largeBasesSpawnChance);
/*    */     
/* 62 */     list.addCategory("Watch Towers");
/* 63 */     list.addOption((AbstractOption)this.enableWatchTowersSpawn, (AbstractOption)this.watchTowersSpawnChance);
/*    */     
/* 65 */     list.addCategory("Sky Islands");
/* 66 */     list.addOption((AbstractOption)this.enableSkyIslandsSpawn, (AbstractOption)this.skyIslandsSpawnChance);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getTitle() {
/* 72 */     return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.structures", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\categories\StructuresConfigPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */