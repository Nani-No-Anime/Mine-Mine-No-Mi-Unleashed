package xyz.pixelatedw.mineminenomi.abilities.noro;
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

public class NoroNoroBeamSwordAbility extends ItemAbility implements IParallelContinuousAbility {
  public static final Ability INSTANCE = (Ability)new NoroNoroBeamSwordAbility();

  
  public NoroNoroBeamSwordAbility() {
    super("Noro Noro Beam Sword", AbilityHelper.getDevilFruitCategory());
    setDescription("Focuses photons inside a hilt to create a sword, which slows enemies upon hit");
  }


  
  public boolean canBeActive(PlayerEntity player) {
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    return devilFruitProps.getDevilFruit().equalsIgnoreCase("noro_noro");
  }


  
  public ItemStack getItemStack(PlayerEntity player) {
    return new ItemStack((IItemProvider)ModWeapons.NORO_NORO_BEAM_SWORD);
  }
}


