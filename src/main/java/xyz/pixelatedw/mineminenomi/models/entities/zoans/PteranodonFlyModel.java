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
/*     */ public class PteranodonFlyModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer head7_r1;
/*     */   private final ModelRenderer head6_r1;
/*     */   private final ModelRenderer head5_r1;
/*     */   private final ModelRenderer head4_r1;
/*     */   private final ModelRenderer head3_r1;
/*     */   private final ModelRenderer head2_r1;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer neck_r1;
/*     */   private final ModelRenderer beck;
/*     */   private final ModelRenderer upperBeck;
/*     */   public final ModelRenderer lowerBeck;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body3_r1;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg5_r1;
/*     */   private final ModelRenderer rightLeg1_r1;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg5_r1;
/*     */   private final ModelRenderer leftLeg1_r1;
/*     */   
/*     */   public PteranodonFlyModel() {
/*  41 */     super(1.0F);
/*  42 */     this.textureWidth = 64;
/*  43 */     this.textureHeight = 64;
/*     */     
/*  45 */     this.head = new ModelRenderer((Model)this);
/*  46 */     this.head.setRotationPoint(-0.5F, 17.0F, -2.0F);
/*  47 */     this.head.setTextureOffset(17, 37).addBox(-1.5F, -4.0F, -17.0F, 3.0F, 4.0F, 7.0F, 0.0F, false);
/*     */     
/*  49 */     this.head7_r1 = new ModelRenderer((Model)this);
/*  50 */     this.head7_r1.setRotationPoint(0.0F, -3.6703F, -11.8864F);
/*  51 */     this.head.addChild(this.head7_r1);
/*  52 */     setRotationAngle(this.head7_r1, 1.0036F, 0.0F, 0.0F);
/*  53 */     this.head7_r1.setTextureOffset(8, 10).addBox(-0.5F, 1.1F, 7.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  55 */     this.head6_r1 = new ModelRenderer((Model)this);
/*  56 */     this.head6_r1.setRotationPoint(0.0F, -3.9663F, -13.3072F);
/*  57 */     this.head.addChild(this.head6_r1);
/*  58 */     setRotationAngle(this.head6_r1, 0.829F, 0.0F, 0.0F);
/*  59 */     this.head6_r1.setTextureOffset(8, 14).addBox(-0.5F, 1.1F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  61 */     this.head5_r1 = new ModelRenderer((Model)this);
/*  62 */     this.head5_r1.setRotationPoint(0.0F, -3.1147F, -15.0223F);
/*  63 */     this.head.addChild(this.head5_r1);
/*  64 */     setRotationAngle(this.head5_r1, 0.7418F, 0.0F, 0.0F);
/*  65 */     this.head5_r1.setTextureOffset(15, 10).addBox(-0.5F, 1.1F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  67 */     this.head4_r1 = new ModelRenderer((Model)this);
/*  68 */     this.head4_r1.setRotationPoint(0.0F, -3.372F, -15.126F);
/*  69 */     this.head.addChild(this.head4_r1);
/*  70 */     setRotationAngle(this.head4_r1, 0.6981F, 0.0F, 0.0F);
/*  71 */     this.head4_r1.setTextureOffset(15, 14).addBox(-0.5F, 1.1F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  73 */     this.head3_r1 = new ModelRenderer((Model)this);
/*  74 */     this.head3_r1.setRotationPoint(0.0F, -3.9118F, -11.6183F);
/*  75 */     this.head.addChild(this.head3_r1);
/*  76 */     setRotationAngle(this.head3_r1, 0.4363F, 0.0F, 0.0F);
/*  77 */     this.head3_r1.setTextureOffset(22, 10).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/*  79 */     this.head2_r1 = new ModelRenderer((Model)this);
/*  80 */     this.head2_r1.setRotationPoint(0.0F, -3.7133F, -13.62F);
/*  81 */     this.head.addChild(this.head2_r1);
/*  82 */     setRotationAngle(this.head2_r1, 0.2618F, 0.0F, 0.0F);
/*  83 */     this.head2_r1.setTextureOffset(22, 15).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/*  85 */     this.neck = new ModelRenderer((Model)this);
/*  86 */     this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  87 */     this.head.addChild(this.neck);
/*     */ 
/*     */     
/*  90 */     this.neck_r1 = new ModelRenderer((Model)this);
/*  91 */     this.neck_r1.setRotationPoint(0.0F, -0.9169F, -9.6038F);
/*  92 */     this.neck.addChild(this.neck_r1);
/*  93 */     setRotationAngle(this.neck_r1, -0.1309F, 0.0F, 0.0F);
/*  94 */     this.neck_r1.setTextureOffset(31, 30).addBox(-0.5F, -1.5F, -4.5F, 1.0F, 3.0F, 9.0F, 0.0F, false);
/*     */     
/*  96 */     this.beck = new ModelRenderer((Model)this);
/*  97 */     this.beck.setRotationPoint(0.0F, -1.0F, -10.0F);
/*  98 */     this.head.addChild(this.beck);
/*  99 */     this.beck.setTextureOffset(0, 0).addBox(-1.0F, -2.5F, -9.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/* 100 */     this.beck.setTextureOffset(35, 10).addBox(-1.0F, 0.0F, -9.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
/*     */     
/* 102 */     this.upperBeck = new ModelRenderer((Model)this);
/* 103 */     this.upperBeck.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 104 */     this.beck.addChild(this.upperBeck);
/* 105 */     this.upperBeck.setTextureOffset(0, 18).addBox(-0.5F, -2.0F, -18.0F, 1.0F, 2.0F, 12.0F, 0.01F, false);
/*     */     
/* 107 */     this.lowerBeck = new ModelRenderer((Model)this);
/* 108 */     this.lowerBeck.setRotationPoint(0.0F, 0.0F, -9.0F);
/* 109 */     this.beck.addChild(this.lowerBeck);
/* 110 */     this.lowerBeck.setTextureOffset(14, 20).addBox(-0.5F, 0.0F, -11.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
/*     */     
/* 112 */     this.body = new ModelRenderer((Model)this);
/* 113 */     this.body.setRotationPoint(-0.5F, 16.0F, 4.0F);
/* 114 */     this.body.setTextureOffset(28, 18).addBox(-3.0F, -1.5F, -12.0F, 6.0F, 5.0F, 7.0F, 0.0F, false);
/*     */     
/* 116 */     this.body3_r1 = new ModelRenderer((Model)this);
/* 117 */     this.body3_r1.setRotationPoint(0.5F, 40.0F, -20.0F);
/* 118 */     this.body.addChild(this.body3_r1);
/* 119 */     setRotationAngle(this.body3_r1, -0.0873F, 0.0F, 0.0F);
/* 120 */     this.body3_r1.setTextureOffset(46, 44).addBox(-2.0F, -42.0F, 18.1F, 3.0F, 2.0F, 1.0F, 0.0F, false);
/* 121 */     this.body3_r1.setTextureOffset(0, 33).addBox(-3.0F, -42.4F, 11.1F, 5.0F, 4.0F, 7.0F, 0.0F, false);
/*     */     
/* 123 */     this.rightWing = new ModelRenderer((Model)this);
/* 124 */     this.rightWing.setRotationPoint(-3.0F, 17.0F, 0.0F);
/* 125 */     this.rightWing.setTextureOffset(17, 33).addBox(-8.5F, -0.5F, -6.0F, 8.0F, 1.0F, 1.0F, 0.0F, true);
/* 126 */     this.rightWing.setTextureOffset(14, 24).addBox(-5.5F, -0.5F, -7.0F, 5.0F, 1.0F, 1.0F, 0.0F, true);
/* 127 */     this.rightWing.setTextureOffset(17, 33).addBox(-13.5F, -0.5F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, true);
/* 128 */     this.rightWing.setTextureOffset(0, 27).addBox(-18.5F, -0.5F, -3.0F, 4.0F, 1.0F, 1.0F, 0.0F, true);
/* 129 */     this.rightWing.setTextureOffset(0, 44).addBox(-17.5F, -0.5F, -4.0F, 7.0F, 1.0F, 1.0F, 0.0F, true);
/* 130 */     this.rightWing.setTextureOffset(7, 20).addBox(-18.5F, -0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
/* 131 */     this.rightWing.setTextureOffset(0, 0).addBox(-18.5F, 0.0F, -5.0F, 19.0F, 0.0F, 9.0F, 0.0F, true);
/*     */     
/* 133 */     this.leftWing = new ModelRenderer((Model)this);
/* 134 */     this.leftWing.setRotationPoint(2.0F, 17.0F, 0.0F);
/* 135 */     this.leftWing.setTextureOffset(17, 33).addBox(0.5F, -0.5F, -6.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
/* 136 */     this.leftWing.setTextureOffset(14, 24).addBox(0.5F, -0.5F, -7.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
/* 137 */     this.leftWing.setTextureOffset(17, 33).addBox(5.5F, -0.5F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
/* 138 */     this.leftWing.setTextureOffset(0, 27).addBox(14.5F, -0.5F, -3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/* 139 */     this.leftWing.setTextureOffset(0, 44).addBox(10.5F, -0.5F, -4.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
/* 140 */     this.leftWing.setTextureOffset(7, 20).addBox(17.5F, -0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/* 141 */     this.leftWing.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, -5.0F, 19.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 143 */     this.rightLeg = new ModelRenderer((Model)this);
/* 144 */     this.rightLeg.setRotationPoint(-3.0F, 18.0F, 11.0F);
/* 145 */     setRotationAngle(this.rightLeg, 1.3526F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 148 */     this.rightLeg5_r1 = new ModelRenderer((Model)this);
/* 149 */     this.rightLeg5_r1.setRotationPoint(5.0F, 12.1847F, 7.6754F);
/* 150 */     this.rightLeg.addChild(this.rightLeg5_r1);
/* 151 */     setRotationAngle(this.rightLeg5_r1, -1.0472F, 0.0F, 0.0F);
/* 152 */     this.rightLeg5_r1.setTextureOffset(14, 20).addBox(-7.5F, 4.6958F, -15.7354F, 5.0F, 4.0F, 0.0F, 0.0F, false);
/* 153 */     this.rightLeg5_r1.setTextureOffset(0, 5).addBox(-6.5F, 3.93F, -15.7223F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/* 154 */     this.rightLeg5_r1.setTextureOffset(0, 13).addBox(-5.5F, 0.6958F, -15.7223F, 1.0F, 4.0F, 1.0F, -0.01F, false);
/* 155 */     this.rightLeg5_r1.setTextureOffset(0, 13).addBox(-5.5F, 0.6958F, -20.7223F, 1.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 157 */     this.rightLeg1_r1 = new ModelRenderer((Model)this);
/* 158 */     this.rightLeg1_r1.setRotationPoint(5.0F, 13.8689F, 7.5424F);
/* 159 */     this.rightLeg.addChild(this.rightLeg1_r1);
/* 160 */     setRotationAngle(this.rightLeg1_r1, -2.2253F, 0.0F, 0.0F);
/* 161 */     this.rightLeg1_r1.setTextureOffset(42, 30).addBox(-6.0F, 18.0963F, -11.8567F, 2.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 163 */     this.leftLeg = new ModelRenderer((Model)this);
/* 164 */     this.leftLeg.setRotationPoint(2.0F, 18.0F, 11.0F);
/* 165 */     setRotationAngle(this.leftLeg, 1.3526F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 168 */     this.leftLeg5_r1 = new ModelRenderer((Model)this);
/* 169 */     this.leftLeg5_r1.setRotationPoint(5.0F, 12.1847F, 7.6754F);
/* 170 */     this.leftLeg.addChild(this.leftLeg5_r1);
/* 171 */     setRotationAngle(this.leftLeg5_r1, -1.0472F, 0.0F, 0.0F);
/* 172 */     this.leftLeg5_r1.setTextureOffset(14, 20).addBox(-7.5F, 4.6958F, -15.7354F, 5.0F, 4.0F, 0.0F, 0.0F, false);
/* 173 */     this.leftLeg5_r1.setTextureOffset(0, 5).addBox(-6.5F, 3.93F, -15.7223F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/* 174 */     this.leftLeg5_r1.setTextureOffset(0, 13).addBox(-5.5F, 0.6958F, -15.7223F, 1.0F, 4.0F, 1.0F, -0.01F, false);
/* 175 */     this.leftLeg5_r1.setTextureOffset(0, 13).addBox(-5.5F, 0.6958F, -20.7223F, 1.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 177 */     this.leftLeg1_r1 = new ModelRenderer((Model)this);
/* 178 */     this.leftLeg1_r1.setRotationPoint(5.0F, 13.8689F, 7.5424F);
/* 179 */     this.leftLeg.addChild(this.leftLeg1_r1);
/* 180 */     setRotationAngle(this.leftLeg1_r1, -2.2253F, 0.0F, 0.0F);
/* 181 */     this.leftLeg1_r1.setTextureOffset(42, 30).addBox(-6.0F, 18.0963F, -11.8567F, 2.0F, 3.0F, 5.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 187 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 188 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 189 */     matrixStack.push();
/* 190 */     matrixStack.scale(1.75F, 1.0F, 1.0F);
/* 191 */     matrixStack.translate(0.10000000149011612D, 0.0D, 0.0D);
/* 192 */     this.rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
/* 193 */     matrixStack.translate(-0.20000000298023224D, 0.0D, 0.0D);
/* 194 */     this.leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
/* 195 */     matrixStack.pop();
/* 196 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 197 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 203 */     if ((entity.getMotion()).y < -1.7D) {
/*     */       
/* 205 */       this.leftWing.rotateAngleX = (float)(this.leftWing.rotateAngleX + Math.toRadians(-90.0D));
/* 206 */       this.leftWing.rotateAngleY = (float)(this.leftWing.rotateAngleY + Math.toRadians(-85.0D));
/* 207 */       this.leftWing.rotateAngleY += MathHelper.cos(((LivingEntity)entity).ticksExisted * 0.9F) / 50.0F;
/* 208 */       this.leftWing.rotateAngleZ += MathHelper.cos(((LivingEntity)entity).ticksExisted * 2.9F) / 50.0F;
/*     */       
/* 210 */       this.rightWing.rotateAngleX = (float)(this.rightWing.rotateAngleX + Math.toRadians(-90.0D));
/* 211 */       this.rightWing.rotateAngleY = (float)(this.rightWing.rotateAngleY + Math.toRadians(85.0D));
/* 212 */       this.rightWing.rotateAngleY += MathHelper.cos(((LivingEntity)entity).ticksExisted * 0.9F) / 50.0F;
/* 213 */       this.rightWing.rotateAngleZ += MathHelper.cos(((LivingEntity)entity).ticksExisted * 2.9F) / 50.0F;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 218 */       this.leftWing.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F) * 0.7F;
/* 219 */       this.rightWing.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.7F;
/*     */     } 
/*     */ 
/*     */     
/* 223 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 224 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 226 */       this.head.rotateAngleY += this.body.rotateAngleY;
/* 227 */       float f1 = 1.0F - this.swingProgress;
/* 228 */       f1 *= f1;
/* 229 */       f1 *= f1;
/* 230 */       f1 = 1.0F - f1;
/* 231 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 232 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
/* 233 */       this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 0.5D + f3);
/* 234 */       this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 240 */     modelRenderer.rotateAngleX = x;
/* 241 */     modelRenderer.rotateAngleY = y;
/* 242 */     modelRenderer.rotateAngleZ = z;
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
/* 258 */     this.head.translateRotate(matrixStack);
/* 259 */     matrixStack.scale(0.5F, 0.5F, 0.5F);
/* 260 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 261 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
/* 262 */     matrixStack.translate(0.35D, -0.1D, -0.1D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\PteranodonFlyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */