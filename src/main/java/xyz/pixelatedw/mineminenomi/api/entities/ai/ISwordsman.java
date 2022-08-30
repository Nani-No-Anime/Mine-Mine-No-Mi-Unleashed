/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.OTatsumakiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.YakkodoriGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public interface ISwordsman
/*    */ {
/* 15 */   public static final List<AIAbilityEntry> ABILITIES_POOL = new ArrayList<>();
/*    */ 
/*    */   
/*    */   default void addSwordsmanAbilities(OPEntity entity, int max) {
/* 19 */     if (entity.world.isRemote) {
/*    */       return;
/*    */     }
/* 22 */     if (ABILITIES_POOL.isEmpty()) {
/*    */       
/* 24 */       ABILITIES_POOL.add(new AIAbilityEntry(YakkodoriAbility.INSTANCE.getName().toLowerCase(), 4, e -> new YakkodoriGoal(e)));
/* 25 */       ABILITIES_POOL.add(new AIAbilityEntry(OTatsumakiAbility.INSTANCE.getName().toLowerCase(), 2, e -> new OTatsumakiGoal(e)));
/*    */     } 
/*    */     
/* 28 */     int abilitiesCount = 0;
/* 29 */     List<String> goals = new ArrayList<>();
/*    */     
/* 31 */     while (abilitiesCount < max) {
/*    */       
/* 33 */       for (AIAbilityEntry entry : ABILITIES_POOL) {
/*    */         
/* 35 */         if (!goals.contains(entry.getId()) && WyHelper.randomWithRange(1, 10) <= entry.getChance()) {
/*    */           
/* 37 */           entity.goalSelector.addGoal(1, entry.getGoal(entity));
/* 38 */           abilitiesCount++;
/* 39 */           goals.add(entry.getId());
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 44 */       abilitiesCount++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\ISwordsman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */