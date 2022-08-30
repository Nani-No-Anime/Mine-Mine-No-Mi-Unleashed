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
/*     */ public class PhoenixFlyModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer head2;
/*     */   private final ModelRenderer beak;
/*     */   private final ModelRenderer beak2;
/*     */   private final ModelRenderer beak3;
/*     */   private final ModelRenderer beak4;
/*     */   private final ModelRenderer head3;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer neckFire;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer body4;
/*     */   private final ModelRenderer backFire;
/*     */   private final ModelRenderer rightWing;
/*     */   private final ModelRenderer rightWing2;
/*     */   private final ModelRenderer leftWing;
/*     */   private final ModelRenderer leftWing2;
/*     */   private final ModelRenderer tail1;
/*     */   private final ModelRenderer tail1b;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer tail2b;
/*     */   private final ModelRenderer tail3;
/*     */   private final ModelRenderer tail3b;
/*     */   private final ModelRenderer tail4;
/*     */   private final ModelRenderer tail4b;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftTalon5;
/*     */   private final ModelRenderer rightTalon3_r1;
/*     */   private final ModelRenderer rightTalon3_r2;
/*     */   private final ModelRenderer rightTalon2_r1;
/*     */   private final ModelRenderer leftTalon1b;
/*     */   private final ModelRenderer leftTalon1b_r;
/*     */   private final ModelRenderer rightTalon3b_r3_r1;
/*     */   private final ModelRenderer rightTalon3b_r3_r2;
/*     */   private final ModelRenderer rightTalon2b_r2_r1;
/*     */   private final ModelRenderer leftTalon1c;
/*     */   private final ModelRenderer leftTalon1c_r;
/*     */   private final ModelRenderer rightTalon3c_r3_r1;
/*     */   private final ModelRenderer rightTalon3c_r3_r2;
/*     */   private final ModelRenderer rightTalon2c_r2_r1;
/*     */   private final ModelRenderer leftTalon6;
/*     */   private final ModelRenderer leftTalonb2;
/*     */   private final ModelRenderer leftTalonb_r2;
/*     */   private final ModelRenderer leftTalonc2;
/*     */   private final ModelRenderer leftTalonc_r2;
/*     */   private final ModelRenderer leftTalon7;
/*     */   private final ModelRenderer leftTalon3_r;
/*     */   private final ModelRenderer leftTalon3b;
/*     */   private final ModelRenderer leftTalon3c;
/*     */   private final ModelRenderer leftTalon8;
/*     */   private final ModelRenderer rightTalon5_r1;
/*     */   private final ModelRenderer leftTalon4b;
/*     */   private final ModelRenderer leftTalon4b_r;
/*     */   private final ModelRenderer rightTalon4b_r2_r1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightTalon;
/*     */   private final ModelRenderer rightTalon4_r1;
/*     */   private final ModelRenderer rightTalon4_r2;
/*     */   private final ModelRenderer rightTalon3_r3;
/*     */   private final ModelRenderer rightTalon1b;
/*     */   private final ModelRenderer rightTalon1b_r;
/*     */   private final ModelRenderer rightTalon4b_r4_r1;
/*     */   private final ModelRenderer rightTalon4b_r4_r2;
/*     */   private final ModelRenderer rightTalon3b_r3_r3;
/*     */   private final ModelRenderer rightTalon1c;
/*     */   private final ModelRenderer rightTalon1c_r;
/*     */   private final ModelRenderer rightTalon4c_r4_r1;
/*     */   private final ModelRenderer rightTalon4c_r4_r2;
/*     */   private final ModelRenderer rightTalon3c_r3_r3;
/*     */   private final ModelRenderer rightTalon3;
/*     */   private final ModelRenderer rightTalonb3;
/*     */   private final ModelRenderer rightTalonb_r3;
/*     */   private final ModelRenderer rightTalonc3;
/*     */   private final ModelRenderer rightTalonc_r3;
/*     */   private final ModelRenderer rightTalon4;
/*     */   private final ModelRenderer rightTalon3_r;
/*     */   private final ModelRenderer rightTalon3b;
/*     */   private final ModelRenderer rightTalon3c;
/*     */   private final ModelRenderer rightTalon9;
/*     */   private final ModelRenderer rightTalon6_r1;
/*     */   private final ModelRenderer rightTalon4b;
/*     */   private final ModelRenderer rightTalon4b_r;
/*     */   private final ModelRenderer rightTalon4b_r3_r1;
/*     */   
/*     */   public PhoenixFlyModel() {
/* 107 */     super(1.0F);
/* 108 */     this.textureWidth = 128;
/* 109 */     this.textureHeight = 64;
/*     */ 
/*     */     
/* 112 */     this.head = new ModelRenderer((Model)this);
/* 113 */     this.head.setRotationPoint(-1.5F, 1.2F, -16.0F);
/* 114 */     setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
/* 115 */     this.head.setTextureOffset(0, 4).addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 117 */     this.head2 = new ModelRenderer((Model)this);
/* 118 */     this.head2.setRotationPoint(0.0F, 1.0F, -1.7F);
/* 119 */     this.head.addChild(this.head2);
/* 120 */     setRotationAngle(this.head2, -0.0175F, 0.0F, 0.0F);
/* 121 */     this.head2.setTextureOffset(0, 10).addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 123 */     this.beak = new ModelRenderer((Model)this);
/* 124 */     this.beak.setRotationPoint(0.5F, 0.8F, -1.3F);
/* 125 */     this.head2.addChild(this.beak);
/* 126 */     setRotationAngle(this.beak, -0.0175F, 0.0F, 0.0F);
/* 127 */     this.beak.setTextureOffset(33, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
/*     */     
/* 129 */     this.beak2 = new ModelRenderer((Model)this);
/* 130 */     this.beak2.setRotationPoint(1.5F, 0.0F, 2.0F);
/* 131 */     this.beak.addChild(this.beak2);
/* 132 */     setRotationAngle(this.beak2, 0.0F, 0.2618F, 0.0F);
/* 133 */     this.beak2.setTextureOffset(33, 4).addBox(0.0F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 135 */     this.beak3 = new ModelRenderer((Model)this);
/* 136 */     this.beak3.setRotationPoint(0.5F, 0.0F, 2.0F);
/* 137 */     this.beak.addChild(this.beak3);
/* 138 */     setRotationAngle(this.beak3, 0.0F, -0.2618F, 0.0F);
/* 139 */     this.beak3.setTextureOffset(33, 9).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
/*     */     
/* 141 */     this.beak4 = new ModelRenderer((Model)this);
/* 142 */     this.beak4.setRotationPoint(1.0F, 0.0F, -1.8F);
/* 143 */     this.beak.addChild(this.beak4);
/* 144 */     setRotationAngle(this.beak4, 0.0F, 0.7854F, 0.0F);
/* 145 */     this.beak4.setTextureOffset(33, 14).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, -0.01F, false);
/*     */     
/* 147 */     this.head3 = new ModelRenderer((Model)this);
/* 148 */     this.head3.setRotationPoint(0.0F, 1.1F, -1.7F);
/* 149 */     this.head.addChild(this.head3);
/* 150 */     setRotationAngle(this.head3, 0.5236F, 0.0F, 0.0F);
/* 151 */     this.head3.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 153 */     this.neck = new ModelRenderer((Model)this);
/* 154 */     this.neck.setRotationPoint(-1.0F, 2.0F, -14.0F);
/* 155 */     setRotationAngle(this.neck, 0.0175F, 0.0F, 0.0F);
/* 156 */     this.neck.setTextureOffset(11, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 158 */     this.neckFire = new ModelRenderer((Model)this);
/* 159 */     this.neckFire.setRotationPoint(1.0F, -2.9956F, -1.75F);
/* 160 */     this.neck.addChild(this.neckFire);
/* 161 */     setRotationAngle(this.neckFire, -0.0175F, 0.0F, 0.0F);
/* 162 */     this.neckFire.setTextureOffset(78, 3).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
/*     */     
/* 164 */     this.body = new ModelRenderer((Model)this);
/* 165 */     this.body.setRotationPoint(-2.5F, 6.0F, -9.0F);
/* 166 */     setRotationAngle(this.body, 1.5708F, 0.0F, 0.0F);
/* 167 */     this.body.setTextureOffset(0, 18).addBox(0.0F, 0.0F, 0.0F, 5.0F, 18.0F, 6.0F, 0.0F, false);
/*     */     
/* 169 */     this.body2 = new ModelRenderer((Model)this);
/* 170 */     this.body2.setRotationPoint(-2.0F, 1.5F, 1.0F);
/* 171 */     this.body.addChild(this.body2);
/* 172 */     this.body2.setTextureOffset(23, 19).addBox(0.0F, 0.0F, 0.0F, 9.0F, 15.0F, 4.0F, 0.0F, false);
/*     */     
/* 174 */     this.body3 = new ModelRenderer((Model)this);
/* 175 */     this.body3.setRotationPoint(-1.0F, 1.0F, 0.5F);
/* 176 */     this.body.addChild(this.body3);
/* 177 */     this.body3.setTextureOffset(0, 43).addBox(0.0F, 0.0F, 0.0F, 7.0F, 16.0F, 5.0F, 0.0F, false);
/*     */     
/* 179 */     this.body4 = new ModelRenderer((Model)this);
/* 180 */     this.body4.setRotationPoint(0.5F, -1.0F, 1.0F);
/* 181 */     this.body.addChild(this.body4);
/* 182 */     this.body4.setTextureOffset(25, 39).addBox(0.0F, 0.0F, 0.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
/*     */     
/* 184 */     this.backFire = new ModelRenderer((Model)this);
/* 185 */     this.backFire.setRotationPoint(2.5F, 0.0F, 9.0F);
/* 186 */     this.body.addChild(this.backFire);
/* 187 */     setRotationAngle(this.backFire, -1.5708F, 0.0F, 0.0F);
/* 188 */     this.backFire.setTextureOffset(78, -12).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 18.0F, 0.0F, false);
/*     */     
/* 190 */     this.rightWing = new ModelRenderer((Model)this);
/* 191 */     this.rightWing.setRotationPoint(-4.0F, 2.0F, -7.0F);
/* 192 */     setRotationAngle(this.rightWing, 0.0F, -0.0436F, 0.0F);
/* 193 */     this.rightWing.setTextureOffset(65, 20).addBox(-13.0F, 0.5F, 0.0F, 13.0F, 0.0F, 10.0F, 0.0F, false);
/*     */     
/* 195 */     this.rightWing2 = new ModelRenderer((Model)this);
/* 196 */     this.rightWing2.setRotationPoint(-12.0F, 0.0F, 0.0F);
/* 197 */     this.rightWing.addChild(this.rightWing2);
/* 198 */     setRotationAngle(this.rightWing2, 0.0F, -0.0698F, 0.0F);
/* 199 */     this.rightWing2.setTextureOffset(55, 31).addBox(-15.0F, 0.5F, 0.0F, 15.0F, 0.0F, 12.0F, 0.0F, false);
/*     */     
/* 201 */     this.leftWing = new ModelRenderer((Model)this);
/* 202 */     this.leftWing.setRotationPoint(4.0F, 2.0F, -7.0F);
/* 203 */     setRotationAngle(this.leftWing, 0.0F, 0.0436F, 0.0F);
/* 204 */     this.leftWing.setTextureOffset(92, 20).addBox(0.0F, 0.5F, 0.0F, 13.0F, 0.0F, 10.0F, 0.0F, false);
/*     */     
/* 206 */     this.leftWing2 = new ModelRenderer((Model)this);
/* 207 */     this.leftWing2.setRotationPoint(12.0F, 0.0F, 0.0F);
/* 208 */     this.leftWing.addChild(this.leftWing2);
/* 209 */     setRotationAngle(this.leftWing2, 0.0F, 0.0524F, 0.0F);
/* 210 */     this.leftWing2.setTextureOffset(86, 31).addBox(0.0F, 0.5F, 0.0F, 15.0F, 0.0F, 12.0F, 0.0F, false);
/*     */     
/* 212 */     this.tail1 = new ModelRenderer((Model)this);
/* 213 */     this.tail1.setRotationPoint(0.5F, 3.0F, 11.0F);
/* 214 */     setRotationAngle(this.tail1, 0.0F, -0.0873F, 0.0F);
/* 215 */     this.tail1.setTextureOffset(110, 55).addBox(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 217 */     this.tail1b = new ModelRenderer((Model)this);
/* 218 */     this.tail1b.setRotationPoint(0.0F, 0.0F, 8.5F);
/* 219 */     this.tail1.addChild(this.tail1b);
/* 220 */     this.tail1b.setTextureOffset(117, 57).addBox(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 222 */     this.tail2 = new ModelRenderer((Model)this);
/* 223 */     this.tail2.setRotationPoint(1.25F, 3.0F, 11.0F);
/* 224 */     this.tail2.setTextureOffset(110, 55).addBox(-1.4924F, 0.0F, -0.1744F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 226 */     this.tail2b = new ModelRenderer((Model)this);
/* 227 */     this.tail2b.setRotationPoint(-1.3924F, 0.0F, 8.3256F);
/* 228 */     this.tail2.addChild(this.tail2b);
/* 229 */     this.tail2b.setTextureOffset(117, 57).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 231 */     this.tail3 = new ModelRenderer((Model)this);
/* 232 */     this.tail3.setRotationPoint(3.0F, 3.0F, 10.0F);
/* 233 */     setRotationAngle(this.tail3, 0.0F, 0.0873F, 0.0F);
/* 234 */     this.tail3.setTextureOffset(110, 55).addBox(-1.4924F, 0.0F, -0.1744F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 236 */     this.tail3b = new ModelRenderer((Model)this);
/* 237 */     this.tail3b.setRotationPoint(-1.3924F, 0.0F, 8.3256F);
/* 238 */     this.tail3.addChild(this.tail3b);
/* 239 */     setRotationAngle(this.tail3b, 0.0F, 0.1309F, 0.0F);
/* 240 */     this.tail3b.setTextureOffset(117, 57).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 242 */     this.tail4 = new ModelRenderer((Model)this);
/* 243 */     this.tail4.setRotationPoint(-0.75F, 3.0F, 10.25F);
/* 244 */     setRotationAngle(this.tail4, 0.0F, -0.2618F, 0.0F);
/* 245 */     this.tail4.setTextureOffset(110, 55).addBox(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, 0.0F, false);
/*     */     
/* 247 */     this.tail4b = new ModelRenderer((Model)this);
/* 248 */     this.tail4b.setRotationPoint(0.0F, 0.0F, 8.5F);
/* 249 */     this.tail4.addChild(this.tail4b);
/* 250 */     this.tail4b.setTextureOffset(117, 57).addBox(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
/*     */     
/* 252 */     this.leftLeg = new ModelRenderer((Model)this);
/* 253 */     this.leftLeg.setRotationPoint(2.0F, 10.0F, 1.0F);
/* 254 */     setRotationAngle(this.leftLeg, 0.48F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 257 */     this.leftLeg2 = new ModelRenderer((Model)this);
/* 258 */     this.leftLeg2.setRotationPoint(0.0F, 4.75F, 0.0F);
/* 259 */     this.leftLeg.addChild(this.leftLeg2);
/* 260 */     this.leftLeg2.setTextureOffset(43, 46).addBox(-1.5F, -7.0F, 5.75F, 3.0F, 3.0F, 3.0F, -0.001F, true);
/*     */     
/* 262 */     this.leftLeg3 = new ModelRenderer((Model)this);
/* 263 */     this.leftLeg3.setRotationPoint(0.0F, -5.0F, 7.25F);
/* 264 */     this.leftLeg2.addChild(this.leftLeg3);
/* 265 */     setRotationAngle(this.leftLeg3, 0.5236F, 0.0F, 0.0F);
/* 266 */     this.leftLeg3.setTextureOffset(45, 54).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 268 */     this.leftTalon5 = new ModelRenderer((Model)this);
/* 269 */     this.leftTalon5.setRotationPoint(0.75F, 10.5F, -7.0F);
/* 270 */     this.leftLeg3.addChild(this.leftTalon5);
/* 271 */     setRotationAngle(this.leftTalon5, 0.0437F, -0.1309F, 0.0F);
/*     */ 
/*     */     
/* 274 */     this.rightTalon3_r1 = new ModelRenderer((Model)this);
/* 275 */     this.rightTalon3_r1.setRotationPoint(-0.0719F, -6.4278F, 7.1532F);
/* 276 */     this.leftTalon5.addChild(this.rightTalon3_r1);
/* 277 */     setRotationAngle(this.rightTalon3_r1, 0.2595F, 0.1338F, -0.0339F);
/* 278 */     this.rightTalon3_r1.setTextureOffset(57, 47).addBox(-0.3781F, -0.7566F, -1.9665F, 1.0F, 1.0F, 2.0F, 0.0F, true);
/*     */     
/* 280 */     this.rightTalon3_r2 = new ModelRenderer((Model)this);
/* 281 */     this.rightTalon3_r2.setRotationPoint(-1.4172F, -5.5126F, 5.2954F);
/* 282 */     this.leftTalon5.addChild(this.rightTalon3_r2);
/* 283 */     setRotationAngle(this.rightTalon3_r2, 0.2205F, 0.276F, 0.0041F);
/* 284 */     this.rightTalon3_r2.setTextureOffset(57, 47).addBox(-0.5F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, true);
/*     */     
/* 286 */     this.rightTalon2_r1 = new ModelRenderer((Model)this);
/* 287 */     this.rightTalon2_r1.setRotationPoint(0.9606F, -5.5496F, 4.8723F);
/* 288 */     this.leftTalon5.addChild(this.rightTalon2_r1);
/* 289 */     setRotationAngle(this.rightTalon2_r1, 0.2595F, 0.0029F, -0.0339F);
/* 290 */     this.rightTalon2_r1.setTextureOffset(57, 47).addBox(-0.5143F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, true);
/*     */     
/* 292 */     this.leftTalon1b = new ModelRenderer((Model)this);
/* 293 */     this.leftTalon1b.setRotationPoint(0.0F, 0.0F, -1.5F);
/* 294 */     this.leftTalon5.addChild(this.leftTalon1b);
/* 295 */     setRotationAngle(this.leftTalon1b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 298 */     this.leftTalon1b_r = new ModelRenderer((Model)this);
/* 299 */     this.leftTalon1b_r.setRotationPoint(-2.75F, 0.75F, 1.25F);
/* 300 */     this.leftTalon1b.addChild(this.leftTalon1b_r);
/* 301 */     setRotationAngle(this.leftTalon1b_r, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 304 */     this.rightTalon3b_r3_r1 = new ModelRenderer((Model)this);
/* 305 */     this.rightTalon3b_r3_r1.setRotationPoint(2.7601F, -4.8487F, 4.5135F);
/* 306 */     this.leftTalon1b_r.addChild(this.rightTalon3b_r3_r1);
/* 307 */     setRotationAngle(this.rightTalon3b_r3_r1, 0.565F, 0.1338F, -0.0339F);
/* 308 */     this.rightTalon3b_r3_r1.setTextureOffset(57, 52).addBox(-0.7801F, -1.7606F, 0.4487F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 310 */     this.rightTalon3b_r3_r2 = new ModelRenderer((Model)this);
/* 311 */     this.rightTalon3b_r3_r2.setRotationPoint(1.3328F, -6.3163F, 5.5793F);
/* 312 */     this.leftTalon1b_r.addChild(this.rightTalon3b_r3_r2);
/* 313 */     setRotationAngle(this.rightTalon3b_r3_r2, 0.526F, 0.276F, 0.0041F);
/* 314 */     this.rightTalon3b_r3_r2.setTextureOffset(57, 52).addBox(-0.5F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 316 */     this.rightTalon2b_r2_r1 = new ModelRenderer((Model)this);
/* 317 */     this.rightTalon2b_r2_r1.setRotationPoint(3.7106F, -6.3534F, 5.1562F);
/* 318 */     this.leftTalon1b_r.addChild(this.rightTalon2b_r2_r1);
/* 319 */     setRotationAngle(this.rightTalon2b_r2_r1, 0.565F, 0.0029F, -0.0339F);
/* 320 */     this.rightTalon2b_r2_r1.setTextureOffset(57, 52).addBox(-0.5161F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 322 */     this.leftTalon1c = new ModelRenderer((Model)this);
/* 323 */     this.leftTalon1c.setRotationPoint(0.0F, 0.0F, -1.0F);
/* 324 */     this.leftTalon1b.addChild(this.leftTalon1c);
/* 325 */     setRotationAngle(this.leftTalon1c, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 328 */     this.leftTalon1c_r = new ModelRenderer((Model)this);
/* 329 */     this.leftTalon1c_r.setRotationPoint(-2.75F, 0.75F, 1.25F);
/* 330 */     this.leftTalon1c.addChild(this.leftTalon1c_r);
/* 331 */     setRotationAngle(this.leftTalon1c_r, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 334 */     this.rightTalon3c_r3_r1 = new ModelRenderer((Model)this);
/* 335 */     this.rightTalon3c_r3_r1.setRotationPoint(2.7601F, -4.8487F, 5.5135F);
/* 336 */     this.leftTalon1c_r.addChild(this.rightTalon3c_r3_r1);
/* 337 */     setRotationAngle(this.rightTalon3c_r3_r1, 1.0014F, 0.1309F, -0.0341F);
/* 338 */     this.rightTalon3c_r3_r1.setTextureOffset(57, 52).addBox(-0.8401F, -0.5099F, -0.9865F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 340 */     this.rightTalon3c_r3_r2 = new ModelRenderer((Model)this);
/* 341 */     this.rightTalon3c_r3_r2.setRotationPoint(1.3328F, -5.5723F, 7.0255F);
/* 342 */     this.leftTalon1c_r.addChild(this.rightTalon3c_r3_r2);
/* 343 */     setRotationAngle(this.rightTalon3c_r3_r2, 0.9589F, 0.2753F, -0.0206F);
/* 344 */     this.rightTalon3c_r3_r2.setTextureOffset(57, 52).addBox(-0.5F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 346 */     this.rightTalon2c_r2_r1 = new ModelRenderer((Model)this);
/* 347 */     this.rightTalon2c_r2_r1.setRotationPoint(3.7106F, -5.6461F, 6.6073F);
/* 348 */     this.leftTalon1c_r.addChild(this.rightTalon2c_r2_r1);
/* 349 */     setRotationAngle(this.rightTalon2c_r2_r1, 1.0014F, 0.0F, -0.0341F);
/* 350 */     this.rightTalon2c_r2_r1.setTextureOffset(57, 52).addBox(-0.4857F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 352 */     this.leftTalon6 = new ModelRenderer((Model)this);
/* 353 */     this.leftTalon6.setRotationPoint(0.0F, 10.5F, -7.0F);
/* 354 */     this.leftLeg3.addChild(this.leftTalon6);
/* 355 */     setRotationAngle(this.leftTalon6, 0.0437F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 358 */     this.leftTalonb2 = new ModelRenderer((Model)this);
/* 359 */     this.leftTalonb2.setRotationPoint(0.0F, 0.0F, -1.5F);
/* 360 */     this.leftTalon6.addChild(this.leftTalonb2);
/* 361 */     setRotationAngle(this.leftTalonb2, 0.1833F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 364 */     this.leftTalonb_r2 = new ModelRenderer((Model)this);
/* 365 */     this.leftTalonb_r2.setRotationPoint(-2.0F, 0.75F, 1.25F);
/* 366 */     this.leftTalonb2.addChild(this.leftTalonb_r2);
/* 367 */     setRotationAngle(this.leftTalonb_r2, -0.1745F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 370 */     this.leftTalonc2 = new ModelRenderer((Model)this);
/* 371 */     this.leftTalonc2.setRotationPoint(0.0F, -0.25F, -2.75F);
/* 372 */     this.leftTalonb2.addChild(this.leftTalonc2);
/* 373 */     setRotationAngle(this.leftTalonc2, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 376 */     this.leftTalonc_r2 = new ModelRenderer((Model)this);
/* 377 */     this.leftTalonc_r2.setRotationPoint(-2.0F, 0.8154F, 2.7486F);
/* 378 */     this.leftTalonc2.addChild(this.leftTalonc_r2);
/* 379 */     setRotationAngle(this.leftTalonc_r2, -0.1745F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 382 */     this.leftTalon7 = new ModelRenderer((Model)this);
/* 383 */     this.leftTalon7.setRotationPoint(-0.75F, 10.5F, -7.0F);
/* 384 */     this.leftLeg3.addChild(this.leftTalon7);
/* 385 */     setRotationAngle(this.leftTalon7, 0.0873F, 0.1309F, 0.0F);
/*     */ 
/*     */     
/* 388 */     this.leftTalon3_r = new ModelRenderer((Model)this);
/* 389 */     this.leftTalon3_r.setRotationPoint(-1.25F, 0.75F, -0.25F);
/* 390 */     this.leftTalon7.addChild(this.leftTalon3_r);
/* 391 */     setRotationAngle(this.leftTalon3_r, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 394 */     this.leftTalon3b = new ModelRenderer((Model)this);
/* 395 */     this.leftTalon3b.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 396 */     this.leftTalon7.addChild(this.leftTalon3b);
/* 397 */     setRotationAngle(this.leftTalon3b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 400 */     this.leftTalon3c = new ModelRenderer((Model)this);
/* 401 */     this.leftTalon3c.setRotationPoint(0.0F, 0.0F, -1.25F);
/* 402 */     this.leftTalon3b.addChild(this.leftTalon3c);
/* 403 */     setRotationAngle(this.leftTalon3c, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 406 */     this.leftTalon8 = new ModelRenderer((Model)this);
/* 407 */     this.leftTalon8.setRotationPoint(0.0F, 10.5F, -7.25F);
/* 408 */     this.leftLeg3.addChild(this.leftTalon8);
/* 409 */     setRotationAngle(this.leftTalon8, 0.0873F, -3.1416F, 0.0F);
/*     */ 
/*     */     
/* 412 */     this.rightTalon5_r1 = new ModelRenderer((Model)this);
/* 413 */     this.rightTalon5_r1.setRotationPoint(-1.0E-4F, -7.5837F, -7.363F);
/* 414 */     this.leftTalon8.addChild(this.rightTalon5_r1);
/* 415 */     setRotationAngle(this.rightTalon5_r1, 0.48F, 0.0F, 0.0F);
/* 416 */     this.rightTalon5_r1.setTextureOffset(57, 47).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, -0.1F, true);
/*     */     
/* 418 */     this.leftTalon4b = new ModelRenderer((Model)this);
/* 419 */     this.leftTalon4b.setRotationPoint(0.0F, 0.0F, -3.0F);
/* 420 */     this.leftTalon8.addChild(this.leftTalon4b);
/* 421 */     setRotationAngle(this.leftTalon4b, 0.1309F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 424 */     this.leftTalon4b_r = new ModelRenderer((Model)this);
/* 425 */     this.leftTalon4b_r.setRotationPoint(-2.0F, 0.9665F, 1.4763F);
/* 426 */     this.leftTalon4b.addChild(this.leftTalon4b_r);
/* 427 */     setRotationAngle(this.leftTalon4b_r, -0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 430 */     this.rightTalon4b_r2_r1 = new ModelRenderer((Model)this);
/* 431 */     this.rightTalon4b_r2_r1.setRotationPoint(1.9999F, -7.6703F, -7.0028F);
/* 432 */     this.leftTalon4b_r.addChild(this.rightTalon4b_r2_r1);
/* 433 */     setRotationAngle(this.rightTalon4b_r2_r1, 1.0908F, 0.0F, 0.0F);
/* 434 */     this.rightTalon4b_r2_r1.setTextureOffset(57, 52).addBox(-0.5F, -0.7456F, -1.6073F, 1.0F, 1.0F, 2.0F, -0.25F, true);
/*     */     
/* 436 */     this.rightLeg = new ModelRenderer((Model)this);
/* 437 */     this.rightLeg.setRotationPoint(-2.0F, 10.0F, 1.0F);
/* 438 */     setRotationAngle(this.rightLeg, 0.48F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 441 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 442 */     this.rightLeg2.setRotationPoint(0.0F, 4.75F, 0.0F);
/* 443 */     this.rightLeg.addChild(this.rightLeg2);
/* 444 */     this.rightLeg2.setTextureOffset(43, 46).addBox(-1.5F, -7.0F, 5.75F, 3.0F, 3.0F, 3.0F, -0.001F, false);
/*     */     
/* 446 */     this.rightLeg3 = new ModelRenderer((Model)this);
/* 447 */     this.rightLeg3.setRotationPoint(0.0F, -5.0F, 7.25F);
/* 448 */     this.rightLeg2.addChild(this.rightLeg3);
/* 449 */     setRotationAngle(this.rightLeg3, 0.5236F, 0.0F, 0.0F);
/* 450 */     this.rightLeg3.setTextureOffset(45, 54).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 452 */     this.rightTalon = new ModelRenderer((Model)this);
/* 453 */     this.rightTalon.setRotationPoint(-0.75F, 10.5F, -7.0F);
/* 454 */     this.rightLeg3.addChild(this.rightTalon);
/* 455 */     setRotationAngle(this.rightTalon, 0.0437F, 0.1309F, 0.0F);
/*     */ 
/*     */     
/* 458 */     this.rightTalon4_r1 = new ModelRenderer((Model)this);
/* 459 */     this.rightTalon4_r1.setRotationPoint(0.0719F, -6.4278F, 7.1532F);
/* 460 */     this.rightTalon.addChild(this.rightTalon4_r1);
/* 461 */     setRotationAngle(this.rightTalon4_r1, 0.2595F, -0.1338F, 0.0339F);
/* 462 */     this.rightTalon4_r1.setTextureOffset(57, 47).addBox(-0.6219F, -0.7566F, -1.9665F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 464 */     this.rightTalon4_r2 = new ModelRenderer((Model)this);
/* 465 */     this.rightTalon4_r2.setRotationPoint(1.4172F, -5.5126F, 5.2954F);
/* 466 */     this.rightTalon.addChild(this.rightTalon4_r2);
/* 467 */     setRotationAngle(this.rightTalon4_r2, 0.2205F, -0.276F, -0.0041F);
/* 468 */     this.rightTalon4_r2.setTextureOffset(57, 47).addBox(-0.5F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 470 */     this.rightTalon3_r3 = new ModelRenderer((Model)this);
/* 471 */     this.rightTalon3_r3.setRotationPoint(-0.9606F, -5.5496F, 4.8723F);
/* 472 */     this.rightTalon.addChild(this.rightTalon3_r3);
/* 473 */     setRotationAngle(this.rightTalon3_r3, 0.2595F, -0.0029F, 0.0339F);
/* 474 */     this.rightTalon3_r3.setTextureOffset(57, 47).addBox(-0.4857F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 476 */     this.rightTalon1b = new ModelRenderer((Model)this);
/* 477 */     this.rightTalon1b.setRotationPoint(0.0F, 0.0F, -1.5F);
/* 478 */     this.rightTalon.addChild(this.rightTalon1b);
/* 479 */     setRotationAngle(this.rightTalon1b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 482 */     this.rightTalon1b_r = new ModelRenderer((Model)this);
/* 483 */     this.rightTalon1b_r.setRotationPoint(2.75F, 0.75F, 1.25F);
/* 484 */     this.rightTalon1b.addChild(this.rightTalon1b_r);
/* 485 */     setRotationAngle(this.rightTalon1b_r, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 488 */     this.rightTalon4b_r4_r1 = new ModelRenderer((Model)this);
/* 489 */     this.rightTalon4b_r4_r1.setRotationPoint(-2.7601F, -4.8487F, 4.5135F);
/* 490 */     this.rightTalon1b_r.addChild(this.rightTalon4b_r4_r1);
/* 491 */     setRotationAngle(this.rightTalon4b_r4_r1, 0.565F, -0.1338F, 0.0339F);
/* 492 */     this.rightTalon4b_r4_r1.setTextureOffset(57, 52).addBox(-0.2199F, -1.7606F, 0.4487F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 494 */     this.rightTalon4b_r4_r2 = new ModelRenderer((Model)this);
/* 495 */     this.rightTalon4b_r4_r2.setRotationPoint(-1.3328F, -6.3163F, 5.5793F);
/* 496 */     this.rightTalon1b_r.addChild(this.rightTalon4b_r4_r2);
/* 497 */     setRotationAngle(this.rightTalon4b_r4_r2, 0.526F, -0.276F, -0.0041F);
/* 498 */     this.rightTalon4b_r4_r2.setTextureOffset(57, 52).addBox(-0.5F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 500 */     this.rightTalon3b_r3_r3 = new ModelRenderer((Model)this);
/* 501 */     this.rightTalon3b_r3_r3.setRotationPoint(-3.7106F, -6.3534F, 5.1562F);
/* 502 */     this.rightTalon1b_r.addChild(this.rightTalon3b_r3_r3);
/* 503 */     setRotationAngle(this.rightTalon3b_r3_r3, 0.565F, -0.0029F, 0.0339F);
/* 504 */     this.rightTalon3b_r3_r3.setTextureOffset(57, 52).addBox(-0.4839F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 506 */     this.rightTalon1c = new ModelRenderer((Model)this);
/* 507 */     this.rightTalon1c.setRotationPoint(0.0F, 0.0F, -1.0F);
/* 508 */     this.rightTalon1b.addChild(this.rightTalon1c);
/* 509 */     setRotationAngle(this.rightTalon1c, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 512 */     this.rightTalon1c_r = new ModelRenderer((Model)this);
/* 513 */     this.rightTalon1c_r.setRotationPoint(2.75F, 0.75F, 1.25F);
/* 514 */     this.rightTalon1c.addChild(this.rightTalon1c_r);
/* 515 */     setRotationAngle(this.rightTalon1c_r, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 518 */     this.rightTalon4c_r4_r1 = new ModelRenderer((Model)this);
/* 519 */     this.rightTalon4c_r4_r1.setRotationPoint(-2.7601F, -4.8487F, 5.5135F);
/* 520 */     this.rightTalon1c_r.addChild(this.rightTalon4c_r4_r1);
/* 521 */     setRotationAngle(this.rightTalon4c_r4_r1, 1.0014F, -0.1309F, 0.0341F);
/* 522 */     this.rightTalon4c_r4_r1.setTextureOffset(57, 52).addBox(-0.1599F, -0.5099F, -0.9865F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 524 */     this.rightTalon4c_r4_r2 = new ModelRenderer((Model)this);
/* 525 */     this.rightTalon4c_r4_r2.setRotationPoint(-1.3328F, -5.5723F, 7.0255F);
/* 526 */     this.rightTalon1c_r.addChild(this.rightTalon4c_r4_r2);
/* 527 */     setRotationAngle(this.rightTalon4c_r4_r2, 0.9589F, -0.2753F, 0.0206F);
/* 528 */     this.rightTalon4c_r4_r2.setTextureOffset(57, 52).addBox(-0.5F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 530 */     this.rightTalon3c_r3_r3 = new ModelRenderer((Model)this);
/* 531 */     this.rightTalon3c_r3_r3.setRotationPoint(-3.7106F, -5.6461F, 6.6073F);
/* 532 */     this.rightTalon1c_r.addChild(this.rightTalon3c_r3_r3);
/* 533 */     setRotationAngle(this.rightTalon3c_r3_r3, 1.0014F, 0.0F, 0.0341F);
/* 534 */     this.rightTalon3c_r3_r3.setTextureOffset(57, 52).addBox(-0.5143F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */     
/* 536 */     this.rightTalon3 = new ModelRenderer((Model)this);
/* 537 */     this.rightTalon3.setRotationPoint(0.0F, 10.5F, -7.0F);
/* 538 */     this.rightLeg3.addChild(this.rightTalon3);
/* 539 */     setRotationAngle(this.rightTalon3, 0.0437F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 542 */     this.rightTalonb3 = new ModelRenderer((Model)this);
/* 543 */     this.rightTalonb3.setRotationPoint(0.0F, 0.0F, -1.5F);
/* 544 */     this.rightTalon3.addChild(this.rightTalonb3);
/* 545 */     setRotationAngle(this.rightTalonb3, 0.1833F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 548 */     this.rightTalonb_r3 = new ModelRenderer((Model)this);
/* 549 */     this.rightTalonb_r3.setRotationPoint(2.0F, 0.75F, 1.25F);
/* 550 */     this.rightTalonb3.addChild(this.rightTalonb_r3);
/* 551 */     setRotationAngle(this.rightTalonb_r3, -0.1745F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 554 */     this.rightTalonc3 = new ModelRenderer((Model)this);
/* 555 */     this.rightTalonc3.setRotationPoint(0.0F, -0.25F, -2.75F);
/* 556 */     this.rightTalonb3.addChild(this.rightTalonc3);
/* 557 */     setRotationAngle(this.rightTalonc3, 0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 560 */     this.rightTalonc_r3 = new ModelRenderer((Model)this);
/* 561 */     this.rightTalonc_r3.setRotationPoint(2.0F, 0.8154F, 2.7486F);
/* 562 */     this.rightTalonc3.addChild(this.rightTalonc_r3);
/* 563 */     setRotationAngle(this.rightTalonc_r3, -0.1745F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 566 */     this.rightTalon4 = new ModelRenderer((Model)this);
/* 567 */     this.rightTalon4.setRotationPoint(0.75F, 10.5F, -7.0F);
/* 568 */     this.rightLeg3.addChild(this.rightTalon4);
/* 569 */     setRotationAngle(this.rightTalon4, 0.0873F, -0.1309F, 0.0F);
/*     */ 
/*     */     
/* 572 */     this.rightTalon3_r = new ModelRenderer((Model)this);
/* 573 */     this.rightTalon3_r.setRotationPoint(1.25F, 0.75F, -0.25F);
/* 574 */     this.rightTalon4.addChild(this.rightTalon3_r);
/* 575 */     setRotationAngle(this.rightTalon3_r, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 578 */     this.rightTalon3b = new ModelRenderer((Model)this);
/* 579 */     this.rightTalon3b.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 580 */     this.rightTalon4.addChild(this.rightTalon3b);
/* 581 */     setRotationAngle(this.rightTalon3b, -0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 584 */     this.rightTalon3c = new ModelRenderer((Model)this);
/* 585 */     this.rightTalon3c.setRotationPoint(0.0F, 0.0F, -1.25F);
/* 586 */     this.rightTalon3b.addChild(this.rightTalon3c);
/* 587 */     setRotationAngle(this.rightTalon3c, 0.0436F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 590 */     this.rightTalon9 = new ModelRenderer((Model)this);
/* 591 */     this.rightTalon9.setRotationPoint(0.0F, 10.5F, -7.25F);
/* 592 */     this.rightLeg3.addChild(this.rightTalon9);
/* 593 */     setRotationAngle(this.rightTalon9, 0.0873F, 3.1416F, 0.0F);
/*     */ 
/*     */     
/* 596 */     this.rightTalon6_r1 = new ModelRenderer((Model)this);
/* 597 */     this.rightTalon6_r1.setRotationPoint(1.0E-4F, -7.5837F, -7.363F);
/* 598 */     this.rightTalon9.addChild(this.rightTalon6_r1);
/* 599 */     setRotationAngle(this.rightTalon6_r1, 0.48F, 0.0F, 0.0F);
/* 600 */     this.rightTalon6_r1.setTextureOffset(57, 47).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);
/*     */     
/* 602 */     this.rightTalon4b = new ModelRenderer((Model)this);
/* 603 */     this.rightTalon4b.setRotationPoint(0.0F, 0.0F, -3.0F);
/* 604 */     this.rightTalon9.addChild(this.rightTalon4b);
/* 605 */     setRotationAngle(this.rightTalon4b, 0.1309F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 608 */     this.rightTalon4b_r = new ModelRenderer((Model)this);
/* 609 */     this.rightTalon4b_r.setRotationPoint(2.0F, 0.9665F, 1.4763F);
/* 610 */     this.rightTalon4b.addChild(this.rightTalon4b_r);
/* 611 */     setRotationAngle(this.rightTalon4b_r, -0.0873F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 614 */     this.rightTalon4b_r3_r1 = new ModelRenderer((Model)this);
/* 615 */     this.rightTalon4b_r3_r1.setRotationPoint(-1.9999F, -7.6703F, -7.0028F);
/* 616 */     this.rightTalon4b_r.addChild(this.rightTalon4b_r3_r1);
/* 617 */     setRotationAngle(this.rightTalon4b_r3_r1, 1.0908F, 0.0F, 0.0F);
/* 618 */     this.rightTalon4b_r3_r1.setTextureOffset(57, 52).addBox(-0.5F, -0.7456F, -1.6073F, 1.0F, 1.0F, 2.0F, -0.25F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 624 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 625 */     this.neck.render(matrixStack, buffer, packedLight, packedOverlay);
/* 626 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 627 */     this.rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
/* 628 */     this.leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
/* 629 */     this.tail1.render(matrixStack, buffer, packedLight, packedOverlay);
/* 630 */     this.tail2.render(matrixStack, buffer, packedLight, packedOverlay);
/* 631 */     this.tail3.render(matrixStack, buffer, packedLight, packedOverlay);
/* 632 */     this.tail4.render(matrixStack, buffer, packedLight, packedOverlay);
/* 633 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 634 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 640 */     if ((entity.getMotion()).y < -1.7D) {
/*     */       
/* 642 */       this.leftWing.rotateAngleX = (float)(this.leftWing.rotateAngleX + Math.toRadians(-90.0D));
/* 643 */       this.leftWing.rotateAngleY = (float)(this.leftWing.rotateAngleY + Math.toRadians(-85.0D));
/* 644 */       this.leftWing2.rotateAngleZ = (float)(this.leftWing2.rotateAngleZ + Math.toRadians(-5.0D));
/* 645 */       this.leftWing.rotateAngleY += MathHelper.cos(((LivingEntity)entity).ticksExisted * 0.9F) / 50.0F;
/* 646 */       this.leftWing.rotateAngleZ += MathHelper.cos(((LivingEntity)entity).ticksExisted * 2.9F) / 50.0F;
/*     */       
/* 648 */       this.rightWing.rotateAngleX = (float)(this.rightWing.rotateAngleX + Math.toRadians(-90.0D));
/* 649 */       this.rightWing.rotateAngleY = (float)(this.rightWing.rotateAngleY + Math.toRadians(85.0D));
/* 650 */       this.rightWing2.rotateAngleZ = (float)(this.rightWing2.rotateAngleZ + Math.toRadians(-5.0D));
/* 651 */       this.rightWing.rotateAngleY += MathHelper.cos(((LivingEntity)entity).ticksExisted * 0.9F) / 50.0F;
/* 652 */       this.rightWing.rotateAngleZ += MathHelper.cos(((LivingEntity)entity).ticksExisted * 2.9F) / 50.0F;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 657 */       this.leftWing.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F) * 0.7F;
/* 658 */       this.rightWing.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.7F;
/* 659 */       this.leftWing2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F) * 0.4F;
/* 660 */       this.rightWing2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.4F;
/*     */     } 
/*     */     
/* 663 */     this.tail1.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.4F) * 0.3F;
/* 664 */     this.tail1.rotateAngleY = (float)(this.tail1.rotateAngleY + Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 665 */     this.tail1b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F) * 0.8F;
/* 666 */     this.tail1b.rotateAngleY = (float)(this.tail1b.rotateAngleY + Math.sin(ageInTicks * 0.02D) / 5.0D);
/*     */     
/* 668 */     this.tail2.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.1F;
/* 669 */     this.tail2.rotateAngleY = (float)(this.tail2.rotateAngleY + Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 670 */     this.tail2b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F + 3.1415927F) * 0.4F;
/* 671 */     this.tail2b.rotateAngleY = (float)(this.tail2b.rotateAngleY + Math.sin(ageInTicks * 0.02D) / 5.0D);
/*     */     
/* 673 */     this.tail3.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.3F + 3.1415927F) * 0.4F;
/* 674 */     this.tail3.rotateAngleY = (float)(this.tail3.rotateAngleY - Math.sin(ageInTicks * 0.04D) / 10.0D);
/* 675 */     this.tail3b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F + 3.1415927F) * 0.8F;
/* 676 */     this.tail3b.rotateAngleY = (float)(this.tail3b.rotateAngleY - Math.sin(ageInTicks * 0.02D) / 5.0D);
/*     */     
/* 678 */     this.tail4.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.4F) * 0.1F;
/* 679 */     this.tail4.rotateAngleY = (float)(this.tail4.rotateAngleY - Math.sin(ageInTicks * 0.04D) / 9.0D);
/* 680 */     this.tail4b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F) * 0.5F;
/* 681 */     this.tail4b.rotateAngleY = (float)(this.tail4b.rotateAngleY - Math.sin(ageInTicks * 0.02D) / 3.0D);
/*     */ 
/*     */     
/* 684 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 685 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 687 */       this.head.rotateAngleY += this.body.rotateAngleY;
/* 688 */       float f1 = 1.0F - this.swingProgress;
/* 689 */       f1 *= f1;
/* 690 */       f1 *= f1;
/* 691 */       f1 = 1.0F - f1;
/* 692 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 693 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
/* 694 */       this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 1.5D + f3);
/* 695 */       this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 713 */     this.head.translateRotate(matrixStack);
/* 714 */     matrixStack.scale(0.5F, 0.5F, 0.5F);
/* 715 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 716 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
/* 717 */     matrixStack.translate(0.35D, -0.1D, -0.1D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 722 */     modelRenderer.rotateAngleX = x;
/* 723 */     modelRenderer.rotateAngleY = y;
/* 724 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\PhoenixFlyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */