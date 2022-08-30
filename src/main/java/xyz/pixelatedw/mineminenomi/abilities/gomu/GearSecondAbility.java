/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import java.awt.Color;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class GearSecondAbility extends ContinuousAbility implements IBodyOverlayAbility, IParallelContinuousAbility {
/*  31 */   public static final GearSecondAbility INSTANCE = new GearSecondAbility();
/*     */   
/*  33 */   private static final AbilityAttributeModifier JUMP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("a44a9644-369a-4e18-88d9-323727d3d85b"), (Ability)INSTANCE, "Gear Second Jump Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  34 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("a2337b58-7e6d-4361-a8ca-943feee4f906"), (Ability)INSTANCE, "Gear Second Attack Damage Modifier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  35 */   private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("eab680cd-a6dc-438a-99d8-46f9eb53a950"), (Ability)INSTANCE, "Gear Second Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   
/*  37 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new GearSecondParticleEffect();
/*     */   
/*     */   private boolean prevSprintValue = false;
/*     */   
/*     */   public GearSecondAbility() {
/*  42 */     super("Gear Second", AbilityHelper.getDevilFruitCategory());
/*  43 */     setThreshold(50.0D);
/*  44 */     setDescription("By speding up their blood flow, the user gains strength, speed and mobility");
/*     */     
/*  46 */     this.onStartContinuityEvent = this::onStartContinuity;
/*  47 */     this.duringContinuityEvent = this::duringContinuity;
/*  48 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuity(PlayerEntity player) {
/*  53 */     if (!GomuHelper.canActivateGear(AbilityDataCapability.get((LivingEntity)player), (Ability)INSTANCE)) {
/*     */       
/*  55 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_GEAR_ACTIVE, new Object[0]));
/*  56 */       return false;
/*     */     } 
/*     */     
/*  59 */     player.getAttribute(ModAttributes.STEP_HEIGHT).applyModifier((AttributeModifier)STEP_HEIGHT);
/*  60 */     player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier((AttributeModifier)JUMP_HEIGHT);
/*  61 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)STRENGTH_MODIFIER);
/*  62 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)STRENGTH_MODIFIER);
/*  63 */     player.world.playSound(null, player.getPosition(), ModSounds.GEAR_SECOND_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*  64 */     this.prevSprintValue = player.isSprinting();
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/*  70 */     if (passiveTimer % 10 == 0) {
/*  71 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  73 */     if (player.isInWater() || !AbilityHelper.canUseMomentumAbility(player)) {
/*  74 */       endContinuity(player);
/*     */     }
/*  76 */     if (player.isSprinting()) {
/*     */       
/*  78 */       if (!this.prevSprintValue) {
/*  79 */         player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */       }
/*     */     } else {
/*  82 */       this.prevSprintValue = false;
/*     */       
/*     */       return;
/*     */     } 
/*  86 */     float maxSpeed = 2.2F;
/*     */     
/*  88 */     Vec3d vec = player.getLookVec();
/*  89 */     if (player.onGround) {
/*     */       
/*  91 */       player.setMotion(vec.x * maxSpeed, (player.getMotion()).y, vec.z * maxSpeed);
/*     */     } else {
/*     */       
/*  94 */       player.setMotion(vec.x * maxSpeed * 0.5D, 
/*  95 */           (player.getMotion()).y, vec.z * maxSpeed * 0.5D);
/*     */     } 
/*     */ 
/*     */     
/*  99 */     this.prevSprintValue = player.isSprinting();
/* 100 */     player.velocityChanged = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 105 */     int cooldown = (int)Math.round(this.continueTime / 30.0D);
/* 106 */     setMaxCooldown(cooldown);
/* 107 */     if (this.continueTime > getThreshold() / 1.425D && EntityStatsCapability.get((LivingEntity)player).getDoriki() < 2000) {
/*     */       
/* 109 */       player.addPotionEffect(new EffectInstance(Effects.HUNGER, 600, 3, true, true));
/* 110 */       player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1, true, true));
/*     */     } 
/*     */     
/* 113 */     player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier((AttributeModifier)STEP_HEIGHT);
/* 114 */     player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier((AttributeModifier)JUMP_HEIGHT);
/* 115 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)STRENGTH_MODIFIER);
/* 116 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)STRENGTH_MODIFIER);
/* 117 */     return true;
/*     */   }
/*     */   
/* 120 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setColor(new Color(232, 54, 54, 74));
/*     */ 
/*     */   
/*     */   public AbilityOverlay getBodyOverlay() {
/* 124 */     return OVERLAY;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GearSecondAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */