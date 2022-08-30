/*    */ package xyz.pixelatedw.mineminenomi.abilities.horu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.horu.WinkExplosionEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.horu.WinkParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class DeathWinkAbility extends Ability {
/* 23 */   public static final DeathWinkAbility INSTANCE = new DeathWinkAbility();
/*    */   
/* 25 */   private static final ParticleEffect WINK = (ParticleEffect)new WinkParticleEffect();
/*    */ 
/*    */   
/*    */   public DeathWinkAbility() {
/* 29 */     super("Death Wink", AbilityHelper.getDevilFruitCategory());
/* 30 */     setMaxCooldown(6.0D);
/* 31 */     setDescription("The user winks really hard creating a shockwave; Ganmen Seicho boosts it's power");
/*    */     
/* 33 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 38 */     player.world.playSound(null, player.getPosition(), ModSounds.DEATH_WINK_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 39 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.0D);
/* 40 */     WINK.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 42 */     int power = player.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE) ? 3 : 2;
/* 43 */     boolean createExplosion = true;
/*    */     
/* 45 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, (8 * power));
/*    */     
/* 47 */     double x = (mop.getHitVec()).x;
/* 48 */     double y = (mop.getHitVec()).y;
/* 49 */     double z = (mop.getHitVec()).z;
/*    */     
/* 51 */     if (mop instanceof EntityRayTraceResult) {
/* 52 */       Entity e = ((EntityRayTraceResult)mop).getEntity();
/* 53 */       if (e instanceof AbilityProjectileEntity && (
/* 54 */         (AbilityProjectileEntity)e).getDamage() < (power * 5)) {
/* 55 */         createExplosion = false;
/* 56 */         e.setMotion(-(e.getMotion()).x, (e.getMotion()).y, -(e.getMotion()).x);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 61 */     if (createExplosion) {
/*    */       
/* 63 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, x, y, z, (1 + power));
/* 64 */       explosion.setStaticDamage((power * 10));
/* 65 */       explosion.setExplosionSound(false);
/* 66 */       explosion.setSmokeParticles((ParticleEffect)new WinkExplosionEffect(2));
/* 67 */       explosion.doExplosion();
/*    */       
/* 69 */       double distance = Math.sqrt(player.getDistanceSq(x, y, z));
/* 70 */       if (distance < 0.5D) {
/*    */         
/* 72 */         player.setMotion(WyHelper.randomWithRange(-1, 1) * 0.5D * power, 1.5D * power, WyHelper.randomWithRange(-1, 1) * 0.5D * power);
/* 73 */         ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*    */       } 
/*    */     } 
/*    */     
/* 77 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\horu\DeathWinkAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */