package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.suke.SkattingAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


@EventBusSubscriber(modid = "mineminenomi")
public class SukePassiveEvents
{
  @SubscribeEvent
  @OnlyIn(Dist.CLIENT)
  public static void onEntityRendered(RenderLivingEvent.Pre event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("suke_suke")) {
      return;
    }
    SkattingAbility ability = (SkattingAbility)abilityProps.getEquippedAbility(SkattingAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (isActive)
      event.setCanceled(true); 
  }
}


