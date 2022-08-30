/*    */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TetsuBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TokuyoAburaBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class SniperTrial03Quest extends Quest {
/*    */   static {
/* 19 */     JUMPSHOT_CHECK = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.getHeldItemMainhand();
/*    */         
/* 22 */         return (ItemsHelper.isBow(heldItem) && !player.onGround);
/*    */       });
/*    */   }
/* 25 */   private static final KillEntityObjective.ICheckKill JUMPSHOT_CHECK; private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s ink sacs", 40, Items.INK_SAC);
/* 26 */   private Objective objective02 = (Objective)new ObtainItemObjective("Collect %s dried kelp", 30, Items.DRIED_KELP);
/* 27 */   private Objective objective03 = (Objective)new ObtainItemObjective("Collect %s iron ingots", 30, Items.IRON_INGOT);
/* 28 */   private Objective objective04 = (new KillEntityObjective("Kill %s enemies with jump shots", 20, JUMPSHOT_CHECK)).addRequirements(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */ 
/*    */   
/*    */   public SniperTrial03Quest() {
/* 32 */     super("sniper_trial_03", "Trial: Hazards");
/* 33 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03, this.objective04 });
/*    */     
/* 35 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean giveReward(PlayerEntity player) {
/* 40 */     if (!removeQuestItem(player, Items.INK_SAC, 40)) {
/* 41 */       return false;
/*    */     }
/* 43 */     if (!removeQuestItem(player, Items.DRIED_KELP, 30)) {
/* 44 */       return false;
/*    */     }
/* 46 */     if (!removeQuestItem(player, Items.IRON_INGOT, 30)) {
/* 47 */       return false;
/*    */     }
/* 49 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 51 */     props.addUnlockedAbility(TokuyoAburaBoshiAbility.INSTANCE);
/* 52 */     props.addUnlockedAbility(TetsuBoshiAbility.INSTANCE);
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */