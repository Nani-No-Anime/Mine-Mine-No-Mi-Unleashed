/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ 
/*    */ public class CandleChampionFaceLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 23 */   private final PlayerModel playerModel = new PlayerModel(1.0F, false);
/*    */ 
/*    */   
/*    */   public CandleChampionFaceLayer(IEntityRenderer renderer) {
/* 27 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 33 */     ResourceLocation skin = DefaultPlayerSkin.getDefaultSkin(entity.getUniqueID());
/* 34 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 36 */       PlayerEntity player = ((LivingEntity)entity).world.getPlayerByUuid(entity.getUniqueID());
/* 37 */       if (player != null) {
/* 38 */         skin = ((AbstractClientPlayerEntity)player).getLocationSkin();
/*    */       }
/*    */     } 
/* 41 */     matrixStack.push();
/* 42 */     matrixStack.translate(0.0D, -2.5D, 0.5D);
/* 43 */     matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
/*    */ 
/*    */     
/* 46 */     headPitch = MathHelper.clamp(headPitch, -17.0F, 60.0F);
/* 47 */     netHeadYaw = MathHelper.clamp(netHeadYaw, -27.0F, 27.0F);
/*    */     
/* 49 */     RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/* 50 */     this.playerModel.setVisible(false);
/* 51 */     this.playerModel.bipedHead.showModel = true;
/* 52 */     this.playerModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 53 */     this.playerModel.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/* 54 */     matrixStack.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\partial\CandleChampionFaceLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */