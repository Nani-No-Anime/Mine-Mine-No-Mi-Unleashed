/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ 
/*    */ 
/*    */ public class DummyRenderer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LivingRenderer<T, M>
/*    */ {
/*    */   private ResourceLocation texture;
/*    */   private float size;
/*    */   
/*    */   public DummyRenderer(EntityRendererManager manager, M model, String texture, float size) {
/* 21 */     super(manager, model, 0.8F);
/* 22 */     this.texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/" + texture + ".png");
/* 23 */     this.size = size;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(T entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
/* 29 */     matrixStack.scale(this.size, this.size, this.size);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(T entity) {
/* 35 */     return this.texture;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canRenderName(T entity) {
/* 41 */     return false;
/*    */   }
/*    */   
/*    */   public static class Factory<M extends EntityModel>
/*    */     implements IRenderFactory {
/*    */     private M model;
/*    */     private String texture;
/* 48 */     private float size = 1.0F;
/*    */ 
/*    */     
/*    */     public Factory(M model, String texture) {
/* 52 */       this(model, texture, 1.0F);
/*    */     }
/*    */ 
/*    */     
/*    */     public Factory(M model, String texture, float size) {
/* 57 */       this.model = model;
/* 58 */       this.texture = texture;
/* 59 */       this.size = size;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 65 */       return (EntityRenderer)new DummyRenderer<>(manager, (EntityModel<LivingEntity>)this.model, this.texture, this.size);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\DummyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */