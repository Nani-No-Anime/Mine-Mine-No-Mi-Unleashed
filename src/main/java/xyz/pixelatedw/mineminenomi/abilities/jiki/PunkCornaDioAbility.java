/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */  import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.PunkCornaDioZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class PunkCornaDioAbility extends ZoanAbility implements IMultiTargetAbility {
/*  33 */   public static final PunkCornaDioAbility INSTANCE = new PunkCornaDioAbility();
/*     */   
/*  35 */   private static final AbilityAttributeModifier DAMAGE_REDUCTION = (new AbilityAttributeModifier(UUID.fromString("e86edafe-c7a7-4e92-a4d2-84ad975660d6"), (Ability)INSTANCE, "Punk Corna DIO Speed Modifier", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  36 */   private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("1d68a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Punk Corna DIO Step Height Modifier", 1.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   
/*     */   private static final int MAX_ITEMS = 200;
/*     */   
/*  40 */   private List<ItemStack> magneticItems = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public PunkCornaDioAbility() {
/*  44 */     super("Punk Corna Dio", AbilityHelper.getDevilFruitCategory());
/*  45 */     setDescription("Uses §2200§r magnetic items from the inventory to create a bull which can then be used to smash into enemies dealing huge damage, knocking them away and potentially stunning them.");
/*  46 */     setThreshold(10.0D);
/*  47 */     setMaxCooldown(10.0D);
/*  48 */     addZoanModifier(ModAttributes.DAMAGE_REDUCTION, (AttributeModifier)DAMAGE_REDUCTION);
/*  49 */     addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/*  55 */     if (!this.magneticItems.isEmpty()) {
/*  56 */       this.magneticItems.clear();
/*     */     }
/*  58 */     List<ItemStack> originals = JikiHelper.getMagneticItems(player, 200);
/*     */     
/*  60 */     int count = JikiHelper.countMagneticItems(originals);
/*  61 */     if (count < 200) {
/*     */       
/*  63 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
/*  64 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  68 */     this.magneticItems = JikiHelper.getMagneticItemsStack(player, originals, 200);
/*  69 */     return super.onStartContinuityEvent(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/*  75 */     ItemStack stack = this.magneticItems.get(player.getRNG().nextInt(this.magneticItems.size() - 1));
/*  76 */     ItemsHelper.itemBreakParticles((LivingEntity)player, 200, stack);
/*  77 */     player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ITEM_BREAK, player.getSoundCategory(), 1.0F, 1.0F);
/*  78 */     JikiHelper.dropComponentItems(player, player.getPosition(), this.magneticItems);
/*     */     
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void duringContinuityEvent(PlayerEntity player, int timer) {
/*  86 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
/*     */     
/*  88 */     if (timer < 90) {
/*     */       return;
/*     */     }
/*  91 */     if (this.magneticItems.size() <= 0) {
/*     */       
/*  93 */       super.onEndContinuityEvent(player);
/*     */       
/*     */       return;
/*     */     } 
/*  97 */     if (canDealDamage()) {
/*     */       
/*  99 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
/* 100 */       player.setMotion(speed.x, 0.05D, speed.z);
/* 101 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*     */       
/* 103 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 6.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 104 */       list.remove(player);
/*     */       
/* 106 */       list.forEach(entity -> {
/*     */             if (isTarget(entity)) {
/*     */               if (WyHelper.randomDouble() > 0.75D) {
/*     */                 entity.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 100, 1, false, false));
/*     */               }
/*     */               Vec3d enemySpeed = WyHelper.propulsion((LivingEntity)player, 4.0D, 4.0D);
/*     */               entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 90.0F);
/*     */               entity.setMotion(enemySpeed.x, 0.2D, enemySpeed.z);
/*     */             } 
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDealDamage() {
/* 129 */     return (this.continueTime > 90);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 135 */     return isContinuous();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 141 */     return (ZoanInfo)PunkCornaDioZoanInfo.INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\PunkCornaDioAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */