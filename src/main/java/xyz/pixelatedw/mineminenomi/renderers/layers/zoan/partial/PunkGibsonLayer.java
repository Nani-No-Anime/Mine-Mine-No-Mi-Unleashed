/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.PunkGibsonModel;
/*    */ 
/*    */ public class PunkGibsonLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 19 */   private PunkGibsonModel model = new PunkGibsonModel();
/* 20 */   public static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/punk_gibson.png");
/*    */ 
/*    */   
/*    */   public PunkGibsonLayer(IEntityRenderer renderer) {
/* 24 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 30 */     BipedModel ogModel = (BipedModel)getEntityModel();
/*    */     
/* 32 */     matrixStack.push();
/*    */ 
/*    */     
/* 35 */     matrixStack.translate(0.0D, 0.0D, -0.1D);
/* 36 */     this.model.rightArm.copyModelAngles(ogModel.bipedRightArm);
/* 37 */     RenderType renderType = ModRenderTypes.getZoanWithCullingRenderType(TEXTURE);
/* 38 */     this.model.rightArm.rotationPointX = (float)(this.model.rightArm.rotationPointX + 2.3D);
/* 39 */     this.model.rightArm.rotationPointY -= 2.0F;
/* 40 */     this.model.rightArm.rotateAngleX = (float)(this.model.rightArm.rotateAngleX + Math.toRadians(-120.0D));
/* 41 */     this.model.rightArm.rotateAngleY = (float)(this.model.rightArm.rotateAngleY + Math.toRadians(20.0D));
/* 42 */     this.model.rightArm.rotateAngleZ = (float)(this.model.rightArm.rotateAngleZ + Math.toRadians(-20.0D));
/* 43 */     this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).rotationYaw, partialTicks, packedLight);
/*    */     
/* 45 */     matrixStack.pop();
/*    */   }
/*    */ 
/*    */   
/*    */   public PunkGibsonModel getPartialModel() {
/* 50 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\partial\PunkGibsonLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */