package xyz.pixelatedw.mineminenomi.abilities.swordsman;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class YakkodoriAbility extends Ability {
  public static final Ability INSTANCE = new YakkodoriAbility();

  
  public YakkodoriAbility() {
    super("Yakkodori", AbilityHelper.getStyleCategory());
    setMaxCooldown(6.0D);
    setDescription("Launches a crescent moon-shaped slash, which destroys everything in its path");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseSwordsmanAbilities((LivingEntity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
      return false;
    } 
    
    ItemStack stack = player.getHeldItemMainhand();
    stack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    
    YakkodoriProjectile proj = new YakkodoriProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    return true;
  }
}


