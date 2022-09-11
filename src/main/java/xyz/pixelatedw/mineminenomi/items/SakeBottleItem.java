package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModFoods;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenNewCrewScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class SakeBottleItem
  extends Item {
  public SakeBottleItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).defaultMaxDamage(5).food(ModFoods.ALCOHOL));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    if (world.isRemote) {
      return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
    }
    if (player.isSneaking() && props.isPirate()) {
      
      ExtendedWorldData worldProps = ExtendedWorldData.get(world);
      boolean isInCrew = (worldProps.getCrewWithMember(player.getUniqueID()) != null);
      if (isInCrew) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_ALREADY_IN_CREW, new Object[0]));
        return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
      } 
      
      if (props.getBounty() < CommonConfig.INSTANCE.getBountyRequirementForCrews()) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_BOUNTY_REQUIREMENT, new Object[0]));
        return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
      } 
      
      WyNetwork.sendTo(new SOpenNewCrewScreenPacket(), player);
    }
    else {
      
      player.setActiveHand(hand);
    } 
    
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }

  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }


  
  public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
    if (!world.isRemote && entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      
      if (entity.isPotionActive(ModEffects.DRUNK)) {
        
        EffectInstance effect = entity.getActivePotionEffect(ModEffects.DRUNK);
        
        int amp = effect.getAmplifier();
        int duration = effect.getDuration();
        
        if (amp < 4) {
          amp++;
        }
        entity.removePotionEffect(ModEffects.DRUNK);
        entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, duration + 200, amp));
      }
      else {
        
        entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, 400, 0));
      } 
      
      if (!player.isCreative()) {
        itemStack.damageItem(1, entity, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
      }
    } 

    
    return itemStack;
  }


  
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.DRINK;
  }
}


