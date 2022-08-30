/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.brawler.KingPunchProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoRocketProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class GomuGomuNoRocketAbility extends PunchTriggerAbility {
/*  23 */   public static final GomuGomuNoRocketAbility INSTANCE = new GomuGomuNoRocketAbility();
/*     */ 
/*     */   
/*     */   public GomuGomuNoRocketAbility() {
/*  27 */     super("Gomu Gomu no Rocket", AbilityHelper.getDevilFruitCategory());
/*  28 */     setDescription("Stretches towards a block, then launches the user on an arch depending on where they fist landed");
/*  29 */     setMaxCooldown(3.0D);
/*  30 */     setThreshold(0.5D);
/*  31 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  32 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  33 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int i) {
/*  38 */     AbilityHelper.slowEntityFall((LivingEntity)player, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  43 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  45 */     if (GomuHelper.hasGearFourthActive(props)) {
/*     */       
/*  47 */       setThreshold(10.0D);
/*  48 */       setMaxCooldown(30.0D);
/*  49 */       setDisplayName("King Kong Gun");
/*  50 */       setCustomTexture("king_kong_gun");
/*  51 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/*  52 */       return true;
/*     */     } 
/*  54 */     setMaxCooldown(3.0D);
/*  55 */     setThreshold(1.0D);
/*  56 */     setDisplayName("Gomu Gomu no Rocket");
/*  57 */     setCustomTexture("gomu_gomu_no_rocket");
/*  58 */     WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/*     */ 
/*     */     
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/*  66 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  67 */     if (GomuHelper.hasGearFourthActive(props)) {
/*     */       
/*  69 */       if (getContinueTime() < getThreshold())
/*  70 */         return false; 
/*  71 */       KingPunchProjectile kingPunchProjectile = new KingPunchProjectile(player.world, (LivingEntity)player);
/*  72 */       kingPunchProjectile.setMaxLife(25);
/*  73 */       kingPunchProjectile.setDamage(120.0F);
/*  74 */       ((AbilityProjectileEntity)kingPunchProjectile).onBlockImpactEvent = (pos -> {
                    // fixed cannot find symbol
                    AbilityProjectileEntity proj=kingPunchProjectile;
/*     */           ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, proj.getPosX(), proj.getPosY(), proj.getPosZ(), 13.0F);
/*     */           
/*     */           explosion.setStaticDamage(80.0F);
/*     */           explosion.setExplosionSound(false);
/*     */           explosion.setDamageOwner(false);
/*     */           explosion.setDestroyBlocks(true);
/*     */           explosion.setFireAfterExplosion(false);
/*     */           explosion.setDamageEntities(false);
/*     */           explosion.doExplosion();
/*     */         });
/*  85 */       player.world.addEntity((Entity)kingPunchProjectile);
/*  86 */       kingPunchProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.0F);
/*  87 */       return true;
/*     */     } 
/*     */     
/*  90 */     AbilityHelper.slowEntityFall((LivingEntity)player, 10);
/*  91 */     GomuGomuNoRocketProjectile projectile = new GomuGomuNoRocketProjectile(player.world, (LivingEntity)player);
/*  92 */     player.world.addEntity((Entity)projectile);
/*  93 */     projectile.setMotion(0.0D, 0.0D, 0.0D);
/*  94 */     projectile.gear2 = GomuHelper.hasGearSecondActive(props);
/*  95 */     float speed = GomuHelper.hasGearSecondActive(props) ? 4.0F : 3.125F;
/*  96 */     projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.0F);
/*  97 */     player.world.playSound(null, player.getPosition(), ModSounds.GOMU_SFX, SoundCategory.PLAYERS, 0.8F, (float)MathHelper.clamp(player.getRNG().nextDouble() * 2.0D, 1.0D, 1.2999999523162842D));
/*     */     
/*  99 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoRocketAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */