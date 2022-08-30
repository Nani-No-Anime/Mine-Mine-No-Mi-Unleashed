/*    */ package xyz.pixelatedw.mineminenomi.world;
/*    */ 
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.biome.provider.BiomeProvider;
/*    */ import net.minecraft.world.chunk.IChunk;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.DebugGenerationSettings;
/*    */ import net.minecraft.world.gen.GenerationSettings;
/*    */ import net.minecraft.world.gen.Heightmap;
/*    */ import net.minecraft.world.gen.WorldGenRegion;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArenas;
/*    */ 
/*    */ public class ChallengesChunkGenerator
/*    */   extends ChunkGenerator<DebugGenerationSettings> {
/*    */   public ChallengesChunkGenerator(IWorld world, BiomeProvider biomeProvider, DebugGenerationSettings settings) {
/* 19 */     super(world, biomeProvider, settings);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decorate(WorldGenRegion region) {
/* 25 */     BlockPos.Mutable blockPos = new BlockPos.Mutable();
/* 26 */     int i = region.getMainChunkX();
/* 27 */     int j = region.getMainChunkZ();
/*    */     
/* 29 */     for (ArenaData arena : ModArenas.ARENAS) {
/*    */       
/* 31 */       int barrierStartX = arena.startBarrierPos.x;
/* 32 */       int barrierEndX = arena.endBarrierPos.x;
/* 33 */       int barrierStartZ = arena.startBarrierPos.z;
/* 34 */       int barrierEndZ = arena.endBarrierPos.z;
/*    */       
/* 36 */       if (i > barrierStartX && i < barrierEndX && j > barrierStartZ && j < barrierEndZ)
/*    */       {
/* 38 */         arena.preGen(region);
/*    */       }
/*    */       
/* 41 */       if (i > barrierStartX && i < barrierEndX && (j == barrierStartZ + 1 || j == barrierEndZ))
/*    */       {
/* 43 */         for (int x = 0; x < 16; x++) {
/*    */           
/* 45 */           int x1 = (i << 4) + x;
/* 46 */           int z1 = j << 4;
/*    */           
/* 48 */           for (int y = 0; y < region.getHeight(); y++) {
/* 49 */             region.setBlockState((BlockPos)blockPos.setPos(x1, y, z1), Blocks.BARRIER.getDefaultState(), 3);
/*    */           }
/*    */         } 
/*    */       }
/* 53 */       if (j > barrierStartZ && j < barrierEndZ && (i == barrierStartX + 1 || i == barrierEndX))
/*    */       {
/* 55 */         for (int z = 0; z < 16; z++) {
/*    */           
/* 57 */           int x1 = i << 4;
/* 58 */           int z1 = (j << 4) + z;
/*    */           
/* 60 */           for (int y = 0; y < region.getHeight(); y++) {
/* 61 */             region.setBlockState((BlockPos)blockPos.setPos(x1, y, z1), Blocks.BARRIER.getDefaultState(), 3);
/*    */           }
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGroundHeight() {
/* 70 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void makeBase(IWorld worldIn, IChunk chunkIn) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generateSurface(WorldGenRegion region, IChunk chunk) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHeight(int x, int z, Heightmap.Type heightmapType) {
/* 87 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\ChallengesChunkGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */