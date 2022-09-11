package xyz.pixelatedw.mineminenomi.abilities.moku;


import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class WhitePullAbility
  extends Ability {
  public static final WhitePullAbility INSTANCE = new WhitePullAbility();

  
  public WhitePullAbility() {
    super("White Pull", AbilityHelper.getDevilFruitCategory());
    setDescription("Pulls all nearby entities surrounded by smoke towards the user.");
    setMaxCooldown(10.0D);
    this.onUseEvent = this::onUseEvent;
  }

  
  public boolean onUseEvent(PlayerEntity player) {
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.stream().filter(entity -> entity.isPotionActive(ModEffects.SMOKE)).forEach(entity -> {
          entity.removePotionEffect(ModEffects.SMOKE);
          
          entity.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
        });
    
    return true;
  }
}


