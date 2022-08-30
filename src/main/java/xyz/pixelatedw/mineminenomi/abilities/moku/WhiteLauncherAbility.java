/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.moku.WhiteLauncherParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class WhiteLauncherAbility extends Ability implements IMultiTargetAbility {
/* 22 */   public static final Ability INSTANCE = new WhiteLauncherAbility();
/*    */   
/* 24 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new WhiteLauncherParticleEffect();
/*    */ 
/*    */   
/*    */   public WhiteLauncherAbility() {
/* 28 */     super("White Launcher", AbilityHelper.getDevilFruitCategory());
/* 29 */     setMaxCooldown(7.0D);
/* 30 */     setDescription("Transforms the user into smoke and launches them forward");
/*    */     
/* 32 */     this.onUseEvent = this::onUseEvent;
/* 33 */     this.duringCooldownEvent = this::duringCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 38 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 39 */       return false;
/*    */     }
/* 41 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/* 42 */     clearTargets();
/*    */     
/* 44 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 5.5D, 5.5D);
/* 45 */     player.setMotion(speed.x, 2.0D, speed.z);
/*    */     
/* 47 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 53 */     if ((cooldownTimer / 20) > this.maxCooldown / 20.0D - 3.0D) {
/*    */       
/* 55 */       if (cooldownTimer % 2 == 0) {
/* 56 */         PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */       }
/* 58 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 59 */       list.remove(player);
/*    */       
/* 61 */       for (LivingEntity target : list) {
/*    */         
/* 63 */         if (isTarget(target) && player.canEntityBeSeen((Entity)target))
/* 64 */           target.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhiteLauncherAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */