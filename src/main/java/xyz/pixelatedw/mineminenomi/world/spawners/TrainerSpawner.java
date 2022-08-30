/*     */ package xyz.pixelatedw.mineminenomi.world.spawners;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;

import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ public class TrainerSpawner
/*     */ {
/*  29 */   private Random random = new Random();
/*     */   
/*     */   private int cooldown;
/*     */   
/*     */   public void tick(ServerWorld world) {
/*  34 */     world.getProfiler().startSection("traderSpawnerTick");
/*  35 */     if (--this.cooldown <= 0) {
/*     */       
/*  37 */       this.cooldown = CommonConfig.INSTANCE.getTimeBetweenTrainerSpawns();
/*  38 */       if (this.random.nextInt(100) <= CommonConfig.INSTANCE.getChanceForTrainerSpawn())
/*     */       {
/*  40 */         spawn(world);
/*     */       }
/*     */     } 
/*  43 */     world.getProfiler().endSection();
/*     */   }
/*     */ 
/*     */   
/*     */   private void spawn(ServerWorld world) {
/*  48 */     world.getProfiler().startSection("trainerSpawnerSpawn");
/*  49 */     ServerPlayerEntity serverPlayerEntity = world.getRandomPlayer();
/*  50 */     if (serverPlayerEntity == null) {
/*     */       return;
/*     */     }
/*     */     
/*  54 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*  55 */     EntityType entityType = null;
/*  56 */     Quest[] quests = null;
/*     */     
/*  58 */     if (props.isSwordsman()) {
/*     */       
/*  60 */       entityType = ModEntities.SWORDSMAN_TRAINER;
/*  61 */       quests = ModQuests.SWORDSMAN_TRIALS;
/*     */     }
/*  63 */     else if (props.isSniper()) {
/*     */       
/*  65 */       entityType = ModEntities.SNIPER_TRAINER;
/*  66 */       quests = ModQuests.SNIPER_TRIALS;
/*     */     }
/*  68 */     else if (props.isWeatherWizard()) {
/*     */       
/*  70 */       entityType = ModEntities.ART_OF_WEATHER_TRAINER;
/*  71 */       quests = ModQuests.ART_OF_WEATHER_TRIALS;
/*     */     }
/*  73 */     else if (props.isDoctor()) {
/*     */       
/*  75 */       entityType = ModEntities.DOCTOR_TRAINER;
/*  76 */       quests = ModQuests.DOCTOR_TRIALS;
/*     */     }
/*  78 */     else if (props.isBrawler()) {
/*     */       
/*  80 */       entityType = ModEntities.BRAWLER_TRAINER;
/*  81 */       quests = ModQuests.BRAWLER_TRIALS;
/*     */     }
/*  83 */     else if (props.isBlackLeg()) {
/*     */       
/*  85 */       entityType = ModEntities.BLACK_LEG_TRAINER;
/*  86 */       quests = ModQuests.BLACK_LEG_TRIALS;
/*     */     } 
/*     */     
/*  89 */     if (entityType == null) {
/*     */       return;
/*     */     }
/*  92 */     BlockPos targetPos = serverPlayerEntity.getPosition();
/*  93 */     BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, entityType, targetPos, 20);
/*  94 */     if (spawnPos == null)
/*     */       return; 
/*  96 */     List<Entity> trainers = WyHelper.getEntitiesNear(spawnPos, world, 100.0D).stream().filter(entity -> entity instanceof xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity).collect(Collectors.toList());
/*  97 */     boolean hasAvailableQuests = true;
/*     */     
/*  99 */     if (quests != null)
/*     */     {
/* 101 */       for (Quest trial : quests) {
/*     */         
/* 103 */         if (!QuestDataCapability.get((PlayerEntity)serverPlayerEntity).hasFinishedQuest(trial)) {
/*     */           break;
/*     */         }
/* 106 */         hasAvailableQuests = false;
/*     */       } 
/*     */     }
/*     */     
/* 110 */     if (spawnPos != null && hasAvailableQuests) {
/*     */       
/* 112 */       boolean canSpawnInBiome = (world.getBiome(targetPos).getCategory() != Biome.Category.OCEAN);
/*     */       
/* 114 */       if (trainers.size() < 2 && canSpawnInBiome) {
/*     */         
/* 116 */         entityType.spawn((World)world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/* 117 */         WyDebug.debug("Trainer spawned at: " + spawnPos);
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     world.getProfiler().endSection();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\spawners\TrainerSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */