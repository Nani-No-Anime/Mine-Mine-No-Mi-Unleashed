package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SUpdateHealthPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

@EventBusSubscriber(modid = "mineminenomi")
public class SyncEvents
{
  @SubscribeEvent
  public static void onDorikiGained(DorikiEvent event) {
    if (event.getPlayer() != null && CommonConfig.INSTANCE.isExtraHeartsEnabled()) {
      
      IAttributeInstance maxHpAttribute = event.getPlayer().getAttribute(SharedMonsterAttributes.MAX_HEALTH);
      IEntityStats props = EntityStatsCapability.get((LivingEntity)event.getPlayer());
      
      if (props.getDoriki() / 100 <= 20) {
        maxHpAttribute.setBaseValue(20.0D);
      } else {
        maxHpAttribute.setBaseValue((props.getDoriki() / 100));
      } 
      ((ServerPlayerEntity)event.getPlayer()).connection.sendPacket((IPacket)new SUpdateHealthPacket(event.getPlayer().getHealth(), event.getPlayer().getFoodStats().getFoodLevel(), event.getPlayer().getFoodStats().getSaturationLevel()));
    } 
  }

  
  @SubscribeEvent
  public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      if (!player.world.isRemote && player.ticksExisted < 5) {
        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SUpdateHealthPacket(player.getHealth(), player.getFoodStats().getFoodLevel(), player.getFoodStats().getSaturationLevel()));
      }
    } 
  }
  
  @SubscribeEvent
  public static void onPlayerChangeDimensions(PlayerEvent.PlayerChangedDimensionEvent event) {
    PlayerEntity player = event.getPlayer();
    
    IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
    IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
    IHakiData hakiDataProps = HakiDataCapability.get((LivingEntity)player);
    
    WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), (LivingEntity)player);
    WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), (LivingEntity)player);
    WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), (LivingEntity)player);
    WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getEntityId(), entityProps), (LivingEntity)player);
    WyNetwork.sendToAllTrackingAndSelf(new SSyncHakiDataPacket(player.getEntityId(), hakiDataProps), (LivingEntity)player);

    
    MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
    WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
  }

  
  @SubscribeEvent
  public static void onPlayerStartsTracking(PlayerEvent.StartTracking event) {
    if (event.getTarget() instanceof PlayerEntity) {
      
      PlayerEntity targetPlayer = (PlayerEntity)event.getTarget();
      
      IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)targetPlayer);
      IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)targetPlayer);
      IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)targetPlayer);
      IHakiData hakiDataProps = HakiDataCapability.get((LivingEntity)targetPlayer);
      
      WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(targetPlayer.getEntityId(), entityStatsProps), (LivingEntity)targetPlayer);
      WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(targetPlayer.getEntityId(), devilFruitProps), (LivingEntity)targetPlayer);
      WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(targetPlayer.getEntityId(), abilityDataProps), (LivingEntity)targetPlayer);
      WyNetwork.sendToAllTrackingAndSelf(new SSyncHakiDataPacket(targetPlayer.getEntityId(), hakiDataProps), (LivingEntity)targetPlayer);
    } 
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onEntityJoinsWorld(EntityJoinWorldEvent event) {
    if (event.getEntity() instanceof net.minecraft.client.entity.player.ClientPlayerEntity);
  }
}


