/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import java.lang.invoke.SerializedLambda;

import net.minecraft.client.network.play.IClientPlayNetHandler;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoElephantGunProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetPistolProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoKongProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoPistolProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*     */ 
/*     */ public class GomuGomuNoGatlingAbility extends RepeaterAbility {
/*  23 */   public static final GomuGomuNoGatlingAbility INSTANCE = new GomuGomuNoGatlingAbility();
/*     */   
/*     */   private boolean hasDataSet = false;
/*     */ 
/*     */   
/*     */   public GomuGomuNoGatlingAbility() {
/*  29 */     super("Gomu Gomu no Gatling", AbilityHelper.getDevilFruitCategory());
/*  30 */     setDescription("Rapidly punches enemies using rubber fists");
/*     */     
/*  32 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  33 */     this.onUseEvent = this::onUseEvent;
/*  34 */     this.duringCooldownEvent = this::duringCooldownEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  39 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  41 */     if (GomuHelper.hasGearFourthActive(props)) {
/*     */       
/*  43 */       setMaxCooldown(10.0D);
/*  44 */       setMaxRepeaterCount(8, 5);
/*  45 */       setDisplayName("Gomu Gomu no Kong Gatling");
/*     */     }
/*  47 */     else if (GomuHelper.hasGearThirdActive(props)) {
/*     */       
/*  49 */       setMaxCooldown(14.0D);
/*  50 */       setMaxRepeaterCount(12, 5);
/*  51 */       setDisplayName("Gomu Gomu no Elephant Gatling");
/*     */     }
/*  53 */     else if (GomuHelper.hasGearSecondActive(props)) {
/*     */       
/*  55 */       setMaxCooldown(5.0D);
/*  56 */       setMaxRepeaterCount(25, 2);
/*  57 */       setDisplayName("Gomu Gomu no Jet Gatling");
/*     */     }
/*     */     else {
/*     */       
/*  61 */       setMaxCooldown(7.0D);
/*  62 */       setMaxRepeaterCount(20, 3);
/*  63 */       setDisplayName("Gomu Gomu no Gatling");
/*     */     } 
/*     */     
/*  66 */     return true;
/*     */   }
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  71 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  73 */     AbilityProjectileEntity projectile = null;
/*  74 */     float speed = 3.0F;
/*  75 */     float projDmageReduction = 0.8F;
/*  76 */     int projectileSpace = 2;
/*     */     
/*  78 */     if (GomuHelper.hasGearFourthActive(props)) {
/*     */       
/*  80 */       GomuGomuNoKongProjectile gomuGomuNoKongProjectile = new GomuGomuNoKongProjectile(player.world, (LivingEntity)player);
/*  81 */       gomuGomuNoKongProjectile.setCollisionSize(2.5D);
/*  82 */       if (!this.hasDataSet) {
/*     */         
/*  84 */         speed = 2.2F;
/*  85 */         projectileSpace = 6;
/*  86 */         this.hasDataSet = true;
/*     */       } 
/*  88 */       projDmageReduction = 0.6F;
/*     */       projectile = gomuGomuNoKongProjectile;
/*     */     }
/*  91 */     else if (GomuHelper.hasGearThirdActive(props)) {
/*     */       
/*  93 */       GomuGomuNoElephantGunProjectile gomuGomuNoElephantGunProjectile = new GomuGomuNoElephantGunProjectile(player.world, (LivingEntity)player);
/*  94 */       gomuGomuNoElephantGunProjectile.setCollisionSize(2.5D);
/*  95 */       if (!this.hasDataSet) {
/*     */         
/*  97 */         speed = 2.4F;
/*  98 */         projectileSpace = 9;
/*  99 */         this.hasDataSet = true;
/*     */       } 
/* 101 */       projDmageReduction = 0.6F;
/*     */       projectile = gomuGomuNoElephantGunProjectile;
/*     */     }
/* 103 */     else if (GomuHelper.hasGearSecondActive(props)) {
/*     */       
/* 105 */       GomuGomuNoJetPistolProjectile gomuGomuNoJetPistolProjectile = new GomuGomuNoJetPistolProjectile(player.world, (LivingEntity)player);
/* 106 */       if (!this.hasDataSet)
/*     */       {
/* 108 */         speed = 3.6F;
/* 109 */         this.hasDataSet = true;
/*     */       }
/*     */       projectile = gomuGomuNoJetPistolProjectile;
/*     */     } else {
/*     */       
/* 114 */       GomuGomuNoPistolProjectile gomuGomuNoPistolProjectile = new GomuGomuNoPistolProjectile(player.world, (LivingEntity)player);
/* 115 */       if (!this.hasDataSet)
/*     */       {
/* 117 */         this.hasDataSet = true;
/*     */       }
/*     */       projectile = gomuGomuNoPistolProjectile;
/*     */     } 
/*     */     
/* 121 */     projectile.setDamage(projectile.getDamage() * (1.0F - projDmageReduction));
/* 122 */     projectile.setChangeHurtTime(true);
/* 123 */     projectile.setMaxLife((int)(projectile.getMaxLife() * 0.75D));
/* 124 */     projectile.setLocationAndAngles(player
/* 125 */         .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
/* 126 */         .getPosY() + 1.5D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
/* 127 */         .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
/*     */     
/* 129 */     player.world.addEntity((Entity)projectile);
/* 130 */     projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 3.0F);
/* 131 */     player.world.playSound(null, player.getPosition(), ModSounds.GOMU_SFX, SoundCategory.PLAYERS, 0.8F, (float)MathHelper.clamp(player.getRNG().nextDouble() * 2.0D, 1.0D, 1.2999999523162842D));
/*     */     
/* 133 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket<IClientPlayNetHandler>)new SAnimateHandPacket((Entity)player, 0));
/*     */     
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldownEvent(PlayerEntity player, int cooldown) {
/* 140 */     if (cooldown <= 1)
/*     */     {
/* 142 */       this.hasDataSet = false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoGatlingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */