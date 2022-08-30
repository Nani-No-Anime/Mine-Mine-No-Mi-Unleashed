/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class LeopardHeavyModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T>
/*     */   implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer snout2;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer waist;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer tail4;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftArm3;
/*     */   private final ModelRenderer leftHand;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightArm3;
/*     */   private final ModelRenderer rightHand;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftFoot;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightLeg4;
/*     */   
/*     */   public LeopardHeavyModel() {
/*  51 */     super(1.0F);
/*  52 */     this.textureWidth = 128;
/*  53 */     this.textureHeight = 128;
/*     */     
/*  55 */     this.head = new ModelRenderer((Model)this);
/*  56 */     this.head.setRotationPoint(0.0F, -14.25F, -1.6F);
/*  57 */     this.head.setTextureOffset(0, 71).addBox(-3.5F, -6.25F, -6.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
/*     */     
/*  59 */     this.snout = new ModelRenderer((Model)this);
/*  60 */     this.snout.setRotationPoint(0.0F, -0.95F, -6.7F);
/*  61 */     this.head.addChild(this.snout);
/*  62 */     this.snout.setTextureOffset(10, 86).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/*  64 */     this.snout2 = new ModelRenderer((Model)this);
/*  65 */     this.snout2.setRotationPoint(0.0F, -8.15F, 5.9F);
/*  66 */     this.snout.addChild(this.snout2);
/*  67 */     setRotationAngle(this.snout2, 0.1955F, 0.0F, 0.0F);
/*  68 */     this.snout2.setTextureOffset(10, 91).addBox(-1.5F, 5.6073F, -8.0548F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/*  70 */     this.rightEar = new ModelRenderer((Model)this);
/*  71 */     this.rightEar.setRotationPoint(-2.85F, -13.5F, 1.15F);
/*  72 */     this.head.addChild(this.rightEar);
/*  73 */     setRotationAngle(this.rightEar, 0.0873F, -0.2618F, -0.4363F);
/*  74 */     this.rightEar.setTextureOffset(1, 86).addBox(-5.0148F, 4.4168F, -3.0083F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  76 */     this.leftEar = new ModelRenderer((Model)this);
/*  77 */     this.leftEar.setRotationPoint(1.9F, -14.0F, 1.4F);
/*  78 */     this.head.addChild(this.leftEar);
/*  79 */     setRotationAngle(this.leftEar, 0.0873F, 0.2618F, 0.4363F);
/*  80 */     this.leftEar.setTextureOffset(1, 86).addBox(3.0148F, 4.4168F, -3.0083F, 3.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/*  82 */     this.body = new ModelRenderer((Model)this);
/*  83 */     this.body.setRotationPoint(0.5F, -9.9F, 4.9F);
/*  84 */     this.body.setTextureOffset(0, 27).addBox(-6.9F, 5.25F, -5.5F, 12.0F, 6.0F, 7.0F, -0.01F, false);
/*     */     
/*  86 */     this.body2 = new ModelRenderer((Model)this);
/*  87 */     this.body2.setRotationPoint(1.1F, -7.7F, -0.6F);
/*  88 */     this.body.addChild(this.body2);
/*  89 */     this.body2.setTextureOffset(0, 0).addBox(-10.2F, 4.65F, -5.3F, 16.0F, 7.0F, 7.0F, 0.0F, false);
/*     */     
/*  91 */     this.body3 = new ModelRenderer((Model)this);
/*  92 */     this.body3.setRotationPoint(0.8F, -2.1F, 0.0F);
/*  93 */     this.body.addChild(this.body3);
/*  94 */     this.body3.setTextureOffset(0, 14).addBox(-9.5F, 5.25F, -5.5F, 15.0F, 6.0F, 7.0F, 0.0F, false);
/*     */     
/*  96 */     this.waist = new ModelRenderer((Model)this);
/*  97 */     this.waist.setRotationPoint(-0.5F, 4.5F, 0.3F);
/*  98 */     this.body.addChild(this.waist);
/*  99 */     this.waist.setTextureOffset(0, 41).addBox(-6.4F, 5.85F, -5.3F, 12.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/* 101 */     this.neck = new ModelRenderer((Model)this);
/* 102 */     this.neck.setRotationPoint(-0.5F, -11.9F, -3.7F);
/* 103 */     this.body.addChild(this.neck);
/* 104 */     setRotationAngle(this.neck, 0.8601F, 0.0F, 0.0F);
/* 105 */     this.neck.setTextureOffset(0, 53).addBox(-2.5F, 0.2372F, -10.3836F, 5.0F, 10.0F, 7.0F, 0.0F, false);
/*     */     
/* 107 */     this.tail = new ModelRenderer((Model)this);
/* 108 */     this.tail.setRotationPoint(-0.75F, 14.2F, -0.45F);
/* 109 */     this.body.addChild(this.tail);
/* 110 */     setRotationAngle(this.tail, 0.6632F, 0.0F, 0.0F);
/* 111 */     this.tail.setTextureOffset(39, 49).addBox(-0.75F, -0.0952F, -0.5599F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/* 113 */     this.tail2 = new ModelRenderer((Model)this);
/* 114 */     this.tail2.setRotationPoint(-1.0F, 0.1429F, 7.4393F);
/* 115 */     this.tail.addChild(this.tail2);
/* 116 */     setRotationAngle(this.tail2, 0.2793F, 0.0F, 0.0F);
/* 117 */     this.tail2.setTextureOffset(39, 57).addBox(0.25F, 2.2267F, -8.9439F, 2.0F, 5.0F, 2.0F, 0.01F, false);
/*     */     
/* 119 */     this.tail3 = new ModelRenderer((Model)this);
/* 120 */     this.tail3.setRotationPoint(0.0F, 4.4F, 0.15F);
/* 121 */     this.tail2.addChild(this.tail3);
/* 122 */     setRotationAngle(this.tail3, 0.6283F, 0.0F, 0.0F);
/* 123 */     this.tail3.setTextureOffset(39, 65).addBox(0.25F, -3.1F, -9.05F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 125 */     this.tail4 = new ModelRenderer((Model)this);
/* 126 */     this.tail4.setRotationPoint(0.0F, 5.65F, 0.15F);
/* 127 */     this.tail3.addChild(this.tail4);
/* 128 */     setRotationAngle(this.tail4, 0.4014F, 0.0F, 0.0F);
/* 129 */     this.tail4.setTextureOffset(39, 74).addBox(0.25F, -6.1248F, -7.4173F, 2.0F, 6.0F, 2.0F, 0.01F, false);
/*     */     
/* 131 */     this.leftArm = new ModelRenderer((Model)this);
/* 132 */     this.leftArm.setRotationPoint(6.0F, -10.75F, 3.0F);
/* 133 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.1745F);
/* 134 */     this.leftArm.setTextureOffset(46, 0).addBox(0.6299F, -2.2111F, -3.0126F, 6.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/* 136 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 137 */     this.leftArm2.setRotationPoint(4.9625F, -2.3633F, 3.35F);
/* 138 */     this.leftArm.addChild(this.leftArm2);
/* 139 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0436F);
/* 140 */     this.leftArm2.setTextureOffset(46, 15).addBox(-3.4768F, 6.7794F, -5.75F, 5.0F, 8.0F, 5.0F, 0.0F, false);
/*     */     
/* 142 */     this.leftArm3 = new ModelRenderer((Model)this);
/* 143 */     this.leftArm3.setRotationPoint(-0.6F, 6.8F, -0.35F);
/* 144 */     this.leftArm2.addChild(this.leftArm3);
/* 145 */     setRotationAngle(this.leftArm3, 0.0F, 0.0F, 0.0873F);
/* 146 */     this.leftArm3.setTextureOffset(46, 29).addBox(-1.8599F, 7.8421F, -4.75F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 148 */     this.leftHand = new ModelRenderer((Model)this);
/* 149 */     this.leftHand.setRotationPoint(0.2901F, 14.4405F, -2.7616F);
/* 150 */     this.leftArm3.addChild(this.leftHand);
/* 151 */     setRotationAngle(this.leftHand, 0.0F, 0.0F, 0.0873F);
/* 152 */     this.leftHand.setTextureOffset(46, 39).addBox(-1.1526F, -2.1272F, -1.9884F, 2.0F, 4.0F, 4.0F, -0.01F, false);
/*     */     
/* 154 */     this.rightArm = new ModelRenderer((Model)this);
/* 155 */     this.rightArm.setRotationPoint(-7.75F, -11.0F, 3.0F);
/* 156 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.1745F);
/* 157 */     this.rightArm.setTextureOffset(46, 0).addBox(-5.7124F, -2.154F, -3.0126F, 6.0F, 8.0F, 6.0F, 0.0F, true);
/*     */     
/* 159 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 160 */     this.rightArm2.setRotationPoint(-4.245F, -2.3062F, 3.35F);
/* 161 */     this.rightArm.addChild(this.rightArm2);
/* 162 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0436F);
/* 163 */     this.rightArm2.setTextureOffset(46, 15).addBox(-1.3232F, 6.7794F, -5.75F, 5.0F, 8.0F, 5.0F, 0.0F, true);
/*     */     
/* 165 */     this.rightArm3 = new ModelRenderer((Model)this);
/* 166 */     this.rightArm3.setRotationPoint(-0.6F, 6.8F, -0.35F);
/* 167 */     this.rightArm2.addChild(this.rightArm3);
/* 168 */     setRotationAngle(this.rightArm3, 0.0F, 0.0F, -0.0873F);
/* 169 */     this.rightArm3.setTextureOffset(46, 29).addBox(-1.1401F, 7.8421F, -4.75F, 4.0F, 5.0F, 4.0F, 0.0F, true);
/*     */     
/* 171 */     this.rightHand = new ModelRenderer((Model)this);
/* 172 */     this.rightHand.setRotationPoint(1.2099F, 14.5921F, -2.75F);
/* 173 */     this.rightArm3.addChild(this.rightHand);
/* 174 */     setRotationAngle(this.rightHand, 0.0F, 0.0F, -0.0873F);
/* 175 */     this.rightHand.setTextureOffset(46, 39).addBox(-1.1149F, -2.0859F, -2.0F, 2.0F, 4.0F, 4.0F, -0.01F, true);
/*     */     
/* 177 */     this.leftLeg = new ModelRenderer((Model)this);
/* 178 */     this.leftLeg.setRotationPoint(3.5F, 5.6F, 2.75F);
/* 179 */     setRotationAngle(this.leftLeg, -0.2618F, 0.0F, 0.0F);
/* 180 */     this.leftLeg.setTextureOffset(72, 1).addBox(-2.0F, -1.1703F, -2.5342F, 4.0F, 9.0F, 4.0F, 0.0F, false);
/*     */     
/* 182 */     this.leftLeg2 = new ModelRenderer((Model)this);
/* 183 */     this.leftLeg2.setRotationPoint(-0.05F, -0.9862F, -0.6546F);
/* 184 */     this.leftLeg.addChild(this.leftLeg2);
/* 185 */     setRotationAngle(this.leftLeg2, 0.9446F, 0.0F, 0.0F);
/* 186 */     this.leftLeg2.setTextureOffset(71, 16).addBox(-1.45F, 3.5732F, -8.1449F, 3.0F, 7.0F, 3.0F, -0.01F, false);
/*     */     
/* 188 */     this.leftLeg3 = new ModelRenderer((Model)this);
/* 189 */     this.leftLeg3.setRotationPoint(-0.9F, 4.7F, 0.5F);
/* 190 */     this.leftLeg2.addChild(this.leftLeg3);
/* 191 */     setRotationAngle(this.leftLeg3, -1.1446F, 0.0F, 0.0F);
/* 192 */     this.leftLeg3.setTextureOffset(71, 28).addBox(-0.05F, 8.4997F, 0.6879F, 2.0F, 7.0F, 2.0F, 0.0F, false);
/*     */     
/* 194 */     this.leftFoot = new ModelRenderer((Model)this);
/* 195 */     this.leftFoot.setRotationPoint(0.75F, 5.95F, 0.55F);
/* 196 */     this.leftLeg3.addChild(this.leftFoot);
/* 197 */     setRotationAngle(this.leftFoot, 0.48F, 0.0F, 0.0F);
/* 198 */     this.leftFoot.setTextureOffset(66, 38).addBox(-1.3F, 8.3533F, -8.0493F, 3.0F, 2.0F, 6.0F, 0.0F, false);
/*     */     
/* 200 */     this.rightLeg = new ModelRenderer((Model)this);
/* 201 */     this.rightLeg.setRotationPoint(-3.5F, 5.6F, 2.75F);
/* 202 */     setRotationAngle(this.rightLeg, -0.2618F, 0.0F, 0.0F);
/* 203 */     this.rightLeg.setTextureOffset(72, 1).addBox(-2.0F, -1.1703F, -2.5342F, 4.0F, 9.0F, 4.0F, 0.0F, true);
/*     */     
/* 205 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 206 */     this.rightLeg2.setRotationPoint(0.7F, -0.9862F, -0.6546F);
/* 207 */     this.rightLeg.addChild(this.rightLeg2);
/* 208 */     setRotationAngle(this.rightLeg2, 0.9446F, 0.0F, 0.0F);
/* 209 */     this.rightLeg2.setTextureOffset(71, 16).addBox(-2.2F, 3.5732F, -8.1449F, 3.0F, 7.0F, 3.0F, -0.01F, true);
/*     */     
/* 211 */     this.rightLeg3 = new ModelRenderer((Model)this);
/* 212 */     this.rightLeg3.setRotationPoint(-0.4F, 4.7F, 0.5F);
/* 213 */     this.rightLeg2.addChild(this.rightLeg3);
/* 214 */     setRotationAngle(this.rightLeg3, -1.1446F, 0.0F, 0.0F);
/* 215 */     this.rightLeg3.setTextureOffset(71, 28).addBox(-1.3F, 8.4997F, 0.6879F, 2.0F, 7.0F, 2.0F, 0.0F, true);
/*     */     
/* 217 */     this.rightLeg4 = new ModelRenderer((Model)this);
/* 218 */     this.rightLeg4.setRotationPoint(0.25F, 5.95F, 0.55F);
/* 219 */     this.rightLeg3.addChild(this.rightLeg4);
/* 220 */     setRotationAngle(this.rightLeg4, 0.48F, 0.0F, 0.0F);
/* 221 */     this.rightLeg4.setTextureOffset(66, 38).addBox(-2.05F, 8.3533F, -8.0493F, 3.0F, 2.0F, 6.0F, 0.0F, true);
/*     */     
/* 223 */     this.bipedBody = this.body;
/* 224 */     this.bipedHead = this.head;
/* 225 */     this.bipedRightArm = this.rightArm;
/* 226 */     this.bipedLeftArm = this.leftArm;
/* 227 */     this.bipedRightLeg = this.rightLeg;
/* 228 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 235 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/* 236 */     boolean flag1 = entity.isActualySwimming();
/* 237 */     this.head.rotateAngleY = netHeadYaw * 0.011635529F;
/* 238 */     if (flag) {
/* 239 */       this.head.rotateAngleX = -0.7853982F;
/* 240 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/* 242 */       if (flag1) {
/* 243 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 245 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 249 */       this.head.rotateAngleX = headPitch * 0.010471975F;
/* 250 */       if (this.head.rotateAngleX > 0.6D) {
/* 251 */         this.head.rotateAngleX = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 255 */     float f = 1.0F;
/* 256 */     this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 257 */     this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 258 */     this.rightLeg.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6F) * 0.7F * limbSwingAmount / f;
/* 259 */     this.leftLeg.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 260 */     if (!entity.getHeldItemMainhand().isEmpty()) {
/* 261 */       this.rightArm.rotateAngleX += -0.15F;
/*     */     }
/* 263 */     if (!entity.isSprinting())
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 269 */       if (!(entity instanceof xyz.pixelatedw.mineminenomi.entities.DummyEntity)) {
/*     */         
/* 271 */         this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 5.0D);
/* 272 */         this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 5.0D);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 277 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 278 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 279 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 281 */       if (isBlackLeg) {
/*     */         
/* 283 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 284 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY;
/* 285 */         this.leftLeg.rotateAngleY += this.body.rotateAngleY;
/* 286 */         float f1 = 1.0F - this.swingProgress;
/* 287 */         f1 *= f1;
/* 288 */         f1 *= f1;
/* 289 */         f1 = 1.0F - f1;
/* 290 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 291 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 292 */         this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 293 */         this.rightLeg.rotateAngleY += this.body2.rotateAngleY * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 297 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 298 */         this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
/* 299 */         this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 5.0F;
/* 300 */         this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
/* 301 */         this.leftArm.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
/* 302 */         this.rightArm.rotateAngleY += this.body.rotateAngleY;
/* 303 */         this.leftArm.rotateAngleY += this.body.rotateAngleY;
/* 304 */         this.leftArm.rotateAngleX += this.body.rotateAngleY;
/* 305 */         float f1 = 1.0F - this.swingProgress;
/* 306 */         f1 *= f1;
/* 307 */         f1 *= f1;
/* 308 */         f1 = 1.0F - f1;
/* 309 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 310 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 311 */         this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 312 */         this.rightArm.rotateAngleY += this.body2.rotateAngleY * 2.0F;
/* 313 */         this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 318 */     if (entity.isSneaking()) {
/*     */       
/* 320 */       this.body.rotateAngleX = 0.5F;
/* 321 */       this.body.rotationPointZ -= 4.0F;
/* 322 */       this.rightArm.rotateAngleX += 0.4F;
/* 323 */       this.rightArm.rotationPointZ -= 2.5F;
/* 324 */       this.leftArm.rotateAngleX += 0.4F;
/* 325 */       this.leftArm.rotationPointZ -= 2.5F;
/* 326 */       this.rightLeg.rotationPointZ = 4.0F;
/* 327 */       this.leftLeg.rotationPointZ = 4.5F;
/* 328 */       this.rightLeg.rotationPointY = 3.5F;
/* 329 */       this.leftLeg.rotationPointY = 3.5F;
/* 330 */       this.head.rotationPointY = -10.0F;
/* 331 */       this.head.rotationPointZ = -8.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 338 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 339 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 340 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 341 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 342 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 343 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 349 */     super.translateHand(side, matrixStack);
/* 350 */     matrixStack.translate(0.0D, 0.6D, 0.0D);
/* 351 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -5.0F : 5.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 357 */     if (side == HandSide.RIGHT) {
/*     */       
/* 359 */       matrixStack.translate(-0.2D, -0.6D, 0.2D);
/* 360 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 361 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
/* 362 */       this.rightArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 366 */       matrixStack.translate(0.2D, -0.6D, 0.2D);
/* 367 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 368 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
/* 369 */       this.leftArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 376 */     if (side == HandSide.RIGHT) {
/*     */       
/* 378 */       matrixStack.translate(0.1D, -0.4D, 0.2D);
/* 379 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 380 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-80.0F));
/* 381 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 385 */       matrixStack.translate(-0.1D, -0.4D, 0.2D);
/* 386 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 387 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(80.0F));
/* 388 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 394 */     modelRenderer.rotateAngleX = x;
/* 395 */     modelRenderer.rotateAngleY = y;
/* 396 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\LeopardHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */