/*     */ package xyz.pixelatedw.mineminenomi.abilities.swordsman;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModEntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.swordsman.HiryuKaenParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class HiryuKaenAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility {
/*  23 */   public static final HiryuKaenAbility INSTANCE = new HiryuKaenAbility();
/*  24 */   public static final HiryuKaenParticleEffect PARTICLES = new HiryuKaenParticleEffect();
/*     */   
/*     */   private boolean wasActivated = false;
/*     */   
/*     */   private boolean canHit = false;
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   public HiryuKaenAbility() {
/*  32 */     super("Hiryu: Kaen", AbilityHelper.getStyleCategory());
/*  33 */     setDescription("The user leaps into the air and releases a big flaming shockwave slash when landing");
/*  34 */     setMaxCooldown(15.0D);
/*     */     
/*  36 */     this.onUseEvent = this::onUseEvent;
/*  37 */     this.duringCooldownEvent = this::duringCooldown;
/*  38 */     this.onEndCooldownEvent = this::onEndCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEndCooldown(PlayerEntity player) {
/*  43 */     this.wasActivated = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldown(PlayerEntity player, int cooldown) {
/*  48 */     if (cooldown < getMaxCooldown() && !this.canHit && !player.onGround && !this.wasActivated) {
/*     */       
/*  50 */       this.canHit = true;
/*  51 */       this.wasActivated = true;
/*     */     } 
/*     */     
/*  54 */     if (player.onGround && this.canHit) {
/*     */       
/*  56 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 4.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  57 */       targets.remove(player);
/*     */       
/*  59 */       for (LivingEntity entity : targets) {
/*     */         
/*  61 */         if (isTarget(entity) && player.canEntityBeSeen((Entity)entity)) {
/*     */           
/*  63 */           entity.attackEntityFrom((DamageSource)(new ModEntityDamageSource("player", (Entity)player)).markDamageAsSlash(), 18.0F);
/*  64 */           entity.setFire(4);
/*     */         } 
/*     */       } 
/*     */       
/*  68 */       if (targets.size() > 0) {
/*  69 */         ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*     */       }
/*  71 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*  72 */       this.canHit = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  78 */     if (!AbilityHelper.canUseSwordsmanAbilities((LivingEntity)player)) {
/*     */       
/*  80 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
/*  81 */       return false;
/*     */     } 
/*     */     
/*  84 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  85 */       return false;
/*     */     }
/*  87 */     clearTargets();
/*     */     
/*  89 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.0D, 1.0D);
/*  90 */     player.setMotion(speed.x, 1.3D, speed.z);
/*  91 */     player.velocityChanged = true;
/*  92 */     this.canHit = false;
/*  93 */     this.hasFallDamage = false;
/*     */     
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/* 101 */     this.hasFallDamage = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/* 107 */     return this.hasFallDamage;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\swordsman\HiryuKaenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */