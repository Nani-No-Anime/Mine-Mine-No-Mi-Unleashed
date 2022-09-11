package xyz.pixelatedw.mineminenomi.api.events;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;


public class RenderMorphEvent
  extends PlayerEvent
{
  private final PlayerRenderer renderer;
  private final float partialRenderTick;
  private final MatrixStack stack;
  private final IRenderTypeBuffer buffers;
  private final int light;
  
  public RenderMorphEvent(PlayerEntity player, ZoanMorphRenderer renderer, float partialRenderTick, MatrixStack stack, IRenderTypeBuffer buffers, int light) {
    super(player);
    this.renderer = (PlayerRenderer)renderer;
    this.partialRenderTick = partialRenderTick;
    this.stack = stack;
    this.buffers = buffers;
    this.light = light;
  }

  
  public PlayerRenderer getRenderer() {
    return this.renderer;
  }

  
  public float getPartialRenderTick() {
    return this.partialRenderTick;
  }

  
  public MatrixStack getMatrixStack() {
    return this.stack;
  }

  
  public IRenderTypeBuffer getBuffers() {
    return this.buffers;
  }

  
  public int getLight() {
    return this.light;
  }
  
  @Cancelable
  public static class Pre
    extends RenderMorphEvent
  {
    public Pre(PlayerEntity player, ZoanMorphRenderer renderer, float tick, MatrixStack stack, IRenderTypeBuffer buffers, int light) {
      super(player, renderer, tick, stack, buffers, light);
    }
  }
  
  public static class Post
    extends RenderMorphEvent
  {
    public Post(PlayerEntity player, ZoanMorphRenderer renderer, float tick, MatrixStack stack, IRenderTypeBuffer buffers, int light) {
      super(player, renderer, tick, stack, buffers, light);
    }
  }
}


