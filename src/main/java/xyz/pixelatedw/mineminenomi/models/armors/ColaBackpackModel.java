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
/*    */ public class ColaBackpackModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   public ModelRenderer base;
/*    */   public ModelRenderer leftCola;
/*    */   public ModelRenderer rightCola;
/*    */   public ModelRenderer leftColaCap;
/*    */   public ModelRenderer rightColaCap;
/*    */   
/*    */   public ColaBackpackModel() {
/* 20 */     super(1.0F);
/* 21 */     this.textureWidth = 16;
/* 22 */     this.textureHeight = 16;
/* 23 */     this.leftColaCap = new ModelRenderer((Model)this, 0, 14);
/* 24 */     this.leftColaCap.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 25 */     this.leftColaCap.addBox(1.5F, 1.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 26 */     this.rightCola = new ModelRenderer((Model)this, 0, 0);
/* 27 */     this.rightCola.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 28 */     this.rightCola.addBox(-3.5F, 1.5F, 2.0F, 3.0F, 7.0F, 3.0F, 0.0F);
/* 29 */     this.leftCola = new ModelRenderer((Model)this, 0, 0);
/* 30 */     this.leftCola.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 31 */     this.leftCola.addBox(0.5F, 1.5F, 2.0F, 3.0F, 7.0F, 3.0F, 0.0F);
/* 32 */     this.rightColaCap = new ModelRenderer((Model)this, 0, 14);
/* 33 */     this.rightColaCap.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 34 */     this.rightColaCap.addBox(-2.5F, 1.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 35 */     this.base = new ModelRenderer((Model)this, 5, 12);
/* 36 */     this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 37 */     this.base.addBox(-0.5F, 3.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F);
/* 38 */     this.leftCola.addChild(this.leftColaCap);
/* 39 */     this.base.addChild(this.rightCola);
/* 40 */     this.base.addChild(this.leftCola);
/* 41 */     this.rightCola.addChild(this.rightColaCap);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 47 */     this.base.copyModelAngles(this.bipedBody);
/* 48 */     this.base.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 54 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 59 */     model.rotateAngleX = x;
/* 60 */     model.rotateAngleY = y;
/* 61 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\armors\ColaBackpackModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */