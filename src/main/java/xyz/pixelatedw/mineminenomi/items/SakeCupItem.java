package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.events.CrewEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModFoods;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

import java.util.UUID;


public class SakeCupItem
  extends Item
{
  private IItemPropertyGetter filledProperty;
  
  public SakeCupItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1).food(ModFoods.ALCOHOL)); this.filledProperty = ((itemStack, world, livingEntity) -> (itemStack.getTag() != null && !WyHelper.isNullOrEmpty(itemStack.getTag().getString("leader"))) ? 1.0F : 0.0F);
    addPropertyOverride(new ResourceLocation("filled"), this.filledProperty);
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (getLeader(player.getHeldItemMainhand(), world) != null) {
      
      player.setActiveHand(hand);
    }
    else {
      
      int slot = WyHelper.getIndexOfItemStack(ModItems.SAKE_BOTTLE, (IInventory)player.inventory);
      if (slot >= 0) {
        
        ItemStack stack = player.inventory.getStackInSlot(slot);
        stack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));

        
        setLeader(player.getHeldItemMainhand(), player);
        return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
      } 
    } 
    return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
  }


  
  public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
    if (!world.isRemote && entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      PlayerEntity leader = getLeader(itemStack, player.world);
      
      if (leader != null) {
        
        ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
        Crew crew = worldProps.getCrewWithCaptain(leader.getUniqueID());
        if (crew == null) {
          WyDebug.debug("Cannot find a crew for captain " + leader.getName().getFormattedText());
        } else {
          
          CrewEvent.Join event = new CrewEvent.Join(player, crew);
          if (!MinecraftForge.EVENT_BUS.post(event))
          {
            if (!crew.hasMember(player.getUniqueID())) {
              
              worldProps.addCrewMember(crew, (LivingEntity)player);
              FactionHelper.sendUpdateMessageToCrew(world, crew);
              FactionHelper.sendMessageToCrew(world, crew, (ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_NEW_JOIN, new Object[] { player.getName().getFormattedText() }));
              itemStack.getOrCreateTag().putInt("leader", 0);
            } 
          }
        } 
      } 
      
      player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)ModItems.SAKE_CUP));
      itemStack.shrink(1);
    } 
    
    return itemStack;
  }

  
  public void setLeader(ItemStack itemStack, PlayerEntity leader) {
    itemStack.setTag(new CompoundNBT());
    itemStack.getTag().putString("leader", leader.getUniqueID().toString());
  }

  
  public PlayerEntity getLeader(ItemStack itemStack, World world) {
    if (!itemStack.hasTag())
      itemStack.setTag(new CompoundNBT()); 
    String leaderUUID = itemStack.getTag().getString("leader");
    if (!WyHelper.isNullOrEmpty(leaderUUID))
      return world.getPlayerByUuid(UUID.fromString(leaderUUID)); 
    return null;
  }


  
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.DRINK;
  }
}


