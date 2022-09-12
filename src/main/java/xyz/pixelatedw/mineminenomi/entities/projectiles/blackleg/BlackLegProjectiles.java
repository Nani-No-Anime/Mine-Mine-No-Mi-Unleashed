package xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg;

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
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityLegModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlackLegProjectiles {
  public static final EntityType EXTRA_HACHIS = WyRegistry.createEntityType(ExtraHachisProjectile::new).size(0.5F, 0.5F).build("extra_hachis");

  
  static {
    WyRegistry.registerEntityType(EXTRA_HACHIS, "Extra Hachis");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(EXTRA_HACHIS, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityLegModel())).setStretchScale(2.0D, 2.0D, 10.0D).setPlayerTexture());
  }
}


