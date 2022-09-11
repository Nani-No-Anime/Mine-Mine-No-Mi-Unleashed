package xyz.pixelatedw.mineminenomi.items;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

















public class BellyPouchItem
  extends Item
{
  private IItemPropertyGetter sizeProperty;
  
  public BellyPouchItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1)); this.sizeProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null)
          return 0.0F;  long belly = itemStack.getOrCreateTag().getLong("belly"); int size = 0; if (belly > 100L && belly <= 500L) { size = 1; } else if (belly > 500L) { size = 2; }  return size; }); addPropertyOverride(new ResourceLocation("size"), this.sizeProperty);
  }


  
  public void inventoryTick(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
    if (!world.isRemote)
    {
      if (!itemStack.hasTag())
      {
        itemStack.getOrCreateTag().putLong("belly", (int)WyHelper.randomWithRange(5, 100));
      }
    }
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    if (!world.isRemote) {
      
      long amount = player.getHeldItemMainhand().getOrCreateTag().getLong("belly");
      
      if (props.getBelly() <= 999999999L - amount) {
        
        props.alterBelly(amount);
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_POUCH_BELLY_GAINED, new Object[] { Long.valueOf(amount) }));
        player.inventory.deleteStack(player.getHeldItemMainhand());
      } else {
        
        props.setBelly(999999999L);
      } 
      WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
    } 
    
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
    if (itemStack.hasTag())
    {
      list.add(new StringTextComponent("Belly: " + itemStack.getOrCreateTag().getLong("belly")));
    }
  }
}


