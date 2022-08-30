/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class BlackKnightRenderer
/*    */   extends HumanoidRenderer<BlackKnightEntity, BipedModel<BlackKnightEntity>> {
/*    */   public BlackKnightRenderer(EntityRendererManager manager) {
/* 21 */     super(manager, new HumanoidModel());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(BlackKnightEntity entity) {
/* 27 */     AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)entity.getOwner();
/* 28 */     if (player != null) {
/* 29 */       return player.getLocationSkin();
/*    */     }
/* 31 */     return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<BlackKnightEntity>
/*    */   {
/*    */     public EntityRenderer<? super BlackKnightEntity> createRenderFor(EntityRendererManager manager) {
/* 41 */       return (EntityRenderer<? super BlackKnightEntity>)new BlackKnightRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\BlackKnightRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */