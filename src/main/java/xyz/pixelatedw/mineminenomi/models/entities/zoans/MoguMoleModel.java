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
/*     */ public class MoguMoleModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer mouth3;
/*     */   private final ModelRenderer mouth4;
/*     */   private final ModelRenderer mouth2;
/*     */   private final ModelRenderer mouth1;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightHand;
/*     */   private final ModelRenderer rightClaw1;
/*     */   private final ModelRenderer rightClaw1b;
/*     */   private final ModelRenderer rightClaw1c;
/*     */   private final ModelRenderer rightClaw2;
/*     */   private final ModelRenderer rightClaw2b;
/*     */   private final ModelRenderer rightClaw2c;
/*     */   private final ModelRenderer rightClaw3;
/*     */   private final ModelRenderer rightClaw3b;
/*     */   private final ModelRenderer rightClaw3c;
/*     */   private final ModelRenderer rightClaw4;
/*     */   private final ModelRenderer rightClaw4_1;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand;
/*     */   private final ModelRenderer leftClaw1;
/*     */   private final ModelRenderer leftClaw1b;
/*     */   private final ModelRenderer leftClaw1c;
/*     */   private final ModelRenderer leftClaw2;
/*     */   private final ModelRenderer leftClaw2b;
/*     */   private final ModelRenderer leftClaw2c;
/*     */   private final ModelRenderer leftClaw3;
/*     */   private final ModelRenderer leftClaw3b;
/*     */   private final ModelRenderer leftClaw3c;
/*     */   private final ModelRenderer leftClaw4;
/*     */   private final ModelRenderer leftClaw4b;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftFoot;
/*     */   private final ModelRenderer leftFootClaw1;
/*     */   private final ModelRenderer leftFootClaw1_r1;
/*     */   private final ModelRenderer leftFootClaw2;
/*     */   private final ModelRenderer leftFootClaw3;
/*     */   private final ModelRenderer leftFootClaw4;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightFoot2;
/*     */   private final ModelRenderer rightFootClaw1;
/*     */   private final ModelRenderer rightFootClaw1_r1;
/*     */   private final ModelRenderer rightFootClaw2;
/*     */   private final ModelRenderer rightFootClaw3;
/*     */   private final ModelRenderer rightFootClaw4;
/*     */   
/*     */   public MoguMoleModel() {
/*  71 */     super(1.0F);
/*  72 */     this.textureWidth = 128;
/*  73 */     this.textureHeight = 64;
/*     */     
/*  75 */     this.head = new ModelRenderer((Model)this);
/*  76 */     this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  77 */     this.head.setTextureOffset(0, 0).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/*  79 */     this.mouth3 = new ModelRenderer((Model)this);
/*  80 */     this.mouth3.setRotationPoint(0.0F, -2.0F, -2.7F);
/*  81 */     this.head.addChild(this.mouth3);
/*  82 */     setRotationAngle(this.mouth3, 0.1745F, 0.0F, 0.0F);
/*  83 */     this.mouth3.setTextureOffset(36, 0).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/*  85 */     this.mouth4 = new ModelRenderer((Model)this);
/*  86 */     this.mouth4.setRotationPoint(0.0F, 1.0F, 0.0F);
/*  87 */     this.mouth3.addChild(this.mouth4);
/*  88 */     setRotationAngle(this.mouth4, -0.2443F, 0.0F, 0.0F);
/*  89 */     this.mouth4.setTextureOffset(36, 5).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, -0.01F, false);
/*     */     
/*  91 */     this.mouth2 = new ModelRenderer((Model)this);
/*  92 */     this.mouth2.setRotationPoint(1.0F, 0.0F, 0.2F);
/*  93 */     this.mouth3.addChild(this.mouth2);
/*  94 */     setRotationAngle(this.mouth2, 0.0F, 0.3491F, 0.0F);
/*  95 */     this.mouth2.setTextureOffset(25, 5).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, -0.01F, false);
/*     */     
/*  97 */     this.mouth1 = new ModelRenderer((Model)this);
/*  98 */     this.mouth1.setRotationPoint(-1.0F, 0.0F, 0.5F);
/*  99 */     this.mouth3.addChild(this.mouth1);
/* 100 */     setRotationAngle(this.mouth1, 0.0F, -0.3491F, 0.0F);
/* 101 */     this.mouth1.setTextureOffset(25, 0).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, -0.01F, false);
/*     */     
/* 103 */     this.body = new ModelRenderer((Model)this);
/* 104 */     this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 105 */     this.body.setTextureOffset(17, 12).addBox(-5.5F, 0.0F, -3.0F, 11.0F, 17.0F, 6.0F, 0.0F, false);
/*     */     
/* 107 */     this.body2 = new ModelRenderer((Model)this);
/* 108 */     this.body2.setRotationPoint(0.0F, 0.5F, -0.5F);
/* 109 */     this.body.addChild(this.body2);
/* 110 */     this.body2.setTextureOffset(17, 36).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 16.0F, 7.0F, 0.0F, false);
/*     */     
/* 112 */     this.body3 = new ModelRenderer((Model)this);
/* 113 */     this.body3.setRotationPoint(0.0F, 9.0F, -3.0F);
/* 114 */     this.body2.addChild(this.body3);
/* 115 */     this.body3.setTextureOffset(52, 12).addBox(-4.5F, -8.5F, -1.0F, 9.0F, 15.0F, 1.0F, 0.0F, false);
/*     */     
/* 117 */     this.rightArm = new ModelRenderer((Model)this);
/* 118 */     this.rightArm.setRotationPoint(-5.4F, 2.0F, 0.0F);
/* 119 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.0698F);
/* 120 */     this.rightArm.setTextureOffset(73, 12).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/* 122 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 123 */     this.rightArm2.setRotationPoint(-2.0F, 5.6F, 0.0F);
/* 124 */     this.rightArm.addChild(this.rightArm2);
/* 125 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0698F);
/* 126 */     this.rightArm2.setTextureOffset(73, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 128 */     this.rightHand = new ModelRenderer((Model)this);
/* 129 */     this.rightHand.setRotationPoint(-0.2F, 6.8F, 0.0F);
/* 130 */     this.rightArm2.addChild(this.rightHand);
/* 131 */     setRotationAngle(this.rightHand, 0.0F, 0.0F, -0.0873F);
/* 132 */     this.rightHand.setTextureOffset(73, 37).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/* 134 */     this.rightClaw1 = new ModelRenderer((Model)this);
/* 135 */     this.rightClaw1.setRotationPoint(-6.6273F, -1.2306F, 1.5F);
/* 136 */     this.rightHand.addChild(this.rightClaw1);
/* 137 */     setRotationAngle(this.rightClaw1, 0.0F, 0.0F, 0.4363F);
/* 138 */     this.rightClaw1.setTextureOffset(73, 44).addBox(5.7219F, -0.8395F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 140 */     this.rightClaw1b = new ModelRenderer((Model)this);
/* 141 */     this.rightClaw1b.setRotationPoint(0.0824F, 2.8796F, 0.0F);
/* 142 */     this.rightClaw1.addChild(this.rightClaw1b);
/* 143 */     setRotationAngle(this.rightClaw1b, 0.0F, 0.0F, -0.4363F);
/* 144 */     this.rightClaw1b.setTextureOffset(73, 49).addBox(5.4385F, 1.6334F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 146 */     this.rightClaw1c = new ModelRenderer((Model)this);
/* 147 */     this.rightClaw1c.setRotationPoint(-0.2194F, 2.7529F, 0.5F);
/* 148 */     this.rightClaw1b.addChild(this.rightClaw1c);
/* 149 */     setRotationAngle(this.rightClaw1c, 0.0F, 0.0F, -0.9599F);
/* 150 */     this.rightClaw1c.setTextureOffset(73, 49).addBox(1.6987F, 5.6297F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 152 */     this.rightClaw2 = new ModelRenderer((Model)this);
/* 153 */     this.rightClaw2.setRotationPoint(-6.6273F, -1.2306F, 0.25F);
/* 154 */     this.rightHand.addChild(this.rightClaw2);
/* 155 */     setRotationAngle(this.rightClaw2, 0.0F, 0.0F, 0.4363F);
/* 156 */     this.rightClaw2.setTextureOffset(73, 44).addBox(5.7219F, -0.8395F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 158 */     this.rightClaw2b = new ModelRenderer((Model)this);
/* 159 */     this.rightClaw2b.setRotationPoint(0.0824F, 2.8796F, 0.0F);
/* 160 */     this.rightClaw2.addChild(this.rightClaw2b);
/* 161 */     setRotationAngle(this.rightClaw2b, 0.0F, 0.0F, -0.4363F);
/* 162 */     this.rightClaw2b.setTextureOffset(73, 49).addBox(5.4385F, 1.6334F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 164 */     this.rightClaw2c = new ModelRenderer((Model)this);
/* 165 */     this.rightClaw2c.setRotationPoint(-0.2194F, 2.7529F, 0.5F);
/* 166 */     this.rightClaw2b.addChild(this.rightClaw2c);
/* 167 */     setRotationAngle(this.rightClaw2c, 0.0F, 0.0F, -0.9599F);
/* 168 */     this.rightClaw2c.setTextureOffset(73, 49).addBox(1.6987F, 5.6297F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 170 */     this.rightClaw3 = new ModelRenderer((Model)this);
/* 171 */     this.rightClaw3.setRotationPoint(-6.6273F, -1.2306F, -1.0F);
/* 172 */     this.rightHand.addChild(this.rightClaw3);
/* 173 */     setRotationAngle(this.rightClaw3, 0.0F, 0.0F, 0.4363F);
/* 174 */     this.rightClaw3.setTextureOffset(73, 44).addBox(5.7219F, -0.8395F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 176 */     this.rightClaw3b = new ModelRenderer((Model)this);
/* 177 */     this.rightClaw3b.setRotationPoint(0.0824F, 2.8796F, 0.0F);
/* 178 */     this.rightClaw3.addChild(this.rightClaw3b);
/* 179 */     setRotationAngle(this.rightClaw3b, 0.0F, 0.0F, -0.4363F);
/* 180 */     this.rightClaw3b.setTextureOffset(73, 49).addBox(5.4385F, 1.6334F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 182 */     this.rightClaw3c = new ModelRenderer((Model)this);
/* 183 */     this.rightClaw3c.setRotationPoint(-0.2194F, 2.7529F, 0.5F);
/* 184 */     this.rightClaw3b.addChild(this.rightClaw3c);
/* 185 */     setRotationAngle(this.rightClaw3c, 0.0F, 0.0F, -0.9599F);
/* 186 */     this.rightClaw3c.setTextureOffset(73, 49).addBox(1.6987F, 5.6297F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 188 */     this.rightClaw4 = new ModelRenderer((Model)this);
/* 189 */     this.rightClaw4.setRotationPoint(9.4363F, -7.7041F, -1.5F);
/* 190 */     this.rightHand.addChild(this.rightClaw4);
/* 191 */     setRotationAngle(this.rightClaw4, -0.6981F, 0.0F, 0.0F);
/* 192 */     this.rightClaw4.setTextureOffset(80, 44).addBox(-9.5176F, 6.7226F, 5.1409F, 1.0F, 2.0F, 1.0F, 0.0F, true);
/*     */     
/* 194 */     this.rightClaw4_1 = new ModelRenderer((Model)this);
/* 195 */     this.rightClaw4_1.setRotationPoint(0.4358F, 1.9296F, -0.7285F);
/* 196 */     this.rightClaw4.addChild(this.rightClaw4_1);
/* 197 */     setRotationAngle(this.rightClaw4_1, 0.7741F, 0.0F, 0.0F);
/* 198 */     this.rightClaw4_1.setTextureOffset(80, 48).addBox(-9.9501F, 8.8318F, -0.5371F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 200 */     this.leftArm = new ModelRenderer((Model)this);
/* 201 */     this.leftArm.setRotationPoint(5.4F, 2.0F, 0.0F);
/* 202 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.0698F);
/* 203 */     this.leftArm.setTextureOffset(73, 12).addBox(0.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
/*     */     
/* 205 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 206 */     this.leftArm2.setRotationPoint(2.0F, 5.6F, 0.0F);
/* 207 */     this.leftArm.addChild(this.leftArm2);
/* 208 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0698F);
/* 209 */     this.leftArm2.setTextureOffset(73, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 211 */     this.leftHand = new ModelRenderer((Model)this);
/* 212 */     this.leftHand.setRotationPoint(0.0F, 6.8F, 0.0F);
/* 213 */     this.leftArm2.addChild(this.leftHand);
/* 214 */     setRotationAngle(this.leftHand, 0.0F, 0.0F, 0.0873F);
/* 215 */     this.leftHand.setTextureOffset(73, 37).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
/*     */     
/* 217 */     this.leftClaw1 = new ModelRenderer((Model)this);
/* 218 */     this.leftClaw1.setRotationPoint(10.0391F, -7.4945F, 1.5F);
/* 219 */     this.leftHand.addChild(this.leftClaw1);
/* 220 */     setRotationAngle(this.leftClaw1, 0.0F, 0.0F, -0.4363F);
/* 221 */     this.leftClaw1.setTextureOffset(73, 44).addBox(-13.4283F, 3.3539F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 223 */     this.leftClaw1b = new ModelRenderer((Model)this);
/* 224 */     this.leftClaw1b.setRotationPoint(-0.5F, 2.95F, 0.0F);
/* 225 */     this.leftClaw1.addChild(this.leftClaw1b);
/* 226 */     setRotationAngle(this.leftClaw1b, 0.0F, 0.0F, 0.4363F);
/* 227 */     this.leftClaw1b.setTextureOffset(73, 49).addBox(-10.5176F, 7.7757F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 229 */     this.leftClaw1c = new ModelRenderer((Model)this);
/* 230 */     this.leftClaw1c.setRotationPoint(-1.2156F, 2.6658F, 0.5F);
/* 231 */     this.leftClaw1b.addChild(this.leftClaw1c);
/* 232 */     setRotationAngle(this.leftClaw1c, 0.0F, 0.0F, 0.9599F);
/* 233 */     this.leftClaw1c.setTextureOffset(73, 49).addBox(1.4797F, 10.6895F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 235 */     this.leftClaw2 = new ModelRenderer((Model)this);
/* 236 */     this.leftClaw2.setRotationPoint(10.0391F, -7.4945F, 0.25F);
/* 237 */     this.leftHand.addChild(this.leftClaw2);
/* 238 */     setRotationAngle(this.leftClaw2, 0.0F, 0.0F, -0.4363F);
/* 239 */     this.leftClaw2.setTextureOffset(73, 44).addBox(-13.4283F, 3.3539F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 241 */     this.leftClaw2b = new ModelRenderer((Model)this);
/* 242 */     this.leftClaw2b.setRotationPoint(-0.5F, 2.95F, 0.0F);
/* 243 */     this.leftClaw2.addChild(this.leftClaw2b);
/* 244 */     setRotationAngle(this.leftClaw2b, 0.0F, 0.0F, 0.4363F);
/* 245 */     this.leftClaw2b.setTextureOffset(73, 49).addBox(-10.5176F, 7.7757F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 247 */     this.leftClaw2c = new ModelRenderer((Model)this);
/* 248 */     this.leftClaw2c.setRotationPoint(-1.2156F, 2.6658F, 0.5F);
/* 249 */     this.leftClaw2b.addChild(this.leftClaw2c);
/* 250 */     setRotationAngle(this.leftClaw2c, 0.0F, 0.0F, 0.9599F);
/* 251 */     this.leftClaw2c.setTextureOffset(73, 49).addBox(1.4797F, 10.6895F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 253 */     this.leftClaw3 = new ModelRenderer((Model)this);
/* 254 */     this.leftClaw3.setRotationPoint(10.0391F, -7.4945F, -1.0F);
/* 255 */     this.leftHand.addChild(this.leftClaw3);
/* 256 */     setRotationAngle(this.leftClaw3, 0.0F, 0.0F, -0.4363F);
/* 257 */     this.leftClaw3.setTextureOffset(73, 44).addBox(-13.4283F, 3.3539F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 259 */     this.leftClaw3b = new ModelRenderer((Model)this);
/* 260 */     this.leftClaw3b.setRotationPoint(-0.5F, 2.95F, 0.0F);
/* 261 */     this.leftClaw3.addChild(this.leftClaw3b);
/* 262 */     setRotationAngle(this.leftClaw3b, 0.0F, 0.0F, 0.4363F);
/* 263 */     this.leftClaw3b.setTextureOffset(73, 49).addBox(-10.5176F, 7.7757F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
/*     */     
/* 265 */     this.leftClaw3c = new ModelRenderer((Model)this);
/* 266 */     this.leftClaw3c.setRotationPoint(-1.2156F, 2.6658F, 0.5F);
/* 267 */     this.leftClaw3b.addChild(this.leftClaw3c);
/* 268 */     setRotationAngle(this.leftClaw3c, 0.0F, 0.0F, 0.9599F);
/* 269 */     this.leftClaw3c.setTextureOffset(73, 49).addBox(1.4797F, 10.6895F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
/*     */     
/* 271 */     this.leftClaw4 = new ModelRenderer((Model)this);
/* 272 */     this.leftClaw4.setRotationPoint(8.492F, -7.7291F, -1.5F);
/* 273 */     this.leftHand.addChild(this.leftClaw4);
/* 274 */     setRotationAngle(this.leftClaw4, -0.6981F, 0.0F, 0.0F);
/* 275 */     this.leftClaw4.setTextureOffset(80, 44).addBox(-9.5176F, 6.7226F, 5.1409F, 1.0F, 2.0F, 1.0F, 0.0F, true);
/*     */     
/* 277 */     this.leftClaw4b = new ModelRenderer((Model)this);
/* 278 */     this.leftClaw4b.setRotationPoint(0.4358F, 1.9296F, -0.7285F);
/* 279 */     this.leftClaw4.addChild(this.leftClaw4b);
/* 280 */     setRotationAngle(this.leftClaw4b, 0.7741F, 0.0F, 0.0F);
/* 281 */     this.leftClaw4b.setTextureOffset(80, 48).addBox(-9.9501F, 8.8318F, -0.5371F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 283 */     this.leftLeg = new ModelRenderer((Model)this);
/* 284 */     this.leftLeg.setRotationPoint(3.0F, 17.0F, 0.0F);
/* 285 */     this.leftLeg.setTextureOffset(0, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 287 */     this.leftFoot = new ModelRenderer((Model)this);
/* 288 */     this.leftFoot.setRotationPoint(0.0F, 5.5F, -1.9F);
/* 289 */     this.leftLeg.addChild(this.leftFoot);
/* 290 */     setRotationAngle(this.leftFoot, 0.7854F, 0.0F, 0.0F);
/* 291 */     this.leftFoot.setTextureOffset(0, 24).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, -0.01F, false);
/*     */     
/* 293 */     this.leftFootClaw1 = new ModelRenderer((Model)this);
/* 294 */     this.leftFootClaw1.setRotationPoint(1.0F, 0.5768F, -0.2232F);
/* 295 */     this.leftFoot.addChild(this.leftFootClaw1);
/* 296 */     setRotationAngle(this.leftFootClaw1, -0.7418F, -0.1745F, 0.1745F);
/*     */ 
/*     */     
/* 299 */     this.leftFootClaw1_r1 = new ModelRenderer((Model)this);
/* 300 */     this.leftFootClaw1_r1.setRotationPoint(-4.5F, 1.1F, 2.3F);
/* 301 */     this.leftFootClaw1.addChild(this.leftFootClaw1_r1);
/* 302 */     setRotationAngle(this.leftFootClaw1_r1, 0.0F, 0.0436F, 0.0F);
/* 303 */     this.leftFootClaw1_r1.setTextureOffset(0, 27).addBox(4.4F, -1.1F, -4.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 305 */     this.leftFootClaw2 = new ModelRenderer((Model)this);
/* 306 */     this.leftFootClaw2.setRotationPoint(0.0F, 0.4F, -0.4F);
/* 307 */     this.leftFoot.addChild(this.leftFootClaw2);
/* 308 */     setRotationAngle(this.leftFootClaw2, -0.7418F, 0.0F, 0.0F);
/* 309 */     this.leftFootClaw2.setTextureOffset(0, 27).addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 311 */     this.leftFootClaw3 = new ModelRenderer((Model)this);
/* 312 */     this.leftFootClaw3.setRotationPoint(0.0F, 0.4F, -0.4F);
/* 313 */     this.leftFoot.addChild(this.leftFootClaw3);
/* 314 */     setRotationAngle(this.leftFootClaw3, -0.7418F, 0.0F, 0.0F);
/* 315 */     this.leftFootClaw3.setTextureOffset(0, 27).addBox(-1.1F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 317 */     this.leftFootClaw4 = new ModelRenderer((Model)this);
/* 318 */     this.leftFootClaw4.setRotationPoint(0.0F, 0.4F, -0.4F);
/* 319 */     this.leftFoot.addChild(this.leftFootClaw4);
/* 320 */     setRotationAngle(this.leftFootClaw4, -0.7418F, 0.1745F, -0.1745F);
/* 321 */     this.leftFootClaw4.setTextureOffset(0, 27).addBox(-1.8541F, 0.0077F, -2.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 323 */     this.rightLeg = new ModelRenderer((Model)this);
/* 324 */     this.rightLeg.setRotationPoint(-3.0F, 17.0F, 0.0F);
/* 325 */     this.rightLeg.setTextureOffset(0, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 327 */     this.rightFoot2 = new ModelRenderer((Model)this);
/* 328 */     this.rightFoot2.setRotationPoint(0.0F, 5.5F, -1.9F);
/* 329 */     this.rightLeg.addChild(this.rightFoot2);
/* 330 */     setRotationAngle(this.rightFoot2, 0.7854F, 0.0F, 0.0F);
/* 331 */     this.rightFoot2.setTextureOffset(0, 24).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, -0.01F, false);
/*     */     
/* 333 */     this.rightFootClaw1 = new ModelRenderer((Model)this);
/* 334 */     this.rightFootClaw1.setRotationPoint(1.0F, 0.5768F, -0.2232F);
/* 335 */     this.rightFoot2.addChild(this.rightFootClaw1);
/* 336 */     setRotationAngle(this.rightFootClaw1, -0.7418F, -0.1745F, 0.1745F);
/*     */ 
/*     */     
/* 339 */     this.rightFootClaw1_r1 = new ModelRenderer((Model)this);
/* 340 */     this.rightFootClaw1_r1.setRotationPoint(-4.5F, 1.1F, 2.3F);
/* 341 */     this.rightFootClaw1.addChild(this.rightFootClaw1_r1);
/* 342 */     setRotationAngle(this.rightFootClaw1_r1, 0.0F, 0.0436F, 0.0F);
/* 343 */     this.rightFootClaw1_r1.setTextureOffset(0, 27).addBox(4.4F, -1.1F, -4.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 345 */     this.rightFootClaw2 = new ModelRenderer((Model)this);
/* 346 */     this.rightFootClaw2.setRotationPoint(0.0F, 0.4F, -0.4F);
/* 347 */     this.rightFoot2.addChild(this.rightFootClaw2);
/* 348 */     setRotationAngle(this.rightFootClaw2, -0.7418F, 0.0F, 0.0F);
/* 349 */     this.rightFootClaw2.setTextureOffset(0, 27).addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 351 */     this.rightFootClaw3 = new ModelRenderer((Model)this);
/* 352 */     this.rightFootClaw3.setRotationPoint(0.0F, 0.4F, -0.4F);
/* 353 */     this.rightFoot2.addChild(this.rightFootClaw3);
/* 354 */     setRotationAngle(this.rightFootClaw3, -0.7418F, 0.0F, 0.0F);
/* 355 */     this.rightFootClaw3.setTextureOffset(0, 27).addBox(-1.1F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 357 */     this.rightFootClaw4 = new ModelRenderer((Model)this);
/* 358 */     this.rightFootClaw4.setRotationPoint(0.0F, 0.4F, -0.4F);
/* 359 */     this.rightFoot2.addChild(this.rightFootClaw4);
/* 360 */     setRotationAngle(this.rightFootClaw4, -0.7418F, 0.1745F, -0.1745F);
/* 361 */     this.rightFootClaw4.setTextureOffset(0, 27).addBox(-1.8541F, 0.0077F, -2.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 363 */     this.bipedBody = this.body;
/* 364 */     this.bipedHead = this.head;
/* 365 */     this.bipedRightArm = this.rightArm;
/* 366 */     this.bipedLeftArm = this.leftArm;
/* 367 */     this.bipedRightLeg = this.rightLeg;
/* 368 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 374 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 375 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 376 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 377 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 378 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 379 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 386 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/* 387 */     boolean flag1 = entity.isActualySwimming();
/* 388 */     this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
/* 389 */     if (flag) {
/* 390 */       this.head.rotateAngleX = -0.7853982F;
/* 391 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/* 393 */       if (flag1) {
/* 394 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 396 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 400 */       this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
/* 401 */       if (this.bipedHead.rotateAngleX > 0.6D) {
/* 402 */         this.bipedHead.rotateAngleX = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 406 */     float f = 1.0F;
/* 407 */     this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 408 */     this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 409 */     this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 410 */     this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 411 */     if (!entity.getHeldItemMainhand().isEmpty()) {
/* 412 */       this.rightArm.rotateAngleX += -0.15F;
/*     */     }
/*     */     
/* 415 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 416 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 417 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 419 */       if (isBlackLeg) {
/*     */         
/* 421 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 422 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY;
/* 423 */         this.leftLeg.rotateAngleY += this.body.rotateAngleY;
/* 424 */         float f1 = 1.0F - this.swingProgress;
/* 425 */         f1 *= f1;
/* 426 */         f1 *= f1;
/* 427 */         f1 = 1.0F - f1;
/* 428 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 429 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 430 */         this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 431 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 435 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 436 */         this.rightArm.rotationPointZ = MathHelper.sin(this.body2.rotateAngleY) * 5.0F;
/* 437 */         this.rightArm.rotationPointX = -MathHelper.cos(this.body2.rotateAngleY) * 5.0F;
/* 438 */         this.leftArm.rotationPointZ = -MathHelper.sin(this.body2.rotateAngleY) * 5.0F;
/* 439 */         this.leftArm.rotationPointX = MathHelper.cos(this.body2.rotateAngleY) * 5.0F;
/* 440 */         this.rightArm.rotateAngleY += this.body2.rotateAngleY;
/* 441 */         this.leftArm.rotateAngleY += this.body2.rotateAngleY;
/* 442 */         this.leftArm.rotateAngleX += this.body2.rotateAngleY;
/* 443 */         float f1 = 1.0F - this.swingProgress;
/* 444 */         f1 *= f1;
/* 445 */         f1 *= f1;
/* 446 */         f1 = 1.0F - f1;
/* 447 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 448 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 1.75F;
/* 449 */         this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
/* 450 */         this.rightArm.rotateAngleY += this.bipedBody.rotateAngleY * 4.0F;
/* 451 */         this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */     
/* 455 */     if (entity.isSneaking()) {
/*     */       
/* 457 */       this.body.rotateAngleX = 0.5F;
/* 458 */       this.body.rotationPointZ -= 4.0F;
/* 459 */       this.rightArm.rotateAngleX += 0.4F;
/* 460 */       this.rightArm.rotationPointZ -= 2.5F;
/* 461 */       this.leftArm.rotateAngleX += 0.4F;
/* 462 */       this.leftArm.rotationPointZ -= 2.5F;
/* 463 */       this.rightLeg.rotationPointZ = 3.0F;
/* 464 */       this.rightLeg.rotationPointY = 15.0F;
/* 465 */       this.leftLeg.rotationPointZ = 3.0F;
/* 466 */       this.leftLeg.rotationPointY = 15.0F;
/* 467 */       this.head.rotationPointZ = -6.0F;
/* 468 */       this.head.rotationPointY = 1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 475 */     super.translateHand(side, matrixStack);
/* 476 */     matrixStack.translate((side == HandSide.RIGHT) ? -0.1D : 0.1D, 0.5D, 0.0D);
/* 477 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -10.0F : 10.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 483 */     if (side == HandSide.RIGHT) {
/*     */       
/* 485 */       matrixStack.translate(-0.5D, -0.05D, 0.0D);
/* 486 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(20.0F));
/* 487 */       this.rightHand.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 491 */       matrixStack.translate(0.5D, -0.05D, 0.0D);
/* 492 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-20.0F));
/* 493 */       this.leftHand.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 500 */     if (side == HandSide.RIGHT) {
/*     */       
/* 502 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 503 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 504 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 505 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 509 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 510 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 511 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 512 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 518 */     modelRenderer.rotateAngleX = x;
/* 519 */     modelRenderer.rotateAngleY = y;
/* 520 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\MoguMoleModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */