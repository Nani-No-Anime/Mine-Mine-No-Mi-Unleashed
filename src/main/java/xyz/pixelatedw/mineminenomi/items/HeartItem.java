package xyz.pixelatedw.mineminenomi.items;

import java.util.Iterator;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public class HeartItem
  extends Item
{
  private static final DamageSource DAMAGE_SOURCE = (new ModDamageSource("magic")).setInternalDamage().setDamageBypassesArmor().setMagicDamage().setDamageAllowedInCreativeMode().setDamageIsAbsolute();

  
  public HeartItem() {
    super((new Item.Properties()).maxStackSize(1));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (world.isRemote) {
      return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
    ItemStack itemStack = player.getHeldItem(hand);
    
    LivingEntity owner = getOwner(world, player.getPosition(), itemStack);
    
    if (owner == null) {
      
      player.inventory.deleteStack(itemStack);
      return ActionResult.resultSuccess(itemStack);
    } 
    
    IEntityStats props = EntityStatsCapability.get(owner);
    
    if (props.hasHeart()) {
      
      player.inventory.deleteStack(itemStack);
      return ActionResult.resultSuccess(itemStack);
    } 
    
    if (itemStack.getOrCreateTag() != null)
    {
      if (owner == player) {
        
        props.setHeart(true);
        WyNetwork.sendToServer(new SSyncEntityStatsPacket(player.getEntityId(), props));
        player.inventory.deleteStack(itemStack);
      }
      else {
        
        owner.attackEntityFrom(DAMAGE_SOURCE, 5.0F);
        owner.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 1));
        owner.addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
        if (owner.getHealth() <= 0.0F) {
          player.inventory.deleteStack(itemStack);
        }
      } 
    }
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }

  
  @Nullable
  public LivingEntity getOwner(World world, BlockPos pos, ItemStack itemStack) {
    UUID uuid = itemStack.getOrCreateTag().getUniqueId("ownerUUID");
    return (LivingEntity)((ServerWorld)world).getEntityByUuid(uuid);
  }


  
  public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem) {
    if (entityItem.world.isRemote) {
      return false;
    }
    if (itemStack.getTag() != null) {
      
      LivingEntity target = getOwner(entityItem.world, entityItem.getPosition(), itemStack);
      if (target != null) {
        
        boolean isBurning = entityItem.isBurning();
        boolean isInVoid = (entityItem.getPosition().getY() < -1);
        
        if (isBurning || isInVoid) {
          
          Iterator<ItemStack> iter = target.getHeldEquipment().iterator();
          while (iter.hasNext()) {
            
            ItemStack stack = iter.next();
            if (stack.getItem() == Items.TOTEM_OF_UNDYING)
              stack.shrink(stack.getCount()); 
          } 
          target.attackEntityFrom(DAMAGE_SOURCE, target.getMaxHealth() + target.getAbsorptionAmount() + 1.0F);
        } 
      } 
    } 
    
    return false;
  }

  
  public void setHeartOwner(ItemStack itemStack, LivingEntity owner) {
    itemStack.setTag(new CompoundNBT());
    itemStack.getTag().putUniqueId("ownerUUID", owner.getUniqueID());
  }
}


