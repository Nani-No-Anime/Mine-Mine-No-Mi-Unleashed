package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.YagaraBullModel;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class YagaraBullRenderer<T extends YagaraBullEntity, M extends YagaraBullModel<T>> extends HumanoidRenderer<T, M> {
  public YagaraBullRenderer(EntityRendererManager manager) {
    super(manager, (M)new YagaraBullModel());
    this.scale = new float[] { 1.0F, 1.0F, 1.0F };
    this.shadowSize = 0.7F;
  }


  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    
    if (entity.isTamed()) {
      
      if (!entity.isSaddled()) {
        
        ((YagaraBullModel)this.entityModel).saddle.showModel = false;
        ((YagaraBullModel)this.entityModel).belt1.showModel = false;
        ((YagaraBullModel)this.entityModel).belt2.showModel = false;
      }
      else {
        
        ((YagaraBullModel)this.entityModel).saddle.showModel = true;
        ((YagaraBullModel)this.entityModel).belt1.showModel = true;
        ((YagaraBullModel)this.entityModel).belt2.showModel = true;
      } 
      
      matrixStack.push();
      
      matrixStack.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
      
      float ageInTicks = ((YagaraBullEntity)entity).ticksExisted + partialTicks;
      float headYawOffset = MathHelper.interpolateAngle(partialTicks, ((YagaraBullEntity)entity).prevRenderYawOffset, ((YagaraBullEntity)entity).renderYawOffset);
      
      WyHelper.rotateCorpse(matrixStack, (LivingEntity)entity, ageInTicks, headYawOffset, partialTicks);
      
      matrixStack.translate(0.0D, -1.5099999904632568D, 0.0D);
      
      ((YagaraBullModel)getEntityModel()).renderSaddle(matrixStack, buffer.getBuffer(RenderType.getEntitySolid(getEntityTexture(entity))), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      
      matrixStack.pop();
    } 
  }

  
  public static class Factory
    implements IRenderFactory
  {
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new YagaraBullRenderer<>(manager);
    }
  }
}


