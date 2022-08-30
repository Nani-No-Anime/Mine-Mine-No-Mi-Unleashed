/*     */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
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
/*     */ public class NoroNoroBeamModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer circle1;
/*     */   public ModelRenderer circle2;
/*     */   public ModelRenderer circle3;
/*     */   public ModelRenderer circle4;
/*     */   public ModelRenderer circle5;
/*     */   public ModelRenderer circle6;
/*     */   public ModelRenderer circle7;
/*     */   public ModelRenderer circle8;
/*     */   public ModelRenderer circle9;
/*     */   public ModelRenderer circle10;
/*     */   public ModelRenderer circle11;
/*     */   public ModelRenderer circle12;
/*     */   public ModelRenderer pellicle;
/*     */   
/*     */   public NoroNoroBeamModel() {
/*  32 */     this.textureWidth = 64;
/*  33 */     this.textureHeight = 32;
/*  34 */     this.circle3 = new ModelRenderer((Model)this, 0, 0);
/*  35 */     this.circle3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  36 */     this.circle3.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  37 */     setRotateAngle(this.circle3, 0.0F, -0.0F, 0.7853982F);
/*  38 */     this.circle1 = new ModelRenderer((Model)this, 0, 0);
/*  39 */     this.circle1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  40 */     this.circle1.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  41 */     setRotateAngle(this.circle1, 0.0F, -0.0F, -0.2617994F);
/*  42 */     this.circle11 = new ModelRenderer((Model)this, 0, 0);
/*  43 */     this.circle11.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  44 */     this.circle11.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  45 */     setRotateAngle(this.circle11, 0.0F, -0.0F, -1.3089969F);
/*  46 */     this.circle12 = new ModelRenderer((Model)this, 0, 0);
/*  47 */     this.circle12.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  48 */     this.circle12.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  49 */     setRotateAngle(this.circle12, 0.0F, -0.0F, -0.7853982F);
/*  50 */     this.pellicle = new ModelRenderer((Model)this, 0, 3);
/*  51 */     this.pellicle.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  52 */     this.pellicle.addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 0.0F, 0.0F);
/*  53 */     this.circle9 = new ModelRenderer((Model)this, 0, 0);
/*  54 */     this.circle9.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  55 */     this.circle9.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  56 */     setRotateAngle(this.circle9, 0.0F, -0.0F, -2.3561945F);
/*  57 */     this.circle2 = new ModelRenderer((Model)this, 0, 0);
/*  58 */     this.circle2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  59 */     this.circle2.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  60 */     setRotateAngle(this.circle2, 0.0F, -0.0F, 0.2617994F);
/*  61 */     this.circle10 = new ModelRenderer((Model)this, 0, 0);
/*  62 */     this.circle10.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  63 */     this.circle10.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  64 */     setRotateAngle(this.circle10, 0.0F, -0.0F, -1.8325957F);
/*  65 */     this.circle7 = new ModelRenderer((Model)this, 0, 0);
/*  66 */     this.circle7.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  67 */     this.circle7.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  68 */     setRotateAngle(this.circle7, 0.0F, -0.0F, 2.8972466F);
/*  69 */     this.circle5 = new ModelRenderer((Model)this, 0, 0);
/*  70 */     this.circle5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  71 */     this.circle5.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  72 */     setRotateAngle(this.circle5, 0.0F, -0.0F, 1.8325957F);
/*  73 */     this.circle8 = new ModelRenderer((Model)this, 0, 0);
/*  74 */     this.circle8.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  75 */     this.circle8.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  76 */     setRotateAngle(this.circle8, 0.0F, -0.0F, -2.8972466F);
/*  77 */     this.circle4 = new ModelRenderer((Model)this, 0, 0);
/*  78 */     this.circle4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  79 */     this.circle4.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  80 */     setRotateAngle(this.circle4, 0.0F, -0.0F, 1.3089969F);
/*  81 */     this.circle6 = new ModelRenderer((Model)this, 0, 0);
/*  82 */     this.circle6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  83 */     this.circle6.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
/*  84 */     setRotateAngle(this.circle6, 0.0F, -0.0F, 2.3561945F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/*  90 */     matrixStack.push();
/*  91 */     matrixStack.translate(0.0D, -0.15D, 0.0D);
/*  92 */     ImmutableList.of(this.circle3, this.circle1, this.circle11, this.circle12, this.pellicle, this.circle9, this.circle2, this.circle10, this.circle7, this.circle5, this.circle8, this.circle4, new ModelRenderer[] { this.circle6 }).forEach(model -> model.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
/*     */ 
/*     */     
/*  95 */     matrixStack.pop();
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
/* 106 */     model.rotateAngleX = x;
/* 107 */     model.rotateAngleY = y;
/* 108 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\NoroNoroBeamModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */