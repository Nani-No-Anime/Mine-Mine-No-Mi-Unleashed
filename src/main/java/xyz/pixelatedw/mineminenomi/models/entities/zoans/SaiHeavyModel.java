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
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class SaiHeavyModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer lowerHead;
/*     */   private final ModelRenderer upperHead;
/*     */   private final ModelRenderer middleHead;
/*     */   private final ModelRenderer horn1;
/*     */   private final ModelRenderer horn2;
/*     */   private final ModelRenderer horn3;
/*     */   private final ModelRenderer horn4;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer leftEar;
/*     */   
/*     */   public SaiHeavyModel() {
/*  42 */     super(1.0F);
/*  43 */     this.textureWidth = 128;
/*  44 */     this.textureHeight = 128;
/*     */     
/*  46 */     this.body = new ModelRenderer((Model)this);
/*  47 */     this.body.setRotationPoint(0.0F, 4.6667F, 0.0F);
/*  48 */     this.body.setTextureOffset(0, 0).addBox(-4.0F, -10.6667F, -8.5F, 10.0F, 12.0F, 17.0F, 0.0F, false);
/*  49 */     this.body.setTextureOffset(0, 29).addBox(-6.0F, -9.6667F, -6.5F, 11.0F, 15.0F, 13.0F, 0.0F, false);
/*  50 */     this.body.setTextureOffset(37, 0).addBox(-5.0F, 4.3333F, -5.5F, 9.0F, 5.0F, 11.0F, 0.0F, false);
/*     */     
/*  52 */     this.rightLeg = new ModelRenderer((Model)this);
/*  53 */     this.rightLeg.setRotationPoint(1.45F, 12.5F, -3.5F);
/*  54 */     setRotationAngle(this.rightLeg, 0.0F, 0.0F, 0.1745F);
/*  55 */     this.rightLeg.setTextureOffset(66, 0).addBox(-4.45F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
/*     */     
/*  57 */     this.rightLeg2 = new ModelRenderer((Model)this);
/*  58 */     this.rightLeg2.setRotationPoint(-2.3908F, 6.3707F, 0.0F);
/*  59 */     this.rightLeg.addChild(this.rightLeg2);
/*  60 */     setRotationAngle(this.rightLeg2, 0.0F, 0.0F, -0.3054F);
/*  61 */     this.rightLeg2.setTextureOffset(72, 11).addBox(-1.5092F, -1.1207F, -2.5F, 4.0F, 6.0F, 5.0F, -0.01F, false);
/*     */     
/*  63 */     this.rightLeg3 = new ModelRenderer((Model)this);
/*  64 */     this.rightLeg3.setRotationPoint(1.5408F, 5.1293F, 0.0F);
/*  65 */     this.rightLeg2.addChild(this.rightLeg3);
/*  66 */     setRotationAngle(this.rightLeg3, 0.0F, 0.0F, 0.1309F);
/*  67 */     this.rightLeg3.setTextureOffset(62, 28).addBox(-3.5F, -1.0F, -2.5F, 7.0F, 2.0F, 5.0F, 0.0F, true);
/*     */     
/*  69 */     this.rightArm = new ModelRenderer((Model)this);
/*  70 */     this.rightArm.setRotationPoint(1.0F, -1.25F, -7.5F);
/*  71 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.1745F);
/*  72 */     this.rightArm.setTextureOffset(0, 57).addBox(-3.0F, -3.25F, -5.5F, 6.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/*  74 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  75 */     this.rightArm2.setRotationPoint(-1.1263F, 7.7688F, -3.0F);
/*  76 */     this.rightArm.addChild(this.rightArm2);
/*  77 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.2618F);
/*  78 */     this.rightArm2.setTextureOffset(0, 72).addBox(-1.0737F, -1.5188F, -2.0F, 5.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/*  80 */     this.leftLeg = new ModelRenderer((Model)this);
/*  81 */     this.leftLeg.setRotationPoint(-0.8F, 12.0F, 3.5F);
/*  82 */     setRotationAngle(this.leftLeg, 0.0F, 0.0F, 0.1745F);
/*  83 */     this.leftLeg.setTextureOffset(66, 0).addBox(-2.2F, 0.25F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, true);
/*     */     
/*  85 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  86 */     this.leftLeg2.setRotationPoint(-0.6408F, 6.1207F, 0.0F);
/*  87 */     this.leftLeg.addChild(this.leftLeg2);
/*  88 */     setRotationAngle(this.leftLeg2, 0.0F, 0.0F, -0.3054F);
/*  89 */     this.leftLeg2.setTextureOffset(72, 11).addBox(-1.0592F, -0.6207F, -2.5F, 4.0F, 6.0F, 5.0F, -0.01F, true);
/*     */     
/*  91 */     this.leftLeg3 = new ModelRenderer((Model)this);
/*  92 */     this.leftLeg3.setRotationPoint(1.5408F, 4.8793F, 0.0F);
/*  93 */     this.leftLeg2.addChild(this.leftLeg3);
/*  94 */     setRotationAngle(this.leftLeg3, 0.0F, 0.0F, 0.1309F);
/*  95 */     this.leftLeg3.setTextureOffset(62, 28).addBox(-3.0F, -0.25F, -2.5F, 7.0F, 2.0F, 5.0F, 0.0F, true);
/*     */     
/*  97 */     this.leftArm = new ModelRenderer((Model)this);
/*  98 */     this.leftArm.setRotationPoint(1.0F, -2.0F, 7.5F);
/*  99 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, 0.1745F);
/* 100 */     this.leftArm.setTextureOffset(0, 57).addBox(-3.0F, -2.5F, 0.5F, 6.0F, 10.0F, 5.0F, 0.0F, true);
/*     */     
/* 102 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 103 */     this.leftArm2.setRotationPoint(1.1883F, 6.7568F, 3.0F);
/* 104 */     this.leftArm.addChild(this.leftArm2);
/* 105 */     setRotationAngle(this.leftArm2, 0.0F, 3.1416F, -0.2618F);
/* 106 */     this.leftArm2.setTextureOffset(0, 72).addBox(-1.1383F, -0.7568F, -2.0F, 5.0F, 7.0F, 4.0F, 0.0F, true);
/*     */     
/* 108 */     this.neck = new ModelRenderer((Model)this);
/* 109 */     this.neck.setRotationPoint(-1.0F, 24.5F, 0.0F);
/* 110 */     setRotationAngle(this.neck, 0.0F, 0.0F, 0.2182F);
/* 111 */     this.neck.setTextureOffset(54, 16).addBox(-8.6F, -33.25F, -3.5F, 4.0F, 5.0F, 7.0F, 0.0F, false);
/*     */     
/* 113 */     this.head = new ModelRenderer((Model)this);
/* 114 */     this.head.setRotationPoint(-1.0F, 24.0F, 0.0F);
/* 115 */     this.head.setTextureOffset(0, 0).addBox(2.0F, -34.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 117 */     this.lowerHead = new ModelRenderer((Model)this);
/* 118 */     this.lowerHead.setRotationPoint(4.1283F, -31.9102F, 0.0F);
/* 119 */     this.head.addChild(this.lowerHead);
/* 120 */     setRotationAngle(this.lowerHead, 0.0F, 0.0F, -0.4363F);
/* 121 */     this.lowerHead.setTextureOffset(48, 42).addBox(-4.0F, -4.0F, -3.5F, 8.0F, 8.0F, 7.0F, 0.01F, false);
/*     */     
/* 123 */     this.upperHead = new ModelRenderer((Model)this);
/* 124 */     this.upperHead.setRotationPoint(9.9143F, -30.4729F, 0.0F);
/* 125 */     this.head.addChild(this.upperHead);
/* 126 */     setRotationAngle(this.upperHead, 0.0F, 0.0F, 0.48F);
/* 127 */     this.upperHead.setTextureOffset(35, 29).addBox(-5.0F, -3.0F, -3.5F, 10.0F, 6.0F, 7.0F, 0.02F, false);
/*     */     
/* 129 */     this.middleHead = new ModelRenderer((Model)this);
/* 130 */     this.middleHead.setRotationPoint(7.0387F, -34.7554F, 0.0F);
/* 131 */     this.head.addChild(this.middleHead);
/* 132 */     setRotationAngle(this.middleHead, 0.0F, 0.0F, 0.5236F);
/* 133 */     this.middleHead.setTextureOffset(44, 57).addBox(-2.5F, -1.0F, -3.5F, 5.0F, 2.0F, 7.0F, 0.0F, false);
/*     */     
/* 135 */     this.horn1 = new ModelRenderer((Model)this);
/* 136 */     this.horn1.setRotationPoint(15.3463F, -32.0735F, 0.0F);
/* 137 */     this.head.addChild(this.horn1);
/* 138 */     setRotationAngle(this.horn1, 0.0F, 0.0F, 0.6109F);
/* 139 */     this.horn1.setTextureOffset(0, 5).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 141 */     this.horn2 = new ModelRenderer((Model)this);
/* 142 */     this.horn2.setRotationPoint(16.3124F, -34.0707F, 0.0F);
/* 143 */     this.head.addChild(this.horn2);
/* 144 */     setRotationAngle(this.horn2, 0.0F, 0.0F, 0.3491F);
/* 145 */     this.horn2.setTextureOffset(0, 0).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.01F, false);
/*     */     
/* 147 */     this.horn3 = new ModelRenderer((Model)this);
/* 148 */     this.horn3.setRotationPoint(12.8548F, -32.4177F, 0.0F);
/* 149 */     this.head.addChild(this.horn3);
/* 150 */     setRotationAngle(this.horn3, 0.0F, 0.0F, 0.6109F);
/* 151 */     this.horn3.setTextureOffset(6, 3).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 153 */     this.horn4 = new ModelRenderer((Model)this);
/* 154 */     this.horn4.setRotationPoint(13.4789F, -33.4751F, 0.0F);
/* 155 */     this.head.addChild(this.horn4);
/* 156 */     setRotationAngle(this.horn4, 0.0F, 0.0F, 0.3491F);
/* 157 */     this.horn4.setTextureOffset(8, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
/*     */     
/* 159 */     this.rightEar = new ModelRenderer((Model)this);
/* 160 */     this.rightEar.setRotationPoint(6.3332F, -37.2594F, -2.8669F);
/* 161 */     this.head.addChild(this.rightEar);
/* 162 */     setRotationAngle(this.rightEar, 0.1745F, 0.0F, 0.4363F);
/* 163 */     this.rightEar.setTextureOffset(0, 9).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);
/*     */     
/* 165 */     this.leftEar = new ModelRenderer((Model)this);
/* 166 */     this.leftEar.setRotationPoint(6.3446F, -37.2837F, 2.9562F);
/* 167 */     this.head.addChild(this.leftEar);
/* 168 */     setRotationAngle(this.leftEar, -0.1745F, 0.0F, 0.4363F);
/* 169 */     this.leftEar.setTextureOffset(0, 9).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);
/*     */     
/* 171 */     this.bipedBody = this.body;
/* 172 */     this.bipedHead = this.head;
/* 173 */     this.bipedRightArm = this.rightArm;
/* 174 */     this.bipedLeftArm = this.leftArm;
/* 175 */     this.bipedRightLeg = this.rightLeg;
/* 176 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 182 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 183 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 184 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 185 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 186 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 187 */     this.neck.render(matrixStack, buffer, packedLight, packedOverlay);
/* 188 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 195 */     float f = 1.0F;
/* 196 */     this.rightArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 197 */     this.leftArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 198 */     this.rightLeg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 199 */     this.leftLeg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 200 */     if (!entity.getHeldItemMainhand().isEmpty()) {
/* 201 */       this.rightArm.rotateAngleZ += -0.15F;
/*     */     }
/*     */     
/* 204 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 205 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 206 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 208 */       if (isBlackLeg) {
/*     */         
/* 210 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 211 */         this.leftLeg.rotateAngleY += this.body.rotateAngleY;
/* 212 */         this.leftLeg.rotateAngleX += this.body.rotateAngleY;
/* 213 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY * 3.0F;
/* 214 */         this.rightLeg.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -1.9F;
/*     */       }
/*     */       else {
/*     */         
/* 218 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 219 */         this.leftArm.rotateAngleY += this.body.rotateAngleY;
/* 220 */         this.leftArm.rotateAngleX += this.body.rotateAngleY;
/* 221 */         float f1 = 1.0F - this.swingProgress;
/* 222 */         f1 *= f1;
/* 223 */         f1 *= f1;
/* 224 */         f1 = 1.0F - f1;
/* 225 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 226 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * 0.75F;
/* 227 */         this.rightArm.rotateAngleX -= (float)(this.rightArm.rotateAngleX - f2 * -0.8D + f3);
/* 228 */         this.rightArm.rotateAngleY += this.body.rotateAngleY * 1.0F;
/* 229 */         this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -1.9F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 237 */     modelRenderer.rotateAngleX = x;
/* 238 */     modelRenderer.rotateAngleY = y;
/* 239 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 245 */     super.translateHand(side, matrixStack);
/* 246 */     matrixStack.translate(-0.1D, 0.3D, -0.12D);
/* 247 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/* 248 */     matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 254 */     if (side == HandSide.RIGHT) {
/*     */       
/* 256 */       matrixStack.translate(-0.4D, -0.2D, -0.3D);
/* 257 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-270.0F));
/* 258 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
/* 259 */       this.rightArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 263 */       matrixStack.translate(0.4D, -0.2D, -0.3D);
/* 264 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-270.0F));
/* 265 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
/* 266 */       this.leftArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 273 */     if (side == HandSide.RIGHT) {
/*     */       
/* 275 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(50.0F));
/* 276 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(40.0F));
/* 277 */       matrixStack.translate(-0.1D, -0.9D, 0.2D);
/* 278 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 282 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(150.0F));
/* 283 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(-20.0F));
/* 284 */       matrixStack.translate(-0.1D, -0.9D, -0.2D);
/* 285 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\SaiHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */