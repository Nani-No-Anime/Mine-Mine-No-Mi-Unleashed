/*    */ package xyz.pixelatedw.mineminenomi.quests.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.CoolBallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.HeatBallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class ArtOfWeatherTrial01Quest extends Quest {
/* 16 */   private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a Clima Tact", 1, (Item)ModWeapons.CLIMA_TACT);
/*    */ 
/*    */   
/*    */   public ArtOfWeatherTrial01Quest() {
/* 20 */     super("art_of_weather_trial_01", "Trial: Heat Ball & Cool Ball");
/* 21 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 23 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 28 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 30 */     props.addUnlockedAbility((Ability)HeatBallAbility.INSTANCE);
/* 31 */     props.addUnlockedAbility((Ability)CoolBallAbility.INSTANCE);
/* 32 */     props.addUnlockedAbility((Ability)WeatherCloudTempo.INSTANCE);
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\artofweather\ArtOfWeatherTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */