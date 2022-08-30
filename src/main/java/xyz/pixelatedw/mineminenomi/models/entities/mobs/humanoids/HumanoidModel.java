/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class HumanoidModel<T extends OPEntity>
/*     */   extends BipedModel<T> {
/*  22 */   protected float animationTimer = 0.0F;
/*  23 */   public HumanoidArmPose armsPose = HumanoidArmPose.EMPTY;
/*     */ 
/*     */   
/*     */   public HumanoidModel() {
/*  27 */     super(0.0F, 0.0F, 64, 64);
/*  28 */     this.bipedHeadwear.showModel = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/*  34 */     super.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  40 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/*  42 */     if (this.armsPose == HumanoidArmPose.SENRIKU_HOLDING) {
/*     */       
/*  44 */       (getArmForSide(HandSide.LEFT)).rotateAngleX = (getArmForSide(HandSide.LEFT)).rotateAngleX * 0.5F - 0.31415927F;
/*  45 */       (getArmForSide(HandSide.LEFT)).rotateAngleY = 0.0F;
/*     */     }
/*  47 */     else if (this.armsPose == HumanoidArmPose.SENRIKU_POINTING) {
/*     */       
/*  49 */       this.bipedRightArm.rotateAngleY = 0.3F + this.bipedHead.rotateAngleY - 0.4F;
/*  50 */       this.bipedLeftArm.rotateAngleY = 0.8F + this.bipedHead.rotateAngleY;
/*  51 */       this.bipedRightArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
/*  52 */       this.bipedLeftArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
/*     */     }
/*  54 */     else if (this.armsPose == HumanoidArmPose.FLINTLOCK_POINTING) {
/*     */       
/*  56 */       HandSide side = (entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == ModWeapons.FLINTLOCK) ? HandSide.RIGHT : HandSide.LEFT;
/*  57 */       HandSide opposide = (entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == ModWeapons.FLINTLOCK) ? HandSide.LEFT : HandSide.RIGHT;
/*  58 */       (getArmForSide(side)).rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
/*  59 */       (getArmForSide(opposide)).rotateAngleY = 0.5F + this.bipedHead.rotateAngleY;
/*  60 */       (getArmForSide(side)).rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
/*  61 */       (getArmForSide(opposide)).rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
/*     */     }
/*  63 */     else if (this.armsPose == HumanoidArmPose.CANNON_HOLDING) {
/*     */       
/*  65 */       this.bipedRightArm.rotateAngleY = 1.2F;
/*  66 */       this.bipedLeftArm.rotateAngleY = 0.8F;
/*  67 */       this.bipedRightArm.rotateAngleX = -2.243995F;
/*  68 */       this.bipedLeftArm.rotateAngleX = -1.7453294F;
/*     */     } 
/*     */     
/*  71 */     if (Minecraft.getInstance().isGamePaused()) {
/*     */       return;
/*     */     }
/*  74 */     if (entity.getAnimation() == OPEntity.Animation.CLEAVE_ATTACK.getId()) {
/*     */       
/*  76 */       if (this.animationTimer < 5.0F) {
/*  77 */         this.animationTimer = (float)(this.animationTimer + 0.15D);
/*     */       }
/*  79 */       this.bipedRightArm.rotateAngleX = -1.3F + (float)Math.sin((this.animationTimer / 1.0F));
/*  80 */       this.bipedRightArm.rotateAngleY = -0.3F;
/*  81 */       this.bipedRightArm.rotateAngleZ = 1.5F;
/*     */     }
/*  83 */     else if (entity.getAnimation() == OPEntity.Animation.SHOCKWAVE.getId()) {
/*     */       
/*  85 */       if (this.animationTimer < 3.0F) {
/*  86 */         this.animationTimer = (float)(this.animationTimer + 0.1D);
/*     */       }
/*  88 */       this.bipedRightArm.rotateAngleX = -1.5F - (float)Math.sin((this.animationTimer * 2.0F)) * 2.0F;
/*  89 */       this.bipedRightArm.rotateAngleY = 0.0F;
/*  90 */       this.bipedRightArm.rotateAngleZ = 0.0F;
/*     */     }
/*  92 */     else if (entity.getAnimation() <= OPEntity.Animation.NONE.getId()) {
/*     */       
/*  94 */       this.animationTimer = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 101 */     super.translateHand(side, matrixStack);
/* 102 */     if (this.armsPose == HumanoidArmPose.FLINTLOCK_POINTING) {
/*     */       
/* 104 */       matrixStack.translate(0.3D, 0.15D, 0.0D);
/* 105 */       matrixStack.rotate(new Quaternion(Vector3f.ZP, 20.0F, true));
/*     */     }
/* 107 */     else if (this.armsPose == HumanoidArmPose.SENRIKU_HOLDING) {
/*     */       
/* 109 */       matrixStack.translate(-0.3D, 0.7D, 0.0D);
/* 110 */       matrixStack.rotate(new Quaternion(Vector3f.YP, -75.0F, true));
/* 111 */       matrixStack.rotate(new Quaternion(Vector3f.XP, -90.0F, true));
/*     */     }
/* 113 */     else if (this.armsPose == HumanoidArmPose.SENRIKU_POINTING) {
/*     */       
/* 115 */       matrixStack.translate(0.05D, 0.15D, 0.0D);
/* 116 */       matrixStack.rotate(new Quaternion(Vector3f.XP, 5.0F, true));
/*     */     }
/* 118 */     else if (this.armsPose == HumanoidArmPose.CANNON_HOLDING) {
/*     */       
/* 120 */       matrixStack.translate(-0.1D, -0.15D, -0.25D);
/* 121 */       matrixStack.rotate(new Quaternion(Vector3f.XP, 35.0F, true));
/* 122 */       matrixStack.rotate(new Quaternion(Vector3f.ZP, -75.0F, true));
/* 123 */       matrixStack.scale(1.25F, 1.25F, 1.25F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 129 */     modelRenderer.rotateAngleX = x;
/* 130 */     modelRenderer.rotateAngleY = y;
/* 131 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public enum HumanoidArmPose
/*     */   {
/* 137 */     EMPTY,
/* 138 */     FLINTLOCK_POINTING,
/* 139 */     SENRIKU_HOLDING,
/* 140 */     SENRIKU_POINTING,
/* 141 */     CANNON_HOLDING;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\HumanoidModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */