/*    */ package xyz.pixelatedw.mineminenomi.models;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class HandcuffModel
/*    */   extends EntityModel<Entity>
/*    */ {
/*    */   private final ModelRenderer handcuffs;
/*    */   private final ModelRenderer rightCuff;
/*    */   private final ModelRenderer leftCuff;
/*    */   private final ModelRenderer chain;
/*    */   
/*    */   public HandcuffModel() {
/* 19 */     this.textureWidth = 32;
/* 20 */     this.textureHeight = 32;
/*    */     
/* 22 */     this.handcuffs = new ModelRenderer((Model)this);
/* 23 */     this.handcuffs.setRotationPoint(1.0F, 12.0F, -10.0F);
/* 24 */     setRotationAngle(this.handcuffs, 0.7854F, 0.0F, 0.0F);
/*    */     
/* 26 */     this.rightCuff = new ModelRenderer((Model)this);
/* 27 */     this.rightCuff.setRotationPoint(-7.0F, 0.75F, 7.0F);
/* 28 */     this.handcuffs.addChild(this.rightCuff);
/* 29 */     this.rightCuff.setTextureOffset(0, 12).addBox(2.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
/* 30 */     this.rightCuff.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
/* 31 */     this.rightCuff.setTextureOffset(0, 0).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
/* 32 */     this.rightCuff.setTextureOffset(0, 12).addBox(-3.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
/*    */     
/* 34 */     this.leftCuff = new ModelRenderer((Model)this);
/* 35 */     this.leftCuff.setRotationPoint(5.0F, 0.75F, 7.0F);
/* 36 */     this.handcuffs.addChild(this.leftCuff);
/* 37 */     this.leftCuff.setTextureOffset(0, 12).addBox(2.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
/* 38 */     this.leftCuff.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
/* 39 */     this.leftCuff.setTextureOffset(0, 0).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
/* 40 */     this.leftCuff.setTextureOffset(0, 12).addBox(-3.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
/*    */     
/* 42 */     this.chain = new ModelRenderer((Model)this);
/* 43 */     this.chain.setRotationPoint(-1.0F, 0.25F, 6.0F);
/* 44 */     this.handcuffs.addChild(this.chain);
/* 45 */     this.chain.setTextureOffset(0, 8).addBox(-2.0F, 1.0F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/* 46 */     this.chain.setTextureOffset(0, 8).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
/* 47 */     this.chain.setTextureOffset(0, 5).addBox(-3.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/* 48 */     this.chain.setTextureOffset(0, 5).addBox(1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 59 */     this.handcuffs.render(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 64 */     modelRenderer.rotateAngleX = x;
/* 65 */     modelRenderer.rotateAngleY = y;
/* 66 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\HandcuffModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */