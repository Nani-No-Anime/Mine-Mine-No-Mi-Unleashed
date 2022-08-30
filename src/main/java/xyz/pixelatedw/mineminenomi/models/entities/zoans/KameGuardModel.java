/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ 
/*     */ public class KameGuardModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer frontLeftFin;
/*     */   private final ModelRenderer frontRightFin;
/*     */   private final ModelRenderer backRightFin;
/*     */   private final ModelRenderer backLeftFin;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer shell;
/*     */   
/*     */   public KameGuardModel() {
/*  25 */     super(1.0F);
/*  26 */     this.textureWidth = 64;
/*  27 */     this.textureHeight = 64;
/*     */     
/*  29 */     this.body = new ModelRenderer((Model)this);
/*  30 */     this.body.setRotationPoint(0.0F, 22.0F, -1.0F);
/*     */     
/*  32 */     this.frontLeftFin = new ModelRenderer((Model)this);
/*  33 */     this.frontLeftFin.setRotationPoint(-1.0F, 0.0F, 0.0F);
/*  34 */     this.body.addChild(this.frontLeftFin);
/*  35 */     this.frontLeftFin.setTextureOffset(33, 8).addBox(5.0F, 0.85F, -4.0F, 6.0F, 0.0F, 3.0F, 0.0F, false);
/*     */     
/*  37 */     this.frontRightFin = new ModelRenderer((Model)this);
/*  38 */     this.frontRightFin.setRotationPoint(-1.0F, 0.0F, 0.0F);
/*  39 */     this.body.addChild(this.frontRightFin);
/*  40 */     this.frontRightFin.setTextureOffset(28, 23).addBox(-9.0F, 1.0F, -4.0F, 6.0F, 0.0F, 3.0F, 0.0F, false);
/*     */     
/*  42 */     this.backRightFin = new ModelRenderer((Model)this);
/*  43 */     this.backRightFin.setRotationPoint(-1.0F, 0.0F, 0.0F);
/*  44 */     this.body.addChild(this.backRightFin);
/*  45 */     this.backRightFin.setTextureOffset(27, 29).addBox(-3.0F, 1.0F, 6.0F, 3.0F, 0.0F, 6.0F, 0.0F, false);
/*     */     
/*  47 */     this.backLeftFin = new ModelRenderer((Model)this);
/*  48 */     this.backLeftFin.setRotationPoint(3.0F, 1.0F, 6.0F);
/*  49 */     this.body.addChild(this.backLeftFin);
/*  50 */     this.backLeftFin.setTextureOffset(28, 16).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 0.0F, 6.0F, 0.0F, false);
/*     */     
/*  52 */     this.head = new ModelRenderer((Model)this);
/*  53 */     this.head.setRotationPoint(0.0F, 0.0F, -4.0F);
/*  54 */     this.body.addChild(this.head);
/*  55 */     this.head.setTextureOffset(33, 0).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
/*     */     
/*  57 */     this.shell = new ModelRenderer((Model)this);
/*  58 */     this.shell.setRotationPoint(-1.0F, 0.0F, 0.0F);
/*  59 */     this.body.addChild(this.shell);
/*  60 */     this.shell.setTextureOffset(0, 0).addBox(-4.0F, -2.0F, -5.0F, 10.0F, 3.0F, 12.0F, 0.0F, false);
/*  61 */     this.shell.setTextureOffset(0, 29).addBox(-3.0F, -3.0F, -4.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);
/*  62 */     this.shell.setTextureOffset(0, 16).addBox(-3.0F, 1.0F, -4.0F, 8.0F, 1.0F, 11.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  68 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  74 */     if (entity.isCrouching()) {
/*     */       
/*  76 */       this.head.showModel = false;
/*  77 */       this.frontRightFin.showModel = false;
/*  78 */       this.frontLeftFin.showModel = false;
/*  79 */       this.backRightFin.showModel = false;
/*  80 */       this.backLeftFin.showModel = false;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     this.frontRightFin.rotateAngleX = MathHelper.cos(limbSwing * 0.4F) * 0.1F;
/*  85 */     this.frontRightFin.rotateAngleZ = MathHelper.sin(limbSwing * 0.4F) * 0.1F;
/*     */     
/*  87 */     this.frontLeftFin.rotateAngleX = -MathHelper.cos(limbSwing * 0.4F) * 0.1F;
/*  88 */     this.frontLeftFin.rotateAngleZ = MathHelper.sin(limbSwing * 0.4F) * 0.1F;
/*     */     
/*  90 */     this.backRightFin.rotateAngleY = MathHelper.cos(limbSwing * 0.4F) * 0.1F;
/*  91 */     this.backRightFin.rotateAngleZ = MathHelper.sin(limbSwing * 0.4F) * 0.1F;
/*     */     
/*  93 */     this.backLeftFin.rotateAngleY = MathHelper.cos(limbSwing * 0.4F) * 0.2F;
/*  94 */     this.backLeftFin.rotateAngleZ = -MathHelper.sin(limbSwing * 0.4F) * 0.1F;
/*     */ 
/*     */     
/*  97 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/*  98 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 100 */       this.head.rotateAngleY += this.body.rotateAngleY;
/* 101 */       float f1 = 1.0F - this.swingProgress;
/* 102 */       f1 *= f1;
/* 103 */       f1 *= f1;
/* 104 */       f1 = 1.0F - f1;
/* 105 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 106 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
/* 107 */       this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 1.5D + f3);
/* 108 */       this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 114 */     modelRenderer.rotateAngleX = x;
/* 115 */     modelRenderer.rotateAngleY = y;
/* 116 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 122 */     if (!this.head.showModel)
/* 123 */       matrixStack.scale(-10.0F, -10.0F, -10.0F); 
/* 124 */     matrixStack.translate(-0.2D, 1.2D, -0.01D);
/* 125 */     this.head.translateRotate(matrixStack);
/* 126 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 127 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(280.0F));
/* 128 */     matrixStack.translate(0.3D, -0.3D, -0.2D);
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\KameGuardModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */