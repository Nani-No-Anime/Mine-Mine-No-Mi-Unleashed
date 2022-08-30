/*    */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierApplyEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.AttributeModifierRemoveEvent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateAbilityTimeProgressionPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class AbilityTimeScaleEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onModifierAdded(AttributeModifierApplyEvent event) {
/* 23 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 26 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 27 */     IAttributeInstance attr = event.getAttribute(ModAttributes.TIME_PROGRESSION);
/*    */     
/* 29 */     if (attr != null) {
/* 30 */       changeCooldownTimeScales(player, attr);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onModifierRemoved(AttributeModifierRemoveEvent event) {
/* 36 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 39 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 40 */     IAttributeInstance attr = event.getAttribute(ModAttributes.TIME_PROGRESSION);
/*    */     
/* 42 */     if (attr != null) {
/* 43 */       changeCooldownTimeScales(player, attr);
/*    */     }
/*    */   }
/*    */   
/*    */   private static void changeCooldownTimeScales(PlayerEntity player, IAttributeInstance instance) {
/* 48 */     double timeScale = instance.getValue();
/* 49 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 51 */     for (Ability abl : abilityProps.getEquippedAbilities()) {
/*    */       
/* 53 */       if (abl != null) {
/*    */         
/* 55 */         abl.setTimeProgression(timeScale);
/* 56 */         WyNetwork.sendToAllTrackingAndSelf(new SUpdateAbilityTimeProgressionPacket(player, abl), (LivingEntity)player);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilityTimeScaleEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */