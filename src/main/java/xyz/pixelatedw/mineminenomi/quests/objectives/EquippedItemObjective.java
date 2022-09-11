package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.IEquipItemObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

public class EquippedItemObjective
  extends Objective
  implements IEquipItemObjective {
  private Item itemTarget;
  private EquipmentSlotType slotTarget;
  
  public EquippedItemObjective(String title, int count, Item item, EquipmentSlotType slot) {
    super(title);
    setMaxProgress(count);
    this.itemTarget = item;
    this.slotTarget = slot;
  }


  
  public boolean checkEquippedItem(PlayerEntity player) {
    return (player.getItemStackFromSlot(this.slotTarget).getItem() == this.itemTarget);
  }


  
  public String getLocalizedTitle() {
    String objectiveKey = (new TranslationTextComponent(String.format("quest.objective.mineminenomi.%s", new Object[] { getId() }), new Object[0])).getKey();
    return (new TranslationTextComponent(objectiveKey, new Object[] { (new ItemStack((IItemProvider)this.itemTarget)).getDisplayName() })).getFormattedText();
  }
}


