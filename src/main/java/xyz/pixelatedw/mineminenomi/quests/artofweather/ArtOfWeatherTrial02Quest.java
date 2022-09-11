package xyz.pixelatedw.mineminenomi.quests.artofweather;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.ThunderBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.FogTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.MirageTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.quests.objectives.UseAbilityObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class ArtOfWeatherTrial02Quest extends Quest {
  private Objective objective01 = (Objective)new UseAbilityObjective("Succesfully perform Weather Cloud Tempo 3 times", 3, (Ability)WeatherCloudTempo.INSTANCE);

  
  public ArtOfWeatherTrial02Quest() {
    super("art_of_weather_trial_02", "Trial: Thunder Ball");
    addObjectives(new Objective[] { this.objective01 });
    addRequirement(ModQuests.ART_OF_WEATHER_TRIAL_01);
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)ThunderBallAbility.INSTANCE);
    props.addUnlockedAbility((Ability)ThunderboltTempo.INSTANCE);
    props.addUnlockedAbility((Ability)RainTempo.INSTANCE);
    props.addUnlockedAbility((Ability)MirageTempo.INSTANCE);
    props.addUnlockedAbility((Ability)FogTempo.INSTANCE);
    
    return true;
  }
}


