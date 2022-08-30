/*    */ package xyz.pixelatedw.mineminenomi.challenges;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.ChunkPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.WorldGenRegion;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.ChallengeArenaTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArenas;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenArenaSetupScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public abstract class ArenaData
/*    */ {
/*    */   public String arenaName;
/* 20 */   public BlockPos spawnPos = new BlockPos(0, 65, 0);
/* 21 */   public ChunkPos startBarrierPos = new ChunkPos(0, 0);
/* 22 */   public ChunkPos endBarrierPos = new ChunkPos(0, 0);
/*    */   
/*    */   public boolean isInUse;
/*    */   
/*    */   public UUID owner;
/*    */   protected BlockPos playerSpawn;
/*    */   protected BlockPos bossSpawn;
/*    */   protected ChallengeArenaTileEntity arenaTileEntity;
/*    */   
/*    */   public ArenaData(String arenaName) {
/* 32 */     this.arenaName = arenaName;
/* 33 */     ModArenas.ARENAS.add(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildIfNeeded(PlayerEntity player, World world) {
/* 38 */     ExtendedWorldData worldData = ExtendedWorldData.get(world);
/*    */     
/* 40 */     if (worldData.isArenaGenerated(this.arenaName)) {
/*    */       return;
/*    */     }
/* 43 */     WyDebug.debug(this.arenaName + " arena is not present so it will be generated!");
/*    */     
/* 45 */     WyNetwork.sendTo(new SOpenArenaSetupScreenPacket(this.arenaName), player);
/*    */     
/* 47 */     long startTime = System.currentTimeMillis();
/*    */     
/* 49 */     build(world);
/*    */     
/* 51 */     long stopTime = System.currentTimeMillis();
/*    */     
/* 53 */     worldData.addGeneratedArena(this.arenaName);
/* 54 */     WyDebug.debug(this.arenaName + " has been generated at " + this.spawnPos + " in " + ((stopTime - startTime) / 1000L) + " seconds");
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract void build(World paramWorld);
/*    */   
/*    */   public abstract void preGen(WorldGenRegion paramWorldGenRegion);
/*    */   
/*    */   public BlockPos getPlayerSpawn() {
/* 63 */     return this.playerSpawn;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos getBossSpawn() {
/* 68 */     return this.bossSpawn;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChallengeArenaTileEntity getArenaTileEntity() {
/* 73 */     return this.arenaTileEntity;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\ArenaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */