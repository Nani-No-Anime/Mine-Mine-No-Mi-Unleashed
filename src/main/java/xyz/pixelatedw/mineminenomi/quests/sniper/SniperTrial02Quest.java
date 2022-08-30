/*    */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.CureEffectObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class SniperTrial02Quest extends Quest {
/*    */   static {
/* 20 */     KAEN_BOSHI_HIT_CHECK = ((player, target, source, amount) -> 
/*    */       
/* 22 */       (source.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KaenBoshiProjectile && !target.isImmuneToFire()));
/*    */   }
/*    */   private static final HitEntityObjective.ICheckHit KAEN_BOSHI_HIT_CHECK;
/* 25 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s spider eyes", 30, Items.SPIDER_EYE);
/* 26 */   private Objective objective02 = (new HitEntityObjective("Set %s enemies on fire using Kaen Boshi", 20, KAEN_BOSHI_HIT_CHECK)).addRequirement(this.objective01);
/* 27 */   private Objective objective03 = (new CureEffectObjective("Cure yourself of Poison", 1, Effects.POISON)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public SniperTrial02Quest() {
/* 31 */     super("sniper_trial_01", "Trial: Kemuri Boshi");
/* 32 */     addRequirements(new Quest[] { ModQuests.SNIPER_TRIAL_01 });
/* 33 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 35 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 40 */     if (!removeQuestItem(player, Items.SPIDER_EYE, 30)) {
/* 41 */       return false;
/*    */     }
/* 43 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 45 */     props.addUnlockedAbility(KemuriBoshiAbility.INSTANCE);
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */