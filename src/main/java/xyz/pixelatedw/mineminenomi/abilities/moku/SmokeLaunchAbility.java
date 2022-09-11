package xyz.pixelatedw.mineminenomi.abilities.moku;


import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SmokeLaunchAbility
  extends Ability {
  public static final SmokeLaunchAbility INSTANCE = new SmokeLaunchAbility();

  
  public SmokeLaunchAbility() {
    super("Smoke Launch", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches all nearby entities surrounded by smoke in the sky.");
    setMaxCooldown(15.0D);
    this.onUseEvent = this::onUseEvent;
  }

  
  public boolean onUseEvent(PlayerEntity player) {
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.stream().filter(entity -> entity.isPotionActive(ModEffects.SMOKE)).forEach(entity -> {
          entity.removePotionEffect(ModEffects.SMOKE);
          
          entity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 200, 1));
        });
    
    return true;
  }
}


