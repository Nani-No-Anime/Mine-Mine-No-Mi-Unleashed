/*    */ package xyz.pixelatedw.mineminenomi.abilities.oto;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.oto.BonAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class BonAbility extends Ability implements IAnimatedAbility {
/* 25 */   public static final Ability INSTANCE = new BonAbility();
/*    */   
/*    */   private static final float VOLUME = 1.5F;
/*    */   
/*    */   private static final double DISTANCE = 22.5D;
/*    */   
/*    */   public BonAbility() {
/* 32 */     super("Bon", AbilityHelper.getDevilFruitCategory());
/* 33 */     setMaxCooldown(3.0D);
/* 34 */     addInPool(new AbilityPool[] { AbilityPool.OTO_ABILITY });
/* 35 */     setDescription("The user plucks a string created from their arm, creating a sound wave that internally damages all who hear it");
/*    */     
/* 37 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 42 */     player.world.playSound(null, player.getPosition(), ModSounds.BON, SoundCategory.PLAYERS, 1.5F, 0.2F + player.getRNG().nextFloat());
/*    */     
/* 44 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 22.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 45 */     targets.remove(player);
/*    */     
/* 47 */     getAnimation().start();
/*    */     
/* 49 */     for (LivingEntity target : targets) {
/*    */       
/* 51 */       AbilityDamageSource ds = ModDamageSource.causeAbilityDamage((LivingEntity)player, this);
/* 52 */       ds.setInternalDamage();
/* 53 */       ds.markDamageAsSlash();
/* 54 */       target.attackEntityFrom((DamageSource)ds, 15.0F);
/*    */       
/* 56 */       Vec3d dist = target.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D).normalize();
/* 57 */       double power = 4.5D;
/* 58 */       double xSpeed = -dist.x * power;
/* 59 */       double zSpeed = -dist.z * power;
/* 60 */       target.setMotion(-xSpeed, 0.10000000149011612D, -zSpeed);
/* 61 */       target.velocityChanged = true;
/*    */       
/* 63 */       for (int i = 0; i < 5; i++) {
/*    */         
/* 65 */         double offsetX = target.getRNG().nextDouble() / 2.0D;
/* 66 */         double offsetZ = target.getRNG().nextDouble() / 2.0D;
/* 67 */         WyHelper.spawnParticles(ParticleTypes.SWEEP_ATTACK, (ServerWorld)player.world, target.getPosX() + offsetX, target.getPosY() + target.getEyeHeight() + offsetX, target.getPosZ() + offsetZ);
/*    */       } 
/* 69 */       WyHelper.spawnParticles(ParticleTypes.EXPLOSION, (ServerWorld)player.world, target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ());
/*    */     } 
/*    */     
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TimeAnimation getAnimation() {
/* 78 */     return (TimeAnimation)BonAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 84 */     return (isOnCooldown() && !isStateForced() && getCooldown() > WyHelper.percentage(70.0D, getMaxCooldown()));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\oto\BonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */