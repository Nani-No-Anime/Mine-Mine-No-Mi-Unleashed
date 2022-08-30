/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningBallEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectricalTempesta2ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectroChargingParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class ElectricalTempestaAbility extends ChargeableAbility {
/*  26 */   public static final ElectricalTempestaAbility INSTANCE = new ElectricalTempestaAbility();
/*     */   
/*  28 */   private static final ElectroChargingParticleEffect PARTICLES1 = new ElectroChargingParticleEffect();
/*  29 */   private static final ElectricalTempesta2ParticleEffect PARTICLES2 = new ElectricalTempesta2ParticleEffect();
/*     */   
/*     */   private static final int COOLDOWN = 8;
/*     */   private static final double CHARGE_TIME = 1.0D;
/*  33 */   private LightningBallEntity ballEntity = null;
/*     */ 
/*     */   
/*     */   public ElectricalTempestaAbility() {
/*  37 */     super("Electrical Tempesta", AbilityHelper.getRacialCategory());
/*  38 */     setMaxCooldown(8.0D);
/*  39 */     setMaxChargeTime(1.0D);
/*  40 */     setDescription("The user releases a charge of energy that deals damage to nearby enemies");
/*  41 */     addInPool(new AbilityPool[] { AbilityPool.MINK_ELECTRO });
/*     */     
/*  43 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  44 */     this.duringChargingEvent = this::duringChargingEvent;
/*  45 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  50 */     EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
/*  51 */     boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
/*     */     
/*  53 */     if (!eleclawEnabled) {
/*     */       
/*  55 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_ELECLAW, new Object[0]));
/*  56 */       return false;
/*     */     } 
/*     */     
/*  59 */     SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
/*  60 */     boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
/*  61 */     setMaxChargeTime(1.0D);
/*  62 */     setMaxCooldown(sulongEnabled ? 4.0D : 8.0D);
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/*  68 */     player.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 4, 1, false, false));
/*  69 */     if (chargeTime > 10) {
/*  70 */       PARTICLES1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  72 */     if (this.ballEntity == null) {
/*     */       
/*  74 */       LightningBallEntity ball = new LightningBallEntity((Entity)player, player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/*  75 */       player.world.addEntity((Entity)ball);
/*  76 */       this.ballEntity = ball;
/*     */     }
/*     */     else {
/*     */       
/*  80 */       float distance = 0.5F;
/*  81 */       Vec3d lookVec = player.getLookVec();
/*  82 */       Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() * 0.85D + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/*  83 */       float percentage = 1.0F - getChargeTime() / getMaxChargeTime();
/*  84 */       this.ballEntity.setSize(percentage * 0.1F);
/*  85 */       this.ballEntity.setLightningLength(2.0F);
/*  86 */       this.ballEntity.setPositionAndRotation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  92 */     if (this.ballEntity != null) {
/*     */       
/*  94 */       this.ballEntity.remove();
/*  95 */       this.ballEntity = null;
/*     */     } 
/*     */     
/*  98 */     EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
/*  99 */     boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
/*     */     
/* 101 */     SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
/* 102 */     boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
/*     */     
/* 104 */     if (eleclawEnabled) {
/*     */       
/* 106 */       float damage = (20 + (sulongEnabled ? 20 : 0));
/* 107 */       int range = 10 * (sulongEnabled ? 2 : 1);
/*     */       
/* 109 */       for (int i = 0; i < 3; i++) {
/* 110 */         PARTICLES2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */       }
/* 112 */       List<LivingEntity> list = WyHelper.getEntitiesNearSphere(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 113 */       list.remove(player);
/*     */       
/* 115 */       list.forEach(entity -> {
/*     */             boolean hit = entity.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player), damage);
/*     */             
/*     */             if (hit) {
/*     */               Vec3d dirVec = player.getPositionVec().subtract(entity.getPositionVec()).normalize();
/*     */               
/*     */               player.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 10, 0, false, false, true));
/*     */               
/*     */               entity.setMotion(-dirVec.x * 7.0D, 1.0D, -dirVec.z * 7.0D);
/*     */               entity.velocityChanged = true;
/*     */             } 
/*     */           });
/* 127 */       int amount = 32;
/* 128 */       for (int j = 0; j < amount; j++) {
/*     */         
/* 130 */         float boltSize = (float)WyHelper.randomWithRange(3, range);
/* 131 */         LightningEntity bolt = new LightningEntity((Entity)player, player.getPosX(), player.getPosY() + 0.75D, player.getPosZ(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(0, 5), boltSize, 8.0F);
/* 132 */         bolt.setAngle(60);
/* 133 */         bolt.setAliveTicks(20);
/* 134 */         bolt.setDamage(0.0F);
/* 135 */         bolt.setExplosion(0, false);
/* 136 */         bolt.setSize(boltSize / 600.0F);
/* 137 */         bolt.setBranches((int)WyHelper.randomWithRange(1, 3));
/* 138 */         bolt.setSegments((int)(boltSize * 0.6D));
/* 139 */         bolt.disableLightningMimic();
/* 140 */         player.world.addEntity((Entity)bolt);
/*     */       } 
/*     */       
/* 143 */       eleclawAbility.reduceUsage(player, 1);
/*     */     } 
/*     */     
/* 146 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\ElectricalTempestaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */