package xyz.pixelatedw.mineminenomi.wypi.abilities;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.APIDefaults;

public abstract class ItemAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public ItemAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
  }




  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.getHeldItemMainhand().isEmpty() && !getItemStack(player).isEmpty()) {
      
      player.inventory.setInventorySlotContents(player.inventory.currentItem, getItemStack(player));
      return true;
    } 

    
    if (getItemStack(player).isEmpty()) {
      player.sendMessage((ITextComponent)new TranslationTextComponent(APIDefaults.ABILITY_MESSAGE_EMPTY_STACK, new Object[0]));
    } else {
      player.sendMessage((ITextComponent)new TranslationTextComponent(APIDefaults.ABILITY_MESSAGE_ANOTHER_ITEM_IN_HAND, new Object[0]));
    }  return false;
  }
  
  public abstract ItemStack getItemStack(PlayerEntity paramPlayerEntity);
  
  public abstract boolean canBeActive(PlayerEntity paramPlayerEntity);
}


