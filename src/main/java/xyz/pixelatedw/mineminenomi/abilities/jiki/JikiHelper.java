package xyz.pixelatedw.mineminenomi.abilities.jiki;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.init.ModTags;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class JikiHelper
{
  public static List<ItemStack> getMagneticItems(PlayerEntity player, int maxCount) {
    int count = 0;
    List<ItemStack> inventory = new ArrayList<>();
    List<ItemStack> list = new ArrayList<>();
    inventory.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
    inventory.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
    inventory.addAll((Collection<? extends ItemStack>)player.inventory.armorInventory);
    for (ItemStack stack : inventory) {
      
      if (maxCount > 0 && count >= maxCount) {
        break;
      }
      if (stack == null) {
        continue;
      }
      if (!stack.isEmpty() && stack.getItem().isIn(ModTags.Items.MAGNETIC)) {
        
        list.add(stack);
        count += stack.getCount();
      } 
    } 
    
    return list;
  }


  
  public static List<ItemStack> getMagneticItemsStack(PlayerEntity player, List<ItemStack> items, int countPerStack) {
    List<ItemStack> components = new ArrayList<>();
    int count = 0;
    for (ItemStack stack : items) {
      
      if (count >= countPerStack) {
        break;
      }
      ItemStack clone = ItemStack.read(stack.write(new CompoundNBT()));
      
      if (stack.isDamageable()) {
        
        components.add(clone);
        stack.shrink(1);
        count++;
        
        continue;
      } 
      int needed = countPerStack - count;
      if (stack.getCount() < needed) {
        
        components.add(clone);
        stack.shrink(stack.getCount());
        count += clone.getCount();
        
        continue;
      } 
      ItemStack split = stack.split(needed);
      components.add(split);
      count += split.getCount();
    } 

    
    return components;
  }


  
  public static int countMagneticItems(List<ItemStack> list) {
    int count = 0;
    for (ItemStack stack : list) {
      
      if (stack == null) {
        continue;
      }
      if (!stack.isEmpty() && stack.getItem().isIn(ModTags.Items.MAGNETIC)) {
        count += stack.getCount();
      }
    } 
    return count;
  }

  
  public static void dropComponentItems(PlayerEntity player, BlockPos pos, List<ItemStack> list) {
    damageMagneticItems(player, list);
    for (ItemStack stack : list) {
      
      ItemEntity item = new ItemEntity(player.world, pos.getX(), (pos.getY() + 0.4F), pos.getZ(), stack);
      item.setPickupDelay(20);
      player.world.addEntity((Entity)item);
    } 
  }

  
  public static void damageMagneticItems(PlayerEntity player, List<ItemStack> list) {
    for (ItemStack stack : list) {
      
      if (stack == null || stack.isEmpty()) {
        continue;
      }
      boolean chance = (player.getRNG().nextInt(10) < 1);
      if (!chance) {
        continue;
      }
      if (stack.isDamageable()) {
        
        int maxDamage = stack.getMaxDamage();
        int damage = (int)WyHelper.clamp(player.getRNG().nextInt(maxDamage), 50L, maxDamage);
        stack.damageItem(damage, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        
        continue;
      } 
      int maxCount = stack.getCount();
      int destroy = (int)WyHelper.clamp(player.getRNG().nextInt(maxCount), 1L, maxCount);
      stack.shrink(destroy);
    } 
  }
}


