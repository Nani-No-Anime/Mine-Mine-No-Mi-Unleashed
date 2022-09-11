package xyz.pixelatedw.mineminenomi.items.weapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.wara.StrawProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class WarabideSwordItem extends AbilitySwordItem {
  public WarabideSwordItem(Ability instance, int damage) {
    super(instance, damage);
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ActionResult<ItemStack> result = super.onItemRightClick(world, player, hand);
    
    if (!world.isRemote) {
      
      StrawProjectile projectile = new StrawProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)projectile);
      projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 1.5F);
      player.getCooldownTracker().setCooldown((Item)this, 25);
    } 
    
    return result;
  }
}


