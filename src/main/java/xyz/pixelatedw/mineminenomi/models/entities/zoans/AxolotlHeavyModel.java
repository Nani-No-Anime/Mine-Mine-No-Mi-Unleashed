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
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ 
/*     */ public class AxolotlHeavyModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T>
/*     */   implements IHasArm
/*     */ {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer bodyDetail_r1;
/*     */   private final ModelRenderer rightNipple_r1;
/*     */   private final ModelRenderer hips_r1;
/*     */   private final ModelRenderer lowerBody_r1;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail1Detail_r1;
/*     */   private final ModelRenderer tail1_r1;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail2Detail_r1;
/*     */   private final ModelRenderer tail2_r1;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer tail3Detail_r1;
/*     */   private final ModelRenderer tail3_r1;
/*     */   private final ModelRenderer tail4;
/*     */   private final ModelRenderer tail4Detail_r1;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer head_r1;
/*     */   private final ModelRenderer neck_r1;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2_r1;
/*     */   private final ModelRenderer leftLeg1_r1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2_r1;
/*     */   private final ModelRenderer rightLeg1_r1;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm3_r1;
/*     */   private final ModelRenderer leftArm2_r1;
/*     */   private final ModelRenderer leftArm1_r1;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm3_r1;
/*     */   private final ModelRenderer rightArm2_r1;
/*     */   private final ModelRenderer rightArm1_r1;
/*     */   
/*     */   public AxolotlHeavyModel() {
/*  53 */     super(1.0F);
/*  54 */     this.textureWidth = 256;
/*  55 */     this.textureHeight = 256;
/*     */     
/*  57 */     this.body = new ModelRenderer((Model)this);
/*  58 */     this.body.setRotationPoint(4.0F, 14.0F, 35.0F);
/*  59 */     setRotationAngle(this.body, 0.3491F, 0.0F, 0.0F);
/*     */     
/*  61 */     this.bodyDetail_r1 = new ModelRenderer((Model)this);
/*  62 */     this.bodyDetail_r1.setRotationPoint(-5.0F, -27.0F, -19.0F);
/*  63 */     this.body.addChild(this.bodyDetail_r1);
/*  64 */     setRotationAngle(this.bodyDetail_r1, 0.0873F, 0.0F, 0.0F);
/*  65 */     this.bodyDetail_r1.setTextureOffset(107, 11).addBox(1.0F, -12.0F, -6.0F, 0.0F, 25.0F, 23.0F, 0.01F, false);
/*     */     
/*  67 */     this.rightNipple_r1 = new ModelRenderer((Model)this);
/*  68 */     this.rightNipple_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  69 */     this.body.addChild(this.rightNipple_r1);
/*  70 */     setRotationAngle(this.rightNipple_r1, 0.0873F, 0.0F, 0.0F);
/*  71 */     this.rightNipple_r1.setTextureOffset(0, 8).addBox(-13.0F, -30.0F, -32.6F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*  72 */     this.rightNipple_r1.setTextureOffset(0, 8).addBox(2.0F, -30.0F, -32.6F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*  73 */     this.rightNipple_r1.setTextureOffset(47, 50).addBox(-15.0F, -36.3F, -31.3F, 22.0F, 3.0F, 13.0F, 0.0F, false);
/*  74 */     this.rightNipple_r1.setTextureOffset(0, 0).addBox(-16.0F, -36.0F, -32.3F, 24.0F, 16.0F, 15.0F, 0.0F, false);
/*     */     
/*  76 */     this.hips_r1 = new ModelRenderer((Model)this);
/*  77 */     this.hips_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  78 */     this.body.addChild(this.hips_r1);
/*  79 */     setRotationAngle(this.hips_r1, 0.0436F, 0.0F, 0.0F);
/*  80 */     this.hips_r1.setTextureOffset(24, 32).addBox(-15.5F, -20.0F, -31.5F, 23.0F, 4.0F, 13.0F, 0.0F, false);
/*     */     
/*  82 */     this.lowerBody_r1 = new ModelRenderer((Model)this);
/*  83 */     this.lowerBody_r1.setRotationPoint(0.0F, -7.0F, -25.0F);
/*  84 */     this.body.addChild(this.lowerBody_r1);
/*  85 */     setRotationAngle(this.lowerBody_r1, -0.48F, 0.0F, 0.0F);
/*  86 */     this.lowerBody_r1.setTextureOffset(47, 67).addBox(-13.0F, -10.0F, -10.0F, 18.0F, 10.0F, 11.0F, 0.0F, false);
/*     */     
/*  88 */     this.tail = new ModelRenderer((Model)this);
/*  89 */     this.tail.setRotationPoint(0.0F, 11.0F, 13.0F);
/*  90 */     setRotationAngle(this.tail, -0.1309F, 0.0F, 0.0F);
/*     */     
/*  92 */     this.tail1Detail_r1 = new ModelRenderer((Model)this);
/*  93 */     this.tail1Detail_r1.setRotationPoint(1.0F, 0.4965F, 2.1955F);
/*  94 */     this.tail.addChild(this.tail1Detail_r1);
/*  95 */     setRotationAngle(this.tail1Detail_r1, -0.2182F, 0.0F, 0.0F);
/*  96 */     this.tail1Detail_r1.setTextureOffset(106, 53).addBox(-1.0F, -9.0F, -8.0F, 0.0F, 14.0F, 15.0F, 0.0F, false);
/*     */     
/*  98 */     this.tail1_r1 = new ModelRenderer((Model)this);
/*  99 */     this.tail1_r1.setRotationPoint(1.0F, 15.4965F, 20.1955F);
/* 100 */     this.tail.addChild(this.tail1_r1);
/* 101 */     setRotationAngle(this.tail1_r1, -0.3054F, 0.0F, 0.0F);
/* 102 */     this.tail1_r1.setTextureOffset(41, 91).addBox(-4.5F, -11.0F, -34.0F, 7.0F, 8.0F, 19.0F, 0.0F, false);
/*     */     
/* 104 */     this.tail2 = new ModelRenderer((Model)this);
/* 105 */     this.tail2.setRotationPoint(0.0F, 5.5F, 8.0F);
/* 106 */     this.tail.addChild(this.tail2);
/*     */     
/* 108 */     this.tail2Detail_r1 = new ModelRenderer((Model)this);
/* 109 */     this.tail2Detail_r1.setRotationPoint(1.0F, -2.5035F, 5.1955F);
/* 110 */     this.tail2.addChild(this.tail2Detail_r1);
/* 111 */     setRotationAngle(this.tail2Detail_r1, -0.1745F, 0.0F, 0.0F);
/* 112 */     this.tail2Detail_r1.setTextureOffset(96, 87).addBox(-1.0F, -8.8987F, -6.0675F, 0.0F, 14.0F, 10.0F, 0.0F, false);
/*     */     
/* 114 */     this.tail2_r1 = new ModelRenderer((Model)this);
/* 115 */     this.tail2_r1.setRotationPoint(1.0F, 9.4965F, 9.1955F);
/* 116 */     this.tail2.addChild(this.tail2_r1);
/* 117 */     setRotationAngle(this.tail2_r1, -0.1745F, 0.0F, 0.0F);
/* 118 */     this.tail2_r1.setTextureOffset(84, 114).addBox(-3.5F, -11.0F, -12.0F, 5.0F, 6.0F, 10.0F, 0.0F, false);
/*     */     
/* 120 */     this.tail3 = new ModelRenderer((Model)this);
/* 121 */     this.tail3.setRotationPoint(0.0F, 2.0F, 13.0F);
/* 122 */     this.tail2.addChild(this.tail3);
/*     */     
/* 124 */     this.tail3Detail_r1 = new ModelRenderer((Model)this);
/* 125 */     this.tail3Detail_r1.setRotationPoint(1.0F, -4.5035F, 3.1955F);
/* 126 */     this.tail3.addChild(this.tail3Detail_r1);
/* 127 */     setRotationAngle(this.tail3Detail_r1, -0.1309F, 0.0F, 0.0F);
/* 128 */     this.tail3Detail_r1.setTextureOffset(120, 87).addBox(-1.0F, -6.9629F, -6.9734F, 0.0F, 13.0F, 10.0F, 0.0F, false);
/*     */     
/* 130 */     this.tail3_r1 = new ModelRenderer((Model)this);
/* 131 */     this.tail3_r1.setRotationPoint(1.0F, 7.4965F, -3.8045F);
/* 132 */     this.tail3.addChild(this.tail3_r1);
/* 133 */     setRotationAngle(this.tail3_r1, -0.0873F, 0.0F, 0.0F);
/* 134 */     this.tail3_r1.setTextureOffset(115, 114).addBox(-2.5F, -10.0F, -3.0F, 3.0F, 4.0F, 12.0F, 0.0F, false);
/*     */     
/* 136 */     this.tail4 = new ModelRenderer((Model)this);
/* 137 */     this.tail4.setRotationPoint(0.0F, 1.0F, 6.0F);
/* 138 */     this.tail3.addChild(this.tail4);
/* 139 */     this.tail4.setTextureOffset(0, 0).addBox(-0.5F, -1.0035F, -0.8045F, 1.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 141 */     this.tail4Detail_r1 = new ModelRenderer((Model)this);
/* 142 */     this.tail4Detail_r1.setRotationPoint(1.0F, -5.5035F, -4.8045F);
/* 143 */     this.tail4.addChild(this.tail4Detail_r1);
/* 144 */     setRotationAngle(this.tail4Detail_r1, -0.0436F, 0.0F, 0.0F);
/* 145 */     this.tail4Detail_r1.setTextureOffset(119, 131).addBox(-1.0F, -6.4629F, 4.0266F, 0.0F, 12.0F, 5.0F, 0.0F, false);
/*     */     
/* 147 */     this.head = new ModelRenderer((Model)this);
/* 148 */     this.head.setRotationPoint(0.0F, -7.0F, -4.0F);
/* 149 */     setRotationAngle(this.head, 0.3491F, 0.0F, 0.0F);
/* 150 */     this.head.setTextureOffset(100, 4).addBox(-8.0F, -10.9277F, 1.4656F, 16.0F, 9.0F, 0.0F, 0.0F, false);
/*     */     
/* 152 */     this.head_r1 = new ModelRenderer((Model)this);
/* 153 */     this.head_r1.setRotationPoint(0.0F, -1.9277F, 0.4656F);
/* 154 */     this.head.addChild(this.head_r1);
/* 155 */     setRotationAngle(this.head_r1, 0.0436F, 0.0F, 0.0F);
/* 156 */     this.head_r1.setTextureOffset(64, 0).addBox(-4.0F, -4.5F, -3.5F, 8.0F, 5.0F, 8.0F, 0.0F, false);
/*     */     
/* 158 */     this.neck_r1 = new ModelRenderer((Model)this);
/* 159 */     this.neck_r1.setRotationPoint(0.0F, -1.9277F, 0.4656F);
/* 160 */     this.head.addChild(this.neck_r1);
/* 161 */     setRotationAngle(this.neck_r1, 0.1745F, 0.0F, 0.0F);
/* 162 */     this.neck_r1.setTextureOffset(81, 16).addBox(-3.5F, -2.0F, -2.0F, 7.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/* 164 */     this.leftLeg = new ModelRenderer((Model)this);
/* 165 */     this.leftLeg.setRotationPoint(7.0F, 14.25F, 3.0F);
/* 166 */     setRotationAngle(this.leftLeg, -0.2618F, 0.0F, 0.0F);
/*     */     
/* 168 */     this.leftLeg2_r1 = new ModelRenderer((Model)this);
/* 169 */     this.leftLeg2_r1.setRotationPoint(-3.0F, -5.0F, 2.0F);
/* 170 */     this.leftLeg.addChild(this.leftLeg2_r1);
/* 171 */     setRotationAngle(this.leftLeg2_r1, 0.2618F, 0.0F, 0.0F);
/* 172 */     this.leftLeg2_r1.setTextureOffset(2, 148).addBox(0.0F, 6.0F, -7.5F, 6.0F, 8.0F, 5.0F, 0.0F, true);
/*     */     
/* 174 */     this.leftLeg1_r1 = new ModelRenderer((Model)this);
/* 175 */     this.leftLeg1_r1.setRotationPoint(-3.0F, -2.0F, 1.0F);
/* 176 */     this.leftLeg.addChild(this.leftLeg1_r1);
/* 177 */     setRotationAngle(this.leftLeg1_r1, -0.5672F, 0.0F, 0.0F);
/* 178 */     this.leftLeg1_r1.setTextureOffset(0, 131).addBox(-0.5F, -1.0F, -1.5F, 7.0F, 8.0F, 6.0F, 0.0F, true);
/*     */     
/* 180 */     this.rightLeg = new ModelRenderer((Model)this);
/* 181 */     this.rightLeg.setRotationPoint(-7.0F, 14.25F, 4.0F);
/* 182 */     setRotationAngle(this.rightLeg, -0.2618F, 0.0F, 0.0F);
/*     */     
/* 184 */     this.rightLeg2_r1 = new ModelRenderer((Model)this);
/* 185 */     this.rightLeg2_r1.setRotationPoint(-3.0F, -5.0341F, 1.2588F);
/* 186 */     this.rightLeg.addChild(this.rightLeg2_r1);
/* 187 */     setRotationAngle(this.rightLeg2_r1, 0.2618F, 0.0F, 0.0F);
/* 188 */     this.rightLeg2_r1.setTextureOffset(2, 148).addBox(0.0F, 6.0F, -7.5F, 6.0F, 8.0F, 5.0F, 0.0F, false);
/*     */     
/* 190 */     this.rightLeg1_r1 = new ModelRenderer((Model)this);
/* 191 */     this.rightLeg1_r1.setRotationPoint(-3.0F, -2.0341F, 0.2588F);
/* 192 */     this.rightLeg.addChild(this.rightLeg1_r1);
/* 193 */     setRotationAngle(this.rightLeg1_r1, -0.5672F, 0.0F, 0.0F);
/* 194 */     this.rightLeg1_r1.setTextureOffset(0, 131).addBox(-0.5F, -1.0F, -1.5F, 7.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/* 196 */     this.leftArm = new ModelRenderer((Model)this);
/* 197 */     this.leftArm.setRotationPoint(12.0F, -4.0F, -1.0F);
/* 198 */     setRotationAngle(this.leftArm, -0.4363F, 0.0F, 0.0F);
/*     */     
/* 200 */     this.leftArm3_r1 = new ModelRenderer((Model)this);
/* 201 */     this.leftArm3_r1.setRotationPoint(6.0F, 16.0F, 2.0F);
/* 202 */     this.leftArm.addChild(this.leftArm3_r1);
/* 203 */     setRotationAngle(this.leftArm3_r1, 0.0843F, -0.0226F, 0.2608F);
/* 204 */     this.leftArm3_r1.setTextureOffset(3, 105).addBox(-6.0F, -2.1134F, -4.5979F, 10.0F, 15.0F, 9.0F, 0.0F, true);
/*     */     
/* 206 */     this.leftArm2_r1 = new ModelRenderer((Model)this);
/* 207 */     this.leftArm2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 208 */     this.leftArm.addChild(this.leftArm2_r1);
/* 209 */     setRotationAngle(this.leftArm2_r1, 0.0865F, 0.0114F, -0.1304F);
/* 210 */     this.leftArm2_r1.setTextureOffset(0, 71).addBox(-1.0F, -3.0F, -5.0F, 12.0F, 20.0F, 11.0F, 0.0F, true);
/*     */     
/* 212 */     this.leftArm1_r1 = new ModelRenderer((Model)this);
/* 213 */     this.leftArm1_r1.setRotationPoint(4.0F, -2.0F, 0.0F);
/* 214 */     this.leftArm.addChild(this.leftArm1_r1);
/* 215 */     setRotationAngle(this.leftArm1_r1, 0.0865F, 0.0114F, -0.1304F);
/* 216 */     this.leftArm1_r1.setTextureOffset(1, 52).addBox(-4.0F, -7.0076F, -4.1743F, 9.0F, 8.0F, 9.0F, 0.0F, true);
/*     */     
/* 218 */     this.rightArm = new ModelRenderer((Model)this);
/* 219 */     this.rightArm.setRotationPoint(-12.0F, -4.0F, -1.0F);
/* 220 */     setRotationAngle(this.rightArm, -0.4363F, 0.0F, 0.0F);
/*     */     
/* 222 */     this.rightArm3_r1 = new ModelRenderer((Model)this);
/* 223 */     this.rightArm3_r1.setRotationPoint(-6.0F, 16.2451F, 1.8498F);
/* 224 */     this.rightArm.addChild(this.rightArm3_r1);
/* 225 */     setRotationAngle(this.rightArm3_r1, 0.0843F, 0.0226F, -0.2608F);
/* 226 */     this.rightArm3_r1.setTextureOffset(3, 105).addBox(-4.0F, -2.1134F, -4.5979F, 10.0F, 15.0F, 9.0F, 0.0F, false);
/*     */     
/* 228 */     this.rightArm2_r1 = new ModelRenderer((Model)this);
/* 229 */     this.rightArm2_r1.setRotationPoint(0.0F, 0.2451F, -0.1502F);
/* 230 */     this.rightArm.addChild(this.rightArm2_r1);
/* 231 */     setRotationAngle(this.rightArm2_r1, 0.0865F, -0.0114F, 0.1304F);
/* 232 */     this.rightArm2_r1.setTextureOffset(0, 71).addBox(-11.0F, -3.0F, -5.0F, 12.0F, 20.0F, 11.0F, 0.0F, false);
/*     */     
/* 234 */     this.rightArm1_r1 = new ModelRenderer((Model)this);
/* 235 */     this.rightArm1_r1.setRotationPoint(-4.0F, -1.7549F, -0.1502F);
/* 236 */     this.rightArm.addChild(this.rightArm1_r1);
/* 237 */     setRotationAngle(this.rightArm1_r1, 0.0865F, -0.0114F, 0.1304F);
/* 238 */     this.rightArm1_r1.setTextureOffset(1, 52).addBox(-5.0F, -7.0076F, -4.1743F, 9.0F, 8.0F, 9.0F, 0.0F, false);
/*     */     
/* 240 */     this.bipedBody = this.body;
/* 241 */     this.bipedHead = this.head;
/* 242 */     this.bipedRightArm = this.rightArm;
/* 243 */     this.bipedLeftArm = this.leftArm;
/* 244 */     this.bipedRightLeg = this.rightLeg;
/* 245 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual getTicksElytraFlying : ()I
/*     */     //   4: iconst_4
/*     */     //   5: if_icmple -> 12
/*     */     //   8: iconst_1
/*     */     //   9: goto -> 13
/*     */     //   12: iconst_0
/*     */     //   13: istore #7
/*     */     //   15: aload_1
/*     */     //   16: invokevirtual isActualySwimming : ()Z
/*     */     //   19: istore #8
/*     */     //   21: aload_0
/*     */     //   22: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   25: fload #5
/*     */     //   27: ldc_w 0.017453292
/*     */     //   30: fmul
/*     */     //   31: putfield rotateAngleY : F
/*     */     //   34: iload #7
/*     */     //   36: ifeq -> 52
/*     */     //   39: aload_0
/*     */     //   40: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   43: ldc_w -0.7853982
/*     */     //   46: putfield rotateAngleX : F
/*     */     //   49: goto -> 191
/*     */     //   52: aload_0
/*     */     //   53: getfield swimAnimation : F
/*     */     //   56: fconst_0
/*     */     //   57: fcmpl
/*     */     //   58: ifle -> 125
/*     */     //   61: iload #8
/*     */     //   63: ifeq -> 94
/*     */     //   66: aload_0
/*     */     //   67: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   70: aload_0
/*     */     //   71: aload_0
/*     */     //   72: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   75: getfield rotateAngleX : F
/*     */     //   78: ldc_w -0.7853982
/*     */     //   81: aload_0
/*     */     //   82: getfield swimAnimation : F
/*     */     //   85: invokevirtual rotLerpRad : (FFF)F
/*     */     //   88: putfield rotateAngleX : F
/*     */     //   91: goto -> 191
/*     */     //   94: aload_0
/*     */     //   95: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   98: aload_0
/*     */     //   99: aload_0
/*     */     //   100: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   103: getfield rotateAngleX : F
/*     */     //   106: fload #6
/*     */     //   108: ldc_w 0.017453292
/*     */     //   111: fmul
/*     */     //   112: aload_0
/*     */     //   113: getfield swimAnimation : F
/*     */     //   116: invokevirtual rotLerpRad : (FFF)F
/*     */     //   119: putfield rotateAngleX : F
/*     */     //   122: goto -> 191
/*     */     //   125: aload_0
/*     */     //   126: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   129: fload #6
/*     */     //   131: ldc_w 0.017453292
/*     */     //   134: fmul
/*     */     //   135: putfield rotateAngleX : F
/*     */     //   138: aload_0
/*     */     //   139: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   142: getfield rotateAngleX : F
/*     */     //   145: f2d
/*     */     //   146: ldc2_w 0.4
/*     */     //   149: dcmpl
/*     */     //   150: ifle -> 166
/*     */     //   153: aload_0
/*     */     //   154: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   157: ldc_w 0.4
/*     */     //   160: putfield rotateAngleX : F
/*     */     //   163: goto -> 191
/*     */     //   166: aload_0
/*     */     //   167: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   170: getfield rotateAngleX : F
/*     */     //   173: f2d
/*     */     //   174: ldc2_w -0.4
/*     */     //   177: dcmpg
/*     */     //   178: ifge -> 191
/*     */     //   181: aload_0
/*     */     //   182: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   185: ldc_w -0.4
/*     */     //   188: putfield rotateAngleX : F
/*     */     //   191: fconst_1
/*     */     //   192: fstore #9
/*     */     //   194: aload_0
/*     */     //   195: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   198: ldc_w -0.34
/*     */     //   201: fload_2
/*     */     //   202: ldc_w 0.6662
/*     */     //   205: fmul
/*     */     //   206: ldc_w 3.1415927
/*     */     //   209: fadd
/*     */     //   210: invokestatic cos : (F)F
/*     */     //   213: ldc_w 0.8
/*     */     //   216: fmul
/*     */     //   217: fload_3
/*     */     //   218: fmul
/*     */     //   219: ldc_w 0.5
/*     */     //   222: fmul
/*     */     //   223: fload #9
/*     */     //   225: fdiv
/*     */     //   226: fadd
/*     */     //   227: putfield rotateAngleX : F
/*     */     //   230: aload_0
/*     */     //   231: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   234: ldc_w -0.34
/*     */     //   237: fload_2
/*     */     //   238: ldc_w 0.6662
/*     */     //   241: fmul
/*     */     //   242: ldc_w 3.1415927
/*     */     //   245: fadd
/*     */     //   246: invokestatic cos : (F)F
/*     */     //   249: ldc_w 0.8
/*     */     //   252: fmul
/*     */     //   253: fload_3
/*     */     //   254: fmul
/*     */     //   255: ldc_w 0.5
/*     */     //   258: fmul
/*     */     //   259: fload #9
/*     */     //   261: fdiv
/*     */     //   262: fadd
/*     */     //   263: putfield rotateAngleX : F
/*     */     //   266: aload_0
/*     */     //   267: getfield leftLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   270: fload_2
/*     */     //   271: ldc_w 0.6662
/*     */     //   274: fmul
/*     */     //   275: invokestatic cos : (F)F
/*     */     //   278: ldc_w 0.7
/*     */     //   281: fmul
/*     */     //   282: fload_3
/*     */     //   283: fmul
/*     */     //   284: fload #9
/*     */     //   286: fdiv
/*     */     //   287: putfield rotateAngleX : F
/*     */     //   290: aload_0
/*     */     //   291: getfield rightLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   294: fload_2
/*     */     //   295: ldc_w 0.6662
/*     */     //   298: fmul
/*     */     //   299: invokestatic cos : (F)F
/*     */     //   302: ldc_w 0.7
/*     */     //   305: fmul
/*     */     //   306: fload_3
/*     */     //   307: fmul
/*     */     //   308: fload #9
/*     */     //   310: fdiv
/*     */     //   311: putfield rotateAngleX : F
/*     */     //   314: aload_1
/*     */     //   315: invokevirtual getHeldItemMainhand : ()Lnet/minecraft/item/ItemStack;
/*     */     //   318: invokevirtual isEmpty : ()Z
/*     */     //   321: ifne -> 339
/*     */     //   324: aload_0
/*     */     //   325: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   328: dup
/*     */     //   329: getfield rotateAngleX : F
/*     */     //   332: ldc_w -0.15
/*     */     //   335: fadd
/*     */     //   336: putfield rotateAngleX : F
/*     */     //   339: aload_0
/*     */     //   340: getfield tail : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   343: dup
/*     */     //   344: getfield rotateAngleY : F
/*     */     //   347: f2d
/*     */     //   348: fload #4
/*     */     //   350: f2d
/*     */     //   351: ldc2_w 0.06
/*     */     //   354: dmul
/*     */     //   355: invokestatic sin : (D)D
/*     */     //   358: ldc2_w 10.0
/*     */     //   361: ddiv
/*     */     //   362: dadd
/*     */     //   363: d2f
/*     */     //   364: putfield rotateAngleY : F
/*     */     //   367: aload_0
/*     */     //   368: getfield tail : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   371: dup
/*     */     //   372: getfield rotateAngleX : F
/*     */     //   375: f2d
/*     */     //   376: fload #4
/*     */     //   378: f2d
/*     */     //   379: ldc2_w 0.02
/*     */     //   382: dmul
/*     */     //   383: invokestatic sin : (D)D
/*     */     //   386: ldc2_w 10.0
/*     */     //   389: ddiv
/*     */     //   390: dadd
/*     */     //   391: d2f
/*     */     //   392: putfield rotateAngleX : F
/*     */     //   395: aload_0
/*     */     //   396: aload_1
/*     */     //   397: getfield swingProgress : F
/*     */     //   400: putfield swingProgress : F
/*     */     //   403: aload_1
/*     */     //   404: invokestatic get : (Lnet/minecraft/entity/LivingEntity;)Lxyz/pixelatedw/mineminenomi/data/entity/entitystats/IEntityStats;
/*     */     //   407: invokeinterface isBlackLeg : ()Z
/*     */     //   412: istore #10
/*     */     //   414: aload_0
/*     */     //   415: getfield swingProgress : F
/*     */     //   418: fconst_0
/*     */     //   419: fcmpl
/*     */     //   420: ifle -> 924
/*     */     //   423: iload #10
/*     */     //   425: ifeq -> 615
/*     */     //   428: aload_0
/*     */     //   429: getfield leftLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   432: dup
/*     */     //   433: getfield rotateAngleY : F
/*     */     //   436: aload_0
/*     */     //   437: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   440: getfield rotateAngleY : F
/*     */     //   443: fadd
/*     */     //   444: putfield rotateAngleY : F
/*     */     //   447: aload_0
/*     */     //   448: getfield rightLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   451: dup
/*     */     //   452: getfield rotateAngleY : F
/*     */     //   455: aload_0
/*     */     //   456: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   459: getfield rotateAngleY : F
/*     */     //   462: fadd
/*     */     //   463: putfield rotateAngleY : F
/*     */     //   466: fconst_1
/*     */     //   467: aload_0
/*     */     //   468: getfield swingProgress : F
/*     */     //   471: fsub
/*     */     //   472: fstore #11
/*     */     //   474: fload #11
/*     */     //   476: fload #11
/*     */     //   478: fmul
/*     */     //   479: fstore #11
/*     */     //   481: fload #11
/*     */     //   483: fload #11
/*     */     //   485: fmul
/*     */     //   486: fstore #11
/*     */     //   488: fconst_1
/*     */     //   489: fload #11
/*     */     //   491: fsub
/*     */     //   492: fstore #11
/*     */     //   494: fload #11
/*     */     //   496: ldc_w 3.1415927
/*     */     //   499: fmul
/*     */     //   500: invokestatic sin : (F)F
/*     */     //   503: fstore #12
/*     */     //   505: aload_0
/*     */     //   506: getfield swingProgress : F
/*     */     //   509: ldc_w 3.1415927
/*     */     //   512: fmul
/*     */     //   513: invokestatic sin : (F)F
/*     */     //   516: aload_0
/*     */     //   517: getfield head : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   520: getfield rotateAngleX : F
/*     */     //   523: ldc_w 0.7
/*     */     //   526: fsub
/*     */     //   527: fneg
/*     */     //   528: fmul
/*     */     //   529: ldc_w 0.75
/*     */     //   532: fmul
/*     */     //   533: fstore #13
/*     */     //   535: aload_0
/*     */     //   536: getfield leftLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   539: aload_0
/*     */     //   540: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   543: getfield rotateAngleX : F
/*     */     //   546: f2d
/*     */     //   547: fload #12
/*     */     //   549: f2d
/*     */     //   550: ldc2_w 1.5
/*     */     //   553: dmul
/*     */     //   554: fload #13
/*     */     //   556: f2d
/*     */     //   557: dadd
/*     */     //   558: dsub
/*     */     //   559: d2f
/*     */     //   560: putfield rotateAngleX : F
/*     */     //   563: aload_0
/*     */     //   564: getfield leftLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   567: aload_0
/*     */     //   568: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   571: getfield rotateAngleX : F
/*     */     //   574: f2d
/*     */     //   575: fload #12
/*     */     //   577: f2d
/*     */     //   578: ldc2_w 0.5
/*     */     //   581: dmul
/*     */     //   582: fload #13
/*     */     //   584: f2d
/*     */     //   585: dadd
/*     */     //   586: dsub
/*     */     //   587: d2f
/*     */     //   588: putfield rotateAngleZ : F
/*     */     //   591: aload_0
/*     */     //   592: getfield leftLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   595: dup
/*     */     //   596: getfield rotateAngleY : F
/*     */     //   599: aload_0
/*     */     //   600: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   603: getfield rotateAngleY : F
/*     */     //   606: fconst_2
/*     */     //   607: fmul
/*     */     //   608: fadd
/*     */     //   609: putfield rotateAngleY : F
/*     */     //   612: goto -> 924
/*     */     //   615: aload_0
/*     */     //   616: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   619: aload_0
/*     */     //   620: getfield swingProgress : F
/*     */     //   623: invokestatic sqrt : (F)F
/*     */     //   626: ldc_w 6.2831855
/*     */     //   629: fmul
/*     */     //   630: invokestatic sin : (F)F
/*     */     //   633: ldc_w 0.05
/*     */     //   636: fmul
/*     */     //   637: putfield rotateAngleY : F
/*     */     //   640: aload_0
/*     */     //   641: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   644: aload_0
/*     */     //   645: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   648: getfield rotateAngleY : F
/*     */     //   651: invokestatic sin : (F)F
/*     */     //   654: ldc 5.0
/*     */     //   656: fmul
/*     */     //   657: putfield rotationPointZ : F
/*     */     //   660: aload_0
/*     */     //   661: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   664: aload_0
/*     */     //   665: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   668: getfield rotateAngleY : F
/*     */     //   671: invokestatic cos : (F)F
/*     */     //   674: fneg
/*     */     //   675: ldc 8.0
/*     */     //   677: fmul
/*     */     //   678: putfield rotationPointX : F
/*     */     //   681: aload_0
/*     */     //   682: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   685: aload_0
/*     */     //   686: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   689: getfield rotateAngleY : F
/*     */     //   692: invokestatic sin : (F)F
/*     */     //   695: fneg
/*     */     //   696: ldc 5.0
/*     */     //   698: fmul
/*     */     //   699: putfield rotationPointZ : F
/*     */     //   702: aload_0
/*     */     //   703: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   706: aload_0
/*     */     //   707: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   710: getfield rotateAngleY : F
/*     */     //   713: invokestatic cos : (F)F
/*     */     //   716: ldc 8.0
/*     */     //   718: fmul
/*     */     //   719: putfield rotationPointX : F
/*     */     //   722: aload_0
/*     */     //   723: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   726: dup
/*     */     //   727: getfield rotateAngleY : F
/*     */     //   730: aload_0
/*     */     //   731: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   734: getfield rotateAngleY : F
/*     */     //   737: fadd
/*     */     //   738: putfield rotateAngleY : F
/*     */     //   741: aload_0
/*     */     //   742: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   745: dup
/*     */     //   746: getfield rotateAngleY : F
/*     */     //   749: aload_0
/*     */     //   750: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   753: getfield rotateAngleY : F
/*     */     //   756: fadd
/*     */     //   757: putfield rotateAngleY : F
/*     */     //   760: aload_0
/*     */     //   761: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   764: dup
/*     */     //   765: getfield rotateAngleX : F
/*     */     //   768: aload_0
/*     */     //   769: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   772: getfield rotateAngleY : F
/*     */     //   775: fadd
/*     */     //   776: putfield rotateAngleX : F
/*     */     //   779: fconst_1
/*     */     //   780: aload_0
/*     */     //   781: getfield swingProgress : F
/*     */     //   784: fsub
/*     */     //   785: fstore #11
/*     */     //   787: fload #11
/*     */     //   789: fload #11
/*     */     //   791: fmul
/*     */     //   792: fstore #11
/*     */     //   794: fload #11
/*     */     //   796: fload #11
/*     */     //   798: fmul
/*     */     //   799: fstore #11
/*     */     //   801: fconst_1
/*     */     //   802: fload #11
/*     */     //   804: fsub
/*     */     //   805: fstore #11
/*     */     //   807: fload #11
/*     */     //   809: ldc_w 3.1415927
/*     */     //   812: fmul
/*     */     //   813: invokestatic sin : (F)F
/*     */     //   816: fstore #12
/*     */     //   818: aload_0
/*     */     //   819: getfield swingProgress : F
/*     */     //   822: ldc_w 3.1415927
/*     */     //   825: fmul
/*     */     //   826: invokestatic sin : (F)F
/*     */     //   829: aload_0
/*     */     //   830: getfield head : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   833: getfield rotateAngleX : F
/*     */     //   836: ldc_w 0.7
/*     */     //   839: fsub
/*     */     //   840: fneg
/*     */     //   841: fmul
/*     */     //   842: ldc_w 0.75
/*     */     //   845: fmul
/*     */     //   846: fstore #13
/*     */     //   848: aload_0
/*     */     //   849: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   852: aload_0
/*     */     //   853: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   856: getfield rotateAngleX : F
/*     */     //   859: f2d
/*     */     //   860: fload #12
/*     */     //   862: f2d
/*     */     //   863: ldc2_w 1.5
/*     */     //   866: dmul
/*     */     //   867: fload #13
/*     */     //   869: f2d
/*     */     //   870: dadd
/*     */     //   871: dsub
/*     */     //   872: d2f
/*     */     //   873: putfield rotateAngleX : F
/*     */     //   876: aload_0
/*     */     //   877: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   880: dup
/*     */     //   881: getfield rotateAngleY : F
/*     */     //   884: aload_0
/*     */     //   885: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   888: getfield rotateAngleY : F
/*     */     //   891: fconst_2
/*     */     //   892: fmul
/*     */     //   893: fadd
/*     */     //   894: putfield rotateAngleY : F
/*     */     //   897: aload_0
/*     */     //   898: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
/*     */     //   901: dup
/*     */     //   902: getfield rotateAngleZ : F
/*     */     //   905: aload_0
/*     */     //   906: getfield swingProgress : F
/*     */     //   909: ldc_w 3.1415927
/*     */     //   912: fmul
/*     */     //   913: invokestatic sin : (F)F
/*     */     //   916: ldc_w -0.9
/*     */     //   919: fmul
/*     */     //   920: fadd
/*     */     //   921: putfield rotateAngleZ : F
/*     */     //   924: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #252	-> 0
/*     */     //   #253	-> 15
/*     */     //   #254	-> 21
/*     */     //   #255	-> 34
/*     */     //   #256	-> 39
/*     */     //   #257	-> 52
/*     */     //   #259	-> 61
/*     */     //   #260	-> 66
/*     */     //   #262	-> 94
/*     */     //   #266	-> 125
/*     */     //   #267	-> 138
/*     */     //   #268	-> 153
/*     */     //   #269	-> 166
/*     */     //   #270	-> 181
/*     */     //   #274	-> 191
/*     */     //   #275	-> 194
/*     */     //   #276	-> 230
/*     */     //   #277	-> 266
/*     */     //   #278	-> 290
/*     */     //   #279	-> 314
/*     */     //   #280	-> 324
/*     */     //   #282	-> 339
/*     */     //   #283	-> 367
/*     */     //   #286	-> 395
/*     */     //   #287	-> 403
/*     */     //   #288	-> 414
/*     */     //   #290	-> 423
/*     */     //   #292	-> 428
/*     */     //   #293	-> 447
/*     */     //   #294	-> 466
/*     */     //   #295	-> 474
/*     */     //   #296	-> 481
/*     */     //   #297	-> 488
/*     */     //   #298	-> 494
/*     */     //   #299	-> 505
/*     */     //   #300	-> 535
/*     */     //   #301	-> 563
/*     */     //   #302	-> 591
/*     */     //   #303	-> 612
/*     */     //   #306	-> 615
/*     */     //   #307	-> 640
/*     */     //   #308	-> 660
/*     */     //   #309	-> 681
/*     */     //   #310	-> 702
/*     */     //   #311	-> 722
/*     */     //   #312	-> 741
/*     */     //   #313	-> 760
/*     */     //   #314	-> 779
/*     */     //   #315	-> 787
/*     */     //   #316	-> 794
/*     */     //   #317	-> 801
/*     */     //   #318	-> 807
/*     */     //   #319	-> 818
/*     */     //   #320	-> 848
/*     */     //   #321	-> 876
/*     */     //   #322	-> 897
/*     */     //   #325	-> 924
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   474	138	11	f1	F
/*     */     //   505	107	12	f2	F
/*     */     //   535	77	13	f3	F
/*     */     //   787	137	11	f1	F
/*     */     //   818	106	12	f2	F
/*     */     //   848	76	13	f3	F
/*     */     //   0	925	0	this	Lxyz/pixelatedw/mineminenomi/models/entities/zoans/AxolotlHeavyModel;
/*     */     //   0	925	1	entity	Lnet/minecraft/entity/LivingEntity;
/*     */     //   0	925	2	limbSwing	F
/*     */     //   0	925	3	limbSwingAmount	F
/*     */     //   0	925	4	ageInTicks	F
/*     */     //   0	925	5	netHeadYaw	F
/*     */     //   0	925	6	headPitch	F
/*     */     //   15	910	7	flag	Z
/*     */     //   21	904	8	flag1	Z
/*     */     //   194	731	9	f	F
/*     */     //   414	511	10	isBlackLeg	Z
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	925	0	this	Lxyz/pixelatedw/mineminenomi/models/entities/zoans/AxolotlHeavyModel<TT;>;
/*     */     //   0	925	1	entity	TT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 330 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 331 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
/* 332 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 333 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 334 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 335 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 336 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 341 */     modelRenderer.rotateAngleX = x;
/* 342 */     modelRenderer.rotateAngleY = y;
/* 343 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 349 */     super.translateHand(side, matrixStack);
/* 350 */     if (side == HandSide.RIGHT) {
/* 351 */       matrixStack.translate(-0.3D, 1.1D, 0.1D);
/*     */     } else {
/* 353 */       matrixStack.translate(0.3D, 1.1D, 0.1D);
/* 354 */     }  matrixStack.rotate(Vector3f.XP.rotationDegrees(-20.0F));
/* 355 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 361 */     if (side == HandSide.RIGHT) {
/*     */       
/* 363 */       matrixStack.translate(0.2D, 0.3D, 0.3D);
/* 364 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 365 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
/* 366 */       this.rightArm2_r1.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 370 */       matrixStack.translate(-0.2D, 0.3D, 0.3D);
/* 371 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 372 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
/* 373 */       this.leftArm2_r1.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 380 */     if (side == HandSide.RIGHT) {
/*     */       
/* 382 */       matrixStack.translate(0.4D, -0.8D, -0.1D);
/* 383 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 384 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 388 */       matrixStack.translate(-0.4D, -0.8D, -0.1D);
/* 389 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(10.0F));
/* 390 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\AxolotlHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */