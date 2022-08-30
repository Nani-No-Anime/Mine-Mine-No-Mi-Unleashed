/*     */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.DiableJambeParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class DiableJambeAbility extends ContinuousAbility implements IParallelContinuousAbility, IPunchOverlayAbility {
/*  26 */   public static final DiableJambeAbility INSTANCE = new DiableJambeAbility();
/*     */   
/*  28 */   private static final DiableJambeParticleEffect PARTICLES = new DiableJambeParticleEffect();
/*  29 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.HOT_BOILING_SPECIAL_ARM).setColor("#FFBB44AA");
/*  30 */   private static final AbilityAttributeModifier DIABLE_JAMBE_STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("e3ae074c-40a9-49ff-aa3b-7cc9b98ddc2d"), (Ability)INSTANCE, "Diable Jambe Attack Multiplier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  31 */   private static final AbilityAttributeModifier DIABLE_JAMBE_ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("a984413a-7459-4989-8362-7c46a2663f0e"), (Ability)INSTANCE, "Diable Jambe Speed Multiplier", 0.4000000059604645D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   
/*     */   private boolean frozen = false;
/*     */ 
/*     */   
/*     */   public DiableJambeAbility() {
/*  37 */     super("Diable Jambe", AbilityHelper.getStyleCategory());
/*  38 */     setThreshold(40.0D);
/*  39 */     setDescription("The user heats up their leg, dealing additional damage and shortly setting the target on fire (Enhances all Blackleg attacks)");
/*     */     
/*  41 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  42 */     this.duringContinuityEvent = this::duringContinuity;
/*  43 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  48 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)DIABLE_JAMBE_STRENGTH_MODIFIER);
/*  49 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)DIABLE_JAMBE_STRENGTH_MODIFIER);
/*     */     
/*  51 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)DIABLE_JAMBE_ATTACK_SPEED_MODIFIER);
/*  52 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)DIABLE_JAMBE_ATTACK_SPEED_MODIFIER);
/*     */     
/*  54 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  56 */     ExtraHachisAbility extraHachiAbl = (ExtraHachisAbility)abilityProps.getEquippedAbility((Ability)ExtraHachisAbility.INSTANCE);
/*  57 */     if (extraHachiAbl != null) {
/*     */       
/*  59 */       extraHachiAbl.enableDiableJambeMode();
/*  60 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)extraHachiAbl), (LivingEntity)player);
/*     */     } 
/*  62 */     this.frozen = false;
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuity(PlayerEntity player, int timer) {
/*  68 */     if (player.getActivePotionEffect(ModEffects.FROZEN) != null) {
/*     */       
/*  70 */       this.frozen = true;
/*  71 */       AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.FROZEN), 1.100000023841858D);
/*  72 */       endContinuity(player);
/*     */     } 
/*     */     
/*  75 */     if (player.getActivePotionEffect(ModEffects.FROSTBITE) != null) {
/*     */       
/*  77 */       this.frozen = true;
/*  78 */       AbilityHelper.reduceEffect(player.getActivePotionEffect(ModEffects.FROSTBITE), 1.5D);
/*  79 */       endContinuity(player);
/*     */     } 
/*     */     
/*  82 */     PARTICLES.spawn(player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/*  87 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)DIABLE_JAMBE_STRENGTH_MODIFIER);
/*  88 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)DIABLE_JAMBE_ATTACK_SPEED_MODIFIER);
/*  89 */     int cooldown = (this.frozen ? 20 : 4) + (int)WyHelper.clamp(Math.round(this.continueTime / 40.0D), 0L, 200L);
/*  90 */     setMaxCooldown(cooldown);
/*     */     
/*  92 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  94 */     ExtraHachisAbility extraHachiAbl = (ExtraHachisAbility)abilityProps.getEquippedAbility((Ability)ExtraHachisAbility.INSTANCE);
/*  95 */     if (extraHachiAbl != null) {
/*     */       
/*  97 */       extraHachiAbl.disableDiableJambeMode();
/*  98 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)extraHachiAbl), (LivingEntity)player);
/*     */     } 
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbilityOverlay getPunchOverlay(LivingEntity entity) {
/* 107 */     return OVERLAY;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\DiableJambeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */