package xyz.pixelatedw.mineminenomi.abilities.hana;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateCustomTexturePacket;
import xyz.pixelatedw.mineminenomi.particles.effects.hana.BloomParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class MilFleurAbility extends ContinuousAbility {
  public static final MilFleurAbility INSTANCE = new MilFleurAbility();
  public static final BloomParticleEffect PARTICLES = new BloomParticleEffect();

  
  public MilFleurAbility() {
    super("Mil Fleur", AbilityHelper.getDevilFruitCategory());
    setDescription("While active all the other abilities of this fruit will transform, either allowing for area of effects or bigger and better versions of themselves.");
    setThreshold(30.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    DosFleurClutchAbility clutchAbility = (DosFleurClutchAbility)abilityProps.getEquippedAbility(DosFleurClutchAbility.INSTANCE);
    if (clutchAbility != null) {
      
      clutchAbility.enableMilFleurMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, clutchAbility), (LivingEntity)player);
    } 
    
    SeisFleurSlapAbility slapAbility = (SeisFleurSlapAbility)abilityProps.getEquippedAbility(SeisFleurSlapAbility.INSTANCE);
    if (slapAbility != null) {
      
      slapAbility.enableMilFleurMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, slapAbility), (LivingEntity)player);
    } 
    
    SeisFleurTwistAbility twistAbility = (SeisFleurTwistAbility)abilityProps.getEquippedAbility(SeisFleurTwistAbility.INSTANCE);
    if (twistAbility != null) {
      
      twistAbility.enableMilFleurMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, twistAbility), (LivingEntity)player);
    } 
    
    return true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    DosFleurClutchAbility clutchAbility = (DosFleurClutchAbility)abilityProps.getEquippedAbility(DosFleurClutchAbility.INSTANCE);
    if (clutchAbility != null) {
      
      clutchAbility.disableMilFleurMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, clutchAbility), (LivingEntity)player);
    } 
    
    SeisFleurSlapAbility slapAbility = (SeisFleurSlapAbility)abilityProps.getEquippedAbility(SeisFleurSlapAbility.INSTANCE);
    if (slapAbility != null) {
      
      slapAbility.disableMilFleurMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, slapAbility), (LivingEntity)player);
    } 
    
    SeisFleurTwistAbility twistAbility = (SeisFleurTwistAbility)abilityProps.getEquippedAbility(SeisFleurTwistAbility.INSTANCE);
    if (twistAbility != null) {
      
      twistAbility.disableMilFleurMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, twistAbility), (LivingEntity)player);
    } 
    
    int cooldown = (int)Math.round(this.continueTime / 20.0D);
    setMaxCooldown(cooldown);
    return true;
  }

  
  public static void spawnBlossomEffect(LivingEntity owner) {
    PARTICLES.spawn(owner.world, owner.getPosX(), owner.getPosY(), owner.getPosZ(), 0.0D, 0.0D, 0.0D);
    owner.world.playSound(null, owner.getPosition(), ModSounds.HANA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
  }
}


