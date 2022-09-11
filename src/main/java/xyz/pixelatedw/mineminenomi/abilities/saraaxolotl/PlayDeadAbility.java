package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class PlayDeadAbility extends ContinuousAbility implements IFormRequiredAbility {
  public static final PlayDeadAbility INSTANCE = new PlayDeadAbility();

  
  public PlayDeadAbility() {
    super("Play Dead", AbilityHelper.getDevilFruitCategory());
    setDescription("While playing dead the user focuses all of their power into regeneration.");
    setThreshold(10.0D);
    
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int passiveTimer) {
    EffectInstance instance = new EffectInstance(ModEffects.UNCONSCIOUS, 2, 1, false, false);
    player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2, 1, false, false));
    player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 1, false, false));
    player.addPotionEffect(instance);
    if (player instanceof ServerPlayerEntity) {
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(player.getEntityId(), instance));
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 20.0D) + 3;
    setMaxCooldown(cooldown);
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)AxolotlWalkZoanInfo.INSTANCE };
  }
}


