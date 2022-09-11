package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;


public class DummyRenderer<T extends LivingEntity, M extends EntityModel<T>>
  extends LivingRenderer<T, M>
{
  private ResourceLocation texture;
  private float size;
  
  public DummyRenderer(EntityRendererManager manager, M model, String texture, float size) {
    super(manager, model, 0.8F);
    this.texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/" + texture + ".png");
    this.size = size;
  }


  
  protected void preRenderCallback(T entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.scale(this.size, this.size, this.size);
  }


  
  public ResourceLocation getEntityTexture(T entity) {
    return this.texture;
  }


  
  protected boolean canRenderName(T entity) {
    return false;
  }
  
  public static class Factory<M extends EntityModel>
    implements IRenderFactory {
    private M model;
    private String texture;
    private float size = 1.0F;

    
    public Factory(M model, String texture) {
      this(model, texture, 1.0F);
    }

    
    public Factory(M model, String texture, float size) {
      this.model = model;
      this.texture = texture;
      this.size = size;
    }


    
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new DummyRenderer<>(manager, (EntityModel<LivingEntity>)this.model, this.texture, this.size);
    }
  }
}


