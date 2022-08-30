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
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class VivreCardRenderer<T extends VivreCardEntity>
/*    */   extends EntityRenderer<T> {
/* 24 */   private CubeModel model = new CubeModel();
/*    */ 
/*    */   
/*    */   protected VivreCardRenderer(EntityRendererManager manager) {
/* 28 */     super(manager);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(VivreCardEntity entity) {
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 40 */     matrixStack.push();
/*    */     
/* 42 */     matrixStack.translate(0.0D, 0.05D, 0.0D);
/* 43 */     matrixStack.rotate(new Quaternion(Vector3f.YP, -((VivreCardEntity)entity).rotationYaw, true));
/*    */     
/* 45 */     matrixStack.scale(0.4F, 0.1F, 0.5F);
/*    */     
/* 47 */     RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
/* 48 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 49 */     this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 51 */     matrixStack.pop();
/* 52 */     super.render((T)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<VivreCardEntity>
/*    */   {
/*    */     public EntityRenderer<? super VivreCardEntity> createRenderFor(EntityRendererManager manager) {
/* 64 */       return new VivreCardRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\VivreCardRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */