package xyz.pixelatedw.mineminenomi.abilities.electro;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.electro.ElectricalLunaProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectroChargingParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class ElectricalLunaAbility extends ChargeableAbility {
  public static final ElectricalLunaAbility INSTANCE = new ElectricalLunaAbility();
  private static final ElectroChargingParticleEffect PARTICLES = new ElectroChargingParticleEffect();
  
  private static final int COOLDOWN = 10;
  
  public ElectricalLunaAbility() {
    super("Electrical Luna", AbilityHelper.getRacialCategory());
    setMaxCooldown(10.0D);
    setMaxChargeTime(2.0D);
    setDescription("Discharges a lightning stream from the ground beneath the user");
    addInPool(new AbilityPool[] { AbilityPool.MINK_ELECTRO });
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int i) {
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
    boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    if (!eleclawEnabled) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_ELECLAW, new Object[0]));
      return false;
    } 
    
    SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
    boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
    setMaxChargeTime(sulongEnabled ? 1.0D : 2.0D);
    setMaxCooldown(sulongEnabled ? 5.0D : 10.0D);
    
    return true;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
    boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
    
    if (eleclawEnabled) {
      
      SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
      boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
      
      float damage = (30 + (sulongEnabled ? 30 : 0));
      int maxLife = 20 + (sulongEnabled ? 20 : 0);
      
      ElectricalLunaProjectile proj = new ElectricalLunaProjectile(player.world, (LivingEntity)player);
      proj.setDamage(damage);
      proj.setMaxLife(maxLife);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, sulongEnabled ? 3.0F : 2.0F, 1.0F);
      
      eleclawAbility.reduceUsage(player, 2);
    } 
    
    return true;
  }
}


