package xyz.pixelatedw.mineminenomi.abilities.yuki;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;

public class TabiraYukiAbility extends ItemAbility implements IParallelContinuousAbility {
  public static final TabiraYukiAbility INSTANCE = new TabiraYukiAbility();

  
  public TabiraYukiAbility() {
    super("Tabira Yuki", AbilityHelper.getDevilFruitCategory());
    setDescription("The user creates a sword made of solid hardened snow. Will inflict §2Frostbite§r");
  }


  
  public boolean canBeActive(PlayerEntity player) {
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    return devilFruitProps.getDevilFruit().equalsIgnoreCase("yuki_yuki");
  }


  
  public ItemStack getItemStack(PlayerEntity player) {
    return new ItemStack((IItemProvider)ModWeapons.TABIRA_YUKI);
  }
}


