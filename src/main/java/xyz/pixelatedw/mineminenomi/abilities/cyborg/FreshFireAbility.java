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
import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.FreshFireProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class FreshFireAbility extends RepeaterAbility {
  public static final Ability INSTANCE = (Ability)new FreshFireAbility();
  
  private static final int COLA_REQUIRED = 2;

  
  public FreshFireAbility() {
    super("Fresh Fire", AbilityHelper.getRacialCategory());
    setMaxCooldown(5.0D);
    setMaxRepeaterCount(10, 3);
    setDescription("The user heats up and breathes fire like a flamethrower at the opponent\nConsumes §2" + (2 * this.maxRepeaterCount) + "§r cola");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    if (props.getCola() - 2 < 0) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
      return false;
    } 
    
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    FreshFireProjectile proj = new FreshFireProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 10.0F);
    
    props.alterCola(-2);
    WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
    
    return true;
  }
}


