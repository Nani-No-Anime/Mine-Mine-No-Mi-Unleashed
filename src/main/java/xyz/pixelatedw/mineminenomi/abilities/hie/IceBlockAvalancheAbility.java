/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockAvalancheProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.hie.IceBlockAvalancheParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class IceBlockAvalancheAbility extends ChargeableAbility {
/* 14 */   public static final IceBlockAvalancheAbility INSTANCE = new IceBlockAvalancheAbility();
/*    */   private IceBlockAvalancheProjectile proj;
/* 16 */   public static final IceBlockAvalancheParticleEffect PARTICLES = new IceBlockAvalancheParticleEffect();
/*    */ 
/*    */   
/*    */   public IceBlockAvalancheAbility() {
/* 20 */     super("Ice Block: Avalanche", AbilityHelper.getDevilFruitCategory());
/* 21 */     setDescription("Makes a big ice ball drop onto the spot the user is aiming at");
/* 22 */     setMaxCooldown(18.0D);
/* 23 */     setMaxChargeTime(5.0D);
/* 24 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 25 */     this.duringChargingEvent = this::duringChargingEvent;
/* 26 */     this.onEndChargingEvent = this::onEndChargingEvent;
/* 27 */     setCancelable();
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 32 */     RayTraceResult ray = WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D);
/* 33 */     removeDuplicate();
/* 34 */     this.proj = new IceBlockAvalancheProjectile(player.world, (LivingEntity)player);
/* 35 */     this.proj.setPosition(ray.getHitVec().getX(), ray.getHitVec().getY() + 20.0D, ray.getHitVec().getZ());
/* 36 */     this.proj.setMotion(0.0D, 0.0D, 0.0D);
/* 37 */     player.world.addEntity((Entity)this.proj);
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int ticks) {
/* 43 */     if (player != null && this.proj != null) {
/* 44 */       PARTICLES.spawn(player.world, this.proj.getPosX(), this.proj.getPosY(), this.proj.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 49 */     if (this.proj == null)
/* 50 */       return false; 
/* 51 */     this.proj.finalized = true;
/* 52 */     this.proj.setMotion(0.0D, -2.0D, 0.0D);
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void removeDuplicate() {
/* 58 */     if (this.proj != null)
/*    */     {
/* 60 */       if (this.proj.isAddedToWorld())
/*    */       {
/* 62 */         this.proj.remove();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceBlockAvalancheAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */