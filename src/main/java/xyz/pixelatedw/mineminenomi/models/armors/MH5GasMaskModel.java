/*    */ package xyz.pixelatedw.mineminenomi.models.armors;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ public class MH5GasMaskModel<T extends LivingEntity>
/*    */   extends BipedModel<T> {
/*    */   public ModelRenderer base_0;
/*    */   public ModelRenderer base_1;
/*    */   public ModelRenderer eyes;
/*    */   public ModelRenderer right_ear;
/*    */   public ModelRenderer left_ear;
/*    */   public ModelRenderer eye_ear_connector_0;
/*    */   public ModelRenderer eye_ear_connector_1;
/*    */   public ModelRenderer eye_ear_connector_2;
/*    */   public ModelRenderer eye_ear_connector_3;
/*    */   
/*    */   public MH5GasMaskModel() {
/* 24 */     super(1.0F);
/* 25 */     this.textureWidth = 32;
/* 26 */     this.textureHeight = 16;
/* 27 */     this.left_ear = new ModelRenderer((Model)this, 17, 10);
/* 28 */     this.left_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 29 */     this.left_ear.addBox(2.8F, -5.6F, -1.8F, 2.0F, 2.0F, 4.0F, 0.0F);
/* 30 */     this.eye_ear_connector_2 = new ModelRenderer((Model)this, 8, 10);
/* 31 */     this.eye_ear_connector_2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 32 */     this.eye_ear_connector_2.addBox(3.5F, -5.4F, -4.4F, 1.0F, 1.0F, 3.0F, 0.0F);
/* 33 */     this.base_0 = new ModelRenderer((Model)this, 0, 12);
/* 34 */     this.base_0.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 35 */     this.base_0.addBox(-1.4F, -3.0F, -4.5F, 3.0F, 3.0F, 1.0F, 0.0F);
/* 36 */     this.eyes = new ModelRenderer((Model)this, 0, 0);
/* 37 */     this.eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 38 */     this.eyes.addBox(-3.0F, -5.9F, -5.0F, 6.0F, 3.0F, 2.0F, 0.0F);
/* 39 */     this.eye_ear_connector_3 = new ModelRenderer((Model)this, 9, 14);
/* 40 */     this.eye_ear_connector_3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 41 */     this.eye_ear_connector_3.addBox(1.5F, -5.4F, -4.4F, 2.0F, 1.0F, 1.0F, 0.0F);
/* 42 */     this.base_1 = new ModelRenderer((Model)this, 0, 8);
/* 43 */     this.base_1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 44 */     this.base_1.addBox(-1.0F, -2.5F, -5.4F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 45 */     this.eye_ear_connector_1 = new ModelRenderer((Model)this, 9, 14);
/* 46 */     this.eye_ear_connector_1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 47 */     this.eye_ear_connector_1.addBox(-4.5F, -5.4F, -4.4F, 2.0F, 1.0F, 1.0F, 0.0F);
/* 48 */     this.right_ear = new ModelRenderer((Model)this, 17, 10);
/* 49 */     this.right_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 50 */     this.right_ear.addBox(-5.2F, -5.6F, -1.8F, 2.0F, 2.0F, 4.0F, 0.0F);
/* 51 */     this.eye_ear_connector_0 = new ModelRenderer((Model)this, 8, 10);
/* 52 */     this.eye_ear_connector_0.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 53 */     this.eye_ear_connector_0.addBox(-4.5F, -5.4F, -4.4F, 1.0F, 1.0F, 3.0F, 0.0F);
/* 54 */     this.base_0.addChild(this.left_ear);
/* 55 */     this.left_ear.addChild(this.eye_ear_connector_2);
/* 56 */     this.base_0.addChild(this.eyes);
/* 57 */     this.left_ear.addChild(this.eye_ear_connector_3);
/* 58 */     this.base_0.addChild(this.base_1);
/* 59 */     this.right_ear.addChild(this.eye_ear_connector_1);
/* 60 */     this.base_0.addChild(this.right_ear);
/* 61 */     this.right_ear.addChild(this.eye_ear_connector_0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 67 */     this.base_0.copyModelAngles(this.bipedHead);
/* 68 */     this.base_0.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 74 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
/* 79 */     model.rotateAngleX = x;
/* 80 */     model.rotateAngleY = y;
/* 81 */     model.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\armors\MH5GasMaskModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */