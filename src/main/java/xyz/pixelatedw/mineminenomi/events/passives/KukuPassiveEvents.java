package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.kuku.GourmetamorphosisAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


@EventBusSubscriber(modid = "mineminenomi")
public class KukuPassiveEvents
{
  @SubscribeEvent
  public static void onItemRightClicked(PlayerInteractEvent.RightClickItem event) {
    PlayerEntity player = event.getPlayer();
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (!props.hasDevilFruit(ModAbilities.KUKU_KUKU_NO_MI)) {
      return;
    }
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    GourmetamorphosisAbility ability = (GourmetamorphosisAbility)abilityProps.getEquippedAbility((Ability)GourmetamorphosisAbility.INSTANCE);
    boolean hasAbilityActive = (ability != null && ability.isContinuous());
    
    if (hasAbilityActive) {
      
      ItemStack stack = event.getItemStack();
      int foodlevel = 2;
      float saturation = 0.25F;
      
      if (stack.getItem() instanceof xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem) {
        return;
      }
      if (stack.isFood()) {
        
        Food food = stack.getItem().getFood();
        foodlevel += food.getHealing();
        saturation += food.getSaturation();
      } 
      
      player.sendBreakAnimation(Hand.MAIN_HAND);
      player.getFoodStats().addStats(foodlevel, saturation);
      stack.shrink(1);
    } 
  }

  
  @SubscribeEvent
  @OnlyIn(Dist.CLIENT)
  public static void onEdibleCheck(ItemTooltipEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (player == null) {
      return;
    }
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (player == null || !props.hasDevilFruit(ModAbilities.KUKU_KUKU_NO_MI)) {
      return;
    }
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    GourmetamorphosisAbility ability = (GourmetamorphosisAbility)abilityProps.getEquippedAbility((Ability)GourmetamorphosisAbility.INSTANCE);
    boolean hasAbilityActive = (ability != null && ability.isContinuous());
    
    if (hasAbilityActive) {
      
      if (event.getItemStack().getItem() instanceof xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem) {
        return;
      }
      StringTextComponent foodString = new StringTextComponent(TextFormatting.YELLOW + "" + (new TranslationTextComponent(ModI18n.ITEM_GOURMETAMORPHOSIS_FOOD, new Object[0])).getFormattedText());
      if (!event.getToolTip().contains(foodString)) {
        
        event.getToolTip().add(new StringTextComponent(""));
        event.getToolTip().add(foodString);
      } 
    } 
  }
}


