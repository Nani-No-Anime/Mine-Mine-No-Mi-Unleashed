/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.living.PotionEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.LivingHealByEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IBrewPotionObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.ICureEffectObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IEntityInteractObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IEquipItemObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IHealEntityObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IHitEntityObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IKillEntityObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IObtainItemObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IReachDorikiObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.ISurviveObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IUseAbilityObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IUseItemObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.AbilityUseEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class QuestEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/*  50 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  53 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*  54 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/*  56 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/*  59 */     WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPotionRemoved(LivingEntityUseItemEvent.Stop event) {
/*  65 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  68 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*  69 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/*  71 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/*  73 */       if (obj instanceof IUseItemObjective)
/*     */       {
/*  75 */         if (((IUseItemObjective)obj).checkItem(player, event.getItem(), event.getDuration())) {
/*     */           
/*  77 */           obj.alterProgress(1.0D);
/*  78 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerAbilityUse(AbilityUseEvent event) {
/*  87 */     if (!(event.getPlayer()).world.isRemote) {
/*     */       
/*  89 */       PlayerEntity player = event.getPlayer();
/*  90 */       IQuestData questProps = QuestDataCapability.get(player);
/*     */       
/*  92 */       for (Objective obj : questProps.getInProgressObjectives()) {
/*     */         
/*  94 */         if (obj instanceof IUseAbilityObjective)
/*     */         {
/*  96 */           if (((IUseAbilityObjective)obj).checkAbility(player, event.getAbility())) {
/*     */             
/*  98 */             obj.alterProgress(1.0D);
/*  99 */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerBrews(PlayerBrewedPotionEvent event) {
/* 113 */     if ((event.getPlayer()).world.isRemote) {
/*     */       return;
/*     */     }
/* 116 */     PlayerEntity player = event.getPlayer();
/* 117 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 119 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 121 */       if (obj instanceof IBrewPotionObjective)
/*     */       {
/* 123 */         if (((IBrewPotionObjective)obj).checkPotion(player, event.getStack())) {
/*     */           
/* 125 */           obj.alterProgress(1.0D);
/* 126 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
/* 135 */     if (event.phase != TickEvent.Phase.END || (event.side == LogicalSide.SERVER && event.player.ticksExisted % 20 == 0)) {
/*     */       
/* 137 */       PlayerEntity player = event.player;
/* 138 */       IQuestData questProps = QuestDataCapability.get(player);
/*     */       
/* 140 */       for (Objective obj : questProps.getInProgressObjectives()) {
/*     */         
/* 142 */         if (obj instanceof IEquipItemObjective)
/*     */         {
/* 144 */           if (((IEquipItemObjective)obj).checkEquippedItem(player)) {
/*     */             
/* 146 */             obj.alterProgress(1.0D);
/* 147 */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */           } 
/*     */         }
/*     */         
/* 151 */         if (obj instanceof ISurviveObjective)
/*     */         {
/* 153 */           if (((ISurviveObjective)obj).checkTime(player)) {
/*     */             
/* 155 */             obj.alterProgress(1.0D);
/* 156 */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityDies(LivingDeathEvent event) {
/* 166 */     if (!(event.getSource().getTrueSource() instanceof PlayerEntity) || (event.getSource().getTrueSource()).world.isRemote) {
/*     */       return;
/*     */     }
/* 169 */     PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
/* 170 */     LivingEntity target = event.getEntityLiving();
/* 171 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 173 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 175 */       if (obj instanceof IKillEntityObjective)
/*     */       {
/* 177 */         if (((IKillEntityObjective)obj).checkKill(player, target, event.getSource())) {
/*     */           
/* 179 */           obj.alterProgress(1.0D);
/* 180 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public static void onEntityAttack(LivingHurtEvent event) {
/* 189 */     if (event.getSource().getTrueSource() instanceof PlayerEntity && !(event.getSource().getTrueSource()).world.isRemote && event.getEntityLiving() instanceof LivingEntity) {
/*     */       
/* 191 */       PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
/* 192 */       LivingEntity target = event.getEntityLiving();
/* 193 */       IQuestData questProps = QuestDataCapability.get(player);
/*     */       
/* 195 */       for (Objective obj : questProps.getInProgressObjectives()) {
/*     */         
/* 197 */         if (obj instanceof IHitEntityObjective)
/*     */         {
/* 199 */           if (((IHitEntityObjective)obj).checkHit(player, target, event.getSource(), event.getAmount())) {
/*     */             
/* 201 */             obj.alterProgress(1.0D);
/* 202 */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public static void onEntityHealed(LivingHealByEvent event) {
/* 212 */     if (event.getHealer() instanceof PlayerEntity && !(event.getHealer()).world.isRemote && event.getEntityLiving() instanceof LivingEntity) {
/*     */       
/* 214 */       PlayerEntity player = (PlayerEntity)event.getHealer();
/* 215 */       LivingEntity target = event.getEntityLiving();
/* 216 */       IQuestData questProps = QuestDataCapability.get(player);
/*     */       
/* 218 */       for (Objective obj : questProps.getInProgressObjectives()) {
/*     */         
/* 220 */         if (obj instanceof IHealEntityObjective)
/*     */         {
/* 222 */           if (((IHealEntityObjective)obj).checkHeal(player, target, event.getAmount())) {
/*     */             
/* 224 */             obj.alterProgress(1.0D);
/* 225 */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemPickedUp(EntityItemPickupEvent event) {
/* 235 */     PlayerEntity player = event.getPlayer();
/* 236 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 238 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/* 241 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 243 */       if (obj instanceof IObtainItemObjective)
/*     */       {
/* 245 */         if (((IObtainItemObjective)obj).checkItem(event.getItem().getItem())) {
/*     */           
/* 247 */           obj.alterProgress(event.getItem().getItem().getCount());
/* 248 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemTossed(ItemTossEvent event) {
/* 257 */     PlayerEntity player = event.getPlayer();
/* 258 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 260 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/* 263 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 265 */       if (obj instanceof IObtainItemObjective)
/*     */       {
/* 267 */         if (((IObtainItemObjective)obj).checkItem(event.getEntityItem().getItem())) {
/*     */           
/* 269 */           obj.alterProgress(-event.getEntityItem().getItem().getCount());
/* 270 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityInteract(PlayerInteractEvent.EntityInteractSpecific event) {
/* 279 */     if (event.getHand() != Hand.MAIN_HAND) {
/*     */       return;
/*     */     }
/* 282 */     PlayerEntity player = event.getPlayer();
/* 283 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 285 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 287 */       if (obj instanceof IEntityInteractObjective)
/*     */       {
/* 289 */         if (((IEntityInteractObjective)obj).checkInteraction(player, event.getTarget())) {
/*     */           
/* 291 */           obj.alterProgress(1.0D);
/* 292 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */           return;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDorikiGained(DorikiEvent event) {
/* 302 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 305 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/* 306 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 308 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 310 */       if (obj instanceof IReachDorikiObjective)
/*     */       {
/* 312 */         if (((IReachDorikiObjective)obj).checkDoriki(player)) {
/*     */           
/* 314 */           obj.alterProgress(1.0D);
/* 315 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPotionRemoved(PotionEvent.PotionRemoveEvent event) {
/* 324 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 327 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 328 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 330 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 332 */       if (obj instanceof ICureEffectObjective)
/*     */       {
/* 334 */         if (((ICureEffectObjective)obj).checkEffect(player, event.getPotionEffect())) {
/*     */           
/* 336 */           obj.alterProgress(1.0D);
/* 337 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\QuestEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */