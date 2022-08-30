/*    */ package xyz.pixelatedw.mineminenomi.abilities.bane;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class SpringSnipeAbility extends ChargeableAbility implements IMultiTargetAbility {
/* 21 */   public static final Ability INSTANCE = (Ability)new SpringSnipeAbility();
/*    */   
/*    */   private Vec3d look;
/*    */ 
/*    */   
/*    */   public SpringSnipeAbility() {
/* 27 */     super("Spring Snipe", AbilityHelper.getDevilFruitCategory());
/* 28 */     setMaxCooldown(14.5D);
/* 29 */     setMaxChargeTime(0.5D);
/* 30 */     setDescription("Turning the user's forelegs into springs, they can launch themselves directly at the opponent");
/*    */     
/* 32 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 33 */     this.onEndChargingEvent = this::onEndChargingEvent;
/* 34 */     this.duringCooldownEvent = this::duringCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 39 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 40 */     SpringHopperAbility ability = (SpringHopperAbility)props.getEquippedAbility(SpringHopperAbility.INSTANCE);
/*    */     
/* 42 */     if (ability == null || ability.isOnCooldown()) {
/* 43 */       return false;
/*    */     }
/* 45 */     if (!ability.isContinuous())
/*    */     {
/* 47 */       ability.startContinuity(player);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 52 */     ability.jumpPower = Math.min(ability.jumpPower + 3, 9);
/*    */     
/* 54 */     clearTargets();
/* 55 */     return AbilityHelper.canUseMomentumAbility(player);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 60 */     this.look = player.getLook(1.0F);
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 66 */     if (canDealDamage()) {
/*    */       
/* 68 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 69 */       SpringHopperAbility ability = (SpringHopperAbility)props.getEquippedAbility(SpringHopperAbility.INSTANCE);
/*    */       
/* 71 */       Vec3d speed = this.look.mul(3.0D, 3.0D, 3.0D);
/* 72 */       player.setMotion(speed.x, speed.y, speed.z);
/* 73 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/* 74 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 2.0D);
/* 75 */       list.remove(player);
/* 76 */       list.forEach(entity -> {
/*    */             if (isTarget(entity) && player.canEntityBeSeen((Entity)entity)) {
/*    */               entity.attackEntityFrom(DamageSource.causePlayerDamage(player), (ability.jumpPower * 6));
/*    */             }
/*    */           });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDealDamage() {
/* 86 */     return (this.cooldown > getMaxCooldown() * 0.9D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bane\SpringSnipeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */