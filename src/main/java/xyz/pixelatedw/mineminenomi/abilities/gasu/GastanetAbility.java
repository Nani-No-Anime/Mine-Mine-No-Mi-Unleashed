package xyz.pixelatedw.mineminenomi.abilities.gasu;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gasu.GastanetParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class GastanetAbility extends Ability {
  public static final GastanetAbility INSTANCE = new GastanetAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GastanetParticleEffect();

  
  public GastanetAbility() {
    super("Gastanet", AbilityHelper.getDevilFruitCategory());
    setDescription("The user creates an explosion by detonating gas with their castanets");
    setMaxCooldown(8.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D);
    ExplosionAbility explosion = AbilityHelper.newExplosion(null, player.world, 
        (mop.getHitVec()).x, 
        (mop.getHitVec()).y, 
        (mop.getHitVec()).z, 5.0F);
    explosion.setStaticDamage(40.0F);
    explosion.setDestroyBlocks(false);
    explosion.doExplosion();
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(new BlockPos(mop.getHitVec()), player.world, 
        ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player) ? 12.0D : 8.0D, new Class[] { LivingEntity.class });
    targets.remove(player);
    
    if (ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      targets.forEach(target -> ((ShinokuniAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)ShinokuniAbility.INSTANCE)).applyEffects(player, target));
    } else {
      targets.forEach(target -> target.addPotionEffect(new EffectInstance(Effects.POISON, 200, 5)));
    } 
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return true;
  }
}


