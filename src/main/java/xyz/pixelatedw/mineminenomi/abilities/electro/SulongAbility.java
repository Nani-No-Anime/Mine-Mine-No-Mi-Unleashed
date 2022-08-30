/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.dimension.DimensionType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ public class SulongAbility extends ContinuousAbility implements IParallelContinuousAbility {
/*  28 */   public static final SulongAbility INSTANCE = new SulongAbility();
/*     */   
/*  30 */   private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("e158d542-5644-4921-9d5f-895f0b0a164c"), (Ability)INSTANCE, "Sulong Speed Modifier", 1.5D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/*  31 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), (Ability)INSTANCE, "Sulong Damage Modifier", 7.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  32 */   private static final AbilityAttributeModifier JUMP_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), (Ability)INSTANCE, "Sulong Damage Modifier", 8.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  33 */   private static final AbilityAttributeModifier FALL_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), (Ability)INSTANCE, "Sulong Damage Modifier", 8.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  34 */   private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("eab680cd-a6dc-438a-99d8-46f9eb53a950"), (Ability)INSTANCE, "Sulong Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */ 
/*     */   
/*     */   public SulongAbility() {
/*  38 */     super("Sulong Form", AbilityHelper.getRacialCategory());
/*  39 */     setThreshold(300.0D);
/*  40 */     setDescription("The user reveals their true power during the full moon, enhancing their physical and electrical power");
/*     */     
/*  42 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  43 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  44 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int i) {
/*  49 */     boolean sulongCheck = canTransform(player.world);
/*  50 */     if (!sulongCheck) {
/*  51 */       endContinuity(player);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  56 */     boolean sulongCheck = (canTransform(player.world) && player.world.canSeeSky(player.getPosition().add(0.0D, 1.2D, 0.0D)));
/*     */     
/*  58 */     if (sulongCheck) {
/*     */       
/*  60 */       player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier((AttributeModifier)SPEED_MODIFIER);
/*  61 */       player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier((AttributeModifier)SPEED_MODIFIER);
/*  62 */       player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)STRENGTH_MODIFIER);
/*  63 */       player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier((AttributeModifier)JUMP_MODIFIER);
/*  64 */       player.getAttribute(ModAttributes.FALL_RESISTANCE).applyModifier((AttributeModifier)FALL_RESISTANCE);
/*  65 */       player.getAttribute(ModAttributes.STEP_HEIGHT).applyModifier((AttributeModifier)STEP_HEIGHT);
/*     */     } else {
/*  67 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MOON, new Object[0]));
/*     */     } 
/*  69 */     return sulongCheck;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/*  74 */     player.addPotionEffect(new EffectInstance(Effects.HUNGER, this.continueTime, (int)(this.continueTime / 2000.0F)));
/*  75 */     player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, (int)(this.continueTime * 0.75F), (int)(this.continueTime / 2000.0F)));
/*  76 */     player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int)(this.continueTime * 0.5F), (int)(this.continueTime / 2000.0F)));
/*  77 */     player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, (int)(this.continueTime * 0.5F), (int)(this.continueTime / 2000.0F)));
/*     */     
/*  79 */     IEntityStats stats = EntityStatsCapability.get((LivingEntity)player);
/*  80 */     if (this.continueTime / 20 > stats.getDoriki() / 32)
/*     */     {
/*  82 */       player.addPotionEffect(new EffectInstance(ModEffects.UNCONSCIOUS, 800, 0));
/*     */     }
/*     */     
/*  85 */     player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier((AttributeModifier)SPEED_MODIFIER);
/*  86 */     player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier((AttributeModifier)SPEED_MODIFIER);
/*  87 */     player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier((AttributeModifier)SPEED_MODIFIER);
/*  88 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)STRENGTH_MODIFIER);
/*  89 */     player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier((AttributeModifier)JUMP_MODIFIER);
/*  90 */     player.getAttribute(ModAttributes.FALL_RESISTANCE).removeModifier((AttributeModifier)FALL_RESISTANCE);
/*  91 */     player.getAttribute(ModAttributes.STEP_HEIGHT).removeModifier((AttributeModifier)STEP_HEIGHT);
/*     */     
/*  93 */     player.addExhaustion(5.0F + this.continueTime / 100.0F);
/*  94 */     setMaxCooldown((this.continueTime / 10.0F));
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canTransform(World world) {
/* 100 */     if (WyDebug.isDebug() && APIConfig.BUILD_MODE == APIConfig.BuildMode.DEV)
/* 101 */       return true; 
/* 102 */     return (world.getDimension().getMoonPhase(world.getDayTime()) == 0 && !world.isRaining() && world.getDimension().getType() == DimensionType.OVERWORLD && world.isNightTime());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\SulongAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */