/*     */ package xyz.pixelatedw.mineminenomi.models.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ 
/*     */ public class HanaCalendulaModel<T extends LivingEntity>
/*     */   extends PlayerModel<T> {
/*     */   private final ModelRenderer calendula;
/*     */   private final ModelRenderer calendula1;
/*     */   private final ModelRenderer calendula2;
/*     */   private final ModelRenderer calendula3;
/*     */   private final ModelRenderer calendula4;
/*     */   private final ModelRenderer calendula5;
/*     */   private final ModelRenderer calendula6;
/*     */   private final ModelRenderer calendula7;
/*     */   private final ModelRenderer calendula8;
/*     */   
/*     */   public HanaCalendulaModel() {
/*  25 */     super(1.0F, false);
/*  26 */     this.textureWidth = 64;
/*  27 */     this.textureHeight = 64;
/*     */     
/*  29 */     this.calendula = new ModelRenderer((Model)this);
/*  30 */     this.calendula.setRotationPoint(-9.0F, 7.0F, 0.0F);
/*     */     
/*  32 */     this.calendula1 = new ModelRenderer((Model)this);
/*  33 */     this.calendula1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  34 */     this.calendula.addChild(this.calendula1);
/*  35 */     this.calendula1.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  37 */     this.calendula2 = new ModelRenderer((Model)this);
/*  38 */     this.calendula2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  39 */     this.calendula.addChild(this.calendula2);
/*  40 */     setRotationAngle(this.calendula2, -0.7854F, 0.0F, 0.0F);
/*  41 */     this.calendula2.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.01F, false);
/*     */     
/*  43 */     this.calendula3 = new ModelRenderer((Model)this);
/*  44 */     this.calendula3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  45 */     this.calendula.addChild(this.calendula3);
/*  46 */     setRotationAngle(this.calendula3, -1.5708F, 0.0F, 0.0F);
/*  47 */     this.calendula3.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.011F, false);
/*     */     
/*  49 */     this.calendula4 = new ModelRenderer((Model)this);
/*  50 */     this.calendula4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  51 */     this.calendula.addChild(this.calendula4);
/*  52 */     setRotationAngle(this.calendula4, -2.3562F, 0.0F, 0.0F);
/*  53 */     this.calendula4.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.012F, false);
/*     */     
/*  55 */     this.calendula5 = new ModelRenderer((Model)this);
/*  56 */     this.calendula5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  57 */     this.calendula.addChild(this.calendula5);
/*  58 */     setRotationAngle(this.calendula5, 3.1416F, 0.0F, 0.0F);
/*  59 */     this.calendula5.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.013F, false);
/*     */     
/*  61 */     this.calendula6 = new ModelRenderer((Model)this);
/*  62 */     this.calendula6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  63 */     this.calendula.addChild(this.calendula6);
/*  64 */     setRotationAngle(this.calendula6, 2.3562F, 0.0F, 0.0F);
/*  65 */     this.calendula6.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.014F, false);
/*     */     
/*  67 */     this.calendula7 = new ModelRenderer((Model)this);
/*  68 */     this.calendula7.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  69 */     this.calendula.addChild(this.calendula7);
/*  70 */     setRotationAngle(this.calendula7, 1.5708F, 0.0F, 0.0F);
/*  71 */     this.calendula7.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.015F, false);
/*     */     
/*  73 */     this.calendula8 = new ModelRenderer((Model)this);
/*  74 */     this.calendula8.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  75 */     this.calendula.addChild(this.calendula8);
/*  76 */     setRotationAngle(this.calendula8, 0.7854F, 0.0F, 0.0F);
/*  77 */     this.calendula8.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.016F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  83 */     this.calendula.rotateAngleX = -ageInTicks * 0.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  89 */     matrixStack.push();
/*  90 */     matrixStack.translate(0.0D, -0.4D, 0.0D);
/*  91 */     matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
/*  92 */     this.calendula.render(matrixStack, buffer, packedLight, packedOverlay);
/*  93 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/*  98 */     modelRenderer.rotateAngleX = x;
/*  99 */     modelRenderer.rotateAngleY = y;
/* 100 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\abilities\HanaCalendulaModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */