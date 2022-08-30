/*    */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.PartyTableKickCourseParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.blackleg.PartyTableKickCourseAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class PartyTableKickCourseAbility extends ContinuousAbility implements IMultiTargetAbility, IAnimatedAbility {
/* 26 */   public static final PartyTableKickCourseAbility INSTANCE = new PartyTableKickCourseAbility();
/*    */   
/* 28 */   private static final PartyTableKickCourseParticleEffect PARTICLES = new PartyTableKickCourseParticleEffect();
/*    */ 
/*    */   
/*    */   public PartyTableKickCourseAbility() {
/* 32 */     super("Party-Table Kick Course", AbilityHelper.getStyleCategory());
/* 33 */     setMaxCooldown(12.0D);
/* 34 */     setThreshold(1.0D);
/* 35 */     setDescription("The user does a hand stand on the ground, legs spread out spinning and dealing damage to all nearby enemies");
/*    */     
/* 37 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 38 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 43 */     getAnimation().start();
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean duringContinuityEvent(PlayerEntity player, int time) {
/* 49 */     clearTargets();
/*    */     
/* 51 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*    */     
/* 53 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 2.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 54 */     list.remove(player);
/*    */     
/* 56 */     list.forEach(entity -> {
/*    */           if (isTarget(entity)) {
/*    */             boolean hit = entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 25.0F);
/*    */             
/*    */             if (hit) {
/*    */               Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.5D, 1.5D);
/*    */               
/*    */               entity.setMotion(speed.x, 1.5D, speed.z);
/*    */               
/*    */               entity.velocityChanged = true;
/*    */             } 
/*    */           } 
/*    */         });
/*    */     
/* 70 */     if (list.size() > 0) {
/* 71 */       ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     }
/* 73 */     DiableJambeAbility diableJambeAbility = (DiableJambeAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
/* 74 */     boolean isAbilityEnabled = (diableJambeAbility != null && diableJambeAbility.isContinuous());
/* 75 */     if (isAbilityEnabled) {
/* 76 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/* 78 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TimeAnimation getAnimation() {
/* 84 */     return (TimeAnimation)PartyTableKickCourseAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 90 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\PartyTableKickCourseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */