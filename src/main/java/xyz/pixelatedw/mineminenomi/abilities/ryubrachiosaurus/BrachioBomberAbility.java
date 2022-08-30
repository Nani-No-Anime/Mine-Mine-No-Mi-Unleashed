/*     */ package xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.BrachiosaurusGuardZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.BrachiosaurusHeavyZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class BrachioBomberAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility, IFormRequiredAbility {
/*  21 */   public static final BrachioBomberAbility INSTANCE = new BrachioBomberAbility();
/*  22 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GreatStompParticleEffect();
/*     */   
/*     */   private boolean wasActivated = false;
/*     */   
/*     */   private boolean canHit = false;
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   public BrachioBomberAbility() {
/*  30 */     super("Brachio Bomber", AbilityHelper.getDevilFruitCategory());
/*  31 */     setDescription("Dives from a high place and lands on his opponent, crushing them under the user's weight");
/*  32 */     setMaxCooldown(15.0D);
/*     */     
/*  34 */     this.onUseEvent = this::onUseEvent;
/*  35 */     this.duringCooldownEvent = this::duringCooldown;
/*  36 */     this.onEndCooldownEvent = this::onEndCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEndCooldown(PlayerEntity player) {
/*  41 */     this.wasActivated = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldown(PlayerEntity player, int cooldown) {
/*  46 */     if (cooldown < getMaxCooldown() && !this.canHit && !player.onGround && !this.wasActivated) {
/*     */       
/*  48 */       this.canHit = true;
/*  49 */       this.wasActivated = true;
/*     */     } 
/*     */     
/*  52 */     if (player.onGround && this.canHit) {
/*     */       
/*  54 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */       
/*  56 */       int size = 7;
/*  57 */       if (BrachiosaurusGuardZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/*  58 */         size = 15;
/*     */       }
/*  60 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), size);
/*  61 */       explosion.setDestroyBlocks(true);
/*  62 */       explosion.setStaticDamage((size * 2));
/*  63 */       explosion.doExplosion();
/*     */       
/*  65 */       this.canHit = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  71 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  72 */       return false;
/*     */     }
/*  74 */     clearTargets();
/*     */     
/*  76 */     double jump = 1.3D;
/*  77 */     if (BrachiosaurusGuardZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/*  78 */       jump = 1.6D;
/*     */     }
/*  80 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.0D, 1.0D);
/*  81 */     player.setMotion(speed.x, jump, speed.z);
/*  82 */     player.velocityChanged = true;
/*  83 */     this.canHit = false;
/*  84 */     this.hasFallDamage = false;
/*     */     
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/*  92 */     this.hasFallDamage = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/*  98 */     return this.hasFallDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 104 */     return new ZoanInfo[] { (ZoanInfo)BrachiosaurusGuardZoanInfo.INSTANCE, (ZoanInfo)BrachiosaurusHeavyZoanInfo.INSTANCE };
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryubrachiosaurus\BrachioBomberAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */