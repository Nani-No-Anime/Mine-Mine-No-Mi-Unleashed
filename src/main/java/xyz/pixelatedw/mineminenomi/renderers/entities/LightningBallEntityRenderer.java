/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.awt.Color;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.culling.ClippingHelperImpl;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningBallEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class LightningBallEntityRenderer
/*    */   extends EntityRenderer<LightningBallEntity>
/*    */ {
/*    */   private Color color;
/*    */   
/*    */   public LightningBallEntityRenderer(EntityRendererManager manager) {
/* 23 */     super(manager);
/*    */ 
/*    */     
/* 26 */     this.color = new Color(641023);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(LightningBallEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
/* 31 */     List<LightningRendererPropieties> entities = entity.entities;
/*    */     
/* 33 */     if (!Minecraft.getInstance().isGamePaused()) {
/*    */       
/* 35 */       if (entity.ticksExisted % 2 == 0) {
/* 36 */         entities.clear();
/*    */       }
/* 38 */       int lightnings = 64;
/*    */       
/* 40 */       if (entities.isEmpty())
/*    */       {
/* 42 */         for (int i = 0; i < lightnings; i++) {
/*    */ 
/*    */           
/* 45 */           LightningRendererPropieties e = new LightningRendererPropieties((Entity)entity, entity.getSeed(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(0, 180), entity.getLightningLength());
/* 46 */           entities.add(e);
/*    */         } 
/*    */       }
/*    */     } 
/*    */     
/* 51 */     matrixStack.push();
/* 52 */     matrixStack.scale(entity.getSize() / 2.0F, entity.getSize() / 2.0F, entity.getSize() / 2.0F);
/* 53 */     entities.forEach(e -> LightningEntityRenderer.renderLightning(e.entity, e.random, e.length, e.rotationYaw, e.rotationPitch, this.color, 0.3F, matrixStack, bufferIn, 8));
/* 54 */     matrixStack.pop();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(LightningBallEntity entity) {
/* 60 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(LightningBallEntity livingEntityIn, ClippingHelperImpl camera, double camX, double camY, double camZ) {
/* 65 */     return true;
/*    */   }
/*    */   
/*    */   public static class LightningRendererPropieties
/*    */   {
/* 70 */     private Entity entity = null; float rotationYaw;
/* 71 */     float length = 0.0F; float rotationPitch;
/* 72 */     long random = 0L;
/*    */     
/*    */     public LightningRendererPropieties(Entity entity, long random, float rotationYaw, float rotationPitch, float length) {
/* 75 */       this.entity = entity;
/* 76 */       this.random = random;
/* 77 */       this.rotationPitch = rotationPitch;
/* 78 */       this.rotationYaw = rotationYaw;
/* 79 */       this.length = length;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<LightningBallEntity>
/*    */   {
/*    */     public EntityRenderer<? super LightningBallEntity> createRenderFor(EntityRendererManager manager) {
/* 93 */       return new LightningBallEntityRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\LightningBallEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */