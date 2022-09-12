package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

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
public class ArtOfWeatherProjectiles {
  public static final EntityType HEAT_BALL = WyRegistry.createEntityType(HeatBallProjectile::new).size(0.5F, 0.5F).build("heat_ball");
  public static final EntityType COOL_BALL = WyRegistry.createEntityType(CoolBallProjectile::new).size(0.5F, 0.5F).build("cool_ball");
  public static final EntityType THUNDER_BALL = WyRegistry.createEntityType(ThunderBallProjectile::new).size(0.5F, 0.5F).build("thunder_ball");
  public static final EntityType GUST_SWORD = WyRegistry.createEntityType(GustSwordProjectile::new).size(0.5F, 0.5F).build("gust_sword");
  public static final EntityType WEATHER_EGG = WyRegistry.createEntityType(WeatherEggProjectile::new).size(0.5F, 0.5F).build("weather_egg");

  
  static {
    WyRegistry.registerEntityType(HEAT_BALL, "Heat Ball");
    WyRegistry.registerEntityType(COOL_BALL, "Cool Ball");
    WyRegistry.registerEntityType(THUNDER_BALL, "Thunder Ball");
    WyRegistry.registerEntityType(GUST_SWORD, "Gust Sword");
    WyRegistry.registerEntityType(WEATHER_EGG, "Weather Egg");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(HEAT_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#FF0000BB").setScale(2.0D, 2.0D, 2.0D));
    RenderingRegistry.registerEntityRenderingHandler(COOL_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#0000FFBB").setScale(2.0D, 2.0D, 2.0D));
    RenderingRegistry.registerEntityRenderingHandler(THUNDER_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#FFFF00BB").setScale(2.0D, 2.0D, 2.0D));
    RenderingRegistry.registerEntityRenderingHandler(GUST_SWORD, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D));
    RenderingRegistry.registerEntityRenderingHandler(WEATHER_EGG, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#8B8B8BBB").setScale(2.0D, 2.0D, 2.0D));
  }
}


