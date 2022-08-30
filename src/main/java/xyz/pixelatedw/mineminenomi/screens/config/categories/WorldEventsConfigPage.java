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
/*    */ public class WorldEventsConfigPage
/*    */   extends ConfigPage {
/* 14 */   private ModBooleanOption canSpawnWorldNPCs = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.canSpawnWorldNPCs);
/*    */   
/* 16 */   private ModBooleanOption canSpawnTraders = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.canSpawnTraders);
/* 17 */   private ModSliderOption chanceForTraderSpawn = new ModSliderOption("spawn_chance", 1.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForTraderSpawn);
/* 18 */   private ModSliderOption timeBetweenTraderSpawns = new ModSliderOption("time_between_spawns", 1.0D, 3600.0D, 60.0F, CommonConfig.INSTANCE.timeBetweenTraderSpawns);
/*    */   
/* 20 */   private ModBooleanOption canSpawnTrainers = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.canSpawnTrainers);
/* 21 */   private ModSliderOption chanceForTrainerSpawn = new ModSliderOption("spawn_chance", 1.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForTrainerSpawn);
/* 22 */   private ModSliderOption timeBetweenTrainerSpawns = new ModSliderOption("time_between_spawns", 1.0D, 3600.0D, 60.0F, CommonConfig.INSTANCE.timeBetweenTrainerSpawns);
/*    */   
/* 24 */   private ModBooleanOption canSpawnAmbushes = new ModBooleanOption("can_spawn", CommonConfig.INSTANCE.canSpawnAmbushes);
/* 25 */   private ModSliderOption chanceForAmbushSpawn = new ModSliderOption("spawn_chance", 1.0D, 100.0D, 1.0F, CommonConfig.INSTANCE.chanceForAmbushSpawn);
/* 26 */   private ModSliderOption timeBetweenAmbushSpawns = new ModSliderOption("time_between_spawns", 1.0D, 3600.0D, 60.0F, CommonConfig.INSTANCE.timeBetweenAmbushSpawns);
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(ConfigCategoryList list) {
/* 31 */     list.addCategory("Faction NPCs");
/* 32 */     list.addOption((AbstractOption)this.canSpawnWorldNPCs, null);
/*    */     
/* 34 */     list.addCategory("Traders");
/* 35 */     list.addOption((AbstractOption)this.canSpawnTraders, (AbstractOption)this.chanceForTraderSpawn);
/* 36 */     list.addOption((AbstractOption)this.timeBetweenTraderSpawns, null);
/*    */     
/* 38 */     list.addCategory("Trainers");
/* 39 */     list.addOption((AbstractOption)this.canSpawnTrainers, (AbstractOption)this.chanceForTrainerSpawn);
/* 40 */     list.addOption((AbstractOption)this.timeBetweenTrainerSpawns, null);
/*    */     
/* 42 */     list.addCategory("Ambushes");
/* 43 */     list.addOption((AbstractOption)this.canSpawnAmbushes, (AbstractOption)this.chanceForAmbushSpawn);
/* 44 */     list.addOption((AbstractOption)this.timeBetweenAmbushSpawns, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getTitle() {
/* 50 */     return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.world_events", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\categories\WorldEventsConfigPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */