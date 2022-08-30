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
/*     */ public class GiraffeHeavyModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer neck2;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer head2;
/*     */   private final ModelRenderer leftHorn;
/*     */   private final ModelRenderer rightHorn;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer mane;
/*     */   private final ModelRenderer mane2;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leftShoulder;
/*     */   private final ModelRenderer rightShoulder;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightHand2;
/*     */   private final ModelRenderer rightHand1;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand2;
/*     */   private final ModelRenderer leftHand1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightHoof;
/*     */   private final ModelRenderer rightHoof2;
/*     */   private final ModelRenderer rightHoof3;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftHoof;
/*     */   private final ModelRenderer leftHoof2;
/*     */   private final ModelRenderer leftHoof3;
/*     */   
/*     */   public GiraffeHeavyModel() {
/*  57 */     super(1.0F);
/*  58 */     this.textureWidth = 128;
/*  59 */     this.textureHeight = 64;
/*     */     
/*  61 */     this.neck = new ModelRenderer((Model)this);
/*  62 */     this.neck.setRotationPoint(-2.0F, -11.0F, -3.5F);
/*  63 */     setRotationAngle(this.neck, 0.1745F, 0.0F, 0.0F);
/*  64 */     this.neck.setTextureOffset(79, 0).addBox(0.0F, 0.0F, 0.0F, 5.0F, 8.0F, 5.0F, 0.0F, false);
/*     */     
/*  66 */     this.neck2 = new ModelRenderer((Model)this);
/*  67 */     this.neck2.setRotationPoint(0.5F, -7.0F, -0.5F);
/*  68 */     this.neck.addChild(this.neck2);
/*  69 */     setRotationAngle(this.neck2, 0.1396F, 0.0F, 0.0F);
/*  70 */     this.neck2.setTextureOffset(79, 14).addBox(0.0F, 0.0F, 0.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/*  72 */     this.head = new ModelRenderer((Model)this);
/*  73 */     this.head.setRotationPoint(2.0F, 0.5F, 1.5F);
/*  74 */     this.neck2.addChild(this.head);
/*  75 */     setRotationAngle(this.head, -0.3142F, 0.0F, 0.0F);
/*  76 */     this.head.setTextureOffset(32, 18).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 3.0F, 8.0F, 0.0F, false);
/*     */     
/*  78 */     this.rightEar = new ModelRenderer((Model)this);
/*  79 */     this.rightEar.setRotationPoint(-2.0F, -3.5F, 0.0F);
/*  80 */     this.head.addChild(this.rightEar);
/*  81 */     setRotationAngle(this.rightEar, 0.0F, 0.0F, -0.2618F);
/*  82 */     this.rightEar.setTextureOffset(32, 0).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, false);
/*     */     
/*  84 */     this.head2 = new ModelRenderer((Model)this);
/*  85 */     this.head2.setRotationPoint(-2.0F, -1.0F, 0.5F);
/*  86 */     this.head.addChild(this.head2);
/*  87 */     this.head2.setTextureOffset(31, 29).addBox(0.01F, 0.0F, -4.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/*  89 */     this.leftHorn = new ModelRenderer((Model)this);
/*  90 */     this.leftHorn.setRotationPoint(0.5F, -6.0F, -0.5F);
/*  91 */     this.head.addChild(this.leftHorn);
/*  92 */     this.leftHorn.setTextureOffset(60, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  94 */     this.rightHorn = new ModelRenderer((Model)this);
/*  95 */     this.rightHorn.setRotationPoint(-1.5F, -6.0F, -0.5F);
/*  96 */     this.head.addChild(this.rightHorn);
/*  97 */     this.rightHorn.setTextureOffset(60, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  99 */     this.leftEar = new ModelRenderer((Model)this);
/* 100 */     this.leftEar.setRotationPoint(2.0F, -3.5F, 0.0F);
/* 101 */     this.head.addChild(this.leftEar);
/* 102 */     setRotationAngle(this.leftEar, 0.0F, 0.0F, 0.2618F);
/* 103 */     this.leftEar.setTextureOffset(32, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, true);
/*     */     
/* 105 */     this.mane = new ModelRenderer((Model)this);
/* 106 */     this.mane.setRotationPoint(2.0F, -2.0F, 3.5F);
/* 107 */     this.neck2.addChild(this.mane);
/* 108 */     setRotationAngle(this.mane, 0.0349F, 0.0F, 0.0F);
/* 109 */     this.mane.setTextureOffset(76, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 0.0F, false);
/*     */     
/* 111 */     this.mane2 = new ModelRenderer((Model)this);
/* 112 */     this.mane2.setRotationPoint(2.5F, 0.0F, 4.6F);
/* 113 */     this.neck.addChild(this.mane2);
/* 114 */     setRotationAngle(this.mane2, 0.0175F, 0.0F, 0.0F);
/* 115 */     this.mane2.setTextureOffset(76, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 1.0F, 0.0F, false);
/*     */     
/* 117 */     this.body = new ModelRenderer((Model)this);
/* 118 */     this.body.setRotationPoint(-0.5F, -4.0F, 0.0F);
/* 119 */     this.body.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -2.0F, 10.0F, 16.0F, 5.0F, 0.0F, false);
/*     */     
/* 121 */     this.leftShoulder = new ModelRenderer((Model)this);
/* 122 */     this.leftShoulder.setRotationPoint(-0.5F, 7.5F, -1.9F);
/* 123 */     this.body.addChild(this.leftShoulder);
/* 124 */     setRotationAngle(this.leftShoulder, 0.0F, 0.0F, -0.9599F);
/* 125 */     this.leftShoulder.setTextureOffset(47, 0).addBox(0.0F, 0.0F, -0.1F, 9.0F, 7.0F, 5.0F, -0.01F, true);
/*     */     
/* 127 */     this.rightShoulder = new ModelRenderer((Model)this);
/* 128 */     this.rightShoulder.setRotationPoint(2.5F, 7.4F, -1.91F);
/* 129 */     this.body.addChild(this.rightShoulder);
/* 130 */     setRotationAngle(this.rightShoulder, 0.0F, 0.0F, -2.2689F);
/* 131 */     this.rightShoulder.setTextureOffset(47, 0).addBox(0.0F, -7.5F, -0.09F, 9.0F, 7.0F, 5.0F, -0.01F, false);
/*     */     
/* 133 */     this.rightArm = new ModelRenderer((Model)this);
/* 134 */     this.rightArm.setRotationPoint(-8.0F, 1.0F, -1.0F);
/* 135 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2793F);
/* 136 */     this.rightArm.setTextureOffset(23, 30).addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 138 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 139 */     this.rightArm2.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 140 */     this.rightArm.addChild(this.rightArm2);
/* 141 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.4189F);
/* 142 */     this.rightArm2.setTextureOffset(23, 39).addBox(0.0F, 0.0F, 0.1F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 144 */     this.rightHand2 = new ModelRenderer((Model)this);
/* 145 */     this.rightHand2.setRotationPoint(1.1392F, 5.1097F, 0.8F);
/* 146 */     this.rightArm2.addChild(this.rightHand2);
/* 147 */     setRotationAngle(this.rightHand2, 0.1745F, 0.0F, -0.3491F);
/* 148 */     this.rightHand2.setTextureOffset(65, 20).addBox(0.0F, -0.0868F, -0.4924F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 150 */     this.rightHand1 = new ModelRenderer((Model)this);
/* 151 */     this.rightHand1.setRotationPoint(0.0F, 5.5F, 2.0F);
/* 152 */     this.rightArm2.addChild(this.rightHand1);
/* 153 */     setRotationAngle(this.rightHand1, 0.1745F, 1.5708F, 0.1396F);
/* 154 */     this.rightHand1.setTextureOffset(65, 24).addBox(-0.1F, 0.0F, 0.1F, 2.0F, 2.0F, 1.0F, -0.01F, false);
/*     */     
/* 156 */     this.leftArm = new ModelRenderer((Model)this);
/* 157 */     this.leftArm.setRotationPoint(9.0F, 1.0F, -1.0F);
/* 158 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2793F);
/* 159 */     this.leftArm.setTextureOffset(23, 30).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 161 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 162 */     this.leftArm2.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 163 */     this.leftArm.addChild(this.leftArm2);
/* 164 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.4189F);
/* 165 */     this.leftArm2.setTextureOffset(23, 39).addBox(-2.0F, 0.0F, 0.1F, 2.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 167 */     this.leftHand2 = new ModelRenderer((Model)this);
/* 168 */     this.leftHand2.setRotationPoint(-7.4075F, 2.7019F, 1.8F);
/* 169 */     this.leftArm2.addChild(this.leftHand2);
/* 170 */     setRotationAngle(this.leftHand2, 0.1745F, 0.0F, 0.3491F);
/* 171 */     this.leftHand2.setTextureOffset(65, 20).addBox(5.9F, -0.07F, -1.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 173 */     this.leftHand1 = new ModelRenderer((Model)this);
/* 174 */     this.leftHand1.setRotationPoint(1.8929F, 2.9691F, 1.5F);
/* 175 */     this.leftArm2.addChild(this.leftHand1);
/* 176 */     setRotationAngle(this.leftHand1, 0.1745F, -1.5708F, -0.1396F);
/* 177 */     this.leftHand1.setTextureOffset(65, 24).addBox(-1.4F, 2.6065F, 1.9F, 2.0F, 2.0F, 1.0F, -0.01F, false);
/*     */     
/* 179 */     this.rightLeg = new ModelRenderer((Model)this);
/* 180 */     this.rightLeg.setRotationPoint(-2.0F, 11.6F, 1.0F);
/* 181 */     setRotationAngle(this.rightLeg, -0.3491F, 0.0F, 0.0F);
/* 182 */     this.rightLeg.setTextureOffset(10, 30).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/* 184 */     this.rightLeg3 = new ModelRenderer((Model)this);
/* 185 */     this.rightLeg3.setRotationPoint(0.5F, 5.0F, 0.0F);
/* 186 */     this.rightLeg.addChild(this.rightLeg3);
/* 187 */     setRotationAngle(this.rightLeg3, 1.7453F, 0.0F, 0.0F);
/* 188 */     this.rightLeg3.setTextureOffset(10, 41).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 190 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 191 */     this.rightLeg2.setRotationPoint(0.0F, 5.2065F, -0.5747F);
/* 192 */     this.rightLeg3.addChild(this.rightLeg2);
/* 193 */     setRotationAngle(this.rightLeg2, -1.9199F, 0.0F, 0.0F);
/* 194 */     this.rightLeg2.setTextureOffset(0, 30).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, 0.01F, false);
/*     */     
/* 196 */     this.rightHoof = new ModelRenderer((Model)this);
/* 197 */     this.rightHoof.setRotationPoint(0.0F, 5.0F, -0.5F);
/* 198 */     this.rightLeg2.addChild(this.rightHoof);
/* 199 */     setRotationAngle(this.rightHoof, 0.5236F, 0.0F, 0.0F);
/* 200 */     this.rightHoof.setTextureOffset(0, 41).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 202 */     this.rightHoof2 = new ModelRenderer((Model)this);
/* 203 */     this.rightHoof2.setRotationPoint(-1.0F, 0.0F, -2.5F);
/* 204 */     this.rightHoof.addChild(this.rightHoof2);
/* 205 */     setRotationAngle(this.rightHoof2, -0.1211F, -0.4883F, -0.0394F);
/* 206 */     this.rightHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 208 */     this.rightHoof3 = new ModelRenderer((Model)this);
/* 209 */     this.rightHoof3.setRotationPoint(-2.0F, 0.0F, -2.0F);
/* 210 */     this.rightHoof.addChild(this.rightHoof3);
/* 211 */     setRotationAngle(this.rightHoof3, -0.1211F, 0.4883F, 0.0394F);
/* 212 */     this.rightHoof3.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 214 */     this.tail = new ModelRenderer((Model)this);
/* 215 */     this.tail.setRotationPoint(0.0F, 10.0F, 3.0F);
/* 216 */     setRotationAngle(this.tail, -0.733F, 0.0F, 0.0F);
/* 217 */     this.tail.setTextureOffset(31, 3).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
/*     */     
/* 219 */     this.tail2 = new ModelRenderer((Model)this);
/* 220 */     this.tail2.setRotationPoint(0.0F, 0.0F, 4.5F);
/* 221 */     this.tail.addChild(this.tail2);
/* 222 */     setRotationAngle(this.tail2, 0.4712F, 0.0F, 0.0F);
/* 223 */     this.tail2.setTextureOffset(31, 10).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
/*     */     
/* 225 */     this.tail3 = new ModelRenderer((Model)this);
/* 226 */     this.tail3.setRotationPoint(-0.5F, -0.3578F, 3.2176F);
/* 227 */     this.tail2.addChild(this.tail3);
/* 228 */     setRotationAngle(this.tail3, 0.2094F, 0.0F, 0.0F);
/* 229 */     this.tail3.setTextureOffset(60, 13).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 231 */     this.leftLeg = new ModelRenderer((Model)this);
/* 232 */     this.leftLeg.setRotationPoint(4.0F, 11.6F, 1.0F);
/* 233 */     setRotationAngle(this.leftLeg, -0.3491F, 0.0F, 0.0F);
/* 234 */     this.leftLeg.setTextureOffset(10, 30).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/* 236 */     this.leftLeg2 = new ModelRenderer((Model)this);
/* 237 */     this.leftLeg2.setRotationPoint(0.5F, 5.0F, 0.0F);
/* 238 */     this.leftLeg.addChild(this.leftLeg2);
/* 239 */     setRotationAngle(this.leftLeg2, 1.7453F, 0.0F, 0.0F);
/* 240 */     this.leftLeg2.setTextureOffset(10, 41).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 242 */     this.leftLeg3 = new ModelRenderer((Model)this);
/* 243 */     this.leftLeg3.setRotationPoint(0.0F, 5.2065F, -0.5747F);
/* 244 */     this.leftLeg2.addChild(this.leftLeg3);
/* 245 */     setRotationAngle(this.leftLeg3, -1.9199F, 0.0F, 0.0F);
/* 246 */     this.leftLeg3.setTextureOffset(0, 30).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, 0.01F, false);
/*     */     
/* 248 */     this.leftHoof = new ModelRenderer((Model)this);
/* 249 */     this.leftHoof.setRotationPoint(0.0F, 5.0F, -0.5F);
/* 250 */     this.leftLeg3.addChild(this.leftHoof);
/* 251 */     setRotationAngle(this.leftHoof, 0.5236F, 0.0F, 0.0F);
/* 252 */     this.leftHoof.setTextureOffset(0, 41).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 254 */     this.leftHoof2 = new ModelRenderer((Model)this);
/* 255 */     this.leftHoof2.setRotationPoint(-1.0F, 0.0F, -2.5F);
/* 256 */     this.leftHoof.addChild(this.leftHoof2);
/* 257 */     setRotationAngle(this.leftHoof2, -0.1211F, -0.4883F, -0.0394F);
/* 258 */     this.leftHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 260 */     this.leftHoof3 = new ModelRenderer((Model)this);
/* 261 */     this.leftHoof3.setRotationPoint(-2.0F, 0.0F, -2.0F);
/* 262 */     this.leftHoof.addChild(this.leftHoof3);
/* 263 */     setRotationAngle(this.leftHoof3, -0.1211F, 0.4883F, 0.0394F);
/* 264 */     this.leftHoof3.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 266 */     this.bipedBody = this.body;
/* 267 */     this.bipedHead = this.head;
/* 268 */     this.bipedRightArm = this.rightArm;
/* 269 */     this.bipedLeftArm = this.leftArm;
/* 270 */     this.bipedRightLeg = this.rightLeg;
/* 271 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 277 */     this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 278 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 279 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 280 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 281 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 282 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 283 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 290 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/* 291 */     boolean flag1 = entity.isActualySwimming();
/* 292 */     this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
/* 293 */     if (flag) {
/* 294 */       this.bipedHead.rotateAngleX = -0.7853982F;
/* 295 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/* 297 */       if (flag1) {
/* 298 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 300 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 304 */       this.bipedHead.rotateAngleX = headPitch * 0.015707964F;
/* 305 */       if (this.bipedHead.rotateAngleX > 0.6D) {
/* 306 */         this.bipedHead.rotateAngleX = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 310 */     float f = 1.0F;
/* 311 */     this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 312 */     this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 313 */     this.rightLeg.rotateAngleX = -0.34F + MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 314 */     this.leftLeg.rotateAngleX = -0.34F + MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 315 */     if (!entity.getHeldItemMainhand().isEmpty())
/* 316 */       this.rightArm.rotateAngleX += -0.15F; 
/* 317 */     if (entity.isSprinting()) {
/*     */       
/* 319 */       this.tail.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 320 */       this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 321 */       this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 325 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 326 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 327 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 329 */       if (isBlackLeg) {
/*     */         
/* 331 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 332 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY;
/* 333 */         this.leftLeg.rotateAngleY += this.body.rotateAngleY;
/* 334 */         float f1 = 1.0F - this.swingProgress;
/* 335 */         f1 *= f1;
/* 336 */         f1 *= f1;
/* 337 */         f1 = 1.0F - f1;
/* 338 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 339 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 340 */         this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 341 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 345 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 346 */         this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 12.0F;
/* 347 */         this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 9.0F;
/* 348 */         this.rightArm.rotateAngleY += this.body.rotateAngleY;
/* 349 */         this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
/* 350 */         this.leftArm.rotateAngleY -= this.body.rotateAngleY;
/* 351 */         this.leftArm.rotateAngleX -= this.body.rotateAngleY;
/* 352 */         float f1 = 1.0F - this.swingProgress;
/* 353 */         f1 *= f1;
/* 354 */         f1 *= f1;
/* 355 */         f1 = 1.0F - f1;
/* 356 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 357 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 358 */         this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
/* 359 */         this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
/* 360 */         this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 365 */     if (entity.isSneaking()) {
/*     */       
/* 367 */       this.body.rotateAngleX = 0.5F;
/* 368 */       this.body.rotationPointZ -= 4.0F;
/* 369 */       this.rightArm.rotateAngleX += 0.4F;
/* 370 */       this.rightArm.rotationPointZ -= 0.5F;
/* 371 */       this.leftArm.rotateAngleX += 0.4F;
/* 372 */       this.leftArm.rotationPointZ -= 0.5F;
/* 373 */       this.rightLeg.rotationPointZ = 4.0F;
/* 374 */       this.leftLeg.rotationPointZ = 4.5F;
/* 375 */       this.rightLeg.rotationPointY = 10.5F;
/* 376 */       this.leftLeg.rotationPointY = 10.5F;
/* 377 */       this.head.rotationPointY = 0.0F;
/* 378 */       this.head.rotationPointZ = 2.0F;
/* 379 */       this.neck.rotateAngleX += 0.25F;
/* 380 */       this.neck.rotationPointZ = -9.0F;
/* 381 */       this.neck.rotationPointY++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 388 */     super.translateHand(side, matrixStack);
/* 389 */     matrixStack.translate((side == HandSide.RIGHT) ? 0.12D : -0.12D, 0.2D, 0.1D);
/* 390 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 396 */     if (side == HandSide.RIGHT) {
/*     */       
/* 398 */       matrixStack.translate(-0.5D, 0.0D, -0.1D);
/* 399 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 400 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
/* 401 */       this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 405 */       matrixStack.translate(0.5D, 0.0D, -0.1D);
/* 406 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 407 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
/* 408 */       this.leftArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 415 */     if (side == HandSide.RIGHT) {
/*     */       
/* 417 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 418 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 419 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 420 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 424 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 425 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 426 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 427 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 433 */     modelRenderer.rotateAngleX = x;
/* 434 */     modelRenderer.rotateAngleY = y;
/* 435 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\GiraffeHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */