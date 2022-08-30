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
/*     */ public class CandleChampionModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer spikes;
/*     */   private final ModelRenderer leftSpikes;
/*     */   private final ModelRenderer leftSpike1;
/*     */   private final ModelRenderer leftSpike2;
/*     */   private final ModelRenderer leftSpike3;
/*     */   private final ModelRenderer leftSpike4;
/*     */   private final ModelRenderer rightSpikes;
/*     */   private final ModelRenderer rightSpike1;
/*     */   private final ModelRenderer rightSpike2;
/*     */   private final ModelRenderer rightSpike3;
/*     */   private final ModelRenderer rightSpike4;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2_r1;
/*     */   private final ModelRenderer leftLeg1_r1;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2_r1;
/*     */   private final ModelRenderer rightLeg1_r1;
/*     */   
/*     */   public CandleChampionModel() {
/*  39 */     super(1.0F);
/*  40 */     this.textureWidth = 128;
/*  41 */     this.textureHeight = 128;
/*     */     
/*  43 */     this.body = new ModelRenderer((Model)this);
/*  44 */     this.body.setRotationPoint(0.0F, 24.0F, 0.0F);
/*  45 */     this.body.setTextureOffset(0, 0).addBox(-8.5F, -31.1F, -8.5F, 17.0F, 11.0F, 12.0F, 0.0F, false);
/*  46 */     this.body.setTextureOffset(0, 23).addBox(-4.5F, -32.1F, -6.5F, 9.0F, 10.0F, 11.0F, 0.0F, false);
/*  47 */     this.body.setTextureOffset(0, 44).addBox(-11.5F, -30.5F, -8.0F, 3.0F, 10.0F, 11.0F, 0.0F, false);
/*  48 */     this.body.setTextureOffset(0, 44).addBox(8.5F, -30.5F, -8.0F, 3.0F, 10.0F, 11.0F, 0.0F, false);
/*  49 */     this.body.setTextureOffset(46, 23).addBox(-3.0F, -26.1F, -7.0F, 6.0F, 7.0F, 9.0F, 0.0F, false);
/*  50 */     this.body.setTextureOffset(67, 21).addBox(-2.5F, -20.1F, -5.5F, 5.0F, 5.0F, 6.0F, 0.0F, false);
/*  51 */     this.body.setTextureOffset(46, 0).addBox(-4.5F, -15.1F, -6.5F, 9.0F, 1.0F, 8.0F, 0.0F, false);
/*  52 */     this.body.setTextureOffset(0, 66).addBox(-4.0F, -15.0F, -6.0F, 8.0F, 4.0F, 7.0F, 0.0F, false);
/*  53 */     this.body.setTextureOffset(28, 54).addBox(-2.5F, -13.5F, -6.5F, 5.0F, 3.0F, 8.0F, 0.0F, false);
/*     */     
/*  55 */     this.spikes = new ModelRenderer((Model)this);
/*  56 */     this.spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  57 */     this.body.addChild(this.spikes);
/*     */     
/*  59 */     this.leftSpikes = new ModelRenderer((Model)this);
/*  60 */     this.leftSpikes.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  61 */     this.spikes.addChild(this.leftSpikes);
/*     */     
/*  63 */     this.leftSpike1 = new ModelRenderer((Model)this);
/*  64 */     this.leftSpike1.setRotationPoint(-10.0F, -32.5F, -2.5F);
/*  65 */     this.leftSpikes.addChild(this.leftSpike1);
/*  66 */     this.leftSpike1.setTextureOffset(0, 8).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  67 */     this.leftSpike1.setTextureOffset(8, 8).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  69 */     this.leftSpike2 = new ModelRenderer((Model)this);
/*  70 */     this.leftSpike2.setRotationPoint(-10.0F, -18.5F, -2.5F);
/*  71 */     this.leftSpikes.addChild(this.leftSpike2);
/*  72 */     this.leftSpike2.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  73 */     this.leftSpike2.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  75 */     this.leftSpike3 = new ModelRenderer((Model)this);
/*  76 */     this.leftSpike3.setRotationPoint(-10.0F, -25.5F, -9.5F);
/*  77 */     this.leftSpikes.addChild(this.leftSpike3);
/*  78 */     setRotationAngle(this.leftSpike3, -1.5708F, 0.0F, 0.0F);
/*  79 */     this.leftSpike3.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  80 */     this.leftSpike3.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  82 */     this.leftSpike4 = new ModelRenderer((Model)this);
/*  83 */     this.leftSpike4.setRotationPoint(-10.0F, -25.5F, 4.5F);
/*  84 */     this.leftSpikes.addChild(this.leftSpike4);
/*  85 */     setRotationAngle(this.leftSpike4, 1.5708F, 0.0F, 0.0F);
/*  86 */     this.leftSpike4.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  87 */     this.leftSpike4.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  89 */     this.rightSpikes = new ModelRenderer((Model)this);
/*  90 */     this.rightSpikes.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  91 */     this.spikes.addChild(this.rightSpikes);
/*     */     
/*  93 */     this.rightSpike1 = new ModelRenderer((Model)this);
/*  94 */     this.rightSpike1.setRotationPoint(10.0F, -32.5F, -2.5F);
/*  95 */     this.rightSpikes.addChild(this.rightSpike1);
/*  96 */     this.rightSpike1.setTextureOffset(0, 8).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/*  97 */     this.rightSpike1.setTextureOffset(8, 8).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/*  99 */     this.rightSpike2 = new ModelRenderer((Model)this);
/* 100 */     this.rightSpike2.setRotationPoint(10.0F, -18.5F, -2.5F);
/* 101 */     this.rightSpikes.addChild(this.rightSpike2);
/* 102 */     this.rightSpike2.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/* 103 */     this.rightSpike2.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 105 */     this.rightSpike3 = new ModelRenderer((Model)this);
/* 106 */     this.rightSpike3.setRotationPoint(10.0F, -25.5F, -9.5F);
/* 107 */     this.rightSpikes.addChild(this.rightSpike3);
/* 108 */     setRotationAngle(this.rightSpike3, -1.5708F, 0.0F, 0.0F);
/* 109 */     this.rightSpike3.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/* 110 */     this.rightSpike3.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 112 */     this.rightSpike4 = new ModelRenderer((Model)this);
/* 113 */     this.rightSpike4.setRotationPoint(10.0F, -25.5F, 4.5F);
/* 114 */     this.rightSpikes.addChild(this.rightSpike4);
/* 115 */     setRotationAngle(this.rightSpike4, 1.5708F, 0.0F, 0.0F);
/* 116 */     this.rightSpike4.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
/* 117 */     this.rightSpike4.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
/*     */     
/* 119 */     this.rightArm = new ModelRenderer((Model)this);
/* 120 */     this.rightArm.setRotationPoint(12.0F, -0.5F, -2.5F);
/* 121 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, -0.3927F);
/* 122 */     this.rightArm.setTextureOffset(0, 44).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);
/* 123 */     this.rightArm.setTextureOffset(17, 49).addBox(-1.5F, 5.5F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
/* 124 */     this.rightArm.setTextureOffset(48, 70).addBox(-3.0F, 7.5F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 125 */     this.rightArm.setTextureOffset(35, 68).addBox(-3.5F, 8.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 127 */     this.leftArm = new ModelRenderer((Model)this);
/* 128 */     this.leftArm.setRotationPoint(-11.5F, -1.5F, -2.5F);
/* 129 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, 0.3927F);
/* 130 */     this.leftArm.setTextureOffset(0, 44).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);
/* 131 */     this.leftArm.setTextureOffset(17, 49).addBox(-1.5F, 6.5F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
/* 132 */     this.leftArm.setTextureOffset(48, 70).addBox(-3.0F, 8.5F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 133 */     this.leftArm.setTextureOffset(35, 68).addBox(2.5F, 9.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
/*     */     
/* 135 */     this.leftLeg = new ModelRenderer((Model)this);
/* 136 */     this.leftLeg.setRotationPoint(-3.5F, 11.0F, -2.5F);
/* 137 */     this.leftLeg.setTextureOffset(17, 44).addBox(-3.5F, 5.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
/* 138 */     this.leftLeg.setTextureOffset(54, 58).addBox(-5.0F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 139 */     this.leftLeg.setTextureOffset(47, 45).addBox(-5.0F, 7.0F, 5.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 140 */     this.leftLeg.setTextureOffset(72, 64).addBox(-4.5F, 8.0F, 2.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/* 142 */     this.leftLeg2_r1 = new ModelRenderer((Model)this);
/* 143 */     this.leftLeg2_r1.setRotationPoint(-1.4463F, 3.8114F, 0.0F);
/* 144 */     this.leftLeg.addChild(this.leftLeg2_r1);
/* 145 */     setRotationAngle(this.leftLeg2_r1, 0.0F, 0.0F, 0.1745F);
/* 146 */     this.leftLeg2_r1.setTextureOffset(35, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 148 */     this.leftLeg1_r1 = new ModelRenderer((Model)this);
/* 149 */     this.leftLeg1_r1.setRotationPoint(-0.293F, 0.1536F, 0.0F);
/* 150 */     this.leftLeg.addChild(this.leftLeg1_r1);
/* 151 */     setRotationAngle(this.leftLeg1_r1, 0.0F, 0.0F, 0.4363F);
/* 152 */     this.leftLeg1_r1.setTextureOffset(30, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 154 */     this.rightLeg = new ModelRenderer((Model)this);
/* 155 */     this.rightLeg.setRotationPoint(3.5F, 11.0F, -2.5F);
/* 156 */     this.rightLeg.setTextureOffset(17, 44).addBox(0.5F, 5.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
/* 157 */     this.rightLeg.setTextureOffset(54, 58).addBox(-1.0F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 158 */     this.rightLeg.setTextureOffset(47, 45).addBox(-1.0F, 7.0F, 5.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/* 159 */     this.rightLeg.setTextureOffset(72, 64).addBox(-0.5F, 8.0F, 2.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/* 161 */     this.rightLeg2_r1 = new ModelRenderer((Model)this);
/* 162 */     this.rightLeg2_r1.setRotationPoint(1.6087F, 3.9529F, 0.0F);
/* 163 */     this.rightLeg.addChild(this.rightLeg2_r1);
/* 164 */     setRotationAngle(this.rightLeg2_r1, 0.0F, 0.0F, -0.1745F);
/* 165 */     this.rightLeg2_r1.setTextureOffset(35, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 167 */     this.rightLeg1_r1 = new ModelRenderer((Model)this);
/* 168 */     this.rightLeg1_r1.setRotationPoint(0.4555F, 0.2951F, 0.0F);
/* 169 */     this.rightLeg.addChild(this.rightLeg1_r1);
/* 170 */     setRotationAngle(this.rightLeg1_r1, 0.0F, 0.0F, -0.4363F);
/* 171 */     this.rightLeg1_r1.setTextureOffset(30, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 177 */     matrixStack.push();
/* 178 */     matrixStack.scale(2.0F, 2.0F, 2.0F);
/* 179 */     matrixStack.translate(0.0D, -0.7D, 0.0D);
/* 180 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 181 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 182 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 183 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 184 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 185 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 192 */     float f = 1.0F;
/* 193 */     this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 194 */     this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 195 */     this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 196 */     this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 197 */     if (!entity.getHeldItemMainhand().isEmpty()) {
/* 198 */       this.rightArm.rotateAngleX += -0.15F;
/*     */     }
/*     */     
/* 201 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 202 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 204 */       this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 205 */       this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
/* 206 */       this.leftArm.rotateAngleY += this.body.rotateAngleY;
/* 207 */       this.leftArm.rotateAngleX += this.body.rotateAngleY;
/* 208 */       float f1 = 1.0F - this.swingProgress;
/* 209 */       f1 *= f1;
/* 210 */       f1 *= f1;
/* 211 */       f1 = 1.0F - f1;
/* 212 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 213 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * 0.75F;
/* 214 */       this.rightArm.rotateAngleX = -((float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3));
/* 215 */       this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
/* 216 */       this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 222 */     modelRenderer.rotateAngleX = x;
/* 223 */     modelRenderer.rotateAngleY = y;
/* 224 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 230 */     if (side == HandSide.RIGHT) {
/*     */       
/* 232 */       matrixStack.translate(-1.5D, -0.1D, 0.1D);
/* 233 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(10.0F));
/* 234 */       this.rightArm.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 238 */       matrixStack.translate(1.5D, -0.1D, 0.1D);
/* 239 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(-10.0F));
/* 240 */       this.leftArm.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 247 */     if (side == HandSide.RIGHT) {
/*     */       
/* 249 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(30.0F));
/* 250 */       matrixStack.translate(0.2D, -1.2D, 0.3D);
/* 251 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 252 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(130.0F));
/* 253 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 257 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(30.0F));
/* 258 */       matrixStack.translate(-0.3D, -1.0D, -0.3D);
/* 259 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 260 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-130.0F));
/* 261 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 268 */     super.translateHand(side, matrixStack);
/* 269 */     matrixStack.translate((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\CandleChampionModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */