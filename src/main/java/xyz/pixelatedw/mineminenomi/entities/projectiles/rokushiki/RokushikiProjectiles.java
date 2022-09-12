package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;

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
public class RokushikiProjectiles {
  public static final EntityType RANKYAKU = WyRegistry.createEntityType(RankyakuProjectile::new).size(5.25F, 0.5F).build("rankyaku");
  public static final EntityType ROKUOGAN = WyRegistry.createEntityType(RokuoganProjectile::new).size(3.0F, 3.0F).build("rokuogan");

  
  static {
    WyRegistry.registerEntityType(RANKYAKU, "Rankyaku");
    WyRegistry.registerEntityType(ROKUOGAN, "Rokuogan");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(RANKYAKU, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#B1B1E1").setScale(12.0D, 0.5D, 1.0D));
    RenderingRegistry.registerEntityRenderingHandler(ROKUOGAN, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
  }
}


