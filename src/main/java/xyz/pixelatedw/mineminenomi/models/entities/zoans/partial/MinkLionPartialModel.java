/*    */ package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ 
/*    */ public class MinkLionPartialModel<T extends LivingEntity>
/*    */   extends ZoanMorphModel<T> {
/*    */   private final ModelRenderer mane;
/*    */   private final ModelRenderer mane2;
/*    */   private final ModelRenderer tail;
/*    */   private final ModelRenderer tail2;
/*    */   private final ModelRenderer tailTip;
/*    */   
/*    */   public MinkLionPartialModel() {
/* 21 */     super(1.0F);
/* 22 */     this.textureWidth = 64;
/* 23 */     this.textureHeight = 64;
/*    */     
/* 25 */     this.mane = new ModelRenderer((Model)this);
/* 26 */     this.mane.setRotationPoint(0.0F, 6.5F, 1.0F);
/* 27 */     this.mane.setTextureOffset(0, 15).addBox(-6.5F, -10.5F, -2.0F, 13.0F, 13.0F, 4.0F, 0.0F, false);
/*    */     
/* 29 */     this.mane2 = new ModelRenderer((Model)this);
/* 30 */     this.mane2.setRotationPoint(4.5F, -0.5F, -1.0F);
/* 31 */     this.mane.addChild(this.mane2);
/* 32 */     this.mane2.setTextureOffset(0, 15).addBox(-10.0F, -9.0F, -2.0F, 11.0F, 11.0F, 6.0F, 0.0F, false);
/*    */     
/* 34 */     this.tail = new ModelRenderer((Model)this);
/* 35 */     this.tail.setRotationPoint(0.0F, 11.0F, 1.0F);
/* 36 */     setRotationAngle(this.tail, 0.3054F, 0.0F, 0.0F);
/* 37 */     this.tail.setTextureOffset(0, 0).addBox(-0.5F, -0.2456F, 0.653F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*    */     
/* 39 */     this.tail2 = new ModelRenderer((Model)this);
/* 40 */     this.tail2.setRotationPoint(0.0F, 1.3592F, 3.0808F);
/* 41 */     this.tail.addChild(this.tail2);
/* 42 */     setRotationAngle(this.tail2, -0.7417F, 0.0F, 0.0F);
/* 43 */     this.tail2.setTextureOffset(0, 6).addBox(-0.5F, -1.5592F, -0.5808F, 1.0F, 1.0F, 4.0F, -0.01F, false);
/*    */     
/* 45 */     this.tailTip = new ModelRenderer((Model)this);
/* 46 */     this.tailTip.setRotationPoint(0.0F, -0.135F, 3.2472F);
/* 47 */     this.tail2.addChild(this.tailTip);
/* 48 */     setRotationAngle(this.tailTip, -0.1309F, 0.0F, 0.0F);
/* 49 */     this.tailTip.setTextureOffset(11, 0).addBox(-1.0F, -1.8743F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 55 */     this.mane.copyModelAngles(this.bipedHead);
/* 56 */     this.mane.render(matrixStack, buffer, packedLight, packedOverlay);
/* 57 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 63 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 65 */     if (entity.isCrouching()) {
/*    */       
/* 67 */       this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + 0.4D);
/* 68 */       this.tail.rotationPointY++;
/* 69 */       this.tail.rotationPointZ += 4.0F;
/*    */     } 
/*    */     
/* 72 */     this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 10.0D);
/* 73 */     this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 5.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 78 */     modelRenderer.rotateAngleX = x;
/* 79 */     modelRenderer.rotateAngleY = y;
/* 80 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\MinkLionPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */