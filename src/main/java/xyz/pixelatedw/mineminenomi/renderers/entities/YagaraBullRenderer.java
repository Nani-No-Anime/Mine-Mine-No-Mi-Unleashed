/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Quaternion;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.YagaraBullModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class YagaraBullRenderer<T extends YagaraBullEntity, M extends YagaraBullModel<T>> extends HumanoidRenderer<T, M> {
/*    */   public YagaraBullRenderer(EntityRendererManager manager) {
/* 22 */     super(manager, (M)new YagaraBullModel());
/* 23 */     this.scale = new float[] { 1.0F, 1.0F, 1.0F };
/* 24 */     this.shadowSize = 0.7F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 30 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */     
/* 32 */     if (entity.isTamed()) {
/*    */       
/* 34 */       if (!entity.isSaddled()) {
/*    */         
/* 36 */         ((YagaraBullModel)this.entityModel).saddle.showModel = false;
/* 37 */         ((YagaraBullModel)this.entityModel).belt1.showModel = false;
/* 38 */         ((YagaraBullModel)this.entityModel).belt2.showModel = false;
/*    */       }
/*    */       else {
/*    */         
/* 42 */         ((YagaraBullModel)this.entityModel).saddle.showModel = true;
/* 43 */         ((YagaraBullModel)this.entityModel).belt1.showModel = true;
/* 44 */         ((YagaraBullModel)this.entityModel).belt2.showModel = true;
/*    */       } 
/*    */       
/* 47 */       matrixStack.push();
/*    */       
/* 49 */       matrixStack.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
/* 50 */       matrixStack.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
/*    */       
/* 52 */       float ageInTicks = ((YagaraBullEntity)entity).ticksExisted + partialTicks;
/* 53 */       float headYawOffset = MathHelper.interpolateAngle(partialTicks, ((YagaraBullEntity)entity).prevRenderYawOffset, ((YagaraBullEntity)entity).renderYawOffset);
/*    */       
/* 55 */       WyHelper.rotateCorpse(matrixStack, (LivingEntity)entity, ageInTicks, headYawOffset, partialTicks);
/*    */       
/* 57 */       matrixStack.translate(0.0D, -1.5099999904632568D, 0.0D);
/*    */       
/* 59 */       ((YagaraBullModel)getEntityModel()).renderSaddle(matrixStack, buffer.getBuffer(RenderType.getEntitySolid(getEntityTexture(entity))), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */       
/* 61 */       matrixStack.pop();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 70 */       return (EntityRenderer)new YagaraBullRenderer<>(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\YagaraBullRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */