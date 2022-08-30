/*     */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IChangeDamageSourceAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ 
/*     */ public class LightAccelerationAbility extends ContinuousAbility implements IChangeDamageSourceAbility, IPunchOverlayAbility, IParallelContinuousAbility {
/*  27 */   public static final Ability INSTANCE = (Ability)new LightAccelerationAbility();
/*     */   
/*  29 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setRenderType(AbilityOverlay.RenderType.ENERGY).setColor(new Color(255, 220, 0));
/*  30 */   public static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), INSTANCE, "Light Acceleration Attack Speed Multiplier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */ 
/*     */   
/*     */   public LightAccelerationAbility() {
/*  34 */     super("Light Acceleration", AbilityHelper.getDevilFruitCategory());
/*  35 */     setThreshold(20.0D);
/*  36 */     setMaxCooldown(10.0D);
/*  37 */     setDescription("The user accelerates its attacks by converting into light before impact");
/*     */     
/*  39 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  40 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  41 */     this.onEndContinuityEvent = this::onEndContinuity;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  46 */     if (!player.getHeldItemMainhand().isEmpty()) {
/*     */       
/*  48 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
/*  49 */       return false;
/*     */     } 
/*     */     
/*  52 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
/*     */     
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int i) {
/*  59 */     if (!player.getHeldItemMainhand().isEmpty()) {
/*  60 */       endContinuity(player);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean onEndContinuity(PlayerEntity player) {
/*  65 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)ATTACK_SPEED_MODIFIER);
/*  66 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float damageToEntityWithSource(PlayerEntity player, LivingEntity target) {
/*  72 */     float strength = Math.min((float)player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue(), 50.0F);
/*     */     
/*  74 */     if (target.hurtResistantTime > 0 && target.hurtResistantTime <= 5) {
/*  75 */       target.hurtTime = target.hurtResistantTime = 0;
/*     */     }
/*  77 */     float punchMultiplier = 1.0F + 5.0F * strength / 60.0F;
/*     */ 
/*     */     
/*  80 */     Vec3d speed = player.getLook(1.0F).mul(punchMultiplier, (punchMultiplier * 0.75F), punchMultiplier);
/*  81 */     target.setMotion(speed.x, speed.y, speed.z);
/*  82 */     target.velocityChanged = true;
/*  83 */     target.fallDistance = 0.0F;
/*     */     
/*  85 */     return 10.0F + strength;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DamageSource getSourceToUse(PlayerEntity player) {
/*  91 */     return (DamageSource)new AbilityDamageSource("player", null, (Ability)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cancelsOriginalDamage() {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSourceChangeEnabled() {
/* 103 */     return isContinuous();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbilityOverlay getPunchOverlay(LivingEntity entity) {
/* 109 */     return entity.isSwingInProgress ? OVERLAY : (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(255, 255, 255, 0));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\LightAccelerationAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */