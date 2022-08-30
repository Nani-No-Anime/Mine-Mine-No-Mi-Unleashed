/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class PteranodonAssaultPartialModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> {
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer leftWing2;
/*     */   private final ModelRenderer leftWingTip;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer rightWing2;
/*     */   private final ModelRenderer rightWingTip;
/*     */   private final ModelRenderer headPiece;
/*     */   
/*     */   public PteranodonAssaultPartialModel() {
/*  27 */     super(1.0F);
/*  28 */     this.textureWidth = 64;
/*  29 */     this.textureHeight = 64;
/*     */     
/*  31 */     this.leftWing = new ModelRenderer((Model)this);
/*  32 */     this.leftWing.setRotationPoint(3.8557F, 1.6409F, -1.0F);
/*  33 */     setRotationAngle(this.leftWing, -0.2967F, -0.0262F, -0.0873F);
/*  34 */     this.leftWing.setTextureOffset(24, 0).addBox(-0.0557F, -1.5F, -5.0F, 1.0F, 18.0F, 10.0F, 0.0F, true);
/*     */     
/*  36 */     this.leftWingTip = new ModelRenderer((Model)this);
/*  37 */     this.leftWingTip.setRotationPoint(1.0F, 15.75F, -4.5F);
/*  38 */     this.leftWing.addChild(this.leftWingTip);
/*  39 */     setRotationAngle(this.leftWingTip, -1.2217F, 0.0F, 0.0F);
/*  40 */     this.leftWingTip.setTextureOffset(4, 7).addBox(-0.8057F, -0.192F, -0.3995F, 1.0F, 2.0F, 1.0F, 0.0F, true);
/*     */     
/*  42 */     this.leftWing2 = new ModelRenderer((Model)this);
/*  43 */     this.leftWing2.setRotationPoint(1.25F, 16.25F, -4.5F);
/*  44 */     this.leftWing.addChild(this.leftWing2);
/*  45 */     setRotationAngle(this.leftWing2, -0.6977F, 0.028F, 0.0334F);
/*  46 */     this.leftWing2.setTextureOffset(0, 0).addBox(-0.8057F, -20.6626F, -0.2402F, 1.0F, 21.0F, 10.0F, 0.0F, true);
/*     */     
/*  48 */     this.rightWing = new ModelRenderer((Model)this);
/*  49 */     this.rightWing.setRotationPoint(-3.8943F, 1.6409F, -1.0F);
/*  50 */     setRotationAngle(this.rightWing, -0.2967F, -0.0262F, 0.0873F);
/*  51 */     this.rightWing.setTextureOffset(24, 0).addBox(-0.9557F, -1.5F, -5.0F, 1.0F, 18.0F, 10.0F, 0.0F, false);
/*     */     
/*  53 */     this.rightWingTip = new ModelRenderer((Model)this);
/*  54 */     this.rightWingTip.setRotationPoint(-1.0F, 15.75F, -4.5F);
/*  55 */     this.rightWing.addChild(this.rightWingTip);
/*  56 */     setRotationAngle(this.rightWingTip, -1.2217F, 0.0F, 0.0F);
/*  57 */     this.rightWingTip.setTextureOffset(0, 7).addBox(-0.2057F, -0.192F, -0.3995F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  59 */     this.rightWing2 = new ModelRenderer((Model)this);
/*  60 */     this.rightWing2.setRotationPoint(-1.75F, 16.25F, -4.5F);
/*  61 */     this.rightWing.addChild(this.rightWing2);
/*  62 */     setRotationAngle(this.rightWing2, -0.6977F, -0.028F, -0.0334F);
/*  63 */     this.rightWing2.setTextureOffset(0, 0).addBox(0.2943F, -20.6626F, -0.2402F, 1.0F, 21.0F, 10.0F, 0.0F, false);
/*     */     
/*  65 */     this.headPiece = new ModelRenderer((Model)this);
/*  66 */     this.headPiece.setRotationPoint(0.0F, 0.75F, 0.0F);
/*  67 */     setRotationAngle(this.headPiece, -0.8727F, 0.0F, 0.0F);
/*  68 */     this.headPiece.setTextureOffset(0, 0).addBox(-1.0F, -8.4532F, -8.5142F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*  69 */     this.headPiece.setTextureOffset(12, 0).addBox(-1.0F, -11.4532F, -8.5142F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  75 */     this.headPiece.copyModelAngles(this.bipedHead);
/*  76 */     this.bipedHead.rotateAngleX += (float)Math.toRadians(-40.0D);
/*  77 */     this.leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
/*  78 */     this.rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
/*  79 */     this.headPiece.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  85 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/*  87 */     if (!((LivingEntity)entity).onGround) {
/*     */       
/*  89 */       this.rightWing2.rotateAngleX = (float)Math.toRadians(190.0D);
/*  90 */       this.rightWing2.rotationPointZ = 4.5F;
/*  91 */       this.rightWing2.rotationPointY = 15.0F;
/*  92 */       this.rightWing2.rotationPointX = -1.5F;
/*     */       
/*  94 */       this.leftWing2.rotateAngleX = (float)Math.toRadians(190.0D);
/*  95 */       this.leftWing2.rotationPointZ = 4.5F;
/*  96 */       this.leftWing2.rotationPointY = 15.0F;
/*  97 */       this.leftWing2.rotationPointX = 0.8F;
/*     */       
/*  99 */       this.rightWing.rotateAngleY += 0.9F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI));
/* 100 */       this.rightWing.rotateAngleZ += 1.3F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI));
/* 101 */       this.rightWing2.rotateAngleZ += MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI)) / 3.0F;
/*     */       
/* 103 */       this.leftWing.rotateAngleY -= 0.9F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI));
/* 104 */       this.leftWing.rotateAngleZ -= 1.3F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI));
/* 105 */       this.leftWing2.rotateAngleZ -= MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI)) / 3.0F;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 110 */       float f = 1.0F;
/* 111 */       this.rightWing.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount / f;
/* 112 */       this.leftWing.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount / f;
/*     */ 
/*     */       
/* 115 */       this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 116 */       boolean isBlackLeg = (EntityStatsCapability.get((LivingEntity)entity).isBlackLeg() && entity.getHeldItemMainhand().isEmpty());
/* 117 */       if (this.swingProgress > 0.0F && !isBlackLeg) {
/*     */         
/* 119 */         this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 120 */         this.rightWing.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 121 */         this.rightWing.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 122 */         this.leftWing.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 123 */         this.leftWing.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 124 */         this.rightWing.rotateAngleY += this.bipedBody.rotateAngleY;
/* 125 */         this.leftWing.rotateAngleY += this.bipedBody.rotateAngleY;
/* 126 */         this.leftWing.rotateAngleX += this.bipedBody.rotateAngleY;
/* 127 */         float f1 = 1.0F - this.swingProgress;
/* 128 */         f1 *= f1;
/* 129 */         f1 *= f1;
/* 130 */         f1 = 1.0F - f1;
/* 131 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 132 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 133 */         this.rightWing.rotateAngleX = (float)(this.rightWing.rotateAngleX - f2 * 1.5D + f3);
/* 134 */         this.rightWing.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 135 */         this.rightWing.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderItemInHand(T entity, HandSide side, MatrixStack matrixStack) {
/* 152 */     if (entity instanceof PlayerEntity)
/*     */     {
/* 154 */       if (!((PlayerEntity)entity).abilities.isFlying)
/*     */       {
/* 156 */         return false;
/*     */       }
/*     */     }
/* 159 */     this.bipedRightLeg.translateRotate(matrixStack);
/* 160 */     matrixStack.rotate(Vector3f.YP.rotationDegrees(-40.0F));
/* 161 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(-30.0F));
/* 162 */     matrixStack.translate(-0.12D, -0.05D, 0.3D);
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 168 */     modelRenderer.rotateAngleX = x;
/* 169 */     modelRenderer.rotateAngleY = y;
/* 170 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\PteranodonAssaultPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */