/*    */ package xyz.pixelatedw.mineminenomi.world;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.world.ClientWorld;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.ChunkPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.Biomes;
/*    */ import net.minecraft.world.biome.provider.BiomeProviderType;
/*    */ import net.minecraft.world.biome.provider.IBiomeProviderSettings;
/*    */ import net.minecraft.world.biome.provider.SingleBiomeProvider;
/*    */ import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
/*    */ import net.minecraft.world.dimension.Dimension;
/*    */ import net.minecraft.world.dimension.DimensionType;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.ChunkGeneratorType;
/*    */ import net.minecraft.world.gen.DebugChunkGenerator;
/*    */ import net.minecraft.world.gen.DebugGenerationSettings;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.IRenderHandler;
/*    */ import net.minecraftforge.client.SkyRenderHandler;
/*    */ 
/*    */ public class ChallengesDimension
/*    */   extends Dimension
/*    */ {
/* 32 */   private SkyRenderer skyRenderer = new SkyRenderer();
/*    */ 
/*    */   
/*    */   public ChallengesDimension(World world, DimensionType type) {
/* 36 */     super(world, type, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ChunkGenerator<?> createChunkGenerator() {
/* 42 */     ChunkGeneratorType<DebugGenerationSettings, DebugChunkGenerator> chunkgeneratortype1 = ChunkGeneratorType.DEBUG;
/* 43 */     BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeprovidertype = BiomeProviderType.FIXED;
/* 44 */     SingleBiomeProviderSettings singlebiomeprovidersettings = ((SingleBiomeProviderSettings)biomeprovidertype.createSettings(getWorld().getWorldInfo())).setBiome(Biomes.THE_VOID);
/* 45 */     return new ChallengesChunkGenerator((IWorld)this.world, biomeprovidertype.create(singlebiomeprovidersettings), chunkgeneratortype1.createSettings());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
/* 51 */     Random random = new Random(this.world.getSeed());
/* 52 */     BlockPos blockpos = new BlockPos(chunkPosIn.getXStart() + random.nextInt(15), 0, chunkPosIn.getZEnd() + random.nextInt(15));
/* 53 */     return this.world.getGroundAboveSeaLevel(blockpos).getMaterial().blocksMovement() ? blockpos : null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
/* 59 */     return findSpawn(new ChunkPos(posX >> 4, posZ >> 4), checkValid);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float calculateCelestialAngle(long worldTime, float partialTicks) {
/* 65 */     return 90.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSurfaceWorld() {
/* 71 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Vec3d getFogColor(float celestialAngle, float partialTicks) {
/* 77 */     return new Vec3d(0.05D, 0.05D, 0.05D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canRespawnHere() {
/* 83 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean doesXZShowFog(int x, int z) {
/* 89 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderHandler getSkyRenderer() {
/* 97 */     return (IRenderHandler)this.skyRenderer;
/*    */   }
/*    */   
/*    */   private static class SkyRenderer implements SkyRenderHandler {
/*    */     private SkyRenderer() {}
/*    */     
/*    */     public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\ChallengesDimension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */