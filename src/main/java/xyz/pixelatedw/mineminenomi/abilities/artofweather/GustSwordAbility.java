package xyz.pixelatedw.mineminenomi.abilities.artofweather;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.GustSwordProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class GustSwordAbility extends RepeaterAbility {
  public static final GustSwordAbility INSTANCE = new GustSwordAbility();

  
  public GustSwordAbility() {
    super("Gust Sword", AbilityHelper.getStyleCategory());
    setDescription("Fires a concentrated wind blast forward");
    setMaxCooldown(10.0D);
    setMaxRepeaterCount(6, 3);
    
    this.onStartContinuityEvent = this::onStartContinuity;
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onStartContinuity(PlayerEntity player) {
    if (!ItemsHelper.isClimaTact(player.getHeldItemMainhand())) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT, new Object[0]));
      return false;
    } 
    
    ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
    if (climaTact.getLevel() < 3) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT, new Object[0]));
      return false;
    } 
    
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    GustSwordProjectile proj = new GustSwordProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 3.0F);
    
    return true;
  }
}


