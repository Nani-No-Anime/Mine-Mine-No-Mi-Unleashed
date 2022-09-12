package xyz.pixelatedw.mineminenomi.abilities.pero;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class CandyManAbility extends PunchAbility {
  public static final CandyManAbility INSTANCE = new CandyManAbility();

  
  public CandyManAbility() {
    super("Candy Man", AbilityHelper.getDevilFruitCategory());
    setDescription("Traps the target inside hardened candy, immobilizing and suffocating them");
    setMaxCooldown(30.0D);
    
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    EffectInstance instance = new EffectInstance(ModEffects.CANDY_STUCK, 400, 1, false, false);
    target.addPotionEffect(instance);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
    WyHelper.spawnParticles(ParticleTypes.ENCHANT, (ServerWorld)player.world, player.getPosX(), player.getPosY() + 0.8D, player.getPosZ());
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 5.0F;
  }
}


