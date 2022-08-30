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
/*    */ import xyz.pixelatedw.mineminenomi.entities.SpikeEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*    */ 
/*    */ public class SpikeRenderer<T extends SpikeEntity>
/*    */   extends EntityRenderer<T> {
/* 21 */   private CubeModel spike = new CubeModel();
/*    */ 
/*    */   
/*    */   protected SpikeRenderer(EntityRendererManager renderManager) {
/* 25 */     super(renderManager);
/* 26 */     this.shadowSize = 0.05F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 32 */     matrixStack.push();
/*    */     
/* 34 */     matrixStack.translate(0.0D, 0.02D, 0.0D);
/*    */     
/* 36 */     float scale = 0.2F;
/* 37 */     matrixStack.scale(scale, scale, scale);
/*    */     
/* 39 */     matrixStack.rotate(new Quaternion(Vector3f.XP, 45.0F, true));
/* 40 */     matrixStack.rotate(new Quaternion(Vector3f.YP, 45.0F, true));
/*    */     
/* 42 */     RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
/* 43 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 44 */     this.spike.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 0.1F, 0.1F, 0.1F, 1.0F);
/*    */     
/* 46 */     matrixStack.pop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(T entity) {
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 60 */       return new SpikeRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\SpikeRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */