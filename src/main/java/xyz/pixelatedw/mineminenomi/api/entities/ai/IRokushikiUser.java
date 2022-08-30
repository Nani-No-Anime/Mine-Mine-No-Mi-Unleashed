/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RankyakuAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.GeppoGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.RankyakuGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.SoruGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.TekkaiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public interface IRokushikiUser
/*    */ {
/* 19 */   public static final List<AIAbilityEntry> ABILITIES_POOL = new ArrayList<>();
/*    */ 
/*    */   
/*    */   default void addRokushikiAbilities(OPEntity entity, int max) {
/* 23 */     if (entity.world.isRemote) {
/*    */       return;
/*    */     }
/* 26 */     if (ABILITIES_POOL.isEmpty()) {
/*    */       
/* 28 */       ABILITIES_POOL.add(new AIAbilityEntry(SoruAbility.INSTANCE.getName().toLowerCase(), 5, e -> new SoruGoal(e)));
/* 29 */       ABILITIES_POOL.add(new AIAbilityEntry(TekkaiAbility.INSTANCE.getName().toLowerCase(), 3, e -> new TekkaiGoal(e)));
/* 30 */       ABILITIES_POOL.add(new AIAbilityEntry(RankyakuAbility.INSTANCE.getName().toLowerCase(), 1, e -> new RankyakuGoal(e)));
/* 31 */       ABILITIES_POOL.add(new AIAbilityEntry(GeppoAbility.INSTANCE.getName().toLowerCase(), 2, e -> new GeppoGoal(e)));
/*    */     } 
/*    */     
/* 34 */     int rokushikiCount = 0;
/* 35 */     List<String> goals = new ArrayList<>();
/*    */     
/* 37 */     while (rokushikiCount < max) {
/*    */       
/* 39 */       for (AIAbilityEntry entry : ABILITIES_POOL) {
/*    */         
/* 41 */         if (!goals.contains(entry.getId()) && WyHelper.randomWithRange(1, 10) <= entry.getChance()) {
/*    */           
/* 43 */           entity.goalSelector.addGoal(1, entry.getGoal(entity));
/* 44 */           rokushikiCount++;
/* 45 */           goals.add(entry.getId());
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 50 */       rokushikiCount++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\IRokushikiUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */