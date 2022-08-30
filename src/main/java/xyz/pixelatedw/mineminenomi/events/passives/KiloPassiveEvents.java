/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class KiloPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 22 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 25 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 26 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 27 */     IDevilFruit devilProps = DevilFruitCapability.get((LivingEntity)player);
/*    */     
/* 29 */     if (!devilProps.getDevilFruit().equalsIgnoreCase("kilo_kilo")) {
/*    */       return;
/*    */     }
/* 32 */     Ability ability = abilityProps.getEquippedAbility((Ability)KiloPress1Ability.INSTANCE);
/* 33 */     boolean isActive = (ability != null && ability.isContinuous());
/* 34 */     boolean hasUmbrella = (player.getHeldItemMainhand().getItem() == ModWeapons.UMBRELLA || player.getHeldItemOffhand().getItem() == ModWeapons.UMBRELLA);
/* 35 */     boolean inAir = (!player.onGround && (player.getMotion()).y < 0.0D);
/*    */     
/* 37 */     if (isActive && hasUmbrella && inAir)
/* 38 */       player.setMotion((player.getMotion()).x, (player.getMotion()).y / 2.0D, (player.getMotion()).z); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\KiloPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */