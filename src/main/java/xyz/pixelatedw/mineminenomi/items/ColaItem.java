package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public class ColaItem
  extends Item
{
  public static final int COLA_REFILL = 25;
  
  public ColaItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16).food(Foods.APPLE));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (!world.isRemote);















    
    player.setActiveHand(hand);
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
    if (!world.isRemote && entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
      
      if (props.isCyborg())
      {
        if (props.getCola() + 25 > props.getMaxCola()) {
          props.setCola(props.getMaxCola());
        } else {
          props.alterCola(25);
        } 
      }
      WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
      if (!player.isCreative()) {
        itemStack.shrink(1);
      }
    } 
    return itemStack;
  }


  
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.DRINK;
  }
}


