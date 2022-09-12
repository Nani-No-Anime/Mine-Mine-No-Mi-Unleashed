package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;

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
public class SunaProjectiles {
  public static final EntityType BARJAN = WyRegistry.createEntityType(BarjanProjectile::new).size(2.0F, 2.0F).build("barjan");
  public static final EntityType DESERT_SPADA = WyRegistry.createEntityType(DesertSpadaProjectile::new).size(2.0F, 6.0F).build("desert_spada");
  
  public static final EntityType DESERT_GRANDE_SPADA = WyRegistry.createEntityType(DesertGrandeSpadaProjectile::new).size(2.0F, 6.0F).build("desert_grande_spada");
  public static final EntityType SABLES = WyRegistry.createEntityType(SablesProjectile::new).size(0.0F, 0.0F).build("sables");
  public static final EntityType SABLES_PESADO = WyRegistry.createEntityType(SablesPesadoProjectile::new).size(1.5F, 1.5F).build("sables_pesado");

  
  static {
    WyRegistry.registerEntityType(BARJAN, "Barjan");
    WyRegistry.registerEntityType(DESERT_SPADA, "Desert Spada");
    WyRegistry.registerEntityType(DESERT_GRANDE_SPADA, "Desert Grande Spada");
    WyRegistry.registerEntityType(SABLES, "Sables");
    WyRegistry.registerEntityType(SABLES_PESADO, "Sables Pesado");
  }


  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(BARJAN, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("C2B280").setScale(6.0D, 0.15D, 1.25D));
    RenderingRegistry.registerEntityRenderingHandler(DESERT_SPADA, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
    RenderingRegistry.registerEntityRenderingHandler(DESERT_GRANDE_SPADA, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("C2B280").setScale(18.0D, 0.45D, 3.75D));
    RenderingRegistry.registerEntityRenderingHandler(SABLES, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
    RenderingRegistry.registerEntityRenderingHandler(SABLES_PESADO, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(194.0D, 178.0D, 128.0D, 255.0D).setScale(6.0D));
  }
}


