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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.PunkPistolProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class PunkPistolsAbility extends RepeaterAbility implements IAnimatedAbility {
/*  21 */   public static final PunkPistolsAbility INSTANCE = new PunkPistolsAbility();
/*     */   
/*     */   private static final int ITEMS_PER_SPEAR = 10;
/*     */   private static final int SPEARS = 6;
/*  25 */   private List<ItemStack> magneticItems = new ArrayList<>();
/*     */   
/*     */   private int prevContTime;
/*     */   private boolean shoot;
/*     */   
/*     */   public PunkPistolsAbility() {
/*  31 */     super("Punk Pistols", AbilityHelper.getDevilFruitCategory());
/*  32 */     setDescription("Uses §260§r magnetic objects from the user's inventory to form §26§r spears and shoots them at enemies.");
/*  33 */     setMaxCooldown(12.0D);
/*  34 */     setMaxRepeaterCount(6, 4);
/*     */     
/*  36 */     this.onStartContinuityEvent = this::onStartContinuity;
/*  37 */     this.onEndContinuityEvent = this::onEndContinuity;
/*  38 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  39 */     this.onUseEvent = this::onUseEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int i) {
/*  44 */     if (getThreshold() != 0 && i % 5 == 0) {
/*     */       
/*  46 */       List<ItemStack> originals = JikiHelper.getMagneticItems(player, 10);
/*  47 */       int count = JikiHelper.countMagneticItems(originals);
/*  48 */       if (count < 10) {
/*     */         
/*  50 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
/*  51 */         endContinuity(player);
/*     */         
/*     */         return;
/*     */       } 
/*  55 */       List<ItemStack> components = JikiHelper.getMagneticItemsStack(player, originals, 10);
/*  56 */       PunkPistolProjectile proj = new PunkPistolProjectile(player.world, (LivingEntity)player, components);
/*  57 */       player.world.addEntity((Entity)proj);
/*  58 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 2.0F);
/*     */       
/*  60 */       AttractAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean onEndContinuity(PlayerEntity player) {
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onStartContinuity(PlayerEntity player) {
/*  82 */     List<ItemStack> originals = JikiHelper.getMagneticItems(player, 10);
/*  83 */     int count = JikiHelper.countMagneticItems(originals);
/*     */     
/*  85 */     if (count < 10) {
/*     */       
/*  87 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
/*  88 */       return false;
/*     */     } 
/*     */     
/*  91 */     this.magneticItems = JikiHelper.getMagneticItemsStack(player, originals, 10);
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  97 */     if (player.world.isRemote || this.shoot) {
/*  98 */       return false;
/*     */     }
/* 100 */     List<ItemStack> originals = JikiHelper.getMagneticItems(player, 10);
/* 101 */     int count = JikiHelper.countMagneticItems(originals);
/* 102 */     if (count < 10) {
/*     */       
/* 104 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
/* 105 */       endContinuity(player);
/* 106 */       return false;
/*     */     } 
/*     */     
/* 109 */     List<ItemStack> components = JikiHelper.getMagneticItemsStack(player, originals, 10);
/*     */     
/* 111 */     PunkPistolProjectile proj = new PunkPistolProjectile(player.world, (LivingEntity)player, components);
/* 112 */     player.world.addEntity((Entity)proj);
/* 113 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 2.0F);
/*     */     
/* 115 */     this.shoot = true;
/* 116 */     this.prevContTime = getContinueTime();
/* 117 */     this.continueTime = 0;
/* 118 */     setThreshold(2.0D);
/* 119 */     WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*     */     
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 127 */     return (IAnimation)PointArmAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 133 */     return isContinuous();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\PunkPistolsAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */