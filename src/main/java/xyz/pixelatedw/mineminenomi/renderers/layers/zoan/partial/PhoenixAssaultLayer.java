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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.PhoenixAssaultPartialModel;
/*    */ 
/*    */ public class PhoenixAssaultLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 18 */   private PhoenixAssaultPartialModel model = new PhoenixAssaultPartialModel();
/* 19 */   private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/tori_tori_phoenix_assault.png");
/*    */ 
/*    */   
/*    */   public PhoenixAssaultLayer(IEntityRenderer renderer) {
/* 23 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 29 */     RenderType renderType = ModRenderTypes.getZoanWithCullingRenderType(this.texture);
/* 30 */     this.model.isSneak = entity.isCrouching();
/* 31 */     this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 32 */     this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).rotationYaw, partialTicks, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public PhoenixAssaultPartialModel getPartialModel() {
/* 37 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\partial\PhoenixAssaultLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */