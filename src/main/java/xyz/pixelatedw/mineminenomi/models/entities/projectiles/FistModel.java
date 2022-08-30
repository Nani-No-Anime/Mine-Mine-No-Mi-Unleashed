/*     */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class FistModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer arm;
/*     */   public ModelRenderer righthand1;
/*     */   public ModelRenderer righthand2;
/*     */   public ModelRenderer rightfinger10;
/*     */   public ModelRenderer rightfinger20;
/*     */   public ModelRenderer rightfinger30;
/*     */   public ModelRenderer rightfinger50;
/*     */   public ModelRenderer rightfinger40;
/*     */   public ModelRenderer elbow;
/*     */   public ModelRenderer rightfinger11;
/*     */   public ModelRenderer rightfinger21;
/*     */   public ModelRenderer rightfinger31;
/*     */   public ModelRenderer rightfinger41;
/*     */   
/*     */   public FistModel() {
/*  31 */     this.textureWidth = 64;
/*  32 */     this.textureHeight = 32;
/*  33 */     this.arm = new ModelRenderer((Model)this, 0, 0);
/*  34 */     this.arm.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  35 */     this.arm.addBox(-2.0F, -2.0F, -5.0F, 0.0F, 0.0F, 1.0F, 0.0F);
/*  36 */     this.rightfinger50 = new ModelRenderer((Model)this, 17, 14);
/*  37 */     this.rightfinger50.setRotationPoint(2.3F, 0.5F, -8.1F);
/*  38 */     this.rightfinger50.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
/*  39 */     setRotateAngle(this.rightfinger50, 0.06405358F, 0.9545206F, 1.6947147F);
/*  40 */     this.rightfinger11 = new ModelRenderer((Model)this, 17, 10);
/*  41 */     this.rightfinger11.setRotationPoint(0.0F, 1.8F, -0.3F);
/*  42 */     this.rightfinger11.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  43 */     setRotateAngle(this.rightfinger11, 1.8675023F, 0.0F, 0.0F);
/*  44 */     this.rightfinger30 = new ModelRenderer((Model)this, 17, 6);
/*  45 */     this.rightfinger30.setRotationPoint(0.0F, -0.5F, -9.5F);
/*  46 */     this.rightfinger30.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  47 */     setRotateAngle(this.rightfinger30, 0.0F, -0.05235988F, 0.0F);
/*  48 */     this.rightfinger21 = new ModelRenderer((Model)this, 17, 10);
/*  49 */     this.rightfinger21.setRotationPoint(0.0F, 1.8F, -0.3F);
/*  50 */     this.rightfinger21.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  51 */     setRotateAngle(this.rightfinger21, 1.8675023F, 0.0F, 0.0F);
/*  52 */     this.rightfinger41 = new ModelRenderer((Model)this, 17, 10);
/*  53 */     this.rightfinger41.setRotationPoint(0.0F, 1.8F, -0.3F);
/*  54 */     this.rightfinger41.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  55 */     setRotateAngle(this.rightfinger41, 1.8849556F, 0.0F, 0.0F);
/*  56 */     this.righthand2 = new ModelRenderer((Model)this, 28, 0);
/*  57 */     this.righthand2.setRotationPoint(0.9F, -0.5F, -5.9F);
/*  58 */     this.righthand2.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 2.0F, 0.0F);
/*  59 */     setRotateAngle(this.righthand2, -1.5707964F, -0.43633232F, 0.0F);
/*  60 */     this.rightfinger40 = new ModelRenderer((Model)this, 17, 6);
/*  61 */     this.rightfinger40.setRotationPoint(1.0F, -0.5F, -9.5F);
/*  62 */     this.rightfinger40.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  63 */     setRotateAngle(this.rightfinger40, 0.0F, -0.08726646F, 0.0F);
/*  64 */     this.rightfinger10 = new ModelRenderer((Model)this, 17, 6);
/*  65 */     this.rightfinger10.setRotationPoint(-1.0F, -0.5F, -9.5F);
/*  66 */     this.rightfinger10.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  67 */     setRotateAngle(this.rightfinger10, 0.0F, 0.08726646F, 0.0F);
/*  68 */     this.elbow = new ModelRenderer((Model)this, 0, 0);
/*  69 */     this.elbow.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  70 */     this.elbow.addBox(-2.0F, -4.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F);
/*  71 */     setRotateAngle(this.elbow, -1.5707964F, 0.0F, 0.0F);
/*  72 */     this.rightfinger20 = new ModelRenderer((Model)this, 17, 6);
/*  73 */     this.rightfinger20.setRotationPoint(0.0F, -0.5F, -9.5F);
/*  74 */     this.rightfinger20.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  75 */     setRotateAngle(this.rightfinger20, 0.0F, 0.05235988F, 0.0F);
/*  76 */     this.rightfinger31 = new ModelRenderer((Model)this, 17, 10);
/*  77 */     this.rightfinger31.setRotationPoint(0.0F, 1.8F, -0.3F);
/*  78 */     this.rightfinger31.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  79 */     setRotateAngle(this.rightfinger31, 1.8849556F, 0.0F, 0.0F);
/*  80 */     this.righthand1 = new ModelRenderer((Model)this, 17, 0);
/*  81 */     this.righthand1.setRotationPoint(0.0F, -1.0F, -5.0F);
/*  82 */     this.righthand1.addBox(-2.0F, 0.0F, -0.5F, 4.0F, 5.0F, 1.0F, 0.0F);
/*  83 */     setRotateAngle(this.righthand1, -1.5707964F, -0.0F, 0.0F);
/*  84 */     this.arm.addChild(this.rightfinger50);
/*  85 */     this.rightfinger10.addChild(this.rightfinger11);
/*  86 */     this.arm.addChild(this.rightfinger30);
/*  87 */     this.rightfinger20.addChild(this.rightfinger21);
/*  88 */     this.rightfinger40.addChild(this.rightfinger41);
/*  89 */     this.arm.addChild(this.righthand2);
/*  90 */     this.arm.addChild(this.rightfinger40);
/*  91 */     this.arm.addChild(this.rightfinger10);
/*  92 */     this.arm.addChild(this.elbow);
/*  93 */     this.arm.addChild(this.rightfinger20);
/*  94 */     this.rightfinger30.addChild(this.rightfinger31);
/*  95 */     this.arm.addChild(this.righthand1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 101 */     this.arm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 112 */     model.rotateAngleX = x;
/* 113 */     model.rotateAngleY = y;
/* 114 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\FistModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */