/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AIAbilityEntry
/*    */ {
/*    */   private String id;
/*    */   private Function<OPEntity, Goal> goal;
/*    */   private int chance;
/*    */   
/*    */   public AIAbilityEntry(String id, int chance, Function<OPEntity, Goal> goal) {
/* 16 */     this.id = id;
/* 17 */     this.chance = chance;
/* 18 */     this.goal = goal;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getId() {
/* 23 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getChance() {
/* 28 */     return this.chance;
/*    */   }
/*    */ 
/*    */   
/*    */   public Goal getGoal(OPEntity entity) {
/* 33 */     return this.goal.apply(entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\AIAbilityEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */