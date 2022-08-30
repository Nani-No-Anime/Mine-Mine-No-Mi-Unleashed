/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.netsu.NetsuEnhancementAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class NetsuPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityAttackEvent(LivingAttackEvent event) {
/* 24 */     if (event.getEntityLiving() == null) {
/*    */       return;
/*    */     }
/* 27 */     LivingEntity entity = event.getEntityLiving();
/* 28 */     IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
/* 29 */     DamageSource damageSource = event.getSource();
/*    */     
/* 31 */     if (devilFruitProps.hasDevilFruit(ModAbilities.NETSU_NETSU_NO_MI) && damageSource.getDamageType().equals(DamageSource.IN_FIRE.getDamageType())) {
/*    */       
/* 33 */       entity.extinguish();
/* 34 */       event.setCanceled(true);
/*    */     } 
/*    */     
/* 37 */     if (damageSource.getImmediateSource() instanceof LivingEntity) {
/*    */       
/* 39 */       LivingEntity netsuAttacker = (LivingEntity)damageSource.getImmediateSource();
/* 40 */       IAbilityData abilityProps = AbilityDataCapability.get(netsuAttacker);
/* 41 */       NetsuEnhancementAbility ability = (NetsuEnhancementAbility)abilityProps.getEquippedAbility((Ability)NetsuEnhancementAbility.INSTANCE);
/*    */       
/* 43 */       if (ability == null || !ability.isContinuous() || netsuAttacker.getHeldItemMainhand().isEmpty()) {
/*    */         return;
/*    */       }
/* 46 */       SetOnFireEvent e = new SetOnFireEvent((LivingEntity)damageSource.getImmediateSource(), entity, 6);
/* 47 */       if (!MinecraftForge.EVENT_BUS.post((Event)e))
/* 48 */         entity.setFire(6); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\NetsuPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */