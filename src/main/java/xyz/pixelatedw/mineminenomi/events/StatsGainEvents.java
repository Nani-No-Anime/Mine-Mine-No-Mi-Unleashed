/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.BountyEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class StatsGainEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityDeath(LivingDeathEvent event) {
/*  33 */     if (event.getSource().getTrueSource() instanceof PlayerEntity) {
/*     */       
/*  35 */       PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
/*  36 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  37 */       LivingEntity target = event.getEntityLiving();
/*     */       
/*  39 */       IAttributeInstance attrAtk = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  40 */       IAttributeInstance attrHP = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
/*     */       
/*  42 */       long plusBelly = 0L;
/*  43 */       long plusBounty = 0L;
/*  44 */       double plusDoriki = 0.0D;
/*     */       
/*  46 */       if (target instanceof PlayerEntity) {
/*     */         
/*  48 */         IEntityStats targetprops = EntityStatsCapability.get(target);
/*     */ 
/*     */         
/*  51 */         if (props.getDoriki() * 0.75D <= targetprops.getDoriki()) {
/*  52 */           plusDoriki = (targetprops.getDoriki() / 4.0F);
/*     */         }
/*  54 */         plusBounty = targetprops.getBounty() / 2L;
/*  55 */         plusBelly = targetprops.getBelly();
/*     */       }
/*     */       else {
/*     */         
/*  59 */         if (props.isMarine() && target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity) {
/*     */           return;
/*     */         }
/*  62 */         if (target instanceof OPEntity) {
/*     */           
/*  64 */           OPEntity entity = (OPEntity)target;
/*     */           
/*  66 */           if (props.getDoriki() / 100 > entity.getDoriki()) {
/*     */             
/*  68 */             if (CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled()) {
/*  69 */               plusDoriki = 1.0D;
/*     */             }
/*     */           } else {
/*  72 */             plusDoriki = entity.getDoriki();
/*     */           } 
/*  74 */           plusDoriki *= CommonConfig.INSTANCE.getDorikiRewardMultiplier();
/*     */           
/*  76 */           plusBounty = (entity.getDoriki() * 2);
/*  77 */           plusBelly = entity.getBelly();
/*     */ 
/*     */         
/*     */         }
/*  81 */         else if (attrAtk != null && attrHP != null) {
/*     */           
/*  83 */           double i = attrAtk.getBaseValue();
/*  84 */           double j = attrHP.getBaseValue();
/*     */           
/*  86 */           double targetDoriki = (int)Math.round((i + j) / 4.0D);
/*     */           
/*  88 */           if ((props.getDoriki() / 100.0F) > targetDoriki) {
/*     */             
/*  90 */             if (CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled()) {
/*  91 */               plusDoriki = 1.0D;
/*     */             }
/*     */           } else {
/*  94 */             plusDoriki = targetDoriki;
/*     */           } 
/*  96 */           plusDoriki *= CommonConfig.INSTANCE.getDorikiRewardMultiplier();
/*     */           
/*  98 */           plusBounty = (int)Math.round((i + j) / 10.0D);
/*  99 */           plusBelly = 1L;
/*     */           
/* 101 */           if (target instanceof net.minecraft.entity.merchant.villager.VillagerEntity) {
/* 102 */             plusBounty = 10L;
/*     */           }
/*     */         } else {
/*     */           
/* 106 */           plusDoriki = 0.0D;
/* 107 */           plusBounty = 1L;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 112 */       if (plusDoriki > 0.0D)
/*     */       {
/* 114 */         if (props.getDoriki() + plusDoriki <= CommonConfig.INSTANCE.getDorikiLimit()) {
/*     */           
/* 116 */           if (1.0D > CommonConfig.INSTANCE.getDorikiRewardMultiplier() && CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled() && plusDoriki > 0.0D && plusDoriki < 1.0D) {
/*     */             
/* 118 */             double random = WyHelper.randomWithRange(0, 100);
/* 119 */             if (plusDoriki * 100.0D > random) {
/* 120 */               plusDoriki = 1.0D;
/*     */             }
/*     */           } 
/* 123 */           props.alterDoriki((int)Math.round(plusDoriki));
/* 124 */           DorikiEvent e = new DorikiEvent(player, props.getDoriki());
/* 125 */           if (MinecraftForge.EVENT_BUS.post((Event)e)) {
/*     */             return;
/*     */           }
/* 128 */         } else if (props.getDoriki() + plusDoriki > CommonConfig.INSTANCE.getDorikiLimit()) {
/*     */           
/* 130 */           props.setDoriki(CommonConfig.INSTANCE.getDorikiLimit());
/* 131 */           DorikiEvent e = new DorikiEvent(player, props.getDoriki());
/* 132 */           if (MinecraftForge.EVENT_BUS.post((Event)e)) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       }
/* 137 */       if (BountyHelper.canGainBounty(player))
/*     */       {
/* 139 */         if (plusBounty > 0L && props.getBounty() + plusBounty < 999999999L) {
/*     */           
/* 141 */           BountyEvent bountyEvent = new BountyEvent(player, plusBounty);
/* 142 */           if (!MinecraftForge.EVENT_BUS.post((Event)bountyEvent)) {
/* 143 */             props.alterBounty(plusBounty);
/*     */           }
/*     */         } 
/*     */       }
/* 147 */       if (props.getBelly() + plusBelly < 999999999L) {
/* 148 */         props.alterBelly(plusBelly);
/*     */       }
/* 150 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\StatsGainEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */