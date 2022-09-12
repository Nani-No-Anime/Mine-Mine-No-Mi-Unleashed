package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

import javax.annotation.Nullable;
import java.util.List;

public class CigarItem extends Item {
  private int smokeFreqency = 1;

  
  public CigarItem(int smokeIn) {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).defaultMaxDamage(200));
    this.smokeFreqency = smokeIn;
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty()) {
      
      ItemStack stack = new ItemStack((IItemProvider)player.getHeldItem(hand).getItem(), 1);
      player.setItemStackToSlot(EquipmentSlotType.HEAD, stack);
      player.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, 1.0F, 1.0F);
      player.getHeldItem(hand).shrink(1);
    } 
    
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
    return armorType.equals(EquipmentSlotType.HEAD);
  }


  
  public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
    if (player.ticksExisted % this.smokeFreqency == 0 && !player.canSwim()) {
      
      Vec3d vec = player.getLookVec().scale(0.5D + (player.getWidth() / 2.0F)).rotateYaw((float)Math.toRadians(-20.0D));
      world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, player.getPosX() + vec.x, vec.y + player.getPosYEye(), player.getPosZ() + vec.z, 0.0D, 0.04D, 0.0D);
      
      if (!world.isRemote) {
        
        IDevilFruit d = DevilFruitCapability.get((LivingEntity)player);
        if (d.hasDevilFruit(ModAbilities.MOKU_MOKU_NO_MI) || d.hasDevilFruit(ModAbilities.GASU_GASU_NO_MI))
          return; 
        stack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.HEAD));
      } 
    } 
  }





  
  public void addInformation(ItemStack stack, @Nullable World p_77624_2_, List<ITextComponent> a, ITooltipFlag p_77624_4_) {
    super.addInformation(stack, p_77624_2_, a, p_77624_4_);
  }
}


