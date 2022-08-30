/*    */ package xyz.pixelatedw.mineminenomi.world.spawners;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*    */ 
/*    */ public class TraderSpawner {
/* 22 */   private Random random = new Random();
/*    */   private int cooldown;
/* 24 */   private static final EntityType[] TRADER_TYPES = new EntityType[] { ModEntities.MARINE_TRADER, ModEntities.PIRATE_TRADER };
/*    */ 
/*    */   
/*    */   public void tick(ServerWorld world) {
/* 28 */     world.getProfiler().startSection("traderSpawnerTick");
/* 29 */     if (--this.cooldown <= 0) {
/*    */       
/* 31 */       this.cooldown = CommonConfig.INSTANCE.getTimeBetweenTraderSpawns();
/* 32 */       if (this.random.nextInt(100) <= CommonConfig.INSTANCE.getChanceForTraderSpawn())
/*    */       {
/* 34 */         spawn(world);
/*    */       }
/*    */     } 
/* 37 */     world.getProfiler().endSection();
/*    */   }
/*    */ 
/*    */   
/*    */   private void spawn(ServerWorld world) {
/* 42 */     world.getProfiler().startSection("traderSpawnerSpawn");
/* 43 */     ServerPlayerEntity serverPlayerEntity = world.getRandomPlayer();
/* 44 */     if (serverPlayerEntity == null) {
/*    */       return;
/*    */     }
/*    */     
/* 48 */     int r = this.random.nextInt(TRADER_TYPES.length);
/* 49 */     EntityType entityType = TRADER_TYPES[r];
/* 50 */     BlockPos targetPos = serverPlayerEntity.getPosition();
/* 51 */     BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, entityType, targetPos, 20);
/* 52 */     List<TraderEntity> traders = WyHelper.getEntitiesNear(targetPos, (World)world, 40.0D, new Class[] { TraderEntity.class });
/* 53 */     if (spawnPos == null)
/*    */       return; 
/* 55 */     boolean canSpawnInBiome = (world.getBiome(targetPos).getCategory() != Biome.Category.OCEAN);
/*    */     
/* 57 */     if (traders.size() < 3 && canSpawnInBiome) {
/*    */       
/* 59 */       entityType.spawn((World)world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/* 60 */       WyDebug.debug("Trader spawned at: " + spawnPos);
/*    */     } 
/*    */     
/* 63 */     world.getProfiler().endSection();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\spawners\TraderSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */