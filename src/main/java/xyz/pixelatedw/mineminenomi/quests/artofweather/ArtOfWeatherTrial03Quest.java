/*    */ package xyz.pixelatedw.mineminenomi.quests.artofweather;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderLanceTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.UseAbilityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class ArtOfWeatherTrial03Quest
/*    */   extends Quest {
/* 28 */   private static final HashMap<UUID, List<Ability>> USED_TEMPOS = new HashMap<>();
/*    */   static {
/* 30 */     UNIQUE_TEMPO_CHECK = ((player, ability) -> {
/*    */         if (ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility) {
/*    */           if (!USED_TEMPOS.containsKey(player.getUniqueID())) {
/*    */             USED_TEMPOS.put(player.getUniqueID(), new ArrayList<>());
/*    */           }
/*    */           
/*    */           List<Ability> usedAbilities = USED_TEMPOS.get(player.getUniqueID());
/*    */           
/*    */           if (!usedAbilities.contains(ability)) {
/*    */             usedAbilities.add(ability);
/*    */             
/*    */             return true;
/*    */           } 
/*    */         } 
/*    */         
/*    */         return false;
/*    */       });
/*    */     
/* 48 */     THUNDERBOLT_TEMPO_KILL_CHECK = ((player, target, source) -> (source.getDamageType() == ModDamageSource.LIGHTNING_BOLT.getDamageType()));
/*    */   }
/*    */   
/*    */   private static final UseAbilityObjective.ICheckAbilityUse UNIQUE_TEMPO_CHECK;
/*    */   private static final KillEntityObjective.ICheckKill THUNDERBOLT_TEMPO_KILL_CHECK;
/* 53 */   private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a Perfect Clima Tact", 1, (Item)ModWeapons.PERFECT_CLIMA_TACT);
/* 54 */   private Objective objective02 = (new UseAbilityObjective("Perform 3 unique Tempos", 3, UNIQUE_TEMPO_CHECK)).addRequirement(this.objective01);
/* 55 */   private Objective objective03 = (new KillEntityObjective("Kill %s enemies using Thunderbolt Tempo", 20, THUNDERBOLT_TEMPO_KILL_CHECK)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public ArtOfWeatherTrial03Quest() {
/* 59 */     super("art_of_weather_trial_03", "Trial: Tempo Training");
/* 60 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 61 */     addRequirement(ModQuests.ART_OF_WEATHER_TRIAL_02);
/*    */     
/* 63 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 68 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 70 */     props.addUnlockedAbility((Ability)ThunderstormTempo.INSTANCE);
/* 71 */     props.addUnlockedAbility((Ability)ThunderLanceTempo.INSTANCE);
/*    */     
/* 73 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\artofweather\ArtOfWeatherTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */