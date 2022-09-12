package xyz.pixelatedw.mineminenomi.abilities.brawler;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.GroundParticlesEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.List;

public class JishinHoAbility extends Ability {
  public static final JishinHoAbility INSTANCE = new JishinHoAbility();
  
  public static final ParticleEffect PARTICLES = (ParticleEffect)new GroundParticlesEffect(7, 100);

  
  public JishinHoAbility() {
    super("Jishin Ho", AbilityHelper.getStyleCategory());
    setDescription("Punches the ground to cause a quake that damages everyone around");
    setMaxCooldown(15.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
      return false;
    } 
    
    for (int i = 0; i < 30; i++) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 7.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.removeIf(entity -> !entity.onGround);
    targets.remove(player);
    
    targets.stream().filter(target -> (target != null && target.isAlive())).forEach(target -> {
          target.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this, "player"), 25.0F);
          
          target.setMotion(0.0D, 0.75D, 0.0D);
          
          target.velocityChanged = true;
        });
    return true;
  }
}


