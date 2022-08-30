/*     */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class GraviZoneAbility extends ContinuousAbility {
/*  28 */   public static final GraviZoneAbility INSTANCE = new GraviZoneAbility();
/*  29 */   private MODE activeMode = MODE.GUARD;
/*     */ 
/*     */   
/*     */   public GraviZoneAbility() {
/*  33 */     super("Gravi Zone", AbilityHelper.getDevilFruitCategory());
/*  34 */     setDescription("Creates an area in which entities cannot move while in GUARD mode or they get pushed back from the user while in REJECT mode\n\n§2SHIFT-USE§r: Switches between GUARD and REJECT modes");
/*  35 */     this.onStartContinuityEvent = this::onStartContinuity;
/*  36 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  37 */     setMaxCooldown(10.0D);
/*  38 */     setThreshold(8.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuity(PlayerEntity player) {
/*  43 */     if (player.isSneaking()) {
/*     */       
/*  45 */       if (this.activeMode == MODE.GUARD) {
/*     */         
/*  47 */         setThreshold(5.0D);
/*  48 */         this.activeMode = MODE.REJECT;
/*  49 */         setCustomTexture("gravi_zone_reject");
/*     */       }
/*     */       else {
/*     */         
/*  53 */         setThreshold(8.0D);
/*  54 */         this.activeMode = MODE.GUARD;
/*  55 */         setCustomTexture("gravi_zone");
/*     */       } 
/*     */       
/*  58 */       player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.activeMode, new Object[0]));
/*  59 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  60 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/*  61 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*     */       
/*  63 */       return false;
/*     */     } 
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int i) {
/*  71 */     int range = 3;
/*     */     
/*  73 */     if (this.activeMode == MODE.GUARD) {
/*     */       
/*  75 */       range += 5;
/*  76 */       List<Entity> generalList = WyHelper.getEntitiesNear(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { Entity.class });
/*  77 */       generalList.remove(player);
/*     */       
/*  79 */       generalList.forEach(entity -> {
/*     */             entity.setPositionAndUpdate(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
/*     */             
/*     */             if (entity instanceof LivingEntity) {
/*     */               ((LivingEntity)entity).addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 0, false, false));
/*     */             }
/*     */             entity.velocityChanged = true;
/*     */           });
/*  87 */       gravityRing((LivingEntity)player, range, 0, false);
/*  88 */       gravityRing((LivingEntity)player, range - 2, 4, false);
/*  89 */       gravityRing((LivingEntity)player, range - 4, 8, false);
/*     */     }
/*  91 */     else if (this.activeMode == MODE.REJECT) {
/*     */       
/*  93 */       List<Entity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { Entity.class });
/*  94 */       list.remove(player);
/*     */       
/*  96 */       list.forEach(entity -> {
/*     */             boolean causedDamage = true;
/*     */             
/*     */             if (entity instanceof LivingEntity) {
/*     */               causedDamage = entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
/*     */             }
/*     */             
/*     */             if (causedDamage) {
/*     */               Vec3d dist = entity.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D).normalize();
/*     */               
/*     */               double power = 4.5D;
/*     */               
/*     */               double xSpeed = -dist.x * power;
/*     */               
/*     */               double zSpeed = -dist.z * power;
/*     */               
/*     */               entity.setMotion(-xSpeed, 0.20000000298023224D, -zSpeed);
/*     */               entity.velocityChanged = true;
/*     */             } 
/*     */           });
/* 116 */       gravityRing((LivingEntity)player, range + 3, 4, false);
/* 117 */       gravityRing((LivingEntity)player, range + 2, 2, false);
/* 118 */       gravityRing((LivingEntity)player, range, 0, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void gravityRing(LivingEntity entity, int range, int yOffset, boolean visibleOnlyFromOwner) {
/*     */     double z;
/* 124 */     for (z = 0.0D; z < 7.283185307179586D; z += 0.09817477042468103D) {
/*     */       
/* 126 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
/* 127 */       data.setLife(12);
/* 128 */       data.setSize(2.0F);
/* 129 */       double offsetX = Math.cos(z) * range;
/* 130 */       double offsetZ = Math.sin(z) * range;
/* 131 */       data.setMotion(offsetX / 20.0D, 0.0D, offsetZ / 20.0D);
/* 132 */       if (visibleOnlyFromOwner) {
/*     */         
/* 134 */         data.setEntityID(entity.getEntityId());
/* 135 */         data.hideFromOthers();
/*     */       } 
/* 137 */       WyHelper.spawnParticles(data, (ServerWorld)entity.world, entity.getPosX() + offsetX, entity.getPosY() + 1.0D + yOffset, entity.getPosZ() + offsetZ);
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum MODE
/*     */   {
/* 143 */     GUARD, REJECT;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\GraviZoneAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */