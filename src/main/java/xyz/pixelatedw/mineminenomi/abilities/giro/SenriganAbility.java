/*     */ package xyz.pixelatedw.mineminenomi.abilities.giro;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class SenriganAbility extends DamagedContinuousAbility implements IOutOfBodyAbility, IExtraUpdateData {
/*  20 */   public static final SenriganAbility INSTANCE = new SenriganAbility();
/*     */   
/*     */   private PhysicalBodyEntity body;
/*     */   
/*     */   private BlockPos pivotPoint;
/*     */   
/*     */   public SenriganAbility() {
/*  27 */     super("Senrigan", AbilityHelper.getDevilFruitCategory());
/*  28 */     setDescription("The spirit leaves the body, allowing them to freely explore the nearby areas from huge heights");
/*  29 */     setThreshold(60.0D);
/*     */     
/*  31 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  32 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  33 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*  34 */     this.onDamagedEvent = this::onDamagedEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  39 */     if (player.isCreative() || player.isSpectator()) {
/*     */       
/*  41 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_SUVIVAL_ONLY, new Object[0]));
/*  42 */       return false;
/*     */     } 
/*  44 */     this.pivotPoint = new BlockPos(player.getPositionVec().getX(), player.getPositionVec().getY(), player.getPositionVec().getZ());
/*     */     
/*  46 */     this.body = new PhysicalBodyEntity(player.world);
/*  47 */     this.body.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/*  48 */     this.body.setOwner((LivingEntity)player);
/*  49 */     player.world.addEntity((Entity)this.body);
/*  50 */     this.body.setHealth(player.getHealth());
/*  51 */     this.body.setParentAbility((Ability)this);
/*     */     
/*  53 */     startOutOfBody(player);
/*     */     
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int continueTime) {
/*  60 */     if (Math.sqrt(player.getDistanceSq(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ())) > getMaxRange()) {
/*     */       
/*  62 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 10, 0, false, false));
/*     */     
/*  68 */     if (player.getPosY() < 128.0D) {
/*  69 */       player.setPositionAndUpdate(player.getPosX(), 128.0D, player.getPosZ());
/*     */     }
/*  71 */     if (this.body == null) {
/*     */       
/*  73 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  77 */     if (!this.body.isAlive()) {
/*  78 */       player.attackEntityFrom(DamageSource.MAGIC, player.getMaxHealth());
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/*  83 */     double cooldown = (this.continueTime / 20.0F);
/*  84 */     setMaxCooldown(cooldown);
/*     */     
/*  86 */     if (this.body != null) {
/*     */       
/*  88 */       this.body.remove();
/*  89 */       this.body = null;
/*  90 */       player.setPositionAndUpdate(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ());
/*     */     } 
/*     */     
/*  93 */     stopOutOfBody(player);
/*     */     
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource, double v) {
/* 100 */     if (entity instanceof PlayerEntity) {
/*     */       
/* 102 */       if (((PlayerEntity)entity).isCreative() || entity.isSpectator()) {
/* 103 */         return false;
/*     */       }
/* 105 */       return (damageSource == DamageSource.MAGIC || damageSource.isDamageAbsolute());
/*     */     } 
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxRange() {
/* 113 */     return 256.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos getPivotPoint() {
/* 119 */     return this.pivotPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/* 125 */     CompoundNBT nbt = new CompoundNBT();
/* 126 */     if (this.pivotPoint != null) {
/*     */       
/* 128 */       nbt.putDouble("x", this.pivotPoint.getX());
/* 129 */       nbt.putDouble("y", this.pivotPoint.getY());
/* 130 */       nbt.putDouble("z", this.pivotPoint.getZ());
/*     */     } 
/* 132 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 138 */     double x = nbt.getDouble("x");
/* 139 */     double y = nbt.getDouble("y");
/* 140 */     double z = nbt.getDouble("z");
/* 141 */     this.pivotPoint = new BlockPos(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPhysical() {
/* 147 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\giro\SenriganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */