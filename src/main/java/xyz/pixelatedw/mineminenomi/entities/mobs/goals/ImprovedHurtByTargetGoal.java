/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityPredicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ 
/*    */ 
/*    */ public class ImprovedHurtByTargetGoal
/*    */   extends HurtByTargetGoal
/*    */ {
/*    */   @Nullable
/*    */   private Predicate<Entity> factionPredicate;
/*    */   
/*    */   public ImprovedHurtByTargetGoal(OPEntity entity, @Nullable Predicate<Entity> factionPredicate, Class<?>... exclude) {
/* 20 */     super(entity, exclude);
/* 21 */     this.factionPredicate = factionPredicate;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isSuitableTarget(@Nullable LivingEntity potentialTarget, EntityPredicate targetPredicate) {
/* 27 */     if (this.factionPredicate == null || this.factionPredicate.test(potentialTarget))
/* 28 */       return false; 
/* 29 */     return super.isSuitableTarget(potentialTarget, targetPredicate);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\ImprovedHurtByTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */