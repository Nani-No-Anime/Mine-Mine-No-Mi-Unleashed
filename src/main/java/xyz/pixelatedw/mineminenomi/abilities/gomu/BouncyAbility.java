package xyz.pixelatedw.mineminenomi.abilities.gomu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.NoFallDamageAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;

public class BouncyAbility
  extends NoFallDamageAbility {
  public static final BouncyAbility INSTANCE = new BouncyAbility();
  
  private boolean touchedGround = true;
  private float bounceValue = 0.0F;

  
  public BouncyAbility() {
    super("Bouncy", AbilityHelper.getDevilFruitCategory());
    setDescription("Makes the user bounce upon landing");
    hideInGUI(false);
  }


  
  protected void duringPassiveEvent(PlayerEntity player) {
    super.duringPassiveEvent(player);
    if (player.fallDistance > 12.0F || !this.touchedGround) {
      
      this.touchedGround = false;
      if (player.fallDistance != 0.0F) {
        this.bounceValue = player.fallDistance;
      }
      if (player.onGround && this.bounceValue / 30.0F > 0.0F) {
        
        this.touchedGround = true;
        player.setMotion((player.getMotion()).x, (this.bounceValue / 30.0F), (player.getMotion()).z);
        player.velocityChanged = true;
      } 
    } 
  }
}


