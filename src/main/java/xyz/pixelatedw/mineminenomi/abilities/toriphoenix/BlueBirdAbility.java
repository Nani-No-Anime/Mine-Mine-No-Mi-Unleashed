/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class BlueBirdAbility extends ChargeableAbility implements IFormRequiredAbility {
/*  28 */   public static final BlueBirdAbility INSTANCE = new BlueBirdAbility();
/*  29 */   private List<LivingEntity> list = new ArrayList<>();
/*     */   
/*  31 */   private BlockPos pos = null;
/*     */   
/*     */   public float rotationPitch;
/*     */   public float rotationYaw;
/*     */   
/*     */   public BlueBirdAbility() {
/*  37 */     super("Blue Bird", AbilityHelper.getDevilFruitCategory());
/*  38 */     setMaxChargeTime(2.0D);
/*  39 */     setMaxCooldown(8.0D);
/*  40 */     setDescription("While in the air, the user builds up momentum through blue flames, to deliver a devastating kick");
/*     */     
/*  42 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  43 */     this.duringChargingEvent = this::duringChargingEvent;
/*  44 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  49 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  50 */       return false;
/*     */     }
/*  52 */     this.list.clear();
/*     */     
/*  54 */     if (player.onGround) {
/*     */       
/*  56 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, new Object[] { getName() }));
/*  57 */       return false;
/*     */     } 
/*     */     
/*  60 */     this.pos = player.getPosition();
/*  61 */     this.rotationPitch = player.rotationPitch;
/*  62 */     this.rotationYaw = player.rotationYaw;
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int time) {
/*  68 */     float incrementPI = 0.049087387F;
/*  69 */     float radius1 = (float)fromRangeToRange(0.0D, getMaxChargeTime(), 0.15D, 1.25D, (getMaxChargeTime() - time));
/*  70 */     float radius2 = (float)fromRangeToRange(0.0D, getMaxChargeTime(), 0.05D, 0.3D, (getMaxChargeTime() - time));
/*  71 */     float rotation = (float)(incrementPI * fromRangeToRange(0.0D, getMaxChargeTime(), -128.0D, 128.0D, time));
/*  72 */     float rotation2 = (float)(incrementPI * fromRangeToRange(0.0D, getMaxChargeTime(), -128.0D, 128.0D, time) + 2.0943951023931953D);
/*  73 */     float rotation3 = (float)(incrementPI * fromRangeToRange(0.0D, getMaxChargeTime(), -128.0D, 128.0D, time) - 2.0943951023931953D);
/*     */     
/*  75 */     Vec3d normalizedH = getPerpendicularHorizontalLine(Vec3d.ZERO, player.getLookVec(), radius1);
/*  76 */     Vec3d normalizedV = getPerpendicularVerticalLine(Vec3d.ZERO, player.getLookVec(), normalizedH.mul(-3.141592653589793D, -3.141592653589793D, -3.141592653589793D), radius2);
/*     */     
/*  78 */     Vec3d finalPosition1 = getParticlePositionInSpiral(rotation, normalizedH, normalizedV);
/*     */     
/*  80 */     Vec3d finalPosition2 = getParticlePositionInSpiral(rotation2, normalizedH, normalizedV);
/*     */     
/*  82 */     Vec3d finalPosition3 = getParticlePositionInSpiral(rotation3, normalizedH, normalizedV);
/*     */     
/*  84 */     GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
/*  85 */     data.setLife(15);
/*  86 */     data.setSize(2.0F);
/*  87 */     data.setMotion(player.getLookVec().getX() / 10.0D, player.getLookVec().getY() / 10.0D, player.getLookVec().getZ() / 10.0D);
/*     */     
/*  89 */     double posX = player.getPosX() + finalPosition1.x;
/*  90 */     double posY = player.getPosYEye() + finalPosition1.y;
/*  91 */     double posZ = player.getPosZ() + finalPosition1.z;
/*     */     
/*  93 */     double posX2 = player.getPosX() + finalPosition2.x;
/*  94 */     double posY2 = player.getPosYEye() + finalPosition2.y;
/*  95 */     double posZ2 = player.getPosZ() + finalPosition2.z;
/*     */     
/*  97 */     double posX3 = player.getPosX() + finalPosition3.x;
/*  98 */     double posY3 = player.getPosYEye() + finalPosition3.y;
/*  99 */     double posZ3 = player.getPosZ() + finalPosition3.z;
/*     */     
/* 101 */     WyHelper.spawnParticles(data, (ServerWorld)player.world, posX, posY, posZ);
/* 102 */     WyHelper.spawnParticles(data, (ServerWorld)player.world, posX2, posY2, posZ2);
/* 103 */     WyHelper.spawnParticles(data, (ServerWorld)player.world, posX3, posY3, posZ3);
/*     */     
/* 105 */     if (!player.onGround && (time == 1 || time == 4 || time == 8 || time == 10)) {
/*     */       
/* 107 */       player.setMotion(player.getLookVec().mul(new Vec3d(5.0D * time / 10.0D, 5.0D, 5.0D * time / 10.0D)));
/* 108 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/* 109 */       player.fallDistance = 0.0F;
/*     */     } 
/*     */     
/* 112 */     if (time < 20) {
/*     */       
/* 114 */       List<LivingEntity> exList = WyHelper.getEntitiesNear(player.getPosition(), player.world, 4.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class );
/*     */       
/* 116 */       exList.remove(player);
/* 117 */       exList.removeIf(o -> this.list.contains(o));
/* 118 */       this.list.addAll(exList);
/*     */       
/* 120 */       if (time == 1)
/* 121 */         for (LivingEntity target : this.list)
/* 122 */           target.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this), 25.0F);  
/*     */     } 
/* 124 */     if (time > 20) {
/*     */       
/* 126 */       player.rotationPitch = this.rotationPitch;
/* 127 */       player.rotationYaw = this.rotationYaw;
/* 128 */       player.setPositionAndUpdate(this.pos.getX(), this.pos.getY(), this.pos.getZ());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 134 */     this.list.clear();
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Vec3d getPerpendicularHorizontalLine(Vec3d startPoint, Vec3d endPoint, float radius) {
/* 140 */     Vec3d axis = new Vec3d(1.0D, 0.0D, 1.0D);
/* 141 */     Vec3d newStart = startPoint.mul(axis);
/* 142 */     Vec3d newEnd = endPoint.mul(axis);
/* 143 */     Vec3d line = newEnd.subtract(newStart);
/* 144 */     line.add((line.getX() == 0.0D) ? 0.001D : 0.0D, 0.0D, (line.getZ() == 0.0D) ? 0.001D : 0.0D);
/*     */     
/* 146 */     Vec3d newLine = new Vec3d(line.getZ(), 0.0D, -line.getX());
/*     */     
/* 148 */     return newLine.normalize().mul(radius, radius, radius);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Vec3d getPerpendicularVerticalLine(Vec3d startPoint, Vec3d endPoint, Vec3d horizontalPoint, float radius) {
/* 154 */     Vec3d vec1 = endPoint.subtract(startPoint);
/* 155 */     Vec3d vec2 = horizontalPoint.subtract(startPoint);
/* 156 */     Vec3d newLine = vec1.crossProduct(vec2);
/*     */     
/* 158 */     return newLine.normalize().mul(radius, radius, radius);
/*     */   }
/*     */ 
/*     */   
/*     */   private static double fromRangeToRange(double oldMin, double oldMax, double min, double max, double oldValue) {
/* 163 */     return (oldValue - oldMin) * (max - min) / (oldMax - oldMin) + min;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Vec3d getParticlePositionInSpiral(float rotation, Vec3d normalizedH, Vec3d normalizedV) {
/* 168 */     Vec3d incrementH = normalizedH.mul(Math.cos(rotation), Math.cos(rotation), Math.cos(rotation));
/* 169 */     Vec3d incrementV = normalizedV.mul(Math.sin(rotation), Math.sin(rotation), Math.sin(rotation));
/*     */     
/* 171 */     Vec3d HVVec = incrementV.subtract(incrementH);
/*     */     
/* 173 */     return incrementV.add(HVVec.mul(0.5D, 0.5D, 0.5D));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 179 */     return new ZoanInfo[] { (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE };
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\BlueBirdAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */