/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.EntityMountEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CSyncZoanPacket;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MorphEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/*  51 */       if (!(event.getEntity() instanceof AbstractClientPlayerEntity)) {
/*     */         return;
/*     */       }
/*  54 */       AbstractClientPlayerEntity entity = (AbstractClientPlayerEntity)event.getEntity();
/*  55 */       IDevilFruit props = DevilFruitCapability.get((LivingEntity)entity);
/*     */       
/*  57 */       if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
/*     */         
/*  59 */         if ((event.getEntity()).hurtTime > 0);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  64 */         ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)entity);
/*  65 */         if (info != null) {
/*     */           
/*  67 */           event.setCanceled(true);
/*  68 */           ZoanMorphRenderer render = (ZoanMorphRenderer)info.getRendererFactory(entity).createRenderFor(Minecraft.getInstance().getRenderManager());
/*  69 */           render.render(entity, entity.rotationYaw, event.getPartialRenderTick(), event.getMatrixStack(), event.getBuffers(), event.getLight());
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityConstructing(EntityJoinWorldEvent event) {
/*  77 */       if (event.getEntity() instanceof PlayerEntity) {
/*     */         
/*  79 */         ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*  80 */         PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  87 */         if (player.world.isRemote && clientPlayerEntity != player)
/*     */         {
/*  89 */           WyNetwork.sendToServer(new CSyncZoanPacket(player.getEntityId()));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onHandRendering(RenderHandEvent event) {
/*  97 */       if (event.getHand() != Hand.MAIN_HAND) {
/*     */         return;
/*     */       }
/* 100 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 101 */       if (clientPlayerEntity == null) {
/*     */         return;
/*     */       }
/* 104 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/* 106 */       boolean renderHand = false;
/* 107 */       boolean hasEmptyHand = clientPlayerEntity.getHeldItemMainhand().isEmpty();
/*     */       
/* 109 */       ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)clientPlayerEntity);
/* 110 */       AbilityOverlay overlay = AbilityHelper.getCurrentOverlay((PlayerEntity)clientPlayerEntity);
/* 111 */       if (info != null) {
/* 112 */         renderHand = true;
/*     */       }
/* 114 */       boolean isZoan = (hasEmptyHand && renderHand);
/* 115 */       boolean isBlackLeg = (props.isBlackLeg() && hasEmptyHand);
/* 116 */       boolean isOverlay = (hasEmptyHand && overlay != null);
/*     */       
/* 118 */       if (isZoan || isOverlay || isBlackLeg) {
/*     */         
/* 120 */         event.setCanceled(true);
/* 121 */         MorphHelper.renderLimbFirstPerson(event.getMatrixStack(), event.getBuffers(), event.getLight(), event.getEquipProgress(), event.getSwingProgress(), clientPlayerEntity.getPrimaryHand(), overlay, info);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class Common
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onZoanSizeChange(EntityEvent.EyeHeight event) {
/* 132 */       if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/* 135 */       PlayerEntity player = (PlayerEntity)event.getEntity();
/* 136 */       IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */       
/* 138 */       Pose pose = player.getPose();
/* 139 */       float eyeHeight = (player.getSize(pose)).height * 0.9F;
/*     */       
/* 141 */       ZoanInfo info = null;
/*     */       
/* 143 */       if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
/*     */         
/* 145 */         info = MorphHelper.getZoanInfo((LivingEntity)player);
/*     */         
/* 147 */         if (info == null) {
/*     */           return;
/*     */         }
/* 150 */         if (info.getEyeHeight() <= 0.0D) {
/*     */           return;
/*     */         }
/* 153 */         if (info != null) {
/*     */           
/* 155 */           eyeHeight = (float)(1.62D * info.getEyeHeight() / 1.75D);
/* 156 */           if (eyeHeight < 0.22F) {
/* 157 */             eyeHeight = 0.22F;
/*     */           }
/*     */         } 
/*     */       } 
/* 161 */       if (info != null && player.isSneaking()) {
/*     */         
/* 163 */         eyeHeight -= 0.3F;
/* 164 */         eyeHeight = (float)Math.max(0.3D, eyeHeight);
/*     */       } 
/*     */       
/* 167 */       event.setNewHeight(eyeHeight);
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onZoanMounts(EntityMountEvent event) {
/* 173 */       if (event.getEntityBeingMounted() == null) {
/*     */         return;
/*     */       }
/* 176 */       if (event.getEntityMounting() instanceof PlayerEntity) {
/*     */         
/* 178 */         PlayerEntity player = (PlayerEntity)event.getEntityMounting();
/* 179 */         ZoanInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
/*     */         
/* 181 */         if (info != null && !info.canMount()) {
/*     */           
/* 183 */           player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_MOUNT_ZOANN, new Object[0]));
/* 184 */           event.setCanceled(true);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\MorphEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */