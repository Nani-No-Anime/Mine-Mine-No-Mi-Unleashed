/*    */ package xyz.pixelatedw.mineminenomi.models.blocks;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class WantedPosterModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer poster;
/*    */   
/*    */   public WantedPosterModel() {
/* 19 */     this.textureWidth = 64;
/* 20 */     this.textureHeight = 64;
/* 21 */     this.poster = new ModelRenderer((Model)this, 0, 0);
/* 22 */     this.poster.setRotationPoint(10.0F, 15.0F, 0.0F);
/* 23 */     this.poster.addBox(-10.0F, -15.0F, 0.0F, 20.0F, 30.0F, 0.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 29 */     this.poster.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 40 */     modelRenderer.rotateAngleX = x;
/* 41 */     modelRenderer.rotateAngleY = y;
/* 42 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\blocks\WantedPosterModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */