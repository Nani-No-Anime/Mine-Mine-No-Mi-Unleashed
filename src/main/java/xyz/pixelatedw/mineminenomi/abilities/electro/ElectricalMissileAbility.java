package xyz.pixelatedw.mineminenomi.abilities.electro;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class ElectricalMissileAbility extends Ability implements IMultiTargetAbility {
  public static final ElectricalMissileAbility INSTANCE = new ElectricalMissileAbility();
  
  private static final int COOLDOWN = 9;
  private boolean used = false;
  
  public ElectricalMissileAbility() {
    super("Electrical Missile", AbilityHelper.getRacialCategory());
    setMaxCooldown(9.0D);
    setDescription("The user dashes forward and rapidly electrifies the enemy");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    this.used = false;
    EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
    boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
    
    if (!eleclawEnabled) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_ELECLAW, new Object[0]));
      return false;
    } 
    
    SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
    boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
    
    IEntityStats stats = EntityStatsCapability.get((LivingEntity)player);
    if (sulongEnabled)
      if (stats.isBunnyMink()) {
        setDisplayName("Comet Rabbit");
      } else if (stats.isLionMink()) {
        setDisplayName("Aka Byobu");
      } else if (stats.isDogMink()) {
        setDisplayName("Inu Odoshi");
      } else {
        setDisplayName("Electrical Missile");
      }  
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, sulongEnabled ? 5.0D : 4.0D, sulongEnabled ? 5.0D : 4.0D);
    player.setMotion(speed.x, 0.2D, speed.z);
    player.velocityChanged = true;
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    clearTargets();
    setMaxCooldown(sulongEnabled ? 4.5D : 9.0D);
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
    boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
    
    SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
    boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
    
    if (canDealDamage()) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      this.used = true;
      
      for (LivingEntity entity : list) {
        
        if (isTarget(entity) && player.canEntityBeSeen((Entity)entity) && eleclawEnabled)
        {
          float damage = 20.0F * (sulongEnabled ? 2.5F : 1.0F);
          entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this), damage);
          entity.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 40, 0, false, false, true));
        }
      
      } 
    } else if (this.used && eleclawEnabled) {
      
      eleclawAbility.reduceUsage(player, 1);
      this.used = false;
    } 
  }

  
  public boolean canDealDamage() {
    return (this.cooldown > getMaxCooldown() * 0.9D);
  }
}


