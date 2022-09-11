package xyz.pixelatedw.mineminenomi.abilities.artofweather;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherEggProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class WeatherEggAbility extends Ability {
  public static final WeatherEggAbility INSTANCE = new WeatherEggAbility();

  
  public WeatherEggAbility() {
    super("Weather Egg", AbilityHelper.getStyleCategory());
    setDescription("Instantly creates a Weather Cloud as if combining a Cool Ball and a Heat Ball");
    setMaxCooldown(16.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!ItemsHelper.isClimaTact(player.getHeldItemMainhand())) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT, new Object[0]));
      return false;
    } 
    
    ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
    if (climaTact.getLevel() < 3) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT, new Object[0]));
      return false;
    } 
    
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
    WeatherEggProjectile proj = new WeatherEggProjectile(player.world, (LivingEntity)player);
    proj.setLocationAndAngles(trace.getHitVec().getX(), player.getPosY() + player.getEyeHeight() - 0.5D, trace.getHitVec().getZ(), player.rotationYaw, player.rotationPitch);
    proj.setMotion(0.0D, 0.3D, 0.0D);
    player.world.addEntity((Entity)proj);
    
    return true;
  }
}


