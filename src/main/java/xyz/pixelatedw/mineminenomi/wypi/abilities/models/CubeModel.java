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
/*    */ public class CubeModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer shape1;
/*    */   
/*    */   public CubeModel() {
/* 19 */     this.textureWidth = 64;
/* 20 */     this.textureHeight = 64;
/*    */     
/* 22 */     this.shape1 = new ModelRenderer((Model)this, 0, 0);
/* 23 */     this.shape1.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F);
/* 24 */     this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 25 */     this.shape1.setTextureSize(64, 64);
/* 26 */     this.shape1.mirror = true;
/* 27 */     setRotation(this.shape1, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 39 */     this.shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 44 */     model.rotateAngleX = x;
/* 45 */     model.rotateAngleY = y;
/* 46 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\models\CubeModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */