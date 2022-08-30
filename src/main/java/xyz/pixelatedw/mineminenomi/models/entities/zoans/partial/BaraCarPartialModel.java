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
/*    */ public class BaraCarPartialModel<T extends LivingEntity>
/*    */   extends ZoanMorphModel<T> {
/*    */   private final ModelRenderer Head;
/*    */   private final ModelRenderer Body;
/*    */   private final ModelRenderer RightArm;
/*    */   private final ModelRenderer LeftArm;
/*    */   private final ModelRenderer RightLeg;
/*    */   private final ModelRenderer LeftLeg;
/*    */   
/*    */   public BaraCarPartialModel() {
/* 22 */     super(1.0F);
/* 23 */     this.textureWidth = 64;
/* 24 */     this.textureHeight = 64;
/*    */     
/* 26 */     this.Head = new ModelRenderer((Model)this);
/* 27 */     this.Head.setRotationPoint(0.0F, 19.0F, 0.0F);
/* 28 */     this.Head.setTextureOffset(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
/* 29 */     this.Head.setTextureOffset(32, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);
/*    */     
/* 31 */     this.Body = new ModelRenderer((Model)this);
/* 32 */     this.Body.setRotationPoint(0.0F, 21.0F, -1.0F);
/* 33 */     setRotationAngle(this.Body, 1.5708F, 0.0F, 0.0F);
/* 34 */     this.Body.setTextureOffset(16, 16).addBox(-4.0F, -6.0F, -1.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
/* 35 */     this.Body.setTextureOffset(16, 32).addBox(-4.0F, -6.0F, -1.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);
/*    */     
/* 37 */     this.RightArm = new ModelRenderer((Model)this);
/* 38 */     this.RightArm.setRotationPoint(-5.0F, 21.0F, -6.0F);
/* 39 */     setRotationAngle(this.RightArm, -1.309F, 0.0F, 0.0F);
/* 40 */     this.RightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.1736F, -2.9848F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/* 41 */     this.RightArm.setTextureOffset(40, 32).addBox(-3.0F, -2.1736F, -2.9848F, 4.0F, 12.0F, 4.0F, 0.25F, false);
/*    */     
/* 43 */     this.LeftArm = new ModelRenderer((Model)this);
/* 44 */     this.LeftArm.setRotationPoint(5.0F, 22.0F, -6.0F);
/* 45 */     setRotationAngle(this.LeftArm, -1.309F, 0.0F, 0.0F);
/* 46 */     this.LeftArm.setTextureOffset(32, 48).addBox(-1.0F, -2.1736F, -3.9848F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/* 47 */     this.LeftArm.setTextureOffset(48, 48).addBox(-1.0F, -2.1736F, -3.9848F, 4.0F, 12.0F, 4.0F, 0.25F, false);
/*    */     
/* 49 */     this.RightLeg = new ModelRenderer((Model)this);
/* 50 */     this.RightLeg.setRotationPoint(-3.9F, 18.0F, 4.0F);
/* 51 */     setRotationAngle(this.RightLeg, 1.7453F, -0.1745F, 0.1745F);
/* 52 */     this.RightLeg.setTextureOffset(0, 16).addBox(-1.271F, 0.2007F, -4.0354F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/* 53 */     this.RightLeg.setTextureOffset(0, 32).addBox(-1.271F, 0.2007F, -4.0354F, 4.0F, 12.0F, 4.0F, 0.25F, false);
/*    */     
/* 55 */     this.LeftLeg = new ModelRenderer((Model)this);
/* 56 */     this.LeftLeg.setRotationPoint(3.9F, 18.0F, 4.0F);
/* 57 */     setRotationAngle(this.LeftLeg, 1.7453F, 0.2618F, -0.1745F);
/* 58 */     this.LeftLeg.setTextureOffset(16, 48).addBox(-2.7323F, 0.2153F, -4.038F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/* 59 */     this.LeftLeg.setTextureOffset(0, 48).addBox(-2.7323F, 0.2153F, -4.038F, 4.0F, 12.0F, 4.0F, 0.25F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 71 */     this.Head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 72 */     this.Body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 73 */     this.RightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 74 */     this.LeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 75 */     this.RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 76 */     this.LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 81 */     modelRenderer.rotateAngleX = x;
/* 82 */     modelRenderer.rotateAngleY = y;
/* 83 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\BaraCarPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */