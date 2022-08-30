/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities.models;
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
/*    */ public class EntityLegModel
/*    */   extends EntityModel
/*    */ {
/*    */   private final ModelRenderer entityLeg;
/*    */   
/*    */   public EntityLegModel() {
/* 19 */     this.textureWidth = 64;
/* 20 */     this.textureHeight = 64;
/*    */     
/* 22 */     this.entityLeg = new ModelRenderer((Model)this);
/* 23 */     this.entityLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
/* 24 */     setRotationAngle(this.entityLeg, -1.5708F, 0.0F, 0.0F);
/* 25 */     this.entityLeg.setTextureOffset(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 31 */     this.entityLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 41 */     modelRenderer.rotateAngleX = x;
/* 42 */     modelRenderer.rotateAngleY = y;
/* 43 */     modelRenderer.rotateAngleZ = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\models\EntityLegModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */