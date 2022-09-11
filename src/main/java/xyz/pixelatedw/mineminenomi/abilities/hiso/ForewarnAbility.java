package xyz.pixelatedw.mineminenomi.abilities.hiso;


import java.util.List;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class ForewarnAbility extends Ability {
  public static final ForewarnAbility INSTANCE = new ForewarnAbility();

  
  public ForewarnAbility() {
    super("Animal Forewarning", AbilityHelper.getDevilFruitCategory());
    setDescription("Allows the user to communicate with nearby animals and learn when the next rain or clear weathers will come");
    this.onUseEvent = this::onUseEvent;
    setMaxCooldown(10.0D);
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    List<AnimalEntity> nearby = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { AnimalEntity.class });
    
    if (nearby.size() <= 0) {
      return true;
    }
    if (nearby.size() > 0 && !player.world.isRemote) {
      
      int rainWeather = player.world.getWorldInfo().getRainTime();
      int clearWeather = player.world.getWorldInfo().getClearWeatherTime();
      
      if (clearWeather == 0) {
        player.sendMessage((ITextComponent)new StringTextComponent("Next rain will happen in " + (player.world.getWorldInfo().getRainTime() / 1200) + " minutes"));
      } else if (rainWeather == 0) {
        player.sendMessage((ITextComponent)new StringTextComponent("Next clear weather will happen in " + (player.world.getWorldInfo().getThunderTime() / 1200) + " minutes"));
      } 
    } 
    return true;
  }
}


