/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class TekkaiGoal extends CooldownGoal {
/*    */   private OPEntity entity;
/*    */   private int hitCount;
/*    */   private int maxCount;
/*    */   private float prevHealth;
/* 20 */   private UUID knockbackResistanceUUID = UUID.fromString("4929edc3-45e7-4763-aecc-d478f5eadc3b");
/* 21 */   private AttributeModifier knockbackModifier = new AttributeModifier(this.knockbackResistanceUUID, "Knockback Resistance", 999.0D, AttributeModifier.Operation.ADDITION);
/*    */ 
/*    */   
/*    */   public TekkaiGoal(OPEntity entity) {
/* 25 */     super(entity, 50, (int)WyHelper.randomWithRange(5, 10));
/* 26 */     this.entity = entity;
/* 27 */     this.entity.addThreat(3);
/* 28 */     this.maxCount = 2;
/* 29 */     this.prevHealth = this.entity.getHealth();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 35 */     if (!super.shouldExecute()) {
/* 36 */       return false;
/*    */     }
/* 38 */     this.entity.fallDistance = 0.0F;
/*    */     
/* 40 */     if (isOnCooldown()) {
/*    */       
/* 42 */       IAttributeInstance knockbackResistsance = this.entity.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
/* 43 */       if (this.cooldown < this.maxCooldown / 2 && knockbackResistsance.getModifier(this.knockbackResistanceUUID) != null) {
/* 44 */         knockbackResistsance.removeModifier(this.knockbackModifier);
/*    */       }
/* 46 */       cooldownTick();
/* 47 */       return false;
/*    */     } 
/*    */     
/* 50 */     if (this.entity.getAttackTarget() == null) {
/* 51 */       return false;
/*    */     }
/* 53 */     if (this.entity.getHealth() < this.prevHealth) {
/*    */       
/* 55 */       this.hitCount++;
/* 56 */       this.prevHealth = this.entity.getHealth();
/*    */     } 
/*    */     
/* 59 */     float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
/* 60 */     if (distance > 3.0F) {
/* 61 */       return false;
/*    */     }
/* 63 */     if (this.hitCount < this.maxCount) {
/* 64 */       return false;
/*    */     }
/* 66 */     execute();
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 73 */     super.endCooldown();
/* 74 */     this.entity.setCurrentGoal(null);
/* 75 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute() {
/* 80 */     if (this.entity.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getModifier(this.knockbackResistanceUUID) == null) {
/* 81 */       this.entity.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(this.knockbackModifier);
/*    */     }
/* 83 */     this.entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 70, 100));
/* 84 */     this.entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 70, 100));
/* 85 */     this.entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 70, 5));
/* 86 */     this.entity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 70, -100));
/*    */     
/* 88 */     this.hitCount = 0;
/* 89 */     this.maxCount = (int)(2.0D + WyHelper.randomWithRange(1, 3));
/* 90 */     this.entity.setCurrentGoal((Goal)this);
/* 91 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\rokushiki\TekkaiGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */