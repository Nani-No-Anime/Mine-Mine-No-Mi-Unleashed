/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.LapahnModel;
/*    */ 
/*    */ public class LapahnRenderer<T extends LapahnEntity, M extends LapahnModel<T>>
/*    */   extends HumanoidRenderer<T, M> {
/*    */   public LapahnRenderer(EntityRendererManager manager) {
/* 15 */     super(manager, (M)new LapahnModel());
/* 16 */     this.shadowSize = 0.8F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(T entity) {
/* 22 */     if (entity.isEnraged()) {
/* 23 */       return new ResourceLocation("mineminenomi", "textures/models/lapahn_angry.png");
/*    */     }
/* 25 */     return new ResourceLocation("mineminenomi", "textures/models/lapahn.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 33 */       return (EntityRenderer)new LapahnRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\LapahnRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */