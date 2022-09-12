package xyz.pixelatedw.mineminenomi.abilities.zushi;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class GraviZoneAbility extends ContinuousAbility {
  public static final GraviZoneAbility INSTANCE = new GraviZoneAbility();
  private MODE activeMode = MODE.GUARD;

  
  public GraviZoneAbility() {
    super("Gravi Zone", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates an area in which entities cannot move while in GUARD mode or they get pushed back from the user while in REJECT mode\n\n§2SHIFT-USE§r: Switches between GUARD and REJECT modes");
    this.onStartContinuityEvent = this::onStartContinuity;
    this.duringContinuityEvent = this::duringContinuityEvent;
    setMaxCooldown(10.0D);
    setThreshold(8.0D);
  }

  
  private boolean onStartContinuity(PlayerEntity player) {
    if (player.isSneaking()) {
      
      if (this.activeMode == MODE.GUARD) {
        
        setThreshold(5.0D);
        this.activeMode = MODE.REJECT;
        setCustomTexture("gravi_zone_reject");
      }
      else {
        
        setThreshold(8.0D);
        this.activeMode = MODE.GUARD;
        setCustomTexture("gravi_zone");
      } 
      
      player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.activeMode, new Object[0]));
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
      
      return false;
    } 
    return true;
  }


  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    int range = 3;
    
    if (this.activeMode == MODE.GUARD) {
      
      range += 5;
      List<Entity> generalList = WyHelper.getEntitiesNear(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { Entity.class });
      generalList.remove(player);
      
      generalList.forEach(entity -> {
            entity.setPositionAndUpdate(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
            
            if (entity instanceof LivingEntity) {
              ((LivingEntity)entity).addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 0, false, false));
            }
            entity.velocityChanged = true;
          });
      gravityRing((LivingEntity)player, range, 0, false);
      gravityRing((LivingEntity)player, range - 2, 4, false);
      gravityRing((LivingEntity)player, range - 4, 8, false);
    }
    else if (this.activeMode == MODE.REJECT) {
      
      List<Entity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { Entity.class });
      list.remove(player);
      
      list.forEach(entity -> {
            boolean causedDamage = true;
            
            if (entity instanceof LivingEntity) {
              causedDamage = entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
            }
            
            if (causedDamage) {
              Vec3d dist = entity.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D).normalize();
              
              double power = 4.5D;
              
              double xSpeed = -dist.x * power;
              
              double zSpeed = -dist.z * power;
              
              entity.setMotion(-xSpeed, 0.20000000298023224D, -zSpeed);
              entity.velocityChanged = true;
            } 
          });
      gravityRing((LivingEntity)player, range + 3, 4, false);
      gravityRing((LivingEntity)player, range + 2, 2, false);
      gravityRing((LivingEntity)player, range, 0, false);
    } 
  }
  
  public static void gravityRing(LivingEntity entity, int range, int yOffset, boolean visibleOnlyFromOwner) {
    double z;
    for (z = 0.0D; z < 7.283185307179586D; z += 0.09817477042468103D) {
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
      data.setLife(12);
      data.setSize(2.0F);
      double offsetX = Math.cos(z) * range;
      double offsetZ = Math.sin(z) * range;
      data.setMotion(offsetX / 20.0D, 0.0D, offsetZ / 20.0D);
      if (visibleOnlyFromOwner) {
        
        data.setEntityID(entity.getEntityId());
        data.hideFromOthers();
      } 
      WyHelper.spawnParticles(data, (ServerWorld)entity.world, entity.getPosX() + offsetX, entity.getPosY() + 1.0D + yOffset, entity.getPosZ() + offsetZ);
    } 
  }
  
  public enum MODE
  {
    GUARD, REJECT;
  }
}


