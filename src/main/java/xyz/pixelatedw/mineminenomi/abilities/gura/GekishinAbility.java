/*     */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gura.GekishinProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.gura.AirCrackParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.gura.KaishinAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class GekishinAbility extends ChargeableAbility implements IAnimatedAbility {
/*  32 */   public static final Ability INSTANCE = (Ability)new GekishinAbility();
/*     */   
/*  34 */   private static final AirCrackParticleEffect PARTICLES = new AirCrackParticleEffect();
/*  35 */   private MODE activeMode = MODE.GEKISHIN;
/*     */ 
/*     */   
/*     */   public GekishinAbility() {
/*  39 */     super("Gekishin", AbilityHelper.getDevilFruitCategory());
/*  40 */     setDescription("The user 'cracks' the air, launching vibrations which send blocks flying \n\n§2SHIFT-USE§r: Switches between Gekishin and Kaishin mode");
/*  41 */     setMaxCooldown(12.0D);
/*  42 */     setMaxChargeTime(1.0D);
/*     */     
/*  44 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  45 */     this.duringChargingEvent = this::duringChargingEvent;
/*  46 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  51 */     if (player.isSneaking()) {
/*     */       
/*  53 */       if (this.activeMode == MODE.GEKISHIN) {
/*     */         
/*  55 */         this.activeMode = MODE.KAISHIN;
/*  56 */         setMaxCooldown(24.0D);
/*  57 */         setMaxChargeTime(2.0D);
/*  58 */         setCustomTexture("kaishin");
/*     */       }
/*     */       else {
/*     */         
/*  62 */         this.activeMode = MODE.GEKISHIN;
/*  63 */         setMaxCooldown(12.0D);
/*  64 */         setMaxChargeTime(1.0D);
/*  65 */         setCustomTexture("gekishin");
/*     */       } 
/*     */       
/*  68 */       player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.activeMode, new Object[0]));
/*  69 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  70 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/*  71 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*  72 */       return false;
/*     */     } 
/*  74 */     getAnimation().start();
/*  75 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int i) {}
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  84 */     float time = getChargeTime() / getMaxChargeTime();
/*  85 */     float multiplier = 1.0F - time;
/*     */     
/*  87 */     if (multiplier < 0.2D) {
/*  88 */       return false;
/*     */     }
/*  90 */     if (this.activeMode == MODE.GEKISHIN) {
/*     */       
/*  92 */       EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
/*  93 */       PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY() + 0.5D, trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
/*     */       
/*  95 */       player.world.playSound(null, player.getPosition(), ModSounds.GURA_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */       
/*  97 */       GekishinProjectile proj = new GekishinProjectile(player.world, (LivingEntity)player);
/*  98 */       proj.setMaxLife((int)(proj.getMaxLife() * multiplier));
/*  99 */       player.world.addEntity((Entity)proj);
/* 100 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 104 */       multiplier = (float)(multiplier * 0.5D);
/*     */       
/* 106 */       Vec3d v1 = player.getPositionVec().add(0.0D, player.getEyeHeight(), 0.0D).add(player.getLookVec().scale(2.5D).rotateYaw(180.0F));
/* 107 */       Vec3d v2 = player.getPositionVec().add(0.0D, player.getEyeHeight(), 0.0D).add(player.getLookVec().scale(2.5D).rotateYaw(-180.0F));
/* 108 */       PARTICLES.spawn(player.world, v1.getX(), player.getPosY() + 0.5D, v1.getZ(), 0.0D, 0.0D, 0.0D);
/* 109 */       PARTICLES.spawn(player.world, v2.getX(), player.getPosY() + 0.5D, v2.getZ(), 0.0D, 0.0D, 0.0D);
/*     */       
/* 111 */       List<Entity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, (16.0F * multiplier), FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { Entity.class });
/* 112 */       list.remove(player);
/*     */       
/* 114 */       for (Entity target : list) {
/*     */         
/* 116 */         if (target instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)target).getDamage() < 40.0F * multiplier && ((AbilityProjectileEntity)target).isPhysical()) {
/* 117 */           target.remove();
/*     */         }
/* 119 */         if (target instanceof LivingEntity) {
/*     */           
/* 121 */           Vec3d speed = target.getLook(1.0F).mul(3.0D, 2.0D, 3.0D);
/* 122 */           target.setMotion(speed.x, speed.y, speed.z);
/* 123 */           target.velocityChanged = true;
/* 124 */           target.fallDistance = 0.0F;
/*     */         } 
/*     */       } 
/*     */       
/* 128 */       player.world.playSound(null, player.getPosition(), ModSounds.GURA_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */       
/* 130 */       GekishinProjectile p1 = new GekishinProjectile(player.world, (LivingEntity)player);
/* 131 */       p1.setMaxLife((int)(p1.getMaxLife() * multiplier));
/* 132 */       player.world.addEntity((Entity)p1);
/* 133 */       p1.shoot((Entity)player, player.rotationPitch, player.rotationYaw + 90.0F, 0.0F, 1.75F, 1.0F);
/* 134 */       GekishinProjectile p2 = new GekishinProjectile(player.world, (LivingEntity)player);
/* 135 */       p2.setMaxLife((int)(p2.getMaxLife() * multiplier));
/* 136 */       player.world.addEntity((Entity)p2);
/* 137 */       p2.shoot((Entity)player, player.rotationPitch, player.rotationYaw - 90.0F, 0.0F, 1.75F, 1.0F);
/*     */     } 
/*     */     
/* 140 */     setMaxCooldown((this.activeMode == MODE.GEKISHIN) ? (6.0F + 6.0F * multiplier) : (12.0F + 12.0F * multiplier));
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   public enum MODE
/*     */   {
/* 146 */     GEKISHIN, KAISHIN;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeAnimation getAnimation() {
/* 152 */     return (TimeAnimation)KaishinAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 158 */     return (isCharging() && getChargeTime() < WyHelper.percentage(75.0D, getMaxChargeTime()));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\GekishinAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */