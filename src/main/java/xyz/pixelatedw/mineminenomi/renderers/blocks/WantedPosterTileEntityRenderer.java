package xyz.pixelatedw.mineminenomi.renderers.blocks;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.state.IProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterModel;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.UUID;

public class WantedPosterTileEntityRenderer
  extends TileEntityRenderer<WantedPosterTileEntity>
{
  private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/wantedposter.png");
  private static final String FONT_HEX_COLOR = "#513413";
  private WantedPosterModel posterModel;
  private final ModelRenderer face = new ModelRenderer(64, 64, 7, 7);

  
  public WantedPosterTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
    super(dispatcher);
    this.posterModel = new WantedPosterModel();
    this.face.addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 1.0F, 0.0F);
  }


  
  public void render(WantedPosterTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
    BlockState blockstate = tileEntity.getBlockState();
    
    String name = tileEntity.getEntityName();
    String uuid = tileEntity.getUUID();
    ResourceLocation rs = null;
    
    matrixStack.push();
    
    matrixStack.translate(0.5D, 0.5D, 0.5D);
    matrixStack.rotate(new Quaternion(Vector3f.YP, -((Direction)blockstate.get((IProperty)WantedPosterBlock.FACING)).getHorizontalAngle() + 180.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));

    
    matrixStack.push();
    
    matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
    matrixStack.translate(-0.36D, 0.6D, 0.5D);
    matrixStack.scale(0.6F, -0.6F, -0.01F);
    IVertexBuilder ivertexbuilder = buffer.getBuffer(ModRenderTypes.getWantedPoster(TEXTURE));
    this.posterModel.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    
    matrixStack.pop();
    
    if (WyHelper.isNullOrEmpty(name)) {
      
      matrixStack.pop();
      
      return;
    } 
    ExtendedWorldData worldData = ExtendedWorldData.get(tileEntity.getWorld());
    
    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
    if (WyHelper.isNullOrEmpty(tileEntity.getPosterBounty()))
      tileEntity.setPosterBounty("0"); 
    String bounty = "0";
    
    try {
      bounty = decimalFormat.format(Long.parseLong(tileEntity.getPosterBounty()));
    }
    catch (Exception e) {
      
      bounty = "0";
      e.printStackTrace();
    } 

    
    matrixStack.push();

    
    if (worldData.getBounty(uuid) != Long.parseLong(bounty.replaceAll("\\D+", ""))) {
      
      matrixStack.push();
      
      matrixStack.translate(-0.35D, -0.389D, 0.496D);
      matrixStack.scale(0.173F, 0.132F, 0.001F);
      IVertexBuilder iVertexBuilder = buffer.getBuffer(ModRenderTypes.getWantedPosterExpiration(ModResources.EXPIRED));
      RendererHelper.drawQuad(matrixStack, iVertexBuilder, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 3.9F, 3.9F);
      
      matrixStack.pop();
    } 

    
    matrixStack.push();
    
    if (tileEntity.getGameProfile() != null) {
      
      Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.getInstance().getSkinManager().loadSkinFromCache(tileEntity.getGameProfile());
      if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
        rs = Minecraft.getInstance().getSkinManager().loadSkin(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
      } else {
        rs = DefaultPlayerSkin.getDefaultSkin(UUID.fromString(uuid));
      } 
    } else {
      rs = DefaultPlayerSkin.getDefaultSkin(UUID.fromString(uuid));
    }  matrixStack.translate(-0.21D, -0.32D, 0.498D);
    matrixStack.scale(0.8F, 0.8F, 0.001F);
    IVertexBuilder iVertexBuilder1 = buffer.getBuffer(ModRenderTypes.getWantedPoster(rs));
    this.face.render(matrixStack, iVertexBuilder1, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    
    matrixStack.pop();

    
    matrixStack.push();
    
    if (tileEntity.isPirate()) {
      
      Crew crew = worldData.getCrewWithMember(UUID.fromString(uuid));
      if (crew != null)
      {
        matrixStack.translate(0.10999999940395355D, -0.09000000357627869D, 0.4970000088214874D);
        matrixStack.scale(0.2F, 0.2F, 1.0F);
        RendererHelper.drawPlayerJollyRoger(crew.getJollyRoger(), matrixStack, buffer);
      }
    
    } else if (tileEntity.isRevolutionary()) {
      
      matrixStack.translate(-0.05000000074505806D, -0.20999999344348907D, 0.4970000088214874D);
      iVertexBuilder1 = buffer.getBuffer(ModRenderTypes.getWantedPoster(ModResources.REVOLUTIONARY_ARMY_ICON));
      RendererHelper.drawQuad(matrixStack, iVertexBuilder1, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.5F, 0.5F);
    } 
    
    matrixStack.pop();

    
    matrixStack.push();
    
    matrixStack.translate(-0.35D, -0.389D, 0.499D);
    matrixStack.scale(0.173F, 0.132F, 0.001F);
    ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/gui/wantedposters/backgrounds/" + tileEntity.getBackground() + ".jpg");
    IVertexBuilder iVertexBuilder2 = buffer.getBuffer(ModRenderTypes.getWantedPoster(texture));
    RendererHelper.drawQuad(matrixStack, iVertexBuilder2, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 3.9F, 3.9F);
    
    matrixStack.pop();
    
    matrixStack.pop();

    
    matrixStack.push();
    
    matrixStack.scale(0.0075F, 0.008F, 0.0075F);
    matrixStack.translate(-12.0D, 29.9D, 66.6D);
    
    if (name.length() > 13)
      name = name.substring(0, 10) + "..."; 
    this.renderDispatcher.getFontRenderer().renderString(TextFormatting.BOLD + name, 9.0F - this.renderDispatcher.getFontRenderer().getStringWidth(name) / 1.75F, -1.0F, WyHelper.hexToRGB("#513413").getRGB(), false, matrixStack.getLast().getMatrix(), buffer, false, 0, combinedLight);
    
    matrixStack.scale(1.2F, 1.2F, 1.2F);
    
    boolean flag = (bounty.length() > 9);
    
    if (flag) {
      
      matrixStack.push();
      matrixStack.translate(-5.0D, 1.5D, 0.0D);
      matrixStack.scale(0.72F, 0.89F, 1.005F);
    } 
    this.renderDispatcher.getFontRenderer().renderString(TextFormatting.BOLD + bounty, -18.0F, 9.5F, WyHelper.hexToRGB("#513413").getRGB(), false, matrixStack.getLast().getMatrix(), buffer, false, 0, combinedLight);
    if (flag) {
      matrixStack.pop();
    }
    matrixStack.scale(0.7F, 0.9F, 0.8F);
    this.renderDispatcher.getFontRenderer().renderString(TextFormatting.BOLD + tileEntity.getIssuedDate(), -40.0F, 25.0F, WyHelper.hexToRGB("#513413").getRGB(), false, matrixStack.getLast().getMatrix(), buffer, false, 0, combinedLight);
    
    matrixStack.pop();
    
    matrixStack.pop();
  }
}


