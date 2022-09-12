package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;

import com.google.common.base.Function;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.apache.commons.lang3.tuple.Triple;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;

import java.awt.*;
import java.util.Random;

public class GlowingModelRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel>
  extends ZoanMorphRenderer<T, M> {
  protected Type type;
  
  public GlowingModelRenderer(EntityRendererManager rendererManager, ZoanInfo info, Type type) {
    super(rendererManager, info);
    addLayer((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
    this.type = type;
  }


  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    
    matrixStack.push();
    matrixStack.translate(0.0D, 2.6D, 0.0D);
    
    int lightLevel = entity.getEntityWorld().getLight(entity.getPosition());
    if (this.type == Type.AMARU)
    {
      lightLevel = 8 + entity.getEntityWorld().getLight(entity.getPosition());
    }








    
    if (lightLevel > 7) {
      
      Random random = new Random(500L);
      float rays = (20 + lightLevel * 16);
      float randMovement = (entity.ticksExisted + partialTicks) / 500.0F;
      
      IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.getLightning());
      matrixStack.push();
      matrixStack.translate(0.0D, 0.75D, 0.0D);
      
      for (int i = 0; i < rays; i++) {
        
        float size = 9.6F + ((this.type == Type.AMARU) ? 0.0F : 2.4F);
        matrixStack.rotate(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
        matrixStack.rotate(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F));
        matrixStack.rotate(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
        matrixStack.rotate(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F + randMovement * 90.0F));
        float f3 = size * random.nextFloat();
        float f4 = size * random.nextFloat();
        Matrix4f matrix4f = matrixStack.getLast().getMatrix();
        
        int alpha = 8 + lightLevel / 2;
        
        Color color1 = (Color)this.type.getColorScheme(alpha).getRight();
        Color color2 = (Color)this.type.getColorScheme(alpha).getMiddle();
        Color color3 = (Color)this.type.getColorScheme(alpha).getLeft();
        
        RendererHelper.drawA(vertexBuilder, matrix4f, color3);
        RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, color2);
        RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, color1);
        RendererHelper.drawA(vertexBuilder, matrix4f, color3);
        RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, color2);
        RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, color1);
        RendererHelper.drawA(vertexBuilder, matrix4f, color3);
        RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, color1);
        RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, color2);
      } 













      
      matrixStack.pop();
    } 
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
    return entity.getLocationSkin();
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    private GlowingModelRenderer.Type type;
    
    public Factory(ZoanInfo info, GlowingModelRenderer.Type type) {
      this.info = info;
      this.type = type;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      GlowingModelRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new GlowingModelRenderer<>(manager, this.info, this.type);
      return (EntityRenderer)renderer;
    }
  }




  
  public enum Type
  {
    AMARU((alpha -> Triple.of(new Color(0, 249, 255, 0), new Color(0, 100, 255, 0), new Color(125, 255, 255, 2)))), 
              DAIBUTSU((alpha -> Triple.of(new Color(0, 249, 255, 0), new Color(0, 100, 255, 0), new Color(125, 255, 255, 2))));
    private Function<Integer, Triple> colorScheme;
    
    
    Type(Function<Integer, Triple> colorScheme) {
      this.colorScheme = colorScheme;
    }

    
    public Triple<Color, Color, Color> getColorScheme(int alpha) {
      return (Triple<Color, Color, Color>)this.colorScheme.apply(Integer.valueOf(alpha));
    }
  }
}


