/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.NightmareSoldierEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.NightmareSoldierLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class NightmareSoldierRenderer extends HumanoidRenderer<NightmareSoldierEntity, BipedModel<NightmareSoldierEntity>> {
/*    */   public NightmareSoldierRenderer(EntityRendererManager renderManager) {
/* 21 */     super(renderManager, new HumanoidModel());
/* 22 */     addLayer(new NightmareSoldierLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(NightmareSoldierEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 28 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<NightmareSoldierEntity>
/*    */   {
/*    */     public EntityRenderer<? super NightmareSoldierEntity> createRenderFor(EntityRendererManager manager) {
/* 40 */       return (EntityRenderer<? super NightmareSoldierEntity>)new NightmareSoldierRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\NightmareSoldierRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */