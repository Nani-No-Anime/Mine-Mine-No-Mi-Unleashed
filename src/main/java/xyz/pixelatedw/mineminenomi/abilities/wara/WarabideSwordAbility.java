package xyz.pixelatedw.mineminenomi.abilities.wara;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;

public class WarabideSwordAbility extends ItemAbility {
  public static final WarabideSwordAbility INSTANCE = new WarabideSwordAbility();

  
  public WarabideSwordAbility() {
    super("Warabide Sword", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates a sword that can shoot long thin straw-like projectiles");
  }


  
  public ItemStack getItemStack(PlayerEntity player) {
    return new ItemStack((IItemProvider)ModWeapons.WARABIDE_SWORD);
  }


  
  public boolean canBeActive(PlayerEntity player) {
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    return props.hasDevilFruit(ModAbilities.WARA_WARA_NO_MI);
  }
}


