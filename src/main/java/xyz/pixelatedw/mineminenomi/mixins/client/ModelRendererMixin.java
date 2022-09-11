package xyz.pixelatedw.mineminenomi.mixins.client;

import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.Random;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;














@Mixin({ModelRenderer.class})
public class ModelRendererMixin
{
  @Shadow
  @Final
  private ObjectList<ModelRenderer.ModelBox> cubeList;
  @Shadow
  @Final
  private ObjectList<ModelRenderer> childModels;
  
  @Inject(method = {"getRandomCube"}, at = {@At("HEAD")}, cancellable = true)
  public void getRandomCube(Random random, CallbackInfoReturnable<ModelRenderer.ModelBox> callback) {
    if (this.cubeList.isEmpty())
    {
      if (!this.childModels.isEmpty())
      {
        for (ObjectListIterator<ModelRenderer> objectListIterator = this.childModels.iterator(); objectListIterator.hasNext(); ) { ModelRenderer renderer = objectListIterator.next();
          
          ModelRenderer.ModelBox box = renderer.getRandomCube(random);
          if (box != null) {
            
            callback.setReturnValue(box);
            return;
          }  }
      
      }
    }
  }
}


