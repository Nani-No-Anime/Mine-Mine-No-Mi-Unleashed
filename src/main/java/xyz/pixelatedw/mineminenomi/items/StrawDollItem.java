package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Iterator;
import java.util.UUID;


public class StrawDollItem
  extends Item
  implements IDyeableArmorItem
{
  public StrawDollItem() {
    super((new Item.Properties()).maxStackSize(1));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (world.isRemote) {
      return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
    ItemStack itemStack = player.getHeldItem(hand);
    
    if (getOwner(world, player.getPosition(), itemStack) == null) {
      player.inventory.deleteStack(itemStack);
    }
    LivingEntity owner = getOwner(world, player.getPosition(), itemStack);
    
    if (owner == null) {
      
      player.inventory.deleteStack(itemStack);
      return ActionResult.resultSuccess(itemStack);
    } 
    
    IEntityStats props = EntityStatsCapability.get(owner);
    
    if (props.hasStrawDoll()) {
      
      player.inventory.deleteStack(itemStack);
      return ActionResult.resultSuccess(itemStack);
    } 
    
    if (itemStack.getOrCreateTag() != null)
    {
      if (owner == player) {
        
        props.setStrawDoll(true);
        WyNetwork.sendToServer(new SSyncEntityStatsPacket(player.getEntityId(), props));
        player.inventory.deleteStack(itemStack);
      } 
    }
    
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }

  
  @Nullable
  public LivingEntity getOwner(World world, BlockPos pos, ItemStack itemStack) {
    UUID uuid = itemStack.getOrCreateTag().getUniqueId("ownerUUID");
    return (LivingEntity)((ServerWorld)world).getEntityByUuid(uuid);
  }


  
  public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem) {
    if (entityItem.world.isRemote) {
      return false;
    }
    if (itemStack.getTag() != null) {
      
      LivingEntity target = getOwner(entityItem.world, entityItem.getPosition(), itemStack);
      if (target != null) {
        
        boolean isBurning = entityItem.isBurning();
        boolean isInVoid = (entityItem.getPosition().getY() < -1);
        
        if (isBurning || isInVoid) {
          
          Iterator<ItemStack> iter = target.getHeldEquipment().iterator();
          while (iter.hasNext()) {
            
            ItemStack stack = iter.next();
            if (stack.getItem() == Items.TOTEM_OF_UNDYING)
              stack.shrink(stack.getCount()); 
          } 
          EntityStatsCapability.get(target).setStrawDoll(true);
        } 
      } 
    } 
    
    return false;
  }

  
  public void setStrawDollOwner(ItemStack itemStack, LivingEntity e) {
    itemStack.setTag(new CompoundNBT());
    itemStack.getTag().putUniqueId("ownerUUID", e.getUniqueID());
    float red = e.getRNG().nextFloat();
    float green = e.getRNG().nextFloat();
    float blue = e.getRNG().nextFloat();
    itemStack.getOrCreateChildTag("display").putInt("color", (new Color(red, green, blue)).getRGB());
  }
}


