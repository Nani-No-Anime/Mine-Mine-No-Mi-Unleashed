package xyz.pixelatedw.mineminenomi.entities.projectiles.bane;

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
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityArmModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BaneProjectiles {
  public static final EntityType SPRING_DEATH_KNOCK = WyRegistry.createEntityType(SpringDeathKnockProjectile::new).size(1.5F, 1.5F).build("spring_death_knock");

  
  static {
    WyRegistry.registerEntityType(SPRING_DEATH_KNOCK, "Spring Death Knock");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(SPRING_DEATH_KNOCK, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setColor("#A8A8A8"));
  }
}


