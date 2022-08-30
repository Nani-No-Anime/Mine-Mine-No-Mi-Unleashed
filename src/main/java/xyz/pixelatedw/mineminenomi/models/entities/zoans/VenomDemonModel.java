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
/*     */ 
/*     */ public class VenomDemonModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer jaw;
/*     */   private final ModelRenderer mouth;
/*     */   private final ModelRenderer rightHorn;
/*     */   private final ModelRenderer rightHorn2;
/*     */   private final ModelRenderer rightHorn3;
/*     */   private final ModelRenderer leftHorn;
/*     */   private final ModelRenderer leftHorn2;
/*     */   private final ModelRenderer leftHorn3;
/*     */   private final ModelRenderer leftHorn3_r1;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer body4;
/*     */   private final ModelRenderer body5;
/*     */   private final ModelRenderer base;
/*     */   private final ModelRenderer base2;
/*     */   private final ModelRenderer base3;
/*     */   private final ModelRenderer shoulders;
/*     */   private final ModelRenderer spine;
/*     */   private final ModelRenderer spineb;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer spine2;
/*     */   private final ModelRenderer spine2b;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand;
/*     */   private final ModelRenderer leftHandFinger1;
/*     */   private final ModelRenderer leftHandFinger1b;
/*     */   private final ModelRenderer leftHandFinger2;
/*     */   private final ModelRenderer leftHandFinger2b;
/*     */   private final ModelRenderer leftHandFinger3;
/*     */   private final ModelRenderer leftHandFinger3b;
/*     */   private final ModelRenderer leftHandFinger4;
/*     */   private final ModelRenderer leftHandFinger4b;
/*     */   private final ModelRenderer leftHandFinger5;
/*     */   private final ModelRenderer leftHandFinger5b;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightHand;
/*     */   private final ModelRenderer rightHandFinger1;
/*     */   private final ModelRenderer rightHandFinger1b;
/*     */   private final ModelRenderer rightHandFinger2;
/*     */   private final ModelRenderer rightHandFinger2b;
/*     */   private final ModelRenderer rightHandFinger3;
/*     */   private final ModelRenderer rightHandFinger3b;
/*     */   private final ModelRenderer rightHandFinger4;
/*     */   private final ModelRenderer rightHandFinger4b;
/*     */   private final ModelRenderer rightHandFinger5;
/*     */   private final ModelRenderer rightHandFinger5b;
/*     */   
/*     */   public VenomDemonModel() {
/*  72 */     super(1.0F);
/*  73 */     this.textureWidth = 256;
/*  74 */     this.textureHeight = 256;
/*     */     
/*  76 */     this.head = new ModelRenderer((Model)this);
/*  77 */     this.head.setRotationPoint(0.5F, -21.0F, -11.5F);
/*  78 */     this.head.setTextureOffset(202, 46).addBox(-3.0F, -3.0F, -6.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
/*     */     
/*  80 */     this.jaw = new ModelRenderer((Model)this);
/*  81 */     this.jaw.setRotationPoint(-3.0F, -3.0F, -6.0F);
/*  82 */     this.head.addChild(this.jaw);
/*  83 */     setRotationAngle(this.jaw, 0.4363F, 0.0F, 0.0F);
/*  84 */     this.jaw.setTextureOffset(240, 49).addBox(0.5F, 7.0F, -2.5F, 4.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/*  86 */     this.mouth = new ModelRenderer((Model)this);
/*  87 */     this.mouth.setRotationPoint(-3.0F, -3.0F, -6.0F);
/*  88 */     this.head.addChild(this.mouth);
/*  89 */     this.mouth.setTextureOffset(223, 49).addBox(0.5F, 3.9F, 0.6F, 4.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/*  91 */     this.rightHorn = new ModelRenderer((Model)this);
/*  92 */     this.rightHorn.setRotationPoint(-2.0F, -2.0F, -2.5F);
/*  93 */     this.head.addChild(this.rightHorn);
/*  94 */     setRotationAngle(this.rightHorn, 0.8727F, -0.9599F, -0.1222F);
/*  95 */     this.rightHorn.setTextureOffset(230, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/*  97 */     this.rightHorn2 = new ModelRenderer((Model)this);
/*  98 */     this.rightHorn2.setRotationPoint(0.0F, 0.0F, 2.8F);
/*  99 */     this.rightHorn.addChild(this.rightHorn2);
/* 100 */     setRotationAngle(this.rightHorn2, -0.4363F, 0.1222F, 0.0F);
/* 101 */     this.rightHorn2.setTextureOffset(241, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 103 */     this.rightHorn3 = new ModelRenderer((Model)this);
/* 104 */     this.rightHorn3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 105 */     this.rightHorn2.addChild(this.rightHorn3);
/* 106 */     setRotationAngle(this.rightHorn3, -0.2967F, 0.0524F, 0.0F);
/* 107 */     this.rightHorn3.setTextureOffset(252, 1).addBox(0.4F, -2.5F, 1.9F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 109 */     this.leftHorn = new ModelRenderer((Model)this);
/* 110 */     this.leftHorn.setRotationPoint(1.0F, -2.0F, -2.5F);
/* 111 */     this.head.addChild(this.leftHorn);
/* 112 */     setRotationAngle(this.leftHorn, 0.8727F, 0.9599F, 0.1222F);
/* 113 */     this.leftHorn.setTextureOffset(230, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 115 */     this.leftHorn2 = new ModelRenderer((Model)this);
/* 116 */     this.leftHorn2.setRotationPoint(0.0F, 0.0F, 2.8F);
/* 117 */     this.leftHorn.addChild(this.leftHorn2);
/* 118 */     setRotationAngle(this.leftHorn2, -0.4363F, 0.1222F, 0.0F);
/* 119 */     this.leftHorn2.setTextureOffset(241, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
/*     */     
/* 121 */     this.leftHorn3 = new ModelRenderer((Model)this);
/* 122 */     this.leftHorn3.setRotationPoint(-1.3145F, 0.4024F, 2.5599F);
/* 123 */     this.leftHorn2.addChild(this.leftHorn3);
/* 124 */     setRotationAngle(this.leftHorn3, -0.2967F, -0.0524F, 0.0F);
/*     */     
/* 126 */     this.leftHorn3_r1 = new ModelRenderer((Model)this);
/* 127 */     this.leftHorn3_r1.setRotationPoint(-0.142F, 0.2276F, -0.077F);
/* 128 */     this.leftHorn3.addChild(this.leftHorn3_r1);
/* 129 */     setRotationAngle(this.leftHorn3_r1, 0.0873F, 0.0F, 0.0F);
/* 130 */     this.leftHorn3_r1.setTextureOffset(252, 1).addBox(0.1012F, -2.3755F, -1.03F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 132 */     this.body = new ModelRenderer((Model)this);
/* 133 */     this.body.setRotationPoint(-10.0F, -19.5F, 6.5F);
/* 134 */     setRotationAngle(this.body, 0.0524F, 0.0F, 0.0F);
/* 135 */     this.body.setTextureOffset(0, 142).addBox(0.0F, 0.0F, -18.0F, 20.0F, 10.0F, 18.0F, 0.0F, false);
/*     */     
/* 137 */     this.body2 = new ModelRenderer((Model)this);
/* 138 */     this.body2.setRotationPoint(0.0F, 9.5F, 0.0F);
/* 139 */     this.body.addChild(this.body2);
/* 140 */     setRotationAngle(this.body2, 0.0175F, 0.0F, 0.0F);
/* 141 */     this.body2.setTextureOffset(0, 116).addBox(0.0F, -0.0349F, -17.4988F, 20.0F, 8.0F, 17.0F, 0.0F, false);
/*     */     
/* 143 */     this.body3 = new ModelRenderer((Model)this);
/* 144 */     this.body3.setRotationPoint(1.0F, 7.9477F, -0.7482F);
/* 145 */     this.body2.addChild(this.body3);
/* 146 */     this.body3.setTextureOffset(0, 94).addBox(0.0F, 0.0F, -16.0F, 18.0F, 5.0F, 16.0F, 0.0F, false);
/*     */     
/* 148 */     this.body4 = new ModelRenderer((Model)this);
/* 149 */     this.body4.setRotationPoint(1.0F, 4.9651F, -0.4988F);
/* 150 */     this.body3.addChild(this.body4);
/* 151 */     this.body4.setTextureOffset(0, 73).addBox(0.0F, 0.0F, -15.0F, 16.0F, 5.0F, 15.0F, 0.0F, false);
/*     */     
/* 153 */     this.body5 = new ModelRenderer((Model)this);
/* 154 */     this.body5.setRotationPoint(1.0F, 4.9651F, -0.4988F);
/* 155 */     this.body4.addChild(this.body5);
/* 156 */     this.body5.setTextureOffset(0, 55).addBox(0.0F, 0.0F, -14.0F, 14.0F, 3.0F, 14.0F, 0.0F, false);
/*     */     
/* 158 */     this.base = new ModelRenderer((Model)this);
/* 159 */     this.base.setRotationPoint(1.0F, 2.2344F, -12.6971F);
/* 160 */     this.body5.addChild(this.base);
/* 161 */     setRotationAngle(this.base, -0.0524F, 0.0F, 0.0F);
/* 162 */     this.base.setTextureOffset(0, 34).addBox(0.0F, 0.0F, 0.0F, 12.0F, 8.0F, 12.0F, 0.0F, false);
/*     */     
/* 164 */     this.base2 = new ModelRenderer((Model)this);
/* 165 */     this.base2.setRotationPoint(-0.5F, 8.1029F, -0.5438F);
/* 166 */     this.base.addChild(this.base2);
/* 167 */     this.base2.setTextureOffset(0, 17).addBox(0.0F, 0.0F, 0.0F, 13.0F, 3.0F, 13.0F, 0.0F, false);
/*     */     
/* 169 */     this.base3 = new ModelRenderer((Model)this);
/* 170 */     this.base3.setRotationPoint(-0.5F, 2.9956F, -0.75F);
/* 171 */     this.base2.addChild(this.base3);
/* 172 */     this.base3.setTextureOffset(0, 0).addBox(0.0F, 0.0044F, 0.25F, 14.0F, 2.0F, 14.0F, 0.0F, false);
/*     */     
/* 174 */     this.shoulders = new ModelRenderer((Model)this);
/* 175 */     this.shoulders.setRotationPoint(0.5F, -2.4993F, -0.3738F);
/* 176 */     this.body.addChild(this.shoulders);
/* 177 */     setRotationAngle(this.shoulders, -0.1047F, 0.0F, 0.0F);
/* 178 */     this.shoulders.setTextureOffset(0, 171).addBox(0.0F, 0.0F, -17.0F, 19.0F, 5.0F, 17.0F, 0.0F, false);
/*     */     
/* 180 */     this.spine = new ModelRenderer((Model)this);
/* 181 */     this.spine.setRotationPoint(9.0F, 1.1399F, -8.8429F);
/* 182 */     this.shoulders.addChild(this.spine);
/* 183 */     setRotationAngle(this.spine, 1.5708F, 0.0F, 0.0F);
/* 184 */     this.spine.setTextureOffset(233, 9).addBox(0.0F, -7.191F, 1.0063F, 1.0F, 17.0F, 1.0F, 0.0F, false);
/*     */     
/* 186 */     this.spineb = new ModelRenderer((Model)this);
/* 187 */     this.spineb.setRotationPoint(0.5F, -8.1102F, 1.1063F);
/* 188 */     this.spine.addChild(this.spineb);
/* 189 */     this.spineb.setTextureOffset(243, 7).addBox(0.0F, 0.9193F, 0.0523F, 0.0F, 17.0F, 3.0F, 0.0F, false);
/*     */     
/* 191 */     this.neck = new ModelRenderer((Model)this);
/* 192 */     this.neck.setRotationPoint(7.5F, -0.0021F, -18.8738F);
/* 193 */     this.shoulders.addChild(this.neck);
/* 194 */     this.neck.setTextureOffset(189, 49).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 196 */     this.spine2 = new ModelRenderer((Model)this);
/* 197 */     this.spine2.setRotationPoint(9.5F, -2.7F, -0.5F);
/* 198 */     this.body.addChild(this.spine2);
/* 199 */     setRotationAngle(this.spine2, -0.0087F, 0.0F, 0.0F);
/* 200 */     this.spine2.setTextureOffset(238, 7).addBox(0.0F, 0.0F, 0.0F, 1.0F, 19.0F, 1.0F, 0.0F, false);
/*     */     
/* 202 */     this.spine2b = new ModelRenderer((Model)this);
/* 203 */     this.spine2b.setRotationPoint(0.5F, 0.2F, 1.0F);
/* 204 */     this.spine2.addChild(this.spine2b);
/* 205 */     this.spine2b.setTextureOffset(250, 6).addBox(0.0F, 0.0F, 0.0F, 0.0F, 18.0F, 3.0F, 0.0F, false);
/*     */     
/* 207 */     this.rightWing = new ModelRenderer((Model)this);
/* 208 */     this.rightWing.setRotationPoint(4.0F, -0.5F, -0.5F);
/* 209 */     this.body.addChild(this.rightWing);
/* 210 */     setRotationAngle(this.rightWing, 0.203F, 0.2261F, 0.5387F);
/* 211 */     this.rightWing.setTextureOffset(151, 0).addBox(-14.0F, 0.0F, 0.0F, 14.0F, 9.0F, 0.0F, 0.0F, false);
/*     */     
/* 213 */     this.leftWing = new ModelRenderer((Model)this);
/* 214 */     this.leftWing.setRotationPoint(16.0F, -0.5F, -0.5F);
/* 215 */     this.body.addChild(this.leftWing);
/* 216 */     setRotationAngle(this.leftWing, 0.203F, -0.2261F, -0.5387F);
/* 217 */     this.leftWing.setTextureOffset(151, 10).addBox(0.0F, 0.0F, 0.0F, 14.0F, 9.0F, 0.0F, 0.0F, false);
/*     */     
/* 219 */     this.leftArm = new ModelRenderer((Model)this);
/* 220 */     this.leftArm.setRotationPoint(8.0F, -17.0F, 0.0F);
/* 221 */     setRotationAngle(this.leftArm, -0.5236F, -0.5236F, 0.0F);
/* 222 */     this.leftArm.setTextureOffset(180, 0).addBox(0.0F, 0.0F, -6.0F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/* 224 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 225 */     this.leftArm2.setRotationPoint(1.2067F, 13.3142F, 0.4276F);
/* 226 */     this.leftArm.addChild(this.leftArm2);
/* 227 */     setRotationAngle(this.leftArm2, -0.3665F, 0.0436F, 0.4363F);
/* 228 */     this.leftArm2.setTextureOffset(205, 0).addBox(-0.8613F, 0.2473F, -6.5225F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/* 230 */     this.leftHand = new ModelRenderer((Model)this);
/* 231 */     this.leftHand.setRotationPoint(-0.9984F, 14.6745F, -0.5989F);
/* 232 */     this.leftArm2.addChild(this.leftHand);
/* 233 */     setRotationAngle(this.leftHand, 0.0262F, 0.0F, 0.0611F);
/* 234 */     this.leftHand.setTextureOffset(232, 28).addBox(0.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
/*     */     
/* 236 */     this.leftHandFinger1 = new ModelRenderer((Model)this);
/* 237 */     this.leftHandFinger1.setRotationPoint(1.4422F, 0.743F, -6.0F);
/* 238 */     this.leftHand.addChild(this.leftHandFinger1);
/* 239 */     setRotationAngle(this.leftHandFinger1, 0.8552F, 0.1309F, 0.0087F);
/* 240 */     this.leftHandFinger1.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 242 */     this.leftHandFinger1b = new ModelRenderer((Model)this);
/* 243 */     this.leftHandFinger1b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
/* 244 */     this.leftHandFinger1.addChild(this.leftHandFinger1b);
/* 245 */     setRotationAngle(this.leftHandFinger1b, 0.3142F, 0.0F, 0.0524F);
/* 246 */     this.leftHandFinger1b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 248 */     this.leftHandFinger2 = new ModelRenderer((Model)this);
/* 249 */     this.leftHandFinger2.setRotationPoint(2.9283F, 0.7596F, -6.203F);
/* 250 */     this.leftHand.addChild(this.leftHandFinger2);
/* 251 */     setRotationAngle(this.leftHandFinger2, 0.8552F, 0.0873F, 0.0087F);
/* 252 */     this.leftHandFinger2.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 254 */     this.leftHandFinger2b = new ModelRenderer((Model)this);
/* 255 */     this.leftHandFinger2b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
/* 256 */     this.leftHandFinger2.addChild(this.leftHandFinger2b);
/* 257 */     setRotationAngle(this.leftHandFinger2b, 0.3142F, 0.0F, 0.0524F);
/* 258 */     this.leftHandFinger2b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 260 */     this.leftHandFinger3 = new ModelRenderer((Model)this);
/* 261 */     this.leftHandFinger3.setRotationPoint(4.4405F, 0.9206F, -6.2036F);
/* 262 */     this.leftHand.addChild(this.leftHandFinger3);
/* 263 */     setRotationAngle(this.leftHandFinger3, 0.8552F, -0.0436F, 0.0087F);
/* 264 */     this.leftHandFinger3.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 266 */     this.leftHandFinger3b = new ModelRenderer((Model)this);
/* 267 */     this.leftHandFinger3b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
/* 268 */     this.leftHandFinger3.addChild(this.leftHandFinger3b);
/* 269 */     setRotationAngle(this.leftHandFinger3b, 0.3142F, 0.0F, 0.0524F);
/* 270 */     this.leftHandFinger3b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 272 */     this.leftHandFinger4 = new ModelRenderer((Model)this);
/* 273 */     this.leftHandFinger4.setRotationPoint(5.9266F, 0.9372F, -6.4066F);
/* 274 */     this.leftHand.addChild(this.leftHandFinger4);
/* 275 */     setRotationAngle(this.leftHandFinger4, 0.9425F, -0.1309F, 0.0087F);
/* 276 */     this.leftHandFinger4.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 278 */     this.leftHandFinger4b = new ModelRenderer((Model)this);
/* 279 */     this.leftHandFinger4b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
/* 280 */     this.leftHandFinger4.addChild(this.leftHandFinger4b);
/* 281 */     setRotationAngle(this.leftHandFinger4b, 0.3142F, 0.0F, 0.0524F);
/* 282 */     this.leftHandFinger4b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 284 */     this.leftHandFinger5 = new ModelRenderer((Model)this);
/* 285 */     this.leftHandFinger5.setRotationPoint(0.117F, 0.3919F, -2.7991F);
/* 286 */     this.leftHand.addChild(this.leftHandFinger5);
/* 287 */     setRotationAngle(this.leftHandFinger5, 0.9425F, 1.6581F, -0.2531F);
/* 288 */     this.leftHandFinger5.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 290 */     this.leftHandFinger5b = new ModelRenderer((Model)this);
/* 291 */     this.leftHandFinger5b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
/* 292 */     this.leftHandFinger5.addChild(this.leftHandFinger5b);
/* 293 */     setRotationAngle(this.leftHandFinger5b, 0.3142F, 0.0F, 0.0524F);
/* 294 */     this.leftHandFinger5b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 296 */     this.rightArm = new ModelRenderer((Model)this);
/* 297 */     this.rightArm.setRotationPoint(-13.0F, -17.0F, 2.75F);
/* 298 */     setRotationAngle(this.rightArm, -0.5236F, 0.5236F, 0.0873F);
/* 299 */     this.rightArm.setTextureOffset(180, 0).addBox(0.0F, 0.0F, -6.0F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/* 301 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 302 */     this.rightArm2.setRotationPoint(0.7753F, 13.4765F, 0.2337F);
/* 303 */     this.rightArm.addChild(this.rightArm2);
/* 304 */     setRotationAngle(this.rightArm2, -0.3665F, 0.0436F, -0.5236F);
/* 305 */     this.rightArm2.setTextureOffset(205, 0).addBox(-1.1789F, 2.1831F, -5.4493F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/* 307 */     this.rightHand = new ModelRenderer((Model)this);
/* 308 */     this.rightHand.setRotationPoint(-0.9984F, 14.6745F, -0.5989F);
/* 309 */     this.rightArm2.addChild(this.rightHand);
/* 310 */     setRotationAngle(this.rightHand, 0.0262F, 0.0F, 0.0611F);
/* 311 */     this.rightHand.setTextureOffset(232, 28).addBox(-0.1988F, 1.9791F, -4.9783F, 6.0F, 2.0F, 6.0F, 0.0F, false);
/*     */     
/* 313 */     this.rightHandFinger1 = new ModelRenderer((Model)this);
/* 314 */     this.rightHandFinger1.setRotationPoint(1.4422F, 0.743F, -6.0F);
/* 315 */     this.rightHand.addChild(this.rightHandFinger1);
/* 316 */     setRotationAngle(this.rightHandFinger1, 0.8552F, 0.1309F, 0.0087F);
/* 317 */     this.rightHandFinger1.setTextureOffset(225, 33).addBox(-1.3133F, 2.0461F, -2.8459F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 319 */     this.rightHandFinger1b = new ModelRenderer((Model)this);
/* 320 */     this.rightHandFinger1b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
/* 321 */     this.rightHandFinger1.addChild(this.rightHandFinger1b);
/* 322 */     setRotationAngle(this.rightHandFinger1b, 0.3142F, 0.0F, 0.0524F);
/* 323 */     this.rightHandFinger1b.setTextureOffset(225, 29).addBox(-1.2058F, 1.7802F, -3.4409F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 325 */     this.rightHandFinger2 = new ModelRenderer((Model)this);
/* 326 */     this.rightHandFinger2.setRotationPoint(2.9283F, 0.7596F, -6.203F);
/* 327 */     this.rightHand.addChild(this.rightHandFinger2);
/* 328 */     setRotationAngle(this.rightHandFinger2, 0.8552F, 0.0873F, 0.0087F);
/* 329 */     this.rightHandFinger2.setTextureOffset(225, 33).addBox(-1.2699F, 2.0557F, -2.8375F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 331 */     this.rightHandFinger2b = new ModelRenderer((Model)this);
/* 332 */     this.rightHandFinger2b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
/* 333 */     this.rightHandFinger2.addChild(this.rightHandFinger2b);
/* 334 */     setRotationAngle(this.rightHandFinger2b, 0.3142F, 0.0F, 0.0524F);
/* 335 */     this.rightHandFinger2b.setTextureOffset(225, 29).addBox(-1.1619F, 1.7898F, -3.4352F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 337 */     this.rightHandFinger3 = new ModelRenderer((Model)this);
/* 338 */     this.rightHandFinger3.setRotationPoint(4.4405F, 0.9206F, -6.2036F);
/* 339 */     this.rightHand.addChild(this.rightHandFinger3);
/* 340 */     setRotationAngle(this.rightHandFinger3, 0.8552F, -0.0436F, 0.0087F);
/* 341 */     this.rightHandFinger3.setTextureOffset(225, 33).addBox(-1.1368F, 2.0758F, -2.82F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 343 */     this.rightHandFinger3b = new ModelRenderer((Model)this);
/* 344 */     this.rightHandFinger3b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
/* 345 */     this.rightHandFinger3.addChild(this.rightHandFinger3b);
/* 346 */     setRotationAngle(this.rightHandFinger3b, 0.3142F, 0.0F, 0.0524F);
/* 347 */     this.rightHandFinger3b.setTextureOffset(225, 29).addBox(-1.028F, 1.8077F, -3.4227F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 349 */     this.rightHandFinger4 = new ModelRenderer((Model)this);
/* 350 */     this.rightHandFinger4.setRotationPoint(5.9266F, 0.9372F, -6.4066F);
/* 351 */     this.rightHand.addChild(this.rightHandFinger4);
/* 352 */     setRotationAngle(this.rightHandFinger4, 0.9425F, -0.1309F, 0.0087F);
/* 353 */     this.rightHandFinger4.setTextureOffset(225, 33).addBox(-1.0466F, 2.0029F, -2.9931F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 355 */     this.rightHandFinger4b = new ModelRenderer((Model)this);
/* 356 */     this.rightHandFinger4b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
/* 357 */     this.rightHandFinger4.addChild(this.rightHandFinger4b);
/* 358 */     setRotationAngle(this.rightHandFinger4b, 0.3142F, 0.0F, 0.0524F);
/* 359 */     this.rightHandFinger4b.setTextureOffset(225, 29).addBox(-0.9417F, 1.6805F, -3.5633F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 361 */     this.rightHandFinger5 = new ModelRenderer((Model)this);
/* 362 */     this.rightHandFinger5.setRotationPoint(4.642F, 1.0653F, -2.5333F);
/* 363 */     this.rightHand.addChild(this.rightHandFinger5);
/* 364 */     setRotationAngle(this.rightHandFinger5, 2.0769F, 1.6581F, -0.2531F);
/* 365 */     this.rightHandFinger5.setTextureOffset(225, 33).addBox(-1.9578F, -1.5821F, -3.2568F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 367 */     this.rightHandFinger5b = new ModelRenderer((Model)this);
/* 368 */     this.rightHandFinger5b.setRotationPoint(0.4204F, 0.3134F, -2.0771F);
/* 369 */     this.rightHandFinger5.addChild(this.rightHandFinger5b);
/* 370 */     setRotationAngle(this.rightHandFinger5b, -0.3142F, 0.0F, 0.0524F);
/* 371 */     this.rightHandFinger5b.setTextureOffset(225, 29).addBox(-2.4653F, -1.3458F, -3.4515F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 373 */     this.bipedBody = this.body;
/* 374 */     this.bipedHead = this.head;
/* 375 */     this.bipedRightArm = this.rightArm;
/* 376 */     this.bipedLeftArm = this.leftArm;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 382 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 383 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 384 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 385 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 392 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/* 393 */     boolean flag1 = entity.isActualySwimming();
/* 394 */     this.head.rotateAngleY = netHeadYaw * 0.017453292F;
/* 395 */     if (flag) {
/* 396 */       this.head.rotateAngleX = -0.7853982F;
/* 397 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/* 399 */       if (flag1) {
/* 400 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 402 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 406 */       this.head.rotateAngleX = headPitch * 0.017453292F;
/* 407 */       if (this.head.rotateAngleX > 0.6D) {
/* 408 */         this.head.rotateAngleX = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 412 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 413 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 415 */       this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.02F;
/* 416 */       this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 12.0F;
/* 417 */       this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 9.0F;
/* 418 */       this.rightArm.rotateAngleY += this.body.rotateAngleY;
/* 419 */       this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
/* 420 */       this.leftArm.rotateAngleY -= this.body.rotateAngleY;
/* 421 */       this.leftArm.rotateAngleX -= this.body.rotateAngleY;
/* 422 */       float f1 = 1.0F - this.swingProgress;
/* 423 */       f1 *= f1;
/* 424 */       f1 *= f1;
/* 425 */       f1 = 1.0F - f1;
/* 426 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 427 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 428 */       this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
/* 429 */       this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
/* 430 */       this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 438 */     super.translateHand(side, matrixStack);
/* 439 */     matrixStack.translate(0.7D, 1.3D, -0.4D);
/* 440 */     matrixStack.rotate(Vector3f.YP.rotationDegrees(-20.0F));
/* 441 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(-30.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 447 */     if (side == HandSide.RIGHT) {
/*     */       
/* 449 */       matrixStack.translate(-1.4D, -0.9D, 0.2D);
/* 450 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(70.0F));
/* 451 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(40.0F));
/* 452 */       this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 456 */       matrixStack.translate(0.7D, -0.6D, -0.5D);
/* 457 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(30.0F));
/* 458 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(30.0F));
/* 459 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
/* 460 */       this.leftArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 471 */     modelRenderer.rotateAngleX = x;
/* 472 */     modelRenderer.rotateAngleY = y;
/* 473 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\VenomDemonModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */