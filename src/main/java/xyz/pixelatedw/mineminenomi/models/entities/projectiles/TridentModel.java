/*    */ package xyz.pixelatedw.mineminenomi.models.entities.projectiles;
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
/*    */ public class TridentModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer spearCable;
/*    */   public ModelRenderer spear1;
/*    */   public ModelRenderer spear2;
/*    */   public ModelRenderer spear3;
/*    */   public ModelRenderer spear4;
/*    */   public ModelRenderer spear5;
/*    */   public ModelRenderer spear6;
/*    */   public ModelRenderer spear7;
/*    */   public ModelRenderer spear8;
/*    */   public ModelRenderer spear9;
/*    */   
/*    */   public TridentModel() {
/* 28 */     this.textureWidth = 128;
/* 29 */     this.textureHeight = 32;
/* 30 */     this.spear2 = new ModelRenderer((Model)this, 0, 5);
/* 31 */     this.spear2.setRotationPoint(0.0F, 9.5F, -7.0F);
/* 32 */     this.spear2.addBox(-3.0F, 0.0F, 0.0F, 6.0F, 1.0F, 2.0F, 0.0F);
/* 33 */     this.spear6 = new ModelRenderer((Model)this, 13, 19);
/* 34 */     this.spear6.setRotationPoint(-2.0F, 9.5F, -7.0F);
/* 35 */     this.spear6.addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 36 */     setRotateAngle(this.spear6, 3.1415927F, 1.5707963F, 3.1415927F);
/* 37 */     this.spear4 = new ModelRenderer((Model)this, 0, 15);
/* 38 */     this.spear4.setRotationPoint(0.9F, 9.5F, -6.0F);
/* 39 */     this.spear4.addBox(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 5.0F, 0.0F);
/* 40 */     setRotateAngle(this.spear4, 0.0F, 0.17453292F, 0.0F);
/* 41 */     this.spear7 = new ModelRenderer((Model)this, 18, 19);
/* 42 */     this.spear7.setRotationPoint(-3.0F, 9.5F, -8.0F);
/* 43 */     this.spear7.addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F);
/* 44 */     setRotateAngle(this.spear7, 0.0F, 1.2217305F, 0.0F);
/* 45 */     this.spear9 = new ModelRenderer((Model)this, 18, 22);
/* 46 */     this.spear9.setRotationPoint(3.0F, 9.5F, -8.0F);
/* 47 */     this.spear9.addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F);
/* 48 */     setRotateAngle(this.spear9, 0.0F, -1.2217305F, 0.0F);
/* 49 */     this.spearCable = new ModelRenderer((Model)this, 0, 0);
/* 50 */     this.spearCable.setRotationPoint(0.0F, 10.0F, -5.0F);
/* 51 */     this.spearCable.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 30.0F, 0.0F);
/* 52 */     this.spear5 = new ModelRenderer((Model)this, 0, 22);
/* 53 */     this.spear5.setRotationPoint(-0.9F, 9.5F, -6.0F);
/* 54 */     this.spear5.addBox(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 5.0F, 0.0F);
/* 55 */     setRotateAngle(this.spear5, 0.0F, -0.17453292F, 0.0F);
/* 56 */     this.spear1 = new ModelRenderer((Model)this, 0, 0);
/* 57 */     this.spear1.setRotationPoint(0.0F, 10.0F, -3.0F);
/* 58 */     this.spear1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 59 */     this.spear8 = new ModelRenderer((Model)this, 13, 22);
/* 60 */     this.spear8.setRotationPoint(2.0F, 9.5F, -7.0F);
/* 61 */     this.spear8.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 62 */     setRotateAngle(this.spear8, 3.1415927F, 1.5707963F, 3.1415927F);
/* 63 */     this.spear3 = new ModelRenderer((Model)this, 0, 9);
/* 64 */     this.spear3.setRotationPoint(0.0F, 9.5F, -7.0F);
/* 65 */     this.spear3.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 1.0F, 4.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 76 */     model.rotateAngleX = x;
/* 77 */     model.rotateAngleY = y;
/* 78 */     model.rotateAngleZ = z;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 84 */     this.spear2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 85 */     this.spear6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 86 */     this.spear4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 87 */     this.spear7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 88 */     this.spear9.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 89 */     this.spearCable.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 90 */     this.spear5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 91 */     this.spear1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 92 */     this.spear8.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 93 */     this.spear3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\TridentModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */