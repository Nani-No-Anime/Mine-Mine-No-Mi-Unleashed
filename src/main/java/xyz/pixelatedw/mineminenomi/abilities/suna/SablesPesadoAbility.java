/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.SablesPesadoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class SablesPesadoAbility extends ChargeableAbility {
/* 16 */   public static final Ability INSTANCE = (Ability)new SablesPesadoAbility();
/*    */   
/* 18 */   private static final SablesParticleEffect PARTICLES = new SablesParticleEffect();
/*    */ 
/*    */   
/*    */   public SablesPesadoAbility() {
/* 22 */     super("Sables: Pesado", AbilityHelper.getDevilFruitCategory());
/* 23 */     setMaxCooldown(25.0D);
/* 24 */     setMaxChargeTime(5.0D);
/* 25 */     setDescription("The user compresses a sandstorm to its limits and shoots it at extreme speeds");
/*    */     
/* 27 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 28 */     this.duringChargingEvent = this::duringChargingEvent;
/* 29 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 34 */     setChargeTime(SunaHelper.isFruitBoosted(player) ? (int)(getMaxChargeTime() * 0.5F) : getMaxChargeTime());
/* 35 */     return !player.isWet();
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int i) {
/* 40 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
/* 41 */     player.addPotionEffect(new EffectInstance(ModEffects.REDUCED_FALL, 2, 1, false, false));
/* 42 */     PARTICLES.mult = 1.0F - i / getMaxChargeTime() * 10.0F;
/* 43 */     if (i % 5 == 0) {
/* 44 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), (player.getMotion()).x, (player.getMotion()).y, (player.getMotion()).z);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 49 */     player.world.playMovingSound(null, (Entity)player, ModSounds.SABLES_PESADO_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
/* 50 */     SablesPesadoProjectile proj = new SablesPesadoProjectile(player.world, (LivingEntity)player);
/* 51 */     player.world.addEntity((Entity)proj);
/* 52 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.25F, 4.0F);
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SablesPesadoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */