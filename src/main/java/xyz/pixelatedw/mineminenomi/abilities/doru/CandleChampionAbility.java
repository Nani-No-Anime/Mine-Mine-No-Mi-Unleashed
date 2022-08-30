/*     */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.CandleChampionZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateCustomTexturePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class CandleChampionAbility extends ZoanAbility {
/*  23 */   public static final CandleChampionAbility INSTANCE = new CandleChampionAbility();
/*     */   
/*  25 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("61619b09-8e41-46ac-84e0-ff6c5b432733"), (Ability)INSTANCE, "Candle Champion Attack Damage Modifier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  26 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("83b8b1b1-ffe6-408e-86ff-aaa5bd8bc18b"), (Ability)INSTANCE, "Candle Champion Attack Speed Modifier", 0.10000000149011612D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  27 */   private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("f498db09-6b14-420c-8ed9-d34c492fe64a"), (Ability)INSTANCE, "Candle Champion Reach Modifier", 0.3D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  28 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("f7bfbd8d-adca-4468-8cc8-55c73c5c232c"), (Ability)INSTANCE, "Candle Champion Armor Modifier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  29 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("8b8b8baa-8458-4a2c-a3fe-6df55ac6b43f"), (Ability)INSTANCE, "Candle Champion Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  30 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("8521a343-3d02-4d64-bbd3-a7e272fd8b74"), (Ability)INSTANCE, "Candle Champion Fall Resistance Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */ 
/*     */   
/*     */   public CandleChampionAbility() {
/*  34 */     super("Candle Champion", AbilityHelper.getDevilFruitCategory());
/*  35 */     setThreshold(60.0D);
/*  36 */     setDescription("The user covers themselves in a thick wax coating, creating a battle suit protecting the user and increasing their offensive capabilities.\n\nWhile active transforms §2Doru Doru Arts: Mori§r into §2Champ Fight§r");
/*     */ 
/*     */     
/*  39 */     addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/*  40 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/*  41 */     addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
/*  42 */     addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/*  43 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/*  44 */     addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
/*  45 */     addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
/*     */     
/*  47 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  48 */     this.duringContinuityEvent = this::duringContinuity;
/*  49 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/*  55 */     if (!super.onStartContinuityEvent(player)) {
/*  56 */       return false;
/*     */     }
/*  58 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  60 */     DoruDoruArtsMoriAbility moriAbility = (DoruDoruArtsMoriAbility)abilityProps.getEquippedAbility((Ability)DoruDoruArtsMoriAbility.INSTANCE);
/*  61 */     if (moriAbility != null) {
/*     */       
/*  63 */       moriAbility.enableChampionMode();
/*  64 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)moriAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/*  67 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void duringContinuity(PlayerEntity player, int passiveTimer) {
/*  72 */     duringContinuityEvent(player, passiveTimer);
/*  73 */     if (player.isBurning()) {
/*     */       
/*  75 */       this.continueTime += 5;
/*  76 */       if (this.continueTime % 5 == 0) {
/*  77 */         WyNetwork.sendTo(new SUpdateEquippedAbilityPacket(player, (Ability)this), player);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/*  84 */     super.onEndContinuityEvent(player);
/*     */     
/*  86 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  88 */     DoruDoruArtsMoriAbility moriAbility = (DoruDoruArtsMoriAbility)abilityProps.getEquippedAbility((Ability)DoruDoruArtsMoriAbility.INSTANCE);
/*  89 */     if (moriAbility != null) {
/*     */       
/*  91 */       moriAbility.disableChampionMode();
/*  92 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)moriAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/*  95 */     int cooldown = (int)Math.round(this.continueTime / 20.0D) + 2;
/*  96 */     setMaxCooldown(cooldown);
/*     */     
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 104 */     return (ZoanInfo)CandleChampionZoanInfo.INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\CandleChampionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */