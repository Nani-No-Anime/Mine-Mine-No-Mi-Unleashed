package xyz.pixelatedw.mineminenomi.abilities.mato;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class Mark1Ability extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new Mark1Ability();

  
  public Mark1Ability() {
    super("Mark1", AbilityHelper.getDevilFruitCategory());
    setDescription("by touching a mob, it locks the mob as it's target until another one is touched");
    this.onHitEntityEvent = this::onHitEntity;
  }


  
  public float onHitEntity(PlayerEntity player, LivingEntity target) {
    String targetname = target.getCommandSource().getName();
    player.sendStatusMessage((ITextComponent)new StringTextComponent("Right hand locked to §c" + targetname), true);
    
    return 1.0F;
  }
}


