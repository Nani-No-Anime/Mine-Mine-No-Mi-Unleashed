/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class HumanPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 21 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 24 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 25 */     IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
/* 26 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 28 */     if (!statsProps.isHuman()) {
/*    */       return;
/*    */     }
/* 31 */     Ability tekkaiAbility = abilityProps.getEquippedAbility(TekkaiAbility.INSTANCE);
/* 32 */     boolean isTekkaiActive = (tekkaiAbility != null && tekkaiAbility.isContinuous());
/* 33 */     if (isTekkaiActive)
/* 34 */       player.setMotion(0.0D, -5.0D, 0.0D); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\HumanPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */