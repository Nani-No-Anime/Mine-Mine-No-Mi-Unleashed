package xyz.pixelatedw.mineminenomi.abilities.electro;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningBallEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectricalTempesta2ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectroChargingParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class ElectricalTempestaAbility extends ChargeableAbility {
  public static final ElectricalTempestaAbility INSTANCE = new ElectricalTempestaAbility();
  
  private static final ElectroChargingParticleEffect PARTICLES1 = new ElectroChargingParticleEffect();
  private static final ElectricalTempesta2ParticleEffect PARTICLES2 = new ElectricalTempesta2ParticleEffect();
  
  private static final int COOLDOWN = 8;
  private static final double CHARGE_TIME = 1.0D;
  private LightningBallEntity ballEntity = null;

  
  public ElectricalTempestaAbility() {
    super("Electrical Tempesta", AbilityHelper.getRacialCategory());
    setMaxCooldown(8.0D);
    setMaxChargeTime(1.0D);
    setDescription("The user releases a charge of energy that deals damage to nearby enemies");
    addInPool(new AbilityPool[] { AbilityPool.MINK_ELECTRO });
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
    boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
    
    if (!eleclawEnabled) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_ELECLAW, new Object[0]));
      return false;
    } 
    
    SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
    boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
    setMaxChargeTime(1.0D);
    setMaxCooldown(sulongEnabled ? 4.0D : 8.0D);
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    player.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 4, 1, false, false));
    if (chargeTime > 10) {
      PARTICLES1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    if (this.ballEntity == null) {
      
      LightningBallEntity ball = new LightningBallEntity((Entity)player, player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
      player.world.addEntity((Entity)ball);
      this.ballEntity = ball;
    }
    else {
      
      float distance = 0.5F;
      Vec3d lookVec = player.getLookVec();
      Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() * 0.85D + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
      float percentage = 1.0F - getChargeTime() / getMaxChargeTime();
      this.ballEntity.setSize(percentage * 0.1F);
      this.ballEntity.setLightningLength(2.0F);
      this.ballEntity.setPositionAndRotation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
    } 
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (this.ballEntity != null) {
      
      this.ballEntity.remove();
      this.ballEntity = null;
    } 
    
    EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
    boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
    
    SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
    boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
    
    if (eleclawEnabled) {
      
      float damage = (20 + (sulongEnabled ? 20 : 0));
      int range = 10 * (sulongEnabled ? 2 : 1);
      
      for (int i = 0; i < 3; i++) {
        PARTICLES2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
      }
      List<LivingEntity> list = WyHelper.getEntitiesNearSphere(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      
      list.forEach(entity -> {
            boolean hit = entity.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player), damage);
            
            if (hit) {
              Vec3d dirVec = player.getPositionVec().subtract(entity.getPositionVec()).normalize();
              
              player.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 10, 0, false, false, true));
              
              entity.setMotion(-dirVec.x * 7.0D, 1.0D, -dirVec.z * 7.0D);
              entity.velocityChanged = true;
            } 
          });
      int amount = 32;
      for (int j = 0; j < amount; j++) {
        
        float boltSize = (float)WyHelper.randomWithRange(3, range);
        LightningEntity bolt = new LightningEntity((Entity)player, player.getPosX(), player.getPosY() + 0.75D, player.getPosZ(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(0, 5), boltSize, 8.0F);
        bolt.setAngle(60);
        bolt.setAliveTicks(20);
        bolt.setDamage(0.0F);
        bolt.setExplosion(0, false);
        bolt.setSize(boltSize / 600.0F);
        bolt.setBranches((int)WyHelper.randomWithRange(1, 3));
        bolt.setSegments((int)(boltSize * 0.6D));
        bolt.disableLightningMimic();
        player.world.addEntity((Entity)bolt);
      } 
      
      eleclawAbility.reduceUsage(player, 1);
    } 
    
    return true;
  }
}


