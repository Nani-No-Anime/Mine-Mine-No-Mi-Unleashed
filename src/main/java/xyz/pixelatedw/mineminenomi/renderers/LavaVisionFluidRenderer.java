/*     */ package xyz.pixelatedw.mineminenomi.renderers;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.FluidBlockRenderer;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.model.ModelBakery;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.fluid.Fluid;
/*     */ import net.minecraft.fluid.IFluidState;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.util.math.shapes.VoxelShapes;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.ILightReader;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.ForgeHooksClient;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class LavaVisionFluidRenderer extends FluidBlockRenderer {
/*  27 */   private final TextureAtlasSprite[] atlasSpritesLava = new TextureAtlasSprite[2];
/*  28 */   private final TextureAtlasSprite[] atlasSpritesWater = new TextureAtlasSprite[2];
/*     */   
/*     */   protected void initAtlasSprites() {
/*  31 */     this.atlasSpritesLava[0] = Minecraft.getInstance().getModelManager().getBlockModelShapes().getModel(Blocks.LAVA.getDefaultState()).getParticleTexture();
/*  32 */     this.atlasSpritesLava[1] = ModelBakery.LOCATION_LAVA_FLOW.getSprite();
/*  33 */     this.atlasSpritesWater[0] = Minecraft.getInstance().getModelManager().getBlockModelShapes().getModel(Blocks.WATER.getDefaultState()).getParticleTexture();
/*  34 */     this.atlasSpritesWater[1] = ModelBakery.LOCATION_WATER_FLOW.getSprite();
/*     */   }
/*     */   
/*     */   private static boolean isAdjacentFluidSameAs(IBlockReader worldIn, BlockPos pos, Direction side, IFluidState state) {
/*  38 */     BlockPos blockpos = pos.offset(side);
/*  39 */     IFluidState ifluidstate = worldIn.getFluidState(blockpos);
/*  40 */     return ifluidstate.getFluid().isEquivalentTo(state.getFluid());
/*     */   }
/*     */   
/*     */   private static boolean isNeighbourSideCovered(IBlockReader reader, BlockPos pos, Direction face, float heightIn) {
/*  44 */     BlockPos blockpos = pos.offset(face);
/*  45 */     BlockState blockstate = reader.getBlockState(blockpos);
/*  46 */     if (blockstate.isSolid()) {
/*  47 */       VoxelShape voxelshape = VoxelShapes.create(0.0D, 0.0D, 0.0D, 1.0D, heightIn, 1.0D);
/*  48 */       VoxelShape voxelshape1 = blockstate.getRenderShape(reader, blockpos);
/*  49 */       return VoxelShapes.isCubeSideCovered(voxelshape, voxelshape1, face);
/*     */     } 
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean render(ILightReader lightReaderIn, BlockPos posIn, IVertexBuilder vertexBuilderIn, IFluidState fluidStateIn) {
/*  56 */     boolean flag = fluidStateIn.isTagged(FluidTags.LAVA);
/*  57 */     TextureAtlasSprite[] atextureatlassprite = ForgeHooksClient.getFluidSprites(lightReaderIn, posIn, fluidStateIn);
/*  58 */     int i = fluidStateIn.getFluid().getAttributes().getColor(lightReaderIn, posIn);
/*  59 */     float alpha = flag ? 0.5F : ((i >> 24 & 0xFF) / 255.0F);
/*  60 */     float f = (i >> 16 & 0xFF) / 255.0F;
/*  61 */     float f1 = (i >> 8 & 0xFF) / 255.0F;
/*  62 */     float f2 = (i & 0xFF) / 255.0F;
/*  63 */     boolean flag1 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.UP, fluidStateIn);
/*  64 */     boolean flag2 = (!isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.DOWN, fluidStateIn) && !isNeighbourSideCovered((IBlockReader)lightReaderIn, posIn, Direction.DOWN, 0.8888889F));
/*  65 */     boolean flag3 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.NORTH, fluidStateIn);
/*  66 */     boolean flag4 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.SOUTH, fluidStateIn);
/*  67 */     boolean flag5 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.WEST, fluidStateIn);
/*  68 */     boolean flag6 = !isAdjacentFluidSameAs((IBlockReader)lightReaderIn, posIn, Direction.EAST, fluidStateIn);
/*  69 */     if (!flag1 && !flag2 && !flag6 && !flag5 && !flag3 && !flag4) {
/*  70 */       return false;
/*     */     }
/*  72 */     boolean flag7 = false;
/*  73 */     float f7 = getFluidHeight((IBlockReader)lightReaderIn, posIn, fluidStateIn.getFluid());
/*  74 */     float f8 = getFluidHeight((IBlockReader)lightReaderIn, posIn.south(), fluidStateIn.getFluid());
/*  75 */     float f9 = getFluidHeight((IBlockReader)lightReaderIn, posIn.east().south(), fluidStateIn.getFluid());
/*  76 */     float f10 = getFluidHeight((IBlockReader)lightReaderIn, posIn.east(), fluidStateIn.getFluid());
/*  77 */     double d0 = (posIn.getX() & 0xF);
/*  78 */     double d1 = (posIn.getY() & 0xF);
/*  79 */     double d2 = (posIn.getZ() & 0xF);
/*  80 */     float f12 = flag2 ? 0.001F : 0.0F;
float f13=0;
float f14=0;
float f15=0;
float f16=0;
float f17=0;
float f18=0;
float f19=0;
float f20=0;
/*  81 */     if (flag1 && !isNeighbourSideCovered((IBlockReader)lightReaderIn, posIn, Direction.UP, Math.min(Math.min(f7, f8), Math.min(f9, f10)))) {
/*  82 */       flag7 = true;
/*  83 */       f7 -= 0.001F;
/*  84 */       f8 -= 0.001F;
/*  85 */       f9 -= 0.001F;
/*  86 */       f10 -= 0.001F;
/*  87 */       Vec3d vec3d = fluidStateIn.getFlow((IBlockReader)lightReaderIn, posIn);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  96 */       if (vec3d.x == 0.0D && vec3d.z == 0.0D) {
/*  97 */         TextureAtlasSprite textureatlassprite1 = atextureatlassprite[0];
/*  98 */         f13 = textureatlassprite1.getInterpolatedU(0.0D);
/*  99 */         f17 = textureatlassprite1.getInterpolatedV(0.0D);
/* 100 */         f14 = f13;
/* 101 */         f18 = textureatlassprite1.getInterpolatedV(16.0D);
/* 102 */         f15 = textureatlassprite1.getInterpolatedU(16.0D);
/* 103 */         f19 = f18;
/* 104 */         f16 = f15;
/* 105 */         f20 = f17;
/*     */       } else {
/* 107 */         TextureAtlasSprite textureatlassprite = atextureatlassprite[1];
/* 108 */         float f21 = (float)MathHelper.atan2(vec3d.z, vec3d.x) - 1.5707964F;
/* 109 */         float f22 = MathHelper.sin(f21) * 0.25F;
/* 110 */         float f23 = MathHelper.cos(f21) * 0.25F;
/* 111 */         float f24 = 8.0F;
/* 112 */         f13 = textureatlassprite.getInterpolatedU((8.0F + (-f23 - f22) * 16.0F));
/* 113 */         f17 = textureatlassprite.getInterpolatedV((8.0F + (-f23 + f22) * 16.0F));
/* 114 */         f14 = textureatlassprite.getInterpolatedU((8.0F + (-f23 + f22) * 16.0F));
/* 115 */         f18 = textureatlassprite.getInterpolatedV((8.0F + (f23 + f22) * 16.0F));
/* 116 */         f15 = textureatlassprite.getInterpolatedU((8.0F + (f23 + f22) * 16.0F));
/* 117 */         f19 = textureatlassprite.getInterpolatedV((8.0F + (f23 - f22) * 16.0F));
/* 118 */         f16 = textureatlassprite.getInterpolatedU((8.0F + (f23 - f22) * 16.0F));
/* 119 */         f20 = textureatlassprite.getInterpolatedV((8.0F + (-f23 - f22) * 16.0F));
/*     */       } 
/*     */       
/* 122 */       float f43 = (f13 + f14 + f15 + f16) / 4.0F;
/* 123 */       float f44 = (f17 + f18 + f19 + f20) / 4.0F;
/* 124 */       float f45 = atextureatlassprite[0].getWidth() / (atextureatlassprite[0].getMaxU() - atextureatlassprite[0].getMinU());
/* 125 */       float f46 = atextureatlassprite[0].getHeight() / (atextureatlassprite[0].getMaxV() - atextureatlassprite[0].getMinV());
/* 126 */       float f47 = 4.0F / Math.max(f46, f45);
/* 127 */       f13 = MathHelper.lerp(f47, f13, f43);
/* 128 */       f14 = MathHelper.lerp(f47, f14, f43);
/* 129 */       f15 = MathHelper.lerp(f47, f15, f43);
/* 130 */       f16 = MathHelper.lerp(f47, f16, f43);
/* 131 */       f17 = MathHelper.lerp(f47, f17, f44);
/* 132 */       f18 = MathHelper.lerp(f47, f18, f44);
/* 133 */       f19 = MathHelper.lerp(f47, f19, f44);
/* 134 */       f20 = MathHelper.lerp(f47, f20, f44);
/* 135 */       int j = getCombinedAverageLight(lightReaderIn, posIn);
/* 136 */       vertex(vertexBuilderIn, d0 + 0.0D, d1 + f7, d2 + 0.0D, f, f1, f2, alpha, f13, f17, j);
/* 137 */       vertex(vertexBuilderIn, d0 + 0.0D, d1 + f8, d2 + 1.0D, f, f1, f2, alpha, f14, f18, j);
/* 138 */       vertex(vertexBuilderIn, d0 + 1.0D, d1 + f9, d2 + 1.0D, f, f1, f2, alpha, f15, f19, j);
/* 139 */       vertex(vertexBuilderIn, d0 + 1.0D, d1 + f10, d2 + 0.0D, f, f1, f2, alpha, f16, f20, j);
/* 140 */       if (fluidStateIn.shouldRenderSides((IBlockReader)lightReaderIn, posIn.up())) {
/* 141 */         vertex(vertexBuilderIn, d0 + 0.0D, d1 + f7, d2 + 0.0D, f, f1, f2, alpha, f13, f17, j);
/* 142 */         vertex(vertexBuilderIn, d0 + 1.0D, d1 + f10, d2 + 0.0D, f, f1, f2, alpha, f16, f20, j);
/* 143 */         vertex(vertexBuilderIn, d0 + 1.0D, d1 + f9, d2 + 1.0D, f, f1, f2, alpha, f15, f19, j);
/* 144 */         vertex(vertexBuilderIn, d0 + 0.0D, d1 + f8, d2 + 1.0D, f, f1, f2, alpha, f14, f18, j);
/*     */       } 
/*     */     } 
/*     */     
/* 148 */     if (flag2) {
/* 149 */       float f34 = atextureatlassprite[0].getMinU();
/* 150 */       float f35 = atextureatlassprite[0].getMaxU();
/* 151 */       float f37 = atextureatlassprite[0].getMinV();
/* 152 */       float f39 = atextureatlassprite[0].getMaxV();
/* 153 */       int i1 = getCombinedAverageLight(lightReaderIn, posIn.down());
/* 154 */       float f40 = 0.5F * f;
/* 155 */       float f41 = 0.5F * f1;
/* 156 */       float f42 = 0.5F * f2;
/* 157 */       vertex(vertexBuilderIn, d0, d1 + f12, d2 + 1.0D, f40, f41, f42, alpha, f34, f39, i1);
/* 158 */       vertex(vertexBuilderIn, d0, d1 + f12, d2, f40, f41, f42, alpha, f34, f37, i1);
/* 159 */       vertex(vertexBuilderIn, d0 + 1.0D, d1 + f12, d2, f40, f41, f42, alpha, f35, f37, i1);
/* 160 */       vertex(vertexBuilderIn, d0 + 1.0D, d1 + f12, d2 + 1.0D, f40, f41, f42, alpha, f35, f39, i1);
/* 161 */       flag7 = true;
/*     */     } 
/*     */     
/* 164 */     for (int l = 0; l < 4; l++) {
/*     */       float f36;
/*     */       float f38;
/*     */       double d3;
/*     */       double d4;
/*     */       double d5;
/*     */       double d6;
/*     */       Direction direction;
/*     */       boolean flag8;
/* 173 */       if (l == 0) {
/* 174 */         f36 = f7;
/* 175 */         f38 = f10;
/* 176 */         d3 = d0;
/* 177 */         d5 = d0 + 1.0D;
/* 178 */         d4 = d2 + 0.0010000000474974513D;
/* 179 */         d6 = d2 + 0.0010000000474974513D;
/* 180 */         direction = Direction.NORTH;
/* 181 */         flag8 = flag3;
/* 182 */       } else if (l == 1) {
/* 183 */         f36 = f9;
/* 184 */         f38 = f8;
/* 185 */         d3 = d0 + 1.0D;
/* 186 */         d5 = d0;
/* 187 */         d4 = d2 + 1.0D - 0.0010000000474974513D;
/* 188 */         d6 = d2 + 1.0D - 0.0010000000474974513D;
/* 189 */         direction = Direction.SOUTH;
/* 190 */         flag8 = flag4;
/* 191 */       } else if (l == 2) {
/* 192 */         f36 = f8;
/* 193 */         f38 = f7;
/* 194 */         d3 = d0 + 0.0010000000474974513D;
/* 195 */         d5 = d0 + 0.0010000000474974513D;
/* 196 */         d4 = d2 + 1.0D;
/* 197 */         d6 = d2;
/* 198 */         direction = Direction.WEST;
/* 199 */         flag8 = flag5;
/*     */       } else {
/* 201 */         f36 = f10;
/* 202 */         f38 = f9;
/* 203 */         d3 = d0 + 1.0D - 0.0010000000474974513D;
/* 204 */         d5 = d0 + 1.0D - 0.0010000000474974513D;
/* 205 */         d4 = d2;
/* 206 */         d6 = d2 + 1.0D;
/* 207 */         direction = Direction.EAST;
/* 208 */         flag8 = flag6;
/*     */       } 
/*     */       
/* 211 */       if (flag8 && !isNeighbourSideCovered((IBlockReader)lightReaderIn, posIn, direction, Math.max(f36, f38))) {
/* 212 */         flag7 = true;
/* 213 */         BlockPos blockpos = posIn.offset(direction);
/* 214 */         TextureAtlasSprite textureatlassprite2 = atextureatlassprite[1];
/* 215 */         if (atextureatlassprite[2] != null && 
/* 216 */           lightReaderIn.getBlockState(blockpos).shouldDisplayFluidOverlay(lightReaderIn, blockpos, fluidStateIn)) {
/* 217 */           textureatlassprite2 = atextureatlassprite[2];
/*     */         }
/*     */ 
/*     */         
/* 221 */         float f48 = textureatlassprite2.getInterpolatedU(0.0D);
/* 222 */         float f49 = textureatlassprite2.getInterpolatedU(8.0D);
/* 223 */         float f50 = textureatlassprite2.getInterpolatedV(((1.0F - f36) * 16.0F * 0.5F));
/* 224 */         float f28 = textureatlassprite2.getInterpolatedV(((1.0F - f38) * 16.0F * 0.5F));
/* 225 */         float f29 = textureatlassprite2.getInterpolatedV(8.0D);
/* 226 */         int k = getCombinedAverageLight(lightReaderIn, blockpos);
/* 227 */         float f30 = (l < 2) ? 0.8F : 0.6F;
/* 228 */         float f31 = 1.0F * f30 * f;
/* 229 */         float f32 = 1.0F * f30 * f1;
/* 230 */         float f33 = 1.0F * f30 * f2;
/* 231 */         vertex(vertexBuilderIn, d3, d1 + f36, d4, f31, f32, f33, alpha, f48, f50, k);
/* 232 */         vertex(vertexBuilderIn, d5, d1 + f38, d6, f31, f32, f33, alpha, f49, f28, k);
/* 233 */         vertex(vertexBuilderIn, d5, d1 + f12, d6, f31, f32, f33, alpha, f49, f29, k);
/* 234 */         vertex(vertexBuilderIn, d3, d1 + f12, d4, f31, f32, f33, alpha, f48, f29, k);
/* 235 */         if (textureatlassprite2 != atextureatlassprite[2]) {
/* 236 */           vertex(vertexBuilderIn, d3, d1 + f12, d4, f31, f32, f33, alpha, f48, f29, k);
/* 237 */           vertex(vertexBuilderIn, d5, d1 + f12, d6, f31, f32, f33, alpha, f49, f29, k);
/* 238 */           vertex(vertexBuilderIn, d5, d1 + f38, d6, f31, f32, f33, alpha, f49, f28, k);
/* 239 */           vertex(vertexBuilderIn, d3, d1 + f36, d4, f31, f32, f33, alpha, f48, f50, k);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 244 */     return flag7;
/*     */   }
/*     */ 
/*     */   
/*     */   private void vertex(IVertexBuilder vertexBuilderIn, double x, double y, double z, float red, float green, float blue, float alpha, float u, float v, int packedLight) {
/* 249 */     vertexBuilderIn.pos(x, y, z).color(red, green, blue, alpha).tex(u, v).lightmap(packedLight).normal(0.0F, 1.0F, 0.0F).endVertex();
/*     */   }
/*     */   
/*     */   private int getCombinedAverageLight(ILightReader lightReaderIn, BlockPos posIn) {
/* 253 */     int i = WorldRenderer.getCombinedLight(lightReaderIn, posIn);
/* 254 */     int j = WorldRenderer.getCombinedLight(lightReaderIn, posIn.up());
/* 255 */     int k = i & 0xFF;
/* 256 */     int l = j & 0xFF;
/* 257 */     int i1 = i >> 16 & 0xFF;
/* 258 */     int j1 = j >> 16 & 0xFF;
/* 259 */     return Math.max(k, l) | Math.max(i1, j1) << 16;
/*     */   }
/*     */   
/*     */   private float getFluidHeight(IBlockReader reader, BlockPos pos, Fluid fluidIn) {
/* 263 */     int i = 0;
/* 264 */     float f = 0.0F;
/*     */     
/* 266 */     for (int j = 0; j < 4; j++) {
/* 267 */       BlockPos blockpos = pos.add(-(j & 0x1), 0, -(j >> 1 & 0x1));
/* 268 */       if (reader.getFluidState(blockpos.up()).getFluid().isEquivalentTo(fluidIn)) {
/* 269 */         return 1.0F;
/*     */       }
/*     */       
/* 272 */       IFluidState ifluidstate = reader.getFluidState(blockpos);
/* 273 */       if (ifluidstate.getFluid().isEquivalentTo(fluidIn)) {
/* 274 */         float f1 = ifluidstate.getActualHeight(reader, blockpos);
/* 275 */         if (f1 >= 0.8F) {
/* 276 */           f += f1 * 10.0F;
/* 277 */           i += 10;
/*     */         } else {
/* 279 */           f += f1;
/* 280 */           i++;
/*     */         } 
/* 282 */       } else if (!reader.getBlockState(blockpos).getMaterial().isSolid()) {
/* 283 */         i++;
/*     */       } 
/*     */     } 
/*     */     
/* 287 */     return f / i;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\LavaVisionFluidRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */