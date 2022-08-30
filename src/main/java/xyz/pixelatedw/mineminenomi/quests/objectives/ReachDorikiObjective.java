/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IReachDorikiObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ 
/*    */ public class ReachDorikiObjective
/*    */   extends Objective implements IReachDorikiObjective {
/*    */   private int doriki;
/*    */   
/*    */   public ReachDorikiObjective(String title, int doriki) {
/* 17 */     super(title);
/* 18 */     setMaxProgress(1.0D);
/* 19 */     this.doriki = doriki;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkDoriki(PlayerEntity player) {
/* 25 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 26 */     return (props.getDoriki() >= this.doriki);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedTitle() {
/* 32 */     String objectiveKey = (new TranslationTextComponent(String.format("quest.objective." + APIConfig.projectId + ".%s", new Object[] { getId() }), new Object[0])).getKey();
/* 33 */     return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf(this.doriki) })).getFormattedText();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\ReachDorikiObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */