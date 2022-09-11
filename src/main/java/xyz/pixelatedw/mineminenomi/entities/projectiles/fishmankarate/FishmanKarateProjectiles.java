package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;
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
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SharkModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SpearModel;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FishmanKarateProjectiles {
  public static final EntityType UCHIMIZU = WyRegistry.createEntityType(UchimizuProjectile::new).size(0.5F, 0.5F).build("uchimizu");
  public static final EntityType MURASAME = WyRegistry.createEntityType(MurasameProjectile::new).size(0.5F, 0.5F).build("murasame");
  public static final EntityType YARINAMI = WyRegistry.createEntityType(YarinamiProjectile::new).size(0.5F, 0.5F).build("yarinami");

  
  static {
    WyRegistry.registerEntityType(UCHIMIZU, "Uchimizu");
    WyRegistry.registerEntityType(MURASAME, "Murasame");
    WyRegistry.registerEntityType(YARINAMI, "Yarinami");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(UCHIMIZU, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#00CED1").setScale(0.5D, 0.5D, 1.0D));
    RenderingRegistry.registerEntityRenderingHandler(MURASAME, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SharkModel())).setTexture("murasame").setScale(0.8D, 0.8D, 1.2D));
    RenderingRegistry.registerEntityRenderingHandler(YARINAMI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SpearModel())).setColor("#00CED1").setScale(5.0D));
  }
}


