/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MorganModel<T extends MorganEntity>
/*     */   extends HumanoidModel<T>
/*     */ {
/*     */   public ModelRenderer head;
/*     */   public ModelRenderer body;
/*     */   public ModelRenderer rightarm1;
/*     */   public ModelRenderer leftarm1;
/*     */   public ModelRenderer rightleg;
/*     */   public ModelRenderer leftleg;
/*     */   public ModelRenderer jaw;
/*     */   public ModelRenderer rightarm2;
/*     */   public ModelRenderer axe_hand;
/*     */   
/*     */   public MorganModel() {
/*  34 */     this.textureWidth = 128;
/*  35 */     this.textureHeight = 64;
/*  36 */     this.axe51 = new ModelRenderer((Model)this, 105, 59);
/*  37 */     this.axe51.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  38 */     this.axe51.addBox(-1.5F, 12.1F, 5.8F, 1.0F, 3.0F, 2.0F, 0.0F);
/*  39 */     setRotateAngle(this.axe51, -0.41887903F, -0.0F, 0.0F);
/*  40 */     this.axe2 = new ModelRenderer((Model)this, 76, 53);
/*  41 */     this.axe2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  42 */     this.axe2.addBox(-2.0F, 10.0F, 9.5F, 2.0F, 7.0F, 4.0F, 0.0F);
/*  43 */     setRotateAngle(this.axe2, -0.9599311F, 0.0F, 0.0F);
/*  44 */     this.jaw = new ModelRenderer((Model)this, 25, 0);
/*  45 */     this.jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  46 */     this.jaw.addBox(-3.6F, -2.0F, -4.0F, 7.0F, 2.0F, 7.0F, 0.0F);
/*  47 */     this.leftarm1 = new ModelRenderer((Model)this, 21, 35);
/*  48 */     this.leftarm1.setRotationPoint(6.0F, -7.0F, 0.0F);
/*  49 */     this.leftarm1.addBox(-1.0F, -2.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
/*  50 */     setRotateAngle(this.leftarm1, 0.0F, -0.0F, -0.05235988F);
/*  51 */     this.body = new ModelRenderer((Model)this, 54, 0);
/*  52 */     this.body.setRotationPoint(0.0F, -9.0F, 0.0F);
/*  53 */     this.body.addBox(-5.0F, 0.0F, -2.5F, 10.0F, 16.0F, 5.0F, 0.0F);
/*  54 */     this.axe61 = new ModelRenderer((Model)this, 112, 59);
/*  55 */     this.axe61.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  56 */     this.axe61.addBox(-1.5F, 7.4F, 19.9F, 1.0F, 3.0F, 2.0F, 0.0F);
/*  57 */     setRotateAngle(this.axe61, -1.5358897F, -0.0F, 0.0F);
/*  58 */     this.axe41 = new ModelRenderer((Model)this, 100, 54);
/*  59 */     this.axe41.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  60 */     this.axe41.addBox(-1.5F, 9.0F, 13.5F, 1.0F, 9.0F, 1.0F, 0.0F);
/*  61 */     setRotateAngle(this.axe41, -0.9599311F, -0.0F, 0.0F);
/*  62 */     this.leftarm2 = new ModelRenderer((Model)this, 21, 50);
/*  63 */     this.leftarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  64 */     this.leftarm2.addBox(-0.9F, 6.9F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
/*  65 */     setRotateAngle(this.leftarm2, 0.0F, -0.0F, 0.017453292F);
/*  66 */     this.axe5 = new ModelRenderer((Model)this, 105, 52);
/*  67 */     this.axe5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  68 */     this.axe5.addBox(-2.0F, 12.1F, 2.5F, 2.0F, 2.0F, 4.0F, 0.0F);
/*  69 */     setRotateAngle(this.axe5, -0.41887903F, -0.0F, 0.0F);
/*  70 */     this.head = new ModelRenderer((Model)this, 0, 0);
/*  71 */     this.head.setRotationPoint(0.0F, -9.0F, 0.0F);
/*  72 */     this.head.addBox(-3.0F, -7.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F);
/*  73 */     this.leftleg = new ModelRenderer((Model)this, 0, 42);
/*  74 */     this.leftleg.setRotationPoint(2.5F, 7.0F, 0.0F);
/*  75 */     this.leftleg.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 17.0F, 5.0F, 0.0F);
/*  76 */     this.rightarm2 = new ModelRenderer((Model)this, 21, 14);
/*  77 */     this.rightarm2.mirror = true;
/*  78 */     this.rightarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  79 */     this.rightarm2.addBox(-4.0F, 3.2F, 3.9F, 5.0F, 7.0F, 5.0F, 0.0F);
/*  80 */     setRotateAngle(this.rightarm2, -0.9599311F, -0.0F, 0.0F);
/*  81 */     this.cable = new ModelRenderer((Model)this, 42, 47);
/*  82 */     this.cable.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  83 */     this.cable.addBox(-2.5F, 0.0F, 5.5F, 2.0F, 20.0F, 2.0F, 0.0F);
/*  84 */     setRotateAngle(this.cable, -0.9599311F, 0.0F, 0.0F);
/*  85 */     this.axe_hand = new ModelRenderer((Model)this, 0, 0);
/*  86 */     this.axe_hand.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  87 */     this.axe_hand.addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*  88 */     setRotateAngle(this.axe_hand, 0.9599311F, 0.0F, 0.0F);
/*  89 */     this.rightarm1 = new ModelRenderer((Model)this, 0, 14);
/*  90 */     this.rightarm1.setRotationPoint(-6.0F, -7.0F, 0.0F);
/*  91 */     this.rightarm1.addBox(-4.0F, -2.0F, -2.5F, 5.0F, 11.0F, 5.0F, 0.0F);
/*  92 */     this.axe1 = new ModelRenderer((Model)this, 51, 51);
/*  93 */     this.axe1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  94 */     this.axe1.addBox(-4.5F, 10.0F, 3.5F, 6.0F, 7.0F, 6.0F, 0.0F);
/*  95 */     setRotateAngle(this.axe1, -0.9599311F, -0.0F, 0.0F);
/*  96 */     this.axe6 = new ModelRenderer((Model)this, 105, 52);
/*  97 */     this.axe6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  98 */     this.axe6.addBox(-2.0F, 8.6F, 16.6F, 2.0F, 2.0F, 4.0F, 0.0F);
/*  99 */     setRotateAngle(this.axe6, -1.5184364F, -0.0F, 0.0F);
/* 100 */     this.rightleg = new ModelRenderer((Model)this, 0, 42);
/* 101 */     this.rightleg.setRotationPoint(-2.5F, 7.0F, 0.0F);
/* 102 */     this.rightleg.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 17.0F, 5.0F, 0.0F);
/* 103 */     this.axe4 = new ModelRenderer((Model)this, 89, 52);
/* 104 */     this.axe4.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 105 */     this.axe4.addBox(-2.0F, 9.0F, 10.5F, 2.0F, 9.0F, 3.0F, 0.0F);
/* 106 */     setRotateAngle(this.axe4, -0.9599311F, -0.0F, 0.0F);
/* 107 */     this.axe_hand.addChild(this.axe51);
/* 108 */     this.axe_hand.addChild(this.axe2);
/* 109 */     this.head.addChild(this.jaw);
/* 110 */     this.axe_hand.addChild(this.axe61);
/* 111 */     this.axe_hand.addChild(this.axe41);
/* 112 */     this.leftarm1.addChild(this.leftarm2);
/* 113 */     this.axe_hand.addChild(this.axe5);
/* 114 */     this.rightarm1.addChild(this.rightarm2);
/* 115 */     this.axe_hand.addChild(this.cable);
/* 116 */     this.rightarm2.addChild(this.axe_hand);
/* 117 */     this.axe_hand.addChild(this.axe1);
/* 118 */     this.axe_hand.addChild(this.axe6);
/* 119 */     this.axe_hand.addChild(this.axe4);
/*     */   }
/*     */   public ModelRenderer cable; public ModelRenderer axe1; public ModelRenderer axe2; public ModelRenderer axe4; public ModelRenderer axe41; public ModelRenderer axe5; public ModelRenderer axe51; public ModelRenderer axe6; public ModelRenderer axe61;
/*     */   public ModelRenderer leftarm2;
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 125 */     this.bipedLeftLeg.showModel = false;
/* 126 */     this.bipedRightLeg.showModel = false;
/* 127 */     this.bipedLeftArm.showModel = false;
/* 128 */     this.bipedRightArm.showModel = true;
/* 129 */     this.bipedHead.showModel = false;
/* 130 */     this.bipedHeadwear.showModel = false;
/* 131 */     this.bipedBody.showModel = false;
/*     */     
/* 133 */     this.bipedRightArm = this.rightarm1;
/* 134 */     this.bipedRightArm.rotationPointY = -7.0F;
/* 135 */     this.bipedRightArm.rotationPointX = -6.0F;
/*     */     
/* 137 */     this.leftarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 138 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 139 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 140 */     this.leftleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 141 */     this.rightarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 142 */     this.rightleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */     
/* 144 */     this.jaw.rotationPointZ = 0.01F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 150 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/* 152 */     this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
/* 153 */     this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.0F * limbSwingAmount;
/* 154 */     this.leftarm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 159 */     model.rotateAngleX = x;
/* 160 */     model.rotateAngleY = y;
/* 161 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\MorganModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */