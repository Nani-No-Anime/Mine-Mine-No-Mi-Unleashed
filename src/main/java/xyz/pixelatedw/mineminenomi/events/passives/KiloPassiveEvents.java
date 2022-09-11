package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


@EventBusSubscriber(modid = "mineminenomi")
public class KiloPassiveEvents
{
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    IDevilFruit devilProps = DevilFruitCapability.get((LivingEntity)player);
    
    if (!devilProps.getDevilFruit().equalsIgnoreCase("kilo_kilo")) {
      return;
    }
    Ability ability = abilityProps.getEquippedAbility((Ability)KiloPress1Ability.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    boolean hasUmbrella = (player.getHeldItemMainhand().getItem() == ModWeapons.UMBRELLA || player.getHeldItemOffhand().getItem() == ModWeapons.UMBRELLA);
    boolean inAir = (!player.onGround && (player.getMotion()).y < 0.0D);
    
    if (isActive && hasUmbrella && inAir)
      player.setMotion((player.getMotion()).x, (player.getMotion()).y / 2.0D, (player.getMotion()).z); 
  }
}


