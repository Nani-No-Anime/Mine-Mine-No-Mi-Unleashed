/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.mera.HeatDashParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ 
/*    */ public class HeatDashAbility extends Ability implements IMultiTargetAbility {
/* 24 */   public static final HeatDashAbility INSTANCE = new HeatDashAbility();
/*    */   
/* 26 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new HeatDashParticleEffect();
/*    */ 
/*    */   
/*    */   public HeatDashAbility() {
/* 30 */     super("Heat Dash", AbilityHelper.getDevilFruitCategory());
/* 31 */     setMaxCooldown(7.0D);
/* 32 */     setDescription("Transforms the user into fire and launches them forward");
/*    */     
/* 34 */     this.onUseEvent = this::onUseEvent;
/* 35 */     this.duringCooldownEvent = this::duringCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 40 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 41 */       return false;
/*    */     }
/* 43 */     clearTargets();
/*    */     
/* 45 */     player.world.playMovingSound(null, (Entity)player, ModSounds.MERA_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
/* 46 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D, 3.0D);
/* 47 */     player.setMotion(speed.x, 0.5D + speed.y, speed.z);
/*    */     
/* 49 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 56 */     if (canDealDamage()) {
/*    */       
/* 58 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D, 3.0D);
/* 59 */       player.setMotion(speed.x, speed.y, speed.z);
/* 60 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*    */       
/* 62 */       if (cooldownTimer % 2 == 0) {
/* 63 */         PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */       }
/* 65 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.4D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 66 */       list.remove(player);
/* 67 */       for (LivingEntity target : list) {
/*    */         
/* 69 */         if (isTarget(target) && player.canEntityBeSeen((Entity)target)) {
/*    */           
/* 71 */           SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, target, 2);
/* 72 */           if (!MinecraftForge.EVENT_BUS.post(event)) {

/* 73 */             target.setFire(2);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean canDealDamage() {
/* 81 */     return (this.cooldown > getMaxCooldown() * 0.9D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HeatDashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */