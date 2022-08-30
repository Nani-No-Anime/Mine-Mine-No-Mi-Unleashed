/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IMultiTargetAbility
/*    */ {
/* 17 */   public static final List<Integer> TARGETS = new ArrayList<>();
/*    */ 
/*    */   
/*    */   default void clearTargets() {
/* 21 */     TARGETS.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean isTarget(LivingEntity target) {
/* 26 */     if (!TARGETS.contains(Integer.valueOf(target.getEntityId()))) {
/*    */       
/* 28 */       TARGETS.add(Integer.valueOf(target.getEntityId()));
/* 29 */       return true;
/*    */     } 
/*    */     
/* 32 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IMultiTargetAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */