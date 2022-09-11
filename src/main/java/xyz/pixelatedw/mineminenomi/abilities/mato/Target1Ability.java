package xyz.pixelatedw.mineminenomi.abilities.mato;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mato.TargetProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class Target1Ability extends Ability {
  public static final Ability INSTANCE = new Target1Ability();

  
  public Target1Ability() {
    super("Target1", AbilityHelper.getDevilFruitCategory());
    setDescription("when activated, the user can drop the item he's holding, which will launch the item towards the marked mob");
    
    this.onUseEvent = this::onUseEvent;
  }


  
  private boolean onUseEvent(PlayerEntity player) {
    ItemStack it = player.getHeldItemMainhand();
    Item item = it.getItem();

    
    if (it == null || it == new ItemStack((IItemProvider)Blocks.AIR) || it == new ItemStack(null)) {

      
      player.sendMessage((ITextComponent)new StringTextComponent("You must be holding an object in your main hand to use the target ability !"));
    }
    else if (it != null || it != new ItemStack((IItemProvider)Blocks.AIR) || it != new ItemStack(null)) {
      
      player.inventory.clearMatchingItems(p -> ((new ItemStack((IItemProvider)item, 1)).getItem() == p.getItem()), 1);
      TargetProjectile projectile = new TargetProjectile(player.world, (LivingEntity)player);






















      
      projectile.setDamage(1.0F);
      player.world.addEntity((Entity)projectile);
      projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 1.0F);
    } 

    
    return true;
  }
}


