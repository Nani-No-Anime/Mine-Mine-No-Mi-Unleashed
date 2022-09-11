package xyz.pixelatedw.mineminenomi.abilities.hie;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;

public class IceSaberAbility extends ItemAbility implements IParallelContinuousAbility {
  public static final Ability INSTANCE = (Ability)new IceSaberAbility();

  
  public IceSaberAbility() {
    super("Ice Saber", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates a sharp blade made of compressed ice");
  }


  
  public boolean canBeActive(PlayerEntity player) {
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    return devilFruitProps.hasDevilFruit(ModAbilities.HIE_HIE_NO_MI);
  }


  
  public ItemStack getItemStack(PlayerEntity player) {
    return new ItemStack((IItemProvider)ModWeapons.ICE_SABER);
  }
}


