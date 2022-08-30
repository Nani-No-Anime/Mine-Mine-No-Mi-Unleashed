/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.GenkotsuMeteorGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.TatsuMakiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public interface IBrawler
/*    */ {
/* 18 */   public static final List<AIAbilityEntry> ABILITIES_POOL = new ArrayList<>();
/*    */ 
/*    */   
/*    */   default void addBrawlerAbilities(OPEntity entity, int max) {
/* 22 */     if (entity.world.isRemote) {
/*    */       return;
/*    */     }
/* 25 */     if (ABILITIES_POOL.isEmpty()) {
/*    */       
/* 27 */       ABILITIES_POOL.add(new AIAbilityEntry(HakaiHoAbility.INSTANCE.getName().toLowerCase(), 3, e -> new HakaiHoGoal(e)));
/* 28 */       ABILITIES_POOL.add(new AIAbilityEntry(GenkotsuMeteorAbility.INSTANCE.getName().toLowerCase(), 5, e -> new GenkotsuMeteorGoal(entity)));
/* 29 */       ABILITIES_POOL.add(new AIAbilityEntry(JishinHoAbility.INSTANCE.getName().toLowerCase(), 1, e -> new JishinHoGoal(entity)));
/*    */       
/* 31 */       ABILITIES_POOL.add(new AIAbilityEntry("tatsu maki", 3, e -> new TatsuMakiGoal(entity)));
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
/* 43 */           entity.goalSelector.addGoal(3, entry.getGoal(entity));
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


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\IBrawler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */