package xyz.pixelatedw.mineminenomi.events.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.items.SakeCupItem;



@EventBusSubscriber(modid = "mineminenomi")
public class SakeEvents
{
  @SubscribeEvent
  public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
    if (!(event.getTarget() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    
    if (player.getHeldItemMainhand().getItem() != ModItems.SAKE_BOTTLE) {
      return;
    }
    PlayerEntity target = (PlayerEntity)event.getTarget();
    
    if (target.getHeldItemMainhand().getItem() != ModItems.SAKE_CUP) {
      return;
    }
    IEntityStats props = EntityStatsCapability.get((LivingEntity)target);
    props.setFaction("pirate");
    ItemStack itemStack = target.getHeldItemMainhand();
    ((SakeCupItem)itemStack.getItem()).setLeader(itemStack, player);
    player.getHeldItemMainhand().damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));

    
    event.setCanceled(true);
  }
}


