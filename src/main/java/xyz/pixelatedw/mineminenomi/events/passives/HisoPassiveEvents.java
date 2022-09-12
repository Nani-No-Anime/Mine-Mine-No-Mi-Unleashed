package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.List;




@EventBusSubscriber(modid = "mineminenomi")
public class HisoPassiveEvents
{
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.getEntityLiving() instanceof net.minecraft.entity.passive.AnimalEntity)) {
      return;
    }
    if (event.getEntityLiving().isPotionActive(ModEffects.ANIMAL_LOOKOUT)) {
      
      LivingEntity animal = event.getEntityLiving();
      if (animal.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() != Items.COBBLESTONE) {
        
        ItemStack itemStack = new ItemStack((IItemProvider)Items.COBBLESTONE);
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("seen", (INBT)new ListNBT());
        itemStack.setTag(nbt);
        animal.setItemStackToSlot(EquipmentSlotType.CHEST, itemStack);
      } 
      List<LivingEntity> around = WyHelper.getEntitiesNear(animal.getPosition(), animal.world, 10.0D);
      around.remove(animal);
      CompoundNBT tag = animal.getItemStackFromSlot(EquipmentSlotType.CHEST).getTag();
      ListNBT seen = (ListNBT)tag.get("seen");
      around.forEach(entity -> {
            INBT hasn = null;
            
            for (INBT previous : seen) {
              if (previous.getString().split(" ")[0].equals(entity.getName().getString())) {
                hasn = previous;
              }
            } 
            
            if (hasn != null) {
              seen.remove(hasn);
            }
            
            seen.add(StringNBT.valueOf(entity.getName().getString() + " " + entity.world.getGameTime()));
          });
      tag.put("seen", (INBT)seen);
    } 
  }
}


