/*    */ package xyz.pixelatedw.mineminenomi.quests.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.ThunderBallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.FogTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.MirageTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.UseAbilityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class ArtOfWeatherTrial02Quest extends Quest {
/* 19 */   private Objective objective01 = (Objective)new UseAbilityObjective("Succesfully perform Weather Cloud Tempo 3 times", 3, (Ability)WeatherCloudTempo.INSTANCE);
/*    */ 
/*    */   
/*    */   public ArtOfWeatherTrial02Quest() {
/* 23 */     super("art_of_weather_trial_02", "Trial: Thunder Ball");
/* 24 */     addObjectives(new Objective[] { this.objective01 });
/* 25 */     addRequirement(ModQuests.ART_OF_WEATHER_TRIAL_01);
/*    */     
/* 27 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 32 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 34 */     props.addUnlockedAbility((Ability)ThunderBallAbility.INSTANCE);
/* 35 */     props.addUnlockedAbility((Ability)ThunderboltTempo.INSTANCE);
/* 36 */     props.addUnlockedAbility((Ability)RainTempo.INSTANCE);
/* 37 */     props.addUnlockedAbility((Ability)MirageTempo.INSTANCE);
/* 38 */     props.addUnlockedAbility((Ability)FogTempo.INSTANCE);
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\artofweather\ArtOfWeatherTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */