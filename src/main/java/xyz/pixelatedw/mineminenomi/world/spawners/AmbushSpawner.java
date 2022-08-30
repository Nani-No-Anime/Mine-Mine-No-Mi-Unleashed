/*     */ package xyz.pixelatedw.mineminenomi.world.spawners;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ public class AmbushSpawner {
/*  25 */   private Random random = new Random();
/*     */   
/*     */   private int cooldown;
/*     */   
/*     */   public void tick(ServerWorld world) {
/*  30 */     world.getProfiler().startSection("ambushSpawnerTick");
/*  31 */     if (--this.cooldown <= 0) {
/*     */       
/*  33 */       this.cooldown = CommonConfig.INSTANCE.getTimeBetweenAmbushSpawns();
/*  34 */       if (this.random.nextInt(100) <= CommonConfig.INSTANCE.getChanceForAmbushSpawn())
/*  35 */         spawn(world); 
/*     */     } 
/*  37 */     world.getProfiler().endSection();
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawn(ServerWorld world) {
/*  42 */     world.getProfiler().startSection("ambushSpawnerSpawn");
/*  43 */     ServerPlayerEntity serverPlayerEntity = world.getRandomPlayer();
/*  44 */     if (serverPlayerEntity == null) {
/*     */       return;
/*     */     }
/*     */     
/*  48 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*     */     
/*  50 */     if (props.isPirate() || props.isBandit() || props.isRevolutionary()) {
/*     */       
/*  52 */       BlockPos targetPos = serverPlayerEntity.getPosition();
/*  53 */       long bounty = props.getBounty();
/*  54 */       boolean canSpawnInBiome = (world.getBiome(targetPos).getCategory() != Biome.Category.OCEAN);
/*  55 */       boolean canSeeSky = ((PlayerEntity)serverPlayerEntity).world.canBlockSeeSky(targetPos);
/*     */       
/*  57 */       if (!canSpawnInBiome || bounty < 10000L || !canSeeSky) {
/*     */         return;
/*     */       }
/*  60 */       EntityType captainEntity = ModEntities.MARINE_CAPTAIN;
/*  61 */       EntityType grunt1Entity = ModEntities.MARINE_WITH_SWORD;
/*  62 */       EntityType grunt2Entity = ModEntities.MARINE_WITH_GUN;
/*     */       
/*  64 */       int r = this.random.nextInt(2);
/*  65 */       int r2 = this.random.nextInt(2);
/*  66 */       if (r == 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  74 */       List<OPEntity> dangers = WyHelper.getEntitiesNear(targetPos, (World)world, 80.0D, new Class[] { OPEntity.class });
/*  75 */       if (dangers.size() > 50) {
/*     */         return;
/*     */       }
/*  78 */       int nrCaptains = 1 + (int)Math.ceil((bounty / 200000L));
/*  79 */       int nrGrunts = 3 + (int)Math.ceil((bounty / 100000L));
/*     */       
/*  81 */       String name = captainEntity.getName().getFormattedText();
/*     */       
/*  83 */       if (nrCaptains > 3) {
/*  84 */         nrCaptains = 3;
/*     */       }
/*  86 */       if (nrGrunts > 30) {
/*  87 */         nrGrunts = 30;
/*     */       }
/*     */       int i;
/*  90 */       for (i = 0; i < nrCaptains; i++) {
/*     */         
/*  92 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, captainEntity, targetPos, 10);
/*  93 */         if (spawnPos != null) {
/*  94 */           captainEntity.spawn((World)world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*     */         }
/*     */       } 
/*     */       
/*  98 */       for (i = 0; i < nrGrunts; i++) {
/*     */         
/* 100 */         EntityType gruntEntity = (i % 2 == 0) ? grunt1Entity : grunt2Entity;
/*     */         
/* 102 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, gruntEntity, targetPos, 20);
/* 103 */         if (spawnPos != null) {
/* 104 */           gruntEntity.spawn((World)world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*     */         }
/*     */       } 
/*     */       
/* 108 */       StringTextComponent stringTextComponent = new StringTextComponent("<" + name + "> We've come to arrest you, surrender now criminal scum!");
/* 109 */       if (r2 == 1) {
/* 110 */         stringTextComponent = new StringTextComponent("<" + name + "> You're surrounded and have no escape, surrender now!");
/*     */       }
/* 112 */       serverPlayerEntity.sendMessage((ITextComponent)stringTextComponent);
/* 113 */       WyDebug.debug("Ambush spawned around these coords: " + targetPos);
/*     */     } 
/*     */     
/* 116 */     world.getProfiler().endSection();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\spawners\AmbushSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */