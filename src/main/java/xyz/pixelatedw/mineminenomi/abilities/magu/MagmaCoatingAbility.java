package xyz.pixelatedw.mineminenomi.abilities.magu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

import java.awt.*;

public class MagmaCoatingAbility
  extends PunchAbility implements IPunchOverlayAbility, IParallelContinuousAbility {
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(160, 0, 0));
  
  public static final Ability INSTANCE = (Ability)new MagmaCoatingAbility();

  
  public MagmaCoatingAbility() {
    super("Magma Coating", AbilityHelper.getDevilFruitCategory());
    setThreshold(30.0D);
    setMaxCooldown(10.0D);
    setDescription("The user coats their arm with magma");
    setStoppingAfterHit(false);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 25.0F;
  }


  
  public AbilityOverlay getPunchOverlay(LivingEntity entity) {
    return OVERLAY;
  }
}


