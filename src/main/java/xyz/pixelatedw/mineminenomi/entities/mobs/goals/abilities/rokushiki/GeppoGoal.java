/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.projectile.ThrowableEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GeppoGoal
/*    */   extends CooldownGoal {
/*    */   private OPEntity entity;
/*    */   
/*    */   public GeppoGoal(OPEntity entity) {
/* 17 */     super(entity, 80, (int)WyHelper.randomWithRange(2, 7));
/* 18 */     this.entity = entity;
/* 19 */     this.entity.addThreat(4);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 25 */     if (!super.shouldExecute()) {
/* 26 */       return false;
/*    */     }
/* 28 */     this.entity.fallDistance = 0.0F;
/*    */     
/* 30 */     List<ThrowableEntity> dangers = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 3.0D, new Class[] { ThrowableEntity.class });
/*    */     
/* 32 */     if (dangers.size() > 0) {
/*    */       
/* 34 */       if (this.cooldown > this.maxCooldown / 2 && this.cooldown < this.maxCooldown) {
/* 35 */         return false;
/*    */       }
/* 37 */       execute();
/* 38 */       return true;
/*    */     } 
/*    */     
/* 41 */     if (this.entity.getAttackTarget() == null) {
/* 42 */       return false;
/*    */     }
/* 44 */     float distance = this.entity.getDistance((Entity)this.entity.getAttackTarget());
/* 45 */     if (distance > 5.0F && this.entity.getHealth() > this.entity.getMaxHealth() / 4.0F) {
/* 46 */       return false;
/*    */     }
/* 48 */     execute();
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {
/* 55 */     super.endCooldown();
/* 56 */     this.entity.setCurrentGoal(null);
/* 57 */     this.entity.setPreviousGoal((Goal)this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute() {
/* 62 */     this.entity.setMotion(0.0D, 1.3D, 0.0D);
/*    */     
/* 64 */     GeppoAbility.PARTICLES.spawn(this.entity.world, this.entity.getPosX(), this.entity.getPosY(), this.entity.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 66 */     this.entity.setCurrentGoal((Goal)this);
/* 67 */     setOnCooldown(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\rokushiki\GeppoGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */