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
/*    */ public class StrawHatModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   private final ModelRenderer base;
/*    */   private final ModelRenderer middle;
/*    */   private final ModelRenderer top;
/*    */   
/*    */   public StrawHatModel() {
/* 18 */     super(1.0F);
/* 19 */     this.textureWidth = 64;
/* 20 */     this.textureHeight = 64;
/*    */     
/* 22 */     this.base = new ModelRenderer((Model)this);
/* 23 */     this.base.setRotationPoint(0.0F, 30.0F, 0.0F);
/* 24 */     this.base.setTextureOffset(0, 0).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
/*    */     
/* 26 */     this.middle = new ModelRenderer((Model)this);
/* 27 */     this.middle.setRotationPoint(0.0F, -7.0F, 0.0F);
/* 28 */     this.base.addChild(this.middle);
/* 29 */     this.middle.setTextureOffset(0, 17).addBox(-5.0F, -2.25F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);
/*    */     
/* 31 */     this.top = new ModelRenderer((Model)this);
/* 32 */     this.top.setRotationPoint(0.0F, -3.0F, 0.0F);
/* 33 */     this.middle.addChild(this.top);
/* 34 */     this.top.setTextureOffset(0, 32).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 40 */     this.base.copyModelAngles(this.bipedHead);
/* 41 */     this.base.render(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 47 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 52 */     modelRenderer.rotateAngleX = x;
/* 53 */     modelRenderer.rotateAngleY = y;
/* 54 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\armors\StrawHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */