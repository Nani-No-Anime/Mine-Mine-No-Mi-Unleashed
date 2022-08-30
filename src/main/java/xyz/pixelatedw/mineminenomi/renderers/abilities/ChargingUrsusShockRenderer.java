/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Quaternion;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.ChargingUrsusShockEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PawModel;
/*    */ 
/*    */ public class ChargingUrsusShockRenderer<T extends ChargingUrsusShockEntity>
/*    */   extends EntityRenderer<T>
/*    */ {
/*    */   private PawModel model;
/*    */   
/*    */   protected ChargingUrsusShockRenderer(EntityRendererManager manager) {
/* 25 */     super(manager);
/* 26 */     this.model = new PawModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 32 */     matrixStack.push();
/*    */     
/* 34 */     matrixStack.translate(0.0D, 1.5D, 0.0D);
/*    */     
/* 36 */     matrixStack.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
/* 37 */     matrixStack.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
/* 38 */     matrixStack.rotate(new Quaternion(Vector3f.YP, ((ChargingUrsusShockEntity)entity).prevRotationYaw + (((ChargingUrsusShockEntity)entity).rotationYaw - ((ChargingUrsusShockEntity)entity).prevRotationYaw) * partialTicks - 180.0F, true));
/*    */     
/* 40 */     float size = 1.0F + entity.getCharge() / 2.0F;
/*    */     
/* 42 */     matrixStack.scale(size, size, size);
/*    */     
/* 44 */     RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
/* 45 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 46 */     this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.6F);
/*    */     
/* 48 */     matrixStack.push();
/*    */     
/* 50 */     double t = (((ChargingUrsusShockEntity)entity).ticksExisted * 3 % 100);
/* 51 */     double mirageSize = (t >= 50.0D) ? (2.0D - t / 100.0D) : (1.0D + t / 100.0D);
/* 52 */     float scale = (float)mirageSize;
/* 53 */     matrixStack.scale(scale, scale, scale);
/* 54 */     this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.2F);
/*    */     
/* 56 */     matrixStack.pop();
/*    */     
/* 58 */     matrixStack.pop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(ChargingUrsusShockEntity entity) {
/* 64 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<ChargingUrsusShockEntity>
/*    */   {
/*    */     public EntityRenderer<? super ChargingUrsusShockEntity> createRenderFor(EntityRendererManager manager) {
/* 74 */       return new ChargingUrsusShockRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\ChargingUrsusShockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */