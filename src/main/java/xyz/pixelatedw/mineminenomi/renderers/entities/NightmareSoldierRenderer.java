package xyz.pixelatedw.mineminenomi.renderers.entities;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.NightmareSoldierEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.NightmareSoldierLayer;

@OnlyIn(Dist.CLIENT)
public class NightmareSoldierRenderer extends HumanoidRenderer<NightmareSoldierEntity, BipedModel<NightmareSoldierEntity>> {
  public NightmareSoldierRenderer(EntityRendererManager renderManager) {
    super(renderManager, new HumanoidModel());
    addLayer(new NightmareSoldierLayer((IEntityRenderer)this));
  }


  
  public void render(NightmareSoldierEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }





  
  public static class Factory
    implements IRenderFactory<NightmareSoldierEntity>
  {
    public EntityRenderer<? super NightmareSoldierEntity> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer<? super NightmareSoldierEntity>)new NightmareSoldierRenderer(manager);
    }
  }
}


