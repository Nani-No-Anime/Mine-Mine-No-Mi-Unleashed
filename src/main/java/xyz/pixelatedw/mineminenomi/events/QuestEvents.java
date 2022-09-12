package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
import xyz.pixelatedw.mineminenomi.api.events.LivingHealByEvent;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.*;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.AbilityUseEvent;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;





@EventBusSubscriber(modid = "mineminenomi")
public class QuestEvents
{
  @SubscribeEvent
  public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    IQuestData questProps = QuestDataCapability.get(player);
    
    if (player.world.isRemote) {
      return;
    }
    WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
  }

  
  @SubscribeEvent
  public static void onPotionRemoved(LivingEntityUseItemEvent.Stop event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    IQuestData questProps = QuestDataCapability.get(player);
    
    for (Objective obj : questProps.getInProgressObjectives()) {
      
      if (obj instanceof IUseItemObjective)
      {
        if (((IUseItemObjective)obj).checkItem(player, event.getItem(), event.getDuration())) {
          
          obj.alterProgress(1.0D);
          WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
        } 
      }
    } 
  }

  
  @SubscribeEvent
  public static void onPlayerAbilityUse(AbilityUseEvent event) {
    if (!(event.getPlayer()).world.isRemote) {
      
      PlayerEntity player = event.getPlayer();
      IQuestData questProps = QuestDataCapability.get(player);
      
      for (Objective obj : questProps.getInProgressObjectives()) {
        
        if (obj instanceof IUseAbilityObjective)
        {
          if (((IUseAbilityObjective)obj).checkAbility(player, event.getAbility())) {
            
            obj.alterProgress(1.0D);
            WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
          } 
        }
      } 
    } 
  }





  
  @SubscribeEvent
  public static void onPlayerBrews(PlayerBrewedPotionEvent event) {
    if ((event.getPlayer()).world.isRemote) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    IQuestData questProps = QuestDataCapability.get(player);
    
    for (Objective obj : questProps.getInProgressObjectives()) {
      
      if (obj instanceof IBrewPotionObjective)
      {
        if (((IBrewPotionObjective)obj).checkPotion(player, event.getStack())) {
          
          obj.alterProgress(1.0D);
          WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
        } 
      }
    } 
  }

  
  @SubscribeEvent
  public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END || (event.side == LogicalSide.SERVER && event.player.ticksExisted % 20 == 0)) {
      
      PlayerEntity player = event.player;
      IQuestData questProps = QuestDataCapability.get(player);
      
      for (Objective obj : questProps.getInProgressObjectives()) {
        
        if (obj instanceof IEquipItemObjective)
        {
          if (((IEquipItemObjective)obj).checkEquippedItem(player)) {
            
            obj.alterProgress(1.0D);
            WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
          } 
        }
        
        if (obj instanceof ISurviveObjective)
        {
          if (((ISurviveObjective)obj).checkTime(player)) {
            
            obj.alterProgress(1.0D);
            WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
          } 
        }
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onEntityDies(LivingDeathEvent event) {
    if (!(event.getSource().getTrueSource() instanceof PlayerEntity) || (event.getSource().getTrueSource()).world.isRemote) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
    LivingEntity target = event.getEntityLiving();
    IQuestData questProps = QuestDataCapability.get(player);
    
    for (Objective obj : questProps.getInProgressObjectives()) {
      
      if (obj instanceof IKillEntityObjective)
      {
        if (((IKillEntityObjective)obj).checkKill(player, target, event.getSource())) {
          
          obj.alterProgress(1.0D);
          WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
        } 
      }
    } 
  }

  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public static void onEntityAttack(LivingHurtEvent event) {
    if (event.getSource().getTrueSource() instanceof PlayerEntity && !(event.getSource().getTrueSource()).world.isRemote && event.getEntityLiving() instanceof LivingEntity) {
      
      PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
      LivingEntity target = event.getEntityLiving();
      IQuestData questProps = QuestDataCapability.get(player);
      
      for (Objective obj : questProps.getInProgressObjectives()) {
        
        if (obj instanceof IHitEntityObjective)
        {
          if (((IHitEntityObjective)obj).checkHit(player, target, event.getSource(), event.getAmount())) {
            
            obj.alterProgress(1.0D);
            WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
          } 
        }
      } 
    } 
  }

  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public static void onEntityHealed(LivingHealByEvent event) {
    if (event.getHealer() instanceof PlayerEntity && !(event.getHealer()).world.isRemote && event.getEntityLiving() instanceof LivingEntity) {
      
      PlayerEntity player = (PlayerEntity)event.getHealer();
      LivingEntity target = event.getEntityLiving();
      IQuestData questProps = QuestDataCapability.get(player);
      
      for (Objective obj : questProps.getInProgressObjectives()) {
        
        if (obj instanceof IHealEntityObjective)
        {
          if (((IHealEntityObjective)obj).checkHeal(player, target, event.getAmount())) {
            
            obj.alterProgress(1.0D);
            WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
          } 
        }
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onItemPickedUp(EntityItemPickupEvent event) {
    PlayerEntity player = event.getPlayer();
    IQuestData questProps = QuestDataCapability.get(player);
    
    if (player.world.isRemote) {
      return;
    }
    for (Objective obj : questProps.getInProgressObjectives()) {
      
      if (obj instanceof IObtainItemObjective)
      {
        if (((IObtainItemObjective)obj).checkItem(event.getItem().getItem())) {
          
          obj.alterProgress(event.getItem().getItem().getCount());
          WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
        } 
      }
    } 
  }

  
  @SubscribeEvent
  public static void onItemTossed(ItemTossEvent event) {
    PlayerEntity player = event.getPlayer();
    IQuestData questProps = QuestDataCapability.get(player);
    
    if (player.world.isRemote) {
      return;
    }
    for (Objective obj : questProps.getInProgressObjectives()) {
      
      if (obj instanceof IObtainItemObjective)
      {
        if (((IObtainItemObjective)obj).checkItem(event.getEntityItem().getItem())) {
          
          obj.alterProgress(-event.getEntityItem().getItem().getCount());
          WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
        } 
      }
    } 
  }

  
  @SubscribeEvent
  public static void onEntityInteract(PlayerInteractEvent.EntityInteractSpecific event) {
    if (event.getHand() != Hand.MAIN_HAND) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    IQuestData questProps = QuestDataCapability.get(player);
    
    for (Objective obj : questProps.getInProgressObjectives()) {
      
      if (obj instanceof IEntityInteractObjective)
      {
        if (((IEntityInteractObjective)obj).checkInteraction(player, event.getTarget())) {
          
          obj.alterProgress(1.0D);
          WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
          return;
        } 
      }
    } 
  }

  
  @SubscribeEvent
  public static void onDorikiGained(DorikiEvent event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    IQuestData questProps = QuestDataCapability.get(player);
    
    for (Objective obj : questProps.getInProgressObjectives()) {
      
      if (obj instanceof IReachDorikiObjective)
      {
        if (((IReachDorikiObjective)obj).checkDoriki(player)) {
          
          obj.alterProgress(1.0D);
          WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
        } 
      }
    } 
  }

  
  @SubscribeEvent
  public static void onPotionRemoved(PotionEvent.PotionRemoveEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IQuestData questProps = QuestDataCapability.get(player);
    
    for (Objective obj : questProps.getInProgressObjectives()) {
      
      if (obj instanceof ICureEffectObjective)
      {
        if (((ICureEffectObjective)obj).checkEffect(player, event.getPotionEffect())) {
          
          obj.alterProgress(1.0D);
          WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
        } 
      }
    } 
  }
}


