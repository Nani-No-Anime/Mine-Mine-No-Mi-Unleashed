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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.DamnedPunkModel;
/*    */ 
/*    */ public class DamnedPunkLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 19 */   private DamnedPunkModel model = new DamnedPunkModel();
/* 20 */   public static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/damned_punk.png");
/*    */ 
/*    */   
/*    */   public DamnedPunkLayer(IEntityRenderer renderer) {
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
/* 34 */     matrixStack.translate(0.0D, 0.0D, -0.1D);
/* 35 */     this.model.rightArm.copyModelAngles(ogModel.bipedRightArm);
/* 36 */     RenderType renderType = ModRenderTypes.getZoanWithCullingRenderType(TEXTURE);
/* 37 */     this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).rotationYaw, partialTicks, packedLight);
/*    */     
/* 39 */     matrixStack.pop();
/*    */   }
/*    */ 
/*    */   
/*    */   public DamnedPunkModel getPartialModel() {
/* 44 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\partial\DamnedPunkLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */