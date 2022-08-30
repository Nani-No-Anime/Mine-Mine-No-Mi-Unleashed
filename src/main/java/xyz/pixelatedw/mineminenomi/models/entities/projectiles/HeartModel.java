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
/*     */ public class HeartModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer heart1;
/*     */   public ModelRenderer heart2;
/*     */   public ModelRenderer heart3;
/*     */   public ModelRenderer heart4;
/*     */   public ModelRenderer heart5;
/*     */   public ModelRenderer heart6;
/*     */   public ModelRenderer heart7;
/*     */   public ModelRenderer heart8;
/*     */   public ModelRenderer heart9;
/*     */   public ModelRenderer heart10;
/*     */   public ModelRenderer heart11;
/*     */   public ModelRenderer heart12;
/*     */   public ModelRenderer heart13;
/*     */   public ModelRenderer heart14;
/*     */   public ModelRenderer heart15;
/*     */   public ModelRenderer heart16;
/*     */   public ModelRenderer heart17;
/*     */   public ModelRenderer heart18;
/*     */   public ModelRenderer pellicle;
/*     */   
/*     */   public HeartModel() {
/*  37 */     this.textureWidth = 64;
/*  38 */     this.textureHeight = 32;
/*  39 */     this.heart4 = new ModelRenderer((Model)this, 0, 0);
/*  40 */     this.heart4.setRotationPoint(3.2F, 8.7F, 0.0F);
/*  41 */     this.heart4.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
/*  42 */     setRotateAngle(this.heart4, 0.0F, -0.0F, -2.4958208F);
/*  43 */     this.heart15 = new ModelRenderer((Model)this, 10, 0);
/*  44 */     this.heart15.setRotationPoint(-4.4F, -8.0F, 0.0F);
/*  45 */     this.heart15.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  46 */     setRotateAngle(this.heart15, 0.0F, -0.0F, -1.012291F);
/*  47 */     this.heart9 = new ModelRenderer((Model)this, 5, 0);
/*  48 */     this.heart9.setRotationPoint(-9.8F, -2.9F, 0.0F);
/*  49 */     this.heart9.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
/*  50 */     setRotateAngle(this.heart9, 0.0F, -0.0F, -2.8972466F);
/*  51 */     this.heart11 = new ModelRenderer((Model)this, 10, 0);
/*  52 */     this.heart11.setRotationPoint(-8.9F, -6.5F, 0.0F);
/*  53 */     this.heart11.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  54 */     setRotateAngle(this.heart11, 0.0F, -0.0F, -2.3212879F);
/*  55 */     this.heart16 = new ModelRenderer((Model)this, 10, 0);
/*  56 */     this.heart16.setRotationPoint(4.4F, -8.0F, 0.0F);
/*  57 */     this.heart16.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  58 */     setRotateAngle(this.heart16, 0.0F, -0.0F, 1.012291F);
/*  59 */     this.heart2 = new ModelRenderer((Model)this, 0, 0);
/*  60 */     this.heart2.setRotationPoint(-0.3F, 12.0F, 0.0F);
/*  61 */     this.heart2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
/*  62 */     setRotateAngle(this.heart2, 0.0F, -0.0F, -2.3387413F);
/*  63 */     this.heart6 = new ModelRenderer((Model)this, 0, 0);
/*  64 */     this.heart6.setRotationPoint(6.2F, 4.9F, 0.0F);
/*  65 */     this.heart6.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
/*  66 */     setRotateAngle(this.heart6, 0.0F, -0.0F, -2.6529005F);
/*  67 */     this.heart12 = new ModelRenderer((Model)this, 10, 0);
/*  68 */     this.heart12.setRotationPoint(8.9F, -6.5F, 0.0F);
/*  69 */     this.heart12.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  70 */     setRotateAngle(this.heart12, 0.0F, -0.0F, 2.3212879F);
/*  71 */     this.heart7 = new ModelRenderer((Model)this, 0, 0);
/*  72 */     this.heart7.setRotationPoint(-8.6F, 0.7F, 0.0F);
/*  73 */     this.heart7.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
/*  74 */     setRotateAngle(this.heart7, 0.0F, -0.0F, 2.86234F);
/*  75 */     this.heart5 = new ModelRenderer((Model)this, 0, 0);
/*  76 */     this.heart5.setRotationPoint(-6.2F, 4.9F, 0.0F);
/*  77 */     this.heart5.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
/*  78 */     setRotateAngle(this.heart5, 0.0F, -0.0F, 2.6529005F);
/*  79 */     this.pellicle = new ModelRenderer((Model)this, 15, 0);
/*  80 */     this.pellicle.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  81 */     this.pellicle.addBox(-9.5F, -9.0F, 0.0F, 19.0F, 21.0F, 0.0F, 0.0F);
/*  82 */     this.heart13 = new ModelRenderer((Model)this, 10, 0);
/*  83 */     this.heart13.mirror = true;
/*  84 */     this.heart13.setRotationPoint(-7.1F, -8.4F, 0.0F);
/*  85 */     this.heart13.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  86 */     setRotateAngle(this.heart13, 0.0F, -0.0F, -1.4137167F);
/*  87 */     this.heart18 = new ModelRenderer((Model)this, 10, 0);
/*  88 */     this.heart18.setRotationPoint(2.0F, -6.5F, 0.0F);
/*  89 */     this.heart18.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  90 */     setRotateAngle(this.heart18, 0.0F, -0.0F, 0.87266463F);
/*  91 */     this.heart8 = new ModelRenderer((Model)this, 0, 0);
/*  92 */     this.heart8.setRotationPoint(8.6F, 0.7F, 0.0F);
/*  93 */     this.heart8.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
/*  94 */     setRotateAngle(this.heart8, 0.0F, -0.0F, -2.86234F);
/*  95 */     this.heart14 = new ModelRenderer((Model)this, 10, 0);
/*  96 */     this.heart14.setRotationPoint(7.1F, -8.4F, 0.0F);
/*  97 */     this.heart14.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  98 */     setRotateAngle(this.heart14, 0.0F, -0.0F, 1.4137167F);
/*  99 */     this.heart1 = new ModelRenderer((Model)this, 0, 0);
/* 100 */     this.heart1.mirror = true;
/* 101 */     this.heart1.setRotationPoint(0.3F, 12.0F, 0.0F);
/* 102 */     this.heart1.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
/* 103 */     setRotateAngle(this.heart1, 0.0F, -0.0F, 2.3387413F);
/* 104 */     this.heart17 = new ModelRenderer((Model)this, 10, 0);
/* 105 */     this.heart17.setRotationPoint(-2.0F, -6.5F, 0.0F);
/* 106 */     this.heart17.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
/* 107 */     setRotateAngle(this.heart17, 0.0F, -0.0F, -0.87266463F);
/* 108 */     this.heart3 = new ModelRenderer((Model)this, 0, 0);
/* 109 */     this.heart3.setRotationPoint(-3.2F, 8.7F, 0.0F);
/* 110 */     this.heart3.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
/* 111 */     setRotateAngle(this.heart3, 0.0F, -0.0F, 2.4958208F);
/* 112 */     this.heart10 = new ModelRenderer((Model)this, 5, 0);
/* 113 */     this.heart10.setRotationPoint(9.8F, -2.9F, 0.0F);
/* 114 */     this.heart10.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
/* 115 */     setRotateAngle(this.heart10, 0.0F, -0.0F, 2.8972466F);
/* 116 */     this.pellicle.addChild(this.heart4);
/* 117 */     this.pellicle.addChild(this.heart15);
/* 118 */     this.pellicle.addChild(this.heart9);
/* 119 */     this.pellicle.addChild(this.heart11);
/* 120 */     this.pellicle.addChild(this.heart16);
/* 121 */     this.pellicle.addChild(this.heart2);
/* 122 */     this.pellicle.addChild(this.heart6);
/* 123 */     this.pellicle.addChild(this.heart12);
/* 124 */     this.pellicle.addChild(this.heart7);
/* 125 */     this.pellicle.addChild(this.heart5);
/* 126 */     this.pellicle.addChild(this.heart13);
/* 127 */     this.pellicle.addChild(this.heart18);
/* 128 */     this.pellicle.addChild(this.heart8);
/* 129 */     this.pellicle.addChild(this.heart14);
/* 130 */     this.pellicle.addChild(this.heart1);
/* 131 */     this.pellicle.addChild(this.heart17);
/* 132 */     this.pellicle.addChild(this.heart3);
/* 133 */     this.pellicle.addChild(this.heart10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 139 */     matrixStack.push();
/* 140 */     matrixStack.scale(1.0F, 1.0F, 1.1F);
/* 141 */     this.pellicle.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 142 */     matrixStack.pop();
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
/* 153 */     model.rotateAngleX = x;
/* 154 */     model.rotateAngleY = y;
/* 155 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\HeartModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */