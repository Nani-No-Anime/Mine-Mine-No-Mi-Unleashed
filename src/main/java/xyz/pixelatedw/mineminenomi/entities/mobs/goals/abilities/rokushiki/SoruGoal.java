/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SoruGoal
/*    */   extends CooldownGoal {
/*    */   private OPEntity entity;
/* 16 */   private UUID soruSpeedUUID = UUID.fromString("4929edc3-45e7-4763-aecc-d478f5eadc3b");
/*    */   
/*    */   private AttributeModifier speedModifier;
/*    */   
/*    */   public SoruGoal(OPEntity entity) {
/* 21 */     super(entity, 40, (int)WyHelper.randomWithRange(5, 10));
/* 22 */     this.entity = entity;
/* 23 */     this.entity.addThreat(1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 29 */     if (!super.shouldExecute()) {
/* 30 */       return false;
/*    */     }
/* 32 */     if (isOnCooldown()) {
/*    */       
/* 34 */       IAttributeInstance soruSpeed = this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
/* 35 */       if (this.cooldown < this.maxCooldown / 2 && soruSpeed.getModifier(this.soruSpeedUUID) != null) {
/* 36 */         soruSpeed.removeModifier(this.speedModifier);
/*    */       }
/* 38 */       cooldownTick();
/* 39 */       return false;
/*    */     } 
/*    */     
/* 42 */     if (this.entity.getAttackTarget() == null) {
/* 43 */       return false;
/*    */     }
/* 45 */     float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
/* 46 */     if (distance > 15.0F && distance <= 25.0F) {
/*    */       
/* 48 */       execute(0.3D);
/* 49 */       return true;
/*    */     } 
/* 51 */     if (distance > 25.0F) {
/*    */       
/* 53 */       execute(0.4D);
/* 54 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 65 */     super.endCooldown();
/* 66 */     this.entity.setCurrentGoal(null);
/* 67 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(double level) {
/* 72 */     IAttributeInstance soruSpeed = this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
/* 73 */     if (soruSpeed.getModifier(this.soruSpeedUUID) != null && this.speedModifier != null) {
/* 74 */       soruSpeed.removeModifier(this.speedModifier);
/*    */     }
/* 76 */     this.speedModifier = new AttributeModifier(this.soruSpeedUUID, "Soru Speed", level, AttributeModifier.Operation.ADDITION);
/*    */     
/* 78 */     this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(this.speedModifier);
/* 79 */     this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(this.speedModifier);
/*    */     
/* 81 */     this.entity.setCurrentGoal((Goal)this);
/* 82 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\rokushiki\SoruGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */