package xyz.pixelatedw.mineminenomi.abilities.doctor;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doctor.MedicBagExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class MedicBagExplosionAbility extends Ability {
  public static final MedicBagExplosionAbility INSTANCE = new MedicBagExplosionAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new MedicBagExplosionParticleEffect();

  
  public MedicBagExplosionAbility() {
    super("Medic Bag Explosion", AbilityHelper.getStyleCategory());
    setMaxCooldown(40.0D);
    setDescription("By sacrificing the medic bag's durability the user can fully heal themselves while applying debuffs to nearby enemies");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    boolean isHandEmpty = player.getHeldItemMainhand().isEmpty();
    
    ItemStack medicBag = !isHandEmpty ? player.getHeldItemMainhand() : (ItemStack)player.inventory.armorInventory.get(2);
    boolean hasMedicBag = (medicBag.getItem() == ModArmors.MEDIC_BAG);
    
    if (!hasMedicBag) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
      return false;
    } 
    
    player.heal(player.getMaxHealth() / 2.0F);
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    for (LivingEntity entity : targets) {
      
      int effect = (int)WyHelper.randomWithRange(0, 6);
      
      switch (effect) {
        
        case 0:
          entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 1));
        case 1:
          entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 1));
        case 2:
          entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 1));
        case 3:
          entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1));
        case 4:
          entity.addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));
        case 5:
          entity.addPotionEffect(new EffectInstance(Effects.WITHER, 200, 1));
        case 6:
          entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
      } 
    
    } 
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    medicBag.damageItem(250, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    
    return true;
  }
}


