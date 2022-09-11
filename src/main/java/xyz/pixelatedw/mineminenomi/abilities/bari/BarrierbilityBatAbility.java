package xyz.pixelatedw.mineminenomi.abilities.bari;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;

public class BarrierbilityBatAbility extends ItemAbility implements IParallelContinuousAbility {
  public static final BarrierbilityBatAbility INSTANCE = new BarrierbilityBatAbility();

  
  public BarrierbilityBatAbility() {
    super("Barrierbility Bat", AbilityHelper.getDevilFruitCategory());
    setDescription("Shapes the barriers in the form of a bat that the user can hold");
  }


  
  public boolean canBeActive(PlayerEntity player) {
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    return devilFruitProps.hasDevilFruit(ModAbilities.BARI_BARI_NO_MI);
  }


  
  public ItemStack getItemStack(PlayerEntity player) {
    return new ItemStack((IItemProvider)ModWeapons.BARRIERBILITY_BAT);
  }
}


