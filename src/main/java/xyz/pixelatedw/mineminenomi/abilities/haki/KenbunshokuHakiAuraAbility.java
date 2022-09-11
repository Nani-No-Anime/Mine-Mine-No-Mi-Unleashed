package xyz.pixelatedw.mineminenomi.abilities.haki;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class KenbunshokuHakiAuraAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility {
  public static final KenbunshokuHakiAuraAbility INSTANCE = new KenbunshokuHakiAuraAbility();

  
  public KenbunshokuHakiAuraAbility() {
    super("Kenbunshoku Haki: Aura", AbilityHelper.getHakiCategory());
    setDescription("Uses Observation Haki to see the auras of all nearby creatures, differentiated by colors");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    WyNetwork.sendTo(new SSyncHakiDataPacket(player.getEntityId(), HakiDataCapability.get((LivingEntity)player)), player);
    WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), EntityStatsCapability.get((LivingEntity)player)), player);
    player.world.playSound(null, player.getPosition(), ModSounds.KENBUNSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 0);
    if (isOnMaxOveruse) {
      endContinuity(player);
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)WyHelper.clamp(Math.round(this.continueTime / 30.0D), 3L, 30L);
    setMaxCooldown(cooldown);
    return true;
  }


  
  public HakiType getType() {
    return HakiType.KENBUNSHOKU;
  }
}


