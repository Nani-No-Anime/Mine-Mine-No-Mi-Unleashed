/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.KameWalkPartialModel;
/*    */ 
/*    */ public class KameWalkLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 18 */   private KameWalkPartialModel model = new KameWalkPartialModel();
/* 19 */   private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/kame_kame_walk.png");
/*    */ 
/*    */   
/*    */   public KameWalkLayer(IEntityRenderer renderer) {
/* 23 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 29 */     RenderType renderType = ModRenderTypes.getZoanRenderType(this.texture);
/* 30 */     this.model.isSneak = entity.isCrouching();
/* 31 */     this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 32 */     this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).rotationYaw, partialTicks, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public KameWalkPartialModel getPartialModel() {
/* 37 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\partial\KameWalkLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */