package xyz.pixelatedw.mineminenomi.renderers.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class PotionLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
  private static List<EffectInstance> copyEffects = new ArrayList<>();

  
  public PotionLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    copyEffects.addAll(entity.getActivePotionEffects());
    
    for (EffectInstance effectInstance : copyEffects) {
      
      if (!(effectInstance.getPotion() instanceof OverlayEffect)) {
        continue;
      }
      OverlayEffect effect = (OverlayEffect)effectInstance.getPotion();
      
      if (entity.isPotionActive((Effect)effect) && entity.getActivePotionEffect((Effect)effect).getDuration() <= 0) {
        entity.removePotionEffect((Effect)effect);
      }
      if (effect.getBlockOverlay() != null) {
        
        Block blockToRender = effect.getBlockOverlay();
        
        matrixStack.push();
        
        float blocksWidth = (float)(Math.ceil(entity.getWidth()) + 1.0D);
        float blocksHeight = (float)(Math.ceil(entity.getHeight()) + 1.0D);
        matrixStack.translate((0.4F - blocksWidth / 2.0F), (1.4F - entity.getHeight() / 2.0F - blocksHeight / 2.0F), (0.4F - blocksWidth / 2.0F));
        
        for (int x = 0; x < blocksWidth; x++) {
          
          for (int y = 0; y < blocksHeight; y++) {
            
            for (int z = 0; z < blocksWidth; z++) {
              
              matrixStack.push();
              matrixStack.translate(x, y, z);
              Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack((IItemProvider)blockToRender), ItemCameraTransforms.TransformType.HEAD, packedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
              matrixStack.pop();
            } 
          } 
        } 
        
        matrixStack.pop();
      } 
      
      if (effect.getOverlayColor() != null && effect.hasBodyOverlayColor()) {
        
        matrixStack.push();
        
        IVertexBuilder vertex = buffer.getBuffer(effect.getRenderType());
        if (effect.getResourceLocation(effectInstance.getDuration()) != null) {
          vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(effect.getResourceLocation(effectInstance.getDuration())));
        }
        getEntityModel().render(matrixStack, vertex, packedLight, 0, effect.getOverlayColor()[0], effect.getOverlayColor()[1], effect.getOverlayColor()[2], effect.getOverlayColor()[3]);
        
        matrixStack.pop();
      } 
    } 
    copyEffects.clear();
  }
}


