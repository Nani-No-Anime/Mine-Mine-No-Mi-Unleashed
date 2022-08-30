/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class LapahnModel<T extends LapahnEntity> extends BipedModel<T> {
/*     */   public ModelRenderer leftLeg1;
/*     */   public ModelRenderer leftLeg2;
/*     */   public ModelRenderer leftFoot;
/*     */   public ModelRenderer head;
/*     */   public ModelRenderer body1;
/*     */   public ModelRenderer body2;
/*     */   public ModelRenderer body3;
/*     */   public ModelRenderer body4;
/*     */   public ModelRenderer body5;
/*     */   public ModelRenderer tail;
/*     */   public ModelRenderer rightLeg1;
/*     */   public ModelRenderer rightArm1;
/*     */   public ModelRenderer leftArm1;
/*     */   public ModelRenderer wiskers;
/*     */   public ModelRenderer rightEar;
/*     */   public ModelRenderer leftEar;
/*     */   public ModelRenderer rightLeg2;
/*     */   public ModelRenderer rightFoot;
/*     */   public ModelRenderer rightArm2;
/*     */   public ModelRenderer leftArm2;
/*     */   
/*     */   public LapahnModel() {
/*  39 */     super(1.0F);
/*  40 */     this.textureWidth = 140;
/*  41 */     this.textureHeight = 70;
/*     */     
/*  43 */     this.bipedBody.showModel = false;
/*  44 */     this.bipedHead.showModel = false;
/*  45 */     this.bipedHeadwear.showModel = false;
/*  46 */     this.bipedLeftArm.showModel = false;
/*  47 */     this.bipedLeftLeg.showModel = false;
/*  48 */     this.bipedRightArm.showModel = false;
/*  49 */     this.bipedRightLeg.showModel = false;
/*     */     
/*  51 */     this.wiskers = new ModelRenderer((Model)this, 92, 44);
/*  52 */     this.wiskers.setRotationPoint(0.0F, -8.0F, -0.1F);
/*  53 */     this.wiskers.addBox(-4.5F, -6.0F, -3.0F, 9.0F, 9.0F, 0.0F, 0.0F);
/*  54 */     this.leftEar = new ModelRenderer((Model)this, 25, 0);
/*  55 */     this.leftEar.setRotationPoint(1.7F, -4.5F, 0.0F);
/*  56 */     this.leftEar.addBox(-1.0F, -6.0F, -0.5F, 2.0F, 6.0F, 1.0F, 0.0F);
/*  57 */     setRotateAngle(this.leftEar, 0.071907565F, -0.1738348F, 0.08866273F);
/*  58 */     this.body4 = new ModelRenderer((Model)this, 35, 54);
/*  59 */     this.body4.setRotationPoint(0.0F, -5.0F, 0.0F);
/*  60 */     this.body4.addBox(-7.0F, 0.0F, -5.0F, 14.0F, 5.0F, 10.0F, 0.0F);
/*  61 */     this.leftFoot = new ModelRenderer((Model)this, 0, 37);
/*  62 */     this.leftFoot.setRotationPoint(0.0F, 3.0F, 0.7F);
/*  63 */     this.leftFoot.addBox(-2.5F, 0.0F, -8.0F, 5.0F, 2.0F, 10.0F, 0.0F);
/*  64 */     setRotateAngle(this.leftFoot, -0.034906585F, 0.0F, 0.0F);
/*  65 */     this.leftLeg1 = new ModelRenderer((Model)this, 0, 13);
/*  66 */     this.leftLeg1.setRotationPoint(4.8F, 14.9F, -2.2F);
/*  67 */     this.leftLeg1.addBox(-4.0F, -1.0F, -4.0F, 8.0F, 6.0F, 9.0F, 0.0F);
/*  68 */     setRotateAngle(this.leftLeg1, -0.2443461F, -0.0F, 0.0F);
/*  69 */     this.body1 = new ModelRenderer((Model)this, 35, 0);
/*  70 */     this.body1.setRotationPoint(0.0F, 16.0F, 0.0F);
/*  71 */     this.body1.addBox(-8.0F, 0.0F, -5.5F, 16.0F, 1.0F, 11.0F, 0.0F);
/*  72 */     this.rightEar = new ModelRenderer((Model)this, 25, 0);
/*  73 */     this.rightEar.setRotationPoint(-1.7F, -4.5F, 0.0F);
/*  74 */     this.rightEar.addBox(-1.0F, -6.0F, -0.5F, 2.0F, 6.0F, 1.0F, 0.0F);
/*  75 */     setRotateAngle(this.rightEar, 0.071907565F, 0.1738348F, -0.08866273F);
/*  76 */     this.body3 = new ModelRenderer((Model)this, 35, 36);
/*  77 */     this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  78 */     this.body3.addBox(-8.0F, 0.0F, -5.5F, 16.0F, 6.0F, 11.0F, 0.0F);
/*  79 */     this.rightLeg1 = new ModelRenderer((Model)this, 0, 13);
/*  80 */     this.rightLeg1.setRotationPoint(-4.8F, 14.9F, -2.2F);
/*  81 */     this.rightLeg1.addBox(-4.0F, -1.0F, -4.0F, 8.0F, 6.0F, 9.0F, 0.0F);
/*  82 */     setRotateAngle(this.rightLeg1, -0.2443461F, -0.0F, 0.0F);
/*  83 */     this.body2 = new ModelRenderer((Model)this, 35, 13);
/*  84 */     this.body2.setRotationPoint(0.0F, 6.0F, 0.0F);
/*  85 */     this.body2.addBox(-8.5F, 0.0F, -6.0F, 17.0F, 10.0F, 12.0F, 0.0F);
/*  86 */     this.leftLeg2 = new ModelRenderer((Model)this, 0, 29);
/*  87 */     this.leftLeg2.setRotationPoint(0.0F, 3.3F, 3.0F);
/*  88 */     this.leftLeg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F);
/*  89 */     setRotateAngle(this.leftLeg2, 0.33161256F, -0.0F, 0.0F);
/*  90 */     this.rightFoot = new ModelRenderer((Model)this, 0, 37);
/*  91 */     this.rightFoot.setRotationPoint(0.0F, 3.0F, 0.7F);
/*  92 */     this.rightFoot.addBox(-2.5F, 0.0F, -8.0F, 5.0F, 2.0F, 10.0F, 0.0F);
/*  93 */     setRotateAngle(this.rightFoot, -0.034906585F, 0.0F, 0.0F);
/*  94 */     this.body5 = new ModelRenderer((Model)this, 90, 0);
/*  95 */     this.body5.setRotationPoint(0.0F, -7.0F, 0.0F);
/*  96 */     this.body5.addBox(-6.5F, 0.0F, -4.5F, 13.0F, 2.0F, 9.0F, 0.0F);
/*  97 */     this.tail = new ModelRenderer((Model)this, 0, 50);
/*  98 */     this.tail.setRotationPoint(0.0F, 13.0F, 5.8F);
/*  99 */     this.tail.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 100 */     setRotateAngle(this.tail, -0.10471976F, -0.0F, 0.0F);
/* 101 */     this.rightLeg2 = new ModelRenderer((Model)this, 0, 29);
/* 102 */     this.rightLeg2.setRotationPoint(0.0F, 3.3F, 3.0F);
/* 103 */     this.rightLeg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F);
/* 104 */     setRotateAngle(this.rightLeg2, 0.33161256F, -0.0F, 0.0F);
/* 105 */     this.head = new ModelRenderer((Model)this, 0, 0);
/* 106 */     this.head.setRotationPoint(0.0F, -8.0F, 0.0F);
/* 107 */     this.head.addBox(-3.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F);
/* 108 */     this.leftArm2 = new ModelRenderer((Model)this, 94, 27);
/* 109 */     this.leftArm2.setRotationPoint(2.5F, 5.5F, 0.0F);
/* 110 */     this.leftArm2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
/* 111 */     setRotateAngle(this.leftArm2, 0.0F, -0.0F, 0.12217305F);
/* 112 */     this.rightArm1 = new ModelRenderer((Model)this, 94, 13);
/* 113 */     this.rightArm1.setRotationPoint(-6.5F, -3.0F, 0.0F);
/* 114 */     this.rightArm1.addBox(-5.0F, -2.0F, -2.5F, 5.0F, 8.0F, 5.0F, 0.0F);
/* 115 */     setRotateAngle(this.rightArm1, 0.0F, -0.0F, 0.2617994F);
/* 116 */     this.leftArm1 = new ModelRenderer((Model)this, 94, 13);
/* 117 */     this.leftArm1.setRotationPoint(6.5F, -3.0F, 0.0F);
/* 118 */     this.leftArm1.addBox(0.0F, -2.0F, -2.5F, 5.0F, 8.0F, 5.0F, 0.0F);
/* 119 */     setRotateAngle(this.leftArm1, 0.0F, -0.0F, -0.2617994F);
/* 120 */     this.rightArm2 = new ModelRenderer((Model)this, 94, 27);
/* 121 */     this.rightArm2.setRotationPoint(-2.5F, 5.5F, 0.0F);
/* 122 */     this.rightArm2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
/* 123 */     setRotateAngle(this.rightArm2, 0.0F, -0.0F, -0.12217305F);
/* 124 */     this.head.addChild(this.leftEar);
/* 125 */     this.leftLeg2.addChild(this.leftFoot);
/* 126 */     this.head.addChild(this.rightEar);
/* 127 */     this.leftLeg1.addChild(this.leftLeg2);
/* 128 */     this.rightLeg2.addChild(this.rightFoot);
/* 129 */     this.rightLeg1.addChild(this.rightLeg2);
/* 130 */     this.leftArm1.addChild(this.leftArm2);
/* 131 */     this.rightArm1.addChild(this.rightArm2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 137 */     this.wiskers.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 138 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 139 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 140 */     this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 141 */     this.body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 142 */     this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 143 */     this.leftLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 144 */     this.body5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 145 */     this.rightArm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 146 */     this.rightLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 147 */     this.leftArm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 148 */     this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 155 */     if (Math.sqrt(entity.getDistanceSq(((LapahnEntity)entity).prevPosX, ((LapahnEntity)entity).prevPosY, ((LapahnEntity)entity).prevPosZ)) > 0.0D) {
/*     */       
/* 157 */       this.rightLeg1.rotateAngleX = 0.9F * (-0.2F + MathHelper.cos(ageInTicks * 0.5F));
/* 158 */       this.leftLeg1.rotateAngleX = 0.9F * (-0.2F + MathHelper.cos(ageInTicks * 0.5F));
/* 159 */       this.rightArm1.rotateAngleX = -0.5F * (-0.2F + MathHelper.sin(ageInTicks * 0.45F));
/* 160 */       this.leftArm1.rotateAngleX = -0.5F * (-0.2F + MathHelper.sin(ageInTicks * 0.45F));
/* 161 */       float formula = (0.0F - 0.9F + MathHelper.cos(ageInTicks * 0.45F)) * 10.0F;
/* 162 */       this.head.rotationPointY = -8.0F + formula;
/* 163 */       this.rightArm1.rotationPointY = -3.0F + formula;
/* 164 */       this.leftArm1.rotationPointY = -3.0F + formula;
/* 165 */       this.head.rotationPointY = -8.0F + formula;
/* 166 */       this.wiskers.rotationPointY = -8.0F + formula;
/* 167 */       this.body1.rotationPointY = 16.0F + formula;
/* 168 */       this.body2.rotationPointY = 6.0F + formula;
/* 169 */       this.body3.rotationPointY = formula;
/* 170 */       this.body4.rotationPointY = -5.0F + formula;
/* 171 */       this.body5.rotationPointY = -7.0F + formula;
/* 172 */       this.leftLeg1.rotationPointY = 14.9F + formula;
/* 173 */       this.rightLeg1.rotationPointY = 14.9F + formula;
/* 174 */       this.tail.rotationPointY = 13.0F + formula;
/*     */     }
/*     */     else {
/*     */       
/* 178 */       this.rightLeg1.rotateAngleX = (float)Math.toRadians(-17.0D);
/* 179 */       this.leftLeg1.rotateAngleX = (float)Math.toRadians(-17.0D);
/* 180 */       this.rightArm1.rotateAngleX = (float)Math.toRadians(0.0D);
/* 181 */       this.leftArm1.rotateAngleX = (float)Math.toRadians(0.0D);
/* 182 */       this.head.rotationPointY = -8.0F;
/* 183 */       this.rightArm1.rotationPointY = -3.0F;
/* 184 */       this.leftArm1.rotationPointY = -3.0F;
/* 185 */       this.head.rotationPointY = -8.0F;
/* 186 */       this.wiskers.rotationPointY = -8.0F;
/* 187 */       this.body1.rotationPointY = 16.0F;
/* 188 */       this.body2.rotationPointY = 6.0F;
/* 189 */       this.body3.rotationPointY = 0.0F;
/* 190 */       this.body4.rotationPointY = -5.0F;
/* 191 */       this.body5.rotationPointY = -7.0F;
/* 192 */       this.leftLeg1.rotationPointY = 14.9F;
/* 193 */       this.rightLeg1.rotationPointY = 14.9F;
/* 194 */       this.tail.rotationPointY = 13.0F;
/*     */ 
/*     */       
/* 197 */       if (ageInTicks % 300.0F > 0.0F && ageInTicks % 300.0F < 100.0F) {
/* 198 */         this.leftEar.rotateAngleX = 0.1F * (0.3F + MathHelper.cos(ageInTicks * 0.55F));
/*     */       } else {
/* 200 */         this.leftEar.rotateAngleX = (float)Math.toRadians(0.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 206 */     model.rotateAngleX = x;
/* 207 */     model.rotateAngleY = y;
/* 208 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\LapahnModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */