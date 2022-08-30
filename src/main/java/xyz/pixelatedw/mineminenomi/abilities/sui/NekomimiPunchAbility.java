/*    */ package xyz.pixelatedw.mineminenomi.abilities.sui;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class NekomimiPunchAbility extends Ability {
/* 20 */   public static final NekomimiPunchAbility INSTANCE = new NekomimiPunchAbility();
/*    */   
/*    */   public NekomimiPunchAbility() {
/* 23 */     super("Nekomimi Punch", AbilityHelper.getDevilFruitCategory());
/* 24 */     setMaxCooldown(6.0D);
/* 25 */     setDescription("Propels the swimming user forward and deals huge damage to all entities they hit");
/*    */     
/* 27 */     this.onUseEvent = this::onUseEvent;
/* 28 */     this.duringCooldownEvent = this::duringCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 33 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 34 */       return false;
/*    */     }
/* 36 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 38 */     if (((FreeSwimmingAbility)props.getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE)).isSwimming) {
/*    */       
/* 40 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 1.5D, 3.0D);
/* 41 */       player.setMotion(speed.x, 0.5D + speed.y, speed.z);
/*    */       
/* 43 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/* 44 */       return true;
/*    */     } 
/*    */     
/* 47 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 53 */     if ((cooldownTimer / 20) > this.maxCooldown / 20.0D - 3.0D) {
/*    */       
/* 55 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.4D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 56 */       list.remove(player);
/* 57 */       for (LivingEntity target : list)
/*    */       {
/* 59 */         target.attackEntityFrom(DamageSource.causePlayerDamage(player), 20.0F);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sui\NekomimiPunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */