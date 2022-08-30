/*    */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*    */ import net.minecraftforge.eventbus.api.EventPriority;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class AbilitiesClientEvents
/*    */ {
/*    */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*    */   public static void onRenderOverlay(RenderGameOverlayEvent event) {
/* 27 */     Minecraft mc = Minecraft.getInstance();
/* 28 */     ClientPlayerEntity clientPlayerEntity = mc.player;
/* 29 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 31 */     if (abilityDataProps == null) {
/*    */       return;
/*    */     }
/* 34 */     int posX = mc.getMainWindow().getScaledWidth();
/* 35 */     int posY = mc.getMainWindow().getScaledHeight();
/*    */     
/* 37 */     if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE) {
/*    */       
/* 39 */       Optional<Ability> ability = Arrays.<Ability>stream(abilityDataProps.getEquippedAbilities(IOutOfBodyAbility.IS_ACTIVE)).findFirst();
/* 40 */       if (ability.isPresent()) {
/*    */         
/* 42 */         float distance = (float)(((IOutOfBodyAbility)ability.get()).getDistanceFromPivot((Entity)clientPlayerEntity) / ((IOutOfBodyAbility)ability.get()).getMaxRange());
/* 43 */         RendererHelper.renderVignette((Entity)clientPlayerEntity, distance, posX, posY);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilitiesClientEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */