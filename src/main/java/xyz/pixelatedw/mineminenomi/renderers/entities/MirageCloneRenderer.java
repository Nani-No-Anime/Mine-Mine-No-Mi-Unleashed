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
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class MirageCloneRenderer
/*    */   extends HumanoidRenderer<MirageCloneEntity, BipedModel<MirageCloneEntity>> {
/*    */   public MirageCloneRenderer(EntityRendererManager manager) {
/* 22 */     super(manager, new HumanoidModel());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(MirageCloneEntity entity) {
/* 28 */     PlayerEntity player = entity.world.getPlayerByUuid(entity.getOwnerUUID());
/* 29 */     if (player != null) {
/* 30 */       return ((AbstractClientPlayerEntity)player).getLocationSkin();
/*    */     }
/* 32 */     return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<MirageCloneEntity>
/*    */   {
/*    */     public EntityRenderer<? super MirageCloneEntity> createRenderFor(EntityRendererManager manager) {
/* 42 */       return (EntityRenderer<? super MirageCloneEntity>)new MirageCloneRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\MirageCloneRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */