package xyz.pixelatedw.mineminenomi.quests.artofweather;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.CoolBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.HeatBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class ArtOfWeatherTrial01Quest extends Quest {
  private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a Clima Tact", 1, (Item)ModWeapons.CLIMA_TACT);

  
  public ArtOfWeatherTrial01Quest() {
    super("art_of_weather_trial_01", "Trial: Heat Ball & Cool Ball");
    addObjectives(new Objective[] { this.objective01 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)HeatBallAbility.INSTANCE);
    props.addUnlockedAbility((Ability)CoolBallAbility.INSTANCE);
    props.addUnlockedAbility((Ability)WeatherCloudTempo.INSTANCE);
    
    return true;
  }
}


