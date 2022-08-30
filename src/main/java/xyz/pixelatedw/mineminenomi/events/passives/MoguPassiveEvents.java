/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguHeavyPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class MoguPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
/* 20 */     PlayerEntity player = event.getPlayer();
/* 21 */     IAbilityData AbilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 22 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 23 */     if (!props.getDevilFruit().equals("mogu_mogu")) {
/*    */       return;
/*    */     }
/* 26 */     MoguHeavyPointAbility ability = (MoguHeavyPointAbility)AbilityProps.getEquippedAbility((Ability)MoguHeavyPointAbility.INSTANCE);
/* 27 */     if (ability != null && ability.isContinuous())
/* 28 */       event.setNewSpeed(event.getOriginalSpeed() * 5.0F); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\MoguPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */