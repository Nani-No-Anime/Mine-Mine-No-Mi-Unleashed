/*    */ package xyz.pixelatedw.mineminenomi.quests.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;



/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.GustSwordAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.WeatherEggAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderLanceTempo;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class ArtOfWeatherTrial04Quest extends Quest {
/*    */   static {
/* 21 */     LIGHTNING_BOLT_KILL_CHECK = ((player, target, source) -> {
/*    */         boolean isSourceGood = (source.getDamageType() == ModDamageSource.LIGHTNING_BOLT.getDamageType());
/*    */         
/*    */         ThunderLanceTempo tempo = (ThunderLanceTempo)AbilityDataCapability.get((LivingEntity)player).getUnlockedAbility((Ability)ThunderLanceTempo.INSTANCE);
/* 25 */         boolean isThunderlance = (tempo != null && tempo.isOnCooldown());
/* 26 */         return (isSourceGood && isThunderlance);
/*    */       });
/*    */     
/* 29 */     THUNDERSTORM_TEMPO_KILL_CHECK = ((player, target, source) -> (source.getDamageType() == ModDamageSource.LIGHTNING_BOLT.getDamageType()));
/*    */   }
/*    */ 
/*    */   
/*    */   private static final KillEntityObjective.ICheckKill LIGHTNING_BOLT_KILL_CHECK;
/*    */   
/*    */   private static final KillEntityObjective.ICheckKill THUNDERSTORM_TEMPO_KILL_CHECK;
/* 36 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using Thunderlance Tempo", 10, LIGHTNING_BOLT_KILL_CHECK);
/* 37 */   private Objective objective02 = (Objective)new TimedKillEntityObjective("Kill at least %s enemies using Thunderstorm Tempo at the same time", 5, 5, THUNDERSTORM_TEMPO_KILL_CHECK);
/*    */ 
/*    */   
/*    */   public ArtOfWeatherTrial04Quest() {
/* 41 */     super("art_of_weather_trial_04", "Trial: Sorcery Clima Tact");
/* 42 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/* 43 */     addRequirement(ModQuests.ART_OF_WEATHER_TRIAL_03);
/*    */     
/* 45 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 50 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 52 */     player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)ModWeapons.SORCERY_CLIMA_TACT));
/*    */     
/* 54 */     props.addUnlockedAbility((Ability)GustSwordAbility.INSTANCE);
/* 55 */     props.addUnlockedAbility((Ability)WeatherEggAbility.INSTANCE);
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\artofweather\ArtOfWeatherTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */