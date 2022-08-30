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
/*     */ public class HitoDaibutsuModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftLeg;
/*     */   
/*     */   public HitoDaibutsuModel() {
/*  27 */     super(1.0F);
/*  28 */     this.textureWidth = 64;
/*  29 */     this.textureHeight = 64;
/*     */     
/*  31 */     this.rightArm = new ModelRenderer((Model)this);
/*  32 */     this.rightArm.setRotationPoint(-9.5F, -2.0F, 0.0F);
/*  33 */     this.rightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  35 */     this.rightLeg = new ModelRenderer((Model)this);
/*  36 */     this.rightLeg.setRotationPoint(-3.6F, 15.0F, 0.0F);
/*  37 */     this.rightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  39 */     this.head = new ModelRenderer((Model)this);
/*  40 */     this.head.setRotationPoint(0.0F, -5.4F, 0.0F);
/*  41 */     this.head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
/*     */     
/*  43 */     this.body = new ModelRenderer((Model)this);
/*  44 */     this.body.setRotationPoint(0.0F, -5.4F, 0.0F);
/*  45 */     this.body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  47 */     this.leftArm = new ModelRenderer((Model)this);
/*  48 */     this.leftArm.setRotationPoint(9.5F, -2.0F, 0.0F);
/*  49 */     this.leftArm.setTextureOffset(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  51 */     this.leftLeg = new ModelRenderer((Model)this);
/*  52 */     this.leftLeg.setRotationPoint(3.2F, 15.0F, 0.0F);
/*  53 */     this.leftLeg.setTextureOffset(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  55 */     this.bipedBody = this.body;
/*  56 */     this.bipedHead = this.head;
/*  57 */     this.bipedRightArm = this.rightArm;
/*  58 */     this.bipedLeftArm = this.leftArm;
/*  59 */     this.bipedRightLeg = this.rightLeg;
/*  60 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  66 */     float scale = 4.5F;
/*  67 */     matrixStack.push();
/*  68 */     matrixStack.scale(scale, scale, scale);
/*  69 */     matrixStack.translate(0.0D, (scale != 4.5D) ? -1.0D : -0.8D, 0.0D);
/*     */     
/*  71 */     matrixStack.push();
/*  72 */     matrixStack.scale(1.1F, 1.15F, 1.15F);
/*  73 */     matrixStack.translate(0.0D, -0.25D, 0.0D);
/*  74 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  75 */     matrixStack.pop();
/*     */     
/*  77 */     matrixStack.push();
/*  78 */     matrixStack.scale(2.5F, 2.0F, 4.0F);
/*  79 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  80 */     matrixStack.pop();
/*     */     
/*  82 */     matrixStack.push();
/*  83 */     matrixStack.scale(1.75F, 1.75F, 1.75F);
/*  84 */     matrixStack.translate(0.2D, -0.1D, 0.0D);
/*  85 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  86 */     matrixStack.pop();
/*     */     
/*  88 */     matrixStack.push();
/*  89 */     matrixStack.scale(1.75F, 1.75F, 1.75F);
/*  90 */     matrixStack.translate(-0.2D, -0.1D, 0.0D);
/*  91 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  92 */     matrixStack.pop();
/*     */     
/*  94 */     matrixStack.push();
/*  95 */     matrixStack.scale(1.75F, 1.35F, 1.75F);
/*  96 */     matrixStack.translate(0.05D, -0.4D, 0.0D);
/*  97 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  98 */     matrixStack.pop();
/*     */     
/* 100 */     matrixStack.push();
/* 101 */     matrixStack.scale(1.75F, 1.35F, 1.75F);
/* 102 */     matrixStack.translate(-0.05D, -0.4D, 0.0D);
/* 103 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 104 */     matrixStack.pop();
/* 105 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 112 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/* 113 */     boolean flag1 = entity.isActualySwimming();
/* 114 */     this.head.rotateAngleY = netHeadYaw * 0.017453292F;
/* 115 */     if (flag) {
/* 116 */       this.head.rotateAngleX = -0.7853982F;
/* 117 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/* 119 */       if (flag1) {
/* 120 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 122 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 126 */       this.head.rotateAngleX = headPitch * 0.017453292F;
/* 127 */       if (this.head.rotateAngleX > 0.6D) {
/* 128 */         this.head.rotateAngleX = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 132 */     float f = 1.0F;
/* 133 */     this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 134 */     this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 135 */     this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 136 */     this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 137 */     if (!entity.getHeldItemMainhand().isEmpty()) {
/* 138 */       this.rightArm.rotateAngleX += -0.15F;
/*     */     }
/*     */     
/* 141 */     if (entity.isSneaking()) {
/*     */       
/* 143 */       this.body.rotateAngleX = 0.2F;
/* 144 */       this.body.rotationPointZ -= 2.0F;
/* 145 */       this.body.rotationPointY -= 0.2F;
/* 146 */       this.rightArm.rotateAngleX += 0.3F;
/* 147 */       this.rightArm.rotationPointZ -= 3.5F;
/* 148 */       this.rightArm.rotationPointY += 1.2F;
/* 149 */       this.leftArm.rotateAngleX += 0.3F;
/* 150 */       this.leftArm.rotationPointZ -= 3.5F;
/* 151 */       this.leftArm.rotationPointY += 1.2F;
/* 152 */       this.rightLeg.rotationPointZ = 1.0F;
/* 153 */       this.rightLeg.rotationPointY = 14.0F;
/* 154 */       this.leftLeg.rotationPointZ = 1.0F;
/* 155 */       this.leftLeg.rotationPointY = 14.0F;
/* 156 */       this.head.rotationPointZ = -6.0F;
/* 157 */       this.head.rotationPointY = -5.0F;
/*     */     } 
/*     */ 
/*     */     
/* 161 */     this.swingProgress = entity.swingProgress;
/* 162 */     boolean isBlackLeg = EntityStatsCapability.get(entity).isBlackLeg();
/* 163 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 165 */       if (isBlackLeg) {
/*     */         
/* 167 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 168 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY;
/* 169 */         this.leftLeg.rotateAngleY += this.body.rotateAngleY;
/* 170 */         float f1 = 1.0F - this.swingProgress;
/* 171 */         f1 *= f1;
/* 172 */         f1 *= f1;
/* 173 */         f1 = 1.0F - f1;
/* 174 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 175 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 176 */         this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 177 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 181 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 182 */         this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 12.0F;
/* 183 */         this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 9.0F;
/* 184 */         this.rightArm.rotateAngleY += this.body.rotateAngleY;
/* 185 */         this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
/* 186 */         this.leftArm.rotateAngleY -= this.body.rotateAngleY;
/* 187 */         this.leftArm.rotateAngleX -= this.body.rotateAngleY;
/* 188 */         float f1 = 1.0F - this.swingProgress;
/* 189 */         f1 *= f1;
/* 190 */         f1 *= f1;
/* 191 */         f1 = 1.0F - f1;
/* 192 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 193 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 194 */         this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
/* 195 */         this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
/* 196 */         this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 203 */     model.rotateAngleX = x;
/* 204 */     model.rotateAngleY = y;
/* 205 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 211 */     if (side == HandSide.RIGHT) {
/*     */       
/* 213 */       matrixStack.translate(0.2D, 0.3D, 0.0D);
/* 214 */       this.rightArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 218 */       matrixStack.translate(-0.2D, 0.3D, 0.0D);
/* 219 */       this.leftArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 226 */     if (side == HandSide.RIGHT) {
/*     */       
/* 228 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 229 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 230 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 231 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 235 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 236 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 237 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 238 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 245 */     super.translateHand(side, matrixStack);
/* 246 */     matrixStack.translate((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\HitoDaibutsuModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */