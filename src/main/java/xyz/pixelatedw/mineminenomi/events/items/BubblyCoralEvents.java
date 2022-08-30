/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraftforge.event.entity.living.LivingDamageEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class BubblyCoralEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onBubblyCoralCheck(LivingEvent.LivingUpdateEvent event) {
/* 18 */     if (event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL))
/*    */     {
/* 20 */       event.getEntityLiving().setAir(event.getEntityLiving().getMaxAir());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void livingDamage(LivingDamageEvent event) {
/* 27 */     if (event.getEntityLiving().isPotionActive(ModEffects.BUBBLY_CORAL)) {
/*    */       
/* 29 */       float val = ((EffectInstance)Objects.<EffectInstance>requireNonNull(event.getEntityLiving().getActivePotionEffect(ModEffects.BUBBLY_CORAL))).getDuration() / 4500.0F;
/*    */       
/* 31 */       if (Math.random() > val)
/* 32 */         event.getEntityLiving().removePotionEffect(ModEffects.BUBBLY_CORAL); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\items\BubblyCoralEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */