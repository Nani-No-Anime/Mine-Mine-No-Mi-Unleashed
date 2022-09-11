package xyz.pixelatedw.mineminenomi.renderers;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FluidBlockRenderer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ILightReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;

@OnlyIn(Dist.CLIENT)
public class LavaVisionFluidRenderer extends FluidBlockRenderer {
  private final TextureAtlasSprite[] atlasSpritesLava = new TextureAtlasSprite[2];
  private final TextureAtlasSprite[] atlasSpritesWater = new TextureAtlasSprite[2];
  
  protected void initAtlasSprites() {
    this.atlasSpritesLava[0] = Minecraft.getInstance().getModelManager().getBlockModelShapes().getModel(Blocks.LAVA.getDefaultState()).getParticleTexture();
    this.atlasSpritesLava[1] = ModelBakery.LOCATION_LAVA_FLOW.getSprite();
    this.atlasSpritesWater[0] = Minecraft.getInstance().getModelManager().getBlockModelShapes().getModel(Blocks.WATER.getDefaultState()).getParticleTexture();
    this.atlasSpritesWater[1] = ModelBakery.LOCATION_WATER_FLOW.getSprite();
  }
  
  private static boolean isAdjacentFluidSameAs(IBlockReader worldIn, BlockPos pos, Direction side, IFluidState state) {
    BlockPos blockpos = pos.offset(side);
    IFluidState ifluidstate = worldIn.getFluidState(blockpos);
    return ifluidstate.getFluid().isEquivalentTo(state.getFluid());
  }
  
  private static boolean isNeighbourSideCovered(IBlockReader reader, BlockPos pos, Direction face, float heightIn) {
    BlockPos blockpos = pos.offset(face);
    BlockState blockstate = reader.getBlockState(blockpos);
    if (blockstate.isSolid()) {
      VoxelShape voxelshape = VoxelShapes.create(0.0D, 0.0D, 0.0D, 1.0D, heightIn, 1.0D);
      VoxelShape voxelshape1 = blockstate.getRenderShape(reader, blockpos);
      return VoxelShapes.isCubeSideCovered(voxelshape, voxelshape1, face);
    } 
    return false;
  }

  
  public boolean render(ILightReader lightReaderIn, BlockPos posIn, IVertexBuilder vertexBuilderIn, IFluidState fluidStateIn) {
    boolean flag = fluidStateIn.isTagged(FluidTags.LAVA);
    TextureAtlasSprite[] atextureatlassprite = ForgeHooksClient.getFluidSprites(lightReaderIn, posIn, fluidStateIn);
    int i = fluidStateIn.getFluid().getAttributes().getColor(lightReaderIn, posIn);
    float alpha = flag ? 0.5F : ((i >> 24 & 0xFF) / 255.0F);
    float f = (i >> 16 & 0xFF) / 255.0F;
    float f1 = (i >> 8 & 0xFF) / 255.0F;
    float f2 = (i & 0xFF) / 255.0F;
    boolean flag1 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.UP, fluidStateIn);
    boolean flag2 = (!isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.DOWN, fluidStateIn) && !isNeighbourSideCovered((IBlockReader)lightReaderIn, posIn, Direction.DOWN, 0.8888889F));
    boolean flag3 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.NORTH, fluidStateIn);
    boolean flag4 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.SOUTH, fluidStateIn);
    boolean flag5 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.WEST, fluidStateIn);
    boolean flag6 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.EAST, fluidStateIn);
    if (!flag1 && !flag2 && !flag6 && !flag5 && !flag3 && !flag4) {
      return false;
    }
    boolean flag7 = false;
    float f7 = getFluidHeight((IBlockReader)lightReaderIn, posIn, fluidStateIn.getFluid());
    float f8 = getFluidHeight((IBlockReader)lightReaderIn, posIn.south(), fluidStateIn.getFluid());
    float f9 = getFluidHeight((IBlockReader)lightReaderIn, posIn.east().south(), fluidStateIn.getFluid());
    float f10 = getFluidHeight((IBlockReader)lightReaderIn, posIn.east(), fluidStateIn.getFluid());
    double d0 = (posIn.getX() & 0xF);
    double d1 = (posIn.getY() & 0xF);
    double d2 = (posIn.getZ() & 0xF);
    float f12 = flag2 ? 0.001F : 0.0F;
float f13=0;
float f14=0;
float f15=0;
float f16=0;
float f17=0;
float f18=0;
float f19=0;
float f20=0;
    if (flag1 && !isNeighbourSideCovered((IBlockReader)lightReaderIn, posIn, Direction.UP, Math.min(Math.min(f7, f8), Math.min(f9, f10)))) {
      flag7 = true;
      f7 -= 0.001F;
      f8 -= 0.001F;
      f9 -= 0.001F;
      f10 -= 0.001F;
      Vec3d vec3d = fluidStateIn.getFlow((IBlockReader)lightReaderIn, posIn);







      
      if (vec3d.x == 0.0D && vec3d.z == 0.0D) {
        TextureAtlasSprite textureatlassprite1 = atextureatlassprite[0];
        f13 = textureatlassprite1.getInterpolatedU(0.0D);
        f17 = textureatlassprite1.getInterpolatedV(0.0D);
        f14 = f13;
        f18 = textureatlassprite1.getInterpolatedV(16.0D);
        f15 = textureatlassprite1.getInterpolatedU(16.0D);
        f19 = f18;
        f16 = f15;
        f20 = f17;
      } else {
        TextureAtlasSprite textureatlassprite = atextureatlassprite[1];
        float f21 = (float)MathHelper.atan2(vec3d.z, vec3d.x) - 1.5707964F;
        float f22 = MathHelper.sin(f21) * 0.25F;
        float f23 = MathHelper.cos(f21) * 0.25F;
        float f24 = 8.0F;
        f13 = textureatlassprite.getInterpolatedU((8.0F + (-f23 - f22) * 16.0F));
        f17 = textureatlassprite.getInterpolatedV((8.0F + (-f23 + f22) * 16.0F));
        f14 = textureatlassprite.getInterpolatedU((8.0F + (-f23 + f22) * 16.0F));
        f18 = textureatlassprite.getInterpolatedV((8.0F + (f23 + f22) * 16.0F));
        f15 = textureatlassprite.getInterpolatedU((8.0F + (f23 + f22) * 16.0F));
        f19 = textureatlassprite.getInterpolatedV((8.0F + (f23 - f22) * 16.0F));
        f16 = textureatlassprite.getInterpolatedU((8.0F + (f23 - f22) * 16.0F));
        f20 = textureatlassprite.getInterpolatedV((8.0F + (-f23 - f22) * 16.0F));
      } 
      
      float f43 = (f13 + f14 + f15 + f16) / 4.0F;
      float f44 = (f17 + f18 + f19 + f20) / 4.0F;
      float f45 = atextureatlassprite[0].getWidth() / (atextureatlassprite[0].getMaxU() - atextureatlassprite[0].getMinU());
      float f46 = atextureatlassprite[0].getHeight() / (atextureatlassprite[0].getMaxV() - atextureatlassprite[0].getMinV());
      float f47 = 4.0F / Math.max(f46, f45);
      f13 = MathHelper.lerp(f47, f13, f43);
      f14 = MathHelper.lerp(f47, f14, f43);
      f15 = MathHelper.lerp(f47, f15, f43);
      f16 = MathHelper.lerp(f47, f16, f43);
      f17 = MathHelper.lerp(f47, f17, f44);
      f18 = MathHelper.lerp(f47, f18, f44);
      f19 = MathHelper.lerp(f47, f19, f44);
      f20 = MathHelper.lerp(f47, f20, f44);
      int j = getCombinedAverageLight(lightReaderIn, posIn);
      vertex(vertexBuilderIn, d0 + 0.0D, d1 + f7, d2 + 0.0D, f, f1, f2, alpha, f13, f17, j);
      vertex(vertexBuilderIn, d0 + 0.0D, d1 + f8, d2 + 1.0D, f, f1, f2, alpha, f14, f18, j);
      vertex(vertexBuilderIn, d0 + 1.0D, d1 + f9, d2 + 1.0D, f, f1, f2, alpha, f15, f19, j);
      vertex(vertexBuilderIn, d0 + 1.0D, d1 + f10, d2 + 0.0D, f, f1, f2, alpha, f16, f20, j);
      if (fluidStateIn.shouldRenderSides((IBlockReader)lightReaderIn, posIn.up())) {
        vertex(vertexBuilderIn, d0 + 0.0D, d1 + f7, d2 + 0.0D, f, f1, f2, alpha, f13, f17, j);
        vertex(vertexBuilderIn, d0 + 1.0D, d1 + f10, d2 + 0.0D, f, f1, f2, alpha, f16, f20, j);
        vertex(vertexBuilderIn, d0 + 1.0D, d1 + f9, d2 + 1.0D, f, f1, f2, alpha, f15, f19, j);
        vertex(vertexBuilderIn, d0 + 0.0D, d1 + f8, d2 + 1.0D, f, f1, f2, alpha, f14, f18, j);
      } 
    } 
    
    if (flag2) {
      float f34 = atextureatlassprite[0].getMinU();
      float f35 = atextureatlassprite[0].getMaxU();
      float f37 = atextureatlassprite[0].getMinV();
      float f39 = atextureatlassprite[0].getMaxV();
      int i1 = getCombinedAverageLight(lightReaderIn, posIn.down());
      float f40 = 0.5F * f;
      float f41 = 0.5F * f1;
      float f42 = 0.5F * f2;
      vertex(vertexBuilderIn, d0, d1 + f12, d2 + 1.0D, f40, f41, f42, alpha, f34, f39, i1);
      vertex(vertexBuilderIn, d0, d1 + f12, d2, f40, f41, f42, alpha, f34, f37, i1);
      vertex(vertexBuilderIn, d0 + 1.0D, d1 + f12, d2, f40, f41, f42, alpha, f35, f37, i1);
      vertex(vertexBuilderIn, d0 + 1.0D, d1 + f12, d2 + 1.0D, f40, f41, f42, alpha, f35, f39, i1);
      flag7 = true;
    } 
    
    for (int l = 0; l < 4; l++) {
      float f36;
      float f38;
      double d3;
      double d4;
      double d5;
      double d6;
      Direction direction;
      boolean flag8;
      if (l == 0) {
        f36 = f7;
        f38 = f10;
        d3 = d0;
        d5 = d0 + 1.0D;
        d4 = d2 + 0.0010000000474974513D;
        d6 = d2 + 0.0010000000474974513D;
        direction = Direction.NORTH;
        flag8 = flag3;
      } else if (l == 1) {
        f36 = f9;
        f38 = f8;
        d3 = d0 + 1.0D;
        d5 = d0;
        d4 = d2 + 1.0D - 0.0010000000474974513D;
        d6 = d2 + 1.0D - 0.0010000000474974513D;
        direction = Direction.SOUTH;
        flag8 = flag4;
      } else if (l == 2) {
        f36 = f8;
        f38 = f7;
        d3 = d0 + 0.0010000000474974513D;
        d5 = d0 + 0.0010000000474974513D;
        d4 = d2 + 1.0D;
        d6 = d2;
        direction = Direction.WEST;
        flag8 = flag5;
      } else {
        f36 = f10;
        f38 = f9;
        d3 = d0 + 1.0D - 0.0010000000474974513D;
        d5 = d0 + 1.0D - 0.0010000000474974513D;
        d4 = d2;
        d6 = d2 + 1.0D;
        direction = Direction.EAST;
        flag8 = flag6;
      } 
      
      if (flag8 && !isNeighbourSideCovered((IBlockReader)lightReaderIn, posIn, direction, Math.max(f36, f38))) {
        flag7 = true;
        BlockPos blockpos = posIn.offset(direction);
        TextureAtlasSprite textureatlassprite2 = atextureatlassprite[1];
        if (atextureatlassprite[2] != null && 
          lightReaderIn.getBlockState(blockpos).shouldDisplayFluidOverlay(lightReaderIn, blockpos, fluidStateIn)) {
          textureatlassprite2 = atextureatlassprite[2];
        }

        
        float f48 = textureatlassprite2.getInterpolatedU(0.0D);
        float f49 = textureatlassprite2.getInterpolatedU(8.0D);
        float f50 = textureatlassprite2.getInterpolatedV(((1.0F - f36) * 16.0F * 0.5F));
        float f28 = textureatlassprite2.getInterpolatedV(((1.0F - f38) * 16.0F * 0.5F));
        float f29 = textureatlassprite2.getInterpolatedV(8.0D);
        int k = getCombinedAverageLight(lightReaderIn, blockpos);
        float f30 = (l < 2) ? 0.8F : 0.6F;
        float f31 = 1.0F * f30 * f;
        float f32 = 1.0F * f30 * f1;
        float f33 = 1.0F * f30 * f2;
        vertex(vertexBuilderIn, d3, d1 + f36, d4, f31, f32, f33, alpha, f48, f50, k);
        vertex(vertexBuilderIn, d5, d1 + f38, d6, f31, f32, f33, alpha, f49, f28, k);
        vertex(vertexBuilderIn, d5, d1 + f12, d6, f31, f32, f33, alpha, f49, f29, k);
        vertex(vertexBuilderIn, d3, d1 + f12, d4, f31, f32, f33, alpha, f48, f29, k);
        if (textureatlassprite2 != atextureatlassprite[2]) {
          vertex(vertexBuilderIn, d3, d1 + f12, d4, f31, f32, f33, alpha, f48, f29, k);
          vertex(vertexBuilderIn, d5, d1 + f12, d6, f31, f32, f33, alpha, f49, f29, k);
          vertex(vertexBuilderIn, d5, d1 + f38, d6, f31, f32, f33, alpha, f49, f28, k);
          vertex(vertexBuilderIn, d3, d1 + f36, d4, f31, f32, f33, alpha, f48, f50, k);
        } 
      } 
    } 
    
    return flag7;
  }

  
  private void vertex(IVertexBuilder vertexBuilderIn, double x, double y, double z, float red, float green, float blue, float alpha, float u, float v, int packedLight) {
    vertexBuilderIn.pos(x, y, z).color(red, green, blue, alpha).tex(u, v).lightmap(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
  }
  
  private int getCombinedAverageLight(ILightReader lightReaderIn, BlockPos posIn) {
    int i = WorldRenderer.getCombinedLight(lightReaderIn, posIn);
    int j = WorldRenderer.getCombinedLight(lightReaderIn, posIn.up());
    int k = i & 0xFF;
    int l = j & 0xFF;
    int i1 = i >> 16 & 0xFF;
    int j1 = j >> 16 & 0xFF;
    return Math.max(k, l) | Math.max(i1, j1) << 16;
  }
  
  private float getFluidHeight(IBlockReader reader, BlockPos pos, Fluid fluidIn) {
    int i = 0;
    float f = 0.0F;
    
    for (int j = 0; j < 4; j++) {
      BlockPos blockpos = pos.add(-(j & 0x1), 0, -(j >> 1 & 0x1));
      if (reader.getFluidState(blockpos.up()).getFluid().isEquivalentTo(fluidIn)) {
        return 1.0F;
      }
      
      IFluidState ifluidstate = reader.getFluidState(blockpos);
      if (ifluidstate.getFluid().isEquivalentTo(fluidIn)) {
        float f1 = ifluidstate.getActualHeight(reader, blockpos);
        if (f1 >= 0.8F) {
          f += f1 * 10.0F;
          i += 10;
        } else {
          f += f1;
          i++;
        } 
      } else if (!reader.getBlockState(blockpos).getMaterial().isSolid()) {
        i++;
      } 
    } 
    
    return f / i;
  }
}


