/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suke.SkattingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class SukePassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 23 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 26 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/* 27 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 28 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 30 */     if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("suke_suke")) {
/*    */       return;
/*    */     }
/* 33 */     SkattingAbility ability = (SkattingAbility)abilityProps.getEquippedAbility(SkattingAbility.INSTANCE);
/* 34 */     boolean isActive = (ability != null && ability.isContinuous());
/*    */     
/* 36 */     if (isActive)
/* 37 */       event.setCanceled(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\SukePassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */