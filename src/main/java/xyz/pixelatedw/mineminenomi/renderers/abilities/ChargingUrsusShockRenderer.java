package xyz.pixelatedw.mineminenomi.renderers.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.ChargingUrsusShockEntity;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PawModel;

public class ChargingUrsusShockRenderer<T extends ChargingUrsusShockEntity>
  extends EntityRenderer<T>
{
  private PawModel model;
  
  protected ChargingUrsusShockRenderer(EntityRendererManager manager) {
    super(manager);
    this.model = new PawModel();
  }


  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    
    matrixStack.translate(0.0D, 1.5D, 0.0D);
    
    matrixStack.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.YP, ((ChargingUrsusShockEntity)entity).prevRotationYaw + (((ChargingUrsusShockEntity)entity).rotationYaw - ((ChargingUrsusShockEntity)entity).prevRotationYaw) * partialTicks - 180.0F, true));
    
    float size = 1.0F + entity.getCharge() / 2.0F;
    
    matrixStack.scale(size, size, size);
    
    RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
    IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
    this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.6F);
    
    matrixStack.push();
    
    double t = (((ChargingUrsusShockEntity)entity).ticksExisted * 3 % 100);
    double mirageSize = (t >= 50.0D) ? (2.0D - t / 100.0D) : (1.0D + t / 100.0D);
    float scale = (float)mirageSize;
    matrixStack.scale(scale, scale, scale);
    this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.2F);
    
    matrixStack.pop();
    
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(ChargingUrsusShockEntity entity) {
    return null;
  }



  
  public static class Factory
    implements IRenderFactory<ChargingUrsusShockEntity>
  {
    public EntityRenderer<? super ChargingUrsusShockEntity> createRenderFor(EntityRendererManager manager) {
      return new ChargingUrsusShockRenderer<>(manager);
    }
  }
}


