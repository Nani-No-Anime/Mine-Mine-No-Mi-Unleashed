/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.SpringLegsPartialModel;
/*    */ 
/*    */ public class SpringLegsLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 19 */   private SpringLegsPartialModel model = new SpringLegsPartialModel();
/* 20 */   private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/spring_legs.png");
/*    */   
/*    */   private float renderingTicks;
/*    */   
/*    */   public SpringLegsLayer(IEntityRenderer renderer) {
/* 25 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 31 */     RenderType renderType = ModRenderTypes.getZoanRenderType(this.texture);
/* 32 */     this.model.isSneak = entity.isCrouching();
/*    */     
/* 34 */     int sum = 0;
/* 35 */     for (int i = 0; i < 5; i++) {
/*    */       
/* 37 */       if (!((LivingEntity)entity).world.isAirBlock(entity.getPosition().down(i)))
/* 38 */         sum++; 
/*    */     } 
/* 40 */     boolean isSolid = (sum >= 2);
/*    */     
/* 42 */     if (!((LivingEntity)entity).onGround) {
/*    */       
/* 44 */       limbSwing /= 4.0F;
/* 45 */       limbSwingAmount /= 4.0F;
/* 46 */       if (ModKeybindings.isSpaceKeyDown() && (entity.getMotion()).y > 0.3D && isSolid) {
/*    */         
/* 48 */         matrixStack.translate(0.0D, -0.7300000190734863D, 0.0D);
/* 49 */         matrixStack.scale(1.0F, 2.0F, 1.0F);
/*    */       } 
/*    */     } 
/*    */     
/* 53 */     this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 54 */     this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).rotationYaw, partialTicks, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpringLegsPartialModel getPartialModel() {
/* 59 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\partial\SpringLegsLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */