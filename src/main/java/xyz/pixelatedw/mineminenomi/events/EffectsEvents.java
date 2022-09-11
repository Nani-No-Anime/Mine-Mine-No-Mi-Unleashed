package xyz.pixelatedw.mineminenomi.events;

import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;
import xyz.pixelatedw.mineminenomi.api.effects.IIgnoreMilkEffect;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.effects.GuardingEffect;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
import xyz.pixelatedw.mineminenomi.packets.server.SSetEffectDetailsPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class EffectsEvents
{
  @EventBusSubscriber(modid = "mineminenomi")
  public static class Common {
    @SubscribeEvent
    public static void onDrinkMilk(PotionEvent.PotionRemoveEvent event) {
      if (event.getEntityLiving().getActiveHand() == null) {
        return;
      }
      boolean isMilkBucket = (event.getEntityLiving().getHeldItem(event.getEntityLiving().getActiveHand()).getItem() == Items.MILK_BUCKET);
      
      if (isMilkBucket)
      {
        if (event.getPotion() instanceof IIgnoreMilkEffect && !((IIgnoreMilkEffect)event.getPotion()).isRemoveable()) {
          event.setCanceled(true);
        }
      }
    }
    
    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event) {
      LivingEntity entity = event.getEntityLiving();
      Entity source = event.getSource().getTrueSource();
      for (EffectInstance effectInstance : entity.getActivePotionEffects()) {
        
        if (effectInstance.getPotion() instanceof GuardingEffect) {
          
          GuardingEffect instance = (GuardingEffect)effectInstance.getPotion();
          if (instance.useOnlySources && !instance.acceptableSources.contains(event.getSource().getDamageType())) {
            return;
          }
          if (event.getSource().isDamageAbsolute()) {
            return;
          }
          if (instance.reduceSpeedAfterHit) {
            Objects.requireNonNull(entity); entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 1, false, false));
          } 
          float power = (effectInstance.getAmplifier() + 1) * 2.5F;
          power *= event.getSource().isUnblockable() ? 0.8F : 1.0F;
          
          if (event.getAmount() < power) {
            
            entity.setMotion(0.0D, 0.0D, 0.0D);
            event.setAmount(0.0F);
            event.setCanceled(true);
          } else {
            event.setAmount(event.getAmount() - power);
          } 
          return;
        } 
        if (effectInstance.getPotion() instanceof xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect) {
          
          int dur = effectInstance.getDuration();
          double damage = event.getAmount();
          
          int newDur = (int)(dur - damage * 100.0D);
          ((EffectInstanceMixin)effectInstance).setDuration(newDur);
          WyNetwork.sendToAllTrackingAndSelf(new SSetEffectDetailsPacket(entity.getEntityId(), effectInstance), entity);
          
          if (effectInstance.getDuration() <= 1)
          {
            if (entity instanceof PlayerEntity) {
              
              PlayerEntity player = (PlayerEntity)entity;
              AbilityHelper.enableAbilities(player, ability -> (ability.getCategory() == AbilityHelper.getDevilFruitCategory()));
            } 
          }
        } 
      } 
    }
  }


  
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class Client
  {
    @SubscribeEvent
    public static void onMouseClicked(InputEvent.ClickInputEvent event) {
      if (event.getHand() == Hand.MAIN_HAND) {
        
        ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;


        
        Objects.requireNonNull(IBindHandsEffect.class); clientPlayerEntity.getActivePotionEffects().stream().map(EffectInstance::getPotion).filter(IBindHandsEffect.class::isInstance)
          .filter(eff -> ((IBindHandsEffect)eff).isBlockingSwings())
          .forEach(eff -> {
              event.setCanceled(true);
              event.setSwingHand(false);
            });
      } 
    }


    
    @SubscribeEvent
    public static void onHandRendering(RenderHandEvent event) {
      if (event.getHand() != Hand.MAIN_HAND) {
        return;
      }
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      
      if (clientPlayerEntity.isPotionActive(ModEffects.NO_HANDS)) {
        event.setCanceled(true);
      }
    }
    
    @SubscribeEvent
    public static void onShocked(EntityViewRenderEvent.CameraSetup event) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      
      if (!clientPlayerEntity.isPotionActive(ModEffects.DIZZY)) {
        return;
      }
      if (clientPlayerEntity.getActivePotionEffect(ModEffects.DIZZY).getDuration() <= 0) {
        
        clientPlayerEntity.removePotionEffect(ModEffects.DIZZY);
        
        return;
      } 
      if ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0)
      {
        event.setRoll((float)Math.sin((((PlayerEntity)clientPlayerEntity).ticksExisted % 100)));
      }
    }
  }
}


