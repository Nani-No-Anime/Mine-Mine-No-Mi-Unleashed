package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.api.events.RenderMorphEvent;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.awt.*;
import java.util.Iterator;




@EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
public class SpecialPotionEffectEvents
{
  @SubscribeEvent
  public static void onEntityPreRendered(RenderLivingEvent.Pre event) {
    LivingEntity entity = event.getEntity();
    checkForRotationBlockEffect(entity);
  }

  
  @SubscribeEvent
  public static void onEntityPostRendered(RenderLivingEvent.Post event) {
    LivingEntity entity = event.getEntity();
    checkForRotationBlockEffect(entity);
  }

  
  @SubscribeEvent
  public static void onEntityPreRendered(RenderMorphEvent.Pre event) {
    LivingEntity entity = event.getEntityLiving();
    checkForRotationBlockEffect(entity);
  }

  
  @SubscribeEvent
  public static void onEntityPostRendered(RenderMorphEvent.Post event) {
    LivingEntity entity = event.getEntityLiving();
    checkForRotationBlockEffect(entity);
  }

  
  private static void checkForRotationBlockEffect(LivingEntity entity) {
    for (EffectInstance instance : entity.getActivePotionEffects()) {
      
      if (instance.getPotion() instanceof ModEffect) {
        
        if (entity.getActivePotionEffect(instance.getPotion()).getDuration() <= 0) {
          
          entity.removePotionEffect(instance.getPotion());
          
          return;
        } 
        if (((ModEffect)instance.getPotion()).isBlockingRotations()) {
          
          entity.renderYawOffset = 0.0F;
          entity.prevRenderYawOffset = 0.0F;
        } 
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onFirstPersonViewRendered(TickEvent.RenderTickEvent event) {
    Minecraft mc = Minecraft.getInstance();
    ClientPlayerEntity clientPlayerEntity = mc.player;
    
    if (clientPlayerEntity == null) {
      return;
    }
    if (mc.gameSettings.thirdPersonView != 0) {
      return;
    }
    Iterator<EffectInstance> iterator = clientPlayerEntity.getActivePotionEffects().iterator();
    while (iterator.hasNext()) {
      
      EffectInstance instance = iterator.next();
      
      if (instance.getPotion() instanceof OverlayEffect) {
        
        OverlayEffect effect = (OverlayEffect)instance.getPotion();
        if (effect.getResourceLocation(instance.getDuration()) == null && effect.getOverlayColor() != null) {
          
          float[] colors = ((OverlayEffect)instance.getPotion()).getOverlayColor();
          Color color = new Color(colors[0], colors[1], colors[2]);
          WyHelper.drawColourOnScreen(color.getRGB(), (int)(colors[3] * 255.0F), 0.0D, 0.0D, mc.getMainWindow().getScaledWidth(), mc.getMainWindow().getScaledHeight(), 500.0D);
        } 
      } 
    } 
  }
}


