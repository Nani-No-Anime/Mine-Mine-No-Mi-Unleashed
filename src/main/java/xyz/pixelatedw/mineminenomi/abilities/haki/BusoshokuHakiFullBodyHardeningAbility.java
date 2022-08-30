/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ 
/*     */ public class BusoshokuHakiFullBodyHardeningAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility, IBodyOverlayAbility {
/*  31 */   public static final BusoshokuHakiFullBodyHardeningAbility INSTANCE = new BusoshokuHakiFullBodyHardeningAbility();
/*     */   
/*  33 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.BUSOSHOKU_HAKI_ARM).setColor(WyHelper.hexToRGB("#FFFFFFAA"));
/*     */   
/*  35 */   private final UUID armorUUID = UUID.fromString("0457f786-0a5a-4e83-9ea6-f924c259a798");
/*  36 */   private final UUID armorThougnessUUID = UUID.fromString("0457f786-0a5a-4e83-9ea6-f924c259a798");
/*  37 */   private final UUID strengthUUID = UUID.fromString("0457f786-0a5a-4e83-9ea4-f924c259a798");
/*  38 */   private final UUID damageReductionUUID = UUID.fromString("9121ac66-fb1c-48a7-a636-0cdc3f01d96e");
/*     */   
/*     */   private float damage;
/*     */   
/*     */   public BusoshokuHakiFullBodyHardeningAbility() {
/*  43 */     super("Busoshoku Haki: Full Body Hardening", AbilityHelper.getHakiCategory());
/*  44 */     setDescription("Covers the whole body of the user user in a layer of Armament haki, used for a balance between offense and defense");
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
/*  62 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*  63 */     double defense = (hakiProps.getBusoshokuHardeningHakiExp() / 5.0F);
/*     */     
/*  65 */     player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier((AttributeModifier)getEntryArmor(defense));
/*  66 */     player.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier((AttributeModifier)getEntryArmor(defense));
/*     */     
/*  68 */     player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).removeModifier((AttributeModifier)getEntryArmorThougness(defense / 4.0D));
/*  69 */     player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).applyModifier((AttributeModifier)getEntryArmorThougness(defense / 4.0D));
/*     */     
/*  71 */     player.getAttribute(ModAttributes.DAMAGE_REDUCTION).removeModifier((AttributeModifier)getEntryDamageReduction(hakiProps.getBusoshokuHardeningHakiExp() * 0.0025D));
/*  72 */     player.getAttribute(ModAttributes.DAMAGE_REDUCTION).applyModifier((AttributeModifier)getEntryDamageReduction(hakiProps.getBusoshokuHardeningHakiExp() * 0.0025D));
/*     */     
/*  74 */     this.damage = BusoshokuHakiHardeningAbility.getPunchDamage(player) * 0.75F;
/*  75 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(this.damage));
/*  76 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)getEntryAttackDamage(this.damage));
/*     */     
/*  78 */     player.world.playSound(null, player.getPosition(), ModSounds.BUSOSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */     
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/*  85 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 1);
/*  86 */     if (isOnMaxOveruse) {
/*  87 */       endContinuity(player);
/*     */     }
/*  89 */     ItemStack heldItem = player.getHeldItemMainhand();
/*     */     
/*  91 */     if (!heldItem.isEmpty()) {
/*  92 */       player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(0.0D));
/*     */     }
/*  94 */     else if (player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(this.strengthUUID) == null) {
/*  95 */       player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier((AttributeModifier)getEntryAttackDamage(this.damage));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 101 */     player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier((AttributeModifier)getEntryArmor(0.0D));
/* 102 */     player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).removeModifier((AttributeModifier)getEntryArmorThougness(0.0D));
/* 103 */     player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier((AttributeModifier)getEntryAttackDamage(0.0D));
/* 104 */     player.getAttribute(ModAttributes.DAMAGE_REDUCTION).removeModifier((AttributeModifier)getEntryDamageReduction(0.0D));
/*     */     
/* 106 */     int cooldown = (int)WyHelper.clamp(Math.round(this.continueTime / 30.0D), 10L, 90L);
/* 107 */     setMaxCooldown(cooldown);
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   private AbilityAttributeModifier getEntryArmor(double amount) {
/* 112 */     return (new AbilityAttributeModifier(this.armorUUID, (Ability)INSTANCE, "Full Body Haki Armor Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   }
/*     */   
/*     */   private AbilityAttributeModifier getEntryArmorThougness(double amount) {
/* 116 */     return (new AbilityAttributeModifier(this.armorThougnessUUID, (Ability)INSTANCE, "Full Body Haki Armor Toughness Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   }
/*     */   
/*     */   private AbilityAttributeModifier getEntryDamageReduction(double amount) {
/* 120 */     return (new AbilityAttributeModifier(this.damageReductionUUID, (Ability)INSTANCE, "Full Body Haki Damage Reduction Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   }
/*     */   
/*     */   private AbilityAttributeModifier getEntryAttackDamage(double amount) {
/* 124 */     return (new AbilityAttributeModifier(this.strengthUUID, (Ability)INSTANCE, "Full Body Haki Strength Attack Modifier", amount, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HakiType getType() {
/* 130 */     return HakiType.HARDENING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbilityOverlay getBodyOverlay() {
/* 136 */     return OVERLAY;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\BusoshokuHakiFullBodyHardeningAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */