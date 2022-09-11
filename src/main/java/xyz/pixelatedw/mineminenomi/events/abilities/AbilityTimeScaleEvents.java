package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierApplyEvent;
import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierRemoveEvent;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateAbilityTimeProgressionPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


@EventBusSubscriber(modid = "mineminenomi")
public class AbilityTimeScaleEvents
{
  @SubscribeEvent
  public static void onModifierAdded(AttributeModifierApplyEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IAttributeInstance attr = event.getAttribute(ModAttributes.TIME_PROGRESSION);
    
    if (attr != null) {
      changeCooldownTimeScales(player, attr);
    }
  }
  
  @SubscribeEvent
  public static void onModifierRemoved(AttributeModifierRemoveEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IAttributeInstance attr = event.getAttribute(ModAttributes.TIME_PROGRESSION);
    
    if (attr != null) {
      changeCooldownTimeScales(player, attr);
    }
  }
  
  private static void changeCooldownTimeScales(PlayerEntity player, IAttributeInstance instance) {
    double timeScale = instance.getValue();
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    for (Ability abl : abilityProps.getEquippedAbilities()) {
      
      if (abl != null) {
        
        abl.setTimeProgression(timeScale);
        WyNetwork.sendToAllTrackingAndSelf(new SUpdateAbilityTimeProgressionPacket(player, abl), (LivingEntity)player);
      } 
    } 
  }
}


