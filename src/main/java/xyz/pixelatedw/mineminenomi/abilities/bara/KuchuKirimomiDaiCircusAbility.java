/*     */ package xyz.pixelatedw.mineminenomi.abilities.bara;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.DaiCircusProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.BaraCircusZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class KuchuKirimomiDaiCircusAbility extends ZoanAbility {
/*  20 */   public static final KuchuKirimomiDaiCircusAbility INSTANCE = new KuchuKirimomiDaiCircusAbility();
/*     */   
/*  22 */   private LivingEntity grabbedEntity = null;
/*  23 */   private DaiCircusProjectile proj = null;
/*     */ 
/*     */   
/*     */   public KuchuKirimomiDaiCircusAbility() {
/*  27 */     super("Kuchu Kirimomi Dai Circus", AbilityHelper.getDevilFruitCategory());
/*  28 */     setDescription("Fires both fists at an enemy and lifts them up, moving them around according to the user's movements");
/*  29 */     setMaxCooldown(15.0D);
/*  30 */     setThreshold(5.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onStartContinuityEvent(PlayerEntity player) {
/*  36 */     if (!super.onStartContinuityEvent(player)) {
/*  37 */       return false;
/*     */     }
/*  39 */     BaraBaraFestivalAbility ability = (BaraBaraFestivalAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BaraBaraFestivalAbility.INSTANCE);
/*  40 */     if (ability != null && ability.isContinuous()) {
/*     */       
/*  42 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_BARA_FESTIVAL, new Object[] { getDisplayName() }));
/*  43 */       return false;
/*     */     } 
/*     */     
/*  46 */     this.proj = new DaiCircusProjectile(player.world, (LivingEntity)player);
/*  47 */     player.world.addEntity((Entity)this.proj);
/*  48 */     this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.0F);
/*     */     
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void duringContinuityEvent(PlayerEntity player, int timer) {
/*  56 */     if ((this.proj == null || !this.proj.isAlive()) && this.grabbedEntity == null) {
/*     */       
/*  58 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*  62 */     if (this.grabbedEntity != null && !this.grabbedEntity.isAlive()) {
/*     */       
/*  64 */       endContinuity(player);
/*     */       return;
/*     */     } 
/*  67 */     if (this.grabbedEntity != null) {
/*     */       
/*  69 */       this.grabbedEntity.rotationPitch = this.grabbedEntity.prevRotationPitch;
/*  70 */       this.grabbedEntity.rotationYaw = this.grabbedEntity.prevRotationYaw;
/*  71 */       this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*     */       
/*  73 */       double distance = 7.0D;
/*  74 */       Vec3d lookVec = player.getLookVec();
/*  75 */       Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + (player.getEyeHeight() / 2.0F) + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/*  76 */       if (!player.world.getBlockState(new BlockPos(pos)).isSolid()) {
/*  77 */         this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
/*     */       }
/*  79 */       this.grabbedEntity.fallDistance = 0.0F;
/*     */     } 
/*  81 */     player.addPotionEffect(new EffectInstance(ModEffects.NO_HANDS, 2, 0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEndContinuityEvent(PlayerEntity player) {
/*  87 */     if (!super.onEndContinuityEvent(player)) {
/*  88 */       return false;
/*     */     }
/*  90 */     if (this.grabbedEntity == null) {
/*  91 */       setMaxCooldown(0.0D);
/*     */     } else {
/*  93 */       setMaxCooldown(15.0D);
/*     */     } 
/*  95 */     this.grabbedEntity = null;
/*  96 */     this.proj = null;
/*     */     
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 104 */     return (ZoanInfo)BaraCircusZoanInfo.INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grabEntity(LivingEntity target) {
/* 109 */     this.grabbedEntity = target;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\KuchuKirimomiDaiCircusAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */