/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoBazookaProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoGrizzlyMagnumProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetBazookaProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoLeoBazookaProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.gomu.GomuBazookaAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class GomuGomuNoBazookaAbility extends ChargeableAbility implements IAnimatedAbility {
/*  25 */   public static final GomuGomuNoBazookaAbility INSTANCE = new GomuGomuNoBazookaAbility();
/*     */ 
/*     */   
/*     */   public GomuGomuNoBazookaAbility() {
/*  29 */     super("Gomu Gomu no Bazooka", AbilityHelper.getDevilFruitCategory());
/*  30 */     setDescription("Hits the enemy with both hands to launch them away");
/*  31 */     setMaxCooldown(10.0D);
/*  32 */     setMaxChargeTime(2.0D);
/*  33 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*     */     
/*  38 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  39 */     AbilityProjectileEntity projectile1 = null;
/*  40 */     AbilityProjectileEntity projectile2 = null;
/*  41 */     float speed = 2.0F;
/*  42 */     double spacingMod = 1.0D;
/*     */     
/*  44 */     if (GomuHelper.hasGearFourthActive(props)) {
/*     */       
/*  46 */       GomuGomuNoLeoBazookaProjectile gomuGomuNoLeoBazookaProjectile1 = new GomuGomuNoLeoBazookaProjectile(player.world, (LivingEntity)player);
/*  47 */       gomuGomuNoLeoBazookaProjectile1.setCollisionSize(2.5D);
/*  48 */       GomuGomuNoLeoBazookaProjectile gomuGomuNoLeoBazookaProjectile2 = new GomuGomuNoLeoBazookaProjectile(player.world, (LivingEntity)player);
/*  49 */       gomuGomuNoLeoBazookaProjectile2.setCollisionSize(2.5D);
/*  50 */       setMaxCooldown(12.0D);
/*  51 */       setDisplayName("Gomu Gomu no Leo Bazooka");
/*  52 */       speed = 3.0F;
/*  53 */       spacingMod = 2.5D;
                projectile1 = gomuGomuNoLeoBazookaProjectile1;
                projectile2 = gomuGomuNoLeoBazookaProjectile2;
/*     */     }
/*  55 */     else if (GomuHelper.hasGearThirdActive(props)) {
/*     */       
/*  57 */       GomuGomuNoGrizzlyMagnumProjectile gomuGomuNoGrizzlyMagnumProjectile1 = new GomuGomuNoGrizzlyMagnumProjectile(player.world, (LivingEntity)player);
/*  58 */       gomuGomuNoGrizzlyMagnumProjectile1.setCollisionSize(2.5D);
/*  59 */       GomuGomuNoGrizzlyMagnumProjectile gomuGomuNoGrizzlyMagnumProjectile2 = new GomuGomuNoGrizzlyMagnumProjectile(player.world, (LivingEntity)player);
/*  60 */       gomuGomuNoGrizzlyMagnumProjectile2.setCollisionSize(2.5D);
/*  61 */       setMaxCooldown(15.0D);
/*  62 */       setDisplayName("Gomu Gomu no Grizzly Magnum");
/*  63 */       speed = 1.8F;
/*  64 */       spacingMod = 2.5D;
                projectile1 = gomuGomuNoGrizzlyMagnumProjectile1;
                projectile2 = gomuGomuNoGrizzlyMagnumProjectile2;
/*     */     }
/*  66 */     else if (GomuHelper.hasGearSecondActive(props)) {
/*     */       
/*  68 */       GomuGomuNoJetBazookaProjectile gomuGomuNoJetBazookaProjectile1 = new GomuGomuNoJetBazookaProjectile(player.world, (LivingEntity)player);
/*  69 */       GomuGomuNoJetBazookaProjectile gomuGomuNoJetBazookaProjectile2 = new GomuGomuNoJetBazookaProjectile(player.world, (LivingEntity)player);
/*  70 */       setMaxCooldown(7.0D);
/*  71 */       setDisplayName("Gomu Gomu no Jet Bazooka");
/*  72 */       speed = 3.0F;
                projectile1 = gomuGomuNoJetBazookaProjectile1;
                projectile2 = gomuGomuNoJetBazookaProjectile2;
/*     */     }
/*     */     else {
/*     */        
/*  76 */       GomuGomuNoBazookaProjectile gomuGomuNoBazookaProjectile1 = new GomuGomuNoBazookaProjectile(player.world, (LivingEntity)player);
/*  77 */       GomuGomuNoBazookaProjectile gomuGomuNoBazookaProjectile2 = new GomuGomuNoBazookaProjectile(player.world, (LivingEntity)player);
/*  78 */       setDisplayName("Gomu Gomu no Bazooka");
/*  79 */       setMaxCooldown(10.0D);
                projectile1 = gomuGomuNoBazookaProjectile1;
                projectile2 = gomuGomuNoBazookaProjectile2;
/*     */     } 
/*     */     
/*  82 */     Vec3d dirVec = Vec3d.ZERO;
/*  83 */     Direction dir = Direction.fromAngle(player.rotationYaw);
/*  84 */     dirVec = dirVec.add(Math.abs(dir.getDirectionVec().getX()), Math.abs(dir.getDirectionVec().getY()), Math.abs(dir.getDirectionVec().getZ())).mul(spacingMod, 1.0D, spacingMod);
/*     */     
/*  86 */     double yPos = player.getPosY() + player.getEyeHeight() - 0.7D;
/*  87 */     projectile1.setLocationAndAngles(player.getPosX() + dirVec.z, yPos, player.getPosZ() + dirVec.x, 0.0F, 0.0F);
/*  88 */     projectile2.setLocationAndAngles(player.getPosX() - dirVec.z, yPos, player.getPosZ() - dirVec.x, 0.0F, 0.0F);
/*     */     
/*  90 */     player.world.addEntity((Entity)projectile1);
/*  91 */     player.world.addEntity((Entity)projectile2);
/*  92 */     projectile1.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.0F);
/*  93 */     projectile2.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.0F);
/*  94 */     player.world.playSound(null, player.getPosition(), ModSounds.GOMU_SFX, SoundCategory.PLAYERS, 0.8F, (float)MathHelper.clamp(player.getRNG().nextDouble() * 2.0D, 1.0D, 1.2999999523162842D));
/*     */     
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 102 */     return (IAnimation)GomuBazookaAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 108 */     return isCharging();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoBazookaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */