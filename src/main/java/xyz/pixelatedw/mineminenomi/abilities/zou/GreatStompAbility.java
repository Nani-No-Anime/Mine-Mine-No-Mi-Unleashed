package xyz.pixelatedw.mineminenomi.abilities.zou;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.List;

public class GreatStompAbility
  extends Ability implements IFormRequiredAbility {
  public static final GreatStompAbility INSTANCE = new GreatStompAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GreatStompParticleEffect();

  
  public GreatStompAbility() {
    super("Great Stomp", AbilityHelper.getDevilFruitCategory());
    setDescription("By stomping the ground as a full-form elephant, the user creates a shockwave");
    setMaxCooldown(12.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    for (LivingEntity entity : targets) {
      
      entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
      entity.setPositionAndUpdate(entity.getPosX(), entity.getPosY() + 3.0D, entity.getPosZ());
    } 
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)ZouGuardZoanInfo.INSTANCE };
  }
}


