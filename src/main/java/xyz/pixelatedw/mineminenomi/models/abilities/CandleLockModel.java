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
/*    */ public class CandleLockModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer candleLock1;
/*    */   public ModelRenderer candleLock2;
/*    */   public ModelRenderer candleLock3;
/*    */   
/*    */   public CandleLockModel() {
/* 21 */     this.textureWidth = 64;
/* 22 */     this.textureHeight = 64;
/* 23 */     this.candleLock3 = new ModelRenderer((Model)this, 0, 17);
/* 24 */     this.candleLock3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 25 */     this.candleLock3.addBox(7.5F, -4.0F, -4.0F, 4.0F, 8.0F, 8.0F, 0.0F);
/* 26 */     this.candleLock1 = new ModelRenderer((Model)this, 0, 0);
/* 27 */     this.candleLock1.setRotationPoint(0.0F, 18.9F, 0.0F);
/* 28 */     this.candleLock1.addBox(-7.5F, -3.5F, -3.5F, 15.0F, 7.0F, 7.0F, 0.0F);
/* 29 */     this.candleLock2 = new ModelRenderer((Model)this, 0, 36);
/* 30 */     this.candleLock2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 31 */     this.candleLock2.addBox(-11.5F, -4.0F, -4.0F, 4.0F, 8.0F, 8.0F, 0.0F);
/* 32 */     this.candleLock1.addChild(this.candleLock3);
/* 33 */     this.candleLock1.addChild(this.candleLock2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 39 */     this.candleLock1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
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
/* 50 */     model.rotateAngleX = x;
/* 51 */     model.rotateAngleY = y;
/* 52 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\abilities\CandleLockModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */