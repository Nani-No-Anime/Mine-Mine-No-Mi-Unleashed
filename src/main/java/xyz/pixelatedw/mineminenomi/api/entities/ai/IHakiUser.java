/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuFullBodyHakiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.HaoshokuHakiGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public interface IHakiUser
/*    */ {
/*    */   default void addBusoshokuHaki(OPEntity entity, int chance) {
/* 13 */     if (entity.world.isRemote) {
/*    */       return;
/*    */     }
/* 16 */     if (chance > WyHelper.randomWithRange(0, 100))
/*    */     {
/* 18 */       if ((chance / 3) > WyHelper.randomWithRange(0, 100)) {
/* 19 */         entity.goalSelector.addGoal(1, (Goal)new BusoshokuFullBodyHakiGoal(entity));
/*    */       } else {
/* 21 */         entity.goalSelector.addGoal(1, (Goal)new BusoshokuHakiGoal(entity));
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   default void addHaoshokuHaki(OPEntity entity, int chance) {
/* 27 */     if (entity.world.isRemote) {
/*    */       return;
/*    */     }
/* 30 */     if (chance > WyHelper.randomWithRange(0, 100))
/*    */     {
/* 32 */       entity.goalSelector.addGoal(1, (Goal)new HaoshokuHakiGoal(entity, (float)WyHelper.randomWithRange(0, 100)));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\IHakiUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */