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
/*    */ public class BaraCarWheelsPartialModel<T extends LivingEntity>
/*    */   extends ZoanMorphModel<T> {
/*    */   private final ModelRenderer frontWheels;
/*    */   private final ModelRenderer frontRightWheel;
/*    */   private final ModelRenderer frontLeftWheel;
/*    */   private final ModelRenderer backWheels;
/*    */   private final ModelRenderer backLeftWheel;
/*    */   private final ModelRenderer backRightWheel;
/*    */   
/*    */   public BaraCarWheelsPartialModel() {
/* 22 */     super(1.0F);
/* 23 */     this.textureWidth = 128;
/* 24 */     this.textureHeight = 128;
/*    */     
/* 26 */     this.frontWheels = new ModelRenderer((Model)this);
/* 27 */     this.frontWheels.setRotationPoint(0.0F, 23.0F, -16.0F);
/* 28 */     this.frontWheels.setTextureOffset(27, 12).addBox(-13.0F, -1.0F, -1.0F, 26.0F, 2.0F, 2.0F, 0.0F, false);
/*    */     
/* 30 */     this.frontRightWheel = new ModelRenderer((Model)this);
/* 31 */     this.frontRightWheel.setRotationPoint(-13.0F, 0.0F, 0.0F);
/* 32 */     this.frontWheels.addChild(this.frontRightWheel);
/* 33 */     this.frontRightWheel.setTextureOffset(0, 42).addBox(-3.0F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, 0.0F, false);
/*    */     
/* 35 */     this.frontLeftWheel = new ModelRenderer((Model)this);
/* 36 */     this.frontLeftWheel.setRotationPoint(13.0F, 0.0F, 0.0F);
/* 37 */     this.frontWheels.addChild(this.frontLeftWheel);
/* 38 */     this.frontLeftWheel.setTextureOffset(0, 42).addBox(0.0F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, 0.0F, false);
/*    */     
/* 40 */     this.backWheels = new ModelRenderer((Model)this);
/* 41 */     this.backWheels.setRotationPoint(8.0F, 20.75F, 14.25F);
/* 42 */     this.backWheels.setTextureOffset(0, 0).addBox(-21.0F, -2.75F, -3.25F, 26.0F, 6.0F, 6.0F, 0.0F, false);
/*    */     
/* 44 */     this.backLeftWheel = new ModelRenderer((Model)this);
/* 45 */     this.backLeftWheel.setRotationPoint(5.0F, 0.0F, 0.0F);
/* 46 */     this.backWheels.addChild(this.backLeftWheel);
/* 47 */     this.backLeftWheel.setTextureOffset(0, 12).addBox(0.0F, -7.75F, -7.25F, 6.0F, 15.0F, 15.0F, 0.0F, false);
/*    */     
/* 49 */     this.backRightWheel = new ModelRenderer((Model)this);
/* 50 */     this.backRightWheel.setRotationPoint(-21.0F, 0.0F, 0.0F);
/* 51 */     this.backWheels.addChild(this.backRightWheel);
/* 52 */     this.backRightWheel.setTextureOffset(0, 12).addBox(-6.0F, -7.75F, -7.25F, 6.0F, 15.0F, 15.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 58 */     this.frontRightWheel.rotateAngleX = limbSwing * 0.8F;
/* 59 */     this.frontLeftWheel.rotateAngleX = limbSwing * 0.8F;
/* 60 */     this.backRightWheel.rotateAngleX = limbSwing * 0.8F;
/* 61 */     this.backLeftWheel.rotateAngleX = limbSwing * 0.8F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 67 */     this.frontWheels.render(matrixStack, buffer, packedLight, packedOverlay);
/* 68 */     this.backWheels.render(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 73 */     modelRenderer.rotateAngleX = x;
/* 74 */     modelRenderer.rotateAngleY = y;
/* 75 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\BaraCarWheelsPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */