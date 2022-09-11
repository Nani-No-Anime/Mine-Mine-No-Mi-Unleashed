package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;
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

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PeroProjectiles {
  public static final EntityType CANDY_ESCALATOR = WyRegistry.createEntityType(CandyEscalatorProjectile::new).size(0.5F, 0.5F).build("candy_escalator");
  public static final EntityType CANDY_MAIDEN = WyRegistry.createEntityType(CandyMaidenProjectile::new).size(2.5F, 7.5F).build("candy_maiden");
  public static final EntityType CANDY_WAVE = WyRegistry.createEntityType(CandyWaveProjectile::new).size(3.0F, 3.0F).build("candy_wave");

  
  static {
    WyRegistry.registerEntityType(CANDY_ESCALATOR, "Candy Escalator");
    WyRegistry.registerEntityType(CANDY_WAVE, "Candy Wave");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(CANDY_ESCALATOR, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
    RenderingRegistry.registerEntityRenderingHandler(CANDY_WAVE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
  }
}


