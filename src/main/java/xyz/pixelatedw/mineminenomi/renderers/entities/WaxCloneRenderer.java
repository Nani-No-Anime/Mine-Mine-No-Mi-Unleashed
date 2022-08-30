/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ public class WaxCloneRenderer extends HumanoidRenderer<WaxCloneEntity, BipedModel<WaxCloneEntity>> {
/* 17 */   public static final ResourceLocation WAX_LOCATION = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/candle_lock.png");
/*    */ 
/*    */   
/*    */   public WaxCloneRenderer(EntityRendererManager manager) {
/* 21 */     super(manager, new HumanoidModel());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(WaxCloneEntity entity) {
/* 27 */     if (entity.isTextured()) {
/*    */       
/* 29 */       PlayerEntity player = entity.world.getPlayerByUuid(entity.getOwnerUUID());
/* 30 */       if (player != null) {
/* 31 */         return ((AbstractClientPlayerEntity)player).getLocationSkin();
/*    */       }
/* 33 */       return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
/*    */     } 
/*    */     
/* 36 */     return WAX_LOCATION;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<WaxCloneEntity>
/*    */   {
/*    */     public EntityRenderer<? super WaxCloneEntity> createRenderFor(EntityRendererManager manager) {
/* 48 */       return (EntityRenderer<? super WaxCloneEntity>)new WaxCloneRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\WaxCloneRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */