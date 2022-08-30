/*     */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.rokushiki.GeppoParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class GeppoAbility extends Ability implements IFallDamageBlockingAbility {
/*  21 */   public static final GeppoAbility INSTANCE = new GeppoAbility();
/*  22 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new GeppoParticleEffect();
/*     */   
/*  24 */   public int airJumps = 0;
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   public GeppoAbility() {
/*  29 */     super("Geppo", AbilityHelper.getRacialCategory());
/*  30 */     addInPool(new AbilityPool[] { AbilityPool.GEPPO_LIKE });
/*  31 */     setMaxCooldown(1.0D);
/*  32 */     setDescription("The user kicks the air beneath them to launch themselves into the air");
/*     */     
/*  34 */     this.onUseEvent = this::onUseEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  39 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     if (!player.isInWater()) {
/*     */       
/*  45 */       if (player.onGround) {
/*     */         
/*  47 */         Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.0D, 1.0D);
/*  48 */         player.setMotion(speed.x, 1.86D, speed.z);
/*     */       }
/*     */       else {
/*     */         
/*  52 */         Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.5D, 1.5D);
/*  53 */         player.setMotion(speed.x, 1.25D, speed.z);
/*     */       } 
/*  55 */       AbilityHelper.setAirJumps(player, this.airJumps + 1);
/*  56 */       player.velocityChanged = true;
/*     */     }
/*     */     else {
/*     */       
/*  60 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D, 2.0D);
/*  61 */       player.setMotion(speed.x, speed.y, speed.z);
/*  62 */       AbilityHelper.setAirJumps(player, 0);
/*  63 */       player.velocityChanged = true;
/*  64 */       setMaxCooldown((5 + this.airJumps));
/*  65 */       startCooldown(player);
/*  66 */       return true;
/*     */     } 
/*     */     
/*  69 */     player.world.playSound(null, player.getPosition(), ModSounds.GEPPO_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*  70 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*  71 */     this.hasFallDamage = false;
/*     */     
/*  73 */     int maxJumps = (int)WyHelper.clamp(Math.round(EntityStatsCapability.get((LivingEntity)player).getDoriki() * 0.001D), 3L, 6L);
/*  74 */     if (this.airJumps >= maxJumps) {
/*     */       
/*  76 */       setMaxCooldown(this.airJumps * 2.5D);
/*  77 */       startCooldown(player);
/*  78 */       AbilityHelper.setAirJumps(player, 0);
/*  79 */       return true;
/*     */     } 
/*     */     
/*  82 */     setMaxCooldown(1.0D);
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/*  89 */     if (this.airJumps > 0) {
/*     */       
/*  91 */       setMaxCooldown((this.airJumps * 2.5F));
/*  92 */       startCooldown((PlayerEntity)player);
/*  93 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)player, this), player);
/*  94 */       checkAbilityPool((PlayerEntity)player, Ability.State.COOLDOWN);
/*     */     } 
/*     */     
/*  97 */     this.hasFallDamage = true;
/*  98 */     AbilityHelper.setAirJumps((PlayerEntity)player, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/* 104 */     return this.hasFallDamage;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\GeppoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */