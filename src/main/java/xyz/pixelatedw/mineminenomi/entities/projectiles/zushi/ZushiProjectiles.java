package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;
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
public class ZushiProjectiles {
  public static final EntityType SAGARI_NO_RYUSEI = WyRegistry.createEntityType(SagariNoRyuseiProjectile::new).size(8.0F, 8.0F).build("sagari_no_ryusei");
  public static final EntityType MOKO = WyRegistry.createEntityType(MokoProjectile::new).size(1.0F, 1.0F).build("moko");

  
  static {
    WyRegistry.registerEntityType(SAGARI_NO_RYUSEI, "Sagari no Ryusei");
    WyRegistry.registerEntityType(MOKO, "Moko");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(SAGARI_NO_RYUSEI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#51585B").setScale(30.0D));
    RenderingRegistry.registerEntityRenderingHandler(MOKO, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D));
  }
}


