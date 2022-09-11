package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class DoruDoruNoYakataAbility extends Ability {
  public static final DoruDoruNoYakataAbility INSTANCE = new DoruDoruNoYakataAbility();

  
  public DoruDoruNoYakataAbility() {
    super("Doru Doru no Yakata", AbilityHelper.getDevilFruitCategory());
    
    setMaxCooldown(40.0D);
    setDescription("The user creates a few wax copies of themselves (Use the Color Palette for color on the clones)");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    for (int i = 0; i < 7; i++) {
      
      int offsetX = (int)WyHelper.randomWithRange(-2, 2);
      int offsetZ = (int)WyHelper.randomWithRange(-2, 2);
      WaxCloneEntity clone = new WaxCloneEntity(player.world);
      clone.setPositionAndRotation(player.getPosX() + offsetX, player.getPosY(), player.getPosZ() + offsetZ, 180.0F, 0.0F);
      clone.setOwner(player.getUniqueID());
      if (player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLOR_PALETTE))) {
        
        clone.setTextured();
        for (EquipmentSlotType slot : EquipmentSlotType.values()) {
          
          ItemStack stack = player.getItemStackFromSlot(slot);
          clone.setItemStackToSlot(slot, stack);
        } 
      } 
      player.world.addEntity((Entity)clone);
    } 
    
    return true;
  }
}


