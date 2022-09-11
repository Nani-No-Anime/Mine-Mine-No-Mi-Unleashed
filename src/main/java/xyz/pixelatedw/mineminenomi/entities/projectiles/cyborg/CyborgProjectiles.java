package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
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
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CyborgProjectiles {
  public static final EntityType FRESH_FIRE = WyRegistry.createEntityType(FreshFireProjectile::new).size(3.0F, 3.0F).build("fresh_fire");
  public static final EntityType RADICAL_BEAM = WyRegistry.createEntityType(RadicalBeamProjectile::new).size(0.5F, 0.5F).build("radical_beam");
  public static final EntityType STRONG_RIGHT = WyRegistry.createEntityType(StrongRightProjectile::new).size(0.5F, 0.5F).build("strong_right");
  public static final EntityType COUP_DE_VENT = WyRegistry.createEntityType(CoupDeVentProjectile::new).size(5.5F, 5.5F).build("coup_de_vent");

  
  static {
    WyRegistry.registerEntityType(FRESH_FIRE, "Fresh Fire");
    WyRegistry.registerEntityType(RADICAL_BEAM, "Radical Beam");
    WyRegistry.registerEntityType(STRONG_RIGHT, "Strong Right");
    WyRegistry.registerEntityType(COUP_DE_VENT, "Coup de Vent");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(FRESH_FIRE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
    RenderingRegistry.registerEntityRenderingHandler(RADICAL_BEAM, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.0D, 1.0D).setGlowing().setColor("#FFFF55").setScale(1.0D, 1.0D, 3.0D));
    RenderingRegistry.registerEntityRenderingHandler(STRONG_RIGHT, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setColor("#336666").setScale(1.5D, 1.5D, 1.7D));
    RenderingRegistry.registerEntityRenderingHandler(COUP_DE_VENT, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
  }
}


