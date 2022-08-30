/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningBallEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.VoltAmaruZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateCustomTexturePacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IChangeDamageSourceAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class VoltAmaruAbility extends ZoanAbility implements IBodyOverlayAbility, IChangeDamageSourceAbility {
/*  43 */   public static final VoltAmaruAbility INSTANCE = new VoltAmaruAbility();
/*     */   
/*  45 */   private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1f8cc62b-ad78-4b25-b19c-76d23dd27517"), (Ability)INSTANCE, "Volt Amaru Reach Modifier", 4.8D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  46 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("84033b39-98f7-4312-acc5-92809e9732b1"), (Ability)INSTANCE, "Volt Amaru Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  47 */   private static final AbilityAttributeModifier DAMAGE_REDUCTION = (new AbilityAttributeModifier(UUID.fromString("8a048300-6f4b-11eb-9439-0242ac130002"), (Ability)INSTANCE, "Volt Amaru Damage Reduction Modifier", 0.25D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  48 */   private static final AbilityAttributeModifier HEALTH_BOOST = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Volt Amaru Health Modifier", 20.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  49 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Volt Amaru Strength Modifier", 12.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  50 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Volt Amaru Attack Speed Modifier", 1.0D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/*  51 */   private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), (Ability)INSTANCE, "Volt Amaru Jump Modifier", 5.0D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/*     */   
/*  53 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setColor("#09C7FF").setRenderType(AbilityOverlay.RenderType.ENERGY);
/*     */   
/*  55 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new HaoshokuHakiParticleEffect(2);
/*     */   
/*  57 */   public float speed = 0.0F;
/*     */ 
/*     */   
/*     */   public VoltAmaruAbility() {
/*  61 */     super("Volt Amaru", AbilityHelper.getDevilFruitCategory());
/*  62 */     setThreshold(20.0D);
/*  63 */     setMaxCooldown(50.0D);
/*  64 */     setDescription("Transforms the user into a powerful, lightning giant massively boosting physical attributes and lightning attacks\n\n× Can only be used while §2" + (new TranslationTextComponent("item.mineminenomi.tomoe_drums", new Object[0])).getFormattedText() + "§r is equipped");
/*     */     
/*  66 */     addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/*  67 */     addZoanModifier(ModAttributes.DAMAGE_REDUCTION, (AttributeModifier)DAMAGE_REDUCTION);
/*  68 */     addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_BOOST);
/*  69 */     addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/*  70 */     addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/*  71 */     addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
/*     */     
/*  73 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/*  74 */     addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
/*     */     
/*  76 */     needsClientSide();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/*  82 */     boolean hasTomoeDrums = (((ItemStack)player.inventory.armorInventory.get(2)).getItem() == ModArmors.TOMOE_DRUMS);
/*     */     
/*  84 */     if (!hasTomoeDrums || ((ItemStack)player.inventory.armorInventory.get(2)).getDamage() > 990) {
/*  85 */       return false;
/*     */     }
/*  87 */     ItemStack tomoe = (ItemStack)player.inventory.armorInventory.get(2);
/*  88 */     int damage = (tomoe.getDamage() + 100 < tomoe.getMaxDamage()) ? 100 : 99;
/*  89 */     tomoe.damageItem(damage, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*     */     
/*  91 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  93 */     ElThorAbility elThor = (ElThorAbility)abilityProps.getEquippedAbility((Ability)ElThorAbility.INSTANCE);
/*  94 */     if (elThor != null) {
/*     */       
/*  96 */       elThor.enableVoltMode();
/*  97 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)elThor), (LivingEntity)player);
/*     */     } 
/*     */     
/* 100 */     LightningBallEntity ball = new LightningBallEntity((Entity)player, player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/* 101 */     ball.setSize(4.0F);
/* 102 */     ball.setLightningLength(10.0F);
/* 103 */     ball.setAliveTicks(20);
/* 104 */     player.world.addEntity((Entity)ball);
/* 105 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 1, false, false));
/*     */     
/* 107 */     return super.onStartContinuityEvent(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void duringContinuityEvent(PlayerEntity player, int i) {
/* 113 */     super.duringContinuityEvent(player, i);
/*     */     
/* 115 */     boolean hasTomoeDrums = (((ItemStack)player.inventory.armorInventory.get(2)).getItem() == ModArmors.TOMOE_DRUMS);
/* 116 */     if (!hasTomoeDrums) {
/* 117 */       endContinuity(player);
/*     */     }
/* 119 */     if (i >= 20) {
/*     */       
/* 121 */       if (i == 21 || i % 100 == 0) {
/*     */         
/* 123 */         if (player.world.isRemote) {
/*     */           return;
/*     */         }
/* 126 */         MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
/* 127 */         WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
/* 128 */         player.recalculateSize();
/*     */       } 
/*     */       
/* 131 */       float maxSpeed = player.isSprinting() ? 2.2F : 1.1F;
/* 132 */       float acceleration = 0.015F;
/*     */       
/* 134 */       acceleration *= (this.speed > 0.0F) ? (1.0F - this.speed / maxSpeed) : 1.0F;
/* 135 */       acceleration = (player.moveForward > 0.0F && !player.collided) ? acceleration : (-maxSpeed / 10.0F);
/* 136 */       this.speed = MathHelper.clamp(this.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 3.0F) : 0.0F, maxSpeed);
/*     */       
/* 138 */       int d1 = player.onGround ? 1 : 0;
/* 139 */       int d2 = (player.moveForward > 0.0F) ? 1 : 0;
/*     */       
/* 141 */       Vec3d vec = player.getLookVec();
/* 142 */       double x = vec.x * this.speed * (1 - d1) * d2;
/* 143 */       double y = (d1 * 0.6F) + vec.y * this.speed * (1 - d1) * d2;
/* 144 */       double z = vec.z * this.speed * (1 - d1) * d2;
/* 145 */       player.setMotion(x, y, z);
/*     */       
/* 147 */       double difference = DevilFruitHelper.getDifferenceToFloor(player);
/*     */       
/* 149 */       if (difference < 5.0D) {
/*     */         
/* 151 */         player.setMotion(player.getMotion().add(0.0D, 1.0D, 0.0D).mul(1.0D, 0.25D, 1.0D));
/*     */       }
/* 153 */       else if (difference > 36.0D) {
/*     */         
/* 155 */         player.setMotion(player.getMotion().add(0.0D, 1.0D, 0.0D).mul(1.0D, -0.35D, 1.0D));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEndContinuityEvent(PlayerEntity player) {
/* 163 */     super.onEndContinuityEvent(player);
/*     */     
/* 165 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 167 */     ElThorAbility elThor = (ElThorAbility)abilityProps.getEquippedAbility((Ability)ElThorAbility.INSTANCE);
/* 168 */     if (elThor != null) {
/*     */       
/* 170 */       elThor.disableVoltMode();
/*     */       
/* 172 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)elThor), (LivingEntity)player);
/*     */     } 
/*     */     
/* 175 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 181 */     return (ZoanInfo)VoltAmaruZoanInfo.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbilityOverlay getBodyOverlay() {
/* 187 */     return OVERLAY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float damageToEntityWithSource(PlayerEntity player, LivingEntity entity) {
/* 193 */     if (player.hurtResistantTime > 0 && player.hurtResistantTime <= 10) {
/* 194 */       player.hurtTime = player.hurtResistantTime = 0;
/*     */     }
/* 196 */     if (player.getHeldItemMainhand().getItem() instanceof CoreSwordItem) {
/*     */       
/* 198 */       CoreSwordItem weapon = (CoreSwordItem)player.getHeldItemMainhand().getItem();
/* 199 */       if (weapon.conductivity > 0.5D) {
/*     */         
/* 201 */         entity.setFire(5);
/* 202 */         entity.hurtTime = entity.hurtResistantTime = 0;
/* 203 */         entity.attackEntityFrom((DamageSource)(new ModDamageSource("onFire", false, true, false)).causeEntityDamageFromSource((Entity)player), (float)(weapon.conductivity * 3.0D));
/* 204 */         entity.hurtTime = entity.hurtResistantTime = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 208 */     return (float)(1.0D + player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DamageSource getSourceToUse(PlayerEntity player) {
/* 214 */     return (DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cancelsOriginalDamage() {
/* 220 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSourceChangeEnabled() {
/* 226 */     return isContinuous();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 232 */     return (isContinuous() && this.continueTime >= 20);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\VoltAmaruAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */