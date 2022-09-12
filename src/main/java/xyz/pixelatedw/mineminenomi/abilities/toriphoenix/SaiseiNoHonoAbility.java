package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.BlueFlamesParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoen2ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

import java.util.List;

public class SaiseiNoHonoAbility
  extends PunchAbility implements IFormRequiredAbility {
  public static final SaiseiNoHonoAbility INSTANCE = new SaiseiNoHonoAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new BlueFlamesParticleEffect();
  private static final ParticleEffect PARTICLES2 = (ParticleEffect)new TenseiNoSoen2ParticleEffect();

  
  public SaiseiNoHonoAbility() {
    super("Saisei No Hono", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(15.0D);
    setDescription("Uses the blue flames to heal the target by hitting them \n\n§2SHIFT-USE§r: Will heal entities around the user including the user themselves");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isSneaking()) {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getSameGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      int healed = 0;
      for (LivingEntity target : targets) {
        
        healed++;
        target.heal(10.0F);
        target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));
        PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
      } 
      setMaxCooldown(Math.max(1, healed * 6));
      PARTICLES2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
      endContinuity(player);
      return false;
    } 
    
    return true;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    target.heal(10.0F);
    target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));
    
    PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
    setMaxCooldown(5.0D);
    endContinuity(player);
    
    return -1.0F;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE };
  }
}


