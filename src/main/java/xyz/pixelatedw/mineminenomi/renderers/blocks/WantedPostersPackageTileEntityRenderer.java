/*    */ package xyz.pixelatedw.mineminenomi.renderers.blocks;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Quaternion;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterPackageModel;
/*    */ 
/*    */ public class WantedPostersPackageTileEntityRenderer
/*    */   extends TileEntityRenderer<WantedPosterPackageTileEntity>
/*    */ {
/*    */   private WantedPosterPackageModel model;
/* 21 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/wantedposterspackage.png");
/*    */ 
/*    */   
/*    */   public WantedPostersPackageTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
/* 25 */     super(rendererDispatcherIn);
/* 26 */     this.model = new WantedPosterPackageModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(WantedPosterPackageTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
/* 32 */     matrixStack.push();
/*    */     
/* 34 */     matrixStack.translate(0.5D, 1.6D, 0.5D);
/* 35 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
/*    */     
/* 37 */     this.model.parachute.showModel = false;
/* 38 */     RenderType type = RenderType.getEntitySolid(TEXTURE);
/* 39 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 40 */     this.model.render(matrixStack, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 42 */     matrixStack.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\blocks\WantedPostersPackageTileEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */