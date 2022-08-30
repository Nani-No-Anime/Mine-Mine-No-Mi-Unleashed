/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.GearFourthZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class GearFourthAbility extends ZoanAbility implements IBodyOverlayAbility {
/*  38 */   public static final GearFourthAbility INSTANCE = new GearFourthAbility();
/*     */   
/*  40 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("e158d542-5644-4921-9d5f-895f0b0a164c"), (Ability)INSTANCE, "Gear Fourth Armor Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  41 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), (Ability)INSTANCE, "Gear Fourth Attack Damage Modifier", 15.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  42 */   private static final AbilityAttributeModifier DAMAGE_REDUCTION_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1972705a-a0b6-4e66-8a4b-f64b0232b6f2"), (Ability)INSTANCE, "Gear Fourth Resistance Damage Modifier", 0.35D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   
/*  44 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.G4_OVERLAY);
/*     */   
/*  46 */   public float speed = 0.0F;
/*  47 */   private double currentHP = 0.0D;
/*     */ 
/*     */   
/*     */   public GearFourthAbility() {
/*  51 */     super("Gear Fourth", AbilityHelper.getDevilFruitCategory());
/*  52 */     setThreshold(50.0D);
/*  53 */     setDescription("The user inflates their muscle structure to tremendously increase the power of their attacks and also allows flight\n\n× Can only be used while §2" + BusoshokuHakiFullBodyHardeningAbility.INSTANCE
/*  54 */         .getDisplayName() + "§r is active\n\nWhile active transforms §2Gomu Gomu no Rocket§r into §2King Kong Gun§r");
/*     */ 
/*     */     
/*  57 */     addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
/*  58 */     addZoanModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS, (AttributeModifier)ARMOR_MODIFIER);
/*  59 */     addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/*  60 */     addZoanModifier(ModAttributes.DAMAGE_REDUCTION, (AttributeModifier)DAMAGE_REDUCTION_MODIFIER);
/*     */ 
/*     */     
/*  63 */     needsClientSide();
/*  64 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  65 */     this.duringContinuityEvent = this::duringContinuity;
/*  66 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onStartContinuityEvent(PlayerEntity player) {
/*  72 */     if (!GomuHelper.canActivateGear(AbilityDataCapability.get((LivingEntity)player), (Ability)INSTANCE)) {
/*     */       
/*  74 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_GEAR_ACTIVE, new Object[0]));
/*  75 */       return false;
/*     */     } 
/*     */     
/*  78 */     BusoshokuHakiFullBodyHardeningAbility ability = (BusoshokuHakiFullBodyHardeningAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/*  79 */     boolean hakiCheck = (ability != null && ability.isContinuous());
/*  80 */     if (!hakiCheck) {
/*     */       
/*  82 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_HAKI, new Object[0]));
/*  83 */       return false;
/*     */     } 
/*     */     
/*  86 */     setThreshold(40.0D);
/*  87 */     this.currentHP = player.getHealth();
/*  88 */     return super.onStartContinuityEvent(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void duringContinuity(PlayerEntity player, int passiveTimer) {
/*  93 */     if (!player.world.isRemote) {
/*     */       
/*  95 */       if (passiveTimer % 2 == 0) {
/*  96 */         GearSecondAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY() + 1.0D, player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */       }
/*  98 */       BusoshokuHakiFullBodyHardeningAbility ability = (BusoshokuHakiFullBodyHardeningAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/*  99 */       boolean hakiCheck = (ability != null && ability.isContinuous());
/* 100 */       boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 2);
/* 101 */       if (isOnMaxOveruse || !hakiCheck || AbilityHelper.isNearbyKairoseki(player)) {
/* 102 */         endContinuity(player);
/*     */       }
/* 104 */       if (player.hurtTime > 0) {
/*     */         
/* 106 */         if (this.currentHP > player.getHealth() + WyHelper.randomWithRange(3, 6)) {
/*     */           
/* 108 */           int diff = Math.abs((int)(player.getHealth() - this.currentHP));
/* 109 */           setThresholdInTicks(Math.max((int)(this.threshold * (1.0F - diff / 20.0F)), 0));
/*     */           
/* 111 */           for (int i = 0; i < diff * 60; i++)
/* 112 */             GearSecondAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY() + 1.0D, player.getPosZ(), 
/* 113 */                 WyHelper.randomWithRange(-2, 2), WyHelper.randomWithRange(-2, 2), WyHelper.randomWithRange(-2, 2)); 
/* 114 */           IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 115 */           WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/* 116 */           WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*     */         } 
/* 118 */         this.currentHP = player.getHealth();
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     GomuGomuNoRocketAbility rocketAbility = (GomuGomuNoRocketAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)GomuGomuNoRocketAbility.INSTANCE);
/*     */     
/* 124 */     float maxSpeed = (rocketAbility != null && rocketAbility.isContinuous()) ? 2.2F : 1.1F;
/* 125 */     float acceleration = 0.01F;
/*     */     
/* 127 */     acceleration *= (this.speed > 0.0F) ? (1.0F - this.speed / maxSpeed) : 1.0F;
/* 128 */     acceleration = (player.moveForward > 0.0F && !player.collided) ? acceleration : (-maxSpeed / 10.0F);
/* 129 */     this.speed = MathHelper.clamp(this.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 4.0F) : 0.0F, maxSpeed);
/*     */     
/* 131 */     int d1 = player.onGround ? 1 : 0;
/* 132 */     int d2 = (player.moveForward > 0.0F) ? 1 : 0;
/*     */     
/* 134 */     Vec3d vec = player.getLookVec();
/* 135 */     double x = vec.x * this.speed * (1 - d1) * d2;
/* 136 */     double y = (d1 * 0.6F) + vec.y * this.speed * (1 - d1) * d2 + Math.cos((player.ticksExisted / (4.0F - this.speed * 1.25F))) / 5.0D;
/* 137 */     double z = vec.z * this.speed * (1 - d1) * d2;
/* 138 */     player.setMotion(x, y, z);
/*     */     
/* 140 */     double difference = DevilFruitHelper.getDifferenceToFloor(player);
/*     */     
/* 142 */     if (difference > 40.0D) {
/* 143 */       player.setMotion(player.getMotion()
/* 144 */           .add(0.0D, 0.25D * difference / 5.0D, 0.0D)
/* 145 */           .mul(1.0D, -0.15D, 1.0D));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onEndContinuityEvent(PlayerEntity player) {
/* 151 */     int duration = (int)(600.0F - HakiDataCapability.get((LivingEntity)player).getTotalHakiExp());
/* 152 */     if (this.continueTime > getThreshold() / 10) {
/*     */       
/* 154 */       player.addPotionEffect(new EffectInstance(Effects.HUNGER, duration, 3, true, true));
/* 155 */       player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, duration, 1, true, true));
/* 156 */       player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, duration, 1, true, true));
/* 157 */       player.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, duration, 0, true, true));
/*     */     } 
/*     */     
/* 160 */     BusoshokuHakiFullBodyHardeningAbility ability = (BusoshokuHakiFullBodyHardeningAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/* 161 */     boolean hakiCheck = (ability != null && ability.isContinuous());
/* 162 */     if (hakiCheck) {
/* 163 */       ability.endContinuity(player);
/*     */     }
/* 165 */     int cooldown = duration / 60 + (int)Math.round(this.continueTime / 30.0D);
/* 166 */     setMaxCooldown(cooldown);
/* 167 */     return super.onEndContinuityEvent(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 173 */     return (ZoanInfo)GearFourthZoanInfo.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbilityOverlay getBodyOverlay() {
/* 179 */     return OVERLAY;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GearFourthAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */