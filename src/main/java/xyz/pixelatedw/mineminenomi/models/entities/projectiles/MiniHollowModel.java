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
/*    */ public class MiniHollowModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer rightarm;
/*    */   public ModelRenderer leftarm;
/*    */   public ModelRenderer rightleg;
/*    */   public ModelRenderer rightleg_1;
/*    */   
/*    */   public MiniHollowModel() {
/* 24 */     this.textureWidth = 64;
/* 25 */     this.textureHeight = 32;
/* 26 */     this.rightleg_1 = new ModelRenderer((Model)this, 19, 0);
/* 27 */     this.rightleg_1.setRotationPoint(0.6F, 10.1F, 0.5F);
/* 28 */     this.rightleg_1.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 29 */     setRotateAngle(this.rightleg_1, 0.0F, -0.0F, -0.61086524F);
/* 30 */     this.rightarm = new ModelRenderer((Model)this, 14, 0);
/* 31 */     this.rightarm.setRotationPoint(0.5F, 7.7F, 0.5F);
/* 32 */     this.rightarm.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 33 */     setRotateAngle(this.rightarm, 0.0F, -0.0F, 0.9424778F);
/* 34 */     this.body = new ModelRenderer((Model)this, 9, 0);
/* 35 */     this.body.setRotationPoint(0.5F, 8.0F, 0.5F);
/* 36 */     this.body.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 37 */     this.head = new ModelRenderer((Model)this, 0, 0);
/* 38 */     this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 39 */     this.head.addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 40 */     this.rightleg = new ModelRenderer((Model)this, 19, 0);
/* 41 */     this.rightleg.setRotationPoint(0.6F, 9.5F, 0.5F);
/* 42 */     this.rightleg.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F);
/* 43 */     setRotateAngle(this.rightleg, 0.0F, -0.0F, 0.61086524F);
/* 44 */     this.leftarm = new ModelRenderer((Model)this, 14, 0);
/* 45 */     this.leftarm.setRotationPoint(1.0F, 8.5F, 0.5F);
/* 46 */     this.leftarm.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
/* 47 */     setRotateAngle(this.leftarm, 0.0F, -0.0F, -0.9424778F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 53 */     this.rightleg_1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 54 */     this.rightarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 55 */     this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 56 */     this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 57 */     this.rightleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 58 */     this.leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
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
/* 69 */     model.rotateAngleX = x;
/* 70 */     model.rotateAngleY = y;
/* 71 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\MiniHollowModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */