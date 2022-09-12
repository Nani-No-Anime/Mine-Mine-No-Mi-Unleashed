package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import xyz.pixelatedw.mineminenomi.abilities.kage.KageGiriAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.List;
import java.util.Random;

public class ScissorsItem extends CoreSwordItem {
  public ScissorsItem() {
    super(5, -2.8F, 500);
  }


  
  public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
    if (!(attacker instanceof PlayerEntity)) {
      return super.hitEntity(itemStack, target, attacker);
    }
    KageGiriAbility passive = (KageGiriAbility)AbilityDataCapability.get(attacker).getUnlockedAbility(KageGiriAbility.INSTANCE);
    boolean hasPassive = (passive != null && !passive.isPaused());
    
    IEntityStats targetProps = EntityStatsCapability.get(target);
    
    if (!hasPassive || itemStack == null || itemStack.getItem() != ModWeapons.SCISSORS || !targetProps.hasShadow()) {
      return super.hitEntity(itemStack, target, attacker);
    }
    targetProps.setShadow(false);
    ((PlayerEntity)attacker).inventory.addItemStackToInventory(new ItemStack((IItemProvider)ModItems.SHADOW));
    WyNetwork.sendToAllAround(new SSyncEntityStatsPacket(target.getEntityId(), targetProps), (Entity)target);
    
    return super.hitEntity(itemStack, target, attacker);
  }




  
  public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entityLiving) {
    if (!world.isRemote)
    {
      stack.damageItem(1, entityLiving, entity -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    }



    
    Block block = state.getBlock();
    return (!state.isIn(BlockTags.LEAVES) && block != Blocks.COBWEB && block != Blocks.GRASS && block != Blocks.FERN && block != Blocks.DEAD_BUSH && block != Blocks.VINE && block != Blocks.TRIPWIRE && !block.isIn(BlockTags.WOOL)) ? super.onBlockDestroyed(stack, world, state, pos, entityLiving) : true;
  }


  
  public boolean canHarvestBlock(BlockState blockIn) {
    Block block = blockIn.getBlock();
    return (block == Blocks.COBWEB || block == Blocks.REDSTONE_WIRE || block == Blocks.TRIPWIRE);
  }


  
  public float getDestroySpeed(ItemStack stack, BlockState state) {
    Block block = state.getBlock();
    if (block != Blocks.COBWEB && !state.isIn(BlockTags.LEAVES)) {
      return block.isIn(BlockTags.WOOL) ? 5.0F : super.getDestroySpeed(stack, state);
    }
    return 15.0F;
  }


  
  public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
    if (entity.world.isRemote)
      return false; 
    if (entity instanceof IShearable) {
      
      IShearable target = (IShearable)entity;
      BlockPos pos = new BlockPos(entity.getPosX(), entity.getPosY(), entity.getPosZ());
      if (target.isShearable(stack, (IWorldReader)entity.world, pos)) {
        
        List<ItemStack> drops = target.onSheared(stack, entity.world, pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack));
        Random rand = new Random();
        drops.forEach(d -> {
              ItemEntity ent = entity.entityDropItem(d, 1.0F);
              
              ent.setMotion(ent.getMotion().add(((rand.nextFloat() - rand.nextFloat()) * 0.1F), (rand.nextFloat() * 0.05F), ((rand.nextFloat() - rand.nextFloat()) * 0.1F)));
            });
        stack.damageItem(1, entity, e -> e.sendBreakAnimation(hand));
      } 
      return true;
    } 
    return false;
  }
}


