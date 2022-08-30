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
/*     */ public class LeopardWalkModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T>
/*     */   implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer snout2;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer leftRearLeg;
/*     */   private final ModelRenderer leftRearLeg2;
/*     */   private final ModelRenderer leftRearLeg3;
/*     */   private final ModelRenderer leftRearFoot;
/*     */   private final ModelRenderer rightRearLeg;
/*     */   private final ModelRenderer rightRearLeg2;
/*     */   private final ModelRenderer rightRearLeg3;
/*     */   private final ModelRenderer rightRearFoot;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   private final ModelRenderer leftFrontLeg2;
/*     */   private final ModelRenderer leftFrontLeg3;
/*     */   private final ModelRenderer leftFrontFoot;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer rightFrontLeg2;
/*     */   private final ModelRenderer rightFrontLeg3;
/*     */   private final ModelRenderer rightFrontFoot;
/*     */   
/*     */   public LeopardWalkModel() {
/*  47 */     super(1.0F);
/*  48 */     this.textureWidth = 128;
/*  49 */     this.textureHeight = 128;
/*     */     
/*  51 */     this.body = new ModelRenderer((Model)this);
/*  52 */     this.body.setRotationPoint(0.0F, 1.4F, -6.6F);
/*  53 */     setRotationAngle(this.body, -0.0873F, 0.0F, 0.0F);
/*  54 */     this.body.setTextureOffset(0, 0).addBox(-4.5F, 4.2281F, 0.109F, 9.0F, 7.0F, 12.0F, 0.01F, false);
/*     */     
/*  56 */     this.body2 = new ModelRenderer((Model)this);
/*  57 */     this.body2.setRotationPoint(0.0F, 9.1F, 10.7F);
/*  58 */     this.body.addChild(this.body2);
/*  59 */     setRotationAngle(this.body2, 0.1047F, 0.0F, 0.0F);
/*  60 */     this.body2.setTextureOffset(0, 19).addBox(-4.5F, -4.6727F, 0.7449F, 9.0F, 7.0F, 9.0F, 0.0F, false);
/*     */     
/*  62 */     this.neck = new ModelRenderer((Model)this);
/*  63 */     this.neck.setRotationPoint(0.0F, 6.8F, 1.0F);
/*  64 */     this.body.addChild(this.neck);
/*  65 */     setRotationAngle(this.neck, 0.9819F, 0.0F, 0.0F);
/*  66 */     this.neck.setTextureOffset(45, 15).addBox(-2.5F, -6.3895F, -3.8895F, 5.0F, 9.0F, 7.0F, 0.0F, false);
/*     */     
/*  68 */     this.head = new ModelRenderer((Model)this);
/*  69 */     this.head.setRotationPoint(-2.0F, 5.0F, -10.0F);
/*  70 */     this.head.setTextureOffset(43, 0).addBox(-1.5F, -4.5F, -6.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
/*     */     
/*  72 */     this.leftEar = new ModelRenderer((Model)this);
/*  73 */     this.leftEar.setRotationPoint(2.9F, -13.05F, -7.0F);
/*  74 */     this.head.addChild(this.leftEar);
/*  75 */     setRotationAngle(this.leftEar, 0.5864F, -1.3683F, 0.0F);
/*  76 */     this.leftEar.setTextureOffset(0, 0).addBox(4.3F, 4.4849F, -7.1516F, 1.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/*  78 */     this.rightEar = new ModelRenderer((Model)this);
/*  79 */     this.rightEar.setRotationPoint(-2.1F, -13.15F, -7.0F);
/*  80 */     this.head.addChild(this.rightEar);
/*  81 */     setRotationAngle(this.rightEar, -0.0391F, -1.7984F, -0.3129F);
/*  82 */     this.rightEar.setTextureOffset(0, 0).addBox(4.1016F, 6.7521F, -0.8553F, 1.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/*  84 */     this.snout = new ModelRenderer((Model)this);
/*  85 */     this.snout.setRotationPoint(2.0F, 1.15F, -5.7F);
/*  86 */     this.head.addChild(this.snout);
/*  87 */     setRotationAngle(this.snout, 0.1955F, 0.0F, 0.0F);
/*  88 */     this.snout.setTextureOffset(50, 32).addBox(-1.5F, -2.2452F, -3.9514F, 3.0F, 2.0F, 5.0F, 0.01F, false);
/*     */     
/*  90 */     this.snout2 = new ModelRenderer((Model)this);
/*  91 */     this.snout2.setRotationPoint(0.0F, -9.7355F, -3.011F);
/*  92 */     this.snout.addChild(this.snout2);
/*  93 */     setRotationAngle(this.snout2, -0.1955F, 0.0F, 0.0F);
/*  94 */     this.snout2.setTextureOffset(50, 40).addBox(-1.5F, 7.5F, 1.0F, 3.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/*  96 */     this.tail = new ModelRenderer((Model)this);
/*  97 */     this.tail.setRotationPoint(0.1F, 8.6F, 12.0F);
/*  98 */     setRotationAngle(this.tail, 0.3519F, 0.0F, 0.0F);
/*  99 */     this.tail.setTextureOffset(73, 1).addBox(-1.0F, -0.966F, -0.3461F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/* 101 */     this.tail2 = new ModelRenderer((Model)this);
/* 102 */     this.tail2.setRotationPoint(-0.5F, 4.05F, 1.1F);
/* 103 */     this.tail.addChild(this.tail2);
/* 104 */     setRotationAngle(this.tail2, -0.0782F, 0.0F, 0.0F);
/* 105 */     this.tail2.setTextureOffset(73, 9).addBox(-0.5F, -0.2402F, -1.45F, 2.0F, 5.0F, 2.0F, -0.01F, false);
/*     */     
/* 107 */     this.tail3 = new ModelRenderer((Model)this);
/* 108 */     this.tail3.setRotationPoint(0.0F, 4.0985F, 0.1813F);
/* 109 */     this.tail2.addChild(this.tail3);
/* 110 */     setRotationAngle(this.tail3, 0.2346F, 0.0F, 0.0F);
/* 111 */     this.tail3.setTextureOffset(73, 17).addBox(-0.5F, 0.1622F, -1.6313F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/* 113 */     this.leftRearLeg = new ModelRenderer((Model)this);
/* 114 */     this.leftRearLeg.setRotationPoint(4.1F, 11.5F, 9.65F);
/* 115 */     setRotationAngle(this.leftRearLeg, -0.2618F, 0.0F, 0.0F);
/* 116 */     this.leftRearLeg.setTextureOffset(1, 36).addBox(-1.1F, -2.926F, -1.6932F, 4.0F, 8.0F, 4.0F, 0.01F, false);
/*     */     
/* 118 */     this.leftRearLeg2 = new ModelRenderer((Model)this);
/* 119 */     this.leftRearLeg2.setRotationPoint(-24.1F, -3.9082F, -7.6316F);
/* 120 */     this.leftRearLeg.addChild(this.leftRearLeg2);
/* 121 */     setRotationAngle(this.leftRearLeg2, 0.7854F, 0.0F, 0.0F);
/* 122 */     this.leftRearLeg2.setTextureOffset(1, 49).addBox(23.0F, 10.4824F, -2.0051F, 4.0F, 6.0F, 4.0F, 0.0F, false);
/*     */     
/* 124 */     this.leftRearLeg3 = new ModelRenderer((Model)this);
/* 125 */     this.leftRearLeg3.setRotationPoint(24.75F, 14.4F, 1.4F);
/* 126 */     this.leftRearLeg2.addChild(this.leftRearLeg3);
/* 127 */     setRotationAngle(this.leftRearLeg3, -1.1868F, 0.0F, 0.0F);
/* 128 */     this.leftRearLeg3.setTextureOffset(3, 60).addBox(-1.25F, 1.047F, -1.8626F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 130 */     this.leftRearFoot = new ModelRenderer((Model)this);
/* 131 */     this.leftRearFoot.setRotationPoint(0.35F, 6.0F, -0.75F);
/* 132 */     this.leftRearLeg3.addChild(this.leftRearFoot);
/* 133 */     setRotationAngle(this.leftRearFoot, 0.6632F, 0.0F, 0.0F);
/* 134 */     this.leftRearFoot.setTextureOffset(1, 70).addBox(-1.8F, 0.0507F, -3.7232F, 3.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 136 */     this.rightRearLeg = new ModelRenderer((Model)this);
/* 137 */     this.rightRearLeg.setRotationPoint(-3.9F, 11.5F, 9.65F);
/* 138 */     setRotationAngle(this.rightRearLeg, -0.2618F, 0.0F, 0.0F);
/* 139 */     this.rightRearLeg.setTextureOffset(1, 36).addBox(-3.1F, -2.926F, -1.6932F, 4.0F, 8.0F, 4.0F, 0.01F, false);
/*     */     
/* 141 */     this.rightRearLeg2 = new ModelRenderer((Model)this);
/* 142 */     this.rightRearLeg2.setRotationPoint(-26.1F, -3.9082F, -7.6316F);
/* 143 */     this.rightRearLeg.addChild(this.rightRearLeg2);
/* 144 */     setRotationAngle(this.rightRearLeg2, 0.7854F, 0.0F, 0.0F);
/* 145 */     this.rightRearLeg2.setTextureOffset(1, 49).addBox(23.0F, 10.4824F, -2.0051F, 4.0F, 6.0F, 4.0F, 0.0F, false);
/*     */     
/* 147 */     this.rightRearLeg3 = new ModelRenderer((Model)this);
/* 148 */     this.rightRearLeg3.setRotationPoint(24.75F, 14.4F, 1.4F);
/* 149 */     this.rightRearLeg2.addChild(this.rightRearLeg3);
/* 150 */     setRotationAngle(this.rightRearLeg3, -1.1868F, 0.0F, 0.0F);
/* 151 */     this.rightRearLeg3.setTextureOffset(3, 60).addBox(-1.25F, 1.047F, -1.8626F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 153 */     this.rightRearFoot = new ModelRenderer((Model)this);
/* 154 */     this.rightRearFoot.setRotationPoint(0.35F, 6.0F, -0.75F);
/* 155 */     this.rightRearLeg3.addChild(this.rightRearFoot);
/* 156 */     setRotationAngle(this.rightRearFoot, 0.6632F, 0.0F, 0.0F);
/* 157 */     this.rightRearFoot.setTextureOffset(1, 70).addBox(-1.8F, 0.0507F, -3.7232F, 3.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 159 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 160 */     this.leftFrontLeg.setRotationPoint(2.7F, 8.5F, -3.75F);
/* 161 */     setRotationAngle(this.leftFrontLeg, 0.0873F, 0.0F, 0.0F);
/* 162 */     this.leftFrontLeg.setTextureOffset(35, 45).addBox(-0.1F, -1.818F, -2.0619F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/* 164 */     this.leftFrontLeg2 = new ModelRenderer((Model)this);
/* 165 */     this.leftFrontLeg2.setRotationPoint(2.35F, 5.5824F, 0.0351F);
/* 166 */     this.leftFrontLeg.addChild(this.leftFrontLeg2);
/* 167 */     setRotationAngle(this.leftFrontLeg2, -0.0873F, 0.0F, 0.0F);
/* 168 */     this.leftFrontLeg2.setTextureOffset(37, 58).addBox(-2.05F, 0.363F, -1.5637F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 170 */     this.leftFrontLeg3 = new ModelRenderer((Model)this);
/* 171 */     this.leftFrontLeg3.setRotationPoint(-0.15F, 5.663F, 0.3363F);
/* 172 */     this.leftFrontLeg2.addChild(this.leftFrontLeg3);
/* 173 */     this.leftFrontLeg3.setTextureOffset(37, 68).addBox(-1.9F, -2.5F, -1.5F, 3.0F, 6.0F, 3.0F, -0.01F, false);
/*     */     
/* 175 */     this.leftFrontFoot = new ModelRenderer((Model)this);
/* 176 */     this.leftFrontFoot.setRotationPoint(0.0F, 2.2F, 0.35F);
/* 177 */     this.leftFrontLeg3.addChild(this.leftFrontFoot);
/* 178 */     setRotationAngle(this.leftFrontFoot, 0.0349F, 0.0F, 0.0F);
/* 179 */     this.leftFrontFoot.setTextureOffset(36, 78).addBox(-1.9F, -0.0741F, -3.1679F, 3.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/* 181 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 182 */     this.rightFrontLeg.setRotationPoint(-3.3F, 8.5F, -3.75F);
/* 183 */     setRotationAngle(this.rightFrontLeg, 0.0873F, 0.0F, 0.0F);
/* 184 */     this.rightFrontLeg.setTextureOffset(35, 45).addBox(-4.1F, -1.818F, -2.0619F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/* 186 */     this.rightFrontLeg2 = new ModelRenderer((Model)this);
/* 187 */     this.rightFrontLeg2.setRotationPoint(-1.65F, 5.5824F, 0.0351F);
/* 188 */     this.rightFrontLeg.addChild(this.rightFrontLeg2);
/* 189 */     setRotationAngle(this.rightFrontLeg2, -0.0873F, 0.0F, 0.0F);
/* 190 */     this.rightFrontLeg2.setTextureOffset(37, 58).addBox(-2.05F, 0.363F, -1.5637F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 192 */     this.rightFrontLeg3 = new ModelRenderer((Model)this);
/* 193 */     this.rightFrontLeg3.setRotationPoint(-0.15F, 5.663F, 0.3363F);
/* 194 */     this.rightFrontLeg2.addChild(this.rightFrontLeg3);
/* 195 */     this.rightFrontLeg3.setTextureOffset(37, 68).addBox(-1.9F, -2.5F, -1.5F, 3.0F, 6.0F, 3.0F, -0.01F, false);
/*     */     
/* 197 */     this.rightFrontFoot = new ModelRenderer((Model)this);
/* 198 */     this.rightFrontFoot.setRotationPoint(0.0F, 2.2F, 0.35F);
/* 199 */     this.rightFrontLeg3.addChild(this.rightFrontFoot);
/* 200 */     setRotationAngle(this.rightFrontFoot, 0.0349F, 0.0F, 0.0F);
/* 201 */     this.rightFrontFoot.setTextureOffset(36, 78).addBox(-1.9F, -0.0741F, -3.1679F, 3.0F, 2.0F, 4.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 208 */     this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 1.1F * limbSwingAmount;
/* 209 */     this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 1.1F * limbSwingAmount;
/* 210 */     this.rightRearLeg.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 1.1F * limbSwingAmount;
/* 211 */     this.leftRearLeg.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 1.1F * limbSwingAmount;
/* 212 */     if (entity.isSprinting()) {
/* 213 */       this.tail.rotateAngleX = 1.2F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     
/*     */     }
/* 216 */     else if (!(entity instanceof xyz.pixelatedw.mineminenomi.entities.DummyEntity)) {
/*     */       
/* 218 */       this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 5.0D);
/* 219 */       this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 5.0D);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 224 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 225 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 227 */       this.head.rotateAngleY += this.body.rotateAngleY;
/* 228 */       float f1 = 1.0F - this.swingProgress;
/* 229 */       f1 *= f1;
/* 230 */       f1 *= f1;
/* 231 */       f1 = 1.0F - f1;
/* 232 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 233 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
/* 234 */       this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 1.5D + f3);
/* 235 */       this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 242 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 243 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 244 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
/* 245 */     this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 246 */     this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 247 */     this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 248 */     this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 254 */     this.head.translateRotate(matrixStack);
/* 255 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 256 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
/* 257 */     matrixStack.translate(0.3D, 0.0D, -0.03D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 272 */     modelRenderer.rotateAngleX = x;
/* 273 */     modelRenderer.rotateAngleY = y;
/* 274 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\LeopardWalkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */