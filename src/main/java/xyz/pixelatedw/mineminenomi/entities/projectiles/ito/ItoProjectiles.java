package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;

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
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItoProjectiles {
  public static final EntityType OVERHEAT = WyRegistry.createEntityType(OverheatProjectile::new).size(2.0F, 2.0F).build("overheat");
  public static final EntityType TAMAITO = WyRegistry.createEntityType(TamaitoProjectile::new).size(0.5F, 0.5F).build("tamaito");
  public static final EntityType STRING_PILLAR = WyRegistry.createEntityType(StringPillarProjectile::new).size(1.0F, 10.0F).build("string");

  
  static {
    WyRegistry.registerEntityType(OVERHEAT, "Overheat");
    WyRegistry.registerEntityType(TAMAITO, "Tamaito");
    WyRegistry.registerEntityType(STRING_PILLAR, "String Pillar");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(OVERHEAT, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(0.5D, 0.5D).setColor("#f77c25").setScale(0.5D, 0.5D, 5.0D));
    RenderingRegistry.registerEntityRenderingHandler(TAMAITO, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#dee1e5").setScale(0.5D));
    RenderingRegistry.registerEntityRenderingHandler(STRING_PILLAR, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#dee1e5").setScale(0.5D, 0.5D, 20.0D));
  }
}


