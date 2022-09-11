package xyz.pixelatedw.mineminenomi.abilities.zushi;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.zushi.MokoProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class MokoAbility extends RepeaterAbility {
  public static final MokoAbility INSTANCE = new MokoAbility();

  
  public MokoAbility() {
    super("Moko", AbilityHelper.getDevilFruitCategory());
    setDescription("Sends a wave of gravitational waves towards the enemies, slowing them down");
    setMaxCooldown(14.0D);
    setMaxRepeaterCount(4, 6);
    
    this.onStartContinuityEvent = this::onStartContinuity;
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onStartContinuity(PlayerEntity player) {
    if (!ItemsHelper.isSword(player.getHeldItemMainhand())) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
      return false;
    } 
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    MokoProjectile proj = new MokoProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 2.0F);
    
    return true;
  }
}


