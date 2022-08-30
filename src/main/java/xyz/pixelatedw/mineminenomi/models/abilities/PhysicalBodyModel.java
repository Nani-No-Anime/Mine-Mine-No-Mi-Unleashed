/*    */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class PhysicalBodyModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer bipedRightLeg;
/*    */   public ModelRenderer bipedBody;
/*    */   public ModelRenderer bipedLeftLeg;
/*    */   public ModelRenderer bipedHead;
/*    */   public ModelRenderer bipedRightArm;
/*    */   public ModelRenderer bipedLeftArm;
/*    */   
/*    */   public PhysicalBodyModel() {
/* 21 */     this.textureWidth = 64;
/* 22 */     this.textureHeight = 64;
/* 23 */     this.bipedRightArm = new ModelRenderer((Model)this, 40, 16);
/* 24 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/* 25 */     this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/* 26 */     this.bipedLeftArm = new ModelRenderer((Model)this, 32, 48);
/* 27 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/* 28 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/* 29 */     this.bipedBody = new ModelRenderer((Model)this, 16, 16);
/* 30 */     this.bipedBody.setRotationPoint(0.0F, 12.0F, 0.0F);
/* 31 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F);
/* 32 */     this.bipedHead = new ModelRenderer((Model)this, 0, 0);
/* 33 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 34 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
/* 35 */     setRotateAngle(this.bipedHead, 0.7853982F, 0.0F, 0.0F);
/* 36 */     this.bipedRightLeg = new ModelRenderer((Model)this, 0, 16);
/* 37 */     this.bipedRightLeg.setRotationPoint(-1.9F, 22.0F, 0.0F);
/* 38 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/* 39 */     setRotateAngle(this.bipedRightLeg, -1.5707964F, 0.0F, 0.0F);
/* 40 */     this.bipedLeftLeg = new ModelRenderer((Model)this, 16, 48);
/* 41 */     this.bipedLeftLeg.setRotationPoint(1.9F, 22.0F, 0.0F);
/* 42 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/* 43 */     setRotateAngle(this.bipedLeftLeg, -1.5707964F, 0.0F, 0.0F);
/* 44 */     this.bipedBody.addChild(this.bipedRightArm);
/* 45 */     this.bipedBody.addChild(this.bipedLeftArm);
/* 46 */     this.bipedBody.addChild(this.bipedHead);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 52 */     this.bipedBody.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 53 */     this.bipedRightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 54 */     this.bipedLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 64 */     modelRenderer.rotateAngleX = x;
/* 65 */     modelRenderer.rotateAngleY = y;
/* 66 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\abilities\PhysicalBodyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */