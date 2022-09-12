package xyz.pixelatedw.mineminenomi.abilities.kachi;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;

public class HotBoilingSpecialAbility extends PunchAbility implements IPunchOverlayAbility {
  public static final HotBoilingSpecialAbility INSTANCE = new HotBoilingSpecialAbility();
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.HOT_BOILING_SPECIAL_ARM).setColor("#FFFFFFAA");

  
  public HotBoilingSpecialAbility() {
    super("Hot Boiling Special", AbilityHelper.getDevilFruitCategory());
    setDescription("Hardens and heats up the fist of the user");
    setMaxCooldown(5.0D);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    SetOnFireEvent event = new SetOnFireEvent((LivingEntity)player, target, 20);
    if (!MinecraftForge.EVENT_BUS.post(event)) {
      target.setFire(20);
    }
  }
  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 20.0F;
  }


  
  public AbilityOverlay getPunchOverlay(LivingEntity entity) {
    return OVERLAY;
  }
}


