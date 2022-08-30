/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import net.minecraft.client.renderer.RenderState;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.texture.AtlasTexture;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModRenderTypes
/*     */   extends RenderType
/*     */ {
/*     */   public ModRenderTypes(String name, VertexFormat format, int drawMode, int bufferSize, boolean useDelegate, boolean needsSorting, Runnable setupTask, Runnable clearTask) {
/*  20 */     super(name, format, drawMode, bufferSize, useDelegate, needsSorting, setupTask, clearTask);
/*     */   }
/*     */   
/*  23 */   private static final RenderType IMBUING = (RenderType)makeType("mineminenomi:imbuing", DefaultVertexFormats.POSITION_TEX, 7, 256, 
/*  24 */       RenderType.State.getBuilder()
/*  25 */       .texture(new RenderState.TextureState(ModResources.BUSOSHOKU_HAKI_ARM, true, false))
/*  26 */       .writeMask(COLOR_WRITE)
/*  27 */       .cull(CULL_DISABLED)
/*  28 */       .depthTest(DEPTH_EQUAL)
/*  29 */       .transparency(GLINT_TRANSPARENCY)
/*  30 */       .texturing(GLINT_TEXTURING)
/*  31 */       .build(false));
/*     */   
/*  33 */   public static final RenderType ENERGY = (RenderType)makeType("mineminenomi:energy", DefaultVertexFormats.POSITION_COLOR_LIGHTMAP, 7, 256, false, true, RenderType.State.getBuilder()
/*  34 */       .texture(RenderState.NO_TEXTURE)
/*  35 */       .cull(RenderState.CULL_ENABLED)
/*  36 */       .alpha(RenderState.DEFAULT_ALPHA)
/*  37 */       .transparency(RenderState.LIGHTNING_TRANSPARENCY)
/*  38 */       .build(true));
/*     */   
/*  40 */   public static final RenderType SOLID = (RenderType)makeType("mineminenomi:solid", DefaultVertexFormats.POSITION_COLOR_LIGHTMAP, 7, 256, false, true, RenderType.State.getBuilder()
/*  41 */       .texture(RenderState.NO_TEXTURE)
/*  42 */       .cull(RenderState.CULL_ENABLED)
/*  43 */       .build(true));
/*     */   
/*  45 */   public static final RenderType TRANSPARENT_COLOR = (RenderType)makeType("mineminenomitranslucent_color_notexture", DefaultVertexFormats.ENTITY, 7, 256, true, true, RenderType.State.getBuilder()
/*  46 */       .transparency(TRANSLUCENT_TRANSPARENCY)
/*  47 */       .texture(NO_TEXTURE)
/*  48 */       .cull(CULL_DISABLED)
/*  49 */       .lightmap(LIGHTMAP_ENABLED)
/*  50 */       .diffuseLighting(DIFFUSE_LIGHTING_ENABLED)
/*  51 */       .build(true));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RenderType getImbuingRenderType() {
/*  66 */     RenderType.State state = RenderType.State.getBuilder().texture(new RenderState.TextureState(AtlasTexture.LOCATION_BLOCKS_TEXTURE, false, false)).transparency(TRANSLUCENT_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_ENABLED).alpha(DEFAULT_ALPHA).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).overlay(OVERLAY_ENABLED).build(true);
/*     */     
/*  68 */     return (RenderType)makeType("imbuing", DefaultVertexFormats.POSITION_TEX, 7, 256, false, false, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RenderType getEnergyRenderType() {
/*  80 */     RenderType.State state = RenderType.State.getBuilder().texture(RenderState.NO_TEXTURE).cull(RenderState.CULL_ENABLED).alpha(RenderState.DEFAULT_ALPHA).diffuseLighting(RenderState.DIFFUSE_LIGHTING_ENABLED).transparency(RenderState.LIGHTNING_TRANSPARENCY).build(true);
/*     */     
/*  82 */     return (RenderType)makeType("mineminenomienergy", DefaultVertexFormats.POSITION_COLOR_LIGHTMAP, 7, 256, false, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getAuraRenderType(ResourceLocation texture) {
/*  87 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     RenderType.State state = RenderType.State.getBuilder().texture(textureState).transparency(TRANSLUCENT_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_DISABLED).alpha(DEFAULT_ALPHA).lightmap(LIGHTMAP_ENABLED).overlay(OVERLAY_ENABLED).build(true);
/*     */     
/*  97 */     return (RenderType)makeType("mineminenomiaura_color_notexture", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getYomiRenderType(ResourceLocation texture) {
/* 102 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).diffuseLighting(RenderState.DiffuseLightingState.DIFFUSE_LIGHTING_ENABLED).cull(RenderState.CullState.CULL_DISABLED).texture(textureState).build(true);
/* 111 */     return (RenderType)makeType("mineminenomi:yomi", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getZoanRenderType(ResourceLocation texture) {
/* 116 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).diffuseLighting(RenderState.DiffuseLightingState.DIFFUSE_LIGHTING_ENABLED).cull(RenderState.CullState.CULL_DISABLED).texture(textureState).build(true);
/* 125 */     return (RenderType)makeType("mineminenomi:zoan", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getZoanWithCullingRenderType(ResourceLocation texture) {
/* 130 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).diffuseLighting(RenderState.DiffuseLightingState.DIFFUSE_LIGHTING_ENABLED).texture(textureState).build(true);
/* 138 */     return (RenderType)makeType("mineminenomi:zoan", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getAbilityBody(ResourceLocation texture) {
/* 143 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).texture(textureState).cull(RenderState.CullState.CULL_DISABLED).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).build(true);
/* 151 */     return (RenderType)makeType("mineminenomi:ability_body", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getAbilityHand(ResourceLocation texture) {
/* 156 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).texture(textureState).cull(RenderState.CullState.CULL_DISABLED).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).build(true);
/* 164 */     return (RenderType)makeType("ability_hand", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getWantedPoster(ResourceLocation texture) {
/* 169 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/* 170 */     RenderType.State state = RenderType.State.getBuilder().transparency(TRANSLUCENT_TRANSPARENCY).alpha(DEFAULT_ALPHA).texture(textureState).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).build(true);
/* 171 */     return (RenderType)makeType("wanted_poster", DefaultVertexFormats.BLOCK, 7, 256, true, true, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RenderType getWantedPosterExpiration(ResourceLocation texture) {
/* 176 */     RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
/* 177 */     RenderType.State state = RenderType.State.getBuilder().transparency(TRANSLUCENT_TRANSPARENCY).alpha(DEFAULT_ALPHA).texture(textureState).cull(CULL_ENABLED).lightmap(LIGHTMAP_ENABLED).build(true);
/* 178 */     return (RenderType)makeType("wanted_poster_expiration", DefaultVertexFormats.BLOCK, 7, 256, true, true, state);
/*     */   }
/*     */
/* 181 */   public static final VertexFormat PARTICLE_POSITION_TEX_COLOR_LMAP = new VertexFormat(ImmutableList.<VertexFormatElement>builder().add(DefaultVertexFormats.POSITION_3F).add(DefaultVertexFormats.TEX_2F).add(DefaultVertexFormats.COLOR_4UB).add(DefaultVertexFormats.TEX_2SB).build());
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModRenderTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */