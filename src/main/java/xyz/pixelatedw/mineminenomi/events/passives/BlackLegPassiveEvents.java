/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class BlackLegPassiveEvents
/*    */ {
/*    */   @EventBusSubscriber(modid = "mineminenomi")
/*    */   public static class Common
/*    */   {
/*    */     @SubscribeEvent
/*    */     public static void onHit(AttackEntityEvent event) {
/* 20 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)event.getPlayer());
/*    */       
/* 22 */       Ability abl = abilityProps.getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
/*    */       
/* 24 */       if (event.getPlayer().getHeldItemMainhand().isEmpty() && abl != null && abl.isContinuous())
/* 25 */         event.getTarget().setFire(3); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\BlackLegPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */