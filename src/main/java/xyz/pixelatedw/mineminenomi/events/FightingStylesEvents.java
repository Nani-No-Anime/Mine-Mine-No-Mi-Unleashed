package xyz.pixelatedw.mineminenomi.events;

import com.google.common.collect.Multimap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.FightingStyleHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;




@EventBusSubscriber(modid = "mineminenomi")
public class FightingStylesEvents
{
  private static final UUID SWORDSMAN_BONUS_ID = UUID.fromString("4b706e44-2567-4019-9b9c-747fa53bb05d");

  
  @SubscribeEvent
  public static void onItemChange(LivingEquipmentChangeEvent event) {
    LivingEntity entity = event.getEntityLiving();
    
    ItemStack stack = event.getTo();
    if (!stack.isEmpty() && ItemsHelper.isSword(stack))
    {
      if (EntityStatsCapability.get(entity).isSwordsman()) {
        
        Multimap<String, AttributeModifier> map = stack.getItem().getAttributeModifiers(EquipmentSlotType.MAINHAND, stack);
        String key = SharedMonsterAttributes.ATTACK_DAMAGE.getName();
        
        AttributeModifier mod = map.get(key).stream().filter(m -> m.getID().equals(SWORDSMAN_BONUS_ID)).findFirst().orElse(null);
        if (mod == null) {
          map.put(key, new AttributeModifier(SWORDSMAN_BONUS_ID, "Swordsman Bonus", 0.25D, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        for (Map.Entry<String, AttributeModifier> entry : (Iterable<Map.Entry<String, AttributeModifier>>)map.entries()) {
          
          if (!stack.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsEntry(entry.getKey(), entry.getValue())) {
            stack.addAttributeModifier(entry.getKey(), entry.getValue(), EquipmentSlotType.MAINHAND);
          }
        } 
      } else if (stack.hasTag()) {
        
        ListNBT listnbt = stack.getTag().getList("AttributeModifiers", 10);
        
        for (int i = 0; i < listnbt.size(); i++) {
          
          CompoundNBT compoundnbt = listnbt.getCompound(i);
          AttributeModifier mod = SharedMonsterAttributes.readAttributeModifier(compoundnbt);
          if (mod.getID().equals(SWORDSMAN_BONUS_ID)) {
            listnbt.remove(i);
          }
        } 
      } 
    }
  }
  
  @SubscribeEvent
  @OnlyIn(Dist.CLIENT)
  public static void onTooltipRender(ItemTooltipEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (player == null) {
      return;
    }
    if (ItemsHelper.isSword(event.getItemStack()) && EntityStatsCapability.get((LivingEntity)event.getPlayer()).isSwordsman()) {
      
      StringTextComponent damageBonus = new StringTextComponent(TextFormatting.GREEN + "" + (new TranslationTextComponent(ModI18n.ITEM_SWORDSMAN_BONUS, new Object[0])).getFormattedText());
      if (!event.getToolTip().contains(damageBonus)) {
        
        event.getToolTip().add(new StringTextComponent(""));
        event.getToolTip().add(damageBonus);
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onStatsChoose(SetPlayerDetailsEvent event) {
    if (event.getEntityStats().isBlackLeg())
      FightingStyleHelper.applyBlackLegModifiers(event.getPlayer()); 
    if (event.getEntityStats().isBrawler()) {
      FightingStyleHelper.applyBrawlerModifiers(event.getPlayer());
    }
  }
  
  @SubscribeEvent
  public static void edgeCasesChecks(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.START || event.side != LogicalSide.SERVER || event.player.ticksExisted % 5 != 0) {
      return;
    }
    PlayerEntity player = event.player;
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    boolean isHandEmpty = player.getHeldItemMainhand().isEmpty();
    
    if (props.isBlackLeg()) {
      
      if (!isHandEmpty && FightingStyleHelper.hasBlackLegModifiers(player)) {
        FightingStyleHelper.removeBlackLegModifiers(player);
      } else if (isHandEmpty && !FightingStyleHelper.hasBlackLegModifiers(player)) {
        FightingStyleHelper.applyBlackLegModifiers(player);
      } 
    } else if (props.isBrawler()) {
      
      if (!isHandEmpty && FightingStyleHelper.hasBrawlerModifiers(player)) {
        FightingStyleHelper.removeBrawlerModifiers(player);
      } else if (isHandEmpty && !FightingStyleHelper.hasBrawlerModifiers(player)) {
        FightingStyleHelper.applyBrawlerModifiers(player);
      } 
    } else {
      if (FightingStyleHelper.hasBrawlerModifiers(player)) {
        FightingStyleHelper.removeBrawlerModifiers(player);
      }
      if (FightingStyleHelper.hasBlackLegModifiers(player))
        FightingStyleHelper.removeBlackLegModifiers(player); 
    } 
  }
}


