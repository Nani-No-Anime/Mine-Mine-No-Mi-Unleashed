package xyz.pixelatedw.mineminenomi.abilities.chiyu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.ChiyupopoParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class KenpopoAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new KenpopoAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new ChiyupopoParticleEffect();

  
  public KenpopoAbility() {
    super("Kenpopo", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(50.0D);
    setDescription("Takes the target's life force, transforming it into a Dandelion. Dandelions can be eaten for healing");
    
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    if (target.getActivePotionEffect(Effects.WEAKNESS) != null) {
      return 0.0F;
    }
    target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 2000, 0));
    player.addItemStackToInventory(new ItemStack((IItemProvider)ModItems.DANDELION));
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return 0.0F;
  }
}


