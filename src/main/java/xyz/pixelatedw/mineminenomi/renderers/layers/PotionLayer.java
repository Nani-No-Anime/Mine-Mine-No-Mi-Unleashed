/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class PotionLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
/* 28 */   private static List<EffectInstance> copyEffects = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public PotionLayer(IEntityRenderer renderer) {
/* 32 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 38 */     copyEffects.addAll(entity.getActivePotionEffects());
/*    */     
/* 40 */     for (EffectInstance effectInstance : copyEffects) {
/*    */       
/* 42 */       if (!(effectInstance.getPotion() instanceof OverlayEffect)) {
/*    */         continue;
/*    */       }
/* 45 */       OverlayEffect effect = (OverlayEffect)effectInstance.getPotion();
/*    */       
/* 47 */       if (entity.isPotionActive((Effect)effect) && entity.getActivePotionEffect((Effect)effect).getDuration() <= 0) {
/* 48 */         entity.removePotionEffect((Effect)effect);
/*    */       }
/* 50 */       if (effect.getBlockOverlay() != null) {
/*    */         
/* 52 */         Block blockToRender = effect.getBlockOverlay();
/*    */         
/* 54 */         matrixStack.push();
/*    */         
/* 56 */         float blocksWidth = (float)(Math.ceil(entity.getWidth()) + 1.0D);
/* 57 */         float blocksHeight = (float)(Math.ceil(entity.getHeight()) + 1.0D);
/* 58 */         matrixStack.translate((0.4F - blocksWidth / 2.0F), (1.4F - entity.getHeight() / 2.0F - blocksHeight / 2.0F), (0.4F - blocksWidth / 2.0F));
/*    */         
/* 60 */         for (int x = 0; x < blocksWidth; x++) {
/*    */           
/* 62 */           for (int y = 0; y < blocksHeight; y++) {
/*    */             
/* 64 */             for (int z = 0; z < blocksWidth; z++) {
/*    */               
/* 66 */               matrixStack.push();
/* 67 */               matrixStack.translate(x, y, z);
/* 68 */               Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack((IItemProvider)blockToRender), ItemCameraTransforms.TransformType.HEAD, packedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
/* 69 */               matrixStack.pop();
/*    */             } 
/*    */           } 
/*    */         } 
/*    */         
/* 74 */         matrixStack.pop();
/*    */       } 
/*    */       
/* 77 */       if (effect.getOverlayColor() != null && effect.hasBodyOverlayColor()) {
/*    */         
/* 79 */         matrixStack.push();
/*    */         
/* 81 */         IVertexBuilder vertex = buffer.getBuffer(effect.getRenderType());
/* 82 */         if (effect.getResourceLocation(effectInstance.getDuration()) != null) {
/* 83 */           vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(effect.getResourceLocation(effectInstance.getDuration())));
/*    */         }
/* 85 */         getEntityModel().render(matrixStack, vertex, packedLight, 0, effect.getOverlayColor()[0], effect.getOverlayColor()[1], effect.getOverlayColor()[2], effect.getOverlayColor()[3]);
/*    */         
/* 87 */         matrixStack.pop();
/*    */       } 
/*    */     } 
/* 90 */     copyEffects.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\PotionLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */