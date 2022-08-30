/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.MobRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.FightingFishEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.FightingFishModel;
/*    */ 
/*    */ public class FightingFishRenderer<T extends FightingFishEntity, M extends FightingFishModel<T>> extends MobRenderer<T, M> {
/*    */   public FightingFishRenderer(EntityRendererManager manager) {
/* 19 */     super(manager, (M)new FightingFishModel(), 1.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void preRenderCallback(T entity, MatrixStack matrixStack, float partialTickTime) {
/* 25 */     matrixStack.scale(entity.getSize(), entity.getSize(), entity.getSize());
/* 26 */     this.shadowSize = entity.getSize() * 2.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 32 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(T entity) {
/* 38 */     return new ResourceLocation("mineminenomi", "textures/models/fighting_fish.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 46 */       return (EntityRenderer)new FightingFishRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\FightingFishRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */