/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class WeatherWizardModel
/*     */   extends BipedModel
/*     */ {
/*     */   public ModelRenderer rightArm;
/*     */   public ModelRenderer rightLeg;
/*     */   public ModelRenderer head;
/*     */   public ModelRenderer body;
/*     */   public ModelRenderer leftArm;
/*     */   public ModelRenderer leftLeg;
/*     */   public ModelRenderer hat1;
/*     */   public ModelRenderer beard1;
/*     */   public ModelRenderer hat2;
/*     */   public ModelRenderer hat3;
/*     */   public ModelRenderer hat4;
/*     */   public ModelRenderer hat5;
/*     */   public ModelRenderer beard2;
/*     */   public ModelRenderer rightFancyBeard1;
/*     */   public ModelRenderer leftFancyBeard1;
/*     */   
/*     */   public WeatherWizardModel() {
/*  32 */     super(1.0F);
/*  33 */     this.textureWidth = 64;
/*  34 */     this.textureHeight = 64;
/*  35 */     this.hat5 = new ModelRenderer((Model)this, 0, 39);
/*  36 */     this.hat5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  37 */     this.hat5.addBox(-2.5F, -9.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F);
/*  38 */     this.leftFancyBeard1 = new ModelRenderer((Model)this, 35, 0);
/*  39 */     this.leftFancyBeard1.setRotationPoint(0.9F, 1.0F, 0.0F);
/*  40 */     this.leftFancyBeard1.addBox(0.0F, -2.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F);
/*  41 */     setRotateAngle(this.leftFancyBeard1, 0.0F, 0.0F, 1.0471976F);
/*  42 */     this.beard2 = new ModelRenderer((Model)this, 35, 0);
/*  43 */     this.beard2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  44 */     this.beard2.addBox(-2.0F, 7.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F);
/*  45 */     this.head = new ModelRenderer((Model)this, 0, 0);
/*  46 */     this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  47 */     this.head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
/*  48 */     this.beard1 = new ModelRenderer((Model)this, 35, 0);
/*  49 */     this.beard1.setRotationPoint(0.0F, -2.0F, -5.0F);
/*  50 */     this.beard1.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 7.0F, 1.0F, 0.0F);
/*  51 */     this.rightArm = new ModelRenderer((Model)this, 40, 16);
/*  52 */     this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/*  53 */     this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/*  54 */     this.rightFancyBeard1 = new ModelRenderer((Model)this, 35, 0);
/*  55 */     this.rightFancyBeard1.setRotationPoint(-0.9F, 1.0F, 0.0F);
/*  56 */     this.rightFancyBeard1.addBox(-4.0F, -2.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F);
/*  57 */     setRotateAngle(this.rightFancyBeard1, 0.0F, 0.0F, -1.0471976F);
/*  58 */     this.body = new ModelRenderer((Model)this, 16, 16);
/*  59 */     this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  60 */     this.body.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F);
/*  61 */     this.hat1 = new ModelRenderer((Model)this, 0, 35);
/*  62 */     this.hat1.setRotationPoint(0.0F, -8.5F, 0.0F);
/*  63 */     this.hat1.addBox(-4.5F, 0.0F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F);
/*  64 */     this.hat2 = new ModelRenderer((Model)this, 0, 36);
/*  65 */     this.hat2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  66 */     this.hat2.addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F);
/*  67 */     this.hat4 = new ModelRenderer((Model)this, 0, 38);
/*  68 */     this.hat4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  69 */     this.hat4.addBox(-3.0F, -7.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F);
/*  70 */     this.leftLeg = new ModelRenderer((Model)this, 16, 48);
/*  71 */     this.leftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
/*  72 */     this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/*  73 */     this.rightLeg = new ModelRenderer((Model)this, 0, 16);
/*  74 */     this.rightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
/*  75 */     this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/*  76 */     this.leftArm = new ModelRenderer((Model)this, 32, 48);
/*  77 */     this.leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/*  78 */     this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
/*  79 */     this.hat3 = new ModelRenderer((Model)this, 0, 36);
/*  80 */     this.hat3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  81 */     this.hat3.addBox(-3.5F, -5.0F, -3.5F, 7.0F, 3.0F, 7.0F, 0.0F);
/*  82 */     this.hat4.addChild(this.hat5);
/*  83 */     this.beard2.addChild(this.leftFancyBeard1);
/*  84 */     this.beard1.addChild(this.beard2);
/*  85 */     this.head.addChild(this.beard1);
/*  86 */     this.beard2.addChild(this.rightFancyBeard1);
/*  87 */     this.head.addChild(this.hat1);
/*  88 */     this.hat1.addChild(this.hat2);
/*  89 */     this.hat3.addChild(this.hat4);
/*  90 */     this.hat2.addChild(this.hat3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  96 */     this.bipedBody = this.body;
/*  97 */     this.bipedHead = this.head;
/*  98 */     this.bipedLeftArm = this.leftArm;
/*  99 */     this.bipedRightArm = this.rightArm;
/* 100 */     this.bipedLeftLeg = this.leftLeg;
/* 101 */     this.bipedRightLeg = this.rightLeg;
/*     */     
/* 103 */     this.bipedHeadwear.showModel = false;
/*     */     
/* 105 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 106 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 107 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 108 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 109 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 110 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 115 */     model.rotateAngleX = x;
/* 116 */     model.rotateAngleY = y;
/* 117 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\WeatherWizardModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */