package xyz.pixelatedw.mineminenomi.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.TaktBlockEntity;

import java.util.Random;

public class TaktBlockRenderer extends EntityRenderer<TaktBlockEntity> {
  public TaktBlockRenderer(EntityRendererManager renderManager) {
    super(renderManager);
    this.shadowSize = 0.5F;
  }


  
  public void render(TaktBlockEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
    BlockState blockstate = entityIn.getBlockState();
    if (blockstate.getRenderType() == BlockRenderType.MODEL) {
      
      World world = entityIn.getWorldObj();
      if (blockstate != world.getBlockState(new BlockPos((Entity)entityIn)) && blockstate.getRenderType() != BlockRenderType.INVISIBLE) {
        
        matrixStackIn.push();
        BlockPos blockpos = new BlockPos(entityIn.getPosX(), (entityIn.getBoundingBox()).maxY, entityIn.getPosZ());
        matrixStackIn.translate(-0.5D, 0.0D, -0.5D);
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
        for (RenderType type : RenderType.getBlockRenderTypes()) {
          
          if (RenderTypeLookup.canRenderInLayer(blockstate, type)) {
            
            ForgeHooksClient.setRenderLayer(type);
            blockrendererdispatcher.getBlockModelRenderer().renderModel((ILightReader)world, blockrendererdispatcher.getModelForState(blockstate), blockstate, blockpos, matrixStackIn, bufferIn.getBuffer(type), false, new Random(), blockstate.getPositionRandom(entityIn.getOrigin()), OverlayTexture.NO_OVERLAY);
          } 
        } 
        ForgeHooksClient.setRenderLayer(null);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
      } 
    } 
  }


  
  public ResourceLocation getEntityTexture(TaktBlockEntity entity) {
    return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
  }

  
  public static class Factory
    implements IRenderFactory<TaktBlockEntity>
  {
    public EntityRenderer<? super TaktBlockEntity> createRenderFor(EntityRendererManager manager) {
      return new TaktBlockRenderer(manager);
    }
  }
}


