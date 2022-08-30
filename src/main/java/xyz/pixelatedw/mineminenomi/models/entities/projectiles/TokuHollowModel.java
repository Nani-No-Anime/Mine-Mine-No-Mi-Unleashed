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
/*    */ public class TokuHollowModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer head2;
/*    */   public ModelRenderer head3;
/*    */   public ModelRenderer head4;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer rightarm;
/*    */   public ModelRenderer leftarm;
/*    */   public ModelRenderer rightleg;
/*    */   public ModelRenderer leftleg;
/*    */   
/*    */   public TokuHollowModel() {
/* 27 */     this.textureWidth = 256;
/* 28 */     this.textureHeight = 128;
/* 29 */     this.head2 = new ModelRenderer((Model)this, 0, 50);
/* 30 */     this.head2.setRotationPoint(-11.0F, -7.0F, -5.5F);
/* 31 */     this.head2.addBox(0.0F, 0.0F, 0.0F, 22.0F, 22.0F, 25.0F, 0.0F);
/* 32 */     this.head3 = new ModelRenderer((Model)this, 98, 50);
/* 33 */     this.head3.setRotationPoint(-12.5F, -7.0F, -4.0F);
/* 34 */     this.head3.addBox(0.0F, 0.0F, 0.0F, 25.0F, 22.0F, 22.0F, 0.0F);
/* 35 */     this.head4 = new ModelRenderer((Model)this, 98, 0);
/* 36 */     this.head4.setRotationPoint(-11.0F, -8.5F, -4.0F);
/* 37 */     this.head4.addBox(0.0F, 0.0F, 0.0F, 22.0F, 25.0F, 22.0F, 0.0F);
/* 38 */     this.rightarm = new ModelRenderer((Model)this, 188, 22);
/* 39 */     this.rightarm.setRotationPoint(-17.0F, 8.0F, -9.0F);
/* 40 */     this.rightarm.addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 12.0F, 0.0F);
/* 41 */     setRotateAngle(this.rightarm, 0.13962634F, 0.41887903F, 0.0F);
/* 42 */     this.head = new ModelRenderer((Model)this, 0, 0);
/* 43 */     this.head.setRotationPoint(-12.0F, -8.0F, -5.0F);
/* 44 */     this.head.addBox(0.0F, 0.0F, 0.0F, 24.0F, 24.0F, 24.0F, 0.0F);
/* 45 */     this.rightleg = new ModelRenderer((Model)this, 188, 14);
/* 46 */     this.rightleg.setRotationPoint(-4.0F, 5.0F, 28.5F);
/* 47 */     this.rightleg.addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
/* 48 */     this.body = new ModelRenderer((Model)this, 188, 0);
/* 49 */     this.body.setRotationPoint(-4.0F, 4.0F, 19.5F);
/* 50 */     this.body.addBox(0.0F, 0.0F, 0.0F, 8.0F, 4.0F, 9.0F, 0.0F);
/* 51 */     this.leftarm = new ModelRenderer((Model)this, 188, 22);
/* 52 */     this.leftarm.setRotationPoint(14.2F, 8.0F, -10.0F);
/* 53 */     this.leftarm.addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 12.0F, 0.0F);
/* 54 */     setRotateAngle(this.leftarm, 0.13962634F, -0.41887903F, 0.0F);
/* 55 */     this.leftleg = new ModelRenderer((Model)this, 188, 14);
/* 56 */     this.leftleg.setRotationPoint(2.0F, 5.0F, 28.5F);
/* 57 */     this.leftleg.addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 63 */     this.head2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 64 */     this.head3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 65 */     this.head4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 66 */     this.rightarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 67 */     this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 68 */     this.rightleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 69 */     this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 70 */     this.leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
/* 71 */     this.leftleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
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
/* 82 */     model.rotateAngleX = x;
/* 83 */     model.rotateAngleY = y;
/* 84 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\TokuHollowModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */