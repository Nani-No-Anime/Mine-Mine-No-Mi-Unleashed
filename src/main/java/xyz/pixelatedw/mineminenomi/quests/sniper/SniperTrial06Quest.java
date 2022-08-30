/*    */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
import java.util.function.Predicate;

/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class SniperTrial06Quest extends Quest {
/*    */   private static final Predicate<ItemStack> IMPACT_DIAL_BOW;
/*    */   
/*    */   static {
/* 24 */     IMPACT_DIAL_BOW = (itemStack -> (itemStack.getItem() == ModBlocks.IMPACT_DIAL.asItem()));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     DETONATE_CREEPER_CHECK = ((player, target, source, amount) -> 
/*    */       
/* 31 */       (target.getType() == EntityType.CREEPER && source.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KaenBoshiProjectile));
/*    */   }
/*    */   private static final HitEntityObjective.ICheckHit DETONATE_CREEPER_CHECK;
/* 34 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s impact dials", 3, IMPACT_DIAL_BOW);
/* 35 */   private Objective objective02 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 5, SharedKillChecks.HAS_BOW);
/* 36 */   private Objective objective03 = (Objective)new HitEntityObjective("Detonate %s Creepers using Kaen Boshi", 3, DETONATE_CREEPER_CHECK);
/*    */ 
/*    */   
/*    */   public SniperTrial06Quest() {
/* 40 */     super("sniper_trial_06", "Trial: Renpatsu Namari Boshi");
/* 41 */     addRequirements(new Quest[] { ModQuests.SNIPER_TRIAL_05 });
/* 42 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 44 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean giveReward(PlayerEntity player) {
/* 49 */     if (!removeQuestItem(player, ModBlocks.IMPACT_DIAL.asItem(), 3)) {
/* 50 */       return false;
/*    */     }
/* 52 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 54 */     props.addUnlockedAbility((Ability)RenpatsuNamariBoshiAbility.INSTANCE);
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial06Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */