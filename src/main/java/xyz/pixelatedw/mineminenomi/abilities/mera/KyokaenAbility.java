/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.mera.KyokaenParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ 
/*    */ public class KyokaenAbility
/*    */   extends ContinuousAbility
/*    */ {
/* 24 */   public static final KyokaenAbility INSTANCE = new KyokaenAbility();
/* 25 */   private static final KyokaenParticleEffect PARTICLES = new KyokaenParticleEffect();
/*    */ 
/*    */   
/*    */   public KyokaenAbility() {
/* 29 */     super("Kyokaen", AbilityHelper.getDevilFruitCategory());
/* 30 */     setDescription("Creates a wall of fire protecting the user");
/* 31 */     setThreshold(5.0D);
/*    */     
/* 33 */     this.duringContinuityEvent = this::duringContinuity;
/* 34 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 38 */     int range = 2;
/* 39 */     double boxSize = 1.1D;
/* 40 */     for (int i = 0; i < range * 2; i++) {
/* 41 */       double distance = i / 2.0D;
/* 42 */       Vec3d lookVec = player.getLookVec();
/* 43 */       Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/* 44 */       List<Entity> list = player.world.getEntitiesInAABBexcluding((Entity)player, new AxisAlignedBB(pos.x - boxSize, pos.y - boxSize, pos.z - boxSize, pos.x + boxSize, pos.y + boxSize * 2.0D, pos.z + boxSize), entity -> (entity != player));
/*    */       
/* 46 */       for (Entity e : list) {
/* 47 */         if (e instanceof LivingEntity) {
/* 48 */           if (!e.isBurning()) {
/* 49 */             SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, (LivingEntity)e, 3);
/* 50 */             if (!MinecraftForge.EVENT_BUS.post(event))
/* 51 */               e.setFire(3); 
/* 52 */             e.attackEntityFrom((DamageSource)ModDamageSource.FIRE.getSource(), 3.0F);
/*    */           } 
/* 54 */           Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 55 */           e.setMotion(speed.x, 0.5D, speed.z);
/* 56 */           e.velocityChanged = true; continue;
/*    */         } 
/* 58 */         if (e instanceof net.minecraft.entity.projectile.AbstractArrowEntity || e instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile || e instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile) {
/* 59 */           e.remove(); continue;
/* 60 */         }  if (e instanceof net.minecraft.entity.projectile.ThrowableEntity) {
/* 61 */           e.setMotion(-(e.getMotion()).x * 1.350000023841858D, (e.getMotion()).y, -(e.getMotion()).x * 1.350000023841858D);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 66 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, range);
/* 67 */     if (passiveTimer % 2 == 0) {
/* 68 */       PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 73 */     int cooldown = 2 + (int)Math.round(this.continueTime / 20.0D);
/* 74 */     setMaxCooldown(cooldown);
/* 75 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\KyokaenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */