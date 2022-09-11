package xyz.pixelatedw.mineminenomi.renderers.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.models.abilities.AbareHimatsuriModel;

@OnlyIn(Dist.CLIENT)
public class AbareHimatsuriRenderer
  extends EntityRenderer
{
  private ResourceLocation texture = new ResourceLocation("minecraft:textures/block/dirt.png");
  
  private AbareHimatsuriModel model;
  
  public AbareHimatsuriRenderer(EntityRendererManager renderManager, EntityModel model) {
    super(renderManager);
    this.shadowSize = 0.0F;
    this.model = (AbareHimatsuriModel)model;
  }


  
  public void render(Entity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    
    matrixStack.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
    
    RenderType type = RenderType.getEntityCutoutNoCull(this.texture);
    IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
    this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(Entity entity) {
    return this.texture;
  }
  
  public static class Factory
    implements IRenderFactory<EntityCloud>
  {
    private AbareHimatsuriModel model;
    
    public Factory(AbareHimatsuriModel model) {
      this.model = model;
    }


    
    public EntityRenderer<? super Entity> createRenderFor(EntityRendererManager manager) {
      return new AbareHimatsuriRenderer(manager, (EntityModel)this.model);
    }
  }
}


