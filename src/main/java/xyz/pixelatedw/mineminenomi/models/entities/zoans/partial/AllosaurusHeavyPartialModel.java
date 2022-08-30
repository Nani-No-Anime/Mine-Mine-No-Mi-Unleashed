/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;
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
/*     */ public class AllosaurusHeavyPartialModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftFeet;
/*     */   private final ModelRenderer leftFeet2;
/*     */   private final ModelRenderer leftToe1;
/*     */   private final ModelRenderer leftToe2;
/*     */   private final ModelRenderer leftToe3;
/*     */   private final ModelRenderer leftToe4;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightFeet;
/*     */   private final ModelRenderer rightFeet2;
/*     */   private final ModelRenderer rightToe;
/*     */   private final ModelRenderer rightToe2;
/*     */   private final ModelRenderer rightToe3;
/*     */   private final ModelRenderer rightToe4;
/*     */   public final ModelRenderer bodyScales;
/*     */   public final ModelRenderer headScales;
/*     */   public final ModelRenderer leftArmScales;
/*     */   public final ModelRenderer rightArmScales;
/*     */   
/*     */   public AllosaurusHeavyPartialModel() {
/*  43 */     super(1.0F);
/*  44 */     this.textureWidth = 128;
/*  45 */     this.textureHeight = 128;
/*     */     
/*  47 */     this.tail = new ModelRenderer((Model)this);
/*  48 */     this.tail.setRotationPoint(0.0F, 8.75F, 1.5F);
/*  49 */     setRotationAngle(this.tail, -0.1309F, 0.0F, 0.0F);
/*  50 */     this.tail.setTextureOffset(25, 28).addBox(-3.0F, -5.7243F, -0.3916F, 6.0F, 6.0F, 10.0F, 0.0F, false);
/*     */     
/*  52 */     this.tail2 = new ModelRenderer((Model)this);
/*  53 */     this.tail2.setRotationPoint(0.0F, 0.0F, 10.0F);
/*  54 */     this.tail.addChild(this.tail2);
/*  55 */     setRotationAngle(this.tail2, 0.0873F, 0.0F, 0.0F);
/*  56 */     this.tail2.setTextureOffset(26, 45).addBox(-2.5F, -5.2471F, -0.6308F, 5.0F, 5.0F, 10.0F, 0.0F, false);
/*     */     
/*  58 */     this.tail3 = new ModelRenderer((Model)this);
/*  59 */     this.tail3.setRotationPoint(0.0F, 0.0F, 8.0F);
/*  60 */     this.tail2.addChild(this.tail3);
/*  61 */     setRotationAngle(this.tail3, 0.0436F, 0.0F, 0.0F);
/*  62 */     this.tail3.setTextureOffset(22, 61).addBox(-2.0F, -4.75F, 0.5F, 4.0F, 4.0F, 15.0F, 0.0F, false);
/*     */     
/*  64 */     this.leftLeg = new ModelRenderer((Model)this);
/*  65 */     this.leftLeg.setRotationPoint(2.5F, 11.396F, 0.0604F);
/*  66 */     setRotationAngle(this.leftLeg, 0.2182F, 0.0F, 0.0F);
/*  67 */     this.leftLeg.setTextureOffset(0, 34).addBox(-0.5F, -3.3918F, -2.0119F, 3.0F, 8.0F, 5.0F, 0.01F, false);
/*     */     
/*  69 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  70 */     this.leftLeg2.setRotationPoint(0.0F, 3.8013F, -0.0043F);
/*  71 */     this.leftLeg.addChild(this.leftLeg2);
/*  72 */     setRotationAngle(this.leftLeg2, -0.6981F, 0.0F, 0.0F);
/*  73 */     this.leftLeg2.setTextureOffset(1, 49).addBox(-0.5F, -1.4808F, -1.3065F, 3.0F, 9.0F, 4.0F, 0.0F, false);
/*     */     
/*  75 */     this.leftFeet = new ModelRenderer((Model)this);
/*  76 */     this.leftFeet.setRotationPoint(-14.0F, 5.7326F, 2.6722F);
/*  77 */     this.leftLeg2.addChild(this.leftFeet);
/*  78 */     setRotationAngle(this.leftFeet, 0.48F, 0.0F, 0.0F);
/*  79 */     this.leftFeet.setTextureOffset(0, 63).addBox(13.0F, -0.5F, -4.5F, 4.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  81 */     this.leftFeet2 = new ModelRenderer((Model)this);
/*  82 */     this.leftFeet2.setRotationPoint(0.0F, 0.5F, -3.0F);
/*  83 */     this.leftFeet.addChild(this.leftFeet2);
/*  84 */     this.leftFeet2.setTextureOffset(0, 72).addBox(12.5F, 0.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.01F, false);
/*     */     
/*  86 */     this.leftToe1 = new ModelRenderer((Model)this);
/*  87 */     this.leftToe1.setRotationPoint(16.8372F, 1.3799F, -3.6384F);
/*  88 */     this.leftFeet2.addChild(this.leftToe1);
/*  89 */     setRotationAngle(this.leftToe1, 0.0873F, -0.1745F, -0.0036F);
/*  90 */     this.leftToe1.setTextureOffset(0, 80).addBox(-0.1632F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/*  92 */     this.leftToe2 = new ModelRenderer((Model)this);
/*  93 */     this.leftToe2.setRotationPoint(15.0F, 1.5361F, -3.8781F);
/*  94 */     this.leftFeet2.addChild(this.leftToe2);
/*  95 */     setRotationAngle(this.leftToe2, 0.0873F, 0.0F, 0.0F);
/*  96 */     this.leftToe2.setTextureOffset(0, 80).addBox(-0.5F, -1.2064F, -1.5019F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/*  98 */     this.leftToe3 = new ModelRenderer((Model)this);
/*  99 */     this.leftToe3.setRotationPoint(12.3246F, 1.2317F, -3.7439F);
/* 100 */     this.leftFeet2.addChild(this.leftToe3);
/* 101 */     setRotationAngle(this.leftToe3, 0.0873F, 0.1745F, 0.0F);
/* 102 */     this.leftToe3.setTextureOffset(0, 80).addBox(-0.0868F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 104 */     this.leftToe4 = new ModelRenderer((Model)this);
/* 105 */     this.leftToe4.setRotationPoint(14.5F, -1.038F, 3.3855F);
/* 106 */     this.leftFeet2.addChild(this.leftToe4);
/* 107 */     setRotationAngle(this.leftToe4, -0.5672F, 0.0F, 0.0F);
/* 108 */     this.leftToe4.setTextureOffset(9, 81).addBox(0.0F, -0.2686F, -1.0783F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 110 */     this.rightLeg = new ModelRenderer((Model)this);
/* 111 */     this.rightLeg.setRotationPoint(-2.5F, 11.396F, 0.0604F);
/* 112 */     setRotationAngle(this.rightLeg, 0.2182F, 0.0F, 0.0F);
/* 113 */     this.rightLeg.setTextureOffset(0, 34).addBox(-2.5F, -3.3918F, -2.0119F, 3.0F, 8.0F, 5.0F, 0.01F, false);
/*     */     
/* 115 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 116 */     this.rightLeg2.setRotationPoint(-2.0F, 3.8013F, -0.0043F);
/* 117 */     this.rightLeg.addChild(this.rightLeg2);
/* 118 */     setRotationAngle(this.rightLeg2, -0.6981F, 0.0F, 0.0F);
/* 119 */     this.rightLeg2.setTextureOffset(1, 49).addBox(-0.5F, -1.4808F, -1.3065F, 3.0F, 9.0F, 4.0F, 0.0F, false);
/*     */     
/* 121 */     this.rightFeet = new ModelRenderer((Model)this);
/* 122 */     this.rightFeet.setRotationPoint(-14.0F, 5.7326F, 2.6722F);
/* 123 */     this.rightLeg2.addChild(this.rightFeet);
/* 124 */     setRotationAngle(this.rightFeet, 0.48F, 0.0F, 0.0F);
/* 125 */     this.rightFeet.setTextureOffset(0, 63).addBox(13.0F, -0.5F, -4.5F, 4.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 127 */     this.rightFeet2 = new ModelRenderer((Model)this);
/* 128 */     this.rightFeet2.setRotationPoint(0.0F, 0.5F, -3.0F);
/* 129 */     this.rightFeet.addChild(this.rightFeet2);
/* 130 */     this.rightFeet2.setTextureOffset(0, 72).addBox(12.5F, 0.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.01F, false);
/*     */     
/* 132 */     this.rightToe = new ModelRenderer((Model)this);
/* 133 */     this.rightToe.setRotationPoint(16.8372F, 1.3799F, -3.6384F);
/* 134 */     this.rightFeet2.addChild(this.rightToe);
/* 135 */     setRotationAngle(this.rightToe, 0.0873F, -0.1745F, -0.0036F);
/* 136 */     this.rightToe.setTextureOffset(0, 80).addBox(-0.1632F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 138 */     this.rightToe2 = new ModelRenderer((Model)this);
/* 139 */     this.rightToe2.setRotationPoint(15.0F, 1.5361F, -3.8781F);
/* 140 */     this.rightFeet2.addChild(this.rightToe2);
/* 141 */     setRotationAngle(this.rightToe2, 0.0873F, 0.0F, 0.0F);
/* 142 */     this.rightToe2.setTextureOffset(0, 80).addBox(-0.5F, -1.2064F, -1.5019F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 144 */     this.rightToe3 = new ModelRenderer((Model)this);
/* 145 */     this.rightToe3.setRotationPoint(12.3246F, 1.2317F, -3.7439F);
/* 146 */     this.rightFeet2.addChild(this.rightToe3);
/* 147 */     setRotationAngle(this.rightToe3, 0.0873F, 0.1745F, 0.0F);
/* 148 */     this.rightToe3.setTextureOffset(0, 80).addBox(-0.0868F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 150 */     this.rightToe4 = new ModelRenderer((Model)this);
/* 151 */     this.rightToe4.setRotationPoint(14.5F, -1.038F, 3.3855F);
/* 152 */     this.rightFeet2.addChild(this.rightToe4);
/* 153 */     setRotationAngle(this.rightToe4, -0.5672F, 0.0F, 0.0F);
/* 154 */     this.rightToe4.setTextureOffset(9, 81).addBox(0.0F, -0.2686F, -1.0783F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 156 */     this.bodyScales = new ModelRenderer((Model)this);
/* 157 */     this.bodyScales.setRotationPoint(0.0F, -3.0F, 0.0F);
/* 158 */     this.bodyScales.setTextureOffset(0, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.01F, false);
/*     */     
/* 160 */     this.headScales = new ModelRenderer((Model)this);
/* 161 */     this.headScales.setRotationPoint(0.0F, -3.0F, 0.0F);
/* 162 */     this.headScales.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.01F, false);
/*     */     
/* 164 */     this.leftArmScales = new ModelRenderer((Model)this);
/* 165 */     this.leftArmScales.setRotationPoint(5.0F, -1.0F, 0.0F);
/* 166 */     this.leftArmScales.setTextureOffset(34, 4).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.01F, false);
/*     */     
/* 168 */     this.rightArmScales = new ModelRenderer((Model)this);
/* 169 */     this.rightArmScales.setRotationPoint(-5.0F, -1.0F, 0.0F);
/* 170 */     this.rightArmScales.setTextureOffset(34, 4).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.01F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 176 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
/* 177 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 178 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 179 */     matrixStack.push();
/* 180 */     float scale = 1.1F;
/* 181 */     matrixStack.scale(scale, scale, scale);
/* 182 */     matrixStack.translate(0.0D, -0.18D, 0.0D);
/* 183 */     this.bodyScales.render(matrixStack, buffer, packedLight, packedOverlay);
/* 184 */     this.headScales.render(matrixStack, buffer, packedLight, packedOverlay);
/* 185 */     this.leftArmScales.render(matrixStack, buffer, packedLight, packedOverlay);
/* 186 */     this.rightArmScales.render(matrixStack, buffer, packedLight, packedOverlay);
/* 187 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 194 */     float limbSpeed = 0.5F;
/* 195 */     if (entity.isSprinting())
/* 196 */       limbSpeed = 0.7F; 
/* 197 */     this.rightLeg.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
/* 198 */     this.rightLeg2.rotateAngleX = -0.7F - MathHelper.sin(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
/* 199 */     this.leftLeg.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;
/* 200 */     this.leftLeg2.rotateAngleX = -0.7F - MathHelper.sin(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;
/*     */     
/* 202 */     this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 20.0D);
/* 203 */     this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 10.0D);
/* 204 */     this.tail2.rotateAngleY = (float)(this.tail2.rotateAngleY + Math.sin(ageInTicks * 0.09D) / 20.0D);
/* 205 */     this.tail2.rotateAngleX = (float)(this.tail2.rotateAngleX + Math.sin(ageInTicks * 0.01D) / 10.0D);
/* 206 */     this.tail3.rotateAngleY = (float)(this.tail3.rotateAngleY + Math.sin(ageInTicks * 0.1D) / 20.0D);
/* 207 */     this.tail3.rotateAngleX = (float)(this.tail3.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 10.0D);
/*     */ 
/*     */     
/* 210 */     if (entity.isSneaking()) {
/*     */       
/* 212 */       this.rightLeg.rotationPointZ = 2.0F;
/* 213 */       this.rightLeg.rotationPointY = 9.5F;
/* 214 */       this.leftLeg.rotationPointZ = 2.0F;
/* 215 */       this.leftLeg.rotationPointY = 9.5F;
/* 216 */       this.tail.rotationPointZ = 3.9F;
/* 217 */       this.tail.rotationPointY = 11.0F;
/*     */     } 
/*     */ 
/*     */     
/* 221 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 222 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 223 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 225 */       if (isBlackLeg) {
/*     */         
/* 227 */         this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 228 */         this.rightLeg.rotateAngleY += this.bipedBody.rotateAngleY;
/* 229 */         this.leftLeg.rotateAngleY += this.bipedBody.rotateAngleY;
/* 230 */         float f1 = 1.0F - this.swingProgress;
/* 231 */         f1 *= f1;
/* 232 */         f1 *= f1;
/* 233 */         f1 = 1.0F - f1;
/* 234 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 235 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 236 */         this.rightLeg.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX - f2 * 1.5D + f3);
/* 237 */         this.rightLeg.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 244 */     modelRenderer.rotateAngleX = x;
/* 245 */     modelRenderer.rotateAngleY = y;
/* 246 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 252 */     super.translateHand(side, matrixStack);
/* 253 */     matrixStack.translate(0.0D, 0.4D, 0.0D);
/* 254 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 263 */     if (side == HandSide.RIGHT) {
/*     */       
/* 265 */       matrixStack.translate(0.0D, -0.8D, 0.3D);
/* 266 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 267 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 268 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 272 */       matrixStack.translate(0.0D, -0.8D, 0.3D);
/* 273 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 274 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 275 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\AllosaurusHeavyPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */