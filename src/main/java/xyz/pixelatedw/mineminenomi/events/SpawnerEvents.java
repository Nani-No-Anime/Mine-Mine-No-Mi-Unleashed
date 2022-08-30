/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import net.minecraft.world.dimension.DimensionType;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.world.spawners.AmbushSpawner;
/*    */ import xyz.pixelatedw.mineminenomi.world.spawners.TraderSpawner;
/*    */ import xyz.pixelatedw.mineminenomi.world.spawners.TrainerSpawner;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class SpawnerEvents
/*    */ {
/* 18 */   private static final TraderSpawner TRADER_SPAWNER = new TraderSpawner();
/* 19 */   private static final TrainerSpawner TRAINER_SPAWNER = new TrainerSpawner();
/* 20 */   private static final AmbushSpawner AMBUSH_SPAWNER = new AmbushSpawner();
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onServerTick(TickEvent.WorldTickEvent event) {
/* 25 */     if (event.phase == TickEvent.Phase.END && event.world.dimension.getType() == DimensionType.OVERWORLD) {
/*    */       
/* 27 */       event.world.getProfiler().startSection("worldSpawners");
/*    */       
/* 29 */       if (CommonConfig.INSTANCE.canSpawnTraders())
/* 30 */         TRADER_SPAWNER.tick((ServerWorld)event.world); 
/* 31 */       if (CommonConfig.INSTANCE.canSpawnTrainers())
/* 32 */         TRAINER_SPAWNER.tick((ServerWorld)event.world); 
/* 33 */       if (CommonConfig.INSTANCE.canSpawnAmbushes()) {
/* 34 */         AMBUSH_SPAWNER.tick((ServerWorld)event.world);
/*    */       }
/* 36 */       event.world.getProfiler().endSection();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\SpawnerEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */