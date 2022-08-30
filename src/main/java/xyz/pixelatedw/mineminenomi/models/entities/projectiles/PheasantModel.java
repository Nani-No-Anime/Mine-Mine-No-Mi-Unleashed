/*     */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class PheasantModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer body1;
/*     */   public ModelRenderer body2;
/*     */   public ModelRenderer body3;
/*     */   public ModelRenderer body4;
/*     */   public ModelRenderer body5;
/*     */   public ModelRenderer head;
/*     */   public ModelRenderer tuft1;
/*     */   public ModelRenderer tuft2;
/*     */   public ModelRenderer tuft3;
/*     */   public ModelRenderer beak1;
/*     */   public ModelRenderer beak2;
/*     */   public ModelRenderer beak3;
/*     */   public ModelRenderer beak4;
/*     */   public ModelRenderer tail1;
/*     */   public ModelRenderer tail2;
/*     */   public ModelRenderer tail3;
/*     */   public ModelRenderer rightleg1;
/*     */   public ModelRenderer rightleg2;
/*     */   public ModelRenderer rightfoot1;
/*     */   public ModelRenderer leftleg1;
/*     */   public ModelRenderer leftleg2;
/*     */   public ModelRenderer leftfoot1;
/*     */   public ModelRenderer rightWing1;
/*     */   public ModelRenderer leftWing1;
/*     */   public ModelRenderer rightWing2;
/*     */   public ModelRenderer rightWing3;
/*     */   public ModelRenderer rightWing4;
/*     */   public ModelRenderer rightWing5;
/*     */   public ModelRenderer leftWing2;
/*     */   public ModelRenderer leftWing3;
/*     */   public ModelRenderer leftWing4;
/*     */   public ModelRenderer leftWing5;
/*     */   
/*     */   public PheasantModel() {
/*  51 */     this.textureWidth = 128;
/*  52 */     this.textureHeight = 64;
/*  53 */     this.body3 = new ModelRenderer((Model)this, 0, 27);
/*  54 */     this.body3.setRotationPoint(0.0F, 8.5F, -5.7F);
/*  55 */     this.body3.addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, 0.0F);
/*  56 */     setRotateAngle(this.body3, -0.05235988F, -0.0F, 0.0F);
/*  57 */     this.leftWing5 = new ModelRenderer((Model)this, 94, 5);
/*  58 */     this.leftWing5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  59 */     this.leftWing5.addBox(0.5F, 0.5F, 1.0F, 6.0F, 0.0F, 6.0F, 0.0F);
/*  60 */     setRotateAngle(this.leftWing5, -0.017453292F, 0.0F, 0.0F);
/*  61 */     this.tuft3 = new ModelRenderer((Model)this, 29, 51);
/*  62 */     this.tuft3.setRotationPoint(0.0F, 7.0F, -12.5F);
/*  63 */     this.tuft3.addBox(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F);
/*  64 */     setRotateAngle(this.tuft3, 0.6981317F, -0.0F, 0.34906584F);
/*  65 */     this.rightleg2 = new ModelRenderer((Model)this, 48, 0);
/*  66 */     this.rightleg2.setRotationPoint(-2.0F, 10.5F, 2.0F);
/*  67 */     this.rightleg2.addBox(-0.5F, 4.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/*  68 */     setRotateAngle(this.rightleg2, 0.7853982F, -0.17453292F, 0.0F);
/*  69 */     this.body1 = new ModelRenderer((Model)this, 0, 0);
/*  70 */     this.body1.setRotationPoint(0.0F, 9.5F, 0.0F);
/*  71 */     this.body1.addBox(-3.5F, -4.0F, -5.0F, 7.0F, 7.0F, 10.0F, 0.0F);
/*  72 */     this.rightWing3 = new ModelRenderer((Model)this, 91, 0);
/*  73 */     this.rightWing3.setRotationPoint(-12.0F, -0.7F, -1.0F);
/*  74 */     this.rightWing3.addBox(-6.5F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F);
/*  75 */     setRotateAngle(this.rightWing3, 0.0F, 0.2443461F, 0.0F);
/*  76 */     this.beak4 = new ModelRenderer((Model)this, 7, 61);
/*  77 */     this.beak4.setRotationPoint(0.0F, 8.9F, -13.2F);
/*  78 */     this.beak4.addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F);
/*  79 */     setRotateAngle(this.beak4, -0.17453292F, -0.0F, 0.0F);
/*  80 */     this.beak1 = new ModelRenderer((Model)this, 0, 58);
/*  81 */     this.beak1.setRotationPoint(0.0F, 8.4F, -13.5F);
/*  82 */     this.beak1.addBox(-0.8F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F);
/*  83 */     setRotateAngle(this.beak1, 0.2664757F, -0.2617586F, -0.018068846F);
/*  84 */     this.rightWing2 = new ModelRenderer((Model)this, 70, 0);
/*  85 */     this.rightWing2.setRotationPoint(-3.1F, -0.1F, -0.5F);
/*  86 */     this.rightWing2.addBox(-9.5F, 0.0F, 0.5F, 9.0F, 1.0F, 1.0F, 0.0F);
/*  87 */     setRotateAngle(this.rightWing2, -0.03577925F, -0.10035643F, 0.060737457F);
/*  88 */     this.leftfoot1 = new ModelRenderer((Model)this, 48, 5);
/*  89 */     this.leftfoot1.setRotationPoint(1.8F, 14.8F, 3.9F);
/*  90 */     this.leftfoot1.addBox(-1.0F, 1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F);
/*  91 */     setRotateAngle(this.leftfoot1, 1.3089969F, 0.17453292F, 0.0F);
/*  92 */     this.rightWing4 = new ModelRenderer((Model)this, 57, 5);
/*  93 */     this.rightWing4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  94 */     this.rightWing4.addBox(-8.8F, 0.5F, 1.0F, 12.0F, 0.0F, 6.0F, 0.0F);
/*  95 */     setRotateAngle(this.rightWing4, 0.017453292F, 0.045553092F, 0.0F);
/*  96 */     this.leftWing4 = new ModelRenderer((Model)this, 57, 5);
/*  97 */     this.leftWing4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  98 */     this.leftWing4.addBox(-2.8F, 0.5F, 1.0F, 12.0F, 0.0F, 6.0F, 0.0F);
/*  99 */     setRotateAngle(this.leftWing4, 0.017453292F, -0.045553092F, 0.0F);
/* 100 */     this.rightleg1 = new ModelRenderer((Model)this, 35, 0);
/* 101 */     this.rightleg1.setRotationPoint(-2.0F, 10.5F, 2.0F);
/* 102 */     this.rightleg1.addBox(-1.0F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F);
/* 103 */     setRotateAngle(this.rightleg1, 0.7853982F, -0.17453292F, 0.0F);
/* 104 */     this.tuft2 = new ModelRenderer((Model)this, 22, 51);
/* 105 */     this.tuft2.setRotationPoint(0.0F, 7.0F, -12.5F);
/* 106 */     this.tuft2.addBox(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F);
/* 107 */     setRotateAngle(this.tuft2, 0.6981317F, -0.0F, -0.34906584F);
/* 108 */     this.leftleg1 = new ModelRenderer((Model)this, 35, 0);
/* 109 */     this.leftleg1.setRotationPoint(2.0F, 10.5F, 2.0F);
/* 110 */     this.leftleg1.addBox(-2.0F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F);
/* 111 */     setRotateAngle(this.leftleg1, 0.7853982F, 0.17453292F, 0.0F);
/* 112 */     this.leftWing3 = new ModelRenderer((Model)this, 91, 0);
/* 113 */     this.leftWing3.setRotationPoint(12.0F, -0.6F, -1.0F);
/* 114 */     this.leftWing3.addBox(0.5F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F);
/* 115 */     setRotateAngle(this.leftWing3, 0.0F, -0.2443461F, 0.0F);
/* 116 */     this.head = new ModelRenderer((Model)this, 0, 51);
/* 117 */     this.head.setRotationPoint(0.0F, 8.0F, -11.5F);
/* 118 */     this.head.addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F);
/* 119 */     setRotateAngle(this.head, 0.08726646F, -0.0F, 0.0F);
/* 120 */     this.beak2 = new ModelRenderer((Model)this, 0, 61);
/* 121 */     this.beak2.setRotationPoint(0.0F, 8.4F, -13.5F);
/* 122 */     this.beak2.addBox(-0.2F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F);
/* 123 */     setRotateAngle(this.beak2, 0.2664757F, 0.2617586F, 0.018068846F);
/* 124 */     this.beak3 = new ModelRenderer((Model)this, 7, 58);
/* 125 */     this.beak3.setRotationPoint(0.0F, 8.3F, -13.5F);
/* 126 */     this.beak3.addBox(-0.5F, -0.5F, -1.2F, 1.0F, 1.0F, 2.0F, 0.0F);
/* 127 */     setRotateAngle(this.beak3, 0.2617994F, -0.0F, 0.0F);
/* 128 */     this.rightfoot1 = new ModelRenderer((Model)this, 48, 5);
/* 129 */     this.rightfoot1.setRotationPoint(-1.8F, 14.8F, 3.9F);
/* 130 */     this.rightfoot1.addBox(-1.0F, 1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F);
/* 131 */     setRotateAngle(this.rightfoot1, 1.3089969F, -0.17453292F, 0.0F);
/* 132 */     this.body2 = new ModelRenderer((Model)this, 0, 18);
/* 133 */     this.body2.setRotationPoint(0.0F, 9.0F, 5.5F);
/* 134 */     this.body2.addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, 0.0F);
/* 135 */     this.tail3 = new ModelRenderer((Model)this, 59, 18);
/* 136 */     this.tail3.setRotationPoint(0.0F, 8.0F, 6.0F);
/* 137 */     this.tail3.addBox(0.2F, -0.5F, 0.0F, 3.0F, 1.0F, 7.0F, 0.0F);
/* 138 */     setRotateAngle(this.tail3, 0.2617994F, 0.2617994F, 0.0F);
/* 139 */     this.tail1 = new ModelRenderer((Model)this, 17, 18);
/* 140 */     this.tail1.setRotationPoint(0.0F, 8.0F, 6.0F);
/* 141 */     this.tail1.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 8.0F, 0.0F);
/* 142 */     setRotateAngle(this.tail1, 0.2617994F, -0.0F, 0.0F);
/* 143 */     this.tail2 = new ModelRenderer((Model)this, 38, 18);
/* 144 */     this.tail2.setRotationPoint(0.0F, 8.0F, 6.0F);
/* 145 */     this.tail2.addBox(-3.2F, -0.5F, 0.0F, 3.0F, 1.0F, 7.0F, 0.0F);
/* 146 */     setRotateAngle(this.tail2, 0.2617994F, -0.2617994F, 0.0F);
/* 147 */     this.leftleg2 = new ModelRenderer((Model)this, 48, 0);
/* 148 */     this.leftleg2.setRotationPoint(2.0F, 10.5F, 2.0F);
/* 149 */     this.leftleg2.addBox(-1.5F, 4.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 150 */     setRotateAngle(this.leftleg2, 0.7853982F, 0.17453292F, 0.0F);
/* 151 */     this.tuft1 = new ModelRenderer((Model)this, 13, 51);
/* 152 */     this.tuft1.setRotationPoint(0.0F, 7.0F, -12.5F);
/* 153 */     this.tuft1.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F);
/* 154 */     setRotateAngle(this.tuft1, 0.6981317F, -0.0F, 0.0F);
/* 155 */     this.rightWing1 = new ModelRenderer((Model)this, 57, 0);
/* 156 */     this.rightWing1.setRotationPoint(-2.9F, 6.1F, -4.0F);
/* 157 */     this.rightWing1.addBox(-4.0F, -0.5F, -0.5F, 4.0F, 2.0F, 2.0F, 0.0F);
/* 158 */     setRotateAngle(this.rightWing1, -0.037274048F, -0.25919265F, 0.1444847F);
/* 159 */     this.body4 = new ModelRenderer((Model)this, 0, 36);
/* 160 */     this.body4.setRotationPoint(0.0F, 8.0F, -7.5F);
/* 161 */     this.body4.addBox(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, 0.0F);
/* 162 */     setRotateAngle(this.body4, -0.05235988F, -0.0F, 0.0F);
/* 163 */     this.rightWing5 = new ModelRenderer((Model)this, 94, 5);
/* 164 */     this.rightWing5.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 165 */     this.rightWing5.addBox(-6.2F, 0.5F, 1.0F, 6.0F, 0.0F, 6.0F, 0.0F);
/* 166 */     setRotateAngle(this.rightWing5, -0.017453292F, 0.0F, 0.0F);
/* 167 */     this.body5 = new ModelRenderer((Model)this, 0, 44);
/* 168 */     this.body5.setRotationPoint(0.0F, 8.5F, -8.7F);
/* 169 */     this.body5.addBox(-2.0F, -2.5F, -1.5F, 4.0F, 4.0F, 2.0F, 0.0F);
/* 170 */     this.leftWing2 = new ModelRenderer((Model)this, 70, 0);
/* 171 */     this.leftWing2.setRotationPoint(3.0F, -0.1F, -0.5F);
/* 172 */     this.leftWing2.addBox(0.5F, 0.0F, 0.5F, 9.0F, 1.0F, 1.0F, 0.0F);
/* 173 */     setRotateAngle(this.leftWing2, -0.03577925F, 0.10035643F, -0.060737457F);
/* 174 */     this.leftWing1 = new ModelRenderer((Model)this, 57, 0);
/* 175 */     this.leftWing1.setRotationPoint(2.9F, 6.1F, -4.0F);
/* 176 */     this.leftWing1.addBox(0.0F, -0.5F, -0.5F, 4.0F, 2.0F, 2.0F, 0.0F);
/* 177 */     setRotateAngle(this.leftWing1, -0.037350047F, 0.25918138F, -0.14451326F);
/* 178 */     this.leftWing3.addChild(this.leftWing5);
/* 179 */     this.rightWing1.addChild(this.rightWing3);
/* 180 */     this.rightWing1.addChild(this.rightWing2);
/* 181 */     this.rightWing2.addChild(this.rightWing4);
/* 182 */     this.leftWing2.addChild(this.leftWing4);
/* 183 */     this.leftWing1.addChild(this.leftWing3);
/* 184 */     this.rightWing3.addChild(this.rightWing5);
/* 185 */     this.leftWing1.addChild(this.leftWing2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent) {
/* 196 */     double[] animationWingMovement = { 5.0D, 10.0D, 15.0D, 20.0D, 25.0D, 30.0D, 35.0D, 30.0D, 25.0D, 20.0D, 15.0D, 10.0D, 5.0D, 0.0D, -5.0D, -10.0D, -15.0D, -10.0D, -5.0D, 0.0D, 5.0D };
/*     */     
/* 198 */     int cycleIndexFly = (int)(ent.ticksExisted * 1.85D % animationWingMovement.length);
/*     */     
/* 200 */     if (!Minecraft.getInstance().isGamePaused()) {
/*     */       
/* 202 */       this.rightWing1.rotateAngleZ = degToRad(animationWingMovement[cycleIndexFly]);
/* 203 */       this.leftWing1.rotateAngleZ = degToRad(animationWingMovement[cycleIndexFly]) * -1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float degToRad(double degrees) {
/* 209 */     return (float)(degrees * Math.PI / 180.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 214 */     model.rotateAngleX = x;
/* 215 */     model.rotateAngleY = y;
/* 216 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 222 */     matrixStack.translate(0.0D, -0.75D, 0.0D);
/*     */     
/* 224 */     this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 225 */     this.tuft3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 226 */     this.rightleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 227 */     this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 228 */     this.beak4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 229 */     this.beak1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 230 */     this.leftfoot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 231 */     this.rightleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 232 */     this.tuft2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 233 */     this.leftleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 234 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 235 */     this.beak2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 236 */     this.beak3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 237 */     this.rightfoot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 238 */     this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 239 */     this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 240 */     this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 241 */     this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 242 */     this.leftleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 243 */     this.tuft1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 244 */     this.rightWing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 245 */     this.body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 246 */     this.body5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 247 */     this.leftWing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\PheasantModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */