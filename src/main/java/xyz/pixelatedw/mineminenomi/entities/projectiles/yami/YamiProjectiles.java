package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;

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
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class YamiProjectiles {
  public static final EntityType LIBERATION = WyRegistry.createEntityType(LiberationProjectile::new).size(1.0F, 1.0F).build("liberation");
  public static final EntityType DARK_MATTER = WyRegistry.createEntityType(DarkMatterProjectile::new).size(1.0F, 1.0F).build("dark_matter");
  public static final EntityType BLACK_ROAD = WyRegistry.createEntityType(BlackRoadProjectile::new).size(1.0F, 1.0F).build("black_road");

  
  static {
    WyRegistry.registerEntityType(LIBERATION, "Liberation");
    WyRegistry.registerEntityType(DARK_MATTER, "Dark Matter");
    WyRegistry.registerEntityType(BLACK_ROAD, "Black Road");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(LIBERATION, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#000000").setScale(1.5D));
    RenderingRegistry.registerEntityRenderingHandler(DARK_MATTER, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#000000").setScale(9.0D));
    RenderingRegistry.registerEntityRenderingHandler(BLACK_ROAD, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
  }
}


