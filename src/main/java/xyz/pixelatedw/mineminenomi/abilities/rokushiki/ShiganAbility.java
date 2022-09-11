package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class ShiganAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new ShiganAbility();

  
  public ShiganAbility() {
    super("Shigan", AbilityHelper.getRacialCategory());
    setMaxCooldown(8.0D);
    setDescription("The user thrusts their finger at the opponent to pierce them");
    
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    player.world.playSound(null, target.getPosition(), ModSounds.SHIGAN_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    return 20.0F;
  }
}


