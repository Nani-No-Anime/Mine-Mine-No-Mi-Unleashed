/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.RenderMorphEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class SpecialPotionEffectEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityPreRendered(RenderLivingEvent.Pre event) {
/* 27 */     LivingEntity entity = event.getEntity();
/* 28 */     checkForRotationBlockEffect(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityPostRendered(RenderLivingEvent.Post event) {
/* 34 */     LivingEntity entity = event.getEntity();
/* 35 */     checkForRotationBlockEffect(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityPreRendered(RenderMorphEvent.Pre event) {
/* 41 */     LivingEntity entity = event.getEntityLiving();
/* 42 */     checkForRotationBlockEffect(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityPostRendered(RenderMorphEvent.Post event) {
/* 48 */     LivingEntity entity = event.getEntityLiving();
/* 49 */     checkForRotationBlockEffect(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   private static void checkForRotationBlockEffect(LivingEntity entity) {
/* 54 */     for (EffectInstance instance : entity.getActivePotionEffects()) {
/*    */       
/* 56 */       if (instance.getPotion() instanceof ModEffect) {
/*    */         
/* 58 */         if (entity.getActivePotionEffect(instance.getPotion()).getDuration() <= 0) {
/*    */           
/* 60 */           entity.removePotionEffect(instance.getPotion());
/*    */           
/*    */           return;
/*    */         } 
/* 64 */         if (((ModEffect)instance.getPotion()).isBlockingRotations()) {
/*    */           
/* 66 */           entity.renderYawOffset = 0.0F;
/* 67 */           entity.prevRenderYawOffset = 0.0F;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onFirstPersonViewRendered(TickEvent.RenderTickEvent event) {
/* 76 */     Minecraft mc = Minecraft.getInstance();
/* 77 */     ClientPlayerEntity clientPlayerEntity = mc.player;
/*    */     
/* 79 */     if (clientPlayerEntity == null) {
/*    */       return;
/*    */     }
/* 82 */     if (mc.gameSettings.thirdPersonView != 0) {
/*    */       return;
/*    */     }
/* 85 */     Iterator<EffectInstance> iterator = clientPlayerEntity.getActivePotionEffects().iterator();
/* 86 */     while (iterator.hasNext()) {
/*    */       
/* 88 */       EffectInstance instance = iterator.next();
/*    */       
/* 90 */       if (instance.getPotion() instanceof OverlayEffect) {
/*    */         
/* 92 */         OverlayEffect effect = (OverlayEffect)instance.getPotion();
/* 93 */         if (effect.getResourceLocation(instance.getDuration()) == null && effect.getOverlayColor() != null) {
/*    */           
/* 95 */           float[] colors = ((OverlayEffect)instance.getPotion()).getOverlayColor();
/* 96 */           Color color = new Color(colors[0], colors[1], colors[2]);
/* 97 */           WyHelper.drawColourOnScreen(color.getRGB(), (int)(colors[3] * 255.0F), 0.0D, 0.0D, mc.getMainWindow().getScaledWidth(), mc.getMainWindow().getScaledHeight(), 500.0D);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\SpecialPotionEffectEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */