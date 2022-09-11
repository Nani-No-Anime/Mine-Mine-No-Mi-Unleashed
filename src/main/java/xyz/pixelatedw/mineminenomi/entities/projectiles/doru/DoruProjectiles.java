package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;
import java.util.function.Function;
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
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SpearModel;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DoruProjectiles {
  public static final EntityType DORU_DORU_ARTS_MORI = WyRegistry.createEntityType(DoruDoruArtsMoriProjectile::new).size(0.5F, 0.5F).build("doru_doru_arts_mori");
  public static final EntityType CANDLE_LOCK = WyRegistry.createEntityType(CandleLockProjectile::new).size(0.5F, 0.5F).build("candle_lock");
  public static final EntityType CHAMP_FIGHT = WyRegistry.createEntityType(ChampFightProjectile::new).size(0.5F, 0.5F).build("champ_fight");

  
  static {
    WyRegistry.registerEntityType(DORU_DORU_ARTS_MORI, "Doru Doru Arts: Mori");
    WyRegistry.registerEntityType(CANDLE_LOCK, "Candle Lock");
    WyRegistry.registerEntityType(CHAMP_FIGHT, "Champ Fight");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(DORU_DORU_ARTS_MORI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SpearModel())).setScale(2.0D).setTexture("dorudoruartsmori"));
    RenderingRegistry.registerEntityRenderingHandler(CANDLE_LOCK, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
    RenderingRegistry.registerEntityRenderingHandler(CHAMP_FIGHT, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(2.0D).setColor("#C3C3C3"));
  }
}


