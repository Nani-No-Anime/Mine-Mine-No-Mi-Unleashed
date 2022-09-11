package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class AbilitySwordItem extends CoreSwordItem {
  private Ability ability = null;

  
  public AbilitySwordItem(Ability ability, int damage) {
    this(ability, damage, -2.4F);
    this.ability = ability;
  }

  
  public AbilitySwordItem(Ability ability, int damage, float attackSpeed) {
    super((new Item.Properties()).maxStackSize(1).defaultMaxDamage(-1), damage, attackSpeed);
    this.ability = ability;
  }


  
  public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
    super.inventoryTick(itemStack, world, entity, itemSlot, isSelected);
    if (entity instanceof PlayerEntity && this.ability != null) {
      
      PlayerEntity owner = (PlayerEntity)entity;
      IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)owner);
      
      boolean deleteSword = true;
      
      for (Ability ability : abilityDataProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
        
        if (ability != null && ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility && this.ability.equals(ability))
        {
          
          if (ability.isContinuous())
            deleteSword = false; 
        }
      } 
      if (deleteSword) {
        owner.inventory.deleteStack(itemStack);
      }
    } 
  }

  
  public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem) {
    if (entityItem.isAlive())
      entityItem.remove(); 
    return true;
  }



  
  public boolean isEnchantable(ItemStack stack) {
    return false;
  }
}


