package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileBlockEvent;





@EventBusSubscriber(modid = "mineminenomi")
public class AbilityProjectileEvents
{
  @SubscribeEvent
  public static void onBlockCheck(ProjectileBlockEvent event) {
    if (event.getProjectile() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile || event.getProjectile() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile)
    {
      event.setCanBlock(true);
    }
  }
}


