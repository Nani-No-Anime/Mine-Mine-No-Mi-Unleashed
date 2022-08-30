/*     */ package xyz.pixelatedw.mineminenomi.abilities.buki;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.FreshFireProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class BukimorphoseAbility extends PunchTriggerAbility {
/*  25 */   public static final BukimorphoseAbility INSTANCE = new BukimorphoseAbility();
/*  26 */   private Mode mode = Mode.SWORD;
/*     */   boolean released = false;
/*     */   boolean exploded;
/*     */   
/*     */   public BukimorphoseAbility() {
/*  31 */     super("Bukimorphose", AbilityHelper.getDevilFruitCategory());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     this.exploded = false; this.onStartContinuityEvent = this::onStartContinuity; this.duringContinuityEvent = this::duringContinuityEvent; this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */     stopAfterUsage(false);
/*     */     this.onSwingEvent = this::onSwingEvent;
/*  46 */     this.duringCooldownEvent = this::duringCooldownEvent; } private void duringCooldownEvent(PlayerEntity player, int i) { if (this.mode == Mode.MISSILE && !this.exploded) {
/*     */       
/*  48 */       float maxSpeed = 4.0F;
/*  49 */       Vec3d vec = player.getLookVec();
/*  50 */       player.setMotion(vec.x * maxSpeed, vec.y * maxSpeed, vec.z * maxSpeed);
/*  51 */       player.velocityChanged = true;
/*     */       
/*  53 */       if (player.collided) {
/*     */         
/*  55 */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 6.0F);
/*  56 */         explosion.setExplosionSound(true);
/*  57 */         explosion.setDamageOwner(false);
/*  58 */         explosion.setDestroyBlocks(true);
/*  59 */         explosion.setFireAfterExplosion(true);
/*  60 */         explosion.setStaticDamage(100.0F);
/*  61 */         explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
/*  62 */         explosion.setDamageEntities(true);
/*  63 */         explosion.doExplosion();
/*  64 */         this.exploded = true;
/*     */       } 
/*     */     }  }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuity(PlayerEntity player) {
/*  70 */     this.released = false;
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onSwingEvent(PlayerEntity player) {
/*  76 */     stopAfterUsage(false);
/*  77 */     if (this.mode == Mode.FIRE) {
/*     */       
/*  79 */       FreshFireProjectile proj = new FreshFireProjectile(player.world, (LivingEntity)player);
/*  80 */       player.world.addEntity((Entity)proj);
/*  81 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 10.0F);
/*  82 */     } else if (this.mode == Mode.GATLING) {
/*     */       
/*  84 */       NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(player.world, (LivingEntity)player);
/*  85 */       player.world.addEntity((Entity)normalBulletProjectile);
/*  86 */       normalBulletProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 2.0F);
/*  87 */     } else if (this.mode == Mode.CANNON) {
/*     */       
/*  89 */       stopAfterUsage(true);
/*  90 */       CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(player.world, (LivingEntity)player);
/*  91 */       setMaxCooldown(2.0D);
/*  92 */       player.world.addEntity((Entity)cannonBallProjectile);
/*  93 */       cannonBallProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 2.0F);
/*  94 */     } else if (this.mode == Mode.MISSILE) {
/*     */       
/*  96 */       stopAfterUsage(true);
/*  97 */       setMaxCooldown(8.0D);
/*  98 */       this.exploded = false;
/*     */     } 
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 106 */     if (i % (player.isSneaking() ? 10 : 40) == 0 && !this.released) {
/*     */       
/* 108 */       this.mode = this.mode.getNext();
/* 109 */       player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.mode, new Object[0]));
/* 110 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 111 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/* 112 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 118 */     if (!this.released) {
/*     */       
/* 120 */       this.released = true;
/* 121 */       return false;
/*     */     } 
/*     */     
/* 124 */     return true;
/*     */   }
/*     */   
/*     */   public enum Mode
/*     */   {
/* 129 */     SWORD,
/* 130 */     CANNON,
/* 131 */     GATLING,
/* 132 */     FIRE,
/* 133 */     MISSILE;
/*     */ 
/*     */     
/*     */     public Mode getNext() {
/* 137 */       return (ordinal() == Arrays.<Mode>stream(values()).count() - 1L) ? SWORD : values()[ordinal() + 1];
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\buki\BukimorphoseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */