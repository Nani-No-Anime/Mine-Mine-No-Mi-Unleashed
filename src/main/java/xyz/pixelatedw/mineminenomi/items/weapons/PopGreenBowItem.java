package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PopGreenBowItem extends BowItem {
  List<Item> ammoItems;
  
  public PopGreenBowItem(int maxDamage) {
    super((new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(maxDamage));































































    
    this.ammoItems = new ArrayList<>(Arrays.asList(new Item[] { ModItems.POP_GREEN, ModItems.POP_GREEN_DEVIL, ModItems.POP_GREEN_TAKE_JAVELIN, ModItems.POP_GREEN_RAFFLESIA, ModItems.POP_GREEN_BAKUHATSU_SO, ModItems.POP_GREEN_HUMANDRAKE, ModItems.POP_GREEN_TRAMPOLIA, ModItems.POP_GREEN_IMPACT_WOLF }));
  }




  
  public Predicate<ItemStack> getInventoryAmmoPredicate() {
    return itemStack -> this.ammoItems.contains(itemStack.getItem());
  }
  
  public void onPlayerStoppedUsing(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
    if (entityLiving instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity)entityLiving;
      boolean flag = (player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0);
      ItemStack arrowStack = player.findAmmo(itemStack);
      int i = getUseDuration(itemStack) - timeLeft;
      i = ForgeEventFactory.onArrowLoose(itemStack, world, player, i, (!arrowStack.isEmpty() || flag));
      if (i < 0)
        return; 
      if (!arrowStack.isEmpty() || flag) {
        float f = getArrowVelocity(i);
        if (f > 0.4D) {
          if (!world.isRemote) {
            PopGreenProjectile proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.NONE);
            if (arrowStack.getItem().equals(ModItems.POP_GREEN)) {
              proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.NONE);
            } else if (arrowStack.getItem().equals(ModItems.POP_GREEN_DEVIL)) {
              proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.DEVIL);
            } else if (arrowStack.getItem().equals(ModItems.POP_GREEN_RAFFLESIA)) {
              proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.RAFFLESIA);
            } else if (arrowStack.getItem().equals(ModItems.POP_GREEN_TAKE_JAVELIN)) {
              proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.TAKE_JAVELIN);
            } else if (arrowStack.getItem().equals(ModItems.POP_GREEN_BAKUHATSU_SO)) {
              proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.BAKUHATSU);
            } else if (arrowStack.getItem().equals(ModItems.POP_GREEN_HUMANDRAKE)) {
              proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.HUMANDRAKE);
            } else if (arrowStack.getItem().equals(ModItems.POP_GREEN_TRAMPOLIA)) {
              proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.TRAMPOLIA);
            } else if (arrowStack.getItem().equals(ModItems.POP_GREEN_IMPACT_WOLF)) {
              proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.IMPACT_WOLF);
            } 
            proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, f * 3.0F, 1.0F);
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0)
              proj.setFire(4); 
            world.addEntity((Entity)proj);
            arrowStack.shrink(1);
            player.addStat(Stats.ITEM_USED.get(this));
            itemStack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(player.getActiveHand()));
          } 
          world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
        } 
      } 
    } 
  }
}


