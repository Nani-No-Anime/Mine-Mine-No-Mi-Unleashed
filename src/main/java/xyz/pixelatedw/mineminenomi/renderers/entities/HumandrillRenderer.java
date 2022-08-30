/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.HumandrillEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.HumandrillModel;
/*    */ 
/*    */ public class HumandrillRenderer<T extends HumandrillEntity, M extends BipedModel<T>>
/*    */   extends HumanoidRenderer<T, M> {
/*    */   public HumandrillRenderer(EntityRendererManager manager) {
/* 16 */     super(manager, (M)new HumandrillModel(), "humandrill");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void preRenderCallback(T entity, MatrixStack matrixStack, float partialTickTime) {
/* 22 */     matrixStack.scale(entity.getSize(), entity.getSize(), entity.getSize());
/* 23 */     this.shadowSize = entity.getSize() / 1.2F;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 31 */       return (EntityRenderer)new HumandrillRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\HumandrillRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */