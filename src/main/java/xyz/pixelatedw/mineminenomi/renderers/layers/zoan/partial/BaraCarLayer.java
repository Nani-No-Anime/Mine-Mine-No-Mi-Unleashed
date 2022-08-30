/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.BaraCarPartialModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.BaraCarWheelsPartialModel;
/*    */ 
/*    */ public class BaraCarLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 23 */   private final BaraCarPartialModel bodyModel = new BaraCarPartialModel();
/* 24 */   private final BaraCarWheelsPartialModel wheelsModel = new BaraCarWheelsPartialModel();
/* 25 */   private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/bara_car_wheels.png");
/*    */ 
/*    */   
/*    */   public BaraCarLayer(IEntityRenderer renderer) {
/* 29 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 35 */     ResourceLocation skin = DefaultPlayerSkin.getDefaultSkin(entity.getUniqueID());
/* 36 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 38 */       PlayerEntity player = ((LivingEntity)entity).world.getPlayerByUuid(entity.getUniqueID());
/* 39 */       if (player != null) {
/* 40 */         skin = ((AbstractClientPlayerEntity)player).getLocationSkin();
/*    */       }
/*    */     } 
/* 43 */     RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/* 44 */     this.bodyModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 45 */     this.bodyModel.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 47 */     renderType = ModRenderTypes.getZoanRenderType(this.texture);
/* 48 */     this.wheelsModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 49 */     this.wheelsModel.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\partial\BaraCarLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */