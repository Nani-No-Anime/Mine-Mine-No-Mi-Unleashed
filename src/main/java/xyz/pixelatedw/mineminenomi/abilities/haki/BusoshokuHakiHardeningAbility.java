/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ import java.awt.Color;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ 
/*     */ public class BusoshokuHakiHardeningAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility, IPunchOverlayAbility {
/*  35 */   public static final BusoshokuHakiHardeningAbility INSTANCE = new BusoshokuHakiHardeningAbility();
/*  36 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.BUSOSHOKU_HAKI_ARM).setColor(new Color(255, 255, 255, 191));
/*  37 */   private static final UUID STRENGTH_UUID = UUID.fromString("0457f786-0a5a-4e83-9ea4-f924c259a798");
/*     */   
/*  39 */   private float damage = 0.0F;
/*     */ 
/*     */   
/*     */   public BusoshokuHakiHardeningAbility() {
/*  43 */     super("Busoshoku Haki: Hardening", AbilityHelper.getHakiCategory());
/*  44 */     setDescription("Covers the fist of the user in Armament haki, making their physical attacks more powerful and being able to damage Logia users");
/*  45 */     addInPool(new AbilityPool[] { AbilityPool.BUSOSHOKU_HAKI });
/*  46 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  47 */     this.duringContinuityEvent = this::duringContinuity;
/*  48 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  53 */     if (!HakiHelper.canUseHaki(player, (Ability)this)) {
/*     */       
/*  55 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONE_HAKI_TYPE, new Object[] { getName() }));
/*  56 */       return false;
/*     */     } 
/*     */     
/*  59 */     if (!HakiHelper.canEnableHaki(player)) {
/*  60 */       return false;
/*     */     }
/*  62 */     this.damage = getPunchDamage(player);
/*  63 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(this.damage));
/*  64 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)getEntryAttackDamage(this.damage));
/*     */     
/*  66 */     player.world.playSound(null, player.getPosition(), ModSounds.BUSOSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */     
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getPunchDamage(PlayerEntity player) {
/*  73 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  74 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*     */     
/*  76 */     float dorikiMultiplier = (props.getDoriki() / CommonConfig.INSTANCE.getDorikiLimit());
/*  77 */     float hakiMultiplier = hakiProps.getBusoshokuHardeningHakiExp() / CommonConfig.INSTANCE.getHakiExpLimit();
/*     */     
/*  79 */     return dorikiMultiplier * 4.0F + hakiMultiplier * 16.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/*  84 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 0);
/*  85 */     if (isOnMaxOveruse) {
/*  86 */       endContinuity(player);
/*     */     }
/*  88 */     ItemStack heldItem = player.getHeldItemMainhand();
/*     */     
/*  90 */     if (!heldItem.isEmpty()) {
/*  91 */       player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(0.0D));
/*     */     }
/*  93 */     else if (player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(STRENGTH_UUID) == null) {
/*  94 */       player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)getEntryAttackDamage(this.damage));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 100 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(0.0D));
/* 101 */     int cooldown = (int)WyHelper.clamp(Math.round(this.continueTime / 30.0D), 5L, 60L);
/* 102 */     setMaxCooldown(cooldown);
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private AbilityAttributeModifier getEntryAttackDamage(double amount) {
/* 108 */     return (new AbilityAttributeModifier(STRENGTH_UUID, (Ability)INSTANCE, "Hardening Haki Strength Attack Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HakiType getType() {
/* 114 */     return HakiType.HARDENING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbilityOverlay getPunchOverlay(LivingEntity player) {
/* 120 */     return OVERLAY;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\BusoshokuHakiHardeningAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */