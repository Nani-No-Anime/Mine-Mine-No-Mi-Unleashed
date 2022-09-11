package xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon;
import java.util.function.Function;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RyuPteranodonProjectiles {
  public static final EntityType BARIZODON = WyRegistry.createEntityType(BarizodonProjectile::new).size(1.5F, 1.5F).build("barizodon");
  public static final EntityType TEMPURAUDON = WyRegistry.createEntityType(TempuraudonProjectile::new).size(0.5F, 0.5F).build("tempuraudon");

  
  static {
    WyRegistry.registerEntityType(BARIZODON, "Barizodon");
    WyRegistry.registerEntityType(TEMPURAUDON, "Tempuraudon");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(BARIZODON, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(2.0D, 0.3D, 2.0D));
    RenderingRegistry.registerEntityRenderingHandler(TEMPURAUDON, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.45D, 1.45D).setColor("#5b5b5b").setScale(1.0D, 1.0D, 1.0D));
  }
}


