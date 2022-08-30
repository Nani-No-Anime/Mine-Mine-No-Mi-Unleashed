/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IUseAbilityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class UseAbilityObjective
/*    */   extends Objective
/*    */   implements IUseAbilityObjective {
/*    */   protected ICheckAbilityUse useEvent = (player, ability) -> true;
/*    */   
/*    */   public UseAbilityObjective(String title, int count) {
/* 14 */     this(title, count, (ICheckAbilityUse)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public UseAbilityObjective(String title, int count, Ability ability) {
/* 19 */     this(title, count, (player, abl) -> abl.equals(ability));
/*    */   }
/*    */ 
/*    */   
/*    */   public UseAbilityObjective(String title, int count, ICheckAbilityUse check) {
/* 24 */     super(title);
/* 25 */     setMaxProgress(count);
/* 26 */     if (check != null) {
/* 27 */       this.useEvent = check;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkAbility(PlayerEntity player, Ability ability) {
/* 33 */     return this.useEvent.test(player, ability);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface ICheckAbilityUse
/*    */   {
/*    */     default ICheckAbilityUse and(ICheckAbilityUse check) {
/* 43 */       return (player, ability) -> 
/* 44 */         (test(player, ability) && check.test(player, ability));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     default ICheckAbilityUse or(ICheckAbilityUse check) {
/* 50 */       return (player, ability) -> 
/* 51 */         (test(player, ability) || check.test(player, ability));
/*    */     }
/*    */     
/*    */     boolean test(PlayerEntity param1PlayerEntity, Ability param1Ability);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\UseAbilityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */