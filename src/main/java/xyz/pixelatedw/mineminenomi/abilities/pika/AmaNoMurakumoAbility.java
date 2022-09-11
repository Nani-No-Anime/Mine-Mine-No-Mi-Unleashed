package xyz.pixelatedw.mineminenomi.abilities.pika;
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

public class AmaNoMurakumoAbility extends ItemAbility implements IParallelContinuousAbility {
  public static final Ability INSTANCE = (Ability)new AmaNoMurakumoAbility();

  
  public AmaNoMurakumoAbility() {
    super("Ama no Murakumo", AbilityHelper.getDevilFruitCategory());
    setDescription("Focuses light in the user's hand to create a sword");
  }


  
  public boolean canBeActive(PlayerEntity player) {
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    return devilFruitProps.getDevilFruit().equalsIgnoreCase("pika_pika");
  }


  
  public ItemStack getItemStack(PlayerEntity player) {
    return new ItemStack((IItemProvider)ModWeapons.AMA_NO_MURAKUMO);
  }
}


