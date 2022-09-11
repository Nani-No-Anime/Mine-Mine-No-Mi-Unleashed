package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;
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
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.HydraModel;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DokuProjectiles {
  public static final EntityType HYDRA = WyRegistry.createEntityType(HydraProjectile::new).size(1.0F, 1.0F).build("hydra");
  public static final EntityType DEMON_HYDRA = WyRegistry.createEntityType(DemonHydraProjectile::new).size(1.5F, 1.5F).build("demon_hydra");
  public static final EntityType CHLORO_BALL = WyRegistry.createEntityType(ChloroBallProjectile::new).size(0.75F, 0.75F).build("chloro_ball");
  public static final EntityType DEMON_CHLORO_BALL = WyRegistry.createEntityType(DemonChloroBallProjectile::new).size(1.4F, 1.4F).build("demon_chloro_ball");

  
  static {
    WyRegistry.registerEntityType(HYDRA, "Hydra");
    WyRegistry.registerEntityType(DEMON_HYDRA, "Demon Hydra");
    WyRegistry.registerEntityType(CHLORO_BALL, "Chloro Ball");
    WyRegistry.registerEntityType(DEMON_CHLORO_BALL, "Demon Chloro Ball");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(HYDRA, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new HydraModel())).setTexture("hydra").setScale(1.75D, 1.75D, 3.5D));
    RenderingRegistry.registerEntityRenderingHandler(DEMON_HYDRA, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new HydraModel())).setTexture("hydra").setColor(255.0D, 0.0D, 0.0D, 255.0D).setScale(2.5D, 2.5D, 4.0D));
    RenderingRegistry.registerEntityRenderingHandler(CHLORO_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#A020F0").setScale(4.0D));
    RenderingRegistry.registerEntityRenderingHandler(DEMON_CHLORO_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#5A0000").setScale(8.0D));
  }
}


