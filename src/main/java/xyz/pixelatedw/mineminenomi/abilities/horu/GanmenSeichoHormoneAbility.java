package xyz.pixelatedw.mineminenomi.abilities.horu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class GanmenSeichoHormoneAbility extends PunchAbility {
  public static final GanmenSeichoHormoneAbility INSTANCE = new GanmenSeichoHormoneAbility();

  
  public GanmenSeichoHormoneAbility() {
    super("Ganmen Seicho Hormone", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("By injecting special growth hormones into a target, their head expands to enormous proportions\n\n" + TextFormatting.DARK_GREEN + "SHIFT-USE" + TextFormatting.RESET + ": The user injects themselves, boosting Death Wink");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isSneaking()) {
      
      if (player.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE)) {
        
        player.removePotionEffect(ModEffects.GANMEN_SEICHO_HORMONE);
        for (ServerPlayerEntity serverPlayer : ((ServerWorld)player.world).getPlayers())
        {
          serverPlayer.connection.sendPacket((IPacket)new SRemoveEntityEffectPacket(player.getEntityId(), ModEffects.GANMEN_SEICHO_HORMONE));
        }
      }
      else {
        
        EffectInstance instance = new EffectInstance(ModEffects.GANMEN_SEICHO_HORMONE, 300, 0);
        player.addPotionEffect(instance);
        for (ServerPlayerEntity serverPlayer : ((ServerWorld)player.world).getPlayers())
        {
          serverPlayer.connection.sendPacket((IPacket)new SPlayEntityEffectPacket(player.getEntityId(), instance));
        }
      } 
      endContinuity(player);
      return false;
    } 
    
    return true;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    if (target.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE)) {
      
      target.removePotionEffect(ModEffects.GANMEN_SEICHO_HORMONE);
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SRemoveEntityEffectPacket(target.getEntityId(), ModEffects.GANMEN_SEICHO_HORMONE));
    }
    else {
      
      EffectInstance instance = new EffectInstance(ModEffects.GANMEN_SEICHO_HORMONE, 300, 0);
      target.addPotionEffect(instance);
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
    } 
    
    return 0.0F;
  }
}


