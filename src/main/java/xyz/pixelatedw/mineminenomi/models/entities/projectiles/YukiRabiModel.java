/*    */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
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
/*    */ public class YukiRabiModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer body1;
/*    */   public ModelRenderer body2;
/*    */   public ModelRenderer body3;
/*    */   public ModelRenderer body4;
/*    */   public ModelRenderer rightEar;
/*    */   public ModelRenderer leftEar;
/*    */   
/*    */   public YukiRabiModel() {
/* 24 */     this.textureWidth = 80;
/* 25 */     this.textureHeight = 40;
/* 26 */     this.rightEar = new ModelRenderer((Model)this, 0, 30);
/* 27 */     this.rightEar.setRotationPoint(-1.0F, -1.8F, 4.0F);
/* 28 */     this.rightEar.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 7.0F, 0.0F);
/* 29 */     setRotateAngle(this.rightEar, 0.15481742F, -0.08593739F, -0.17518608F);
/* 30 */     this.body4 = new ModelRenderer((Model)this, 29, 15);
/* 31 */     this.body4.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 32 */     this.body4.addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F);
/* 33 */     this.leftEar = new ModelRenderer((Model)this, 19, 30);
/* 34 */     this.leftEar.setRotationPoint(1.0F, -1.8F, 4.0F);
/* 35 */     this.leftEar.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 7.0F, 0.0F);
/* 36 */     setRotateAngle(this.leftEar, 0.15481742F, 0.08593739F, 0.17518608F);
/* 37 */     this.body3 = new ModelRenderer((Model)this, 29, 0);
/* 38 */     this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 39 */     this.body3.addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 8.0F, 0.0F);
/* 40 */     this.body1 = new ModelRenderer((Model)this, 0, 0);
/* 41 */     this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 42 */     this.body1.addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, 0.0F);
/* 43 */     this.body2 = new ModelRenderer((Model)this, 0, 15);
/* 44 */     this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 45 */     this.body2.addBox(-4.0F, -3.0F, -3.0F, 8.0F, 6.0F, 6.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 51 */     this.rightEar.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 52 */     this.body4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 53 */     this.leftEar.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 54 */     this.body3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 55 */     this.body1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 56 */     this.body2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 67 */     model.rotateAngleX = x;
/* 68 */     model.rotateAngleY = y;
/* 69 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\YukiRabiModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */