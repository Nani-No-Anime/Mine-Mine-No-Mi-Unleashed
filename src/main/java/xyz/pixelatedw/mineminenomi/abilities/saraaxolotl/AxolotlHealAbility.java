package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;


import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.ChiyupopoParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AxolotlHealAbility extends Ability implements IFormRequiredAbility {
  public static final AxolotlHealAbility INSTANCE = new AxolotlHealAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new ChiyupopoParticleEffect();

  
  public AxolotlHealAbility() {
    super("Axolotl Heal", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(15.0D);
    setDescription("Regenerates all friendlies nearby based on how many other Axolotls are around.");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 15.0D, FactionHelper.getSameGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    
    int axolots = WyHelper.getEntitiesNear(player.getPosition(), player.world, 15.0D, entity -> (!entity.equals(player) && entity instanceof PlayerEntity && DevilFruitCapability.get((LivingEntity)entity).hasDevilFruit(ModAbilities.SARA_SARA_NO_MI_AXOLOTL)), new Class[0]).size();
    axolots = Math.max(axolots, 3);
    int time = Math.max(axolots * 300, 900);
    targets.remove(player);
    
    for (LivingEntity entity : targets) {
      entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, time, axolots));
    }
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)AxolotlHeavyZoanInfo.INSTANCE, (ZoanInfo)AxolotlWalkZoanInfo.INSTANCE };
  }
}


