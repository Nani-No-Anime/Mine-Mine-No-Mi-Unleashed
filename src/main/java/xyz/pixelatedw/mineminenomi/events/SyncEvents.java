/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SUpdateHealthPacket;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class SyncEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onDorikiGained(DorikiEvent event) {
/*  43 */     if (event.getPlayer() != null && CommonConfig.INSTANCE.isExtraHeartsEnabled()) {
/*     */       
/*  45 */       IAttributeInstance maxHpAttribute = event.getPlayer().getAttribute(SharedMonsterAttributes.MAX_HEALTH);
/*  46 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)event.getPlayer());
/*     */       
/*  48 */       if (props.getDoriki() / 100 <= 20) {
/*  49 */         maxHpAttribute.setBaseValue(20.0D);
/*     */       } else {
/*  51 */         maxHpAttribute.setBaseValue((props.getDoriki() / 100));
/*     */       } 
/*  53 */       ((ServerPlayerEntity)event.getPlayer()).connection.sendPacket((IPacket)new SUpdateHealthPacket(event.getPlayer().getHealth(), event.getPlayer().getFoodStats().getFoodLevel(), event.getPlayer().getFoodStats().getSaturationLevel()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*  60 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/*     */       
/*  62 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  63 */       if (!player.world.isRemote && player.ticksExisted < 5) {
/*  64 */         ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SUpdateHealthPacket(player.getHealth(), player.getFoodStats().getFoodLevel(), player.getFoodStats().getSaturationLevel()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerChangeDimensions(PlayerEvent.PlayerChangedDimensionEvent event) {
/*  71 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  73 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*  74 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  75 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*  76 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/*  77 */     IHakiData hakiDataProps = HakiDataCapability.get((LivingEntity)player);
/*     */     
/*  79 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), (LivingEntity)player);
/*  80 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), (LivingEntity)player);
/*  81 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), (LivingEntity)player);
/*  82 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getEntityId(), entityProps), (LivingEntity)player);
/*  83 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncHakiDataPacket(player.getEntityId(), hakiDataProps), (LivingEntity)player);
/*     */ 
/*     */     
/*  86 */     MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
/*  87 */     WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerStartsTracking(PlayerEvent.StartTracking event) {
/*  93 */     if (event.getTarget() instanceof PlayerEntity) {
/*     */       
/*  95 */       PlayerEntity targetPlayer = (PlayerEntity)event.getTarget();
/*     */       
/*  97 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)targetPlayer);
/*  98 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)targetPlayer);
/*  99 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)targetPlayer);
/* 100 */       IHakiData hakiDataProps = HakiDataCapability.get((LivingEntity)targetPlayer);
/*     */       
/* 102 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(targetPlayer.getEntityId(), entityStatsProps), (LivingEntity)targetPlayer);
/* 103 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(targetPlayer.getEntityId(), devilFruitProps), (LivingEntity)targetPlayer);
/* 104 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(targetPlayer.getEntityId(), abilityDataProps), (LivingEntity)targetPlayer);
/* 105 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncHakiDataPacket(targetPlayer.getEntityId(), hakiDataProps), (LivingEntity)targetPlayer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onEntityJoinsWorld(EntityJoinWorldEvent event) {
/* 113 */     if (event.getEntity() instanceof net.minecraft.client.entity.player.ClientPlayerEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\SyncEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */