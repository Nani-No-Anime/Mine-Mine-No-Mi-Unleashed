/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class YagaraBullModel<T extends YagaraBullEntity> extends BipedModel<T> {
/*     */   public ModelRenderer body1;
/*     */   public ModelRenderer body2;
/*     */   public ModelRenderer tail1;
/*     */   public ModelRenderer neck1;
/*     */   public ModelRenderer leftfin1;
/*     */   public ModelRenderer leftfin2;
/*     */   public ModelRenderer rightfin1;
/*     */   public ModelRenderer rightfin2;
/*     */   public ModelRenderer saddle;
/*     */   public ModelRenderer belt1;
/*     */   public ModelRenderer belt2;
/*     */   public ModelRenderer tail2;
/*     */   public ModelRenderer tail3;
/*     */   public ModelRenderer tail4;
/*     */   public ModelRenderer tail5;
/*     */   public ModelRenderer tail6;
/*     */   public ModelRenderer neck2;
/*     */   public ModelRenderer neck3;
/*     */   public ModelRenderer neck4;
/*     */   public ModelRenderer head1;
/*     */   public ModelRenderer mane;
/*     */   public ModelRenderer head2;
/*     */   public ModelRenderer head3;
/*     */   public ModelRenderer righteye;
/*     */   public ModelRenderer lefteye;
/*     */   public ModelRenderer leftgill;
/*     */   public ModelRenderer rightgill;
/*     */   public ModelRenderer mout;
/*     */   public ModelRenderer saddleside1;
/*     */   public ModelRenderer saddleside2;
/*     */   public ModelRenderer saddlefront;
/*     */   public ModelRenderer saddleside3;
/*     */   public ModelRenderer saddleside4;
/*     */   public ModelRenderer saddleback;
/*     */   public ModelRenderer saddlemiddle;
/*     */   
/*     */   public YagaraBullModel() {
/*  54 */     super(1.0F);
/*  55 */     this.textureWidth = 180;
/*  56 */     this.textureHeight = 90;
/*     */     
/*  58 */     this.bipedBody.showModel = false;
/*  59 */     this.bipedHead.showModel = false;
/*  60 */     this.bipedHeadwear.showModel = false;
/*  61 */     this.bipedLeftArm.showModel = false;
/*  62 */     this.bipedLeftLeg.showModel = false;
/*  63 */     this.bipedRightArm.showModel = false;
/*  64 */     this.bipedRightLeg.showModel = false;
/*     */     
/*  66 */     this.saddleside2 = new ModelRenderer((Model)this, 25, 64);
/*  67 */     this.saddleside2.setRotationPoint(-4.8F, -1.2F, 10.1F);
/*  68 */     this.saddleside2.addBox(0.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
/*  69 */     setRotateAngle(this.saddleside2, 0.017453292F, 0.10471976F, -0.13962634F);
/*  70 */     this.rightfin1 = new ModelRenderer((Model)this, 99, 41);
/*  71 */     this.rightfin1.setRotationPoint(-3.9F, 20.3F, -6.0F);
/*  72 */     this.rightfin1.addBox(-8.0F, -0.5F, -6.0F, 8.0F, 1.0F, 6.0F, 0.0F);
/*  73 */     setRotateAngle(this.rightfin1, -0.19221127F, 0.6485391F, -0.4435539F);
/*  74 */     this.tail2 = new ModelRenderer((Model)this, 47, 15);
/*  75 */     this.tail2.setRotationPoint(0.0F, 0.0F, 4.0F);
/*  76 */     this.tail2.addBox(-4.0F, -3.5F, 0.0F, 8.0F, 7.0F, 5.0F, 0.0F);
/*  77 */     setRotateAngle(this.tail2, -0.034906585F, -0.0F, 0.0F);
/*  78 */     this.saddleside3 = new ModelRenderer((Model)this, 50, 64);
/*  79 */     this.saddleside3.setRotationPoint(5.2F, -0.8F, -0.6F);
/*  80 */     this.saddleside3.addBox(-1.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
/*  81 */     setRotateAngle(this.saddleside3, 0.05235988F, 0.10471976F, 0.13962634F);
/*  82 */     this.saddle = new ModelRenderer((Model)this, 115, 0);
/*  83 */     this.saddle.setRotationPoint(0.0F, 12.7F, -5.0F);
/*  84 */     this.saddle.addBox(-5.5F, -1.0F, -5.5F, 11.0F, 1.0F, 21.0F, 0.0F);
/*  85 */     setRotateAngle(this.saddle, -0.05235988F, -0.0F, 0.0F);
/*  86 */     this.neck3 = new ModelRenderer((Model)this, 78, 30);
/*  87 */     this.neck3.setRotationPoint(0.0F, -1.6F, -3.0F);
/*  88 */     this.neck3.addBox(-3.5F, -2.5F, 0.0F, 7.0F, 5.0F, 5.0F, 0.0F);
/*  89 */     setRotateAngle(this.neck3, -0.4886922F, 0.0F, 0.0F);
/*  90 */     this.body2 = new ModelRenderer((Model)this, 0, 23);
/*  91 */     this.body2.setRotationPoint(0.0F, 18.4F, 5.3F);
/*  92 */     this.body2.addBox(-6.0F, -5.0F, -4.5F, 12.0F, 10.0F, 9.0F, 0.0F);
/*  93 */     setRotateAngle(this.body2, 0.034906585F, -0.0F, 0.0F);
/*  94 */     this.rightgill = new ModelRenderer((Model)this, 0, 61);
/*  95 */     this.rightgill.setRotationPoint(-2.2F, -1.0F, 0.0F);
/*  96 */     this.rightgill.addBox(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, 0.0F);
/*  97 */     setRotateAngle(this.rightgill, -0.17732546F, 0.3064798F, -0.93322754F);
/*  98 */     this.tail6 = new ModelRenderer((Model)this, 47, 42);
/*  99 */     this.tail6.setRotationPoint(0.0F, 0.0F, 2.5F);
/* 100 */     this.tail6.addBox(0.0F, -2.5F, -2.5F, 0.0F, 7.0F, 7.0F, 0.0F);
/* 101 */     setRotateAngle(this.tail6, 0.6981317F, -0.0F, 0.0F);
/* 102 */     this.body1 = new ModelRenderer((Model)this, 0, 0);
/* 103 */     this.body1.setRotationPoint(0.0F, 18.3F, -4.0F);
/* 104 */     this.body1.addBox(-6.0F, -5.5F, -5.5F, 12.0F, 11.0F, 11.0F, 0.0F);
/* 105 */     setRotateAngle(this.body1, -0.06981317F, -0.0F, 0.0F);
/* 106 */     this.tail4 = new ModelRenderer((Model)this, 47, 37);
/* 107 */     this.tail4.setRotationPoint(0.0F, 0.0F, 3.0F);
/* 108 */     this.tail4.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F);
/* 109 */     setRotateAngle(this.tail4, -0.034906585F, -0.0F, 0.0F);
/* 110 */     this.neck2 = new ModelRenderer((Model)this, 78, 0);
/* 111 */     this.neck2.setRotationPoint(0.0F, -3.0F, -3.5F);
/* 112 */     this.neck2.addBox(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 7.0F, 0.0F);
/* 113 */     setRotateAngle(this.neck2, -0.6981317F, -0.0F, 0.0F);
/* 114 */     this.head2 = new ModelRenderer((Model)this, 0, 52);
/* 115 */     this.head2.setRotationPoint(0.0F, 0.0F, -0.5F);
/* 116 */     this.head2.addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 3.0F, 0.0F);
/* 117 */     setRotateAngle(this.head2, 0.034906585F, -0.0F, 0.0F);
/* 118 */     this.rightfin2 = new ModelRenderer((Model)this, 99, 49);
/* 119 */     this.rightfin2.setRotationPoint(-4.4F, 21.3F, 8.0F);
/* 120 */     this.rightfin2.addBox(-6.0F, -0.5F, -5.0F, 6.0F, 1.0F, 5.0F, 0.0F);
/* 121 */     setRotateAngle(this.rightfin2, -0.24228175F, 0.72677505F, -0.47536334F);
/* 122 */     this.leftfin2 = new ModelRenderer((Model)this, 99, 49);
/* 123 */     this.leftfin2.setRotationPoint(4.4F, 21.3F, 8.0F);
/* 124 */     this.leftfin2.addBox(0.0F, -0.5F, -5.0F, 6.0F, 1.0F, 5.0F, 0.0F);
/* 125 */     setRotateAngle(this.leftfin2, -0.24228175F, -0.72677505F, 0.47536334F);
/* 126 */     this.mout = new ModelRenderer((Model)this, 19, 52);
/* 127 */     this.mout.setRotationPoint(0.0F, 0.0F, -4.0F);
/* 128 */     this.mout.addBox(-3.0F, -2.5F, -4.0F, 6.0F, 5.0F, 4.0F, 0.0F);
/* 129 */     this.saddlemiddle = new ModelRenderer((Model)this, 100, 78);
/* 130 */     this.saddlemiddle.setRotationPoint(-0.2F, -0.9F, 4.5F);
/* 131 */     this.saddlemiddle.addBox(-5.0F, -5.0F, -1.0F, 11.0F, 5.0F, 1.0F, 0.0F);
/* 132 */     setRotateAngle(this.saddlemiddle, -0.08726646F, -0.0F, 0.0F);
/* 133 */     this.saddleside4 = new ModelRenderer((Model)this, 75, 64);
/* 134 */     this.saddleside4.setRotationPoint(5.2F, -1.1F, 9.9F);
/* 135 */     this.saddleside4.addBox(-1.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
/* 136 */     setRotateAngle(this.saddleside4, 0.017453292F, -0.10367256F, 0.14032447F);
/* 137 */     this.head1 = new ModelRenderer((Model)this, 0, 43);
/* 138 */     this.head1.setRotationPoint(0.0F, -0.5F, 0.5F);
/* 139 */     this.head1.addBox(-3.0F, -5.0F, -1.0F, 6.0F, 5.0F, 3.0F, 0.0F);
/* 140 */     setRotateAngle(this.head1, 1.7976891F, -0.0F, 0.0F);
/* 141 */     this.lefteye = new ModelRenderer((Model)this, 40, 57);
/* 142 */     this.lefteye.setRotationPoint(2.5F, -4.5F, -1.5F);
/* 143 */     this.lefteye.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 144 */     setRotateAngle(this.lefteye, -0.08726646F, -0.0F, 0.0F);
/* 145 */     this.saddleback = new ModelRenderer((Model)this, 100, 71);
/* 146 */     this.saddleback.setRotationPoint(0.4F, -1.3F, 15.6F);
/* 147 */     this.saddleback.addBox(-4.5F, -5.0F, -1.0F, 9.0F, 5.0F, 1.0F, 0.0F);
/* 148 */     setRotateAngle(this.saddleback, 0.017453292F, -0.0F, 0.0F);
/* 149 */     this.righteye = new ModelRenderer((Model)this, 40, 57);
/* 150 */     this.righteye.setRotationPoint(-2.5F, -4.5F, -1.5F);
/* 151 */     this.righteye.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 152 */     setRotateAngle(this.righteye, -0.08726646F, -0.0F, 0.0F);
/* 153 */     this.neck1 = new ModelRenderer((Model)this, 78, 14);
/* 154 */     this.neck1.setRotationPoint(0.0F, 16.0F, -13.8F);
/* 155 */     this.neck1.addBox(-5.5F, -4.5F, 0.0F, 11.0F, 9.0F, 6.0F, 0.0F);
/* 156 */     setRotateAngle(this.neck1, -0.34906584F, -0.0F, 0.0F);
/* 157 */     this.saddleside1 = new ModelRenderer((Model)this, 0, 64);
/* 158 */     this.saddleside1.setRotationPoint(-5.0F, -0.9F, -0.7F);
/* 159 */     this.saddleside1.addBox(0.0F, -5.0F, -5.5F, 1.0F, 5.0F, 11.0F, 0.0F);
/* 160 */     setRotateAngle(this.saddleside1, 0.05235988F, -0.05235988F, -0.13962634F);
/* 161 */     this.tail3 = new ModelRenderer((Model)this, 47, 28);
/* 162 */     this.tail3.setRotationPoint(0.0F, 0.0F, 4.5F);
/* 163 */     this.tail3.addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 3.0F, 0.0F);
/* 164 */     setRotateAngle(this.tail3, -0.06981317F, -0.0F, 0.0F);
/* 165 */     this.belt1 = new ModelRenderer((Model)this, 152, 64);
/* 166 */     this.belt1.setRotationPoint(0.0F, 12.8F, 6.9F);
/* 167 */     this.belt1.addBox(-6.5F, 0.0F, -1.0F, 13.0F, 11.0F, 1.0F, 0.0F);
/* 168 */     setRotateAngle(this.belt1, -0.08726646F, -0.0F, 0.0F);
/* 169 */     this.head3 = new ModelRenderer((Model)this, 19, 43);
/* 170 */     this.head3.setRotationPoint(0.0F, -2.5F, -2.5F);
/* 171 */     this.head3.addBox(-2.5F, -2.0F, -4.0F, 5.0F, 4.0F, 4.0F, 0.0F);
/* 172 */     setRotateAngle(this.head3, 0.13962634F, -0.0F, 0.0F);
/* 173 */     this.tail5 = new ModelRenderer((Model)this, 47, 44);
/* 174 */     this.tail5.setRotationPoint(0.0F, 0.0F, 3.0F);
/* 175 */     this.tail5.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F);
/* 176 */     setRotateAngle(this.tail5, -0.08726646F, -0.0F, 0.0F);
/* 177 */     this.belt2 = new ModelRenderer((Model)this, 152, 77);
/* 178 */     this.belt2.setRotationPoint(0.0F, 12.3F, -4.1F);
/* 179 */     this.belt2.addBox(-6.5F, 0.0F, -1.0F, 13.0F, 12.0F, 1.0F, 0.0F);
/* 180 */     setRotateAngle(this.belt2, -0.08726646F, -0.0F, 0.0F);
/* 181 */     this.saddlefront = new ModelRenderer((Model)this, 100, 64);
/* 182 */     this.saddlefront.setRotationPoint(0.0F, -0.7F, -5.0F);
/* 183 */     this.saddlefront.addBox(-5.0F, -5.0F, -1.0F, 10.0F, 5.0F, 1.0F, 0.0F);
/* 184 */     setRotateAngle(this.saddlefront, 0.08726646F, -0.0F, 0.0F);
/* 185 */     this.leftfin1 = new ModelRenderer((Model)this, 99, 41);
/* 186 */     this.leftfin1.setRotationPoint(3.9F, 20.3F, -6.0F);
/* 187 */     this.leftfin1.addBox(0.0F, -0.5F, -6.0F, 8.0F, 1.0F, 6.0F, 0.0F);
/* 188 */     setRotateAngle(this.leftfin1, -0.19221127F, -0.6485391F, 0.4435539F);
/* 189 */     this.mane = new ModelRenderer((Model)this, 67, 29);
/* 190 */     this.mane.setRotationPoint(0.0F, -7.5F, 1.0F);
/* 191 */     this.mane.addBox(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 4.0F, 0.0F);
/* 192 */     setRotateAngle(this.mane, -0.17453292F, -0.0F, 0.0F);
/* 193 */     this.neck4 = new ModelRenderer((Model)this, 78, 41);
/* 194 */     this.neck4.setRotationPoint(0.0F, -0.8F, -3.0F);
/* 195 */     this.neck4.addBox(-3.0F, -2.5F, 0.0F, 6.0F, 5.0F, 4.0F, 0.0F);
/* 196 */     setRotateAngle(this.neck4, -0.20943952F, -0.0F, 0.0F);
/* 197 */     this.tail1 = new ModelRenderer((Model)this, 47, 0);
/* 198 */     this.tail1.setRotationPoint(0.0F, 18.0F, 9.5F);
/* 199 */     this.tail1.addBox(-5.0F, -4.5F, 0.0F, 10.0F, 9.0F, 5.0F, 0.0F);
/* 200 */     setRotateAngle(this.tail1, 0.08726646F, -0.0F, 0.0F);
/* 201 */     this.leftgill = new ModelRenderer((Model)this, 0, 61);
/* 202 */     this.leftgill.setRotationPoint(2.2F, -1.0F, 0.0F);
/* 203 */     this.leftgill.addBox(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, 0.0F);
/* 204 */     setRotateAngle(this.leftgill, -0.17732546F, -0.3064798F, 0.93322754F);
/* 205 */     this.saddle.addChild(this.saddleside2);
/* 206 */     this.tail1.addChild(this.tail2);
/* 207 */     this.saddle.addChild(this.saddleside3);
/* 208 */     this.neck2.addChild(this.neck3);
/* 209 */     this.head2.addChild(this.rightgill);
/* 210 */     this.tail5.addChild(this.tail6);
/* 211 */     this.tail3.addChild(this.tail4);
/* 212 */     this.neck1.addChild(this.neck2);
/* 213 */     this.head1.addChild(this.head2);
/* 214 */     this.head3.addChild(this.mout);
/* 215 */     this.saddle.addChild(this.saddlemiddle);
/* 216 */     this.saddle.addChild(this.saddleside4);
/* 217 */     this.neck4.addChild(this.head1);
/* 218 */     this.head2.addChild(this.lefteye);
/* 219 */     this.saddle.addChild(this.saddleback);
/* 220 */     this.head2.addChild(this.righteye);
/* 221 */     this.saddle.addChild(this.saddleside1);
/* 222 */     this.tail2.addChild(this.tail3);
/* 223 */     this.head2.addChild(this.head3);
/* 224 */     this.tail4.addChild(this.tail5);
/* 225 */     this.saddle.addChild(this.saddlefront);
/* 226 */     this.head1.addChild(this.mane);
/* 227 */     this.neck3.addChild(this.neck4);
/* 228 */     this.head2.addChild(this.leftgill);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 234 */     this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 235 */     this.rightfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 236 */     this.leftfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 237 */     this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 238 */     this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 239 */     this.leftfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 240 */     this.rightfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 241 */     this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderSaddle(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 246 */     this.saddle.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 247 */     this.belt1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 248 */     this.belt2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 254 */     this.tail1.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
/* 255 */     this.tail2.rotateAngleY = 0.2F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
/* 256 */     this.tail3.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
/* 257 */     this.tail4.rotateAngleY = 0.2F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
/* 258 */     this.tail5.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
/*     */     
/* 260 */     this.leftfin1.rotateAngleY = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
/* 261 */     this.leftfin1.rotateAngleX = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
/* 262 */     this.leftfin2.rotateAngleY = 0.2F * MathHelper.cos(ageInTicks * 0.15F);
/* 263 */     this.leftfin2.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.15F);
/*     */     
/* 265 */     this.rightfin1.rotateAngleY = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
/* 266 */     this.rightfin1.rotateAngleX = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
/* 267 */     this.rightfin2.rotateAngleY = 0.2F * MathHelper.cos(ageInTicks * 0.15F);
/* 268 */     this.rightfin2.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.15F);
/*     */ 
/*     */     
/* 271 */     if (ageInTicks % 300.0F > 0.0F && ageInTicks % 300.0F < 50.0F) {
/* 272 */       this.neck4.rotateAngleZ = 0.4F * MathHelper.cos(ageInTicks * 0.25F);
/*     */     } else {
/* 274 */       this.neck4.rotateAngleZ = (float)Math.toDegrees(0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 279 */     modelRenderer.rotateAngleX = x;
/* 280 */     modelRenderer.rotateAngleY = y;
/* 281 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\YagaraBullModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */