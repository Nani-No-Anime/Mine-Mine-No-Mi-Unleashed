/*    */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.VoltVariProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.GenericUseLightningEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class VoltVariAbility extends ChargeableAbility {
/* 12 */   public static final Ability INSTANCE = (Ability)new VoltVariAbility();
/* 13 */   private static final GenericUseLightningEffect PARTICLES = new GenericUseLightningEffect();
/*    */   
/* 15 */   private int power = 0;
/*    */ 
/*    */   
/*    */   public VoltVariAbility() {
/* 19 */     super("Volt Vari", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("The user discharges variable amounts of electricity, in the form of a concentrated ball or a discharge");
/* 21 */     setMaxCooldown(10.0D);
/* 22 */     setMaxChargeTime(5.0D);
/* 23 */     setCancelable();
/*    */     
/* 25 */     this.duringChargingEvent = this::duringChargingEvent;
/* 26 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/* 31 */     this.power = chargeTimer;
/* 32 */     if (chargeTimer % 5 == 0) {
/* 33 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/* 35 */     AbilityHelper.slowEntityFall((LivingEntity)player);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 40 */     float truePower = Math.abs(this.power - getMaxChargeTime());
/*    */     
/* 42 */     VoltVariProjectile projectile = new VoltVariProjectile(player.world, (LivingEntity)player, truePower);
/* 43 */     projectile.setSize(truePower / 4.0F);
/* 44 */     player.world.addEntity((Entity)projectile);
/* 45 */     projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 1.0F);
/*    */     
/* 47 */     setMaxCooldown((truePower / 10.0F));
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\VoltVariAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */