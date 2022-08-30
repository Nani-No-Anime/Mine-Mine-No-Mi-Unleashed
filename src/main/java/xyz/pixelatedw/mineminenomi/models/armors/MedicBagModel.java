/*    */ package xyz.pixelatedw.mineminenomi.models.armors;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class MedicBagModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   public ModelRenderer backpack;
/*    */   public ModelRenderer backpack_2;
/*    */   public ModelRenderer backpack_strings;
/*    */   
/*    */   public MedicBagModel() {
/* 21 */     super(1.0F);
/* 22 */     this.textureWidth = 64;
/* 23 */     this.textureHeight = 64;
/*    */     
/* 25 */     this.backpack = new ModelRenderer((Model)this);
/* 26 */     this.backpack.setRotationPoint(0.0F, 3.7F, 2.0F);
/* 27 */     this.backpack.setTextureOffset(0, 0).addBox(-2.5F, 1.5F, 2.0F, 5.0F, 5.0F, 3.0F, 0.0F, false);
/*    */     
/* 29 */     this.backpack_2 = new ModelRenderer((Model)this);
/* 30 */     this.backpack_2.setRotationPoint(0.0F, 3.7F, 2.0F);
/* 31 */     this.backpack.addChild(this.backpack_2);
/* 32 */     this.backpack_2.setTextureOffset(0, 9).addBox(-3.5F, 0.5F, 0.0F, 7.0F, 4.0F, 4.0F, 0.0F, false);
/*    */     
/* 34 */     this.backpack_strings = new ModelRenderer((Model)this);
/* 35 */     this.backpack_strings.setRotationPoint(0.0F, 3.7F, 2.0F);
/* 36 */     this.backpack.addChild(this.backpack_strings);
/* 37 */     this.backpack_strings.setTextureOffset(20, 0).addBox(-5.0F, -4.0F, 0.1F, 10.0F, 9.0F, 0.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 43 */     this.backpack.copyModelAngles(this.bipedBody);
/* 44 */     this.backpack.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 50 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 55 */     model.rotateAngleX = x;
/* 56 */     model.rotateAngleY = y;
/* 57 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\armors\MedicBagModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */