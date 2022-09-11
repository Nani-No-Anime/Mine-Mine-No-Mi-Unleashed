package xyz.pixelatedw.mineminenomi.items.weapons;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class AbilityPickaxeItem extends PickaxeItem {
  private Ability ability = null;

  
  public AbilityPickaxeItem(Ability ability, AbilityItemTier tier, int attackDamage, float attackSpeed) {
    super((IItemTier)tier, attackDamage, attackSpeed, new Item.Properties());
    this.ability = ability;
  }


  
  public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
    super.inventoryTick(itemStack, world, entity, itemSlot, isSelected);
    if (entity instanceof PlayerEntity && this.ability != null) {
      
      PlayerEntity owner = (PlayerEntity)entity;
      IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)owner);
      
      boolean deletePicaxe = true;
      
      for (Ability ability : abilityDataProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
        
        if (ability != null && ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility && this.ability.equals(ability))
        {
          
          if (ability.isContinuous())
            deletePicaxe = false; 
        }
      } 
      if (deletePicaxe) {
        owner.inventory.deleteStack(itemStack);
      }
    } 
  }

  
  public float getDestroySpeed(ItemStack stack, BlockState state) {
    if (stack.getItem() == ModWeapons.DORU_PICKAXE && state.getBlock() == ModBlocks.WAX)
    {
      return 999.0F;
    }
    return super.getDestroySpeed(stack, state);
  }


  
  public boolean isEnchantable(ItemStack stack) {
    return false;
  }
}


