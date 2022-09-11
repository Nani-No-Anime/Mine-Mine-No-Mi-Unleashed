package xyz.pixelatedw.mineminenomi.events.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.GunArrayAbility;
import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.ColaBackpackBonusAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;


@EventBusSubscriber(modid = "mineminenomi")
public class CombatItemEvents
{
  @SubscribeEvent
  public static void onEntityHurt(LivingHurtEvent event) {
    Entity source = event.getSource().getImmediateSource();
    if (source == null) {
      return;
    }
    if (source instanceof LivingEntity) {
      
      LivingEntity entity = (LivingEntity)event.getSource().getImmediateSource();
      ItemStack stack = entity.getHeldItemMainhand();
      if (stack != null && !stack.isEmpty() && stack.hasTag() && stack.getTag().getBoolean("isClone")) {
        event.setAmount(event.getAmount() / 1.25F);
      }
    } 
  }
  
  @SubscribeEvent
  public static void onEquipmentUpdate(LivingEquipmentChangeEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity))
      return; 
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    IEntityStats stats = EntityStatsCapability.get((LivingEntity)player);



    
    boolean flag = false;
    
    if (event.getSlot() == EquipmentSlotType.CHEST) {
      
      flag |= checkForCombatItem(player, event.getFrom(), event.getTo(), ModArmors.WOOTZ_STEEL_ARMOR, (Ability)GunArrayAbility.INSTANCE);
      boolean colaBackpackCheck = checkForCombatItem(player, event.getFrom(), event.getTo(), ModArmors.COLA_BACKPACK, (Ability)ColaBackpackBonusAbility.INSTANCE);
      
      flag |= colaBackpackCheck;
      if (colaBackpackCheck) {
        
        if (props.hasUnlockedAbility((Ability)ColaBackpackBonusAbility.INSTANCE)) {
          stats.alterMaxCola(400);
        } else {
          stats.alterMaxCola(-400);
        }  WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), stats), player);
      } 
    } 
    
    if (event.getSlot() == EquipmentSlotType.HEAD)
    {
      flag |= checkForCombatItem(player, event.getFrom(), event.getTo(), ModArmors.SNIPER_GOGGLES, (Ability)ZoomAbility.INSTANCE);
    }
    
    if (flag) {
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
    }
  }
  
  private static boolean checkForCombatItem(PlayerEntity player, ItemStack from, ItemStack to, Item itemCheck, Ability ability) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    boolean flag = false;
    
    if (to.getItem() == itemCheck && !props.hasUnlockedAbility(ability)) {
      
      props.addUnlockedAbility(ability);
      flag = true;
    }
    else if (from.getItem() == itemCheck && props.hasUnlockedAbility(ability)) {
      
      Ability abl = props.getUnlockedAbility(ability);
      if (abl instanceof ContinuousAbility)
        ((ContinuousAbility)abl).endContinuity(player); 
      props.removeUnlockedAbility(ability);
      props.removeEquippedAbility(ability);
      flag = true;
    } 
    
    return flag;
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void updateZoom(FOVUpdateEvent event) {
    ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
    if (clientPlayerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() != ModArmors.SNIPER_GOGGLES) {
      return;
    }
    IAbilityData aprops = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
    
    if (aprops.getEquippedAbility((Ability)ZoomAbility.INSTANCE) != null) {
      
      Ability ability = aprops.getEquippedAbility((Ability)ZoomAbility.INSTANCE);
      
      if (ability.isContinuous())
        event.setNewfov(0.01F); 
    } 
  }
}


