/*     */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.ConcasseParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.blackleg.ConcasseAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class ConcasseAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility, IExtraUpdateData, IAnimatedAbility {
/*  32 */   public static final ConcasseAbility INSTANCE = new ConcasseAbility();
/*     */   
/*  34 */   private static final ConcasseParticleEffect PARTICLES = new ConcasseParticleEffect();
/*     */   
/*     */   private boolean hasLanded = false;
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   private LivingEntity owner;
/*     */   
/*     */   public ConcasseAbility() {
/*  42 */     super("Concasse", AbilityHelper.getStyleCategory());
/*  43 */     setMaxCooldown(15.0D);
/*  44 */     setDescription("Leaps forward kicking all nearby enemies for moderate damage and knocking them down");
/*     */     
/*  46 */     this.onUseEvent = this::onUseEvent;
/*  47 */     this.duringCooldownEvent = this::duringCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  52 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  53 */       return false;
/*     */     }
/*  55 */     clearTargets();
/*     */     
/*  57 */     player.setMotion((player.getMotion()).x, 1.3D, (player.getMotion()).z);
/*  58 */     player.velocityChanged = true;
/*  59 */     this.hasLanded = false;
/*  60 */     this.hasFallDamage = false;
/*  61 */     this.owner = (LivingEntity)player;
/*     */     
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/*  68 */     if (player.isInWater() && !this.hasLanded) {
/*  69 */       this.hasLanded = true;
/*     */     }
/*  71 */     if (player.onGround && getCooldownPercentage() < 98.0D && !this.hasLanded) {
/*     */       
/*  73 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.75D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  74 */       targets.remove(player);
/*     */       
/*  76 */       for (LivingEntity entity : targets) {
/*     */         
/*  78 */         if (isTarget(entity)) {
/*     */           
/*  80 */           entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this, "player"), 15.0F);
/*  81 */           EffectInstance effInst = new EffectInstance(ModEffects.UNCONSCIOUS, 60, 0, false, false);
/*  82 */           entity.addPotionEffect(effInst);
/*  83 */           ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(entity.getEntityId(), effInst));
/*     */         } 
/*     */       } 
/*     */       
/*  87 */       if (targets.size() > 0) {
/*  88 */         ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*     */       }
/*  90 */       this.hasLanded = true;
/*  91 */       if (this.hasLanded) {
/*  92 */         WyNetwork.sendTo(new SUpdateExtraDataPacket(player, this), player);
/*     */       }
/*     */     } 
/*  95 */     DiableJambeAbility diableJambeAbility = (DiableJambeAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
/*  96 */     boolean isAbilityEnabled = (diableJambeAbility != null && diableJambeAbility.isContinuous());
/*     */     
/*  98 */     if (isAbilityEnabled && !player.onGround && !this.hasLanded) {
/*  99 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasLanded() {
/* 104 */     return this.hasLanded;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/* 110 */     CompoundNBT nbt = new CompoundNBT();
/* 111 */     nbt.putBoolean("hasLanded", this.hasLanded);
/* 112 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 118 */     this.hasLanded = nbt.getBoolean("hasLanded");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/* 124 */     this.hasFallDamage = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/* 130 */     return this.hasFallDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 136 */     return (IAnimation)ConcasseAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 142 */     return (isOnCooldown() && !hasLanded());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\ConcasseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */