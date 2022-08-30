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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.AllosaurusHeavyPartialModel;
/*    */ 
/*    */ public class AllosaurusHeavyLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 19 */   private AllosaurusHeavyPartialModel model = new AllosaurusHeavyPartialModel();
/* 20 */   private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/ryu_ryu_allosaurus_heavy.png");
/*    */ 
/*    */   
/*    */   public AllosaurusHeavyLayer(IEntityRenderer renderer) {
/* 24 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 30 */     BipedModel ogModel = (BipedModel)getEntityModel();
/*    */     
/* 32 */     this.model.bodyScales.copyModelAngles(ogModel.bipedBody);
/* 33 */     this.model.headScales.copyModelAngles(ogModel.bipedHead);
/* 34 */     this.model.rightArmScales.copyModelAngles(ogModel.bipedRightArm);
/* 35 */     this.model.leftArmScales.copyModelAngles(ogModel.bipedLeftArm);
/*    */     
/* 37 */     RenderType renderType = ModRenderTypes.getZoanWithCullingRenderType(this.texture);
/* 38 */     this.model.isSneak = entity.isCrouching();
/* 39 */     this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 40 */     this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).rotationYaw, partialTicks, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public AllosaurusHeavyPartialModel getPartialModel() {
/* 45 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\partial\AllosaurusHeavyLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */