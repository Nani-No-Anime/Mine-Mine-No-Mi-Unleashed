/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class DenDenMushiModel
/*    */   extends BipedModel
/*    */ {
/*    */   private ModelRenderer shape1;
/*    */   private ModelRenderer shape2;
/*    */   private ModelRenderer shape3;
/*    */   private ModelRenderer shape4;
/*    */   private ModelRenderer shape5;
/*    */   private ModelRenderer shape6;
/*    */   private ModelRenderer shape7;
/*    */   
/*    */   public DenDenMushiModel() {
/* 24 */     super(1.0F);
/* 25 */     this.textureWidth = 64;
/* 26 */     this.textureHeight = 64;
/*    */     
/* 28 */     this.bipedBody.showModel = false;
/* 29 */     this.bipedHead.showModel = false;
/* 30 */     this.bipedHeadwear.showModel = false;
/* 31 */     this.bipedLeftArm.showModel = false;
/* 32 */     this.bipedLeftLeg.showModel = false;
/* 33 */     this.bipedRightArm.showModel = false;
/* 34 */     this.bipedRightLeg.showModel = false;
/*    */     
/* 36 */     this.shape1 = new ModelRenderer((Model)this, 0, 0);
/* 37 */     this.shape1.addBox(-2.0F, 0.0F, -3.0F, 5.0F, 1.0F, 9.0F);
/* 38 */     this.shape1.setRotationPoint(0.0F, 23.0F, 0.0F);
/* 39 */     this.shape1.setTextureSize(64, 64);
/* 40 */     this.shape1.mirror = true;
/* 41 */     setRotation(this.shape1, 0.0F, 0.0F, 0.0F);
/* 42 */     this.shape2 = new ModelRenderer((Model)this, 21, 11);
/* 43 */     this.shape2.addBox(-1.0F, -3.0F, -3.0F, 3.0F, 3.0F, 3.0F);
/* 44 */     this.shape2.setRotationPoint(0.0F, 23.0F, 0.0F);
/* 45 */     this.shape2.setTextureSize(64, 64);
/* 46 */     this.shape2.mirror = true;
/* 47 */     setRotation(this.shape2, 0.0F, 0.0F, 0.0F);
/* 48 */     this.shape3 = new ModelRenderer((Model)this, 0, 11);
/* 49 */     this.shape3.addBox(-2.0F, -5.0F, 0.0F, 5.0F, 5.0F, 5.0F);
/* 50 */     this.shape3.setRotationPoint(0.0F, 23.0F, 0.0F);
/* 51 */     this.shape3.setTextureSize(64, 64);
/* 52 */     this.shape3.mirror = true;
/* 53 */     setRotation(this.shape3, 0.0F, 0.0F, 0.0F);
/* 54 */     this.shape4 = new ModelRenderer((Model)this, 29, 0);
/* 55 */     this.shape4.addBox(-1.0F, -5.0F, -2.0F, 1.0F, 2.0F, 1.0F);
/* 56 */     this.shape4.setRotationPoint(0.0F, 23.0F, 0.0F);
/* 57 */     this.shape4.setTextureSize(64, 64);
/* 58 */     this.shape4.mirror = true;
/* 59 */     setRotation(this.shape4, 0.0F, 0.0F, 0.0F);
/* 60 */     this.shape5 = new ModelRenderer((Model)this, 34, 3);
/* 61 */     this.shape5.addBox(-1.666667F, -7.0F, -2.5F, 2.0F, 2.0F, 2.0F);
/* 62 */     this.shape5.setRotationPoint(0.0F, 23.0F, 0.0F);
/* 63 */     this.shape5.setTextureSize(64, 64);
/* 64 */     this.shape5.mirror = true;
/* 65 */     setRotation(this.shape5, 0.0F, 0.0F, 0.0F);
/* 66 */     this.shape6 = new ModelRenderer((Model)this, 29, 0);
/* 67 */     this.shape6.addBox(1.0F, -5.0F, -2.0F, 1.0F, 2.0F, 1.0F);
/* 68 */     this.shape6.setRotationPoint(0.0F, 23.0F, 0.0F);
/* 69 */     this.shape6.setTextureSize(64, 64);
/* 70 */     this.shape6.mirror = true;
/* 71 */     setRotation(this.shape6, 0.0F, 0.0F, 0.0F);
/* 72 */     this.shape7 = new ModelRenderer((Model)this, 34, 3);
/* 73 */     this.shape7.addBox(0.7F, -7.0F, -2.5F, 2.0F, 2.0F, 2.0F);
/* 74 */     this.shape7.setRotationPoint(0.0F, 23.0F, 0.0F);
/* 75 */     this.shape7.setTextureSize(64, 64);
/* 76 */     this.shape7.mirror = true;
/* 77 */     setRotation(this.shape7, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 83 */     this.shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 84 */     this.shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 85 */     this.shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 86 */     this.shape4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 87 */     this.shape5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 88 */     this.shape6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 89 */     this.shape7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 94 */     model.rotateAngleX = x;
/* 95 */     model.rotateAngleY = y;
/* 96 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\DenDenMushiModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */