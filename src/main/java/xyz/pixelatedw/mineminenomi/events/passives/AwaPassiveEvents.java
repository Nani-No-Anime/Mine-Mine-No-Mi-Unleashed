/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.awa.SoapDefenseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SoapDefenseModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class AwaPassiveEvents
/*    */ {
/*    */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */   public static class Client
/*    */   {
/* 27 */     private static final SoapDefenseModel SOAP = new SoapDefenseModel();
/*    */ 
/*    */     
/*    */     @SubscribeEvent
/*    */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 32 */       if (!(event.getEntity() instanceof PlayerEntity)) {
/*    */         return;
/*    */       }
/* 35 */       PlayerEntity player = (PlayerEntity)event.getEntity();
/* 36 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */       
/* 38 */       Ability ability = abilityProps.getEquippedAbility(SoapDefenseAbility.INSTANCE);
/* 39 */       if (ability == null || !ability.isContinuous()) {
/*    */         return;
/*    */       }
/* 42 */       event.setCanceled(true);
/*    */       
/* 44 */       event.getMatrixStack().push();
/*    */       
/* 46 */       event.getMatrixStack().translate(0.0D, player.getEyeHeight() - 1.2D, 0.0D);
/*    */       
/* 48 */       event.getMatrixStack().scale(1.2F, 1.2F, 1.2F);
/*    */       
/* 50 */       SOAP.render(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityBody(ModResources.AWA_SOAP)), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */       
/* 52 */       RenderSystem.enableLighting();
/* 53 */       event.getMatrixStack().pop();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\AwaPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */