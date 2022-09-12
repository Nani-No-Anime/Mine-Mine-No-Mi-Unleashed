package xyz.pixelatedw.mineminenomi.abilities.netsu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class HeatDenasshiAbility extends PunchAbility {
  public static final HeatDenasshiAbility INSTANCE = new HeatDenasshiAbility();
  private int damage = 15;

  
  public HeatDenasshiAbility() {
    super("Heat Denasshi", AbilityHelper.getDevilFruitCategory());
    setDescription("The user concentrates a high amount of heat in their fist to maximize damage");
    setMaxCooldown(5.0D);
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, target, 7);
    if (!MinecraftForge.EVENT_BUS.post(event)) {
      target.setFire(7);
    }
  }
  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    NetsuEnhancementAbility ability = (NetsuEnhancementAbility)abilityProps.getEquippedAbility((Ability)NetsuEnhancementAbility.INSTANCE);
    
    if (ability != null && ability.isContinuous()) {
      this.damage = (int)(this.damage + ability.damageToEntityWithSource(player, target));
    }
    return this.damage;
  }
}


