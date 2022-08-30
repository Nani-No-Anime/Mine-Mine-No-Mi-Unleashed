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
/*    */ public class BaratieArena
/*    */   extends ArenaData
/*    */ {
/*    */   public BaratieArena() {
/* 18 */     super("baratie");
/* 19 */     this.spawnPos = new BlockPos(0, 65, 0);
/* 20 */     this.startBarrierPos = new ChunkPos(-5, -5);
/* 21 */     this.endBarrierPos = new ChunkPos(15, 15);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void build(World world) {
/* 27 */     WyHelper.loadNBTStructure((ServerWorld)world, this.arenaName, this.spawnPos.add(0, -13, 0));
/* 28 */     world.setBlockState(this.spawnPos.add(0, -13, 0), Blocks.AIR.getDefaultState());
/*    */     
/* 30 */     this.playerSpawn = this.spawnPos.add(50, -1, 53);
/* 31 */     this.bossSpawn = this.playerSpawn.add(10, 0, 0);
/* 32 */     BlockPos arenaSpawn = this.playerSpawn.add(0, 5, 0);
/*    */     
/* 34 */     world.setBlockState(arenaSpawn, ModBlocks.CHALLENGE_ARENA.getDefaultState());
/* 35 */     this.arenaTileEntity = (ChallengeArenaTileEntity)world.getTileEntity(arenaSpawn);
/*    */     
/* 37 */     this.arenaTileEntity.setupArena(10000);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void preGen(WorldGenRegion region) {
/* 43 */     BlockPos.Mutable blockPos = new BlockPos.Mutable();
/* 44 */     int i = region.getMainChunkX();
/* 45 */     int j = region.getMainChunkZ();
/*    */     
/* 47 */     for (int x = 0; x < 16; x++) {
/*    */       
/* 49 */       for (int z = 0; z < 16; z++) {
/*    */         
/* 51 */         int x1 = (i << 4) + x;
/* 52 */         int z1 = (j << 4) + z;
/*    */         
/* 54 */         for (int y = 0; y < 64; y++)
/* 55 */           region.setBlockState((BlockPos)blockPos.setPos(x1, y, z1), Blocks.WATER.getDefaultState(), 3); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\BaratieArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */