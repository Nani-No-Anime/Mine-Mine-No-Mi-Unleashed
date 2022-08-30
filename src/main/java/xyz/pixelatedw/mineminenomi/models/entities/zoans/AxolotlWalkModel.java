/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ 
/*     */ public class AxolotlWalkModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer top_gills;
/*     */   private final ModelRenderer left_gills;
/*     */   private final ModelRenderer right_gills;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leg4;
/*     */   private final ModelRenderer leg2;
/*     */   private final ModelRenderer leg3;
/*     */   private final ModelRenderer leg1;
/*     */   private final ModelRenderer tail;
/*     */   
/*     */   public AxolotlWalkModel() {
/*  29 */     super(1.0F);
/*  30 */     this.textureWidth = 64;
/*  31 */     this.textureHeight = 64;
/*     */     
/*  33 */     this.head = new ModelRenderer((Model)this);
/*  34 */     this.head.setRotationPoint(0.0F, 18.0F, -5.0F);
/*  35 */     this.head.setTextureOffset(0, 1).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/*  37 */     this.top_gills = new ModelRenderer((Model)this);
/*  38 */     this.top_gills.setRotationPoint(0.0F, -3.0F, -1.0F);
/*  39 */     this.head.addChild(this.top_gills);
/*  40 */     this.top_gills.setTextureOffset(3, 37).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 3.0F, 0.0F, 0.0F, false);
/*     */     
/*  42 */     this.left_gills = new ModelRenderer((Model)this);
/*  43 */     this.left_gills.setRotationPoint(4.0F, 0.0F, -1.0F);
/*  44 */     this.head.addChild(this.left_gills);
/*  45 */     this.left_gills.setTextureOffset(0, 40).addBox(-11.0F, -5.0F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);
/*     */     
/*  47 */     this.right_gills = new ModelRenderer((Model)this);
/*  48 */     this.right_gills.setRotationPoint(-4.0F, 0.0F, -1.0F);
/*  49 */     this.head.addChild(this.right_gills);
/*  50 */     this.right_gills.setTextureOffset(11, 40).addBox(8.0F, -5.0F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);
/*     */     
/*  52 */     this.body = new ModelRenderer((Model)this);
/*  53 */     this.body.setRotationPoint(0.0F, 18.0F, 4.0F);
/*  54 */     this.body.setTextureOffset(0, 11).addBox(-4.0F, -2.0F, -9.0F, 8.0F, 4.0F, 10.0F, 0.0F, false);
/*  55 */     this.body.setTextureOffset(2, 17).addBox(0.0F, -3.0F, -8.0F, 0.0F, 5.0F, 9.0F, 0.0F, false);
/*     */     
/*  57 */     this.leg4 = new ModelRenderer((Model)this);
/*  58 */     this.leg4.setRotationPoint(-3.5F, 19.0F, -4.0F);
/*  59 */     this.leg4.setTextureOffset(2, 13).addBox(6.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  61 */     this.leg2 = new ModelRenderer((Model)this);
/*  62 */     this.leg2.setRotationPoint(-3.5F, 19.0F, 3.0F);
/*  63 */     this.leg2.setTextureOffset(2, 13).addBox(6.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  65 */     this.leg3 = new ModelRenderer((Model)this);
/*  66 */     this.leg3.setRotationPoint(3.5F, 19.0F, -4.0F);
/*  67 */     this.leg3.setTextureOffset(2, 13).addBox(-9.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  69 */     this.leg1 = new ModelRenderer((Model)this);
/*  70 */     this.leg1.setRotationPoint(3.5F, 19.0F, 3.0F);
/*  71 */     this.leg1.setTextureOffset(2, 13).addBox(-9.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
/*     */     
/*  73 */     this.tail = new ModelRenderer((Model)this);
/*  74 */     this.tail.setRotationPoint(0.0F, 18.0F, 5.0F);
/*  75 */     this.tail.setTextureOffset(2, 19).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 12.0F, 0.0F, false);
/*     */     
/*  77 */     this.bipedBody = this.body;
/*  78 */     this.bipedHead = this.head;
/*  79 */     this.bipedRightArm = this.leg1;
/*  80 */     this.bipedLeftArm = this.leg2;
/*  81 */     this.bipedRightLeg = this.leg3;
/*  82 */     this.bipedLeftLeg = this.leg4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  89 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/*  90 */     boolean flag1 = entity.isActualySwimming();
/*  91 */     this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
/*  92 */     if (flag) {
/*  93 */       this.bipedHead.rotateAngleX = -0.7853982F;
/*  94 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/*  96 */       if (flag1) {
/*  97 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/*  99 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 103 */       this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
/* 104 */       if (this.bipedHead.rotateAngleX > 0.4D) {
/* 105 */         this.bipedHead.rotateAngleX = 0.4F;
/* 106 */       } else if (this.bipedHead.rotateAngleX < -0.4D) {
/* 107 */         this.bipedHead.rotateAngleX = -0.4F;
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     float f = 1.0F;
/* 112 */     this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 113 */     this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 114 */     this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 115 */     this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/*     */     
/* 117 */     this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 10.0D);
/* 118 */     this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/*     */ 
/*     */     
/* 121 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 122 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 124 */       this.head.rotateAngleY += this.body.rotateAngleY;
/* 125 */       float f1 = 1.0F - this.swingProgress;
/* 126 */       f1 *= f1;
/* 127 */       f1 *= f1;
/* 128 */       f1 = 1.0F - f1;
/* 129 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 130 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
/* 131 */       this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 1.5D + f3);
/* 132 */       this.head.rotateAngleZ = MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 139 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 140 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 141 */     this.leg4.render(matrixStack, buffer, packedLight, packedOverlay);
/* 142 */     this.leg2.render(matrixStack, buffer, packedLight, packedOverlay);
/* 143 */     this.leg3.render(matrixStack, buffer, packedLight, packedOverlay);
/* 144 */     this.leg1.render(matrixStack, buffer, packedLight, packedOverlay);
/* 145 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 150 */     modelRenderer.rotateAngleX = x;
/* 151 */     modelRenderer.rotateAngleY = y;
/* 152 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 158 */     this.head.translateRotate(matrixStack);
/* 159 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 160 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
/* 161 */     matrixStack.translate(0.1D, -0.2D, -0.03D);
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\AxolotlWalkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */