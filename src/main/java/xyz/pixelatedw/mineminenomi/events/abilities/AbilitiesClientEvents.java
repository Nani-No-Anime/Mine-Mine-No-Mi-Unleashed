package xyz.pixelatedw.mineminenomi.events.abilities;

import java.util.Arrays;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;



@EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
public class AbilitiesClientEvents
{
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public static void onRenderOverlay(RenderGameOverlayEvent event) {
    Minecraft mc = Minecraft.getInstance();
    ClientPlayerEntity clientPlayerEntity = mc.player;
    IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
    
    if (abilityDataProps == null) {
      return;
    }
    int posX = mc.getMainWindow().getScaledWidth();
    int posY = mc.getMainWindow().getScaledHeight();
    
    if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE) {
      
      Optional<Ability> ability = Arrays.<Ability>stream(abilityDataProps.getEquippedAbilities(IOutOfBodyAbility.IS_ACTIVE)).findFirst();
      if (ability.isPresent()) {
        
        float distance = (float)(((IOutOfBodyAbility)ability.get()).getDistanceFromPivot((Entity)clientPlayerEntity) / ((IOutOfBodyAbility)ability.get()).getMaxRange());
        RendererHelper.renderVignette((Entity)clientPlayerEntity, distance, posX, posY);
      } 
    } 
  }
}


