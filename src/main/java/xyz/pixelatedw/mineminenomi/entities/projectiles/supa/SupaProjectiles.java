package xyz.pixelatedw.mineminenomi.entities.projectiles.supa;

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
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SupaProjectiles {
  public static final EntityType SPIRAL_HOLLOW = WyRegistry.createEntityType(SpiralHollowProjectile::new).size(1.0F, 1.0F).build("spiral_hollow");

  
  static {
    WyRegistry.registerEntityType(SPIRAL_HOLLOW, "Spiral Hollow");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(SPIRAL_HOLLOW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setColor("#F8F8FF").setScale(3.0D, 3.0D, 5.0D));
  }
}


