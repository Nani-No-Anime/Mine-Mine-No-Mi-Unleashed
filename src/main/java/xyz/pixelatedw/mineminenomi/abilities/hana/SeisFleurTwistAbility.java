package xyz.pixelatedw.mineminenomi.abilities.hana;


import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
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

public class SeisFleurTwistAbility
  extends Ability implements IAnimatedAbility {
  public static final SeisFleurTwistAbility INSTANCE = new SeisFleurTwistAbility();

  
  public SeisFleurTwistAbility() {
    super("Seis Fleur: Twist", AbilityHelper.getDevilFruitCategory());
    setDescription("The six arms sprout from around a target's body and then twists it around.");
    setMaxCooldown(5.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  public boolean onUseEvent(PlayerEntity player) {
    int distance = 20;
    
    MilFleurAbility ability = (MilFleurAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)MilFleurAbility.INSTANCE);
    boolean hasAbility = (ability != null && ability.isContinuous());
    
    if (hasAbility) {
      
      distance = 30;
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, distance, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      for (LivingEntity target : targets)
      {
        twist(player, (Entity)target, 8.0F);
      }
    }
    else {
      
      HanaGenericEntity proj = new HanaGenericEntity(player.world, (LivingEntity)player);
      proj.onEntityImpactEvent = (target -> twist(player, (Entity)target, 6.0F));

      
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 6.0F, 0.0F);
      player.world.addEntity((Entity)proj);
    } 
    
    MilFleurAbility.spawnBlossomEffect((LivingEntity)player);
    
    return true;
  }

  
  private void twist(PlayerEntity player, @Nullable Entity entity, float damage) {
    if (entity != null && entity instanceof LivingEntity) {
      
      LivingEntity target = (LivingEntity)entity;
      
      target.rotationYaw += 180.0F;
      target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 1));
      EffectInstance inst = new EffectInstance(ModEffects.HANA_HANDS, 20, 0, false, false);
      target.addPotionEffect(inst);
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), inst));
      
      HanaHandsEntity clutch = new HanaHandsEntity(player.world, this);
      clutch.setWarmup(5);
      clutch.setCaster((LivingEntity)player);
      clutch.setTarget(target);
      clutch.setDamage(damage);
      player.world.addEntity((Entity)clutch);
      
      MilFleurAbility.PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
  }

  
  public void enableMilFleurMode() {
    setCustomTexture("mil_fleur_twist");
    setDisplayName("Mil Fleur: Twist");
  }

  
  public void disableMilFleurMode() {
    setCustomTexture("");
    setDisplayName("Seis Fleur: Twist");
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)CrossedArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isOnCooldown() && getCooldown() > getMaxCooldown() - 15.0D);
  }
}


