/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingDamageEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ito.BlackKnightAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class ItoPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityAttack(LivingHurtEvent event) {
/* 25 */     if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 28 */     PlayerEntity attacker = (PlayerEntity)event.getSource().getTrueSource();
/* 29 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)attacker);
/* 30 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)attacker);
/* 31 */     LivingEntity attacked = event.getEntityLiving();
/*    */     
/* 33 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.ITO_ITO_NO_MI)) {
/*    */       return;
/*    */     }
/* 36 */     BlackKnightAbility ability = (BlackKnightAbility)abilityProps.getEquippedAbility((Ability)BlackKnightAbility.INSTANCE);
/* 37 */     boolean isActive = (ability != null && ability.isContinuous());
/*    */     
/* 39 */     if (!isActive) {
/*    */       return;
/*    */     }
/* 42 */     BlackKnightEntity knight = WyHelper.getEntitiesNear(attacker.getPosition(), attacker.world, 20.0D,  BlackKnightEntity.class ).stream().findFirst().orElse(null);
/*    */     
/* 44 */     if (knight != null && knight.getOwner() == attacker) {
/* 45 */       knight.forcedTargets.add(attacked);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityDamaged(LivingDamageEvent event) {
/* 51 */     if (!(event.getEntityLiving() instanceof BlackKnightEntity)) {
/*    */       return;
/*    */     }
/* 54 */     BlackKnightEntity entity = (BlackKnightEntity)event.getEntityLiving();
/* 55 */     PlayerEntity owner = entity.getOwner();
/*    */     
/* 57 */     if (owner == null) {
/*    */       return;
/*    */     }
/* 60 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)owner);
/*    */     
/* 62 */     if (!props.hasDevilFruit(ModAbilities.ITO_ITO_NO_MI) || entity.getHealth() - event.getAmount() >= 0.0F) {
/*    */       return;
/*    */     }
/* 65 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)owner);
/*    */     
/* 67 */     BlackKnightAbility ability = (BlackKnightAbility)abilityProps.getEquippedAbility((Ability)BlackKnightAbility.INSTANCE);
/* 68 */     boolean isActive = (ability != null && ability.isContinuous());
/*    */     
/* 70 */     if (!isActive) {
/*    */       return;
/*    */     }
/* 73 */     ability.setMaxCooldown(60.0D);
/* 74 */     ability.endContinuity(owner);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\ItoPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */