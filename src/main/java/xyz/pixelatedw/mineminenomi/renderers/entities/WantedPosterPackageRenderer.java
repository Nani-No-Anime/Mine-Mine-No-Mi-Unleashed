/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Quaternion;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterPackageModel;
/*    */ 
/*    */ public class WantedPosterPackageRenderer<T extends WantedPosterPackageEntity>
/*    */   extends EntityRenderer<T>
/*    */ {
/* 21 */   private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/wantedposterspackage.png");
/* 22 */   private WantedPosterPackageModel model = new WantedPosterPackageModel();
/*    */ 
/*    */   
/*    */   protected WantedPosterPackageRenderer(EntityRendererManager manager) {
/* 26 */     super(manager);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 32 */     matrixStack.push();
/*    */     
/* 34 */     matrixStack.translate(0.0D, 1.5D, 0.0D);
/* 35 */     matrixStack.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
/* 36 */     matrixStack.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
/* 37 */     matrixStack.rotate(new Quaternion(Vector3f.YP, ((WantedPosterPackageEntity)entity).prevRotationYaw + (((WantedPosterPackageEntity)entity).rotationYaw - ((WantedPosterPackageEntity)entity).prevRotationYaw) * partialTicks - 180.0F, true));
/*    */     
/* 39 */     matrixStack.scale(1.5F, 1.0F, 1.5F);
/*    */     
/* 41 */     RenderType type = RenderType.getEntityCutoutNoCull(getEntityTexture((WantedPosterPackageEntity)entity));
/* 42 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 43 */     this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 45 */     matrixStack.pop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(WantedPosterPackageEntity entity) {
/* 51 */     return this.texture;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<WantedPosterPackageEntity>
/*    */   {
/*    */     public EntityRenderer<? super WantedPosterPackageEntity> createRenderFor(EntityRendererManager manager) {
/* 61 */       return new WantedPosterPackageRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\WantedPosterPackageRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */