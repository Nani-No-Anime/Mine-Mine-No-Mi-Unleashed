package xyz.pixelatedw.mineminenomi.abilities.awa;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class SoapDefenseAbility extends ContinuousAbility implements IOnDamageAbility {
  public static final Ability INSTANCE = (Ability)new SoapDefenseAbility();

  
  public SoapDefenseAbility() {
    super("Soap Defense", AbilityHelper.getDevilFruitCategory());
    setThreshold(7.0D);
    setDescription("Protect yourself using a giant concentrated soap shield");
    addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player);
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    if (!player.isInWater()) {
      player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 4, false, false));
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 8.0D) + 3;
    setMaxCooldown(cooldown);
    return true;
  }


  
  public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
    if (source == ModDamageSource.LIGHTNING_BOLT) {
      return false;
    }
    return true;
  }
}


