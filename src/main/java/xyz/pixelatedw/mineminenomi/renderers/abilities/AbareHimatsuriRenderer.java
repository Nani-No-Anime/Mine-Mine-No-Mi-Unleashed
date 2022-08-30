/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Quaternion;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.AbareHimatsuriModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class AbareHimatsuriRenderer
/*    */   extends EntityRenderer
/*    */ {
/* 25 */   private ResourceLocation texture = new ResourceLocation("minecraft:textures/block/dirt.png");
/*    */   
/*    */   private AbareHimatsuriModel model;
/*    */   
/*    */   public AbareHimatsuriRenderer(EntityRendererManager renderManager, EntityModel model) {
/* 30 */     super(renderManager);
/* 31 */     this.shadowSize = 0.0F;
/* 32 */     this.model = (AbareHimatsuriModel)model;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 38 */     matrixStack.push();
/*    */     
/* 40 */     matrixStack.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
/*    */     
/* 42 */     RenderType type = RenderType.getEntityCutoutNoCull(this.texture);
/* 43 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 44 */     this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 46 */     matrixStack.pop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(Entity entity) {
/* 52 */     return this.texture;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<EntityCloud>
/*    */   {
/*    */     private AbareHimatsuriModel model;
/*    */     
/*    */     public Factory(AbareHimatsuriModel model) {
/* 61 */       this.model = model;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super Entity> createRenderFor(EntityRendererManager manager) {
/* 67 */       return new AbareHimatsuriRenderer(manager, (EntityModel)this.model);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\AbareHimatsuriRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */