package xyz.pixelatedw.mineminenomi.abilities.cyborg;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.RadicalBeamProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class RadicalBeamAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new RadicalBeamAbility();
  
  private static final int COLA_REQUIRED = 30;

  
  public RadicalBeamAbility() {
    super("Radical Beam", AbilityHelper.getRacialCategory());
    setMaxCooldown(15.0D);
    setMaxChargeTime(3.0D);
    setDescription("The user launches a powerful beam of energy at the opponent\nConsumes §230§r cola");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int i) {
    if (i == 39) {
      player.world.playMovingSound(null, (Entity)player, ModSounds.PRE_CYBORG_BEAM_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
  }
  
  private boolean onStartChargingEvent(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    if (props.getCola() - 30 < 0) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
      return false;
    } 
    
    player.world.playMovingSound(null, (Entity)player, ModSounds.CHARGE_CYBORG_BEAM_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    return true;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    player.world.playMovingSound(null, (Entity)player, ModSounds.CYBORG_BEAM_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    RadicalBeamProjectile proj = new RadicalBeamProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.75F, 1.0F);
    
    props.alterCola(-30);
    WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
    
    return true;
  }
}


