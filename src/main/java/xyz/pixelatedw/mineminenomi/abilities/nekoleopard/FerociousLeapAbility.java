/*     */ package xyz.pixelatedw.mineminenomi.abilities.nekoleopard;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.LeopardHeavyZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.LeopardWalkZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class FerociousLeapAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility, IFormRequiredAbility {
/*  23 */   public static final FerociousLeapAbility INSTANCE = new FerociousLeapAbility();
/*     */   
/*     */   private boolean wasActivated = false;
/*     */   
/*     */   private boolean canHit = false;
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   public FerociousLeapAbility() {
/*  31 */     super("Ferocious Leap", AbilityHelper.getDevilFruitCategory());
/*  32 */     setDescription("Leaps at the enemy and damages all nearby entities on landing");
/*  33 */     setMaxCooldown(8.0D);
/*     */     
/*  35 */     this.onUseEvent = this::onUseEvent;
/*  36 */     this.duringCooldownEvent = this::duringCooldown;
/*  37 */     this.onEndCooldownEvent = this::onEndCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEndCooldown(PlayerEntity player) {
/*  42 */     this.wasActivated = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldown(PlayerEntity player, int cooldown) {
/*  47 */     if (cooldown < getMaxCooldown() && !this.canHit && !player.onGround && !this.wasActivated) {
/*     */       
/*  49 */       this.canHit = true;
/*  50 */       this.wasActivated = true;
/*     */     } 
/*     */     
/*  53 */     if (player.onGround && this.canHit) {
/*     */       
/*  55 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 3.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  56 */       targets.remove(player);
/*     */       
/*  58 */       for (LivingEntity entity : targets) {
/*     */         
/*  60 */         if (isTarget(entity) && player.canEntityBeSeen((Entity)entity))
/*     */         {
/*  62 */           entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
/*     */         }
/*     */       } 
/*     */       
/*  66 */       this.canHit = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  72 */     if (!AbilityHelper.canUseMomentumAbility(player) || player
/*  73 */       .getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || !player.onGround)
/*     */     {
/*  75 */       return false;
/*     */     }
/*  77 */     clearTargets();
/*     */     
/*  79 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.0D, 1.0D);
/*  80 */     player.setMotion(speed.x, 1.4D, speed.z);
/*  81 */     player.velocityChanged = true;
/*  82 */     this.canHit = false;
/*  83 */     this.hasFallDamage = false;
/*     */     
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/*  91 */     this.hasFallDamage = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/*  97 */     return this.hasFallDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 103 */     return new ZoanInfo[] { (ZoanInfo)LeopardWalkZoanInfo.INSTANCE, (ZoanInfo)LeopardHeavyZoanInfo.INSTANCE };
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\nekoleopard\FerociousLeapAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */