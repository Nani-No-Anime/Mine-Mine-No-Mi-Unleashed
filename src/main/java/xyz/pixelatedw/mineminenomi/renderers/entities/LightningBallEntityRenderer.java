package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningBallEntity;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class LightningBallEntityRenderer
  extends EntityRenderer<LightningBallEntity>
{
  private Color color;
  
  public LightningBallEntityRenderer(EntityRendererManager manager) {
    super(manager);

    
    this.color = new Color(641023);
  }

  
  public void render(LightningBallEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
    List<LightningRendererPropieties> entities = entity.entities;
    
    if (!Minecraft.getInstance().isGamePaused()) {
      
      if (entity.ticksExisted % 2 == 0) {
        entities.clear();
      }
      int lightnings = 64;
      
      if (entities.isEmpty())
      {
        for (int i = 0; i < lightnings; i++) {

          
          LightningRendererPropieties e = new LightningRendererPropieties((Entity)entity, entity.getSeed(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(0, 180), entity.getLightningLength());
          entities.add(e);
        } 
      }
    } 
    
    matrixStack.push();
    matrixStack.scale(entity.getSize() / 2.0F, entity.getSize() / 2.0F, entity.getSize() / 2.0F);
    entities.forEach(e -> LightningEntityRenderer.renderLightning(e.entity, e.random, e.length, e.rotationYaw, e.rotationPitch, this.color, 0.3F, matrixStack, bufferIn, 8));
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(LightningBallEntity entity) {
    return null;
  }

  
  public boolean shouldRender(LightningBallEntity livingEntityIn, ClippingHelperImpl camera, double camX, double camY, double camZ) {
    return true;
  }
  
  public static class LightningRendererPropieties
  {
    private Entity entity = null; float rotationYaw;
    float length = 0.0F; float rotationPitch;
    long random = 0L;
    
    public LightningRendererPropieties(Entity entity, long random, float rotationYaw, float rotationPitch, float length) {
      this.entity = entity;
      this.random = random;
      this.rotationPitch = rotationPitch;
      this.rotationYaw = rotationYaw;
      this.length = length;
    }
  }






  
  public static class Factory
    implements IRenderFactory<LightningBallEntity>
  {
    public EntityRenderer<? super LightningBallEntity> createRenderFor(EntityRendererManager manager) {
      return new LightningBallEntityRenderer(manager);
    }
  }
}


