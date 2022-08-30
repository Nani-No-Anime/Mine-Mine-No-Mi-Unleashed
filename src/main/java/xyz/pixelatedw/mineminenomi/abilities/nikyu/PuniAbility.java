/*    */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class PuniAbility
/*    */   extends ContinuousAbility
/*    */   implements IAnimatedAbility {
/* 23 */   public static final PuniAbility INSTANCE = new PuniAbility();
/*    */ 
/*    */   
/*    */   public PuniAbility() {
/* 27 */     super("Puni", AbilityHelper.getDevilFruitCategory());
/* 28 */     setDescription("The user takes a defensive posture that uses their paw pads to repel and counter enemy attacks");
/* 29 */     setThreshold(2.0D);
/* 30 */     setMaxCooldown(4.0D);
/*    */     
/* 32 */     this.onStartContinuityEvent = this::onStartContiunityEvent;
/* 33 */     this.duringContinuityEvent = this::duringContinuity;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContiunityEvent(PlayerEntity player) {
/* 38 */     return player.getHeldItemMainhand().isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 43 */     if (!player.getHeldItemMainhand().isEmpty()) {
/* 44 */       endContinuity(player);
/*    */     }
/* 46 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
/*    */     
/* 48 */     int range = 3;
/* 49 */     double boxSize = 0.85D;
/* 50 */     for (int i = 0; i < range * 2; i++) {
/*    */       
/* 52 */       double distance = i / 2.0D;
/* 53 */       Vec3d lookVec = player.getLookVec();
/* 54 */       Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/* 55 */       List<Entity> list = player.world.getEntitiesInAABBexcluding((Entity)player, new AxisAlignedBB(pos.x - boxSize, pos.y - boxSize, pos.z - boxSize, pos.x + boxSize, pos.y + boxSize * 2.0D, pos.z + boxSize), entity -> (entity != player));
/*    */       
/* 57 */       for (Entity e : list) {
/*    */         
/* 59 */         if (e instanceof LivingEntity) {
/*    */           
/* 61 */           e.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
/* 62 */           Vec3d speed = WyHelper.propulsion((LivingEntity)player, 6.0D, 6.0D);
/* 63 */           e.setMotion(speed.x, 1.5D, speed.z);
/* 64 */           e.velocityChanged = true; continue;
/* 65 */         }  if (e instanceof net.minecraft.entity.projectile.ThrowableEntity) {
/* 66 */           e.setMotion(-(e.getMotion()).x * 3.0D, (e.getMotion()).y + 0.5D, -(e.getMotion()).x * 3.0D);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 74 */     return (IAnimation)PointArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 80 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\PuniAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */