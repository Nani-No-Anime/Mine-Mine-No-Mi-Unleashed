package xyz.pixelatedw.mineminenomi.abilities.cyborg;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.CoupDeVentProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class CoupDeVentAbility extends Ability {
  public static final Ability INSTANCE = new CoupDeVentAbility();
  
  private static final int COLA_REQUIRED = 30;

  
  public CoupDeVentAbility() {
    super("Coup de Vent", AbilityHelper.getRacialCategory());
    setMaxCooldown(12.0D);
    setDescription("Launches a powerful blast of compressed air that blows the opponent away\nConsumes §230§r cola");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    if (props.getCola() - 30 < 0) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
      return false;
    } 
    
    CoupDeVentProjectile proj = new CoupDeVentProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    
    props.alterCola(-30);
    WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
    
    return true;
  }
}


