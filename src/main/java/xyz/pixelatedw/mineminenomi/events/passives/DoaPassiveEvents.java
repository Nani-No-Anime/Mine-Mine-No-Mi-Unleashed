package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.doa.AirDoorAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;






@EventBusSubscriber(modid = "mineminenomi")
public class DoaPassiveEvents
{
  @SubscribeEvent
  public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isInsideDoor(player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onItemPickup(EntityItemPickupEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isInsideDoor(player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onItemTossed(ItemTossEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isInsideDoor(player)) {
      return;
    }
    event.setCanceled(true);
    player.addItemStackToInventory(event.getEntityItem().getItem());
  }

  
  @SubscribeEvent
  public static void onEntityHits(AttackEntityEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isInsideDoor(player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityLeftClickBlocks(PlayerInteractEvent.LeftClickBlock event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isInsideDoor(player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityRightClickBlocks(PlayerInteractEvent.RightClickBlock event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isInsideDoor(player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityBreaksBlocks(BlockEvent.BreakEvent event) {
    PlayerEntity player = event.getPlayer();
    
    if (!isInsideDoor(player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityPlaceBlocks(BlockEvent.EntityPlaceEvent event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    
    if (!isInsideDoor(player)) {
      return;
    }
    event.setCanceled(true);
  }

  
  @SubscribeEvent
  public static void onEntityTargetedEvent(LivingSetAttackTargetEvent event) {
    if (!(event.getTarget() instanceof PlayerEntity) || event.getTarget() instanceof net.minecraftforge.common.util.FakePlayer || !(event.getEntity() instanceof MobEntity)) {
      return;
    }
    if (!isInsideDoor((PlayerEntity)event.getTarget())) {
      return;
    }
    MobEntity entity = (MobEntity)event.getEntity();
    
    entity.setAttackTarget(null);
  }


  
  public static boolean isInsideDoor(PlayerEntity player) {
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.DOA_DOA_NO_MI)) {
      return false;
    }
    Ability ability = abilityProps.getEquippedAbility((Ability)AirDoorAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (!isActive) {
      return false;
    }
    return true;
  }

  
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class Client
  {
    @SubscribeEvent
    public static void onPlayerCameraSetup(EntityViewRenderEvent.CameraSetup event) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      
      if (!clientPlayerEntity.isPotionActive(ModEffects.DOOR_HEAD)) {
        return;
      }
      if (clientPlayerEntity.getActivePotionEffect(ModEffects.DOOR_HEAD).getDuration() <= 0) {
        
        clientPlayerEntity.removePotionEffect(ModEffects.DOOR_HEAD);
        
        return;
      } 
      if ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0) {
        event.setYaw((((PlayerEntity)clientPlayerEntity).ticksExisted * 10 % 360));
      }
    }
    
    @SubscribeEvent
    public static void onEntityRendered(RenderLivingEvent.Pre event) {
      LivingEntity entity = event.getEntity();
      
      if (entity.isPotionActive(ModEffects.DOOR_HEAD)) {
        
        if (entity.getActivePotionEffect(ModEffects.DOOR_HEAD).getDuration() <= 0) {
          
          entity.removePotionEffect(ModEffects.DOOR_HEAD);
          
          return;
        } 
        entity.renderYawOffset = 0.0F;
        entity.prevRenderYawOffset = 0.0F;
      } 
      
      IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
      IAbilityData abilityProps = AbilityDataCapability.get(entity);
      
      if (devilFruitProps.hasDevilFruit(ModAbilities.DOA_DOA_NO_MI)) {
        
        AirDoorAbility ability = (AirDoorAbility)abilityProps.getEquippedAbility((Ability)AirDoorAbility.INSTANCE);
        boolean isActive = (ability != null && ability.isContinuous());
        
        if (isActive) {
          event.setCanceled(true);
        }
      } 
    }

    
    @SubscribeEvent
    public static void onEntityRendered(RenderLivingEvent.Post event) {
      LivingEntity entity = event.getEntity();
      
      if (!entity.isPotionActive(ModEffects.DOOR_HEAD)) {
        return;
      }
      if (entity.getActivePotionEffect(ModEffects.DOOR_HEAD).getDuration() <= 0) {
        
        entity.removePotionEffect(ModEffects.DOOR_HEAD);
        
        return;
      } 
      entity.rotationYawHead += 10.0F;
      entity.prevRotationYawHead += 10.0F;
      entity.renderYawOffset = 0.0F;
      entity.prevRenderYawOffset = 0.0F;
    }

    
    @SubscribeEvent
    public static void onFirstPersonViewRendered(TickEvent.RenderTickEvent event) {
      Minecraft mc = Minecraft.getInstance();
      ClientPlayerEntity clientPlayerEntity = mc.player;
      
      if (clientPlayerEntity == null) {
        return;
      }
      if (!DoaPassiveEvents.isInsideDoor((PlayerEntity)clientPlayerEntity)) {
        return;
      }
      WyHelper.drawColourOnScreen(WyHelper.hexToRGB("#2AFFAE").getRGB(), 50, 0.0D, 0.0D, mc.getMainWindow().getScaledWidth(), mc.getMainWindow().getScaledHeight(), 500.0D);
    }
  }
}


