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
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.KungFuDugongEntity;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class KungFuDugongModel<T extends KungFuDugongEntity> extends BipedModel<T> {
/*     */   public ModelRenderer head;
/*     */   public ModelRenderer headShell;
/*     */   public ModelRenderer body1;
/*     */   public ModelRenderer body2;
/*     */   public ModelRenderer bodyShell;
/*     */   public ModelRenderer rightArm;
/*     */   public ModelRenderer leftArm;
/*     */   public ModelRenderer snout;
/*     */   public ModelRenderer tail1;
/*     */   public ModelRenderer tail2;
/*     */   public ModelRenderer tail3;
/*     */   public ModelRenderer tail4;
/*     */   public ModelRenderer leftTail1;
/*     */   public ModelRenderer rightTail1;
/*     */   
/*     */   public KungFuDugongModel() {
/*  33 */     super(1.0F);
/*  34 */     this.textureWidth = 100;
/*  35 */     this.textureHeight = 50;
/*     */     
/*  37 */     this.bipedBody.showModel = false;
/*  38 */     this.bipedHead.showModel = false;
/*  39 */     this.bipedHeadwear.showModel = false;
/*  40 */     this.bipedLeftArm.showModel = false;
/*  41 */     this.bipedLeftLeg.showModel = false;
/*  42 */     this.bipedRightArm.showModel = false;
/*  43 */     this.bipedRightLeg.showModel = false;
/*     */     
/*  45 */     this.tail3 = new ModelRenderer((Model)this, 48, 16);
/*  46 */     this.tail3.setRotationPoint(0.0F, 3.0F, 0.0F);
/*  47 */     this.tail3.addBox(-2.0F, 0.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F);
/*  48 */     this.body2 = new ModelRenderer((Model)this, 21, 14);
/*  49 */     this.body2.setRotationPoint(0.0F, 18.0F, -0.2F);
/*  50 */     this.body2.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 6.0F, 4.0F, 0.0F);
/*  51 */     setRotateAngle(this.body2, 0.43633232F, -0.0F, 0.0F);
/*  52 */     this.rightTail1 = new ModelRenderer((Model)this, 48, 25);
/*  53 */     this.rightTail1.setRotationPoint(-0.5F, 0.0F, 0.0F);
/*  54 */     this.rightTail1.addBox(-3.0F, -2.0F, -0.5F, 3.0F, 2.0F, 1.0F, 0.0F);
/*  55 */     setRotateAngle(this.rightTail1, 0.0F, -0.2268928F, 0.34906584F);
/*  56 */     this.rightArm = new ModelRenderer((Model)this, 0, 24);
/*  57 */     this.rightArm.setRotationPoint(-4.5F, 12.3F, 0.0F);
/*  58 */     this.rightArm.addBox(-1.5F, -1.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F);
/*  59 */     this.leftArm = new ModelRenderer((Model)this, 0, 24);
/*  60 */     this.leftArm.setRotationPoint(4.5F, 12.3F, 0.0F);
/*  61 */     this.leftArm.addBox(-0.5F, -1.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, true);
/*  62 */     this.snout = new ModelRenderer((Model)this, 0, 20);
/*  63 */     this.snout.setRotationPoint(0.0F, 1.0F, -2.7F);
/*  64 */     this.snout.addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F);
/*  65 */     this.tail2 = new ModelRenderer((Model)this, 48, 9);
/*  66 */     this.tail2.setRotationPoint(0.0F, 3.0F, -0.5F);
/*  67 */     this.tail2.addBox(-2.5F, 0.0F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F);
/*  68 */     setRotateAngle(this.tail2, 0.6981317F, 0.0F, 0.0F);
/*  69 */     this.tail4 = new ModelRenderer((Model)this, 48, 21);
/*  70 */     this.tail4.setRotationPoint(0.0F, 1.7F, -0.1F);
/*  71 */     this.tail4.addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
/*  72 */     setRotateAngle(this.tail4, -2.9670596F, -0.0F, 0.0F);
/*  73 */     this.leftTail1 = new ModelRenderer((Model)this, 48, 25);
/*  74 */     this.leftTail1.setRotationPoint(0.4F, 0.0F, -0.5F);
/*  75 */     this.leftTail1.addBox(0.0F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F);
/*  76 */     setRotateAngle(this.leftTail1, 0.0F, 0.2268928F, -0.34906584F);
/*  77 */     this.body1 = new ModelRenderer((Model)this, 21, 0);
/*  78 */     this.body1.setRotationPoint(0.0F, 11.0F, 0.0F);
/*  79 */     this.body1.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 8.0F, 5.0F, 0.0F);
/*  80 */     this.head = new ModelRenderer((Model)this, 0, 9);
/*  81 */     this.head.setRotationPoint(0.0F, 8.5F, -0.2F);
/*  82 */     this.head.addBox(-2.5F, -2.5F, -2.6F, 5.0F, 5.0F, 5.0F, 0.0F);
/*  83 */     this.tail1 = new ModelRenderer((Model)this, 48, 0);
/*  84 */     this.tail1.setRotationPoint(0.0F, 4.2F, -1.0F);
/*  85 */     this.tail1.addBox(-3.0F, 0.0F, -2.0F, 6.0F, 4.0F, 4.0F, 0.0F);
/*  86 */     setRotateAngle(this.tail1, 1.134464F, -0.0F, 0.0F);
/*  87 */     this.bodyShell = new ModelRenderer((Model)this, 21, 25);
/*  88 */     this.bodyShell.setRotationPoint(0.0F, 11.0F, 2.0F);
/*  89 */     this.bodyShell.addBox(-4.5F, -0.5F, -1.5F, 9.0F, 9.0F, 3.0F, 0.0F);
/*  90 */     this.headShell = new ModelRenderer((Model)this, 0, 0);
/*  91 */     this.headShell.setRotationPoint(0.0F, 8.2F, -0.5F);
/*  92 */     this.headShell.addBox(-3.0F, -2.5F, 0.0F, 6.0F, 5.0F, 3.0F, 0.0F);
/*  93 */     this.tail2.addChild(this.tail3);
/*  94 */     this.tail4.addChild(this.rightTail1);
/*  95 */     this.head.addChild(this.snout);
/*  96 */     this.tail1.addChild(this.tail2);
/*  97 */     this.tail3.addChild(this.tail4);
/*  98 */     this.tail4.addChild(this.leftTail1);
/*  99 */     this.body2.addChild(this.tail1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 105 */     this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 106 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 107 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 108 */     this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 109 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 110 */     this.bodyShell.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 111 */     this.headShell.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 118 */     if (entity.isHappy()) {
/*     */       
/* 120 */       this.tail2.rotateAngleX = 0.4F * (0.7F + MathHelper.cos(ageInTicks * 0.4F));
/* 121 */       this.tail3.rotateAngleX = 0.6F * this.tail2.rotateAngleX;
/*     */     }
/*     */     else {
/*     */       
/* 125 */       this.tail2.rotateAngleX = 0.69F;
/* 126 */       this.tail3.rotateAngleX = (float)Math.toRadians(0.0D);
/*     */       
/* 128 */       this.tail2.rotateAngleZ = MathHelper.cos(limbSwing * 0.4F + 3.1415927F) * 2.0F * limbSwingAmount * 0.3F;
/* 129 */       this.tail3.rotateAngleZ = MathHelper.cos(limbSwing * 0.4F + 3.1415927F) * 2.0F * limbSwingAmount * 0.3F;
/*     */     } 
/*     */ 
/*     */     
/* 133 */     if (entity.isTraining()) {
/*     */       
/* 135 */       float rightArmMovement = MathHelper.cos(ageInTicks * 1.2F);
/* 136 */       float leftArmMovement = -MathHelper.cos(ageInTicks * 1.2F);
/*     */       
/* 138 */       if (leftArmMovement >= 0.4D || leftArmMovement <= -0.4D) {
/*     */         
/* 140 */         this.rightArm.rotateAngleX = (float)Math.toRadians(-90.0D);
/* 141 */         this.rightArm.rotationPointZ = 0.1F * (0.2F - rightArmMovement);
/*     */       } 
/*     */       
/* 144 */       if (rightArmMovement >= 0.4D || rightArmMovement <= -0.4D)
/*     */       {
/* 146 */         this.leftArm.rotateAngleX = (float)Math.toRadians(-90.0D);
/* 147 */         this.leftArm.rotationPointZ = 0.1F * (0.2F - leftArmMovement);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 152 */       this.rightArm.rotationPointZ = 0.0F;
/* 153 */       this.leftArm.rotationPointZ = 0.0F;
/*     */       
/* 155 */       this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F;
/* 156 */       this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 162 */     model.rotateAngleX = x;
/* 163 */     model.rotateAngleY = y;
/* 164 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\KungFuDugongModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */