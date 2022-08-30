/*    */ package xyz.pixelatedw.mineminenomi.models.armors;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ public class ChoppersHatModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   public ModelRenderer base;
/*    */   public ModelRenderer base_1;
/*    */   
/*    */   public ChoppersHatModel() {
/* 17 */     super(1.0F);
/* 18 */     this.textureWidth = 64;
/* 19 */     this.textureHeight = 32;
/*    */     
/* 21 */     this.base = new ModelRenderer((Model)this);
/* 22 */     this.base.setRotationPoint(0.0F, -1.0F, 0.0F);
/* 23 */     this.base.setTextureOffset(0, 18).addBox(-5.5F, -8.0F, -5.5F, 11.0F, 1.0F, 11.0F, 0.0F, false);
/*    */     
/* 25 */     this.base_1 = new ModelRenderer((Model)this);
/* 26 */     this.base_1.setRotationPoint(0.0F, -8.0F, 0.0F);
/* 27 */     this.base.addChild(this.base_1);
/* 28 */     this.base_1.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 34 */     this.base.copyModelAngles(this.bipedHead);
/* 35 */     this.base.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 41 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 46 */     model.rotateAngleX = x;
/* 47 */     model.rotateAngleY = y;
/* 48 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\armors\ChoppersHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */