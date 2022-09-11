package xyz.pixelatedw.mineminenomi.abilities.doru;

import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.CandleChampionZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateCustomTexturePacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class CandleChampionAbility extends ZoanAbility {
  public static final CandleChampionAbility INSTANCE = new CandleChampionAbility();
  
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("61619b09-8e41-46ac-84e0-ff6c5b432733"), (Ability)INSTANCE, "Candle Champion Attack Damage Modifier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("83b8b1b1-ffe6-408e-86ff-aaa5bd8bc18b"), (Ability)INSTANCE, "Candle Champion Attack Speed Modifier", 0.10000000149011612D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("f498db09-6b14-420c-8ed9-d34c492fe64a"), (Ability)INSTANCE, "Candle Champion Reach Modifier", 0.3D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("f7bfbd8d-adca-4468-8cc8-55c73c5c232c"), (Ability)INSTANCE, "Candle Champion Armor Modifier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("8b8b8baa-8458-4a2c-a3fe-6df55ac6b43f"), (Ability)INSTANCE, "Candle Champion Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("8521a343-3d02-4d64-bbd3-a7e272fd8b74"), (Ability)INSTANCE, "Candle Champion Fall Resistance Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public CandleChampionAbility() {
    super("Candle Champion", AbilityHelper.getDevilFruitCategory());
    setThreshold(60.0D);
    setDescription("The user covers themselves in a thick wax coating, creating a battle suit protecting the user and increasing their offensive capabilities.\n\nWhile active transforms §2Doru Doru Arts: Mori§r into §2Champ Fight§r");

    
    addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
    addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  protected boolean onStartContinuityEvent(PlayerEntity player) {
    if (!super.onStartContinuityEvent(player)) {
      return false;
    }
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    DoruDoruArtsMoriAbility moriAbility = (DoruDoruArtsMoriAbility)abilityProps.getEquippedAbility((Ability)DoruDoruArtsMoriAbility.INSTANCE);
    if (moriAbility != null) {
      
      moriAbility.enableChampionMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)moriAbility), (LivingEntity)player);
    } 
    
    return true;
  }

  
  public void duringContinuity(PlayerEntity player, int passiveTimer) {
    duringContinuityEvent(player, passiveTimer);
    if (player.isBurning()) {
      
      this.continueTime += 5;
      if (this.continueTime % 5 == 0) {
        WyNetwork.sendTo(new SUpdateEquippedAbilityPacket(player, (Ability)this), player);
      }
    } 
  }

  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    super.onEndContinuityEvent(player);
    
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    DoruDoruArtsMoriAbility moriAbility = (DoruDoruArtsMoriAbility)abilityProps.getEquippedAbility((Ability)DoruDoruArtsMoriAbility.INSTANCE);
    if (moriAbility != null) {
      
      moriAbility.disableChampionMode();
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateCustomTexturePacket(player, (Ability)moriAbility), (LivingEntity)player);
    } 
    
    int cooldown = (int)Math.round(this.continueTime / 20.0D) + 2;
    setMaxCooldown(cooldown);
    
    return true;
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)CandleChampionZoanInfo.INSTANCE;
  }
}


