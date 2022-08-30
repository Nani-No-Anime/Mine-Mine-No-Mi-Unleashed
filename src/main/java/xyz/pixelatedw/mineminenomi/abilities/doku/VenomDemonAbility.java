/*     */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateCustomTexturePacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.VenomDemonParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class VenomDemonAbility extends ZoanAbility {
/*  29 */   public static final VenomDemonAbility INSTANCE = new VenomDemonAbility();
/*     */   
/*  31 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new VenomDemonParticleEffect();
/*  32 */   private static final BlockProtectionRule GRIEF_RULE = DefaultProtectionRules.AIR_FOLIAGE.addApprovedBlocks(new Block[] { ModBlocks.POISON, ModBlocks.DEMON_POISON });
/*     */   
/*  34 */   private static final AbilityAttributeModifier ATTACK_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Venom Demon Attack Modifier", 8.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  35 */   private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("930fd8dc-5f0c-4daa-869c-d42d6166d3f1"), (Ability)INSTANCE, "Venom Demon Reach Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  36 */   private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4e2e4824-f5e8-449f-a54b-7d0a5572c6a1"), (Ability)INSTANCE, "Venom Demon Speed Modifier", 0.02D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  37 */   private static final AbilityAttributeModifier ATTACK_SPEED = (new AbilityAttributeModifier(UUID.fromString("2880a753-352a-4e1c-bf4e-4e4e4649febe"), (Ability)INSTANCE, "Venom Demon Attack Speed Modifier", 0.15D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  38 */   private static final AbilityAttributeModifier STEP_ASSIST = (new AbilityAttributeModifier(UUID.fromString("2880a753-352a-4e1c-bf4e-4e4e4649febe"), (Ability)INSTANCE, "Venom Demon Step assist Modifier", 1.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  39 */   private static final AbilityAttributeModifier JUMP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("2880a753-352a-4e1c-bf4e-4e4e4649febe"), (Ability)INSTANCE, "Venom Demon Jump Height Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  40 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Venom Demon Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */ 
/*     */   
/*     */   public VenomDemonAbility() {
/*  44 */     super("Venom Demon", AbilityHelper.getDevilFruitCategory());
/*  45 */     setDescription("The user coats themselves in layers of strong corrosive venom, becoming a Venom Demon and leaving a highly poisonous trail. Also enhances all Posion abilities");
/*     */     
/*  47 */     setMaxCooldown(100.0D);
/*  48 */     setThreshold(60.0D);
/*     */     
/*  50 */     addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/*  51 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/*  52 */     addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)ATTACK_MODIFIER);
/*  53 */     addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
/*  54 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED);
/*  55 */     addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_ASSIST);
/*  56 */     addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)STEP_ASSIST);
/*  57 */     addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_HEIGHT);
/*  58 */     addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
/*     */     
/*  60 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  61 */     this.duringContinuityEvent = this::duringContinuity;
/*  62 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onStartContinuityEvent(PlayerEntity player) {
/*  68 */     if (!super.onStartContinuityEvent(player)) {
/*  69 */       return false;
/*     */     }
/*  71 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  73 */     HydraAbility hydraAbility = (HydraAbility)abilityProps.getEquippedAbility(HydraAbility.INSTANCE);
/*  74 */     if (hydraAbility != null) {
/*     */       
/*  76 */       hydraAbility.enableVenomDemoMode();
/*  77 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, hydraAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/*  80 */     ChloroBallAbility chloroBallAbility = (ChloroBallAbility)abilityProps.getEquippedAbility((Ability)ChloroBallAbility.INSTANCE);
/*  81 */     if (chloroBallAbility != null) {
/*     */       
/*  83 */       chloroBallAbility.enableVenomDemoMode();
/*  84 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)chloroBallAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/*  87 */     DokuGumoAbility dokuGumoAbility = (DokuGumoAbility)abilityProps.getEquippedAbility((Ability)DokuGumoAbility.INSTANCE);
/*  88 */     if (dokuGumoAbility != null) {
/*     */       
/*  90 */       dokuGumoAbility.enableVenomDemoMode();
/*  91 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)dokuGumoAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/*  94 */     VenomRoadAbility venomDemonAbility = (VenomRoadAbility)abilityProps.getEquippedAbility(VenomRoadAbility.INSTANCE);
/*  95 */     if (venomDemonAbility != null) {
/*     */       
/*  97 */       venomDemonAbility.enableVenomDemoMode();
/*  98 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, venomDemonAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuity(PlayerEntity player, int timer) {
/* 106 */     player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 3, false, false));
/*     */     
/* 108 */     if (!AbilityHelper.isNearbyKairoseki(player))
/*     */     {
/* 110 */       for (int x = -1; x < 1; x++) {
/* 111 */         for (int z = -1; z < 1; z++) {
/*     */           
/* 113 */           BlockPos pos = new BlockPos(player.getPosX() + x, player.getPosY(), player.getPosZ() + z);
/* 114 */           if (player.world.getBlockState(pos.down()).isSolid())
/* 115 */             AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() + x, player.getPosY(), player.getPosZ() + z, ModBlocks.DEMON_POISON, GRIEF_RULE); 
/*     */         } 
/*     */       } 
/*     */     }
/* 119 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEndContinuityEvent(PlayerEntity player) {
/* 125 */     super.onEndContinuityEvent(player);
/*     */     
/* 127 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 129 */     HydraAbility hydraAbility = (HydraAbility)abilityProps.getEquippedAbility(HydraAbility.INSTANCE);
/* 130 */     if (hydraAbility != null) {
/*     */       
/* 132 */       hydraAbility.disableVenomDemoMode();
/* 133 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, hydraAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/* 136 */     ChloroBallAbility chloroBallAbility = (ChloroBallAbility)abilityProps.getEquippedAbility((Ability)ChloroBallAbility.INSTANCE);
/* 137 */     if (chloroBallAbility != null) {
/*     */       
/* 139 */       chloroBallAbility.disableVenomDemoMode();
/* 140 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)chloroBallAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/* 143 */     DokuGumoAbility dokuGumoAbility = (DokuGumoAbility)abilityProps.getEquippedAbility((Ability)DokuGumoAbility.INSTANCE);
/* 144 */     if (dokuGumoAbility != null) {
/*     */       
/* 146 */       dokuGumoAbility.disableVenomDemoMode();
/* 147 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)dokuGumoAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/* 150 */     VenomRoadAbility venomDemonAbility = (VenomRoadAbility)abilityProps.getEquippedAbility(VenomRoadAbility.INSTANCE);
/* 151 */     if (venomDemonAbility != null) {
/*     */       
/* 153 */       venomDemonAbility.disableVenomDemoMode();
/* 154 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, venomDemonAbility), (LivingEntity)player);
/*     */     } 
/*     */     
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 163 */     return (ZoanInfo)VenomDemonZoanInfo.INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\VenomDemonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */