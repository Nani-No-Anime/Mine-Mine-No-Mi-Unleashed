package xyz.pixelatedw.mineminenomi.entities.projectiles.beta;
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
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetaProjectiles {
  public static final EntityType STICKY_PROJECTILE = WyRegistry.createEntityType(StickyProjectile::new).size(0.5F, 0.5F).build("sticky");
  public static final EntityType BETA_BETA_CHAIN = WyRegistry.createEntityType(BetaBetaChainProjectile::new).size(0.5F, 0.5F).build("beta_beta_chain");

  
  static {
    WyRegistry.registerEntityType(STICKY_PROJECTILE, "Mucus Bomb");
    WyRegistry.registerEntityType(BETA_BETA_CHAIN, "Beta Chain");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(STICKY_PROJECTILE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setTexture(ModResources.BETA_COATING).setColor(0.0D, 255.0D, 0.0D, 100.0D).setScale(0.5D));
    RenderingRegistry.registerEntityRenderingHandler(BETA_BETA_CHAIN, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.4D, 1.4D, 10.0D).setColor("#269B20").setScale(1.0D, 1.0D, 1.0D));
  }
}


