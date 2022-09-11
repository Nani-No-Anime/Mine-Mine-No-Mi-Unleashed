package xyz.pixelatedw.mineminenomi.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.DebugChunkGenerator;
import net.minecraft.world.gen.DebugGenerationSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.SkyRenderHandler;

public class ChallengesDimension
  extends Dimension
{
  private SkyRenderer skyRenderer = new SkyRenderer();

  
  public ChallengesDimension(World world, DimensionType type) {
    super(world, type, 0.0F);
  }


  
  public ChunkGenerator<?> createChunkGenerator() {
    ChunkGeneratorType<DebugGenerationSettings, DebugChunkGenerator> chunkgeneratortype1 = ChunkGeneratorType.DEBUG;
    BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeprovidertype = BiomeProviderType.FIXED;
    SingleBiomeProviderSettings singlebiomeprovidersettings = ((SingleBiomeProviderSettings)biomeprovidertype.createSettings(getWorld().getWorldInfo())).setBiome(Biomes.THE_VOID);
    return new ChallengesChunkGenerator((IWorld)this.world, biomeprovidertype.create(singlebiomeprovidersettings), chunkgeneratortype1.createSettings());
  }


  
  public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
    Random random = new Random(this.world.getSeed());
    BlockPos blockpos = new BlockPos(chunkPosIn.getXStart() + random.nextInt(15), 0, chunkPosIn.getZEnd() + random.nextInt(15));
    return this.world.getGroundAboveSeaLevel(blockpos).getMaterial().blocksMovement() ? blockpos : null;
  }


  
  public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
    return findSpawn(new ChunkPos(posX >> 4, posZ >> 4), checkValid);
  }


  
  public float calculateCelestialAngle(long worldTime, float partialTicks) {
    return 90.0F;
  }


  
  public boolean isSurfaceWorld() {
    return true;
  }


  
  public Vec3d getFogColor(float celestialAngle, float partialTicks) {
    return new Vec3d(0.05D, 0.05D, 0.05D);
  }


  
  public boolean canRespawnHere() {
    return false;
  }


  
  public boolean doesXZShowFog(int x, int z) {
    return true;
  }


  
  @Nullable
  @OnlyIn(Dist.CLIENT)
  public IRenderHandler getSkyRenderer() {
    return (IRenderHandler)this.skyRenderer;
  }
  
  private static class SkyRenderer implements SkyRenderHandler {
    private SkyRenderer() {}
    
    public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc) {}
  }
}


