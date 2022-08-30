/*     */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class BrickBatModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer body;
/*     */   public ModelRenderer body2;
/*     */   public ModelRenderer body3;
/*     */   public ModelRenderer body4;
/*     */   public ModelRenderer rightear;
/*     */   public ModelRenderer leftear;
/*     */   public ModelRenderer rightWing1;
/*     */   public ModelRenderer leftWing1;
/*     */   public ModelRenderer rightWing2;
/*     */   public ModelRenderer leftWing2;
/*     */   
/*     */   public BrickBatModel() {
/*  29 */     this.textureWidth = 128;
/*  30 */     this.textureHeight = 64;
/*  31 */     this.body3 = new ModelRenderer((Model)this, 42, 0);
/*  32 */     this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  33 */     this.body3.addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 6.0F, 0.0F);
/*  34 */     this.leftWing1 = new ModelRenderer((Model)this, 80, 5);
/*  35 */     this.leftWing1.setRotationPoint(1.0F, 0.0F, -1.0F);
/*  36 */     this.leftWing1.addBox(0.0F, 0.0F, 0.0F, 6.0F, 4.0F, 0.0F, 0.0F);
/*  37 */     setRotateAngle(this.leftWing1, 0.87266463F, -0.0F, -0.43633232F);
/*  38 */     this.rightWing1 = new ModelRenderer((Model)this, 80, 0);
/*  39 */     this.rightWing1.setRotationPoint(-1.0F, 0.0F, -1.0F);
/*  40 */     this.rightWing1.addBox(-6.0F, 0.0F, 0.0F, 6.0F, 4.0F, 0.0F, 0.0F);
/*  41 */     setRotateAngle(this.rightWing1, 0.87266463F, -0.0F, 0.43633232F);
/*  42 */     this.rightWing2 = new ModelRenderer((Model)this, 93, 0);
/*  43 */     this.rightWing2.setRotationPoint(-6.0F, 0.0F, 0.0F);
/*  44 */     this.rightWing2.addBox(-5.0F, 0.0F, 0.0F, 5.0F, 4.0F, 0.0F, 0.0F);
/*  45 */     setRotateAngle(this.rightWing2, 0.0F, -0.5235988F, -0.17453292F);
/*  46 */     this.body2 = new ModelRenderer((Model)this, 21, 0);
/*  47 */     this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  48 */     this.body2.addBox(-3.0F, -2.0F, -2.0F, 6.0F, 4.0F, 4.0F, 0.0F);
/*  49 */     this.body4 = new ModelRenderer((Model)this, 63, 0);
/*  50 */     this.body4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  51 */     this.body4.addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F);
/*  52 */     this.rightear = new ModelRenderer((Model)this, 0, 11);
/*  53 */     this.rightear.setRotationPoint(-1.9F, -2.6F, 0.0F);
/*  54 */     this.rightear.addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  55 */     setRotateAngle(this.rightear, 0.0F, -0.0F, -0.5235988F);
/*  56 */     this.leftear = new ModelRenderer((Model)this, 0, 11);
/*  57 */     this.leftear.setRotationPoint(1.9F, -2.6F, 0.0F);
/*  58 */     this.leftear.addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  59 */     setRotateAngle(this.leftear, 0.0F, -0.0F, 0.5235988F);
/*  60 */     this.body = new ModelRenderer((Model)this, 0, 0);
/*  61 */     this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  62 */     this.body.addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F);
/*  63 */     this.leftWing2 = new ModelRenderer((Model)this, 93, 5);
/*  64 */     this.leftWing2.setRotationPoint(6.0F, 0.0F, 0.0F);
/*  65 */     this.leftWing2.addBox(0.0F, 0.0F, 0.0F, 5.0F, 4.0F, 0.0F, 0.0F);
/*  66 */     setRotateAngle(this.leftWing2, 0.0F, 0.5235988F, 0.0F);
/*  67 */     this.body.addChild(this.body3);
/*  68 */     this.body.addChild(this.leftWing1);
/*  69 */     this.body.addChild(this.rightWing1);
/*  70 */     this.rightWing1.addChild(this.rightWing2);
/*  71 */     this.body.addChild(this.body2);
/*  72 */     this.body.addChild(this.body4);
/*  73 */     this.body.addChild(this.rightear);
/*  74 */     this.body.addChild(this.leftear);
/*  75 */     this.leftWing1.addChild(this.leftWing2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/*  81 */     this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent) {
/*  92 */     double[] animationWingMovement = { 25.0D, 30.0D, 35.0D, 40.0D, 45.0D, 50.0D, 55.0D, 50.0D, 45.0D, 40.0D, 35.0D, 30.0D, 25.0D, 20.0D, 15.0D, 10.0D, 5.0D, 0.0D, -5.0D, -10.0D, -15.0D, -10.0D, -5.0D, 0.0D, 5.0D, 10.0D, 15.0D, 20.0D, 25.0D };
/*     */     
/*  94 */     int cycleIndexFly = (int)(ent.ticksExisted * 1.75D % animationWingMovement.length);
/*     */     
/*  96 */     if (!Minecraft.getInstance().isGamePaused()) {
/*     */       
/*  98 */       this.rightWing1.rotateAngleZ = degToRad(animationWingMovement[cycleIndexFly]);
/*  99 */       this.leftWing1.rotateAngleZ = degToRad(animationWingMovement[cycleIndexFly]) * -1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float degToRad(double degrees) {
/* 105 */     return (float)(degrees * Math.PI / 180.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 110 */     model.rotateAngleX = x;
/* 111 */     model.rotateAngleY = y;
/* 112 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\BrickBatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */