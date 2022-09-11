package xyz.pixelatedw.mineminenomi.abilities.kage;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class KagemushaAbility extends Ability {
  public static final KagemushaAbility INSTANCE = new KagemushaAbility();

  
  public KagemushaAbility() {
    super("Kagemusha", AbilityHelper.getDevilFruitCategory());
    setDescription("Allows the user to change its position with that of the Doppelman.");
    setMaxCooldown(5.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    DoppelmanAbility ability = (DoppelmanAbility)props.getEquippedAbility((Ability)DoppelmanAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (isActive && ability.getDoppelman() != null) {
      
      BlockPos temp = player.getPosition();
      player.setPositionAndUpdate(ability.getDoppelman().getPosX(), ability.getDoppelman().getPosY(), ability.getDoppelman().getPosZ());
      ability.getDoppelman().setPositionAndUpdate(temp.getX(), temp.getY(), temp.getZ());
      return true;
    } 
    
    return false;
  }
}


