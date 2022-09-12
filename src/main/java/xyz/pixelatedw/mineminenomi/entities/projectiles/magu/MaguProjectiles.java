package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;

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
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.MeigoModel;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MaguProjectiles {
  public static final EntityType DAI_FUNKA = WyRegistry.createEntityType(DaiFunkaProjectile::new).size(5.0F, 5.0F).build("dai_funka");
  public static final EntityType BAKURETSU_KAZAN = WyRegistry.createEntityType(RyuseiKazanProjectile::new).size(3.0F, 3.0F).build("bakuretsu_kazan");
  public static final EntityType MEIGO = WyRegistry.createEntityType(MeigoProjectile::new).size(0.5F, 0.5F).build("meigo");

  
  static {
    WyRegistry.registerEntityType(DAI_FUNKA, "Dai Funka");
    WyRegistry.registerEntityType(BAKURETSU_KAZAN, "Bakuretsu Kazan");
    WyRegistry.registerEntityType(MEIGO, "Meigo");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(DAI_FUNKA, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setTexture("daifunka").setScale(10.0D));
    RenderingRegistry.registerEntityRenderingHandler(BAKURETSU_KAZAN, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setTexture("daifunka").setScale(3.0D));
    RenderingRegistry.registerEntityRenderingHandler(MEIGO, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new MeigoModel())).setTexture("meigo").setScale(4.0D));
  }
}


