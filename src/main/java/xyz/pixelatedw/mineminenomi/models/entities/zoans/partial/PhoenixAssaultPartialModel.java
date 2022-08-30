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
/*     */ public class PhoenixAssaultPartialModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> {
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer rightWing2;
/*     */   private final ModelRenderer rightWingLayer1;
/*     */   private final ModelRenderer rightWingLayer1b;
/*     */   private final ModelRenderer rightWingLayer2;
/*     */   private final ModelRenderer rightWingLayer2b;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer leftWing2;
/*     */   private final ModelRenderer leftWingLayer1;
/*     */   private final ModelRenderer leftWingLayer1b;
/*     */   private final ModelRenderer leftWingLayer2;
/*     */   private final ModelRenderer leftWingLayer2b;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightTalon1;
/*     */   private final ModelRenderer rightTalon1b;
/*     */   private final ModelRenderer rightTalon1b_r1;
/*     */   private final ModelRenderer rightTalon1c;
/*     */   private final ModelRenderer rightTalon1c_r1;
/*     */   private final ModelRenderer rightTalon2;
/*     */   private final ModelRenderer rightTalon2b;
/*     */   private final ModelRenderer rightTalon2b_r1;
/*     */   private final ModelRenderer rightTalon2c;
/*     */   private final ModelRenderer rightTalon2c_r1;
/*     */   private final ModelRenderer rightTalon3;
/*     */   private final ModelRenderer rightTalon3_r1;
/*     */   private final ModelRenderer rightTalon3b;
/*     */   private final ModelRenderer rightTalon3c;
/*     */   private final ModelRenderer rightTalon4;
/*     */   private final ModelRenderer rightTalon4b;
/*     */   private final ModelRenderer rightTalon4b_r1;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftTalon1;
/*     */   private final ModelRenderer leftTalon1_r1;
/*     */   private final ModelRenderer leftTalon1b;
/*     */   private final ModelRenderer leftTalon1c;
/*     */   private final ModelRenderer leftTalon1c_r1;
/*     */   private final ModelRenderer leftTalon2;
/*     */   private final ModelRenderer leftTalon2_r1;
/*     */   private final ModelRenderer leftTalon2b;
/*     */   private final ModelRenderer leftTalon2c;
/*     */   private final ModelRenderer leftTalon3;
/*     */   private final ModelRenderer leftTalon3_r1;
/*     */   private final ModelRenderer leftTalon3b;
/*     */   private final ModelRenderer leftTalon3c;
/*     */   private final ModelRenderer leftTalon4;
/*     */   private final ModelRenderer leftTalon4b;
/*     */   private final ModelRenderer tail1;
/*     */   private final ModelRenderer tail1b;
/*     */   private final ModelRenderer tail1c;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail2b;
/*     */   private final ModelRenderer tail2c;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer tail3b;
/*     */   private final ModelRenderer tail3c;
/*     */   
/*     */   public PhoenixAssaultPartialModel() {
/*  79 */     super(1.0F);
/*  80 */     this.textureWidth = 128;
/*  81 */     this.textureHeight = 64;
/*     */     
/*  83 */     this.rightWing = new ModelRenderer((Model)this);
/*  84 */     this.rightWing.setRotationPoint(-1.5F, 1.0F, 2.5F);
/*  85 */     setRotationAngle(this.rightWing, 1.5708F, 0.0F, -1.3963F);
/*  86 */     this.rightWing.setTextureOffset(71, 54).addBox(-13.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/*  88 */     this.rightWing2 = new ModelRenderer((Model)this);
/*  89 */     this.rightWing2.setRotationPoint(-12.1F, -5.05F, 1.0F);
/*  90 */     this.rightWing.addChild(this.rightWing2);
/*  91 */     setRotationAngle(this.rightWing2, 0.0F, 0.0F, 0.1047F);
/*  92 */     this.rightWing2.setTextureOffset(98, 52).addBox(-14.9F, 0.0F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/*  94 */     this.rightWingLayer1 = new ModelRenderer((Model)this);
/*  95 */     this.rightWingLayer1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  96 */     this.rightWing.addChild(this.rightWingLayer1);
/*  97 */     setRotationAngle(this.rightWingLayer1, -0.1745F, 0.0F, 0.0F);
/*  98 */     this.rightWingLayer1.setTextureOffset(71, 54).addBox(-13.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 100 */     this.rightWingLayer1b = new ModelRenderer((Model)this);
/* 101 */     this.rightWingLayer1b.setRotationPoint(-12.1F, -5.05F, 1.0F);
/* 102 */     this.rightWingLayer1.addChild(this.rightWingLayer1b);
/* 103 */     setRotationAngle(this.rightWingLayer1b, 0.0F, 0.0F, 0.1047F);
/* 104 */     this.rightWingLayer1b.setTextureOffset(98, 52).addBox(-14.9F, 0.0F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 106 */     this.rightWingLayer2 = new ModelRenderer((Model)this);
/* 107 */     this.rightWingLayer2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 108 */     this.rightWing.addChild(this.rightWingLayer2);
/* 109 */     setRotationAngle(this.rightWingLayer2, 0.1745F, 0.0F, 0.0F);
/* 110 */     this.rightWingLayer2.setTextureOffset(71, 54).addBox(-13.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 112 */     this.rightWingLayer2b = new ModelRenderer((Model)this);
/* 113 */     this.rightWingLayer2b.setRotationPoint(-12.1F, -5.05F, 1.0F);
/* 114 */     this.rightWingLayer2.addChild(this.rightWingLayer2b);
/* 115 */     setRotationAngle(this.rightWingLayer2b, 0.0F, 0.0F, 0.1047F);
/* 116 */     this.rightWingLayer2b.setTextureOffset(98, 52).addBox(-14.9F, 0.0F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 118 */     this.leftWing = new ModelRenderer((Model)this);
/* 119 */     this.leftWing.setRotationPoint(1.5F, 1.0F, 2.5F);
/* 120 */     setRotationAngle(this.leftWing, 1.5708F, 0.0F, 1.3963F);
/* 121 */     this.leftWing.setTextureOffset(72, 39).addBox(0.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 123 */     this.leftWing2 = new ModelRenderer((Model)this);
/* 124 */     this.leftWing2.setRotationPoint(12.0F, -5.05F, 1.0F);
/* 125 */     this.leftWing.addChild(this.leftWing2);
/* 126 */     setRotationAngle(this.leftWing2, 0.0F, 0.0F, -0.1047F);
/* 127 */     this.leftWing2.setTextureOffset(98, 39).addBox(0.1055F, -0.0545F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 129 */     this.leftWingLayer1 = new ModelRenderer((Model)this);
/* 130 */     this.leftWingLayer1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 131 */     this.leftWing.addChild(this.leftWingLayer1);
/* 132 */     setRotationAngle(this.leftWingLayer1, 0.1745F, 0.0F, 0.0F);
/* 133 */     this.leftWingLayer1.setTextureOffset(72, 39).addBox(0.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 135 */     this.leftWingLayer1b = new ModelRenderer((Model)this);
/* 136 */     this.leftWingLayer1b.setRotationPoint(12.0F, -5.05F, 1.0F);
/* 137 */     this.leftWingLayer1.addChild(this.leftWingLayer1b);
/* 138 */     setRotationAngle(this.leftWingLayer1b, 0.0F, 0.0F, -0.1047F);
/* 139 */     this.leftWingLayer1b.setTextureOffset(98, 39).addBox(0.1055F, -0.0545F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 141 */     this.leftWingLayer2 = new ModelRenderer((Model)this);
/* 142 */     this.leftWingLayer2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 143 */     this.leftWing.addChild(this.leftWingLayer2);
/* 144 */     setRotationAngle(this.leftWingLayer2, -0.1745F, 0.0F, 0.0F);
/* 145 */     this.leftWingLayer2.setTextureOffset(72, 39).addBox(0.0F, -5.0F, 1.0F, 13.0F, 10.0F, 0.0F, 0.0F, false);
/*     */     
/* 147 */     this.leftWingLayer2b = new ModelRenderer((Model)this);
/* 148 */     this.leftWingLayer2b.setRotationPoint(12.0F, -5.05F, 1.0F);
/* 149 */     this.leftWingLayer2.addChild(this.leftWingLayer2b);
/* 150 */     setRotationAngle(this.leftWingLayer2b, 0.0F, 0.0F, -0.1047F);
/* 151 */     this.leftWingLayer2b.setTextureOffset(98, 39).addBox(0.1055F, -0.0545F, 0.0F, 15.0F, 12.0F, 0.0F, 0.0F, false);
/*     */     
/* 153 */     this.rightLeg = new ModelRenderer((Model)this);
/* 154 */     this.rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/* 155 */     this.rightLeg.setTextureOffset(0, 34).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 157 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 158 */     this.rightLeg2.setRotationPoint(0.0F, 4.75F, 0.0F);
/* 159 */     this.rightLeg.addChild(this.rightLeg2);
/* 160 */     this.rightLeg2.setTextureOffset(0, 45).addBox(-1.5F, 0.0F, -1.25F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 162 */     this.rightLeg3 = new ModelRenderer((Model)this);
/* 163 */     this.rightLeg3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 164 */     this.rightLeg2.addChild(this.rightLeg3);
/* 165 */     this.rightLeg3.setTextureOffset(0, 54).addBox(-1.0F, 3.0F, -0.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 167 */     this.rightTalon1 = new ModelRenderer((Model)this);
/* 168 */     this.rightTalon1.setRotationPoint(-0.75F, 6.5F, 0.25F);
/* 169 */     this.rightLeg3.addChild(this.rightTalon1);
/* 170 */     setRotationAngle(this.rightTalon1, 0.0437F, 0.1309F, 0.0F);
/* 171 */     this.rightTalon1.setTextureOffset(13, 46).addBox(-0.5F, -0.5F, -2.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 173 */     this.rightTalon1b = new ModelRenderer((Model)this);
/* 174 */     this.rightTalon1b.setRotationPoint(0.0F, 0.0F, -1.5F);
/* 175 */     this.rightTalon1.addChild(this.rightTalon1b);
/* 176 */     setRotationAngle(this.rightTalon1b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 179 */     this.rightTalon1b_r1 = new ModelRenderer((Model)this);
/* 180 */     this.rightTalon1b_r1.setRotationPoint(2.75F, 0.75F, 1.25F);
/* 181 */     this.rightTalon1b.addChild(this.rightTalon1b_r1);
/* 182 */     setRotationAngle(this.rightTalon1b_r1, 0.0436F, 0.0F, 0.0F);
/* 183 */     this.rightTalon1b_r1.setTextureOffset(13, 53).addBox(-3.25F, -1.15F, -3.7264F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 185 */     this.rightTalon1c = new ModelRenderer((Model)this);
/* 186 */     this.rightTalon1c.setRotationPoint(0.0F, 0.0F, -1.0F);
/* 187 */     this.rightTalon1b.addChild(this.rightTalon1c);
/* 188 */     setRotationAngle(this.rightTalon1c, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 191 */     this.rightTalon1c_r1 = new ModelRenderer((Model)this);
/* 192 */     this.rightTalon1c_r1.setRotationPoint(2.75F, 0.75F, 1.25F);
/* 193 */     this.rightTalon1c.addChild(this.rightTalon1c_r1);
/* 194 */     setRotationAngle(this.rightTalon1c_r1, 0.0436F, 0.0F, 0.0F);
/* 195 */     this.rightTalon1c_r1.setTextureOffset(13, 53).addBox(-3.25F, -1.15F, -3.9764F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 197 */     this.rightTalon2 = new ModelRenderer((Model)this);
/* 198 */     this.rightTalon2.setRotationPoint(0.0F, 6.5F, 0.25F);
/* 199 */     this.rightLeg3.addChild(this.rightTalon2);
/* 200 */     setRotationAngle(this.rightTalon2, 0.0437F, 0.0F, 0.0F);
/* 201 */     this.rightTalon2.setTextureOffset(13, 46).addBox(-0.5F, -0.5F, -2.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 203 */     this.rightTalon2b = new ModelRenderer((Model)this);
/* 204 */     this.rightTalon2b.setRotationPoint(0.0F, 0.0F, -1.5F);
/* 205 */     this.rightTalon2.addChild(this.rightTalon2b);
/* 206 */     setRotationAngle(this.rightTalon2b, 0.1833F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 209 */     this.rightTalon2b_r1 = new ModelRenderer((Model)this);
/* 210 */     this.rightTalon2b_r1.setRotationPoint(2.0F, 0.75F, 1.25F);
/* 211 */     this.rightTalon2b.addChild(this.rightTalon2b_r1);
/* 212 */     setRotationAngle(this.rightTalon2b_r1, -0.1745F, 0.0F, 0.0F);
/* 213 */     this.rightTalon2b_r1.setTextureOffset(13, 53).addBox(-2.5F, -0.9F, -3.9764F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 215 */     this.rightTalon2c = new ModelRenderer((Model)this);
/* 216 */     this.rightTalon2c.setRotationPoint(0.0F, -0.25F, -2.75F);
/* 217 */     this.rightTalon2b.addChild(this.rightTalon2c);
/* 218 */     setRotationAngle(this.rightTalon2c, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 221 */     this.rightTalon2c_r1 = new ModelRenderer((Model)this);
/* 222 */     this.rightTalon2c_r1.setRotationPoint(2.0F, 0.8154F, 2.7486F);
/* 223 */     this.rightTalon2c.addChild(this.rightTalon2c_r1);
/* 224 */     setRotationAngle(this.rightTalon2c_r1, -0.1745F, 0.0F, 0.0F);
/* 225 */     this.rightTalon2c_r1.setTextureOffset(13, 53).addBox(-2.5F, -0.8F, -3.9764F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 227 */     this.rightTalon3 = new ModelRenderer((Model)this);
/* 228 */     this.rightTalon3.setRotationPoint(0.75F, 6.5F, 0.25F);
/* 229 */     this.rightLeg3.addChild(this.rightTalon3);
/* 230 */     setRotationAngle(this.rightTalon3, 0.0873F, -0.1309F, 0.0F);
/*     */ 
/*     */     
/* 233 */     this.rightTalon3_r1 = new ModelRenderer((Model)this);
/* 234 */     this.rightTalon3_r1.setRotationPoint(1.25F, 0.75F, -0.25F);
/* 235 */     this.rightTalon3.addChild(this.rightTalon3_r1);
/* 236 */     setRotationAngle(this.rightTalon3_r1, -0.0436F, 0.0F, 0.0F);
/* 237 */     this.rightTalon3_r1.setTextureOffset(13, 46).addBox(-1.75F, -1.25F, -1.98F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 239 */     this.rightTalon3b = new ModelRenderer((Model)this);
/* 240 */     this.rightTalon3b.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 241 */     this.rightTalon3.addChild(this.rightTalon3b);
/* 242 */     setRotationAngle(this.rightTalon3b, -0.0436F, 0.0F, 0.0F);
/* 243 */     this.rightTalon3b.setTextureOffset(13, 53).addBox(-0.5F, -0.45F, -1.9883F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 245 */     this.rightTalon3c = new ModelRenderer((Model)this);
/* 246 */     this.rightTalon3c.setRotationPoint(0.0F, 0.0F, -1.25F);
/* 247 */     this.rightTalon3b.addChild(this.rightTalon3c);
/* 248 */     setRotationAngle(this.rightTalon3c, 0.0436F, 0.0F, 0.0F);
/* 249 */     this.rightTalon3c.setTextureOffset(13, 53).addBox(-0.5F, -0.35F, -1.9883F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 251 */     this.rightTalon4 = new ModelRenderer((Model)this);
/* 252 */     this.rightTalon4.setRotationPoint(0.0F, 6.5F, 0.0F);
/* 253 */     this.rightLeg3.addChild(this.rightTalon4);
/* 254 */     setRotationAngle(this.rightTalon4, 0.0873F, 3.1416F, 0.0F);
/* 255 */     this.rightTalon4.setTextureOffset(13, 46).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 257 */     this.rightTalon4b = new ModelRenderer((Model)this);
/* 258 */     this.rightTalon4b.setRotationPoint(0.0F, 0.0F, -3.0F);
/* 259 */     this.rightTalon4.addChild(this.rightTalon4b);
/* 260 */     setRotationAngle(this.rightTalon4b, 0.1309F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 263 */     this.rightTalon4b_r1 = new ModelRenderer((Model)this);
/* 264 */     this.rightTalon4b_r1.setRotationPoint(2.0F, 0.9665F, 1.4763F);
/* 265 */     this.rightTalon4b.addChild(this.rightTalon4b_r1);
/* 266 */     setRotationAngle(this.rightTalon4b_r1, -0.0873F, 0.0F, 0.0F);
/* 267 */     this.rightTalon4b_r1.setTextureOffset(13, 53).addBox(-2.5F, -1.3165F, -2.9764F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 269 */     this.leftLeg = new ModelRenderer((Model)this);
/* 270 */     this.leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/* 271 */     this.leftLeg.setTextureOffset(0, 34).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 273 */     this.leftLeg2 = new ModelRenderer((Model)this);
/* 274 */     this.leftLeg2.setRotationPoint(0.0F, 4.75F, 0.0F);
/* 275 */     this.leftLeg.addChild(this.leftLeg2);
/* 276 */     this.leftLeg2.setTextureOffset(0, 45).addBox(-1.5F, 0.0F, -1.25F, 3.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 278 */     this.leftLeg3 = new ModelRenderer((Model)this);
/* 279 */     this.leftLeg3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 280 */     this.leftLeg2.addChild(this.leftLeg3);
/* 281 */     this.leftLeg3.setTextureOffset(0, 54).addBox(-1.0F, 3.0F, -0.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 283 */     this.leftTalon1 = new ModelRenderer((Model)this);
/* 284 */     this.leftTalon1.setRotationPoint(-0.75F, 6.5F, 0.25F);
/* 285 */     this.leftLeg3.addChild(this.leftTalon1);
/* 286 */     setRotationAngle(this.leftTalon1, 0.0873F, 0.1309F, 0.0F);
/*     */ 
/*     */     
/* 289 */     this.leftTalon1_r1 = new ModelRenderer((Model)this);
/* 290 */     this.leftTalon1_r1.setRotationPoint(-1.25F, 0.75F, -0.25F);
/* 291 */     this.leftTalon1.addChild(this.leftTalon1_r1);
/* 292 */     setRotationAngle(this.leftTalon1_r1, -0.0433F, -6.0E-4F, -0.0057F);
/* 293 */     this.leftTalon1_r1.setTextureOffset(13, 46).addBox(0.7892F, -1.2759F, -2.0463F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 295 */     this.leftTalon1b = new ModelRenderer((Model)this);
/* 296 */     this.leftTalon1b.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 297 */     this.leftTalon1.addChild(this.leftTalon1b);
/* 298 */     setRotationAngle(this.leftTalon1b, -0.0436F, 0.0F, 0.0F);
/* 299 */     this.leftTalon1b.setTextureOffset(13, 53).addBox(-0.4608F, -0.4895F, -2.0101F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 301 */     this.leftTalon1c = new ModelRenderer((Model)this);
/* 302 */     this.leftTalon1c.setRotationPoint(0.0F, 0.0F, -1.25F);
/* 303 */     this.leftTalon1b.addChild(this.leftTalon1c);
/* 304 */     setRotationAngle(this.leftTalon1c, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 307 */     this.leftTalon1c_r1 = new ModelRenderer((Model)this);
/* 308 */     this.leftTalon1c_r1.setRotationPoint(-1.25F, 0.75F, 1.25F);
/* 309 */     this.leftTalon1c.addChild(this.leftTalon1c_r1);
/* 310 */     setRotationAngle(this.leftTalon1c_r1, 0.0436F, 0.0F, 0.0F);
/* 311 */     this.leftTalon1c_r1.setTextureOffset(13, 53).addBox(0.7892F, -1.2395F, -3.2601F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 313 */     this.leftTalon2 = new ModelRenderer((Model)this);
/* 314 */     this.leftTalon2.setRotationPoint(0.0F, 6.5F, 0.25F);
/* 315 */     this.leftLeg3.addChild(this.leftTalon2);
/* 316 */     setRotationAngle(this.leftTalon2, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 319 */     this.leftTalon2_r1 = new ModelRenderer((Model)this);
/* 320 */     this.leftTalon2_r1.setRotationPoint(-2.0F, 0.75F, -0.25F);
/* 321 */     this.leftTalon2.addChild(this.leftTalon2_r1);
/* 322 */     setRotationAngle(this.leftTalon2_r1, -0.0436F, 0.0F, 0.0F);
/* 323 */     this.leftTalon2_r1.setTextureOffset(13, 46).addBox(1.5F, -1.2762F, -2.0489F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 325 */     this.leftTalon2b = new ModelRenderer((Model)this);
/* 326 */     this.leftTalon2b.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 327 */     this.leftTalon2.addChild(this.leftTalon2b);
/* 328 */     setRotationAngle(this.leftTalon2b, -0.0436F, 0.0F, 0.0F);
/* 329 */     this.leftTalon2b.setTextureOffset(13, 53).addBox(-0.5F, -0.4902F, -2.0125F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 331 */     this.leftTalon2c = new ModelRenderer((Model)this);
/* 332 */     this.leftTalon2c.setRotationPoint(0.0F, 0.0F, -1.25F);
/* 333 */     this.leftTalon2b.addChild(this.leftTalon2c);
/* 334 */     setRotationAngle(this.leftTalon2c, 0.0873F, 0.0F, 0.0F);
/* 335 */     this.leftTalon2c.setTextureOffset(13, 53).addBox(-0.5F, -0.4902F, -2.1F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 337 */     this.leftTalon3 = new ModelRenderer((Model)this);
/* 338 */     this.leftTalon3.setRotationPoint(0.75F, 6.5F, 0.25F);
/* 339 */     this.leftLeg3.addChild(this.leftTalon3);
/* 340 */     setRotationAngle(this.leftTalon3, 0.0873F, -0.1309F, 0.0F);
/*     */ 
/*     */     
/* 343 */     this.leftTalon3_r1 = new ModelRenderer((Model)this);
/* 344 */     this.leftTalon3_r1.setRotationPoint(-2.75F, 0.75F, -0.25F);
/* 345 */     this.leftTalon3.addChild(this.leftTalon3_r1);
/* 346 */     setRotationAngle(this.leftTalon3_r1, -0.0433F, 6.0E-4F, 0.0057F);
/* 347 */     this.leftTalon3_r1.setTextureOffset(13, 46).addBox(2.2108F, -1.2759F, -2.0463F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 349 */     this.leftTalon3b = new ModelRenderer((Model)this);
/* 350 */     this.leftTalon3b.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 351 */     this.leftTalon3.addChild(this.leftTalon3b);
/* 352 */     setRotationAngle(this.leftTalon3b, -0.0349F, 0.0F, 0.0F);
/* 353 */     this.leftTalon3b.setTextureOffset(13, 53).addBox(-0.5392F, -0.4895F, -2.0101F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 355 */     this.leftTalon3c = new ModelRenderer((Model)this);
/* 356 */     this.leftTalon3c.setRotationPoint(0.0F, 0.0F, -1.25F);
/* 357 */     this.leftTalon3b.addChild(this.leftTalon3c);
/* 358 */     setRotationAngle(this.leftTalon3c, 0.096F, 0.0F, 0.0F);
/* 359 */     this.leftTalon3c.setTextureOffset(13, 53).addBox(-0.5392F, -0.4895F, -2.0101F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 361 */     this.leftTalon4 = new ModelRenderer((Model)this);
/* 362 */     this.leftTalon4.setRotationPoint(0.0F, 6.5F, 0.0F);
/* 363 */     this.leftLeg3.addChild(this.leftTalon4);
/* 364 */     setRotationAngle(this.leftTalon4, 0.0873F, 3.1416F, 0.0F);
/* 365 */     this.leftTalon4.setTextureOffset(13, 46).addBox(-0.5F, -0.55F, -2.9962F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 367 */     this.leftTalon4b = new ModelRenderer((Model)this);
/* 368 */     this.leftTalon4b.setRotationPoint(0.0F, -0.25F, -3.0F);
/* 369 */     this.leftTalon4.addChild(this.leftTalon4b);
/* 370 */     setRotationAngle(this.leftTalon4b, 0.0437F, 0.0F, 0.0F);
/* 371 */     this.leftTalon4b.setTextureOffset(13, 53).addBox(-0.5F, -0.2F, -1.4F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 373 */     this.tail1 = new ModelRenderer((Model)this);
/* 374 */     this.tail1.setRotationPoint(0.5F, 11.25F, 2.0F);
/* 375 */     setRotationAngle(this.tail1, 0.0F, -0.2182F, 0.0F);
/* 376 */     this.tail1.setTextureOffset(18, 53).addBox(-2.2651F, -1.0F, -0.0855F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 378 */     this.tail1b = new ModelRenderer((Model)this);
/* 379 */     this.tail1b.setRotationPoint(0.0F, 0.0F, 8.5F);
/* 380 */     this.tail1.addChild(this.tail1b);
/* 381 */     setRotationAngle(this.tail1b, 0.0F, -0.1745F, 0.0F);
/* 382 */     this.tail1b.setTextureOffset(18, 53).addBox(-2.2835F, -1.0F, -0.125F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 384 */     this.tail1c = new ModelRenderer((Model)this);
/* 385 */     this.tail1c.setRotationPoint(-1.5F, 0.0F, 9.0F);
/* 386 */     this.tail1b.addChild(this.tail1c);
/* 387 */     setRotationAngle(this.tail1c, 0.0F, 0.1746F, 0.0F);
/* 388 */     this.tail1c.setTextureOffset(20, 45).addBox(-0.7708F, -1.0F, -0.2162F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 390 */     this.tail2 = new ModelRenderer((Model)this);
/* 391 */     this.tail2.setRotationPoint(0.0F, 11.25F, 2.0F);
/* 392 */     setRotationAngle(this.tail2, 0.0F, 0.1745F, 0.0F);
/* 393 */     this.tail2.setTextureOffset(18, 53).addBox(-0.2842F, -1.0F, -0.304F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 395 */     this.tail2b = new ModelRenderer((Model)this);
/* 396 */     this.tail2b.setRotationPoint(0.4696F, 0.0F, 8.1526F);
/* 397 */     this.tail2.addChild(this.tail2b);
/* 398 */     setRotationAngle(this.tail2b, 0.0F, 0.1746F, 0.0F);
/* 399 */     this.tail2b.setTextureOffset(18, 53).addBox(-0.7708F, -1.0F, -0.0452F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 401 */     this.tail2c = new ModelRenderer((Model)this);
/* 402 */     this.tail2c.setRotationPoint(-0.0057F, 0.0F, 8.8693F);
/* 403 */     this.tail2b.addChild(this.tail2c);
/* 404 */     setRotationAngle(this.tail2c, 0.0F, -0.1309F, 0.0F);
/* 405 */     this.tail2c.setTextureOffset(20, 45).addBox(-0.7616F, -1.0F, -0.0766F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 407 */     this.tail3 = new ModelRenderer((Model)this);
/* 408 */     this.tail3.setRotationPoint(-0.25F, 11.5F, 2.0F);
/* 409 */     setRotationAngle(this.tail3, 0.0F, 0.0F, 0.0F);
/* 410 */     this.tail3.setTextureOffset(18, 53).addBox(-1.0342F, -1.0F, -0.304F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 412 */     this.tail3b = new ModelRenderer((Model)this);
/* 413 */     this.tail3b.setRotationPoint(-0.2804F, 0.0F, 8.1526F);
/* 414 */     this.tail3.addChild(this.tail3b);
/* 415 */     setRotationAngle(this.tail3b, 0.0F, -0.0436F, 0.0F);
/* 416 */     this.tail3b.setTextureOffset(18, 53).addBox(-0.7708F, -1.0F, -0.0452F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 418 */     this.tail3c = new ModelRenderer((Model)this);
/* 419 */     this.tail3c.setRotationPoint(-0.0057F, 0.0F, 8.8693F);
/* 420 */     this.tail3b.addChild(this.tail3c);
/* 421 */     setRotationAngle(this.tail3c, 0.0F, 0.0436F, 0.0F);
/* 422 */     this.tail3c.setTextureOffset(20, 45).addBox(-0.7616F, -1.0F, -0.0766F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 424 */     this.bipedLeftLeg = this.leftLeg;
/* 425 */     this.bipedRightLeg = this.rightLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 431 */     matrixStack.push();
/* 432 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 433 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 434 */     this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 435 */     this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 436 */     this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 437 */     matrixStack.scale(1.6F, 1.6F, 1.6F);
/* 438 */     matrixStack.translate(0.0D, -0.1D, 0.0D);
/* 439 */     this.rightWing.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 440 */     this.leftWing.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/* 441 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 447 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/* 449 */     this.tail1.rotateAngleY = (float)(this.tail1.rotateAngleY + Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 450 */     this.tail1.rotateAngleX = (float)(this.tail1.rotateAngleX + Math.sin(ageInTicks * 0.01D) / 8.0D);
/* 451 */     this.tail2.rotateAngleY = (float)(this.tail2.rotateAngleY - Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 452 */     this.tail2.rotateAngleX = (float)(this.tail2.rotateAngleX - Math.sin(ageInTicks * 0.05D) / 8.0D);
/* 453 */     this.tail3.rotateAngleY = (float)(this.tail3.rotateAngleY + Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 454 */     this.tail3.rotateAngleX = (float)(this.tail3.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 8.0D);
/*     */     
/* 456 */     this.tail1b.rotateAngleY = (float)(this.tail1b.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 10.0D);
/* 457 */     this.tail1b.rotateAngleX = (float)(this.tail1b.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 5.0D);
/* 458 */     this.tail2b.rotateAngleY = (float)(this.tail2b.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 10.0D);
/* 459 */     this.tail2b.rotateAngleX = (float)(this.tail2b.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 5.0D);
/* 460 */     this.tail3b.rotateAngleY = (float)(this.tail3b.rotateAngleY - Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 461 */     this.tail3b.rotateAngleX = (float)(this.tail3b.rotateAngleX - Math.sin(ageInTicks * 0.01D) / 5.0D);
/*     */     
/* 463 */     this.tail1c.rotateAngleY = (float)(this.tail1c.rotateAngleY + Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 464 */     this.tail1c.rotateAngleX = (float)(this.tail1c.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 8.0D);
/* 465 */     this.tail2c.rotateAngleY = (float)(this.tail2c.rotateAngleY + Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 466 */     this.tail2c.rotateAngleX = (float)(this.tail2c.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 8.0D);
/* 467 */     this.tail3c.rotateAngleY = (float)(this.tail3c.rotateAngleY + Math.sin(ageInTicks * 0.08D) / 10.0D);
/* 468 */     this.tail3c.rotateAngleX = (float)(this.tail3c.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 8.0D);
/*     */     
/* 470 */     this.tail1.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.2F) * 0.3F;
/* 471 */     this.tail1b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.3F) * 0.8F;
/* 472 */     this.tail1c.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.4F) * 0.8F;
/* 473 */     this.tail2.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.2F + 3.1415927F) * 0.3F;
/* 474 */     this.tail2b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.3F + 3.1415927F) * 0.8F;
/* 475 */     this.tail2c.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.8F;
/* 476 */     this.tail3b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.3F + 3.1415927F) * 0.8F;
/* 477 */     this.tail3c.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.8F;
/*     */     
/* 479 */     if (!((LivingEntity)entity).onGround) {
/*     */       
/* 481 */       this.rightWing.rotationPointX--;
/* 482 */       this.rightWing.rotationPointY += 2.0F;
/* 483 */       this.rightWing.rotateAngleZ = 0.3F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D));
/* 484 */       this.rightWing.rotateAngleY = MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
/* 485 */       this.rightWing2.rotateAngleY = MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
/* 486 */       this.rightWingLayer1b.rotateAngleY = MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
/* 487 */       this.rightWingLayer2b.rotateAngleY = MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
/*     */       
/* 489 */       this.leftWing.rotationPointX += 0.3F;
/* 490 */       this.leftWing.rotationPointY += 1.55F;
/* 491 */       this.leftWing.rotateAngleZ = -0.3F - MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D));
/* 492 */       this.leftWing.rotateAngleY = -MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
/* 493 */       this.leftWing2.rotateAngleY = -MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
/* 494 */       this.leftWingLayer1b.rotateAngleY = -MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
/* 495 */       this.leftWingLayer2b.rotateAngleY = -MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.2D)) / 3.0F;
/*     */       
/* 497 */       if (!entity.getHeldItemMainhand().isEmpty()) {
/*     */         
/* 499 */         this.rightLeg.rotateAngleX -= 0.15F;
/* 500 */         this.rightLeg2.rotationPointY -= 3.0F;
/* 501 */         this.rightLeg2.rotateAngleX = (float)(this.rightLeg2.rotateAngleX - 0.4D);
/* 502 */         this.rightLeg2.rotateAngleY = (float)(this.rightLeg2.rotateAngleY + 0.8D);
/* 503 */         this.rightTalon1.rotateAngleX = (float)(this.rightTalon1.rotateAngleX + 0.7D);
/* 504 */         this.rightTalon1b.rotateAngleX = (float)(this.rightTalon1b.rotateAngleX + 0.7D);
/* 505 */         this.rightTalon2.rotateAngleX = (float)(this.rightTalon2.rotateAngleX + 0.5D);
/* 506 */         this.rightTalon2b.rotateAngleX = (float)(this.rightTalon2b.rotateAngleX + 0.7D);
/* 507 */         this.rightTalon3.rotateAngleX = (float)(this.rightTalon3.rotateAngleX + 0.4D);
/* 508 */         this.rightTalon3b.rotateAngleX = (float)(this.rightTalon3b.rotateAngleX + 0.7D);
/* 509 */         this.rightTalon4.rotateAngleX = (float)(this.rightTalon4.rotateAngleX + 0.6D);
/* 510 */         this.rightTalon4b.rotateAngleX = (float)(this.rightTalon4b.rotateAngleX + 0.7D);
/*     */       } 
/*     */ 
/*     */       
/* 514 */       this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 515 */       if (this.swingProgress > 0.0F && !EntityStatsCapability.get((LivingEntity)entity).isBlackLeg())
/*     */       {
/* 517 */         float swingProgress = ((LivingEntity)entity).swingProgress;
/* 518 */         this.bipedRightLeg.rotateAngleX -= ((LivingEntity)entity).swingProgress * 2.0F;
/* 519 */         this.bipedRightLeg.rotateAngleZ += ((LivingEntity)entity).swingProgress * 2.0F;
/* 520 */         this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F) * 0.2F;
/* 521 */         this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 522 */         this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 523 */         this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 524 */         this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 525 */         this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 526 */         this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 527 */         this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 532 */       this.rightWing2.showModel = false;
/* 533 */       this.rightWingLayer1b.showModel = false;
/* 534 */       this.rightWingLayer2b.showModel = false;
/* 535 */       this.leftWing2.showModel = false;
/* 536 */       this.leftWingLayer1b.showModel = false;
/* 537 */       this.leftWingLayer2b.showModel = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 543 */       float f = 1.0F;
/* 544 */       this.rightWing.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 545 */       this.leftWing.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/*     */ 
/*     */       
/* 548 */       this.rightWing.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 549 */       this.leftWing.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/*     */ 
/*     */       
/* 552 */       this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 553 */       boolean isBlackLeg = (EntityStatsCapability.get((LivingEntity)entity).isBlackLeg() && entity.getHeldItemMainhand().isEmpty());
/* 554 */       if (this.swingProgress > 0.0F && !isBlackLeg) {
/*     */         
/* 556 */         this.rightWing.rotateAngleY += this.bipedBody.rotateAngleY;
/* 557 */         float f1 = 1.0F - this.swingProgress;
/* 558 */         f1 *= f1;
/* 559 */         f1 *= f1;
/* 560 */         f1 = 1.0F - f1;
/* 561 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 562 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 563 */         this.rightWing.rotateAngleY -= (float)(this.rightWing.rotateAngleX - f2 * 1.5D + f3);
/* 564 */         this.rightWing.rotateAngleZ -= this.bipedBody.rotateAngleY * 2.0F;
/* 565 */         this.rightWing.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 580 */     if (side == HandSide.RIGHT) {
/*     */       
/* 582 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 583 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 584 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 585 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 589 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 590 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 591 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 592 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderItemInHand(T entity, HandSide side, MatrixStack matrixStack) {
/* 599 */     if (entity instanceof PlayerEntity)
/*     */     {
/* 601 */       if (!((PlayerEntity)entity).abilities.isFlying)
/*     */       {
/* 603 */         return false;
/*     */       }
/*     */     }
/* 606 */     this.rightLeg.translateRotate(matrixStack);
/* 607 */     matrixStack.rotate(Vector3f.YP.rotationDegrees(-40.0F));
/* 608 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(-30.0F));
/* 609 */     matrixStack.translate(-0.12D, -0.05D, 0.3D);
/* 610 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 615 */     modelRenderer.rotateAngleX = x;
/* 616 */     modelRenderer.rotateAngleY = y;
/* 617 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\PhoenixAssaultPartialModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */