/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GomuGomuNoRocketAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class GearFourthModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftLeg;
/*     */   boolean gomuAnimations = true;
/*     */   
/*     */   public GearFourthModel(boolean gomuAnimations) {
/*  34 */     super(-0.2F);
/*  35 */     this.textureWidth = 64;
/*  36 */     this.textureHeight = 64;
/*  37 */     this.gomuAnimations = gomuAnimations;
/*     */     
/*  39 */     this.rightArm = new ModelRenderer((Model)this);
/*  40 */     this.rightArm.setRotationPoint(-9.5F, -2.0F, 0.0F);
/*  41 */     this.rightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  43 */     this.rightLeg = new ModelRenderer((Model)this);
/*  44 */     this.rightLeg.setRotationPoint(-3.6F, 15.0F, 0.0F);
/*  45 */     this.rightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  47 */     this.head = new ModelRenderer((Model)this);
/*  48 */     this.head.setRotationPoint(0.0F, -5.4F, 0.0F);
/*  49 */     this.head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
/*     */     
/*  51 */     this.body = new ModelRenderer((Model)this);
/*  52 */     this.body.setRotationPoint(0.0F, -5.4F, 0.0F);
/*  53 */     this.body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  55 */     this.leftArm = new ModelRenderer((Model)this);
/*  56 */     this.leftArm.setRotationPoint(9.5F, -2.0F, 0.0F);
/*  57 */     this.leftArm.setTextureOffset(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  59 */     this.leftLeg = new ModelRenderer((Model)this);
/*  60 */     this.leftLeg.setRotationPoint(3.2F, 15.0F, 0.0F);
/*  61 */     this.leftLeg.setTextureOffset(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  63 */     this.bipedBody = this.body;
/*  64 */     this.bipedHead = this.head;
/*  65 */     this.bipedRightArm = this.rightArm;
/*  66 */     this.bipedLeftArm = this.leftArm;
/*  67 */     this.bipedRightLeg = this.rightLeg;
/*  68 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  74 */     float scale = 1.5F;
/*  75 */     matrixStack.push();
/*  76 */     matrixStack.scale(scale, scale, scale);
/*  77 */     matrixStack.translate(0.0D, -0.8D, 0.0D);
/*     */     
/*  79 */     matrixStack.push();
/*  80 */     matrixStack.translate(0.0D, -0.21D, 0.0D);
/*  81 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*  82 */     if (this.gomuAnimations) {
/*     */       
/*  84 */       this.bipedHeadwear.copyModelAngles(this.head);
/*  85 */       this.bipedHeadwear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */     } 
/*  87 */     matrixStack.pop();
/*     */     
/*  89 */     matrixStack.push();
/*  90 */     matrixStack.scale(2.0F, 1.7F, 3.0F);
/*  91 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     matrixStack.pop();
/*     */     
/*  99 */     if (this.gomuAnimations) {
/*     */       
/* 101 */       float time = 0.2F;
/* 102 */       GomuGomuNoRocketAbility ability = (GomuGomuNoRocketAbility)AbilityDataCapability.get((LivingEntity)(Minecraft.getInstance()).player).getEquippedAbility((Ability)GomuGomuNoRocketAbility.INSTANCE);
/* 103 */       if (ability != null && ability.isContinuous()) {
/*     */         
/* 105 */         time = 0.2F + ability.getContinueTime() / ability.getThreshold() * 0.8F;
/* 106 */         if (Float.isNaN(time)) {
/* 107 */           time = 0.2F;
/*     */         }
/*     */       } 
/* 110 */       matrixStack.push();
/* 111 */       matrixStack.scale(1.75F * 5.0F * time, 1.75F * 5.0F * time, 1.75F * 5.0F * time);
/* 112 */       matrixStack.translate((0.25F + 0.26F * time), -0.07D + 0.28D * time, 0.0D);
/*     */     }
/*     */     else {
/*     */       
/* 116 */       matrixStack.push();
/* 117 */       matrixStack.scale(1.75F, 1.75F, 1.75F);
/* 118 */       matrixStack.translate(0.25D, -0.07D, 0.0D);
/*     */     } 
/*     */     
/* 121 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     matrixStack.pop();
/*     */     
/* 129 */     matrixStack.push();
/* 130 */     matrixStack.scale(1.75F, 1.75F, 1.75F);
/* 131 */     matrixStack.translate(-0.25D, -0.07D, 0.0D);
/* 132 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     matrixStack.pop();
/*     */     
/* 140 */     matrixStack.push();
/* 141 */     matrixStack.translate(0.05D, -0.4D, 0.0D);
/* 142 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 143 */     matrixStack.pop();
/*     */     
/* 145 */     matrixStack.push();
/* 146 */     matrixStack.translate(-0.05D, -0.4D, 0.0D);
/* 147 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 148 */     matrixStack.pop();
/* 149 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 155 */     double x = entity.prevPosX - entity.getPosX();
/* 156 */     double z = entity.prevPosZ - entity.getPosZ();
/* 157 */     boolean isMoving = (x != 0.0D || z != 0.0D);
/* 158 */     BlockPos pos = entity.getPosition().down(2);
/* 159 */     boolean isInAir = (entity.world.getBlockState(pos).getMaterial() == Material.AIR);
/* 160 */     boolean isFlying = (isMoving && isInAir);
/*     */     
/* 162 */     if (this.gomuAnimations) {
/*     */ 
/*     */       
/* 165 */       if (isFlying) {
/*     */         
/* 167 */         this.rightArm.rotateAngleZ = (float)Math.toRadians(90.0D);
/* 168 */         this.leftArm.rotateAngleZ = (float)Math.toRadians(-90.0D);
/*     */       } 
/*     */ 
/*     */       
/* 172 */       boolean flag = (entity.getTicksElytraFlying() > 4);
/* 173 */       boolean flag1 = entity.isActualySwimming();
/* 174 */       this.head.rotateAngleY = netHeadYaw * 0.017453292F;
/* 175 */       if (flag) {
/* 176 */         this.head.rotateAngleX = -0.7853982F;
/* 177 */       } else if (this.swimAnimation > 0.0F) {
/*     */         
/* 179 */         if (flag1) {
/* 180 */           this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */         } else {
/* 182 */           this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */         } 
/*     */       } else {
/*     */         
/* 186 */         this.head.rotateAngleX = headPitch * 0.017453292F;
/* 187 */         if (this.head.rotateAngleX > 0.6D) {
/* 188 */           this.head.rotateAngleX = 0.6F;
/*     */         }
/*     */       } 
/*     */       
/* 192 */       float f = 1.0F;
/* 193 */       if (!isFlying) {
/*     */         
/* 195 */         this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 196 */         this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/*     */       } 
/* 198 */       float speed = 0.4F;
/* 199 */       if (entity.isSprinting())
/* 200 */         speed = 0.7F; 
/* 201 */       this.rightLeg.rotationPointY += -2.0F + MathHelper.cos(ageInTicks * speed) * 2.0F;
/* 202 */       this.leftLeg.rotationPointY += -2.0F + MathHelper.cos(ageInTicks * speed) * 2.0F;
/* 203 */       if (!entity.getHeldItemMainhand().isEmpty()) {
/* 204 */         this.rightArm.rotateAngleX += -0.15F;
/*     */       }
/*     */       
/* 207 */       this.swingProgress = entity.swingProgress;
/* 208 */       boolean isBlackLeg = EntityStatsCapability.get(entity).isBlackLeg();
/* 209 */       if (this.swingProgress > 0.0F) {
/*     */         
/* 211 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 212 */         if (isBlackLeg) {
/*     */           
/* 214 */           this.rightLeg.rotateAngleY += this.body.rotateAngleY;
/* 215 */           this.leftLeg.rotateAngleY += this.body.rotateAngleY;
/* 216 */           float f1 = 1.0F - this.swingProgress;
/* 217 */           f1 *= f1;
/* 218 */           f1 *= f1;
/* 219 */           f1 = 1.0F - f1;
/* 220 */           float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 221 */           float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 222 */           this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 223 */           this.rightLeg.rotateAngleY += this.body.rotateAngleY * 2.0F;
/*     */         }
/*     */         else {
/*     */           
/* 227 */           this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 12.0F;
/* 228 */           this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 9.0F;
/* 229 */           this.rightArm.rotateAngleY += this.body.rotateAngleY;
/* 230 */           this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
/* 231 */           this.leftArm.rotateAngleY -= this.body.rotateAngleY;
/* 232 */           this.leftArm.rotateAngleX -= this.body.rotateAngleY;
/* 233 */           float f1 = 1.0F - this.swingProgress;
/* 234 */           f1 *= f1;
/* 235 */           f1 *= f1;
/* 236 */           f1 = 1.0F - f1;
/* 237 */           float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 238 */           float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 239 */           this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
/* 240 */           this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
/* 241 */           this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 246 */       if (!isMoving)
/*     */       {
/* 248 */         this.rightArm.rotateAngleX = (float)Math.toRadians(-90.0D);
/* 249 */         this.leftArm.rotateAngleX = (float)Math.toRadians(-90.0D);
/* 250 */         this.leftArm.rotateAngleZ = (float)Math.toRadians(10.0D);
/* 251 */         this.leftArm.rotateAngleY = (float)Math.toRadians(-5.0D);
/* 252 */         this.leftArm.rotationPointZ += 4.0F;
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 258 */     else if (isFlying && entity.isSprinting()) {
/*     */       
/* 260 */       this.rightArm.rotateAngleZ = (float)Math.toRadians(90.0D);
/* 261 */       this.leftArm.rotateAngleZ = (float)Math.toRadians(-90.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 270 */     model.rotateAngleX = x;
/* 271 */     model.rotateAngleY = y;
/* 272 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 278 */     if (side == HandSide.RIGHT) {
/*     */       
/* 280 */       matrixStack.translate(0.2D, 0.3D, 0.0D);
/* 281 */       this.rightArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 0.7F, 0.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 285 */       matrixStack.translate(-0.2D, 0.3D, 0.0D);
/* 286 */       this.leftArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 0.7F, 0.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 293 */     if (side == HandSide.RIGHT) {
/*     */       
/* 295 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 296 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 297 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 298 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 302 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 303 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 304 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 305 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 312 */     super.translateHand(side, matrixStack);
/* 313 */     matrixStack.translate((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\GearFourthModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */