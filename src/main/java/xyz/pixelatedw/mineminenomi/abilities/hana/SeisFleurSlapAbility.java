package xyz.pixelatedw.mineminenomi.abilities.hana;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaGenericEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaHandsEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class SeisFleurSlapAbility
  extends Ability implements IAnimatedAbility {
  public static final SeisFleurSlapAbility INSTANCE = new SeisFleurSlapAbility();

  
  public SeisFleurSlapAbility() {
    super("Seis Fleur: Slap", AbilityHelper.getDevilFruitCategory());
    setDescription("Slaps the enemy pushing them few blocks back and dealing some damage.");
    setMaxCooldown(5.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  public boolean onUseEvent(PlayerEntity player) {
    int distance = 10;
    
    MilFleurAbility ability = (MilFleurAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)MilFleurAbility.INSTANCE);
    boolean hasAbility = (ability != null && ability.isContinuous());
    
    if (hasAbility) {
      distance = 20;
    }
    int slapPower = hasAbility ? 5 : 3;
    
    if (hasAbility) {
      
      distance = 30;
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, distance, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      for (LivingEntity target : targets)
      {
        slap(player, (Entity)target, slapPower);
      }
    }
    else {
      
      HanaGenericEntity proj = new HanaGenericEntity(player.world, (LivingEntity)player);
      proj.onEntityImpactEvent = (target -> slap(player, (Entity)target, slapPower));

      
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 6.0F, 0.0F);
      player.world.addEntity((Entity)proj);
    } 
    
    MilFleurAbility.spawnBlossomEffect((LivingEntity)player);
    
    return true;
  }

  
  private void slap(PlayerEntity player, @Nullable Entity entity, float slapPower) {
    if (entity != null && entity instanceof LivingEntity) {
      
      LivingEntity target = (LivingEntity)entity;
      
      HanaHandsEntity slap = new HanaHandsEntity(player.world, this);
      slap.setWarmup(0);
      slap.setCaster((LivingEntity)player);
      slap.setTarget(target);
      slap.setDamage(6.0F);
      slap.setSlap();
      player.world.addEntity((Entity)slap);
      
      Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize();
      target.setMotion(-dirVec.x * 3.0D, player.getMotion().getY() + 0.1D, -dirVec.z * 3.0D);
      target.velocityChanged = true;
      
      EffectInstance inst = new EffectInstance(ModEffects.HANA_HANDS, 5, 0, false, false);
      target.addPotionEffect(inst);
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), inst));
      
      MilFleurAbility.PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
  }

  
  public void enableMilFleurMode() {
    setCustomTexture("mil_fleur_slap");
    setDisplayName("Mil Fleur: Slap");
  }

  
  public void disableMilFleurMode() {
    setCustomTexture("");
    setDisplayName("Seis Fleur: Slap");
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)CrossedArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isOnCooldown() && getCooldown() > getMaxCooldown() - 15.0D);
  }
}


