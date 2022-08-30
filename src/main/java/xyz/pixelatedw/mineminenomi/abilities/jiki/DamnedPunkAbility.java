/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMorphAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.DamnedPunkProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.DamnedPunkZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.PunkGibsonZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class DamnedPunkAbility extends PunchTriggerAbility implements IFormRequiredAbility, IMorphAbility {
/*  24 */   public static final DamnedPunkAbility INSTANCE = new DamnedPunkAbility();
/*     */   
/*     */   private static final int MAX_ITEMS = 32;
/*  27 */   private List<ItemStack> magneticItems = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public DamnedPunkAbility() {
/*  31 */     super("Damned Punk", AbilityHelper.getDevilFruitCategory());
/*  32 */     setDescription("Transforms the users arm into a railgun that shoots a projectile with every swing using §232§r magnetic objects from the users inventory per shoot, dealing massive damage on impact.");
/*  33 */     setThreshold(15.0D);
/*  34 */     setMaxCooldown(25.0D);
/*     */     
/*  36 */     this.onStartContinuityEvent = this::onStartChargingEvent;
/*  37 */     this.onEndContinuityEvent = this::onEndChargingEvent;
/*  38 */     this.onSwingEvent = this::onSwingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onSwingEvent(PlayerEntity player) {
/*  43 */     if (player.world.isRemote) {
/*  44 */       return false;
/*     */     }
/*  46 */     List<ItemStack> originals = JikiHelper.getMagneticItems(player, 32);
/*  47 */     int count = JikiHelper.countMagneticItems(originals);
/*  48 */     if (count < 32) {
/*     */       
/*  50 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
/*  51 */       return false;
/*     */     } 
/*     */     
/*  54 */     this.magneticItems = JikiHelper.getMagneticItemsStack(player, originals, 32);
/*     */     
/*  56 */     DamnedPunkProjectile proj = new DamnedPunkProjectile(player.world, (LivingEntity)player, this.magneticItems);
/*  57 */     player.world.addEntity((Entity)proj);
/*  58 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.5F);
/*     */     
/*  60 */     AttractAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onStartChargingEvent(PlayerEntity player) {
/*  67 */     List<ItemStack> originals = JikiHelper.getMagneticItems(player, 32);
/*  68 */     int count = JikiHelper.countMagneticItems(originals);
/*     */     
/*  70 */     if (count < 32) {
/*     */       
/*  72 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
/*  73 */       return false;
/*     */     } 
/*     */     
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onEndChargingEvent(PlayerEntity player) {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(PlayerEntity player) {
/*  87 */     if (!super.canUse(player)) {
/*  88 */       return false;
/*     */     }
/*  90 */     Ability abl = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)PunkGibsonAbility.INSTANCE);
/*     */     
/*  92 */     return (abl != null && abl.isContinuous());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/*  98 */     return new ZoanInfo[] { (ZoanInfo)PunkGibsonZoanInfo.INSTANCE };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 104 */     return (ZoanInfo)DamnedPunkZoanInfo.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 110 */     return isContinuous();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\DamnedPunkAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */