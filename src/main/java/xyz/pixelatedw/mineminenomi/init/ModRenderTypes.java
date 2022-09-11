package xyz.pixelatedw.mineminenomi.init;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;






public class ModRenderTypes
  extends RenderType
{
  public ModRenderTypes(String name, VertexFormat format, int drawMode, int bufferSize, boolean useDelegate, boolean needsSorting, Runnable setupTask, Runnable clearTask) {
    super(name, format, drawMode, bufferSize, useDelegate, needsSorting, setupTask, clearTask);
  }
  
  private static final RenderType IMBUING = (RenderType)makeType("mineminenomi:imbuing", DefaultVertexFormats.POSITION_TEX, 7, 256, 
      RenderType.State.getBuilder()
      .texture(new RenderState.TextureState(ModResources.BUSOSHOKU_HAKI_ARM, true, false))
      .writeMask(COLOR_WRITE)
      .cull(CULL_DISABLED)
      .depthTest(DEPTH_EQUAL)
      .transparency(GLINT_TRANSPARENCY)
      .texturing(GLINT_TEXTURING)
      .build(false));
  
  public static final RenderType ENERGY = (RenderType)makeType("mineminenomi:energy", DefaultVertexFormats.POSITION_COLOR_LIGHTMAP, 7, 256, false, true, RenderType.State.getBuilder()
      .texture(RenderState.NO_TEXTURE)
      .cull(RenderState.CULL_ENABLED)
      .alpha(RenderState.DEFAULT_ALPHA)
      .transparency(RenderState.LIGHTNING_TRANSPARENCY)
      .build(true));
  
  public static final RenderType SOLID = (RenderType)makeType("mineminenomi:solid", DefaultVertexFormats.POSITION_COLOR_LIGHTMAP, 7, 256, false, true, RenderType.State.getBuilder()
      .texture(RenderState.NO_TEXTURE)
      .cull(RenderState.CULL_ENABLED)
      .build(true));
  
  public static final RenderType TRANSPARENT_COLOR = (RenderType)makeType("mineminenomitranslucent_color_notexture", DefaultVertexFormats.ENTITY, 7, 256, true, true, RenderType.State.getBuilder()
      .transparency(TRANSLUCENT_TRANSPARENCY)
      .texture(NO_TEXTURE)
      .cull(CULL_DISABLED)
      .lightmap(LIGHTMAP_ENABLED)
      .diffuseLighting(DIFFUSE_LIGHTING_ENABLED)
      .build(true));












  
  public static RenderType getImbuingRenderType() {
    RenderType.State state = RenderType.State.getBuilder().texture(new RenderState.TextureState(AtlasTexture.LOCATION_BLOCKS_TEXTURE, false, false)).transparency(TRANSLUCENT_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_ENABLED).alpha(DEFAULT_ALPHA).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).overlay(OVERLAY_ENABLED).build(true);
    
    return (RenderType)makeType("imbuing", DefaultVertexFormats.POSITION_TEX, 7, 256, false, false, state);
  }








  
  public static RenderType getEnergyRenderType() {
    RenderType.State state = RenderType.State.getBuilder().texture(RenderState.NO_TEXTURE).cull(RenderState.CULL_ENABLED).alpha(RenderState.DEFAULT_ALPHA).diffuseLighting(RenderState.DIFFUSE_LIGHTING_ENABLED).transparency(RenderState.LIGHTNING_TRANSPARENCY).build(true);
    
    return (RenderType)makeType("mineminenomienergy", DefaultVertexFormats.POSITION_COLOR_LIGHTMAP, 7, 256, false, true, state);
  }

  
  public static RenderType getAuraRenderType(ResourceLocation texture) {
    RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);






    
    RenderType.State state = RenderType.State.getBuilder().texture(textureState).transparency(TRANSLUCENT_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_DISABLED).alpha(DEFAULT_ALPHA).lightmap(LIGHTMAP_ENABLED).overlay(OVERLAY_ENABLED).build(true);
    
    return (RenderType)makeType("mineminenomiaura_color_notexture", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
  }

  
  public static RenderType getYomiRenderType(ResourceLocation texture) {
    RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);






    
    RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).diffuseLighting(RenderState.DiffuseLightingState.DIFFUSE_LIGHTING_ENABLED).cull(RenderState.CullState.CULL_DISABLED).texture(textureState).build(true);
    return (RenderType)makeType("mineminenomi:yomi", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
  }

  
  public static RenderType getZoanRenderType(ResourceLocation texture) {
    RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);






    
    RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).diffuseLighting(RenderState.DiffuseLightingState.DIFFUSE_LIGHTING_ENABLED).cull(RenderState.CullState.CULL_DISABLED).texture(textureState).build(true);
    return (RenderType)makeType("mineminenomi:zoan", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
  }

  
  public static RenderType getZoanWithCullingRenderType(ResourceLocation texture) {
    RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);





    
    RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).diffuseLighting(RenderState.DiffuseLightingState.DIFFUSE_LIGHTING_ENABLED).texture(textureState).build(true);
    return (RenderType)makeType("mineminenomi:zoan", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
  }

  
  public static RenderType getAbilityBody(ResourceLocation texture) {
    RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);





    
    RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).texture(textureState).cull(RenderState.CullState.CULL_DISABLED).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).build(true);
    return (RenderType)makeType("mineminenomi:ability_body", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
  }

  
  public static RenderType getAbilityHand(ResourceLocation texture) {
    RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);





    
    RenderType.State state = RenderType.State.getBuilder().transparency(RenderState.TransparencyState.TRANSLUCENT_TRANSPARENCY).alpha(RenderState.AlphaState.DEFAULT_ALPHA).texture(textureState).cull(RenderState.CullState.CULL_DISABLED).lightmap(RenderState.LightmapState.LIGHTMAP_ENABLED).build(true);
    return (RenderType)makeType("ability_hand", DefaultVertexFormats.ENTITY, 7, 256, true, true, state);
  }

  
  public static RenderType getWantedPoster(ResourceLocation texture) {
    RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
    RenderType.State state = RenderType.State.getBuilder().transparency(TRANSLUCENT_TRANSPARENCY).alpha(DEFAULT_ALPHA).texture(textureState).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).build(true);
    return (RenderType)makeType("wanted_poster", DefaultVertexFormats.BLOCK, 7, 256, true, true, state);
  }

  
  public static RenderType getWantedPosterExpiration(ResourceLocation texture) {
    RenderState.TextureState textureState = new RenderState.TextureState(texture, false, false);
    RenderType.State state = RenderType.State.getBuilder().transparency(TRANSLUCENT_TRANSPARENCY).alpha(DEFAULT_ALPHA).texture(textureState).cull(CULL_ENABLED).lightmap(LIGHTMAP_ENABLED).build(true);
    return (RenderType)makeType("wanted_poster_expiration", DefaultVertexFormats.BLOCK, 7, 256, true, true, state);
  }

  public static final VertexFormat PARTICLE_POSITION_TEX_COLOR_LMAP = new VertexFormat(ImmutableList.<VertexFormatElement>builder().add(DefaultVertexFormats.POSITION_3F).add(DefaultVertexFormats.TEX_2F).add(DefaultVertexFormats.COLOR_4UB).add(DefaultVertexFormats.TEX_2SB).build());
}


