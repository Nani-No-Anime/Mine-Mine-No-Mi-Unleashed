package xyz.pixelatedw.mineminenomi.entities.projectiles.noro;

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
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NoroNoroBeamModel;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NoroProjectiles {
  public static final EntityType NORO_NORO_BEAM = WyRegistry.createEntityType(NoroNoroBeamProjectile::new).size(1.5F, 1.5F).build("noro_noro_beam");

  
  static {
    WyRegistry.registerEntityType(NORO_NORO_BEAM, "Noro Noro Beam");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(NORO_NORO_BEAM, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new NoroNoroBeamModel())).setTexture("noronorobeam").setScale(5.0D));
  }
}


