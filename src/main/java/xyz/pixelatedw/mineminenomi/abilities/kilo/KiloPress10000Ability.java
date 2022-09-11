package xyz.pixelatedw.mineminenomi.abilities.kilo;

import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class KiloPress10000Ability extends ContinuousAbility {
  public static final KiloPress10000Ability INSTANCE = new KiloPress10000Ability();
  
  private static final AbilityAttributeModifier KILO_PRESS_JUMP_HEIGHT = new AbilityAttributeModifier(UUID.fromString("692759d2-5d8d-4809-912d-86ad362f8f95"), (Ability)INSTANCE, "Kilo Press Jump Height Modifier", -10.0D, AttributeModifier.Operation.ADDITION);
  private static final AbilityAttributeModifier KILO_PRESS_KNOCKBACK = new AbilityAttributeModifier(UUID.fromString("f3597992-9268-4a40-9363-555cf06c7771"), (Ability)INSTANCE, "Kilo Press Knockback Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GreatStompParticleEffect();
  
  private double initialPosY = 0.0D;

  
  public KiloPress10000Ability() {
    super("10,000 Kilo Press", AbilityHelper.getDevilFruitCategory());
    setDescription("Makes the user become extremely heavy, crashing down on enemies from above crushes them");
    setThreshold(60.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier((AttributeModifier)KILO_PRESS_JUMP_HEIGHT);
    player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier((AttributeModifier)KILO_PRESS_KNOCKBACK);
    
    player.setMotion((player.getMotion()).x, -5.0D, (player.getMotion()).z);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    
    if (!player.onGround) {
      this.initialPosY = player.getPosY();
    } else {
      this.initialPosY = 0.0D;
    } 
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int time) {
    player.fallDistance = 0.0F;
    
    if (player.onGround && this.initialPosY > 0.0D && player.getPosY() < this.initialPosY) {
      
      double damage = Math.min(this.initialPosY - player.getPosY(), 80.0D);
      if (damage > 0.0D) {
        
        List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 5.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
        targets.remove(player);
        for (LivingEntity entity : targets)
        {
          entity.attackEntityFrom(DamageSource.causePlayerDamage(player), (float)damage);
        }
        this.initialPosY = 0.0D;
      } 
      
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier((AttributeModifier)KILO_PRESS_JUMP_HEIGHT);
    player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).removeModifier((AttributeModifier)KILO_PRESS_KNOCKBACK);
    
    int cooldown = (int)Math.round(this.continueTime / 20.0D);
    setMaxCooldown(cooldown);
    
    return true;
  }
}


