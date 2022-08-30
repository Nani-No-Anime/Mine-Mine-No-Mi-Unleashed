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
/*    */ public class MinkBunnyPartialModel<T extends LivingEntity>
/*    */   extends ZoanMorphModel<T> {
/*    */   private final ModelRenderer ears;
/*    */   private final ModelRenderer rightEar1;
/*    */   private final ModelRenderer rightEar2;
/*    */   private final ModelRenderer leftEar1;
/*    */   private final ModelRenderer leftEar2;
/*    */   private final ModelRenderer tail;
/*    */   
/*    */   public MinkBunnyPartialModel() {
/* 22 */     super(1.0F);
/* 23 */     this.textureWidth = 32;
/* 24 */     this.textureHeight = 32;
/*    */     
/* 26 */     this.ears = new ModelRenderer((Model)this);
/* 27 */     this.ears.setRotationPoint(0.0F, 8.0F, 0.0F);
/*    */ 
/*    */     
/* 30 */     this.rightEar1 = new ModelRenderer((Model)this);
/* 31 */     this.rightEar1.setRotationPoint(-2.0F, -8.0F, 0.0F);
/* 32 */     this.ears.addChild(this.rightEar1);
/* 33 */     setRotationAngle(this.rightEar1, 0.0F, -0.2182F, -0.2182F);
/* 34 */     this.rightEar1.setTextureOffset(0, 25).addBox(-2.0F, -5.0F, 0.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
/*    */     
/* 36 */     this.rightEar2 = new ModelRenderer((Model)this);
/* 37 */     this.rightEar2.setRotationPoint(-0.5315F, -4.4739F, 0.1179F);
/* 38 */     this.rightEar1.addChild(this.rightEar2);
/* 39 */     setRotationAngle(this.rightEar2, 0.2967F, 0.0F, 0.0F);
/* 40 */     this.rightEar2.setTextureOffset(0, 16).addBox(-1.4685F, -5.2761F, -0.1179F, 3.0F, 5.0F, 1.0F, 0.0F, false);
/*    */     
/* 42 */     this.leftEar1 = new ModelRenderer((Model)this);
/* 43 */     this.leftEar1.setRotationPoint(2.0F, -8.0F, 0.0F);
/* 44 */     this.ears.addChild(this.leftEar1);
/* 45 */     setRotationAngle(this.leftEar1, 0.0F, 0.2182F, 0.2182F);
/* 46 */     this.leftEar1.setTextureOffset(0, 25).addBox(-1.0F, -5.0F, 0.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
/*    */     
/* 48 */     this.leftEar2 = new ModelRenderer((Model)this);
/* 49 */     this.leftEar2.setRotationPoint(0.717F, -4.7721F, 0.159F);
/* 50 */     this.leftEar1.addChild(this.leftEar2);
/* 51 */     setRotationAngle(this.leftEar2, 0.2967F, 0.0F, 0.0F);
/* 52 */     this.leftEar2.setTextureOffset(0, 16).addBox(-1.717F, -4.9779F, -0.159F, 3.0F, 5.0F, 1.0F, 0.0F, false);
/*    */     
/* 54 */     this.tail = new ModelRenderer((Model)this);
/* 55 */     this.tail.setRotationPoint(0.0F, 3.0F, 1.0F);
/* 56 */     this.tail.setTextureOffset(0, 0).addBox(-3.0F, 7.0F, 2.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 62 */     this.ears.copyModelAngles(this.bipedHead);
/* 63 */     this.tail.copyModelAngles(this.bipedBody);
/* 64 */     this.ears.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 65 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 71 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ 
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 82 */     modelRenderer.rotateAngleX = x;
/* 83 */     modelRenderer.rotateAngleY = y;
/* 84 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\MinkBunnyPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */