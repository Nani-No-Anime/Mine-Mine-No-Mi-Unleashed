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
/*     */ public class SpringLegsPartialModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> {
/*     */   private final ModelRenderer rightSpring;
/*     */   private final ModelRenderer leftSpring;
/*     */   private final ModelRenderer rightSpringLayer;
/*     */   private final ModelRenderer leftSpringLayer;
/*     */   
/*     */   public SpringLegsPartialModel() {
/*  20 */     super(1.0F);
/*  21 */     this.textureWidth = 64;
/*  22 */     this.textureHeight = 64;
/*     */     
/*  24 */     this.rightSpring = new ModelRenderer((Model)this);
/*  25 */     this.rightSpring.setRotationPoint(-2.0F, 12.0F, 0.0F);
/*  26 */     this.rightSpring.setTextureOffset(0, 0).addBox(-1.0F, -0.25F, -2.0F, 2.0F, 1.0F, 1.0F, -0.01F, false);
/*  27 */     this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*  28 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/*  29 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  30 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 1.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
/*  31 */     this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 2.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  32 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 2.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
/*  33 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 3.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  34 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 3.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
/*  35 */     this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 4.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  36 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 4.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
/*  37 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 5.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  38 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 5.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
/*  39 */     this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 6.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  40 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 6.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
/*  41 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 7.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  42 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 7.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
/*  43 */     this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 8.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  44 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 8.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
/*  45 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 9.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  46 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 9.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
/*  47 */     this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 10.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  48 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 10.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/*  49 */     this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 11.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*  50 */     this.rightSpring.setTextureOffset(0, 0).addBox(-1.0F, 11.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/*  52 */     this.leftSpring = new ModelRenderer((Model)this);
/*  53 */     this.leftSpring.setRotationPoint(2.0F, 12.0F, 0.0F);
/*  54 */     this.leftSpring.setTextureOffset(0, 0).addBox(-1.0F, -0.25F, -2.0F, 2.0F, 1.0F, 1.0F, -0.01F, true);
/*  55 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, true);
/*  56 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, true);
/*  57 */     this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  58 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 1.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
/*  59 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 2.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  60 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 2.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
/*  61 */     this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 3.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  62 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 3.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
/*  63 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 4.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  64 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 4.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
/*  65 */     this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 5.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  66 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 5.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
/*  67 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 6.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  68 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 6.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
/*  69 */     this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 7.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  70 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 7.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
/*  71 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 8.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  72 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 8.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
/*  73 */     this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 9.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  74 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 9.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
/*  75 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 10.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  76 */     this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 10.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, true);
/*  77 */     this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 11.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
/*  78 */     this.leftSpring.setTextureOffset(0, 0).addBox(-1.0F, 11.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);
/*     */     
/*  80 */     this.rightSpringLayer = new ModelRenderer((Model)this);
/*  81 */     this.rightSpringLayer.setRotationPoint(-2.0F, 12.0F, 0.0F);
/*  82 */     this.rightSpringLayer.setTextureOffset(0, 48).addBox(-2.0F, -0.25F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
/*     */     
/*  84 */     this.leftSpringLayer = new ModelRenderer((Model)this);
/*  85 */     this.leftSpringLayer.setRotationPoint(2.0F, 12.0F, 0.0F);
/*  86 */     this.leftSpringLayer.setTextureOffset(16, 48).addBox(-2.0F, -0.25F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, true);
/*     */     
/*  88 */     this.bipedLeftLeg = this.leftSpring;
/*  89 */     this.bipedRightLeg = this.rightSpring;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  95 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 101 */     this.rightSpring.render(matrixStack, buffer, packedLight, packedOverlay);
/* 102 */     this.leftSpring.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 107 */     modelRenderer.rotateAngleX = x;
/* 108 */     modelRenderer.rotateAngleY = y;
/* 109 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\SpringLegsPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */