/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities.models;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class EntityArmModel
/*    */   extends EntityModel
/*    */ {
/*    */   public final ModelRenderer entityArm;
/*    */   
/*    */   public EntityArmModel() {
/* 19 */     this(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityArmModel(boolean smallArms) {
/* 24 */     this.textureWidth = 64;
/* 25 */     this.textureHeight = 64;
/*    */     
/* 27 */     this.entityArm = new ModelRenderer((Model)this);
/* 28 */     this.entityArm.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 29 */     setRotationAngle(this.entityArm, -1.5708F, 0.0F, 0.0F);
/* 30 */     if (smallArms) {
/* 31 */       this.entityArm.setTextureOffset(40, 16).addBox(-1.0F, -6.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, true);
/*    */     } else {
/* 33 */       this.entityArm.setTextureOffset(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 39 */     this.entityArm.render(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 49 */     modelRenderer.rotateAngleX = x;
/* 50 */     modelRenderer.rotateAngleY = y;
/* 51 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\models\EntityArmModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */