package xyz.pixelatedw.mineminenomi.entities.projectiles.mato;
import java.util.function.Function;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MatoProjectiles {
  public static final EntityType TARGET_PROJECTILE = WyRegistry.createEntityType(TargetProjectile::new).size(2.0F, 0.5F).build("target_projectile");
  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent e) {}
}


