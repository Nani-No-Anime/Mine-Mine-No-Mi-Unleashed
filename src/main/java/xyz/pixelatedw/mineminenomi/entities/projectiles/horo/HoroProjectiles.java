package xyz.pixelatedw.mineminenomi.entities.projectiles.horo;
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
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.MiniHollowModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NegativeHollowModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TokuHollowModel;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HoroProjectiles {
  public static final EntityType NEGATIVE_HOLLOW = WyRegistry.createEntityType(NegativeHollowProjectile::new).size(0.5F, 0.5F).build("negative_hollow");
  public static final EntityType MINI_HOLLOW = WyRegistry.createEntityType(MiniHollowProjectile::new).size(0.5F, 0.5F).build("mini_hollow");
  public static final EntityType TOKU_HOLLOW = WyRegistry.createEntityType(TokuHollowProjectile::new).size(1.0F, 1.0F).build("toku_hollow");

  
  static {
    WyRegistry.registerEntityType(NEGATIVE_HOLLOW, "Negative Hollow");
    WyRegistry.registerEntityType(MINI_HOLLOW, "Mini Hollow");
    WyRegistry.registerEntityType(TOKU_HOLLOW, "Toku Hollow");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(NEGATIVE_HOLLOW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new NegativeHollowModel())).setTexture("negativehollow").setAlpha(120.0D).setScale(2.0D));
    RenderingRegistry.registerEntityRenderingHandler(MINI_HOLLOW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new MiniHollowModel())).setColor("#F8F8FF").setAlpha(120.0D));
    RenderingRegistry.registerEntityRenderingHandler(TOKU_HOLLOW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new TokuHollowModel())).setTexture("tokuhollow").setAlpha(120.0D).setScale(4.0D));
  }
}


