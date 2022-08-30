/*     */ package xyz.pixelatedw.mineminenomi.models.blocks;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class WantedPosterPackageModel
/*     */   extends EntityModel
/*     */ {
/*     */   public ModelRenderer poster1;
/*     */   public ModelRenderer poster2;
/*     */   public ModelRenderer poster3;
/*     */   public ModelRenderer poster4;
/*     */   public ModelRenderer poster5;
/*     */   public ModelRenderer poster6;
/*     */   public ModelRenderer poster7;
/*     */   public ModelRenderer package1;
/*     */   public ModelRenderer package2;
/*     */   public ModelRenderer package3;
/*     */   public ModelRenderer package4;
/*     */   public ModelRenderer package5;
/*     */   public ModelRenderer package6;
/*     */   public ModelRenderer package7;
/*     */   public ModelRenderer package8;
/*     */   public ModelRenderer package9;
/*     */   public ModelRenderer package10;
/*     */   public ModelRenderer package11;
/*     */   public ModelRenderer package12;
/*     */   public ModelRenderer parachute;
/*     */   public ModelRenderer parachute_cord1;
/*     */   public ModelRenderer parachute_cord2;
/*     */   public ModelRenderer parachute_cord3;
/*     */   public ModelRenderer parachute_cord4;
/*     */   public ModelRenderer parachute_cloth1;
/*     */   public ModelRenderer parachute_cloth2;
/*     */   public ModelRenderer parachute_cloth3;
/*     */   public ModelRenderer parachute_cloth4;
/*     */   public ModelRenderer parachute_cloth5;
/*     */   public ModelRenderer parachute_cloth6;
/*     */   public ModelRenderer parachute_cloth7;
/*     */   public ModelRenderer parachute_cloth8;
/*     */   
/*     */   public WantedPosterPackageModel() {
/*  50 */     this.textureWidth = 64;
/*  51 */     this.textureHeight = 64;
/*  52 */     this.package9 = new ModelRenderer((Model)this, 7, 0);
/*  53 */     this.package9.setRotationPoint(0.0F, 21.0F, 0.0F);
/*  54 */     this.package9.addBox(-0.5F, -1.0F, -9.0F, 1.0F, 1.0F, 18.0F, 0.0F);
/*  55 */     this.parachute_cloth8 = new ModelRenderer((Model)this, 5, 30);
/*  56 */     this.parachute_cloth8.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  57 */     this.parachute_cloth8.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  58 */     setRotateAngle(this.parachute_cloth8, 0.2617994F, 5.497787F, 0.0F);
/*  59 */     this.parachute_cloth2 = new ModelRenderer((Model)this, 5, 30);
/*  60 */     this.parachute_cloth2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  61 */     this.parachute_cloth2.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  62 */     setRotateAngle(this.parachute_cloth2, 0.2617994F, 0.7853982F, 0.0F);
/*  63 */     this.package6 = new ModelRenderer((Model)this, 30, 0);
/*  64 */     this.package6.setRotationPoint(0.0F, 21.0F, 0.0F);
/*  65 */     this.package6.addBox(-7.0F, -1.0F, 4.0F, 14.0F, 1.0F, 1.0F, 0.0F);
/*  66 */     this.parachute = new ModelRenderer((Model)this, 0, 30);
/*  67 */     this.parachute.setRotationPoint(0.0F, 19.0F, 0.0F);
/*  68 */     this.parachute.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
/*  69 */     this.poster1 = new ModelRenderer((Model)this, -16, 0);
/*  70 */     this.poster1.setRotationPoint(0.0F, 24.0F, 0.0F);
/*  71 */     this.poster1.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
/*  72 */     this.parachute_cloth7 = new ModelRenderer((Model)this, 5, 30);
/*  73 */     this.parachute_cloth7.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  74 */     this.parachute_cloth7.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  75 */     setRotateAngle(this.parachute_cloth7, 0.2617994F, 4.712389F, 0.0F);
/*  76 */     this.package1 = new ModelRenderer((Model)this, 0, 20);
/*  77 */     this.package1.setRotationPoint(0.0F, 21.0F, 0.0F);
/*  78 */     this.package1.addBox(-7.0F, 0.0F, 4.0F, 1.0F, 3.0F, 1.0F, 0.0F);
/*  79 */     this.package10 = new ModelRenderer((Model)this, 7, 0);
/*  80 */     this.package10.setRotationPoint(0.0F, 21.0F, 0.0F);
/*  81 */     this.package10.addBox(-0.5F, 3.0F, -9.0F, 1.0F, 1.0F, 18.0F, 0.0F);
/*  82 */     this.poster5 = new ModelRenderer((Model)this, -16, 0);
/*  83 */     this.poster5.setRotationPoint(0.0F, 22.0F, 0.0F);
/*  84 */     this.poster5.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
/*  85 */     this.parachute_cord1 = new ModelRenderer((Model)this, 0, 30);
/*  86 */     this.parachute_cord1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  87 */     this.parachute_cord1.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/*  88 */     setRotateAngle(this.parachute_cord1, 0.43633232F, 0.0F, 0.0F);
/*  89 */     this.parachute_cloth3 = new ModelRenderer((Model)this, 5, 30);
/*  90 */     this.parachute_cloth3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  91 */     this.parachute_cloth3.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/*  92 */     setRotateAngle(this.parachute_cloth3, 0.2617994F, 1.5707964F, 0.0F);
/*  93 */     this.parachute_cloth6 = new ModelRenderer((Model)this, 5, 30);
/*  94 */     this.parachute_cloth6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  95 */     this.parachute_cloth6.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/*  96 */     setRotateAngle(this.parachute_cloth6, 0.2617994F, 3.9269907F, 0.0F);
/*  97 */     this.parachute_cord2 = new ModelRenderer((Model)this, 0, 30);
/*  98 */     this.parachute_cord2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  99 */     this.parachute_cord2.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/* 100 */     setRotateAngle(this.parachute_cord2, 0.43633232F, 3.1415927F, 0.0F);
/* 101 */     this.package11 = new ModelRenderer((Model)this, 0, 20);
/* 102 */     this.package11.setRotationPoint(0.0F, 21.0F, 0.0F);
/* 103 */     this.package11.addBox(-0.5F, 0.0F, -9.0F, 1.0F, 3.0F, 1.0F, 0.0F);
/* 104 */     this.package5 = new ModelRenderer((Model)this, 30, 0);
/* 105 */     this.package5.setRotationPoint(0.0F, 21.0F, 0.0F);
/* 106 */     this.package5.addBox(-7.0F, -1.0F, -5.0F, 14.0F, 1.0F, 1.0F, 0.0F);
/* 107 */     this.parachute_cord3 = new ModelRenderer((Model)this, 0, 30);
/* 108 */     this.parachute_cord3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 109 */     this.parachute_cord3.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/* 110 */     setRotateAngle(this.parachute_cord3, 0.43633232F, 1.5707964F, 0.0F);
/* 111 */     this.package4 = new ModelRenderer((Model)this, 0, 20);
/* 112 */     this.package4.setRotationPoint(0.0F, 21.0F, 0.0F);
/* 113 */     this.package4.addBox(6.0F, 0.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F);
/* 114 */     this.poster4 = new ModelRenderer((Model)this, -16, 0);
/* 115 */     this.poster4.setRotationPoint(0.0F, 22.5F, 0.0F);
/* 116 */     this.poster4.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
/* 117 */     this.parachute_cloth1 = new ModelRenderer((Model)this, 5, 30);
/* 118 */     this.parachute_cloth1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 119 */     this.parachute_cloth1.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/* 120 */     setRotateAngle(this.parachute_cloth1, 0.2617994F, 0.0F, 0.0F);
/* 121 */     this.package2 = new ModelRenderer((Model)this, 0, 20);
/* 122 */     this.package2.setRotationPoint(0.0F, 21.0F, 0.0F);
/* 123 */     this.package2.addBox(-7.0F, 0.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F);
/* 124 */     this.poster6 = new ModelRenderer((Model)this, -16, 0);
/* 125 */     this.poster6.setRotationPoint(0.0F, 21.5F, 0.0F);
/* 126 */     this.poster6.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
/* 127 */     this.package8 = new ModelRenderer((Model)this, 30, 0);
/* 128 */     this.package8.setRotationPoint(0.0F, 21.0F, 0.0F);
/* 129 */     this.package8.addBox(-7.0F, 3.0F, 4.0F, 14.0F, 1.0F, 1.0F, 0.0F);
/* 130 */     this.parachute_cloth4 = new ModelRenderer((Model)this, 5, 30);
/* 131 */     this.parachute_cloth4.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 132 */     this.parachute_cloth4.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
/* 133 */     setRotateAngle(this.parachute_cloth4, 0.2617994F, 2.3561945F, 0.0F);
/* 134 */     this.poster3 = new ModelRenderer((Model)this, -16, 0);
/* 135 */     this.poster3.setRotationPoint(0.0F, 23.0F, 0.0F);
/* 136 */     this.poster3.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
/* 137 */     this.parachute_cord4 = new ModelRenderer((Model)this, 0, 30);
/* 138 */     this.parachute_cord4.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 139 */     this.parachute_cord4.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
/* 140 */     setRotateAngle(this.parachute_cord4, 0.43633232F, -1.5707964F, 0.0F);
/* 141 */     this.poster7 = new ModelRenderer((Model)this, -16, 0);
/* 142 */     this.poster7.setRotationPoint(0.0F, 21.0F, 0.0F);
/* 143 */     this.poster7.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
/* 144 */     this.parachute_cloth5 = new ModelRenderer((Model)this, 5, 30);
/* 145 */     this.parachute_cloth5.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 146 */     this.parachute_cloth5.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
/* 147 */     setRotateAngle(this.parachute_cloth5, 0.2617994F, 3.1415927F, 0.0F);
/* 148 */     this.package7 = new ModelRenderer((Model)this, 30, 0);
/* 149 */     this.package7.setRotationPoint(0.0F, 21.0F, 0.0F);
/* 150 */     this.package7.addBox(-7.0F, 3.0F, -5.0F, 14.0F, 1.0F, 1.0F, 0.0F);
/* 151 */     this.poster2 = new ModelRenderer((Model)this, -16, 0);
/* 152 */     this.poster2.setRotationPoint(0.0F, 23.5F, 0.0F);
/* 153 */     this.poster2.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
/* 154 */     this.package3 = new ModelRenderer((Model)this, 0, 20);
/* 155 */     this.package3.setRotationPoint(0.0F, 21.0F, 0.0F);
/* 156 */     this.package3.addBox(6.0F, 0.0F, 4.0F, 1.0F, 3.0F, 1.0F, 0.0F);
/* 157 */     this.package12 = new ModelRenderer((Model)this, 0, 20);
/* 158 */     this.package12.setRotationPoint(0.0F, 21.0F, 0.0F);
/* 159 */     this.package12.addBox(-0.5F, 0.0F, 8.0F, 1.0F, 3.0F, 1.0F, 0.0F);
/* 160 */     this.parachute.addChild(this.parachute_cloth8);
/* 161 */     this.parachute.addChild(this.parachute_cloth2);
/* 162 */     this.parachute.addChild(this.parachute_cloth7);
/* 163 */     this.parachute.addChild(this.parachute_cord1);
/* 164 */     this.parachute.addChild(this.parachute_cloth3);
/* 165 */     this.parachute.addChild(this.parachute_cloth6);
/* 166 */     this.parachute.addChild(this.parachute_cord2);
/* 167 */     this.parachute.addChild(this.parachute_cord3);
/* 168 */     this.parachute.addChild(this.parachute_cloth1);
/* 169 */     this.parachute.addChild(this.parachute_cloth4);
/* 170 */     this.parachute.addChild(this.parachute_cord4);
/* 171 */     this.parachute.addChild(this.parachute_cloth5);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 177 */     this.package9.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 178 */     this.package6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 179 */     this.parachute.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 180 */     this.poster1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 181 */     this.package1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 182 */     this.package10.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 183 */     this.poster5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 184 */     this.package11.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 185 */     this.package5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 186 */     this.package4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 187 */     this.poster4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 188 */     this.package2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 189 */     this.poster6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 190 */     this.package8.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 191 */     this.poster3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 192 */     this.poster7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 193 */     this.package7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 194 */     this.poster2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 195 */     this.package3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 196 */     this.package12.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 207 */     modelRenderer.rotateAngleX = x;
/* 208 */     modelRenderer.rotateAngleY = y;
/* 209 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\blocks\WantedPosterPackageModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */