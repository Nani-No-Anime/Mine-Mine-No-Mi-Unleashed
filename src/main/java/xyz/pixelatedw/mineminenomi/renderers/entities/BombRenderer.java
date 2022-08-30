/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*    */ 
/*    */ public class BombRenderer<T extends Entity>
/*    */   extends EntityRenderer<T>
/*    */ {
/* 19 */   private SphereModel sphere = new SphereModel();
/*    */ 
/*    */   
/*    */   protected BombRenderer(EntityRendererManager renderManager) {
/* 23 */     super(renderManager);
/* 24 */     this.shadowSize = 0.4F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 30 */     matrixStack.push();
/*    */     
/* 32 */     if (((Entity)entity).ticksExisted < 4) {
/* 33 */       matrixStack.translate(0.0D, 1.5D - (((Entity)entity).ticksExisted / 3.0F), 0.0D);
/*    */     } else {
/* 35 */       matrixStack.translate(0.0D, 0.4D, 0.0D);
/*    */     } 
/* 37 */     float scale = 3.0F;
/* 38 */     matrixStack.scale(scale, scale, scale);
/*    */     
/* 40 */     RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
/* 41 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 42 */     this.sphere.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 0.1F, 0.1F, 0.1F, 1.0F);
/*    */     
/* 44 */     matrixStack.pop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(T entity) {
/* 50 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 58 */       return new BombRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\BombRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */