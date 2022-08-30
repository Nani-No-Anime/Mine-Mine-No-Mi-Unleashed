/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class YomiModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   public ModelRenderer head;
/*     */   public ModelRenderer body;
/*     */   public ModelRenderer rightArm;
/*     */   public ModelRenderer leftArm;
/*     */   public ModelRenderer rightLeg;
/*     */   public ModelRenderer leftLeg;
/*     */   public ModelRenderer afro;
/*     */   
/*     */   public YomiModel() {
/*  28 */     super(1.0F);
/*  29 */     this.textureWidth = 64;
/*  30 */     this.textureHeight = 64;
/*     */     
/*  32 */     this.head = new ModelRenderer((Model)this, 0, 0);
/*  33 */     this.head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
/*  34 */     this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  35 */     this.body = new ModelRenderer((Model)this, 16, 16);
/*  36 */     this.body.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F);
/*  37 */     this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  38 */     this.rightArm = new ModelRenderer((Model)this, 40, 16);
/*  39 */     this.rightArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
/*  40 */     this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/*  41 */     this.leftArm = new ModelRenderer((Model)this, 40, 16);
/*  42 */     this.leftArm.mirror = true;
/*  43 */     this.leftArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
/*  44 */     this.leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/*  45 */     this.rightLeg = new ModelRenderer((Model)this, 0, 16);
/*  46 */     this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
/*  47 */     this.rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/*  48 */     this.leftLeg = new ModelRenderer((Model)this, 0, 16);
/*  49 */     this.leftLeg.mirror = true;
/*  50 */     this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
/*  51 */     this.leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/*     */     
/*  53 */     this.bipedBody = this.body;
/*  54 */     this.bipedHead = this.head;
/*  55 */     this.bipedRightArm = this.rightArm;
/*  56 */     this.bipedLeftArm = this.leftArm;
/*  57 */     this.bipedRightLeg = this.rightLeg;
/*  58 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  64 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  65 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  66 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  67 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  68 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  69 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  76 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/*  77 */     boolean flag1 = entity.isActualySwimming();
/*  78 */     this.head.rotateAngleY = netHeadYaw * 0.017453292F;
/*  79 */     if (flag) {
/*  80 */       this.head.rotateAngleX = -0.7853982F;
/*  81 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/*  83 */       if (flag1) {
/*  84 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/*  86 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/*  90 */       this.head.rotateAngleX = headPitch * 0.017453292F;
/*  91 */       if (this.head.rotateAngleX > 0.6D) {
/*  92 */         this.head.rotateAngleX = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/*  96 */     float f = 1.0F;
/*  97 */     this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/*  98 */     this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/*  99 */     this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 100 */     this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 101 */     if (!entity.getHeldItemMainhand().isEmpty()) {
/* 102 */       this.rightArm.rotateAngleX += -0.15F;
/*     */     }
/*     */     
/* 105 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 106 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 107 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 109 */       if (isBlackLeg) {
/*     */         
/* 111 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 112 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY;
/* 113 */         this.leftLeg.rotateAngleY += this.body.rotateAngleY;
/* 114 */         float f1 = 1.0F - this.swingProgress;
/* 115 */         f1 *= f1;
/* 116 */         f1 *= f1;
/* 117 */         f1 = 1.0F - f1;
/* 118 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 119 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 120 */         this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 121 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 125 */         this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 126 */         this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 4.0F;
/* 127 */         this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 4.0F;
/* 128 */         this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 129 */         this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 130 */         this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
/* 131 */         float f1 = 1.0F - this.swingProgress;
/* 132 */         f1 *= f1;
/* 133 */         f1 *= f1;
/* 134 */         f1 = 1.0F - f1;
/* 135 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 136 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 137 */         this.bipedRightArm.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX - f2 * 1.2D + f3);
/* 138 */         this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 139 */         this.bipedRightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */     
/* 143 */     if (entity.isSneaking()) {
/*     */       
/* 145 */       this.bipedBody.rotateAngleX = 0.5F;
/* 146 */       this.bipedBody.rotationPointZ -= 4.0F;
/* 147 */       this.bipedRightArm.rotateAngleX += 0.4F;
/* 148 */       this.bipedRightArm.rotationPointZ -= 2.5F;
/* 149 */       this.bipedLeftArm.rotateAngleX += 0.4F;
/* 150 */       this.bipedLeftArm.rotationPointZ -= 2.5F;
/* 151 */       this.bipedRightLeg.rotationPointZ = 1.0F;
/* 152 */       this.bipedRightLeg.rotationPointY = 10.0F;
/* 153 */       this.bipedLeftLeg.rotationPointZ = 1.0F;
/* 154 */       this.bipedLeftLeg.rotationPointY = 10.0F;
/* 155 */       this.bipedHead.rotationPointZ = -4.0F;
/* 156 */       this.bipedHead.rotationPointY = 1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 162 */     model.rotateAngleX = x;
/* 163 */     model.rotateAngleY = y;
/* 164 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 170 */     if (side == HandSide.RIGHT) {
/*     */       
/* 172 */       matrixStack.translate(-0.2D, 0.0D, -0.0D);
/* 173 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 174 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
/* 175 */       this.rightArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 179 */       matrixStack.translate(0.2D, 0.0D, -0.2D);
/* 180 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 181 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
/* 182 */       this.leftArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 189 */     if (side == HandSide.RIGHT) {
/*     */       
/* 191 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 192 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 193 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 194 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 198 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 199 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 200 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 201 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 208 */     super.translateHand(side, matrixStack);
/* 209 */     matrixStack.translate((side == HandSide.RIGHT) ? 0.06D : -0.06D, 0.1D, 0.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\YomiModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */