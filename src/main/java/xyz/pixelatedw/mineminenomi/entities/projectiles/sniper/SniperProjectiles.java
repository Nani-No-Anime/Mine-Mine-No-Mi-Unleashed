package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
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
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SniperProjectiles {
  public static final EntityType KAEN_BOSHI = WyRegistry.createEntityType(KaenBoshiProjectile::new).size(0.5F, 0.5F).build("kaen_boshi");
  public static final EntityType KEMURI_BOSHI = WyRegistry.createEntityType(KemuriBoshiProjectile::new).size(0.5F, 0.5F).build("kemuri_boshi");
  public static final EntityType RENPATSU_NAMARI_BOSHI = WyRegistry.createEntityType(RenpatsuNamariBoshiProjectile::new).size(0.5F, 0.5F).build("renpatsu_namari_boshi");
  public static final EntityType SAKURETSU_SABOTEN_BOSHI = WyRegistry.createEntityType(SakuretsuSabotenBoshiProjectile::new).size(0.5F, 0.5F).build("sakuretsu_saboten_boshi");
  public static final EntityType TETSU_BOSHI = WyRegistry.createEntityType(TetsuBoshiProjectile::new).size(0.5F, 0.5F).build("tetsu_boshi");
  public static final EntityType TOKUYO_ABURA_BOSHI = WyRegistry.createEntityType(TokuyoAburaBoshiProjectile::new).size(0.5F, 0.5F).build("tokuya_abura_boshi");
  public static final EntityType NEMURI_BOSHI = WyRegistry.createEntityType(NemuriBoshiProjectile::new).size(0.5F, 0.5F).build("nemuri_boshi");

  
  static {
    WyRegistry.registerEntityType(KAEN_BOSHI, "Kaen Boshi");
    WyRegistry.registerEntityType(KEMURI_BOSHI, "Kemuri Boshi");
    WyRegistry.registerEntityType(RENPATSU_NAMARI_BOSHI, "Renpatsu Namari Boshi");
    WyRegistry.registerEntityType(SAKURETSU_SABOTEN_BOSHI, "Sakuretsu Saboten Boshi");
    WyRegistry.registerEntityType(TETSU_BOSHI, "Tetsu Boshi");
    WyRegistry.registerEntityType(TOKUYO_ABURA_BOSHI, "Tokuyo Abura Boshi");
    WyRegistry.registerEntityType(NEMURI_BOSHI, "Nemuri Boshi");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(KAEN_BOSHI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#63110E"));
    RenderingRegistry.registerEntityRenderingHandler(KEMURI_BOSHI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#C0C0C0"));
    RenderingRegistry.registerEntityRenderingHandler(RENPATSU_NAMARI_BOSHI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#C0C0C0"));
    RenderingRegistry.registerEntityRenderingHandler(SAKURETSU_SABOTEN_BOSHI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#437C17"));
    RenderingRegistry.registerEntityRenderingHandler(TETSU_BOSHI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#C0C0C0"));
    RenderingRegistry.registerEntityRenderingHandler(TOKUYO_ABURA_BOSHI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#C0C0C0"));
    RenderingRegistry.registerEntityRenderingHandler(NEMURI_BOSHI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#FF88FF"));
  }
}


