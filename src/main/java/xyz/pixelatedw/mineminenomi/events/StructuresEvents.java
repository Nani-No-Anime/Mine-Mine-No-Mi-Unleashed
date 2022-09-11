package xyz.pixelatedw.mineminenomi.events;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;




@EventBusSubscriber(modid = "mineminenomi")
public class StructuresEvents
{
  @SubscribeEvent
  public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (event.getEntity() instanceof net.minecraft.entity.player.PlayerEntity)
    {
      
      StructuresHelper.SPAWNED_STRUCTURES.clear();
    }
  }
}


