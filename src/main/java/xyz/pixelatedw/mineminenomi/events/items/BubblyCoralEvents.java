package xyz.pixelatedw.mineminenomi.events.items;

import java.util.Objects;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.init.ModEffects;



@EventBusSubscriber(modid = "mineminenomi")
public class BubblyCoralEvents
{
  @SubscribeEvent
  public static void onBubblyCoralCheck(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL))
    {
      event.getEntityLiving().setAir(event.getEntityLiving().getMaxAir());
    }
  }

  
  @SubscribeEvent
  public static void livingDamage(LivingDamageEvent event) {
    if (event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL)) {
      
      float val = ((EffectInstance)Objects.<EffectInstance>requireNonNull(event.getEntityLiving().getActivePotionEffect(ModEffects.BUBBLY_CORAL))).getDuration() / 4500.0F;
      
      if (Math.random() > val)
        event.getEntityLiving().removePotionEffect(ModEffects.BUBBLY_CORAL); 
    } 
  }
}


