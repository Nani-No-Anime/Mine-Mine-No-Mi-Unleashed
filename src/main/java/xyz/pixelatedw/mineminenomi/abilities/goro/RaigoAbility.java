/*    */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.RaigoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.RaigoParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class RaigoAbility extends ChargeableAbility {
/* 16 */   public static final Ability INSTANCE = (Ability)new RaigoAbility();
/*    */   
/* 18 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new RaigoParticleEffect();
/*    */   
/*    */   private double posX;
/*    */   
/*    */   private double posY;
/*    */   private double posZ;
/*    */   
/*    */   public RaigoAbility() {
/* 26 */     super("Raigo", AbilityHelper.getDevilFruitCategory());
/* 27 */     setMaxCooldown(70.0D);
/* 28 */     setMaxChargeTime(5.0D);
/* 29 */     setDescription("Creates a huge cloud filled with electricity, which drops a massive lighting ball onto enemies");
/*    */     
/* 31 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 32 */     this.duringChargingEvent = this::duringChargingEvent;
/* 33 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 38 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
/*    */     
/* 40 */     double x = (mop.getHitVec()).x;
/* 41 */     double y = (mop.getHitVec()).y;
/* 42 */     double z = (mop.getHitVec()).z;
/*    */     
/* 44 */     this.posX = x;
/* 45 */     this.posY = y;
/* 46 */     this.posZ = z;
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 53 */     RaigoProjectile raigoProjectile = new RaigoProjectile(player.world, (LivingEntity)player);
/* 54 */     raigoProjectile.setLocationAndAngles(this.posX, this.posY + 90.0D, this.posZ, 0.0F, 0.0F);
/* 55 */     raigoProjectile.setMotion(0.0D, -2.0D, 0.0D);
/* 56 */     player.world.addEntity((Entity)raigoProjectile);
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/* 63 */     if (chargeTime % 10 == 0)
/* 64 */       PARTICLES.spawn(player.world, this.posX, this.posY + 40.0D, this.posZ, 0.0D, 0.0D, 0.0D); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\RaigoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */