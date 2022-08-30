/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ 
/*     */ public class ShinokuniModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   
/*     */   public ShinokuniModel() {
/*  25 */     super(1.0F);
/*  26 */     this.textureWidth = 128;
/*  27 */     this.textureHeight = 128;
/*     */     
/*  29 */     this.body = new ModelRenderer((Model)this);
/*  30 */     this.body.setRotationPoint(1.5F, 17.0F, -2.0F);
/*  31 */     this.body.setTextureOffset(46, 0).addBox(-7.0F, -6.0F, -1.0F, 11.0F, 3.0F, 6.0F, 0.0F, false);
/*  32 */     this.body.setTextureOffset(42, 27).addBox(-8.0F, -11.0F, -2.0F, 13.0F, 5.0F, 8.0F, 0.0F, false);
/*  33 */     this.body.setTextureOffset(0, 16).addBox(-9.0F, -19.0F, -3.0F, 15.0F, 9.0F, 10.0F, 0.0F, false);
/*  34 */     this.body.setTextureOffset(40, 16).addBox(-8.0F, -22.0F, -0.5F, 13.0F, 3.0F, 7.0F, 0.0F, false);
/*  35 */     this.body.setTextureOffset(52, 51).addBox(-7.5F, -21.0F, -2.5F, 12.0F, 2.0F, 2.0F, 0.0F, false);
/*  36 */     this.body.setTextureOffset(42, 40).addBox(-8.0F, -3.0F, -2.0F, 13.0F, 3.0F, 8.0F, 0.0F, false);
/*  37 */     this.body.setTextureOffset(0, 35).addBox(-9.0F, 0.0F, -3.0F, 15.0F, 3.0F, 10.0F, 0.0F, false);
/*  38 */     this.body.setTextureOffset(0, 0).addBox(-10.0F, 3.0F, -4.0F, 17.0F, 4.0F, 12.0F, 0.0F, false);
/*     */     
/*  40 */     this.neck = new ModelRenderer((Model)this);
/*  41 */     this.neck.setRotationPoint(-1.0F, -19.0F, 3.0F);
/*  42 */     this.body.addChild(this.neck);
/*  43 */     setRotationAngle(this.neck, 0.48F, 0.0F, 0.0F);
/*  44 */     this.neck.setTextureOffset(36, 74).addBox(-2.5F, -5.0F, -5.5F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/*  46 */     this.leftArm = new ModelRenderer((Model)this);
/*  47 */     this.leftArm.setRotationPoint(7.0F, -1.0F, 1.0F);
/*  48 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, 1.2217F);
/*  49 */     this.leftArm.setTextureOffset(0, 48).addBox(-0.4739F, -4.8191F, -5.0F, 7.0F, 7.0F, 8.0F, 0.0F, true);
/*     */     
/*  51 */     this.leftArm2 = new ModelRenderer((Model)this);
/*  52 */     this.leftArm2.setRotationPoint(12.3118F, -1.3905F, 0.0F);
/*  53 */     this.leftArm.addChild(this.leftArm2);
/*  54 */     this.leftArm2.setTextureOffset(0, 69).addBox(-5.7857F, -2.4286F, -4.0F, 9.0F, 5.0F, 1.0F, 0.0F, true);
/*  55 */     this.leftArm2.setTextureOffset(0, 80).addBox(-5.7857F, -2.4286F, 1.0F, 11.0F, 4.0F, 1.0F, 0.0F, true);
/*  56 */     this.leftArm2.setTextureOffset(0, 86).addBox(-5.7857F, 1.5714F, -3.0F, 9.0F, 1.0F, 2.0F, 0.0F, true);
/*  57 */     this.leftArm2.setTextureOffset(0, 66).addBox(-5.7857F, 1.5714F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, true);
/*  58 */     this.leftArm2.setTextureOffset(0, 63).addBox(-5.7857F, -2.4286F, -3.0F, 10.0F, 1.0F, 2.0F, 0.0F, true);
/*  59 */     this.leftArm2.setTextureOffset(0, 76).addBox(-5.7857F, -2.4286F, -1.0F, 11.0F, 1.0F, 2.0F, 0.0F, true);
/*  60 */     this.leftArm2.setTextureOffset(0, 90).addBox(0.2143F, -1.4286F, -2.5F, 7.0F, 3.0F, 3.0F, 0.0F, true);
/*     */     
/*  62 */     this.rightArm = new ModelRenderer((Model)this);
/*  63 */     this.rightArm.setRotationPoint(-7.0F, -1.0F, 1.0F);
/*  64 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, -1.2217F);
/*  65 */     this.rightArm.setTextureOffset(0, 48).addBox(-6.5261F, -4.8191F, -5.0F, 7.0F, 7.0F, 8.0F, 0.0F, false);
/*     */     
/*  67 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  68 */     this.rightArm2.setRotationPoint(-12.3118F, -1.3905F, 0.0F);
/*  69 */     this.rightArm.addChild(this.rightArm2);
/*  70 */     this.rightArm2.setTextureOffset(0, 69).addBox(-3.2143F, -2.4286F, -4.0F, 9.0F, 5.0F, 1.0F, 0.0F, false);
/*  71 */     this.rightArm2.setTextureOffset(0, 80).addBox(-5.2143F, -2.4286F, 1.0F, 11.0F, 4.0F, 1.0F, 0.0F, false);
/*  72 */     this.rightArm2.setTextureOffset(0, 86).addBox(-3.2143F, 1.5714F, -3.0F, 9.0F, 1.0F, 2.0F, 0.0F, false);
/*  73 */     this.rightArm2.setTextureOffset(0, 66).addBox(-4.2143F, 1.5714F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
/*  74 */     this.rightArm2.setTextureOffset(0, 63).addBox(-4.2143F, -2.4286F, -3.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
/*  75 */     this.rightArm2.setTextureOffset(0, 76).addBox(-5.2143F, -2.4286F, -1.0F, 11.0F, 1.0F, 2.0F, 0.0F, false);
/*  76 */     this.rightArm2.setTextureOffset(0, 90).addBox(-7.2143F, -1.4286F, -2.5F, 7.0F, 3.0F, 3.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  82 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*  83 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*  84 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  91 */     float f = 1.0F;
/*  92 */     this.rightArm.rotateAngleY = MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 0.5F * limbSwingAmount * 0.5F / f;
/*  93 */     this.leftArm.rotateAngleY = -MathHelper.cos(limbSwing * 0.6F) * 0.5F * limbSwingAmount * 0.5F / f;
/*  94 */     if (!entity.getHeldItemMainhand().isEmpty()) {
/*  95 */       this.rightArm.rotateAngleX += -0.15F;
/*     */     }
/*     */     
/*  98 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/*  99 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 101 */       this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 102 */       this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
/* 103 */       this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 8.0F;
/* 104 */       this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
/* 105 */       this.leftArm.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
/* 106 */       this.rightArm.rotateAngleY += this.body.rotateAngleY;
/* 107 */       this.leftArm.rotateAngleY += this.body.rotateAngleY;
/* 108 */       this.leftArm.rotateAngleX += this.body.rotateAngleY;
/* 109 */       float f1 = 1.0F - this.swingProgress;
/* 110 */       f1 *= f1;
/* 111 */       f1 *= f1;
/* 112 */       f1 = 1.0F - f1;
/* 113 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 114 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * 0.75F;
/* 115 */       this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 116 */       this.rightArm.rotateAngleY += this.body.rotateAngleY * 5.0F;
/* 117 */       this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 123 */     modelRenderer.rotateAngleX = x;
/* 124 */     modelRenderer.rotateAngleY = y;
/* 125 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 131 */     if (side == HandSide.RIGHT) {
/*     */       
/* 133 */       matrixStack.translate(0.2D, 0.3D, 0.0D);
/* 134 */       this.rightArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 138 */       matrixStack.translate(-0.2D, 0.3D, 0.0D);
/* 139 */       this.leftArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 151 */     super.translateHand(side, matrixStack);
/* 152 */     matrixStack.translate((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\ShinokuniModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */