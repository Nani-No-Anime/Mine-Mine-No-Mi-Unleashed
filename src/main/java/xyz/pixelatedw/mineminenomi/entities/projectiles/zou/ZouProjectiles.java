package xyz.pixelatedw.mineminenomi.entities.projectiles.zou;

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
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZouProjectiles {
  public static final EntityType TRUNK_SHOT = WyRegistry.createEntityType(TrunkShotProjectile::new).size(1.0F, 1.0F).build("trunk_shot");

  
  static {
    WyRegistry.registerEntityType(TRUNK_SHOT, "Trunk Shot");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(TRUNK_SHOT, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setTexture("zouskin").setScale(3.0D, 3.0D, 4.0D));
  }
}


