package xyz.pixelatedw.mineminenomi.abilities.gura;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class ShingenNoIchigekiAbility extends PunchAbility {
  public static final ShingenNoIchigekiAbility INSTANCE = new ShingenNoIchigekiAbility();

  
  public ShingenNoIchigekiAbility() {
    super("Shingen no Ichigeki", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("The user focuses vibrations around his fist in an spherical bubble, then releasing them in its enemies\n\n§2SHIFT-USE§r: Slams the fist into the ground pushing back all enemies");
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int time) {
    if (player.isCrouching()) {
      
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 20.0F);
      explosion.setDestroyBlocks(false);
      explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(20));
      explosion.setDamageEntities(false);
      explosion.doExplosion();
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      
      for (LivingEntity target : targets) {
        
        target.attackEntityFrom((DamageSource)AbilityDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 6.0F);
        Vec3d dirVec = player.getPositionVector().subtract(target.getPositionVector()).normalize().mul(3.0D, 3.0D, 3.0D);
        target.setMotion(-dirVec.x, 0.25D + -dirVec.y, -dirVec.z);
        target.velocityChanged = true;
      } 
      
      endContinuity(player);
    } 
  }


  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)target, target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 4.0F);
    explosion.setDestroyBlocks(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
    explosion.setDamageEntities(false);
    explosion.doExplosion();
    
    Vec3d speed = player.getLook(1.0F).normalize().mul(3.0D, 2.0D, 3.0D);
    target.setMotion(speed.x, 0.25D + speed.y, speed.z);
    target.velocityChanged = true;
    target.fallDistance = 0.0F;
    
    target.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 100, 0, false, false));
  }

  
  public float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 40.0F;
  }


  
  public DamageSource getPunchDamageSource(PlayerEntity player) {
    return ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this).setDamageBypassesArmor();
  }
}


