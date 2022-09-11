package xyz.pixelatedw.mineminenomi.items.armors;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.models.armors.ColaBackpackModel;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;





public class ColaBackpackItem
  extends ArmorItem
{
  private static final int COLA_REFILL_TICKS = 20;
  
  public ColaBackpackItem() {
    super((IArmorMaterial)ModArmors.COLA_BACKPACK_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModCreativeTabs.EQUIPMENT));
  }


  
  @OnlyIn(Dist.CLIENT)
  @Nullable
  public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A)new ColaBackpackModel();
  }




  
  @Nullable
  public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
    return String.format("%s:textures/models/armor/cola_backpack.png", new Object[] { "mineminenomi" });
  }


  
  public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
    if (world.isRemote) {
      return;
    }
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    if (props.isCyborg())
    {
      if (player.ticksExisted % 20 == 0) {
        
        int colaSlot = getColaSlot(player);
        int ultraColaSlot = getUltraColaSlot(player);
        if (colaSlot != -1 && props.getMaxCola() > props.getCola()) {
          
          props.alterCola(25);
          player.inventory.decrStackSize(colaSlot, 1);
          WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
        }
        else if (ultraColaSlot != -1 && props.getMaxCola() > props.getCola()) {
          
          props.alterCola(50);
          player.inventory.decrStackSize(ultraColaSlot, 1);
          WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
        } 
      } 
    }
  }

  
  public int getColaSlot(PlayerEntity player) {
    if (!player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLA))) {
      return -1;
    }
    for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
      
      ItemStack stack = player.inventory.getStackInSlot(i);
      if (!stack.isEmpty() && stack.getItem() == ModItems.COLA) {
        return i;
      }
    } 
    return -1;
  }

  
  public int getUltraColaSlot(PlayerEntity player) {
    if (!player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.ULTRA_COLA))) {
      return -1;
    }
    for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
      
      ItemStack stack = player.inventory.getStackInSlot(i);
      if (!stack.isEmpty() && stack.getItem() == ModItems.ULTRA_COLA) {
        return i;
      }
    } 
    return -1;
  }
}


