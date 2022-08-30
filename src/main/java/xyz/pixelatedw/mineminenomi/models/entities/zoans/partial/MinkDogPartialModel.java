/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ 
/*     */ public class MinkDogPartialModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> {
/*     */   private final ModelRenderer ears;
/*     */   private final ModelRenderer rightEar1;
/*     */   private final ModelRenderer rightEar2;
/*     */   private final ModelRenderer leftEar1;
/*     */   private final ModelRenderer leftEar2;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer mouth;
/*     */   
/*     */   public MinkDogPartialModel() {
/*  24 */     super(1.0F);
/*  25 */     this.textureWidth = 32;
/*  26 */     this.textureHeight = 32;
/*     */     
/*  28 */     this.ears = new ModelRenderer((Model)this);
/*  29 */     this.ears.setRotationPoint(0.0F, 8.0F, 0.0F);
/*     */ 
/*     */     
/*  32 */     this.rightEar1 = new ModelRenderer((Model)this);
/*  33 */     this.rightEar1.setRotationPoint(-2.5F, -7.5F, 0.0F);
/*  34 */     this.ears.addChild(this.rightEar1);
/*  35 */     setRotationAngle(this.rightEar1, 0.0F, -0.1745F, -0.2618F);
/*  36 */     this.rightEar1.setTextureOffset(0, 25).addBox(-1.1471F, -2.5F, -0.1504F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  38 */     this.rightEar2 = new ModelRenderer((Model)this);
/*  39 */     this.rightEar2.setRotationPoint(0.3214F, -2.2239F, -0.0325F);
/*  40 */     this.rightEar1.addChild(this.rightEar2);
/*  41 */     setRotationAngle(this.rightEar2, 0.0F, 0.0F, 0.7854F);
/*  42 */     this.rightEar2.setTextureOffset(0, 16).addBox(-1.1814F, -1.189F, -0.1179F, 2.0F, 2.0F, 1.0F, -0.001F, false);
/*     */     
/*  44 */     this.leftEar1 = new ModelRenderer((Model)this);
/*  45 */     this.leftEar1.setRotationPoint(2.5F, -7.0F, 0.0F);
/*  46 */     this.ears.addChild(this.leftEar1);
/*  47 */     setRotationAngle(this.leftEar1, 0.0F, 0.1745F, 0.2618F);
/*  48 */     this.leftEar1.setTextureOffset(0, 25).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  50 */     this.leftEar2 = new ModelRenderer((Model)this);
/*  51 */     this.leftEar2.setRotationPoint(-0.5315F, -2.7239F, 0.1179F);
/*  52 */     this.leftEar1.addChild(this.leftEar2);
/*  53 */     setRotationAngle(this.leftEar2, 0.0F, 0.0F, 0.7854F);
/*  54 */     this.leftEar2.setTextureOffset(0, 16).addBox(-1.1814F, -1.189F, -0.1179F, 2.0F, 2.0F, 1.0F, -0.001F, false);
/*     */     
/*  56 */     this.tail = new ModelRenderer((Model)this);
/*  57 */     this.tail.setRotationPoint(0.0F, 11.0F, 1.0F);
/*  58 */     setRotationAngle(this.tail, 0.3054F, 0.0F, 0.0F);
/*  59 */     this.tail.setTextureOffset(0, 0).addBox(-1.0F, -0.2456F, 0.653F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/*  61 */     this.tail2 = new ModelRenderer((Model)this);
/*  62 */     this.tail2.setRotationPoint(0.5F, 1.3463F, 3.8713F);
/*  63 */     this.tail.addChild(this.tail2);
/*  64 */     setRotationAngle(this.tail2, -0.3054F, 0.0F, 0.0F);
/*  65 */     this.tail2.setTextureOffset(0, 6).addBox(-1.5F, -1.4919F, -0.6388F, 2.0F, 2.0F, 4.0F, -0.01F, false);
/*     */     
/*  67 */     this.mouth = new ModelRenderer((Model)this);
/*  68 */     this.mouth.setRotationPoint(0.0F, 6.0F, 1.0F);
/*  69 */     this.mouth.setTextureOffset(16, 13).addBox(-1.5F, -3.0F, -8.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  75 */     this.ears.copyModelAngles(this.bipedHead);
/*  76 */     this.mouth.copyModelAngles(this.bipedHead);
/*  77 */     this.ears.render(matrixStack, buffer, packedLight, packedOverlay);
/*  78 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
/*  79 */     this.mouth.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  85 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/*  87 */     this.tail.rotationPointY--;
/*     */     
/*  89 */     if (entity.isCrouching()) {
/*     */       
/*  91 */       this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + 0.4D);
/*  92 */       this.tail.rotationPointY++;
/*  93 */       this.tail.rotationPointZ += 4.0F;
/*     */     } 
/*     */     
/*  96 */     this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 6.0D);
/*  97 */     this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 102 */     modelRenderer.rotateAngleX = x;
/* 103 */     modelRenderer.rotateAngleY = y;
/* 104 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\MinkDogPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */