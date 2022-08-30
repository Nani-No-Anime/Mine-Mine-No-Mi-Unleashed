/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.SablesProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class SablesAbility extends ChargeableAbility {
/* 13 */   public static final Ability INSTANCE = (Ability)new SablesAbility();
/*    */   
/* 15 */   private SablesProjectile proj = null;
/* 16 */   private float maxMultiplier = 0.0F;
/*    */ 
/*    */   
/*    */   public SablesAbility() {
/* 20 */     super("Sables", AbilityHelper.getDevilFruitCategory());
/* 21 */     setMaxCooldown(18.0D);
/* 22 */     setMaxChargeTime(6.0D);
/* 23 */     setDescription("The user launches a compressed sandstorm at the opponent, which sends them flying");
/*    */     
/* 25 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 26 */     this.duringChargingEvent = this::duringContinuity;
/* 27 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 31 */     this.proj = null;
/* 32 */     this.maxMultiplier = (SunaHelper.isFruitBoosted(player) ? 2 : 1);
/* 33 */     return !player.isWet();
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 38 */     if (this.proj.isAlive())
/* 39 */       this.proj.shoot((Entity)player, 0.0F, 0.0F, 0.0F, 2.0F, 0.0F); 
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int i) {
/* 45 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 100.0D);
/*    */     
/* 47 */     if (this.proj == null) {
/*    */       
/* 49 */       this.proj = new SablesProjectile(player.world, (LivingEntity)player);
/* 50 */       this.proj.setPosition(player.getPosX(), player.getPosY() + 10.0D, player.getPosZ());
/* 51 */       player.world.addEntity((Entity)this.proj);
/*    */     } 
/*    */     
/* 54 */     if (!this.proj.isAlive() || this.proj == null) {
/*    */       
/* 56 */       this.proj = null;
/* 57 */       endCharging(player);
/*    */     } 
/*    */     
/* 60 */     float time = getChargeTime() / getMaxChargeTime();
/* 61 */     float multiplier = 1.0F - time;
/* 62 */     this.proj.mult = this.maxMultiplier * multiplier;
/* 63 */     double distance = Math.sqrt(this.proj.getDistanceSq(mop.getHitVec()));
/* 64 */     if (mop.getType().equals(RayTraceResult.Type.BLOCK) && distance < 100.0D)
/* 65 */       this.proj.vector = mop.getHitVec().add(0.0D, 10.0D, 0.0D); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SablesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */