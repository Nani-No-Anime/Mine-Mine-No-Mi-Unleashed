package xyz.pixelatedw.mineminenomi.renderers.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity;
import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterPackageModel;

public class WantedPostersPackageTileEntityRenderer
  extends TileEntityRenderer<WantedPosterPackageTileEntity>
{
  private WantedPosterPackageModel model;
  private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/wantedposterspackage.png");

  
  public WantedPostersPackageTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
    super(rendererDispatcherIn);
    this.model = new WantedPosterPackageModel();
  }


  
  public void render(WantedPosterPackageTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
    matrixStack.push();
    
    matrixStack.translate(0.5D, 1.6D, 0.5D);
    matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
    
    this.model.parachute.showModel = false;
    RenderType type = RenderType.getEntitySolid(TEXTURE);
    IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
    this.model.render(matrixStack, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    
    matrixStack.pop();
  }
}


