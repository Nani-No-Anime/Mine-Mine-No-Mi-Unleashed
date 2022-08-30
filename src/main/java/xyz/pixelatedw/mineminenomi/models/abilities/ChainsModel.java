/*    */ package xyz.pixelatedw.mineminenomi.models.abilities;
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
/*    */ public class ChainsModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer shape15;
/*    */   
/*    */   public ChainsModel() {
/* 19 */     this.textureWidth = 64;
/* 20 */     this.textureHeight = 64;
/* 21 */     this.shape15 = new ModelRenderer((Model)this, 0, 0);
/* 22 */     this.shape15.setRotationPoint(0.0F, 5.8F, 0.0F);
/* 23 */     this.shape15.addBox(-9.0F, 0.0F, -6.0F, 18.0F, 5.0F, 12.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 29 */     this.shape15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 40 */     model.rotateAngleX = x;
/* 41 */     model.rotateAngleY = y;
/* 42 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\abilities\ChainsModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */