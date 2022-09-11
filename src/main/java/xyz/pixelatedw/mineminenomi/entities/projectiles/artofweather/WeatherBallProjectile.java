package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class WeatherBallProjectile
  extends AbilityProjectileEntity {
  public WeatherBallProjectile(EntityType type, World world) {
    super(type, world);
  }
  protected Item weaponUsed;
  
  public WeatherBallProjectile(EntityType type, World world, double x, double y, double z) {
    super(type, world, x, y, z);
  }

  
  public WeatherBallProjectile(EntityType type, World world, LivingEntity player) {
    super(type, world, player);
    
    setMaxLife(300);
  }


  
  public void tick() {
    super.tick();
    
    setMotion((getMotion()).x / 1.5D, (getMotion()).y, (getMotion()).z / 1.5D);
    if (this.ticksExisted < 100) {
      getMotion().add(0.0D, 0.25D, 0.0D);
    } else {
      setMotion(0.0D, 0.0D, 0.0D);
    } 
    if (this.world.isRemote || getThrower() == null || this.ticksExisted < 70) {
      return;
    }
    List<WeatherCloudEntity> clouds = WyHelper.getEntitiesNear(getPosition(), this.world, 5.0D, new Class[] { WeatherCloudEntity.class });
    
    if (clouds.size() > 0) {
      
      ((WeatherCloudEntity)clouds.get(0)).addWeatherBall(this);
      remove();
      
      return;
    } 
    if (this instanceof CoolBallProjectile) {
      
      List<HeatBallProjectile> heatBalls = WyHelper.getEntitiesNear(getPosition(), this.world, 4.0D, new Class[] { HeatBallProjectile.class });
      IAbilityData props = AbilityDataCapability.get(getThrower());
      WeatherCloudTempo ability = (WeatherCloudTempo)props.getUnlockedAbility((Ability)WeatherCloudTempo.INSTANCE);
      boolean canUseAbility = (ability != null && ability.canUseTempo((PlayerEntity)getThrower(), (player, check) -> (heatBalls.size() > 0)));



      
      if (canUseAbility) {
        
        for (HeatBallProjectile heatBall : heatBalls) {
          
          ability.use((PlayerEntity)getThrower());
          
          WeatherCloudEntity cloud = new WeatherCloudEntity(this.world);
          cloud.setLocationAndAngles(getPosX(), getPosY() + 1.0D, getPosZ(), 0.0F, 0.0F);
          cloud.setMotion(0.0D, 0.0D, 0.0D);
          cloud.setThrower(getThrower());
          this.world.addEntity((Entity)cloud);
          
          heatBall.remove();
        } 
        remove();
      } 
    } 
  }

  
  public Item getWeaponUsed() {
    return this.weaponUsed;
  }
}


