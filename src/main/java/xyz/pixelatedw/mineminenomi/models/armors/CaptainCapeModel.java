/*     */ package xyz.pixelatedw.mineminenomi.models.armors;
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
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class CaptainCapeModel<T extends LivingEntity>
/*     */   extends BipedModel<T> {
/*     */   public ModelRenderer cape;
/*     */   public ModelRenderer capeback;
/*     */   private ModelRenderer capeleftsholderpad2;
/*     */   private ModelRenderer capeleftsholderpad1;
/*     */   private ModelRenderer capeleftarm;
/*     */   private ModelRenderer capeleft;
/*     */   private ModelRenderer capefrontleft;
/*     */   private ModelRenderer caperightsholderpad2;
/*     */   private ModelRenderer caperightarm;
/*     */   private ModelRenderer caperightsholderpad1;
/*     */   private ModelRenderer caperight;
/*     */   private ModelRenderer capefrontright;
/*     */   private ModelRenderer caperightsholder;
/*     */   private ModelRenderer capeleftsholder;
/*     */   private ModelRenderer capeleftcollar1;
/*     */   private ModelRenderer capeleftcollar2;
/*     */   private ModelRenderer caperightcollar1;
/*     */   private ModelRenderer caperightcollar2;
/*     */   
/*     */   public CaptainCapeModel() {
/*  37 */     super(1.0F);
/*  38 */     this.textureWidth = 128;
/*  39 */     this.textureHeight = 128;
/*     */     
/*  41 */     this.cape = new ModelRenderer((Model)this);
/*  42 */     this.cape.setRotationPoint(0.0F, 0.5F, 0.0F);
/*     */     
/*  44 */     this.capeback = new ModelRenderer((Model)this);
/*  45 */     this.capeback.setRotationPoint(-0.5F, -0.5F, 2.5F);
/*  46 */     this.cape.addChild(this.capeback);
/*  47 */     this.capeback.setTextureOffset(5, 75).addBox(-8.0F, 0.0F, 0.0F, 17.0F, 22.0F, 0.0F, 0.0F, false);
/*     */     
/*  49 */     this.capeleftsholderpad2 = new ModelRenderer((Model)this);
/*  50 */     this.capeleftsholderpad2.setRotationPoint(6.4F, 0.0F, 0.0F);
/*  51 */     this.capeback.addChild(this.capeleftsholderpad2);
/*  52 */     this.capeleftsholderpad2.setTextureOffset(5, 106).addBox(0.0F, 0.0F, -5.5F, 5.0F, 3.0F, 6.0F, 0.0F, false);
/*     */     
/*  54 */     this.capeleftsholderpad1 = new ModelRenderer((Model)this);
/*  55 */     this.capeleftsholderpad1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  56 */     this.capeleftsholderpad2.addChild(this.capeleftsholderpad1);
/*  57 */     setRotateAngle(this.capeleftsholderpad1, 0.0F, 0.0F, 0.1745F);
/*  58 */     this.capeleftsholderpad1.setTextureOffset(5, 98).addBox(0.0F, -1.0F, -5.5F, 5.0F, 1.0F, 6.0F, 0.0F, false);
/*     */     
/*  60 */     this.capeleftarm = new ModelRenderer((Model)this);
/*  61 */     this.capeleftarm.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  62 */     this.capeleftsholderpad2.addChild(this.capeleftarm);
/*  63 */     this.capeleftarm.setTextureOffset(35, 98).addBox(2.6F, 1.0F, -4.5F, 2.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  65 */     this.capeleft = new ModelRenderer((Model)this);
/*  66 */     this.capeleft.setRotationPoint(9.0F, 0.0F, 0.0F);
/*  67 */     this.capeback.addChild(this.capeleft);
/*  68 */     this.capeleft.setTextureOffset(40, 70).addBox(0.0F, 0.0F, -5.0F, 0.0F, 22.0F, 5.0F, 0.0F, false);
/*     */     
/*  70 */     this.capefrontleft = new ModelRenderer((Model)this);
/*  71 */     this.capefrontleft.setRotationPoint(0.0F, 0.0F, -5.0F);
/*  72 */     this.capeleft.addChild(this.capefrontleft);
/*  73 */     this.capefrontleft.setTextureOffset(28, 98).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 22.0F, 0.0F, 0.0F, false);
/*     */     
/*  75 */     this.caperightsholderpad2 = new ModelRenderer((Model)this);
/*  76 */     this.caperightsholderpad2.setRotationPoint(-6.4F, 0.0F, 0.0F);
/*  77 */     this.capeback.addChild(this.caperightsholderpad2);
/*  78 */     this.caperightsholderpad2.setTextureOffset(5, 106).addBox(-4.0F, 0.0F, -5.5F, 5.0F, 3.0F, 6.0F, 0.0F, false);
/*     */     
/*  80 */     this.caperightarm = new ModelRenderer((Model)this);
/*  81 */     this.caperightarm.setRotationPoint(0.5F, 0.0F, -2.5F);
/*  82 */     this.caperightsholderpad2.addChild(this.caperightarm);
/*  83 */     this.caperightarm.setTextureOffset(35, 98).addBox(-4.1F, 1.0F, -2.0F, 2.0F, 12.0F, 4.0F, 0.0F, false);
/*     */     
/*  85 */     this.caperightsholderpad1 = new ModelRenderer((Model)this);
/*  86 */     this.caperightsholderpad1.setRotationPoint(0.5F, 0.0F, -2.5F);
/*  87 */     this.caperightsholderpad2.addChild(this.caperightsholderpad1);
/*  88 */     setRotateAngle(this.caperightsholderpad1, 0.0F, 0.0F, -0.1745F);
/*  89 */     this.caperightsholderpad1.setTextureOffset(5, 98).addBox(-4.0F, -0.8F, -3.0F, 5.0F, 1.0F, 6.0F, 0.0F, false);
/*     */     
/*  91 */     this.caperight = new ModelRenderer((Model)this);
/*  92 */     this.caperight.setRotationPoint(-8.0F, 0.0F, 0.0F);
/*  93 */     this.capeback.addChild(this.caperight);
/*  94 */     this.caperight.setTextureOffset(40, 70).addBox(0.0F, 0.0F, -5.0F, 0.0F, 22.0F, 5.0F, 0.0F, false);
/*     */     
/*  96 */     this.capefrontright = new ModelRenderer((Model)this);
/*  97 */     this.capefrontright.setRotationPoint(0.0F, 0.0F, -5.0F);
/*  98 */     this.caperight.addChild(this.capefrontright);
/*  99 */     this.capefrontright.setTextureOffset(28, 98).addBox(0.0F, 0.0F, 0.0F, 3.0F, 22.0F, 0.0F, 0.0F, false);
/*     */     
/* 101 */     this.caperightsholder = new ModelRenderer((Model)this);
/* 102 */     this.caperightsholder.setRotationPoint(-3.5F, -0.51F, -2.5F);
/* 103 */     this.cape.addChild(this.caperightsholder);
/* 104 */     this.caperightsholder.setTextureOffset(51, 75).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);
/*     */     
/* 106 */     this.capeleftsholder = new ModelRenderer((Model)this);
/* 107 */     this.capeleftsholder.setRotationPoint(3.5F, -0.51F, -2.5F);
/* 108 */     this.cape.addChild(this.capeleftsholder);
/* 109 */     this.capeleftsholder.setTextureOffset(51, 75).addBox(0.0F, 0.0F, 0.0F, 6.0F, 0.0F, 5.0F, 0.0F, false);
/*     */     
/* 111 */     this.capeleftcollar1 = new ModelRenderer((Model)this);
/* 112 */     this.capeleftcollar1.setRotationPoint(5.2F, -3.5F, -2.3F);
/* 113 */     this.cape.addChild(this.capeleftcollar1);
/* 114 */     setRotateAngle(this.capeleftcollar1, -0.0169F, -0.1913F, 0.0888F);
/* 115 */     this.capeleftcollar1.setTextureOffset(51, 81).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 117 */     this.capeleftcollar2 = new ModelRenderer((Model)this);
/* 118 */     this.capeleftcollar2.setRotationPoint(5.2F, -3.5F, -2.3F);
/* 119 */     this.cape.addChild(this.capeleftcollar2);
/* 120 */     setRotateAngle(this.capeleftcollar2, 0.0202F, -0.1909F, -0.1066F);
/* 121 */     this.capeleftcollar2.setTextureOffset(51, 90).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 123 */     this.caperightcollar1 = new ModelRenderer((Model)this);
/* 124 */     this.caperightcollar1.setRotationPoint(-5.2F, -3.5F, -2.3F);
/* 125 */     this.cape.addChild(this.caperightcollar1);
/* 126 */     setRotateAngle(this.caperightcollar1, -0.0169F, 0.1913F, -0.0888F);
/* 127 */     this.caperightcollar1.setTextureOffset(51, 81).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, false);
/*     */     
/* 129 */     this.caperightcollar2 = new ModelRenderer((Model)this);
/* 130 */     this.caperightcollar2.setRotationPoint(-5.2F, -3.5F, -2.3F);
/* 131 */     this.cape.addChild(this.caperightcollar2);
/* 132 */     setRotateAngle(this.caperightcollar2, 0.0202F, 0.1909F, 0.1066F);
/* 133 */     this.caperightcollar2.setTextureOffset(51, 90).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 139 */     this.cape.copyModelAngles(this.bipedBody);
/*     */     
/* 141 */     this.cape.rotationPointZ = 0.2F;
/*     */     
/* 143 */     this.cape.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 149 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 150 */     double dist = entity.getDistanceSq(((LivingEntity)entity).prevPosX, ((LivingEntity)entity).prevPosY, ((LivingEntity)entity).prevPosZ);
/* 151 */     if (dist > 0.0D && dist <= 0.02D)
/* 152 */       dist += 0.02D; 
/* 153 */     double angle = MathHelper.clamp(dist * 1000.0D - 1.0D, 0.0D, 70.0D);
/* 154 */     boolean isMoving = (dist > 0.02D);
/* 155 */     if (isMoving)
/* 156 */       angle += (MathHelper.sin(MathHelper.lerp(limbSwing, ((LivingEntity)entity).prevDistanceWalkedModified, ((LivingEntity)entity).distanceWalkedModified)) * 4.0F); 
/* 157 */     this.capeback.rotateAngleX = (float)Math.toRadians(angle);
/* 158 */     this.caperightsholderpad2.rotateAngleX = (float)Math.toRadians(-angle);
/* 159 */     this.capeleftsholderpad2.rotateAngleX = (float)Math.toRadians(-angle);
/* 160 */     this.caperightarm.rotateAngleX = (float)Math.toRadians((float)(angle - (!isMoving ? 0 : 20)));
/* 161 */     this.capeleftarm.rotateAngleX = (float)Math.toRadians((float)(angle - (!isMoving ? 0 : 20)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 166 */     model.rotateAngleX = x;
/* 167 */     model.rotateAngleY = y;
/* 168 */     model.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\armors\CaptainCapeModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */