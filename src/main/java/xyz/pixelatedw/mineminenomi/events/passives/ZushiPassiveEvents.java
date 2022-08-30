/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.zushi.AbareHimatsuriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.AbareHimatsuriModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbareHimatsuriRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class ZushiPassiveEvents
/*    */ {
/* 25 */   private static final AbareHimatsuriRenderer.Factory ABARE_HIMATSURI = new AbareHimatsuriRenderer.Factory(new AbareHimatsuriModel());
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 30 */     if (!(event.getEntity() instanceof net.minecraft.entity.player.PlayerEntity)) {
/*    */       return;
/*    */     }
/* 33 */     LivingEntity entity = event.getEntity();
/* 34 */     IDevilFruit props = DevilFruitCapability.get(entity);
/*    */     
/* 36 */     if (!props.hasDevilFruit(ModAbilities.ZUSHI_ZUSHI_NO_MI)) {
/*    */       return;
/*    */     }
/* 39 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*    */     
/* 41 */     Ability abareAbility = abilityProps.getEquippedAbility((Ability)AbareHimatsuriAbility.INSTANCE);
/* 42 */     boolean isAbareActive = (abareAbility != null && abareAbility.isContinuous());
/*    */     
/* 44 */     if (!isAbareActive) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 49 */     if (!(event.getEntity()).onGround)
/* 50 */       ABARE_HIMATSURI.createRenderFor(Minecraft.getInstance().getRenderManager()).render((Entity)entity, entity.rotationYaw, event.getPartialRenderTick(), event.getMatrixStack(), event.getBuffers(), event.getLight()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\ZushiPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */