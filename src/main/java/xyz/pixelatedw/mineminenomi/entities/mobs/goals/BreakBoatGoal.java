/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.item.BoatEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ 
/*    */ 
/*    */ public class BreakBoatGoal
/*    */   extends Goal
/*    */ {
/*    */   private final CreatureEntity entity;
/*    */   private Entity boat;
/*    */   
/*    */   public BreakBoatGoal(CreatureEntity entity) {
/* 21 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 27 */     List<BoatEntity> list = this.entity.world.getEntitiesWithinAABB(BoatEntity.class, this.entity.getBoundingBox().grow(2.0D));
/*    */     
/* 29 */     for (BoatEntity boat : list) {
/*    */       
/* 31 */       Entity entity = boat.getControllingPassenger();
/* 32 */       if (entity != null && entity instanceof LivingEntity && boat.isAlive() && this.entity.canEntityBeSeen((Entity)boat)) {
/*    */         
/* 34 */         this.boat = (Entity)boat;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 39 */     return (this.boat != null && this.boat.isAlive());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldContinueExecuting() {
/* 45 */     return (this.boat != null && this.boat.isAlive());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 51 */     IAttributeInstance attr = this.entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/* 52 */     float damage = (float)((attr != null) ? attr.getValue() : 1.0D);
/* 53 */     this.boat.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), damage);
/* 54 */     this.boat = null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 60 */     this.boat = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\BreakBoatGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */