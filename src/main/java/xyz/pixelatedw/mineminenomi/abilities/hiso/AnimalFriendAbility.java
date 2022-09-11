package xyz.pixelatedw.mineminenomi.abilities.hiso;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AnimalFriendAbility extends Ability {
  public static final AnimalFriendAbility INSTANCE = new AnimalFriendAbility();

  
  public AnimalFriendAbility() {
    super("Animal Friend", AbilityHelper.getDevilFruitCategory());
    setDescription("Allows the user to tame all nearby tameable entities");
    setMaxCooldown(10.0D);
    
    this.onUseEvent = this::onUseEvent;
  }


  
  private boolean onUseEvent(PlayerEntity player) {
    List<TameableEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { TameableEntity.class });
    for (TameableEntity target : targets) {
      
      target.setTamedBy(player);
      spawnHearts((LivingEntity)target);
    } 

    
    List<AbstractHorseEntity> horses = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { AbstractHorseEntity.class });
    for (AbstractHorseEntity target : horses) {
      
      target.setTamedBy(player);
      spawnHearts((LivingEntity)target);
    } 

    
    List<OcelotEntity> ocelots = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { OcelotEntity.class });
    for (OcelotEntity target : ocelots) {
      
      Method method = ObfuscationReflectionHelper.findMethod(OcelotEntity.class, "func_213528_r", new Class[] { boolean.class });
      
      try {
        method.setAccessible(true);
        method.invoke(target, new Object[] { Boolean.valueOf(true) });
      }
      catch (Exception e) {
        
        e.printStackTrace();
      } 
      spawnHearts((LivingEntity)target);
    } 
    
    return true;
  }

  
  private void spawnHearts(LivingEntity target) {
    BasicParticleType basicParticleType = ParticleTypes.HEART;
    
    for (int i = 0; i < 7; i++)
      WyHelper.spawnParticles(basicParticleType, (ServerWorld)target.world, target.getPosXRandom(1.0D), target.getPosYRandom() + 0.5D, target.getPosZRandom(1.0D)); 
  }
}


