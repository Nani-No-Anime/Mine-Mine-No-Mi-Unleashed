package xyz.pixelatedw.mineminenomi.abilities.cyborg;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class ColaOverdriveAbility extends Ability {
  public static final Ability INSTANCE = new ColaOverdriveAbility();

  
  public ColaOverdriveAbility() {
    super("Cola Overdrive", AbilityHelper.getRacialCategory());
    setMaxCooldown(50.0D);
    setDescription("The user absorbs half of their cola at once to boost their physical abilities temporarily");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    double half = (props.getMaxCola() / 2.0F);
    
    if (props.getCola() - half < 0.0D) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
      return false;
    } 
    
    props.setCola((int)(props.getCola() - half));
    WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
    
    player.heal((float)(half / 1000.0D * player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()));
    player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 300, 3));
    player.addPotionEffect(new EffectInstance(Effects.SPEED, 300, 2));
    player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 300, 3));
    
    return true;
  }
}


