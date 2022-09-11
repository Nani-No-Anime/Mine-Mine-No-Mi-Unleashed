package xyz.pixelatedw.mineminenomi.quests.artofweather;




import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.GustSwordAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.WeatherEggAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderLanceTempo;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class ArtOfWeatherTrial04Quest extends Quest {
  static {
    LIGHTNING_BOLT_KILL_CHECK = ((player, target, source) -> {
        boolean isSourceGood = (source.getDamageType() == ModDamageSource.LIGHTNING_BOLT.getDamageType());
        
        ThunderLanceTempo tempo = (ThunderLanceTempo)AbilityDataCapability.get((LivingEntity)player).getUnlockedAbility((Ability)ThunderLanceTempo.INSTANCE);
        boolean isThunderlance = (tempo != null && tempo.isOnCooldown());
        return (isSourceGood && isThunderlance);
      });
    
    THUNDERSTORM_TEMPO_KILL_CHECK = ((player, target, source) -> (source.getDamageType() == ModDamageSource.LIGHTNING_BOLT.getDamageType()));
  }

  
  private static final KillEntityObjective.ICheckKill LIGHTNING_BOLT_KILL_CHECK;
  
  private static final KillEntityObjective.ICheckKill THUNDERSTORM_TEMPO_KILL_CHECK;
  private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using Thunderlance Tempo", 10, LIGHTNING_BOLT_KILL_CHECK);
  private Objective objective02 = (Objective)new TimedKillEntityObjective("Kill at least %s enemies using Thunderstorm Tempo at the same time", 5, 5, THUNDERSTORM_TEMPO_KILL_CHECK);

  
  public ArtOfWeatherTrial04Quest() {
    super("art_of_weather_trial_04", "Trial: Sorcery Clima Tact");
    addObjectives(new Objective[] { this.objective01, this.objective02 });
    addRequirement(ModQuests.ART_OF_WEATHER_TRIAL_03);
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)ModWeapons.SORCERY_CLIMA_TACT));
    
    props.addUnlockedAbility((Ability)GustSwordAbility.INSTANCE);
    props.addUnlockedAbility((Ability)WeatherEggAbility.INSTANCE);
    
    return true;
  }
}


