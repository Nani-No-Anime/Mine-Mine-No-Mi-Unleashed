/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.GenericUseLightningEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class EleclawAbility extends PunchAbility implements IParallelContinuousAbility {
/*  22 */   public static final EleclawAbility INSTANCE = new EleclawAbility();
/*     */   
/*  24 */   private static final GenericUseLightningEffect PARTICLES = new GenericUseLightningEffect();
/*  25 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Eleclaw Attack Speed Modifier", 0.3499999940395355D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   
/*     */   private static final int MAX_USES = 9;
/*  28 */   public int uses = 9;
/*     */ 
/*     */   
/*     */   public EleclawAbility() {
/*  32 */     super("Eleclaw", AbilityHelper.getRacialCategory());
/*  33 */     setMaxCooldown(10.0D);
/*  34 */     setDescription("The user coats their hands and weapons with lightning, enabling the use of other electric skills and giving the chance to stun foes");
/*  35 */     setStoppingAfterHit(false);
/*     */     
/*  37 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  38 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  39 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*  40 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*  41 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/*  46 */     if (player.getRNG().nextInt(10) <= 2) {
/*     */       
/*  48 */       target.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 20, 0, false, false, true));
/*  49 */       PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/*  55 */     return 3.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  60 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
/*  61 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int i) {
/*  67 */     if (this.uses <= 0) {
/*     */       
/*  69 */       endContinuity(player);
/*  70 */       this.uses = 9;
/*     */     } 
/*     */     
/*  73 */     if (player.areEyesInFluid(FluidTags.WATER, true)) {
/*     */       
/*  75 */       player.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.getSource(), 4.0F);
/*  76 */       endContinuity(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/*  82 */     this.uses = 9;
/*     */     
/*  84 */     int extraCooldown = Math.min(25, (int)Math.round(this.continueTime / 20.0D));
/*  85 */     setMaxCooldown((5 + extraCooldown));
/*  86 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
/*  87 */     return true;
/*     */   }
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
/*     */   public void reduceUsage(PlayerEntity player, int number) {
/* 126 */     SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
/* 127 */     boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
/* 128 */     if (!sulongEnabled)
/* 129 */       this.uses -= number; 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\EleclawAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */