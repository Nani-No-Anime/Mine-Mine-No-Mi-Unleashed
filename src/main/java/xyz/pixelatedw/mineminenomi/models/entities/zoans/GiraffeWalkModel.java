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
/*     */ public class GiraffeWalkModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer tail1;
/*     */   private final ModelRenderer hunch;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer head2;
/*     */   private final ModelRenderer rightHorn;
/*     */   private final ModelRenderer leftHorn;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer mane;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer rightFrontHoof1;
/*     */   private final ModelRenderer rightFrontHoof2;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   private final ModelRenderer leftFrontHoof1;
/*     */   private final ModelRenderer leftFrontHoof2;
/*     */   private final ModelRenderer leftRearLeg;
/*     */   private final ModelRenderer leftRearHoof1;
/*     */   private final ModelRenderer leftRearHoof2;
/*     */   private final ModelRenderer rightRearLeg;
/*     */   private final ModelRenderer rightRearHoof1;
/*     */   private final ModelRenderer rightRearHoof2;
/*     */   
/*     */   public GiraffeWalkModel() {
/*  42 */     super(1.0F);
/*  43 */     this.textureWidth = 128;
/*  44 */     this.textureHeight = 64;
/*     */     
/*  46 */     this.body = new ModelRenderer((Model)this);
/*  47 */     this.body.setRotationPoint(0.0F, 7.0F, 3.0F);
/*  48 */     setRotationAngle(this.body, 1.4835F, 0.0F, 0.0F);
/*  49 */     this.body.setTextureOffset(18, 18).addBox(-6.0F, -10.4346F, -6.7471F, 10.0F, 16.0F, 9.0F, 0.0F, false);
/*     */     
/*  51 */     this.tail1 = new ModelRenderer((Model)this);
/*  52 */     this.tail1.setRotationPoint(-1.0F, 4.5747F, 1.0142F);
/*  53 */     this.body.addChild(this.tail1);
/*  54 */     setRotationAngle(this.tail1, -1.0472F, 0.0F, 0.0F);
/*  55 */     this.tail1.setTextureOffset(60, 28).addBox(-0.5F, 0.4821F, -0.5745F, 1.0F, 5.0F, 1.0F, 0.0F, false);
/*     */     
/*  57 */     this.hunch = new ModelRenderer((Model)this);
/*  58 */     this.hunch.setRotationPoint(-6.02F, -10.5F, 3.0F);
/*  59 */     this.body.addChild(this.hunch);
/*  60 */     setRotationAngle(this.hunch, -0.7854F, 0.0F, 0.0F);
/*  61 */     this.hunch.setTextureOffset(40, 0).addBox(0.02F, 0.5745F, -0.4821F, 10.0F, 9.0F, 7.0F, -0.02F, false);
/*     */     
/*  63 */     this.neck = new ModelRenderer((Model)this);
/*  64 */     this.neck.setRotationPoint(-1.0F, 2.0F, -2.0F);
/*  65 */     setRotationAngle(this.neck, 0.7854F, 0.0F, 0.0F);
/*  66 */     this.neck.setTextureOffset(0, 0).addBox(-2.0F, -21.9506F, -3.0052F, 4.0F, 21.0F, 4.0F, 0.0F, false);
/*     */     
/*  68 */     this.head = new ModelRenderer((Model)this);
/*  69 */     this.head.setRotationPoint(2.0F, -20.481F, -1.9748F);
/*  70 */     this.neck.addChild(this.head);
/*  71 */     setRotationAngle(this.head, -0.7854F, 0.0F, 0.0F);
/*  72 */     this.head.setTextureOffset(17, 0).addBox(-4.0F, -3.25F, -6.0F, 4.0F, 3.0F, 7.0F, -0.01F, false);
/*     */     
/*  74 */     this.head2 = new ModelRenderer((Model)this);
/*  75 */     this.head2.setRotationPoint(-4.0F, -6.0F, -3.0F);
/*  76 */     this.head.addChild(this.head2);
/*  77 */     this.head2.setTextureOffset(18, 11).addBox(0.0F, 0.75F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/*  79 */     this.rightHorn = new ModelRenderer((Model)this);
/*  80 */     this.rightHorn.setRotationPoint(0.75F, 0.0F, 3.0F);
/*  81 */     this.head2.addChild(this.rightHorn);
/*  82 */     this.rightHorn.setTextureOffset(60, 20).addBox(-0.5F, -1.25F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  84 */     this.leftHorn = new ModelRenderer((Model)this);
/*  85 */     this.leftHorn.setRotationPoint(3.25F, 0.0F, 3.0F);
/*  86 */     this.head2.addChild(this.leftHorn);
/*  87 */     this.leftHorn.setTextureOffset(60, 20).addBox(-0.5F, -1.25F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  89 */     this.rightEar = new ModelRenderer((Model)this);
/*  90 */     this.rightEar.setRotationPoint(-0.25F, 1.25F, 3.0F);
/*  91 */     this.head2.addChild(this.rightEar);
/*  92 */     setRotationAngle(this.rightEar, 0.0F, 0.0F, 0.2618F);
/*  93 */     this.rightEar.setTextureOffset(60, 24).addBox(-2.099F, -0.0129F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, false);
/*     */     
/*  95 */     this.leftEar = new ModelRenderer((Model)this);
/*  96 */     this.leftEar.setRotationPoint(4.0F, 1.7F, 3.0F);
/*  97 */     this.head2.addChild(this.leftEar);
/*  98 */     setRotationAngle(this.leftEar, 0.0F, 0.0F, -0.2618F);
/*  99 */     this.leftEar.setTextureOffset(67, 24).addBox(-0.4183F, -0.3709F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, true);
/*     */     
/* 101 */     this.mane = new ModelRenderer((Model)this);
/* 102 */     this.mane.setRotationPoint(0.0F, -22.481F, 1.5252F);
/* 103 */     this.neck.addChild(this.mane);
/* 104 */     this.mane.setTextureOffset(76, 0).addBox(0.0F, 0.0303F, -0.5303F, 0.0F, 21.0F, 1.0F, 0.0F, false);
/*     */     
/* 106 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 107 */     this.rightFrontLeg.setRotationPoint(-2.75F, 12.0F, -4.75F);
/* 108 */     this.rightFrontLeg.setTextureOffset(0, 28).addBox(-3.0F, 0.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
/*     */     
/* 110 */     this.rightFrontHoof1 = new ModelRenderer((Model)this);
/* 111 */     this.rightFrontHoof1.setRotationPoint(-2.0F, 10.0F, -3.5F);
/* 112 */     this.rightFrontLeg.addChild(this.rightFrontHoof1);
/* 113 */     setRotationAngle(this.rightFrontHoof1, -0.1211F, -0.4883F, -0.0394F);
/* 114 */     this.rightFrontHoof1.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 116 */     this.rightFrontHoof2 = new ModelRenderer((Model)this);
/* 117 */     this.rightFrontHoof2.setRotationPoint(-3.0F, 10.0F, -3.0F);
/* 118 */     this.rightFrontLeg.addChild(this.rightFrontHoof2);
/* 119 */     setRotationAngle(this.rightFrontHoof2, -0.1211F, 0.4883F, 0.0394F);
/* 120 */     this.rightFrontHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 122 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 123 */     this.leftFrontLeg.setRotationPoint(4.75F, 12.0F, -4.75F);
/* 124 */     this.leftFrontLeg.setTextureOffset(0, 28).addBox(-3.0F, 0.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
/*     */     
/* 126 */     this.leftFrontHoof1 = new ModelRenderer((Model)this);
/* 127 */     this.leftFrontHoof1.setRotationPoint(-2.0F, 10.0F, -3.5F);
/* 128 */     this.leftFrontLeg.addChild(this.leftFrontHoof1);
/* 129 */     setRotationAngle(this.leftFrontHoof1, -0.1211F, -0.4883F, -0.0394F);
/* 130 */     this.leftFrontHoof1.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 132 */     this.leftFrontHoof2 = new ModelRenderer((Model)this);
/* 133 */     this.leftFrontHoof2.setRotationPoint(-3.0F, 10.0F, -3.0F);
/* 134 */     this.leftFrontLeg.addChild(this.leftFrontHoof2);
/* 135 */     setRotationAngle(this.leftFrontHoof2, -0.1211F, 0.4883F, 0.0394F);
/* 136 */     this.leftFrontHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 138 */     this.leftRearLeg = new ModelRenderer((Model)this);
/* 139 */     this.leftRearLeg.setRotationPoint(4.75F, 12.0F, 8.25F);
/* 140 */     this.leftRearLeg.setTextureOffset(0, 28).addBox(-3.0F, 0.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
/*     */     
/* 142 */     this.leftRearHoof1 = new ModelRenderer((Model)this);
/* 143 */     this.leftRearHoof1.setRotationPoint(-2.0F, 10.0F, -3.5F);
/* 144 */     this.leftRearLeg.addChild(this.leftRearHoof1);
/* 145 */     setRotationAngle(this.leftRearHoof1, -0.1211F, -0.4883F, -0.0394F);
/* 146 */     this.leftRearHoof1.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 148 */     this.leftRearHoof2 = new ModelRenderer((Model)this);
/* 149 */     this.leftRearHoof2.setRotationPoint(-3.0F, 10.0F, -3.0F);
/* 150 */     this.leftRearLeg.addChild(this.leftRearHoof2);
/* 151 */     setRotationAngle(this.leftRearHoof2, -0.1211F, 0.4883F, 0.0394F);
/* 152 */     this.leftRearHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 154 */     this.rightRearLeg = new ModelRenderer((Model)this);
/* 155 */     this.rightRearLeg.setRotationPoint(-2.75F, 12.0F, 8.25F);
/* 156 */     this.rightRearLeg.setTextureOffset(0, 28).addBox(-3.0F, 0.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
/*     */     
/* 158 */     this.rightRearHoof1 = new ModelRenderer((Model)this);
/* 159 */     this.rightRearHoof1.setRotationPoint(-2.0F, 10.0F, -3.5F);
/* 160 */     this.rightRearLeg.addChild(this.rightRearHoof1);
/* 161 */     setRotationAngle(this.rightRearHoof1, -0.1211F, -0.4883F, -0.0394F);
/* 162 */     this.rightRearHoof1.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 164 */     this.rightRearHoof2 = new ModelRenderer((Model)this);
/* 165 */     this.rightRearHoof2.setRotationPoint(-3.0F, 10.0F, -3.0F);
/* 166 */     this.rightRearLeg.addChild(this.rightRearHoof2);
/* 167 */     setRotationAngle(this.rightRearHoof2, -0.1211F, 0.4883F, 0.0394F);
/* 168 */     this.rightRearHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 174 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 175 */     this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 176 */     this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 177 */     this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 178 */     this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 179 */     this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 186 */     this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
/* 187 */     this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
/* 188 */     this.rightRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.3F * limbSwingAmount;
/* 189 */     this.leftRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.4F * limbSwingAmount;
/* 190 */     if (entity.isSprinting()) {
/*     */       
/* 192 */       this.tail1.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 193 */       this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 194 */       this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 198 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 199 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 201 */       this.neck.rotateAngleY += this.body.rotateAngleY;
/* 202 */       float f1 = 1.0F - this.swingProgress;
/* 203 */       f1 *= f1;
/* 204 */       f1 *= f1;
/* 205 */       f1 = 1.0F - f1;
/* 206 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 207 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
/* 208 */       this.neck.rotateAngleX = 1.0F + (float)(this.neck.rotateAngleX - f2 * 1.5D + f3);
/* 209 */       this.neck.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 226 */     this.neck.translateRotate(matrixStack);
/* 227 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 228 */     matrixStack.rotate(Vector3f.YP.rotationDegrees(50.0F));
/* 229 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(270.0F));
/* 230 */     matrixStack.translate(-0.7D, 0.8D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 235 */     modelRenderer.rotateAngleX = x;
/* 236 */     modelRenderer.rotateAngleY = y;
/* 237 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\GiraffeWalkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */