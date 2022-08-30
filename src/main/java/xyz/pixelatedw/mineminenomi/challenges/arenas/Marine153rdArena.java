/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas;
/*    */ 
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.ChunkPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.WorldGenRegion;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.ChallengeArenaTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class Marine153rdArena
/*    */   extends ArenaData
/*    */ {
/*    */   public Marine153rdArena() {
/* 18 */     super("marine153branch");
/* 19 */     this.spawnPos = new BlockPos(1000, 65, 0);
/* 20 */     this.startBarrierPos = new ChunkPos(61, -2);
/* 21 */     this.endBarrierPos = new ChunkPos(67, 5);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void build(World world) {
/* 27 */     world.getChunk(this.spawnPos);
/*    */     
/* 29 */     WyHelper.loadNBTStructure((ServerWorld)world, this.arenaName, this.spawnPos.add(0, -13, 0));
/* 30 */     world.setBlockState(this.spawnPos.add(0, -13, 0), Blocks.AIR.getDefaultState());
/*    */     
/* 32 */     this.playerSpawn = this.spawnPos.add(30, 62, 35);
/* 33 */     this.bossSpawn = this.playerSpawn.add(10, 0, 0);
/* 34 */     BlockPos arenaSpawn = this.playerSpawn.add(0, -34, 0);
/*    */     
/* 36 */     world.setBlockState(arenaSpawn, ModBlocks.CHALLENGE_ARENA.getDefaultState());
/* 37 */     this.arenaTileEntity = (ChallengeArenaTileEntity)world.getTileEntity(arenaSpawn);
/*    */     
/* 39 */     this.arenaTileEntity.setupArena(20000);
/*    */   }
/*    */   
/*    */   public void preGen(WorldGenRegion region) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\Marine153rdArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */