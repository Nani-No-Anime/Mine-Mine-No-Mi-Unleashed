/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingDamageEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kage.DoppelmanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class KagePassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 28 */     if (!(event.getEntityLiving() instanceof LivingEntity)) {
/*    */       return;
/*    */     }
/* 31 */     LivingEntity entity = event.getEntityLiving();
/* 32 */     IEntityStats statsProps = EntityStatsCapability.get(entity);
/*    */     
/* 34 */     if (!statsProps.hasShadow() && entity.getBrightness() > 0.8F) {
/* 35 */       entity.setFire(2);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityHurt(LivingAttackEvent event) {
/* 41 */     if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 44 */     PlayerEntity attacker = (PlayerEntity)event.getSource().getTrueSource();
/* 45 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)attacker);
/* 46 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)attacker);
/* 47 */     LivingEntity attacked = event.getEntityLiving();
/*    */     
/* 49 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.KAGE_KAGE_NO_MI)) {
/*    */       return;
/*    */     }
/* 52 */     DoppelmanAbility ability = (DoppelmanAbility)abilityProps.getEquippedAbility((Ability)DoppelmanAbility.INSTANCE);
/* 53 */     boolean isActive = (ability != null && ability.isContinuous());
/*    */     
/* 55 */     if (!isActive) {
/*    */       return;
/*    */     }
/* 58 */     DoppelmanEntity doppelman = WyHelper.getEntitiesNear(attacker.getPosition(), attacker.world, 20.0D, DoppelmanEntity.class).stream().findFirst().orElse(null);
/*    */     
/* 60 */     if (doppelman != null && doppelman.getOwner() == attacker) {
/* 61 */       doppelman.forcedTargets.add(attacked);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityDamaged(LivingDamageEvent event) {
/* 67 */     if (!(event.getEntityLiving() instanceof DoppelmanEntity)) {
/*    */       return;
/*    */     }
/* 70 */     DoppelmanEntity entity = (DoppelmanEntity)event.getEntityLiving();
/* 71 */     PlayerEntity owner = entity.getOwner();
/*    */     
/* 73 */     if (owner == null) {
/*    */       return;
/*    */     }
/* 76 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)owner);
/*    */     
/* 78 */     if (!props.hasDevilFruit(ModAbilities.KAGE_KAGE_NO_MI) || entity.getHealth() - event.getAmount() >= 0.0F) {
/*    */       return;
/*    */     }
/* 81 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)owner);
/*    */     
/* 83 */     DoppelmanAbility ability = (DoppelmanAbility)abilityProps.getEquippedAbility((Ability)DoppelmanAbility.INSTANCE);
/* 84 */     boolean isActive = (ability != null && ability.isContinuous());
/*    */     
/* 86 */     if (!isActive) {
/*    */       return;
/*    */     }
/* 89 */     ability.setMaxCooldown(60.0D);
/* 90 */     ability.endContinuity(owner);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\KagePassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */