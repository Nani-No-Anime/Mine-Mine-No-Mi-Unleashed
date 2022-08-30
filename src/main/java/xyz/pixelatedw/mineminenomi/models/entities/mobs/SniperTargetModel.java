/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ 
/*     */ public class SniperTargetModel
/*     */   extends BipedModel<LivingEntity>
/*     */ {
/*     */   public ModelRenderer parachute;
/*     */   public ModelRenderer target;
/*     */   public ModelRenderer parachuteChild;
/*     */   public ModelRenderer parachuteChild_1;
/*     */   public ModelRenderer parachuteChild_2;
/*     */   public ModelRenderer parachuteChild_3;
/*     */   public ModelRenderer parachuteChild_4;
/*     */   public ModelRenderer parachuteChild_5;
/*     */   public ModelRenderer parachuteChild_6;
/*     */   public ModelRenderer parachuteChild_7;
/*     */   public ModelRenderer parachuteChild_8;
/*     */   public ModelRenderer parachuteChild_9;
/*     */   public ModelRenderer parachuteChild_10;
/*     */   public ModelRenderer parachuteChild_11;
/*     */   
/*     */   public SniperTargetModel() {
/*  29 */     super(1.0F);
/*  30 */     this.textureWidth = 64;
/*  31 */     this.textureHeight = 64;
/*  32 */     this.parachuteChild_4 = new ModelRenderer((Model)this, 5, 30);
/*  33 */     this.parachuteChild_4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  34 */     this.parachuteChild_4.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  35 */     setRotateAngle(this.parachuteChild_4, 0.2617994F, 1.5707964F, 0.0F);
/*  36 */     this.parachuteChild_11 = new ModelRenderer((Model)this, 5, 30);
/*  37 */     this.parachuteChild_11.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  38 */     this.parachuteChild_11.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  39 */     setRotateAngle(this.parachuteChild_11, 0.2617994F, 3.1415927F, 0.0F);
/*  40 */     this.parachuteChild_3 = new ModelRenderer((Model)this, 0, 30);
/*  41 */     this.parachuteChild_3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  42 */     this.parachuteChild_3.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/*  43 */     setRotateAngle(this.parachuteChild_3, 0.43633232F, 0.0F, 0.0F);
/*  44 */     this.parachuteChild_9 = new ModelRenderer((Model)this, 5, 30);
/*  45 */     this.parachuteChild_9.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  46 */     this.parachuteChild_9.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  47 */     setRotateAngle(this.parachuteChild_9, 0.2617994F, 2.3561945F, 0.0F);
/*  48 */     this.parachuteChild_7 = new ModelRenderer((Model)this, 0, 30);
/*  49 */     this.parachuteChild_7.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  50 */     this.parachuteChild_7.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/*  51 */     setRotateAngle(this.parachuteChild_7, 0.43633232F, 1.5707964F, 0.0F);
/*  52 */     this.parachuteChild_5 = new ModelRenderer((Model)this, 5, 30);
/*  53 */     this.parachuteChild_5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  54 */     this.parachuteChild_5.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  55 */     setRotateAngle(this.parachuteChild_5, 0.2617994F, 3.9269907F, 0.0F);
/*  56 */     this.parachute = new ModelRenderer((Model)this, 0, 30);
/*  57 */     this.parachute.setRotationPoint(0.0F, 19.0F, 0.0F);
/*  58 */     this.parachute.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  59 */     this.target = new ModelRenderer((Model)this, 0, 0);
/*  60 */     this.target.setRotationPoint(0.0F, 20.0F, 0.0F);
/*  61 */     this.target.addBox(-4.0F, 0.0F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F);
/*  62 */     this.parachuteChild_1 = new ModelRenderer((Model)this, 5, 30);
/*  63 */     this.parachuteChild_1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  64 */     this.parachuteChild_1.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  65 */     setRotateAngle(this.parachuteChild_1, 0.2617994F, 0.7853982F, 0.0F);
/*  66 */     this.parachuteChild_10 = new ModelRenderer((Model)this, 0, 30);
/*  67 */     this.parachuteChild_10.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  68 */     this.parachuteChild_10.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/*  69 */     setRotateAngle(this.parachuteChild_10, 0.43633232F, -1.5707964F, 0.0F);
/*  70 */     this.parachuteChild_8 = new ModelRenderer((Model)this, 5, 30);
/*  71 */     this.parachuteChild_8.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  72 */     this.parachuteChild_8.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  73 */     setRotateAngle(this.parachuteChild_8, 0.2617994F, 0.0F, 0.0F);
/*  74 */     this.parachuteChild = new ModelRenderer((Model)this, 5, 30);
/*  75 */     this.parachuteChild.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  76 */     this.parachuteChild.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  77 */     setRotateAngle(this.parachuteChild, 0.2617994F, 5.497787F, 0.0F);
/*  78 */     this.parachuteChild_2 = new ModelRenderer((Model)this, 5, 30);
/*  79 */     this.parachuteChild_2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  80 */     this.parachuteChild_2.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  81 */     setRotateAngle(this.parachuteChild_2, 0.2617994F, 4.712389F, 0.0F);
/*  82 */     this.parachuteChild_6 = new ModelRenderer((Model)this, 0, 30);
/*  83 */     this.parachuteChild_6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  84 */     this.parachuteChild_6.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/*  85 */     setRotateAngle(this.parachuteChild_6, 0.43633232F, 3.1415927F, 0.0F);
/*  86 */     this.parachute.addChild(this.parachuteChild_4);
/*  87 */     this.parachute.addChild(this.parachuteChild_11);
/*  88 */     this.parachute.addChild(this.parachuteChild_3);
/*  89 */     this.parachute.addChild(this.parachuteChild_9);
/*  90 */     this.parachute.addChild(this.parachuteChild_7);
/*  91 */     this.parachute.addChild(this.parachuteChild_5);
/*  92 */     this.parachute.addChild(this.parachuteChild_1);
/*  93 */     this.parachute.addChild(this.parachuteChild_10);
/*  94 */     this.parachute.addChild(this.parachuteChild_8);
/*  95 */     this.parachute.addChild(this.parachuteChild);
/*  96 */     this.parachute.addChild(this.parachuteChild_2);
/*  97 */     this.parachute.addChild(this.parachuteChild_6);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 103 */     this.parachute.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 104 */     this.target.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 109 */     model.rotateAngleX = x;
/* 110 */     model.rotateAngleY = y;
/* 111 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\SniperTargetModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */