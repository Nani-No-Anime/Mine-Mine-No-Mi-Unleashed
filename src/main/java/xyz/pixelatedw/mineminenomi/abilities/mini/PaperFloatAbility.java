package xyz.pixelatedw.mineminenomi.abilities.mini;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseBothArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class PaperFloatAbility extends PassiveAbility implements IAnimatedAbility {
  public static final PaperFloatAbility INSTANCE = new PaperFloatAbility();
  
  private boolean hasAnimation = false;

  
  public PaperFloatAbility() {
    super("Paper Float", AbilityHelper.getDevilFruitCategory());
    setDescription("While in the mini form and holding a piece of paper the user is able to float");
    hideInGUI(false);
    
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  private void duringPassiveEvent(PlayerEntity player) {
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    player.fallDistance = 0.0F;
    
    Ability ability = abilityProps.getEquippedAbility((Ability)MiniMiniAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    boolean hasPaper = (player.getHeldItemMainhand().getItem() == Items.PAPER || player.getHeldItemOffhand().getItem() == Items.PAPER);
    boolean inAir = (!player.onGround && (player.getMotion()).y < 0.0D);
    
    this.hasAnimation = (isActive && hasPaper && inAir);
    if (this.hasAnimation) {
      player.setMotion((player.getMotion()).x, (player.getMotion()).y / 2.0D, (player.getMotion()).z);
    }
  }

  
  public IAnimation getAnimation() {
    return (IAnimation)RaiseBothArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return this.hasAnimation;
  }
}


