package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
import java.awt.Color;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.WeatherCloudChargedParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.WeatherCloudParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class WeatherCloudEntity extends EntityCloud {
  private static final ParticleEffect PARTICLES1 = (ParticleEffect)new WeatherCloudParticleEffect();
  private static final ParticleEffect PARTICLES2 = (ParticleEffect)new WeatherCloudChargedParticleEffect();
  
  private List<WeatherBallProjectile> weatherBalls = new ArrayList<>();
  
  private boolean charged = false;
  private boolean superCharged = false;
  
  public WeatherCloudEntity(World world) {
    super(world);
    setLife(200);
  }


  
  public void tick() {
    super.tick();
    
    if (!this.world.isRemote) {
      
      IAbilityData props = AbilityDataCapability.get(getThrower());
      
      if (this.ticksExisted % 2 == 0) {
        
        PARTICLES1.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
        if (this.charged || this.superCharged) {
          PARTICLES2.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
        }
      } 
      if (getLife() <= 0 || getThrower() == null) {
        remove();
      }
      setLife(getLife() - 1);

      
      if (this.charged) {
        
        List<LivingEntity> targets = WyHelper.getEntitiesNear(getPosition().down(15), this.world, 9.0D, new Class[] { LivingEntity.class });
        targets.remove(getThrower());
        targets = WyHelper.shuffle(targets);
        
        for (LivingEntity entity : targets) {
          
          double l = Math.sqrt(getDistanceSq(entity.getPosX(), entity.getPosY(), entity.getPosZ()));
          if (entity.getPosY() <= getPosY() && this.ticksExisted % 100 == 0 && entity.canEntityBeSeen((Entity)this)) {
            
            LightningEntity bolt = new LightningEntity((Entity)getThrower(), entity.getPosX(), entity.getPosY() + 14.0D, entity.getPosZ(), 0.0F, 90.0F, (float)(l + 1.0D), 5.0F);
            bolt.setAngle(30);
            bolt.setBranches(6);
            bolt.setSegments(15);
            bolt.setColor(new Color(253, 208, 35, 205));
            bolt.setSize(this.superCharged ? 0.05F : 0.035F);
            bolt.setExplosion(this.superCharged ? 1 : 0, false);
            bolt.setDamage(this.superCharged ? 20.0F : 10.0F);
            bolt.setAliveTicks(this.superCharged ? 20 : 10);
            this.world.addEntity((Entity)bolt);

            
            if (!this.superCharged)
              break; 
            remove();
          } 
        } 
      } 
      
      List<WeatherBallProjectile> thunderBalls = (List<WeatherBallProjectile>)this.weatherBalls.stream().filter(ball -> ball instanceof ThunderBallProjectile).collect(Collectors.toList());
      List<WeatherBallProjectile> coolBalls = (List<WeatherBallProjectile>)this.weatherBalls.stream().filter(ball -> ball instanceof CoolBallProjectile).collect(Collectors.toList());


      
      ThunderstormTempo thunderstormTempo = (ThunderstormTempo)props.getUnlockedAbility((Ability)ThunderstormTempo.INSTANCE);
      boolean canUseAbility = (thunderstormTempo != null && !thunderstormTempo.isOnCooldown() && thunderstormTempo.canUseTempo((PlayerEntity)getThrower(), (player, check) -> {
            if (!ItemsHelper.isClimaTact(getThrower().getHeldItemMainhand())) {
              return false;
            }
            ClimaTactItem climaTact = (ClimaTactItem)getThrower().getHeldItemMainhand().getItem();
            return (climaTact.getLevel() >= 2 && !this.superCharged && this.charged && thunderBalls.size() >= 3);
          }));
      
      if (canUseAbility) {
        
        thunderstormTempo.use((PlayerEntity)getThrower());
        this.superCharged = true;
        thunderstormTempo.startCooldown((PlayerEntity)getThrower());
        
        return;
      } 
      
      ThunderboltTempo thunderboltTempo = (ThunderboltTempo)props.getUnlockedAbility((Ability)ThunderboltTempo.INSTANCE);
      canUseAbility = (thunderboltTempo != null && !thunderboltTempo.isOnCooldown() && thunderboltTempo.canUseTempo((PlayerEntity)getThrower(), (player, check) -> 
          
          (thunderBalls.size() > 0 && !this.charged)));

      
      if (canUseAbility) {
        
        thunderboltTempo.use((PlayerEntity)getThrower());
        this.charged = true;
        int extraLife = 0;
        for (WeatherBallProjectile ball : thunderBalls) {
          
          ball.remove();
          extraLife += 200;
        } 
        thunderboltTempo.startCooldown((PlayerEntity)getThrower());
        setLife(getLife() + extraLife);
      } 

      
      RainTempo rainTempo = (RainTempo)props.getUnlockedAbility((Ability)RainTempo.INSTANCE);
      canUseAbility = (rainTempo != null && rainTempo.canUseTempo((PlayerEntity)getThrower(), (player, check) -> (coolBalls.size() >= 3)));



      
      if (canUseAbility) {
        
        rainTempo.use((PlayerEntity)getThrower());
        for (WeatherBallProjectile cb : coolBalls)
        {
          cb.remove();
        }
        remove();
      } 
    } 
  }

  
  public boolean isCharged() {
    return this.charged;
  }

  
  public boolean isSuperCharged() {
    return this.superCharged;
  }

  
  public void addWeatherBall(WeatherBallProjectile ball) {
    this.weatherBalls.add(ball);
  }
}


