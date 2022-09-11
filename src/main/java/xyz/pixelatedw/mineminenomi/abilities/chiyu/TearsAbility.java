package xyz.pixelatedw.mineminenomi.abilities.chiyu;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import xyz.pixelatedw.mineminenomi.api.abilities.HurtPassiveAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModItems;

public class TearsAbility extends HurtPassiveAbility {
  public static final TearsAbility INSTANCE = new TearsAbility();

  
  public TearsAbility() {
    super("Tears", AbilityHelper.getDevilFruitCategory());
    setDescription("Each time the user is hurt their Watering Can gets filled with tears, which can be drank to heal themselves");
    hideInGUI(false);
    
    this.onHurtEvent = this::onHurtEvent;
  }

  
  private boolean onHurtEvent(LivingEntity entity, Entity source) {
    if (!(entity instanceof PlayerEntity)) {
      return true;
    }
    PlayerEntity attacked = (PlayerEntity)entity;
    
    ItemStack waterCan = null;
    for (int i = 0; i < attacked.inventory.mainInventory.size(); i++) {
      
      if (attacked.inventory.mainInventory.get(i) != null && ((ItemStack)attacked.inventory.mainInventory.get(i)).getItem() == ModItems.WATERING_CAN) {
        
        waterCan = (ItemStack)attacked.inventory.mainInventory.get(i);
        
        break;
      } 
    } 
    if (waterCan == null) {
      return true;
    }
    if (!waterCan.hasTag()) {
      waterCan.setTag(new CompoundNBT());
    }
    int bonusTears = (int)(getAmount() / 3.0F);
    if (bonusTears <= 0) {
      bonusTears = 1;
    }
    int tears = waterCan.getTag().getInt("tears");
    waterCan.getTag().putInt("tears", tears + bonusTears);
    
    return true;
  }
}


