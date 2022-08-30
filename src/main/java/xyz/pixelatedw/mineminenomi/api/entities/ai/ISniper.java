/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.NemuriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TetsuBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KaenBoshiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KemuriBoshiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.NemuriBoshiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TetsuBoshiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public interface ISniper
/*    */ {
/* 19 */   public static final List<AIAbilityEntry> ABILITIES_POOL = new ArrayList<>();
/*    */ 
/*    */   
/*    */   default void addSniperAbilities(OPEntity opEntity, int max) {
/* 23 */     if (opEntity.world.isRemote) {
/*    */       return;
/*    */     }
/* 26 */     if (ABILITIES_POOL.isEmpty()) {
/*    */       
/* 28 */       ABILITIES_POOL.add(new AIAbilityEntry(KaenBoshiAbility.INSTANCE.getName().toLowerCase(), 5, e -> new KaenBoshiGoal(e)));
/* 29 */       ABILITIES_POOL.add(new AIAbilityEntry(KemuriBoshiAbility.INSTANCE.getName().toLowerCase(), 3, e -> new KemuriBoshiGoal(e)));
/* 30 */       ABILITIES_POOL.add(new AIAbilityEntry(TetsuBoshiAbility.INSTANCE.getName().toLowerCase(), 3, e -> new TetsuBoshiGoal(e)));
/* 31 */       ABILITIES_POOL.add(new AIAbilityEntry(NemuriBoshiAbility.INSTANCE.getName().toLowerCase(), 1, e -> new NemuriBoshiGoal(e)));
/*    */     } 
/*    */     
/* 34 */     int abilitiesCount = 0;
/* 35 */     List<String> goals = new ArrayList<>();
/*    */     
/* 37 */     while (abilitiesCount < max) {
/*    */       
/* 39 */       for (AIAbilityEntry entry : ABILITIES_POOL) {
/*    */         
/* 41 */         if (!goals.contains(entry.getId()) && WyHelper.randomWithRange(1, 10) <= entry.getChance()) {
/*    */           
/* 43 */           opEntity.goalSelector.addGoal(1, entry.getGoal(opEntity));
/* 44 */           abilitiesCount++;
/* 45 */           goals.add(entry.getId());
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 50 */       abilitiesCount++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\ISniper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */