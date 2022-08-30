/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraFestivalEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.BaraFestivalModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class BaraFestivalRenderer<T extends BaraFestivalEntity>
/*    */   extends EntityRenderer<T> {
/* 25 */   private BaraFestivalModel model = new BaraFestivalModel();
/*    */ 
/*    */   
/*    */   protected BaraFestivalRenderer(EntityRendererManager renderManager) {
/* 29 */     super(renderManager);
/* 30 */     this.shadowSize = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 36 */     matrixStack.push();
/*    */     
/* 38 */     RenderType type = ModRenderTypes.getZoanRenderType(getEntityTexture(entity));
/* 39 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 40 */     this.model.setRotationAngles((BaraFestivalEntity)entity, 0.0F, 0.0F, ((BaraFestivalEntity)entity).ticksExisted, 0.0F, 0.0F);
/* 41 */     this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 43 */     matrixStack.pop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(T entity) {
/* 49 */     PlayerEntity player = ((BaraFestivalEntity)entity).world.getPlayerByUuid(entity.getOwnerUUID());
/* 50 */     if (player != null) {
/* 51 */       return ((AbstractClientPlayerEntity)player).getLocationSkin();
/*    */     }
/* 53 */     return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 61 */       return new BaraFestivalRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\BaraFestivalRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */