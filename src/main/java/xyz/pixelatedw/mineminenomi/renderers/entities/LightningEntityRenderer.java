package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.awt.Color;
import java.util.Random;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;



public class LightningEntityRenderer
  extends EntityRenderer<LightningEntity>
{
  public LightningEntityRenderer(EntityRendererManager manager) {
    super(manager);
  }

  
  public void render(LightningEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
    matrixStack.push();
    renderLightning(entity, partialTicks, matrixStack, bufferIn, packedLightIn);
    matrixStack.pop();
  }

  
  public static void renderLightning(LightningEntity entity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
    if (0 > entity.getSegments()) {
      return;
    }
    Random random = new Random(entity.seed);
    int angle = entity.getAngle();
    int segments = entity.getSegments();
    float length = entity.getLength() * Math.min((entity.ticksExisted + partialTicks) / 2.0F, 1.0F);
    float size = entity.getSize();
    int branches = entity.getBranches();
    float maxDistance = entity.getLength() / segments;
    Color rgb = new Color(entity.getColor());
    
    float[] arr = new float[segments];
    int targetNumber = (int)(segments * Math.min((entity.ticksExisted + partialTicks) / 2.0F, 1.0F));
    int renderTime = entity.getAliveTicks() / 2;
    
    float alpha = (entity.ticksExisted < renderTime) ? 0.3F : Math.max(0.3F * (1.0F - ((entity.ticksExisted - renderTime) + partialTicks) / renderTime), 0.0F);
    
    for (int segment = 0; segment < arr.length; segment++) {
      arr[segment] = (segment == targetNumber) ? (length - maxDistance * segment) : maxDistance;
    }
    float[] offsetsY = new float[segments];
    float[] offsetsX = new float[segments];
    
    IVertexBuilder vertex = bufferIn.getBuffer(entity.getEnergyEffect() ? ModRenderTypes.ENERGY : ModRenderTypes.SOLID);
    
    Matrix4f matrix4f = matrixStack.getLast().getMatrix();
    
    matrixStack.rotate(Vector3f.YN.rotationDegrees(entity.getYaw(partialTicks)));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(entity.getPitch(partialTicks)));
    matrixStack.translate(0.0D, 0.0D, 0.1D);

    
    for (int i = 0; i < branches; i++) {
      float lastOffsetY = 0.0F;
      float lastOffsetX = 0.0F;
      
      int j;
      for (j = 0; j < segments; j++) {
        
        offsetsY[j] = lastOffsetY;
        offsetsX[j] = lastOffsetX;
        lastOffsetY = (float)(lastOffsetY + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
        lastOffsetX = (float)(lastOffsetX + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
      } 
      
      for (j = 0; j < segments; j++) {
        
        float y = offsetsY[j];
        float x = offsetsX[j];
        
        for (int depth = 1; depth < 4; depth++) {
          
          float depthY = size / 2.0F + depth * size;
          float depthZ = size / 2.0F + depth * size;
          
          float endY = (j == segments - 1) ? y : offsetsY[j + 1];
          float endX = (j == segments - 1) ? x : offsetsX[j + 1];
          if (j <= targetNumber) {
            
            drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, false, true, false, maxDistance, arr[j], packedLightIn);
            drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, false, true, true, maxDistance, arr[j], packedLightIn);
            drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, true, false, true, maxDistance, arr[j], packedLightIn);
            drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, true, false, false, maxDistance, arr[j], packedLightIn);
          } 
        } 
      } 
    } 
  }

  
  private static void drawQuad(Matrix4f matrix4f, IVertexBuilder vertex, float startY, float startX, int segmentIndex, float endY, float endX, float red, float green, float blue, float alpha, float firstOffset, float secondOffset, boolean negativeOffset, boolean bl2, boolean bl3, boolean bl4, float segmentLength, float segmentLengthAdded, int light) {
    vertex.pos(matrix4f, startX + (bl2 ? secondOffset : -secondOffset), startY + (negativeOffset ? secondOffset : -secondOffset), segmentIndex * segmentLength).color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha).lightmap(light).endVertex();
    vertex.pos(matrix4f, endX + (bl2 ? firstOffset : -firstOffset), endY + (negativeOffset ? firstOffset : -firstOffset), segmentIndex * segmentLength + segmentLengthAdded).color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha).lightmap(light).endVertex();
    vertex.pos(matrix4f, endX + (bl4 ? firstOffset : -firstOffset), endY + (bl3 ? firstOffset : -firstOffset), segmentIndex * segmentLength + segmentLengthAdded).color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha).lightmap(light).endVertex();
    vertex.pos(matrix4f, startX + (bl4 ? secondOffset : -secondOffset), startY + (bl3 ? secondOffset : -secondOffset), segmentIndex * segmentLength).color(red / 255.0F, green / 255.0F, blue / 255.0F, alpha).lightmap(light).endVertex();
  }

  
  public static void renderLightning(Entity entity, long seed, float len, float pitch, float yaw, Color rgb, float alpha, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
    float partialTicks = 1.0F;
    Random random = new Random(seed);
    int angle = 90;
    int segments = 6;
    float size = 0.02F;
    int branches = 4;
    float length = len * Math.min((entity.ticksExisted + partialTicks) / 2.0F, 1.0F);
    float maxDistance = length / segments;
    
    float[] arr = new float[segments];
    int targetNumber = (int)(segments * Math.min((entity.ticksExisted + partialTicks) / 2.0F, 1.0F));
    
    for (int segment = 0; segment < arr.length; segment++) {
      arr[segment] = (segment == targetNumber) ? (length - maxDistance * segment) : maxDistance;
    }
    float[] offsetsY = new float[segments];
    float[] offsetsX = new float[segments];
    IVertexBuilder vertex = bufferIn.getBuffer(ModRenderTypes.ENERGY);
    Matrix4f matrix4f = matrixStack.getLast().getMatrix();
    
    matrixStack.rotate(Vector3f.YN.rotationDegrees(pitch));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(yaw));


    
    for (int i = 0; i < branches; i++) {
      float lastOffsetY = 0.0F;
      float lastOffsetX = 0.0F;
      
      int j;
      for (j = 0; j < segments; j++) {
        
        offsetsY[j] = lastOffsetY;
        offsetsX[j] = lastOffsetX;
        lastOffsetY = (float)(lastOffsetY + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
        lastOffsetX = (float)(lastOffsetX + Math.sin(Math.toRadians((random.nextInt(angle) - angle / 2.0F))));
      } 
      
      for (j = 0; j < segments; j++) {
        
        float y = offsetsY[j];
        float x = offsetsX[j];
        
        for (int depth = 1; depth < 4; depth++) {
          
          float depthY = size / 2.0F + depth * size;
          float depthZ = size / 2.0F + depth * size;
          
          float endY = (j == segments - 1) ? y : offsetsY[j + 1];
          float endX = (j == segments - 1) ? x : offsetsX[j + 1];
          if (j <= targetNumber) {
            
            drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, false, true, false, maxDistance, arr[j], packedLightIn);
            drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, false, true, true, maxDistance, arr[j], packedLightIn);
            drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, true, true, false, true, maxDistance, arr[j], packedLightIn);
            drawQuad(matrix4f, vertex, y, x, j, endY, endX, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha, depthY, depthZ, false, true, false, false, maxDistance, arr[j], packedLightIn);
          } 
        } 
      } 
    } 
  }


  
  public ResourceLocation getEntityTexture(LightningEntity entity) {
    return null;
  }

  
  public boolean shouldRender(LightningEntity livingEntityIn, ClippingHelperImpl camera, double camX, double camY, double camZ) {
    return true;
  }







  
  public static class Factory
    implements IRenderFactory<LightningEntity>
  {
    public EntityRenderer<? super LightningEntity> createRenderFor(EntityRendererManager manager) {
      return new LightningEntityRenderer(manager);
    }
  }
}


