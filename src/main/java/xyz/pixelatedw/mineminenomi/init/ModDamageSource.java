/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class ModDamageSource
/*     */   extends DamageSource {
/*  14 */   public static final ModDamageSource FIRE = new ModDamageSource("onFire", false, false, false);
/*  15 */   public static final ModDamageSource LIGHTNING_BOLT = new ModDamageSource("lightningBolt", false, false, false);
/*  16 */   public static final ModDamageSource MAGMA = new ModDamageSource("lava", false, true, false);
/*  17 */   public static final ModDamageSource SPECIAL = new ModDamageSource("special", false, false, false);
/*  18 */   public static final DamageSource DEVILS_CURSE = (new ModDamageSource("devils_curse")).setDamageIsAbsolute();
/*     */   
/*     */   private boolean markSlashDamage = false;
/*     */   
/*     */   private boolean isInternal = false;
/*     */   private boolean isLogiaInvulBypass = false;
/*     */   
/*     */   public ModDamageSource(String damageTypeIn, boolean bypassArmor, boolean isFireDamage, boolean isExplosive) {
/*  26 */     super(damageTypeIn);
/*     */     
/*  28 */     if (bypassArmor)
/*  29 */       setDamageBypassesArmor(); 
/*  30 */     if (isFireDamage)
/*  31 */       setFireDamage(); 
/*  32 */     if (isExplosive)
/*  33 */       setExplosion(); 
/*     */   }
/*     */   
/*     */   public ModDamageSource(String damageType) {
/*  37 */     this(damageType, false, false, false);
/*     */   }
/*     */   
/*     */   public ModDamageSource causeIndirectDamageFromSource(ThrowableEntity entity) {
/*  41 */     ModIndirectEntityDamageSource modIndirectEntityDamageSource = new ModIndirectEntityDamageSource(getDamageType(), (Entity)entity, (Entity)entity.getThrower());
/*  42 */     if (isFireDamage())
/*  43 */       modIndirectEntityDamageSource.setFireDamage(); 
/*  44 */     if (isExplosion())
/*  45 */       modIndirectEntityDamageSource.setExplosion(); 
/*  46 */     if (isUnblockable()) {
/*  47 */       modIndirectEntityDamageSource.setDamageBypassesArmor();
/*     */     }
/*  49 */     return (ModDamageSource)modIndirectEntityDamageSource;
/*     */   }
/*     */   
/*     */   public ModDamageSource causeEntityDamageFromSource(Entity entity) {
/*  53 */     ModEntityDamageSource modEntityDamageSource = new ModEntityDamageSource(getDamageType(), entity);
/*  54 */     return setSourceProprieties((ModDamageSource)modEntityDamageSource);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource setDamageBypassingLogiaInvulnerability() {
/*  59 */     this.isLogiaInvulBypass = true;
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDamageBypassingLogiaInvulnerability() {
/*  65 */     return this.isLogiaInvulBypass;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSlashDamage() {
/*  70 */     return this.markSlashDamage;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource markDamageAsSlash() {
/*  75 */     this.markSlashDamage = true;
/*  76 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInternalDamage() {
/*  81 */     return this.isInternal;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource setInternalDamage() {
/*  86 */     this.isInternal = true;
/*  87 */     setDamageBypassesArmor();
/*  88 */     return this;
/*     */   }
/*     */   
/*     */   public ModDamageSource getSource() {
/*  92 */     return setSourceProprieties(new ModDamageSource(getDamageType()));
/*     */   }
/*     */   
/*     */   private ModDamageSource setSourceProprieties(ModDamageSource s) {
/*  96 */     if (isFireDamage())
/*  97 */       s.setFireDamage(); 
/*  98 */     if (isExplosion())
/*  99 */       s.setExplosion(); 
/* 100 */     if (isUnblockable())
/* 101 */       s.setDamageBypassesArmor(); 
/* 102 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModDamageSource setProjectile() {
/* 107 */     return setSourceProprieties((ModDamageSource)super.setProjectile());
/*     */   }
/*     */ 
/*     */   
/*     */   public static AbilityDamageSource causeAbilityDamage(LivingEntity player, Ability ability) {
/* 112 */     return new AbilityDamageSource("ability", (Entity)player, ability);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbilityDamageSource causeAbilityDamage(LivingEntity player, Ability ability, String damageType) {
/* 118 */     return new AbilityDamageSource(damageType, (Entity)player, ability);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */