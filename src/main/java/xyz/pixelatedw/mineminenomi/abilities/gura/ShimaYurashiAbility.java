/*     */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;

import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.GroundParticlesEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.gura.AirCrackParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.gura.KaishinAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class ShimaYurashiAbility extends ChargeableAbility implements IAnimatedAbility {
/*  43 */   public static final Ability INSTANCE = (Ability)new ShimaYurashiAbility();
/*     */   
/*     */   private static final int EXPLOSION_RADIUS = 27;
/*     */   private static final int EXPLOSION_DEPTH = 10;
/*  47 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GroundParticlesEffect(27, 800);
/*  48 */   private static final ParticleEffect AIR_GRAB_PARTICLES = (ParticleEffect)new AirCrackParticleEffect();
/*     */   
/*  50 */   public List<FallingBlockEntity> entityBlocks = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public ShimaYurashiAbility() {
/*  54 */     super("Shima Yurashi", AbilityHelper.getDevilFruitCategory());
/*  55 */     setMaxCooldown(60.0D);
/*  56 */     setMaxChargeTime(5.0D);
/*  57 */     setDescription("The user grabs the air and pulls it downwards after which the nearby land and entities are sent flying");
/*     */     
/*  59 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  60 */     this.duringChargingEvent = this::duringChargingEvent;
/*  61 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*  62 */     this.duringCooldownEvent = this::duringCooldownEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  67 */     if (!player.onGround) {
/*     */       
/*  69 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
/*  70 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  75 */     List<BlockPos> randomPositions = (List<BlockPos>)WyHelper.getNearbyBlocks(player.getPosition(), (IWorld)player.world, 13, (List)ImmutableList.of(Blocks.AIR)).stream().filter(pos -> DefaultProtectionRules.CORE_FOLIAGE_ORE.check(player.world, (BlockPos)pos, player.world.getBlockState((BlockPos)pos))).collect(Collectors.toList());
/*  76 */     Collections.shuffle(randomPositions, player.getRNG());
/*     */     
/*  78 */     this
/*     */ 
/*     */       
/*  81 */       .entityBlocks = (List<FallingBlockEntity>)randomPositions.stream().limit(600L).map(pos -> new FallingBlockEntity(player.world, pos.getX(), pos.getY(), pos.getZ(), player.world.getBlockState(pos))).collect(Collectors.toList());
/*     */     
/*  83 */     return CommonConfig.INSTANCE.isAbilityGriefingEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/*  88 */     if (chargeTime % 2 == 0) {
/*  89 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  91 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 10, 0, false, false));
/*     */     
/*  93 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 27.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  94 */     targets.remove(player);
/*     */     
/*  96 */     targets.stream()
/*  97 */       .filter(target -> 
/*  98 */         (target != null && target.canEntityBeSeen((Entity)player) && target.isAlive() && target.world.rayTraceBlocks(new RayTraceContext(target.getPositionVec(), target.getPositionVec().add(0.0D, -10.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)target)).getType().equals(RayTraceResult.Type.BLOCK)))
/*     */ 
/*     */ 
/*     */       
/* 102 */       .forEach(target -> target.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 10, 0, false, false)));
/*     */ 
/*     */     
/* 105 */     if (chargeTime < 30 && !getAnimation().isPlaying()) {
/* 106 */       getAnimation().start();
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 111 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 27.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 112 */     targets.remove(player);
/*     */     
/* 114 */     targets.stream().filter(target -> (target != null && target.isAlive() && player.canEntityBeSeen((Entity)target)))
/* 115 */       .forEach(target -> {
/*     */           double distance = Math.sqrt(target.getDistanceSq((Entity)player));
/*     */           
/*     */           double damage = 80.0D - distance / 2.0D;
/*     */           
/*     */           target.attackEntityFrom(ModDamageSource.causeAbilityDamage((LivingEntity)player, INSTANCE).setDamageBypassingLogiaInvulnerability().setDamageBypassesArmor(), (float)damage);
/*     */           
/*     */           if (target.onGround) {
/*     */             Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize().mul(25.0D, 1.0D, 25.0D);
/*     */             
/*     */             target.setMotion(-dirVec.x, 3.0D, -dirVec.z);
/*     */             target.velocityChanged = true;
/*     */             damage *= 1.5D;
/*     */           } 
/*     */         });
/* 130 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
/* 131 */     AIR_GRAB_PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY() + 0.5D, trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/* 133 */     Iterator<FallingBlockEntity> iter = this.entityBlocks.iterator();
/* 134 */     while (iter.hasNext()) {
/*     */       
/* 136 */       FallingBlockEntity entity = iter.next();
/* 137 */       Vec3d dirVec = player.getPositionVec().subtract(entity.getPositionVec()).normalize().mul(2.0D, 2.0D, 2.0D);
/* 138 */       entity.setPosition(player
/* 139 */           .getPosition().getX() + WyHelper.randomDouble() * 20.0D, player
/* 140 */           .getPosition().getY() + WyHelper.randomDouble() * 3.0D, player
/* 141 */           .getPosition().getZ() + WyHelper.randomDouble() * 20.0D);
/* 142 */       entity.setMotion(-dirVec.x, 1.0D + dirVec.y, -dirVec.z);
/*     */ 
/*     */ 
/*     */       
/* 146 */       entity.velocityChanged = true;
/* 147 */       entity.shouldDropItem = false;
/* 148 */       entity.fallTime = 1;
/* 149 */       player.world.addEntity((Entity)entity);
/*     */     } 
/*     */     
/* 152 */     int craterRadius = 72;
/* 153 */     AbilityHelper.createSphereWithProtection(player.world, player.getPosition(), craterRadius, 10, Blocks.AIR, 3, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID);
/* 154 */     player.world.playSound(null, player.getPosition(), ModSounds.GURA_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     
/* 156 */     getAnimation().stop();
/*     */     
/* 158 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void duringCooldownEvent(PlayerEntity player, int i) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeAnimation getAnimation() {
/* 169 */     return (TimeAnimation)KaishinAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 175 */     return isCharging();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\ShimaYurashiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */