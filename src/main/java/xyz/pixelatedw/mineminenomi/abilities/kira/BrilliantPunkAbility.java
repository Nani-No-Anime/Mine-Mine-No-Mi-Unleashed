package xyz.pixelatedw.mineminenomi.abilities.kira;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class BrilliantPunkAbility extends Ability implements IMultiTargetAbility {
  public static final BrilliantPunkAbility INSTANCE = new BrilliantPunkAbility();

  
  public BrilliantPunkAbility() {
    super("Brilliant Punk", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("The user rams into the target with their diamond body");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    clearTargets();
    
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    DiamondBodyAbility ability = (DiamondBodyAbility)abilityProps.getEquippedAbility((Ability)DiamondBodyAbility.INSTANCE);
    boolean diamondBodyActive = (ability != null && ability.isContinuous());
    if (!diamondBodyActive) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, new Object[] { getName(), DiamondBodyAbility.INSTANCE.getName() }));
      return false;
    } 

    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
    player.setMotion(speed.x, 0.2D, speed.z);
    player.velocityChanged = true;
    
    return true;
  }


  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage()) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      
      list.forEach(entity -> {
            if (isTarget(entity) && player.canEntityBeSeen((Entity)entity)) {
              entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
            }
          });
    } 
  }

  
  public boolean canDealDamage() {
    return (this.cooldown > getMaxCooldown() * 0.9D);
  }
}


