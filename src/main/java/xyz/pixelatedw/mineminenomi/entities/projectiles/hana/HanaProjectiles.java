package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;

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
import xyz.pixelatedw.mineminenomi.renderers.abilities.EmptyRenderer;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityLegModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanaProjectiles {
  public static final EntityType HANDS = WyRegistry.createEntityType(HanaHandsEntity::new).size(2.0F, 2.0F).build("hands");
  public static final EntityType FEET = WyRegistry.createEntityType(HanaFeetEntity::new).size(2.0F, 2.0F).build("feet");
  
  public static final EntityType GENERIC_HANA = WyRegistry.createEntityType(HanaGenericEntity::new).size(0.5F, 0.5F).build("generic_hana");

  
  static {
    WyRegistry.registerEntityType(HANDS, "Hands");
    WyRegistry.registerEntityType(FEET, "Feet");
    WyRegistry.registerEntityType(GENERIC_HANA, "Hana Projectile");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(HANDS, (IRenderFactory)new EmptyRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(FEET, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new EntityLegModel())).setPlayerTexture().setScale(8.0D));
    
    RenderingRegistry.registerEntityRenderingHandler(GENERIC_HANA, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D));
  }
}


