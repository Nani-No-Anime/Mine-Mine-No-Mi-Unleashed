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
/*     */ 
/*     */ public class AllosaurusWalkModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T>
/*     */   implements IHasArm {
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftFeet;
/*     */   private final ModelRenderer leftFeet2;
/*     */   private final ModelRenderer leftToe1;
/*     */   private final ModelRenderer leftToe2;
/*     */   private final ModelRenderer leftToe3;
/*     */   private final ModelRenderer leftToe4;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightFeet;
/*     */   private final ModelRenderer rightFeet2;
/*     */   private final ModelRenderer rightToe1;
/*     */   private final ModelRenderer rightToe2;
/*     */   private final ModelRenderer rightToe3;
/*     */   private final ModelRenderer rightToe4;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightHand;
/*     */   private final ModelRenderer rightFinger1;
/*     */   private final ModelRenderer rightFinger2;
/*     */   private final ModelRenderer rightFinger3;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand;
/*     */   private final ModelRenderer leftFinger1;
/*     */   private final ModelRenderer leftFinger2;
/*     */   private final ModelRenderer leftFinger3;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer neck3;
/*     */   private final ModelRenderer neck2;
/*     */   private final ModelRenderer neck1;
/*     */   private final ModelRenderer upperHead;
/*     */   private final ModelRenderer upperTeeth;
/*     */   private final ModelRenderer lowerHead;
/*     */   private final ModelRenderer lowerTeeth;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer tail4;
/*     */   private final ModelRenderer tail5;
/*     */   private final ModelRenderer tail6;
/*     */   private final ModelRenderer tail7;
/*     */   private final ModelRenderer tail8;
/*     */   
/*     */   public AllosaurusWalkModel() {
/*  65 */     super(1.0F);
/*  66 */     this.textureWidth = 256;
/*  67 */     this.textureHeight = 256;
/*     */     
/*  69 */     this.leftLeg = new ModelRenderer((Model)this);
/*  70 */     this.leftLeg.setRotationPoint(6.5F, -10.564F, 16.0191F);
/*  71 */     setRotationAngle(this.leftLeg, 0.2182F, 0.0F, 0.0F);
/*  72 */     this.leftLeg.setTextureOffset(52, 80).addBox(0.0F, -12.0F, -7.0F, 7.0F, 24.0F, 14.0F, 0.0F, false);
/*     */     
/*  74 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  75 */     this.leftLeg2.setRotationPoint(0.0F, 9.4613F, -5.3088F);
/*  76 */     this.leftLeg.addChild(this.leftLeg2);
/*  77 */     setRotationAngle(this.leftLeg2, -0.6981F, 0.0F, 0.0F);
/*  78 */     this.leftLeg2.setTextureOffset(46, 120).addBox(0.5F, -4.6299F, 0.6175F, 6.0F, 27.0F, 8.0F, 0.0F, false);
/*     */     
/*  80 */     this.leftFeet = new ModelRenderer((Model)this);
/*  81 */     this.leftFeet.setRotationPoint(0.0F, 21.6027F, 5.2897F);
/*  82 */     this.leftLeg2.addChild(this.leftFeet);
/*  83 */     setRotationAngle(this.leftFeet, 0.48F, 0.0F, 0.0F);
/*  84 */     this.leftFeet.setTextureOffset(95, 92).addBox(-1.0F, -2.5F, -5.0F, 9.0F, 5.0F, 10.0F, 0.0F, false);
/*     */     
/*  86 */     this.leftFeet2 = new ModelRenderer((Model)this);
/*  87 */     this.leftFeet2.setRotationPoint(0.0F, 0.5F, -3.0F);
/*  88 */     this.leftFeet.addChild(this.leftFeet2);
/*  89 */     this.leftFeet2.setTextureOffset(94, 109).addBox(-1.5F, -2.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
/*     */     
/*  91 */     this.leftToe1 = new ModelRenderer((Model)this);
/*  92 */     this.leftToe1.setRotationPoint(3.8689F, 1.01F, -6.0793F);
/*  93 */     this.leftFeet2.addChild(this.leftToe1);
/*  94 */     setRotationAngle(this.leftToe1, 0.2075F, -0.2612F, -0.0036F);
/*  95 */     this.leftToe1.setTextureOffset(104, 125).addBox(1.8812F, -1.6739F, -3.3871F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/*  97 */     this.leftToe2 = new ModelRenderer((Model)this);
/*  98 */     this.leftToe2.setRotationPoint(0.0F, 0.9569F, -6.7225F);
/*  99 */     this.leftFeet2.addChild(this.leftToe2);
/* 100 */     setRotationAngle(this.leftToe2, 0.1745F, 0.0F, 0.0F);
/* 101 */     this.leftToe2.setTextureOffset(104, 125).addBox(2.0F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 103 */     this.leftToe3 = new ModelRenderer((Model)this);
/* 104 */     this.leftToe3.setRotationPoint(-3.7618F, 0.7739F, -5.8025F);
/* 105 */     this.leftFeet2.addChild(this.leftToe3);
/* 106 */     setRotationAngle(this.leftToe3, 0.2188F, 0.1744F, 0.0077F);
/* 107 */     this.leftToe3.setTextureOffset(104, 125).addBox(1.9468F, -1.3945F, -2.4015F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 109 */     this.leftToe4 = new ModelRenderer((Model)this);
/* 110 */     this.leftToe4.setRotationPoint(0.0F, -2.4597F, 7.6541F);
/* 111 */     this.leftFeet2.addChild(this.leftToe4);
/* 112 */     setRotationAngle(this.leftToe4, -0.5672F, 0.0F, 0.0F);
/* 113 */     this.leftToe4.setTextureOffset(104, 125).addBox(2.0F, -1.5F, -1.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 115 */     this.rightLeg = new ModelRenderer((Model)this);
/* 116 */     this.rightLeg.setRotationPoint(-13.5F, -10.564F, 16.0191F);
/* 117 */     setRotationAngle(this.rightLeg, 0.2182F, 0.0F, 0.0F);
/* 118 */     this.rightLeg.setTextureOffset(52, 80).addBox(0.0F, -12.0F, -7.0F, 7.0F, 24.0F, 14.0F, 0.0F, false);
/*     */     
/* 120 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 121 */     this.rightLeg2.setRotationPoint(0.0F, 9.4613F, -5.3088F);
/* 122 */     this.rightLeg.addChild(this.rightLeg2);
/* 123 */     setRotationAngle(this.rightLeg2, -0.6981F, 0.0F, 0.0F);
/* 124 */     this.rightLeg2.setTextureOffset(46, 120).addBox(0.5F, -4.6299F, 0.6175F, 6.0F, 27.0F, 8.0F, 0.0F, false);
/*     */     
/* 126 */     this.rightFeet = new ModelRenderer((Model)this);
/* 127 */     this.rightFeet.setRotationPoint(0.0F, 21.6027F, 5.2897F);
/* 128 */     this.rightLeg2.addChild(this.rightFeet);
/* 129 */     setRotationAngle(this.rightFeet, 0.48F, 0.0F, 0.0F);
/* 130 */     this.rightFeet.setTextureOffset(95, 92).addBox(-1.0F, -2.5F, -5.0F, 9.0F, 5.0F, 10.0F, 0.0F, false);
/*     */     
/* 132 */     this.rightFeet2 = new ModelRenderer((Model)this);
/* 133 */     this.rightFeet2.setRotationPoint(0.0F, 0.5F, -3.0F);
/* 134 */     this.rightFeet.addChild(this.rightFeet2);
/* 135 */     this.rightFeet2.setTextureOffset(94, 109).addBox(-1.5F, -2.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
/*     */     
/* 137 */     this.rightToe1 = new ModelRenderer((Model)this);
/* 138 */     this.rightToe1.setRotationPoint(3.8689F, 1.01F, -6.0793F);
/* 139 */     this.rightFeet2.addChild(this.rightToe1);
/* 140 */     setRotationAngle(this.rightToe1, 0.2075F, -0.2612F, -0.0036F);
/* 141 */     this.rightToe1.setTextureOffset(104, 125).addBox(1.8812F, -1.6739F, -3.3871F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 143 */     this.rightToe2 = new ModelRenderer((Model)this);
/* 144 */     this.rightToe2.setRotationPoint(0.0F, 0.9569F, -6.7225F);
/* 145 */     this.rightFeet2.addChild(this.rightToe2);
/* 146 */     setRotationAngle(this.rightToe2, 0.1745F, 0.0F, 0.0F);
/* 147 */     this.rightToe2.setTextureOffset(104, 125).addBox(2.0F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 149 */     this.rightToe3 = new ModelRenderer((Model)this);
/* 150 */     this.rightToe3.setRotationPoint(-3.7618F, 0.7739F, -5.8025F);
/* 151 */     this.rightFeet2.addChild(this.rightToe3);
/* 152 */     setRotationAngle(this.rightToe3, 0.2188F, 0.1744F, 0.0077F);
/* 153 */     this.rightToe3.setTextureOffset(104, 125).addBox(1.9468F, -1.3945F, -2.4015F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 155 */     this.rightToe4 = new ModelRenderer((Model)this);
/* 156 */     this.rightToe4.setRotationPoint(0.0F, -2.4597F, 7.6541F);
/* 157 */     this.rightFeet2.addChild(this.rightToe4);
/* 158 */     setRotationAngle(this.rightToe4, -0.5672F, 0.0F, 0.0F);
/* 159 */     this.rightToe4.setTextureOffset(104, 125).addBox(2.0F, -1.5F, -1.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 161 */     this.rightArm = new ModelRenderer((Model)this);
/* 162 */     this.rightArm.setRotationPoint(-13.0F, -7.0F, -16.0F);
/* 163 */     setRotationAngle(this.rightArm, 0.3054F, 0.0F, 0.0F);
/* 164 */     this.rightArm.setTextureOffset(68, 169).addBox(0.5F, -2.5559F, -3.1355F, 6.0F, 11.0F, 6.0F, 0.0F, false);
/*     */     
/* 166 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 167 */     this.rightArm2.setRotationPoint(0.0F, 7.4026F, -1.1698F);
/* 168 */     this.rightArm.addChild(this.rightArm2);
/* 169 */     setRotationAngle(this.rightArm2, -1.309F, 0.0F, 0.0F);
/* 170 */     this.rightArm2.setTextureOffset(69, 187).addBox(1.0F, -2.6022F, -3.2765F, 5.0F, 11.0F, 5.0F, 0.0F, false);
/*     */     
/* 172 */     this.rightHand = new ModelRenderer((Model)this);
/* 173 */     this.rightHand.setRotationPoint(0.0F, 9.3131F, -0.4006F);
/* 174 */     this.rightArm2.addChild(this.rightHand);
/* 175 */     setRotationAngle(this.rightHand, 0.6545F, 0.0F, 0.0F);
/* 176 */     this.rightHand.setTextureOffset(69, 205).addBox(0.0F, -2.5F, -1.5F, 7.0F, 5.0F, 3.0F, 0.0F, false);
/*     */     
/* 178 */     this.rightFinger1 = new ModelRenderer((Model)this);
/* 179 */     this.rightFinger1.setRotationPoint(2.25F, 4.4447F, 0.6321F);
/* 180 */     this.rightHand.addChild(this.rightFinger1);
/* 181 */     setRotationAngle(this.rightFinger1, -1.2217F, 0.0F, 0.0F);
/* 182 */     this.rightFinger1.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 184 */     this.rightFinger2 = new ModelRenderer((Model)this);
/* 185 */     this.rightFinger2.setRotationPoint(0.0F, 4.5397F, 0.4151F);
/* 186 */     this.rightHand.addChild(this.rightFinger2);
/* 187 */     setRotationAngle(this.rightFinger2, -1.2217F, 0.0F, 0.0F);
/* 188 */     this.rightFinger2.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 190 */     this.rightFinger3 = new ModelRenderer((Model)this);
/* 191 */     this.rightFinger3.setRotationPoint(-2.25F, 4.3628F, 0.7644F);
/* 192 */     this.rightHand.addChild(this.rightFinger3);
/* 193 */     setRotationAngle(this.rightFinger3, -1.2217F, 0.0F, 0.0F);
/* 194 */     this.rightFinger3.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 196 */     this.leftArm = new ModelRenderer((Model)this);
/* 197 */     this.leftArm.setRotationPoint(6.0F, -7.0F, -16.0F);
/* 198 */     setRotationAngle(this.leftArm, 0.3054F, 0.0F, 0.0F);
/* 199 */     this.leftArm.setTextureOffset(68, 169).addBox(0.5F, -2.5559F, -3.1355F, 6.0F, 11.0F, 6.0F, 0.0F, false);
/*     */     
/* 201 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 202 */     this.leftArm2.setRotationPoint(0.0F, 7.4026F, -1.1698F);
/* 203 */     this.leftArm.addChild(this.leftArm2);
/* 204 */     setRotationAngle(this.leftArm2, -1.309F, 0.0F, 0.0F);
/* 205 */     this.leftArm2.setTextureOffset(69, 187).addBox(1.0F, -2.6022F, -3.2765F, 5.0F, 11.0F, 5.0F, 0.0F, false);
/*     */     
/* 207 */     this.leftHand = new ModelRenderer((Model)this);
/* 208 */     this.leftHand.setRotationPoint(0.0F, 9.3131F, -0.4006F);
/* 209 */     this.leftArm2.addChild(this.leftHand);
/* 210 */     setRotationAngle(this.leftHand, 0.6545F, 0.0F, 0.0F);
/* 211 */     this.leftHand.setTextureOffset(69, 205).addBox(0.0F, -2.5F, -1.5F, 7.0F, 5.0F, 3.0F, 0.0F, false);
/*     */     
/* 213 */     this.leftFinger1 = new ModelRenderer((Model)this);
/* 214 */     this.leftFinger1.setRotationPoint(2.25F, 4.4447F, 0.6321F);
/* 215 */     this.leftHand.addChild(this.leftFinger1);
/* 216 */     setRotationAngle(this.leftFinger1, -1.2217F, 0.0F, 0.0F);
/* 217 */     this.leftFinger1.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 219 */     this.leftFinger2 = new ModelRenderer((Model)this);
/* 220 */     this.leftFinger2.setRotationPoint(0.0F, 4.5397F, 0.4151F);
/* 221 */     this.leftHand.addChild(this.leftFinger2);
/* 222 */     setRotationAngle(this.leftFinger2, -1.2217F, 0.0F, 0.0F);
/* 223 */     this.leftFinger2.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 225 */     this.leftFinger3 = new ModelRenderer((Model)this);
/* 226 */     this.leftFinger3.setRotationPoint(-2.25F, 4.3628F, 0.7644F);
/* 227 */     this.leftHand.addChild(this.leftFinger3);
/* 228 */     setRotationAngle(this.leftFinger3, -1.2217F, 0.0F, 0.0F);
/* 229 */     this.leftFinger3.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 231 */     this.body = new ModelRenderer((Model)this);
/* 232 */     this.body.setRotationPoint(-3.5F, -14.9286F, -4.2143F);
/* 233 */     this.body.setTextureOffset(0, 0).addBox(-7.0F, -13.0714F, -10.7857F, 21.0F, 27.0F, 16.0F, 0.0F, false);
/* 234 */     this.body.setTextureOffset(1, 156).addBox(-6.0F, -12.0714F, -16.7857F, 19.0F, 24.0F, 6.0F, 0.0F, false);
/* 235 */     this.body.setTextureOffset(3, 188).addBox(-5.0F, -11.0714F, -21.7857F, 17.0F, 21.0F, 5.0F, 0.0F, false);
/* 236 */     this.body.setTextureOffset(8, 217).addBox(-3.5F, -10.0714F, -23.7857F, 14.0F, 18.0F, 2.0F, 0.0F, false);
/* 237 */     this.body.setTextureOffset(0, 43).addBox(-6.0F, -12.0714F, 5.2143F, 19.0F, 25.0F, 12.0F, 0.0F, false);
/* 238 */     this.body.setTextureOffset(0, 81).addBox(-5.5F, -11.5714F, 17.2143F, 18.0F, 24.0F, 7.0F, 0.0F, false);
/* 239 */     this.body.setTextureOffset(0, 115).addBox(-4.5F, -10.5714F, 24.2143F, 16.0F, 22.0F, 5.0F, 0.0F, false);
/*     */     
/* 241 */     this.head = new ModelRenderer((Model)this);
/* 242 */     this.head.setRotationPoint(-3.5F, -19.3015F, -23.9587F);
/* 243 */     this.head.setTextureOffset(75, 146).addBox(-3.5F, -14.1985F, -23.0413F, 14.0F, 15.0F, 5.0F, 0.03F, false);
/*     */     
/* 245 */     this.neck3 = new ModelRenderer((Model)this);
/* 246 */     this.neck3.setRotationPoint(3.5F, -6.3693F, -16.9473F);
/* 247 */     this.head.addChild(this.neck3);
/* 248 */     setRotationAngle(this.neck3, 1.3963F, 0.0F, 0.0F);
/* 249 */     this.neck3.setTextureOffset(106, 52).addBox(-6.5F, -3.5F, -7.0F, 13.0F, 7.0F, 14.0F, 0.02F, false);
/*     */     
/* 251 */     this.neck2 = new ModelRenderer((Model)this);
/* 252 */     this.neck2.setRotationPoint(3.5F, -4.5383F, -10.4505F);
/* 253 */     this.head.addChild(this.neck2);
/* 254 */     setRotationAngle(this.neck2, 1.2217F, 0.0F, 0.0F);
/* 255 */     this.neck2.setTextureOffset(74, 0).addBox(-6.5F, -4.5F, -7.0F, 13.0F, 9.0F, 14.0F, 0.01F, false);
/*     */     
/* 257 */     this.neck1 = new ModelRenderer((Model)this);
/* 258 */     this.neck1.setRotationPoint(3.5F, 1.606F, -0.0608F);
/* 259 */     this.head.addChild(this.neck1);
/* 260 */     setRotationAngle(this.neck1, 0.9599F, 0.0F, 0.0F);
/* 261 */     this.neck1.setTextureOffset(62, 29).addBox(-6.5F, -9.5F, -7.0F, 13.0F, 19.0F, 14.0F, 0.0F, false);
/*     */     
/* 263 */     this.upperHead = new ModelRenderer((Model)this);
/* 264 */     this.upperHead.setRotationPoint(0.0F, -9.0255F, -22.121F);
/* 265 */     this.head.addChild(this.upperHead);
/* 266 */     setRotationAngle(this.upperHead, -0.1309F, 0.0F, 0.0F);
/* 267 */     this.upperHead.setTextureOffset(117, 149).addBox(-3.5F, -5.1947F, -6.7478F, 14.0F, 12.0F, 7.0F, 0.0F, false);
/* 268 */     this.upperHead.setTextureOffset(116, 173).addBox(-2.5F, -4.1947F, -12.7478F, 12.0F, 11.0F, 6.0F, 0.0F, false);
/* 269 */     this.upperHead.setTextureOffset(117, 194).addBox(-1.5F, -3.1947F, -20.7478F, 10.0F, 10.0F, 8.0F, 0.0F, false);
/*     */     
/* 271 */     this.upperTeeth = new ModelRenderer((Model)this);
/* 272 */     this.upperTeeth.setRotationPoint(0.0F, 7.8053F, -11.0335F);
/* 273 */     this.upperHead.addChild(this.upperTeeth);
/* 274 */     this.upperTeeth.setTextureOffset(102, 20).addBox(7.5F, -1.5F, -8.7143F, 0.0F, 3.0F, 20.0F, 0.0F, false);
/* 275 */     this.upperTeeth.setTextureOffset(102, 17).addBox(-0.5F, -1.5F, -8.7143F, 0.0F, 3.0F, 20.0F, 0.0F, false);
/* 276 */     this.upperTeeth.setTextureOffset(58, 10).addBox(0.0F, -1.5F, -8.7143F, 7.0F, 3.0F, 0.0F, 0.0F, false);
/*     */     
/* 278 */     this.lowerHead = new ModelRenderer((Model)this);
/* 279 */     this.lowerHead.setRotationPoint(0.0F, -1.1857F, -21.4852F);
/* 280 */     this.head.addChild(this.lowerHead);
/* 281 */     setRotationAngle(this.lowerHead, 0.2182F, 0.0F, 0.0F);
/* 282 */     this.lowerHead.setTextureOffset(92, 241).addBox(-0.5F, -0.7355F, -21.9123F, 8.0F, 3.0F, 9.0F, 0.0F, false);
/* 283 */     this.lowerHead.setTextureOffset(94, 230).addBox(-1.5F, -0.7355F, -12.9123F, 10.0F, 3.0F, 6.0F, 0.0F, false);
/* 284 */     this.lowerHead.setTextureOffset(93, 219).addBox(-2.5F, -0.7355F, -6.9123F, 12.0F, 3.0F, 6.0F, 0.0F, false);
/*     */     
/* 286 */     this.lowerTeeth = new ModelRenderer((Model)this);
/* 287 */     this.lowerTeeth.setRotationPoint(0.0F, -1.7355F, -12.198F);
/* 288 */     this.lowerHead.addChild(this.lowerTeeth);
/* 289 */     this.lowerTeeth.setTextureOffset(102, 14).addBox(7.25F, -1.5F, -8.7143F, 0.0F, 3.0F, 20.0F, 0.0F, false);
/* 290 */     this.lowerTeeth.setTextureOffset(102, 11).addBox(-0.25F, -1.5F, -8.7143F, 0.0F, 3.0F, 20.0F, 0.0F, false);
/* 291 */     this.lowerTeeth.setTextureOffset(58, 13).addBox(0.0F, -1.5F, -8.7143F, 7.0F, 3.0F, 0.0F, 0.0F, false);
/*     */     
/* 293 */     this.tail = new ModelRenderer((Model)this);
/* 294 */     this.tail.setRotationPoint(-3.5F, -15.5F, 62.625F);
/* 295 */     this.tail.setTextureOffset(166, 4).addBox(-3.5F, -7.5F, -37.625F, 14.0F, 15.0F, 5.0F, 0.0F, false);
/*     */     
/* 297 */     this.tail2 = new ModelRenderer((Model)this);
/* 298 */     this.tail2.setRotationPoint(0.0F, 0.0F, -33.0F);
/* 299 */     this.tail.addChild(this.tail2);
/* 300 */     this.tail2.setTextureOffset(167, 26).addBox(-2.0F, -6.0F, 0.375F, 11.0F, 12.0F, 6.0F, 0.0F, false);
/*     */     
/* 302 */     this.tail3 = new ModelRenderer((Model)this);
/* 303 */     this.tail3.setRotationPoint(0.0F, 0.0F, 7.0F);
/* 304 */     this.tail2.addChild(this.tail3);
/* 305 */     this.tail3.setTextureOffset(165, 47).addBox(-1.5F, -5.0F, -0.625F, 10.0F, 10.0F, 10.0F, 0.0F, false);
/*     */     
/* 307 */     this.tail4 = new ModelRenderer((Model)this);
/* 308 */     this.tail4.setRotationPoint(0.0F, 0.0F, 9.0F);
/* 309 */     this.tail3.addChild(this.tail4);
/* 310 */     this.tail4.setTextureOffset(165, 69).addBox(-1.0F, -4.5F, -0.125F, 9.0F, 9.0F, 11.0F, 0.0F, false);
/*     */     
/* 312 */     this.tail5 = new ModelRenderer((Model)this);
/* 313 */     this.tail5.setRotationPoint(0.0F, 0.0F, 11.0F);
/* 314 */     this.tail4.addChild(this.tail5);
/* 315 */     this.tail5.setTextureOffset(160, 93).addBox(-0.5F, -4.0F, -0.125F, 8.0F, 8.0F, 16.0F, 0.0F, false);
/*     */     
/* 317 */     this.tail6 = new ModelRenderer((Model)this);
/* 318 */     this.tail6.setRotationPoint(0.0F, 0.0F, 16.0F);
/* 319 */     this.tail5.addChild(this.tail6);
/* 320 */     this.tail6.setTextureOffset(161, 119).addBox(0.0F, -3.5F, -0.625F, 7.0F, 7.0F, 15.0F, 0.0F, false);
/*     */     
/* 322 */     this.tail7 = new ModelRenderer((Model)this);
/* 323 */     this.tail7.setRotationPoint(0.0F, 0.0F, 14.0F);
/* 324 */     this.tail6.addChild(this.tail7);
/* 325 */     this.tail7.setTextureOffset(163, 144).addBox(0.5F, -3.0F, 0.375F, 6.0F, 6.0F, 15.0F, 0.0F, false);
/*     */     
/* 327 */     this.tail8 = new ModelRenderer((Model)this);
/* 328 */     this.tail8.setRotationPoint(0.0F, 0.0F, 15.0F);
/* 329 */     this.tail7.addChild(this.tail8);
/* 330 */     this.tail8.setTextureOffset(161, 169).addBox(1.0F, -2.5F, 0.375F, 5.0F, 5.0F, 16.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 336 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 337 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 338 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 339 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 340 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 341 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 342 */     this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 349 */     float limbSpeed = 0.3F;
/* 350 */     if (entity.isSprinting())
/* 351 */       limbSpeed = 0.5F; 
/* 352 */     this.rightLeg.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
/* 353 */     this.rightLeg2.rotateAngleX = -0.7F - MathHelper.sin(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
/* 354 */     this.leftLeg.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;
/* 355 */     this.leftLeg2.rotateAngleX = -0.7F - MathHelper.sin(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;
/*     */ 
/*     */ 
/*     */     
/* 359 */     if (!(entity instanceof xyz.pixelatedw.mineminenomi.entities.DummyEntity))
/*     */     {
/* 361 */       if (entity.isSprinting()) {
/*     */         
/* 363 */         this.tail3.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 0.1F * limbSwingAmount;
/* 364 */         this.tail5.rotateAngleY = (float)(this.tail5.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
/* 365 */         this.tail5.rotateAngleX = (float)(this.tail5.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/* 366 */         this.tail6.rotateAngleY = (float)(this.tail6.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
/* 367 */         this.tail6.rotateAngleX = (float)(this.tail6.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/* 368 */         this.tail7.rotateAngleY = (float)(this.tail7.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
/* 369 */         this.tail7.rotateAngleX = (float)(this.tail7.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/* 370 */         this.tail8.rotateAngleY = (float)(this.tail8.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
/* 371 */         this.tail8.rotateAngleX = (float)(this.tail8.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/*     */       }
/*     */       else {
/*     */         
/* 375 */         this.tail2.rotateAngleY = (float)(this.tail2.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 20.0D);
/* 376 */         this.tail2.rotateAngleX = (float)(this.tail2.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 10.0D);
/* 377 */         this.tail3.rotateAngleY = (float)(this.tail3.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 20.0D);
/* 378 */         this.tail3.rotateAngleX = (float)(this.tail3.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 10.0D);
/* 379 */         this.tail4.rotateAngleY = (float)(this.tail4.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 20.0D);
/* 380 */         this.tail4.rotateAngleX = (float)(this.tail4.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 10.0D);
/* 381 */         this.tail5.rotateAngleY = (float)(this.tail5.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
/* 382 */         this.tail5.rotateAngleX = (float)(this.tail5.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/* 383 */         this.tail6.rotateAngleY = (float)(this.tail6.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
/* 384 */         this.tail6.rotateAngleX = (float)(this.tail6.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/* 385 */         this.tail7.rotateAngleY = (float)(this.tail7.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
/* 386 */         this.tail7.rotateAngleX = (float)(this.tail7.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/* 387 */         this.tail8.rotateAngleY = (float)(this.tail8.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
/* 388 */         this.tail8.rotateAngleX = (float)(this.tail8.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 393 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 394 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 396 */       this.head.rotateAngleY += this.body.rotateAngleY;
/* 397 */       float f1 = 1.0F - this.swingProgress;
/* 398 */       f1 *= f1;
/* 399 */       f1 *= f1;
/* 400 */       f1 = 1.0F - f1;
/* 401 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 402 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
/* 403 */       this.head.rotateAngleX = (float)(this.head.rotateAngleX + f2 * 0.8D + f3);
/* 404 */       this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.8F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 410 */     modelRenderer.rotateAngleX = x;
/* 411 */     modelRenderer.rotateAngleY = y;
/* 412 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 418 */     this.head.translateRotate(matrixStack);
/* 419 */     matrixStack.scale(2.0F, 2.0F, 2.0F);
/* 420 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 421 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
/* 422 */     matrixStack.translate(0.05D, 0.5D, 0.1D);
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\AllosaurusWalkModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */