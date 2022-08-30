/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BlockGoal extends CooldownGoal {
/*     */   private OPEntity entity;
/*     */   private int hitCount;
/*     */   private int maxCount;
/*     */   private int duration;
/*     */   private int maxDuration;
/*     */   private int randomizer;
/*     */   private float prevHealth;
/*  23 */   private UUID armorUuid = UUID.fromString("89a306f5-3d73-40ba-9332-c30fd88c204a");
/*  24 */   private AttributeModifier armorModifier = new AttributeModifier(this.armorUuid, "Armor Bonus", 2.5D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*     */ 
/*     */   
/*     */   public BlockGoal(OPEntity entity) {
/*  28 */     this(entity, 2, 3);
/*  29 */     this.entity.addThreat(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockGoal(OPEntity entity, int duration, int hitCount) {
/*  34 */     this(entity, duration, hitCount, 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockGoal(OPEntity entity, int duration, int hitCount, int random) {
/*  39 */     super(entity, 30, (int)WyHelper.randomWithRange(5, 10));
/*  40 */     this.entity = entity;
/*  41 */     this.maxDuration = duration;
/*  42 */     this.maxCount = hitCount;
/*  43 */     this.prevHealth = this.entity.getHealth();
/*  44 */     this.randomizer = random;
/*  45 */     this.entity.addThreat(1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  51 */     if (!super.shouldExecute()) {
/*  52 */       return false;
/*     */     }
/*  54 */     if (this.entity.getAttackTarget() == null) {
/*  55 */       return false;
/*     */     }
/*  57 */     if (this.entity.getHealth() < this.prevHealth) {
/*     */       
/*  59 */       this.hitCount++;
/*  60 */       this.prevHealth = this.entity.getHealth();
/*     */     } 
/*     */     
/*  63 */     float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
/*  64 */     if (distance > 3.0F) {
/*  65 */       return false;
/*     */     }
/*  67 */     if (this.hitCount < this.maxCount) {
/*  68 */       return false;
/*     */     }
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCooldown() {
/*  76 */     super.endCooldown();
/*  77 */     this.entity.setCurrentGoal(null);
/*  78 */     this.entity.setPreviousGoal((Goal)this);
/*  79 */     this.duration = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  85 */     this.entity.setAnimation(OPEntity.Animation.BLOCK.ordinal());
/*  86 */     if (this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED))
/*  87 */       this.entity.removePotionEffect(ModEffects.MOVEMENT_BLOCKED); 
/*  88 */     this.entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, this.maxDuration, 0, false, false));
/*  89 */     IAttributeInstance attr = this.entity.getAttribute(SharedMonsterAttributes.ARMOR);
/*  90 */     if (attr.hasModifier(this.armorModifier))
/*  91 */       attr.removeModifier(this.armorUuid); 
/*  92 */     attr.applyModifier(this.armorModifier);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/*  98 */     this.duration++;
/*  99 */     boolean continueExecution = false;
/*     */     
/* 101 */     if (this.duration < this.maxDuration) {
/* 102 */       continueExecution = true;
/*     */     }
/* 104 */     if (!continueExecution) {
/*     */       
/* 106 */       this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
/* 107 */       this.hitCount = 0;
/* 108 */       this.maxCount = (int)WyHelper.randomWithRange(0, this.randomizer);
/* 109 */       this.entity.setCurrentGoal((Goal)this);
/* 110 */       setOnCooldown(true);
/*     */     } 
/*     */     
/* 113 */     return continueExecution;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\BlockGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */