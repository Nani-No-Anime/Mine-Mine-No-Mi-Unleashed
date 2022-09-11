package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class WeatherEggProjectile extends WeatherBallProjectile {
  public WeatherEggProjectile(World world) {
    super(ArtOfWeatherProjectiles.WEATHER_EGG, world);
  }

  
  public WeatherEggProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public WeatherEggProjectile(World world, double x, double y, double z) {
    super(ArtOfWeatherProjectiles.WEATHER_EGG, world, x, y, z);
  }

  
  public WeatherEggProjectile(World world, LivingEntity player) {
    super(ArtOfWeatherProjectiles.WEATHER_EGG, world, player);
  }

  
  public void tick() {
    super.tick();
    
    if (this.world.isRemote || getThrower() == null || this.ticksExisted < 100) {
      return;
    }
    IAbilityData props = AbilityDataCapability.get(getThrower());
    WeatherCloudTempo ability = (WeatherCloudTempo)props.getUnlockedAbility((Ability)WeatherCloudTempo.INSTANCE);
    boolean canUseAbility = (ability != null && ability.canUseTempo((PlayerEntity)getThrower(), (player, check) -> true));
    
    if (canUseAbility) {
      
      WeatherCloudEntity cloud = new WeatherCloudEntity(this.world);
      cloud.setLife(300);
      cloud.setLocationAndAngles(getPosX(), getPosY() + 1.0D, getPosZ(), 0.0F, 0.0F);
      cloud.setMotion(0.0D, 0.0D, 0.0D);
      cloud.setThrower(getThrower());
      this.world.addEntity((Entity)cloud);
    } 
    
    remove();
  }
}


