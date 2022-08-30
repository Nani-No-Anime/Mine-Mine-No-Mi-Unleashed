/*     */ package xyz.pixelatedw.mineminenomi.abilities.beta;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.LogiaParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class BetaCoatingAbility extends DamagedContinuousAbility implements IParallelContinuousAbility, IBodyOverlayAbility {
/*  30 */   public static final BetaCoatingAbility INSTANCE = new BetaCoatingAbility();
/*  31 */   private final ArrayList<DamageSource> explosiveSources = new ArrayList<>(Arrays.asList(new DamageSource[] { DamageSource.IN_FIRE, DamageSource.LIGHTNING_BOLT, DamageSource.ON_FIRE, DamageSource.LAVA }));
/*  32 */   private static final AbilityAttributeModifier SPEED_MULTIPLIER = (new AbilityAttributeModifier(UUID.fromString("efa08cbd-57e5-478f-b15c-6295eb1b375e"), (Ability)INSTANCE, "Beta Speed Modifier", -0.25D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
/*  33 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.BETA_COATING).setColor(WyHelper.hexToRGB("#FFFFFFA6"));
/*     */   boolean exploded; private boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource, double damage) { for (DamageSource s : this.explosiveSources) { if (damageSource.getDamageType().equals(s.getDamageType())) { disableAbilityAndExplode(entity); return true; }  }
/*     */      if (damageSource.getImmediateSource() instanceof LivingEntity && damageSource.getImmediateSource().isBurning())
/*     */       return true;  if (!damageSource.isExplosion() && !damageSource.isFireDamage() && !damageSource.isDamageAbsolute()) { LogiaParticleEffect logiaParticleEffect = new LogiaParticleEffect(ModParticleTypes.BETA); logiaParticleEffect.spawn(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0.0D, 0.0D, 0.0D); return false; }
/*  37 */      disableAbilityAndExplode(entity); return true; } public BetaCoatingAbility() { super("Beta Coating", AbilityHelper.getDevilFruitCategory());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     this.exploded = false; setDescription("Covers the user in a thick mucus coat, which makes them immune to almost all attacks, but extremely vulnerable to fire"); setMaxCooldown(10.0D); this.onDamagedEvent = this::onDamagedEvent; this.onStartContinuityEvent = this::onStartContinuityEvent; this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */     this.duringContinuityEvent = this::duringContinuity; }
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) { this.exploded = false;
/*     */     player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier((AttributeModifier)SPEED_MULTIPLIER);
/*  98 */     return true; } private void disableAbilityAndExplode(LivingEntity entity) { if (!this.exploded) {
/*     */       
/* 100 */       if (entity instanceof PlayerEntity) {
/* 101 */         endContinuity((PlayerEntity)entity);
/*     */       }
/* 103 */       this.exploded = true;
/* 104 */       entity.extinguish();
/* 105 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 6.0F);
/* 106 */       explosion.setExplosionSound(true);
/* 107 */       explosion.setDamageOwner(true);
/* 108 */       explosion.setDestroyBlocks(true);
/* 109 */       explosion.setFireAfterExplosion(true);
/* 110 */       explosion.setStaticDamage(100.0F);
/* 111 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
/* 112 */       explosion.setDamageEntities(true);
/* 113 */       explosion.doExplosion();
/*     */     }  } private void duringContinuity(PlayerEntity player, int i) { DiableJambeAbility ability = (DiableJambeAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
/*     */     if (player.isBurning() || (ability != null && ability.isContinuous()))
/*     */       disableAbilityAndExplode((LivingEntity)player);  }
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) { player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier((AttributeModifier)SPEED_MULTIPLIER);
/*     */     return true; }
/*     */   public AbilityOverlay getBodyOverlay() {
/* 120 */     return OVERLAY;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\beta\BetaCoatingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */