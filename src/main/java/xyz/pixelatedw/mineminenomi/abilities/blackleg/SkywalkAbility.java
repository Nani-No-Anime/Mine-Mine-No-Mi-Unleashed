/*     */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.rokushiki.GeppoParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class SkywalkAbility extends Ability implements IFallDamageBlockingAbility {
/*  20 */   public static final SkywalkAbility INSTANCE = new SkywalkAbility();
/*  21 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new GeppoParticleEffect();
/*     */   
/*     */   private static final int MAX_JUMPS = 6;
/*     */   
/*  25 */   public int airJumps = 0;
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   public SkywalkAbility() {
/*  30 */     super("Skywalk", AbilityHelper.getStyleCategory());
/*  31 */     addInPool(new AbilityPool[] { AbilityPool.GEPPO_LIKE });
/*  32 */     setMaxCooldown(1.0D);
/*  33 */     setDescription("Allows the user to kick the air beneath them and launch themselves into the air");
/*     */     
/*  35 */     this.onUseEvent = this::onUseEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  40 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  41 */       return false;
/*     */     }
/*     */     
/*  44 */     if (!player.isInWater()) {
/*     */       
/*  46 */       if (player.onGround) {
/*     */         
/*  48 */         Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.0D, 1.0D);
/*  49 */         player.setMotion(speed.x, 1.86D, speed.z);
/*     */       }
/*     */       else {
/*     */         
/*  53 */         Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.5D, 1.5D);
/*  54 */         player.setMotion(speed.x, 1.25D, speed.z);
/*     */       } 
/*  56 */       AbilityHelper.setAirJumps(player, this.airJumps + 1);
/*  57 */       player.velocityChanged = true;
/*     */     }
/*     */     else {
/*     */       
/*  61 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.5D, 2.5D, 2.5D);
/*  62 */       player.setMotion(speed.x, speed.y, speed.z);
/*  63 */       setMaxCooldown((4 + this.airJumps * 2));
/*  64 */       AbilityHelper.setAirJumps(player, 0);
/*  65 */       this.hasFallDamage = false;
/*  66 */       player.velocityChanged = true;
/*  67 */       startCooldown(player);
/*  68 */       return true;
/*     */     } 
/*     */     
/*  71 */     player.world.playSound(null, player.getPosition(), ModSounds.GEPPO_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*  72 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*  73 */     this.hasFallDamage = false;
/*     */     
/*  75 */     if (this.airJumps >= 6) {
/*     */       
/*  77 */       setMaxCooldown(this.airJumps * 2.5D);
/*  78 */       startCooldown(player);
/*  79 */       AbilityHelper.setAirJumps(player, 0);
/*  80 */       return true;
/*     */     } 
/*     */     
/*  83 */     setMaxCooldown(1.0D);
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/*  90 */     if (this.airJumps > 0) {
/*     */       
/*  92 */       setMaxCooldown((this.airJumps * 2.5F));
/*  93 */       startCooldown((PlayerEntity)player);
/*  94 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)player, this), player);
/*  95 */       checkAbilityPool((PlayerEntity)player, Ability.State.COOLDOWN);
/*     */     } 
/*     */     
/*  98 */     this.hasFallDamage = true;
/*  99 */     AbilityHelper.setAirJumps((PlayerEntity)player, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/* 105 */     return this.hasFallDamage;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\SkywalkAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */