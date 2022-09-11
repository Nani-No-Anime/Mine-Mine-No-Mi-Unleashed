package xyz.pixelatedw.mineminenomi.abilities.gasu;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;

public class BlueSwordAbility extends ItemAbility implements IParallelContinuousAbility {
  public static final Ability INSTANCE = (Ability)new BlueSwordAbility();

  
  public BlueSwordAbility() {
    super("Blue Sword", AbilityHelper.getDevilFruitCategory());
    setDescription("The user fills a hilt with lamable gas, them sets it on fire to create a sword");
  }


  
  public boolean canBeActive(PlayerEntity player) {
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    return devilFruitProps.getDevilFruit().equalsIgnoreCase("gasu_gasu");
  }


  
  public ItemStack getItemStack(PlayerEntity player) {
    return new ItemStack((IItemProvider)ModWeapons.BLUE_SWORD);
  }
}


