/*     */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IHeldItemAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.ope.InjectionShotAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class InjectionShotAbility extends ChargeableAbility implements IMultiTargetAbility, IAnimatedAbility {
/*  29 */   public static final Ability INSTANCE = (Ability)new InjectionShotAbility();
/*     */ 
/*     */   
/*     */   public InjectionShotAbility() {
/*  33 */     super("Injection Shot", AbilityHelper.getDevilFruitCategory());
/*  34 */     setMaxCooldown(10.0D);
/*  35 */     setMaxChargeTime(1.0D);
/*  36 */     setDescription("While holding a weapon, the user charges at the enemy, leaving them confused");
/*     */     
/*  38 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  39 */     this.duringChargingEvent = this::duringChargingEvent;
/*  40 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*  41 */     this.duringCooldownEvent = this::duringCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  46 */     if (!AbilityHelper.canUseMomentumAbility(player) || !canUseAbility(player)) {
/*  47 */       return false;
/*     */     }
/*  49 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, getMaxChargeTime(), 0, false, false));
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int i) {
/*  55 */     if (!canUseAbility(player)) {
/*  56 */       startCooldown(player);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  61 */     if (!canUseAbility(player)) {
/*  62 */       return false;
/*     */     }
/*  64 */     clearTargets();
/*     */     
/*  66 */     Vec3d dirVec = player.getLookVec().mul(16.0D, 1.0D, 16.0D);
/*  67 */     player.setMotion(dirVec.x * 0.25D, 0.35D, dirVec.z * 0.25D);
/*  68 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*     */     
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/*  75 */     if (canDealDamage()) {
/*     */       
/*  77 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  78 */       list.remove(player);
/*     */       
/*  80 */       list.forEach(entity -> {
/*     */             if (isTarget(entity)) {
/*     */               entity.attackEntityFrom(ModDamageSource.causePlayerDamage(player), 40.0F);
/*     */               entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 60, 0));
/*     */             } 
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canUseAbility(PlayerEntity player) {
/*  93 */     if (!OpeHelper.hasRoomActive(player, (Ability)this)) {
/*  94 */       return false;
/*     */     }
/*  96 */     if (!ItemsHelper.isSword(player.getHeldItemMainhand())) {
/*     */       
/*  98 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
/*  99 */       return false;
/*     */     } 
/*     */     
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDealDamage() {
/* 107 */     return (this.cooldown > getMaxCooldown() * 0.8D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IHeldItemAnimation getAnimation() {
/* 113 */     return (IHeldItemAnimation)InjectionShotAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 119 */     return isCharging();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\InjectionShotAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */