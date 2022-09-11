package xyz.pixelatedw.mineminenomi.mixins.client;

import java.util.List;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({LivingRenderer.class})
public interface ILivingRendererMixin<T extends net.minecraft.entity.LivingEntity, M extends net.minecraft.client.renderer.entity.model.EntityModel<T>> {
  @Accessor("layerRenderers")
  List<LayerRenderer<T, M>> getLayers();
}


