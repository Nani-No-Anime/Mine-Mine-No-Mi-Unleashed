package xyz.pixelatedw.mineminenomi.abilities.yomi;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yomi.KasuriutaFubukiGiriParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class KasuriutaFubukiGiriAbility extends Ability implements IMultiTargetAbility {
  public static final KasuriutaFubukiGiriAbility INSTANCE = new KasuriutaFubukiGiriAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new KasuriutaFubukiGiriParticleEffect();

  
  public KasuriutaFubukiGiriAbility() {
    super("Kasuriuta: Fubuki Giri", AbilityHelper.getDevilFruitCategory());
    setDescription("A quick slash at the enemy that also freezes them");
    setMaxCooldown(8.0D);
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    if (!ItemsHelper.isSword(player.getHeldItemMainhand())) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
      return false;
    } 
    
    clearTargets();
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 5.0D, 5.0D);
    player.setMotion(speed.x, (player.getMotion()).y, speed.z);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage()) {
      
      if (cooldownTimer % 2 == 0) {
        PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
      }
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      
      for (LivingEntity target : list) {
        
        if (isTarget(target) && player.canEntityBeSeen((Entity)target)) {
          
          target.attackEntityFrom(DamageSource.causePlayerDamage(player), 8.0F);
          EffectInstance instance = new EffectInstance(ModEffects.FROZEN, 70, 0);
          target.addPotionEffect(instance);
          ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
        } 
      } 
    } 
  }

  
  public boolean canDealDamage() {
    return (this.cooldown > 140.0D);
  }
}


