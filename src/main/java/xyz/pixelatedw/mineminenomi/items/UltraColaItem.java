package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;



public class UltraColaItem
  extends Item
{
  private static final int ULTRA_COLA_BONUS = 20;
  public static final int COLA_REFILL = 50;
  
  public UltraColaItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16).food(Foods.APPLE));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    player.setActiveHand(hand);
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
    if (!world.isRemote && entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
      
      if (props.isCyborg()) {
        
        if (props.getUltraCola() <= 19) {
          
          props.addUltraCola(1);
          props.alterMaxCola(20);
          props.alterCola(25);
        }
        else {
          
          player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 0, false, false));
          player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 200, 0, false, false));
          player.addPotionEffect(new EffectInstance(Effects.SPEED, 200, 0, false, false));
          props.alterCola(50);
        } 
      } else {
        
        player.addPotionEffect(new EffectInstance(Effects.SPEED, 250, 0));
      } 
      WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
      if (!player.isCreative()) {
        itemStack.shrink(1);
      }
    } 
    return itemStack;
  }


  
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.DRINK;
  }
}


