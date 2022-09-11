package xyz.pixelatedw.mineminenomi.abilities.gasu;

import java.util.List;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class ShinokuniAbility extends ZoanAbility {
  public static final ShinokuniAbility INSTANCE = new ShinokuniAbility();
  
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1f8cc62b-ad78-4b25-b19c-76d23dd27517"), (Ability)INSTANCE, "Shinokuni Reach Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("84033b39-98f7-4312-acc5-92809e9732b1"), (Ability)INSTANCE, "Shinokuni Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier DAMAGE_REDUCTION = (new AbilityAttributeModifier(UUID.fromString("8a048300-6f4b-11eb-9439-0242ac130002"), (Ability)INSTANCE, "Shinokuni Damage Reduction Modifier", 0.2D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier HEALTH_BOOST = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Shinokuni Health Modifier", 20.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Shinokuni Strength Modifier", 7.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  
  public static final ParticleEffect PARTICLES = (ParticleEffect)new HaoshokuHakiParticleEffect(2);
  
  public Potion effect;

  
  public ShinokuniAbility() {
    super("Shinokuni", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms into a gas giant releasing toxic gas near the user, whatever §2Potion§r the user holds when transforminng is absorbed by Shinokuni and used its in area of effects");
    setThreshold(30.0D);
    
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
    addZoanModifier(ModAttributes.DAMAGE_REDUCTION, (AttributeModifier)DAMAGE_REDUCTION);
    addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_BOOST);
    addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
  }


  
  public boolean onStartContinuityEvent(PlayerEntity player) {
    if (!super.onStartContinuityEvent(player)) {
      return false;
    }
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20.0D, new Class[] { LivingEntity.class });
    list.remove(player);
    this.effect = PotionUtils.getPotionFromItem(player.getHeldItemMainhand());
    
    if (!this.effect.equals(Potions.EMPTY)) {
      
      player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount() - 1);
      list.forEach(target -> applyEffects(player, target));
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
    
    return true;
  }


  
  public void duringContinuityEvent(PlayerEntity player, int time) {
    super.duringContinuityEvent(player, time);
    
    if (this.effect.equals(Potions.EMPTY)) {
      return;
    }
    if (time % 100 == 0) {
      
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20.0D, new Class[] { LivingEntity.class });
      list.remove(player);
      for (LivingEntity target : list)
      {
        applyEffects(player, target);
      }
    } 
    
    if (time % 2 != 0) {
      return;
    }
    for (int i = 0; i < 10; i++) {
      ParticleType type;
      double offsetX = WyHelper.randomDouble() * 2.0D;
      double offsetY = WyHelper.randomDouble() * 2.0D;
      double offsetZ = WyHelper.randomDouble() * 2.0D;

      
      if (i % 5 == 0) {
        type = ModParticleTypes.GASU2;
      } else {
        type = ModParticleTypes.GASU;
      } 
      GenericParticleData data = new GenericParticleData(type);
      data.setMotion(0.0D, 0.1D, 0.0D);
      data.setLife(40);
      data.setSize(3.0F);
      WyHelper.spawnParticles(data, (ServerWorld)player.world, player.getPosX() + offsetX, player.getPosY() + offsetY, player.getPosZ() + offsetZ);
    } 
  }

  
  public void applyEffects(PlayerEntity player, LivingEntity entity) {
    for (EffectInstance inst : this.effect.getEffects()) {
      
      boolean isAlly = FactionHelper.getSameGroupPredicate((LivingEntity)player).test(entity);
      
      if (isAlly && inst.getPotion().isBeneficial()) {
        entity.addPotionEffect(new EffectInstance(inst.getPotion(), inst.getDuration(), inst.getAmplifier()));
      }
      if (!isAlly && !inst.getPotion().isBeneficial()) {
        entity.addPotionEffect(new EffectInstance(inst.getPotion(), inst.getDuration(), inst.getAmplifier()));
      }
    } 
  }

  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    if (!super.onEndContinuityEvent(player)) {
      return false;
    }
    int cooldown = (int)(5L + Math.round(this.continueTime / 18.0D));
    setMaxCooldown(cooldown);
    
    this.effect = null;
    return true;
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)ShinokuniZoanInfo.INSTANCE;
  }
}


