/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doa.AirDoorAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class DoaPassiveEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
/*  40 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  42 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*  45 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemPickup(EntityItemPickupEvent event) {
/*  51 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  53 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*  56 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemTossed(ItemTossEvent event) {
/*  62 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  64 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*  67 */     event.setCanceled(true);
/*  68 */     player.addItemStackToInventory(event.getEntityItem().getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityHits(AttackEntityEvent event) {
/*  74 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  76 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*  79 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityLeftClickBlocks(PlayerInteractEvent.LeftClickBlock event) {
/*  85 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  87 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*  90 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRightClickBlocks(PlayerInteractEvent.RightClickBlock event) {
/*  96 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  98 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/* 101 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityBreaksBlocks(BlockEvent.BreakEvent event) {
/* 107 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 109 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/* 112 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityPlaceBlocks(BlockEvent.EntityPlaceEvent event) {
/* 118 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 121 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */     
/* 123 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/* 126 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityTargetedEvent(LivingSetAttackTargetEvent event) {
/* 132 */     if (!(event.getTarget() instanceof PlayerEntity) || event.getTarget() instanceof net.minecraftforge.common.util.FakePlayer || !(event.getEntity() instanceof MobEntity)) {
/*     */       return;
/*     */     }
/* 135 */     if (!isInsideDoor((PlayerEntity)event.getTarget())) {
/*     */       return;
/*     */     }
/* 138 */     MobEntity entity = (MobEntity)event.getEntity();
/*     */     
/* 140 */     entity.setAttackTarget(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isInsideDoor(PlayerEntity player) {
/* 146 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 147 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 149 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.DOA_DOA_NO_MI)) {
/* 150 */       return false;
/*     */     }
/* 152 */     Ability ability = abilityProps.getEquippedAbility((Ability)AirDoorAbility.INSTANCE);
/* 153 */     boolean isActive = (ability != null && ability.isContinuous());
/*     */     
/* 155 */     if (!isActive) {
/* 156 */       return false;
/*     */     }
/* 158 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onPlayerCameraSetup(EntityViewRenderEvent.CameraSetup event) {
/* 167 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*     */       
/* 169 */       if (!clientPlayerEntity.isPotionActive(ModEffects.DOOR_HEAD)) {
/*     */         return;
/*     */       }
/* 172 */       if (clientPlayerEntity.getActivePotionEffect(ModEffects.DOOR_HEAD).getDuration() <= 0) {
/*     */         
/* 174 */         clientPlayerEntity.removePotionEffect(ModEffects.DOOR_HEAD);
/*     */         
/*     */         return;
/*     */       } 
/* 178 */       if ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0) {
/* 179 */         event.setYaw((((PlayerEntity)clientPlayerEntity).ticksExisted * 10 % 360));
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 185 */       LivingEntity entity = event.getEntity();
/*     */       
/* 187 */       if (entity.isPotionActive(ModEffects.DOOR_HEAD)) {
/*     */         
/* 189 */         if (entity.getActivePotionEffect(ModEffects.DOOR_HEAD).getDuration() <= 0) {
/*     */           
/* 191 */           entity.removePotionEffect(ModEffects.DOOR_HEAD);
/*     */           
/*     */           return;
/*     */         } 
/* 195 */         entity.renderYawOffset = 0.0F;
/* 196 */         entity.prevRenderYawOffset = 0.0F;
/*     */       } 
/*     */       
/* 199 */       IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
/* 200 */       IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*     */       
/* 202 */       if (devilFruitProps.hasDevilFruit(ModAbilities.DOA_DOA_NO_MI)) {
/*     */         
/* 204 */         AirDoorAbility ability = (AirDoorAbility)abilityProps.getEquippedAbility((Ability)AirDoorAbility.INSTANCE);
/* 205 */         boolean isActive = (ability != null && ability.isContinuous());
/*     */         
/* 207 */         if (isActive) {
/* 208 */           event.setCanceled(true);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Post event) {
/* 216 */       LivingEntity entity = event.getEntity();
/*     */       
/* 218 */       if (!entity.isPotionActive(ModEffects.DOOR_HEAD)) {
/*     */         return;
/*     */       }
/* 221 */       if (entity.getActivePotionEffect(ModEffects.DOOR_HEAD).getDuration() <= 0) {
/*     */         
/* 223 */         entity.removePotionEffect(ModEffects.DOOR_HEAD);
/*     */         
/*     */         return;
/*     */       } 
/* 227 */       entity.rotationYawHead += 10.0F;
/* 228 */       entity.prevRotationYawHead += 10.0F;
/* 229 */       entity.renderYawOffset = 0.0F;
/* 230 */       entity.prevRenderYawOffset = 0.0F;
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onFirstPersonViewRendered(TickEvent.RenderTickEvent event) {
/* 236 */       Minecraft mc = Minecraft.getInstance();
/* 237 */       ClientPlayerEntity clientPlayerEntity = mc.player;
/*     */       
/* 239 */       if (clientPlayerEntity == null) {
/*     */         return;
/*     */       }
/* 242 */       if (!DoaPassiveEvents.isInsideDoor((PlayerEntity)clientPlayerEntity)) {
/*     */         return;
/*     */       }
/* 245 */       WyHelper.drawColourOnScreen(WyHelper.hexToRGB("#2AFFAE").getRGB(), 50, 0.0D, 0.0D, mc.getMainWindow().getScaledWidth(), mc.getMainWindow().getScaledHeight(), 500.0D);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\DoaPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */