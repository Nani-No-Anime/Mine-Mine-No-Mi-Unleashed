package xyz.pixelatedw.mineminenomi.entities.projectiles.brawler;

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
public class BrawlerProjectiles {
  public static final EntityType KING_PUNCH = WyRegistry.createEntityType(KingPunchProjectile::new).size(0.5F, 0.5F).build("king_punch");

  
  static {
    WyRegistry.registerEntityType(KING_PUNCH, "King Punch");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(KING_PUNCH, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setTexture("gomugomunopistol").setScale(3.0D, 3.0D, 3.0D));
  }
}


