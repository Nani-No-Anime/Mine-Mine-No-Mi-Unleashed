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
/*     */ public class BisonWalkModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer rightHorn;
/*     */   private final ModelRenderer rightHorn2;
/*     */   private final ModelRenderer leftHorn;
/*     */   private final ModelRenderer leftHorn2;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer hunch;
/*     */   private final ModelRenderer lowerBack;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   private final ModelRenderer leftFrontLeg2;
/*     */   private final ModelRenderer leftFrontNail1;
/*     */   private final ModelRenderer leftFrontNail2;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer rightFrontLeg2;
/*     */   private final ModelRenderer rightFrontNail1;
/*     */   private final ModelRenderer rightFrontNail2;
/*     */   private final ModelRenderer leftRearLeg;
/*     */   private final ModelRenderer leftRearNail1;
/*     */   private final ModelRenderer leftRearNail2;
/*     */   private final ModelRenderer rightRearLeg;
/*     */   private final ModelRenderer rightRearNail1;
/*     */   private final ModelRenderer rightRearNail2;
/*     */   
/*     */   public BisonWalkModel() {
/*  43 */     super(1.0F);
/*  44 */     this.textureWidth = 128;
/*  45 */     this.textureHeight = 64;
/*     */     
/*  47 */     this.head = new ModelRenderer((Model)this);
/*  48 */     this.head.setRotationPoint(-2.5F, 8.0F, -8.5F);
/*  49 */     setRotationAngle(this.head, 0.0873F, 0.0F, 0.0F);
/*  50 */     this.head.setTextureOffset(13, 29).addBox(0.0F, 0.3861F, -7.0606F, 5.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/*  52 */     this.snout = new ModelRenderer((Model)this);
/*  53 */     this.snout.setRotationPoint(0.5F, 2.0F, -2.5F);
/*  54 */     this.head.addChild(this.snout);
/*  55 */     setRotationAngle(this.snout, 0.0873F, 0.0F, 0.0F);
/*  56 */     this.snout.setTextureOffset(13, 40).addBox(0.0F, -0.2307F, -7.0674F, 4.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/*  58 */     this.rightHorn = new ModelRenderer((Model)this);
/*  59 */     this.rightHorn.setRotationPoint(-0.5F, -1.0F, 3.5F);
/*  60 */     this.head.addChild(this.rightHorn);
/*  61 */     setRotationAngle(this.rightHorn, 0.0F, 0.0F, 0.733F);
/*  62 */     this.rightHorn.setTextureOffset(115, 0).addBox(0.2584F, 0.2869F, -7.0606F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  64 */     this.rightHorn2 = new ModelRenderer((Model)this);
/*  65 */     this.rightHorn2.setRotationPoint(0.3809F, -1.1029F, 0.0436F);
/*  66 */     this.rightHorn.addChild(this.rightHorn2);
/*  67 */     setRotationAngle(this.rightHorn2, 0.0F, 0.0F, 1.3352F);
/*  68 */     this.rightHorn2.setTextureOffset(122, 0).addBox(0.2584F, -0.3313F, -7.1041F, 2.0F, 1.0F, 1.0F, 0.01F, false);
/*     */     
/*  70 */     this.leftHorn = new ModelRenderer((Model)this);
/*  71 */     this.leftHorn.setRotationPoint(5.5F, -0.751F, 3.4782F);
/*  72 */     this.head.addChild(this.leftHorn);
/*  73 */     setRotationAngle(this.leftHorn, 0.0F, 0.0F, -0.733F);
/*  74 */     this.leftHorn.setTextureOffset(115, 0).addBox(-2.0911F, 0.1011F, -7.0606F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  76 */     this.leftHorn2 = new ModelRenderer((Model)this);
/*  77 */     this.leftHorn2.setRotationPoint(0.3457F, -4.1516F, 0.0F);
/*  78 */     this.leftHorn.addChild(this.leftHorn2);
/*  79 */     setRotationAngle(this.leftHorn2, 0.0F, 0.0F, -1.3352F);
/*  80 */     this.leftHorn2.setTextureOffset(122, 0).addBox(-5.1393F, -0.1842F, -7.0606F, 2.0F, 1.0F, 1.0F, 0.01F, false);
/*     */     
/*  82 */     this.body = new ModelRenderer((Model)this);
/*  83 */     this.body.setRotationPoint(-4.5F, 7.0F, -3.5F);
/*  84 */     this.body.setTextureOffset(0, 0).addBox(0.0F, 1.0F, -7.0F, 9.0F, 7.0F, 11.0F, 0.0F, false);
/*     */     
/*  86 */     this.hunch = new ModelRenderer((Model)this);
/*  87 */     this.hunch.setRotationPoint(0.5F, -5.0F, 3.5F);
/*  88 */     this.body.addChild(this.hunch);
/*  89 */     setRotationAngle(this.hunch, -0.5934F, 0.0F, 0.0F);
/*  90 */     this.hunch.setTextureOffset(41, 0).addBox(0.0F, 4.7434F, -5.2441F, 8.0F, 6.0F, 9.0F, 0.0F, false);
/*     */     
/*  92 */     this.lowerBack = new ModelRenderer((Model)this);
/*  93 */     this.lowerBack.setRotationPoint(0.5F, 0.0F, 10.5F);
/*  94 */     this.body.addChild(this.lowerBack);
/*  95 */     this.lowerBack.setTextureOffset(76, 0).addBox(0.0F, 1.0F, -7.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
/*     */     
/*  97 */     this.tail = new ModelRenderer((Model)this);
/*  98 */     this.tail.setRotationPoint(4.0F, 4.0F, 0.5F);
/*  99 */     this.lowerBack.addChild(this.tail);
/* 100 */     setRotationAngle(this.tail, 0.3491F, 0.0F, 0.0F);
/* 101 */     this.tail.setTextureOffset(110, 0).addBox(-0.5F, 3.0E-4F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 103 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 104 */     this.leftFrontLeg.setRotationPoint(2.75F, 14.0F, -7.0F);
/* 105 */     this.leftFrontLeg.setTextureOffset(0, 29).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 107 */     this.leftFrontLeg2 = new ModelRenderer((Model)this);
/* 108 */     this.leftFrontLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 109 */     this.leftFrontLeg.addChild(this.leftFrontLeg2);
/* 110 */     this.leftFrontLeg2.setTextureOffset(0, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 112 */     this.leftFrontNail1 = new ModelRenderer((Model)this);
/* 113 */     this.leftFrontNail1.setRotationPoint(0.0F, 3.1F, 6.5F);
/* 114 */     this.leftFrontLeg2.addChild(this.leftFrontNail1);
/* 115 */     setRotationAngle(this.leftFrontNail1, -0.1211F, -0.4883F, -0.0394F);
/* 116 */     this.leftFrontNail1.setTextureOffset(0, 55).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 118 */     this.leftFrontNail2 = new ModelRenderer((Model)this);
/* 119 */     this.leftFrontNail2.setRotationPoint(0.0F, 3.1F, 6.5F);
/* 120 */     this.leftFrontLeg2.addChild(this.leftFrontNail2);
/* 121 */     setRotationAngle(this.leftFrontNail2, -0.1211F, 0.4883F, 0.0394F);
/* 122 */     this.leftFrontNail2.setTextureOffset(0, 55).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 124 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 125 */     this.rightFrontLeg.setRotationPoint(-2.75F, 14.0F, -7.0F);
/* 126 */     this.rightFrontLeg.setTextureOffset(0, 29).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 128 */     this.rightFrontLeg2 = new ModelRenderer((Model)this);
/* 129 */     this.rightFrontLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 130 */     this.rightFrontLeg.addChild(this.rightFrontLeg2);
/* 131 */     this.rightFrontLeg2.setTextureOffset(0, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 133 */     this.rightFrontNail1 = new ModelRenderer((Model)this);
/* 134 */     this.rightFrontNail1.setRotationPoint(0.0F, 3.1F, 6.5F);
/* 135 */     this.rightFrontLeg2.addChild(this.rightFrontNail1);
/* 136 */     setRotationAngle(this.rightFrontNail1, -0.1211F, -0.4883F, -0.0394F);
/* 137 */     this.rightFrontNail1.setTextureOffset(0, 55).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 139 */     this.rightFrontNail2 = new ModelRenderer((Model)this);
/* 140 */     this.rightFrontNail2.setRotationPoint(0.0F, 3.1F, 6.5F);
/* 141 */     this.rightFrontLeg2.addChild(this.rightFrontNail2);
/* 142 */     setRotationAngle(this.rightFrontNail2, -0.1211F, 0.4883F, 0.0394F);
/* 143 */     this.rightFrontNail2.setTextureOffset(0, 55).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 145 */     this.leftRearLeg = new ModelRenderer((Model)this);
/* 146 */     this.leftRearLeg.setRotationPoint(3.0F, 14.0F, 7.0F);
/* 147 */     this.leftRearLeg.setTextureOffset(0, 39).addBox(-1.25F, 0.0F, -1.25F, 2.0F, 10.0F, 2.0F, 0.0F, false);
/*     */     
/* 149 */     this.leftRearNail1 = new ModelRenderer((Model)this);
/* 150 */     this.leftRearNail1.setRotationPoint(0.0F, 9.1F, 6.5F);
/* 151 */     this.leftRearLeg.addChild(this.leftRearNail1);
/* 152 */     setRotationAngle(this.leftRearNail1, -0.1211F, 0.4883F, 0.0394F);
/* 153 */     this.leftRearNail1.setTextureOffset(0, 52).addBox(2.6808F, 0.7975F, -7.4708F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 155 */     this.leftRearNail2 = new ModelRenderer((Model)this);
/* 156 */     this.leftRearNail2.setRotationPoint(0.0F, 9.1F, 6.5F);
/* 157 */     this.leftRearLeg.addChild(this.leftRearNail2);
/* 158 */     setRotationAngle(this.leftRearNail2, -0.1211F, -0.4883F, -0.0394F);
/* 159 */     this.leftRearNail2.setTextureOffset(0, 52).addBox(-4.122F, 0.7496F, -7.2405F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 161 */     this.rightRearLeg = new ModelRenderer((Model)this);
/* 162 */     this.rightRearLeg.setRotationPoint(-2.75F, 14.0F, 6.75F);
/* 163 */     this.rightRearLeg.setTextureOffset(0, 39).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
/*     */     
/* 165 */     this.rightRearNail1 = new ModelRenderer((Model)this);
/* 166 */     this.rightRearNail1.setRotationPoint(0.0F, 9.1F, 6.5F);
/* 167 */     this.rightRearLeg.addChild(this.rightRearNail1);
/* 168 */     setRotationAngle(this.rightRearNail1, -0.1211F, 0.4883F, 0.0394F);
/* 169 */     this.rightRearNail1.setTextureOffset(0, 52).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 171 */     this.rightRearNail2 = new ModelRenderer((Model)this);
/* 172 */     this.rightRearNail2.setRotationPoint(0.0F, 9.1F, 6.5F);
/* 173 */     this.rightRearLeg.addChild(this.rightRearNail2);
/* 174 */     setRotationAngle(this.rightRearNail2, -0.1211F, -0.4883F, -0.0394F);
/* 175 */     this.rightRearNail2.setTextureOffset(0, 52).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 181 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 182 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 183 */     this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 184 */     this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 185 */     this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 186 */     this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 193 */     this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
/* 194 */     this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
/* 195 */     this.rightRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
/* 196 */     this.leftRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
/* 197 */     if (entity.isSprinting()) {
/* 198 */       this.tail.rotateAngleX = 1.2F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     }
/*     */     
/* 201 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 202 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 204 */       this.head.rotateAngleY += this.body.rotateAngleY;
/* 205 */       float f1 = 1.0F - this.swingProgress;
/* 206 */       f1 *= f1;
/* 207 */       f1 *= f1;
/* 208 */       f1 = 1.0F - f1;
/* 209 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 210 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
/* 211 */       this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 1.5D + f3);
/* 212 */       this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 219 */     this.head.translateRotate(matrixStack);
/* 220 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 221 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
/* 222 */     matrixStack.translate(0.3D, 0.0D, -0.03D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 233 */     modelRenderer.rotateAngleX = x;
/* 234 */     modelRenderer.rotateAngleY = y;
/* 235 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\BisonWalkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */