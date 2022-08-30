/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
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
/*    */ public class ShinokuniFaceLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 22 */   private final PlayerModel playerModel = new PlayerModel(1.0F, false);
/*    */ 
/*    */   
/*    */   public ShinokuniFaceLayer(IEntityRenderer renderer) {
/* 26 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 32 */     ResourceLocation skin = DefaultPlayerSkin.getDefaultSkin(entity.getUniqueID());
/* 33 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 35 */       PlayerEntity player = ((LivingEntity)entity).world.getPlayerByUuid(entity.getUniqueID());
/* 36 */       if (player != null) {
/* 37 */         skin = ((AbstractClientPlayerEntity)player).getLocationSkin();
/*    */       }
/*    */     } 
/* 40 */     matrixStack.push();
/* 41 */     matrixStack.scale(0.85F, 0.85F, 0.85F);
/* 42 */     matrixStack.translate(0.0D, -0.95D, -0.35D);
/*    */ 
/*    */     
/* 45 */     headPitch = MathHelper.clamp(headPitch, -17.0F, 60.0F);
/* 46 */     netHeadYaw = MathHelper.clamp(netHeadYaw, -27.0F, 27.0F);
/*    */     
/* 48 */     RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/* 49 */     this.playerModel.setVisible(false);
/* 50 */     this.playerModel.bipedHead.showModel = true;
/* 51 */     this.playerModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 52 */     this.playerModel.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/* 53 */     matrixStack.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\partial\ShinokuniFaceLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */