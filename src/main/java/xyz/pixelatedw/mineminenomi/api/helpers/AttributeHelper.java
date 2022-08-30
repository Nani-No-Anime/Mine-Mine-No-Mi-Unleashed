/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ 
/*    */ public class AttributeHelper
/*    */ {
/*    */   public static double getAttackRangeDistance(LivingEntity entity, double baseReachDistance) {
/* 11 */     IAttributeInstance reachDistance = entity.getAttribute(ModAttributes.ATTACK_RANGE);
/* 12 */     return (reachDistance != null) ? (baseReachDistance + reachDistance.getValue()) : baseReachDistance;
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getSquaredAttackRangeDistance(LivingEntity entity, double sqBaseReachDistance) {
/* 17 */     double reachDistance = getAttackRangeDistance(entity, Math.sqrt(sqBaseReachDistance));
/* 18 */     return reachDistance * reachDistance;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\AttributeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */