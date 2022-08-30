/*    */ package xyz.pixelatedw.mineminenomi.abilities.beta;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.beta.HanamizuShinkenShirahadoriEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class HanamizuShinkenAbility
/*    */   extends ContinuousAbility
/*    */ {
/* 24 */   public static final HanamizuShinkenAbility INSTANCE = new HanamizuShinkenAbility();
/*    */ 
/*    */   
/* 27 */   private ParticleEffect particles = (ParticleEffect)new HanamizuShinkenShirahadoriEffect();
/*    */   public HanamizuShinkenAbility() {
/* 29 */     super("Hanamizu Shinken Shirahadori", AbilityHelper.getDevilFruitCategory());
/* 30 */     setThreshold(3.0D);
/* 31 */     setDescription("Creates a protecting wall of mucus, protecting the user from attacks");
/*    */     
/* 33 */     this.duringContinuityEvent = this::duringContinuity;
/* 34 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 38 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 0, false, false));
/*    */     
/* 40 */     int range = 2;
/* 41 */     double boxSize = 1.2D;
/* 42 */     for (int i = 0; i < range * 2; i++) {
/* 43 */       double distance = i / 2.0D;
/* 44 */       Vec3d lookVec = player.getLookVec();
/* 45 */       Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/* 46 */       List<Entity> list = player.world.getEntitiesInAABBexcluding((Entity)player, new AxisAlignedBB(pos.x - boxSize, pos.y - boxSize, pos.z - boxSize, pos.x + boxSize, pos.y + boxSize * 2.0D, pos.z + boxSize), entity -> (entity != player));
/*    */       
/* 48 */       for (Entity e : list) {
/* 49 */         if (e instanceof LivingEntity) {
/* 50 */           e.attackEntityFrom(DamageSource.causePlayerDamage(player), 4.0F);
/* 51 */           ((LivingEntity)e).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 4));
/* 52 */           Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 53 */           e.setMotion(speed.x, 0.5D, speed.z);
/* 54 */           e.velocityChanged = true; continue;
/* 55 */         }  if (e instanceof net.minecraft.entity.projectile.ThrowableEntity || e instanceof net.minecraft.entity.projectile.AbstractArrowEntity) {
/* 56 */           e.setMotion(-(e.getMotion()).x, (e.getMotion()).y, -(e.getMotion()).x);
/*    */         }
/*    */       } 
/*    */     } 
/* 60 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, range);
/* 61 */     if (passiveTimer % 2 == 0)
/* 62 */       this.particles.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D); 
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 66 */     int cooldown = 2 + (int)Math.round(this.continueTime / 20.0D);
/* 67 */     setMaxCooldown(cooldown);
/* 68 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\beta\HanamizuShinkenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */