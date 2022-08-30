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
/*     */ public class MeigoModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer leftarm;
/*     */   public ModelRenderer leftarm2;
/*     */   public ModelRenderer leftarm3;
/*     */   public ModelRenderer leftarm4;
/*     */   public ModelRenderer leftarm5;
/*     */   public ModelRenderer leftarm6;
/*     */   public ModelRenderer leftarm7;
/*     */   public ModelRenderer lefthand;
/*     */   public ModelRenderer leftfinger10;
/*     */   public ModelRenderer leftfinger20;
/*     */   public ModelRenderer leftfinger30;
/*     */   public ModelRenderer leftfinger40;
/*     */   public ModelRenderer leftfinger50;
/*     */   public ModelRenderer leftfinger11;
/*     */   public ModelRenderer leftfinger21;
/*     */   public ModelRenderer leftfinger31;
/*     */   public ModelRenderer leftfinger41;
/*     */   public ModelRenderer leftfinger51;
/*     */   
/*     */   public MeigoModel() {
/*  36 */     this.textureWidth = 64;
/*  37 */     this.textureHeight = 32;
/*  38 */     this.leftarm3 = new ModelRenderer((Model)this, 0, 21);
/*  39 */     this.leftarm3.setRotationPoint(0.4F, -7.3F, 0.5F);
/*  40 */     this.leftarm3.addBox(-0.8F, 1.0F, -0.9F, 3.0F, 5.0F, 3.0F, 0.0F);
/*  41 */     this.leftfinger51 = new ModelRenderer((Model)this, 17, 10);
/*  42 */     this.leftfinger51.setRotationPoint(-1.0F, 2.7F, -1.7F);
/*  43 */     this.leftfinger51.addBox(0.0F, 0.2F, 0.2F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  44 */     setRotateAngle(this.leftfinger51, 1.0471976F, -0.0F, 0.0F);
/*  45 */     this.leftfinger30 = new ModelRenderer((Model)this, 17, 6);
/*  46 */     this.leftfinger30.setRotationPoint(0.0F, 0.1F, 0.4F);
/*  47 */     this.leftfinger30.addBox(-0.8F, 1.0F, -2.2F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  48 */     setRotateAngle(this.leftfinger30, -0.43633232F, 0.0F, 0.0F);
/*  49 */     this.leftfinger20 = new ModelRenderer((Model)this, 17, 6);
/*  50 */     this.leftfinger20.setRotationPoint(0.0F, 0.1F, 0.4F);
/*  51 */     this.leftfinger20.addBox(0.4F, 1.0F, -2.2F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  52 */     setRotateAngle(this.leftfinger20, -0.43633232F, 0.0F, 0.0F);
/*  53 */     this.leftfinger41 = new ModelRenderer((Model)this, 17, 10);
/*  54 */     this.leftfinger41.setRotationPoint(-1.7F, 3.0F, -2.2F);
/*  55 */     this.leftfinger41.addBox(0.0F, 0.2F, 0.2F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  56 */     setRotateAngle(this.leftfinger41, 1.0471976F, -0.0F, 0.0F);
/*  57 */     this.leftarm6 = new ModelRenderer((Model)this, 26, 21);
/*  58 */     this.leftarm6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  59 */     this.leftarm6.addBox(-0.7F, 3.5F, -0.7F, 3.0F, 4.0F, 3.0F, 0.0F);
/*  60 */     this.leftfinger50 = new ModelRenderer((Model)this, 17, 6);
/*  61 */     this.leftfinger50.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  62 */     this.leftfinger50.addBox(-1.0F, 0.6F, -1.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  63 */     setRotateAngle(this.leftfinger50, -0.43633232F, 2.048842F, 0.4098033F);
/*  64 */     this.leftarm2 = new ModelRenderer((Model)this, 0, 21);
/*  65 */     this.leftarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  66 */     this.leftarm2.addBox(-2.4F, -7.5F, -2.4F, 3.0F, 5.0F, 3.0F, 0.0F);
/*  67 */     this.leftarm7 = new ModelRenderer((Model)this, 26, 21);
/*  68 */     this.leftarm7.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  69 */     this.leftarm7.addBox(-2.2F, 4.2F, -2.3F, 3.0F, 4.0F, 3.0F, 0.0F);
/*  70 */     this.leftfinger10 = new ModelRenderer((Model)this, 17, 6);
/*  71 */     this.leftfinger10.setRotationPoint(0.0F, 0.1F, 0.4F);
/*  72 */     this.leftfinger10.addBox(1.5F, 1.0F, -2.2F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  73 */     setRotateAngle(this.leftfinger10, -0.43633232F, -0.091106184F, 0.0F);
/*  74 */     this.leftfinger21 = new ModelRenderer((Model)this, 17, 10);
/*  75 */     this.leftfinger21.setRotationPoint(0.45F, 3.0F, -2.2F);
/*  76 */     this.leftfinger21.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  77 */     setRotateAngle(this.leftfinger21, 1.0471976F, -0.0F, 0.0F);
/*  78 */     this.leftarm4 = new ModelRenderer((Model)this, 13, 21);
/*  79 */     this.leftarm4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  80 */     this.leftarm4.addBox(-2.4F, -3.2F, -1.4F, 3.0F, 5.0F, 3.0F, 0.0F);
/*  81 */     this.leftarm5 = new ModelRenderer((Model)this, 13, 21);
/*  82 */     this.leftarm5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  83 */     this.leftarm5.addBox(-0.9F, -2.1F, -2.4F, 3.0F, 5.0F, 3.0F, 0.0F);
/*  84 */     this.leftarm = new ModelRenderer((Model)this, 0, 0);
/*  85 */     this.leftarm.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  86 */     this.leftarm.addBox(-2.0F, -7.0F, -2.0F, 4.0F, 16.0F, 4.0F, 0.0F);
/*  87 */     setRotateAngle(this.leftarm, -1.5707964F, -0.0F, 0.0F);
/*  88 */     this.leftfinger40 = new ModelRenderer((Model)this, 17, 6);
/*  89 */     this.leftfinger40.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  90 */     this.leftfinger40.addBox(-1.7F, 0.9F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  91 */     setRotateAngle(this.leftfinger40, -0.43633232F, 0.18203785F, 0.0F);
/*  92 */     this.lefthand = new ModelRenderer((Model)this, 17, 0);
/*  93 */     this.lefthand.setRotationPoint(-0.5F, 8.5F, 0.0F);
/*  94 */     this.lefthand.addBox(-1.5F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F);
/*  95 */     setRotateAngle(this.lefthand, 0.17453292F, -0.0F, 0.0F);
/*  96 */     this.leftfinger31 = new ModelRenderer((Model)this, 17, 10);
/*  97 */     this.leftfinger31.setRotationPoint(-0.8F, 3.0F, -2.2F);
/*  98 */     this.leftfinger31.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  99 */     setRotateAngle(this.leftfinger31, 1.0471976F, -0.0F, 0.0F);
/* 100 */     this.leftfinger11 = new ModelRenderer((Model)this, 17, 10);
/* 101 */     this.leftfinger11.setRotationPoint(1.5F, 3.0F, -2.2F);
/* 102 */     this.leftfinger11.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 103 */     setRotateAngle(this.leftfinger11, 1.0471976F, -0.0F, 0.0F);
/* 104 */     this.leftarm.addChild(this.leftarm3);
/* 105 */     this.leftfinger50.addChild(this.leftfinger51);
/* 106 */     this.lefthand.addChild(this.leftfinger30);
/* 107 */     this.lefthand.addChild(this.leftfinger20);
/* 108 */     this.leftfinger40.addChild(this.leftfinger41);
/* 109 */     this.leftarm.addChild(this.leftarm6);
/* 110 */     this.lefthand.addChild(this.leftfinger50);
/* 111 */     this.leftarm.addChild(this.leftarm2);
/* 112 */     this.leftarm.addChild(this.leftarm7);
/* 113 */     this.lefthand.addChild(this.leftfinger10);
/* 114 */     this.leftfinger20.addChild(this.leftfinger21);
/* 115 */     this.leftarm.addChild(this.leftarm4);
/* 116 */     this.leftarm.addChild(this.leftarm5);
/* 117 */     this.lefthand.addChild(this.leftfinger40);
/* 118 */     this.leftarm.addChild(this.lefthand);
/* 119 */     this.leftfinger30.addChild(this.leftfinger31);
/* 120 */     this.leftfinger10.addChild(this.leftfinger11);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 126 */     this.leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
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
/* 137 */     model.rotateAngleX = x;
/* 138 */     model.rotateAngleY = y;
/* 139 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\MeigoModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */