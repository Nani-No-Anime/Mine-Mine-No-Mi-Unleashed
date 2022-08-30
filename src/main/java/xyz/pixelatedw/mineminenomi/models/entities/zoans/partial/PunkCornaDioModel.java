/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ 
/*     */ public class PunkCornaDioModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer rightHorn;
/*     */   private final ModelRenderer rightHorn2;
/*     */   private final ModelRenderer rightHorn3;
/*     */   private final ModelRenderer leftHorn;
/*     */   private final ModelRenderer leftHorn2;
/*     */   private final ModelRenderer leftHorn3;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer lowerBack;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail_r1;
/*     */   private final ModelRenderer collar;
/*     */   private final ModelRenderer collarMargin8;
/*     */   private final ModelRenderer collarMargin7;
/*     */   private final ModelRenderer collarMargin6;
/*     */   private final ModelRenderer collarMargin5;
/*     */   private final ModelRenderer collarMargin4;
/*     */   private final ModelRenderer collarMargin3;
/*     */   private final ModelRenderer collarMargin2;
/*     */   private final ModelRenderer collarMargin1;
/*     */   private final ModelRenderer collarFill2;
/*     */   private final ModelRenderer collarFill;
/*     */   private final ModelRenderer collarSpike12;
/*     */   private final ModelRenderer collarSpike11;
/*     */   private final ModelRenderer collarSpike10;
/*     */   private final ModelRenderer collarSpike9;
/*     */   private final ModelRenderer collarSpike8;
/*     */   private final ModelRenderer collarSpike7;
/*     */   private final ModelRenderer collarSpike6;
/*     */   private final ModelRenderer collarSpike5;
/*     */   private final ModelRenderer collarSpike4;
/*     */   private final ModelRenderer collarSpike3;
/*     */   private final ModelRenderer collarSpike2;
/*     */   private final ModelRenderer collarSpike1;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   private final ModelRenderer leftFrontLegJoint;
/*     */   private final ModelRenderer leftFrontLeg2;
/*     */   private final ModelRenderer leftFrontNail1;
/*     */   private final ModelRenderer leftFrontNail2;
/*     */   private final ModelRenderer leftLegCollar;
/*     */   private final ModelRenderer leftLegCollarMargin1;
/*     */   private final ModelRenderer leftLegCollarMargin2;
/*     */   private final ModelRenderer leftLegCollarMargin3;
/*     */   private final ModelRenderer leftLegCollarMargin4;
/*     */   private final ModelRenderer leftLegCollarMargin5;
/*     */   private final ModelRenderer leftLegCollarMargin6;
/*     */   private final ModelRenderer leftLegCollarMargin7;
/*     */   private final ModelRenderer leftLegCollarMargin8;
/*     */   private final ModelRenderer leftLegCollarFill1;
/*     */   private final ModelRenderer leftLegCollarFill2;
/*     */   private final ModelRenderer leftLegCollarSpike1;
/*     */   private final ModelRenderer leftLegCollarSpike2;
/*     */   private final ModelRenderer leftLegCollarSpike3;
/*     */   private final ModelRenderer leftLegCollarSpike4;
/*     */   private final ModelRenderer leftLegCollarSpike5;
/*     */   private final ModelRenderer leftLegCollarSpike6;
/*     */   private final ModelRenderer leftLegCollarSpike7;
/*     */   private final ModelRenderer leftLegCollarSpike8;
/*     */   private final ModelRenderer leftLegCollarSpike9;
/*     */   private final ModelRenderer leftLegCollarSpike10;
/*     */   private final ModelRenderer leftLegCollarSpike11;
/*     */   private final ModelRenderer leftLegCollarSpike12;
/*     */   private final ModelRenderer leftLegCollarConnection;
/*     */   private final ModelRenderer leftRearLeg;
/*     */   private final ModelRenderer leftRearNail1;
/*     */   private final ModelRenderer leftRearNail2;
/*     */   private final ModelRenderer leftRearLegCollar;
/*     */   private final ModelRenderer leftRearLegCollarMargin1;
/*     */   private final ModelRenderer leftRearLegCollarMargin2;
/*     */   private final ModelRenderer leftRearLegCollarMargin3;
/*     */   private final ModelRenderer leftRearLegCollarMargin4;
/*     */   private final ModelRenderer leftRearLegCollarMargin5;
/*     */   private final ModelRenderer leftRearLegCollarMargin6;
/*     */   private final ModelRenderer leftRearLegCollarMargin7;
/*     */   private final ModelRenderer leftRearLegCollarMargin8;
/*     */   private final ModelRenderer leftRearLegCollarSpike1;
/*     */   private final ModelRenderer leftRearLegCollarSpike2;
/*     */   private final ModelRenderer leftRearLegCollarSpike3;
/*     */   private final ModelRenderer leftRearLegCollarSpike4;
/*     */   private final ModelRenderer leftRearLegCollarSpike5;
/*     */   private final ModelRenderer leftRearLegCollarSpike6;
/*     */   private final ModelRenderer leftRearLegCollarSpike7;
/*     */   private final ModelRenderer leftRearLegCollarSpike8;
/*     */   private final ModelRenderer leftRearLegCollarSpikeConnection;
/*     */   private final ModelRenderer leftRearLegCollarFill3;
/*     */   private final ModelRenderer leftRearLegCollarFill2;
/*     */   private final ModelRenderer leftRearLegCollarFill1;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer rightFrontLegJoint;
/*     */   private final ModelRenderer rightFrontLeg2;
/*     */   private final ModelRenderer leftFrontNail3;
/*     */   private final ModelRenderer leftFrontNail4;
/*     */   private final ModelRenderer rightLegCollar;
/*     */   private final ModelRenderer rightLegCollarMargin1;
/*     */   private final ModelRenderer rightLegCollarMargin2;
/*     */   private final ModelRenderer rightLegCollarMargin3;
/*     */   private final ModelRenderer rightLegCollarMargin4;
/*     */   private final ModelRenderer rightLegCollarMargin5;
/*     */   private final ModelRenderer rightLegCollarMargin6;
/*     */   private final ModelRenderer rightLegCollarMargin7;
/*     */   private final ModelRenderer rightLegCollarMargin8;
/*     */   private final ModelRenderer rightLegCollarFill1;
/*     */   private final ModelRenderer rightLegCollarFill2;
/*     */   private final ModelRenderer rightLegCollarSpike1;
/*     */   private final ModelRenderer rightLegCollarSpike2;
/*     */   private final ModelRenderer rightLegCollarSpike3;
/*     */   private final ModelRenderer rightLegCollarSpike4;
/*     */   private final ModelRenderer rightLegCollarSpike5;
/*     */   private final ModelRenderer rightLegCollarSpike6;
/*     */   private final ModelRenderer rightLegCollarSpike7;
/*     */   private final ModelRenderer rightLegCollarSpike8;
/*     */   private final ModelRenderer rightLegCollarSpike9;
/*     */   private final ModelRenderer rightLegCollarSpike10;
/*     */   private final ModelRenderer rightLegCollarSpike11;
/*     */   private final ModelRenderer rightLegCollarSpike12;
/*     */   private final ModelRenderer rightLegCollarConnector;
/*     */   private final ModelRenderer rightRearLeg;
/*     */   private final ModelRenderer rightRearNail1;
/*     */   private final ModelRenderer rightRearNail2;
/*     */   private final ModelRenderer rightRearLegCollar;
/*     */   private final ModelRenderer rightRearLegCollarMargin1;
/*     */   private final ModelRenderer rightRearLegCollarMargin2;
/*     */   private final ModelRenderer rightRearLegCollarMargin3;
/*     */   private final ModelRenderer rightRearLegCollarMargin4;
/*     */   private final ModelRenderer rightRearLegCollarMargin5;
/*     */   private final ModelRenderer rightRearLegCollarMargin6;
/*     */   private final ModelRenderer rightRearLegCollarMargin7;
/*     */   private final ModelRenderer rightRearLegCollarMargin8;
/*     */   private final ModelRenderer rightRearLegCollarSpike1;
/*     */   private final ModelRenderer rightRearLegCollarSpike2;
/*     */   private final ModelRenderer rightRearLegCollarSpike3;
/*     */   private final ModelRenderer rightRearLegCollarSpike4;
/*     */   private final ModelRenderer rightRearLegCollarSpike5;
/*     */   private final ModelRenderer rightRearLegCollarSpike6;
/*     */   private final ModelRenderer rightRearLegCollarSpike7;
/*     */   private final ModelRenderer rightRearLegCollarSpike8;
/*     */   private final ModelRenderer rightRearLegCollarConnection;
/*     */   private final ModelRenderer rightRearLegCollarFill1;
/*     */   private final ModelRenderer rightRearLegCollarFill2;
/*     */   private final ModelRenderer rightRearLegCollarFill3;
/*     */   
/*     */   public PunkCornaDioModel() {
/* 158 */     super(1.0F);
/* 159 */     this.textureWidth = 128;
/* 160 */     this.textureHeight = 128;
/*     */     
/* 162 */     this.head = new ModelRenderer((Model)this);
/* 163 */     this.head.setRotationPoint(-2.5F, 8.0F, -9.5F);
/* 164 */     setRotationAngle(this.head, 0.0873F, 0.0F, 0.0F);
/* 165 */     this.head.setTextureOffset(0, 38).addBox(0.0F, 0.3861F, -7.0606F, 5.0F, 5.0F, 5.0F, 0.0F, false);
/*     */     
/* 167 */     this.snout = new ModelRenderer((Model)this);
/* 168 */     this.snout.setRotationPoint(2.5F, 5.4999F, -6.6855F);
/* 169 */     this.head.addChild(this.snout);
/* 170 */     setRotationAngle(this.snout, -0.6109F, 0.0F, 0.0F);
/* 171 */     this.snout.setTextureOffset(0, 48).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 3.0F, 3.0F, 0.0F, false);
/*     */     
/* 173 */     this.rightHorn = new ModelRenderer((Model)this);
/* 174 */     this.rightHorn.setRotationPoint(-0.5F, 1.0F, -3.5F);
/* 175 */     this.head.addChild(this.rightHorn);
/* 176 */     setRotationAngle(this.rightHorn, 1.4835F, -0.5236F, 0.7854F);
/* 177 */     this.rightHorn.setTextureOffset(0, 60).addBox(-2.25F, -1.5732F, -0.5F, 4.0F, 2.0F, 2.0F, 0.0F, false);
/*     */     
/* 179 */     this.rightHorn2 = new ModelRenderer((Model)this);
/* 180 */     this.rightHorn2.setRotationPoint(-2.1744F, -0.8963F, 0.5F);
/* 181 */     this.rightHorn.addChild(this.rightHorn2);
/* 182 */     setRotationAngle(this.rightHorn2, 0.0F, 0.0F, 0.8116F);
/* 183 */     this.rightHorn2.setTextureOffset(2, 55).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.01F, false);
/*     */     
/* 185 */     this.rightHorn3 = new ModelRenderer((Model)this);
/* 186 */     this.rightHorn3.setRotationPoint(-0.75F, 0.0F, 0.0F);
/* 187 */     this.rightHorn2.addChild(this.rightHorn3);
/* 188 */     setRotationAngle(this.rightHorn3, -0.0385F, 0.2148F, -0.0914F);
/* 189 */     this.rightHorn3.setTextureOffset(0, 66).addBox(-3.0282F, -0.9763F, -1.0F, 3.0F, 2.0F, 2.0F, 0.01F, false);
/*     */     
/* 191 */     this.leftHorn = new ModelRenderer((Model)this);
/* 192 */     this.leftHorn.setRotationPoint(4.0F, 1.75F, -3.25F);
/* 193 */     this.head.addChild(this.leftHorn);
/* 194 */     setRotationAngle(this.leftHorn, 1.4835F, 0.5236F, -0.7854F);
/* 195 */     this.leftHorn.setTextureOffset(0, 60).addBox(-0.2905F, -0.9022F, -1.0059F, 4.0F, 2.0F, 2.0F, 0.0F, true);
/*     */     
/* 197 */     this.leftHorn2 = new ModelRenderer((Model)this);
/* 198 */     this.leftHorn2.setRotationPoint(4.0008F, -0.0562F, -0.8383F);
/* 199 */     this.leftHorn.addChild(this.leftHorn2);
/* 200 */     setRotationAngle(this.leftHorn2, 0.0F, 0.0F, -0.8029F);
/* 201 */     this.leftHorn2.setTextureOffset(2, 55).addBox(-1.0913F, -1.3938F, -0.1676F, 2.0F, 2.0F, 2.0F, 0.01F, true);
/*     */     
/* 203 */     this.leftHorn3 = new ModelRenderer((Model)this);
/* 204 */     this.leftHorn3.setRotationPoint(0.5F, -0.25F, 0.75F);
/* 205 */     this.leftHorn2.addChild(this.leftHorn3);
/* 206 */     setRotationAngle(this.leftHorn3, -0.0349F, -0.2094F, 0.0873F);
/* 207 */     this.leftHorn3.setTextureOffset(0, 66).addBox(0.0918F, -1.1438F, -0.9617F, 3.0F, 2.0F, 2.0F, 0.01F, true);
/*     */     
/* 209 */     this.body = new ModelRenderer((Model)this);
/* 210 */     this.body.setRotationPoint(-4.5F, 7.0F, -3.5F);
/* 211 */     this.body.setTextureOffset(0, 0).addBox(0.0F, 1.0F, -7.0F, 9.0F, 9.0F, 11.0F, 0.0F, false);
/*     */     
/* 213 */     this.lowerBack = new ModelRenderer((Model)this);
/* 214 */     this.lowerBack.setRotationPoint(4.5F, 4.5F, 9.5F);
/* 215 */     this.body.addChild(this.lowerBack);
/* 216 */     this.lowerBack.setTextureOffset(0, 20).addBox(-4.0F, -3.5F, -6.0F, 8.0F, 8.0F, 10.0F, -0.01F, false);
/*     */     
/* 218 */     this.tail = new ModelRenderer((Model)this);
/* 219 */     this.tail.setRotationPoint(0.0F, -0.5F, 3.5F);
/* 220 */     this.lowerBack.addChild(this.tail);
/* 221 */     setRotationAngle(this.tail, 0.3491F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 224 */     this.tail_r1 = new ModelRenderer((Model)this);
/* 225 */     this.tail_r1.setRotationPoint(0.0F, 3.0E-4F, 0.5F);
/* 226 */     this.tail.addChild(this.tail_r1);
/* 227 */     setRotationAngle(this.tail_r1, 0.3054F, 0.0F, 0.0F);
/* 228 */     this.tail_r1.setTextureOffset(31, 3).addBox(-0.5F, -1.3933F, -0.8051F, 1.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/* 230 */     this.collar = new ModelRenderer((Model)this);
/* 231 */     this.collar.setRotationPoint(4.0F, 3.5F, -7.75F);
/* 232 */     this.body.addChild(this.collar);
/* 233 */     this.collar.setTextureOffset(38, 31).addBox(-3.5F, -3.51F, -0.5F, 8.0F, 8.0F, 2.0F, -0.02F, false);
/*     */     
/* 235 */     this.collarMargin8 = new ModelRenderer((Model)this);
/* 236 */     this.collarMargin8.setRotationPoint(-3.0F, 4.0F, 1.0F);
/* 237 */     this.collar.addChild(this.collarMargin8);
/* 238 */     setRotationAngle(this.collarMargin8, 0.0F, 0.0F, 0.7854F);
/* 239 */     this.collarMargin8.setTextureOffset(45, 0).addBox(-1.35F, -10.7F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 241 */     this.collarMargin7 = new ModelRenderer((Model)this);
/* 242 */     this.collarMargin7.setRotationPoint(5.25F, 0.5F, 0.5F);
/* 243 */     this.collar.addChild(this.collarMargin7);
/* 244 */     this.collarMargin7.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 246 */     this.collarMargin6 = new ModelRenderer((Model)this);
/* 247 */     this.collarMargin6.setRotationPoint(4.25F, 4.25F, 1.0F);
/* 248 */     this.collar.addChild(this.collarMargin6);
/* 249 */     setRotationAngle(this.collarMargin6, 0.0F, 0.0F, -0.7854F);
/* 250 */     this.collarMargin6.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 252 */     this.collarMargin5 = new ModelRenderer((Model)this);
/* 253 */     this.collarMargin5.setRotationPoint(-1.0F, 5.75F, 1.0F);
/* 254 */     this.collar.addChild(this.collarMargin5);
/* 255 */     this.collarMargin5.setTextureOffset(42, 5).addBox(-1.4F, -1.05F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 257 */     this.collarMargin4 = new ModelRenderer((Model)this);
/* 258 */     this.collarMargin4.setRotationPoint(-3.0F, 4.0F, 1.0F);
/* 259 */     this.collar.addChild(this.collarMargin4);
/* 260 */     setRotationAngle(this.collarMargin4, 0.0F, 0.0F, 0.7854F);
/* 261 */     this.collarMargin4.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 263 */     this.collarMargin3 = new ModelRenderer((Model)this);
/* 264 */     this.collarMargin3.setRotationPoint(-4.0F, 0.5F, 0.5F);
/* 265 */     this.collar.addChild(this.collarMargin3);
/* 266 */     this.collarMargin3.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 268 */     this.collarMargin2 = new ModelRenderer((Model)this);
/* 269 */     this.collarMargin2.setRotationPoint(-3.5F, -3.25F, 1.0F);
/* 270 */     this.collar.addChild(this.collarMargin2);
/* 271 */     setRotationAngle(this.collarMargin2, 0.0F, 0.0F, -0.7854F);
/* 272 */     this.collarMargin2.setTextureOffset(45, 0).addBox(-1.35F, -0.15F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 274 */     this.collarMargin1 = new ModelRenderer((Model)this);
/* 275 */     this.collarMargin1.setRotationPoint(-1.0F, -3.5F, 1.0F);
/* 276 */     this.collar.addChild(this.collarMargin1);
/* 277 */     this.collarMargin1.setTextureOffset(42, 5).addBox(-1.4F, -0.99F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 279 */     this.collarFill2 = new ModelRenderer((Model)this);
/* 280 */     this.collarFill2.setRotationPoint(0.0F, 5.0F, 0.5F);
/* 281 */     this.collar.addChild(this.collarFill2);
/* 282 */     this.collarFill2.setTextureOffset(40, 38).addBox(-3.0F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.016F, false);
/*     */     
/* 284 */     this.collarFill = new ModelRenderer((Model)this);
/* 285 */     this.collarFill.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 286 */     this.collar.addChild(this.collarFill);
/* 287 */     this.collarFill.setTextureOffset(38, 31).addBox(4.0F, -2.25F, -0.5F, 1.0F, 6.0F, 2.0F, -0.015F, false);
/*     */     
/* 289 */     this.collarSpike12 = new ModelRenderer((Model)this);
/* 290 */     this.collarSpike12.setRotationPoint(-3.8536F, -3.8536F, 0.5F);
/* 291 */     this.collar.addChild(this.collarSpike12);
/* 292 */     setRotationAngle(this.collarSpike12, 0.0F, 0.0F, 0.7854F);
/* 293 */     this.collarSpike12.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 295 */     this.collarSpike11 = new ModelRenderer((Model)this);
/* 296 */     this.collarSpike11.setRotationPoint(-1.0F, -5.0F, 0.5F);
/* 297 */     this.collar.addChild(this.collarSpike11);
/* 298 */     this.collarSpike11.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 300 */     this.collarSpike10 = new ModelRenderer((Model)this);
/* 301 */     this.collarSpike10.setRotationPoint(2.0F, -5.0F, 0.5F);
/* 302 */     this.collar.addChild(this.collarSpike10);
/* 303 */     this.collarSpike10.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 305 */     this.collarSpike9 = new ModelRenderer((Model)this);
/* 306 */     this.collarSpike9.setRotationPoint(4.8964F, -3.8964F, 0.5F);
/* 307 */     this.collar.addChild(this.collarSpike9);
/* 308 */     setRotationAngle(this.collarSpike9, 0.0F, 0.0F, -0.7854F);
/* 309 */     this.collarSpike9.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 311 */     this.collarSpike8 = new ModelRenderer((Model)this);
/* 312 */     this.collarSpike8.setRotationPoint(6.0F, -1.0F, 0.5F);
/* 313 */     this.collar.addChild(this.collarSpike8);
/* 314 */     this.collarSpike8.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 316 */     this.collarSpike7 = new ModelRenderer((Model)this);
/* 317 */     this.collarSpike7.setRotationPoint(6.0F, 2.0F, 0.5F);
/* 318 */     this.collar.addChild(this.collarSpike7);
/* 319 */     this.collarSpike7.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 321 */     this.collarSpike6 = new ModelRenderer((Model)this);
/* 322 */     this.collarSpike6.setRotationPoint(4.8964F, 4.8964F, 0.5F);
/* 323 */     this.collar.addChild(this.collarSpike6);
/* 324 */     setRotationAngle(this.collarSpike6, 0.0F, 0.0F, 0.7854F);
/* 325 */     this.collarSpike6.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 327 */     this.collarSpike5 = new ModelRenderer((Model)this);
/* 328 */     this.collarSpike5.setRotationPoint(2.0F, 6.25F, 0.5F);
/* 329 */     this.collar.addChild(this.collarSpike5);
/* 330 */     this.collarSpike5.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 332 */     this.collarSpike4 = new ModelRenderer((Model)this);
/* 333 */     this.collarSpike4.setRotationPoint(-1.0F, 6.25F, 0.5F);
/* 334 */     this.collar.addChild(this.collarSpike4);
/* 335 */     this.collarSpike4.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 337 */     this.collarSpike3 = new ModelRenderer((Model)this);
/* 338 */     this.collarSpike3.setRotationPoint(-3.5F, 4.5F, 0.5F);
/* 339 */     this.collar.addChild(this.collarSpike3);
/* 340 */     setRotationAngle(this.collarSpike3, 0.0F, 0.0F, -0.7854F);
/* 341 */     this.collarSpike3.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 343 */     this.collarSpike2 = new ModelRenderer((Model)this);
/* 344 */     this.collarSpike2.setRotationPoint(-4.5F, 2.0F, 0.5F);
/* 345 */     this.collar.addChild(this.collarSpike2);
/* 346 */     this.collarSpike2.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 348 */     this.collarSpike1 = new ModelRenderer((Model)this);
/* 349 */     this.collarSpike1.setRotationPoint(-4.5F, -1.0F, 0.5F);
/* 350 */     this.collar.addChild(this.collarSpike1);
/* 351 */     this.collarSpike1.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 353 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 354 */     this.leftFrontLeg.setRotationPoint(4.75F, 14.0F, -6.0F);
/* 355 */     this.leftFrontLeg.setTextureOffset(25, 49).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, true);
/*     */     
/* 357 */     this.leftFrontLegJoint = new ModelRenderer((Model)this);
/* 358 */     this.leftFrontLegJoint.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 359 */     this.leftFrontLeg.addChild(this.leftFrontLegJoint);
/* 360 */     this.leftFrontLegJoint.setTextureOffset(6, 6).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.3F, true);
/*     */     
/* 362 */     this.leftFrontLeg2 = new ModelRenderer((Model)this);
/* 363 */     this.leftFrontLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 364 */     this.leftFrontLeg.addChild(this.leftFrontLeg2);
/* 365 */     this.leftFrontLeg2.setTextureOffset(25, 58).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, true);
/*     */     
/* 367 */     this.leftFrontNail1 = new ModelRenderer((Model)this);
/* 368 */     this.leftFrontNail1.setRotationPoint(0.0F, 3.1F, 6.5F);
/* 369 */     this.leftFrontLeg2.addChild(this.leftFrontNail1);
/* 370 */     setRotationAngle(this.leftFrontNail1, -0.1211F, -0.4883F, -0.0394F);
/* 371 */     this.leftFrontNail1.setTextureOffset(6, 3).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, true);
/*     */     
/* 373 */     this.leftFrontNail2 = new ModelRenderer((Model)this);
/* 374 */     this.leftFrontNail2.setRotationPoint(0.0F, 3.1F, 6.5F);
/* 375 */     this.leftFrontLeg2.addChild(this.leftFrontNail2);
/* 376 */     setRotationAngle(this.leftFrontNail2, -0.1211F, 0.4883F, 0.0394F);
/* 377 */     this.leftFrontNail2.setTextureOffset(6, 3).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, true);
/*     */     
/* 379 */     this.leftLegCollar = new ModelRenderer((Model)this);
/* 380 */     this.leftLegCollar.setRotationPoint(2.25F, -1.51F, 0.25F);
/* 381 */     this.leftFrontLeg.addChild(this.leftLegCollar);
/* 382 */     setRotationAngle(this.leftLegCollar, 0.0F, -1.5708F, 0.0F);
/* 383 */     this.leftLegCollar.setTextureOffset(38, 31).addBox(-4.0F, -4.0F, -1.0F, 8.0F, 8.0F, 2.0F, -0.02F, false);
/*     */     
/* 385 */     this.leftLegCollarMargin1 = new ModelRenderer((Model)this);
/* 386 */     this.leftLegCollarMargin1.setRotationPoint(-3.5F, 3.51F, 0.5F);
/* 387 */     this.leftLegCollar.addChild(this.leftLegCollarMargin1);
/* 388 */     setRotationAngle(this.leftLegCollarMargin1, 0.0F, 0.0F, 0.7854F);
/* 389 */     this.leftLegCollarMargin1.setTextureOffset(45, 0).addBox(-1.35F, -10.7F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 391 */     this.leftLegCollarMargin2 = new ModelRenderer((Model)this);
/* 392 */     this.leftLegCollarMargin2.setRotationPoint(4.75F, 0.01F, 0.0F);
/* 393 */     this.leftLegCollar.addChild(this.leftLegCollarMargin2);
/* 394 */     this.leftLegCollarMargin2.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 396 */     this.leftLegCollarMargin3 = new ModelRenderer((Model)this);
/* 397 */     this.leftLegCollarMargin3.setRotationPoint(3.75F, 3.76F, 0.5F);
/* 398 */     this.leftLegCollar.addChild(this.leftLegCollarMargin3);
/* 399 */     setRotationAngle(this.leftLegCollarMargin3, 0.0F, 0.0F, -0.7854F);
/* 400 */     this.leftLegCollarMargin3.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 402 */     this.leftLegCollarMargin4 = new ModelRenderer((Model)this);
/* 403 */     this.leftLegCollarMargin4.setRotationPoint(-1.5F, 5.26F, 0.5F);
/* 404 */     this.leftLegCollar.addChild(this.leftLegCollarMargin4);
/* 405 */     this.leftLegCollarMargin4.setTextureOffset(42, 5).addBox(-1.4F, -1.05F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 407 */     this.leftLegCollarMargin5 = new ModelRenderer((Model)this);
/* 408 */     this.leftLegCollarMargin5.setRotationPoint(-3.5F, 3.51F, 0.5F);
/* 409 */     this.leftLegCollar.addChild(this.leftLegCollarMargin5);
/* 410 */     setRotationAngle(this.leftLegCollarMargin5, 0.0F, 0.0F, 0.7854F);
/* 411 */     this.leftLegCollarMargin5.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 413 */     this.leftLegCollarMargin6 = new ModelRenderer((Model)this);
/* 414 */     this.leftLegCollarMargin6.setRotationPoint(-4.5F, 0.01F, 0.0F);
/* 415 */     this.leftLegCollar.addChild(this.leftLegCollarMargin6);
/* 416 */     this.leftLegCollarMargin6.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 418 */     this.leftLegCollarMargin7 = new ModelRenderer((Model)this);
/* 419 */     this.leftLegCollarMargin7.setRotationPoint(-4.0F, -3.74F, 0.5F);
/* 420 */     this.leftLegCollar.addChild(this.leftLegCollarMargin7);
/* 421 */     setRotationAngle(this.leftLegCollarMargin7, 0.0F, 0.0F, -0.7854F);
/* 422 */     this.leftLegCollarMargin7.setTextureOffset(45, 0).addBox(-1.35F, -0.15F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 424 */     this.leftLegCollarMargin8 = new ModelRenderer((Model)this);
/* 425 */     this.leftLegCollarMargin8.setRotationPoint(-1.5F, -3.99F, 0.5F);
/* 426 */     this.leftLegCollar.addChild(this.leftLegCollarMargin8);
/* 427 */     this.leftLegCollarMargin8.setTextureOffset(42, 5).addBox(-1.4F, -0.99F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 429 */     this.leftLegCollarFill1 = new ModelRenderer((Model)this);
/* 430 */     this.leftLegCollarFill1.setRotationPoint(-0.5F, 4.51F, 0.0F);
/* 431 */     this.leftLegCollar.addChild(this.leftLegCollarFill1);
/* 432 */     this.leftLegCollarFill1.setTextureOffset(40, 38).addBox(-3.0F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.016F, false);
/*     */     
/* 434 */     this.leftLegCollarFill2 = new ModelRenderer((Model)this);
/* 435 */     this.leftLegCollarFill2.setRotationPoint(-0.5F, -0.49F, -0.5F);
/* 436 */     this.leftLegCollar.addChild(this.leftLegCollarFill2);
/* 437 */     this.leftLegCollarFill2.setTextureOffset(38, 31).addBox(4.0F, -2.25F, -0.5F, 1.0F, 6.0F, 2.0F, -0.015F, false);
/*     */     
/* 439 */     this.leftLegCollarSpike1 = new ModelRenderer((Model)this);
/* 440 */     this.leftLegCollarSpike1.setRotationPoint(-4.3536F, -4.3436F, 0.0F);
/* 441 */     this.leftLegCollar.addChild(this.leftLegCollarSpike1);
/* 442 */     setRotationAngle(this.leftLegCollarSpike1, 0.0F, 0.0F, 0.7854F);
/* 443 */     this.leftLegCollarSpike1.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 445 */     this.leftLegCollarSpike2 = new ModelRenderer((Model)this);
/* 446 */     this.leftLegCollarSpike2.setRotationPoint(-1.5F, -5.49F, 0.0F);
/* 447 */     this.leftLegCollar.addChild(this.leftLegCollarSpike2);
/* 448 */     this.leftLegCollarSpike2.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 450 */     this.leftLegCollarSpike3 = new ModelRenderer((Model)this);
/* 451 */     this.leftLegCollarSpike3.setRotationPoint(1.5F, -5.49F, 0.0F);
/* 452 */     this.leftLegCollar.addChild(this.leftLegCollarSpike3);
/* 453 */     this.leftLegCollarSpike3.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 455 */     this.leftLegCollarSpike4 = new ModelRenderer((Model)this);
/* 456 */     this.leftLegCollarSpike4.setRotationPoint(4.3964F, -4.3864F, 0.0F);
/* 457 */     this.leftLegCollar.addChild(this.leftLegCollarSpike4);
/* 458 */     setRotationAngle(this.leftLegCollarSpike4, 0.0F, 0.0F, -0.7854F);
/* 459 */     this.leftLegCollarSpike4.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 461 */     this.leftLegCollarSpike5 = new ModelRenderer((Model)this);
/* 462 */     this.leftLegCollarSpike5.setRotationPoint(5.5F, -1.49F, 0.0F);
/* 463 */     this.leftLegCollar.addChild(this.leftLegCollarSpike5);
/* 464 */     this.leftLegCollarSpike5.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 466 */     this.leftLegCollarSpike6 = new ModelRenderer((Model)this);
/* 467 */     this.leftLegCollarSpike6.setRotationPoint(5.5F, 1.51F, 0.0F);
/* 468 */     this.leftLegCollar.addChild(this.leftLegCollarSpike6);
/* 469 */     this.leftLegCollarSpike6.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 471 */     this.leftLegCollarSpike7 = new ModelRenderer((Model)this);
/* 472 */     this.leftLegCollarSpike7.setRotationPoint(4.3964F, 4.4064F, 0.0F);
/* 473 */     this.leftLegCollar.addChild(this.leftLegCollarSpike7);
/* 474 */     setRotationAngle(this.leftLegCollarSpike7, 0.0F, 0.0F, 0.7854F);
/* 475 */     this.leftLegCollarSpike7.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 477 */     this.leftLegCollarSpike8 = new ModelRenderer((Model)this);
/* 478 */     this.leftLegCollarSpike8.setRotationPoint(1.5F, 5.76F, 0.0F);
/* 479 */     this.leftLegCollar.addChild(this.leftLegCollarSpike8);
/* 480 */     this.leftLegCollarSpike8.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 482 */     this.leftLegCollarSpike9 = new ModelRenderer((Model)this);
/* 483 */     this.leftLegCollarSpike9.setRotationPoint(-1.5F, 5.76F, 0.0F);
/* 484 */     this.leftLegCollar.addChild(this.leftLegCollarSpike9);
/* 485 */     this.leftLegCollarSpike9.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 487 */     this.leftLegCollarSpike10 = new ModelRenderer((Model)this);
/* 488 */     this.leftLegCollarSpike10.setRotationPoint(-4.0F, 4.01F, 0.0F);
/* 489 */     this.leftLegCollar.addChild(this.leftLegCollarSpike10);
/* 490 */     setRotationAngle(this.leftLegCollarSpike10, 0.0F, 0.0F, -0.7854F);
/* 491 */     this.leftLegCollarSpike10.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 493 */     this.leftLegCollarSpike11 = new ModelRenderer((Model)this);
/* 494 */     this.leftLegCollarSpike11.setRotationPoint(-5.0F, 1.51F, 0.0F);
/* 495 */     this.leftLegCollar.addChild(this.leftLegCollarSpike11);
/* 496 */     this.leftLegCollarSpike11.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 498 */     this.leftLegCollarSpike12 = new ModelRenderer((Model)this);
/* 499 */     this.leftLegCollarSpike12.setRotationPoint(-5.0F, -1.49F, 0.0F);
/* 500 */     this.leftLegCollar.addChild(this.leftLegCollarSpike12);
/* 501 */     this.leftLegCollarSpike12.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 503 */     this.leftLegCollarConnection = new ModelRenderer((Model)this);
/* 504 */     this.leftLegCollarConnection.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 505 */     this.leftLegCollar.addChild(this.leftLegCollarConnection);
/* 506 */     this.leftLegCollarConnection.setTextureOffset(40, 20).addBox(-3.5F, -3.5F, 0.5F, 7.0F, 7.0F, 2.0F, 0.0F, false);
/*     */     
/* 508 */     this.leftRearLeg = new ModelRenderer((Model)this);
/* 509 */     this.leftRearLeg.setRotationPoint(4.0F, 14.0F, 8.0F);
/* 510 */     this.leftRearLeg.setTextureOffset(17, 50).addBox(-0.75F, 0.0F, -1.25F, 1.0F, 10.0F, 2.0F, 0.0F, true);
/*     */     
/* 512 */     this.leftRearNail1 = new ModelRenderer((Model)this);
/* 513 */     this.leftRearNail1.setRotationPoint(0.0F, 9.1F, 6.5F);
/* 514 */     this.leftRearLeg.addChild(this.leftRearNail1);
/* 515 */     setRotationAngle(this.leftRearNail1, -0.1211F, 0.4883F, 0.0394F);
/* 516 */     this.leftRearNail1.setTextureOffset(6, 3).addBox(2.6808F, 0.7975F, -7.4708F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 518 */     this.leftRearNail2 = new ModelRenderer((Model)this);
/* 519 */     this.leftRearNail2.setRotationPoint(0.0F, 9.1F, 6.5F);
/* 520 */     this.leftRearLeg.addChild(this.leftRearNail2);
/* 521 */     setRotationAngle(this.leftRearNail2, -0.1211F, -0.4883F, -0.0394F);
/* 522 */     this.leftRearNail2.setTextureOffset(6, 3).addBox(-4.122F, 0.7496F, -7.2405F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 524 */     this.leftRearLegCollar = new ModelRenderer((Model)this);
/* 525 */     this.leftRearLegCollar.setRotationPoint(2.0F, -1.01F, -0.15F);
/* 526 */     this.leftRearLeg.addChild(this.leftRearLegCollar);
/* 527 */     setRotationAngle(this.leftRearLegCollar, 0.0F, -1.5708F, 0.0F);
/* 528 */     this.leftRearLegCollar.setTextureOffset(60, 34).addBox(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, -0.02F, false);
/*     */     
/* 530 */     this.leftRearLegCollarMargin1 = new ModelRenderer((Model)this);
/* 531 */     this.leftRearLegCollarMargin1.setRotationPoint(-2.1F, 2.01F, 0.5F);
/* 532 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin1);
/* 533 */     setRotationAngle(this.leftRearLegCollarMargin1, 0.0F, 0.0F, 0.7854F);
/* 534 */     this.leftRearLegCollarMargin1.setTextureOffset(45, 0).addBox(-1.35F, -6.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 536 */     this.leftRearLegCollarMargin2 = new ModelRenderer((Model)this);
/* 537 */     this.leftRearLegCollarMargin2.setRotationPoint(3.15F, -1.49F, 0.0F);
/* 538 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin2);
/* 539 */     this.leftRearLegCollarMargin2.setTextureOffset(42, 10).addBox(-0.55F, 0.1F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 541 */     this.leftRearLegCollarMargin3 = new ModelRenderer((Model)this);
/* 542 */     this.leftRearLegCollarMargin3.setRotationPoint(2.15F, 2.26F, 0.5F);
/* 543 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin3);
/* 544 */     setRotationAngle(this.leftRearLegCollarMargin3, 0.0F, 0.0F, -0.7854F);
/* 545 */     this.leftRearLegCollarMargin3.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 547 */     this.leftRearLegCollarMargin4 = new ModelRenderer((Model)this);
/* 548 */     this.leftRearLegCollarMargin4.setRotationPoint(-0.1F, 3.76F, 0.5F);
/* 549 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin4);
/* 550 */     this.leftRearLegCollarMargin4.setTextureOffset(45, 0).addBox(-1.4F, -1.05F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 552 */     this.leftRearLegCollarMargin5 = new ModelRenderer((Model)this);
/* 553 */     this.leftRearLegCollarMargin5.setRotationPoint(-2.1F, 2.01F, 0.5F);
/* 554 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin5);
/* 555 */     setRotationAngle(this.leftRearLegCollarMargin5, 0.0F, 0.0F, 0.7854F);
/* 556 */     this.leftRearLegCollarMargin5.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 558 */     this.leftRearLegCollarMargin6 = new ModelRenderer((Model)this);
/* 559 */     this.leftRearLegCollarMargin6.setRotationPoint(-3.1F, -1.49F, 0.0F);
/* 560 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin6);
/* 561 */     this.leftRearLegCollarMargin6.setTextureOffset(42, 10).addBox(-0.5F, 0.1F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 563 */     this.leftRearLegCollarMargin7 = new ModelRenderer((Model)this);
/* 564 */     this.leftRearLegCollarMargin7.setRotationPoint(-2.6F, -2.24F, 0.5F);
/* 565 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin7);
/* 566 */     setRotationAngle(this.leftRearLegCollarMargin7, 0.0F, 0.0F, -0.7854F);
/* 567 */     this.leftRearLegCollarMargin7.setTextureOffset(45, 0).addBox(-1.34F, -0.1F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 569 */     this.leftRearLegCollarMargin8 = new ModelRenderer((Model)this);
/* 570 */     this.leftRearLegCollarMargin8.setRotationPoint(-0.1F, -5.49F, 0.5F);
/* 571 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarMargin8);
/* 572 */     this.leftRearLegCollarMargin8.setTextureOffset(45, 0).addBox(-1.4F, 2.01F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 574 */     this.leftRearLegCollarSpike1 = new ModelRenderer((Model)this);
/* 575 */     this.leftRearLegCollarSpike1.setRotationPoint(-2.7768F, -2.8383F, 0.0F);
/* 576 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike1);
/* 577 */     setRotationAngle(this.leftRearLegCollarSpike1, 0.0F, 0.0F, 0.7854F);
/* 578 */     this.leftRearLegCollarSpike1.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 580 */     this.leftRearLegCollarSpike2 = new ModelRenderer((Model)this);
/* 581 */     this.leftRearLegCollarSpike2.setRotationPoint(-0.1F, -3.99F, 0.0F);
/* 582 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike2);
/* 583 */     this.leftRearLegCollarSpike2.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 585 */     this.leftRearLegCollarSpike3 = new ModelRenderer((Model)this);
/* 586 */     this.leftRearLegCollarSpike3.setRotationPoint(2.7912F, -2.8812F, 0.0F);
/* 587 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike3);
/* 588 */     setRotationAngle(this.leftRearLegCollarSpike3, 0.0F, 0.0F, -0.7854F);
/* 589 */     this.leftRearLegCollarSpike3.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 591 */     this.leftRearLegCollarSpike4 = new ModelRenderer((Model)this);
/* 592 */     this.leftRearLegCollarSpike4.setRotationPoint(3.9F, 0.01F, 0.0F);
/* 593 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike4);
/* 594 */     this.leftRearLegCollarSpike4.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 596 */     this.leftRearLegCollarSpike5 = new ModelRenderer((Model)this);
/* 597 */     this.leftRearLegCollarSpike5.setRotationPoint(3.0464F, 3.1564F, 0.0F);
/* 598 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike5);
/* 599 */     setRotationAngle(this.leftRearLegCollarSpike5, 0.0F, 0.0F, 0.7854F);
/* 600 */     this.leftRearLegCollarSpike5.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 602 */     this.leftRearLegCollarSpike6 = new ModelRenderer((Model)this);
/* 603 */     this.leftRearLegCollarSpike6.setRotationPoint(-0.1F, 4.26F, 0.0F);
/* 604 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike6);
/* 605 */     this.leftRearLegCollarSpike6.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 607 */     this.leftRearLegCollarSpike7 = new ModelRenderer((Model)this);
/* 608 */     this.leftRearLegCollarSpike7.setRotationPoint(-2.6F, 2.51F, 0.0F);
/* 609 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike7);
/* 610 */     setRotationAngle(this.leftRearLegCollarSpike7, 0.0F, 0.0F, -0.7854F);
/* 611 */     this.leftRearLegCollarSpike7.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 613 */     this.leftRearLegCollarSpike8 = new ModelRenderer((Model)this);
/* 614 */     this.leftRearLegCollarSpike8.setRotationPoint(-3.6F, 0.01F, 0.0F);
/* 615 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarSpike8);
/* 616 */     this.leftRearLegCollarSpike8.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 618 */     this.leftRearLegCollarSpikeConnection = new ModelRenderer((Model)this);
/* 619 */     this.leftRearLegCollarSpikeConnection.setRotationPoint(1.4F, -1.5F, 0.0F);
/* 620 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarSpikeConnection);
/* 621 */     this.leftRearLegCollarSpikeConnection.setTextureOffset(61, 23).addBox(-3.5F, -0.5F, 0.5F, 4.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 623 */     this.leftRearLegCollarFill3 = new ModelRenderer((Model)this);
/* 624 */     this.leftRearLegCollarFill3.setRotationPoint(-2.6F, 0.0F, 0.0F);
/* 625 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarFill3);
/* 626 */     this.leftRearLegCollarFill3.setTextureOffset(38, 34).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, -0.025F, false);
/*     */     
/* 628 */     this.leftRearLegCollarFill2 = new ModelRenderer((Model)this);
/* 629 */     this.leftRearLegCollarFill2.setRotationPoint(1.4F, -1.5F, 0.0F);
/* 630 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarFill2);
/* 631 */     this.leftRearLegCollarFill2.setTextureOffset(38, 34).addBox(0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, -0.025F, false);
/*     */     
/* 633 */     this.leftRearLegCollarFill1 = new ModelRenderer((Model)this);
/* 634 */     this.leftRearLegCollarFill1.setRotationPoint(1.4F, -1.5F, 0.0F);
/* 635 */     this.leftRearLegCollar.addChild(this.leftRearLegCollarFill1);
/* 636 */     this.leftRearLegCollarFill1.setTextureOffset(45, 0).addBox(-3.0F, 3.75F, -1.0F, 3.0F, 1.0F, 2.0F, -0.025F, false);
/*     */     
/* 638 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 639 */     this.rightFrontLeg.setRotationPoint(-4.5F, 14.0F, -6.0F);
/* 640 */     this.rightFrontLeg.setTextureOffset(25, 49).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 642 */     this.rightFrontLegJoint = new ModelRenderer((Model)this);
/* 643 */     this.rightFrontLegJoint.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 644 */     this.rightFrontLeg.addChild(this.rightFrontLegJoint);
/* 645 */     this.rightFrontLegJoint.setTextureOffset(6, 6).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.3F, false);
/*     */     
/* 647 */     this.rightFrontLeg2 = new ModelRenderer((Model)this);
/* 648 */     this.rightFrontLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 649 */     this.rightFrontLeg.addChild(this.rightFrontLeg2);
/* 650 */     this.rightFrontLeg2.setTextureOffset(25, 58).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 652 */     this.leftFrontNail3 = new ModelRenderer((Model)this);
/* 653 */     this.leftFrontNail3.setRotationPoint(0.0F, 3.1F, 6.5F);
/* 654 */     this.rightFrontLeg2.addChild(this.leftFrontNail3);
/* 655 */     setRotationAngle(this.leftFrontNail3, -0.1211F, -0.4883F, -0.0394F);
/* 656 */     this.leftFrontNail3.setTextureOffset(6, 3).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 658 */     this.leftFrontNail4 = new ModelRenderer((Model)this);
/* 659 */     this.leftFrontNail4.setRotationPoint(0.0F, 3.1F, 6.5F);
/* 660 */     this.rightFrontLeg2.addChild(this.leftFrontNail4);
/* 661 */     setRotationAngle(this.leftFrontNail4, -0.1211F, 0.4883F, 0.0394F);
/* 662 */     this.leftFrontNail4.setTextureOffset(6, 3).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 664 */     this.rightLegCollar = new ModelRenderer((Model)this);
/* 665 */     this.rightLegCollar.setRotationPoint(-2.25F, -1.51F, 0.25F);
/* 666 */     this.rightFrontLeg.addChild(this.rightLegCollar);
/* 667 */     setRotationAngle(this.rightLegCollar, 0.0F, -1.5708F, 0.0F);
/* 668 */     this.rightLegCollar.setTextureOffset(38, 31).addBox(-4.0F, -4.0F, -1.0F, 8.0F, 8.0F, 2.0F, -0.02F, false);
/*     */     
/* 670 */     this.rightLegCollarMargin1 = new ModelRenderer((Model)this);
/* 671 */     this.rightLegCollarMargin1.setRotationPoint(-3.5F, 3.51F, 0.5F);
/* 672 */     this.rightLegCollar.addChild(this.rightLegCollarMargin1);
/* 673 */     setRotationAngle(this.rightLegCollarMargin1, 0.0F, 0.0F, 0.7854F);
/* 674 */     this.rightLegCollarMargin1.setTextureOffset(45, 0).addBox(-1.35F, -10.7F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 676 */     this.rightLegCollarMargin2 = new ModelRenderer((Model)this);
/* 677 */     this.rightLegCollarMargin2.setRotationPoint(4.75F, 0.01F, 0.0F);
/* 678 */     this.rightLegCollar.addChild(this.rightLegCollarMargin2);
/* 679 */     this.rightLegCollarMargin2.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 681 */     this.rightLegCollarMargin3 = new ModelRenderer((Model)this);
/* 682 */     this.rightLegCollarMargin3.setRotationPoint(3.75F, 3.76F, 0.5F);
/* 683 */     this.rightLegCollar.addChild(this.rightLegCollarMargin3);
/* 684 */     setRotationAngle(this.rightLegCollarMargin3, 0.0F, 0.0F, -0.7854F);
/* 685 */     this.rightLegCollarMargin3.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 687 */     this.rightLegCollarMargin4 = new ModelRenderer((Model)this);
/* 688 */     this.rightLegCollarMargin4.setRotationPoint(-1.5F, 5.26F, 0.5F);
/* 689 */     this.rightLegCollar.addChild(this.rightLegCollarMargin4);
/* 690 */     this.rightLegCollarMargin4.setTextureOffset(42, 5).addBox(-1.4F, -1.05F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 692 */     this.rightLegCollarMargin5 = new ModelRenderer((Model)this);
/* 693 */     this.rightLegCollarMargin5.setRotationPoint(-3.5F, 3.51F, 0.5F);
/* 694 */     this.rightLegCollar.addChild(this.rightLegCollarMargin5);
/* 695 */     setRotationAngle(this.rightLegCollarMargin5, 0.0F, 0.0F, 0.7854F);
/* 696 */     this.rightLegCollarMargin5.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 698 */     this.rightLegCollarMargin6 = new ModelRenderer((Model)this);
/* 699 */     this.rightLegCollarMargin6.setRotationPoint(-4.5F, 0.01F, 0.0F);
/* 700 */     this.rightLegCollar.addChild(this.rightLegCollarMargin6);
/* 701 */     this.rightLegCollarMargin6.setTextureOffset(50, 10).addBox(-0.5F, -2.9F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
/*     */     
/* 703 */     this.rightLegCollarMargin7 = new ModelRenderer((Model)this);
/* 704 */     this.rightLegCollarMargin7.setRotationPoint(-4.0F, -3.74F, 0.5F);
/* 705 */     this.rightLegCollar.addChild(this.rightLegCollarMargin7);
/* 706 */     setRotationAngle(this.rightLegCollarMargin7, 0.0F, 0.0F, -0.7854F);
/* 707 */     this.rightLegCollarMargin7.setTextureOffset(45, 0).addBox(-1.35F, -0.15F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 709 */     this.rightLegCollarMargin8 = new ModelRenderer((Model)this);
/* 710 */     this.rightLegCollarMargin8.setRotationPoint(-1.5F, -3.99F, 0.5F);
/* 711 */     this.rightLegCollar.addChild(this.rightLegCollarMargin8);
/* 712 */     this.rightLegCollarMargin8.setTextureOffset(42, 5).addBox(-1.4F, -0.99F, -1.5F, 6.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 714 */     this.rightLegCollarFill1 = new ModelRenderer((Model)this);
/* 715 */     this.rightLegCollarFill1.setRotationPoint(-0.5F, 4.51F, 0.0F);
/* 716 */     this.rightLegCollar.addChild(this.rightLegCollarFill1);
/* 717 */     this.rightLegCollarFill1.setTextureOffset(40, 38).addBox(-3.0F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.016F, false);
/*     */     
/* 719 */     this.rightLegCollarFill2 = new ModelRenderer((Model)this);
/* 720 */     this.rightLegCollarFill2.setRotationPoint(-0.5F, -0.49F, -0.5F);
/* 721 */     this.rightLegCollar.addChild(this.rightLegCollarFill2);
/* 722 */     this.rightLegCollarFill2.setTextureOffset(38, 31).addBox(4.0F, -2.25F, -0.5F, 1.0F, 6.0F, 2.0F, -0.015F, false);
/*     */     
/* 724 */     this.rightLegCollarSpike1 = new ModelRenderer((Model)this);
/* 725 */     this.rightLegCollarSpike1.setRotationPoint(-4.3536F, -4.3436F, 0.0F);
/* 726 */     this.rightLegCollar.addChild(this.rightLegCollarSpike1);
/* 727 */     setRotationAngle(this.rightLegCollarSpike1, 0.0F, 0.0F, 0.7854F);
/* 728 */     this.rightLegCollarSpike1.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 730 */     this.rightLegCollarSpike2 = new ModelRenderer((Model)this);
/* 731 */     this.rightLegCollarSpike2.setRotationPoint(-1.5F, -5.49F, 0.0F);
/* 732 */     this.rightLegCollar.addChild(this.rightLegCollarSpike2);
/* 733 */     this.rightLegCollarSpike2.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 735 */     this.rightLegCollarSpike3 = new ModelRenderer((Model)this);
/* 736 */     this.rightLegCollarSpike3.setRotationPoint(1.5F, -5.49F, 0.0F);
/* 737 */     this.rightLegCollar.addChild(this.rightLegCollarSpike3);
/* 738 */     this.rightLegCollarSpike3.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 740 */     this.rightLegCollarSpike4 = new ModelRenderer((Model)this);
/* 741 */     this.rightLegCollarSpike4.setRotationPoint(4.3964F, -4.3864F, 0.0F);
/* 742 */     this.rightLegCollar.addChild(this.rightLegCollarSpike4);
/* 743 */     setRotationAngle(this.rightLegCollarSpike4, 0.0F, 0.0F, -0.7854F);
/* 744 */     this.rightLegCollarSpike4.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 746 */     this.rightLegCollarSpike5 = new ModelRenderer((Model)this);
/* 747 */     this.rightLegCollarSpike5.setRotationPoint(5.5F, -1.49F, 0.0F);
/* 748 */     this.rightLegCollar.addChild(this.rightLegCollarSpike5);
/* 749 */     this.rightLegCollarSpike5.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 751 */     this.rightLegCollarSpike6 = new ModelRenderer((Model)this);
/* 752 */     this.rightLegCollarSpike6.setRotationPoint(5.5F, 1.51F, 0.0F);
/* 753 */     this.rightLegCollar.addChild(this.rightLegCollarSpike6);
/* 754 */     this.rightLegCollarSpike6.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 756 */     this.rightLegCollarSpike7 = new ModelRenderer((Model)this);
/* 757 */     this.rightLegCollarSpike7.setRotationPoint(4.3964F, 4.4064F, 0.0F);
/* 758 */     this.rightLegCollar.addChild(this.rightLegCollarSpike7);
/* 759 */     setRotationAngle(this.rightLegCollarSpike7, 0.0F, 0.0F, 0.7854F);
/* 760 */     this.rightLegCollarSpike7.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 762 */     this.rightLegCollarSpike8 = new ModelRenderer((Model)this);
/* 763 */     this.rightLegCollarSpike8.setRotationPoint(1.5F, 5.76F, 0.0F);
/* 764 */     this.rightLegCollar.addChild(this.rightLegCollarSpike8);
/* 765 */     this.rightLegCollarSpike8.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 767 */     this.rightLegCollarSpike9 = new ModelRenderer((Model)this);
/* 768 */     this.rightLegCollarSpike9.setRotationPoint(-1.5F, 5.76F, 0.0F);
/* 769 */     this.rightLegCollar.addChild(this.rightLegCollarSpike9);
/* 770 */     this.rightLegCollarSpike9.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 772 */     this.rightLegCollarSpike10 = new ModelRenderer((Model)this);
/* 773 */     this.rightLegCollarSpike10.setRotationPoint(-4.0F, 4.01F, 0.0F);
/* 774 */     this.rightLegCollar.addChild(this.rightLegCollarSpike10);
/* 775 */     setRotationAngle(this.rightLegCollarSpike10, 0.0F, 0.0F, -0.7854F);
/* 776 */     this.rightLegCollarSpike10.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 778 */     this.rightLegCollarSpike11 = new ModelRenderer((Model)this);
/* 779 */     this.rightLegCollarSpike11.setRotationPoint(-5.0F, 1.51F, 0.0F);
/* 780 */     this.rightLegCollar.addChild(this.rightLegCollarSpike11);
/* 781 */     this.rightLegCollarSpike11.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 783 */     this.rightLegCollarSpike12 = new ModelRenderer((Model)this);
/* 784 */     this.rightLegCollarSpike12.setRotationPoint(-5.0F, -1.49F, 0.0F);
/* 785 */     this.rightLegCollar.addChild(this.rightLegCollarSpike12);
/* 786 */     this.rightLegCollarSpike12.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 788 */     this.rightLegCollarConnector = new ModelRenderer((Model)this);
/* 789 */     this.rightLegCollarConnector.setRotationPoint(0.0F, 0.0F, -3.0F);
/* 790 */     this.rightLegCollar.addChild(this.rightLegCollarConnector);
/* 791 */     this.rightLegCollarConnector.setTextureOffset(40, 20).addBox(-3.5F, -3.5F, 0.5F, 7.0F, 7.0F, 2.0F, 0.0F, false);
/*     */     
/* 793 */     this.rightRearLeg = new ModelRenderer((Model)this);
/* 794 */     this.rightRearLeg.setRotationPoint(-3.5F, 14.0F, 8.0F);
/* 795 */     this.rightRearLeg.setTextureOffset(17, 50).addBox(-0.75F, 0.0F, -1.25F, 1.0F, 10.0F, 2.0F, 0.0F, false);
/*     */     
/* 797 */     this.rightRearNail1 = new ModelRenderer((Model)this);
/* 798 */     this.rightRearNail1.setRotationPoint(0.0F, 9.1F, 6.5F);
/* 799 */     this.rightRearLeg.addChild(this.rightRearNail1);
/* 800 */     setRotationAngle(this.rightRearNail1, -0.1211F, 0.4883F, 0.0394F);
/* 801 */     this.rightRearNail1.setTextureOffset(6, 3).addBox(2.6808F, 0.7975F, -7.4708F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 803 */     this.rightRearNail2 = new ModelRenderer((Model)this);
/* 804 */     this.rightRearNail2.setRotationPoint(0.0F, 9.1F, 6.5F);
/* 805 */     this.rightRearLeg.addChild(this.rightRearNail2);
/* 806 */     setRotationAngle(this.rightRearNail2, -0.1211F, -0.4883F, -0.0394F);
/* 807 */     this.rightRearNail2.setTextureOffset(6, 3).addBox(-4.122F, 0.7496F, -7.2405F, 1.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 809 */     this.rightRearLegCollar = new ModelRenderer((Model)this);
/* 810 */     this.rightRearLegCollar.setRotationPoint(-2.5F, -1.01F, -0.15F);
/* 811 */     this.rightRearLeg.addChild(this.rightRearLegCollar);
/* 812 */     setRotationAngle(this.rightRearLegCollar, 0.0F, -1.5708F, 0.0F);
/* 813 */     this.rightRearLegCollar.setTextureOffset(60, 34).addBox(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, -0.02F, false);
/*     */     
/* 815 */     this.rightRearLegCollarMargin1 = new ModelRenderer((Model)this);
/* 816 */     this.rightRearLegCollarMargin1.setRotationPoint(-2.1F, 2.01F, 0.5F);
/* 817 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin1);
/* 818 */     setRotationAngle(this.rightRearLegCollarMargin1, 0.0F, 0.0F, 0.7854F);
/* 819 */     this.rightRearLegCollarMargin1.setTextureOffset(45, 0).addBox(-1.35F, -6.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 821 */     this.rightRearLegCollarMargin2 = new ModelRenderer((Model)this);
/* 822 */     this.rightRearLegCollarMargin2.setRotationPoint(3.15F, -1.49F, 0.0F);
/* 823 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin2);
/* 824 */     this.rightRearLegCollarMargin2.setTextureOffset(42, 10).addBox(-0.55F, 0.1F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 826 */     this.rightRearLegCollarMargin3 = new ModelRenderer((Model)this);
/* 827 */     this.rightRearLegCollarMargin3.setRotationPoint(2.15F, 2.26F, 0.5F);
/* 828 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin3);
/* 829 */     setRotationAngle(this.rightRearLegCollarMargin3, 0.0F, 0.0F, -0.7854F);
/* 830 */     this.rightRearLegCollarMargin3.setTextureOffset(45, 0).addBox(-1.5F, -0.45F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 832 */     this.rightRearLegCollarMargin4 = new ModelRenderer((Model)this);
/* 833 */     this.rightRearLegCollarMargin4.setRotationPoint(-0.1F, 3.76F, 0.5F);
/* 834 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin4);
/* 835 */     this.rightRearLegCollarMargin4.setTextureOffset(45, 0).addBox(-1.4F, -1.05F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 837 */     this.rightRearLegCollarMargin5 = new ModelRenderer((Model)this);
/* 838 */     this.rightRearLegCollarMargin5.setRotationPoint(-2.1F, 2.01F, 0.5F);
/* 839 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin5);
/* 840 */     setRotationAngle(this.rightRearLegCollarMargin5, 0.0F, 0.0F, 0.7854F);
/* 841 */     this.rightRearLegCollarMargin5.setTextureOffset(45, 0).addBox(-1.35F, -0.2F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 843 */     this.rightRearLegCollarMargin6 = new ModelRenderer((Model)this);
/* 844 */     this.rightRearLegCollarMargin6.setRotationPoint(-3.1F, -1.49F, 0.0F);
/* 845 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin6);
/* 846 */     this.rightRearLegCollarMargin6.setTextureOffset(42, 10).addBox(-0.5F, 0.1F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 848 */     this.rightRearLegCollarMargin7 = new ModelRenderer((Model)this);
/* 849 */     this.rightRearLegCollarMargin7.setRotationPoint(-2.6F, -2.24F, 0.5F);
/* 850 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin7);
/* 851 */     setRotationAngle(this.rightRearLegCollarMargin7, 0.0F, 0.0F, -0.7854F);
/* 852 */     this.rightRearLegCollarMargin7.setTextureOffset(45, 0).addBox(-1.34F, -0.1F, -1.5F, 3.0F, 1.0F, 2.0F, -0.01F, false);
/*     */     
/* 854 */     this.rightRearLegCollarMargin8 = new ModelRenderer((Model)this);
/* 855 */     this.rightRearLegCollarMargin8.setRotationPoint(-0.1F, -5.49F, 0.5F);
/* 856 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarMargin8);
/* 857 */     this.rightRearLegCollarMargin8.setTextureOffset(45, 0).addBox(-1.4F, 2.01F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
/*     */     
/* 859 */     this.rightRearLegCollarSpike1 = new ModelRenderer((Model)this);
/* 860 */     this.rightRearLegCollarSpike1.setRotationPoint(-2.7768F, -2.8383F, 0.0F);
/* 861 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike1);
/* 862 */     setRotationAngle(this.rightRearLegCollarSpike1, 0.0F, 0.0F, 0.7854F);
/* 863 */     this.rightRearLegCollarSpike1.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 865 */     this.rightRearLegCollarSpike2 = new ModelRenderer((Model)this);
/* 866 */     this.rightRearLegCollarSpike2.setRotationPoint(-0.1F, -3.99F, 0.0F);
/* 867 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike2);
/* 868 */     this.rightRearLegCollarSpike2.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 870 */     this.rightRearLegCollarSpike3 = new ModelRenderer((Model)this);
/* 871 */     this.rightRearLegCollarSpike3.setRotationPoint(2.7912F, -2.8812F, 0.0F);
/* 872 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike3);
/* 873 */     setRotationAngle(this.rightRearLegCollarSpike3, 0.0F, 0.0F, -0.7854F);
/* 874 */     this.rightRearLegCollarSpike3.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 876 */     this.rightRearLegCollarSpike4 = new ModelRenderer((Model)this);
/* 877 */     this.rightRearLegCollarSpike4.setRotationPoint(3.9F, 0.01F, 0.0F);
/* 878 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike4);
/* 879 */     this.rightRearLegCollarSpike4.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 881 */     this.rightRearLegCollarSpike5 = new ModelRenderer((Model)this);
/* 882 */     this.rightRearLegCollarSpike5.setRotationPoint(3.0464F, 3.1564F, 0.0F);
/* 883 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike5);
/* 884 */     setRotationAngle(this.rightRearLegCollarSpike5, 0.0F, 0.0F, 0.7854F);
/* 885 */     this.rightRearLegCollarSpike5.setTextureOffset(4, 0).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 887 */     this.rightRearLegCollarSpike6 = new ModelRenderer((Model)this);
/* 888 */     this.rightRearLegCollarSpike6.setRotationPoint(-0.1F, 4.26F, 0.0F);
/* 889 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike6);
/* 890 */     this.rightRearLegCollarSpike6.setTextureOffset(1, 1).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 892 */     this.rightRearLegCollarSpike7 = new ModelRenderer((Model)this);
/* 893 */     this.rightRearLegCollarSpike7.setRotationPoint(-2.6F, 2.51F, 0.0F);
/* 894 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike7);
/* 895 */     setRotationAngle(this.rightRearLegCollarSpike7, 0.0F, 0.0F, -0.7854F);
/* 896 */     this.rightRearLegCollarSpike7.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 898 */     this.rightRearLegCollarSpike8 = new ModelRenderer((Model)this);
/* 899 */     this.rightRearLegCollarSpike8.setRotationPoint(-3.6F, 0.01F, 0.0F);
/* 900 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarSpike8);
/* 901 */     this.rightRearLegCollarSpike8.setTextureOffset(4, 0).addBox(-1.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 903 */     this.rightRearLegCollarConnection = new ModelRenderer((Model)this);
/* 904 */     this.rightRearLegCollarConnection.setRotationPoint(-0.1F, 0.0F, -1.5F);
/* 905 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarConnection);
/* 906 */     this.rightRearLegCollarConnection.setTextureOffset(61, 23).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 908 */     this.rightRearLegCollarFill1 = new ModelRenderer((Model)this);
/* 909 */     this.rightRearLegCollarFill1.setRotationPoint(-2.6F, 0.0F, 0.0F);
/* 910 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarFill1);
/* 911 */     this.rightRearLegCollarFill1.setTextureOffset(42, 10).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, -0.025F, false);
/*     */     
/* 913 */     this.rightRearLegCollarFill2 = new ModelRenderer((Model)this);
/* 914 */     this.rightRearLegCollarFill2.setRotationPoint(1.4F, -1.5F, 0.0F);
/* 915 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarFill2);
/* 916 */     this.rightRearLegCollarFill2.setTextureOffset(38, 34).addBox(0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, -0.025F, false);
/*     */     
/* 918 */     this.rightRearLegCollarFill3 = new ModelRenderer((Model)this);
/* 919 */     this.rightRearLegCollarFill3.setRotationPoint(1.4F, -1.5F, 0.0F);
/* 920 */     this.rightRearLegCollar.addChild(this.rightRearLegCollarFill3);
/* 921 */     this.rightRearLegCollarFill3.setTextureOffset(45, 0).addBox(-3.0F, 3.75F, -1.0F, 3.0F, 1.0F, 2.0F, -0.025F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 928 */     this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
/* 929 */     this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
/* 930 */     this.rightRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
/* 931 */     this.leftRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
/* 932 */     if (entity.isSprinting()) {
/* 933 */       this.tail.rotateAngleX = 1.2F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 939 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 940 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 941 */     this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 942 */     this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 943 */     this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 944 */     this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 949 */     modelRenderer.rotateAngleX = x;
/* 950 */     modelRenderer.rotateAngleY = y;
/* 951 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\partial\PunkCornaDioModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */