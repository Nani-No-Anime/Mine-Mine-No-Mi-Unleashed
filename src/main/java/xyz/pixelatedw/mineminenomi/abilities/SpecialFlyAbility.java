/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.SunaHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SFlightValuePacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.SpecialFlyingParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.SpecialFlyAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class SpecialFlyAbility extends PassiveAbility implements IAnimatedAbility {
/*  28 */   public static final SpecialFlyAbility INSTANCE = new SpecialFlyAbility();
/*     */   
/*  30 */   private static final ParticleEffect PARTICLES_SUNA = (ParticleEffect)new SpecialFlyingParticleEffect(ModParticleTypes.SUNA2);
/*  31 */   private static final ParticleEffect PARTICLES_GASU = (ParticleEffect)new SpecialFlyingParticleEffect(ModParticleTypes.GASU);
/*  32 */   private static final ParticleEffect PARTICLES_MOKU = (ParticleEffect)new SpecialFlyingParticleEffect(ModParticleTypes.MOKU);
/*     */   
/*     */   private boolean isFlying;
/*     */ 
/*     */   
/*     */   public SpecialFlyAbility() {
/*  38 */     super("Special Fly", AbilityHelper.getDevilFruitCategory());
/*  39 */     setDescription("Allows the user to fly");
/*  40 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*  41 */     hideInGUI(false);
/*  42 */     setDisplayName("Elemental Flight");
/*  43 */     setCustomTexture("special_fly");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(PlayerEntity player) {
/*  49 */     if ((isPaused() || !canUse(player)) && !player.isCreative() && !player.isSpectator() && player.abilities.allowFlying) {
/*     */       
/*  51 */       player.abilities.allowFlying = false;
/*  52 */       player.abilities.isFlying = false;
/*  53 */       if (player instanceof ServerPlayerEntity) {
/*  54 */         ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*     */       }
/*     */     } 
/*  57 */     super.tick(player);
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringPassiveEvent(PlayerEntity player) {
/*  62 */     if (!CommonConfig.INSTANCE.isSpecialFlyingEnabled() || player.isCreative() || player.isSpectator()) {
/*     */       return;
/*     */     }
/*  65 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*  66 */     boolean isSuna = props.hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI);
/*     */     
/*  68 */     if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || (isSuna && player.isWet())) {
/*     */       
/*  70 */       player.abilities.allowFlying = false;
/*  71 */       player.abilities.isFlying = false;
/*  72 */       if (player instanceof ServerPlayerEntity) {
/*  73 */         ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*     */       }
/*     */       return;
/*     */     } 
/*  77 */     boolean isGasu = props.hasDevilFruit(ModAbilities.GASU_GASU_NO_MI);
/*  78 */     boolean isMoku = props.hasDevilFruit(ModAbilities.MOKU_MOKU_NO_MI);
/*  79 */     this.isFlying = player.abilities.isFlying;
/*  80 */     boolean flight = (!AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.ABILITY_OFF) && !DevilFruitHelper.kairosekiChecks((LivingEntity)player));
/*     */     
/*  82 */     if (!player.world.isRemote) {
/*     */       
/*  84 */       WyNetwork.sendTo(new SFlightValuePacket(flight), player);
/*  85 */       player.abilities.allowFlying = flight;
/*     */       
/*  87 */       if (this.isFlying)
/*     */       {
/*  89 */         if (isSuna) {
/*  90 */           PARTICLES_SUNA.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*  91 */         } else if (isMoku) {
/*  92 */           PARTICLES_MOKU.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*  93 */         } else if (isGasu) {
/*  94 */           PARTICLES_GASU.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       }
/*     */     } 
/*  98 */     if (this.isFlying) {
/*     */       
/* 100 */       double maxDifference = isGasu ? 64.0D : 40.0D;
/* 101 */       float speedMultiplier = isGasu ? 1.0F : (isMoku ? 0.95F : (SunaHelper.isFruitBoosted(player) ? 1.0F : 0.93F));
/* 102 */       if (ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/* 103 */         speedMultiplier = (float)(speedMultiplier + 0.05D);
/*     */       }
/* 105 */       player.setMotion(player.getMotion().mul(speedMultiplier, (speedMultiplier - 0.25F), speedMultiplier));
/*     */       
/* 107 */       if (player.isSprinting()) {
/*     */         
/* 109 */         player.setMotion(player.getMotion().mul(0.67D, 1.0D, 0.67D));
/* 110 */         player.setSprinting(false);
/*     */       } 
/*     */       
/* 113 */       boolean canFly = DevilFruitHelper.isFlyingAtMaxHeight(player, maxDifference);
/* 114 */       DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, canFly ? 256 : ((int)player.getPosY() - 1));
/*     */     } 
/*     */     
/* 117 */     if (!flight) {
/* 118 */       player.abilities.isFlying = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 124 */     return (IAnimation)SpecialFlyAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 130 */     return this.isFlying;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\SpecialFlyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */