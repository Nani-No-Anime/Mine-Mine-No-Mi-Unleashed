package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherBallProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.DeathpiaCloudParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DeathpiaCloudEntity
  extends EntityCloud
{
  private static final ParticleEffect PARTICLES1 = (ParticleEffect)new DeathpiaCloudParticleEffect();
  
  private List<WeatherBallProjectile> weatherBalls = new ArrayList<>();
  
  private boolean superCharged = false;
  
  public DeathpiaCloudEntity(World world) {
    super(world);
    setLife(3000);
  }


  
  public void tick() {
    super.tick();
    
    if (!this.world.isRemote) {
      
      IAbilityData props = AbilityDataCapability.get(getThrower());
      
      if (this.ticksExisted % 2 == 0)
      {
        PARTICLES1.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
      }
      
      if (getLife() <= 0 || getThrower() == null) {
        remove();
      }
      setLife(getLife() - 1);



      
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
          bolt.setSize(this.superCharged ? 0.05F : 0.035F);
          bolt.setExplosion(this.superCharged ? 1 : 0, false);
          bolt.setDamage(this.superCharged ? 20.0F : 10.0F);
          bolt.setAliveTicks(this.superCharged ? 20 : 10);
          this.world.addEntity(bolt);
        } 
      } 

      
      List<WeatherBallProjectile> thunderBalls = (List<WeatherBallProjectile>)this.weatherBalls.stream().filter(ball -> ball instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.ThunderBallProjectile).collect(Collectors.toList());
      List list = (List)this.weatherBalls.stream().filter(ball -> ball instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.CoolBallProjectile).collect(Collectors.toList());
    } 
  }
}


