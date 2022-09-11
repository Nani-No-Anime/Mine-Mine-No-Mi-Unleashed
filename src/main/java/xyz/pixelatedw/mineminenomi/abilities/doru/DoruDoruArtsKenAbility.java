package xyz.pixelatedw.mineminenomi.abilities.doru;

import java.awt.Color;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;

public class DoruDoruArtsKenAbility extends ItemAbility implements IParallelContinuousAbility {
  public static final Ability INSTANCE = (Ability)new DoruDoruArtsKenAbility();

  
  public DoruDoruArtsKenAbility() {
    super("Doru Doru Arts: Ken", AbilityHelper.getDevilFruitCategory());
    setDescription("The user uses hardened wax to create a sword");
  }


  
  public boolean canBeActive(PlayerEntity player) {
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    return devilFruitProps.hasDevilFruit(ModAbilities.DORU_DORU_NO_MI);
  }


  
  public ItemStack getItemStack(PlayerEntity player) {
    ItemStack itemStack = new ItemStack((IItemProvider)ModWeapons.DORU_DORU_ARTS_KEN);
    float red = 1.0F;
    float green = 1.0F;
    float blue = 1.0F;
    if (player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLOR_PALETTE))) {
      
      red = this.random.nextFloat();
      green = this.random.nextFloat();
      blue = this.random.nextFloat();
    } 
    itemStack.getOrCreateChildTag("display").putInt("color", (new Color(red, green, blue)).getRGB());
    return itemStack;
  }
}


