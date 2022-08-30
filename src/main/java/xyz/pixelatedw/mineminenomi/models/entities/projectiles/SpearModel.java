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
/*    */ public class SpearModel
/*    */   extends EntityModel
/*    */ {
/*    */   public ModelRenderer harpooncable;
/*    */   public ModelRenderer harpoon1;
/*    */   public ModelRenderer harpoon2;
/*    */   public ModelRenderer harpoon3;
/*    */   public ModelRenderer harpoon4;
/*    */   public ModelRenderer harpoon5;
/*    */   public ModelRenderer harpoon6;
/*    */   
/*    */   public SpearModel() {
/* 25 */     this.textureWidth = 128;
/* 26 */     this.textureHeight = 32;
/* 27 */     this.harpoon4 = new ModelRenderer((Model)this, 0, 12);
/* 28 */     this.harpoon4.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 29 */     this.harpoon4.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F);
/* 30 */     this.harpooncable = new ModelRenderer((Model)this, 0, 0);
/* 31 */     this.harpooncable.setRotationPoint(0.0F, 0.0F, -5.0F);
/* 32 */     this.harpooncable.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 30.0F, 0.0F);
/* 33 */     this.harpoon3 = new ModelRenderer((Model)this, 0, 8);
/* 34 */     this.harpoon3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 35 */     this.harpoon3.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 1.0F, 2.0F, 0.0F);
/* 36 */     this.harpoon5 = new ModelRenderer((Model)this, 0, 15);
/* 37 */     this.harpoon5.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 38 */     this.harpoon5.addBox(-1.1F, 0.0F, -2.25F, 5.0F, 1.0F, 1.0F, 0.0F);
/* 39 */     setRotateAngle(this.harpoon5, 0.0F, 1.134464F, 0.0F);
/* 40 */     this.harpoon2 = new ModelRenderer((Model)this, 0, 5);
/* 41 */     this.harpoon2.setRotationPoint(0.5F, 0.0F, 0.0F);
/* 42 */     this.harpoon2.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F);
/* 43 */     this.harpoon6 = new ModelRenderer((Model)this, 0, 18);
/* 44 */     this.harpoon6.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 45 */     this.harpoon6.addBox(-3.9F, 0.0F, -2.25F, 5.0F, 1.0F, 1.0F, 0.0F);
/* 46 */     setRotateAngle(this.harpoon6, 0.0F, -1.134464F, 0.0F);
/* 47 */     this.harpoon1 = new ModelRenderer((Model)this, 0, 0);
/* 48 */     this.harpoon1.setRotationPoint(0.0F, 0.0F, 5.0F);
/* 49 */     this.harpoon1.addBox(-0.5F, -0.5F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 50 */     this.harpoon2.addChild(this.harpoon4);
/* 51 */     this.harpoon2.addChild(this.harpoon3);
/* 52 */     this.harpoon2.addChild(this.harpoon5);
/* 53 */     this.harpooncable.addChild(this.harpoon2);
/* 54 */     this.harpoon2.addChild(this.harpoon6);
/* 55 */     this.harpooncable.addChild(this.harpoon1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
/* 61 */     this.harpooncable.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
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
/* 72 */     model.rotateAngleX = x;
/* 73 */     model.rotateAngleY = y;
/* 74 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\projectiles\SpearModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */