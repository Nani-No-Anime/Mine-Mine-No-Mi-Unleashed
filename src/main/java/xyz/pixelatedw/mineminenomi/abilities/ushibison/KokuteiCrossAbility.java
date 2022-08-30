/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushibison;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ushibison.KokuteiCrossParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class KokuteiCrossAbility extends PunchAbility implements IFormRequiredAbility {
/* 19 */   public static final KokuteiCrossAbility INSTANCE = new KokuteiCrossAbility();
/*    */   
/* 21 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new KokuteiCrossParticleEffect();
/*    */ 
/*    */   
/*    */   public KokuteiCrossAbility() {
/* 25 */     super("Kokutei Cross", AbilityHelper.getDevilFruitCategory());
/* 26 */     setMaxCooldown(7.0D);
/* 27 */     setDescription("The transformed user crosses their hooves to hit the opponent");
/*    */     
/* 29 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 30 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 35 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.5D, 1.5D);
/* 36 */     target.setMotion(speed.x, player.getMotion().getY(), speed.z);
/* 37 */     if (target instanceof PlayerEntity) {
/* 38 */       ((ServerPlayerEntity)target).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)target));
/*    */     }
/* 40 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 45 */     return 20.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 51 */     return new ZoanInfo[] { (ZoanInfo)BisonHeavyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushibison\KokuteiCrossAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */